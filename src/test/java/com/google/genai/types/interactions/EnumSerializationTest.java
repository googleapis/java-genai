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

package com.google.genai.types.interactions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Tests that all Interactions API enums serialize to lowercase per the OpenAPI specification.
 *
 * <p>The Interactions API OpenAPI spec requires enum values to be lowercase:
 * <ul>
 *   <li>ToolChoiceType: "auto", "any", "none", "validated"
 *   <li>ThinkingLevel: "minimal", "low", "medium", "high"
 *   <li>ThinkingSummaries: "auto", "none"
 *   <li>MediaResolution: "low", "medium", "high"
 *   <li>ResponseModality: "text", "image", "audio"
 * </ul>
 */
public class EnumSerializationTest {

  @Nested
  @DisplayName("ToolChoiceType serialization")
  class ToolChoiceTypeSerializationTest {

    @Test
    public void testAuto_serializesToLowercase() {
      ToolChoiceType type = new ToolChoiceType(ToolChoiceType.Known.AUTO);
      assertEquals("auto", type.toString());
    }

    @Test
    public void testAny_serializesToLowercase() {
      ToolChoiceType type = new ToolChoiceType(ToolChoiceType.Known.ANY);
      assertEquals("any", type.toString());
    }

    @Test
    public void testNone_serializesToLowercase() {
      ToolChoiceType type = new ToolChoiceType(ToolChoiceType.Known.NONE);
      assertEquals("none", type.toString());
    }

    @Test
    public void testValidated_serializesToLowercase() {
      ToolChoiceType type = new ToolChoiceType(ToolChoiceType.Known.VALIDATED);
      assertEquals("validated", type.toString());
    }

    @Test
    public void testUnspecified_serializesToLowercase() {
      ToolChoiceType type = new ToolChoiceType(ToolChoiceType.Known.TOOL_CHOICE_TYPE_UNSPECIFIED);
      assertEquals("unspecified", type.toString());
    }

    @Test
    public void testFromString_caseInsensitive() {
      ToolChoiceType fromLower = new ToolChoiceType("auto");
      ToolChoiceType fromUpper = new ToolChoiceType("AUTO");
      ToolChoiceType fromMixed = new ToolChoiceType("Auto");

      assertEquals(ToolChoiceType.Known.AUTO, fromLower.knownEnum());
      assertEquals(ToolChoiceType.Known.AUTO, fromUpper.knownEnum());
      assertEquals(ToolChoiceType.Known.AUTO, fromMixed.knownEnum());
    }
  }

  @Nested
  @DisplayName("ThinkingLevel serialization")
  class ThinkingLevelSerializationTest {

    @Test
    public void testMinimal_serializesToLowercase() {
      ThinkingLevel level = new ThinkingLevel(ThinkingLevel.Known.MINIMAL);
      assertEquals("minimal", level.toString());
    }

    @Test
    public void testLow_serializesToLowercase() {
      ThinkingLevel level = new ThinkingLevel(ThinkingLevel.Known.LOW);
      assertEquals("low", level.toString());
    }

    @Test
    public void testMedium_serializesToLowercase() {
      ThinkingLevel level = new ThinkingLevel(ThinkingLevel.Known.MEDIUM);
      assertEquals("medium", level.toString());
    }

    @Test
    public void testHigh_serializesToLowercase() {
      ThinkingLevel level = new ThinkingLevel(ThinkingLevel.Known.HIGH);
      assertEquals("high", level.toString());
    }

    @Test
    public void testUnspecified_serializesToLowercase() {
      ThinkingLevel level = new ThinkingLevel(ThinkingLevel.Known.THINKING_LEVEL_UNSPECIFIED);
      assertEquals("unspecified", level.toString());
    }

    @Test
    public void testFromString_caseInsensitive() {
      ThinkingLevel fromLower = new ThinkingLevel("high");
      ThinkingLevel fromUpper = new ThinkingLevel("HIGH");
      ThinkingLevel fromMixed = new ThinkingLevel("High");

      assertEquals(ThinkingLevel.Known.HIGH, fromLower.knownEnum());
      assertEquals(ThinkingLevel.Known.HIGH, fromUpper.knownEnum());
      assertEquals(ThinkingLevel.Known.HIGH, fromMixed.knownEnum());
    }
  }

  @Nested
  @DisplayName("ThinkingSummaries serialization")
  class ThinkingSummariesSerializationTest {

    @Test
    public void testAuto_serializesToLowercase() {
      ThinkingSummaries summaries = new ThinkingSummaries(ThinkingSummaries.Known.AUTO);
      assertEquals("auto", summaries.toString());
    }

    @Test
    public void testNone_serializesToLowercase() {
      ThinkingSummaries summaries = new ThinkingSummaries(ThinkingSummaries.Known.NONE);
      assertEquals("none", summaries.toString());
    }

    @Test
    public void testUnspecified_serializesToLowercase() {
      ThinkingSummaries summaries =
          new ThinkingSummaries(ThinkingSummaries.Known.THINKING_SUMMARIES_UNSPECIFIED);
      assertEquals("unspecified", summaries.toString());
    }

    @Test
    public void testFromString_caseInsensitive() {
      ThinkingSummaries fromLower = new ThinkingSummaries("auto");
      ThinkingSummaries fromUpper = new ThinkingSummaries("AUTO");
      ThinkingSummaries fromMixed = new ThinkingSummaries("Auto");

      assertEquals(ThinkingSummaries.Known.AUTO, fromLower.knownEnum());
      assertEquals(ThinkingSummaries.Known.AUTO, fromUpper.knownEnum());
      assertEquals(ThinkingSummaries.Known.AUTO, fromMixed.knownEnum());
    }
  }

  @Nested
  @DisplayName("MediaResolution serialization")
  class MediaResolutionSerializationTest {

    @Test
    public void testLow_serializesToLowercase() {
      MediaResolution resolution = new MediaResolution(MediaResolution.Known.LOW);
      assertEquals("low", resolution.toString());
    }

    @Test
    public void testMedium_serializesToLowercase() {
      MediaResolution resolution = new MediaResolution(MediaResolution.Known.MEDIUM);
      assertEquals("medium", resolution.toString());
    }

    @Test
    public void testHigh_serializesToLowercase() {
      MediaResolution resolution = new MediaResolution(MediaResolution.Known.HIGH);
      assertEquals("high", resolution.toString());
    }

    @Test
    public void testFromString_caseInsensitive() {
      MediaResolution fromLower = new MediaResolution("medium");
      MediaResolution fromUpper = new MediaResolution("MEDIUM");
      MediaResolution fromMixed = new MediaResolution("Medium");

      assertEquals(MediaResolution.Known.MEDIUM, fromLower.knownEnum());
      assertEquals(MediaResolution.Known.MEDIUM, fromUpper.knownEnum());
      assertEquals(MediaResolution.Known.MEDIUM, fromMixed.knownEnum());
    }
  }

  @Nested
  @DisplayName("ResponseModality serialization")
  class ResponseModalitySerializationTest {

    @Test
    public void testText_serializesToLowercase() {
      ResponseModality modality = new ResponseModality(ResponseModality.Known.TEXT);
      assertEquals("text", modality.toString());
    }

    @Test
    public void testImage_serializesToLowercase() {
      ResponseModality modality = new ResponseModality(ResponseModality.Known.IMAGE);
      assertEquals("image", modality.toString());
    }

    @Test
    public void testAudio_serializesToLowercase() {
      ResponseModality modality = new ResponseModality(ResponseModality.Known.AUDIO);
      assertEquals("audio", modality.toString());
    }

    @Test
    public void testFromString_caseInsensitive() {
      ResponseModality fromLower = new ResponseModality("text");
      ResponseModality fromUpper = new ResponseModality("TEXT");
      ResponseModality fromMixed = new ResponseModality("Text");

      assertEquals(ResponseModality.Known.TEXT, fromLower.knownEnum());
      assertEquals(ResponseModality.Known.TEXT, fromUpper.knownEnum());
      assertEquals(ResponseModality.Known.TEXT, fromMixed.knownEnum());
    }
  }

  @Nested
  @DisplayName("GenerationConfig JSON serialization")
  class GenerationConfigJsonSerializationTest {

    @Test
    public void testThinkingLevel_serializesAsLowercaseInJson() {
      GenerationConfig config =
          GenerationConfig.builder()
              .thinkingLevel(new ThinkingLevel(ThinkingLevel.Known.HIGH))
              .build();

      String json = config.toJson();

      // Verify lowercase value in JSON
      assertTrue(json.contains("\"thinking_level\":\"high\""),
          "Expected lowercase 'high' in JSON, got: " + json);
    }

    @Test
    public void testThinkingSummaries_serializesAsLowercaseInJson() {
      GenerationConfig config =
          GenerationConfig.builder()
              .thinkingSummaries(new ThinkingSummaries(ThinkingSummaries.Known.AUTO))
              .build();

      String json = config.toJson();

      // Verify lowercase value in JSON
      assertTrue(json.contains("\"thinking_summaries\":\"auto\""),
          "Expected lowercase 'auto' in JSON, got: " + json);
    }

    @Test
    public void testAllEnums_serializeAsLowercaseInJson() {
      GenerationConfig config =
          GenerationConfig.builder()
              .thinkingLevel(new ThinkingLevel(ThinkingLevel.Known.MEDIUM))
              .thinkingSummaries(new ThinkingSummaries(ThinkingSummaries.Known.NONE))
              .toolChoice(ToolChoice.fromString("any"))
              .build();

      String json = config.toJson();

      // Verify all lowercase values in JSON
      assertTrue(json.contains("\"thinking_level\":\"medium\""),
          "Expected lowercase 'medium' in JSON");
      assertTrue(json.contains("\"thinking_summaries\":\"none\""),
          "Expected lowercase 'none' in JSON");
      assertTrue(json.contains("\"any\""),
          "Expected lowercase 'any' in JSON");
    }

    @Test
    public void testJsonRoundTrip_preservesEnumValues() {
      GenerationConfig original =
          GenerationConfig.builder()
              .thinkingLevel(new ThinkingLevel(ThinkingLevel.Known.HIGH))
              .thinkingSummaries(new ThinkingSummaries(ThinkingSummaries.Known.AUTO))
              .build();

      String json = original.toJson();
      GenerationConfig deserialized = GenerationConfig.fromJson(json);

      assertEquals(ThinkingLevel.Known.HIGH, deserialized.thinkingLevel().get().knownEnum());
      assertEquals(ThinkingSummaries.Known.AUTO, deserialized.thinkingSummaries().get().knownEnum());
    }

    @Test
    public void testJsonDeserialization_fromLowercaseValues() {
      String json = "{\"thinking_level\":\"low\",\"thinking_summaries\":\"none\"}";

      GenerationConfig config = GenerationConfig.fromJson(json);

      assertEquals(ThinkingLevel.Known.LOW, config.thinkingLevel().get().knownEnum());
      assertEquals(ThinkingSummaries.Known.NONE, config.thinkingSummaries().get().knownEnum());
    }

    @Test
    public void testJsonDeserialization_fromUppercaseValues() {
      // API might return uppercase, ensure we handle it
      String json = "{\"thinking_level\":\"HIGH\",\"thinking_summaries\":\"AUTO\"}";

      GenerationConfig config = GenerationConfig.fromJson(json);

      assertEquals(ThinkingLevel.Known.HIGH, config.thinkingLevel().get().knownEnum());
      assertEquals(ThinkingSummaries.Known.AUTO, config.thinkingSummaries().get().knownEnum());
    }
  }
}
