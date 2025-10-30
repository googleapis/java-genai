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

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.stream.Collectors.joining;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.collect.ImmutableMap;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.ClientOptions;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.ReplayFile;
import com.google.genai.types.ReplayInteraction;
import com.google.genai.types.ReplayRequest;
import com.google.genai.types.ReplayResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;

/** Base client for the HTTP APIs. */
@ExcludeFromGeneratedCoverageReport
final class ReplayApiClient extends ApiClient {
  private final String clientMode;
  private final String replaysDirectory;
  private String replayId;
  private int replayInteractionIndex;
  private List<ReplayInteraction> replayInteractions;

  /** Constructs an ApiClient for Google AI APIs. */
  ReplayApiClient(
      Optional<String> apiKey,
      Optional<HttpOptions> httpOptions,
      Optional<ClientOptions> clientOptions,
      String replaysDirectory,
      String replayId,
      String clientMode) {
    super(apiKey, httpOptions, clientOptions);
    checkNotNull(replaysDirectory, "replaysDirectory cannot be null");
    checkNotNull(replayId, "replayId cannot be null");
    checkNotNull(clientMode, "clientMode cannot be null");

    this.replaysDirectory = replaysDirectory;
    this.replayId = replayId;
    this.clientMode = clientMode;
  }

  /** Constructs an ApiClient for Vertex AI APIs. */
  ReplayApiClient(
      Optional<String> apiKey,
      Optional<String> project,
      Optional<String> location,
      Optional<GoogleCredentials> credentials,
      Optional<HttpOptions> httpOptions,
      Optional<ClientOptions> clientOptions,
      String replaysDirectory,
      String replayId,
      String clientMode) {
    super(apiKey, project, location, credentials, httpOptions, clientOptions);
    checkNotNull(replaysDirectory, "replaysDirectory cannot be null");
    checkNotNull(replayId, "replayId cannot be null");
    checkNotNull(clientMode, "clientMode cannot be null");

    this.replaysDirectory = replaysDirectory;
    this.replayId = replayId;
    this.clientMode = clientMode;
  }

  /** Reads a string from a file path. */
  static String readString(Path path) {
    try (Stream<String> stream = Files.lines(path)) {
      return stream.collect(joining(System.lineSeparator()));
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read replay file from path: " + path.toString(), e);
    }
  }

  /** Initializes the replay session. */
  void initializeReplaySession(String replayId) {
    this.replayId = replayId;
    String replayPath = this.replaysDirectory + "/" + this.replayId;
    String replayData = readString(Paths.get(replayPath));
    this.replayInteractions =
        ReplayFile.fromJson(replayData)
            .interactions()
            .orElseThrow(
                () ->
                    new GenAiIOException(
                        String.format(
                            "The replay file %s does not have interactions.", replayPath)));
    this.replayInteractionIndex = 0;
  }

  /** Sends a Http Post request given the path and request json string. */
  @Override
  public ApiResponse request(
      String httpMethod, String path, String requestJson, Optional<HttpOptions> httpOptions) {
    if (this.clientMode.equals("replay")) {
      ReplayInteraction currentInteraction = replayInteractions.get(this.replayInteractionIndex);
      this.replayInteractionIndex++;
      matchRequest(
          currentInteraction.request().orElse(null),
          buildRequest(httpMethod, path, requestJson, httpOptions));
      return buildResponseFromReplay(currentInteraction.response().orElse(null));
    } else {
      // Note that if the client mode is "api", then the ReplayApiClient will not be used.
      throw new IllegalArgumentException("Invalid client mode: " + this.clientMode);
    }
  }

  /** Sends a Http request given the http method, path, request bytes, and http options. */
  @Override
  public ApiResponse request(
      String httpMethod, String path, byte[] requestBytes, Optional<HttpOptions> httpOptions) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }

  /**
   * Sends an asynchronous Http request given the http method, path, request json string, and http
   * options.
   */
  @Override
  public CompletableFuture<ApiResponse> asyncRequest(
      String httpMethod, String path, String requestJson, Optional<HttpOptions> httpOptions) {
    return CompletableFuture.completedFuture(request(httpMethod, path, requestJson, httpOptions));
  }

  /**
   * Sends an asynchronous Http request given the http method, path, request bytes, and http
   * options.
   */
  @Override
  public CompletableFuture<ApiResponse> asyncRequest(
      String httpMethod, String path, byte[] requestBytes, Optional<HttpOptions> httpOptions) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }

  /** Makes sure the replay request matches the actual request message. */
  private void matchRequest(ReplayRequest replayRequest, Request actualRequest) {
    if (replayRequest == null) {
      throw new IllegalArgumentException("Replay request is null.");
    }

    // Match request method.
    String replayMethod = replayRequest.method().orElse("").toLowerCase(Locale.ROOT);
    String actualMethod = actualRequest.method().toLowerCase(Locale.ROOT);
    if (!equalsIgnoreKeyCase(replayMethod, actualMethod)) {
      throw new AssertionError(
          String.format(
              "Request method mismatch:\nReplay: %s\nActual: %s", replayMethod, actualMethod));
    }

    // Match request url.
    String replayPath = replayRequest.url().orElse("");
    String actualPath = redactRequestUrl(actualRequest.url().toString());
    if (!equalsIgnoreKeyCase(replayPath, actualPath)) {
      throw new AssertionError(
          String.format("Request url mismatch:\nReplay: %s\nActual: %s", replayPath, actualPath));
    }

    // Match request headers.
    Map<String, String> replayHeaders = replayRequest.headers().orElse(ImmutableMap.of());
    Map<String, String> actualHeaders = new HashMap<>();
    Map<String, List<String>> headersMap = actualRequest.headers().toMultimap();
    for (Map.Entry<String, List<String>> entry : headersMap.entrySet()) {
      actualHeaders.put(entry.getKey(), String.join(" ", entry.getValue()));
    }
    actualHeaders = redactRequestHeaders(actualHeaders);
    if (!equalsIgnoreKeyCase(replayHeaders, actualHeaders)) {
      throw new AssertionError(
          String.format(
              "Request headers mismatch:\nReplay: %s\nActual: %s", replayHeaders, actualHeaders));
    }

    // Match request body.
    if (actualMethod.equals("get") || actualMethod.equals("delete")) {
      // No body for these request methods.
      return;
    }
    JsonNode actualBody =
        redactRequestBody(
            JsonSerializable.stringToJsonNode(requestBodyToString(actualRequest.body())));
    JsonNode replayBody =
        JsonSerializable.toJsonNode(replayRequest.bodySegments().orElse(new ArrayList<>()).get(0));
    if (!equalsIgnoreKeyCase(replayBody, actualBody)) {
      throw new AssertionError(
          String.format("Request body mismatch:\nReplay: %s\nActual: %s", replayBody, actualBody));
    }
  }

  /** Builds the response from a {@link ReplayResponse}. */
  private ReplayApiResponse buildResponseFromReplay(ReplayResponse replayResponse) {
    if (replayResponse == null) {
      throw new IllegalArgumentException("Replay response is null.");
    }
    JsonNode bodyNode =
        JsonSerializable.toJsonNode(replayResponse.bodySegments().orElse(new ArrayList<>()));
    Headers headers = Headers.of(replayResponse.headers().orElse(ImmutableMap.of()));
    return new ReplayApiResponse(
        (ArrayNode) bodyNode, replayResponse.statusCode().orElse(0), headers);
  }

  /**
   * Redact all the url parts before the resource name, so the test can work against any project,
   * location, version, or whether it's GCP Express (API keys on Vertex AI).
   */
  private static String redactRequestUrl(String requestUrl) {
    String result =
        requestUrl.replaceAll(".*/projects/[^/]+/locations/[^/]+/", "{VERTEX_URL_PREFIX}/");

    result = result.replaceAll(".*-aiplatform.googleapis.com/[^/]+/", "{VERTEX_URL_PREFIX}/");

    result = result.replaceAll(".*aiplatform.googleapis.com/[^/]+/", "{VERTEX_URL_PREFIX}/");

    result =
        result.replaceAll("https://generativelanguage.googleapis.com/[^/]+", "{MLDEV_URL_PREFIX}");

    return result;
  }

  /** Redact all the request headers that are not robust to replay files. */
  private static Map<String, String> redactRequestHeaders(Map<String, String> headers) {
    Map<String, String> redactedHeaders = new HashMap<>();

    for (Map.Entry<String, String> entry : headers.entrySet()) {
      String headerName = entry.getKey();
      String headerValue = entry.getValue();

      switch (headerName.toLowerCase(Locale.ROOT)) {
        case "x-goog-api-key":
          redactedHeaders.put(headerName, "{REDACTED}");
          break;

        case "user-agent":
        case "x-goog-api-client":
          String redactedValue =
              headerValue
                  .replaceAll("\\d+\\.\\d+\\.\\d+", "{VERSION_NUMBER}")
                  .replace("gl-java/", "{LANGUAGE_LABEL}/");
          redactedHeaders.put(headerName, redactedValue);
          break;

        case "x-goog-user-project":
        case "authorization":
          break;

        default:
          redactedHeaders.put(headerName, headerValue);
          break;
      }
    }
    return redactedHeaders;
  }

  /** Redact the request body to make it robust to replay files. */
  private static JsonNode redactRequestBody(JsonNode requestBody) {
    ObjectNode redactedNode = JsonSerializable.objectMapper.createObjectNode();
    requestBody
        .fields()
        .forEachRemaining(
            entry -> {
              if (entry.getValue().isTextual()) {
                redactedNode.set(
                    entry.getKey(),
                    JsonSerializable.toJsonNode(
                        entry
                            .getValue()
                            .asText()
                            .replaceAll(
                                "projects/[^/]+/locations/[^/]+/",
                                "{PROJECT_AND_LOCATION_PATH}/")));
              } else {
                redactedNode.set(entry.getKey(), entry.getValue());
              }
            });
    return redactedNode;
  }

  /**
   * Normalizes the key casing(snake vs camel) for a given object.
   *
   * <p>e.g., {'my_key': 'my_value'} -> {'myKey': 'myValue'}
   */
  @SuppressWarnings("PatternMatchingInstanceOf")
  private static Object normalizeKeyCase(Object obj) {
    if (obj instanceof Map) {
      Map<?, ?> originalMap = (Map<?, ?>) obj;
      Map<String, Object> normalizedMap = new HashMap<>();
      for (Map.Entry<?, ?> entry : originalMap.entrySet()) {
        String key = (String) entry.getKey();
        normalizedMap.put(
            Common.snakeToCamel(key).toLowerCase(Locale.ROOT), normalizeKeyCase(entry.getValue()));
      }
      return normalizedMap;
    } else if (obj instanceof List) {
      List<?> originalList = (List<?>) obj;
      List<Object> normalizedList = new ArrayList<>();
      for (Object item : originalList) {
        normalizedList.add(normalizeKeyCase(item));
      }
      return normalizedList;
    } else if (obj instanceof JsonNode) {
      JsonNode node = (JsonNode) obj;
      if (node.isObject()) {
        ObjectNode normalizedNode = JsonSerializable.objectMapper.createObjectNode();
        node.fields()
            .forEachRemaining(
                entry -> {
                  String newKey = Common.snakeToCamel(entry.getKey());
                  Object normalizedValue = normalizeKeyCase(entry.getValue());
                  normalizedNode.set(newKey, JsonSerializable.toJsonNode(normalizedValue));
                });
        return normalizedNode;
      } else if (node.isArray()) {
        ArrayNode normalizedNode = JsonSerializable.objectMapper.createArrayNode();
        node.elements()
            .forEachRemaining(
                item -> {
                  normalizedNode.add(JsonSerializable.toJsonNode(normalizeKeyCase(item)));
                });
        return normalizedNode;
      } else if (node.isTextual()) {
        // In the replay file, the timestamp has +00:00 offset, while in the
        // actual request it uses Z to represent the offset. We need to
        // replace it to match the replay file.
        return JsonSerializable.toJsonNode(node.asText().replaceAll("(?<=00)Z$", "\\+00:00"));
      }
    }
    return obj;
  }

  /**
   * Compares two objects for equality ignoring key casing(snake vs camel).
   *
   * <p>e.g., {'my_key': 'my_value'} and {'myKey': 'my_value'} are considered equal.
   */
  private static boolean equalsIgnoreKeyCase(Object replay, Object actual) {
    Object normalizedReplay = normalizeKeyCase(replay);
    Object normalizedActual = normalizeKeyCase(actual);

    return Objects.equals(normalizedReplay, normalizedActual);
  }

  /** Converts a {@link RequestBody} to a string. */
  private static String requestBodyToString(final RequestBody requestBody) {
    if (requestBody == null) {
      return "No RequestBody";
    }
    try {
      final Buffer buffer = new Buffer();
      requestBody.writeTo(buffer);
      MediaType contentType = requestBody.contentType();
      if (contentType != null && contentType.charset() != null) {
        return buffer.readString(contentType.charset());
      } else {
        return buffer.readUtf8();
      }
    } catch (final IOException e) {
      throw new GenAiIOException("Failed to convert request body to string.", e);
    }
  }
}
