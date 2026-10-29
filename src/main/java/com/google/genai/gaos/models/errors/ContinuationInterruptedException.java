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

package com.google.genai.gaos.models.errors;

import com.google.genai.gaos.models.interactions.Interaction;
import jakarta.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Thrown when Interactions automatic continuation fails after progress.
 *
 * <p>{@code interactions.create()} with automatic continuation enabled (the default) may make
 * several requests ("hops"), each one resuming the decode from the previous hop's {@code
 * continuation_token}. If a hop fails after the server already produced (and billed) output in an
 * earlier hop, or after a resumable checkpoint was reached, this exception is thrown instead of
 * the underlying error so the work is not lost. The underlying error is {@link #getCause()}.
 *
 * <p>To resume, pass {@link #continuationToken()} as {@code continuation_token} to {@code
 * interactions.create()}: with {@code store} enabled (the default), also pass {@link
 * #interactionId()} as {@code previous_interaction_id} and no {@code input}; with {@code
 * store=false}, resend the original {@code input}.
 */
@SuppressWarnings("serial")
public class ContinuationInterruptedException extends RuntimeException {

  private final transient Interaction partialInteraction;
  private final String continuationToken;
  private final String interactionId;
  private final List<String> continuationIds;

  public ContinuationInterruptedException(
      String message,
      @Nullable Throwable cause,
      @Nullable Interaction partialInteraction,
      @Nullable String continuationToken,
      @Nullable String interactionId,
      @Nullable List<String> continuationIds) {
    super(message, cause);
    this.partialInteraction = partialInteraction;
    this.continuationToken = continuationToken;
    this.interactionId = interactionId;
    this.continuationIds =
        continuationIds == null
            ? Collections.<String>emptyList()
            : Collections.unmodifiableList(new ArrayList<>(continuationIds));
  }

  /**
   * For unary calls, the interaction merged from the hops that completed (status {@code
   * requires_continuation}, with the resumable {@code continuation_token}). Always empty for
   * streams, whose events were already yielded.
   */
  public Optional<Interaction> partialInteraction() {
    return Optional.ofNullable(partialInteraction);
  }

  /**
   * The latest resumable {@code continuation_token}. For streams this may be a mid-hop checkpoint:
   * resuming from it re-generates any output yielded after the checkpoint.
   */
  public Optional<String> continuationToken() {
    return Optional.ofNullable(continuationToken);
  }

  /**
   * The id of the interaction that issued {@link #continuationToken()} (the value to send as
   * {@code previous_interaction_id}), or empty if unknown or unstored ({@code store=false}).
   */
  public Optional<String> interactionId() {
    return Optional.ofNullable(interactionId);
  }

  /** The ids of every hop created during the call, in order. */
  public List<String> continuationIds() {
    return continuationIds;
  }
}
