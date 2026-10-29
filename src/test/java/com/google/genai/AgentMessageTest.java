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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.genai.gaos.models.operations.GetInteractionByIdRequest;
import com.google.genai.types.HttpOptions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import mockwebserver3.MockResponse;
import mockwebserver3.MockWebServer;
import okhttp3.Headers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Tests for Agent-Message response header parsing and warning emission. */
public final class AgentMessageTest {

  // Synchronized: async GAOS responses are handled on OkHttp dispatcher threads.
  private final List<String> recordedWarnings = Collections.synchronizedList(new ArrayList<>());
  private Handler testHandler;
  private static final String WARNING_PREFIX = "[Gemini API] ";

  private Logger httpResponseLogger;

  @BeforeEach
  public void setUp() {
    HttpApiResponse.clearSeenAgentMessages();
    recordedWarnings.clear();

    testHandler =
        new Handler() {
          @Override
          public void publish(LogRecord record) {
            if (record.getLevel().intValue() >= Level.WARNING.intValue()) {
              // Strip the expected prefix. Exceptions thrown here are swallowed by the SDK,
              // so a missing prefix is recorded in a form that fails the comparison.
              String message = record.getMessage();
              recordedWarnings.add(
                  message.startsWith(WARNING_PREFIX)
                      ? message.substring(WARNING_PREFIX.length())
                      : "missing prefix: " + message);
            }
          }

          @Override
          public void flush() {}

          @Override
          public void close() {}
        };

    httpResponseLogger = Logger.getLogger(HttpApiResponse.class.getName());
    httpResponseLogger.addHandler(testHandler);
  }

  @AfterEach
  public void tearDown() {
    if (httpResponseLogger != null && testHandler != null) {
      httpResponseLogger.removeHandler(testHandler);
    }
    HttpApiResponse.clearSeenAgentMessages();
  }

  @Test
  public void testParseSingleStructuredItem() {
    String header =
        "field_ignored;field=\"temperature\";"
            + "msg=%\"temperature is ignored by Gemini 3.6+ models and will be removed"
            + " in a future release.\"";
    assertEquals(
        Collections.singletonList(
            "temperature is ignored by Gemini 3.6+ models and will be removed in a future"
                + " release."),
        HttpApiResponse.parseAgentMessageHeader(header));
  }

  @Test
  public void testParseMultipleCommaSeparatedItems() {
    String header =
        "field_ignored;field=\"temperature\";"
            + "msg=%\"temperature is ignored, using default.\", "
            + "field_ignored;field=\"top_k\";"
            + "msg=%\"top_k is ignored; see docs.\"";
    assertEquals(
        Arrays.asList("temperature is ignored, using default.", "top_k is ignored; see docs."),
        HttpApiResponse.parseAgentMessageHeader(header));
  }

  @Test
  public void testParseItemWithoutFieldAndUnknownParams() {
    String header =
        "legacy_usage;severity=\"warn\";experimental;"
            + "msg=%\"generateContent is a legacy endpoint. Use the Interactions API"
            + " for new development.\"";
    assertEquals(
        Collections.singletonList(
            "generateContent is a legacy endpoint. Use the Interactions API for new development."),
        HttpApiResponse.parseAgentMessageHeader(header));
  }

  @Test
  public void testParsePercentEncodedUtf8AndQuotedString() {
    String header =
        "field_ignored;msg=%\"use %22max_output_tokens%22 %E2%80%94 100%25\", "
            + "other_code;msg=\"plain \\\"quoted\\\" \\\\ string\"";
    assertEquals(
        Arrays.asList("use \"max_output_tokens\" \u2014 100%", "plain \"quoted\" \\ string"),
        HttpApiResponse.parseAgentMessageHeader(header));
  }

  @Test
  public void testParseMalformedHeadersGracefullyIgnored() {
    assertTrue(HttpApiResponse.parseAgentMessageHeader(null).isEmpty());
    assertTrue(HttpApiResponse.parseAgentMessageHeader("").isEmpty());
    assertTrue(HttpApiResponse.parseAgentMessageHeader("   ").isEmpty());
    assertTrue(HttpApiResponse.parseAgentMessageHeader(";;;").isEmpty());
    assertTrue(HttpApiResponse.parseAgentMessageHeader("field_ignored").isEmpty());
    assertTrue(
        HttpApiResponse.parseAgentMessageHeader("field_ignored;field=\"temperature\"").isEmpty());
    assertTrue(HttpApiResponse.parseAgentMessageHeader("field_ignored;msg=unquoted").isEmpty());
    assertTrue(
        HttpApiResponse.parseAgentMessageHeader("field_ignored;msg=%\"unterminated").isEmpty());
    assertTrue(HttpApiResponse.parseAgentMessageHeader("bad code;msg=%\"not a token\"").isEmpty());
  }

  @Test
  public void testParseSkipsMalformedSiblingItem() {
    String header =
        "field_ignored;field=\"temperature\", "
            + "field_ignored;field=\"top_k\";msg=%\"top_k is ignored.\"";
    assertEquals(
        Collections.singletonList("top_k is ignored."),
        HttpApiResponse.parseAgentMessageHeader(header));
  }

  @Test
  public void testParsePlainStringFallback() {
    String header = "generation_config.temperature is ignored, using default value.";
    assertEquals(
        Collections.singletonList("generation_config.temperature is ignored, using default value."),
        HttpApiResponse.parseAgentMessageHeader(header));
  }

  @Test
  public void testInteractionsSyncAsyncAndErrorResponsesEmitWarnings() throws Exception {
    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    try (MockWebServer server = new MockWebServer()) {
      server.enqueue(agentMessageResponse(200, "{\"id\": \"i1\", \"status\": \"completed\"}", "gaos sync warning"));
      server.enqueue(agentMessageResponse(200, "{}", "gaos async warning"));
      server.enqueue(
          agentMessageResponse(
              404, "{\"error\": {\"code\": 404, \"message\": \"nope\"}}", "gaos error warning"));
      server.start(0);
      String baseUrl = server.url("/").toString();
      Client client =
          Client.builder()
              .apiKey("test-api-key")
              .vertexAI(false)
              .httpOptions(
                  HttpOptions.builder()
                      .baseUrl(baseUrl.substring(0, baseUrl.length() - 1))
                      .build())
              .asyncRetryScheduler(scheduler)
              .build();

      assertEquals(
          200,
          client.interactions.get(GetInteractionByIdRequest.builder().id("i1").build()).statusCode());
      client.async.interactions.delete("i2").get(5, TimeUnit.SECONDS);
      assertThrows(
          RuntimeException.class,
          () -> client.interactions.get(GetInteractionByIdRequest.builder().id("i3").build()));

      assertEquals(
          Arrays.asList("gaos sync warning", "gaos async warning", "gaos error warning"),
          recordedWarnings);
    } finally {
      scheduler.shutdown();
    }
  }

  private static MockResponse agentMessageResponse(int code, String body, String message) {
    return new MockResponse(
        code,
        Headers.of(
            "Content-Type", "application/json",
            "Agent-Message", "field_ignored;msg=%\"" + message + "\""),
        body);
  }
}
