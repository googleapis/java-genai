/*
 * Copyright 2026 Google LLC
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.genai.gaos.SDKConfiguration;
import com.google.genai.gaos.models.operations.CreateInteractionRequestBody;
import com.google.genai.gaos.utils.HTTPClient;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URI;
import com.google.genai.gaos.utils.HttpRequest;
import com.google.genai.gaos.utils.HttpResponse;
import com.google.genai.gaos.utils.HttpHeaders;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.Test;

public final class GaosClientTest {

  private static final String PROJECT = "test-project";
  private static final String LOCATION = "us-central1";
  private static final GoogleCredentials CREDENTIALS =
      GoogleCredentials.newBuilder()
          .setAccessToken(
              new AccessToken("test-token", new Date(System.currentTimeMillis() + 3600 * 1000)))
          .build();

  private void setMockGaosClient(Client client, HTTPClient mockClient) throws Exception {
    Field interactionsField = Client.class.getDeclaredField("interactions");
    interactionsField.setAccessible(true);
    Object interactions = interactionsField.get(client);

    Field sdkConfigField = interactions.getClass().getDeclaredField("sdkConfiguration");
    sdkConfigField.setAccessible(true);
    SDKConfiguration sdkConfig = (SDKConfiguration) sdkConfigField.get(interactions);

    sdkConfig.setClient(mockClient);
  }

  @Test
  public void testInteractionsUrl_vertex() throws Exception {
    Client client =
        Client.builder()
            .project(PROJECT)
            .location(LOCATION)
            .credentials(CREDENTIALS)
            .vertexAI(true)
            .build();

    AtomicReference<HttpRequest> capturedRequest = new AtomicReference<>();
    HTTPClient mockClient =
        new HTTPClient() {
          @Override
          public HttpResponse<InputStream> send(HttpRequest request) {
            capturedRequest.set(request);
            HttpResponse<InputStream> mockResponse = mock(HttpResponse.class);
            when(mockResponse.statusCode()).thenReturn(200);
            when(mockResponse.body())
                .thenReturn(new ByteArrayInputStream("{\"status\": \"completed\"}".getBytes()));
            HttpHeaders mockHeaders = mock(HttpHeaders.class);
            when(mockHeaders.firstValue("Content-Type"))
                .thenReturn(Optional.of("application/json"));
            when(mockResponse.headers()).thenReturn(mockHeaders);
            HttpRequest mockHttpRequest = mock(HttpRequest.class);
            when(mockHttpRequest.method()).thenReturn("POST");
            when(mockResponse.request()).thenReturn(mockHttpRequest);
            return mockResponse;
          }
        };
    setMockGaosClient(client, mockClient);

    com.google.genai.gaos.models.interactions.CreateModelInteraction body =
        com.google.genai.gaos.models.interactions.CreateModelInteraction.builder()
            .model(com.google.genai.gaos.models.interactions.Model.GEMINI25_FLASH)
            .input(com.google.genai.gaos.models.interactions.InteractionsInput.of("test-input"))
            .build();
    CreateInteractionRequestBody requestBody = CreateInteractionRequestBody.of(body);

    client.interactions.create(requestBody);

    HttpRequest req = capturedRequest.get();
    assertNotNull(req);
    assertEquals("POST", req.method());

    URI expectedUri =
        URI.create(
            "https://"
                + LOCATION
                + "-aiplatform.googleapis.com/v1beta1/projects/"
                + PROJECT
                + "/locations/"
                + LOCATION
                + "/interactions");
    assertEquals(expectedUri, req.uri());
    assertTrue(req.headers().firstValue("Authorization").orElse("").contains("Bearer test-token"));
  }

  @Test
  public void testInteractionsUrl_gemini() throws Exception {
    Client client = Client.builder().apiKey("test-api-key").vertexAI(false).build();

    AtomicReference<HttpRequest> capturedRequest = new AtomicReference<>();
    HTTPClient mockClient =
        new HTTPClient() {
          @Override
          public HttpResponse<InputStream> send(HttpRequest request) {
            capturedRequest.set(request);
            HttpResponse<InputStream> mockResponse = mock(HttpResponse.class);
            when(mockResponse.statusCode()).thenReturn(200);
            when(mockResponse.body())
                .thenReturn(new ByteArrayInputStream("{\"status\": \"completed\"}".getBytes()));
            HttpHeaders mockHeaders = mock(HttpHeaders.class);
            when(mockHeaders.firstValue("Content-Type"))
                .thenReturn(Optional.of("application/json"));
            when(mockResponse.headers()).thenReturn(mockHeaders);
            HttpRequest mockHttpRequest = mock(HttpRequest.class);
            when(mockHttpRequest.method()).thenReturn("POST");
            when(mockResponse.request()).thenReturn(mockHttpRequest);
            return mockResponse;
          }
        };
    setMockGaosClient(client, mockClient);

    com.google.genai.gaos.models.interactions.CreateModelInteraction body =
        com.google.genai.gaos.models.interactions.CreateModelInteraction.builder()
            .model(com.google.genai.gaos.models.interactions.Model.GEMINI25_FLASH)
            .input(com.google.genai.gaos.models.interactions.InteractionsInput.of("test-input"))
            .build();
    CreateInteractionRequestBody requestBody = CreateInteractionRequestBody.of(body);

    client.interactions.create(requestBody);

    HttpRequest req = capturedRequest.get();
    assertNotNull(req);
    assertEquals("POST", req.method());

    URI expectedUri = URI.create("https://generativelanguage.googleapis.com/v1beta/interactions");
    assertEquals(expectedUri, req.uri());
    assertEquals("test-api-key", req.headers().firstValue("x-goog-api-key").orElse(null));
  }

  @Test
  public void testClientHeadersPropagation() throws Exception {
    java.util.Map<String, String> customHeaders = new java.util.HashMap<>();
    customHeaders.put("custom-header-key", "custom-header-value");
    customHeaders.put("user-agent", "google-genai-sdk/1.12.0");
    com.google.genai.types.HttpOptions httpOptions =
        com.google.genai.types.HttpOptions.builder().headers(customHeaders).build();

    Client client =
        Client.builder().apiKey("test-api-key").vertexAI(false).httpOptions(httpOptions).build();

    AtomicReference<HttpRequest> capturedRequest = new AtomicReference<>();
    HTTPClient mockClient =
        new HTTPClient() {
          @Override
          public HttpResponse<InputStream> send(HttpRequest request) {
            capturedRequest.set(request);
            HttpResponse<InputStream> mockResponse = mock(HttpResponse.class);
            when(mockResponse.statusCode()).thenReturn(200);
            when(mockResponse.body())
                .thenReturn(new ByteArrayInputStream("{\"status\": \"completed\"}".getBytes()));
            HttpHeaders mockHeaders = mock(HttpHeaders.class);
            when(mockHeaders.firstValue("Content-Type"))
                .thenReturn(Optional.of("application/json"));
            when(mockResponse.headers()).thenReturn(mockHeaders);
            HttpRequest mockHttpRequest = mock(HttpRequest.class);
            when(mockHttpRequest.method()).thenReturn("POST");
            when(mockResponse.request()).thenReturn(mockHttpRequest);
            return mockResponse;
          }
        };
    setMockGaosClient(client, mockClient);

    com.google.genai.gaos.models.interactions.CreateModelInteraction body =
        com.google.genai.gaos.models.interactions.CreateModelInteraction.builder()
            .model(com.google.genai.gaos.models.interactions.Model.GEMINI25_FLASH)
            .input(com.google.genai.gaos.models.interactions.InteractionsInput.of("test-input"))
            .build();
    CreateInteractionRequestBody requestBody = CreateInteractionRequestBody.of(body);

    client.interactions.create(requestBody);

    HttpRequest req = capturedRequest.get();
    assertNotNull(req);
    assertEquals("custom-header-value", req.headers().firstValue("custom-header-key").orElse(null));
    // Verify rewrite of user-agent
    assertTrue(
        req.headers().firstValue("user-agent").orElse("").contains("google-genai-sdk/2.0.0"));
    // Verify rewrite or injection of x-goog-api-client
    assertTrue(
        req.headers()
            .firstValue("x-goog-api-client")
            .orElse("")
            .contains("google-genai-sdk/2.0.0"));
  }

  @Test
  public void testOtherInteractionsPaths_vertex() throws Exception {
    Client client =
        Client.builder()
            .project(PROJECT)
            .location(LOCATION)
            .credentials(CREDENTIALS)
            .vertexAI(true)
            .build();

    AtomicReference<HttpRequest> capturedRequest = new AtomicReference<>();
    HTTPClient mockClient =
        new HTTPClient() {
          @Override
          public HttpResponse<InputStream> send(HttpRequest request) {
            capturedRequest.set(request);
            HttpResponse<InputStream> mockResponse = mock(HttpResponse.class);
            when(mockResponse.statusCode()).thenReturn(200);
            when(mockResponse.body())
                .thenReturn(new ByteArrayInputStream("{\"status\": \"completed\"}".getBytes()));
            HttpHeaders mockHeaders = mock(HttpHeaders.class);
            when(mockHeaders.firstValue("Content-Type"))
                .thenReturn(Optional.of("application/json"));
            when(mockResponse.headers()).thenReturn(mockHeaders);
            HttpRequest mockHttpRequest = mock(HttpRequest.class);
            when(mockHttpRequest.method()).thenReturn("POST");
            when(mockResponse.request()).thenReturn(mockHttpRequest);
            return mockResponse;
          }
        };
    setMockGaosClient(client, mockClient);

    String interactionId = "test-interaction-id";
    String expectedUrlPrefix =
        "https://"
            + LOCATION
            + "-aiplatform.googleapis.com/v1beta1/projects/"
            + PROJECT
            + "/locations/"
            + LOCATION;

    // 1. Test Get
    client.interactions.get(
        com.google.genai.gaos.models.operations.GetInteractionByIdRequest.builder()
            .id(interactionId)
            .build());
    HttpRequest req = capturedRequest.get();
    assertNotNull(req);
    assertEquals("GET", req.method());
    assertEquals(
        URI.create(
            expectedUrlPrefix
                + "/interactions/"
                + interactionId
                + "?stream=false&include_input=false"),
        req.uri());

    // 2. Test Cancel
    capturedRequest.set(null);
    client.interactions.cancel(interactionId);
    req = capturedRequest.get();
    assertNotNull(req);
    assertEquals("POST", req.method());
    assertEquals(
        URI.create(expectedUrlPrefix + "/interactions/" + interactionId + "/cancel"), req.uri());

    // 3. Test Delete
    capturedRequest.set(null);
    client.interactions.delete(interactionId);
    req = capturedRequest.get();
    assertNotNull(req);
    assertEquals("DELETE", req.method());
    assertEquals(URI.create(expectedUrlPrefix + "/interactions/" + interactionId), req.uri());
  }

  @Test
  public void testLegacyLyriaOutputsNormalization() throws Exception {
    String legacyJson =
        "{\n"
            + "  \"id\": \"test-interaction-id\",\n"
            + "  \"status\": \"completed\",\n"
            + "  \"model\": \"lyria-3-pro-preview\",\n"
            + "  \"outputs\": [\n"
            + "    {\n"
            + "      \"parts\": [\n"
            + "        {\n"
            + "          \"text\": \"Hello Lyria\"\n"
            + "        }\n"
            + "      ]\n"
            + "    }\n"
            + "  ]\n"
            + "}";

    com.fasterxml.jackson.databind.ObjectMapper mapper = com.google.genai.gaos.utils.Utils.mapper();
    com.google.genai.gaos.models.interactions.Interaction interaction =
        mapper.readValue(legacyJson, com.google.genai.gaos.models.interactions.Interaction.class);

    assertNotNull(interaction);
    assertEquals("test-interaction-id", interaction.id().orElse(null));
    assertEquals(
        com.google.genai.gaos.models.interactions.InteractionStatus.COMPLETED,
        interaction.status());

    assertTrue(interaction.steps().isPresent());
    java.util.List<com.google.genai.gaos.models.interactions.Step> steps =
        interaction.steps().get();
    assertEquals(1, steps.size());

    com.google.genai.gaos.models.interactions.Step step = steps.get(0);
    assertTrue(step.value() instanceof com.google.genai.gaos.models.interactions.ModelOutputStep);

    com.google.genai.gaos.models.interactions.ModelOutputStep modelOutput =
        (com.google.genai.gaos.models.interactions.ModelOutputStep) step.value();
    assertEquals("model_output", modelOutput.type());
    assertTrue(modelOutput.content().isPresent());

    java.util.List<com.google.genai.gaos.models.interactions.Content> contents =
        modelOutput.content().get();
    assertEquals(1, contents.size());
  }

  @Test
  public void testAgentsAndWebhooksPaths_gemini() throws Exception {
    Client client = Client.builder().credentials(CREDENTIALS).vertexAI(false).build();

    AtomicReference<HttpRequest> capturedRequest = new AtomicReference<>();
    HTTPClient mockClient =
        new HTTPClient() {
          @Override
          public HttpResponse<InputStream> send(HttpRequest request) {
            capturedRequest.set(request);
            HttpResponse<InputStream> mockResponse = mock(HttpResponse.class);
            when(mockResponse.statusCode()).thenReturn(200);
            when(mockResponse.body())
                .thenReturn(
                    new ByteArrayInputStream(
                        "{\"uri\": \"https://example.com\", \"subscribed_events\": []}"
                            .getBytes()));
            HttpHeaders mockHeaders = mock(HttpHeaders.class);
            when(mockHeaders.firstValue("Content-Type"))
                .thenReturn(Optional.of("application/json"));
            when(mockResponse.headers()).thenReturn(mockHeaders);
            HttpRequest mockHttpRequest = mock(HttpRequest.class);
            when(mockHttpRequest.method()).thenReturn("GET");
            when(mockResponse.request()).thenReturn(mockHttpRequest);
            return mockResponse;
          }
        };
    setMockGaosClient(client, mockClient);

    String agentId = "test-agent-id";
    String webhookId = "test-webhook-id";
    String expectedUrlPrefix = "https://generativelanguage.googleapis.com/v1beta";

    // 1. Test Get Agent
    client.agents.get(agentId);
    HttpRequest req = capturedRequest.get();
    assertNotNull(req);
    assertEquals("GET", req.method());
    assertEquals(URI.create(expectedUrlPrefix + "/agents/" + agentId), req.uri());

    // 2. Test Get Webhook
    capturedRequest.set(null);
    client.webhooks.get(webhookId);
    req = capturedRequest.get();
    assertNotNull(req);
    assertEquals("GET", req.method());
    assertEquals(URI.create(expectedUrlPrefix + "/webhooks/" + webhookId), req.uri());
  }
}
