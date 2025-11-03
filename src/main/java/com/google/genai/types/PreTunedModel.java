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

/** A pre-tuned model for continuous tuning. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = PreTunedModel.Builder.class)
public abstract class PreTunedModel extends JsonSerializable {
  /** Output only. The name of the base model this PreTunedModel was tuned from. */
  @JsonProperty("baseModel")
  public abstract Optional<String> baseModel();

  /** Optional. The source checkpoint id. If not specified, the default checkpoint will be used. */
  @JsonProperty("checkpointId")
  public abstract Optional<String> checkpointId();

  /**
   * The resource name of the Model. E.g., a model resource name with a specified version id or
   * alias: `projects/{project}/locations/{location}/models/{model}@{version_id}`
   * `projects/{project}/locations/{location}/models/{model}@{alias}` Or, omit the version id to use
   * the default version: `projects/{project}/locations/{location}/models/{model}`
   */
  @JsonProperty("tunedModelName")
  public abstract Optional<String> tunedModelName();

  /** Instantiates a builder for PreTunedModel. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_PreTunedModel.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for PreTunedModel. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `PreTunedModel.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_PreTunedModel.Builder();
    }

    /**
     * Setter for baseModel.
     *
     * <p>baseModel: Output only. The name of the base model this PreTunedModel was tuned from.
     */
    @JsonProperty("baseModel")
    public abstract Builder baseModel(String baseModel);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder baseModel(Optional<String> baseModel);

    /** Clears the value of baseModel field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearBaseModel() {
      return baseModel(Optional.empty());
    }

    /**
     * Setter for checkpointId.
     *
     * <p>checkpointId: Optional. The source checkpoint id. If not specified, the default checkpoint
     * will be used.
     */
    @JsonProperty("checkpointId")
    public abstract Builder checkpointId(String checkpointId);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder checkpointId(Optional<String> checkpointId);

    /** Clears the value of checkpointId field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCheckpointId() {
      return checkpointId(Optional.empty());
    }

    /**
     * Setter for tunedModelName.
     *
     * <p>tunedModelName: The resource name of the Model. E.g., a model resource name with a
     * specified version id or alias:
     * `projects/{project}/locations/{location}/models/{model}@{version_id}`
     * `projects/{project}/locations/{location}/models/{model}@{alias}` Or, omit the version id to
     * use the default version: `projects/{project}/locations/{location}/models/{model}`
     */
    @JsonProperty("tunedModelName")
    public abstract Builder tunedModelName(String tunedModelName);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder tunedModelName(Optional<String> tunedModelName);

    /** Clears the value of tunedModelName field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTunedModelName() {
      return tunedModelName(Optional.empty());
    }

    public abstract PreTunedModel build();
  }

  /** Deserializes a JSON string to a PreTunedModel object. */
  @ExcludeFromGeneratedCoverageReport
  public static PreTunedModel fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, PreTunedModel.class);
  }
}
