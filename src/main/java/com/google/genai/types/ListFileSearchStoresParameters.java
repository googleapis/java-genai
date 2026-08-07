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

/** Config for file_search_stores.list parameters. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = ListFileSearchStoresParameters.Builder.class)
public abstract class ListFileSearchStoresParameters extends JsonSerializable {
  /** Optional parameters for the list request. */
  @JsonProperty("config")
  public abstract Optional<ListFileSearchStoresConfig> config();

  /** Instantiates a builder for ListFileSearchStoresParameters. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ListFileSearchStoresParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ListFileSearchStoresParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `ListFileSearchStoresParameters.builder()` for instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ListFileSearchStoresParameters.Builder();
    }

    /**
     * Setter for config.
     *
     * <p>config: Optional parameters for the list request.
     */
    @JsonProperty("config")
    public abstract Builder config(ListFileSearchStoresConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config: Optional parameters for the list request.
     */
    @CanIgnoreReturnValue
    public Builder config(ListFileSearchStoresConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder config(Optional<ListFileSearchStoresConfig> config);

    /** Clears the value of config field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearConfig() {
      return config(Optional.empty());
    }

    public abstract ListFileSearchStoresParameters build();
  }

  /** Deserializes a JSON string to a ListFileSearchStoresParameters object. */
  @ExcludeFromGeneratedCoverageReport
  public static ListFileSearchStoresParameters fromJson(String jsonString) {
    return Common.fromJsonString(jsonString, ListFileSearchStoresParameters.class);
  }
}
