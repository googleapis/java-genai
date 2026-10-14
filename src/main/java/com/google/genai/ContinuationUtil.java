/*
 * Copyright 2026 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.genai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.LongNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ImmutableSet;
import com.google.genai.errors.ApiException;
import com.google.genai.types.Candidate;
import com.google.genai.types.Content;
import com.google.genai.types.FinishReason;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.GenerateContentResponseUsageMetadata;
import com.google.genai.types.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.function.Function;
import org.jspecify.annotations.Nullable;

/**
 * Automatic continuation: sending a request again with the continuation token of a response that
 * stopped before the model finished, until it finishes.
 */
final class ContinuationUtil {

  private static final ImmutableSet<String> END_OF_GENERATION_FIELDS =
      ImmutableSet.of("continuationToken", "finishReason", "finishMessage");

  private static final ImmutableSet<String> MODALITY_TOKEN_COUNT_FIELDS =
      ImmutableSet.of("modality", "tokenCount");

  /**
   * How many times in a row a stream retries a request that dropped before sending a new checkpoint
   * token or any content, before the error reaches the caller.
   */
  static final int MAX_STREAM_RECONNECTS_WITHOUT_PROGRESS = 3;

  private ContinuationUtil() {}

  /** Whether a request made with {@code config} is continued: unless the config turns it off. */
  static boolean isEnabled(@Nullable GenerateContentConfig config) {
    return config == null || config.automaticContinuation().orElse(true);
  }

  /**
   * Sends the request through {@code send}, then sends it again with each response's continuation
   * token until the model finishes, and returns the responses merged into one. The first request
   * goes out with {@code config} as given.
   */
  static GenerateContentResponse generate(
      @Nullable GenerateContentConfig config,
      Function<GenerateContentConfig, GenerateContentResponse> send) {
    List<GenerateContentResponse> responses = new ArrayList<>();
    GenerateContentConfig requestConfig = config;
    while (true) {
      GenerateContentResponse response = send.apply(requestConfig);
      responses.add(response);
      Optional<byte[]> token = nextToken(response);
      if (!token.isPresent()) {
        return merge(responses);
      }
      requestConfig = withContinuationToken(config, token.get());
    }
  }

  /** The asynchronous counterpart of {@link #generate}. */
  static CompletableFuture<GenerateContentResponse> generateAsync(
      @Nullable GenerateContentConfig config,
      Function<GenerateContentConfig, CompletableFuture<GenerateContentResponse>> send) {
    return continueAsync(config, config, send, new ArrayList<>())
        .thenApply(ContinuationUtil::merge);
  }

  /**
   * Continues a stream into a new request whenever one stops before the model finished. The chunks
   * of every request reach the caller in order, with usage metadata accumulated across requests. A
   * request that drops before any content or new checkpoint is retried from its token; prompt
   * feedback ends the stream; and a resumed request that ends without a finish reason, prompt
   * feedback or new checkpoint simply ends the stream, unless a request failed since the stream
   * last made progress, in which case that error is thrown. A request the API rejects (an {@link
   * ApiException} before any chunk) is never retried here: that is left to the HTTP retry options.
   */
  static ResponseStream.Continuation<GenerateContentResponse> streamContinuation(
      @Nullable GenerateContentConfig config,
      Function<GenerateContentConfig, ResponseStream<GenerateContentResponse>> send) {
    return new StreamContinuation(config, send);
  }

  /**
   * The state of one {@code generateContentStream} call with automatic continuation.
   *
   * <p>A stream is made of one or more requests (hops); each hop after the first resumes from a
   * continuation token. {@link ResponseStream} drives the loop: it hands every chunk to {@link
   * #observe} and then {@link #transform}, asks {@link #canResume} when a hop fails, and calls
   * {@link #next} when a hop ends (or fails and can resume) to get the stream of the next hop.
   *
   * <p>Loop state (the usage metadata accumulated across hops, the token to resume from, the count
   * of reconnects without progress and the latest error since the stream last made progress)
   * carries across hops. Per-hop state lives in a {@link StreamHop} that {@link #startHop} replaces
   * wholesale, so it can't leak from one hop into the next.
   *
   * <p>Usage metadata: within a hop, each chunk's usage metadata is the server's running total for
   * that hop, so the latest one is the hop's usage (chunks are not summed). Across hops, the hops'
   * usage is summed, since each hop is a separate request. Each yielded chunk that has usage
   * metadata carries the usage of all previous hops plus its own.
   *
   * <p>Two rules decide how a failed hop ends (see {@link #endHop}):
   *
   * <ul>
   *   <li>A hop the API rejects before it receives any chunk (an {@link ApiException}, from an HTTP
   *       error status or an error event in the stream) throws at once and is not retried. Retrying
   *       a rejected request is the job of the HTTP client's retry options, which back off and
   *       which the caller can turn off. Transport errors and clean empty closes are still retried.
   *   <li>Errors are not lost across retries: the latest error since the stream last made progress
   *       is kept, and thrown if the stream gives up without the API ending it (with a finish
   *       reason or prompt feedback), e.g. after a drop followed by clean empty closes.
   * </ul>
   *
   * <p>Recording a chunk ({@link #observe}) and rewriting it ({@link #transform}) stay two steps
   * because that is the shape of {@link ResponseStream.Continuation}, which {@link ResponseStream}
   * calls back to back for each chunk.
   */
  private static final class StreamContinuation
      implements ResponseStream.Continuation<GenerateContentResponse> {

    /** What one request (hop) of the stream has shown so far. */
    private static final class StreamHop {
      /** The token the hop was sent with, or empty for the first request. */
      final Optional<byte[]> startToken;

      /** The latest checkpoint token of the hop, or empty after a non-resumable finish reason. */
      Optional<byte[]> token = Optional.empty();

      Optional<FinishReason> finishReason = Optional.empty();
      boolean hasPromptFeedback = false;
      boolean hasYieldedContent = false;
      boolean receivedAnyChunk = false;

      /** The error the hop failed with, or null while it has not failed. */
      @Nullable RuntimeException error;

      /**
       * Usage for the whole stream so far: the usage of all previous hops plus this hop's latest
       * usage metadata. Within a hop, each chunk's usage metadata is the server's running total for
       * the hop, so it replaces the previous value (it is not summed); a chunk without usage
       * metadata leaves it unchanged.
       */
      Optional<GenerateContentResponseUsageMetadata> usage = Optional.empty();

      StreamHop(Optional<byte[]> startToken) {
        this.startToken = startToken;
      }

      /**
       * The error the API rejected the hop's request with, or null: an {@link ApiException} (from
       * an HTTP error status or an error event in the stream) before the hop received any chunk.
       * Transport errors such as a dropped connection are not rejections.
       */
      @Nullable ApiException rejection() {
        return error instanceof ApiException && !receivedAnyChunk ? (ApiException) error : null;
      }
    }

    private final @Nullable GenerateContentConfig config;
    private final Function<GenerateContentConfig, ResponseStream<GenerateContentResponse>> send;

    // Loop state.
    // Usage of all finished hops, summed. Each hop is a separate request whose usage the server
    // reports independently, so hop totals are added (unlike chunks within a hop; see
    // StreamHop.usage).
    private Optional<GenerateContentResponseUsageMetadata> accumulatedUsageAcrossHops =
        Optional.empty();
    // The token the next hop resumes from.
    private Optional<byte[]> resumeToken = Optional.empty();
    // Consecutive hops that ended without a new checkpoint token.
    private int reconnectsWithoutProgress = 0;
    // The latest error since the stream last made progress, thrown if the stream gives up.
    private @Nullable RuntimeException lastError;

    // The state of the hop being read. The first request is sent by the caller.
    private StreamHop hop = new StreamHop(Optional.empty());

    StreamContinuation(
        @Nullable GenerateContentConfig config,
        Function<GenerateContentConfig, ResponseStream<GenerateContentResponse>> send) {
      this.config = config;
      this.send = send;
    }

    /** Records what one chunk of the hop shows. */
    @Override
    public void observe(GenerateContentResponse chunk) {
      hop.receivedAnyChunk = true;
      // Prompt feedback ends the generation: the stream is not resumed after it.
      if (chunk.promptFeedback().isPresent()) {
        hop.hasPromptFeedback = true;
      }
      Optional<Candidate> candidate = firstCandidate(chunk);
      if (!candidate.isPresent()) {
        return;
      }
      if (candidate.get().content().flatMap(Content::parts).map(p -> !p.isEmpty()).orElse(false)) {
        hop.hasYieldedContent = true;
      }
      if (candidate.get().finishReason().isPresent()) {
        hop.finishReason = candidate.get().finishReason();
      }
      // A long stream also puts checkpoint tokens on chunks before the last one, so the token
      // to resume from is the last one seen unless the hop ends with a non-resumable reason.
      Optional<byte[]> chunkToken = candidate.get().continuationToken().filter(t -> t.length > 0);
      if (chunkToken.isPresent()) {
        hop.token = chunkToken;
      } else if (candidate.get().finishReason().isPresent()
          && !isContinuable(candidate.get().finishReason())) {
        hop.token = Optional.empty();
      }
    }

    /**
     * Returns {@code chunk} without its finish reason when the stream will be resumed, and with its
     * usage metadata (if any) set to this hop's latest usage plus the usage of all previous hops.
     */
    @Override
    public GenerateContentResponse transform(GenerateContentResponse chunk) {
      chunk =
          clearIntermediateFinishReason(
              chunk, hop.hasPromptFeedback ? Optional.empty() : hop.token);
      if (chunk.usageMetadata().isPresent()) {
        GenerateContentResponseUsageMetadata chunkUsage = chunk.usageMetadata().get();
        if (accumulatedUsageAcrossHops.isPresent()) {
          GenerateContentResponseUsageMetadata mergedUsage =
              mergeUsageMetadata(accumulatedUsageAcrossHops.get(), chunkUsage);
          hop.usage = Optional.of(mergedUsage);
          chunk = chunk.toBuilder().usageMetadata(mergedUsage).build();
        } else {
          hop.usage = Optional.of(chunkUsage);
        }
      }
      return chunk;
    }

    // Called by the stream when the current request fails with error. When this returns false the
    // stream rethrows error.
    @Override
    public boolean canResume(RuntimeException error) {
      hop.error = error;
      return hop.rejection() == null && nextResumeToken().isPresent();
    }

    // Called by the stream when the current request fails and canResume() is true, or when the
    // current request ends.
    @Override
    public @Nullable ResponseStream<GenerateContentResponse> next() {
      if (!endHop()) {
        // Nothing to resume from: the stream ends, keeping everything already returned.
        return null;
      }
      while (true) {
        GenerateContentConfig callConfig = startHop();
        try {
          return send.apply(callConfig);
        } catch (RuntimeException e) {
          // The request failed before reading anything. endHop() throws an ApiException (the API
          // rejected the request) and any error it can't resume from; otherwise the request is
          // retried from the same token.
          hop.error = e;
          if (!endHop()) {
            throw e;
          }
        }
      }
    }

    /**
     * Ends the current hop. When the stream can resume, sets the token to resume from, updates the
     * reconnect count and commits the hop's usage to the loop, and returns true.
     *
     * <p>Otherwise, throws: the hop's error if it failed; or, when the hop ended without a finish
     * reason or prompt feedback, the latest error since the stream last made progress, if any.
     * Returns false when the stream simply ends. A hop that failed with an {@link ApiException}
     * before receiving any chunk throws that error without being retried.
     */
    private boolean endHop() {
      ApiException rejection = hop.rejection();
      if (rejection != null) {
        throw rejection;
      }
      RuntimeException error = hop.error;
      boolean madeProgress =
          hop.hasYieldedContent
              || (hop.token.isPresent() && !isSameToken(hop.token, hop.startToken));
      if (error != null) {
        lastError = error;
      } else if (madeProgress) {
        lastError = null;
      }
      Optional<byte[]> resumeFrom = nextResumeToken();
      if (!resumeFrom.isPresent()) {
        if (error != null) {
          throw error;
        }
        // Never throw a stale error when the API itself ended the stream.
        boolean gaveUp = !hop.finishReason.isPresent() && !hop.hasPromptFeedback;
        if (gaveUp && lastError != null) {
          throw lastError;
        }
        return false;
      }
      if (isSameToken(resumeFrom, hop.startToken)) {
        reconnectsWithoutProgress++;
      } else {
        reconnectsWithoutProgress = 0;
      }
      if (hop.usage.isPresent()) {
        accumulatedUsageAcrossHops = hop.usage;
      }
      resumeToken = resumeFrom;
      return true;
    }

    /** Starts a new hop from the resume token and returns the config to send it with. */
    private GenerateContentConfig startHop() {
      hop = new StreamHop(resumeToken);
      return withContinuationToken(config, resumeToken.get());
    }

    /**
     * The token to resume from once the current hop ends or fails, or empty to stop.
     *
     * <p>Checkpoint tokens only come part way through a request, so a request can drop before it
     * sends a token of its own. Unless it already handed out content, it is then retried from the
     * token it was sent with, up to {@link #MAX_STREAM_RECONNECTS_WITHOUT_PROGRESS} times in a row.
     */
    private Optional<byte[]> nextResumeToken() {
      if (hop.hasPromptFeedback) {
        return Optional.empty();
      }
      if (hop.finishReason.isPresent()) {
        // A token the request was sent with would only repeat the request.
        return canResumeStream(hop.token, hop.finishReason)
                && !isSameToken(hop.token, hop.startToken)
            ? hop.token
            : Optional.empty();
      }
      if (hop.token.isPresent() && !isSameToken(hop.token, hop.startToken)) {
        return hop.token;
      }
      if (!hop.startToken.isPresent()
          || hop.hasYieldedContent
          || reconnectsWithoutProgress >= MAX_STREAM_RECONNECTS_WITHOUT_PROGRESS) {
        return Optional.empty();
      }
      return hop.startToken;
    }
  }

  private static boolean isSameToken(Optional<byte[]> a, Optional<byte[]> b) {
    return a.isPresent() && b.isPresent() && Arrays.equals(a.get(), b.get());
  }

  /**
   * Waits for {@code future}, rethrowing an unchecked failure as itself rather than wrapped in a
   * {@link CompletionException}, so a stream continued by an asynchronous client fails the same way
   * as one continued by a synchronous client.
   */
  static <T> T join(CompletableFuture<T> future) {
    try {
      return future.join();
    } catch (CompletionException e) {
      if (e.getCause() instanceof RuntimeException) {
        throw (RuntimeException) e.getCause();
      }
      throw e;
    }
  }

  /**
   * Merges the responses of one continued generation into one response, by the merge rules of the
   * SDK design doc. Lists such as the parts are concatenated in order, each candidate is merged
   * with the one of the same index, and token counts are summed, while the values describing how a
   * candidate's generation ended, such as the finish reason, come from the last response.
   */
  static GenerateContentResponse merge(List<GenerateContentResponse> responses) {
    if (responses.size() == 1) {
      return responses.get(0);
    }
    ObjectNode merged = null;
    Optional<HttpResponse> sdkHttpResponse = Optional.empty();
    for (GenerateContentResponse response : responses) {
      // sdkHttpResponse holds the raw body, continuation token and all, so it is kept out of the
      // merge, and the merged response gets the last one.
      if (response.sdkHttpResponse().isPresent()) {
        sdkHttpResponse = response.sdkHttpResponse();
      }
      ObjectNode node = JsonSerializable.objectMapper.valueToTree(response);
      node.remove("sdkHttpResponse");
      merged = merged == null ? node : mergeObjects(merged, node, false, ImmutableSet.of());
    }
    GenerateContentResponse result =
        JsonSerializable.fromJsonNode(merged, GenerateContentResponse.class);
    Optional<Integer> firstInitialPrompt =
        responses.stream()
            .map(GenerateContentResponse::initialPromptTokenCount)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .findFirst();
    if (result.usageMetadata().isPresent() && firstInitialPrompt.isPresent()) {
      result =
          result.toBuilder()
              .usageMetadata(
                  result.usageMetadata().get().toBuilder()
                      .firstHopPromptTokenCount(firstInitialPrompt.get())
                      .build())
              .build();
    }
    if (!sdkHttpResponse.isPresent()) {
      return result;
    }
    return result.toBuilder().sdkHttpResponse(sdkHttpResponse.get()).build();
  }

  /**
   * Merges usage metadata from a previous hop and the current chunk, summing token counts across
   * hops while preserving the initial request's prompt token count.
   */
  static GenerateContentResponseUsageMetadata mergeUsageMetadata(
      GenerateContentResponseUsageMetadata prev, GenerateContentResponseUsageMetadata curr) {
    ObjectNode prevNode = JsonSerializable.objectMapper.valueToTree(prev);
    ObjectNode currNode = JsonSerializable.objectMapper.valueToTree(curr);
    ObjectNode mergedNode = mergeObjects(prevNode, currNode, true, ImmutableSet.of());
    GenerateContentResponseUsageMetadata merged =
        JsonSerializable.fromJsonNode(mergedNode, GenerateContentResponseUsageMetadata.class);
    Optional<Integer> firstHop =
        prev.initialPromptTokenCount().isPresent()
            ? prev.initialPromptTokenCount()
            : curr.initialPromptTokenCount();
    if (firstHop.isPresent()) {
      merged = merged.toBuilder().firstHopPromptTokenCount(firstHop.get()).build();
    }
    return merged;
  }

  private static CompletableFuture<List<GenerateContentResponse>> continueAsync(
      @Nullable GenerateContentConfig config,
      @Nullable GenerateContentConfig requestConfig,
      Function<GenerateContentConfig, CompletableFuture<GenerateContentResponse>> send,
      List<GenerateContentResponse> responses) {
    return send.apply(requestConfig)
        .thenCompose(
            response -> {
              responses.add(response);
              Optional<byte[]> token = nextToken(response);
              if (!token.isPresent()) {
                return CompletableFuture.completedFuture(responses);
              }
              return continueAsync(
                  config, withContinuationToken(config, token.get()), send, responses);
            });
  }

  /** The token to send next, or empty when the response is complete. */
  private static Optional<byte[]> nextToken(GenerateContentResponse response) {
    Optional<Candidate> candidate = firstCandidate(response);
    if (!candidate.isPresent() || !isContinuable(candidate.get().finishReason())) {
      return Optional.empty();
    }
    return candidate.get().continuationToken().filter(t -> t.length > 0);
  }

  // Per the continuation protocol, when a continuationToken is present the backend only sets
  // finishReason to null (intermediate checkpoint chunk) or CONTINUATION (stream end). MAX_TOKENS
  // means the caller's maxOutputTokens, which the server counts across all the requests, is spent.
  private static boolean isContinuable(Optional<FinishReason> finishReason) {
    return !finishReason.isPresent()
        || finishReason.get().knownEnum() == FinishReason.Known.CONTINUATION;
  }

  static boolean canResumeStream(Optional<byte[]> token, Optional<FinishReason> finishReason) {
    return token.isPresent() && token.get().length > 0 && isContinuable(finishReason);
  }

  static GenerateContentResponse clearIntermediateFinishReason(
      GenerateContentResponse chunk, Optional<byte[]> token) {
    Optional<Candidate> candidate = firstCandidate(chunk);
    if (!candidate.isPresent()) {
      return chunk;
    }
    if (candidate.get().finishReason().isPresent()
        && canResumeStream(token, candidate.get().finishReason())) {
      List<Candidate> updatedCandidates = new ArrayList<>(chunk.candidates().get());
      updatedCandidates.set(0, candidate.get().toBuilder().clearFinishReason().build());
      return chunk.toBuilder().candidates(updatedCandidates).build();
    }
    return chunk;
  }

  static Optional<Candidate> firstCandidate(GenerateContentResponse response) {
    if (!response.candidates().isPresent() || response.candidates().get().isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(response.candidates().get().get(0));
  }

  private static GenerateContentConfig withContinuationToken(
      @Nullable GenerateContentConfig config, byte[] token) {
    GenerateContentConfig.Builder builder =
        config == null ? GenerateContentConfig.builder() : config.toBuilder();
    return builder.continuationToken(token).build();
  }

  private static ObjectNode mergeObjects(
      ObjectNode prev, ObjectNode curr, boolean sumAllNumbers, Set<String> latestOnly) {
    Set<String> keys = new LinkedHashSet<>();
    prev.fieldNames().forEachRemaining(keys::add);
    curr.fieldNames().forEachRemaining(keys::add);
    ObjectNode merged = JsonSerializable.objectMapper.createObjectNode();
    for (String key : keys) {
      JsonNode value =
          latestOnly.contains(key)
              ? curr.get(key)
              : mergeValues(key, prev.get(key), curr.get(key), sumAllNumbers);
      if (value != null && !value.isNull()) {
        merged.set(key, value);
      }
    }
    return merged;
  }

  private static @Nullable JsonNode mergeValues(
      String key, @Nullable JsonNode prev, @Nullable JsonNode curr, boolean sumAllNumbers) {
    // Candidates go through mergeCandidates even when the later response has none, so the
    // candidates kept from earlier responses lose their continuation state.
    if (key.equals("candidates") && prev != null && prev.isArray()) {
      return mergeCandidates(
          (ArrayNode) prev,
          curr != null && curr.isArray()
              ? (ArrayNode) curr
              : JsonSerializable.objectMapper.createArrayNode());
    }
    if (curr == null || curr.isNull()) {
      return prev;
    }
    if (prev == null || prev.isNull()) {
      return curr;
    }
    if (prev.isObject() && curr.isObject()) {
      return mergeObjects(
          (ObjectNode) prev,
          (ObjectNode) curr,
          sumAllNumbers || key.equals("usageMetadata"),
          ImmutableSet.of());
    }
    if (prev.isArray() && curr.isArray()) {
      return mergeArrays(key, (ArrayNode) prev, (ArrayNode) curr);
    }
    if (prev.isNumber()
        && curr.isNumber()
        && (sumAllNumbers || key.endsWith("Count") || key.endsWith("Sum"))) {
      if (prev.isIntegralNumber() && curr.isIntegralNumber()) {
        return LongNode.valueOf(prev.longValue() + curr.longValue());
      }
      return DoubleNode.valueOf(prev.doubleValue() + curr.doubleValue());
    }
    return curr;
  }

  private static ArrayNode mergeArrays(String key, ArrayNode prev, ArrayNode curr) {
    JsonNode first = prev.size() > 0 ? prev.get(0) : curr.size() > 0 ? curr.get(0) : null;
    if (first != null && isModalityTokenCount(first)) {
      return mergeModalityTokenCounts(prev, curr);
    }
    if (key.equals("safetyRatings")) {
      return latestPerCategory(prev, curr);
    }
    ArrayNode merged = JsonSerializable.objectMapper.createArrayNode();
    merged.addAll(prev);
    merged.addAll(curr);
    return merged;
  }

  // Each candidate is merged with the one that has the same index in the next response. A candidate
  // that only one response has is kept.
  private static ArrayNode mergeCandidates(ArrayNode prev, ArrayNode curr) {
    if (curr.size() == 0) {
      // The later response has no candidates, e.g. because its prompt was blocked. The earlier
      // candidates keep their output, but not the continuation state of a generation that ended.
      ArrayNode result = JsonSerializable.objectMapper.createArrayNode();
      ObjectNode empty = JsonSerializable.objectMapper.createObjectNode();
      for (JsonNode candidate : prev) {
        result.add(mergeObjects((ObjectNode) candidate, empty, false, END_OF_GENERATION_FIELDS));
      }
      return result;
    }
    Map<Integer, ObjectNode> merged = new LinkedHashMap<>();
    for (int position = 0; position < prev.size(); position++) {
      merged.put(candidateIndex(prev.get(position), position), (ObjectNode) prev.get(position));
    }
    for (int position = 0; position < curr.size(); position++) {
      merged.merge(
          candidateIndex(curr.get(position), position),
          (ObjectNode) curr.get(position),
          (previous, current) ->
              mergeObjects(
                  previous,
                  shiftGroundingSupports(previous, current),
                  false,
                  END_OF_GENERATION_FIELDS));
    }
    ArrayNode result = JsonSerializable.objectMapper.createArrayNode();
    merged.values().forEach(result::add);
    return result;
  }

  private static ObjectNode shiftGroundingSupports(ObjectNode previous, ObjectNode current) {
    JsonNode currGmNode = current.get("groundingMetadata");
    if (currGmNode == null || !currGmNode.isObject()) {
      return current;
    }
    JsonNode currSupportsNode = currGmNode.get("groundingSupports");
    if (currSupportsNode == null || !currSupportsNode.isArray() || currSupportsNode.size() == 0) {
      return current;
    }

    int partOffset = 0;
    JsonNode prevContentNode = previous.get("content");
    if (prevContentNode != null && prevContentNode.isObject()) {
      JsonNode prevPartsNode = prevContentNode.get("parts");
      if (prevPartsNode != null && prevPartsNode.isArray()) {
        partOffset = prevPartsNode.size();
      }
    }

    int chunkOffset = 0;
    JsonNode prevGmNode = previous.get("groundingMetadata");
    if (prevGmNode != null && prevGmNode.isObject()) {
      JsonNode prevChunksNode = prevGmNode.get("groundingChunks");
      if (prevChunksNode != null && prevChunksNode.isArray()) {
        chunkOffset = prevChunksNode.size();
      }
    }

    if (partOffset == 0 && chunkOffset == 0) {
      return current;
    }

    ObjectNode shiftedCandidate = current.deepCopy();
    ObjectNode shiftedGm = (ObjectNode) shiftedCandidate.get("groundingMetadata");
    ArrayNode shiftedSupports = (ArrayNode) shiftedGm.get("groundingSupports");
    for (JsonNode supportNode : shiftedSupports) {
      if (!supportNode.isObject()) {
        continue;
      }
      ObjectNode supportObj = (ObjectNode) supportNode;
      if (chunkOffset > 0) {
        JsonNode indicesNode = supportObj.get("groundingChunkIndices");
        if (indicesNode != null && indicesNode.isArray()) {
          ArrayNode indicesArr = (ArrayNode) indicesNode;
          for (int i = 0; i < indicesArr.size(); i++) {
            JsonNode idx = indicesArr.get(i);
            if (idx != null && idx.canConvertToInt()) {
              indicesArr.set(i, LongNode.valueOf(idx.longValue() + chunkOffset));
            }
          }
        }
      }
      if (partOffset > 0) {
        JsonNode segmentNode = supportObj.get("segment");
        if (segmentNode != null && segmentNode.isObject()) {
          ObjectNode segmentObj = (ObjectNode) segmentNode;
          JsonNode partIdxNode = segmentObj.get("partIndex");
          long prevPartIdx =
              partIdxNode != null && partIdxNode.canConvertToInt() ? partIdxNode.longValue() : 0L;
          segmentObj.set("partIndex", LongNode.valueOf(prevPartIdx + partOffset));
        }
      }
    }
    return shiftedCandidate;
  }

  // A candidate without an index is matched by its position.
  private static int candidateIndex(JsonNode candidate, int position) {
    JsonNode index = candidate.get("index");
    return index != null && index.canConvertToInt() ? index.intValue() : position;
  }

  private static boolean isModalityTokenCount(JsonNode element) {
    if (!element.isObject() || element.size() == 0) {
      return false;
    }
    Iterator<String> names = element.fieldNames();
    while (names.hasNext()) {
      if (!MODALITY_TOKEN_COUNT_FIELDS.contains(names.next())) {
        return false;
      }
    }
    return true;
  }

  private static ArrayNode mergeModalityTokenCounts(ArrayNode prev, ArrayNode curr) {
    Map<JsonNode, Long> totals = new LinkedHashMap<>();
    for (JsonNode item : concat(prev, curr)) {
      JsonNode modality = item.get("modality");
      long count = item.has("tokenCount") ? item.get("tokenCount").longValue() : 0L;
      totals.merge(modality, count, Long::sum);
    }
    ArrayNode merged = JsonSerializable.objectMapper.createArrayNode();
    for (Map.Entry<JsonNode, Long> total : totals.entrySet()) {
      ObjectNode item = merged.addObject();
      if (total.getKey() != null) {
        item.set("modality", total.getKey());
      }
      item.put("tokenCount", total.getValue());
    }
    return merged;
  }

  private static ArrayNode latestPerCategory(ArrayNode prev, ArrayNode curr) {
    Map<JsonNode, JsonNode> latest = new LinkedHashMap<>();
    for (JsonNode rating : concat(prev, curr)) {
      latest.put(rating.get("category"), rating);
    }
    ArrayNode merged = JsonSerializable.objectMapper.createArrayNode();
    latest.values().forEach(merged::add);
    return merged;
  }

  private static List<JsonNode> concat(ArrayNode prev, ArrayNode curr) {
    List<JsonNode> all = new ArrayList<>();
    prev.forEach(all::add);
    curr.forEach(all::add);
    return all;
  }
}
