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

/** Config for models.generate_content parameters. */
@GenerateTypeAdapter
@AutoValue
public abstract class GenerateContentParameters extends JsonSerializable {
  /**
   * ID of the model to use. For a list of models, see `Google models
   * <https://cloud.google.com/vertex-ai/generative-ai/docs/learn/models>`_.
   */
  @SerializedName("model")
  public abstract Optional<String> model();

  /** Content of the request. */
  @SerializedName("contents")
  public abstract Optional<List<Content>> contents();

  /** Configuration that contains optional model parameters. */
  @SerializedName("config")
  public abstract Optional<GenerateContentConfig> config();

  /** Instantiates a builder for GenerateContentParameters. */
  public static Builder builder() {
    return new AutoValue_GenerateContentParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerateContentParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("model")
    public abstract Builder model(String model);

    @SerializedName("model")
    abstract Builder model(Optional<String> model);

    @SerializedName("contents")
    public abstract Builder contents(List<Content> contents);

    @SerializedName("contents")
    abstract Builder contents(Optional<List<Content>> contents);

    @SerializedName("config")
    public abstract Builder config(GenerateContentConfig config);

    @SerializedName("config")
    abstract Builder config(Optional<GenerateContentConfig> config);

    public abstract GenerateContentParameters build();
  }

  /** Deserializes a JSON string to a GenerateContentParameters object. */
  public static GenerateContentParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerateContentParameters.class);
  }
}
