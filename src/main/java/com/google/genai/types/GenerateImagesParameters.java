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

/** The parameters for generating images. */
@GenerateTypeAdapter
@AutoValue
public abstract class GenerateImagesParameters extends JsonSerializable {
  /**
   * ID of the model to use. For a list of models, see `Google models
   * <https://cloud.google.com/vertex-ai/generative-ai/docs/learn/models>`_.
   */
  @SerializedName("model")
  public abstract Optional<String> model();

  /** Text prompt that typically describes the images to output. */
  @SerializedName("prompt")
  public abstract Optional<String> prompt();

  /** Configuration for generating images. */
  @SerializedName("config")
  public abstract Optional<GenerateImagesConfig> config();

  /** Instantiates a builder for GenerateImagesParameters. */
  public static Builder builder() {
    return new AutoValue_GenerateImagesParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerateImagesParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("model")
    public abstract Builder model(String model);

    @SerializedName("model")
    abstract Builder model(Optional<String> model);

    @SerializedName("prompt")
    public abstract Builder prompt(String prompt);

    @SerializedName("prompt")
    abstract Builder prompt(Optional<String> prompt);

    @SerializedName("config")
    public abstract Builder config(GenerateImagesConfig config);

    @SerializedName("config")
    abstract Builder config(Optional<GenerateImagesConfig> config);

    public abstract GenerateImagesParameters build();
  }

  /** Deserializes a JSON string to a GenerateImagesParameters object. */
  public static GenerateImagesParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerateImagesParameters.class);
  }
}
