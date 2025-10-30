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
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Config for model selection. */
@AutoValue
@JsonDeserialize(builder = ModelSelectionConfig.Builder.class)
public abstract class ModelSelectionConfig extends JsonSerializable {
  /** Options for feature selection preference. */
  @JsonProperty("featureSelectionPreference")
  public abstract Optional<FeatureSelectionPreference> featureSelectionPreference();

  /** Instantiates a builder for ModelSelectionConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ModelSelectionConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ModelSelectionConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ModelSelectionConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ModelSelectionConfig.Builder();
    }

    /**
     * Setter for featureSelectionPreference.
     *
     * <p>featureSelectionPreference: Options for feature selection preference.
     */
    @JsonProperty("featureSelectionPreference")
    public abstract Builder featureSelectionPreference(
        FeatureSelectionPreference featureSelectionPreference);

    /**
     * Setter for featureSelectionPreference given a known enum.
     *
     * <p>featureSelectionPreference: Options for feature selection preference.
     */
    @CanIgnoreReturnValue
    public Builder featureSelectionPreference(FeatureSelectionPreference.Known knownType) {
      return featureSelectionPreference(new FeatureSelectionPreference(knownType));
    }

    /**
     * Setter for featureSelectionPreference given a string.
     *
     * <p>featureSelectionPreference: Options for feature selection preference.
     */
    @CanIgnoreReturnValue
    public Builder featureSelectionPreference(String featureSelectionPreference) {
      return featureSelectionPreference(new FeatureSelectionPreference(featureSelectionPreference));
    }

    public abstract ModelSelectionConfig build();
  }

  /** Deserializes a JSON string to a ModelSelectionConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static ModelSelectionConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ModelSelectionConfig.class);
  }
}
