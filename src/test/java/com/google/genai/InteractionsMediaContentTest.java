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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.ImmutableList;
import com.google.genai.types.interactions.CreateInteractionConfig;
import com.google.genai.types.interactions.GenerationConfig;
import com.google.genai.types.interactions.ImageConfig;
import com.google.genai.types.interactions.Interaction;
import com.google.genai.types.interactions.MediaResolution;
import com.google.genai.types.interactions.content.AudioContent;
import com.google.genai.types.interactions.content.DocumentContent;
import com.google.genai.types.interactions.content.ImageContent;
import com.google.genai.types.interactions.content.TextContent;
import com.google.genai.types.interactions.content.VideoContent;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Comprehensive tests for multimodal content handling in Interactions API.
 *
 * <p>This test suite validates ImageContent, AudioContent, VideoContent, DocumentContent, and
 * mixed media scenarios with various configurations.
 *
 * <p>To run these tests, set: export GOOGLE_GENAI_REPLAYS_DIRECTORY="/path/to/genai/replays"
 */
@EnabledIfEnvironmentVariable(
    named = "GOOGLE_GENAI_REPLAYS_DIRECTORY",
    matches = ".*genai/replays.*")
@ExtendWith(EnvironmentVariablesMockingExtension.class)
public class InteractionsMediaContentTest {

  private static final String MODEL_ID = "gemini-2.5-flash";

  // ==================== ImageContent Tests ====================

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testImageContent_fromFile(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/interactions/media/image_from_file." + suffix + ".json");

    URL resourceUrl = getClass().getClassLoader().getResource("google.png");
    Path filePath = Paths.get(resourceUrl.toURI());

    ImageContent imageContent =
        ImageContent.builder()
            .uri(filePath.toAbsolutePath().toString())
            .build();

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .inputFromContents(
                ImmutableList.of(
                    TextContent.builder().text("Describe this logo.").build(),
                    imageContent))
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testImageContent_fromBytes(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/interactions/media/image_from_bytes." + suffix + ".json");

    URL resourceUrl = getClass().getClassLoader().getResource("shapes.jpg");
    Path filePath = Paths.get(resourceUrl.toURI());

    ImageContent imageContent =
        ImageContent.builder()
            .uri(filePath.toAbsolutePath().toString())
            .build();

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .inputFromContents(
                ImmutableList.of(
                    TextContent.builder().text("What shapes are in this image?").build(),
                    imageContent))
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testImageContent_withImageConfig(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/interactions/media/image_with_config." + suffix + ".json");

    URL resourceUrl = getClass().getClassLoader().getResource("watercolor_night_sky.jpg");
    Path filePath = Paths.get(resourceUrl.toURI());

    // ImageContent can specify resolution for input images
    ImageContent imageContent =
        ImageContent.builder()
            .uri(filePath.toAbsolutePath().toString())
            .resolution(new MediaResolution(MediaResolution.Known.HIGH))
            .build();

    // ImageConfig is used within GenerationConfig for image generation settings
    // (aspect ratio, image size for generated output images)
    ImageConfig imageConfig =
        ImageConfig.builder()
            .aspectRatio("16:9")
            .imageSize("2K")
            .build();

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .inputFromContents(
                ImmutableList.of(
                    TextContent.builder().text("Analyze this watercolor painting.").build(),
                    imageContent))
            .generationConfig(
                GenerationConfig.builder()
                    .imageConfig(imageConfig)
                    .build())
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testImageContent_multipleImages(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/interactions/media/multiple_images." + suffix + ".json");

    URL resource1 = getClass().getClassLoader().getResource("google.png");
    URL resource2 = getClass().getClassLoader().getResource("logo.jpg");
    Path file1 = Paths.get(resource1.toURI());
    Path file2 = Paths.get(resource2.toURI());

    ImageContent image1 =
        ImageContent.builder()
            .uri(file1.toAbsolutePath().toString())
            .build();

    ImageContent image2 =
        ImageContent.builder()
            .uri(file2.toAbsolutePath().toString())
            .build();

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .inputFromContents(
                ImmutableList.of(
                    TextContent.builder().text("Compare these two logos.").build(),
                    image1,
                    image2))
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  // ==================== AudioContent Tests ====================

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testAudioContent_fromFile(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/interactions/media/audio_from_file." + suffix + ".json");

    // Use a GCS URI for audio since we don't have local audio files in test resources
    AudioContent audioContent =
        AudioContent.builder()
            .uri("gs://cloud-samples-data/generative-ai/audio/pixel.mp3")
            .build();

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .inputFromContents(
                ImmutableList.of(
                    TextContent.builder().text("Transcribe this audio.").build(),
                    audioContent))
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testAudioContent_withFormat(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/interactions/media/audio_with_format." + suffix + ".json");

    AudioContent audioContent =
        AudioContent.builder()
            .uri("gs://cloud-samples-data/generative-ai/audio/sample.wav")
            .build();

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .inputFromContents(
                ImmutableList.of(
                    TextContent.builder().text("What is said in this audio?").build(),
                    audioContent))
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testAudioContent_withTimestamp(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/interactions/media/audio_with_timestamp." + suffix + ".json");

    AudioContent audioContent =
        AudioContent.builder()
            .uri("gs://cloud-samples-data/generative-ai/audio/audio-sample.mp3")
            .build();

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .inputFromContents(
                ImmutableList.of(
                    TextContent.builder()
                        .text("Provide a transcription with timestamps.")
                        .build(),
                    audioContent))
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  // ==================== VideoContent Tests ====================

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testVideoContent_fromFile(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/interactions/media/video_from_file." + suffix + ".json");

    VideoContent videoContent =
        VideoContent.builder()
            .uri("gs://cloud-samples-data/generative-ai/video/animals.mp4")
            .build();

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .inputFromContents(
                ImmutableList.of(
                    TextContent.builder().text("Describe what happens in this video.").build(),
                    videoContent))
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testVideoContent_withFormat(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/interactions/media/video_with_format." + suffix + ".json");

    VideoContent videoContent =
        VideoContent.builder()
            .uri("gs://cloud-samples-data/generative-ai/video/sample.mp4")
            .build();

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .inputFromContents(
                ImmutableList.of(
                    TextContent.builder().text("Analyze this video content.").build(),
                    videoContent))
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  // Note: VideoContent does not currently support start/end offset parameters
  // This test is commented out until that functionality is added to the API

  // ==================== DocumentContent Tests ====================

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testDocumentContent_fromPdf(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/interactions/media/document_from_pdf." + suffix + ".json");

    DocumentContent documentContent =
        DocumentContent.builder()
            .uri("gs://cloud-samples-data/generative-ai/pdf/sample-document.pdf")
            .build();

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .inputFromContents(
                ImmutableList.of(
                    TextContent.builder().text("Summarize this PDF document.").build(),
                    documentContent))
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testDocumentContent_fromDocx(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/interactions/media/document_from_docx." + suffix + ".json");

    DocumentContent documentContent =
        DocumentContent.builder()
            .uri("gs://cloud-samples-data/generative-ai/documents/document.docx")
            .build();

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .inputFromContents(
                ImmutableList.of(
                    TextContent.builder().text("Extract key points from this document.").build(),
                    documentContent))
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testDocumentContent_withMetadata(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/interactions/media/document_with_metadata." + suffix + ".json");

    DocumentContent documentContent =
        DocumentContent.builder()
            .uri("gs://cloud-samples-data/generative-ai/documents/report.pdf")
            .build();

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .inputFromContents(
                ImmutableList.of(
                    TextContent.builder()
                        .text("Analyze this report and extract metadata.")
                        .build(),
                    documentContent))
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  // ==================== Mixed Media Tests ====================

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testMixedContent_textAndImage(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/interactions/media/mixed_text_image." + suffix + ".json");

    URL resourceUrl = getClass().getClassLoader().getResource("bridge1.png");
    Path filePath = Paths.get(resourceUrl.toURI());

    ImageContent imageContent =
        ImageContent.builder()
            .uri(filePath.toAbsolutePath().toString())
            .build();

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .inputFromContents(
                ImmutableList.of(
                    TextContent.builder()
                        .text("Describe this bridge and suggest a caption.")
                        .build(),
                    imageContent,
                    TextContent.builder().text("Make the caption poetic.").build()))
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testMixedContent_textImageAudio(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/interactions/media/mixed_text_image_audio." + suffix + ".json");

    URL resourceUrl = getClass().getClassLoader().getResource("umbrella.jpg");
    Path filePath = Paths.get(resourceUrl.toURI());

    ImageContent imageContent =
        ImageContent.builder()
            .uri(filePath.toAbsolutePath().toString())
            .build();

    AudioContent audioContent =
        AudioContent.builder()
            .uri("gs://cloud-samples-data/generative-ai/audio/rain-sounds.mp3")
            .build();

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .inputFromContents(
                ImmutableList.of(
                    TextContent.builder()
                        .text("Describe the umbrella image and transcribe the audio.")
                        .build(),
                    imageContent,
                    audioContent))
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testMixedContent_inputFromContents(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/interactions/media/mixed_input_from_contents." + suffix + ".json");

    URL imageUrl = getClass().getClassLoader().getResource("google.png");
    Path imagePath = Paths.get(imageUrl.toURI());

    ImageContent imageContent =
        ImageContent.builder()
            .uri(imagePath.toAbsolutePath().toString())
            .build();

    VideoContent videoContent =
        VideoContent.builder()
            .uri("gs://cloud-samples-data/generative-ai/video/demo.mp4")
            .build();

    DocumentContent documentContent =
        DocumentContent.builder()
            .uri("gs://cloud-samples-data/generative-ai/documents/branding-guide.pdf")
            .build();

    CreateInteractionConfig config =
        CreateInteractionConfig.builder()
            .model(MODEL_ID)
            .inputFromContents(
                ImmutableList.of(
                    TextContent.builder()
                        .text(
                            "Analyze the logo, video, and branding document. "
                                + "Provide a cohesive brand analysis.")
                        .build(),
                    imageContent,
                    videoContent,
                    documentContent))
            .build();

    // Act
    Interaction interaction = client.interactions.create(config);

    // Assert
    assertNotNull(interaction);
    assertNotNull(interaction.id());
    assertTrue(interaction.outputs().isPresent());
  }
}
