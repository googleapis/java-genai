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

/** Config for file_search_stores.import_file parameters. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = ImportFileParameters.Builder.class)
public abstract class ImportFileParameters extends JsonSerializable {
  /**
   * The resource name of the FileSearchStore. Example: `fileSearchStores/my-file-search-store-123`
   */
  @JsonProperty("fileSearchStoreName")
  public abstract Optional<String> fileSearchStoreName();

  /** The name of the File API File to import. Example: `files/abc-123` */
  @JsonProperty("fileName")
  public abstract Optional<String> fileName();

  /** Optional parameters for the request. */
  @JsonProperty("config")
  public abstract Optional<ImportFileConfig> config();

  /** Instantiates a builder for ImportFileParameters. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ImportFileParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ImportFileParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ImportFileParameters.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ImportFileParameters.Builder();
    }

    /**
     * Setter for fileSearchStoreName.
     *
     * <p>fileSearchStoreName: The resource name of the FileSearchStore. Example:
     * `fileSearchStores/my-file-search-store-123`
     */
    @JsonProperty("fileSearchStoreName")
    public abstract Builder fileSearchStoreName(String fileSearchStoreName);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder fileSearchStoreName(Optional<String> fileSearchStoreName);

    /** Clears the value of fileSearchStoreName field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearFileSearchStoreName() {
      return fileSearchStoreName(Optional.empty());
    }

    /**
     * Setter for fileName.
     *
     * <p>fileName: The name of the File API File to import. Example: `files/abc-123`
     */
    @JsonProperty("fileName")
    public abstract Builder fileName(String fileName);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder fileName(Optional<String> fileName);

    /** Clears the value of fileName field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearFileName() {
      return fileName(Optional.empty());
    }

    /**
     * Setter for config.
     *
     * <p>config: Optional parameters for the request.
     */
    @JsonProperty("config")
    public abstract Builder config(ImportFileConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config: Optional parameters for the request.
     */
    @CanIgnoreReturnValue
    public Builder config(ImportFileConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder config(Optional<ImportFileConfig> config);

    /** Clears the value of config field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearConfig() {
      return config(Optional.empty());
    }

    public abstract ImportFileParameters build();
  }

  /** Deserializes a JSON string to a ImportFileParameters object. */
  @ExcludeFromGeneratedCoverageReport
  public static ImportFileParameters fromJson(String jsonString) {
    return Common.fromJsonString(jsonString, ImportFileParameters.class);
  }
}
