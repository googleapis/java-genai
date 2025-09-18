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

import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.File;
import com.google.genai.types.ListFilesConfig;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@EnabledIfEnvironmentVariable(
    named = "GOOGLE_GENAI_REPLAYS_DIRECTORY",
    matches = ".*genai/replays.*")
@ExtendWith(EnvironmentVariablesMockingExtension.class)
public class AsyncFilesTest {
  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testAsyncGetFile(boolean vertexAI) throws ExecutionException, InterruptedException {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(vertexAI, "tests/files/get/test_async." + suffix + ".json");
    String fileName = "files/vjvu9fwk2qj8";

    // Act
    if (vertexAI) {
      UnsupportedOperationException exception =
          assertThrows(
              UnsupportedOperationException.class,
              () -> client.async.files.get(fileName, null).get());

      // Assert
      assertEquals(
          "This method is only supported in the Gemini Developer client.", exception.getMessage());
    } else {
      File file = client.async.files.get(fileName, null).get();

      // Assert
      assertNotNull(file);
      assertEquals(fileName, file.name().get());
    }
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testAsyncListFiles(boolean vertexAI) throws ExecutionException, InterruptedException {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(vertexAI, "tests/files/list/test_async_pager." + suffix + ".json");
    ListFilesConfig config = ListFilesConfig.builder().pageSize(2).build();

    // Act
    if (vertexAI) {
      AsyncPager<File> pager = client.async.files.list(config).get();

      // Assert
      ExecutionException exception =
          assertThrows(
              ExecutionException.class, () -> pager.forEach(file -> assertNotNull(file)).get());
      assertTrue(exception.getCause() instanceof GenAiIOException);
      assertTrue(
          exception
              .getCause()
              .getMessage()
              .contains("This method is only supported in the Gemini Developer client."));
    } else {
      AsyncPager<File> pager = client.async.files.list(config).get();

      // Assert
      assertNotNull(pager);
      assertEquals(pager.pageSize().get(), 2);
      assertTrue(pager.size().get() <= 2);
      pager.forEach(item -> assertNotNull(item)).get();
      assertNotNull(pager.sdkHttpResponse().get().get().headers().get());
    }
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testAsyncUploadUnknownPath(boolean vertexAI) throws ExecutionException {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/files/upload/test_unknown_path_upload_async." + suffix + ".json");
    String filePath = "unknown_path";

    // Act & Assert
    ExecutionException exception =
        assertThrows(
            ExecutionException.class, () -> client.async.files.upload(filePath, null).get());
    assertTrue(exception.getCause() instanceof GenAiIOException);
    assertTrue(exception.getCause().getMessage().contains("Failed to upload file."));
    assertTrue(exception.getCause().getCause() instanceof java.io.FileNotFoundException);
  }
}
