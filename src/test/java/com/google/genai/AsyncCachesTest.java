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

import com.google.genai.errors.ServerException;
import com.google.genai.types.CachedContent;
import com.google.genai.types.Content;
import com.google.genai.types.CreateCachedContentConfig;
import com.google.genai.types.DeleteCachedContentResponse;
import com.google.genai.types.ListCachedContentsConfig;
import com.google.genai.types.Part;
import com.google.genai.types.UpdateCachedContentConfig;
import java.time.Duration;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@EnabledIfEnvironmentVariable(
    named = "GOOGLE_GENAI_REPLAYS_DIRECTORY",
    matches = ".*genai/replays.*")
@ExtendWith(EnvironmentVariablesMockingExtension.class)
public class AsyncCachesTest {
  private static final String CACHED_CONTENT_NAME_MLDEV = "cachedContents/op47f693jk6b";
  private static final String CACHED_CONTENT_NAME_VERTEX =
      "projects/801452371447/locations/us-central1/cachedContents/1899938500610883584";

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testAsyncCreateCachedContent(boolean vertexAI)
      throws ExecutionException, InterruptedException {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/caches/create/test_async_googleai_file_create." + suffix + ".json");

    String model = "gemini-1.5-pro-001";
    CreateCachedContentConfig config =
        CreateCachedContentConfig.builder()
            .contents(
                Content.fromParts(
                    Part.fromUri(
                        "https://generativelanguage.googleapis.com/v1beta/files/v200dhvn15h7",
                        "video/mp4")))
            .displayName("test cache")
            .ttl(Duration.ofSeconds(86400))
            .systemInstruction(Transformers.tContent("What is the sum of the two pdfs?"))
            .build();

    // Act
    if (vertexAI) {
      ExecutionException exception =
          assertThrows(
              ExecutionException.class, () -> client.async.caches.create(model, config).get());

      // Assert
      assertTrue(exception.getCause() instanceof ServerException);
      assertEquals(exception.getCause().getMessage(), "500 INTERNAL. Internal error encountered.");
    } else {
      CachedContent response = client.async.caches.create(model, config).get();

      // Assert
      assertNotNull(response);
    }
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testAsyncDeleteCachedContent(boolean vertexAI)
      throws ExecutionException, InterruptedException {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/caches/delete/test_async_delete." + suffix + ".json");
    String cacheName = vertexAI ? CACHED_CONTENT_NAME_VERTEX : CACHED_CONTENT_NAME_MLDEV;

    // Act
    DeleteCachedContentResponse response = client.async.caches.delete(cacheName, null).get();

    // Assert
    assertNotNull(response);
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testAsyncGetCachedContent(boolean vertexAI)
      throws ExecutionException, InterruptedException {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(vertexAI, "tests/caches/get/test_async_get." + suffix + ".json");
    String cacheName = vertexAI ? CACHED_CONTENT_NAME_VERTEX : CACHED_CONTENT_NAME_MLDEV;

    // Act
    CachedContent response = client.async.caches.get(cacheName, null).get();

    // Assert
    assertNotNull(response);
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testAsyncListCachedContents(boolean vertexAI)
      throws ExecutionException, InterruptedException {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(vertexAI, "tests/caches/list/test_async_pager." + suffix + ".json");
    ListCachedContentsConfig config = ListCachedContentsConfig.builder().pageSize(2).build();

    // Act
    AsyncPager<CachedContent> pager = client.async.caches.list(config).get();

    // Assert
    assertNotNull(pager);
    assertEquals(pager.pageSize().get(), 2);
    assertTrue(pager.size().get() <= 2);
    pager.forEach(item -> assertNotNull(item)).get();
    assertNotNull(pager.sdkHttpResponse().get().get().headers().get());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testAsyncUpdateCachedContent(boolean vertexAI)
      throws ExecutionException, InterruptedException {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/caches/update/test_async_update." + suffix + ".json");
    String cacheName = vertexAI ? CACHED_CONTENT_NAME_VERTEX : CACHED_CONTENT_NAME_MLDEV;

    UpdateCachedContentConfig config =
        UpdateCachedContentConfig.builder().ttl(Duration.ofSeconds(7600)).build();

    // Act
    CachedContent response = client.async.caches.update(cacheName, config).get();

    // Assert
    assertNotNull(response);
  }
}
