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
import com.google.genai.types.Candidate;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
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

  @Test
  public void testHttpClientMLDevTimeout() throws Exception {
    HttpOptions httpOptions = HttpOptions.builder().timeout(5000).build();
    HttpApiClient client = new HttpApiClient(Optional.of("api-key"), Optional.of(httpOptions));

    CloseableHttpClient httpClient = client.httpClient();
    assertNotNull(httpClient);

    RequestConfig config = getRequestConfig(httpClient);

    assertEquals(5000, config.getConnectTimeout());
    assertEquals(5000, config.getConnectionRequestTimeout());
    assertEquals(5000, config.getSocketTimeout());
    assertEquals("api-key", client.apiKey());
    assertFalse(client.isVertexAI());
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
    assertEquals(5000, config.getConnectionRequestTimeout());
    assertEquals(5000, config.getSocketTimeout());
    assertEquals("project", client.project());
    assertEquals("location", client.location());
    assertTrue(client.isVertexAI());
  }

  @Test
  public void testHttpClientNoTimeout() throws Exception {
    HttpOptions httpOptions = HttpOptions.builder().build();
    HttpApiClient client = new HttpApiClient(Optional.of("api-key"), Optional.of(httpOptions));

    CloseableHttpClient httpClient = client.httpClient();
    assertNotNull(httpClient);

    RequestConfig config = getRequestConfig(httpClient);

    assertEquals(-1, config.getConnectTimeout());
    assertEquals(-1, config.getConnectionRequestTimeout());
    assertEquals(-1, config.getSocketTimeout());
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
    assertEquals(-1, config.getConnectionRequestTimeout());
    assertEquals(-1, config.getSocketTimeout());
    assertEquals("project", client.project());
    assertEquals("location", client.location());
    assertTrue(client.isVertexAI());
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
