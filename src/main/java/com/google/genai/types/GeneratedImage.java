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

/** An output image. */
@GenerateTypeAdapter
@AutoValue
public abstract class GeneratedImage extends JsonSerializable {
  /** The output image data. */
  @SerializedName("image")
  public abstract Optional<Image> image();

  /** Responsible AI filter reason if the image is filtered out of the response. */
  @SerializedName("raiFilteredReason")
  public abstract Optional<String> raiFilteredReason();

  /** The rewritten prompt used for the image generation if the prompt enhancer is enabled. */
  @SerializedName("enhancedPrompt")
  public abstract Optional<String> enhancedPrompt();

  /** Instantiates a builder for GeneratedImage. */
  public static Builder builder() {
    return new AutoValue_GeneratedImage.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GeneratedImage. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("image")
    public abstract Builder image(Image image);

    @SerializedName("image")
    abstract Builder image(Optional<Image> image);

    @SerializedName("raiFilteredReason")
    public abstract Builder raiFilteredReason(String raiFilteredReason);

    @SerializedName("raiFilteredReason")
    abstract Builder raiFilteredReason(Optional<String> raiFilteredReason);

    @SerializedName("enhancedPrompt")
    public abstract Builder enhancedPrompt(String enhancedPrompt);

    @SerializedName("enhancedPrompt")
    abstract Builder enhancedPrompt(Optional<String> enhancedPrompt);

    public abstract GeneratedImage build();
  }

  /** Deserializes a JSON string to a GeneratedImage object. */
  public static GeneratedImage fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GeneratedImage.class);
  }
}
