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

import com.google.common.collect.ImmutableMap;
import com.google.genai.types.ComputeTokensResponse;
import com.google.genai.types.Content;
import com.google.genai.types.CountTokensResponse;
import com.google.genai.types.DeleteModelConfig;
import com.google.genai.types.DeleteModelResponse;
import com.google.genai.types.EditImageConfig;
import com.google.genai.types.EditImageResponse;
import com.google.genai.types.EditMode;
import com.google.genai.types.EmbedContentConfig;
import com.google.genai.types.EmbedContentResponse;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.GenerateImagesConfig;
import com.google.genai.types.GenerateImagesResponse;
import com.google.genai.types.GenerateVideosConfig;
import com.google.genai.types.GenerateVideosOperation;
import com.google.genai.types.GetModelConfig;
import com.google.genai.types.GoogleSearch;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.Image;
import com.google.genai.types.ListModelsConfig;
import com.google.genai.types.MaskReferenceConfig;
import com.google.genai.types.MaskReferenceImage;
import com.google.genai.types.McpServer;
import com.google.genai.types.Model;
import com.google.genai.types.Part;
import com.google.genai.types.PersonGeneration;
import com.google.genai.types.RagRetrievalConfig;
import com.google.genai.types.RagRetrievalConfigFilter;
import com.google.genai.types.RawReferenceImage;
import com.google.genai.types.Retrieval;
import com.google.genai.types.SafetyFilterLevel;
import com.google.genai.types.StreamableHttpTransport;
import com.google.genai.types.Tool;
import com.google.genai.types.ToolCodeExecution;
import com.google.genai.types.UpdateModelConfig;
import com.google.genai.types.UpscaleImageConfig;
import com.google.genai.types.UpscaleImageResponse;
import com.google.genai.types.VertexAISearch;
import com.google.genai.types.VertexRagStore;
import com.google.genai.types.VertexRagStoreRagResource;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@EnabledIfEnvironmentVariable(
    named = "GOOGLE_GENAI_REPLAYS_DIRECTORY",
    matches = ".*genai/replays.*")
@ExtendWith(EnvironmentVariablesMockingExtension.class)
public class AsyncModelsTest {
  private static final String MODEL_ID = "gemini-2.5-flash";
  private static final String IMAGE_GENERATION_MODEL_ID = "gemini-2.5-flash-image";
  private static final String IMAGEN_GENERATE_MODEL_NAME = "imagen-4.0-generate-001";
  private static final String IMAGEN_UPSCALE_MODEL_NAME = "imagen-4.0-upscale-preview";
  private static final String TEXT_EMBEDDING_MODEL_ID = "gemini-embedding-001";

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

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testGenerateContent_withContentAsync(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/models/generate_content/test_async." + suffix + ".json");

    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .httpOptions(HttpOptions.builder().headers(ImmutableMap.of("test", "headers")))
            .build();

    // Act
    CompletableFuture<GenerateContentResponse> responseFuture =
        client.async.models.generateContent(
            MODEL_ID, Content.fromParts(Part.fromText("Tell me a story in 300 words.")), config);
    GenerateContentResponse response = responseFuture.join();

    // Assert
    assertNotNull(response.text());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testListModelsAsync(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/models/list/test_base_models_async_pager." + suffix + ".json");

    // Act
    AsyncPager<Model> pager =
        client
            .async
            .models
            .list(ListModelsConfig.builder().pageSize(10).queryBase(true).build())
            .get();

    // Assert
    assertEquals(pager.pageSize().get(), 10);
    assertTrue(pager.size().get() <= 10);
    pager.forEach(item -> assertNotNull(item)).get();
    assertNotNull(pager.sdkHttpResponse().get().get().headers().get());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testListTunedModelsAsync(boolean vertexAI) throws Exception {
    if (vertexAI) {
      // Skipped due to b/457846189
      return;
    }
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/models/list/test_tuned_models_async_pager." + suffix + ".json");

    // Act
    AsyncPager<Model> pager =
        client
            .async
            .models
            .list(ListModelsConfig.builder().pageSize(3).queryBase(false).build())
            .get();

    // Assert
    assertEquals(3, pager.size().get());
    pager.forEach(item -> assertNotNull(item)).get();
    assertNotNull(pager.sdkHttpResponse().get().get().headers().get());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testGenerateContent_withHeadersAsync(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/models/generate_content/test_async_with_headers." + suffix + ".json");

    // Act
    CompletableFuture<GenerateContentResponse> responseFuture =
        client.async.models.generateContent(
            MODEL_ID, Content.fromParts(Part.fromText("Tell me a story in 300 words.")), null);
    GenerateContentResponse response = responseFuture.join();

    // Assert
    assertNotNull(response.text());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testGenerateContentStream_withContentAsync(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/models/generate_content/test_async_stream." + suffix + ".json");

    // Act
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .httpOptions(HttpOptions.builder().headers(ImmutableMap.of("test", "headers")))
            .build();
    CompletableFuture<ResponseStream<GenerateContentResponse>> responseStreamFuture =
        client.async.models.generateContentStream(
            MODEL_ID, "Tell me a story in 300 words.", config);
    ResponseStream<GenerateContentResponse> responseStream = responseStreamFuture.join();

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
  public void testGenerateContentStream_withHeadersAsync(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/models/generate_content/test_async_stream_with_headers." + suffix + ".json");

    // Act
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .httpOptions(HttpOptions.builder().headers(ImmutableMap.of("test", "headers")))
            .build();
    CompletableFuture<ResponseStream<GenerateContentResponse>> responseStreamFuture =
        client.async.models.generateContentStream(
            MODEL_ID, "Tell me a story in 300 words.", config);

    ResponseStream<GenerateContentResponse> responseStream = responseStreamFuture.join();

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
  public void testGenerateContentStream_withImageModalityAsync(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/models/generate_content/test_async_stream_with_non_text_modality."
                + suffix
                + ".json");

    // Act
    GenerateContentConfig config =
        GenerateContentConfig.builder().responseModalities("IMAGE", "TEXT").build();
    CompletableFuture<ResponseStream<GenerateContentResponse>> responseStreamFuture =
        client.async.models.generateContentStream(
            IMAGE_GENERATION_MODEL_ID,
            "Generate an image of the Eiffel tower with fireworks in the background.",
            config);
    ResponseStream<GenerateContentResponse> responseStream = responseStreamFuture.join();

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
  public void testGenerateContent_withContentAndConfigAsync(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/models/generate_content/test_simple_shared_generation_config_async."
                + suffix
                + ".json");

    // Act
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .maxOutputTokens(4000)
            .topK(2f)
            .temperature(0.5f)
            .topP(0.5f)
            .responseMimeType("application/json")
            .stopSequences("\n")
            .seed(42)
            .build();
    CompletableFuture<GenerateContentResponse> responseFuture =
        client.async.models.generateContent(
            MODEL_ID, Content.fromParts(Part.fromText("tell me a story in 300 words")), config);
    GenerateContentResponse response = responseFuture.join();

    // Assert
    assertNotNull(response.text());
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
    CompletableFuture<ResponseStream<GenerateContentResponse>> responseStreamFuture =
        client.async.models.generateContentStream(
            MODEL_ID, Content.fromParts(Part.fromText("tell me a story in 300 words")), config);
    ResponseStream<GenerateContentResponse> responseStream = responseStreamFuture.join();

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
  public void testEmbedContentAsync(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/models/embed_content/test_async." + suffix + ".json");

    EmbedContentConfig config = EmbedContentConfig.builder().outputDimensionality(10).build();

    // Act
    CompletableFuture<EmbedContentResponse> responseFuture =
        client.async.models.embedContent(TEXT_EMBEDDING_MODEL_ID, "What is your name?", config);
    EmbedContentResponse response = responseFuture.join();

    // Assert
    assertTrue(response.embeddings().isPresent());
    assertEquals(1, response.embeddings().get().size());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testCountTokensAsync(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/models/count_tokens/test_async." + suffix + ".json");

    // Act
    CompletableFuture<CountTokensResponse> responseFuture =
        client.async.models.countTokens(MODEL_ID, "Tell me a story in 300 words.", null);
    CountTokensResponse response = responseFuture.join();

    // Assert
    assertTrue(response.totalTokens().isPresent());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testComputeTokensAsync(boolean vertexAI) throws Exception {
    if (!vertexAI) {
      return;
    }

    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/models/compute_tokens/test_async." + suffix + ".json");

    // Act
    CompletableFuture<ComputeTokensResponse> responseFuture =
        client.async.models.computeTokens(MODEL_ID, "Tell me a story in 300 words.", null);
    ComputeTokensResponse response = responseFuture.join();

    // Assert
    assertTrue(response.tokensInfo().isPresent());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testEditImage_withMaskReferenceAsync(boolean vertexAI) throws Exception {
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
      CompletableFuture<EditImageResponse> responseFuture =
          client.async.models.editImage(
              "imagen-3.0-capability-001",
              "Sunlight and clear weather",
              Arrays.asList(createRawReferenceImage(), createMaskReferenceImage()),
              config);
      EditImageResponse response = responseFuture.join();

      // Assert
      assertTrue(response.generatedImages().get().get(0).image().isPresent());
    } else {
      UnsupportedOperationException exception =
          assertThrows(
              UnsupportedOperationException.class,
              () ->
                  client
                      .async
                      .models
                      .editImage(
                          "imagen-3.0-capability-001",
                          "Sunlight and clear weather",
                          Arrays.asList(createRawReferenceImage(), createMaskReferenceImage()),
                          config)
                      .join());
      // Assert
      assertEquals(
          "This method is only supported in the Vertex AI client.", exception.getMessage());
    }
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testGoogleSearchAsync(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/models/generate_content_tools/test_google_search." + suffix + ".json");

    Tool tool = Tool.builder().googleSearch(GoogleSearch.builder()).build();
    GenerateContentConfig config = GenerateContentConfig.builder().tools(tool).build();

    // Act
    CompletableFuture<GenerateContentResponse> responseFuture =
        client.async.models.generateContent(MODEL_ID, "Why is the sky blue?", config);
    GenerateContentResponse response = responseFuture.join();

    // Assert
    assertNotNull(response.text());
  }

  @ParameterizedTest
  @ValueSource(booleans = {true}) // Only for Vertex
  public void testVaiSearchAsync(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/models/generate_content_tools/test_vai_search." + suffix + ".json");

    Tool tool =
        Tool.builder()
            .retrieval(
                Retrieval.builder()
                    .vertexAiSearch(
                        VertexAISearch.builder()
                            .datastore(
                                "projects/vertex-sdk-dev/locations/global/collections/default_collection/dataStores/yvonne_1728691676574")))
            .build();
    GenerateContentConfig config = GenerateContentConfig.builder().tools(tool).build();

    // Act
    CompletableFuture<GenerateContentResponse> responseFuture =
        client.async.models.generateContent(MODEL_ID, "what is vertex ai search?", config);
    GenerateContentResponse response = responseFuture.join();

    // Assert
    assertNotNull(response.text());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testCodeExecutionAsync(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/models/generate_content_tools/test_code_execution." + suffix + ".json");

    Tool tool = Tool.builder().codeExecution(ToolCodeExecution.builder()).build();
    GenerateContentConfig config = GenerateContentConfig.builder().tools(tool).build();

    // Act
    CompletableFuture<GenerateContentResponse> responseFuture =
        client.async.models.generateContent(
            MODEL_ID,
            "What is the sum of the first 50 prime numbers? Generate and run code for the"
                + " calculation, and make sure you get all 50.",
            config);
    GenerateContentResponse response = responseFuture.join();

    // Assert
    assertNotNull(response.text());
  }

  @ParameterizedTest
  @ValueSource(booleans = {true}) // Only for Vertex
  public void testRagModelGaAsync(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/models/generate_content_tools/test_rag_model_ga." + suffix + ".json");

    Tool tool =
        Tool.builder()
            .retrieval(
                Retrieval.builder()
                    .vertexRagStore(
                        VertexRagStore.builder()
                            .ragResources(
                                VertexRagStoreRagResource.builder()
                                    .ragCorpus(
                                        "projects/964831358985/locations/us-central1/ragCorpora/3379951520341557248"))
                            .ragRetrievalConfig(
                                RagRetrievalConfig.builder()
                                    .topK(3)
                                    .filter(
                                        RagRetrievalConfigFilter.builder()
                                            .vectorSimilarityThreshold(0.5)))))
            .build();
    GenerateContentConfig config = GenerateContentConfig.builder().tools(tool).build();

    // Act
    CompletableFuture<GenerateContentResponse> responseFuture =
        client.async.models.generateContent(
            MODEL_ID,
            "How much gain or loss did Google get in the Motorola Mobile deal in 2014?",
            config);
    GenerateContentResponse response = responseFuture.join();

    // Assert
    assertNotNull(response.text());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testGenerateImagesAsync(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/models/generate_images/test_simple_prompt." + suffix + ".json");

    GenerateImagesConfig config =
        GenerateImagesConfig.builder().numberOfImages(1).outputMimeType("image/jpeg").build();

    // Act
    CompletableFuture<GenerateImagesResponse> responseFuture =
        client.async.models.generateImages(IMAGEN_GENERATE_MODEL_NAME, "Red skateboard", config);
    GenerateImagesResponse response = responseFuture.join();

    // Assert
    assertTrue(response.generatedImages().get().get(0).image().isPresent());
    assertEquals(1, response.generatedImages().get().size());
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testUpscaleImageAsync(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/models/upscale_image/test_upscale." + suffix + ".json");

    URL resourceUrl = getClass().getClassLoader().getResource("bridge1.png");
    Path filePath = Paths.get(resourceUrl.toURI());
    Image image = Image.fromFile(filePath.toAbsolutePath().toString());

    UpscaleImageConfig config =
        UpscaleImageConfig.builder()
            .includeRaiReason(true)
            .safetyFilterLevel(SafetyFilterLevel.Known.BLOCK_LOW_AND_ABOVE)
            .personGeneration(PersonGeneration.Known.ALLOW_ADULT)
            .outputMimeType("image/jpeg")
            .outputCompressionQuality(80)
            .enhanceInputImage(true)
            .imagePreservationFactor(0.6f)
            .labels(ImmutableMap.of("imagen_label_key", "upscale_image"))
            .build();

    // Act
    if (vertexAI) {
      CompletableFuture<UpscaleImageResponse> responseFuture =
          client.async.models.upscaleImage(IMAGEN_UPSCALE_MODEL_NAME, image, "x2", config);
      UpscaleImageResponse response = responseFuture.join();

      // Assert
      assertTrue(response.generatedImages().get().get(0).image().isPresent());
    } else {
      UnsupportedOperationException exception =
          assertThrows(
              UnsupportedOperationException.class,
              () ->
                  client
                      .async
                      .models
                      .upscaleImage(IMAGEN_UPSCALE_MODEL_NAME, image, "x2", config)
                      .join());
      // Assert
      assertEquals(
          "This method is only supported in the Vertex AI client.", exception.getMessage());
    }
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testGenerateVideosAsync(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/models/generate_videos/test_text_to_video_poll." + suffix + ".json");

    GenerateVideosConfig.Builder configBuilder = GenerateVideosConfig.builder();
    if (vertexAI) {
      configBuilder.outputGcsUri("gs://genai-sdk-tests/temp/videos/");
    }
    GenerateVideosConfig config = configBuilder.build();

    // Act
    CompletableFuture<GenerateVideosOperation> responseFuture =
        client.async.models.generateVideos(
            "veo-3.1-generate-preview",
            "A neon hologram of a cat driving at top speed",
            null,
            config);
    GenerateVideosOperation operation = responseFuture.join();

    // Assert
    assertNotNull(operation.name());
  }

  @ParameterizedTest
  @ValueSource(booleans = {true})
  public void testUpdateTunedModelAsync(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    // Act
    if (vertexAI) {
      Client client =
          TestUtils.createClient(
              vertexAI, "tests/models/update/test_async_update_tuned_model." + suffix + ".json");

      UpdateModelConfig config =
          UpdateModelConfig.builder()
              .description("My tuned gemini model")
              .httpOptions(HttpOptions.builder().headers(ImmutableMap.of("test", "headers")))
              .build();
      CompletionException exception =
          assertThrows(
              CompletionException.class,
              () ->
                  client
                      .async
                      .models
                      .update("tunedModels/generatenum5443-ekrw7ie9wis23zbeogbw6jq8", config)
                      .join());
      // Assert
      assertTrue(exception.getCause().getMessage().contains("404"));
    }
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testUpdateModelAsync(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/models/update/test_async_update_model." + suffix + ".json");

    UpdateModelConfig config =
        UpdateModelConfig.builder()
            .displayName("My tuned gemini model")
            .httpOptions(HttpOptions.builder().headers(ImmutableMap.of("test", "headers")))
            .build();

    // Act
    if (vertexAI) {
      CompletableFuture<Model> responseFuture =
          client.async.models.update("models/2171259487439028224", config);
      Model response = responseFuture.join();
      // Assert
      assertNotNull(response);
    } else {
      CompletionException exception =
          assertThrows(
              CompletionException.class,
              () -> client.async.models.update("models/2171259487439028224", config).join());
      // Assert
      assertTrue(exception.getCause().getMessage().contains("404"));
    }
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testGetTunedModelAsync(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/models/get/test_async_get_tuned_model." + suffix + ".json");

    // Act
    if (vertexAI) {
      CompletionException exception =
          assertThrows(
              CompletionException.class,
              () -> client.async.models.get("tunedModels/generate-num-1896", null).join());
      // Assert
      assertTrue(exception.getCause().getMessage().contains("404"));
    } else {
      CompletableFuture<Model> responseFuture =
          client.async.models.get("tunedModels/generate-num-1896", null);
      Model response = responseFuture.join();
      // Assert
      assertNotNull(response);
    }
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testGetModelAsync(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/models/get/test_async_get_model." + suffix + ".json");

    // Act
    if (vertexAI) {
      CompletableFuture<Model> responseFuture =
          client.async.models.get(
              "models/7687416965014487040",
              GetModelConfig.builder()
                  .httpOptions(HttpOptions.builder().headers(ImmutableMap.of("test", "headers")))
                  .build());
      Model response = responseFuture.join();
      // Assert
      assertNotNull(response);
    } else {
      CompletionException exception =
          assertThrows(
              CompletionException.class,
              () ->
                  client
                      .async
                      .models
                      .get(
                          "models/7687416965014487040",
                          GetModelConfig.builder()
                              .httpOptions(
                                  HttpOptions.builder().headers(ImmutableMap.of("test", "headers")))
                              .build())
                      .join());
      // Assert
      assertTrue(exception.getCause().getMessage().contains("404"));
    }
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testDeleteModelWithHttpOptionsAsync(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/models/delete/test_async_delete_model_with_http_options_in_method."
                + suffix
                + ".json");

    // Act
    CompletionException exception =
        assertThrows(
            CompletionException.class,
            () ->
                client
                    .async
                    .models
                    .delete(
                        "tunedModels/generate-num-888",
                        DeleteModelConfig.builder()
                            .httpOptions(
                                HttpOptions.builder()
                                    .apiVersion("test_api_version")
                                    .headers(ImmutableMap.of("test", "headers")))
                            .build())
                    .join());

    // Assert
    if (vertexAI) {
      assertTrue(exception.getCause().getMessage().contains("test_api_version"));
    } else {
      assertTrue(exception.getCause().getMessage().contains("404"));
    }
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testDeleteTunedModelAsync(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/models/delete/test_async_delete_tuned_model." + suffix + ".json");

    // Act
    if (vertexAI) {
      CompletionException exception =
          assertThrows(
              CompletionException.class,
              () -> client.async.models.delete("tunedModels/generate-num-888", null).join());
      // Assert
      assertTrue(exception.getCause().getMessage().contains("404"));
    } else {
      CompletableFuture<DeleteModelResponse> responseFuture =
          client.async.models.delete("tunedModels/generate-num-888", null);
      responseFuture.join();
    }
  }

  @ParameterizedTest
  @ValueSource(booleans = {false, true})
  public void testDeleteModelAsync(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI, "tests/models/delete/test_async_delete_model." + suffix + ".json");

    // Act
    if (vertexAI) {
      CompletableFuture<DeleteModelResponse> responseFuture =
          client.async.models.delete("models/1071206899942162432", null);
      responseFuture.join();
    } else {
      CompletionException exception =
          assertThrows(
              CompletionException.class,
              () -> client.async.models.delete("models/1071206899942162432", null).join());
      // Assert
      assertTrue(exception.getCause().getMessage().contains("404"));
    }
  }

  @ParameterizedTest
  @ValueSource(booleans = {false})
  public void testGenerateContentStream_withServerSideMcpAsync(boolean vertexAI) throws Exception {
    // Arrange
    String suffix = vertexAI ? "vertex" : "mldev";
    Client client =
        TestUtils.createClient(
            vertexAI,
            "tests/models/generate_content_tools/test_server_side_mcp_only_async."
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
    CompletableFuture<GenerateContentResponse> responseFuture =
        client.async.models.generateContent(
            "gemini-2.5-flash", "What is the weather like in New York on 02/02/2026?", config);
    GenerateContentResponse response = responseFuture.join();
    assertNotNull(response.text());
  }
}
