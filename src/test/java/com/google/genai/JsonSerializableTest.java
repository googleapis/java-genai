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
import com.google.genai.types.Candidate;
import com.google.genai.types.FunctionCall;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class JsonSerializableTest {

  // Jackson's default limit is 20,000,000
  private static final int DEFAULT_LIMIT = 20_000_000;
  // A new limit for testing
  private static final int NEW_LIMIT = 30_000_000;

  /** A response body with explicit nulls, as re-serialized by some proxies. */
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

  @Test
  void fromJsonString_explicitNullForOptionalField_returnsEmpty() {
    // Arrange
    String json = "{\"text\":\"hello\",\"inlineData\":null,\"functionCall\":null,\"thought\":null}";

    // Act
    Part part = Part.fromJson(json);

    // Assert
    assertEquals(Optional.of("hello"), part.text());
    assertEquals(Optional.empty(), part.inlineData());
    assertEquals(Optional.empty(), part.functionCall());
    assertEquals(Optional.empty(), part.thought());
  }

  @Test
  void fromJsonString_responseWithExplicitNulls_deserializes() {
    // Arrange
    String json = RESPONSE_WITH_EXPLICIT_NULLS;

    // Act
    GenerateContentResponse response = GenerateContentResponse.fromJson(json);

    // Assert
    assertEquals("I am a large language model, trained by Google.", response.text());
    assertEquals(Optional.of("gemini-2.0-flash-001"), response.modelVersion());
    Candidate candidate = response.candidates().get().get(0);
    assertEquals(Optional.empty(), candidate.index());
    assertEquals(Optional.empty(), candidate.safetyRatings().get().get(0).blocked());
    assertEquals(Optional.empty(), response.usageMetadata().get().cachedContentTokenCount());
  }

  @Test
  void fromJsonString_explicitNullForListEnumAndMessageFields_returnsEmpty() {
    // Arrange
    String json = "{\"finishReason\":null,\"safetyRatings\":null,\"content\":null}";

    // Act
    Candidate candidate = Candidate.fromJson(json);

    // Assert
    assertEquals(Optional.empty(), candidate.finishReason());
    assertEquals(Optional.empty(), candidate.safetyRatings());
    assertEquals(Optional.empty(), candidate.content());
  }

  /** A null inside a map field is a value, so it must be kept rather than skipped. */
  @Test
  void fromJsonString_nullInsideMapField_isKept() {
    // Arrange
    String json = "{\"name\":\"f\",\"args\":{\"a\":null,\"b\":1},\"id\":null}";

    // Act
    FunctionCall functionCall = FunctionCall.fromJson(json);

    // Assert
    Map<String, Object> args = functionCall.args().get();
    assertTrue(args.containsKey("a"));
    assertNull(args.get("a"));
    assertEquals(1, args.get("b"));
    assertEquals(Optional.empty(), functionCall.id());
  }
}
