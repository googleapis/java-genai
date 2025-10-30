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

package com.google.genai.types;

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** The output images response. */
@AutoValue
@JsonDeserialize(builder = GenerateImagesResponse.Builder.class)
public abstract class GenerateImagesResponse extends JsonSerializable {
  /** Used to retain the full HTTP response. */
  @JsonProperty("sdkHttpResponse")
  public abstract Optional<HttpResponse> sdkHttpResponse();

  /** List of generated images. */
  @JsonProperty("generatedImages")
  public abstract Optional<List<GeneratedImage>> generatedImages();

  /**
   * Safety attributes of the positive prompt. Only populated if ``include_safety_attributes`` is
   * set to True.
   */
  @JsonProperty("positivePromptSafetyAttributes")
  public abstract Optional<SafetyAttributes> positivePromptSafetyAttributes();

  /** Instantiates a builder for GenerateImagesResponse. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GenerateImagesResponse.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerateImagesResponse. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `GenerateImagesResponse.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GenerateImagesResponse.Builder();
    }

    /**
     * Setter for sdkHttpResponse.
     *
     * <p>sdkHttpResponse: Used to retain the full HTTP response.
     */
    @JsonProperty("sdkHttpResponse")
    public abstract Builder sdkHttpResponse(HttpResponse sdkHttpResponse);

    /**
     * Setter for sdkHttpResponse builder.
     *
     * <p>sdkHttpResponse: Used to retain the full HTTP response.
     */
    public Builder sdkHttpResponse(HttpResponse.Builder sdkHttpResponseBuilder) {
      return sdkHttpResponse(sdkHttpResponseBuilder.build());
    }

    /**
     * Setter for generatedImages.
     *
     * <p>generatedImages: List of generated images.
     */
    @JsonProperty("generatedImages")
    public abstract Builder generatedImages(List<GeneratedImage> generatedImages);

    /**
     * Setter for generatedImages.
     *
     * <p>generatedImages: List of generated images.
     */
    public Builder generatedImages(GeneratedImage... generatedImages) {
      return generatedImages(Arrays.asList(generatedImages));
    }

    /**
     * Setter for generatedImages builder.
     *
     * <p>generatedImages: List of generated images.
     */
    public Builder generatedImages(GeneratedImage.Builder... generatedImagesBuilders) {
      return generatedImages(
          Arrays.asList(generatedImagesBuilders).stream()
              .map(GeneratedImage.Builder::build)
              .collect(toImmutableList()));
    }

    /**
     * Setter for positivePromptSafetyAttributes.
     *
     * <p>positivePromptSafetyAttributes: Safety attributes of the positive prompt. Only populated
     * if ``include_safety_attributes`` is set to True.
     */
    @JsonProperty("positivePromptSafetyAttributes")
    public abstract Builder positivePromptSafetyAttributes(
        SafetyAttributes positivePromptSafetyAttributes);

    /**
     * Setter for positivePromptSafetyAttributes builder.
     *
     * <p>positivePromptSafetyAttributes: Safety attributes of the positive prompt. Only populated
     * if ``include_safety_attributes`` is set to True.
     */
    public Builder positivePromptSafetyAttributes(
        SafetyAttributes.Builder positivePromptSafetyAttributesBuilder) {
      return positivePromptSafetyAttributes(positivePromptSafetyAttributesBuilder.build());
    }

    public abstract GenerateImagesResponse build();
  }

  /** Deserializes a JSON string to a GenerateImagesResponse object. */
  @ExcludeFromGeneratedCoverageReport
  public static GenerateImagesResponse fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerateImagesResponse.class);
  }
}
