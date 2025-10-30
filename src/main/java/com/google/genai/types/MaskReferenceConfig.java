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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** Configuration for a Mask reference image. */
@AutoValue
@JsonDeserialize(builder = MaskReferenceConfig.Builder.class)
public abstract class MaskReferenceConfig extends JsonSerializable {
  /**
   * Prompts the model to generate a mask instead of you needing to provide one (unless
   * MASK_MODE_USER_PROVIDED is used).
   */
  @JsonProperty("maskMode")
  public abstract Optional<MaskReferenceMode> maskMode();

  /**
   * A list of up to 5 class ids to use for semantic segmentation. Automatically creates an image
   * mask based on specific objects.
   */
  @JsonProperty("segmentationClasses")
  public abstract Optional<List<Integer>> segmentationClasses();

  /** Dilation percentage of the mask provided. Float between 0 and 1. */
  @JsonProperty("maskDilation")
  public abstract Optional<Float> maskDilation();

  /** Instantiates a builder for MaskReferenceConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_MaskReferenceConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for MaskReferenceConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `MaskReferenceConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_MaskReferenceConfig.Builder();
    }

    /**
     * Setter for maskMode.
     *
     * <p>maskMode: Prompts the model to generate a mask instead of you needing to provide one
     * (unless MASK_MODE_USER_PROVIDED is used).
     */
    @JsonProperty("maskMode")
    public abstract Builder maskMode(MaskReferenceMode maskMode);

    /**
     * Setter for maskMode given a known enum.
     *
     * <p>maskMode: Prompts the model to generate a mask instead of you needing to provide one
     * (unless MASK_MODE_USER_PROVIDED is used).
     */
    @CanIgnoreReturnValue
    public Builder maskMode(MaskReferenceMode.Known knownType) {
      return maskMode(new MaskReferenceMode(knownType));
    }

    /**
     * Setter for maskMode given a string.
     *
     * <p>maskMode: Prompts the model to generate a mask instead of you needing to provide one
     * (unless MASK_MODE_USER_PROVIDED is used).
     */
    @CanIgnoreReturnValue
    public Builder maskMode(String maskMode) {
      return maskMode(new MaskReferenceMode(maskMode));
    }

    /**
     * Setter for segmentationClasses.
     *
     * <p>segmentationClasses: A list of up to 5 class ids to use for semantic segmentation.
     * Automatically creates an image mask based on specific objects.
     */
    @JsonProperty("segmentationClasses")
    public abstract Builder segmentationClasses(List<Integer> segmentationClasses);

    /**
     * Setter for segmentationClasses.
     *
     * <p>segmentationClasses: A list of up to 5 class ids to use for semantic segmentation.
     * Automatically creates an image mask based on specific objects.
     */
    public Builder segmentationClasses(Integer... segmentationClasses) {
      return segmentationClasses(Arrays.asList(segmentationClasses));
    }

    /**
     * Setter for maskDilation.
     *
     * <p>maskDilation: Dilation percentage of the mask provided. Float between 0 and 1.
     */
    @JsonProperty("maskDilation")
    public abstract Builder maskDilation(Float maskDilation);

    public abstract MaskReferenceConfig build();
  }

  /** Deserializes a JSON string to a MaskReferenceConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static MaskReferenceConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, MaskReferenceConfig.class);
  }
}
