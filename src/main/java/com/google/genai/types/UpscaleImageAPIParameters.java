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

/** API parameters for UpscaleImage. */
@GenerateTypeAdapter
@AutoValue
public abstract class UpscaleImageAPIParameters extends JsonSerializable {
  /** The model to use. */
  @SerializedName("model")
  public abstract Optional<String> model();

  /** The input image to upscale. */
  @SerializedName("image")
  public abstract Optional<Image> image();

  /** The factor to upscale the image (x2 or x4). */
  @SerializedName("upscaleFactor")
  public abstract Optional<String> upscaleFactor();

  /** Configuration for upscaling. */
  @SerializedName("config")
  public abstract Optional<UpscaleImageAPIConfig> config();

  /** Instantiates a builder for UpscaleImageAPIParameters. */
  public static Builder builder() {
    return new AutoValue_UpscaleImageAPIParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for UpscaleImageAPIParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("model")
    public abstract Builder model(String model);

    @SerializedName("model")
    abstract Builder model(Optional<String> model);

    @SerializedName("image")
    public abstract Builder image(Image image);

    @SerializedName("image")
    abstract Builder image(Optional<Image> image);

    @SerializedName("upscaleFactor")
    public abstract Builder upscaleFactor(String upscaleFactor);

    @SerializedName("upscaleFactor")
    abstract Builder upscaleFactor(Optional<String> upscaleFactor);

    @SerializedName("config")
    public abstract Builder config(UpscaleImageAPIConfig config);

    @SerializedName("config")
    abstract Builder config(Optional<UpscaleImageAPIConfig> config);

    public abstract UpscaleImageAPIParameters build();
  }

  /** Deserializes a JSON string to a UpscaleImageAPIParameters object. */
  public static UpscaleImageAPIParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, UpscaleImageAPIParameters.class);
  }
}
