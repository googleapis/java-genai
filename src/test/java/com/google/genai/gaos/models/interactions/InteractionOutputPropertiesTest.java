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

package com.google.genai.gaos.models.interactions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.google.genai.gaos.utils.Utils;
import org.junit.jupiter.api.Test;

/**
 * Tests that the SDK-only {@code output_text}, {@code output_image}, {@code output_audio} and
 * {@code output_video} helpers on {@link Interaction} are derived from {@code steps}.
 */
public final class InteractionOutputPropertiesTest {

  private static final String USER_INPUT =
      "{\"type\": \"user_input\", \"content\": [{\"type\": \"text\", \"text\": \"hi\"}]}";
  private static final String THOUGHT = "{\"type\": \"thought\", \"signature\": \"sig\"}";
  private static final String FUNCTION_CALL =
      "{\"type\": \"function_call\", \"id\": \"call-1\", \"name\": \"get_weather\","
          + " \"arguments\": {}}";
  private static final String TRAILING_FUNCTION_CALL =
      "{\"type\": \"function_call\", \"id\": \"call_trailing\", \"name\": \"noop\","
          + " \"arguments\": {}}";
  private static final String FUNCTION_RESULT =
      "{\"type\": \"function_result\", \"call_id\": \"call-1\", \"result\": \"sunny\"}";
  private static final String EMPTY_MODEL_OUTPUT = "{\"type\": \"model_output\"}";
  private static final String IMAGE_1 =
      "{\"type\": \"image\", \"data\": \"aW1nMQ==\", \"mime_type\": \"image/png\"}";
  private static final String IMAGE_2 =
      "{\"type\": \"image\", \"data\": \"aW1nMg==\", \"mime_type\": \"image/png\"}";
  private static final String AUDIO =
      "{\"type\": \"audio\", \"data\": \"YXVkaW8=\", \"mime_type\": \"audio/wav\"}";
  private static final String AUDIO_2 =
      "{\"type\": \"audio\", \"data\": \"YXVkaW8y\", \"mime_type\": \"audio/wav\"}";
  private static final String VIDEO =
      "{\"type\": \"video\", \"uri\": \"gs://bucket/video.mp4\", \"mime_type\": \"video/mp4\"}";
  private static final String VIDEO_2 =
      "{\"type\": \"video\", \"uri\": \"gs://bucket/video2.mp4\", \"mime_type\": \"video/mp4\"}";

  private static String thought(String signature) throws Exception {
    String sigJson = Utils.mapper().writeValueAsString(signature);
    String summaryJson = Utils.mapper().writeValueAsString("summary " + signature);
    return "{\"type\": \"thought\", \"signature\": "
        + sigJson
        + ", \"summary\": [{\"type\": \"text\", \"text\": "
        + summaryJson
        + "}]}";
  }

  private static String text(String text) throws Exception {
    return "{\"type\": \"text\", \"text\": " + Utils.mapper().writeValueAsString(text) + "}";
  }

  private static String modelOutput(String... content) {
    return "{\"type\": \"model_output\", \"content\": [" + String.join(", ", content) + "]}";
  }

  private static Interaction parse(String... steps) throws Exception {
    String json =
        "{\"id\": \"interaction-1\", \"status\": \"completed\", \"steps\": ["
            + String.join(", ", steps)
            + "]}";
    return Utils.mapper().readValue(json, Interaction.class);
  }

  @Test
  public void longDecodeMultiStep() throws Exception {
    Interaction interaction =
        parse(
            thought("a"),
            modelOutput(text("First part. ")),
            thought("b"),
            modelOutput(text("Second part. ")),
            EMPTY_MODEL_OUTPUT,
            modelOutput(text("Third part.")),
            thought("c"));
    assertEquals("First part. Second part. Third part.", interaction.outputText().orElse(null));
  }

  @Test
  public void toolBoundary() throws Exception {
    Interaction interaction =
        parse(
            modelOutput(text("Before tool call. ")),
            FUNCTION_CALL,
            FUNCTION_RESULT,
            thought("a"),
            modelOutput(text("Final part 1. ")),
            thought("b"),
            modelOutput(text("Final part 2.")),
            TRAILING_FUNCTION_CALL);
    assertEquals("Final part 1. Final part 2.", interaction.outputText().orElse(null));
  }

  @Test
  public void userInputBoundary() throws Exception {
    Interaction interaction =
        parse(
            modelOutput(text("Previous turn. ")),
            USER_INPUT,
            thought("a"),
            modelOutput(text("A")),
            thought("b"),
            modelOutput(text("B")));
    assertEquals("AB", interaction.outputText().orElse(null));
  }

  @Test
  public void nonTextBarrier() throws Exception {
    Interaction interaction =
        parse(modelOutput(text("X"), IMAGE_1), thought("a"), modelOutput(text("Y")));
    assertEquals("Y", interaction.outputText().orElse(null));
  }

  @Test
  public void singleModelOutputText() throws Exception {
    Interaction interaction = parse(USER_INPUT, modelOutput(text("Hello world!")));
    assertEquals("Hello world!", interaction.outputText().orElse(null));
  }

  @Test
  public void multiPartTextIsConcatenated() throws Exception {
    Interaction interaction =
        parse(USER_INPUT, modelOutput(text("Hello"), text(", "), text("world!")));
    assertEquals("Hello, world!", interaction.outputText().orElse(null));
  }

  @Test
  public void thoughtStepsBetweenModelOutputsAreSkipped() throws Exception {
    Interaction interaction =
        parse(
            USER_INPUT,
            THOUGHT,
            modelOutput(text("First. ")),
            THOUGHT,
            modelOutput(text("Second.")));
    assertEquals("First. Second.", interaction.outputText().orElse(null));
  }

  @Test
  public void toolStepsEndTextCollection() throws Exception {
    Interaction interaction =
        parse(
            USER_INPUT,
            modelOutput(text("Let me check. ")),
            FUNCTION_CALL,
            FUNCTION_RESULT,
            modelOutput(text("It is sunny.")));
    assertEquals("It is sunny.", interaction.outputText().orElse(null));
  }

  @Test
  public void trailingToolStepsAreSkippedBeforeCollectionStarts() throws Exception {
    Interaction interaction =
        parse(USER_INPUT, modelOutput(text("Calling a tool.")), FUNCTION_CALL);
    assertEquals("Calling a tool.", interaction.outputText().orElse(null));
  }

  @Test
  public void userInputEndsTextCollection() throws Exception {
    Interaction interaction =
        parse(
            modelOutput(text("Previous turn. ")),
            USER_INPUT,
            modelOutput(text("This turn.")));
    assertEquals("This turn.", interaction.outputText().orElse(null));
  }

  @Test
  public void emptyModelOutputIsSkipped() throws Exception {
    Interaction interaction =
        parse(
            USER_INPUT,
            modelOutput(text("Part one. ")),
            EMPTY_MODEL_OUTPUT,
            modelOutput(text("Part two.")),
            EMPTY_MODEL_OUTPUT,
            modelOutput());
    assertEquals("Part one. Part two.", interaction.outputText().orElse(null));
  }

  @Test
  public void nonTextContentEndsTextCollection() throws Exception {
    Interaction interaction =
        parse(USER_INPUT, modelOutput(text("Before image. "), IMAGE_1, text("After image.")));
    assertEquals("After image.", interaction.outputText().orElse(null));
  }

  @Test
  public void nonTextContentInEarlierStepEndsTextCollection() throws Exception {
    Interaction interaction =
        parse(USER_INPUT, modelOutput(text("Before image."), IMAGE_1), modelOutput(text("After image.")));
    assertEquals("After image.", interaction.outputText().orElse(null));
  }

  @Test
  public void nonTextContentBeforeCollectionStartsIsSkipped() throws Exception {
    Interaction interaction = parse(USER_INPUT, modelOutput(text("Caption."), IMAGE_1));
    assertEquals("Caption.", interaction.outputText().orElse(null));
  }

  @Test
  public void outputTextUnsetWhenNoText() throws Exception {
    Interaction interaction = parse(USER_INPUT, modelOutput(IMAGE_1));
    assertFalse(interaction.outputText().isPresent());
  }

  @Test
  public void outputTextUnsetWhenOnlyUserText() throws Exception {
    Interaction interaction = parse(modelOutput(text("Old turn.")), USER_INPUT);
    assertFalse(interaction.outputText().isPresent());
    assertFalse(interaction.outputImage().isPresent());
  }

  @Test
  public void outputPropertiesUnsetWithoutSteps() throws Exception {
    Interaction interaction =
        Utils.mapper()
            .readValue("{\"id\": \"interaction-1\", \"status\": \"completed\"}", Interaction.class);
    assertFalse(interaction.outputText().isPresent());
    assertFalse(interaction.outputImage().isPresent());
    assertFalse(interaction.outputAudio().isPresent());
    assertFalse(interaction.outputVideo().isPresent());
  }

  @Test
  public void mediaOutputsSelectLastOfEachKind() throws Exception {
    Interaction interaction =
        parse(
            modelOutput(IMAGE_1, AUDIO, VIDEO),
            USER_INPUT,
            modelOutput(IMAGE_1, AUDIO_2, VIDEO),
            FUNCTION_CALL,
            modelOutput(IMAGE_2, text("Done.")),
            modelOutput(VIDEO_2));
    assertEquals("aW1nMg==", interaction.outputImage().get().data().orElse(null));
    assertEquals("YXVkaW8y", interaction.outputAudio().get().data().orElse(null));
    assertEquals("gs://bucket/video2.mp4", interaction.outputVideo().get().uri().orElse(null));
    assertEquals("Done.", interaction.outputText().orElse(null));
  }

  @Test
  public void mediaOutputsStopAtUserInput() throws Exception {
    Interaction interaction =
        parse(modelOutput(IMAGE_1, AUDIO, VIDEO), USER_INPUT, modelOutput(text("No media.")));
    assertFalse(interaction.outputImage().isPresent());
    assertFalse(interaction.outputAudio().isPresent());
    assertFalse(interaction.outputVideo().isPresent());
  }

  @Test
  public void legacyOutputsArePopulated() throws Exception {
    String json =
        "{\"id\": \"interaction-1\", \"status\": \"completed\", \"model\": \"lyria-realtime-exp\","
            + " \"outputs\": ["
            + text("Lyrics.")
            + ", "
            + AUDIO
            + "]}";
    Interaction interaction = Utils.mapper().readValue(json, Interaction.class);
    assertEquals("Lyrics.", interaction.outputText().orElse(null));
    assertEquals("YXVkaW8=", interaction.outputAudio().get().data().orElse(null));
  }

  @Test
  public void builderPopulatesFromSteps() {
    Interaction interaction =
        Interaction.builder()
            .status(InteractionStatus.COMPLETED)
            .steps(
                java.util.Collections.singletonList(
                    ModelOutputStep.builder()
                        .content(
                            java.util.Collections.singletonList(
                                TextContent.builder().text("Built.").build()))
                        .build()))
            .build();
    assertEquals("Built.", interaction.outputText().orElse(null));
  }
}
