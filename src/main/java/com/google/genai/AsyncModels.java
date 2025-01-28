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
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.GenerateImagesConfig;
import com.google.genai.types.GenerateImagesResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.apache.http.HttpException;

public final class AsyncModels {
  Models models;

  public AsyncModels(ApiClient apiClient) {
    this.models = new Models(apiClient);
  }

  public CompletableFuture<GenerateContentResponse> generateContent(
      String model, List<Content> contents, GenerateContentConfig config) {
    return CompletableFuture.supplyAsync(
        () -> {
          try {
            return models.generateContent(model, contents, config);
          } catch (IOException | HttpException e) {
            throw new RuntimeException(e);
          }
        });
  }

  public CompletableFuture<ResponseStream<GenerateContentResponse>> generateContentStream(
      String model, List<Content> contents, GenerateContentConfig config) {
    return CompletableFuture.supplyAsync(
        () -> {
          try {
            return models.generateContentStream(model, contents, config);
          } catch (IOException | HttpException e) {
            throw new RuntimeException(e);
          }
        });
  }

  public CompletableFuture<GenerateImagesResponse> generateImages(
      String model, String prompt, GenerateImagesConfig config) {
    return CompletableFuture.supplyAsync(
        () -> {
          try {
            return models.generateImages(model, prompt, config);
          } catch (IOException | HttpException e) {
            throw new RuntimeException(e);
          }
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
   * @throws IOException if an I/O error occurs while making the API call
   * @throws HttpException if an HTTP error occurs while making the API call
   */
  public CompletableFuture<GenerateContentResponse> generateContent(
      String model, Content content, GenerateContentConfig config)
      throws IOException, HttpException {
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
   * @throws IOException if an I/O error occurs while making the API call
   * @throws HttpException if an HTTP error occurs while making the API call
   */
  public CompletableFuture<GenerateContentResponse> generateContent(
      String model, String text, GenerateContentConfig config) throws IOException, HttpException {
    return generateContent(model, Transformers.tContents(null, (Object) text), config);
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
   * @throws IOException if an I/O error occurs while making the API call
   * @throws HttpException if an HTTP error occurs while making the API call
   */
  public CompletableFuture<ResponseStream<GenerateContentResponse>> generateContentStream(
      String model, Content content, GenerateContentConfig config)
      throws IOException, HttpException {
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
   * @throws IOException if an I/O error occurs while making the API call
   * @throws HttpException if an HTTP error occurs while making the API call
   */
  public CompletableFuture<ResponseStream<GenerateContentResponse>> generateContentStream(
      String model, String text, GenerateContentConfig config) throws IOException, HttpException {
    return generateContentStream(model, Transformers.tContents(null, (Object) text), config);
  }
}
