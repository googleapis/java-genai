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

// Auto-generated code. Do not edit.

package com.google.genai;

import com.google.genai.types.DeleteFileConfig;
import com.google.genai.types.DeleteFileResponse;
import com.google.genai.types.DownloadFileConfig;
import com.google.genai.types.File;
import com.google.genai.types.GeneratedVideo;
import com.google.genai.types.GetFileConfig;
import com.google.genai.types.UploadFileConfig;
import com.google.genai.types.Video;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CompletableFuture;

/** Async module of {@link Files} */
public final class AsyncFiles {
  Files files;

  public AsyncFiles(ApiClient apiClient) {
    this.files = new Files(apiClient);
  }

  public CompletableFuture<File> get(String name, GetFileConfig config) {
    return CompletableFuture.supplyAsync(() -> files.get(name, config));
  }

  public CompletableFuture<DeleteFileResponse> delete(String name, DeleteFileConfig config) {
    return CompletableFuture.supplyAsync(() -> files.delete(name, config));
  }

  /**
   * Asynchronously uploads a file to the GenAI API.
   *
   * @param file The file to upload.
   * @param config The configuration for the upload.
   * @return A future that resolves to the uploaded file.
   */
  public CompletableFuture<File> upload(java.io.File file, UploadFileConfig config) {
    return CompletableFuture.supplyAsync(
        () -> {
          try {
            return files.upload(file, config);
          } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file: " + file.getAbsolutePath(), e);
          }
        });
  }

  /**
   * Asynchronously uploads a bytes array as a file to the GenAI API.
   *
   * @param bytes The bytes of the file to upload.
   * @param config The configuration for the upload.
   * @return A future that resolves to the uploaded file.
   */
  public CompletableFuture<File> upload(byte[] bytes, UploadFileConfig config) {
    return CompletableFuture.supplyAsync(
        () -> {
          try {
            return files.upload(bytes, config);
          } catch (IOException e) {
            throw new IllegalStateException("Failed to upload bytes", e);
          }
        });
  }

  /**
   * Asynchronously uploads a stream as a file to the GenAI API.
   *
   * @param stream The stream of the file to upload.
   * @param size The size of the file in bytes.
   * @param config The configuration for the upload.
   * @return A future that resolves to the uploaded file.
   */
  public CompletableFuture<File> upload(InputStream stream, long size, UploadFileConfig config) {
    return CompletableFuture.supplyAsync(
        () -> {
          try {
            return files.upload(stream, size, config);
          } catch (IOException e) {
            throw new IllegalStateException("Failed to upload stream", e);
          }
        });
  }

  /**
   * Asynchronously uploads a file by its path to the GenAI API.
   *
   * @param filePath The path to the file to upload.
   * @param config The configuration for the upload.
   * @return A future that resolves to the uploaded file.
   */
  public CompletableFuture<File> upload(String filePath, UploadFileConfig config) {
    return CompletableFuture.supplyAsync(
        () -> {
          try {
            return files.upload(filePath, config);
          } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file by path: " + filePath, e);
          }
        });
  }

  /**
   * Asynchronously downloads a file from the GenAI API to the provided path.
   *
   * @param fileName The name of the file to download.
   * @param downloadPath The path to download the file to.
   * @param config The configuration for the download.
   * @return A future that resolves to the downloaded file.
   */
  public CompletableFuture<Void> download(
      String fileName, String downloadPath, DownloadFileConfig config) {
    return CompletableFuture.runAsync(
        () -> {
          try {
            files.download(fileName, downloadPath, config);
          } catch (IOException e) {
            throw new IllegalStateException("Failed to download file: " + fileName, e);
          }
        });
  }

  /**
   * Asynchronously downloads a video from the GenAI API to the provided path.
   *
   * @param video The video to download.
   * @param downloadPath The path to download the video to.
   * @param config The configuration for the download.
   * @return A future that resolves to the downloaded video.
   */
  public CompletableFuture<Void> download(
      Video video, String downloadPath, DownloadFileConfig config) {
    return CompletableFuture.runAsync(
        () -> {
          try {
            files.download(video, downloadPath, config);
          } catch (IOException e) {
            throw new IllegalStateException("Failed to download video", e);
          }
        });
  }

  /**
   * Asynchronously downloads a generated video from the GenAI API to the provided path.
   *
   * @param video The generated video to download.
   * @param downloadPath The path to download the video to.
   * @param config The configuration for the download.
   * @return A future that resolves to the downloaded video.
   */
  public CompletableFuture<Void> download(
      GeneratedVideo video, String downloadPath, DownloadFileConfig config) {
    return CompletableFuture.runAsync(
        () -> {
          try {
            files.download(video, downloadPath, config);
          } catch (IOException e) {
            throw new IllegalStateException("Failed to download generated video", e);
          }
        });
  }

  /**
   * Asynchronously downloads a file from the GenAI API to the provided path.
   *
   * @param file The file to download.
   * @param downloadPath The path to download the file to.
   * @param config The configuration for the download.
   * @return A future that resolves to the downloaded file.
   */
  public CompletableFuture<Void> download(
      File file, String downloadPath, DownloadFileConfig config) {
    return CompletableFuture.runAsync(
        () -> {
          try {
            files.download(file, downloadPath, config);
          } catch (IOException e) {
            throw new IllegalStateException("Failed to download file: " + file.name(), e);
          }
        });
  }
}
