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

/**
 * A content reference image.
 *
 * <p>A content reference image represents a subject to reference (ex. person, product, animal)
 * provided by the user. It can optionally be provided in addition to a style reference image (ex.
 * background, style reference).
 */
@AutoValue
@JsonDeserialize(builder = ContentReferenceImage.Builder.class)
public abstract class ContentReferenceImage extends JsonSerializable implements ReferenceImage {
  /** The reference image for the editing operation. */
  @JsonProperty("referenceImage")
  public abstract Optional<Image> referenceImage();

  /** The id of the reference image. */
  @JsonProperty("referenceId")
  public abstract Optional<Integer> referenceId();

  /** The type of the reference image. Only set by the SDK. */
  @JsonProperty("referenceType")
  public abstract Optional<String> referenceType();

  /** Instantiates a builder for ContentReferenceImage. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ContentReferenceImage.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ContentReferenceImage. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ContentReferenceImage.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ContentReferenceImage.Builder();
    }

    /**
     * Setter for referenceImage.
     *
     * <p>referenceImage: The reference image for the editing operation.
     */
    @JsonProperty("referenceImage")
    public abstract Builder referenceImage(Image referenceImage);

    /**
     * Setter for referenceImage builder.
     *
     * <p>referenceImage: The reference image for the editing operation.
     */
    public Builder referenceImage(Image.Builder referenceImageBuilder) {
      return referenceImage(referenceImageBuilder.build());
    }

    /**
     * Setter for referenceId.
     *
     * <p>referenceId: The id of the reference image.
     */
    @JsonProperty("referenceId")
    public abstract Builder referenceId(Integer referenceId);

    /**
     * Setter for referenceType.
     *
     * <p>referenceType: The type of the reference image. Only set by the SDK.
     */
    @JsonProperty("referenceType")
    public abstract Builder referenceType(String referenceType);

    public abstract ContentReferenceImage build();
  }

  /** Deserializes a JSON string to a ContentReferenceImage object. */
  @ExcludeFromGeneratedCoverageReport
  public static ContentReferenceImage fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ContentReferenceImage.class);
  }

  @Override
  public ReferenceImageAPI toReferenceImageAPI() {
    ReferenceImageAPI.Builder referenceImageAPIBuilder = ReferenceImageAPI.builder();
    referenceImage().ifPresent(referenceImageAPIBuilder::referenceImage);
    referenceId().ifPresent(referenceImageAPIBuilder::referenceId);
    referenceImageAPIBuilder.referenceType("REFERENCE_TYPE_CONTENT");
    return referenceImageAPIBuilder.build();
  }
}
