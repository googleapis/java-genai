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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.RegisterFilesConfig;
import com.google.genai.types.RegisterFilesResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;

@ExtendWith(EnvironmentVariablesMockingExtension.class)
public final class FilesTest {

  @Test
  public void testRegisterFiles_verifyHeaders() throws Exception {
    // Arrange
    ApiClient mockApiClient = mock(ApiClient.class);
    when(mockApiClient.vertexAI()).thenReturn(false);

    String responseJson = "{\"files\": []}";
    Headers headers = Headers.of("Content-Type", "application/json");
    ResponseBody body = ResponseBody.create(responseJson, MediaType.parse("application/json"));
    FakeApiResponse fakeResponse = new FakeApiResponse(headers, body);

    @SuppressWarnings("unchecked")
    ArgumentCaptor<Optional<HttpOptions>> httpOptionsCaptor = ArgumentCaptor.forClass(Optional.class);
    when(mockApiClient.request(eq("post"), eq("files:register"), any(String.class), httpOptionsCaptor.capture()))
        .thenReturn(fakeResponse);

    Files files = new Files(mockApiClient);

    GoogleCredentials credentials =
        GoogleCredentials.newBuilder()
            .setAccessToken(new AccessToken("fake-token", null))
            .setQuotaProjectId("test-quota-project")
            .build();

    List<String> uris = Arrays.asList("gs://bucket/file");

    // Act
    files.registerFiles(credentials, uris, null);

    // Assert
    Optional<HttpOptions> capturedHttpOptions = httpOptionsCaptor.getValue();
    assertTrue(capturedHttpOptions.isPresent());
    Map<String, String> capturedHeaders = capturedHttpOptions.get().headers().get();
    assertEquals("Bearer fake-token", capturedHeaders.get("Authorization"));
    assertEquals("test-quota-project", capturedHeaders.get("x-goog-user-project"));
  }

  @Test
  public void testRegisterFiles_mldev() throws Exception {
    // Arrange
    ApiClient mockApiClient = mock(ApiClient.class);
    when(mockApiClient.vertexAI()).thenReturn(false);

    String responseJson = "{\"files\": [{\"name\": \"files/123\", \"uri\": \"gs://bucket/file\"}]}";
    Headers headers = Headers.of("Content-Type", "application/json");
    ResponseBody body = ResponseBody.create(responseJson, MediaType.parse("application/json"));
    FakeApiResponse fakeResponse = new FakeApiResponse(headers, body);

    when(mockApiClient.request(eq("post"), eq("files:register"), any(String.class), any()))
        .thenReturn(fakeResponse);

    Files files = new Files(mockApiClient);

    GoogleCredentials credentials =
        GoogleCredentials.newBuilder().setAccessToken(new AccessToken("fake-token", null)).build();

    List<String> uris = Arrays.asList("gs://bucket/file");

    // Act
    RegisterFilesResponse response = files.registerFiles(credentials, uris, null);

    // Assert
    assertNotNull(response);
    assertEquals(1, response.files().get().size());
    assertEquals("files/123", response.files().get().get(0).name().get());
  }

  @Test
  public void testRegisterFiles_vertexAIThrows() {
    // Arrange
    ApiClient mockApiClient = mock(ApiClient.class);
    when(mockApiClient.vertexAI()).thenReturn(true);

    Files files = new Files(mockApiClient);

    GoogleCredentials credentials = mock(GoogleCredentials.class);
    List<String> uris = Arrays.asList("gs://bucket/file");

    // Act & Assert
    UnsupportedOperationException exception =
        assertThrows(
            UnsupportedOperationException.class,
            () -> files.registerFiles(credentials, uris, null));
    assertEquals(
        "This method is only supported in the Gemini Developer client.", exception.getMessage());
  }

  @Test
  public void testRegisterFiles_withConfig() throws Exception {
    // Arrange
    ApiClient mockApiClient = mock(ApiClient.class);
    when(mockApiClient.vertexAI()).thenReturn(false);

    String responseJson = "{\"files\": []}";
    Headers headers = Headers.of("Content-Type", "application/json");
    ResponseBody body = ResponseBody.create(responseJson, MediaType.parse("application/json"));
    FakeApiResponse fakeResponse = new FakeApiResponse(headers, body);

    when(mockApiClient.request(eq("post"), eq("files:register"), any(String.class), any()))
        .thenReturn(fakeResponse);

    Files files = new Files(mockApiClient);

    GoogleCredentials credentials =
        GoogleCredentials.newBuilder()
            .setAccessToken(new AccessToken("fake-token", null))
            .setQuotaProjectId("test-quota-project")
            .build();

    List<String> uris = Arrays.asList("gs://bucket/file");
    RegisterFilesConfig config =
        RegisterFilesConfig.builder()
            .httpOptions(HttpOptions.builder().baseUrl("https://custom.url").build())
            .build();

    // Act
    RegisterFilesResponse response = files.registerFiles(credentials, uris, config);

    // Assert
    assertNotNull(response);
  }

  @Test
  public void testAsyncRegisterFiles_verifyHeaders() throws Exception {
    // Arrange
    ApiClient mockApiClient = mock(ApiClient.class);
    when(mockApiClient.vertexAI()).thenReturn(false);

    String responseJson = "{\"files\": []}";
    Headers headers = Headers.of("Content-Type", "application/json");
    ResponseBody body = ResponseBody.create(responseJson, MediaType.parse("application/json"));
    FakeApiResponse fakeResponse = new FakeApiResponse(headers, body);

    @SuppressWarnings("unchecked")
    ArgumentCaptor<Optional<HttpOptions>> httpOptionsCaptor = ArgumentCaptor.forClass(Optional.class);
    when(mockApiClient.asyncRequest(eq("post"), eq("files:register"), any(String.class), httpOptionsCaptor.capture()))
        .thenReturn(java.util.concurrent.CompletableFuture.completedFuture(fakeResponse));

    AsyncFiles asyncFiles = new AsyncFiles(mockApiClient);

    GoogleCredentials credentials =
        GoogleCredentials.newBuilder()
            .setAccessToken(new AccessToken("fake-token", null))
            .setQuotaProjectId("test-quota-project")
            .build();

    List<String> uris = Arrays.asList("gs://bucket/file");

    // Act
    asyncFiles.registerFiles(credentials, uris, null).get();

    // Assert
    Optional<HttpOptions> capturedHttpOptions = httpOptionsCaptor.getValue();
    assertTrue(capturedHttpOptions.isPresent());
    Map<String, String> capturedHeaders = capturedHttpOptions.get().headers().get();
    assertEquals("Bearer fake-token", capturedHeaders.get("Authorization"));
    assertEquals("test-quota-project", capturedHeaders.get("x-goog-user-project"));
  }

  @Test
  public void testAsyncRegisterFiles_mldev() throws Exception {
    // Arrange
    ApiClient mockApiClient = mock(ApiClient.class);
    when(mockApiClient.vertexAI()).thenReturn(false);

    String responseJson = "{\"files\": [{\"name\": \"files/123\", \"uri\": \"gs://bucket/file\"}]}";
    Headers headers = Headers.of("Content-Type", "application/json");
    ResponseBody body = ResponseBody.create(responseJson, MediaType.parse("application/json"));
    FakeApiResponse fakeResponse = new FakeApiResponse(headers, body);

    when(mockApiClient.asyncRequest(eq("post"), eq("files:register"), any(String.class), any()))
        .thenReturn(java.util.concurrent.CompletableFuture.completedFuture(fakeResponse));

    AsyncFiles asyncFiles = new AsyncFiles(mockApiClient);

    GoogleCredentials credentials =
        GoogleCredentials.newBuilder().setAccessToken(new AccessToken("fake-token", null)).build();

    List<String> uris = Arrays.asList("gs://bucket/file");

    // Act
    RegisterFilesResponse response = asyncFiles.registerFiles(credentials, uris, null).get();

    // Assert
    assertNotNull(response);
    assertEquals(1, response.files().get().size());
    assertEquals("files/123", response.files().get().get(0).name().get());
  }

  @Test
  public void testAsyncRegisterFiles_vertexAIThrows() throws Exception {
    // Arrange
    ApiClient mockApiClient = mock(ApiClient.class);
    when(mockApiClient.vertexAI()).thenReturn(true);

    AsyncFiles asyncFiles = new AsyncFiles(mockApiClient);

    GoogleCredentials credentials =
        GoogleCredentials.newBuilder().setAccessToken(new AccessToken("fake-token", null)).build();
    List<String> uris = Arrays.asList("gs://bucket/file");

    // Act & Assert
    java.util.concurrent.ExecutionException exception =
        assertThrows(
            java.util.concurrent.ExecutionException.class,
            () -> asyncFiles.registerFiles(credentials, uris, null).get());

    Throwable cause = exception.getCause();
    assertNotNull(cause);
    assertEquals(UnsupportedOperationException.class, cause.getClass());
    assertEquals(
        "This method is only supported in the Gemini Developer client.", cause.getMessage());
  }

  @Test
  public void testRegisterFiles_nullCredentialsThrows() {
    ApiClient mockApiClient = mock(ApiClient.class);
    when(mockApiClient.vertexAI()).thenReturn(false);
    Files files = new Files(mockApiClient);

    assertThrows(
        NullPointerException.class,
        () -> files.registerFiles(null, Arrays.asList("gs://uri"), null));
  }

  @Test
  public void testRegisterFiles_nullUrisThrows() {
    ApiClient mockApiClient = mock(ApiClient.class);
    when(mockApiClient.vertexAI()).thenReturn(false);
    Files files = new Files(mockApiClient);
    GoogleCredentials credentials = mock(GoogleCredentials.class);

    assertThrows(NullPointerException.class, () -> files.registerFiles(credentials, null, null));
  }

  @Test
  public void testRegisterFiles_refreshCredentialsFailsThrows() throws Exception {
    ApiClient mockApiClient = mock(ApiClient.class);
    when(mockApiClient.vertexAI()).thenReturn(false);
    Files files = new Files(mockApiClient);
    GoogleCredentials credentials = mock(GoogleCredentials.class);
    doThrow(new java.io.IOException("Refresh failed")).when(credentials).refreshIfExpired();

    assertThrows(
        GenAiIOException.class,
        () -> files.registerFiles(credentials, Arrays.asList("gs://uri"), null));
  }

  @Test
  public void testRegisterFiles_noAccessTokenThrows() throws Exception {
    ApiClient mockApiClient = mock(ApiClient.class);
    when(mockApiClient.vertexAI()).thenReturn(false);
    Files files = new Files(mockApiClient);
    GoogleCredentials credentials = GoogleCredentials.newBuilder().build();

    assertThrows(
        GenAiIOException.class,
        () -> files.registerFiles(credentials, Arrays.asList("gs://uri"), null));
  }
}
