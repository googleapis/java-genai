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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.genai.types.HttpOptions;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AsyncLiveTest {
  private ApiClient apiClient;
  private AsyncLive asyncLive;

  @BeforeEach
  public void setUp() {
    apiClient = mock(ApiClient.class);
    asyncLive = new AsyncLive(apiClient);
  }

  @Test
  public void testGetWebSocketUri_VertexCustomBaseUrlNoAuth() throws Exception {
    when(apiClient.vertexAI()).thenReturn(true);
    when(apiClient.customBaseUrl()).thenReturn("https://my-custom-endpoint.com");
    when(apiClient.project()).thenReturn(null);
    when(apiClient.location()).thenReturn(null);
    when(apiClient.apiKey()).thenReturn(null);
    when(apiClient.httpOptions()).thenReturn(HttpOptions.builder().baseUrl("https://my-custom-endpoint.com").build());

    Method getWebSocketUri = AsyncLive.class.getDeclaredMethod("getWebSocketUri");
    getWebSocketUri.setAccessible(true);
    URI uri = (URI) getWebSocketUri.invoke(asyncLive);

    assertEquals("wss://my-custom-endpoint.com", uri.toString());
  }

  @Test
  public void testGetWebSocketHeaders_VertexQuotaProject() throws Exception {
    GoogleCredentials credentials = mock(GoogleCredentials.class);
    when(credentials.getAccessToken()).thenReturn(AccessToken.newBuilder().setTokenValue("test-token").build());
    when(credentials.getQuotaProjectId()).thenReturn("test-quota-project");

    when(apiClient.vertexAI()).thenReturn(true);
    when(apiClient.credentials()).thenReturn(credentials);
    when(apiClient.httpOptions()).thenReturn(HttpOptions.builder().build());

    Method getWebSocketHeaders = AsyncLive.class.getDeclaredMethod("getWebSocketHeaders");
    getWebSocketHeaders.setAccessible(true);
    Map<String, String> headers = (Map<String, String>) getWebSocketHeaders.invoke(asyncLive);

    assertEquals("Bearer test-token", headers.get("Authorization"));
    assertEquals("test-quota-project", headers.get("x-goog-user-project"));
  }

  @Test
  public void testGetWebSocketHeaders_VertexApiKey() throws Exception {
    when(apiClient.vertexAI()).thenReturn(true);
    when(apiClient.apiKey()).thenReturn("vertex-api-key");
    when(apiClient.credentials()).thenReturn(null);
    when(apiClient.httpOptions()).thenReturn(HttpOptions.builder().build());

    Method getWebSocketHeaders = AsyncLive.class.getDeclaredMethod("getWebSocketHeaders");
    getWebSocketHeaders.setAccessible(true);
    Map<String, String> headers = (Map<String, String>) getWebSocketHeaders.invoke(asyncLive);

    assertEquals("vertex-api-key", headers.get("x-goog-api-key"));
  }

  @Test
  public void testGetWebSocketUri_GoogleAiEphemeralToken() throws Exception {
    when(apiClient.vertexAI()).thenReturn(false);
    when(apiClient.apiKey()).thenReturn("auth_tokens/ephemeral-token");
    when(apiClient.httpOptions()).thenReturn(HttpOptions.builder()
        .baseUrl("https://generativelanguage.googleapis.com")
        .apiVersion("v1alpha")
        .build());

    Method getWebSocketUri = AsyncLive.class.getDeclaredMethod("getWebSocketUri");
    getWebSocketUri.setAccessible(true);
    URI uri = (URI) getWebSocketUri.invoke(asyncLive);

    assertTrue(uri.toString().contains("BidiGenerateContentConstrained"));
    assertTrue(uri.toString().contains("v1alpha"));
  }

  @Test
  public void testGetWebSocketHeaders_GoogleAiEphemeralToken() throws Exception {
    when(apiClient.vertexAI()).thenReturn(false);
    when(apiClient.apiKey()).thenReturn("auth_tokens/ephemeral-token");
    when(apiClient.httpOptions()).thenReturn(HttpOptions.builder().build());

    Method getWebSocketHeaders = AsyncLive.class.getDeclaredMethod("getWebSocketHeaders");
    getWebSocketHeaders.setAccessible(true);
    Map<String, String> headers = (Map<String, String>) getWebSocketHeaders.invoke(asyncLive);

    assertEquals("Token auth_tokens/ephemeral-token", headers.get("Authorization"));
  }
  @Test
  public void testOnMessage_PopulatesSetupCompleteWithVoiceConsent() throws Exception {
    CompletableFuture<AsyncSession> future = new CompletableFuture<>();
    URI uri = new URI("wss://test");
    Map<String, String> headers = new HashMap<>();
    String setupRequest = "{}";

    AsyncLive.GenAiWebSocketClient client =
        new AsyncLive.GenAiWebSocketClient(uri, headers, setupRequest, future, apiClient);

    String message =
        "{\"setupComplete\":{\"voiceConsentSignature\":{\"signature\":\"test_sig\"}}}";

    client.onMessage(message);

    AsyncSession session = future.get();
    assertTrue(session != null);
    assertTrue(session.setupComplete() != null);
    assertTrue(session.setupComplete().voiceConsentSignature().isPresent());
    assertEquals("test_sig", session.setupComplete().voiceConsentSignature().get().signature().get());
  }
}
