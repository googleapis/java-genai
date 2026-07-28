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
 * Enables context window compression — a mechanism for managing the model's context window so that
 * it does not exceed a given length. This data type is not supported in Vertex AI.
 */
@AutoValue
@JsonDeserialize(builder = ContextWindowCompressionConfig.Builder.class)
public abstract class ContextWindowCompressionConfig extends JsonSerializable {
  /**
   * The number of tokens (before running a turn) required to trigger a context window compression.
   * This can be used to balance quality against latency as shorter context windows may result in
   * faster model responses. However, any compression operation will cause a temporary latency
   * increase, so they should not be triggered frequently. If not set, the default is 80% of the
   * model's context window limit. This leaves 20% for the next user request/model response.
   */
  @JsonProperty("triggerTokens")
  public abstract Optional<Long> triggerTokens();

  /** A sliding-window mechanism. */
  @JsonProperty("slidingWindow")
  public abstract Optional<SlidingWindow> slidingWindow();

  /** Instantiates a builder for ContextWindowCompressionConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ContextWindowCompressionConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ContextWindowCompressionConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `ContextWindowCompressionConfig.builder()` for instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ContextWindowCompressionConfig.Builder();
    }

    /**
     * Setter for triggerTokens.
     *
     * <p>triggerTokens: The number of tokens (before running a turn) required to trigger a context
     * window compression. This can be used to balance quality against latency as shorter context
     * windows may result in faster model responses. However, any compression operation will cause a
     * temporary latency increase, so they should not be triggered frequently. If not set, the
     * default is 80% of the model's context window limit. This leaves 20% for the next user
     * request/model response.
     */
    @JsonProperty("triggerTokens")
    public abstract Builder triggerTokens(Long triggerTokens);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder triggerTokens(Optional<Long> triggerTokens);

    /** Clears the value of triggerTokens field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTriggerTokens() {
      return triggerTokens(Optional.empty());
    }

    /**
     * Setter for slidingWindow.
     *
     * <p>slidingWindow: A sliding-window mechanism.
     */
    @JsonProperty("slidingWindow")
    public abstract Builder slidingWindow(SlidingWindow slidingWindow);

    /**
     * Setter for slidingWindow builder.
     *
     * <p>slidingWindow: A sliding-window mechanism.
     */
    @CanIgnoreReturnValue
    public Builder slidingWindow(SlidingWindow.Builder slidingWindowBuilder) {
      return slidingWindow(slidingWindowBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder slidingWindow(Optional<SlidingWindow> slidingWindow);

    /** Clears the value of slidingWindow field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSlidingWindow() {
      return slidingWindow(Optional.empty());
    }

    public abstract ContextWindowCompressionConfig build();
  }

  /** Deserializes a JSON string to a ContextWindowCompressionConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static ContextWindowCompressionConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ContextWindowCompressionConfig.class);
  }
}
