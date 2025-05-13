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
import com.google.genai.types.File;
import com.google.genai.types.GetFileConfig;
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
}
