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

import com.google.genai.types.ClientOptions;
import com.google.genai.types.HttpOptions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.ResponseBody;

/** A fake client for testing File upload requests. */
public final class FakeUploadApiClient extends ApiClient {

  public final Map<String, UploadedFile> files;
  private final Map<String, Integer> fileUploadFailureCount;

  public FakeUploadApiClient() {
    super(
        Optional.of("fake-api-key"),
        Optional.<HttpOptions>empty(),
        Optional.<ClientOptions>empty());
    fileUploadFailureCount = new HashMap<>();
    this.files = new HashMap<>();
  }

  public void makeFileUploadFail(String path, int count) {
    fileUploadFailureCount.put(path, count);
  }

  @Override
  public ApiResponse request(
      String httpMethod, String path, String requestJson, Optional<HttpOptions> httpOptions) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }

  /** Sends a Http request given the http method, path, and request bytes. */
  @Override
  public ApiResponse request(
      String httpMethod, String path, byte[] requestBytes, Optional<HttpOptions> httpOptions) {
    if (!files.containsKey(path)) {
      UploadedFile newFile = new UploadedFile();
      newFile.uploadedByteCount = 0;
      newFile.uploadedBytes = new ArrayList<>();
      files.put(path, newFile);
    }

    Map<String, String> headers;
    if (httpOptions.isPresent() && httpOptions.get().headers().isPresent()) {
      headers = httpOptions.get().headers().get();
    } else {
      throw new IllegalArgumentException("HttpOptions is required for upload requests.");
    }

    if (!headers.containsKey("X-Goog-Upload-Command")) {
      throw new IllegalArgumentException("X-Goog-Upload-Command is required for upload requests.");
    }
    if (!headers.containsKey("X-Goog-Upload-Offset")) {
      throw new IllegalArgumentException("X-Goog-Upload-Offset is required for upload requests.");
    }

    UploadedFile file = files.get(path);

    if (!headers.get("X-Goog-Upload-Offset").equals(file.uploadedByteCount + "")) {
      throw new IllegalArgumentException(
          "X-Goog-Upload-Offset does not match the current uploaded byte count. Current: "
              + file.uploadedByteCount
              + " Offset: "
              + headers.get("X-Goog-Upload-Offset"));
    }
    if (!(headers.get("X-Goog-Upload-Command").equals("upload, finalize")
        || headers.get("X-Goog-Upload-Command").equals("upload"))) {
      throw new IllegalArgumentException("X-Goog-Upload-Command has an invalid value.");
    }

    if (fileUploadFailureCount.containsKey(path)) {
      if (fileUploadFailureCount.get(path) > 0) {
        fileUploadFailureCount.replace(path, fileUploadFailureCount.get(path) - 1);
      } else {
        fileUploadFailureCount.remove(path);
      }
      return new FakeApiResponse(
          Headers.of(), ResponseBody.create("", MediaType.get("text/plain")));
    }

    file.uploadedByteCount += requestBytes.length;
    for (byte b : requestBytes) {
      file.uploadedBytes.add(b);
    }

    boolean isFinalUpload = headers.get("X-Goog-Upload-Command").equals("upload, finalize");
    String uploadStatus = isFinalUpload ? "final" : "active";

    return new FakeApiResponse(
        Headers.of("X-Goog-Upload-Status", uploadStatus),
        ResponseBody.create("", MediaType.get("text/plain")));
  }

  /**
   * Sends an asynchronous Http request given the http method, path, request json string, and http
   * options.
   */
  @Override
  public CompletableFuture<ApiResponse> asyncRequest(
      String httpMethod, String path, String requestJson, Optional<HttpOptions> httpOptions) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }

  /**
   * Sends an asynchronous Http request given the http method, path, request bytes, and http
   * options.
   */
  @Override
  public CompletableFuture<ApiResponse> asyncRequest(
      String httpMethod, String path, byte[] requestBytes, Optional<HttpOptions> httpOptions) {
    return CompletableFuture.completedFuture(request(httpMethod, path, requestBytes, httpOptions));
  }

  public class UploadedFile {
    public int uploadedByteCount;
    public ArrayList<Byte> uploadedBytes;
    public String uploadStatus;
  }
}
