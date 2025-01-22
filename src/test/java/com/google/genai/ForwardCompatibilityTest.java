package com.google.genai;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.genai.datatypes.GenerateContentConfig;
import com.google.genai.datatypes.GenerateContentResponse;
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

    GenerateContentConfig config = GenerateContentConfig.builder().build();
    GenerateContentResponse response =
        client.models.generateContent("gemini-2.0-flash-exp", "What is your name?", config);

    assertNotNull(response);
  }
}
