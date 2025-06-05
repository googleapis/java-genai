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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.genai.types.Blob;
import com.google.genai.types.LiveClientMessage;
import com.google.genai.types.LiveConnectConfig;
import com.google.genai.types.LiveSendRealtimeInputParameters;
import com.google.genai.types.MultiSpeakerVoiceConfig;
import com.google.genai.types.PrebuiltVoiceConfig;
import com.google.genai.types.SpeakerVoiceConfig;
import com.google.genai.types.SpeechConfig;
import com.google.genai.types.VoiceConfig;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class LiveConvertersTest {

  ApiClient GEMINI_API_CLIENT = mock(ApiClient.class);
  ApiClient VERTEX_AI_CLIENT = mock(ApiClient.class);

  @Test
  public void testLiveSendRealtimeInputParameters_Media_wrapped() {

    final LiveClientMessage message =
        LiveClientMessage.builder()
            .realtimeInputParameters(
                LiveSendRealtimeInputParameters.builder()
                    .media(
                        Blob.builder()
                            .mimeType("audio/mp3")
                            .data(new byte[] {72, 101, 108, 108, 111, 33})))
            .build();

    final LiveConverters mldevLiveConverters = new LiveConverters(GEMINI_API_CLIENT);
    ObjectNode transformed =
        mldevLiveConverters.liveClientMessageToMldev(
            GEMINI_API_CLIENT,
            JsonSerializable.toJsonNode(message),
            JsonSerializable.objectMapper.createObjectNode());

    assertEquals(
        transformed.get("realtime_input").get("mediaChunks").get(0).get("mimeType").asText(),
        "audio/mp3");

    final LiveConverters vertexLiveConverters = new LiveConverters(GEMINI_API_CLIENT);

    transformed =
        vertexLiveConverters.liveClientMessageToMldev(
            GEMINI_API_CLIENT,
            JsonSerializable.toJsonNode(message),
            JsonSerializable.objectMapper.createObjectNode());

    assertEquals(
        transformed.get("realtime_input").get("mediaChunks").get(0).get("mimeType").asText(),
        "audio/mp3");
  }

  @Test
  public void testLiveSendRealtimeInputParameters_Text_okay() {

    final LiveClientMessage message =
        LiveClientMessage.builder()
            .realtimeInputParameters(LiveSendRealtimeInputParameters.builder().text("test"))
            .build();

    final LiveConverters mldevLiveConverters = new LiveConverters(GEMINI_API_CLIENT);
    ObjectNode transformed =
        mldevLiveConverters.liveClientMessageToMldev(
            GEMINI_API_CLIENT,
            JsonSerializable.toJsonNode(message),
            JsonSerializable.objectMapper.createObjectNode());

    assertEquals(transformed.get("realtime_input").get("text").asText(), "test");

    final LiveConverters vertexLiveConverters = new LiveConverters(GEMINI_API_CLIENT);

    assertThrows(
        IllegalArgumentException.class, // Expected exception type
        () ->
            vertexLiveConverters.liveClientMessageToVertex(
                VERTEX_AI_CLIENT,
                JsonSerializable.toJsonNode(message),
                JsonSerializable.objectMapper
                    .createObjectNode()) // Code that should throw the exception
        );
  }

  @Test
  public void testLiveSendRealtimeInputParameters_Audio_okay() {

    final LiveClientMessage message =
        LiveClientMessage.builder()
            .realtimeInputParameters(
                LiveSendRealtimeInputParameters.builder()
                    .audio(
                        Blob.builder()
                            .mimeType("audio/mp3")
                            .data(new byte[] {72, 101, 108, 108, 111, 33})))
            .build();

    final LiveConverters mldevLiveConverters = new LiveConverters(GEMINI_API_CLIENT);
    ObjectNode transformed =
        mldevLiveConverters.liveClientMessageToMldev(
            GEMINI_API_CLIENT,
            JsonSerializable.toJsonNode(message),
            JsonSerializable.objectMapper.createObjectNode());

    assertEquals(
        transformed.get("realtime_input").get("audio").get("mimeType").asText(), "audio/mp3");

    final LiveConverters vertexLiveConverters = new LiveConverters(GEMINI_API_CLIENT);

    assertThrows(
        IllegalArgumentException.class, // Expected exception type
        () ->
            vertexLiveConverters.liveClientMessageToVertex(
                VERTEX_AI_CLIENT,
                JsonSerializable.toJsonNode(message),
                JsonSerializable.objectMapper
                    .createObjectNode()) // Code that should throw the exception
        );
  }

  @Test
  public void testLiveSendRealtimeInputParameters_BadBlob_throws() {

    final LiveClientMessage message =
        LiveClientMessage.builder()
            .realtimeInputParameters(
                LiveSendRealtimeInputParameters.builder()
                    .video(
                        Blob.builder()
                            .mimeType("audio/mp3")
                            .data(new byte[] {72, 101, 108, 108, 111, 33})))
            .build();

    final LiveConverters mldevLiveConverters = new LiveConverters(GEMINI_API_CLIENT);

    assertThrows(
        IllegalArgumentException.class, // Expected exception type
        () ->
            mldevLiveConverters.liveClientMessageToMldev(
                GEMINI_API_CLIENT,
                JsonSerializable.toJsonNode(message),
                JsonSerializable.objectMapper
                    .createObjectNode()) // Code that should throw the exception
        );
  }

  @Test
  public void testMultiSpeakerVoiceConfig_throws() {
    List<SpeakerVoiceConfig> speakerVoiceConfigs = new ArrayList<>();
    speakerVoiceConfigs.add(
        SpeakerVoiceConfig.builder()
            .speaker("Alice")
            .voiceConfig(
                VoiceConfig.builder()
                    .prebuiltVoiceConfig(PrebuiltVoiceConfig.builder().voiceName("kore")))
            .build());
    speakerVoiceConfigs.add(
        SpeakerVoiceConfig.builder()
            .speaker("Bob")
            .voiceConfig(
                VoiceConfig.builder()
                    .prebuiltVoiceConfig(PrebuiltVoiceConfig.builder().voiceName("puck")))
            .build());

    final LiveConnectConfig message =
        LiveConnectConfig.builder()
            .speechConfig(
                SpeechConfig.builder()
                    .multiSpeakerVoiceConfig(
                        MultiSpeakerVoiceConfig.builder().speakerVoiceConfigs(speakerVoiceConfigs)))
            .build();

    final LiveConverters mldevLiveConverters = new LiveConverters(GEMINI_API_CLIENT);

    assertThrows(
        IllegalArgumentException.class, // Expected exception type
        () ->
            mldevLiveConverters.liveConnectConfigToMldev(
                GEMINI_API_CLIENT,
                JsonSerializable.toJsonNode(message),
                JsonSerializable.objectMapper
                    .createObjectNode()) // Code that should throw the exception
        );
  }
}
