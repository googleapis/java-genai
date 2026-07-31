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

/** Config for documents.delete parameters. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = DeleteDocumentParameters.Builder.class)
public abstract class DeleteDocumentParameters extends JsonSerializable {
  /**
   * The resource name of the Document. Example:
   * fileSearchStores/file-search-store-foo/documents/documents-bar
   */
  @JsonProperty("name")
  public abstract Optional<String> name();

  /** Optional parameters for the request. */
  @JsonProperty("config")
  public abstract Optional<DeleteDocumentConfig> config();

  /** Instantiates a builder for DeleteDocumentParameters. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_DeleteDocumentParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for DeleteDocumentParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `DeleteDocumentParameters.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_DeleteDocumentParameters.Builder();
    }

    /**
     * Setter for name.
     *
     * <p>name: The resource name of the Document. Example:
     * fileSearchStores/file-search-store-foo/documents/documents-bar
     */
    @JsonProperty("name")
    public abstract Builder name(String name);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder name(Optional<String> name);

    /** Clears the value of name field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearName() {
      return name(Optional.empty());
    }

    /**
     * Setter for config.
     *
     * <p>config: Optional parameters for the request.
     */
    @JsonProperty("config")
    public abstract Builder config(DeleteDocumentConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config: Optional parameters for the request.
     */
    @CanIgnoreReturnValue
    public Builder config(DeleteDocumentConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder config(Optional<DeleteDocumentConfig> config);

    /** Clears the value of config field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearConfig() {
      return config(Optional.empty());
    }

    public abstract DeleteDocumentParameters build();
  }

  /** Deserializes a JSON string to a DeleteDocumentParameters object. */
  @ExcludeFromGeneratedCoverageReport
  public static DeleteDocumentParameters fromJson(String jsonString) {
    return Common.fromJsonString(jsonString, DeleteDocumentParameters.class);
  }
}
