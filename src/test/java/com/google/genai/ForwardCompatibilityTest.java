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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.genai.types.GenerateContentResponse;
import java.lang.reflect.Field;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ForwardCompatibilityTest {
  @Test
  public void testForwardCompatibility() throws Exception {
    // Mocks and test setup.
    ApiClient mockedClient = Mockito.mock(ApiClient.class);
    ApiResponse mockedResponse = Mockito.mock(ApiResponse.class);
    when(mockedClient.post(anyString(), anyString())).thenReturn(mockedResponse);
    HttpEntity mockedEntity = Mockito.mock(HttpEntity.class);
    GenerateContentResponse returnResponse = GenerateContentResponse.builder().build();

    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode rootNode = objectMapper.readTree(returnResponse.toJson());
    ObjectNode objectNode = (ObjectNode) rootNode;
    objectNode.put("unknownFieldToTestForwardCompatibility", "Hello World!");
    String jsonString = objectMapper.writeValueAsString(objectNode);

    StringEntity content = new StringEntity(jsonString);
    when(mockedResponse.getEntity()).thenReturn(content);

    Client client = Client.builder().build();
    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Models.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.models, mockedClient);

    GenerateContentResponse response =
        client.models.generateContent("gemini-2.0-flash-exp", "What is your name?", null);

    assertNotNull(response);
  }
}
