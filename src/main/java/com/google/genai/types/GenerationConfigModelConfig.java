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
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Config for model selection. */
@AutoValue
@JsonDeserialize(builder = GenerationConfigModelConfig.Builder.class)
public abstract class GenerationConfigModelConfig extends JsonSerializable {
  /** Options for feature selection preference. */
  @JsonProperty("featureSelectionPreference")
  public abstract Optional<String> featureSelectionPreference();

  /** Instantiates a builder for GenerationConfigModelConfig. */
  public static Builder builder() {
    return new AutoValue_GenerationConfigModelConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerationConfigModelConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `GenerationConfigModelConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GenerationConfigModelConfig.Builder();
    }

    @JsonProperty("featureSelectionPreference")
    public abstract Builder featureSelectionPreference(String featureSelectionPreference);

    public abstract GenerationConfigModelConfig build();
  }

  /** Deserializes a JSON string to a GenerationConfigModelConfig object. */
  public static GenerationConfigModelConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerationConfigModelConfig.class);
  }
}
