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

/** Media resolution for the input media. */
@AutoValue
@JsonDeserialize(builder = PartMediaResolution.Builder.class)
public abstract class PartMediaResolution extends JsonSerializable {
  /** The tokenization quality used for given media. */
  @JsonProperty("level")
  public abstract Optional<PartMediaResolutionLevel> level();

  /** Specifies the required sequence length for media tokenization. */
  @JsonProperty("numTokens")
  public abstract Optional<Integer> numTokens();

  /** Instantiates a builder for PartMediaResolution. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_PartMediaResolution.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for PartMediaResolution. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `PartMediaResolution.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_PartMediaResolution.Builder();
    }

    /**
     * Setter for level.
     *
     * <p>level: The tokenization quality used for given media.
     */
    @JsonProperty("level")
    public abstract Builder level(PartMediaResolutionLevel level);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder level(Optional<PartMediaResolutionLevel> level);

    /** Clears the value of level field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearLevel() {
      return level(Optional.empty());
    }

    /**
     * Setter for level given a known enum.
     *
     * <p>level: The tokenization quality used for given media.
     */
    @CanIgnoreReturnValue
    public Builder level(PartMediaResolutionLevel.Known knownType) {
      return level(new PartMediaResolutionLevel(knownType));
    }

    /**
     * Setter for level given a string.
     *
     * <p>level: The tokenization quality used for given media.
     */
    @CanIgnoreReturnValue
    public Builder level(String level) {
      return level(new PartMediaResolutionLevel(level));
    }

    /**
     * Setter for numTokens.
     *
     * <p>numTokens: Specifies the required sequence length for media tokenization.
     */
    @JsonProperty("numTokens")
    public abstract Builder numTokens(Integer numTokens);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder numTokens(Optional<Integer> numTokens);

    /** Clears the value of numTokens field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearNumTokens() {
      return numTokens(Optional.empty());
    }

    public abstract PartMediaResolution build();
  }

  /** Deserializes a JSON string to a PartMediaResolution object. */
  @ExcludeFromGeneratedCoverageReport
  public static PartMediaResolution fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, PartMediaResolution.class);
  }
}
