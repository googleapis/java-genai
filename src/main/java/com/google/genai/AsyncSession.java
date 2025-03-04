/*
 * Copyright 2025 Google LLC
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

import com.google.genai.types.LiveClientMessage;
import com.google.genai.types.LiveServerMessage;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.function.Consumer;

/**
 * AsyncSession manages sending and receiving messages over a WebSocket connection for a live
 * session. The live module is experimental.
 */
public final class AsyncSession {

  @SuppressWarnings("unused")
  // For future use of converters for different backends.
  private final ApiClient apiClient;

  private final AsyncLive.GenAiWebSocketClient websocket;

  AsyncSession(ApiClient apiClient, AsyncLive.GenAiWebSocketClient websocket) {
    this.apiClient = apiClient;
    this.websocket = websocket;
  }

  /**
   * Sends a message to the live session.
   *
   * @param input A {@link LiveClientMessage} to send.
   * @return A {@link CompletableFuture} that completes when the message has been sent. The future
   *     will fail if the message cannot be sent.
   */
  public CompletableFuture<Void> send(LiveClientMessage input) {
    return CompletableFuture.runAsync(
        () -> {
          try {
            websocket.send(input.toJson());
          } catch (RuntimeException e) {
            throw new CompletionException("Failed to send message to live session.", e);
          }
        });
  }

  /**
   * Registers a callback to receive messages from the live session. Only one callback can be
   * registered at a time.
   *
   * @param onMessage A {@link Consumer} that will be called for each {@link LiveServerMessage}
   *     received.
   * @return A {@link CompletableFuture} that completes when the callback has been registered. Note:
   *     This future doesn't represent the entire lifecycle of receiving messages, just the
   *     *registration* of the callback.
   */
  public CompletableFuture<Void> receive(Consumer<LiveServerMessage> onMessage) {
    websocket.setMessageCallback(onMessage);
    return CompletableFuture.completedFuture(null);
  }

  /**
   * Closes the WebSocket connection.
   *
   * @return A {@link CompletableFuture} that completes when the connection has been closed. Fails
   *     with a {@link CompletionException} wrapping the underlying cause if closing fails.
   */
  public CompletableFuture<Void> close() {
    return CompletableFuture.runAsync(
        () -> {
          try {
            websocket.close();
          } catch (RuntimeException e) {
            throw new CompletionException("Failed to close websocket connection.", e);
          }
        });
  }
}
