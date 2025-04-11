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

import com.google.genai.types.Content;
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
import com.google.genai.types.Image;
import com.google.genai.types.ReferenceImage;
import com.google.genai.types.UpscaleImageConfig;
import com.google.genai.types.UpscaleImageResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class AsyncModels {
  Models models;

  public AsyncModels(ApiClient apiClient) {
    this.models = new Models(apiClient);
  }

  public CompletableFuture<GenerateVideosOperation> generateVideos(
      String model, String prompt, Image image, GenerateVideosConfig config) {
    return CompletableFuture.supplyAsync(() -> models.generateVideos(model, prompt, image, config));
  }

  /**
   * Asynchronously generates content given a GenAI model and a content object.
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
    return CompletableFuture.supplyAsync(() -> models.generateContent(model, contents, config));
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
    return generateContent(model, Transformers.tContents(null, (Object) content), config);
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
    return generateContent(model, Transformers.tContents(null, (Object) text), config);
  }

  /**
   * Asynchronously generates content with streaming support given a GenAI model and a content
   * object.
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
    return CompletableFuture.supplyAsync(
        () -> models.generateContentStream(model, contents, config));
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
    return generateContentStream(model, Transformers.tContents(null, (Object) content), config);
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
    return generateContentStream(model, Transformers.tContents(null, (Object) text), config);
  }

  /**
   * Asynchronously generates images given a GenAI model and a prompt.
   *
   * @param model the name of the GenAI model to use for upscaling
   * @param prompt the factor to upscale the image
   * @param config a {@link com.google.genai.types.GenerateImagesConfig} instance that specifies the
   *     optional configurations
   * @return a {@link com.google.genai.types.GenerateImagesResponse} instance that contains the
   *     generated images.
   */
  public CompletableFuture<GenerateImagesResponse> generateImages(
      String model, String prompt, GenerateImagesConfig config) {
    return CompletableFuture.supplyAsync(() -> models.generateImages(model, prompt, config));
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
   * @return a {@link com.google.genai.types.EditImageResponse} instance that contains the upscaled
   *     image.
   */
  public CompletableFuture<EditImageResponse> editImage(
      String model, String prompt, List<ReferenceImage> referenceImages, EditImageConfig config) {

    return CompletableFuture.supplyAsync(
        () -> models.editImage(model, prompt, referenceImages, config));
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
    return CompletableFuture.supplyAsync(
        () -> models.upscaleImage(model, image, upscaleFactor, config));
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
    return CompletableFuture.supplyAsync(() -> models.embedContent(model, text, config));
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
    return CompletableFuture.supplyAsync(() -> models.embedContent(model, texts, config));
  }
}
