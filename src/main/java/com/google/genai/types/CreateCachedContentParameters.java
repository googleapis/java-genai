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

/** Parameters for caches.create method. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = CreateCachedContentParameters.Builder.class)
public abstract class CreateCachedContentParameters extends JsonSerializable {
  /** ID of the model to use. Example: gemini-2.0-flash */
  @JsonProperty("model")
  public abstract Optional<String> model();

  /** Configuration that contains optional parameters. */
  @JsonProperty("config")
  public abstract Optional<CreateCachedContentConfig> config();

  /** Instantiates a builder for CreateCachedContentParameters. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_CreateCachedContentParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for CreateCachedContentParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `CreateCachedContentParameters.builder()` for instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_CreateCachedContentParameters.Builder();
    }

    /**
     * Setter for model.
     *
     * <p>model: ID of the model to use. Example: gemini-2.0-flash
     */
    @JsonProperty("model")
    public abstract Builder model(String model);

    /**
     * Setter for config.
     *
     * <p>config: Configuration that contains optional parameters.
     */
    @JsonProperty("config")
    public abstract Builder config(CreateCachedContentConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config: Configuration that contains optional parameters.
     */
    public Builder config(CreateCachedContentConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    public abstract CreateCachedContentParameters build();
  }

  /** Deserializes a JSON string to a CreateCachedContentParameters object. */
  @ExcludeFromGeneratedCoverageReport
  public static CreateCachedContentParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, CreateCachedContentParameters.class);
  }
}
