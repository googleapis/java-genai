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
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** The configuration for automatic function calling. */
@AutoValue
@JsonDeserialize(builder = AutomaticFunctionCallingConfig.Builder.class)
public abstract class AutomaticFunctionCallingConfig extends JsonSerializable {
  /**
   * Whether to disable automatic function calling. If not set or set to False, will enable
   * automatic function calling. If set to True, will disable automatic function calling.
   */
  @JsonProperty("disable")
  public abstract Optional<Boolean> disable();

  /**
   * If automatic function calling is enabled, maximum number of remote calls for automatic function
   * calling. This number should be a positive integer. If not set, SDK will set maximum number of
   * remote calls to 10.
   */
  @JsonProperty("maximumRemoteCalls")
  public abstract Optional<Integer> maximumRemoteCalls();

  /**
   * If automatic function calling is enabled, whether to ignore call history to the response. If
   * not set, SDK will set ignore_call_history to false, and will append the call history to
   * GenerateContentResponse.automatic_function_calling_history.
   */
  @JsonProperty("ignoreCallHistory")
  public abstract Optional<Boolean> ignoreCallHistory();

  /** Instantiates a builder for AutomaticFunctionCallingConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_AutomaticFunctionCallingConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for AutomaticFunctionCallingConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `AutomaticFunctionCallingConfig.builder()` for instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_AutomaticFunctionCallingConfig.Builder();
    }

    /**
     * Setter for disable.
     *
     * <p>disable: Whether to disable automatic function calling. If not set or set to False, will
     * enable automatic function calling. If set to True, will disable automatic function calling.
     */
    @JsonProperty("disable")
    public abstract Builder disable(boolean disable);

    /**
     * Setter for maximumRemoteCalls.
     *
     * <p>maximumRemoteCalls: If automatic function calling is enabled, maximum number of remote
     * calls for automatic function calling. This number should be a positive integer. If not set,
     * SDK will set maximum number of remote calls to 10.
     */
    @JsonProperty("maximumRemoteCalls")
    public abstract Builder maximumRemoteCalls(Integer maximumRemoteCalls);

    /**
     * Setter for ignoreCallHistory.
     *
     * <p>ignoreCallHistory: If automatic function calling is enabled, whether to ignore call
     * history to the response. If not set, SDK will set ignore_call_history to false, and will
     * append the call history to GenerateContentResponse.automatic_function_calling_history.
     */
    @JsonProperty("ignoreCallHistory")
    public abstract Builder ignoreCallHistory(boolean ignoreCallHistory);

    public abstract AutomaticFunctionCallingConfig build();
  }

  /** Deserializes a JSON string to a AutomaticFunctionCallingConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static AutomaticFunctionCallingConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, AutomaticFunctionCallingConfig.class);
  }
}
