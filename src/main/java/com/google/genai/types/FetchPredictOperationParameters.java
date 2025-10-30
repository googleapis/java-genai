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

/** Parameters for the fetchPredictOperation method. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = FetchPredictOperationParameters.Builder.class)
public abstract class FetchPredictOperationParameters extends JsonSerializable {
  /** The server-assigned name for the operation. */
  @JsonProperty("operationName")
  public abstract Optional<String> operationName();

  /** */
  @JsonProperty("resourceName")
  public abstract Optional<String> resourceName();

  /** Used to override the default configuration. */
  @JsonProperty("config")
  public abstract Optional<FetchPredictOperationConfig> config();

  /** Instantiates a builder for FetchPredictOperationParameters. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_FetchPredictOperationParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for FetchPredictOperationParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `FetchPredictOperationParameters.builder()` for instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_FetchPredictOperationParameters.Builder();
    }

    /**
     * Setter for operationName.
     *
     * <p>operationName: The server-assigned name for the operation.
     */
    @JsonProperty("operationName")
    public abstract Builder operationName(String operationName);

    /**
     * Setter for resourceName.
     *
     * <p>resourceName:
     */
    @JsonProperty("resourceName")
    public abstract Builder resourceName(String resourceName);

    /**
     * Setter for config.
     *
     * <p>config: Used to override the default configuration.
     */
    @JsonProperty("config")
    public abstract Builder config(FetchPredictOperationConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config: Used to override the default configuration.
     */
    public Builder config(FetchPredictOperationConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    public abstract FetchPredictOperationParameters build();
  }

  /** Deserializes a JSON string to a FetchPredictOperationParameters object. */
  @ExcludeFromGeneratedCoverageReport
  public static FetchPredictOperationParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, FetchPredictOperationParameters.class);
  }
}
