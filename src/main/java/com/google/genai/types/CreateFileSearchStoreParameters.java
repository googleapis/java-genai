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
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.Common;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Config for file_search_stores.create parameters. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = CreateFileSearchStoreParameters.Builder.class)
public abstract class CreateFileSearchStoreParameters extends JsonSerializable {
  /** Optional parameters for creating a file search store. */
  @JsonProperty("config")
  public abstract Optional<CreateFileSearchStoreConfig> config();

  /** Instantiates a builder for CreateFileSearchStoreParameters. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_CreateFileSearchStoreParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for CreateFileSearchStoreParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `CreateFileSearchStoreParameters.builder()` for instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_CreateFileSearchStoreParameters.Builder();
    }

    /**
     * Setter for config.
     *
     * <p>config: Optional parameters for creating a file search store.
     */
    @JsonProperty("config")
    public abstract Builder config(CreateFileSearchStoreConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config: Optional parameters for creating a file search store.
     */
    @CanIgnoreReturnValue
    public Builder config(CreateFileSearchStoreConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder config(Optional<CreateFileSearchStoreConfig> config);

    /** Clears the value of config field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearConfig() {
      return config(Optional.empty());
    }

    public abstract CreateFileSearchStoreParameters build();
  }

  /** Deserializes a JSON string to a CreateFileSearchStoreParameters object. */
  @ExcludeFromGeneratedCoverageReport
  public static CreateFileSearchStoreParameters fromJson(String jsonString) {
    return Common.fromJsonString(jsonString, CreateFileSearchStoreParameters.class);
  }
}
