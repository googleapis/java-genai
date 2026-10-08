/*
 * Copyright 2026 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.genai.gaos;

import static com.google.genai.gaos.operations.Operations.AsyncRequestOperation;
import static com.google.genai.gaos.operations.Operations.RequestOperation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.genai.gaos.models.errors.ContinuationInterruptedException;
import com.google.genai.gaos.models.interactions.CreateAgentInteraction;
import com.google.genai.gaos.models.interactions.CreateModelInteraction;
import com.google.genai.gaos.models.interactions.Interaction;
import com.google.genai.gaos.models.interactions.InteractionSSEStreamEvent;
import com.google.genai.gaos.models.operations.CreateInteractionRequest;
import com.google.genai.gaos.models.operations.CreateInteractionRequestBody;
import com.google.genai.gaos.models.operations.CreateInteractionResponse;
import com.google.genai.gaos.operations.CreateInteraction;
import com.google.genai.gaos.operations.Operations;
import com.google.genai.gaos.utils.EventStream;
import com.google.genai.gaos.utils.Headers;
import com.google.genai.gaos.utils.Options;
import com.google.genai.gaos.utils.Utils;
import jakarta.annotation.Nullable;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.LongSupplier;
import java.util.logging.Logger;

/**
 * Automatic continuation for {@code interactions.create()}.
 *
 * <p>Hand-written (not generated). Every {@code create} entry point of {@link Interactions}, {@link
 * AsyncInteractions} and their request builders delegates here. It mirrors the Python SDK's {@code
 * _interactions_continuation.py}: a unary loop that follows {@code requires_continuation} hops and
 * merges them into one {@link Interaction}, a streaming wrapper ({@link ContinuationEventStream})
 * that yields every hop's events in order and resumes from checkpoints, and a whole-call deadline.
 *
 * <p>Behavior summary (see {@link Interactions#create(CreateInteractionRequest, Options)}):
 *
 * <ul>
 *   <li>{@code automaticContinuation} defaults to {@code true}. It is a no-op for {@code
 *       background=true} and for agent interactions, whose loops are managed by the server.
 *   <li>Continuation hops send {@code continuation_token}; with {@code store} enabled (the default)
 *       they also send the hop that issued the token as {@code previous_interaction_id} and omit
 *       {@code input}; with {@code store=false} the original {@code input} is resent.
 *   <li>A failure after progress throws {@link ContinuationInterruptedException} (cause attached)
 *       carrying the token to resume from; a first-hop failure throws the underlying error.
 * </ul>
 */
public final class InteractionContinuation {

  private InteractionContinuation() {}

  /**
   * Maximum consecutive reconnects after a mid-hop disconnect that made no progress (no new
   * checkpoint token since the hop's start token).
   */
  public static final int MAX_STREAM_RECONNECTS_WITHOUT_PROGRESS = 3;

  static final String AUTO_CONTINUE_AND_BACKGROUND_ERROR =
      "automaticContinuation=false cannot be used with background=true.";

  static final String INCOMPLETE_CONTINUATION_WARNING =
      "Interaction stopped before completion (status='requires_continuation') with a"
          + " continuation_token because the per-request decode token limit was reached. To resume"
          + " decoding automatically, pass automaticContinuation=true (note: each continuation hop"
          + " re-prefills and bills input tokens), or use background=true (the most cost-efficient"
          + " option for server-managed continuation), or resume manually by passing"
          + " continuation_token to interactions.create() (with previous_interaction_id set to this"
          + " interaction's id and no input, or with the original input when store=false).";

  private static final String REQUIRES_CONTINUATION = "requires_continuation";
  private static final String SSE_SENTINEL = "[DONE]";

  private static final Logger logger = Logger.getLogger(InteractionContinuation.class.getName());
  private static volatile boolean incompleteContinuationWarned = false;

  /** Clock for {@link CallDeadline}, in nanoseconds. Replaceable by tests in this package. */
  static LongSupplier clock = System::nanoTime;

  // Usage list fields are merged per key (e.g. per modality), summing the value.
  private static final Map<String, String[]> USAGE_LIST_KEYS = new LinkedHashMap<>();

  static {
    USAGE_LIST_KEYS.put("cached_tokens_by_modality", new String[] {"modality", "tokens"});
    USAGE_LIST_KEYS.put("input_tokens_by_modality", new String[] {"modality", "tokens"});
    USAGE_LIST_KEYS.put("output_tokens_by_modality", new String[] {"modality", "tokens"});
    USAGE_LIST_KEYS.put("tool_use_tokens_by_modality", new String[] {"modality", "tokens"});
    USAGE_LIST_KEYS.put("grounding_tool_count", new String[] {"type", "count"});
  }

  // SDK-only convenience fields recomputed from the merged steps by the Interaction constructor.
  private static final List<String> OUTPUT_PROPERTIES =
      Arrays.asList("output_text", "output_image", "output_audio", "output_video");

  // ---------------------------------------------------------------------------------------------
  // Entry points
  // ---------------------------------------------------------------------------------------------

  /**
   * Runs a synchronous {@code create} with automatic continuation.
   *
   * @throws IllegalArgumentException if {@code automaticContinuation=false} is combined with {@code
   *     background=true} (before any request is sent)
   */
  public static CreateInteractionResponse create(
      SDKConfiguration sdkConfiguration,
      CreateInteractionRequest request,
      @Nullable Options options,
      Headers headers) {
    Prepared prepared = Prepared.of(request);
    CallDeadline deadline = new CallDeadline(prepared.timeout, prepared.enableContinuation);
    HopSender sender = new HopSender(sdkConfiguration, options, headers);

    CreateInteractionResponse response = sender.send(prepared.request);
    if (prepared.stream) {
      if (!isEventStream(response)) {
        return response;
      }
      return response.withEvents(wrapStream(sender, prepared, deadline, response.events()));
    }
    if (prepared.enableContinuation) {
      return continueUnary(sender, prepared, deadline, response);
    }
    if (prepared.callerToken == null) {
      response.interaction().ifPresent(InteractionContinuation::warnIfIncompleteUnary);
    }
    return response;
  }

  /**
   * Runs an asynchronous {@code create} with automatic continuation.
   *
   * <p>The generated async surface exposes the response as a blocking {@link EventStream} (it is
   * meant for {@code stream=true}); continuation hops are fetched on the thread iterating it.
   *
   * @throws IllegalArgumentException if {@code automaticContinuation=false} is combined with {@code
   *     background=true} (before any request is sent)
   */
  public static CompletableFuture<EventStream<InteractionSSEStreamEvent>> createAsync(
      SDKConfiguration sdkConfiguration,
      CreateInteractionRequest request,
      @Nullable Options options,
      Headers headers) {
    // Resolve the (required in some environments) retry scheduler before validating the request,
    // as the generated code did.
    AsyncRequestOperation<
            CreateInteractionRequest,
            com.google.genai.gaos.models.operations.async.CreateInteractionResponse>
        operation =
            new CreateInteraction.Async(
                sdkConfiguration, options, sdkConfiguration.retryScheduler(), headers);
    final Prepared prepared = Prepared.of(request);
    final CallDeadline deadline = new CallDeadline(prepared.timeout, prepared.enableContinuation);
    final HopSender sender = new HopSender(sdkConfiguration, options, headers);

    return Operations.relayCancel(
        Operations.applyBodyReadAsync(
                operation.doRequest(prepared.request), operation::handleResponse)
            .thenApplyAsync(
                response -> {
                  EventStream<InteractionSSEStreamEvent> events =
                      new EventStream<InteractionSSEStreamEvent>(
                          response.rawResponse().body(),
                          new TypeReference<InteractionSSEStreamEvent>() {},
                          Utils.mapper(),
                          Optional.of(SSE_SENTINEL));
                  if (!prepared.stream) {
                    return events;
                  }
                  return wrapStream(sender, prepared, deadline, events);
                },
                Operations.streamCompletionExecutor()),
        operation);
  }

  private static EventStream<InteractionSSEStreamEvent> wrapStream(
      final HopSender sender,
      final Prepared prepared,
      CallDeadline deadline,
      EventStream<InteractionSSEStreamEvent> events) {
    if (!prepared.enableContinuation) {
      if (prepared.callerToken == null) {
        return ContinuationEventStream.withIncompleteWarning(events);
      }
      return events;
    }
    return new ContinuationEventStream(
        events,
        (token, tokenOwnerId) -> {
          CreateInteractionResponse next = sender.send(prepared.hopRequest(token, tokenOwnerId));
          return isEventStream(next) ? next.events() : null;
        },
        prepared.callerToken,
        deadline);
  }

  private static boolean isEventStream(CreateInteractionResponse response) {
    return !response.interaction().isPresent()
        && Utils.contentTypeMatches(response.contentType(), "text/event-stream");
  }

  /** Sends one hop (one HTTP request) through the generated operation. */
  private static final class HopSender {
    private final SDKConfiguration sdkConfiguration;
    private final Options options;
    private final Headers headers;

    HopSender(SDKConfiguration sdkConfiguration, @Nullable Options options, Headers headers) {
      this.sdkConfiguration = sdkConfiguration;
      this.options = options;
      this.headers = headers;
    }

    CreateInteractionResponse send(CreateInteractionRequest request) {
      RequestOperation<CreateInteractionRequest, CreateInteractionResponse> operation =
          new CreateInteraction.Sync(sdkConfiguration, options, headers);
      return operation.handleResponse(operation.doRequest(request));
    }
  }

  // ---------------------------------------------------------------------------------------------
  // Request preparation
  // ---------------------------------------------------------------------------------------------

  /** A validated {@code create} request plus what the continuation loop needs to know about it. */
  static final class Prepared {
    /** The initial request. */
    final CreateInteractionRequest request;

    final boolean stream;
    final boolean enableContinuation;
    @Nullable final String callerToken;
    @Nullable final Duration timeout;

    private Prepared(
        CreateInteractionRequest request,
        boolean stream,
        boolean enableContinuation,
        @Nullable String callerToken,
        @Nullable Duration timeout) {
      this.request = request;
      this.stream = stream;
      this.enableContinuation = enableContinuation;
      this.callerToken = callerToken;
      this.timeout = timeout;
    }

    static Prepared of(CreateInteractionRequest request) {
      Utils.checkNotNull(request, "request");
      CreateInteractionRequestBody body =
          request
              .body()
              .orElseThrow(() -> new IllegalArgumentException("Request body is required"));
      Boolean automaticContinuation = request.automaticContinuation().orElse(null);
      boolean background = bodyBool(body, "background");
      boolean stream = bodyBool(body, "stream");
      String callerToken = nonEmpty(bodyContinuationToken(body));
      if (background && Boolean.FALSE.equals(automaticContinuation)) {
        throw new IllegalArgumentException(AUTO_CONTINUE_AND_BACKGROUND_ERROR);
      }
      boolean enableContinuation =
          !Boolean.FALSE.equals(automaticContinuation)
              && !background
              && !body.createAgentInteraction().isPresent();
      return new Prepared(
          request, stream, enableContinuation, callerToken, request.timeout().orElse(null));
    }

    /**
     * The request for a continuation hop resuming from {@code token}.
     *
     * <p>With {@code store} enabled (the default) the server rebuilds the context from the stored
     * interaction that issued {@code token}, so the hop sends that interaction as {@code
     * previous_interaction_id} (the server checks the token belongs to it) and omits {@code
     * input}. With {@code store=false} nothing is stored, so the original {@code input} is resent
     * with the token instead.
     *
     * @param tokenOwnerId the id of the hop that issued {@code token}, or null when the token did
     *     not come from an earlier hop (e.g. the caller's own {@code continuation_token}); the
     *     original request is then resent as-is with {@code token}
     */
    CreateInteractionRequest hopRequest(String token, @Nullable String tokenOwnerId) {
      CreateInteractionRequestBody body = request.body().get();
      boolean stateful = tokenOwnerId != null && !Boolean.FALSE.equals(bodyBoolObj(body, "store"));
      CreateInteractionRequestBody hopBody;
      if (body.createModelInteraction().isPresent()) {
        CreateModelInteraction copy = copy(body.createModelInteraction().get());
        copy.withContinuationToken(token);
        if (stateful) {
          copy.withPreviousInteractionId(tokenOwnerId).withInput(null);
        }
        hopBody = CreateInteractionRequestBody.of(copy);
      } else if (body.createAgentInteraction().isPresent()) {
        CreateAgentInteraction copy = copy(body.createAgentInteraction().get());
        copy.withContinuationToken(token);
        if (stateful) {
          copy.withPreviousInteractionId(tokenOwnerId).withInput(null);
        }
        hopBody = CreateInteractionRequestBody.of(copy);
      } else {
        hopBody = body;
      }
      return new CreateInteractionRequest(request.apiVersion().orElse(null), hopBody);
    }
  }

  /** A boolean field of either body variant ({@code background}, {@code stream}, {@code store}). */
  @Nullable
  private static Boolean bodyBoolObj(CreateInteractionRequestBody body, String name) {
    if (body.createModelInteraction().isPresent()) {
      CreateModelInteraction m = body.createModelInteraction().get();
      switch (name) {
        case "background":
          return m.background().orElse(null);
        case "stream":
          return m.stream().orElse(null);
        case "store":
          return m.store().orElse(null);
        default:
          return null;
      }
    }
    if (body.createAgentInteraction().isPresent()) {
      CreateAgentInteraction a = body.createAgentInteraction().get();
      switch (name) {
        case "background":
          return a.background().orElse(null);
        case "stream":
          return a.stream().orElse(null);
        case "store":
          return a.store().orElse(null);
        default:
          return null;
      }
    }
    JsonNode json = body.asJson().orElse(null);
    JsonNode node = json == null ? null : json.get(name);
    return node != null && node.isBoolean() ? node.asBoolean() : null;
  }

  private static boolean bodyBool(CreateInteractionRequestBody body, String name) {
    return Boolean.TRUE.equals(bodyBoolObj(body, name));
  }

  /** The body's {@code continuation_token}, if any. */
  @Nullable
  private static String bodyContinuationToken(CreateInteractionRequestBody body) {
    if (body.createModelInteraction().isPresent()) {
      return body.createModelInteraction().get().continuationToken().orElse(null);
    }
    if (body.createAgentInteraction().isPresent()) {
      return body.createAgentInteraction().get().continuationToken().orElse(null);
    }
    JsonNode json = body.asJson().orElse(null);
    JsonNode node = json == null ? null : json.get("continuation_token");
    return node != null && node.isTextual() ? node.asText() : null;
  }

  /** Deep-copies a model through its JSON form, so the caller's object is never mutated. */
  private static <T> T copy(T value) {
    ObjectMapper mapper = Utils.mapper();
    try {
      @SuppressWarnings("unchecked")
      Class<T> type = (Class<T>) value.getClass();
      return mapper.readValue(mapper.writeValueAsBytes(value), type);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  @Nullable
  private static String nonEmpty(@Nullable String value) {
    return value == null || value.isEmpty() ? null : value;
  }

  // ---------------------------------------------------------------------------------------------
  // Whole-call deadline
  // ---------------------------------------------------------------------------------------------

  /** The per-call {@code timeout} ran out before automatic continuation finished. */
  @SuppressWarnings("serial")
  public static final class ContinuationTimeoutException extends RuntimeException {
    ContinuationTimeoutException(String message) {
      super(message);
    }
  }

  /**
   * Wall-clock budget of one logical {@code interactions.create()} call.
   *
   * <p>A per-call {@code timeout} bounds the whole call rather than each continuation hop: the
   * deadline is fixed when the call starts and no further hop starts (and, for streams, no further
   * event is read) once it has passed. Per-request HTTP timeouts (e.g. the OkHttp call timeout from
   * {@code HttpOptions.timeout}) still apply to each hop separately.
   */
  static final class CallDeadline {
    @Nullable private final Duration timeout;
    private final long deadlineNanos;
    private final boolean bounded;

    CallDeadline(@Nullable Duration timeout, boolean enabled) {
      this.timeout = timeout;
      this.bounded = enabled && timeout != null;
      this.deadlineNanos = bounded ? clock.getAsLong() + timeout.toNanos() : 0L;
    }

    boolean expired() {
      return bounded && clock.getAsLong() - deadlineNanos >= 0;
    }

    /** Throws {@link ContinuationTimeoutException} once the deadline has passed. */
    void check() {
      if (expired()) {
        throw new ContinuationTimeoutException(
            "interactions.create() timed out after "
                + timeout
                + " before automatic continuation finished. Increase the timeout (it bounds the"
                + " whole call, across continuation hops) or resume from the continuation_token.");
      }
    }
  }

  // ---------------------------------------------------------------------------------------------
  // Warnings
  // ---------------------------------------------------------------------------------------------

  static void warnIncompleteContinuation() {
    if (!incompleteContinuationWarned) {
      incompleteContinuationWarned = true;
      logger.warning(INCOMPLETE_CONTINUATION_WARNING);
    }
  }

  static void resetIncompleteContinuationWarningForTesting() {
    incompleteContinuationWarned = false;
  }

  /** Emits a one-time warning when unary {@code create()} stops uncontinued. */
  static void warnIfIncompleteUnary(Interaction interaction) {
    if (continuationToken(interaction) != null) {
      warnIncompleteContinuation();
    }
  }

  // ---------------------------------------------------------------------------------------------
  // Unary loop
  // ---------------------------------------------------------------------------------------------

  /** The token to continue a unary interaction from, or null if it should not be continued. */
  @Nullable
  static String continuationToken(Interaction interaction) {
    String status = interaction.status().map(s -> s.value()).orElse(null);
    if (!REQUIRES_CONTINUATION.equals(status)) {
      return null;
    }
    return nonEmpty(interaction.continuationToken().orElse(null));
  }

  /** The token to continue from after {@code hop}, or null to stop. */
  @Nullable
  private static String nextUnaryHopToken(Interaction hop, @Nullable String sentToken) {
    String token = continuationToken(hop);
    if (token != null && token.equals(sentToken)) {
      // The hop handed back the token it was started with: continuing would loop forever
      // without progress.
      return null;
    }
    return token;
  }

  /**
   * Continues a unary interaction across {@code requires_continuation} hops.
   *
   * <p>A hop that fails after an earlier hop completed throws {@link
   * ContinuationInterruptedException} (with the failure as cause) carrying the hops merged so far
   * and the token to resume from, so the output already produced (and billed) is not lost. Running
   * out of {@code deadline} before a hop starts is such a failure.
   */
  private static CreateInteractionResponse continueUnary(
      HopSender sender,
      Prepared prepared,
      CallDeadline deadline,
      CreateInteractionResponse initialResponse) {
    if (!initialResponse.interaction().isPresent()) {
      return initialResponse;
    }
    List<Interaction> hops = new ArrayList<>();
    hops.add(initialResponse.interaction().get());
    CreateInteractionResponse lastResponse = initialResponse;
    String sentToken = prepared.callerToken;
    while (true) {
      Interaction last = hops.get(hops.size() - 1);
      String token = nextUnaryHopToken(last, sentToken);
      if (token == null) {
        return lastResponse.withInteraction(mergeUnaryHops(hops));
      }
      CreateInteractionResponse response;
      try {
        deadline.check();
        response = sender.send(prepared.hopRequest(token, nonEmpty(last.id().orElse(null))));
      } catch (RuntimeException e) {
        throw interruptedUnary(e, hops, token);
      }
      if (!response.interaction().isPresent()) {
        return response;
      }
      hops.add(response.interaction().get());
      lastResponse = response;
      sentToken = token;
    }
  }

  /** Builds the error for a continuation hop that failed after {@code hops}. */
  static ContinuationInterruptedException interruptedUnary(
      Throwable error, List<Interaction> hops, String token) {
    Interaction partial = mergeUnaryHops(hops);
    String interactionId = nonEmpty(hops.get(hops.size() - 1).id().orElse(null));
    return new ContinuationInterruptedException(
        "Automatic continuation was interrupted after "
            + hops.size()
            + " completed hop(s): "
            + error
            + ". The partial interaction (status 'requires_continuation') and its"
            + " continuation_token can be used to resume.",
        error,
        partial,
        token,
        interactionId,
        mergeContinuationIds(hops));
  }

  // ---------------------------------------------------------------------------------------------
  // Unary merge (on the JSON tree, so fields added to the schema later need no code change)
  // ---------------------------------------------------------------------------------------------

  /**
   * Merges automatic continuation hops into a single interaction.
   *
   * <p>Each hop is its own interaction on the server, and only contains the output generated
   * during that hop. The merged result is the final hop (so {@code id}, {@code status}, etc. refer
   * to it) with the steps of every hop concatenated, usage summed across hops, and {@code
   * continuation_ids} listing every hop's id. Fields describing the turn's request ({@code
   * previous_interaction_id}, {@code created}, {@code input}) come from the first hop: later hops
   * point back at the hop before them, not at the caller's previous turn.
   */
  static Interaction mergeUnaryHops(List<Interaction> hops) {
    Interaction last = hops.get(hops.size() - 1);
    if (!last.steps().isPresent()) {
      return last;
    }
    ObjectMapper mapper = Utils.mapper();
    List<ObjectNode> trees = new ArrayList<>();
    for (Interaction hop : hops) {
      trees.add((ObjectNode) mapper.valueToTree(hop));
    }
    ObjectNode merged = trees.get(trees.size() - 1).deepCopy();

    ArrayNode steps = mapper.createArrayNode();
    for (int i = 0; i < trees.size(); i++) {
      JsonNode hopSteps = trees.get(i).get("steps");
      if (hopSteps == null || !hopSteps.isArray()) {
        continue;
      }
      int start = 0;
      if (i > 0) {
        // Later hops continue the same turn; a repeated user_input step would duplicate the
        // input and cut output_text (which stops at the latest user_input step) short.
        while (start < hopSteps.size()
            && "user_input".equals(hopSteps.get(start).path("type").asText(null))) {
          start++;
        }
      }
      for (int j = start; j < hopSteps.size(); j++) {
        steps.add(hopSteps.get(j));
      }
    }
    merged.set("steps", steps);

    ArrayNode continuationIds = mapper.createArrayNode();
    for (String id : mergeContinuationIds(hops)) {
      continuationIds.add(id);
    }
    merged.set("continuation_ids", continuationIds);

    if (trees.size() > 1) {
      ObjectNode first = trees.get(0);
      for (String name : Arrays.asList("previous_interaction_id", "created", "input")) {
        JsonNode value = first.get(name);
        if (value == null || value.isNull()) {
          merged.remove(name);
        } else {
          merged.set(name, value);
        }
      }
    }

    List<JsonNode> usages = new ArrayList<>();
    for (ObjectNode tree : trees) {
      JsonNode usage = tree.get("usage");
      if (usage != null && usage.isObject()) {
        usages.add(usage);
      }
    }
    if (!usages.isEmpty()) {
      merged.set("usage", mergeUsages(usages));
    }

    // Recomputed from the merged steps by the Interaction constructor.
    for (String name : OUTPUT_PROPERTIES) {
      merged.remove(name);
    }
    try {
      return mapper.treeToValue(merged, Interaction.class);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  /** Each hop's server {@code continuation_ids} followed by its id, de-duplicated in order. */
  static List<String> mergeContinuationIds(List<Interaction> hops) {
    LinkedHashSet<String> ids = new LinkedHashSet<>();
    for (Interaction hop : hops) {
      if (hop.continuationIds().isPresent()) {
        for (String id : hop.continuationIds().get()) {
          if (id != null && !id.isEmpty()) {
            ids.add(id);
          }
        }
      }
      String id = nonEmpty(hop.id().orElse(null));
      if (id != null) {
        ids.add(id);
      }
    }
    return new ArrayList<>(ids);
  }

  /** Sums token usage across hops, keeping the first hop's input tokens. */
  private static ObjectNode mergeUsages(List<JsonNode> usages) {
    ObjectMapper mapper = Utils.mapper();
    JsonNode first = usages.get(0);
    ObjectNode merged = usages.get(usages.size() - 1).deepCopy();

    LinkedHashSet<String> names = new LinkedHashSet<>();
    for (JsonNode usage : usages) {
      for (Iterator<String> it = usage.fieldNames(); it.hasNext(); ) {
        names.add(it.next());
      }
    }
    for (String name : names) {
      if (name.equals("initial_input_tokens")) {
        continue;
      }
      List<JsonNode> present = new ArrayList<>();
      for (JsonNode usage : usages) {
        JsonNode value = usage.get(name);
        if (value != null && !value.isNull()) {
          present.add(value);
        }
      }
      if (present.isEmpty()) {
        continue;
      }
      boolean allInts = true;
      boolean allLists = true;
      for (JsonNode value : present) {
        allInts &= value.isIntegralNumber();
        allLists &= value.isArray();
      }
      if (allInts) {
        long sum = 0;
        for (JsonNode value : present) {
          sum += value.asLong();
        }
        merged.put(name, sum);
      } else if (allLists && USAGE_LIST_KEYS.containsKey(name)) {
        String[] keys = USAGE_LIST_KEYS.get(name);
        merged.set(name, mergeUsageList(mapper, present, keys[0], keys[1]));
      }
    }

    JsonNode initialInput = first.get("initial_input_tokens");
    if (initialInput == null || initialInput.isNull()) {
      initialInput = first.get("total_input_tokens");
    }
    if (initialInput != null && !initialInput.isNull()) {
      merged.set("initial_input_tokens", initialInput);
    }
    return merged;
  }

  private static ArrayNode mergeUsageList(
      ObjectMapper mapper, List<JsonNode> lists, String keyName, String valueName) {
    LinkedHashMap<String, ObjectNode> merged = new LinkedHashMap<>();
    for (JsonNode items : lists) {
      for (JsonNode item : items) {
        if (!item.isObject()) {
          continue;
        }
        String key = item.path(keyName).asText(null);
        ObjectNode prev = merged.get(key);
        if (prev == null) {
          merged.put(key, ((ObjectNode) item).deepCopy());
          continue;
        }
        JsonNode prevValue = prev.get(valueName);
        JsonNode value = item.get(valueName);
        if (prevValue != null
            && prevValue.isIntegralNumber()
            && value != null
            && value.isIntegralNumber()) {
          prev.put(valueName, prevValue.asLong() + value.asLong());
        }
      }
    }
    ArrayNode result = mapper.createArrayNode();
    for (ObjectNode item : merged.values()) {
      result.add(item);
    }
    return result;
  }
}
