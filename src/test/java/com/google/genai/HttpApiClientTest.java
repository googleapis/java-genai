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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.genai.types.Candidate;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.Part;
import java.lang.reflect.Field;
import java.util.Optional;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
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
  private static final String TEST_PATH = "test-path";
  private static final String TEST_REQUEST_JSON = "{\"test\": \"request-json\"}";

  @Mock CloseableHttpClient mockHttpClient;

  private void setMockClient(HttpApiClient client) throws Exception {
    mockHttpClient = Mockito.mock(CloseableHttpClient.class);
    CloseableHttpResponse mockResponse = Mockito.mock(CloseableHttpResponse.class);
    when(mockHttpClient.execute(any())).thenReturn(mockResponse);
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
            Optional.of(PROJECT), Optional.of(LOCATION), Optional.empty(), Optional.empty());
    setMockClient(client);

    // Act
    client.request("POST", TEST_PATH, TEST_REQUEST_JSON, Optional.empty());

    // Assert
    ArgumentCaptor<HttpRequestBase> requestCaptor = ArgumentCaptor.forClass(HttpRequestBase.class);
    verify(mockHttpClient).execute(requestCaptor.capture());
    HttpRequestBase capturedRequest = requestCaptor.getValue();

    assertTrue(capturedRequest instanceof HttpPost);
    assertEquals("POST", capturedRequest.getMethod());
    assertEquals(
        String.format(
            "https://%s-aiplatform.googleapis.com/v1beta1/projects/%s/locations/%s/%s",
            LOCATION, PROJECT, LOCATION, TEST_PATH),
        capturedRequest.getURI().toString());
    Header authHeader = capturedRequest.getFirstHeader("Authorization");
    assertNotNull(authHeader);
    assertEquals("Bearer ", authHeader.getValue());
    assertNull(capturedRequest.getFirstHeader("x-goog-api-key"));

    HttpEntity entity = ((HttpPost) capturedRequest).getEntity();
    assertNotNull(entity);
    assertEquals(TEST_REQUEST_JSON, EntityUtils.toString(entity));
    assertTrue(
        entity.getContentType().getValue().contains(ContentType.APPLICATION_JSON.getMimeType()));
  }

  @Test
  public void testRequestGetMethodWithMldev() throws Exception {
    // Arrange
    HttpApiClient client = new HttpApiClient(Optional.of(API_KEY), Optional.empty());
    setMockClient(client);

    // Act
    client.request("GET", TEST_PATH, "", Optional.empty());

    // Assert
    ArgumentCaptor<HttpRequestBase> requestCaptor = ArgumentCaptor.forClass(HttpRequestBase.class);
    verify(mockHttpClient).execute(requestCaptor.capture());
    HttpRequestBase capturedRequest = requestCaptor.getValue();

    assertTrue(capturedRequest instanceof HttpGet);
    assertEquals("GET", capturedRequest.getMethod());
    assertEquals(
        "https://generativelanguage.googleapis.com/v1beta/" + TEST_PATH,
        capturedRequest.getURI().toString());
    Header authHeader = capturedRequest.getFirstHeader("x-goog-api-key");
    assertNotNull(authHeader);
    assertEquals(API_KEY, authHeader.getValue());
    assertNull(capturedRequest.getFirstHeader("Authorization"));
  }

  @Test
  public void testRequestDeleteMethodWithMldev() throws Exception {
    // Arrange
    HttpApiClient client = new HttpApiClient(Optional.of(API_KEY), Optional.empty());
    setMockClient(client);

    // Act
    client.request("DELETE", TEST_PATH, "", Optional.empty());

    // Assert
    ArgumentCaptor<HttpRequestBase> requestCaptor = ArgumentCaptor.forClass(HttpRequestBase.class);
    verify(mockHttpClient).execute(requestCaptor.capture());
    HttpRequestBase capturedRequest = requestCaptor.getValue();

    assertTrue(capturedRequest instanceof HttpDelete);
    assertEquals("DELETE", capturedRequest.getMethod());
    assertEquals(
        "https://generativelanguage.googleapis.com/v1beta/" + TEST_PATH,
        capturedRequest.getURI().toString());
    Header authHeader = capturedRequest.getFirstHeader("x-goog-api-key");
    assertNotNull(authHeader);
    assertEquals(API_KEY, authHeader.getValue());
    assertNull(capturedRequest.getFirstHeader("Authorization"));
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
    HttpApiClient client = new HttpApiClient(Optional.of(API_KEY), Optional.of(httpOptions));

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
            Optional.of(httpOptions));

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
    HttpApiClient client = new HttpApiClient(Optional.of("api-key"), Optional.of(httpOptions));

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
            Optional.of(httpOptions));

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
    HttpApiClient client = new HttpApiClient(Optional.of("api-key"), Optional.of(httpOptions));

    CloseableHttpClient httpClient = client.httpClient();
    assertNotNull(httpClient);

    RequestConfig config = getRequestConfig(httpClient);

    assertEquals(5000, config.getConnectTimeout());
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
        new HttpApiClient(project, location, credentials, Optional.of(httpOptions));

    CloseableHttpClient httpClient = client.httpClient();
    assertNotNull(httpClient);

    RequestConfig config = getRequestConfig(httpClient);

    assertEquals(4999, config.getConnectTimeout());
    assertEquals("project", client.project());
    assertEquals("location", client.location());
    assertTrue(client.vertexAI());
  }

  @Test
  public void testHttpClientNoTimeout() throws Exception {
    HttpOptions httpOptions = HttpOptions.builder().build();
    HttpApiClient client = new HttpApiClient(Optional.of("api-key"), Optional.of(httpOptions));

    CloseableHttpClient httpClient = client.httpClient();
    assertNotNull(httpClient);

    RequestConfig config = getRequestConfig(httpClient);

    assertEquals(-1, config.getConnectTimeout());
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
        new HttpApiClient(project, location, credentials, Optional.of(httpOptions));

    CloseableHttpClient httpClient = client.httpClient();
    assertNotNull(httpClient);

    RequestConfig config = getRequestConfig(httpClient);

    assertEquals(-1, config.getConnectTimeout());
    assertEquals("project", client.project());
    assertEquals("location", client.location());
    assertTrue(client.vertexAI());
    assertFalse(client.httpOptions.headers().get().containsKey("X-Server-Timeout"));
  }

  private RequestConfig getRequestConfig(CloseableHttpClient client) throws Exception {
    Field configField = client.getClass().getDeclaredField("defaultConfig");
    configField.setAccessible(true);
    return (RequestConfig) configField.get(client);
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
            Optional.of(httpOptions));
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
            Optional.of(httpOptions));

    assertEquals("global", client.location());
    assertTrue(client.vertexAI());
    assertEquals(Optional.of("https://aiplatform.googleapis.com"), client.httpOptions.baseUrl());
  }

  @Test
  public void testHttpClientVertexWithNoHttpOptions() throws Exception {
    HttpApiClient client =
        new HttpApiClient(
            Optional.of(PROJECT), Optional.of("global"), Optional.empty(), Optional.empty());

    assertEquals("global", client.location());
    assertTrue(client.vertexAI());
    assertEquals(Optional.of("https://aiplatform.googleapis.com"), client.httpOptions.baseUrl());
    assertFalse(client.httpOptions.headers().get().containsKey("X-Server-Timeout"));
  }

  @Test
  public void testHttpClientMLDevWithNoHttpOptions() throws Exception {
    HttpApiClient client = new HttpApiClient(Optional.of("api-key"), Optional.empty());

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
      Content content = Content.builder().parts(ImmutableList.of(part)).build();
      Candidate candidate = Candidate.builder().content(content).build();
      GenerateContentResponse fakeResponse =
          GenerateContentResponse.builder().candidates(ImmutableList.of(candidate)).build();
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
      Client client = Client.builder().httpOptions(httpOptions).build();

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
    Client client = Client.builder().httpOptions(httpOptions).build();

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
    Client client = Client.builder().httpOptions(httpOptions).build();

    assertTrue(client.baseUrl().isPresent());
    assertEquals(client.baseUrl().get(), "https://custom-base-url.googleapis.com/");
  }

  @Test
  public void testClientInitializationWithBaseUrlFromSetBaseUrls() throws Exception {
    Client.setDefaultBaseUrls(
        Optional.of("https://custom-base-url.googleapis.com/"), Optional.empty());
    Client client = Client.builder().build();

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
            .environmentVariables(
                ImmutableMap.of(
                    "GOOGLE_GEMINI_BASE_URL", "https://custom-base-url.googleapis.com/"))
            .build();

    assertTrue(client.baseUrl().isPresent());
    assertEquals(client.baseUrl().get(), "https://custom-base-url.googleapis.com/");
  }
}
