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

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.ClientOptions;
import com.google.genai.types.HttpOptions;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

// TODO(b/369384123): Currently the ReplayApiClient mirrors the HttpApiClient. We will refactor the
// ReplayApiClient to use the ReplayFile as part of resolving b/369384123.

/** Base client for the HTTP APIs. */
@ExcludeFromGeneratedCoverageReport
final class ReplayApiClient extends ApiClient {
  private final String replaysDirectory;
  private String replayId;
  private final String clientMode;
  private Map<String, Object> replaySession = null;
  private int replayIndex = -1;

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
      Optional<String> project,
      Optional<String> location,
      Optional<GoogleCredentials> credentials,
      Optional<HttpOptions> httpOptions,
      Optional<ClientOptions> clientOptions,
      String replaysDirectory,
      String replayId,
      String clientMode) {
    super(project, location, credentials, httpOptions, clientOptions);
    checkNotNull(replaysDirectory, "replaysDirectory cannot be null");
    checkNotNull(replayId, "replayId cannot be null");
    checkNotNull(clientMode, "clientMode cannot be null");

    this.replaysDirectory = replaysDirectory;
    this.replayId = replayId;
    this.clientMode = clientMode;
  }

  static String readString(Path path) {
    try (Stream<String> stream = Files.lines(path)) {
      return stream.collect(joining(System.lineSeparator()));
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read replay file. ", e);
    }
  }

  static Map<String, Object> loadReplayData(String replayId) {
    String replaysPath = System.getenv("GOOGLE_GENAI_REPLAYS_DIRECTORY");
    if (replaysPath == null) {
      throw new RuntimeException("GOOGLE_GENAI_REPLAYS_DIRECTORY is not set");
    }
    String testsReplaysPath = replaysPath + "/tests";
    String replayPath = testsReplaysPath + "/" + replayId;
    // Open the replay file if it exists.
    try {
      String replayData = readString(Paths.get(replayPath));
      // TODO(b/369384123): Parsing to a ReplaySession object is not working because snake_case
      // fields like body_segments are not being populated. For now, we will just use basic JSON
      // parsing and switch to the generated JSON classes once we have the replays working.
      // convert JSON string to Map
      return JsonSerializable.objectMapper.readValue(
          replayData, new TypeReference<Map<String, Object>>() {});
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read replay file: " + e, e);
    }
  }

  void initializeReplaySession(String replayId) {
    this.replayId = replayId;
    String replayPath = this.replaysDirectory + "/" + this.replayId;
    // Open the replay file if it exists.
    try {
      String replayData = readString(Paths.get(replayPath));
      // TODO(b/369384123): Parsing to a ReplaySession object is not working because snake_case
      // fields like body_segments are not being populated. For now, we will just use basic JSON
      // parsing and switch to the generated JSON classes once we have the replays working.
      // convert JSON string to Map
      Map<String, Object> map =
          JsonSerializable.objectMapper.readValue(
              replayData, new TypeReference<Map<String, Object>>() {});
      this.replaySession = map;
      this.replayIndex = 0;
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read replay file: " + e, e);
    }
  }

  /** Sends a Http Post request given the path and request json string. */
  @SuppressWarnings("unchecked")
  @Override
  public ApiResponse request(
      String httpMethod, String path, String requestJson, Optional<HttpOptions> httpOptions) {
    if (this.clientMode.equals("replay") || this.clientMode.equals("auto")) {
      System.out.println("    === Using replay for ID: " + this.replayId);
      List<Object> interactions = Arrays.asList(this.replaySession.get("interactions"));
      // TODO(b/369384123): Ensure the replay is correctly loaded by index for multi-turn
      // conversations.
      Object currentInteraction = Arrays.asList(interactions.get(this.replayIndex)).get(0);
      LinkedHashMap<String, Object> currentMember =
          ((ArrayList<LinkedHashMap<String, Object>>) currentInteraction).get(0);
      Map<String, Object> responseMap = (Map<String, Object>) currentMember.get("response");
      Integer statusCode = (Integer) responseMap.get("status_code");
      List<Object> bodySegments = (List<Object>) responseMap.get("body_segments");
      Map<String, String> headerMap = (Map<String, String>) responseMap.get("headers");
      StringBuilder responseBody = new StringBuilder();
      for (Object bodySegment : bodySegments) {
        responseBody.append(bodySegment.toString());
      }

      // Create a dummy request, which is required by the Response.Builder
      Request dummyRequest =
          new Request.Builder()
              .url("https://replay.googleapis.com" + path)
              .method(httpMethod, null)
              .build();
      Headers.Builder headersBuilder = new Headers.Builder();
      headerMap.forEach(headersBuilder::add);
      Headers okHttpHeaders = headersBuilder.build();
      String responseString = responseBody.toString();
      String contentType = okHttpHeaders.get("Content-Type");
      MediaType mediaType =
          (contentType != null)
              ? MediaType.parse(contentType)
              : MediaType.parse("application/json; charset=utf-8");
      ResponseBody okHttpResponseBody = ResponseBody.create(responseString, mediaType);
      Response okHttpResponse =
          new Response.Builder()
              .request(dummyRequest)
              .protocol(Protocol.HTTP_1_1)
              .code(statusCode)
              .message("OK")
              .headers(okHttpHeaders)
              .body(okHttpResponseBody)
              .build();
      return new ReplayApiResponse(okHttpResponse.body(), statusCode, okHttpHeaders);
    } else {
      // Note that if the client mode is "api", then the ReplayApiClient will not be used.
      throw new IllegalArgumentException("Invalid client mode: " + this.clientMode);
    }
  }

  @Override
  public ApiResponse request(
      String httpMethod, String path, byte[] requestBytes, Optional<HttpOptions> httpOptions) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }
}
