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

/** None */
@GenerateTypeAdapter
@AutoValue
public abstract class UpscaleImageResponse extends JsonSerializable {
  /** Generated images. */
  @SerializedName("generatedImages")
  public abstract Optional<List<GeneratedImage>> generatedImages();

  /** Instantiates a builder for UpscaleImageResponse. */
  public static Builder builder() {
    return new AutoValue_UpscaleImageResponse.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for UpscaleImageResponse. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("generatedImages")
    public abstract Builder generatedImages(List<GeneratedImage> generatedImages);

    @SerializedName("generatedImages")
    abstract Builder generatedImages(Optional<List<GeneratedImage>> generatedImages);

    public abstract UpscaleImageResponse build();
  }

  /** Deserializes a JSON string to a UpscaleImageResponse object. */
  public static UpscaleImageResponse fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, UpscaleImageResponse.class);
  }
}
