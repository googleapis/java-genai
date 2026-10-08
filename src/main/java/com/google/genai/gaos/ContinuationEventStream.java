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

import com.google.genai.gaos.models.errors.ContinuationInterruptedException;
import com.google.genai.gaos.models.errors.GaosBaseException;
import com.google.genai.gaos.models.interactions.ErrorEvent;
import com.google.genai.gaos.models.interactions.InteractionCompletedEvent;
import com.google.genai.gaos.models.interactions.InteractionCreatedEvent;
import com.google.genai.gaos.models.interactions.InteractionSSEEvent;
import com.google.genai.gaos.models.interactions.InteractionSSEStreamEvent;
import com.google.genai.gaos.models.interactions.InteractionSseEventInteraction;
import com.google.genai.gaos.models.interactions.InteractionStatusUpdate;
import com.google.genai.gaos.utils.EventStream;
import jakarta.annotation.Nullable;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * An {@link EventStream} of a streaming {@code interactions.create()} call that automatically
 * continues across hops (HTTP requests).
 *
 * <p>Every event of every hop is yielded verbatim and in order, including each hop's own {@code
 * interaction.created} and intermediate {@code interaction.completed} (with status {@code
 * requires_continuation}) events. When a hop completes with {@code requires_continuation} and a
 * token, the next hop is requested from that token. When the transport drops (or the stream ends)
 * mid-hop after an {@code interaction.status_update} checkpoint token, and nothing was yielded
 * since that checkpoint, the hop is resumed from it; a hop that drops before making progress is
 * retried from its start token up to {@link InteractionContinuation#MAX_STREAM_RECONNECTS_WITHOUT_PROGRESS}
 * times in a row. SSE {@code error} events are terminal; HTTP status errors are not retried here.
 *
 * <p>Hand-written (not generated). Per-hop state lives in a {@link Hop} record replaced wholesale
 * when a hop starts, so per-hop fields cannot leak across hops; loop state (next resume token and
 * owner, no-progress reconnect counter, pending transport error) persists on the stream.
 */
public final class ContinuationEventStream extends EventStream<InteractionSSEStreamEvent> {

  /** Opens the stream of a continuation hop. */
  @FunctionalInterface
  public interface HopOpener {
    /**
     * Sends a continuation request resuming from {@code token}.
     *
     * @param token the {@code continuation_token} to resume from
     * @param tokenOwnerId the id of the interaction that issued {@code token}, or null for the
     *     caller's own token
     * @return the hop's event stream, or null if the response was not a stream (ends the stream)
     */
    @Nullable
    EventStream<InteractionSSEStreamEvent> open(String token, @Nullable String tokenOwnerId)
        throws Exception;
  }

  private static final String ERROR_STATUS = "error";
  private static final String IN_PROGRESS = "in_progress";
  private static final String REQUIRES_CONTINUATION = "requires_continuation";

  /** What was observed during one hop (HTTP request) of a continuation stream. */
  private static final class Hop {
    // The continuation_token and owner id this hop was requested with.
    @Nullable final String startToken;
    @Nullable final String startTokenOwnerId;
    // Seeded with startToken so a disconnect before the hop's first checkpoint retries from the
    // hop start.
    @Nullable String token;
    // The interaction that issued `token`. With store=true the server only accepts a token
    // together with the interaction that issued it as previous_interaction_id, so a retry of the
    // seed token keeps the seed's owner even after this hop's own interaction was created.
    @Nullable String tokenOwnerId;
    // This hop's own interaction id, once known.
    @Nullable String interactionId;
    // Latest status observed during the hop, or null if none arrived yet.
    @Nullable String status;
    // Whether the HTTP stream was opened (response headers received).
    boolean streamOpened;
    // Whether output events were yielded since the latest checkpoint token.
    boolean contentSinceCheckpoint;

    Hop(@Nullable String startToken, @Nullable String startTokenOwnerId, boolean streamOpened) {
      this.startToken = startToken;
      this.startTokenOwnerId = startTokenOwnerId;
      this.token = startToken;
      this.tokenOwnerId = startTokenOwnerId;
      this.streamOpened = streamOpened;
    }
  }

  @Nullable private final HopOpener opener;
  private final InteractionContinuation.CallDeadline deadline;
  private final boolean warnWhenIncomplete;

  @Nullable private EventStream<InteractionSSEStreamEvent> current;
  private boolean finished = false;
  private boolean closed = false;

  // Loop state.
  @Nullable private String nextToken;
  // The caller's own token: a failure that leaves nothing newer to resume from is not "after
  // progress" and is thrown as-is.
  @Nullable private final String initialToken;
  @Nullable private String nextTokenOwnerId = null;
  private final List<String> hopIds = new ArrayList<>();
  private int noProgressReconnects = 0;
  // The latest transport error not followed by progress; thrown if the loop gives up so the
  // failure is not hidden by later clean closes.
  @Nullable private Exception lastError = null;
  private Hop hop;

  /**
   * Creates a stream that continues {@code initial} across hops.
   *
   * @param initial the first hop's stream (its HTTP response headers were already received)
   * @param opener opens the stream of each further hop
   * @param initialContinuationToken the caller's own {@code continuation_token}, if any
   * @param deadline the whole-call deadline, checked before each hop and before each event
   */
  public ContinuationEventStream(
      EventStream<InteractionSSEStreamEvent> initial,
      HopOpener opener,
      @Nullable String initialContinuationToken,
      InteractionContinuation.CallDeadline deadline) {
    this(initial, opener, initialContinuationToken, deadline, false);
  }

  private ContinuationEventStream(
      EventStream<InteractionSSEStreamEvent> initial,
      @Nullable HopOpener opener,
      @Nullable String initialContinuationToken,
      InteractionContinuation.CallDeadline deadline,
      boolean warnWhenIncomplete) {
    super();
    this.current = initial;
    this.opener = opener;
    this.deadline = deadline;
    this.warnWhenIncomplete = warnWhenIncomplete;
    this.initialToken = initialContinuationToken;
    this.nextToken = initialContinuationToken;
    this.hop = new Hop(initialContinuationToken, null, /* streamOpened= */ true);
  }

  /**
   * Wraps a stream that is not continued ({@code automaticContinuation=false}) so that a one-time
   * warning is logged if it stops at {@code requires_continuation} with a token.
   */
  static ContinuationEventStream withIncompleteWarning(
      EventStream<InteractionSSEStreamEvent> stream) {
    return new ContinuationEventStream(
        stream, null, null, new InteractionContinuation.CallDeadline(null, false), true);
  }

  // ---------------------------------------------------------------------------------------------
  // EventStream
  // ---------------------------------------------------------------------------------------------

  @Override
  public Optional<InteractionSSEStreamEvent> next() throws IOException {
    while (!finished && !closed) {
      Exception error = null;
      if (current == null) {
        // Start the next hop.
        String token = nextToken;
        String tokenOwnerId = nextTokenOwnerId;
        hop = new Hop(token, tokenOwnerId, /* streamOpened= */ false);
        try {
          deadline.check();
          EventStream<InteractionSSEStreamEvent> next = opener.open(token, tokenOwnerId);
          if (next == null) {
            finished = true;
            return Optional.empty();
          }
          hop.streamOpened = true;
          current = next;
        } catch (Exception e) {
          error = e;
        }
      }
      if (error == null) {
        Optional<InteractionSSEStreamEvent> event;
        try {
          deadline.check();
          event = current.next();
        } catch (Exception e) {
          error = e;
          event = Optional.empty();
        }
        if (event.isPresent()) {
          observe(event.get());
          return event;
        }
      }
      // The hop ended: cleanly, or with `error`.
      closeCurrentQuietly();
      if (opener == null) {
        finished = true;
        if (error != null) {
          throw rethrow(error);
        }
        if (warnWhenIncomplete
            && REQUIRES_CONTINUATION.equals(hop.status)
            && hop.token != null) {
          InteractionContinuation.warnIncompleteContinuation();
        }
        return Optional.empty();
      }
      if (!endHop(error)) {
        finished = true;
        return Optional.empty();
      }
    }
    return Optional.empty();
  }

  @Override
  public void close() throws IOException {
    closed = true;
    finished = true;
    super.close();
    if (current != null) {
      EventStream<InteractionSSEStreamEvent> stream = current;
      current = null;
      stream.close();
    }
  }

  @Override
  public boolean isClosed() {
    return closed;
  }

  private void closeCurrentQuietly() {
    if (current == null) {
      return;
    }
    EventStream<InteractionSSEStreamEvent> stream = current;
    current = null;
    try {
      stream.close();
    } catch (IOException | RuntimeException ignored) {
      // The hop is over; a failure to close a dead stream is not interesting.
    }
  }

  // ---------------------------------------------------------------------------------------------
  // State machine (port of the Python SDK's StreamContinuation)
  // ---------------------------------------------------------------------------------------------

  /** Records one streamed SSE event. */
  private void observe(InteractionSSEStreamEvent streamEvent) {
    hop.streamOpened = true;
    InteractionSSEEvent event = streamEvent.data().orElse(null);
    String eventInteractionId = eventInteractionId(event);
    if (eventInteractionId != null) {
      hop.interactionId = eventInteractionId;
      if (!hopIds.contains(eventInteractionId)) {
        hopIds.add(eventInteractionId);
      }
    }
    String previousToken = hop.token;
    extractHopContinuationState(event);
    if (hop.token != null && !hop.token.equals(previousToken)) {
      hop.tokenOwnerId = hop.interactionId;
    }
    hop.contentSinceCheckpoint = hasContentSinceCheckpoint(event, hop.contentSinceCheckpoint);
  }

  /** Updates the hop's {@code (status, token)} from one SSE event. */
  private void extractHopContinuationState(@Nullable InteractionSSEEvent event) {
    if (event instanceof ErrorEvent) {
      hop.status = ERROR_STATUS;
      hop.token = null;
      return;
    }
    InteractionSseEventInteraction interaction = eventInteraction(event);
    if (interaction != null) {
      String status = interaction.status().map(s -> s.value()).orElse(null);
      if (status != null && !status.isEmpty()) {
        hop.status = status;
      }
      String token = nonEmpty(interaction.continuationToken().orElse(null));
      if (event instanceof InteractionCompletedEvent) {
        // The terminal snapshot is authoritative: a missing token clears the hop's seed token and
        // any earlier mid-hop checkpoint token.
        hop.token = token;
      } else if (token != null) {
        hop.token = token;
      }
    }
    if (event instanceof InteractionStatusUpdate) {
      InteractionStatusUpdate update = (InteractionStatusUpdate) event;
      String status = update.status().map(s -> s.value()).orElse(null);
      if (status != null && !status.isEmpty()) {
        hop.status = status;
      }
      String token = nonEmpty(update.continuationToken().orElse(null));
      if (token != null) {
        hop.token = token;
      }
    }
  }

  /** Tracks whether output was yielded since the latest resume point. */
  private static boolean hasContentSinceCheckpoint(
      @Nullable InteractionSSEEvent event, boolean contentSinceCheckpoint) {
    if (event instanceof InteractionStatusUpdate) {
      if (nonEmpty(((InteractionStatusUpdate) event).continuationToken().orElse(null)) != null) {
        return false;
      }
      return contentSinceCheckpoint;
    }
    if (event instanceof InteractionCompletedEvent) {
      // The terminal snapshot's token (if any) is a fresh resume point.
      return false;
    }
    if (event instanceof InteractionCreatedEvent) {
      // Carries no model output.
      return contentSinceCheckpoint;
    }
    return true;
  }

  @Nullable
  private static InteractionSseEventInteraction eventInteraction(
      @Nullable InteractionSSEEvent event) {
    if (event instanceof InteractionCreatedEvent) {
      return ((InteractionCreatedEvent) event).interaction().orElse(null);
    }
    if (event instanceof InteractionCompletedEvent) {
      return ((InteractionCompletedEvent) event).interaction().orElse(null);
    }
    return null;
  }

  /** The id of the interaction (hop) an SSE event belongs to, if present. */
  @Nullable
  private static String eventInteractionId(@Nullable InteractionSSEEvent event) {
    if (event instanceof InteractionStatusUpdate) {
      String id = nonEmpty(((InteractionStatusUpdate) event).interactionId().orElse(null));
      if (id != null) {
        return id;
      }
    }
    InteractionSseEventInteraction interaction = eventInteraction(event);
    return interaction == null ? null : nonEmpty(interaction.id().orElse(null));
  }

  private static boolean canResumeStreamHop(@Nullable String status, @Nullable String token) {
    return token != null
        && (status == null || IN_PROGRESS.equals(status) || REQUIRES_CONTINUATION.equals(status));
  }

  private boolean canResumeHop() {
    // Resuming from the checkpoint would re-generate (and duplicate) any output yielded since it.
    return !hop.contentSinceCheckpoint && canResumeStreamHop(hop.status, hop.token);
  }

  /** Updates the no-progress reconnect counter after a resumable hop ends. */
  private static int nextNoProgressReconnects(
      int noProgressReconnects,
      @Nullable String status,
      @Nullable String startToken,
      @Nullable String token) {
    if (REQUIRES_CONTINUATION.equals(status)) {
      // Clean hop boundary (per-request decode limit reached).
      return 0;
    }
    if (!Objects.equals(token, startToken)) {
      // Dropped mid-hop after a new checkpoint token was received.
      return 0;
    }
    // Dropped mid-hop before any new checkpoint: retry the hop's own token.
    return noProgressReconnects + 1;
  }

  /**
   * The error to throw when the stream gives up with {@code error}.
   *
   * <p>If the server issued a resumable token during this call (a completed {@code
   * requires_continuation} hop, or a mid-hop checkpoint), the work so far is resumable and {@code
   * error} is wrapped in {@link ContinuationInterruptedException} carrying that token. Otherwise
   * {@code error} is returned as-is.
   */
  private Exception fail(Exception error) {
    String token = canResumeStreamHop(hop.status, hop.token) ? hop.token : null;
    if (token == null || token.equals(initialToken)) {
      return error;
    }
    return new ContinuationInterruptedException(
        "Automatic continuation of the stream was interrupted: "
            + error
            + ". The continuation_token can be used to resume (output yielded after a mid-hop"
            + " checkpoint is re-generated).",
        error,
        null,
        token,
        hop.tokenOwnerId,
        new ArrayList<>(hopIds));
  }

  /**
   * Ends the current hop and returns true if another hop should start.
   *
   * <p>Throws {@code error} (or the pending prior disconnect error) when the stream cannot safely
   * resume or exceeds {@link InteractionContinuation#MAX_STREAM_RECONNECTS_WITHOUT_PROGRESS},
   * wrapped in {@link ContinuationInterruptedException} if a server-issued token to resume from
   * exists (see {@link #fail}).
   */
  private boolean endHop(@Nullable Exception error) throws IOException {
    if (error instanceof InteractionContinuation.ContinuationTimeoutException) {
      throw rethrow(fail(error));
    }
    if (error != null && !hop.streamOpened) {
      // Pre-stream HTTP status or validation errors (already retried by RetryConfig) must
      // propagate immediately; only connection errors resume.
      if (!isConnectionError(error)) {
        throw rethrow(fail(error));
      }
    }

    if (!canResumeHop()) {
      if (error != null) {
        throw rethrow(fail(error));
      }
      return false;
    }

    if (REQUIRES_CONTINUATION.equals(hop.status) && Objects.equals(hop.token, hop.startToken)) {
      // The hop finished but handed back the token it was started with: resuming would loop
      // forever without progress.
      if (error != null) {
        throw rethrow(fail(error));
      }
      return false;
    }

    noProgressReconnects =
        nextNoProgressReconnects(noProgressReconnects, hop.status, hop.startToken, hop.token);
    if (noProgressReconnects == 0) {
      lastError = null;
    }
    if (error != null) {
      lastError = error;
    }
    if (noProgressReconnects > InteractionContinuation.MAX_STREAM_RECONNECTS_WITHOUT_PROGRESS) {
      if (lastError != null) {
        throw rethrow(fail(lastError));
      }
      return false;
    }

    nextToken = hop.token;
    nextTokenOwnerId = hop.tokenOwnerId;
    return true;
  }

  /** Whether {@code error} is a transport (connection) failure rather than an API error. */
  static boolean isConnectionError(Throwable error) {
    for (Throwable t = error; t != null; t = t.getCause()) {
      if (t instanceof IOException || t instanceof UncheckedIOException) {
        return true;
      }
      if (t instanceof GaosBaseException) {
        return false;
      }
      if (t.getCause() == t) {
        break;
      }
    }
    return false;
  }

  /** Rethrows {@code error} as an {@link IOException} or an unchecked exception. */
  private static IOException rethrow(Exception error) throws IOException {
    if (error instanceof IOException) {
      throw (IOException) error;
    }
    if (error instanceof RuntimeException) {
      throw (RuntimeException) error;
    }
    throw new IOException(error);
  }

  @Nullable
  private static String nonEmpty(@Nullable String value) {
    return value == null || value.isEmpty() ? null : value;
  }
}
