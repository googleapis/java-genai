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

/** Response for the request to edit an image. */
@AutoValue
@JsonDeserialize(builder = EditImageResponse.Builder.class)
public abstract class EditImageResponse extends JsonSerializable {
  /** Used to retain the full HTTP response. */
  @JsonProperty("sdkHttpResponse")
  public abstract Optional<HttpResponse> sdkHttpResponse();

  /** Generated images. */
  @JsonProperty("generatedImages")
  public abstract Optional<List<GeneratedImage>> generatedImages();

  /** Instantiates a builder for EditImageResponse. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_EditImageResponse.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for EditImageResponse. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `EditImageResponse.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_EditImageResponse.Builder();
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
     * <p>generatedImages: Generated images.
     */
    @JsonProperty("generatedImages")
    public abstract Builder generatedImages(List<GeneratedImage> generatedImages);

    /**
     * Setter for generatedImages.
     *
     * <p>generatedImages: Generated images.
     */
    public Builder generatedImages(GeneratedImage... generatedImages) {
      return generatedImages(Arrays.asList(generatedImages));
    }

    /**
     * Setter for generatedImages builder.
     *
     * <p>generatedImages: Generated images.
     */
    public Builder generatedImages(GeneratedImage.Builder... generatedImagesBuilders) {
      return generatedImages(
          Arrays.asList(generatedImagesBuilders).stream()
              .map(GeneratedImage.Builder::build)
              .collect(toImmutableList()));
    }

    public abstract EditImageResponse build();
  }

  /** Deserializes a JSON string to a EditImageResponse object. */
  @ExcludeFromGeneratedCoverageReport
  public static EditImageResponse fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, EditImageResponse.class);
  }
}
