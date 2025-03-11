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
import java.util.Optional;

/**
 * A mask reference image.
 *
 * <p>This encapsulates either a mask image provided by the user and configs for the user provided
 * mask, or only config parameters for the model to generate a mask.
 *
 * <p>A mask image is an image whose non-zero values indicate where to edit the base image. If the
 * user provides a mask image, the mask must be in the same dimensions as the raw image.
 */
@GenerateTypeAdapter
@AutoValue
public abstract class MaskReferenceImage extends JsonSerializable {
  /** The reference image for the editing operation. */
  @SerializedName("referenceImage")
  public abstract Optional<Image> referenceImage();

  /** The id of the reference image. */
  @SerializedName("referenceId")
  public abstract Optional<Integer> referenceId();

  /** The type of the reference image. Only set by the SDK. */
  @SerializedName("referenceType")
  public abstract Optional<String> referenceType();

  /** Configuration for the mask reference image. */
  @SerializedName("config")
  public abstract Optional<MaskReferenceConfig> config();

  /** Instantiates a builder for MaskReferenceImage. */
  public static Builder builder() {
    return new AutoValue_MaskReferenceImage.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for MaskReferenceImage. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("referenceImage")
    public abstract Builder referenceImage(Image referenceImage);

    @SerializedName("referenceImage")
    abstract Builder referenceImage(Optional<Image> referenceImage);

    @SerializedName("referenceId")
    public abstract Builder referenceId(Integer referenceId);

    @SerializedName("referenceId")
    abstract Builder referenceId(Optional<Integer> referenceId);

    @SerializedName("referenceType")
    public abstract Builder referenceType(String referenceType);

    @SerializedName("referenceType")
    abstract Builder referenceType(Optional<String> referenceType);

    @SerializedName("config")
    public abstract Builder config(MaskReferenceConfig config);

    @SerializedName("config")
    abstract Builder config(Optional<MaskReferenceConfig> config);

    public abstract MaskReferenceImage build();
  }

  /** Deserializes a JSON string to a MaskReferenceImage object. */
  public static MaskReferenceImage fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, MaskReferenceImage.class);
  }
}
