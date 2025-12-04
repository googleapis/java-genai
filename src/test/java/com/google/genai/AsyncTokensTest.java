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

import com.google.genai.types.AuthToken;
import com.google.genai.types.CreateAuthTokenConfig;
import com.google.genai.types.HttpOptions;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@EnabledIfEnvironmentVariable(
    named = "GOOGLE_GENAI_REPLAYS_DIRECTORY",
    matches = ".*genai/replays.*")
@ExtendWith(EnvironmentVariablesMockingExtension.class)
public class AsyncTokensTest {
  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testAsyncCreateAuthToken(boolean vertexAI)
      throws ExecutionException, InterruptedException {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/tokens/create/test_async_create_no_lock." + suffix + ".json");

    CreateAuthTokenConfig config =
        CreateAuthTokenConfig.builder()
            .httpOptions(
                HttpOptions.builder().apiVersion("v1alpha").build())
            .build();

    // Act
    if (vertexAI) {
      UnsupportedOperationException exception =
          assertThrows(
              UnsupportedOperationException.class,
              () -> client.async.authTokens.create(CreateAuthTokenConfig.builder().build()).get());
      assertEquals(
          "This method is only supported in the Gemini Developer client.", exception.getMessage());
    } else {
      AuthToken response = client.async.authTokens.create(config).get();

      // Assert
      assertNotNull(response);
    }
  }
}
