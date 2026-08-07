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
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.genai.errors.GenAiIOException;
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

  /** A helper class with a Duration field, exercising CustomDurationDeserializer. */
  static class DurationPayload extends JsonSerializable {
    @JsonProperty("timeout")
    public Duration timeout;
  }

  @Test
  void fromJsonString_duration_parsesWholeSeconds() {
    DurationPayload payload =
        DurationPayload.fromJsonString("{\"timeout\":\"7s\"}", DurationPayload.class);

    assertEquals(Duration.ofSeconds(7), payload.timeout);
  }

  @Test
  void fromJsonString_duration_parsesFractionalSeconds() {
    // proto3 JSON encodes google.protobuf.Duration with fractional seconds
    // (e.g. "7.280s", up to 9 fractional digits); the Live API streams these.
    DurationPayload payload =
        DurationPayload.fromJsonString("{\"timeout\":\"7.280s\"}", DurationPayload.class);

    assertEquals(Duration.ofSeconds(7, 280_000_000L), payload.timeout);
  }

  @Test
  void fromJsonString_duration_keepsNanosecondPrecision() {
    DurationPayload payload =
        DurationPayload.fromJsonString("{\"timeout\":\"0.000000001s\"}", DurationPayload.class);

    assertEquals(Duration.ofNanos(1), payload.timeout);
  }

  @Test
  void fromJsonString_duration_parsesNegativeFractionalSeconds() {
    DurationPayload payload =
        DurationPayload.fromJsonString("{\"timeout\":\"-1.5s\"}", DurationPayload.class);

    assertEquals(Duration.ofMillis(-1500), payload.timeout);
  }

  @Test
  void fromJsonString_duration_rejectsNonNumericValue() {
    assertThrows(
        GenAiIOException.class,
        () -> {
          DurationPayload.fromJsonString("{\"timeout\":\"abcs\"}", DurationPayload.class);
        });
  }
}
