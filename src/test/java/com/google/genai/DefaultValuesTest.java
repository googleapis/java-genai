package com.google.genai;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import java.lang.reflect.Field;
import java.util.Optional;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class DefaultValuesTest {
  @Test
  public void testDefaultValues() throws Exception {
    // Mocks and test setup.
    ApiClient httpClientSpy = Mockito.spy(Mockito.mock(ApiClient.class));
    ApiResponse mockedResponse = Mockito.mock(ApiResponse.class);
    when(httpClientSpy.post(anyString(), anyString())).thenReturn(mockedResponse);
    HttpEntity mockedEntity = Mockito.mock(HttpEntity.class);
    GenerateContentResponse returnResponse = GenerateContentResponse.builder().build();
    StringEntity content = new StringEntity(returnResponse.toJson());
    when(mockedResponse.getEntity()).thenReturn(content);

    Client client = Client.builder().build();
    // Make the apiClient field public so that it can be spied on in the tests. This is a
    // workaround for the fact that the ApiClient is a final class and cannot be spied on directly.
    Field apiClientField = Models.class.getDeclaredField("apiClient");
    apiClientField.setAccessible(true);
    apiClientField.set(client.models, httpClientSpy);

    GenerateContentConfig config = GenerateContentConfig.builder().build();
    GenerateContentResponse response =
        client.models.generateContent("gemini-2.0-flash-exp", "What is your name?", config);

    ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
    verify(httpClientSpy).post(argumentCaptor.capture(), argumentCaptor.capture());
    GenerateContentConfig spiedConfig =
        GenerateContentConfig.fromJson(argumentCaptor.getAllValues().get(1));

    // Assert that the temperature and candidateCount fields are not set when not passed via config.
    assertEquals(spiedConfig.getAudioTimestamp(), Optional.empty());
    assertEquals(spiedConfig.getCachedContent(), Optional.empty());
    assertEquals(spiedConfig.getCandidateCount(), Optional.empty());
    assertEquals(spiedConfig.getFrequencyPenalty(), Optional.empty());
    assertEquals(spiedConfig.getLogprobs(), Optional.empty());
    assertEquals(spiedConfig.getMaxOutputTokens(), Optional.empty());
    assertEquals(spiedConfig.getMediaResolution(), Optional.empty());
    assertEquals(spiedConfig.getPresencePenalty(), Optional.empty());
    assertEquals(spiedConfig.getResponseLogprobs(), Optional.empty());
    assertEquals(spiedConfig.getResponseModalities(), Optional.empty());
    assertEquals(spiedConfig.getResponseMimeType(), Optional.empty());
    assertEquals(spiedConfig.getResponseSchema(), Optional.empty());
    assertEquals(spiedConfig.getRoutingConfig(), Optional.empty());
    assertEquals(spiedConfig.getSafetySettings(), Optional.empty());
    assertEquals(spiedConfig.getSeed(), Optional.empty());
    assertEquals(spiedConfig.getStopSequences(), Optional.empty());
    assertEquals(spiedConfig.getSpeechConfig(), Optional.empty());
    assertEquals(spiedConfig.getTemperature(), Optional.empty());
    assertEquals(spiedConfig.getThinkingConfig(), Optional.empty());
    assertEquals(spiedConfig.getTools(), Optional.empty());
    assertEquals(spiedConfig.getToolConfig(), Optional.empty());
    assertEquals(spiedConfig.getTopK(), Optional.empty());
    assertEquals(spiedConfig.getTopP(), Optional.empty());
  }
}
