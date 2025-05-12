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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ImmutableList;
import com.google.genai.types.FeatureSelectionPreference;
import com.google.genai.types.FinishReason;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.HarmBlockThreshold;
import com.google.genai.types.HarmCategory;
import com.google.genai.types.ModelSelectionConfig;
import com.google.genai.types.SafetySetting;
import java.lang.reflect.Field;
import java.util.Optional;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class ForwardCompatibilityTest {

  ApiClient mockedClient;
  ApiResponse mockedResponse;
  HttpEntity mockedEntity;
  Client client;
  GenerateContentResponse returnResponse;
  ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    mockedClient = Mockito.mock(ApiClient.class);
    mockedResponse = Mockito.mock(ApiResponse.class);
    when(mockedClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(mockedResponse);
    mockedEntity = Mockito.mock(HttpEntity.class);
    returnResponse = GenerateContentResponse.builder().build();

    objectMapper = new ObjectMapper();

    client = Client.builder().build();
  }

  @Test
  public void testForwardCompatibility() throws Exception {
    // Mocks and test setup.
    JsonNode rootNode = objectMapper.readTree(returnResponse.toJson());
    ObjectNode objectNode = (ObjectNode) rootNode;
    objectNode.put("unknownFieldToTestForwardCompatibility", "Hello World!");
    String jsonString = objectMapper.writeValueAsString(objectNode);

    StringEntity content = new StringEntity(jsonString);
    when(mockedResponse.getEntity()).thenReturn(content);

    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Models.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.models, mockedClient);

    GenerateContentResponse response =
        client.models.generateContent("gemini-2.0-flash-exp", "What is your name?", null);

    assertNotNull(response);
  }

  @Test
  public void testEnumForwardCompatibilityFinishReason() throws Exception {
    /** Tests a response with two different unrecognized enum values for FinishReason. */
    JsonNode rootNode = objectMapper.readTree(returnResponse.toJson());
    ObjectNode rootObjectNode = (ObjectNode) rootNode;

    ArrayNode candidatesArray = objectMapper.createArrayNode();
    ObjectNode candidateObject = objectMapper.createObjectNode();
    candidateObject.put("finishReason", "NEW_UNKNOWN_VALUE");

    ObjectNode candidateObject2 = objectMapper.createObjectNode();
    candidateObject2.put("finishReason", "ANOTHER_VALUE");

    candidatesArray.add(candidateObject);
    candidatesArray.add(candidateObject2);
    rootObjectNode.putArray("candidates").addAll(candidatesArray);

    String jsonString = objectMapper.writeValueAsString(rootNode);
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
    Optional<FinishReason> finishReason1 = response.candidates().get().get(0).finishReason();
    Optional<FinishReason> finishReason2 = response.candidates().get().get(1).finishReason();

    if (finishReason1.isPresent()) {
      FinishReason finishReason = finishReason1.get();
      assert finishReason.toString().equals("NEW_UNKNOWN_VALUE");
      assert (finishReason.knownEnum().equals(FinishReason.Known.FINISH_REASON_UNSPECIFIED));
    }
    if (finishReason2.isPresent()) {
      FinishReason finishReason = finishReason2.get();
      assert finishReason.toString().equals("ANOTHER_VALUE");
      assert (finishReason.knownEnum().equals(FinishReason.Known.FINISH_REASON_UNSPECIFIED));
    }
  }

  @Test
  public void testFinishReasonEnumReturnsEnum() throws Exception {
    /** Tests knownEnum method returns the correct enum value. */
    JsonNode rootNode = objectMapper.readTree(returnResponse.toJson());
    ObjectNode rootObjectNode = (ObjectNode) rootNode;

    ArrayNode candidatesArray = objectMapper.createArrayNode();
    ObjectNode candidateObject = objectMapper.createObjectNode();
    candidateObject.put("finishReason", "MAX_TOKENS");
    candidatesArray.add(candidateObject);
    rootObjectNode.putArray("candidates").addAll(candidatesArray);

    String jsonString = objectMapper.writeValueAsString(rootNode);
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

    Optional<FinishReason> finishReasonResponse = response.candidates().get().get(0).finishReason();

    if (finishReasonResponse.isPresent()) {
      FinishReason finishReason = finishReasonResponse.get();
      assert (finishReason.knownEnum().equals(FinishReason.Known.MAX_TOKENS));
    }
  }

  @Test
  public void testEnumForwardCompatibilitySafetyFilterLevel() throws Exception {
    /** Tests setting an unrecognized enum value for SafetyFilterLevel. */
    JsonNode rootNode = objectMapper.readTree(returnResponse.toJson());
    ObjectNode rootObjectNode = (ObjectNode) rootNode;

    ArrayNode candidatesArray = objectMapper.createArrayNode();
    ObjectNode candidateObject = objectMapper.createObjectNode();
    ArrayNode safetyRatingsArray = objectMapper.createArrayNode();
    ObjectNode safetyRatingsObjectNode = objectMapper.createObjectNode();

    safetyRatingsObjectNode.put("category", "UNKNOWN_SAFETY_RATING_VALUE");
    safetyRatingsArray.add(safetyRatingsObjectNode);
    candidateObject.put("safetyRatings", safetyRatingsArray);
    candidatesArray.add(candidateObject);
    rootObjectNode.putArray("candidates").addAll(candidatesArray);

    String jsonString = objectMapper.writeValueAsString(rootNode);
    StringEntity content = new StringEntity(jsonString);
    when(mockedResponse.getEntity()).thenReturn(content);

    Client client = Client.builder().build();
    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on
    Field apiClientField = Models.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.models, mockedClient);

    GenerateContentResponse response =
        client.models.generateContent("gemini-2.0-flash-exp", "What is your name?", null);

    Optional<HarmCategory> harmCategory =
        response.candidates().get().get(0).safetyRatings().get().get(0).category();

    if (harmCategory.isPresent()) {
      assert (harmCategory.get().knownEnum()).equals(HarmCategory.Known.HARM_CATEGORY_UNSPECIFIED);
      assert (harmCategory.get().toString().equals("UNKNOWN_SAFETY_RATING_VALUE"));
    }
  }

  @Test
  public void testInlineEnum() throws Exception {
    /** Tests instantiating an inline enum (ModelRoutingPreference) in the request. */
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .modelSelectionConfig(
                ModelSelectionConfig.builder()
                    .featureSelectionPreference(FeatureSelectionPreference.Known.PRIORITIZE_QUALITY)
                    .build())
            .build();
  }

  public void testEnumSendingUnknownSafetysettings() throws Exception {
    /** Tests sending an enum value that is not in the enum definition. */
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .safetySettings(
                ImmutableList.of(
                    SafetySetting.builder()
                        .threshold(new HarmBlockThreshold("NEW_UNKNOWN_VALUE"))
                        .build()))
            .build();

    Client client = Client.builder().build();
    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on
    Field apiClientField = Models.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.models, mockedClient);
    when(mockedResponse.getEntity()).thenReturn(new StringEntity("{}"));

    GenerateContentResponse response =
        client.models.generateContent("gemini-2.0-flash-exp", "What is your name?", config);

    ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
    verify(mockedClient).request(anyString(), anyString(), stringCaptor.capture(), any());
    assertEquals(
        "{\"contents\":[{\"parts\":[{\"text\":\"What is your name?\"}],\"role\":\"user\"}],"
            + "\"safetySettings\":[{\"threshold\":\"NEW_UNKNOWN_VALUE\"}],\"generationConfig\":{}}",
        stringCaptor.getValue());
  }
}
