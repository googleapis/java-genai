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

// Auto-generated code. Do not edit.

package com.google.genai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.genai.Common.BuiltRequest;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.ComputeTokensConfig;
import com.google.genai.types.ComputeTokensResponse;
import com.google.genai.types.Content;
import com.google.genai.types.CountTokensConfig;
import com.google.genai.types.CountTokensResponse;
import com.google.genai.types.DeleteModelConfig;
import com.google.genai.types.DeleteModelResponse;
import com.google.genai.types.EditImageConfig;
import com.google.genai.types.EditImageResponse;
import com.google.genai.types.EmbedContentConfig;
import com.google.genai.types.EmbedContentResponse;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.GenerateImagesConfig;
import com.google.genai.types.GenerateImagesResponse;
import com.google.genai.types.GenerateVideosConfig;
import com.google.genai.types.GenerateVideosOperation;
import com.google.genai.types.GenerateVideosSource;
import com.google.genai.types.GetModelConfig;
import com.google.genai.types.Image;
import com.google.genai.types.ListModelsConfig;
import com.google.genai.types.ListModelsResponse;
import com.google.genai.types.Model;
import com.google.genai.types.Part;
import com.google.genai.types.RecontextImageConfig;
import com.google.genai.types.RecontextImageResponse;
import com.google.genai.types.RecontextImageSource;
import com.google.genai.types.ReferenceImage;
import com.google.genai.types.ReferenceImageAPI;
import com.google.genai.types.SegmentImageConfig;
import com.google.genai.types.SegmentImageResponse;
import com.google.genai.types.SegmentImageSource;
import com.google.genai.types.UpdateModelConfig;
import com.google.genai.types.UpscaleImageAPIConfig;
import com.google.genai.types.UpscaleImageConfig;
import com.google.genai.types.UpscaleImageResponse;
import com.google.genai.types.Video;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.logging.Logger;

/** Async module of {@link Models} */
public final class AsyncModels {

  Models models;
  ApiClient apiClient;

  public AsyncModels(ApiClient apiClient) {
    this.apiClient = apiClient;
    this.models = new Models(apiClient);
  }

  CompletableFuture<GenerateContentResponse> privateGenerateContent(
      String model, List<Content> contents, GenerateContentConfig config) {
    BuiltRequest builtRequest =
        models.buildRequestForPrivateGenerateContent(model, contents, config);
    return this.apiClient
        .asyncRequest("post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return models.processResponseForPrivateGenerateContent(res, config);
              }
            });
  }

  CompletableFuture<ResponseStream<GenerateContentResponse>> privateGenerateContentStream(
      String model, List<Content> contents, GenerateContentConfig config) {
    BuiltRequest builtRequest =
        models.buildRequestForPrivateGenerateContentStream(model, contents, config);
    return this.apiClient
        .asyncRequest("post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)
        .thenApplyAsync(
            response -> {
              return models.processResponseForPrivateGenerateContentStream(response, config);
            });
  }

  CompletableFuture<EmbedContentResponse> privateEmbedContent(
      String model, List<Content> contents, EmbedContentConfig config) {
    BuiltRequest builtRequest = models.buildRequestForPrivateEmbedContent(model, contents, config);
    return this.apiClient
        .asyncRequest("post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return models.processResponseForPrivateEmbedContent(res, config);
              }
            });
  }

  /** Asynchronously private method for generating images. */
  CompletableFuture<GenerateImagesResponse> privateGenerateImages(
      String model, String prompt, GenerateImagesConfig config) {
    BuiltRequest builtRequest = models.buildRequestForPrivateGenerateImages(model, prompt, config);
    return this.apiClient
        .asyncRequest("post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return models.processResponseForPrivateGenerateImages(res, config);
              }
            });
  }

  /** Asynchronously private method for editing an image. */
  CompletableFuture<EditImageResponse> privateEditImage(
      String model,
      String prompt,
      List<ReferenceImageAPI> referenceImages,
      EditImageConfig config) {
    BuiltRequest builtRequest =
        models.buildRequestForPrivateEditImage(model, prompt, referenceImages, config);
    return this.apiClient
        .asyncRequest("post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return models.processResponseForPrivateEditImage(res, config);
              }
            });
  }

  /** Asynchronously private method for upscaling an image. */
  CompletableFuture<UpscaleImageResponse> privateUpscaleImage(
      String model, Image image, String upscaleFactor, UpscaleImageAPIConfig config) {
    BuiltRequest builtRequest =
        models.buildRequestForPrivateUpscaleImage(model, image, upscaleFactor, config);
    return this.apiClient
        .asyncRequest("post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return models.processResponseForPrivateUpscaleImage(res, config);
              }
            });
  }

  /**
   * Asynchronously recontextualizes an image.
   *
   * <p>There are two types of recontextualization currently supported: 1) Imagen Product Recontext
   * - Generate images of products in new scenes and contexts. 2) Virtual Try-On: Generate images of
   * persons modeling fashion products.
   *
   * @param model the name of the GenAI model to use for image recontext
   * @param source a {@link com.google.genai.types.RecontextImageSource} An object containing the
   *     source inputs (prompt, personImage, productImages) for image recontext. prompt is optional
   *     for product recontext and disallowed for virtual try-on. personImage is required for
   *     virtual try-on, disallowed for product recontext. productImages is required for both
   *     product recontext and virtual try-on. Only one product image is supported for virtual
   *     try-on, and up to 3 product images (different angles of the same product) are supported for
   *     product recontext.
   * @param config a {@link com.google.genai.types.RecontextImageConfig} instance that specifies the
   *     optional configurations
   * @return a {@link com.google.genai.types.RecontextImageResponse} instance that contains the
   *     generated images.
   */
  public CompletableFuture<RecontextImageResponse> recontextImage(
      String model, RecontextImageSource source, RecontextImageConfig config) {
    BuiltRequest builtRequest = models.buildRequestForRecontextImage(model, source, config);
    return this.apiClient
        .asyncRequest("post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return models.processResponseForRecontextImage(res, config);
              }
            });
  }

  /**
   * Asynchronously segments an image, creating a mask of a specified area.
   *
   * @param model the name of the GenAI model to use for image segmentation
   * @param source a {@link com.google.genai.types.SegmentImageSource} An object containing the
   *     source inputs (prompt, image, scribbleImmage) for image segmentation. The prompt is
   *     required for prompt mode and semantic mode, disallowed for other modes. scribbleImage is
   *     required for the interactive mode, disallowed for other modes.
   * @param config a {@link com.google.genai.types.SegmentImageConfig} instance that specifies the
   *     optional configurations
   * @return a {@link com.google.genai.types.SegmentImageResponse} instance that contains the
   *     generated mask.
   */
  public CompletableFuture<SegmentImageResponse> segmentImage(
      String model, SegmentImageSource source, SegmentImageConfig config) {
    BuiltRequest builtRequest = models.buildRequestForSegmentImage(model, source, config);
    return this.apiClient
        .asyncRequest("post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return models.processResponseForSegmentImage(res, config);
              }
            });
  }

  /**
   * Asynchronously fetches information about a model by name.
   *
   * @example ```java Model model = client.models.get("gemini-2.0-flash"); ```
   */
  public CompletableFuture<Model> get(String model, GetModelConfig config) {
    BuiltRequest builtRequest = models.buildRequestForGet(model, config);
    return this.apiClient
        .asyncRequest("get", builtRequest.path, builtRequest.body, builtRequest.httpOptions)
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return models.processResponseForGet(res, config);
              }
            });
  }

  CompletableFuture<ListModelsResponse> privateList(ListModelsConfig config) {
    BuiltRequest builtRequest = models.buildRequestForPrivateList(config);
    return this.apiClient
        .asyncRequest("get", builtRequest.path, builtRequest.body, builtRequest.httpOptions)
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return models.processResponseForPrivateList(res, config);
              }
            });
  }

  /**
   * Asynchronously updates a tuned model by its name.
   *
   * @param model The name of the tuned model to update
   * @param config A {@link com.google.genai.types.UpdateModelConfig} instance that specifies the
   *     optional configurations
   * @return A {@link com.google.genai.types.Model} instance
   * @example ```java Model model = client.models.update( "tunedModels/12345",
   *     UpdateModelConfig.builder() .displayName("New display name") .description("New
   *     description") .build()); ```
   */
  public CompletableFuture<Model> update(String model, UpdateModelConfig config) {
    BuiltRequest builtRequest = models.buildRequestForUpdate(model, config);
    return this.apiClient
        .asyncRequest("patch", builtRequest.path, builtRequest.body, builtRequest.httpOptions)
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return models.processResponseForUpdate(res, config);
              }
            });
  }

  /**
   * Asynchronously fetches information about a model by name.
   *
   * @example ```java Model model = client.models.delete("tunedModels/12345"); ```
   */
  public CompletableFuture<DeleteModelResponse> delete(String model, DeleteModelConfig config) {
    BuiltRequest builtRequest = models.buildRequestForDelete(model, config);
    return this.apiClient
        .asyncRequest("delete", builtRequest.path, builtRequest.body, builtRequest.httpOptions)
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return models.processResponseForDelete(res, config);
              }
            });
  }

  /**
   * Asynchronously counts tokens given a GenAI model and a list of content.
   *
   * @param model the name of the GenAI model to use.
   * @param contents a {@link List<com.google.genai.types.Content>} to send to count tokens for.
   * @param config a {@link com.google.genai.types.CountTokensConfig} instance that specifies the
   *     optional configurations
   * @return a {@link com.google.genai.types.CountTokensResponse} instance that contains tokens
   *     count.
   */
  public CompletableFuture<CountTokensResponse> countTokens(
      String model, List<Content> contents, CountTokensConfig config) {
    BuiltRequest builtRequest = models.buildRequestForCountTokens(model, contents, config);
    return this.apiClient
        .asyncRequest("post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return models.processResponseForCountTokens(res, config);
              }
            });
  }

  /**
   * Asynchronously computes tokens given a GenAI model and a list of content.
   *
   * @param model the name of the GenAI model to use.
   * @param contents a {@link List<com.google.genai.types.Content>} to send to compute tokens for.
   * @param config a {@link com.google.genai.types.ComputeTokensConfig} instance that specifies the
   *     optional configurations
   * @return a {@link com.google.genai.types.ComputeTokensResponse} instance that contains tokens
   *     results.
   */
  public CompletableFuture<ComputeTokensResponse> computeTokens(
      String model, List<Content> contents, ComputeTokensConfig config) {
    BuiltRequest builtRequest = models.buildRequestForComputeTokens(model, contents, config);
    return this.apiClient
        .asyncRequest("post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return models.processResponseForComputeTokens(res, config);
              }
            });
  }

  /** Asynchronously private method for generating videos. */
  CompletableFuture<GenerateVideosOperation> privateGenerateVideos(
      String model,
      String prompt,
      Image image,
      Video video,
      GenerateVideosSource source,
      GenerateVideosConfig config) {
    BuiltRequest builtRequest =
        models.buildRequestForPrivateGenerateVideos(model, prompt, image, video, source, config);
    return this.apiClient
        .asyncRequest("post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)
        .thenApplyAsync(
            response -> {
              try (ApiResponse res = response) {
                return models.processResponseForPrivateGenerateVideos(res, config);
              }
            });
  }

  private static final Logger logger = Logger.getLogger(AsyncModels.class.getName());

  /**
   * Asynchronously counts tokens given a GenAI model and a text string.
   *
   * @param model the name of the GenAI model to use.
   * @param text the text string to send to count tokens for.
   * @param config a {@link com.google.genai.types.CountTokensConfig} instance that specifies the
   *     optional configurations
   * @return a {@link com.google.genai.types.CountTokensResponse} instance that contains tokens
   *     count.
   */
  public CompletableFuture<CountTokensResponse> countTokens(
      String model, String text, CountTokensConfig config) {
    return countTokens(model, Transformers.tContents(text), config);
  }

  /**
   * Asynchronously computes tokens given a GenAI model and a text string.
   *
   * @param model the name of the GenAI model to use.
   * @param text the text string to send to count tokens for.
   * @param config a {@link com.google.genai.types.ComputeTokensConfig} instance that specifies the
   *     optional configurations
   * @return a {@link com.google.genai.types.ComputeTokensResponse} instance that contains tokens
   *     results.
   */
  public CompletableFuture<ComputeTokensResponse> computeTokens(
      String model, String text, ComputeTokensConfig config) {
    return computeTokens(model, Transformers.tContents(text), config);
  }

  /** A private helper class to pass the result of the AFC loop up the async chain. */
  private static class AfcLoopResult {
    final GenerateContentResponse response;
    final List<Content> history;

    AfcLoopResult(GenerateContentResponse response, List<Content> history) {
      this.response = response;
      this.history = history;
    }
  }

  /**
   * Represents a single iteration of the asynchronous AFC loop. This method calls itself
   * recursively inside a .thenCompose() block to chain asynchronous calls sequentially.
   */
  private CompletableFuture<AfcLoopResult> privateGenerateContentLoopAsync(
      String model,
      List<Content> contents,
      GenerateContentConfig transformedConfig,
      ImmutableMap<String, Method> functionMap,
      List<Content> automaticFunctionCallingHistory,
      int remainingRemoteCalls,
      int initialMaxCalls) {

    logger.info(
        String.format(
            "Automatic function calling remote call %d is done",
            (initialMaxCalls - remainingRemoteCalls + 1)));

    return privateGenerateContent(model, contents, transformedConfig)
        .thenCompose(
            response -> {
              if (remainingRemoteCalls - 1 <= 0) {
                logger.info("Reached max remote calls for automatic function calling.");
                return CompletableFuture.completedFuture(
                    new AfcLoopResult(response, automaticFunctionCallingHistory));
              }

              if (!response.candidates().isPresent()
                  || response.candidates().get().isEmpty()
                  || !response.candidates().get().get(0).content().isPresent()
                  || !response.candidates().get().get(0).content().get().parts().isPresent()
                  || response.candidates().get().get(0).content().get().parts().get().isEmpty()) {
                return CompletableFuture.completedFuture(
                    new AfcLoopResult(response, automaticFunctionCallingHistory));
              }

              ImmutableList<Part> functionResponseParts =
                  AfcUtil.getFunctionResponseParts(response, functionMap);
              if (functionResponseParts.isEmpty()) {
                return CompletableFuture.completedFuture(
                    new AfcLoopResult(response, automaticFunctionCallingHistory));
              }

              Content functionCallContent = response.candidates().get().get(0).content().get();
              Content functionResponseContent =
                  Content.builder().role("user").parts(functionResponseParts).build();

              List<Content> newHistory = new ArrayList<>(automaticFunctionCallingHistory);
              newHistory.add(functionCallContent);
              newHistory.add(functionResponseContent);

              return privateGenerateContentLoopAsync(
                  model,
                  newHistory,
                  transformedConfig,
                  functionMap,
                  newHistory,
                  remainingRemoteCalls - 1,
                  initialMaxCalls);
            });
  }

  /**
   * Asynchronously generates content given a GenAI model and a list of content.
   *
   * @param model the name of the GenAI model to use for generation
   * @param contents a {@link List<com.google.genai.types.Content>} to send to the generative model
   * @param config a {@link com.google.genai.types.GenerateContentConfig} instance that specifies
   *     the optional configurations
   * @return a {@link com.google.genai.types.GenerateContentResponse} instance that contains
   *     response contents and other metadata
   */
  public CompletableFuture<GenerateContentResponse> generateContent(
      String model, List<Content> contents, GenerateContentConfig config) {
    GenerateContentConfig transformedConfig = AfcUtil.transformGenerateContentConfig(config);
    if (AfcUtil.shouldDisableAfc(transformedConfig)) {
      return privateGenerateContent(model, contents, transformedConfig);
    }

    ImmutableMap<String, Method> functionMap = AfcUtil.getFunctionMap(config);
    if (functionMap.isEmpty()) {
      return privateGenerateContent(model, contents, transformedConfig);
    }

    int maxRemoteCalls = AfcUtil.getMaxRemoteCallsAfc(transformedConfig);
    logger.info(
        String.format(
            "Automatic function calling is enabled with max remote calls: %d", maxRemoteCalls));
    List<Content> automaticFunctionCallingHistory = new ArrayList<>(contents);

    return privateGenerateContentLoopAsync(
            model,
            contents,
            transformedConfig,
            functionMap,
            automaticFunctionCallingHistory,
            maxRemoteCalls,
            maxRemoteCalls)
        .thenApply(
            loopResult -> {
              if (AfcUtil.shouldAppendAfcHistory(transformedConfig)) {
                ObjectNode responseNode =
                    JsonSerializable.objectMapper.valueToTree(loopResult.response);
                responseNode.set(
                    "automaticFunctionCallingHistory",
                    JsonSerializable.objectMapper.valueToTree(loopResult.history));
                return JsonSerializable.fromJsonNode(responseNode, GenerateContentResponse.class);
              }
              return loopResult.response;
            });
  }

  /**
   * Asynchronously generates content given a GenAI model and a content object.
   *
   * @param model the name of the GenAI model to use for generation
   * @param content a {@link com.google.genai.types.Content} to send to the generative model
   * @param config a {@link com.google.genai.types.GenerateContentConfig} instance that specifies
   *     the optional configurations
   * @return a {@link com.google.genai.types.GenerateContentResponse} instance that contains
   *     response contents and other metadata
   */
  public CompletableFuture<GenerateContentResponse> generateContent(
      String model, Content content, GenerateContentConfig config) {
    return generateContent(model, Transformers.tContents(content), config);
  }

  /**
   * Asynchronously generates content given a GenAI model and a text string.
   *
   * @param model the name of the GenAI model to use for generation
   * @param text the text string to send to the generative model
   * @param config a {@link com.google.genai.types.GenerateContentConfig} instance that specifies
   *     the optional configurations
   * @return a {@link com.google.genai.types.GenerateContentResponse} instance that contains
   *     response contents and other metadata
   */
  public CompletableFuture<GenerateContentResponse> generateContent(
      String model, String text, GenerateContentConfig config) {
    return generateContent(model, Transformers.tContents(text), config);
  }

  /**
   * Asynchronously generates content with streaming support given a GenAI model and a list of
   * content.
   *
   * @param model the name of the GenAI model to use for generation
   * @param contents a {@link List<com.google.genai.types.Content>} to send to the generative model
   * @param config a {@link com.google.genai.types.GenerateContentConfig} instance that specifies
   *     the optional configurations
   * @return a {@link com.google.genai.types.GenerateContentResponse} instance that contains
   *     response contents and other metadata
   */
  public CompletableFuture<ResponseStream<GenerateContentResponse>> generateContentStream(
      String model, List<Content> contents, GenerateContentConfig config) {
    GenerateContentConfig transformedConfig = AfcUtil.transformGenerateContentConfig(config);
    if (AfcUtil.hasCallableTool(config) && !AfcUtil.shouldDisableAfc(transformedConfig)) {
      logger.warning(
          "In generateContentStream method, detected that automatic function calling is enabled in"
              + " the config.AutomaticFunctionCalling(), and callable tool is present in the"
              + " config.tools() list. Automatic function calling is not supported in streaming"
              + " methods at the moment, will just return the function call parts from model if"
              + " there is any.");
    }
    return privateGenerateContentStream(model, contents, transformedConfig);
  }

  /**
   * Asynchronously generates content with streaming support given a GenAI model and a content
   * object.
   *
   * @param model the name of the GenAI model to use for generation
   * @param content a {@link com.google.genai.types.Content} to send to the generative model
   * @param config a {@link com.google.genai.types.GenerateContentConfig} instance that specifies
   *     the optional configurations
   * @return a {@link com.google.genai.types.GenerateContentResponse} instance that contains
   *     response contents and other metadata
   */
  public CompletableFuture<ResponseStream<GenerateContentResponse>> generateContentStream(
      String model, Content content, GenerateContentConfig config) {
    return generateContentStream(model, Transformers.tContents(content), config);
  }

  /**
   * Asynchronously generates content with streaming support given a GenAI model and a text string.
   *
   * @param model the name of the GenAI model to use for generation
   * @param text the text string to send to the generative model
   * @param config a {@link com.google.genai.types.GenerateContentConfig} instance that specifies
   *     the optional configurations
   * @return a {@link com.google.genai.types.GenerateContentResponse} instance that contains
   *     response contents and other metadata
   */
  public CompletableFuture<ResponseStream<GenerateContentResponse>> generateContentStream(
      String model, String text, GenerateContentConfig config) {
    return generateContentStream(model, Transformers.tContents(text), config);
  }

  /**
   * Asynchronously generates images given a GenAI model and a prompt.
   *
   * @param model the name of the GenAI model to use for generating images
   * @param prompt the prompt to generate images
   * @param config a {@link com.google.genai.types.GenerateImagesConfig} instance that specifies the
   *     optional configurations
   * @return a {@link com.google.genai.types.GenerateImagesResponse} instance that contains the
   *     generated images.
   */
  public CompletableFuture<GenerateImagesResponse> generateImages(
      String model, String prompt, GenerateImagesConfig config) {
    return privateGenerateImages(model, prompt, config)
        .thenApply(models::postProcessGenerateImagesResponse);
  }

  /**
   * Asynchronously edits an image given a GenAI model, a prompt, and a list of reference images.
   *
   * @param model the name of the GenAI model to use for editing capabilities
   * @param prompt the prompt to edit the image
   * @param referenceImages a {@link List<com.google.genai.types.ReferenceImage>} to send to use for
   *     editing. The 5 types of reference images are: {@link
   *     com.google.genai.types.RawReferenceImage}, {@link
   *     com.google.genai.types.MaskReferenceImage}, {@link
   *     com.google.genai.types.ControlReferenceImage}, {@link
   *     com.google.genai.types.StyleReferenceImage}, {@link
   *     com.google.genai.types.SubjectReferenceImage},
   * @param config a {@link com.google.genai.types.EditImageConfig} instance that specifies the
   *     optional configurations
   * @return a {@link com.google.genai.types.EditImageResponse} instance that contains the edited
   *     image.
   */
  public CompletableFuture<EditImageResponse> editImage(
      String model, String prompt, List<ReferenceImage> referenceImages, EditImageConfig config) {
    List<ReferenceImageAPI> referenceImagesAPI = new ArrayList<>();
    for (ReferenceImage referenceImage : referenceImages) {
      referenceImagesAPI.add(referenceImage.toReferenceImageAPI());
    }

    return privateEditImage(model, prompt, referenceImagesAPI, config);
  }

  /**
   * Asynchronously upscales an image given a GenAI model and an image and an upscale factor.
   *
   * @param model the name of the GenAI model to use for upscaling
   * @param image a {@link com.google.genai.types.Image} to send to the generative model
   * @param upscaleFactor the factor to upscale the image
   * @param config a {@link com.google.genai.types.UpscaleImageConfig} instance that specifies the
   *     optional configurations
   * @return a {@link com.google.genai.types.UpscaleImageResponse} instance that contains the
   *     upscaled image.
   */
  public CompletableFuture<UpscaleImageResponse> upscaleImage(
      String model, Image image, String upscaleFactor, UpscaleImageConfig config) {
    return privateUpscaleImage(
        model, image, upscaleFactor, models.preProcessUpscaleImageConfig(config));
  }

  /**
   * Asynchronously generates videos given a GenAI model, and a GenerateVideosSource source.
   *
   * <p>This method is experimental.
   *
   * @param model the name of the GenAI model to use for generating videos
   * @param source a {@link com.google.genai.types.GenerateVideosSource} that specifies the inputs
   *     (prompt, image, and/or video) to generate videos.
   * @param config a {@link com.google.genai.types.GenerateVideosConfig} instance that specifies the
   *     optional configurations
   * @return a {@link com.google.genai.types.GenerateVideosOperation} instance that contains the
   *     generated videos.
   */
  public CompletableFuture<GenerateVideosOperation> generateVideos(
      String model, GenerateVideosSource source, GenerateVideosConfig config) {
    return privateGenerateVideos(
        model, null, null, null, models.preProcessGenerateVideosSource(source), config);
  }

  /**
   * Asynchronously generates videos given a GenAI model, and an input (text, image, or video).
   *
   * <p>This method is experimental.
   *
   * @param model the name of the GenAI model to use for generating videos
   * @param prompt the text prompt for generating the videos. Optional for image to video and video
   *     extension use cases.
   * @param image the input image for generating the videos. Optional if prompt is provided.
   * @param video the input video for video extension use cases. Optional if prompt or image is
   *     provided.
   * @param config a {@link com.google.genai.types.GenerateVideosConfig} instance that specifies the
   *     optional configurations
   * @return a {@link com.google.genai.types.GenerateVideosOperation} instance that contains the
   *     generated videos.
   */
  public CompletableFuture<GenerateVideosOperation> generateVideos(
      String model, String prompt, Image image, Video video, GenerateVideosConfig config) {
    return privateGenerateVideos(model, prompt, image, models.preProcessVideo(video), null, config);
  }

  /**
   * Asynchronously generates videos given a GenAI model, and an input (text, image).
   *
   * <p>This method is experimental, and kept for backward compatibility.
   *
   * @param model the name of the GenAI model to use for generating videos
   * @param prompt the text prompt for generating the videos. Optional for image to video use cases.
   * @param image the input image for generating the videos. Optional if prompt is provided.
   * @param config a {@link com.google.genai.types.GenerateVideosConfig} instance that specifies the
   *     optional configurations
   * @return a {@link com.google.genai.types.GenerateVideosOperation} instance that contains the
   *     generated videos.
   */
  public CompletableFuture<GenerateVideosOperation> generateVideos(
      String model, String prompt, Image image, GenerateVideosConfig config) {
    return generateVideos(model, prompt, image, null, config);
  }

  /**
   * Asynchronously embeds content given a GenAI model and a text string.
   *
   * @param model the name of the GenAI model to use for embedding
   * @param text the text string to send to the embedding model
   * @return a {@link com.google.genai.types.EmbedContentResponse} instance that contains the
   *     embedding.
   */
  public CompletableFuture<EmbedContentResponse> embedContent(
      String model, String text, EmbedContentConfig config) {
    return embedContent(model, ImmutableList.of(text), config);
  }

  /**
   * Asynchronously embeds content given a GenAI model and a list of text strings.
   *
   * @param model the name of the GenAI model to use for embedding
   * @param texts the list of text strings to send to the embedding model
   * @return a {@link com.google.genai.types.EmbedContentResponse} instance that contains the
   *     embedding.
   */
  public CompletableFuture<EmbedContentResponse> embedContent(
      String model, List<String> texts, EmbedContentConfig config) {
    List<Content> contents = new ArrayList<>();
    for (String text : texts) {
      contents.add(Content.fromParts(Part.fromText(text)));
    }
    return privateEmbedContent(model, contents, config);
  }

  /**
   * Asynchronously makes an API request to list the available models.
   *
   * @param config A {@link ListModelsConfig} for configuring the list request.
   * @return A CompletableFuture that resolves to a {@link AsyncPager}. The AsyncPager has a
   *     `forEach` method that can be used to asynchronously process items in the page and
   *     automatically query the next page once the current page is exhausted.
   */
  @SuppressWarnings("PatternMatchingInstanceof")
  public CompletableFuture<AsyncPager<Model>> list(ListModelsConfig config) {
    if (config == null) {
      config = ListModelsConfig.builder().build();
    }
    if (config.filter().isPresent()) {
      throw new IllegalArgumentException("Filter is currently not supported for list models.");
    }
    ListModelsConfig.Builder configBuilder = config.toBuilder();
    if (!config.queryBase().isPresent()) {
      configBuilder.queryBase(true);
    } else if (!config.queryBase().get() && models.apiClient.vertexAI()) {
      configBuilder.filter("labels.tune-type:*");
    }
    final ListModelsConfig updatedConfig = configBuilder.build();

    Function<JsonSerializable, CompletableFuture<JsonNode>> request =
        requestConfig -> {
          if (!(requestConfig instanceof ListModelsConfig)) {
            throw new GenAiIOException(
                "Internal error: Pager expected ListModelsConfig but received "
                    + requestConfig.getClass().getName());
          }
          return this.privateList((ListModelsConfig) requestConfig)
              .thenApply(JsonSerializable::toJsonNode);
        };
    return CompletableFuture.supplyAsync(
        () ->
            new AsyncPager<>(
                Pager.PagedItem.MODELS,
                request,
                (ObjectNode) JsonSerializable.toJsonNode(updatedConfig),
                request.apply(updatedConfig)));
  }
}
