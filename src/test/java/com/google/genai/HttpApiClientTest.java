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
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HttpApiClientTest {

  private static final String API_KEY = "api-key";
  private static final String PROJECT = "project";
  private static final String LOCATION = "location";
  private static final HttpOptions defaultHttpOptionsMLDev =
      HttpApiClient.defaultHttpOptions(false, Optional.empty());
  private static final HttpOptions defaultHttpOptionsVertex =
      HttpApiClient.defaultHttpOptions(true, Optional.of(LOCATION));

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
  }

  @Test
  public void testInitHttpClientVertex() throws Exception {
    HttpOptions httpOptions =
        HttpOptions.builder()
            .baseUrl("https://aiplatform.googleapis.com/")
            .apiVersion("v1beta1")
            .headers(ImmutableMap.of("test", "header"))
            .timeout(5000)
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
    HttpOptions httpOptions = HttpOptions.builder().timeout(5000).build();
    Optional<String> project = Optional.of("project");
    Optional<String> location = Optional.of("location");
    Optional<GoogleCredentials> credentials =
        Optional.of(GoogleCredentials.getApplicationDefault());
    HttpApiClient client =
        new HttpApiClient(project, location, credentials, Optional.of(httpOptions));

    CloseableHttpClient httpClient = client.httpClient();
    assertNotNull(httpClient);

    RequestConfig config = getRequestConfig(httpClient);

    assertEquals(5000, config.getConnectTimeout());
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
    assertEquals(Optional.of("https://aiplatform.googleapis.com/"), client.httpOptions.baseUrl());
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
          client.models.generateContent("gemini-2.0-flash-exp", "What is your name?", null);

      assertEquals(response.text(), expectedText);
    } finally {
      wireMockServer.stop();
    }
  }
}
