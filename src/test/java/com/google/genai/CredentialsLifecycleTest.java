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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.gaos.SDKConfiguration;
import com.google.genai.gaos.models.credentials.Credential;
import com.google.genai.gaos.models.credentials.CredentialListResponse;
import com.google.genai.gaos.models.credentials.EnvironmentVariableConfig;
import com.google.genai.gaos.models.credentials.EnvironmentVariableConfigInjectionLocation;
import com.google.genai.gaos.models.credentials.EnvironmentVariableUpdateConfig;
import com.google.genai.gaos.models.credentials.EnvironmentVariableUpdateConfigInjectionLocation;
import com.google.genai.gaos.models.credentials.HttpBearerConfig;
import com.google.genai.gaos.models.credentials.HttpBearerUpdateConfig;
import com.google.genai.gaos.models.credentials.InjectionLocationEnum;
import com.google.genai.gaos.models.credentials.OAuth2Config;
import com.google.genai.gaos.models.credentials.OAuth2UpdateConfig;
import com.google.genai.gaos.models.credentials.Status;
import com.google.genai.gaos.models.credentials.Type;
import com.google.genai.gaos.models.operations.CreateCredentialResponse;
import com.google.genai.gaos.models.operations.GetCredentialResponse;
import com.google.genai.gaos.models.operations.ListCredentialsResponse;
import com.google.genai.gaos.utils.HTTPClient;
import com.google.genai.gaos.utils.Headers;
import com.google.genai.gaos.utils.transport.HttpRequest;
import com.google.genai.gaos.utils.transport.HttpResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Credentials lifecycle routing through the Google GenAI Client. Modeled after
 * credentials_lifecycle_test.ts.
 */
public class CredentialsLifecycleTest {

  private static final String API_KEY = "test-api-key";
  private static final ObjectMapper MAPPER = new ObjectMapper();

  private List<String> captured;
  private List<String> capturedBodies;
  private ScheduledExecutorService scheduler;

  @BeforeEach
  public void setUp() {
    captured = Collections.synchronizedList(new ArrayList<String>());
    capturedBodies = Collections.synchronizedList(new ArrayList<String>());
    scheduler = Executors.newSingleThreadScheduledExecutor();
  }

  @AfterEach
  public void tearDown() {
    if (scheduler != null) {
      scheduler.shutdownNow();
    }
  }

  private static String readUtf8(InputStream in) throws IOException {
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    byte[] data = new byte[1024];
    int nRead;
    while ((nRead = in.read(data, 0, data.length)) != -1) {
      buffer.write(data, 0, nRead);
    }
    return new String(buffer.toByteArray(), StandardCharsets.UTF_8);
  }

  private static HttpResponse<InputStream> createMockResponse(
      HttpRequest request, int statusCode, String jsonBody) {
    Headers headers = new Headers();
    headers.add("Content-Type", "application/json");
    return new HttpResponse<InputStream>(
        request,
        statusCode,
        headers,
        new ByteArrayInputStream(jsonBody.getBytes(StandardCharsets.UTF_8)));
  }

  private Client createClientWithMockTransport() throws Exception {
    Client client =
        Client.builder().apiKey(API_KEY).vertexAI(false).asyncRetryScheduler(scheduler).build();

    Field sdkConfigField = client.credentials.getClass().getDeclaredField("sdkConfiguration");
    sdkConfigField.setAccessible(true);
    SDKConfiguration sdkConfig = (SDKConfiguration) sdkConfigField.get(client.credentials);

    final HTTPClient existingClient = sdkConfig.client();
    Class<?> gaosHttpClientClass = Class.forName("com.google.genai.Client$GenAiGaosHttpClient");
    final Method authorizeMethod =
        gaosHttpClientClass.getDeclaredMethod("authorize", HttpRequest.class);
    authorizeMethod.setAccessible(true);

    HTTPClient delegatingClient =
        new HTTPClient() {
          @Override
          public HttpResponse<InputStream> send(HttpRequest request) {
            try {
              HttpRequest authorized =
                  (HttpRequest) authorizeMethod.invoke(existingClient, request);
              String method = authorized.method();
              String path = authorized.uri().getRawPath();
              if (authorized.uri().getRawQuery() != null) {
                path = path + "?" + authorized.uri().getRawQuery();
              }
              captured.add(method + " " + path);

              if (authorized.body().isPresent()
                  && ("POST".equals(method) || "PATCH".equals(method) || "PUT".equals(method))) {
                String bodyStr = readUtf8(authorized.body().get().stream());
                if (!bodyStr.isEmpty()) {
                  capturedBodies.add(bodyStr);
                }
              }

              if ("GET".equals(method) && "/v1beta/credentials".equals(path)) {
                return createMockResponse(
                    authorized,
                    200,
                    "{\"credentials\": ["
                        + "{\"id\": \"cred_bearer_123\", \"type\": \"bearer_token\", \"status\":"
                        + " \"active\", \"create_time\": \"2026-07-22T15:18:38Z\","
                        + " \"update_time\": \"2026-07-22T15:18:38Z\"},"
                        + "{\"id\": \"cred_env_123\", \"type\": \"environment_variable\","
                        + " \"status\": \"active\", \"create_time\": \"2026-07-22T15:18:38Z\","
                        + " \"update_time\": \"2026-07-22T15:18:38Z\"},"
                        + "{\"id\": \"cred_oauth_123\", \"type\": \"oauth2\", \"status\":"
                        + " \"active\", \"create_time\": \"2026-07-22T15:18:38Z\","
                        + " \"update_time\": \"2026-07-22T15:18:38Z\"}"
                        + "], \"next_page_token\": \"token_next_123\"}");
              }
              return createMockResponse(
                  authorized,
                  200,
                  "{\"id\": \"cred_bearer_123\", \"type\": \"bearer_token\", \"status\":"
                      + " \"active\", \"create_time\": \"2026-07-22T15:18:38Z\", \"update_time\":"
                      + " \"2026-07-22T15:18:38Z\"}");
            } catch (Exception e) {
              throw new RuntimeException(e);
            }
          }

          @Override
          public CompletableFuture<HttpResponse<InputStream>> sendAsync(HttpRequest request) {
            try {
              return CompletableFuture.completedFuture(send(request));
            } catch (Exception e) {
              CompletableFuture<HttpResponse<InputStream>> future =
                  new CompletableFuture<HttpResponse<InputStream>>();
              future.completeExceptionally(e);
              return future;
            }
          }
        };

    sdkConfig.setClient(delegatingClient);
    return client;
  }

  @Test
  public void testRoutesThroughGoogleGenAiClientForBearerOAuth2AndEnvVars() throws Exception {
    Client ai = createClientWithMockTransport();

    // 1. Bearer token credential creation with custom header and prefix
    CreateCredentialResponse bearerCred =
        ai.credentials.create(
            HttpBearerConfig.builder()
                .id("cred_bearer_123")
                .token("test-bearer-token")
                .headerName("X-Custom-Auth")
                .prefix("Token")
                .build());
    assertTrue(bearerCred.credential().isPresent());
    assertEquals("cred_bearer_123", bearerCred.credential().get().id().orElse(null));

    // 2. Environment variable credential creation with injection_location and trusted_domains
    ai.credentials.create(
        EnvironmentVariableConfig.builder()
            .id("cred_env_123")
            .value("super-secret-key")
            .injectionLocation(
                EnvironmentVariableConfigInjectionLocation.of(
                    Arrays.asList(InjectionLocationEnum.HEADER, InjectionLocationEnum.QUERY)))
            .trustedDomains(Arrays.asList("api.example.com", "service.example.org"))
            .build());

    // 3. OAuth2 credential creation with scopes
    ai.credentials.create(
        OAuth2Config.builder()
            .id("cred_oauth_123")
            .clientId("test-client-id")
            .clientSecret("test-client-secret")
            .refreshToken("test-refresh-token")
            .tokenUrl("https://oauth2.googleapis.com/token")
            .scopes(Collections.singletonList("https://www.googleapis.com/auth/cloud-platform"))
            .build());

    // 4. List credentials
    ListCredentialsResponse listResponse = ai.credentials.listDirect();
    assertTrue(listResponse.credentialListResponse().isPresent());
    assertEquals(
        3,
        listResponse
            .credentialListResponse()
            .get()
            .credentials()
            .orElse(Collections.<Credential>emptyList())
            .size());

    // 5. Get credential
    GetCredentialResponse fetched = ai.credentials.get("cred_bearer_123");
    assertTrue(fetched.credential().isPresent());
    assertEquals("cred_bearer_123", fetched.credential().get().id().orElse(null));

    // 6. Update bearer token credential
    ai.credentials.update(
        "cred_bearer_123",
        HttpBearerUpdateConfig.builder()
            .token("updated-token")
            .headerName("Authorization")
            .prefix("Bearer")
            .build());

    // 7. Update environment variable credential
    ai.credentials.update(
        "cred_env_123",
        EnvironmentVariableUpdateConfig.builder()
            .value("updated-secret-key")
            .injectionLocation(
                EnvironmentVariableUpdateConfigInjectionLocation.of(InjectionLocationEnum.HEADER))
            .trustedDomains(Collections.singletonList("api.example.com"))
            .build());

    // 8. Update OAuth2 credential
    ai.credentials.update(
        "cred_oauth_123",
        OAuth2UpdateConfig.builder()
            .clientSecret("updated-secret")
            .scopes(Arrays.asList("scope1", "scope2"))
            .build());

    // 9. Delete credential
    ai.credentials.delete("cred_bearer_123");

    assertEquals(
        Arrays.asList(
            "POST /v1beta/credentials",
            "POST /v1beta/credentials",
            "POST /v1beta/credentials",
            "GET /v1beta/credentials",
            "GET /v1beta/credentials/cred_bearer_123",
            "PATCH /v1beta/credentials/cred_bearer_123",
            "PATCH /v1beta/credentials/cred_env_123",
            "PATCH /v1beta/credentials/cred_oauth_123",
            "DELETE /v1beta/credentials/cred_bearer_123"),
        captured);

    // Verify request bodies
    assertEquals(
        MAPPER.readTree(
            "{\"id\": \"cred_bearer_123\", \"type\": \"bearer_token\", \"token\":"
                + " \"test-bearer-token\", \"header_name\": \"X-Custom-Auth\", \"prefix\":"
                + " \"Token\"}"),
        MAPPER.readTree(capturedBodies.get(0)));

    assertEquals(
        MAPPER.readTree(
            "{\"id\": \"cred_env_123\", \"type\": \"environment_variable\", \"value\":"
                + " \"super-secret-key\", \"injection_location\": [\"header\", \"query\"],"
                + " \"trusted_domains\": [\"api.example.com\", \"service.example.org\"]}"),
        MAPPER.readTree(capturedBodies.get(1)));

    assertEquals(
        MAPPER.readTree(
            "{\"id\": \"cred_oauth_123\", \"type\": \"oauth2\", \"client_id\": \"test-client-id\","
                + " \"client_secret\": \"test-client-secret\", \"refresh_token\":"
                + " \"test-refresh-token\", \"token_url\": \"https://oauth2.googleapis.com/token\","
                + " \"scopes\": [\"https://www.googleapis.com/auth/cloud-platform\"]}"),
        MAPPER.readTree(capturedBodies.get(2)));
  }

  @Test
  public void testAsyncCredentialsLifecycle() throws Exception {
    Client ai = createClientWithMockTransport();

    com.google.genai.gaos.models.operations.async.CreateCredentialResponse credential =
        ai.async
            .credentials
            .create(
                EnvironmentVariableConfig.builder()
                    .id("cred_env_123")
                    .value("super-secret-key")
                    .injectionLocation(
                        EnvironmentVariableConfigInjectionLocation.of(
                            Arrays.asList(
                                InjectionLocationEnum.HEADER, InjectionLocationEnum.QUERY)))
                    .trustedDomains(Collections.singletonList("api.example.com"))
                    .build())
            .get(5, TimeUnit.SECONDS);

    com.google.genai.gaos.models.operations.async.ListCredentialsResponse listRes =
        ai.async.credentials.listDirect().get(5, TimeUnit.SECONDS);

    com.google.genai.gaos.models.operations.async.GetCredentialResponse fetched =
        ai.async.credentials.get("cred_env_123").get(5, TimeUnit.SECONDS);

    com.google.genai.gaos.models.operations.async.UpdateCredentialResponse updated =
        ai.async
            .credentials
            .update(
                "cred_env_123",
                EnvironmentVariableUpdateConfig.builder()
                    .value("updated-secret")
                    .injectionLocation(
                        EnvironmentVariableUpdateConfigInjectionLocation.of(
                            InjectionLocationEnum.HEADER))
                    .build())
            .get(5, TimeUnit.SECONDS);

    ai.async.credentials.delete("cred_env_123").get(5, TimeUnit.SECONDS);

    assertTrue(credential.credential().isPresent());
    assertEquals("cred_bearer_123", credential.credential().get().id().orElse(null));
    assertTrue(fetched.credential().isPresent());
    assertEquals("cred_bearer_123", fetched.credential().get().id().orElse(null));
    assertTrue(updated.credential().isPresent());
    assertEquals("cred_bearer_123", updated.credential().get().id().orElse(null));
    assertTrue(listRes.credentialListResponse().isPresent());
    assertEquals(
        3,
        listRes
            .credentialListResponse()
            .get()
            .credentials()
            .orElse(Collections.<Credential>emptyList())
            .size());

    assertEquals(
        Arrays.asList(
            "POST /v1beta/credentials",
            "GET /v1beta/credentials",
            "GET /v1beta/credentials/cred_env_123",
            "PATCH /v1beta/credentials/cred_env_123",
            "DELETE /v1beta/credentials/cred_env_123"),
        captured);
  }

  @Test
  public void testCredentialsTypesAndModels() {
    OffsetDateTime now = OffsetDateTime.parse("2026-07-22T15:18:38Z");
    Credential cred =
        Credential.builder()
            .id("cred_123")
            .status(Status.ACTIVE)
            .type(Type.BEARER_TOKEN)
            .createTime(now)
            .updateTime(now)
            .build();
    assertEquals("cred_123", cred.id().orElse(null));
    assertEquals(Status.ACTIVE, cred.status().orElse(null));
    assertEquals(Type.BEARER_TOKEN, cred.type().orElse(null));
    assertNotNull(cred.createTime().orElse(null));
    assertNotNull(cred.updateTime().orElse(null));

    CredentialListResponse listResp =
        CredentialListResponse.builder()
            .credentials(Collections.singletonList(cred))
            .nextPageToken("next_tok")
            .build();
    assertEquals(1, listResp.credentials().orElse(Collections.<Credential>emptyList()).size());
    assertEquals("next_tok", listResp.nextPageToken().orElse(null));

    HttpBearerConfig bearer =
        HttpBearerConfig.builder()
            .id("cred_bearer")
            .token("secret-token")
            .headerName("X-Auth")
            .prefix("Bearer")
            .build();
    assertEquals("cred_bearer", bearer.id().orElse(null));
    assertEquals("secret-token", bearer.token().orElse(null));
    assertEquals("X-Auth", bearer.headerName().orElse(null));
    assertEquals("Bearer", bearer.prefix().orElse(null));
    assertEquals("bearer_token", bearer.type());

    OAuth2Config oauth =
        OAuth2Config.builder()
            .id("cred_oauth")
            .clientId("cid")
            .clientSecret("csecret")
            .refreshToken("rtoken")
            .tokenUrl("https://example.com/token")
            .scopes(Collections.singletonList("https://www.googleapis.com/auth/cloud-platform"))
            .build();
    assertEquals("cred_oauth", oauth.id().orElse(null));
    assertEquals("cid", oauth.clientId().orElse(null));
    assertEquals("oauth2", oauth.type());

    EnvironmentVariableConfig envVar =
        EnvironmentVariableConfig.builder()
            .id("cred_env")
            .value("secret-key")
            .injectionLocation(
                EnvironmentVariableConfigInjectionLocation.of(
                    Arrays.asList(InjectionLocationEnum.HEADER, InjectionLocationEnum.QUERY)))
            .trustedDomains(Collections.singletonList("api.example.com"))
            .build();
    assertEquals("cred_env", envVar.id().orElse(null));
    assertEquals("environment_variable", envVar.type());
  }
}
