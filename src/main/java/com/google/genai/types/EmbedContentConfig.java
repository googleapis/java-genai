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
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Optional parameters for the embed_content method. */
@AutoValue
@JsonDeserialize(builder = EmbedContentConfig.Builder.class)
public abstract class EmbedContentConfig extends JsonSerializable {
  /** Used to override HTTP request options. */
  @JsonProperty("httpOptions")
  public abstract Optional<HttpOptions> httpOptions();

  /** Type of task for which the embedding will be used. */
  @JsonProperty("taskType")
  public abstract Optional<String> taskType();

  /** Title for the text. Only applicable when TaskType is `RETRIEVAL_DOCUMENT`. */
  @JsonProperty("title")
  public abstract Optional<String> title();

  /**
   * Reduced dimension for the output embedding. If set, excessive values in the output embedding
   * are truncated from the end. Supported by newer models since 2024 only. You cannot set this
   * value if using the earlier model (`models/embedding-001`).
   */
  @JsonProperty("outputDimensionality")
  public abstract Optional<Integer> outputDimensionality();

  /** Vertex API only. The MIME type of the input. */
  @JsonProperty("mimeType")
  public abstract Optional<String> mimeType();

  /**
   * Vertex API only. Whether to silently truncate inputs longer than the max sequence length. If
   * this option is set to false, oversized inputs will lead to an INVALID_ARGUMENT error, similar
   * to other text APIs.
   */
  @JsonProperty("autoTruncate")
  public abstract Optional<Boolean> autoTruncate();

  /** Instantiates a builder for EmbedContentConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_EmbedContentConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for EmbedContentConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `EmbedContentConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_EmbedContentConfig.Builder();
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
     * Setter for taskType.
     *
     * <p>taskType: Type of task for which the embedding will be used.
     */
    @JsonProperty("taskType")
    public abstract Builder taskType(String taskType);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder taskType(Optional<String> taskType);

    /** Clears the value of taskType field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTaskType() {
      return taskType(Optional.empty());
    }

    /**
     * Setter for title.
     *
     * <p>title: Title for the text. Only applicable when TaskType is `RETRIEVAL_DOCUMENT`.
     */
    @JsonProperty("title")
    public abstract Builder title(String title);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder title(Optional<String> title);

    /** Clears the value of title field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTitle() {
      return title(Optional.empty());
    }

    /**
     * Setter for outputDimensionality.
     *
     * <p>outputDimensionality: Reduced dimension for the output embedding. If set, excessive values
     * in the output embedding are truncated from the end. Supported by newer models since 2024
     * only. You cannot set this value if using the earlier model (`models/embedding-001`).
     */
    @JsonProperty("outputDimensionality")
    public abstract Builder outputDimensionality(Integer outputDimensionality);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder outputDimensionality(Optional<Integer> outputDimensionality);

    /** Clears the value of outputDimensionality field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearOutputDimensionality() {
      return outputDimensionality(Optional.empty());
    }

    /**
     * Setter for mimeType.
     *
     * <p>mimeType: Vertex API only. The MIME type of the input.
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
     * Setter for autoTruncate.
     *
     * <p>autoTruncate: Vertex API only. Whether to silently truncate inputs longer than the max
     * sequence length. If this option is set to false, oversized inputs will lead to an
     * INVALID_ARGUMENT error, similar to other text APIs.
     */
    @JsonProperty("autoTruncate")
    public abstract Builder autoTruncate(boolean autoTruncate);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder autoTruncate(Optional<Boolean> autoTruncate);

    /** Clears the value of autoTruncate field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAutoTruncate() {
      return autoTruncate(Optional.empty());
    }

    public abstract EmbedContentConfig build();
  }

  /** Deserializes a JSON string to a EmbedContentConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static EmbedContentConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, EmbedContentConfig.class);
  }
}
