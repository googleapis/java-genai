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

/** Optional parameters for uploading a file to a FileSearchStore. */
@AutoValue
@JsonDeserialize(builder = UploadToFileSearchStoreConfig.Builder.class)
public abstract class UploadToFileSearchStoreConfig extends JsonSerializable {
  /** Used to override HTTP request options. */
  @JsonProperty("httpOptions")
  public abstract Optional<HttpOptions> httpOptions();

  /** If true, the raw HTTP response will be returned in the 'sdk_http_response' field. */
  @JsonProperty("shouldReturnHttpResponse")
  public abstract Optional<Boolean> shouldReturnHttpResponse();

  /**
   * MIME type of the file to be uploaded. If not provided, it will be inferred from the file
   * extension.
   */
  @JsonProperty("mimeType")
  public abstract Optional<String> mimeType();

  /** Display name of the created document. */
  @JsonProperty("displayName")
  public abstract Optional<String> displayName();

  /** User provided custom metadata stored as key-value pairs used for querying. */
  @JsonProperty("customMetadata")
  public abstract Optional<List<CustomMetadata>> customMetadata();

  /** Config for telling the service how to chunk the file. */
  @JsonProperty("chunkingConfig")
  public abstract Optional<ChunkingConfig> chunkingConfig();

  /** Instantiates a builder for UploadToFileSearchStoreConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_UploadToFileSearchStoreConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for UploadToFileSearchStoreConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `UploadToFileSearchStoreConfig.builder()` for instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_UploadToFileSearchStoreConfig.Builder();
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
     * Setter for shouldReturnHttpResponse.
     *
     * <p>shouldReturnHttpResponse: If true, the raw HTTP response will be returned in the
     * 'sdk_http_response' field.
     */
    @JsonProperty("shouldReturnHttpResponse")
    public abstract Builder shouldReturnHttpResponse(boolean shouldReturnHttpResponse);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder shouldReturnHttpResponse(Optional<Boolean> shouldReturnHttpResponse);

    /** Clears the value of shouldReturnHttpResponse field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearShouldReturnHttpResponse() {
      return shouldReturnHttpResponse(Optional.empty());
    }

    /**
     * Setter for mimeType.
     *
     * <p>mimeType: MIME type of the file to be uploaded. If not provided, it will be inferred from
     * the file extension.
     */
    @JsonProperty("mimeType")
    public abstract Builder mimeType(String mimeType);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder mimeType(Optional<String> mimeType);

    /** Clears the value of mimeType field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMimeType() {
      return mimeType(Optional.empty());
    }

    /**
     * Setter for displayName.
     *
     * <p>displayName: Display name of the created document.
     */
    @JsonProperty("displayName")
    public abstract Builder displayName(String displayName);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder displayName(Optional<String> displayName);

    /** Clears the value of displayName field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDisplayName() {
      return displayName(Optional.empty());
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

    public abstract UploadToFileSearchStoreConfig build();
  }

  /** Deserializes a JSON string to a UploadToFileSearchStoreConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static UploadToFileSearchStoreConfig fromJson(String jsonString) {
    return Common.fromJsonString(jsonString, UploadToFileSearchStoreConfig.class);
  }
}
