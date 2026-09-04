/*
 * Copyright 2026 Google LLC
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.genai.types.AudioTranscriptionConfig;
import com.google.genai.types.Content;
import com.google.genai.types.FunctionCall;
import com.google.genai.types.FunctionDeclaration;
import com.google.genai.types.FunctionResponse;
import com.google.genai.types.LiveConnectConfig;
import com.google.genai.types.LiveSendClientContentParameters;
import com.google.genai.types.LiveSendToolResponseParameters;
import com.google.genai.types.LiveServerMessage;
import com.google.genai.types.Modality;
import com.google.genai.types.Part;
import com.google.genai.types.Schema;
import com.google.genai.types.Tool;
import com.google.genai.types.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

/**
 * API-mode integration tests for the live (bidirectional WebSocket) module. The live module opens
 * a WebSocket, so it cannot be replayed and only runs against the real backend. See
 * go/genai-sdk:integration-testing.
 *
 * <p>Gated on {@code GOOGLE_GENAI_CLIENT_MODE=api}, which only the nightly sets. That gate is
 * required: presubmit picks up any {@code *Test} class automatically, with no API key.
 */
@EnabledIfEnvironmentVariable(named = "GOOGLE_GENAI_CLIENT_MODE", matches = "api")
public class LiveApiTest {

  /**
   * The only live model family currently served on the Gemini API. It is audio-native and rejects
   * a TEXT response modality, so these tests request AUDIO and enable output transcription.
   */
  private static final String LIVE_MODEL = "gemini-3.1-flash-live-preview";

  /**
   * Bounds a single model turn. AsyncSession never propagates post-setup socket failures, so an
   * unbounded {@code join()} on a broken session would hang rather than fail.
   */
  private static final long TURN_TIMEOUT_SECONDS = 90;

  private static final long CALL_TIMEOUT_SECONDS = 30;

  /** Everything a single model turn produced. */
  private static final class Turn {
    int audioBytes;
    final StringBuilder transcript = new StringBuilder();
    final List<FunctionCall> toolCalls = new ArrayList<>();
  }

  /**
   * Accumulates server messages into discrete turns. {@code receive()} registers one callback for
   * the life of the session, so turns are published to a queue the test drains one at a time.
   */
  private static final class TurnCollector {
    private final BlockingQueue<Turn> completed = new LinkedBlockingQueue<>();
    private Turn current = new Turn();

    void onMessage(LiveServerMessage message) {
      message
          .toolCall()
          .flatMap(toolCall -> toolCall.functionCalls())
          .ifPresent(
              calls -> {
                current.toolCalls.addAll(calls);
                flush();
              });

      message
          .serverContent()
          .ifPresent(
              content -> {
                content
                    .outputTranscription()
                    .flatMap(transcription -> transcription.text())
                    .ifPresent(current.transcript::append);
                content
                    .modelTurn()
                    .ifPresent(
                        modelTurn ->
                            modelTurn.parts().orElse(Collections.emptyList()).stream()
                                .forEach(
                                    part ->
                                        part.inlineData()
                                            .flatMap(blob -> blob.data())
                                            .ifPresent(data -> current.audioBytes += data.length)));
                if (content.turnComplete().orElse(false)) {
                  flush();
                }
              });
    }

    private void flush() {
      completed.add(current);
      current = new Turn();
    }

    Turn nextTurn() throws InterruptedException {
      Turn turn = completed.poll(TURN_TIMEOUT_SECONDS, TimeUnit.SECONDS);
      assertNotNull(
          turn, "Timed out after " + TURN_TIMEOUT_SECONDS + "s waiting for the model turn");
      return turn;
    }
  }

  /**
   * Skips the current test when the running job has selected the other backend, via
   * GOOGLE_GENAI_RUN_{VERTEX,GEMINI}_ONLY_IN_API_MODE. Required, not cosmetic: both nightly
   * wrappers share this runner, but only the Gemini job loads an API key.
   */
  private static void skipIfBackendDisabled(boolean isVertex) {
    boolean vertexOnly =
        !isNullOrEmpty(System.getenv("GOOGLE_GENAI_RUN_VERTEX_ONLY_IN_API_MODE"));
    boolean geminiOnly =
        !isNullOrEmpty(System.getenv("GOOGLE_GENAI_RUN_GEMINI_ONLY_IN_API_MODE"));

    if (isVertex && geminiOnly) {
      Assumptions.abort("Skipping Vertex AI live tests (GEMINI ONLY config enabled).");
    }
    if (!isVertex && vertexOnly) {
      Assumptions.abort("Skipping Gemini API live tests (VERTEX ONLY config enabled).");
    }
  }

  private static boolean isNullOrEmpty(String value) {
    return value == null || value.isEmpty();
  }

  private static LiveConnectConfig.Builder baseConfig() {
    return LiveConnectConfig.builder()
        .responseModalities(Modality.Known.AUDIO)
        .outputAudioTranscription(AudioTranscriptionConfig.builder());
  }

  /**
   * Clients opened by the running test, closed by {@link #closeClients()}.
   *
   * <p>Client owns an OkHttp dispatcher and connection pool, so a client that is never closed
   * leaves non-daemon threads behind. Closing the session is not enough.
   */
  private final List<Client> clients = new ArrayList<>();

  @AfterEach
  public void closeClients() {
    for (Client client : clients) {
      client.close();
    }
    clients.clear();
  }

  private Client newClient() {
    Client client = Client.builder().apiKey(System.getenv("GOOGLE_API_KEY")).build();
    clients.add(client);
    return client;
  }

  /** Connects and registers the collector before any content is sent. */
  private AsyncSession connect(TurnCollector collector, LiveConnectConfig config) throws Exception {
    AsyncSession session =
        newClient().async.live.connect(LIVE_MODEL, config).get(CALL_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    session.receive(collector::onMessage).get(CALL_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    return session;
  }

  private static void say(AsyncSession session, String text) throws Exception {
    session
        .sendClientContent(
            LiveSendClientContentParameters.builder()
                .turnComplete(true)
                .turns(Content.fromParts(Part.fromText(text)))
                .build())
        .get(CALL_TIMEOUT_SECONDS, TimeUnit.SECONDS);
  }

  @Test
  public void textInput_producesAudioAndTranscription() throws Exception {
    skipIfBackendDisabled(/* isVertex= */ false);
    TurnCollector collector = new TurnCollector();
    AsyncSession session = connect(collector, baseConfig().build());
    try {
      say(session, "Say hello.");
      Turn turn = collector.nextTurn();

      assertTrue(turn.audioBytes > 0, "Expected audio output from the model");
      assertFalse(
          turn.transcript.toString().trim().isEmpty(), "Expected an output transcription");
    } finally {
      session.close().get(CALL_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }
  }

  @Test
  public void multiTurn_retainsContext() throws Exception {
    skipIfBackendDisabled(/* isVertex= */ false);
    TurnCollector collector = new TurnCollector();
    AsyncSession session = connect(collector, baseConfig().build());
    try {
      say(session, "Remember the number 42. Just acknowledge it.");
      Turn first = collector.nextTurn();
      assertFalse(
          first.transcript.toString().trim().isEmpty(), "Expected a response to the first turn");

      say(session, "What number did I ask you to remember?");
      Turn second = collector.nextTurn();

      assertTrue(second.audioBytes > 0, "Expected audio output on the second turn");
      assertTrue(
          second.transcript.toString().contains("42"),
          "Expected the second turn to recall context from the first, transcript was \""
              + second.transcript
              + "\"");
    } finally {
      session.close().get(CALL_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }
  }

  @Test
  public void functionCalling_completesRoundTrip() throws Exception {
    skipIfBackendDisabled(/* isVertex= */ false);
    Tool tool =
        Tool.builder()
            .functionDeclarations(
                FunctionDeclaration.builder()
                    .name("turn_on_the_lights")
                    .description("Turns the lights on in the room.")
                    .parameters(Schema.builder().type(Type.Known.OBJECT))
                    .build())
            .build();

    TurnCollector collector = new TurnCollector();
    AsyncSession session = connect(collector, baseConfig().tools(tool).build());
    try {
      say(session, "Please turn on the lights.");
      Turn turn = collector.nextTurn();

      assertFalse(turn.toolCalls.isEmpty(), "Expected the model to request the tool");
      FunctionCall call = turn.toolCalls.get(0);
      assertTrue(
          call.name().isPresent() && call.name().get().equals("turn_on_the_lights"),
          "Unexpected tool call name: " + call.name());

      session
          .sendToolResponse(
              LiveSendToolResponseParameters.builder()
                  .functionResponses(
                      FunctionResponse.builder()
                          .id(call.id().orElse(""))
                          .name(call.name().orElse(""))
                          .response(Collections.singletonMap("result", "ok"))
                          .build())
                  .build())
          .get(CALL_TIMEOUT_SECONDS, TimeUnit.SECONDS);

      Turn followUp = collector.nextTurn();
      assertFalse(
          followUp.transcript.toString().trim().isEmpty(),
          "Expected the model to respond after the tool result");
    } finally {
      session.close().get(CALL_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }
  }
}
