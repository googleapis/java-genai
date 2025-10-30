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

/** Generates the parameters for the get method. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = GetFileParameters.Builder.class)
public abstract class GetFileParameters extends JsonSerializable {
  /** The name identifier for the file to retrieve. */
  @JsonProperty("name")
  public abstract Optional<String> name();

  /** Used to override the default configuration. */
  @JsonProperty("config")
  public abstract Optional<GetFileConfig> config();

  /** Instantiates a builder for GetFileParameters. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GetFileParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GetFileParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `GetFileParameters.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GetFileParameters.Builder();
    }

    /**
     * Setter for name.
     *
     * <p>name: The name identifier for the file to retrieve.
     */
    @JsonProperty("name")
    public abstract Builder name(String name);

    /**
     * Setter for config.
     *
     * <p>config: Used to override the default configuration.
     */
    @JsonProperty("config")
    public abstract Builder config(GetFileConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config: Used to override the default configuration.
     */
    public Builder config(GetFileConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    public abstract GetFileParameters build();
  }

  /** Deserializes a JSON string to a GetFileParameters object. */
  @ExcludeFromGeneratedCoverageReport
  public static GetFileParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GetFileParameters.class);
  }
}
