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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.ImmutableList;
import com.google.genai.types.interactions.ThinkingLevel;
import com.google.genai.types.interactions.AllowedTools;
import com.google.genai.types.interactions.GenerationConfig;
import com.google.genai.types.interactions.ImageConfig;
import com.google.genai.types.interactions.SpeechConfig;
import com.google.genai.types.interactions.ThinkingSummaries;
import com.google.genai.types.interactions.ToolChoice;
import com.google.genai.types.interactions.ToolChoiceConfig;
import com.google.genai.types.interactions.ToolChoiceType;
import org.junit.jupiter.api.Test;

/**
 * Comprehensive testing for all 10 GenerationConfig fields.
 *
 * <p>This test suite ensures 100% coverage of GenerationConfig:
 *
 * <ul>
 *   <li>Basic controls: temperature, topP, seed, maxOutputTokens (5 tests)
 *   <li>Stop sequences: stopSequences (1 test)
 *   <li>Tool choice: toolChoice with types and config (6 tests)
 *   <li>Thinking controls: thinkingLevel, thinkingSummaries (6 tests)
 *   <li>Speech config: speechConfig (3 tests)
 *   <li>Image config: imageConfig (11 tests)
 *   <li>Combined configs: all fields together (2 tests)
 *   <li>Serialization: JSON roundtrip (1 test)
 * </ul>
 */
public class InteractionsGenerationConfigTest {

  // ======================
  // BASIC CONTROLS (5 tests)
  // ======================

  @Test
  public void testTemperature() {
    GenerationConfig config =
        GenerationConfig.builder().temperature(0.7f).build();

    assertTrue(config.temperature().isPresent());
    assertEquals(0.7f, config.temperature().get(), 0.001);
  }

  @Test
  public void testTopP() {
    GenerationConfig config = GenerationConfig.builder().topP(0.9f).build();

    assertTrue(config.topP().isPresent());
    assertEquals(0.9f, config.topP().get(), 0.001);
  }

  @Test
  public void testSeed() {
    GenerationConfig config = GenerationConfig.builder().seed(42).build();

    assertTrue(config.seed().isPresent());
    assertEquals(42, config.seed().get());
  }

  @Test
  public void testMaxOutputTokens() {
    GenerationConfig config =
        GenerationConfig.builder().maxOutputTokens(2048).build();

    assertTrue(config.maxOutputTokens().isPresent());
    assertEquals(2048, config.maxOutputTokens().get());
  }

  @Test
  public void testBasicControlsCombined() {
    GenerationConfig config =
        GenerationConfig.builder()
            .temperature(0.8f)
            .topP(0.95f)
            .seed(123)
            .maxOutputTokens(1000)
            .build();

    assertTrue(config.temperature().isPresent());
    assertEquals(0.8f, config.temperature().get(), 0.001);
    assertTrue(config.topP().isPresent());
    assertEquals(0.95f, config.topP().get(), 0.001);
    assertTrue(config.seed().isPresent());
    assertEquals(123, config.seed().get());
    assertTrue(config.maxOutputTokens().isPresent());
    assertEquals(1000, config.maxOutputTokens().get());
  }

  // ======================
  // STOP SEQUENCES (1 test)
  // ======================

  @Test
  public void testStopSequences() {
    ImmutableList<String> stopSeqs = ImmutableList.of("STOP", "END", "\n\n");
    GenerationConfig config =
        GenerationConfig.builder().stopSequences(stopSeqs).build();

    assertTrue(config.stopSequences().isPresent());
    assertEquals(3, config.stopSequences().get().size());
    assertEquals("STOP", config.stopSequences().get().get(0));
    assertEquals("END", config.stopSequences().get().get(1));
    assertEquals("\n\n", config.stopSequences().get().get(2));
  }

  // ======================
  // TOOL CHOICE (6 tests)
  // ======================

  @Test
  public void testToolChoice_AUTO() {
    ToolChoice toolChoice = ToolChoice.fromType(ToolChoiceType.Known.AUTO);
    GenerationConfig config =
        GenerationConfig.builder().toolChoice(toolChoice).build();

    assertTrue(config.toolChoice().isPresent());
    assertTrue(config.toolChoice().get().isType());
    assertEquals(
        ToolChoiceType.Known.AUTO, config.toolChoice().get().asType().knownEnum());
  }

  @Test
  public void testToolChoice_ANY() {
    ToolChoice toolChoice = ToolChoice.fromType(ToolChoiceType.Known.ANY);
    GenerationConfig config =
        GenerationConfig.builder().toolChoice(toolChoice).build();

    assertTrue(config.toolChoice().isPresent());
    assertTrue(config.toolChoice().get().isType());
    assertEquals(
        ToolChoiceType.Known.ANY, config.toolChoice().get().asType().knownEnum());
  }

  @Test
  public void testToolChoice_NONE() {
    ToolChoice toolChoice = ToolChoice.fromType(ToolChoiceType.Known.NONE);
    GenerationConfig config =
        GenerationConfig.builder().toolChoice(toolChoice).build();

    assertTrue(config.toolChoice().isPresent());
    assertTrue(config.toolChoice().get().isType());
    assertEquals(
        ToolChoiceType.Known.NONE, config.toolChoice().get().asType().knownEnum());
  }

  @Test
  public void testToolChoice_VALIDATED() {
    ToolChoice toolChoice = ToolChoice.fromType(ToolChoiceType.Known.VALIDATED);
    GenerationConfig config =
        GenerationConfig.builder().toolChoice(toolChoice).build();

    assertTrue(config.toolChoice().isPresent());
    assertTrue(config.toolChoice().get().isType());
    assertEquals(
        ToolChoiceType.Known.VALIDATED, config.toolChoice().get().asType().knownEnum());
  }

  @Test
  public void testToolChoice_withConfig() {
    AllowedTools allowedTools =
        AllowedTools.builder()
            .mode("auto")
            .tools(ImmutableList.of("search_tool", "calculator"))
            .build();

    ToolChoiceConfig toolChoiceConfig =
        ToolChoiceConfig.builder().allowedTools(allowedTools).build();

    ToolChoice toolChoice = ToolChoice.fromConfig(toolChoiceConfig);
    GenerationConfig config =
        GenerationConfig.builder().toolChoice(toolChoice).build();

    assertTrue(config.toolChoice().isPresent());
    assertTrue(config.toolChoice().get().isConfig());
    ToolChoiceConfig retrievedConfig = config.toolChoice().get().asConfig();
    assertTrue(retrievedConfig.allowedTools().isPresent());
    assertTrue(retrievedConfig.allowedTools().get().mode().isPresent());
    assertEquals("auto", retrievedConfig.allowedTools().get().mode().get());
    assertTrue(retrievedConfig.allowedTools().get().tools().isPresent());
    assertEquals(2, retrievedConfig.allowedTools().get().tools().get().size());
  }

  @Test
  public void testToolChoice_fromString() {
    ToolChoice toolChoice = ToolChoice.fromString("auto");
    GenerationConfig config =
        GenerationConfig.builder().toolChoice(toolChoice).build();

    assertTrue(config.toolChoice().isPresent());
    assertTrue(config.toolChoice().get().isType());
  }

  // ======================
  // THINKING CONTROLS (6 tests)
  // ======================

  @Test
  public void testThinkingLevel_MINIMAL() {
    ThinkingLevel thinkingLevel = new ThinkingLevel(ThinkingLevel.Known.MINIMAL);
    GenerationConfig config =
        GenerationConfig.builder().thinkingLevel(thinkingLevel).build();

    assertTrue(config.thinkingLevel().isPresent());
    assertEquals(ThinkingLevel.Known.MINIMAL, config.thinkingLevel().get().knownEnum());
  }

  @Test
  public void testThinkingLevel_LOW() {
    ThinkingLevel thinkingLevel = new ThinkingLevel(ThinkingLevel.Known.LOW);
    GenerationConfig config =
        GenerationConfig.builder().thinkingLevel(thinkingLevel).build();

    assertTrue(config.thinkingLevel().isPresent());
    assertEquals(ThinkingLevel.Known.LOW, config.thinkingLevel().get().knownEnum());
  }

  @Test
  public void testThinkingLevel_MEDIUM() {
    ThinkingLevel thinkingLevel = new ThinkingLevel(ThinkingLevel.Known.MEDIUM);
    GenerationConfig config =
        GenerationConfig.builder().thinkingLevel(thinkingLevel).build();

    assertTrue(config.thinkingLevel().isPresent());
    assertEquals(ThinkingLevel.Known.MEDIUM, config.thinkingLevel().get().knownEnum());
  }

  @Test
  public void testThinkingLevel_HIGH() {
    ThinkingLevel thinkingLevel = new ThinkingLevel(ThinkingLevel.Known.HIGH);
    GenerationConfig config =
        GenerationConfig.builder().thinkingLevel(thinkingLevel).build();

    assertTrue(config.thinkingLevel().isPresent());
    assertEquals(ThinkingLevel.Known.HIGH, config.thinkingLevel().get().knownEnum());
  }

  @Test
  public void testThinkingSummaries_AUTO() {
    ThinkingSummaries thinkingSummaries = new ThinkingSummaries(ThinkingSummaries.Known.AUTO);
    GenerationConfig config =
        GenerationConfig.builder().thinkingSummaries(thinkingSummaries).build();

    assertTrue(config.thinkingSummaries().isPresent());
    assertEquals(ThinkingSummaries.Known.AUTO, config.thinkingSummaries().get().knownEnum());
  }

  @Test
  public void testThinkingSummaries_NONE() {
    ThinkingSummaries thinkingSummaries = new ThinkingSummaries(ThinkingSummaries.Known.NONE);
    GenerationConfig config =
        GenerationConfig.builder().thinkingSummaries(thinkingSummaries).build();

    assertTrue(config.thinkingSummaries().isPresent());
    assertEquals(ThinkingSummaries.Known.NONE, config.thinkingSummaries().get().knownEnum());
  }

  // ======================
  // SPEECH CONFIG (3 tests)
  // ======================

  @Test
  public void testSpeechConfig_allFields() {
    SpeechConfig speechConfig =
        SpeechConfig.builder()
            .voice("en-US-Studio-O")
            .language("en-US")
            .speaker("speaker1")
            .build();

    GenerationConfig config =
        GenerationConfig.builder().speechConfig(speechConfig).build();

    assertTrue(config.speechConfig().isPresent());
    assertTrue(config.speechConfig().get().voice().isPresent());
    assertEquals("en-US-Studio-O", config.speechConfig().get().voice().get());
    assertTrue(config.speechConfig().get().language().isPresent());
    assertEquals("en-US", config.speechConfig().get().language().get());
    assertTrue(config.speechConfig().get().speaker().isPresent());
    assertEquals("speaker1", config.speechConfig().get().speaker().get());
  }

  @Test
  public void testSpeechConfig_voiceOnly() {
    SpeechConfig speechConfig =
        SpeechConfig.builder().voice("en-GB-Studio-B").build();

    GenerationConfig config =
        GenerationConfig.builder().speechConfig(speechConfig).build();

    assertTrue(config.speechConfig().isPresent());
    assertTrue(config.speechConfig().get().voice().isPresent());
    assertEquals("en-GB-Studio-B", config.speechConfig().get().voice().get());
    assertFalse(config.speechConfig().get().language().isPresent());
    assertFalse(config.speechConfig().get().speaker().isPresent());
  }

  @Test
  public void testSpeechConfig_builderConvenience() {
    GenerationConfig config =
        GenerationConfig.builder()
            .speechConfig(SpeechConfig.builder().voice("en-US-Studio-O"))
            .build();

    assertTrue(config.speechConfig().isPresent());
    assertTrue(config.speechConfig().get().voice().isPresent());
    assertEquals("en-US-Studio-O", config.speechConfig().get().voice().get());
  }

  // ======================
  // IMAGE CONFIG (11 tests)
  // ======================

  @Test
  public void testImageConfig_aspectRatio_1_1() {
    ImageConfig imageConfig =
        ImageConfig.builder().aspectRatio("1:1").build();

    GenerationConfig config =
        GenerationConfig.builder().imageConfig(imageConfig).build();

    assertTrue(config.imageConfig().isPresent());
    assertTrue(config.imageConfig().get().aspectRatio().isPresent());
    assertEquals("1:1", config.imageConfig().get().aspectRatio().get());
  }

  @Test
  public void testImageConfig_allAspectRatios() {
    String[] aspectRatios = {"1:1", "2:3", "3:2", "3:4", "4:3", "9:16", "16:9", "21:9"};

    for (String aspectRatio : aspectRatios) {
      ImageConfig imageConfig =
          ImageConfig.builder().aspectRatio(aspectRatio).build();

      GenerationConfig config =
          GenerationConfig.builder().imageConfig(imageConfig).build();

      assertTrue(config.imageConfig().isPresent());
      assertTrue(config.imageConfig().get().aspectRatio().isPresent());
      assertEquals(aspectRatio, config.imageConfig().get().aspectRatio().get());
    }
  }

  @Test
  public void testImageConfig_imageSize_1K() {
    ImageConfig imageConfig = ImageConfig.builder().imageSize("1K").build();

    GenerationConfig config =
        GenerationConfig.builder().imageConfig(imageConfig).build();

    assertTrue(config.imageConfig().isPresent());
    assertTrue(config.imageConfig().get().imageSize().isPresent());
    assertEquals("1K", config.imageConfig().get().imageSize().get());
  }

  @Test
  public void testImageConfig_imageSize_2K() {
    ImageConfig imageConfig = ImageConfig.builder().imageSize("2K").build();

    GenerationConfig config =
        GenerationConfig.builder().imageConfig(imageConfig).build();

    assertTrue(config.imageConfig().isPresent());
    assertTrue(config.imageConfig().get().imageSize().isPresent());
    assertEquals("2K", config.imageConfig().get().imageSize().get());
  }

  @Test
  public void testImageConfig_imageSize_4K() {
    ImageConfig imageConfig = ImageConfig.builder().imageSize("4K").build();

    GenerationConfig config =
        GenerationConfig.builder().imageConfig(imageConfig).build();

    assertTrue(config.imageConfig().isPresent());
    assertTrue(config.imageConfig().get().imageSize().isPresent());
    assertEquals("4K", config.imageConfig().get().imageSize().get());
  }

  @Test
  public void testImageConfig_aspectRatioAndSize() {
    ImageConfig imageConfig =
        ImageConfig.builder().aspectRatio("16:9").imageSize("4K").build();

    GenerationConfig config =
        GenerationConfig.builder().imageConfig(imageConfig).build();

    assertTrue(config.imageConfig().isPresent());
    assertTrue(config.imageConfig().get().aspectRatio().isPresent());
    assertEquals("16:9", config.imageConfig().get().aspectRatio().get());
    assertTrue(config.imageConfig().get().imageSize().isPresent());
    assertEquals("4K", config.imageConfig().get().imageSize().get());
  }

  @Test
  public void testImageConfig_builderConvenience() {
    GenerationConfig config =
        GenerationConfig.builder()
            .imageConfig(ImageConfig.builder().aspectRatio("16:9"))
            .build();

    assertTrue(config.imageConfig().isPresent());
    assertTrue(config.imageConfig().get().aspectRatio().isPresent());
    assertEquals("16:9", config.imageConfig().get().aspectRatio().get());
  }

  // Additional tests for edge cases

  @Test
  public void testImageConfig_onlyAspectRatio() {
    ImageConfig imageConfig =
        ImageConfig.builder().aspectRatio("3:4").build();

    GenerationConfig config =
        GenerationConfig.builder().imageConfig(imageConfig).build();

    assertTrue(config.imageConfig().isPresent());
    assertTrue(config.imageConfig().get().aspectRatio().isPresent());
    assertEquals("3:4", config.imageConfig().get().aspectRatio().get());
    assertFalse(config.imageConfig().get().imageSize().isPresent());
  }

  @Test
  public void testImageConfig_onlyImageSize() {
    ImageConfig imageConfig = ImageConfig.builder().imageSize("2K").build();

    GenerationConfig config =
        GenerationConfig.builder().imageConfig(imageConfig).build();

    assertTrue(config.imageConfig().isPresent());
    assertFalse(config.imageConfig().get().aspectRatio().isPresent());
    assertTrue(config.imageConfig().get().imageSize().isPresent());
    assertEquals("2K", config.imageConfig().get().imageSize().get());
  }

  @Test
  public void testImageConfig_widescreen() {
    ImageConfig imageConfig =
        ImageConfig.builder().aspectRatio("21:9").imageSize("4K").build();

    GenerationConfig config =
        GenerationConfig.builder().imageConfig(imageConfig).build();

    assertTrue(config.imageConfig().isPresent());
    assertEquals("21:9", config.imageConfig().get().aspectRatio().get());
    assertEquals("4K", config.imageConfig().get().imageSize().get());
  }

  @Test
  public void testImageConfig_portrait() {
    ImageConfig imageConfig =
        ImageConfig.builder().aspectRatio("9:16").imageSize("2K").build();

    GenerationConfig config =
        GenerationConfig.builder().imageConfig(imageConfig).build();

    assertTrue(config.imageConfig().isPresent());
    assertEquals("9:16", config.imageConfig().get().aspectRatio().get());
    assertEquals("2K", config.imageConfig().get().imageSize().get());
  }

  // ======================
  // COMBINED CONFIG (2 tests)
  // ======================

  @Test
  public void testCombinedConfig_allFields() {
    AllowedTools allowedTools =
        AllowedTools.builder()
            .mode("auto")
            .tools(ImmutableList.of("search", "calc"))
            .build();

    ToolChoiceConfig toolChoiceConfig =
        ToolChoiceConfig.builder().allowedTools(allowedTools).build();

    GenerationConfig config =
        GenerationConfig.builder()
            .temperature(0.7f)
            .topP(0.9f)
            .seed(12345)
            .maxOutputTokens(500)
            .stopSequences(ImmutableList.of("\n\n", "---"))
            .toolChoice(ToolChoice.fromConfig(toolChoiceConfig))
            .thinkingLevel(new ThinkingLevel(ThinkingLevel.Known.MEDIUM))
            .thinkingSummaries(new ThinkingSummaries(ThinkingSummaries.Known.AUTO))
            .speechConfig(
                SpeechConfig.builder()
                    .voice("en-US-Studio-O")
                    .language("en-US")
                    .build())
            .imageConfig(
                ImageConfig.builder().aspectRatio("16:9").imageSize("2K").build())
            .build();

    // Verify all 10 fields are present
    assertTrue(config.temperature().isPresent());
    assertTrue(config.topP().isPresent());
    assertTrue(config.seed().isPresent());
    assertTrue(config.maxOutputTokens().isPresent());
    assertTrue(config.stopSequences().isPresent());
    assertTrue(config.toolChoice().isPresent());
    assertTrue(config.thinkingLevel().isPresent());
    assertTrue(config.thinkingSummaries().isPresent());
    assertTrue(config.speechConfig().isPresent());
    assertTrue(config.imageConfig().isPresent());

    // Verify values
    assertEquals(0.7f, config.temperature().get(), 0.001);
    assertEquals(0.9f, config.topP().get(), 0.001);
    assertEquals(12345, config.seed().get());
    assertEquals(500, config.maxOutputTokens().get());
    assertEquals(2, config.stopSequences().get().size());
    assertTrue(config.toolChoice().get().isConfig());
    assertEquals(ThinkingLevel.Known.MEDIUM, config.thinkingLevel().get().knownEnum());
    assertEquals(ThinkingSummaries.Known.AUTO, config.thinkingSummaries().get().knownEnum());
    assertEquals("en-US-Studio-O", config.speechConfig().get().voice().get());
    assertEquals("16:9", config.imageConfig().get().aspectRatio().get());
  }

  @Test
  public void testCombinedConfig_emptyOptionals() {
    GenerationConfig config = GenerationConfig.builder().build();

    // All fields should be absent
    assertFalse(config.temperature().isPresent());
    assertFalse(config.topP().isPresent());
    assertFalse(config.seed().isPresent());
    assertFalse(config.maxOutputTokens().isPresent());
    assertFalse(config.stopSequences().isPresent());
    assertFalse(config.toolChoice().isPresent());
    assertFalse(config.thinkingLevel().isPresent());
    assertFalse(config.thinkingSummaries().isPresent());
    assertFalse(config.speechConfig().isPresent());
    assertFalse(config.imageConfig().isPresent());
  }

  // ======================
  // SERIALIZATION (1 test)
  // ======================

  @Test
  public void testSerialization_allFields() {
    AllowedTools allowedTools =
        AllowedTools.builder()
            .mode("validated")
            .tools(ImmutableList.of("tool1", "tool2"))
            .build();

    ToolChoiceConfig toolChoiceConfig =
        ToolChoiceConfig.builder().allowedTools(allowedTools).build();

    GenerationConfig config =
        GenerationConfig.builder()
            .temperature(0.5f)
            .topP(0.85f)
            .seed(999)
            .maxOutputTokens(1024)
            .stopSequences(ImmutableList.of("STOP"))
            .toolChoice(ToolChoice.fromConfig(toolChoiceConfig))
            .thinkingLevel(new ThinkingLevel(ThinkingLevel.Known.HIGH))
            .thinkingSummaries(new ThinkingSummaries(ThinkingSummaries.Known.NONE))
            .speechConfig(
                SpeechConfig.builder()
                    .voice("en-AU-Studio-A")
                    .language("en-AU")
                    .speaker("speaker2")
                    .build())
            .imageConfig(
                ImageConfig.builder().aspectRatio("4:3").imageSize("1K").build())
            .build();

    // Serialize to JSON
    String json = config.toJson();
    assertNotNull(json);
    assertFalse(json.isEmpty());

    // Verify JSON contains expected field names
    assertTrue(json.contains("\"temperature\""));
    assertTrue(json.contains("\"top_p\""));
    assertTrue(json.contains("\"seed\""));
    assertTrue(json.contains("\"max_output_tokens\""));
    assertTrue(json.contains("\"stop_sequences\""));
    assertTrue(json.contains("\"tool_choice\""));
    assertTrue(json.contains("\"thinking_level\""));
    assertTrue(json.contains("\"thinking_summaries\""));
    assertTrue(json.contains("\"speech_config\""));
    assertTrue(json.contains("\"image_config\""));

    // Deserialize and verify
    GenerationConfig deserialized = GenerationConfig.fromJson(json);
    assertNotNull(deserialized);
    assertEquals(0.5f, deserialized.temperature().get(), 0.001);
    assertEquals(0.85f, deserialized.topP().get(), 0.001);
    assertEquals(999, deserialized.seed().get());
    assertEquals(1024, deserialized.maxOutputTokens().get());
    assertEquals(ThinkingLevel.Known.HIGH, deserialized.thinkingLevel().get().knownEnum());
    assertEquals(ThinkingSummaries.Known.NONE, deserialized.thinkingSummaries().get().knownEnum());
  }
}
