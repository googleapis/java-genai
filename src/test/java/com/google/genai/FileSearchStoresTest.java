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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.types.ChunkingConfig;
import com.google.genai.types.ClientOptions;
import com.google.genai.types.CustomMetadata;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.UploadToFileSearchStoreConfig;
import com.google.genai.types.UploadToFileSearchStoreOperation;
import com.google.genai.types.WhiteSpaceConfig;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;

public class FileSearchStoresTest {

  private static final String STORE_NAME = "fileSearchStores/store-1";
  private static final byte[] CONTENT = "hello".getBytes(StandardCharsets.UTF_8);

  /** Records the start request of a resumable upload and fakes both upload steps. */
  private static class RecordingApiClient extends ApiClient {
    String startPath;
    String startRequestJson;
    Optional<HttpOptions> startHttpOptions;

    RecordingApiClient() {
      super(
          Optional.of("fake-api-key"),
          Optional.<HttpOptions>empty(),
          Optional.<ClientOptions>empty());
    }

    @Override
    public ApiResponse request(
        String httpMethod, String path, String requestJson, Optional<HttpOptions> httpOptions) {
      startPath = path;
      startRequestJson = requestJson;
      startHttpOptions = httpOptions;
      return new FakeApiResponse(
          Headers.of("x-goog-upload-url", "https://upload.example.com/session-1"),
          ResponseBody.create("", MediaType.get("text/plain")));
    }

    @Override
    public ApiResponse request(
        String httpMethod, String path, byte[] requestBytes, Optional<HttpOptions> httpOptions) {
      return new FakeApiResponse(
          Headers.of("X-Goog-Upload-Status", "final"),
          ResponseBody.create(
              "{\"name\":\"fileSearchStores/store-1/upload/operations/op-1\"}",
              MediaType.get("application/json")));
    }

    @Override
    public CompletableFuture<ApiResponse> asyncRequest(
        String httpMethod, String path, String requestJson, Optional<HttpOptions> httpOptions) {
      return CompletableFuture.completedFuture(request(httpMethod, path, requestJson, httpOptions));
    }

    @Override
    public CompletableFuture<ApiResponse> asyncRequest(
        String httpMethod, String path, byte[] requestBytes, Optional<HttpOptions> httpOptions) {
      return CompletableFuture.completedFuture(
          request(httpMethod, path, requestBytes, httpOptions));
    }

    JsonNode startBody() throws Exception {
      return new ObjectMapper().readTree(startRequestJson);
    }
  }

  private static UploadToFileSearchStoreConfig fullConfig() {
    return UploadToFileSearchStoreConfig.builder()
        .mimeType("text/plain")
        .displayName("my-doc")
        .customMetadata(
            Arrays.asList(CustomMetadata.builder().key("author").stringValue("alice").build()))
        .chunkingConfig(
            ChunkingConfig.builder()
                .whiteSpaceConfig(
                    WhiteSpaceConfig.builder().maxTokensPerChunk(200).maxOverlapTokens(20).build())
                .build())
        .build();
  }

  private static void assertFullConfigInBody(JsonNode body) {
    assertEquals("my-doc", body.path("displayName").asText(null));
    assertEquals("author", body.path("customMetadata").path(0).path("key").asText(null));
    assertEquals("alice", body.path("customMetadata").path(0).path("stringValue").asText(null));
    assertEquals(
        200,
        body.path("chunkingConfig").path("whiteSpaceConfig").path("maxTokensPerChunk").asInt());
    assertEquals(
        20, body.path("chunkingConfig").path("whiteSpaceConfig").path("maxOverlapTokens").asInt());
  }

  /** The start request body carries the display name, metadata and chunking config. */
  @Test
  public void uploadToFileSearchStore_bytes_forwardsConfigFieldsInStartRequest() throws Exception {
    // Arrange
    RecordingApiClient apiClient = new RecordingApiClient();
    FileSearchStores fileSearchStores = new FileSearchStores(apiClient);

    // Act
    UploadToFileSearchStoreOperation operation =
        fileSearchStores.uploadToFileSearchStore(STORE_NAME, CONTENT, fullConfig());

    // Assert
    assertFullConfigInBody(apiClient.startBody());
    assertEquals(
        "upload/v1beta/fileSearchStores/store-1:uploadToFileSearchStore", apiClient.startPath);
    assertTrue(operation.name().isPresent());
  }

  /** The async client forwards the same config fields in its start request. */
  @Test
  public void asyncUploadToFileSearchStore_bytes_forwardsConfigFieldsInStartRequest()
      throws Exception {
    // Arrange
    RecordingApiClient apiClient = new RecordingApiClient();
    AsyncFileSearchStores fileSearchStores = new AsyncFileSearchStores(apiClient);

    // Act
    UploadToFileSearchStoreOperation operation =
        fileSearchStores.uploadToFileSearchStore(STORE_NAME, CONTENT, fullConfig()).get();

    // Assert
    assertFullConfigInBody(apiClient.startBody());
    assertEquals(
        "upload/v1beta/fileSearchStores/store-1:uploadToFileSearchStore", apiClient.startPath);
    assertTrue(operation.name().isPresent());
  }

  /** The InputStream overload also sends the user's config in the start request. */
  @Test
  public void uploadToFileSearchStore_inputStream_forwardsDisplayName() throws Exception {
    // Arrange
    RecordingApiClient apiClient = new RecordingApiClient();
    FileSearchStores fileSearchStores = new FileSearchStores(apiClient);
    UploadToFileSearchStoreConfig config =
        UploadToFileSearchStoreConfig.builder()
            .mimeType("text/plain")
            .displayName("my-doc")
            .build();

    // Act
    UploadToFileSearchStoreOperation operation =
        fileSearchStores.uploadToFileSearchStore(
            STORE_NAME, new ByteArrayInputStream(CONTENT), CONTENT.length, config);

    // Assert
    assertTrue(operation.name().isPresent());
    assertEquals("my-doc", apiClient.startBody().path("displayName").asText(null));
  }

  /** Fields the caller did not set are not sent in the start request. */
  @Test
  public void uploadToFileSearchStore_unsetFieldsAreOmittedFromStartRequest() throws Exception {
    // Arrange
    RecordingApiClient apiClient = new RecordingApiClient();
    FileSearchStores fileSearchStores = new FileSearchStores(apiClient);
    UploadToFileSearchStoreConfig config =
        UploadToFileSearchStoreConfig.builder().mimeType("text/plain").build();

    // Act
    UploadToFileSearchStoreOperation operation =
        fileSearchStores.uploadToFileSearchStore(STORE_NAME, CONTENT, config);

    // Assert
    assertTrue(operation.name().isPresent());
    JsonNode body = apiClient.startBody();
    assertFalse(body.has("displayName"));
    assertFalse(body.has("customMetadata"));
    assertFalse(body.has("chunkingConfig"));
  }

  /**
   * The start request always reads the upload URL from the response headers and uses the
   * resumable-upload headers, whatever the caller set for those two fields.
   */
  @Test
  public void uploadToFileSearchStore_overridesShouldReturnHttpResponseAndHttpOptions()
      throws Exception {
    // Arrange
    RecordingApiClient apiClient = new RecordingApiClient();
    FileSearchStores fileSearchStores = new FileSearchStores(apiClient);
    UploadToFileSearchStoreConfig config =
        UploadToFileSearchStoreConfig.builder()
            .mimeType("text/plain")
            .displayName("my-doc")
            .shouldReturnHttpResponse(false)
            .httpOptions(
                HttpOptions.builder().headers(Collections.singletonMap("X-Custom", "1")).build())
            .build();

    // Act
    UploadToFileSearchStoreOperation operation =
        fileSearchStores.uploadToFileSearchStore(STORE_NAME, CONTENT, config);

    // Assert
    assertTrue(operation.name().isPresent());
    Map<String, String> headers = apiClient.startHttpOptions.get().headers().get();
    assertEquals("start", headers.get("X-Goog-Upload-Command"));
    assertEquals("resumable", headers.get("X-Goog-Upload-Protocol"));
    assertEquals("my-doc", apiClient.startBody().path("displayName").asText(null));
  }
}
