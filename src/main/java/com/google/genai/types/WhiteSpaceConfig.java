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
import com.google.genai.Common;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Configuration for a white space chunking algorithm. */
@AutoValue
@JsonDeserialize(builder = WhiteSpaceConfig.Builder.class)
public abstract class WhiteSpaceConfig extends JsonSerializable {
  /** Maximum number of tokens per chunk. */
  @JsonProperty("maxTokensPerChunk")
  public abstract Optional<Integer> maxTokensPerChunk();

  /** Maximum number of overlapping tokens between two adjacent chunks. */
  @JsonProperty("maxOverlapTokens")
  public abstract Optional<Integer> maxOverlapTokens();

  /** Instantiates a builder for WhiteSpaceConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_WhiteSpaceConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for WhiteSpaceConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `WhiteSpaceConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_WhiteSpaceConfig.Builder();
    }

    /**
     * Setter for maxTokensPerChunk.
     *
     * <p>maxTokensPerChunk: Maximum number of tokens per chunk.
     */
    @JsonProperty("maxTokensPerChunk")
    public abstract Builder maxTokensPerChunk(Integer maxTokensPerChunk);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder maxTokensPerChunk(Optional<Integer> maxTokensPerChunk);

    /** Clears the value of maxTokensPerChunk field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMaxTokensPerChunk() {
      return maxTokensPerChunk(Optional.empty());
    }

    /**
     * Setter for maxOverlapTokens.
     *
     * <p>maxOverlapTokens: Maximum number of overlapping tokens between two adjacent chunks.
     */
    @JsonProperty("maxOverlapTokens")
    public abstract Builder maxOverlapTokens(Integer maxOverlapTokens);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder maxOverlapTokens(Optional<Integer> maxOverlapTokens);

    /** Clears the value of maxOverlapTokens field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMaxOverlapTokens() {
      return maxOverlapTokens(Optional.empty());
    }

    public abstract WhiteSpaceConfig build();
  }

  /** Deserializes a JSON string to a WhiteSpaceConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static WhiteSpaceConfig fromJson(String jsonString) {
    return Common.fromJsonString(jsonString, WhiteSpaceConfig.class);
  }
}
