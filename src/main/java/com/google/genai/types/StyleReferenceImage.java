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
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/**
 * A style reference image.
 *
 * <p>This encapsulates a style reference image provided by the user, and additionally optional
 * config parameters for the style reference image.
 *
 * <p>A raw reference image can also be provided as a destination for the style to be applied to.
 */
@AutoValue
@JsonDeserialize(builder = StyleReferenceImage.Builder.class)
public abstract class StyleReferenceImage extends JsonSerializable implements ReferenceImage {
  /** The reference image for the editing operation. */
  @JsonProperty("referenceImage")
  public abstract Optional<Image> referenceImage();

  /** The id of the reference image. */
  @JsonProperty("referenceId")
  public abstract Optional<Integer> referenceId();

  /** The type of the reference image. Only set by the SDK. */
  @JsonProperty("referenceType")
  public abstract Optional<String> referenceType();

  /** Configuration for the style reference image. */
  @JsonProperty("config")
  public abstract Optional<StyleReferenceConfig> config();

  /** Instantiates a builder for StyleReferenceImage. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_StyleReferenceImage.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for StyleReferenceImage. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `StyleReferenceImage.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_StyleReferenceImage.Builder();
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
    @CanIgnoreReturnValue
    public Builder referenceImage(Image.Builder referenceImageBuilder) {
      return referenceImage(referenceImageBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder referenceImage(Optional<Image> referenceImage);

    /** Clears the value of referenceImage field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearReferenceImage() {
      return referenceImage(Optional.empty());
    }

    /**
     * Setter for referenceId.
     *
     * <p>referenceId: The id of the reference image.
     */
    @JsonProperty("referenceId")
    public abstract Builder referenceId(Integer referenceId);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder referenceId(Optional<Integer> referenceId);

    /** Clears the value of referenceId field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearReferenceId() {
      return referenceId(Optional.empty());
    }

    /**
     * Setter for referenceType.
     *
     * <p>referenceType: The type of the reference image. Only set by the SDK.
     */
    @JsonProperty("referenceType")
    public abstract Builder referenceType(String referenceType);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder referenceType(Optional<String> referenceType);

    /** Clears the value of referenceType field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearReferenceType() {
      return referenceType(Optional.empty());
    }

    /**
     * Setter for config.
     *
     * <p>config: Configuration for the style reference image.
     */
    @JsonProperty("config")
    public abstract Builder config(StyleReferenceConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config: Configuration for the style reference image.
     */
    @CanIgnoreReturnValue
    public Builder config(StyleReferenceConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder config(Optional<StyleReferenceConfig> config);

    /** Clears the value of config field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearConfig() {
      return config(Optional.empty());
    }

    public abstract StyleReferenceImage build();
  }

  /** Deserializes a JSON string to a StyleReferenceImage object. */
  @ExcludeFromGeneratedCoverageReport
  public static StyleReferenceImage fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, StyleReferenceImage.class);
  }

  @Override
  public ReferenceImageAPI toReferenceImageAPI() {
    ReferenceImageAPI.Builder referenceImageAPIBuilder = ReferenceImageAPI.builder();
    referenceImage().ifPresent(referenceImageAPIBuilder::referenceImage);
    referenceId().ifPresent(referenceImageAPIBuilder::referenceId);
    config().ifPresent(referenceImageAPIBuilder::styleImageConfig);
    referenceImageAPIBuilder.referenceType("REFERENCE_TYPE_STYLE");
    return referenceImageAPIBuilder.build();
  }
}
