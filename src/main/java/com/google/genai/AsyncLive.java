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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.LiveConnectConfig;
import com.google.genai.types.LiveConnectParameters;
import com.google.genai.types.LiveServerMessage;
import com.google.genai.types.LiveServerSetupComplete;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
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
    String baseUrl = apiClient.httpOptions().baseUrl().orElse(null);
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

      boolean hasStandardAuth =
          (apiClient.project() != null && apiClient.location() != null)
              || apiClient.apiKey() != null;
      if (apiClient.customBaseUrl() != null && !hasStandardAuth) {
        return new URI(wsBaseUrl);
      }

      if (!apiClient.vertexAI()) {
        String method;
        if (apiClient.apiKey() != null && apiClient.apiKey().startsWith("auth_tokens/")) {
          logger.warning(
              "Warning: Ephemeral token support is experimental and may change in future"
                  + " versions.");
          if (!apiClient.httpOptions().apiVersion().orElse("v1beta").equals("v1alpha")) {
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
                wsBaseUrl, apiClient.httpOptions().apiVersion().orElse("v1beta"), method));
      } else {
        return new URI(
            String.format(
                "%s/ws/google.cloud.aiplatform.%s.LlmBidiService/BidiGenerateContent",
                wsBaseUrl, apiClient.httpOptions().apiVersion().orElse("v1beta1")));
      }
    } catch (URISyntaxException e) {
      throw new IllegalStateException("Failed to parse URL.", e);
    }
  }

  /** Gets the headers for the websocket connection. */
  private Map<String, String> getWebSocketHeaders() {
    Map<String, String> headers = new HashMap<>();
    apiClient.httpOptions().headers().ifPresent(headers::putAll);

    if (apiClient.vertexAI()) {
      if (apiClient.credentials() != null) {
        try {
          GoogleCredentials credentials = apiClient.credentials();
          credentials.refreshIfExpired();
          headers.put("Authorization", "Bearer " + credentials.getAccessToken().getTokenValue());
          if (credentials.getQuotaProjectId() != null) {
            headers.put("x-goog-user-project", credentials.getQuotaProjectId());
          }
        } catch (IOException e) {
          throw new GenAiIOException("Failed to refresh credentials for Vertex AI.", e);
        }
      } else if (apiClient.apiKey() != null) {
        headers.put("x-goog-api-key", apiClient.apiKey());
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
    if (apiClient.vertexAI()
        && transformedModel.startsWith("publishers/")
        && apiClient.project() != null
        && apiClient.location() != null) {
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

  static class GenAiWebSocketClient extends WebSocketListener {
    private final URI uri;
    private final Map<String, String> headers;
    private final String setupRequest;
    private final CompletableFuture<AsyncSession> sessionFuture;
    private final ApiClient apiClient;
    private Consumer<LiveServerMessage> messageCallback;
    private Consumer<Throwable> errorCallback;
    private Runnable closeCallback;
    private WebSocket webSocket;

    public GenAiWebSocketClient(
        URI uri,
        Map<String, String> headers,
        String setupRequest,
        CompletableFuture<AsyncSession> sessionFuture,
        ApiClient apiClient) {
      this.uri = uri;
      this.headers = headers;
      this.setupRequest = setupRequest;
      this.sessionFuture = sessionFuture;
      this.messageCallback = null;
      this.apiClient = apiClient;
    }

    public void connect() {
      Request.Builder requestBuilder = new Request.Builder().url(uri.toString());
      if (headers != null) {
        for (Map.Entry<String, String> header : headers.entrySet()) {
          requestBuilder.addHeader(header.getKey(), header.getValue());
        }
      }
      Request request = requestBuilder.build();
      this.webSocket = apiClient.httpClient().newWebSocket(request, this);
    }

    public void send(String text) {
      if (webSocket != null) {
        webSocket.send(text);
      }
    }

    public void close() {
      if (webSocket != null) {
        webSocket.close(1000, "Client closing");
      }
    }

    public void setMessageCallback(Consumer<LiveServerMessage> messageCallback) {
      this.messageCallback = messageCallback;
    }

    public void setErrorCallback(Consumer<Throwable> errorCallback) {
      this.errorCallback = errorCallback;
    }

    public void setCloseCallback(Runnable closeCallback) {
      this.closeCallback = closeCallback;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
      this.webSocket = webSocket;
      webSocket.send(setupRequest);
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
      handleIncomingMessage(text);
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
      handleIncomingMessage(bytes.utf8());
    }

    public void onMessage(String text) {
      handleIncomingMessage(text);
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
      logger.log(
          Level.INFO,
          "Live session closing with code: {0} and reason: {1}",
          new Object[] {code, reason});
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
      logger.log(
          Level.INFO,
          "Live session closed with code: {0} and reason: {1}",
          new Object[] {code, reason});
      if (!sessionFuture.isDone()) {
        sessionFuture.completeExceptionally(
            new GenAiIOException("WebSocket closed unexpectedly: " + reason));
      } else if (closeCallback != null) {
        closeCallback.run();
      }
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {
      logger.log(Level.SEVERE, "Error during live session", t);
      if (!sessionFuture.isDone()) {
        sessionFuture.completeExceptionally(t);
      } else if (errorCallback != null) {
        errorCallback.accept(t);
      }
    }

    private void handleIncomingMessage(String message) {
      if (!sessionFuture.isDone()) {
        // For the first message, we know it's the setup response.
        // We just complete the future and don't handle the message.
        try {
          LiveServerMessage initialResponse = LiveServerMessage.fromJson(message);
          if (initialResponse.setupComplete().isPresent()) {
            LiveServerSetupComplete setupComplete = initialResponse.setupComplete().get();
            sessionFuture.complete(
                new AsyncSession(
                    apiClient,
                    this,
                    setupComplete.sessionId().orElse(null),
                    setupComplete));
          } else {
            sessionFuture.completeExceptionally(
                new GenAiIOException(
                    "Initial message from WebSocket did not contain setupComplete: " + message));
          }
        } catch (RuntimeException e) {
          logger.log(Level.SEVERE, "Error deserializing message", e);
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
          logger.log(Level.SEVERE, "Error deserializing message", e);
        }
      } else {
        logger.severe(
            "Received message from live session, but no callback registered! Please call"
                + " `AsyncSession.receive()` to register a callback first.");
      }
    }
  }
}
