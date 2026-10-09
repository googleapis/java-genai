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
import com.google.genai.types.Candidate;
import com.google.genai.types.FinishReason;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.HttpResponse;
import java.util.ArrayList;
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
   * of every request reach the caller unchanged, in order.
   */
  static ResponseStream.Continuation<GenerateContentResponse> streamContinuation(
      @Nullable GenerateContentConfig config,
      Function<GenerateContentConfig, ResponseStream<GenerateContentResponse>> send) {
    return new ResponseStream.Continuation<GenerateContentResponse>() {
      private Optional<FinishReason> finishReason = Optional.empty();
      private Optional<byte[]> token = Optional.empty();

      @Override
      public void observe(GenerateContentResponse chunk) {
        Optional<Candidate> candidate = firstCandidate(chunk);
        if (!candidate.isPresent()) {
          return;
        }
        if (candidate.get().finishReason().isPresent()) {
          finishReason = candidate.get().finishReason();
        }
        // A long stream also puts checkpoint tokens on chunks before the last one, so the token
        // to resume from is the last one seen.
        if (candidate.get().continuationToken().isPresent()) {
          token = candidate.get().continuationToken();
        }
      }

      @Override
      public @Nullable ResponseStream<GenerateContentResponse> next() {
        boolean continuable = isContinuable(finishReason);
        Optional<byte[]> resumeFrom = token;
        finishReason = Optional.empty();
        token = Optional.empty();
        if (!continuable || !resumeFrom.isPresent()) {
          return null;
        }
        return send.apply(withContinuationToken(config, resumeFrom.get()));
      }
    };
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
    if (!sdkHttpResponse.isPresent()) {
      return result;
    }
    return result.toBuilder().sdkHttpResponse(sdkHttpResponse.get()).build();
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
    return candidate.get().continuationToken();
  }

  private static boolean isContinuable(Optional<FinishReason> finishReason) {
    return finishReason.isPresent()
        && finishReason.get().knownEnum() == FinishReason.Known.CONTINUATION;
  }

  private static Optional<Candidate> firstCandidate(GenerateContentResponse response) {
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
    if (curr == null || curr.isNull()) {
      return prev;
    }
    if (prev == null || prev.isNull()) {
      return curr;
    }
    if (prev.isObject() && curr.isObject()) {
      return mergeObjects(
          (ObjectNode) prev, (ObjectNode) curr, key.equals("usageMetadata"), ImmutableSet.of());
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
    if (key.equals("candidates")) {
      return mergeCandidates(prev, curr);
    }
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
    Map<Integer, ObjectNode> merged = new LinkedHashMap<>();
    for (int position = 0; position < prev.size(); position++) {
      merged.put(candidateIndex(prev.get(position), position), (ObjectNode) prev.get(position));
    }
    for (int position = 0; position < curr.size(); position++) {
      merged.merge(
          candidateIndex(curr.get(position), position),
          (ObjectNode) curr.get(position),
          (previous, current) -> mergeObjects(previous, current, false, END_OF_GENERATION_FIELDS));
    }
    ArrayNode result = JsonSerializable.objectMapper.createArrayNode();
    merged.values().forEach(result::add);
    return result;
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
