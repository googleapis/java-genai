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

import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.util.List;
import java.util.Optional;

/** Configuration for a Mask reference image. */
@GenerateTypeAdapter
@AutoValue
public abstract class MaskReferenceConfig extends JsonSerializable {
  /**
   * Prompts the model to generate a mask instead of you needing to provide one (unless
   * MASK_MODE_USER_PROVIDED is used).
   */
  @SerializedName("maskMode")
  public abstract Optional<String> maskMode();

  /**
   * A list of up to 5 class ids to use for semantic segmentation. Automatically creates an image
   * mask based on specific objects.
   */
  @SerializedName("segmentationClasses")
  public abstract Optional<List<Integer>> segmentationClasses();

  /** Dilation percentage of the mask provided. Float between 0 and 1. */
  @SerializedName("maskDilation")
  public abstract Optional<Float> maskDilation();

  /** Instantiates a builder for MaskReferenceConfig. */
  public static Builder builder() {
    return new AutoValue_MaskReferenceConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for MaskReferenceConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("maskMode")
    public abstract Builder maskMode(String maskMode);

    @SerializedName("maskMode")
    abstract Builder maskMode(Optional<String> maskMode);

    @SerializedName("segmentationClasses")
    public abstract Builder segmentationClasses(List<Integer> segmentationClasses);

    @SerializedName("segmentationClasses")
    abstract Builder segmentationClasses(Optional<List<Integer>> segmentationClasses);

    @SerializedName("maskDilation")
    public abstract Builder maskDilation(Float maskDilation);

    @SerializedName("maskDilation")
    abstract Builder maskDilation(Optional<Float> maskDilation);

    public abstract MaskReferenceConfig build();
  }

  /** Deserializes a JSON string to a MaskReferenceConfig object. */
  public static MaskReferenceConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, MaskReferenceConfig.class);
  }
}
