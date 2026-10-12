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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.genai.types.AutomaticFunctionCallingConfig;
import com.google.genai.types.Candidate;
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
import com.google.genai.types.FinishReason;
import com.google.genai.types.FunctionDeclaration;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.Image;
import com.google.genai.types.ListModelsConfig;
import com.google.genai.types.MaskReferenceConfig;
import com.google.genai.types.MaskReferenceImage;
import com.google.genai.types.McpServer;
import com.google.genai.types.Model;
import com.google.genai.types.Part;
import com.google.genai.types.RawReferenceImage;
import com.google.genai.types.StreamableHttpTransport;
import com.google.genai.types.StyleReferenceConfig;
import com.google.genai.types.StyleReferenceImage;
import com.google.genai.types.SubjectReferenceConfig;
import com.google.genai.types.SubjectReferenceImage;
import com.google.genai.types.Tool;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

@EnabledIfEnvironmentVariable(
    named = "GOOGLE_GENAI_REPLAYS_DIRECTORY",
    matches = ".*genai/replays.*")
@ExtendWith(EnvironmentVariablesMockingExtension.class)
public class ModelsTest {

  private static final String GEMINI_MODEL_NAME = "gemini-2.5-flash";
  private static final String EMBEDDING_MODEL_NAME = "gemini-embedding-001";
  private static final String IMAGEN_CAPABILITY_MODEL_NAME = "imagen-3.0-capability-001";
  private static final String GEMINI_IMAGE_MODALITY_MODEL_NAME =
      "gemini-2.0-flash-preview-image-generation";
  private static final String CONTINUATION_REPLAYS =
      "tests/models/generate_content_continuation_token/";
  private static final String LONG_PROMPT =
      "Write an exhaustive, multi-chapter textbook on compiler design that is around 40,000 tokens"
          + " long.";
  private static final byte[] CONTINUATION_TOKEN = "token".getBytes(StandardCharsets.UTF_8);

  /**
   * The model in the continuation recordings. It has no public name yet, so the recordings name it
   * this way.
   */
  private static String longDecodingModel(boolean vertexAI) {
    return vertexAI ? "test-model1" : "test-model2";
  }

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
            GEMINI_MODEL_NAME,
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
            GEMINI_MODEL_NAME, "Tell me a story in 300 words.", config);

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
            .maxOutputTokens(1000)
            .topK(2f)
            .temperature(0.5f)
            .topP(0.5f)
            .responseMimeType("application/json")
            .stopSequences("\n")
            .seed(42)
            .build();
    ResponseStream<GenerateContentResponse> responseStream =
        client.models.generateContentStream(
            GEMINI_MODEL_NAME,
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
        client.models.countTokens(GEMINI_MODEL_NAME, "Tell me a story in 300 words.", null);

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
        client.models.computeTokens(GEMINI_MODEL_NAME, "Tell me a story in 300 words.", null);

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
          "This method is only supported in Gemini Enterprise Agent Platform mode, not in Gemini"
              + " Developer API mode.",
          exception.getMessage());
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
          "This method is only supported in Gemini Enterprise Agent Platform mode, not in Gemini"
              + " Developer API mode.",
          exception.getMessage());
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
          "This method is only supported in Gemini Enterprise Agent Platform mode, not in Gemini"
              + " Developer API mode.",
          exception.getMessage());
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
          "This method is only supported in Gemini Enterprise Agent Platform mode, not in Gemini"
              + " Developer API mode.",
          exception.getMessage());
    }
  }

  @ParameterizedTest
  @ValueSource(booleans = {false})
  public void testGenerateContent_withServerSideMcp(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/models/generate_content_tools/test_server_side_mcp_only." + suffix + ".json");

    // Act
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .tools(
                Tool.builder()
                    .mcpServers(
                        McpServer.builder()
                            .name("get_weather")
                            .streamableHttpTransport(
                                StreamableHttpTransport.builder()
                                    .url("https://gemini-api-demos.uc.r.appspot.com/mcp")
                                    .headers(
                                        ImmutableMap.of("AUTHORIZATION", "Bearer github_pat_XXXX"))
                                    .build())
                            .build())
                    .build())
            .build();
    GenerateContentResponse response =
        client.models.generateContent(
            "gemini-2.5-pro",
            Content.fromParts(
                Part.fromText("What is the weather like in New York (NY) on 02/02/2026?")),
            config);

    // Assert
    assertNotNull(response.text());
    assertNotNull(response.sdkHttpResponse().get().headers());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false})
  public void testGenerateContentStream_withServerSideMcp(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/models/generate_content_tools/test_server_side_mcp_only_stream."
                + suffix
                + ".json");

    // Act
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .tools(
                Tool.builder()
                    .mcpServers(
                        McpServer.builder()
                            .name("get_weather")
                            .streamableHttpTransport(
                                StreamableHttpTransport.builder()
                                    .url("https://gemini-api-demos.uc.r.appspot.com/mcp")
                                    .headers(
                                        ImmutableMap.of("AUTHORIZATION", "Bearer github_pat_XXXX"))
                                    .build())
                            .build())
                    .build())
            .build();
    ResponseStream<GenerateContentResponse> responseStream =
        client.models.generateContentStream(
            "gemini-2.5-pro", "What is the weather like in New York (NY) on 02/02/2026?", config);

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
  public void testGenerateContent_continuesByDefault(boolean vertexAI) throws Exception {
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            CONTINUATION_REPLAYS
                + "test_gc_without_afc_continuation_token_opt_in."
                + suffix
                + ".json");

    GenerateContentResponse response =
        client.models.generateContent(
            longDecodingModel(vertexAI), LONG_PROMPT, GenerateContentConfig.builder().build());

    // The recording holds two requests, and the replay fails unless the second is the first one
    // with the continuation token added.
    assertEquals(FinishReason.Known.STOP, response.finishReason().knownEnum());
    assertFalse(response.candidates().get().get(0).continuationToken().isPresent());
    assertNotNull(response.text());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testGenerateContent_withoutAutomaticContinuation(boolean vertexAI) throws Exception {
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            CONTINUATION_REPLAYS + "test_gc_without_afc_continuation_token." + suffix + ".json");

    GenerateContentResponse response =
        client.models.generateContent(
            longDecodingModel(vertexAI),
            LONG_PROMPT,
            GenerateContentConfig.builder().automaticContinuation(false).build());

    // The recording holds one request, so a continuation request would fail the replay.
    assertEquals(FinishReason.Known.CONTINUATION, response.finishReason().knownEnum());
    assertTrue(response.candidates().get().get(0).continuationToken().isPresent());
  }

  @Test
  public void testGenerateContentStream_automaticContinuationResendsTheRequestWithTheToken()
      throws Exception {
    ApiClient apiClient = Mockito.mock(ApiClient.class);
    when(apiClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(
            streamResponse(
                textResponse("Hello ", FinishReason.Known.CONTINUATION, CONTINUATION_TOKEN)),
            streamResponse(textResponse("world", FinishReason.Known.STOP, null)));
    Models models = new Models(apiClient);

    List<String> texts = new ArrayList<>();
    try (ResponseStream<GenerateContentResponse> stream =
        models.generateContentStream(
            GEMINI_MODEL_NAME,
            "Write a long story.",
            GenerateContentConfig.builder().automaticContinuation(true).build())) {
      for (GenerateContentResponse chunk : stream) {
        texts.add(chunk.text());
      }
    }

    assertEquals(ImmutableList.of("Hello ", "world"), texts);
    List<String> bodies = sentBodies(apiClient, 2);
    ObjectNode second = (ObjectNode) JsonSerializable.stringToJsonNode(bodies.get(1));
    assertEquals(
        Base64.getEncoder().encodeToString(CONTINUATION_TOKEN),
        second.remove("continuationToken").asText());
    // Apart from the token, the second request is the first one: no earlier output is appended.
    assertEquals(JsonSerializable.stringToJsonNode(bodies.get(0)), second);
    assertFalse(bodies.get(0).contains("automaticContinuation"));
  }

  @Test
  public void testGenerateContent_continuesTheAnswerThatFollowsAFunctionCall() throws Exception {
    Content functionCall =
        Content.builder()
            .role("model")
            .parts(Part.fromFunctionCall("describeTopic", ImmutableMap.of("topic", "compilers")))
            .build();
    ApiClient apiClient = Mockito.mock(ApiClient.class);
    when(apiClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(
            jsonResponse(
                GenerateContentResponse.builder()
                    .candidates(
                        Candidate.builder()
                            .content(functionCall)
                            .finishReason(FinishReason.Known.STOP))
                    .build()),
            jsonResponse(
                textResponse("Compilers ", FinishReason.Known.CONTINUATION, CONTINUATION_TOKEN)),
            jsonResponse(textResponse("translate code.", FinishReason.Known.STOP, null)));
    Models models = new Models(apiClient);
    Method describeTopic = ModelsTest.class.getDeclaredMethod("describeTopic", String.class);

    GenerateContentResponse response =
        models.generateContent(
            GEMINI_MODEL_NAME,
            "Describe compilers.",
            GenerateContentConfig.builder().tools(Tool.builder().functions(describeTopic)).build());

    assertEquals("Compilers translate code.", response.text());
    List<String> bodies = sentBodies(apiClient, 3);
    ObjectNode third = (ObjectNode) JsonSerializable.stringToJsonNode(bodies.get(2));
    assertTrue(third.remove("continuationToken") != null);
    // The answer is completed before anything else: its follow-up request is the request that
    // sent the function response, with the token added.
    assertEquals(JsonSerializable.stringToJsonNode(bodies.get(1)), third);
  }

  @Test
  public void testGenerateContent_continuesAFunctionCallBeforeRunningTheFunction()
      throws Exception {
    ApiClient apiClient = Mockito.mock(ApiClient.class);
    when(apiClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(
            jsonResponse(textResponse("", FinishReason.Known.CONTINUATION, CONTINUATION_TOKEN)),
            jsonResponse(
                GenerateContentResponse.builder()
                    .candidates(
                        Candidate.builder()
                            .content(
                                Content.builder()
                                    .role("model")
                                    .parts(
                                        Part.fromFunctionCall(
                                            "describeTopic",
                                            ImmutableMap.of("topic", "compilers"))))
                            .finishReason(FinishReason.Known.STOP))
                    .build()),
            jsonResponse(textResponse("Compilers translate code.", FinishReason.Known.STOP, null)));
    Models models = new Models(apiClient);
    Method describeTopic = ModelsTest.class.getDeclaredMethod("describeTopic", String.class);

    GenerateContentResponse response =
        models.generateContent(
            GEMINI_MODEL_NAME,
            "Describe compilers.",
            GenerateContentConfig.builder().tools(Tool.builder().functions(describeTopic)).build());

    assertEquals("Compilers translate code.", response.text());
    List<String> bodies = sentBodies(apiClient, 3);
    assertTrue(JsonSerializable.stringToJsonNode(bodies.get(1)).has("continuationToken"));
    // The token belongs to the response it continued, so the request carrying the function
    // response goes out without it.
    assertFalse(JsonSerializable.stringToJsonNode(bodies.get(2)).has("continuationToken"));
    assertTrue(bodies.get(2).contains("functionResponse"));
  }

  @Test
  public void testGenerateContent_continuesWithAutomaticFunctionCallingDisabled() {
    ApiClient apiClient = Mockito.mock(ApiClient.class);
    when(apiClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(
            jsonResponse(
                textResponse("Hello ", FinishReason.Known.CONTINUATION, CONTINUATION_TOKEN)),
            jsonResponse(textResponse("world", FinishReason.Known.STOP, null)));
    Models models = new Models(apiClient);

    GenerateContentResponse response =
        models.generateContent(
            GEMINI_MODEL_NAME,
            "Write a long story.",
            GenerateContentConfig.builder()
                .automaticFunctionCalling(AutomaticFunctionCallingConfig.builder().disable(true))
                .build());

    assertEquals("Hello world", response.text());
    sentBodies(apiClient, 2);
  }

  @Test
  public void testGenerateContent_continuesWithToolsItCannotCall() {
    ApiClient apiClient = Mockito.mock(ApiClient.class);
    when(apiClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(
            jsonResponse(
                textResponse("Hello ", FinishReason.Known.CONTINUATION, CONTINUATION_TOKEN)),
            jsonResponse(textResponse("world", FinishReason.Known.STOP, null)));
    Models models = new Models(apiClient);

    GenerateContentResponse response =
        models.generateContent(
            GEMINI_MODEL_NAME,
            "Write a long story.",
            GenerateContentConfig.builder()
                .tools(
                    Tool.builder()
                        .functionDeclarations(
                            FunctionDeclaration.builder().name("manualFunction").build()))
                .build());

    assertEquals("Hello world", response.text());
    sentBodies(apiClient, 2);
  }

  @Test
  public void testGenerateContentStream_continuesByDefault() {
    ApiClient apiClient = Mockito.mock(ApiClient.class);
    when(apiClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(
            streamResponse(
                textResponse("Hello ", FinishReason.Known.CONTINUATION, CONTINUATION_TOKEN)),
            streamResponse(textResponse("world", FinishReason.Known.STOP, null)));
    Models models = new Models(apiClient);

    List<String> texts = new ArrayList<>();
    try (ResponseStream<GenerateContentResponse> stream =
        models.generateContentStream(GEMINI_MODEL_NAME, "Write a long story.", null)) {
      for (GenerateContentResponse chunk : stream) {
        texts.add(chunk.text());
      }
    }

    assertEquals(ImmutableList.of("Hello ", "world"), texts);
    sentBodies(apiClient, 2);
  }

  @Test
  public void testGenerateContentStream_doesNotContinueWhenAutomaticContinuationIsFalse() {
    ApiClient apiClient = Mockito.mock(ApiClient.class);
    when(apiClient.request(anyString(), anyString(), anyString(), any()))
        .thenReturn(
            streamResponse(
                textResponse("Hello ", FinishReason.Known.CONTINUATION, CONTINUATION_TOKEN)));
    Models models = new Models(apiClient);

    List<String> texts = new ArrayList<>();
    try (ResponseStream<GenerateContentResponse> stream =
        models.generateContentStream(
            GEMINI_MODEL_NAME,
            "Write a long story.",
            GenerateContentConfig.builder().automaticContinuation(false).build())) {
      for (GenerateContentResponse chunk : stream) {
        texts.add(chunk.text());
      }
    }

    assertEquals(ImmutableList.of("Hello "), texts);
    sentBodies(apiClient, 1);
  }

  public static String describeTopic(String topic) {
    return topic + " translate code";
  }

  private static GenerateContentResponse textResponse(
      String text, FinishReason.Known finishReason, byte[] token) {
    Candidate.Builder candidate =
        Candidate.builder()
            .content(Content.builder().role("model").parts(Part.fromText(text)))
            .finishReason(finishReason);
    if (token != null) {
      candidate.continuationToken(token);
    }
    return GenerateContentResponse.builder().candidates(candidate.build()).build();
  }

  private static ApiResponse jsonResponse(GenerateContentResponse response) {
    return new FakeApiResponse(
        Headers.of(), ResponseBody.create(response.toJson(), MediaType.get("application/json")));
  }

  private static ApiResponse streamResponse(GenerateContentResponse... chunks) {
    StringBuilder sse = new StringBuilder();
    for (GenerateContentResponse chunk : chunks) {
      sse.append("data: ").append(chunk.toJson()).append("\n\n");
    }
    return new FakeApiResponse(
        Headers.of(), ResponseBody.create(sse.toString(), MediaType.get("text/event-stream")));
  }

  /** The bodies of the requests sent through {@code apiClient}, checking how many there were. */
  private static List<String> sentBodies(ApiClient apiClient, int expectedRequests) {
    ArgumentCaptor<String> bodies = ArgumentCaptor.forClass(String.class);
    verify(apiClient, times(expectedRequests))
        .request(anyString(), anyString(), bodies.capture(), any());
    return bodies.getAllValues();
  }
}
