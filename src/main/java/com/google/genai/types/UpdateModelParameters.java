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
import com.google.api.core.InternalApi;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Configuration for updating a tuned model. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = UpdateModelParameters.Builder.class)
public abstract class UpdateModelParameters extends JsonSerializable {
  /** */
  @JsonProperty("model")
  public abstract Optional<String> model();

  /** */
  @JsonProperty("config")
  public abstract Optional<UpdateModelConfig> config();

  /** Instantiates a builder for UpdateModelParameters. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_UpdateModelParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for UpdateModelParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `UpdateModelParameters.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_UpdateModelParameters.Builder();
    }

    /**
     * Setter for model.
     *
     * <p>model:
     */
    @JsonProperty("model")
    public abstract Builder model(String model);

    /**
     * Setter for config.
     *
     * <p>config:
     */
    @JsonProperty("config")
    public abstract Builder config(UpdateModelConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config:
     */
    public Builder config(UpdateModelConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    public abstract UpdateModelParameters build();
  }

  /** Deserializes a JSON string to a UpdateModelParameters object. */
  @ExcludeFromGeneratedCoverageReport
  public static UpdateModelParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, UpdateModelParameters.class);
  }
}
