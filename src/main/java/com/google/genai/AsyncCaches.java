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

import com.google.genai.types.CachedContent;
import com.google.genai.types.CreateCachedContentConfig;
import com.google.genai.types.DeleteCachedContentConfig;
import com.google.genai.types.DeleteCachedContentResponse;
import com.google.genai.types.GetCachedContentConfig;
import java.util.concurrent.CompletableFuture;

/** Async module of {@link Caches} */
public final class AsyncCaches {
  Caches caches;

  public AsyncCaches(ApiClient apiClient) {
    this.caches = new Caches(apiClient);
  }

  public CompletableFuture<CachedContent> create(String model, CreateCachedContentConfig config) {
    return CompletableFuture.supplyAsync(() -> caches.create(model, config));
  }

  public CompletableFuture<CachedContent> get(String name, GetCachedContentConfig config) {
    return CompletableFuture.supplyAsync(() -> caches.get(name, config));
  }

  public CompletableFuture<DeleteCachedContentResponse> delete(
      String name, DeleteCachedContentConfig config) {
    return CompletableFuture.supplyAsync(() -> caches.delete(name, config));
  }
}
