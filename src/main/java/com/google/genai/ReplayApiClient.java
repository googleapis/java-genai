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

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.genai.types.HttpOptions;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicStatusLine;
import org.mockito.Mockito;

// TODO(b/369384123): Currently the ReplayApiClient mirrors the HttpApiClient. We will refactor the
// ReplayApiClient to use the ReplayFile as part of resolving b/369384123.

/** Base client for the HTTP APIs. */
final class ReplayApiClient extends ApiClient {
  private final String replaysDirectory;
  private String replayId;
  private final String clientMode;
  private Map<String, Object> replaySession = null;
  private int replayIndex = -1;
  private int sdkResponseIndex = -1;

  /** Constructs an ApiClient for Google AI APIs. */
  ReplayApiClient(
      Optional<String> apiKey,
      Optional<HttpOptions> httpOptions,
      String replaysDirectory,
      String replayId,
      String clientMode) {
    super(apiKey, httpOptions);
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
      String replaysDirectory,
      String replayId,
      String clientMode) {
    super(project, location, credentials, httpOptions);
    checkNotNull(replaysDirectory, "replaysDirectory cannot be null");
    checkNotNull(replayId, "replayId cannot be null");
    checkNotNull(clientMode, "clientMode cannot be null");

    this.replaysDirectory = replaysDirectory;
    this.replayId = replayId;
    this.clientMode = clientMode;
  }

  void initializeReplaySession(String replayId) {
    this.replayId = replayId;
    String replayPath = this.replaysDirectory + "/" + this.replayId;
    // Open the replay file if it exists.
    try {
      String replayData = Files.readString(Paths.get(replayPath));
      // TODO(b/369384123): Parsing to a ReplaySession object is not working because snake_case
      // fields like body_segments are not being populated. For now, we will just use basic JSON
      // parsing and switch to the generated JSON classes once we have the replays working.
      Map<String, Object> map = new HashMap<>();
      // convert JSON string to Map
      map =
          JsonSerializable.objectMapper.readValue(
              replayData, new TypeReference<Map<String, Object>>() {});
      this.replaySession = map;
      this.replayIndex = 0;
      this.sdkResponseIndex = 0;
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read replay file: " + e, e);
    }
  }

  /** Sends a Http Post request given the path and request json string. */
  public ApiResponse post(String path, String requestJson) throws IOException {
    if (this.clientMode.equals("replay") || this.clientMode.equals("auto")) {
      System.out.println("    === Using replay for ID: " + this.replayId);
      List<Object> interactions = Arrays.asList(this.replaySession.get("interactions"));
      // TODO(b/369384123): Ensure the replay is correctly loaded by index for multi-turn
      // conversations.
      Object currentInteraction = Arrays.asList(interactions.get(this.replayIndex)).get(0);
      java.util.LinkedHashMap<String, Object> currentMember =
          ((ArrayList<java.util.LinkedHashMap<String, Object>>) currentInteraction).get(0);
      Map<String, Object> responseMap = (Map<String, Object>) currentMember.get("response");
      Integer statusCode = (Integer) responseMap.get("status_code");
      List<Object> bodySegments = (List<Object>) responseMap.get("body_segments");
      StringBuffer responseBody = new StringBuffer();
      for (Object bodySegment : bodySegments) {
        responseBody.append(bodySegment.toString());
      }
      String responseString = responseBody.toString();
      CloseableHttpResponse response = Mockito.mock(CloseableHttpResponse.class);

      BasicHttpEntity entity = new BasicHttpEntity();
      entity.setContent(new ByteArrayInputStream(responseString.getBytes(StandardCharsets.UTF_8)));
      entity.setContentLength(responseString.length());
      Mockito.when(response.getEntity()).thenReturn(entity);

      StatusLine statusLine =
          new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), statusCode, "OK");
      Mockito.when(response.getStatusLine()).thenReturn(statusLine);

      return new ReplayApiResponse(response);
    } else {
      // Note that if the client mode is "api", then the ReplayApiClient will not be used.
      throw new IllegalArgumentException("Invalid client mode: " + this.clientMode);
    }
  }
}
