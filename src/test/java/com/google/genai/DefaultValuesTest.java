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

    GenerateContentResponse response =
        client.models.generateContent("gemini-2.0-flash-exp", "What is your name?", null);

    ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
    verify(httpClientSpy).post(argumentCaptor.capture(), argumentCaptor.capture());
    GenerateContentConfig spiedConfig =
        GenerateContentConfig.fromJson(argumentCaptor.getAllValues().get(1));

    // Assert that the temperature and candidateCount fields are not set when not passed via config.
    assertEquals(spiedConfig.audioTimestamp(), Optional.empty());
    assertEquals(spiedConfig.cachedContent(), Optional.empty());
    assertEquals(spiedConfig.candidateCount(), Optional.empty());
    assertEquals(spiedConfig.frequencyPenalty(), Optional.empty());
    assertEquals(spiedConfig.logprobs(), Optional.empty());
    assertEquals(spiedConfig.maxOutputTokens(), Optional.empty());
    assertEquals(spiedConfig.mediaResolution(), Optional.empty());
    assertEquals(spiedConfig.presencePenalty(), Optional.empty());
    assertEquals(spiedConfig.responseLogprobs(), Optional.empty());
    assertEquals(spiedConfig.responseModalities(), Optional.empty());
    assertEquals(spiedConfig.responseMimeType(), Optional.empty());
    assertEquals(spiedConfig.responseSchema(), Optional.empty());
    assertEquals(spiedConfig.routingConfig(), Optional.empty());
    assertEquals(spiedConfig.safetySettings(), Optional.empty());
    assertEquals(spiedConfig.seed(), Optional.empty());
    assertEquals(spiedConfig.stopSequences(), Optional.empty());
    assertEquals(spiedConfig.speechConfig(), Optional.empty());
    assertEquals(spiedConfig.temperature(), Optional.empty());
    assertEquals(spiedConfig.thinkingConfig(), Optional.empty());
    assertEquals(spiedConfig.tools(), Optional.empty());
    assertEquals(spiedConfig.toolConfig(), Optional.empty());
    assertEquals(spiedConfig.topK(), Optional.empty());
    assertEquals(spiedConfig.topP(), Optional.empty());
  }
}
