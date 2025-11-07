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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.genai.types.ComputeTokensResponse;
import com.google.genai.types.Content;
import com.google.genai.types.ControlReferenceConfig;
import com.google.genai.types.ControlReferenceImage;
import com.google.genai.types.CountTokensResponse;
import com.google.genai.types.EditImageConfig;
import com.google.genai.types.EditImageResponse;
import com.google.genai.types.EditMode;
import com.google.genai.types.EmbedContentConfig;
import com.google.genai.types.EmbedContentResponse;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.Image;
import com.google.genai.types.ListModelsConfig;
import com.google.genai.types.MaskReferenceConfig;
import com.google.genai.types.MaskReferenceImage;
import com.google.genai.types.Model;
import com.google.genai.types.Part;
import com.google.genai.types.RawReferenceImage;
import com.google.genai.types.StyleReferenceConfig;
import com.google.genai.types.StyleReferenceImage;
import com.google.genai.types.SubjectReferenceConfig;
import com.google.genai.types.SubjectReferenceImage;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@EnabledIfEnvironmentVariable(
    named = "GOOGLE_GENAI_REPLAYS_DIRECTORY",
    matches = ".*genai/replays.*")
@ExtendWith(EnvironmentVariablesMockingExtension.class)
public class ModelsTest {

  private static final String GEMINI_MODEL_NAME = "gemini-1.5-flash";
  private static final String EMBEDDING_MODEL_NAME = "text-embedding-004";
  private static final String IMAGEN_CAPABILITY_MODEL_NAME = "imagen-3.0-capability-001";
  private static final String GEMINI_IMAGE_MODALITY_MODEL_NAME =
      "gemini-2.0-flash-preview-image-generation";

  /** Creates a raw reference image for edit image tests. */
  private RawReferenceImage createRawReferenceImage() throws Exception {
    URL resourceUrl = getClass().getClassLoader().getResource("google.png");
    Path filePath = Paths.get(resourceUrl.toURI());
    Image image = Image.fromFile(filePath.toAbsolutePath().toString());
    return RawReferenceImage.builder().referenceImage(image).referenceId(1).build();
  }

  /** Creates a mask reference image for edit image tests. */
  private MaskReferenceImage createMaskReferenceImage() {
    return MaskReferenceImage.builder()
        .referenceId(2)
        .config(MaskReferenceConfig.builder().maskMode("MASK_MODE_BACKGROUND").maskDilation(0.06f))
        .build();
  }

  /** Creates a control reference image for edit image tests. */
  private ControlReferenceImage createControlReferenceImage() throws Exception {
    URL resourceUrl = getClass().getClassLoader().getResource("checkerboard.png");
    Path filePath = Paths.get(resourceUrl.toURI());
    Image image = Image.fromFile(filePath.toAbsolutePath().toString());
    return ControlReferenceImage.builder()
        .referenceId(2)
        .referenceImage(image)
        .config(
            ControlReferenceConfig.builder()
                .controlType("CONTROL_TYPE_SCRIBBLE")
                .enableControlImageComputation(false))
        .build();
  }

  /** Creates a subject reference image for edit image tests. */
  private SubjectReferenceImage createSubjectReferenceImage() throws Exception {
    URL resourceUrl = getClass().getClassLoader().getResource("google.png");
    Path filePath = Paths.get(resourceUrl.toURI());
    Image image = Image.fromFile(filePath.toAbsolutePath().toString());
    return SubjectReferenceImage.builder()
        .referenceId(1)
        .referenceImage(image)
        .config(
            SubjectReferenceConfig.builder()
                .subjectType("SUBJECT_TYPE_PRODUCT")
                .subjectDescription("A product logo that is a multi-colored letter G"))
        .build();
  }

  /** Creates a style reference image for edit image tests. */
  private StyleReferenceImage createStyleReferenceImage() throws Exception {
    URL resourceUrl = getClass().getClassLoader().getResource("google.png");
    Path filePath = Paths.get(resourceUrl.toURI());
    Image image = Image.fromFile(filePath.toAbsolutePath().toString());
    return StyleReferenceImage.builder()
        .referenceId(1)
        .referenceImage(image)
        .config(StyleReferenceConfig.builder().styleDescription("glowing style"))
        .build();
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testGenerateContent_withContent(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/models/generate_content/test_sync." + suffix + ".json");

    // Act
    GenerateContentResponse response =
        client.models.generateContent(
            "gemini-2.5-flash",
            Content.fromParts(Part.fromText("Tell me a story in 300 words.")),
            null);

    // Assert
    assertNotNull(response.text());
    assertNotNull(response.sdkHttpResponse().get().headers());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testGenerateContentStream_withText(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/models/generate_content/test_sync_stream." + suffix + ".json");

    // Act
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .httpOptions(HttpOptions.builder().headers(ImmutableMap.of("test", "headers")))
            .build();
    ResponseStream<GenerateContentResponse> responseStream =
        client.models.generateContentStream(
            "gemini-2.5-flash", "Tell me a story in 300 words.", config);

    // Assert
    int chunks = 0;
    for (GenerateContentResponse response : responseStream) {
      chunks++;
      assertNotNull(response.text());
      assertNotNull(response.sdkHttpResponse().get().headers());
    }
    assertTrue(chunks > 2);
    assertTrue(responseStream.isConsumed());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testGenerateContentStream_withContentAndConfig(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/models/generate_content/test_simple_shared_generation_config_stream."
                + suffix
                + ".json");

    // Act
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .maxOutputTokens(400)
            .topK(2f)
            .temperature(0.5f)
            .topP(0.5f)
            .responseMimeType("application/json")
            .stopSequences("\n")
            .seed(42)
            .build();
    ResponseStream<GenerateContentResponse> responseStream =
        client.models.generateContentStream(
            "gemini-2.0-flash-001",
            Content.fromParts(Part.fromText("tell me a story in 300 words")),
            config);

    // Assert
    int chunks = 0;
    for (GenerateContentResponse response : responseStream) {
      chunks++;
      assertNotNull(response.text());
      assertNotNull(response.sdkHttpResponse().get().headers());
    }
    assertTrue(chunks >= 1);
    assertTrue(responseStream.isConsumed());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testGenerateContentStream_withImageModality(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/models/generate_content/test_sync_stream_with_non_text_modality."
                + suffix
                + ".json");

    // Act
    GenerateContentConfig config =
        GenerateContentConfig.builder().responseModalities("IMAGE", "TEXT").build();
    ResponseStream<GenerateContentResponse> responseStream =
        client.models.generateContentStream(
            GEMINI_IMAGE_MODALITY_MODEL_NAME,
            Content.fromParts(
                Part.fromText(
                    "Generate an image of the Eiffel tower with fireworks in the background.")),
            config);

    // Assert
    int chunks = 0;
    for (GenerateContentResponse response : responseStream) {
      chunks++;
      assertNotNull(response.sdkHttpResponse().get().headers());
    }
    assertTrue(chunks > 2);
    assertTrue(responseStream.isConsumed());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testEmbedContent_withText(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/models/embed_content/test_single_text." + suffix + ".json");

    // Act
    EmbedContentResponse response =
        client.models.embedContent(EMBEDDING_MODEL_NAME, "What is your name?", null);

    // Assert
    assertTrue(response.embeddings().isPresent());
    assertEquals(1, response.embeddings().get().size());
    assertNotNull(response.sdkHttpResponse().get().headers().get());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testEmbedContent_withListOfTexts(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/models/embed_content/test_multi_texts_with_config." + suffix + ".json");

    // Act
    EmbedContentConfig config =
        EmbedContentConfig.builder()
            .outputDimensionality(10)
            .title("test_title")
            .taskType("RETRIEVAL_DOCUMENT")
            .httpOptions(HttpOptions.builder().headers(ImmutableMap.of("test", "headers")))
            .build();
    EmbedContentResponse response =
        client.models.embedContent(
            EMBEDDING_MODEL_NAME, ImmutableList.of("What is your name?", "I am a model."), config);

    // Assert
    assertTrue(response.embeddings().isPresent());
    assertEquals(2, response.embeddings().get().size());
    assertNotNull(response.sdkHttpResponse().get().headers().get());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testCountTokens_withText(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/models/count_tokens/test_count_tokens." + suffix + ".json");

    // Act
    CountTokensResponse response =
        client.models.countTokens("gemini-2.5-flash", "Tell me a story in 300 words.", null);

    // Assert
    assertTrue(response.totalTokens().isPresent());
    assertNotNull(response.sdkHttpResponse().get().headers().get());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testComputeTokens_withText(boolean vertexAI) throws Exception {
    if (!vertexAI) {
      // ComputeTokens is not supported in MLDev.
      return;
    }
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/models/compute_tokens/test_compute_tokens." + suffix + ".json");

    // Act
    ComputeTokensResponse response =
        client.models.computeTokens("gemini-2.5-flash", "Tell me a story in 300 words.", null);

    // Assert
    assertTrue(response.tokensInfo().isPresent());
    assertNotNull(response.sdkHttpResponse().get().headers().get());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testListModels(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/models/list/test_base_models_pager." + suffix + ".json");

    // Act
    Pager<Model> pager =
        client.models.list(ListModelsConfig.builder().pageSize(10).queryBase(true).build());

    // Assert
    assertEquals(10, pager.size());
    assertTrue(pager.size() <= 10);
    for (Model model : pager) {
      assertTrue(model.name().isPresent());
    }
    IndexOutOfBoundsException exception =
        assertThrows(IndexOutOfBoundsException.class, () -> pager.nextPage());
    assertEquals("No more page in the pager.", exception.getMessage());
    assertNotNull(pager.sdkHttpResponse().get().headers().get());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testListModel_filterThrowException(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/models/list/test_base_models_pager." + suffix + ".json");

    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> client.models.list(ListModelsConfig.builder().filter("filter").build()));

    // Assert
    assertEquals("Filter is currently not supported for list models.", exception.getMessage());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testEditImage_withMaskReference(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/models/edit_image/test_edit_mask_inpaint_insert." + suffix + ".json");

    EditImageConfig config =
        EditImageConfig.builder()
            .editMode(EditMode.Known.EDIT_MODE_INPAINT_INSERTION)
            .numberOfImages(1)
            .negativePrompt("human")
            .guidanceScale(15.0f)
            .safetyFilterLevel("BLOCK_MEDIUM_AND_ABOVE")
            .personGeneration("DONT_ALLOW")
            .includeSafetyAttributes(false)
            .includeRaiReason(true)
            .outputMimeType("image/jpeg")
            .outputCompressionQuality(80)
            .baseSteps(32)
            .addWatermark(false)
            .labels(ImmutableMap.of("imagen_label_key", "edit_image"))
            .build();

    // Act
    if (vertexAI) {
      EditImageResponse response =
          client.models.editImage(
              IMAGEN_CAPABILITY_MODEL_NAME,
              "Sunlight and clear weather",
              Arrays.asList(createRawReferenceImage(), createMaskReferenceImage()),
              config);

      // Assert
      assertTrue(response.generatedImages().get().get(0).image().isPresent());
      assertNotNull(response.sdkHttpResponse().get().headers().get());
    } else {
      UnsupportedOperationException exception =
          assertThrows(
              UnsupportedOperationException.class,
              () ->
                  client.models.editImage(
                      IMAGEN_CAPABILITY_MODEL_NAME,
                      "Sunlight and clear weather",
                      Arrays.asList(createRawReferenceImage(), createMaskReferenceImage()),
                      config));
      // Assert
      assertEquals(
          "This method is only supported in the Vertex AI client.", exception.getMessage());
    }
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testEditImage_withControlReference(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/models/edit_image/test_edit_control_user_provided." + suffix + ".json");

    EditImageConfig config =
        EditImageConfig.builder()
            .numberOfImages(1)
            .aspectRatio("9:16")
            .includeRaiReason(true)
            .build();

    // Act
    if (vertexAI) {
      EditImageResponse response =
          client.models.editImage(
              IMAGEN_CAPABILITY_MODEL_NAME,
              "Change the colors aligning with the scribble map [2]",
              Arrays.asList(createRawReferenceImage(), createControlReferenceImage()),
              config);

      // Assert
      assertTrue(response.generatedImages().get().get(0).image().isPresent());
      assertNotNull(response.sdkHttpResponse().get().headers().get());
    } else {
      UnsupportedOperationException exception =
          assertThrows(
              UnsupportedOperationException.class,
              () ->
                  client.models.editImage(
                      IMAGEN_CAPABILITY_MODEL_NAME,
                      "Change the colors aligning with the scribble map [2]",
                      Arrays.asList(createRawReferenceImage(), createControlReferenceImage()),
                      config));
      // Assert
      assertEquals(
          "This method is only supported in the Vertex AI client.", exception.getMessage());
    }
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testEditImage_withSubjectReference(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/models/edit_image/test_edit_subject_image_customization." + suffix + ".json");

    EditImageConfig config =
        EditImageConfig.builder()
            .numberOfImages(1)
            .aspectRatio("9:16")
            .includeRaiReason(true)
            .build();

    // Act
    if (vertexAI) {
      EditImageResponse response =
          client.models.editImage(
              IMAGEN_CAPABILITY_MODEL_NAME,
              "Generate an image containing a mug with the product logo [1] visible on the side of"
                  + " the mug.",
              Arrays.asList(createSubjectReferenceImage()),
              config);

      // Assert
      assertTrue(response.generatedImages().get().get(0).image().isPresent());
      assertNotNull(response.sdkHttpResponse().get().headers().get());
    } else {
      UnsupportedOperationException exception =
          assertThrows(
              UnsupportedOperationException.class,
              () ->
                  client.models.editImage(
                      IMAGEN_CAPABILITY_MODEL_NAME,
                      "Generate an image containing a mug with the product logo [1] visible on the"
                          + " side of the mug.",
                      Arrays.asList(createSubjectReferenceImage()),
                      config));
      // Assert
      assertEquals(
          "This method is only supported in the Vertex AI client.", exception.getMessage());
    }
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testEditImage_withStyleTransfer(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/models/edit_image/test_edit_style_reference_image_customization."
                + suffix
                + ".json");

    EditImageConfig config =
        EditImageConfig.builder()
            .numberOfImages(1)
            .aspectRatio("9:16")
            .includeRaiReason(true)
            .build();

    // Act
    if (vertexAI) {
      EditImageResponse response =
          client.models.editImage(
              IMAGEN_CAPABILITY_MODEL_NAME,
              "Generate an image in glowing style [1] based on the following caption: A church in"
                  + " the mountain.",
              Arrays.asList(createStyleReferenceImage()),
              config);

      // Assert
      assertTrue(response.generatedImages().get().get(0).image().isPresent());
      assertNotNull(response.sdkHttpResponse().get().headers().get());
    } else {
      UnsupportedOperationException exception =
          assertThrows(
              UnsupportedOperationException.class,
              () ->
                  client.models.editImage(
                      IMAGEN_CAPABILITY_MODEL_NAME,
                      "Generate an image in glowing style [1] based on the following caption: A"
                          + " church in the mountain.",
                      Arrays.asList(createStyleReferenceImage()),
                      config));
      // Assert
      assertEquals(
          "This method is only supported in the Vertex AI client.", exception.getMessage());
    }
  }
}
