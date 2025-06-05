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
import com.google.genai.types.ComputeTokensResponse;
import com.google.genai.types.Content;
import com.google.genai.types.ControlReferenceConfig;
import com.google.genai.types.ControlReferenceImage;
import com.google.genai.types.ControlReferenceType;
import com.google.genai.types.CountTokensResponse;
import com.google.genai.types.EditImageConfig;
import com.google.genai.types.EditImageResponse;
import com.google.genai.types.EditMode;
import com.google.genai.types.EmbedContentResponse;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Image;
import com.google.genai.types.ListModelsConfig;
import com.google.genai.types.MaskReferenceConfig;
import com.google.genai.types.MaskReferenceImage;
import com.google.genai.types.MaskReferenceMode;
import com.google.genai.types.Model;
import com.google.genai.types.Part;
import com.google.genai.types.RawReferenceImage;
import com.google.genai.types.ReferenceImage;
import com.google.genai.types.StyleReferenceConfig;
import com.google.genai.types.StyleReferenceImage;
import com.google.genai.types.SubjectReferenceConfig;
import com.google.genai.types.SubjectReferenceImage;
import com.google.genai.types.SubjectReferenceType;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ModelsTest {
  private Client createClient(boolean vertexAI, String replayId) {
    // Use the API mode until the replay mode is complete.
    DebugConfig debugConfig =
        new DebugConfig("api", "", System.getenv("GOOGLE_GENAI_REPLAYS_DIRECTORY"));
    Client client = Client.builder().debugConfig(debugConfig).vertexAI(vertexAI).build();
    if (client.clientMode().equals("replay") || client.clientMode().equals("auto")) {
      client.setReplayId(replayId);
    }
    return client;
  }

  private static final String GEMINI_MODEL_NAME = "gemini-2.0-flash-001";
  private static final String EMBEDDING_MODEL_NAME = "text-embedding-004";
  private static final String PROMPT_TEXT = "Tell me a story in 300 words.";
  private static final String PROMPT_TEXT_2 = "What is your name?";
  private static final String PROMPT_TEXT_3 = "What is your age?";

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testGenerateContent_withContent(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        createClient(
            vertexAI, "tests/models/generate_content/test_sync_stream." + suffix + ".json");

    // Act
    GenerateContentResponse response =
        client.models.generateContent(
            GEMINI_MODEL_NAME, Content.fromParts(Part.fromText(PROMPT_TEXT)), null);

    // Assert
    assertNotNull(response.text());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testGenerateContentStream_withText(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        createClient(
            vertexAI, "tests/models/generate_content/test_sync_stream." + suffix + ".json");

    // Act
    ResponseStream<GenerateContentResponse> responseStream =
        client.models.generateContentStream(GEMINI_MODEL_NAME, PROMPT_TEXT, null);

    // Assert
    int chunks = 0;
    for (GenerateContentResponse response : responseStream) {
      chunks++;
      assertNotNull(response.text());
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
        createClient(
            vertexAI, "tests/models/generate_content/test_sync_stream." + suffix + ".json");

    // Act
    ResponseStream<GenerateContentResponse> responseStream =
        client.models.generateContentStream(
            GEMINI_MODEL_NAME,
            Content.fromParts(Part.fromText(PROMPT_TEXT)),
            GenerateContentConfig.builder().maxOutputTokens(300).build());

    // Assert
    int chunks = 0;
    for (GenerateContentResponse response : responseStream) {
      chunks++;
      assertNotNull(response.text());
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
        createClient(vertexAI, "tests/models/embed_content/test_single_text." + suffix + ".json");

    // Act
    EmbedContentResponse response =
        client.models.embedContent(EMBEDDING_MODEL_NAME, PROMPT_TEXT_2, null);

    // Assert
    assertTrue(response.embeddings().isPresent());
    assertEquals(1, response.embeddings().get().size());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testEmbedContent_withListOfTexts(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        createClient(
            vertexAI,
            "tests/models/embed_content/test_multi_texts_with_config." + suffix + ".json");

    // Act
    EmbedContentResponse response =
        client.models.embedContent(
            EMBEDDING_MODEL_NAME, ImmutableList.of(PROMPT_TEXT_2, PROMPT_TEXT_3), null);

    // Assert
    assertTrue(response.embeddings().isPresent());
    assertEquals(2, response.embeddings().get().size());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testCountTokens_withText(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        createClient(vertexAI, "tests/models/count_tokens/test_count_tokens." + suffix + ".json");

    // Act
    CountTokensResponse response =
        client.models.countTokens(GEMINI_MODEL_NAME, PROMPT_TEXT, null);

    // Assert
    assertTrue(response.totalTokens().isPresent());
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
        createClient(
            vertexAI, "tests/models/compute_tokens/test_compute_tokens." + suffix + ".json");

    // Act
    ComputeTokensResponse response =
        client.models.computeTokens(GEMINI_MODEL_NAME, PROMPT_TEXT, null);

    // Assert
    assertTrue(response.tokensInfo().isPresent());
  }

  @ParameterizedTest
  @CsvSource({"false, false", "false, true", "true,  false", "true,  true"})
  public void testListModels(boolean vertexAI, boolean queryBase) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        createClient(vertexAI, "tests/models/list/test_base_models_pager." + suffix + ".json");

    // Act
    Pager<Model> pager =
        client.models.list(ListModelsConfig.builder().pageSize(5).queryBase(queryBase).build());

    // Assert
    assertEquals(5, pager.size());
    assertTrue(pager.size() <= 5);
    for (Model model : pager) {
      assertTrue(model.name().isPresent());
    }
    IndexOutOfBoundsException exception =
        assertThrows(IndexOutOfBoundsException.class, () -> pager.nextPage());
    assertEquals("No more page in the pager.", exception.getMessage());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testListModel_filterThrowException(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client = createClient(vertexAI, "tests/models/get/test_list_model." + suffix + ".json");

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
    if (!vertexAI) {
      // EditImage is not supported in MLDev.
      return;
    }
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        createClient(
            vertexAI, "tests/models/edit_image/test_edit_mask_inpaint_insert." + suffix + ".json");

    // Act
    // Base image created using generateImages with prompt:
    // "An umbrella in the foreground, and a rainy night sky in the background"
    URL resourceUrl = getClass().getClassLoader().getResource("umbrella.jpg");
    Path filePath = Paths.get(resourceUrl.toURI());
    Image image = Image.fromFile(filePath.toAbsolutePath().toString());

    EditImageConfig editImageConfig =
        EditImageConfig.builder()
            .editMode(EditMode.Known.EDIT_MODE_INPAINT_INSERTION)
            .numberOfImages(1)
            .outputMimeType("image/jpeg")
            .build();

    ArrayList<ReferenceImage> referenceImages = new ArrayList<>();
    RawReferenceImage rawReferenceImage =
        RawReferenceImage.builder().referenceImage(image).referenceId(1).build();
    referenceImages.add(rawReferenceImage);

    MaskReferenceImage maskReferenceImage =
        MaskReferenceImage.builder()
            .referenceId(2)
            .config(
                MaskReferenceConfig.builder()
                    .maskMode(MaskReferenceMode.Known.MASK_MODE_BACKGROUND)
                    .maskDilation(0.0f))
            .build();
    referenceImages.add(maskReferenceImage);
    EditImageResponse response =
        client.models.editImage(
            "imagen-3.0-capability-001",
            "Sunlight and clear sky",
            referenceImages,
            editImageConfig);

    // Assert
    assertTrue(response.generatedImages().get().get(0).image().isPresent());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testEditImage_withControlReference(boolean vertexAI) throws Exception {
    if (!vertexAI) {
      // EditImage is not supported in MLDev.
      return;
    }
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        createClient(vertexAI, "tests/models/edit_image/test_edit_control." + suffix + ".json");

    // Act
    // Base image created using generateImages with prompt:
    // "A square, circle, and triangle with a white background"
    URL resourceUrl = getClass().getClassLoader().getResource("shapes.jpg");
    Path filePath = Paths.get(resourceUrl.toURI());
    Image image = Image.fromFile(filePath.toAbsolutePath().toString());

    // Control reference.
    EditImageConfig editImageConfig =
        EditImageConfig.builder().numberOfImages(1).outputMimeType("image/jpeg").build();

    ArrayList<ReferenceImage> referenceImages = new ArrayList<>();

    ControlReferenceImage controlReferenceImage =
        ControlReferenceImage.builder()
            .referenceId(1)
            .referenceImage(image)
            .config(
                ControlReferenceConfig.builder()
                    .controlType(ControlReferenceType.Known.CONTROL_TYPE_SCRIBBLE)
                    .enableControlImageComputation(true))
            .build();
    referenceImages.add(controlReferenceImage);

    EditImageResponse response =
        client.models.editImage(
            "imagen-3.0-capability-001",
            "Change the colors aligning with the scribble map [1].",
            referenceImages,
            editImageConfig);

    // Assert
    assertTrue(response.generatedImages().get().get(0).image().isPresent());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testEditImage_withSubjectReference(boolean vertexAI) throws Exception {
    if (!vertexAI) {
      // EditImage is not supported in MLDev.
      return;
    }
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        createClient(vertexAI, "tests/models/edit_image/test_edit_subject." + suffix + ".json");

    // Act
    // Base image created using generateImages with prompt:
    // "A logo with the letters 'SERN' in a futuristic font with a white background"
    URL resourceUrl = getClass().getClassLoader().getResource("logo.jpg");
    Path filePath = Paths.get(resourceUrl.toURI());
    Image image = Image.fromFile(filePath.toAbsolutePath().toString());

    // Subject reference.
    EditImageConfig editImageConfig =
        EditImageConfig.builder().numberOfImages(1).outputMimeType("image/jpeg").build();

    ArrayList<ReferenceImage> referenceImages = new ArrayList<>();
    SubjectReferenceImage subjectReferenceImage =
        SubjectReferenceImage.builder()
            .referenceImage(image)
            .referenceId(1)
            .config(
                SubjectReferenceConfig.builder()
                    .subjectType(SubjectReferenceType.Known.SUBJECT_TYPE_PRODUCT)
                    .subjectDescription("Product logo"))
            .build();
    referenceImages.add(subjectReferenceImage);

    EditImageResponse response =
        client.models.editImage(
            "imagen-3.0-capability-001",
            "Generate an image containing a mug with the product logo [1] visible on the side of"
                + " the mug.",
            referenceImages,
            editImageConfig);

    // Assert
    assertTrue(response.generatedImages().get().get(0).image().isPresent());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testEditImage_withStyleTransfer(boolean vertexAI) throws Exception {
    if (!vertexAI) {
      // EditImage is not supported in MLDev.
      return;
    }
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        createClient(
            vertexAI, "tests/models/edit_image/test_edit_style_transfer." + suffix + ".json");

    // Act
    // Base image created using generateImages with prompt:
    // "A starry night sky painted with watercolors"
    URL resourceUrl = getClass().getClassLoader().getResource("watercolor_night_sky.jpg");
    Path filePath = Paths.get(resourceUrl.toURI());
    Image image = Image.fromFile(filePath.toAbsolutePath().toString());

    // Style transfer.
    EditImageConfig editImageConfig =
        EditImageConfig.builder().numberOfImages(1).outputMimeType("image/jpeg").build();

    ArrayList<ReferenceImage> referenceImages = new ArrayList<>();
    StyleReferenceImage styleReferenceImage =
        StyleReferenceImage.builder()
            .referenceImage(image)
            .referenceId(1)
            .config(StyleReferenceConfig.builder().styleDescription("Watercolor"))
            .build();
    referenceImages.add(styleReferenceImage);

    EditImageResponse response =
        client.models.editImage(
            "imagen-3.0-capability-001",
            "Generate an image in the style of [1] based on the following caption: A church in the"
                + " mountain.",
            referenceImages,
            editImageConfig);

    // Assert
    assertTrue(response.generatedImages().get().get(0).image().isPresent());
  }
}
