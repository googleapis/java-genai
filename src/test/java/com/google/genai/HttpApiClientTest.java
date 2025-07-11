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

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.collect.ImmutableMap;
import com.google.genai.types.Candidate;
import com.google.genai.types.ClientOptions;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.Part;
import java.lang.reflect.Field;
import java.util.Optional;
import okhttp3.Call;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

public class HttpApiClientTest {

  private static final String API_KEY = "api-key";
  private static final String PROJECT = "project";
  private static final String LOCATION = "location";
  private static final HttpOptions defaultHttpOptionsMLDev =
      HttpApiClient.defaultHttpOptions(false, Optional.empty());
  private static final HttpOptions defaultHttpOptionsVertex =
      HttpApiClient.defaultHttpOptions(true, Optional.of(LOCATION));
  private static final Optional<HttpOptions> REQUEST_HTTP_OPTIONS =
      Optional.of(
          HttpOptions.builder()
              .baseUrl("http://test-url")
              .apiVersion("test-api-version")
              .headers(ImmutableMap.of("test", "header"))
              .build());
  private static final String TEST_PATH = "test-path";
  private static final String TEST_REQUEST_JSON = "{\"test\": \"request-json\"}";

  @Mock OkHttpClient mockHttpClient;
  @Mock Call mockCall;

  private void setMockClient(HttpApiClient client) throws Exception {
    mockHttpClient = Mockito.mock(OkHttpClient.class);
    mockCall = Mockito.mock(Call.class);
    Response mockResponse =
        new Response.Builder()
            .request(new Request.Builder().url("https://example.com").build())
            .protocol(Protocol.HTTP_1_1)
            .code(200)
            .message("OK")
            .body(ResponseBody.create(null, "{}"))
            .build();
    when(mockHttpClient.newCall(any())).thenReturn(mockCall);
    when(mockCall.execute()).thenReturn(mockResponse);
    Field clientField = ApiClient.class.getDeclaredField("httpClient");
    clientField.setAccessible(true);
    clientField.set(client, mockHttpClient);
    GoogleCredentials credentials = Mockito.mock(GoogleCredentials.class);
    Field credentialsField = ApiClient.class.getDeclaredField("credentials");
    credentialsField.setAccessible(true);
    credentialsField.set(client, Optional.of(credentials));
  }
  
  @Test
  public void testRequestPostMethodWithVertexAI() throws Exception {
    // Arrange
    HttpApiClient client =
        new HttpApiClient(
            Optional.of(PROJECT),
            Optional.of(LOCATION),
            Optional.empty(),
            Optional.empty(),
            Optional.empty());
    setMockClient(client);

    // Act
    client.request("POST", TEST_PATH, TEST_REQUEST_JSON, Optional.empty());

    // Assert
    ArgumentCaptor<Request> requestCaptor = ArgumentCaptor.forClass(Request.class);
    verify(mockHttpClient).newCall(requestCaptor.capture());
    Request capturedRequest = requestCaptor.getValue();

    assertEquals("POST", capturedRequest.method());
    assertEquals(
        String.format(
            "https://%s-aiplatform.googleapis.com/v1beta1/projects/%s/locations/%s/%s",
            LOCATION, PROJECT, LOCATION, TEST_PATH),
        capturedRequest.url().toString());
    assertNotNull(capturedRequest.header("Authorization"));
    assertEquals("Bearer", capturedRequest.header("Authorization"));
    assertNull(capturedRequest.header("x-goog-api-key"));

    RequestBody body = capturedRequest.body();
    assertNotNull(body);
    final Buffer buffer = new Buffer();
    body.writeTo(buffer);
    assertEquals(TEST_REQUEST_JSON, buffer.readUtf8());
    assertEquals("application/json; charset=utf-8", body.contentType().toString());
  }

  @Test
  public void testRequestGetMethodWithMldev() throws Exception {
    // Arrange
    HttpApiClient client =
        new HttpApiClient(Optional.of(API_KEY), Optional.empty(), Optional.empty());
    setMockClient(client);

    // Act
    client.request("GET", TEST_PATH, "", Optional.empty());

    // Assert
    ArgumentCaptor<Request> requestCaptor = ArgumentCaptor.forClass(Request.class);
    verify(mockHttpClient).newCall(requestCaptor.capture());
    Request capturedRequest = requestCaptor.getValue();

    assertEquals("GET", capturedRequest.method());
    assertEquals(
        "https://generativelanguage.googleapis.com/v1beta/" + TEST_PATH,
        capturedRequest.url().toString());
    assertNotNull(capturedRequest.header("x-goog-api-key"));
    assertEquals(API_KEY, capturedRequest.header("x-goog-api-key"));
    assertNull(capturedRequest.header("Authorization"));
  }

  @Test
  public void testRequestDeleteMethodWithMldev() throws Exception {
    // Arrange
    HttpApiClient client =
        new HttpApiClient(Optional.of(API_KEY), Optional.empty(), Optional.empty());
    setMockClient(client);

    // Act
    client.request("DELETE", TEST_PATH, "", Optional.empty());

    // Assert
    ArgumentCaptor<Request> requestCaptor = ArgumentCaptor.forClass(Request.class);
    verify(mockHttpClient).newCall(requestCaptor.capture());
    Request capturedRequest = requestCaptor.getValue();

    assertEquals("DELETE", capturedRequest.method());
    assertEquals(
        "https://generativelanguage.googleapis.com/v1beta/" + TEST_PATH,
        capturedRequest.url().toString());
    assertNotNull(capturedRequest.header("x-goog-api-key"));
    assertEquals(API_KEY, capturedRequest.header("x-goog-api-key"));
    assertNull(capturedRequest.header("Authorization"));
  }

  @Test
  public void testRequestWithHttpOptions() throws Exception {
    // Arrange
    HttpApiClient client =
        new HttpApiClient(Optional.of(API_KEY), Optional.empty(), Optional.empty());
    setMockClient(client);

    // Act
    client.request("POST", TEST_PATH, TEST_REQUEST_JSON, REQUEST_HTTP_OPTIONS);

    // Assert
    ArgumentCaptor<Request> requestCaptor = ArgumentCaptor.forClass(Request.class);
    verify(mockHttpClient).newCall(requestCaptor.capture());
    Request capturedRequest = requestCaptor.getValue();

    assertEquals("POST", capturedRequest.method());
    // The request URL is set by the request-level http options.
    assertEquals("http://test-url/test-api-version/" + TEST_PATH, capturedRequest.url().toString());
    // Request should have the header set by the request-level http options.
    assertEquals("header", capturedRequest.header("test"));
  }

  @Test
  public void testRequestWithInvalidHttpMethod() throws Exception {
    // Arrange
    HttpApiClient client =
        new HttpApiClient(Optional.of(API_KEY), Optional.empty(), Optional.empty());

    // Act & Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> client.request("INVALID_METHOD", TEST_PATH, TEST_REQUEST_JSON, Optional.empty()));
  }

  @Test
  public void testInitHttpClientMldev() throws Exception {
    HttpOptions httpOptions =
        HttpOptions.builder()
            .baseUrl("https://generativelanguage.googleapis.com/")
            .apiVersion("v1beta")
            .headers(ImmutableMap.of("test", "application/json"))
            .timeout(5000)
            .build();
    HttpApiClient client =
        new HttpApiClient(Optional.of(API_KEY), Optional.of(httpOptions), Optional.empty());

    assertEquals(API_KEY, client.apiKey());
    assertFalse(client.vertexAI());
    assertEquals(httpOptions.baseUrl(), client.httpOptions.baseUrl());
    assertEquals(httpOptions.apiVersion(), client.httpOptions.apiVersion());
    assertEquals(httpOptions.timeout(), client.httpOptions.timeout());
    assertTrue(
        client
            .httpOptions
            .headers()
            .orElse(ImmutableMap.of())
            .entrySet()
            .containsAll(httpOptions.headers().orElse(ImmutableMap.of()).entrySet()));
    assertEquals("5", client.httpOptions.headers().get().get("X-Server-Timeout"));
  }

  @Test
  public void testInitHttpClientVertex() throws Exception {
    HttpOptions httpOptions =
        HttpOptions.builder()
            .baseUrl("https://aiplatform.googleapis.com")
            .apiVersion("v1beta1")
            .headers(ImmutableMap.of("test", "header"))
            .timeout(5001)
            .build();
    HttpApiClient client =
        new HttpApiClient(
            Optional.of(PROJECT),
            Optional.of(LOCATION),
            Optional.empty(),
            Optional.of(httpOptions),
            Optional.empty());

    assertEquals(PROJECT, client.project());
    assertEquals(LOCATION, client.location());
    assertTrue(client.vertexAI());
    assertEquals(httpOptions.baseUrl(), client.httpOptions.baseUrl());
    assertEquals(httpOptions.apiVersion(), client.httpOptions.apiVersion());
    assertEquals(httpOptions.timeout(), client.httpOptions.timeout());
    assertTrue(
        client
            .httpOptions
            .headers()
            .orElse(ImmutableMap.of())
            .entrySet()
            .containsAll(httpOptions.headers().orElse(ImmutableMap.of()).entrySet()));
    // Note that the timeout is rounded up to the nearest integer number of seconds.
    assertEquals("6", client.httpOptions.headers().get().get("X-Server-Timeout"));
  }

  @Test
  public void testInitHttpClientMldevWithPartialHttpOptions() throws Exception {
    HttpOptions httpOptions = HttpOptions.builder().apiVersion("v100").timeout(5000).build();
    HttpApiClient client =
        new HttpApiClient(Optional.of("api-key"), Optional.of(httpOptions), Optional.empty());

    assertEquals(httpOptions.apiVersion(), client.httpOptions.apiVersion());
    assertEquals(httpOptions.timeout(), client.httpOptions.timeout());
    // Default values for baseUrl and apiVersion are used.
    assertEquals(defaultHttpOptionsMLDev.baseUrl(), client.httpOptions.baseUrl());
    assertEquals(defaultHttpOptionsMLDev.headers(), client.httpOptions.headers());
    // Defaults to having no timeout.
    assertFalse(client.httpOptions.headers().get().containsKey("X-Server-Timeout"));
  }

  @Test
  public void testInitHttpClientVertexWithPartialHttpOptions() throws Exception {
    HttpOptions httpOptions = HttpOptions.builder().apiVersion("v100").timeout(5000).build();
    HttpApiClient client =
        new HttpApiClient(
            Optional.of(PROJECT),
            Optional.of(LOCATION),
            Optional.empty(),
            Optional.of(httpOptions),
            Optional.empty());

    assertEquals(httpOptions.apiVersion(), client.httpOptions.apiVersion());
    assertEquals(httpOptions.timeout(), client.httpOptions.timeout());
    // Default values for baseUrl and apiVersion are used.
    assertEquals(defaultHttpOptionsVertex.baseUrl(), client.httpOptions.baseUrl());
    assertEquals(defaultHttpOptionsVertex.headers(), client.httpOptions.headers());
    // Defaults to having no timeout.
    assertFalse(client.httpOptions.headers().get().containsKey("X-Server-Timeout"));
  }

  @Test
  public void testHttpClientMLDevTimeout() throws Exception {
    HttpOptions httpOptions = HttpOptions.builder().timeout(5000).build();
    HttpApiClient client =
        new HttpApiClient(Optional.of("api-key"), Optional.of(httpOptions), Optional.empty());

    OkHttpClient httpClient = client.httpClient();
    assertNotNull(httpClient);

    assertEquals(5000, httpClient.connectTimeoutMillis());
    assertEquals("api-key", client.apiKey());
    assertFalse(client.vertexAI());
  }

  @Test
  public void testHttpClientVertexTimeout() throws Exception {
    HttpOptions httpOptions = HttpOptions.builder().timeout(4999).build();
    Optional<String> project = Optional.of("project");
    Optional<String> location = Optional.of("location");
    Optional<GoogleCredentials> credentials =
        Optional.of(GoogleCredentials.getApplicationDefault());
    HttpApiClient client =
        new HttpApiClient(
            project, location, credentials, Optional.of(httpOptions), Optional.empty());

    OkHttpClient httpClient = client.httpClient();
    assertNotNull(httpClient);

    assertEquals(4999, httpClient.connectTimeoutMillis());
    assertEquals("project", client.project());
    assertEquals("location", client.location());
    assertTrue(client.vertexAI());
  }

  @Test
  public void testHttpClientNoTimeout() throws Exception {
    HttpOptions httpOptions = HttpOptions.builder().build();
    HttpApiClient client =
        new HttpApiClient(Optional.of("api-key"), Optional.of(httpOptions), Optional.empty());

    OkHttpClient httpClient = client.httpClient();
    assertNotNull(httpClient);

    assertEquals(0, httpClient.connectTimeoutMillis());
    assertEquals("api-key", client.apiKey());
    assertFalse(client.httpOptions.headers().get().containsKey("X-Server-Timeout"));
  }

  @Test
  public void testHttpClientVertexNoTimeout() throws Exception {
    HttpOptions httpOptions = HttpOptions.builder().build();
    Optional<String> project = Optional.of("project");
    Optional<String> location = Optional.of("location");
    Optional<GoogleCredentials> credentials =
        Optional.of(GoogleCredentials.getApplicationDefault());
    HttpApiClient client =
        new HttpApiClient(
            project, location, credentials, Optional.of(httpOptions), Optional.empty());

    OkHttpClient httpClient = client.httpClient();
    assertNotNull(httpClient);

    assertEquals(0, httpClient.connectTimeoutMillis());
    assertEquals("project", client.project());
    assertEquals("location", client.location());
    assertTrue(client.vertexAI());
    assertFalse(client.httpOptions.headers().get().containsKey("X-Server-Timeout"));
  }

  @Test
  public void testHttpClientMldevCustomClientOptions() throws Exception {
    ClientOptions clientOptions =
        ClientOptions.builder().maxConnections(64).maxConnectionsPerHost(16).build();
    HttpApiClient client =
        new HttpApiClient(Optional.of("api-key"), Optional.empty(), Optional.of(clientOptions));

    Dispatcher dispatcher = client.httpClient().dispatcher();

    assertEquals("api-key", client.apiKey());
    assertFalse(client.vertexAI());
    assertEquals(64, dispatcher.getMaxRequests());
    assertEquals(16, dispatcher.getMaxRequestsPerHost());
  }

  @Test
  public void testHttpClientVertexCustomClientOptions() throws Exception {
    ClientOptions clientOptions =
        ClientOptions.builder().maxConnections(64).maxConnectionsPerHost(16).build();
    HttpApiClient client =
        new HttpApiClient(
            Optional.of("project"),
            Optional.of("location"),
            Optional.empty(),
            Optional.empty(),
            Optional.of(clientOptions));

    Dispatcher dispatcher = client.httpClient().dispatcher();

    assertEquals("project", client.project());
    assertEquals("location", client.location());
    assertTrue(client.vertexAI());
    assertEquals(64, dispatcher.getMaxRequests());
    assertEquals(16, dispatcher.getMaxRequestsPerHost());
  }

  @Test
  public void testHttpClientMldevDefaultClientOptions() throws Exception {
    HttpApiClient client =
        new HttpApiClient(Optional.of("api-key"), Optional.empty(), Optional.empty());

    Dispatcher dispatcher = client.httpClient().dispatcher();

    // Default values for max connections and max connections per host are 64 and 5 respectively
    // (set by OkHttp). When maxConnectionsPerHost is not set, the dispatcher's maxRequestsPerHost
    // should be equal to 5.
    assertEquals(64, dispatcher.getMaxRequests());
    assertEquals(5, dispatcher.getMaxRequestsPerHost());
    assertEquals("api-key", client.apiKey());
    assertFalse(client.vertexAI());
  }

  @Test
  public void testHttpClientWithCustomCredentials() throws Exception {
    GoogleCredentials credentials = Mockito.mock(GoogleCredentials.class);
    HttpOptions httpOptions = HttpOptions.builder().build();
    HttpApiClient client =
        new HttpApiClient(
            Optional.of("project"),
            Optional.of("us-central1"),
            Optional.of(credentials),
            Optional.of(httpOptions),
            Optional.empty());
    assertEquals(credentials, client.credentials.get());
  }

  @Test
  public void testHttpClientVertexWithGlobalEndpoint() throws Exception {
    HttpOptions httpOptions = HttpOptions.builder().build();
    HttpApiClient client =
        new HttpApiClient(
            Optional.of(PROJECT),
            Optional.of("global"),
            Optional.empty(),
            Optional.of(httpOptions),
            Optional.empty());

    assertEquals("global", client.location());
    assertTrue(client.vertexAI());
    assertEquals(Optional.of("https://aiplatform.googleapis.com"), client.httpOptions.baseUrl());
  }

  @Test
  public void testHttpClientVertexWithNoHttpOptions() throws Exception {
    HttpApiClient client =
        new HttpApiClient(
            Optional.of(PROJECT),
            Optional.of("global"),
            Optional.empty(),
            Optional.empty(),
            Optional.empty());

    assertEquals("global", client.location());
    assertTrue(client.vertexAI());
    assertEquals(Optional.of("https://aiplatform.googleapis.com"), client.httpOptions.baseUrl());
    assertFalse(client.httpOptions.headers().get().containsKey("X-Server-Timeout"));
  }

  @Test
  public void testHttpClientMLDevWithNoHttpOptions() throws Exception {
    HttpApiClient client =
        new HttpApiClient(Optional.of("api-key"), Optional.empty(), Optional.empty());

    assertEquals(API_KEY, client.apiKey());
    assertFalse(client.vertexAI());
    assertEquals(
        Optional.of("https://generativelanguage.googleapis.com"), client.httpOptions.baseUrl());
    assertFalse(client.httpOptions.headers().get().containsKey("X-Server-Timeout"));
  }

  @Test
  public void testProxySetup() throws Exception {
    WireMockServer wireMockServer = null;
    try {
      wireMockServer = new WireMockServer(options().dynamicPort());
      wireMockServer.start();
      WireMock.configureFor("localhost", wireMockServer.port());
      String expectedText = "This is Proxy speaking, Hello, World!";
      Part part = Part.builder().text(expectedText).build();
      Content content = Content.fromParts(part);
      Candidate candidate = Candidate.builder().content(content).build();
      GenerateContentResponse fakeResponse =
          GenerateContentResponse.builder().candidates(candidate).build();
      stubFor(
          post(urlMatching(".*"))
              .willReturn(
                  aResponse()
                      .withStatus(200)
                      .withHeader("Content-Type", "application/json")
                      .withBody(fakeResponse.toJson())));

      HttpOptions httpOptions =
          HttpOptions.builder()
              .baseUrl("http://localhost:" + wireMockServer.port())
              .apiVersion("v1beta")
              .build();
      Client client =
          Client.builder().apiKey(API_KEY).vertexAI(false).httpOptions(httpOptions).build();

      GenerateContentResponse response =
          client.models.generateContent("gemini-2.0-flash", "What is your name?", null);

      assertEquals(response.text(), expectedText);
    } finally {
      wireMockServer.stop();
    }
  }

  @Test
  public void testClientInitializationWithBaseUrlFromHttpOptions() throws Exception {
    HttpOptions httpOptions =
        HttpOptions.builder().baseUrl("https://custom-base-url.googleapis.com/").build();
    Client client =
        Client.builder().apiKey(API_KEY).vertexAI(false).httpOptions(httpOptions).build();

    assertTrue(client.baseUrl().isPresent());
    assertEquals(client.baseUrl().get(), "https://custom-base-url.googleapis.com/");
  }

  @Test
  public void testClientInitializationWithBaseUrlFromHttpOptionsOverridesSetDefaultBaseUrls()
      throws Exception {
    HttpOptions httpOptions =
        HttpOptions.builder().baseUrl("https://custom-base-url.googleapis.com/").build();
    Client.setDefaultBaseUrls(
        Optional.of("https://gemini-base-url.googleapis.com/"), Optional.empty());
    Client client =
        Client.builder().apiKey(API_KEY).vertexAI(false).httpOptions(httpOptions).build();

    assertTrue(client.baseUrl().isPresent());
    assertEquals(client.baseUrl().get(), "https://custom-base-url.googleapis.com/");
  }

  @Test
  public void testClientInitializationWithBaseUrlFromSetBaseUrls() throws Exception {
    Client.setDefaultBaseUrls(
        Optional.of("https://custom-base-url.googleapis.com/"), Optional.empty());
    Client client = Client.builder().apiKey(API_KEY).vertexAI(false).build();

    assertTrue(client.baseUrl().isPresent());
    assertEquals(client.baseUrl().get(), "https://custom-base-url.googleapis.com/");

    Client.setDefaultBaseUrls(Optional.empty(), Optional.empty());
  }

  @Test
  public void testClientInitializationWithBaseUrlFromSetBaseUrlsOverridesEnvironment()
      throws Exception {
    Client.setDefaultBaseUrls(
        Optional.of("https://custom-base-url.googleapis.com/"), Optional.empty());
    Client client =
        Client.builder()
            .apiKey(API_KEY)
            .vertexAI(false)
            .environmentVariables(
                ImmutableMap.of(
                    "GOOGLE_GEMINI_BASE_URL", "https://gemini-base-url.googleapis.com/"))
            .build();

    assertTrue(client.baseUrl().isPresent());
    assertEquals(client.baseUrl().get(), "https://custom-base-url.googleapis.com/");

    Client.setDefaultBaseUrls(Optional.empty(), Optional.empty());
  }

  @Test
  public void testClientInitializationWithBaseUrlFromEnvironment() throws Exception {
    Client client =
        Client.builder()
            .apiKey(API_KEY)
            .vertexAI(false)
            .environmentVariables(
                ImmutableMap.of(
                    "GOOGLE_GEMINI_BASE_URL", "https://custom-base-url.googleapis.com/"))
            .build();

    assertTrue(client.baseUrl().isPresent());
    assertEquals(client.baseUrl().get(), "https://custom-base-url.googleapis.com/");
  }
}
