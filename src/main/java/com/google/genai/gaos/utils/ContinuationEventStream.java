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

package com.google.genai.gaos.utils;

import com.google.genai.gaos.models.interactions.ErrorEvent;
import com.google.genai.gaos.models.interactions.InteractionCompletedEvent;
import com.google.genai.gaos.models.interactions.InteractionSSEEvent;
import com.google.genai.gaos.models.interactions.InteractionSSEStreamEvent;
import com.google.genai.gaos.models.interactions.InteractionStatusUpdate;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

/**
 * An {@link EventStream} that automatically reconnects and streams across continuation hops.
 */
public class ContinuationEventStream extends EventStream<InteractionSSEStreamEvent> {

  @FunctionalInterface
  public interface StreamSupplier {
    EventStream<InteractionSSEStreamEvent> get(String continuationToken) throws Exception;
  }

  private final StreamSupplier streamSupplier;
  private final Runnable onIncompleteStop;
  private EventStream<InteractionSSEStreamEvent> currentStream;
  private String hopStatus;
  private String hopContinuationToken;
  private boolean closed = false;

  public ContinuationEventStream(
      EventStream<InteractionSSEStreamEvent> initialStream,
      StreamSupplier streamSupplier) {
    this(initialStream, streamSupplier, null);
  }

  public ContinuationEventStream(
      EventStream<InteractionSSEStreamEvent> initialStream,
      StreamSupplier streamSupplier,
      Runnable onIncompleteStop) {
    super();
    this.currentStream = initialStream;
    this.streamSupplier = streamSupplier;
    this.onIncompleteStop = onIncompleteStop;
  }

  private static boolean canResumeStreamHop(String hopStatus, String hopContinuationToken) {
    return hopContinuationToken != null
        && !hopContinuationToken.isEmpty()
        && (hopStatus == null
            || Objects.equals(hopStatus, "in_progress")
            || Objects.equals(hopStatus, "continuation_required"));
  }

  @Override
  public Optional<InteractionSSEStreamEvent> next() throws IOException {
    while (!closed) {
      Optional<InteractionSSEStreamEvent> maybeEvent;
      try {
        maybeEvent = currentStream.next();
      } catch (IOException | RuntimeException e) {
        if (streamSupplier == null || !canResumeStreamHop(hopStatus, hopContinuationToken)) {
          throw e;
        }
        maybeEvent = Optional.empty();
      }
      if (maybeEvent.isPresent()) {
        InteractionSSEStreamEvent streamEvent = maybeEvent.get();
        extractHopContinuationState(streamEvent);
        return maybeEvent;
      }

      // Current hop reached end of stream or disconnected
      try {
        currentStream.close();
      } catch (IOException ignored) {
        // Ignore close failure on dead stream when resuming or finishing.
      }

      if (streamSupplier != null && canResumeStreamHop(hopStatus, hopContinuationToken)) {
        String nextToken = hopContinuationToken;
        hopStatus = null;
        hopContinuationToken = null;
        try {
          this.currentStream = streamSupplier.get(nextToken);
          continue;
        } catch (IOException | RuntimeException e) {
          throw e;
        } catch (Exception e) {
          throw new IOException("Failed to continue stream hop", e);
        }
      } else if (streamSupplier == null
          && onIncompleteStop != null
          && Objects.equals(hopStatus, "continuation_required")
          && hopContinuationToken != null
          && !hopContinuationToken.isEmpty()) {
        onIncompleteStop.run();
      }

      closed = true;
      return Optional.empty();
    }
    return Optional.empty();
  }

  private void extractHopContinuationState(InteractionSSEStreamEvent streamEvent) {
    InteractionSSEEvent event = streamEvent.data().orElse(null);
    if (event instanceof InteractionCompletedEvent) {
      InteractionCompletedEvent completed = (InteractionCompletedEvent) event;
      completed
          .interaction()
          .ifPresent(
              inter -> {
                inter
                    .status()
                    .map(st -> st.value())
                    .filter(s -> !s.isEmpty())
                    .ifPresent(s -> hopStatus = s);
                inter
                    .continuationToken()
                    .filter(tok -> !tok.isEmpty())
                    .ifPresent(tok -> hopContinuationToken = tok);
              });
    } else if (event instanceof InteractionStatusUpdate) {
      InteractionStatusUpdate update = (InteractionStatusUpdate) event;
      update
          .status()
          .map(st -> st.value())
          .filter(s -> !s.isEmpty())
          .ifPresent(s -> hopStatus = s);
      update
          .continuationToken()
          .filter(tok -> !tok.isEmpty())
          .ifPresent(tok -> hopContinuationToken = tok);
    } else if (event instanceof ErrorEvent) {
      hopStatus = "error";
      hopContinuationToken = null;
    }
  }

  @Override
  public void close() throws IOException {
    super.close();
    closed = true;
    if (currentStream != null) {
      currentStream.close();
    }
  }

  @Override
  public boolean isClosed() {
    return closed || super.isClosed();
  }
}
