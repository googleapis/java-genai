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

import static java.nio.charset.StandardCharsets.UTF_8;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.LiveConnectConfig;
import com.google.genai.types.LiveConnectParameters;
import com.google.genai.types.LiveServerMessage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.logging.Logger;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.jspecify.annotations.Nullable;

/**
 * AsyncLive provides asynchronous access to a bidirectional GenAI live session. The live module is
 * experimental.
 */
public class AsyncLive {

  private final ApiClient apiClient;
  private static final Logger logger = Logger.getLogger(AsyncLive.class.getName());

  AsyncLive(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Connects to the live server.
   *
   * @param model The model name to use.
   * @param config A {@link LiveConnectConfig} configuration for the live connection. May be null.
   * @return A {@link CompletableFuture} that resolves to an {@link AsyncSession} upon successful
   *     connection.
   */
  public CompletableFuture<AsyncSession> connect(String model, LiveConnectConfig config) {
    // TODO: b/404946806 - Support per request HTTP options.
    if (config != null && config.httpOptions().isPresent()) {
      throw new IllegalArgumentException(
          "The AsyncLive module does not support httpOptions at request-level in LiveConnectConfig"
              + " yet. Please use the client-level httpOptions configuration instead.");
    }
    CompletableFuture<AsyncSession> future = new CompletableFuture<>();

    GenAiWebSocketClient websocket =
        new GenAiWebSocketClient(
            getWebSocketUri(),
            getWebSocketHeaders(),
            getSetupRequest(model, config),
            future,
            apiClient);

    websocket.connect();

    return future;
  }

  /** Gets the URI for the websocket connection. */
  private URI getWebSocketUri() {
    String baseUrl = apiClient.httpOptions.baseUrl().orElse(null);
    if (baseUrl == null) {
      throw new IllegalArgumentException("No base URL provided in the client.");
    }
    try {
      URI baseUri = new URI(baseUrl);
      String wsBaseUrl =
          new URI(
                  "wss",
                  baseUri.getAuthority(),
                  baseUri.getPath(),
                  baseUri.getQuery(),
                  baseUri.getFragment())
              .toString();

      if (!apiClient.vertexAI()) {
        String method;
        if (apiClient.apiKey().startsWith("auth_tokens/")) {
          logger.warning(
              "Warning: Ephemeral token support is experimental and may change in future"
                  + " versions.");
          if (!apiClient.httpOptions.apiVersion().orElse("v1beta").equals("v1alpha")) {
            logger.warning(
                "Warning: The SDK's ephemeral token support is in v1alpha only. Please use  client"
                    + " = Client.builder().httpOptions(HttpOptions.builder().apiVersion(\"v1alpha\").build()).build()"
                    + " before session connection.");
          }
          method = "BidiGenerateContentConstrained";
        } else {
          method = "BidiGenerateContent";
        }
        return new URI(
            String.format(
                "%s/ws/google.ai.generativelanguage.%s.GenerativeService.%s",
                wsBaseUrl, apiClient.httpOptions.apiVersion().orElse("v1beta"), method));
      } else {
        return new URI(
            String.format(
                "%s/ws/google.cloud.aiplatform.%s.LlmBidiService/BidiGenerateContent",
                wsBaseUrl, apiClient.httpOptions.apiVersion().orElse("v1beta1")));
      }
    } catch (URISyntaxException e) {
      throw new IllegalStateException("Failed to parse URL.", e);
    }
  }

  /** Gets the headers for the websocket connection. */
  private Map<String, String> getWebSocketHeaders() {
    Map<String, String> headers = new HashMap<>();
    apiClient.httpOptions.headers().ifPresent(headers::putAll);

    if (apiClient.vertexAI()) {
      try {
        GoogleCredentials credentials =
            apiClient.credentials.orElseGet(() -> apiClient.defaultCredentials());
        credentials.refreshIfExpired();
        headers.put("Authorization", "Bearer " + credentials.getAccessToken().getTokenValue());
      } catch (IOException e) {
        throw new GenAiIOException("Failed to refresh credentials for Vertex AI.", e);
      }
    } else {
      @Nullable String apiKey = apiClient.apiKey();
      if (apiKey == null) {
        throw new IllegalArgumentException("Missing API key in the client.");
      } else if (apiKey.startsWith("auth_tokens/")) {
        headers.put("Authorization", "Token " + apiKey);
      } else {
        headers.put("x-goog-api-key", apiKey);
      }
    }
    return headers;
  }

  /** Gets the request message for the initial setup. */
  private String getSetupRequest(String model, LiveConnectConfig config) {

    String transformedModel = Transformers.tModel(apiClient, model);
    // Vertex requires the full resource path for the model.
    if (apiClient.vertexAI() && transformedModel.startsWith("publishers/")) {
      model =
          String.format(
              "projects/%s/locations/%s/%s",
              apiClient.project(), apiClient.location(), transformedModel);
    }

    LiveConverters liveConverters = new LiveConverters(apiClient);
    LiveConnectParameters.Builder parameterBuilder = LiveConnectParameters.builder();
    if (!Common.isZero(model)) {
      parameterBuilder.model(model);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    if (this.apiClient.vertexAI()) {
      body = liveConverters.liveConnectParametersToVertex(this.apiClient, parameterNode, null);
    } else {
      body = liveConverters.liveConnectParametersToMldev(this.apiClient, parameterNode, null);
    }

    // TODO: Remove the hack that removes config.
    body.remove("config");

    return JsonSerializable.toJsonString(body);
  }

  static class GenAiWebSocketClient extends WebSocketClient {
    private final String setupRequest;
    private final CompletableFuture<AsyncSession> sessionFuture;
    private final ApiClient apiClient;
    private Consumer<LiveServerMessage> messageCallback;

    public GenAiWebSocketClient(
        URI uri,
        Map<String, String> headers,
        String setupRequest,
        CompletableFuture<AsyncSession> sessionFuture,
        ApiClient apiClient) {
      super(uri, headers);
      this.setupRequest = setupRequest;
      this.sessionFuture = sessionFuture;
      this.messageCallback = null;
      this.apiClient = apiClient;
    }

    public void setMessageCallback(Consumer<LiveServerMessage> messageCallback) {
      this.messageCallback = messageCallback;
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
      send(setupRequest);
    }

    @Override
    public void onMessage(String message) {
      handleIncomingMessage(message);
    }

    @Override
    public void onMessage(ByteBuffer message) {
      try {
        CharsetDecoder decoder = UTF_8.newDecoder();
        CharBuffer charBuffer = decoder.decode(message);
        handleIncomingMessage(charBuffer.toString());

      } catch (CharacterCodingException e) {
        throw new IllegalStateException("Invalid UTF-8 message received from the live session.", e);
      }
    }

    @Override
    public void onError(Exception ex) {
      System.err.println("Error during live session: " + ex.getMessage());
      ex.printStackTrace();
      if (!sessionFuture.isDone()) {
        sessionFuture.completeExceptionally(ex);
      }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
      System.out.println("Live session closed with code: " + code + " and reason: " + reason);
      if (!sessionFuture.isDone()) {
        sessionFuture.completeExceptionally(
            new GenAiIOException("WebSocket closed unexpectedly: " + reason));
      }
    }

    private void handleIncomingMessage(String message) {
      if (!sessionFuture.isDone()) {
        // For the first message, we know it's the setup response.
        // We just complete the future and don't handle the message.
        try {
          LiveServerMessage initialResponse = LiveServerMessage.fromJson(message);
          if (initialResponse.setupComplete().isPresent()) {
            sessionFuture.complete(
                new AsyncSession(
                    apiClient,
                    this,
                    initialResponse.setupComplete().get().sessionId().orElse(null)));
          } else {
            sessionFuture.completeExceptionally(
                new GenAiIOException(
                    "Initial message from WebSocket did not contain setupComplete: " + message));
          }
        } catch (RuntimeException e) {
          System.err.println("Error deserializing message: " + e.getMessage());
          e.printStackTrace();
          sessionFuture.completeExceptionally(e);
        }
        return;
      }

      if (messageCallback != null) {
        try {
          LiveConverters liveConverters = new LiveConverters(this.apiClient);
          JsonNode responseNode = JsonSerializable.stringToJsonNode(message);
          if (this.apiClient.vertexAI()) {
            responseNode = liveConverters.liveServerMessageFromVertex(responseNode, null);
          }
          LiveServerMessage serverMessage =
              JsonSerializable.fromJsonNode(responseNode, LiveServerMessage.class);
          messageCallback.accept(serverMessage);
        } catch (RuntimeException e) {
          System.err.println("Error deserializing message: " + e.getMessage());
          e.printStackTrace();
        }
      } else {
        System.err.println(
            "Received message from live session but no callback registered! Please call"
                + " `AsyncSession.receive()` to register a callback.");
      }
    }
  }
}
