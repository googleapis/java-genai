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

/** Config for telling the service how to chunk the file. */
@AutoValue
@JsonDeserialize(builder = ChunkingConfig.Builder.class)
public abstract class ChunkingConfig extends JsonSerializable {
  /** White space chunking configuration. */
  @JsonProperty("whiteSpaceConfig")
  public abstract Optional<WhiteSpaceConfig> whiteSpaceConfig();

  /** Instantiates a builder for ChunkingConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ChunkingConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ChunkingConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ChunkingConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ChunkingConfig.Builder();
    }

    /**
     * Setter for whiteSpaceConfig.
     *
     * <p>whiteSpaceConfig: White space chunking configuration.
     */
    @JsonProperty("whiteSpaceConfig")
    public abstract Builder whiteSpaceConfig(WhiteSpaceConfig whiteSpaceConfig);

    /**
     * Setter for whiteSpaceConfig builder.
     *
     * <p>whiteSpaceConfig: White space chunking configuration.
     */
    @CanIgnoreReturnValue
    public Builder whiteSpaceConfig(WhiteSpaceConfig.Builder whiteSpaceConfigBuilder) {
      return whiteSpaceConfig(whiteSpaceConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder whiteSpaceConfig(Optional<WhiteSpaceConfig> whiteSpaceConfig);

    /** Clears the value of whiteSpaceConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearWhiteSpaceConfig() {
      return whiteSpaceConfig(Optional.empty());
    }

    public abstract ChunkingConfig build();
  }

  /** Deserializes a JSON string to a ChunkingConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static ChunkingConfig fromJson(String jsonString) {
    return Common.fromJsonString(jsonString, ChunkingConfig.class);
  }
}
