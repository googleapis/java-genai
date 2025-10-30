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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** An image of the product. */
@AutoValue
@JsonDeserialize(builder = ProductImage.Builder.class)
public abstract class ProductImage extends JsonSerializable {
  /** An image of the product to be recontextualized. */
  @JsonProperty("productImage")
  public abstract Optional<Image> productImage();

  /** Instantiates a builder for ProductImage. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ProductImage.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ProductImage. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ProductImage.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ProductImage.Builder();
    }

    /**
     * Setter for productImage.
     *
     * <p>productImage: An image of the product to be recontextualized.
     */
    @JsonProperty("productImage")
    public abstract Builder productImage(Image productImage);

    /**
     * Setter for productImage builder.
     *
     * <p>productImage: An image of the product to be recontextualized.
     */
    public Builder productImage(Image.Builder productImageBuilder) {
      return productImage(productImageBuilder.build());
    }

    public abstract ProductImage build();
  }

  /** Deserializes a JSON string to a ProductImage object. */
  @ExcludeFromGeneratedCoverageReport
  public static ProductImage fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ProductImage.class);
  }
}
