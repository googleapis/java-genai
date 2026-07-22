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

/**
 * The SlidingWindow method operates by discarding content at the beginning of the context window.
 * The resulting context will always begin at the start of a USER role turn. System instructions and
 * any `BidiGenerateContentSetup.prefix_turns` will always remain at the beginning of the result.
 * This data type is not supported in Vertex AI.
 */
@AutoValue
@JsonDeserialize(builder = SlidingWindow.Builder.class)
public abstract class SlidingWindow extends JsonSerializable {
  /**
   * The target number of tokens to keep. The default value is trigger_tokens/2. Discarding parts of
   * the context window causes a temporary latency increase so this value should be calibrated to
   * avoid frequent compression operations.
   */
  @JsonProperty("targetTokens")
  public abstract Optional<Long> targetTokens();

  /** Instantiates a builder for SlidingWindow. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_SlidingWindow.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for SlidingWindow. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `SlidingWindow.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_SlidingWindow.Builder();
    }

    /**
     * Setter for targetTokens.
     *
     * <p>targetTokens: The target number of tokens to keep. The default value is trigger_tokens/2.
     * Discarding parts of the context window causes a temporary latency increase so this value
     * should be calibrated to avoid frequent compression operations.
     */
    @JsonProperty("targetTokens")
    public abstract Builder targetTokens(Long targetTokens);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder targetTokens(Optional<Long> targetTokens);

    /** Clears the value of targetTokens field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTargetTokens() {
      return targetTokens(Optional.empty());
    }

    public abstract SlidingWindow build();
  }

  /** Deserializes a JSON string to a SlidingWindow object. */
  @ExcludeFromGeneratedCoverageReport
  public static SlidingWindow fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SlidingWindow.class);
  }
}
