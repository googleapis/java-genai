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

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.Common;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** Optional parameters for importing a file. */
@AutoValue
@JsonDeserialize(builder = ImportFileConfig.Builder.class)
public abstract class ImportFileConfig extends JsonSerializable {
  /** Used to override HTTP request options. */
  @JsonProperty("httpOptions")
  public abstract Optional<HttpOptions> httpOptions();

  /** User provided custom metadata stored as key-value pairs used for querying. */
  @JsonProperty("customMetadata")
  public abstract Optional<List<CustomMetadata>> customMetadata();

  /** Config for telling the service how to chunk the file. */
  @JsonProperty("chunkingConfig")
  public abstract Optional<ChunkingConfig> chunkingConfig();

  /** Instantiates a builder for ImportFileConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ImportFileConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ImportFileConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ImportFileConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ImportFileConfig.Builder();
    }

    /**
     * Setter for httpOptions.
     *
     * <p>httpOptions: Used to override HTTP request options.
     */
    @JsonProperty("httpOptions")
    public abstract Builder httpOptions(HttpOptions httpOptions);

    /**
     * Setter for httpOptions builder.
     *
     * <p>httpOptions: Used to override HTTP request options.
     */
    @CanIgnoreReturnValue
    public Builder httpOptions(HttpOptions.Builder httpOptionsBuilder) {
      return httpOptions(httpOptionsBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder httpOptions(Optional<HttpOptions> httpOptions);

    /** Clears the value of httpOptions field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearHttpOptions() {
      return httpOptions(Optional.empty());
    }

    /**
     * Setter for customMetadata.
     *
     * <p>customMetadata: User provided custom metadata stored as key-value pairs used for querying.
     */
    @JsonProperty("customMetadata")
    public abstract Builder customMetadata(List<CustomMetadata> customMetadata);

    /**
     * Setter for customMetadata.
     *
     * <p>customMetadata: User provided custom metadata stored as key-value pairs used for querying.
     */
    @CanIgnoreReturnValue
    public Builder customMetadata(CustomMetadata... customMetadata) {
      return customMetadata(Arrays.asList(customMetadata));
    }

    /**
     * Setter for customMetadata builder.
     *
     * <p>customMetadata: User provided custom metadata stored as key-value pairs used for querying.
     */
    @CanIgnoreReturnValue
    public Builder customMetadata(CustomMetadata.Builder... customMetadataBuilders) {
      return customMetadata(
          Arrays.asList(customMetadataBuilders).stream()
              .map(CustomMetadata.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder customMetadata(Optional<List<CustomMetadata>> customMetadata);

    /** Clears the value of customMetadata field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCustomMetadata() {
      return customMetadata(Optional.empty());
    }

    /**
     * Setter for chunkingConfig.
     *
     * <p>chunkingConfig: Config for telling the service how to chunk the file.
     */
    @JsonProperty("chunkingConfig")
    public abstract Builder chunkingConfig(ChunkingConfig chunkingConfig);

    /**
     * Setter for chunkingConfig builder.
     *
     * <p>chunkingConfig: Config for telling the service how to chunk the file.
     */
    @CanIgnoreReturnValue
    public Builder chunkingConfig(ChunkingConfig.Builder chunkingConfigBuilder) {
      return chunkingConfig(chunkingConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder chunkingConfig(Optional<ChunkingConfig> chunkingConfig);

    /** Clears the value of chunkingConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearChunkingConfig() {
      return chunkingConfig(Optional.empty());
    }

    public abstract ImportFileConfig build();
  }

  /** Deserializes a JSON string to a ImportFileConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static ImportFileConfig fromJson(String jsonString) {
    return Common.fromJsonString(jsonString, ImportFileConfig.class);
  }
}
