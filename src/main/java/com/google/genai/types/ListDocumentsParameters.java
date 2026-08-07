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

/** Config for documents.list parameters. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = ListDocumentsParameters.Builder.class)
public abstract class ListDocumentsParameters extends JsonSerializable {
  /** The resource name of the FileSearchStores. Example: `fileSearchStore/file-search-store-foo` */
  @JsonProperty("parent")
  public abstract Optional<String> parent();

  /** */
  @JsonProperty("config")
  public abstract Optional<ListDocumentsConfig> config();

  /** Instantiates a builder for ListDocumentsParameters. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ListDocumentsParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ListDocumentsParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ListDocumentsParameters.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ListDocumentsParameters.Builder();
    }

    /**
     * Setter for parent.
     *
     * <p>parent: The resource name of the FileSearchStores. Example:
     * `fileSearchStore/file-search-store-foo`
     */
    @JsonProperty("parent")
    public abstract Builder parent(String parent);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder parent(Optional<String> parent);

    /** Clears the value of parent field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearParent() {
      return parent(Optional.empty());
    }

    /**
     * Setter for config.
     *
     * <p>config:
     */
    @JsonProperty("config")
    public abstract Builder config(ListDocumentsConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config:
     */
    @CanIgnoreReturnValue
    public Builder config(ListDocumentsConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder config(Optional<ListDocumentsConfig> config);

    /** Clears the value of config field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearConfig() {
      return config(Optional.empty());
    }

    public abstract ListDocumentsParameters build();
  }

  /** Deserializes a JSON string to a ListDocumentsParameters object. */
  @ExcludeFromGeneratedCoverageReport
  public static ListDocumentsParameters fromJson(String jsonString) {
    return Common.fromJsonString(jsonString, ListDocumentsParameters.class);
  }
}
