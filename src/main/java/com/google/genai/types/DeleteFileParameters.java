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
@JsonDeserialize(builder = DeleteFileParameters.Builder.class)
public abstract class DeleteFileParameters extends JsonSerializable {
  /** The name identifier for the file to be deleted. */
  @JsonProperty("name")
  public abstract Optional<String> name();

  /** Used to override the default configuration. */
  @JsonProperty("config")
  public abstract Optional<DeleteFileConfig> config();

  /** Instantiates a builder for DeleteFileParameters. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_DeleteFileParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for DeleteFileParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `DeleteFileParameters.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_DeleteFileParameters.Builder();
    }

    /**
     * Setter for name.
     *
     * <p>name: The name identifier for the file to be deleted.
     */
    @JsonProperty("name")
    public abstract Builder name(String name);

    /**
     * Setter for config.
     *
     * <p>config: Used to override the default configuration.
     */
    @JsonProperty("config")
    public abstract Builder config(DeleteFileConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config: Used to override the default configuration.
     */
    public Builder config(DeleteFileConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    public abstract DeleteFileParameters build();
  }

  /** Deserializes a JSON string to a DeleteFileParameters object. */
  @ExcludeFromGeneratedCoverageReport
  public static DeleteFileParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, DeleteFileParameters.class);
  }
}
