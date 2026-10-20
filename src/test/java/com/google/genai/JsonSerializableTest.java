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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.LiveServerMessage;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class JsonSerializableTest {

  // Jackson's default limit is 20,000,000
  private static final int DEFAULT_LIMIT = 20_000_000;
  // A new limit for testing
  private static final int NEW_LIMIT = 30_000_000;

  /** A simple helper class that extends JsonSerializable for testing deserialization. */
  static class TestPayload extends JsonSerializable {
    @JsonProperty("largeField")
    public String largeField;
  }

  @AfterEach
  void tearDown() {
    // Reset the static mapper to its default state
    JsonSerializable.setMaxReadLength(DEFAULT_LIMIT);
  }

  /** Helper method to create a large JSON string. */
  private String createLargeJson(int jsonSize) {
    StringBuilder sb = new StringBuilder();
    sb.append("{\"largeField\":\"");
    for (int i = 0; i < jsonSize; i++) {
      sb.append('a');
    }
    sb.append("\"}");
    return sb.toString();
  }

  @Test
  void fromJsonString_withDefaultLimit_failsOnLargeString() {
    // Arrange
    String json = createLargeJson(DEFAULT_LIMIT + 100); // ~20.0001 MB

    // Act
    // assertThrows returns the exception, which we can store and inspect
    GenAiIOException exception =
        assertThrows(
            GenAiIOException.class,
            () -> {
              TestPayload.fromJsonString(json, TestPayload.class);
            });

    // Assert
    assertTrue(exception.getCause() instanceof JsonProcessingException);
    assertTrue(exception.getCause().getMessage().contains("String value length"));
    assertTrue(exception.getCause().getMessage().contains("exceeds the maximum allowed (20000000"));
  }

  @Test
  void fromJsonString_withCustomLimit_succeedsOnLargeString() {
    // Arrange
    JsonSerializable.setMaxReadLength(NEW_LIMIT); // 30MB
    String json = createLargeJson(DEFAULT_LIMIT + 100); // ~20.0001 MB

    // Act
    TestPayload payload = TestPayload.fromJsonString(json, TestPayload.class);

    // Assert
    assertNotNull(payload);
    assertEquals(DEFAULT_LIMIT + 100, payload.largeField.length());
  }

  @Test
  void fromJsonString_withCustomLimit_failsOnEvenLargerString() {
    // Arrange
    JsonSerializable.setMaxReadLength(NEW_LIMIT); // 30MB
    String json = createLargeJson(NEW_LIMIT + 100); // ~30.0001 MB

    // Act
    GenAiIOException exception =
        assertThrows(
            GenAiIOException.class,
            () -> {
              TestPayload.fromJsonString(json, TestPayload.class);
            });

    // Assert
    assertTrue(exception.getCause() instanceof JsonProcessingException);
    assertTrue(exception.getCause().getMessage().contains("String value length"));
    assertTrue(exception.getCause().getMessage().contains("exceeds the maximum allowed (30000000"));
  }

  @Test
  void setMaxReadLength_withInvalidInput_throwsException() {
    // Act & Assert for 0
    IllegalArgumentException ex1 =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              JsonSerializable.setMaxReadLength(0);
            });
    assertEquals("Invalid JSON max read length: 0", ex1.getMessage());

    // Act & Assert for -100
    IllegalArgumentException ex2 =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              JsonSerializable.setMaxReadLength(-100);
            });
    assertEquals("Invalid JSON max read length: -100", ex2.getMessage());
  }

  /** Deserializes a Live voice activity offset, the field that carries the reported values. */
  private static Duration parseAudioOffset(String value) {
    LiveServerMessage message =
        LiveServerMessage.fromJson("{\"voiceActivity\":{\"audioOffset\":\"" + value + "\"}}");
    return message.voiceActivity().get().audioOffset().get();
  }

  /** The Live API sends fractional seconds, so the sub-second part must survive the parse. */
  @Test
  void fromJsonString_durationWithFractionalSeconds_keepsSubSecondPrecision() {
    assertEquals(Duration.ofSeconds(7, 280_000_000), parseAudioOffset("7.280s"));
    assertEquals(Duration.ofSeconds(0, 360_000_000), parseAudioOffset("0.360s"));
    assertEquals(Duration.ofSeconds(1, 600_000_000), parseAudioOffset("1.600s"));
    assertEquals(Duration.ofSeconds(1, 1), parseAudioOffset("1.000000001s"));
  }

  @Test
  void fromJsonString_durationWithWholeSeconds_parsesSeconds() {
    assertEquals(Duration.ofSeconds(86400), parseAudioOffset("86400s"));
    assertEquals(Duration.ofSeconds(-3), parseAudioOffset("-3s"));
  }

  @Test
  void fromJsonString_negativeFractionalDuration_parsesNegativeDuration() {
    assertEquals(Duration.ofMillis(-1500), parseAudioOffset("-1.500s"));
    assertEquals(Duration.ofMillis(-500), parseAudioOffset("-0.500s"));
  }

  @Test
  void fromJsonString_goAwayTimeLeftWithFractionalSeconds_keepsSubSecondPrecision() {
    LiveServerMessage message =
        LiveServerMessage.fromJson("{\"goAway\":{\"timeLeft\":\"7.280s\"}}");

    assertEquals(Duration.ofSeconds(7, 280_000_000), message.goAway().get().timeLeft().get());
  }

  @Test
  void fromJsonString_malformedDuration_throwsException() {
    String[] values = {"7.2.8s", "7.s", "abcs", "1.0000000001s", "1.-5s"};
    for (String value : values) {
      GenAiIOException exception =
          assertThrows(GenAiIOException.class, () -> parseAudioOffset(value));
      assertTrue(
          exception.getCause().getMessage().contains("Cannot parse duration from string: " + value),
          "Unexpected message for " + value + ": " + exception.getCause().getMessage());
    }
  }

  @Test
  void fromJsonString_durationWithoutSecondsSuffix_throwsException() {
    GenAiIOException exception = assertThrows(GenAiIOException.class, () -> parseAudioOffset("5"));

    assertTrue(
        exception.getCause().getMessage().contains("Expected duration in format 'Xs', but got: 5"));
  }

  @Test
  void readValue_nullOrEmptyDuration_returnsNull() throws Exception {
    assertNull(JsonSerializable.objectMapper().readValue("null", Duration.class));
    assertNull(JsonSerializable.objectMapper().readValue("\"\"", Duration.class));
  }
}
