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

/** A set of source input(s) for image recontextualization. */
@AutoValue
@JsonDeserialize(builder = RecontextImageSource.Builder.class)
public abstract class RecontextImageSource extends JsonSerializable {
  /**
   * A text prompt for guiding the model during image recontextualization. Not supported for Virtual
   * Try-On.
   */
  @JsonProperty("prompt")
  public abstract Optional<String> prompt();

  /** Image of the person or subject who will be wearing the product(s). */
  @JsonProperty("personImage")
  public abstract Optional<Image> personImage();

  /** A list of product images. */
  @JsonProperty("productImages")
  public abstract Optional<List<ProductImage>> productImages();

  /** Instantiates a builder for RecontextImageSource. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_RecontextImageSource.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for RecontextImageSource. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `RecontextImageSource.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_RecontextImageSource.Builder();
    }

    /**
     * Setter for prompt.
     *
     * <p>prompt: A text prompt for guiding the model during image recontextualization. Not
     * supported for Virtual Try-On.
     */
    @JsonProperty("prompt")
    public abstract Builder prompt(String prompt);

    /**
     * Setter for personImage.
     *
     * <p>personImage: Image of the person or subject who will be wearing the product(s).
     */
    @JsonProperty("personImage")
    public abstract Builder personImage(Image personImage);

    /**
     * Setter for personImage builder.
     *
     * <p>personImage: Image of the person or subject who will be wearing the product(s).
     */
    public Builder personImage(Image.Builder personImageBuilder) {
      return personImage(personImageBuilder.build());
    }

    /**
     * Setter for productImages.
     *
     * <p>productImages: A list of product images.
     */
    @JsonProperty("productImages")
    public abstract Builder productImages(List<ProductImage> productImages);

    /**
     * Setter for productImages.
     *
     * <p>productImages: A list of product images.
     */
    public Builder productImages(ProductImage... productImages) {
      return productImages(Arrays.asList(productImages));
    }

    /**
     * Setter for productImages builder.
     *
     * <p>productImages: A list of product images.
     */
    public Builder productImages(ProductImage.Builder... productImagesBuilders) {
      return productImages(
          Arrays.asList(productImagesBuilders).stream()
              .map(ProductImage.Builder::build)
              .collect(toImmutableList()));
    }

    public abstract RecontextImageSource build();
  }

  /** Deserializes a JSON string to a RecontextImageSource object. */
  @ExcludeFromGeneratedCoverageReport
  public static RecontextImageSource fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, RecontextImageSource.class);
  }
}
