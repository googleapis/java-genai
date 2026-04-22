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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.collect.ImmutableMap;
import com.google.genai.types.HttpOptions;
import java.lang.reflect.Field;
import java.util.Optional;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Test;


public class ClientTest {
  private static final String API_KEY = "api-key";
  private static final String PROJECT = "project";
  private static final String LOCATION = "location";
  private static final HttpOptions HTTP_OPTIONS = HttpOptions.builder().baseUrl("test-url").build();
  private static final DebugConfig DEBUG_CONFIG =
      new DebugConfig("replay", "replay-id", "replay-dir");
  private static final GoogleCredentials CREDENTIALS = GoogleCredentials.newBuilder().build();

  @Test
  public void testInitClientFromBuilder_mldev() {
    // Act
    Client client = Client.builder().apiKey(API_KEY).vertexAI(false).build();

    // Assert
    assertEquals(API_KEY, client.apiKey());
    assertEquals(null, client.project());
    assertEquals(null, client.location());
    assertFalse(client.vertexAI());
    assertEquals("https://generativelanguage.googleapis.com", client.baseUrl().orElse(null));
  }

  @Test
  public void testInitClientFromBuilder_vertex() {
    // Act
    Client client =
        Client.builder()
            .project(PROJECT)
            .location(LOCATION)
            .credentials(CREDENTIALS)
            .vertexAI(true)
            .build();

    // Assert
    assertEquals(null, client.apiKey());
    assertEquals(PROJECT, client.project());
    assertEquals(LOCATION, client.location());
    assertTrue(client.vertexAI());
    assertEquals(
        String.format("https://%s-aiplatform.googleapis.com", LOCATION),
        client.baseUrl().orElse(null));
  }

  @Test
  public void testInitClientFromBuilder_withCredentialsAndHttpOptions() {
    // Act
    Client client =
        Client.builder()
            .project(PROJECT)
            .location(LOCATION)
            .credentials(CREDENTIALS)
            .vertexAI(true)
            .httpOptions(HTTP_OPTIONS)
            .build();

    // Assert
    assertEquals(null, client.apiKey());
    assertEquals(PROJECT, client.project());
    assertEquals(LOCATION, client.location());
    assertTrue(client.vertexAI());
    assertEquals("test-url", client.baseUrl().orElse(null));
  }

  @Test
  public void testReplayClient_mldev() {
    // Act
    Client client =
        Client.builder().apiKey(API_KEY).vertexAI(false).debugConfig(DEBUG_CONFIG).build();

    // Assert
    assertEquals(API_KEY, client.apiKey());
    assertEquals("replay", client.clientMode());
    assertEquals("replay-id", client.debugConfig().replayId());
    assertEquals("replay-dir", client.debugConfig().replaysDirectory());
  }

  @Test
  public void testReplayClient_vertex() {

    // Act
    Client client =
        Client.builder()
            .project(PROJECT)
            .location(LOCATION)
            .credentials(CREDENTIALS)
            .vertexAI(true)
            .debugConfig(DEBUG_CONFIG)
            .build();

    // Assert
    assertEquals(PROJECT, client.project());
    assertEquals(LOCATION, client.location());
    assertTrue(client.vertexAI());
    assertEquals("replay", client.clientMode());
    assertEquals("replay-id", client.debugConfig().replayId());
    assertEquals("replay-dir", client.debugConfig().replaysDirectory());
  }

  @Test
  public void testInitClientFromBuilder_setApiKeyInVertex() {
    // Act
    Client client = Client.builder().apiKey(API_KEY).vertexAI(true).build();

    // Assert
    assertEquals(API_KEY, client.apiKey());
    assertEquals(null, client.project());
    assertEquals(null, client.location());
    assertTrue(client.vertexAI());
    assertEquals("https://aiplatform.googleapis.com", client.baseUrl().orElse(null));
  }

  @Test
  public void testInitClientFromBuilder_setProjectInMldev_throwsException() {
    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> Client.builder().vertexAI(false).project(PROJECT).build());

    // Assert
    assertEquals("Gemini API do not support project/location.", exception.getMessage());
  }

  @Test
  public void testInitClientFromBuilder_setBothApiKeyAndProject_throwsException() {
    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> Client.builder().apiKey(API_KEY).project(PROJECT).build());

    // Assert
    assertEquals(
        "Project/location and API key are mutually exclusive in the client initializer.",
        exception.getMessage());
  }

  @Test
  public void testSetDefaultBaseUrls() {
    // Act
    Client.setDefaultBaseUrls(Optional.of("gemini-base-url"), Optional.of("vertex-base-url"));
    Client vertexClient =
        Client.builder()
            .project(PROJECT)
            .location(LOCATION)
            .credentials(CREDENTIALS)
            .vertexAI(true)
            .build();
    Client mldevClient = Client.builder().apiKey(API_KEY).vertexAI(false).build();

    assertEquals("gemini-base-url", mldevClient.baseUrl().orElse(null));
    assertEquals("vertex-base-url", vertexClient.baseUrl().orElse(null));

    // Reset the base URLs after the test.
    Client.setDefaultBaseUrls(Optional.empty(), Optional.empty());
  }

  @Test
  public void testInitClientFromBuilder_globalLocation() {
    // Act
    Client client =
        Client.builder()
            .project(PROJECT)
            .location("global")
            .credentials(CREDENTIALS)
            .vertexAI(true)
            .build();

    // Assert
    assertEquals("global", client.location());
    assertTrue(client.vertexAI());
    assertEquals("https://aiplatform.googleapis.com", client.baseUrl().orElse(null));
  }

  @Test
  public void testInitClientFromBuilder_globalLocationWithCustomBaseUrl() {
    // Act
    Client client =
        Client.builder()
            .httpOptions(HttpOptions.builder().baseUrl("https://my-endpoint.com").build())
            .vertexAI(true)
            .build();

    // Assert
    assertEquals(null, client.location());
    assertTrue(client.vertexAI());
    assertEquals("https://my-endpoint.com", client.baseUrl().orElse(null));
  }

  @Test
  void enterpriseFlag_whenSetToTrue_isRespected() {
    Client client =
        Client.builder()
            .enterprise(true)
            .project("project")
            .location("location")
            .credentials(CREDENTIALS)
            .build();

    assertTrue(client.vertexAI());
  }

  @Test
  void enterpriseFlag_whenSetToFalse_isRespected() {
    Client client = Client.builder().enterprise(false).apiKey("api-key").build();
    assertFalse(client.vertexAI());
  }

  @Test
  void vertexAIFlag_whenSetToTrue_isRespected() {
    Client client =
        Client.builder()
            .vertexAI(true)
            .project("project")
            .location("location")
            .credentials(CREDENTIALS)
            .build();

    assertTrue(client.vertexAI());
  }

  @Test
  void conflictingFlags_throwsException() {
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                Client.builder()
                    .enterprise(true)
                    .vertexAI(false)
                    .credentials(CREDENTIALS)
                    .project("project")
                    .location("location")
                    .build());
    assertEquals(
        "enterprise and vertexAI flags have conflicting values, please set enterprise value only.",
        exception.getMessage());
  }

  @Test
  void nonConflictingFlags_doesNotThrowException() {
    Client client =
        Client.builder()
            .enterprise(true)
            .vertexAI(true)
            .project("project")
            .location("location")
            .credentials(CREDENTIALS)
            .build();

    assertTrue(client.vertexAI());
  }

  @Test
  void enterpriseEnvVar_isRespected() {
    try (MockedStatic<ApiClient> mocked =
            Mockito.mockStatic(ApiClient.class, Mockito.CALLS_REAL_METHODS);
        MockedStatic<GoogleCredentials> mockedCredentials =
            Mockito.mockStatic(GoogleCredentials.class)) {
      mockedCredentials.when(GoogleCredentials::getApplicationDefault).thenReturn(CREDENTIALS);

      mocked
          .when(ApiClient::defaultEnvironmentVariables)
          .thenReturn(
              ImmutableMap.of("enterprise", "true", "project", "project", "location", "location"));
      Client client = new Client();
      assertTrue(client.vertexAI());
    }
  }

  @Test
  void vertexAIEnvVar_isRespected() {
    try (MockedStatic<ApiClient> mocked =
            Mockito.mockStatic(ApiClient.class, Mockito.CALLS_REAL_METHODS);
        MockedStatic<GoogleCredentials> mockedCredentials =
            Mockito.mockStatic(GoogleCredentials.class)) {
      mockedCredentials.when(GoogleCredentials::getApplicationDefault).thenReturn(CREDENTIALS);

      mocked
          .when(ApiClient::defaultEnvironmentVariables)
          .thenReturn(
              ImmutableMap.of("vertexAI", "true", "project", "project", "location", "location"));
      Client client = new Client();
      assertTrue(client.vertexAI());
    }
  }

  @Test
  void enterpriseEnvVar_takesPrecedenceOverVertexAIEnvVar() {
    try (MockedStatic<ApiClient> mocked =
        Mockito.mockStatic(ApiClient.class, Mockito.CALLS_REAL_METHODS)) {
      mocked
          .when(ApiClient::defaultEnvironmentVariables)
          .thenReturn(
              ImmutableMap.of(
                  "enterprise", "false", "vertexAI", "true", "googleApiKey", "api-key"));
      Client client = new Client();
      assertFalse(client.vertexAI());
    }
  }

  @Test
  void flags_takePrecedenceOverEnvVars() {
    try (MockedStatic<ApiClient> mocked =
        Mockito.mockStatic(ApiClient.class, Mockito.CALLS_REAL_METHODS)) {
      mocked
          .when(ApiClient::defaultEnvironmentVariables)
          .thenReturn(ImmutableMap.of("enterprise", "false"));
      Client client =
          Client.builder()
              .enterprise(true)
              .project("project")
              .location("location")
              .credentials(CREDENTIALS)
              .build();

      assertTrue(client.vertexAI());
    }
  }

  @Test
  void defaultIsFalse_whenNoFlagsOrEnvsAreSet() {
    try (MockedStatic<ApiClient> mocked =
        Mockito.mockStatic(ApiClient.class, Mockito.CALLS_REAL_METHODS)) {
      mocked
          .when(ApiClient::defaultEnvironmentVariables)
          .thenReturn(ImmutableMap.of("googleApiKey", "api-key"));
      Client client = new Client();
      assertFalse(client.vertexAI());
    }
  }
}
