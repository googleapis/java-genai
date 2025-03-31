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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ChatTest {
  @Test
  public void testCreateChatSession() throws Exception {
    // Mocks and test setup.
    ApiClient mockedClient = Mockito.mock(ApiClient.class);
    ApiResponse mockedResponse = Mockito.mock(ApiResponse.class);
    when(mockedClient.post(anyString(), anyString())).thenReturn(mockedResponse);
    HttpEntity mockedEntity = Mockito.mock(HttpEntity.class);

    Client client = Client.builder().build();
    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Models.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.models, mockedClient);

    Chat chat = client.chats.create("gemini-2.0-flash-exp", null, new ArrayList<>());

    assertNotNull(chat);
  }

  @Test
  public void testChatSendMessage() throws Exception {
    // Mocks and test setup.
    ApiClient mockedClient = Mockito.mock(ApiClient.class);
    ApiResponse mockedResponse = Mockito.mock(ApiResponse.class);
    when(mockedClient.post(anyString(), anyString())).thenReturn(mockedResponse);
    HttpEntity mockedEntity = Mockito.mock(HttpEntity.class);
    GenerateContentResponse returnResponse = GenerateContentResponse.builder().build();

    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode rootNode = objectMapper.readTree(returnResponse.toJson());
    ObjectNode objectNode = (ObjectNode) rootNode;
    ArrayNode candidatesArray = objectMapper.createArrayNode();
    ObjectNode candidateObject = objectMapper.createObjectNode();
    candidateObject.put("content", "I'm doing great!");
    candidatesArray.add(candidateObject);
    objectNode.putArray("candidates").addAll(candidatesArray);
    String jsonString = objectMapper.writeValueAsString(objectNode);

    StringEntity content = new StringEntity(jsonString);
    when(mockedResponse.getEntity()).thenReturn(content);

    Client client = Client.builder().build();
    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Models.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.models, mockedClient);

    Chat chatSession = client.chats.create("gemini-2.0-flash-exp", null, new ArrayList<>());
    GenerateContentResponse response =
        chatSession.sendMessage(
            Content.builder().build().fromParts(Part.fromText("How are you?")), null);

    assertNotNull(response.text());
  }

  @Test
  public void testGetHistory() throws Exception {
    // Mocks and test setup.
    ApiClient mockedClient = Mockito.mock(ApiClient.class);
    ApiResponse mockedResponse = Mockito.mock(ApiResponse.class);
    when(mockedClient.post(anyString(), anyString())).thenReturn(mockedResponse);
    HttpEntity mockedEntity = Mockito.mock(HttpEntity.class);
    GenerateContentResponse returnResponse = GenerateContentResponse.builder().build();

    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode rootNode = objectMapper.readTree(returnResponse.toJson());
    ObjectNode objectNode = (ObjectNode) rootNode;
    ArrayNode candidatesArray = objectMapper.createArrayNode();
    ObjectNode candidateObject = objectMapper.createObjectNode();
    candidateObject.put("content", "I'm doing great!");
    candidatesArray.add(candidateObject);
    objectNode.putArray("candidates").addAll(candidatesArray);
    String jsonString = objectMapper.writeValueAsString(objectNode);

    StringEntity content = new StringEntity(jsonString);
    when(mockedResponse.getEntity()).thenReturn(content);

    Client client = Client.builder().build();
    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Models.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.models, mockedClient);

    Chat chatSession = client.chats.create("gemini-2.0-flash-exp", null, new ArrayList<>());
    assert chatSession.getHistory(false).isEmpty();

    GenerateContentResponse response = chatSession.sendMessage("How are you?", null);

    assert chatSession.getHistory(false).size() == 2;
  }

  @Test
  public void testCuratedHistoryExcludesEmptyParts() throws Exception {
    // Mocks and test setup.
    ApiClient mockedClient = Mockito.mock(ApiClient.class);
    ApiResponse mockedResponse = Mockito.mock(ApiResponse.class);
    when(mockedClient.post(anyString(), anyString())).thenReturn(mockedResponse);

    Client client = Client.builder().build();
    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Models.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.models, mockedClient);

    List<Content> invalidHistory = new ArrayList<>();
    Part userTextPart = Part.builder().text("How are you?").build();
    Content userContent1 =
        Content.builder().role("user").parts(Arrays.asList(userTextPart)).build();
    Part modelTextPart = Part.builder().text("I am doing great!").build();
    Content modelContent1 =
        Content.builder().role("model").parts(Arrays.asList(modelTextPart)).build();
    invalidHistory.add(userContent1);
    invalidHistory.add(modelContent1);
    // These are empty parts, so they should be excluded from the curated history but included in
    // the comprehensive history.
    invalidHistory.add(Content.builder().build().fromParts(Part.builder().build()));
    invalidHistory.add(Content.builder().build().fromParts(Part.fromText("")));

    Chat chatSession = client.chats.create("gemini-2.0-flash-exp", null, invalidHistory);

    List<Content> curatedHistory = chatSession.getHistory(true);
    List<Content> comprehensiveHistory = chatSession.getHistory(false);
    assert curatedHistory.size() == 2;
    assert comprehensiveHistory.size() == 4;
  }

  @Test
  public void testMultiTurnChat() throws Exception {
    // Mocks and test setup.
    ApiClient mockedClient = Mockito.mock(ApiClient.class);
    ApiResponse mockedResponse = Mockito.mock(ApiResponse.class);
    when(mockedClient.post(anyString(), anyString())).thenReturn(mockedResponse);
    HttpEntity mockedEntity = Mockito.mock(HttpEntity.class);
    GenerateContentResponse returnResponse = GenerateContentResponse.builder().build();

    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode rootNode = objectMapper.readTree(returnResponse.toJson());
    ObjectNode objectNode = (ObjectNode) rootNode;
    ArrayNode candidatesArray = objectMapper.createArrayNode();
    ObjectNode candidateObject = objectMapper.createObjectNode();
    candidateObject.put("content", "I'm doing great!");
    candidatesArray.add(candidateObject);
    objectNode.putArray("candidates").addAll(candidatesArray);
    String jsonString = objectMapper.writeValueAsString(objectNode);

    StringEntity content = new StringEntity(jsonString);
    when(mockedResponse.getEntity()).thenReturn(content);

    Client client = Client.builder().build();
    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Models.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.models, mockedClient);

    Chat chatSession = client.chats.create("gemini-2.0-flash-exp", null, new ArrayList<>());
    assert chatSession.getHistory(false).isEmpty();

    GenerateContentResponse response = chatSession.sendMessage("How are you?", null);

    assert chatSession.getHistory(false).size() == 2;

    GenerateContentResponse response2 = chatSession.sendMessage("What should I do today?", null);

    assert chatSession.getHistory(false).size() == 4;
    assert chatSession.getHistory(true).size() == 4;
  }

  @Test
  public void testInvalidRoleThrows() throws Exception {
    // Mocks and test setup.
    ApiClient mockedClient = Mockito.mock(ApiClient.class);
    ApiResponse mockedResponse = Mockito.mock(ApiResponse.class);
    when(mockedClient.post(anyString(), anyString())).thenReturn(mockedResponse);

    Client client = Client.builder().build();
    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Models.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.models, mockedClient);

    List<Content> invalidHistory = new ArrayList<>();
    Part userTextPart = Part.builder().text("What is your favorite song?").build();
    Content validContent =
        Content.builder().role("user").parts(Arrays.asList(userTextPart)).build();
    Part invalidTextPart = Part.builder().text("All Too Well 10 Minute Version").build();
    Content invalidContent =
        Content.builder().role("Taylor Swift").parts(Arrays.asList(invalidTextPart)).build();
    invalidHistory.add(validContent);
    invalidHistory.add(invalidContent);

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> client.chats.create("gemini-2.0-flash-exp", null, invalidHistory));

    assertEquals(
        exception.getMessage(), "The role of the message must be either 'user' or 'model'.");
  }

  @Test
  public void testInvalidHistoryOrderThrows() throws Exception {
    // Mocks and test setup.
    ApiClient mockedClient = Mockito.mock(ApiClient.class);
    ApiResponse mockedResponse = Mockito.mock(ApiResponse.class);
    when(mockedClient.post(anyString(), anyString())).thenReturn(mockedResponse);

    Client client = Client.builder().build();
    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Models.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.models, mockedClient);

    List<Content> invalidHistory = new ArrayList<>();
    Part userTextPart = Part.builder().text("I am a response from the model").build();
    Content invalidContent =
        Content.builder().role("model").parts(Arrays.asList(userTextPart)).build();
    invalidHistory.add(invalidContent);

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> client.chats.create("gemini-2.0-flash-exp", null, invalidHistory));

    assertEquals(exception.getMessage(), "The first message in the history must be from the user.");
  }
}
