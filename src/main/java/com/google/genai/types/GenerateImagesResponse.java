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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.List;
import java.util.Optional;

/** The output images response. */
@AutoValue
@JsonDeserialize(builder = AutoValue_GenerateImagesResponse.Builder.class)
public abstract class GenerateImagesResponse extends JsonSerializable {
  /** List of generated images. */
  @JsonProperty("generatedImages")
  public abstract Optional<List<GeneratedImage>> generatedImages();

  /** Instantiates a builder for GenerateImagesResponse. */
  public static Builder builder() {
    return new AutoValue_GenerateImagesResponse.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerateImagesResponse. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("generatedImages")
    public abstract Builder generatedImages(List<GeneratedImage> generatedImages);

    public abstract GenerateImagesResponse build();
  }

  /** Deserializes a JSON string to a GenerateImagesResponse object. */
  public static GenerateImagesResponse fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerateImagesResponse.class);
  }
}
