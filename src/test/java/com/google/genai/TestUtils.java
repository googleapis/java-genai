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

public final class TestUtils {
  static final String API_KEY = "api-key";
  static final String PROJECT = "project";
  static final String LOCATION = "location";

  /** A generateContent response body with explicit nulls for optional fields. */
  static final String RESPONSE_WITH_EXPLICIT_NULLS =
      "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":"
          + "\"I am a large language model, trained by Google.\","
          + "\"inlineData\":null,\"functionCall\":null,\"functionResponse\":null,"
          + "\"executableCode\":null,\"codeExecutionResult\":null}],\"role\":\"model\"},"
          + "\"finishReason\":\"STOP\","
          + "\"safetyRatings\":[{\"category\":\"HARM_CATEGORY_HATE_SPEECH\","
          + "\"probability\":\"NEGLIGIBLE\",\"blocked\":null}],"
          + "\"citationMetadata\":null,\"tokenCount\":null,\"groundingMetadata\":null,"
          + "\"index\":null}],"
          + "\"modelVersion\":\"gemini-2.0-flash-001\",\"promptFeedback\":null,"
          + "\"usageMetadata\":{\"promptTokenCount\":5,\"candidatesTokenCount\":11,"
          + "\"totalTokenCount\":16,\"cachedContentTokenCount\":null}}";

  private TestUtils() {}

  /** Creates a client given the vertexAI and replayId. Can be used in replay tests. */
  public static Client createClient(boolean vertexAI, String replayId) {
    String clientMode = System.getenv("GOOGLE_GENAI_CLIENT_MODE");
    DebugConfig debugConfig =
        new DebugConfig(
            clientMode == null ? "replay" : clientMode,
            "",
            System.getenv("GOOGLE_GENAI_REPLAYS_DIRECTORY"));

    Client.Builder clientBuilder = Client.builder().vertexAI(vertexAI).debugConfig(debugConfig);

    if (vertexAI) {
      clientBuilder.project(PROJECT).location(LOCATION);
    } else {
      clientBuilder.apiKey(API_KEY);
    }
    Client client = clientBuilder.build();

    if (client.clientMode().equals("replay")) {
      client.setReplayId(replayId);
    }
    return client;
  }
}
