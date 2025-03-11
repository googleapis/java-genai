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

/** When manual routing is set, the specified model will be used directly. */
@GenerateTypeAdapter
@AutoValue
public abstract class GenerationConfigRoutingConfigManualRoutingMode extends JsonSerializable {
  /** The model name to use. Only the public LLM models are accepted. e.g. 'gemini-1.5-pro-001'. */
  @SerializedName("modelName")
  public abstract Optional<String> modelName();

  /** Instantiates a builder for GenerationConfigRoutingConfigManualRoutingMode. */
  public static Builder builder() {
    return new AutoValue_GenerationConfigRoutingConfigManualRoutingMode.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerationConfigRoutingConfigManualRoutingMode. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("modelName")
    public abstract Builder modelName(String modelName);

    @SerializedName("modelName")
    abstract Builder modelName(Optional<String> modelName);

    public abstract GenerationConfigRoutingConfigManualRoutingMode build();
  }

  /** Deserializes a JSON string to a GenerationConfigRoutingConfigManualRoutingMode object. */
  public static GenerationConfigRoutingConfigManualRoutingMode fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(
        jsonString, GenerationConfigRoutingConfigManualRoutingMode.class);
  }
}
