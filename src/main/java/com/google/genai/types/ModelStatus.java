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
import java.time.Instant;
import java.util.Optional;

/**
 * The status of the underlying model. This is used to indicate the stage of the underlying model
 * and the retirement time if applicable. This data type is not supported in Vertex AI.
 */
@AutoValue
@JsonDeserialize(builder = ModelStatus.Builder.class)
public abstract class ModelStatus extends JsonSerializable {
  /** A message explaining the model status. */
  @JsonProperty("message")
  public abstract Optional<String> message();

  /** The stage of the underlying model. */
  @JsonProperty("modelStage")
  public abstract Optional<ModelStage> modelStage();

  /** The time at which the model will be retired. */
  @JsonProperty("retirementTime")
  public abstract Optional<Instant> retirementTime();

  /** Instantiates a builder for ModelStatus. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ModelStatus.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ModelStatus. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ModelStatus.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ModelStatus.Builder();
    }

    /**
     * Setter for message.
     *
     * <p>message: A message explaining the model status.
     */
    @JsonProperty("message")
    public abstract Builder message(String message);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder message(Optional<String> message);

    /** Clears the value of message field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMessage() {
      return message(Optional.empty());
    }

    /**
     * Setter for modelStage.
     *
     * <p>modelStage: The stage of the underlying model.
     */
    @JsonProperty("modelStage")
    public abstract Builder modelStage(ModelStage modelStage);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder modelStage(Optional<ModelStage> modelStage);

    /** Clears the value of modelStage field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearModelStage() {
      return modelStage(Optional.empty());
    }

    /**
     * Setter for modelStage given a known enum.
     *
     * <p>modelStage: The stage of the underlying model.
     */
    @CanIgnoreReturnValue
    public Builder modelStage(ModelStage.Known knownType) {
      return modelStage(new ModelStage(knownType));
    }

    /**
     * Setter for modelStage given a string.
     *
     * <p>modelStage: The stage of the underlying model.
     */
    @CanIgnoreReturnValue
    public Builder modelStage(String modelStage) {
      return modelStage(new ModelStage(modelStage));
    }

    /**
     * Setter for retirementTime.
     *
     * <p>retirementTime: The time at which the model will be retired.
     */
    @JsonProperty("retirementTime")
    public abstract Builder retirementTime(Instant retirementTime);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder retirementTime(Optional<Instant> retirementTime);

    /** Clears the value of retirementTime field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRetirementTime() {
      return retirementTime(Optional.empty());
    }

    public abstract ModelStatus build();
  }

  /** Deserializes a JSON string to a ModelStatus object. */
  @ExcludeFromGeneratedCoverageReport
  public static ModelStatus fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ModelStatus.class);
  }
}
