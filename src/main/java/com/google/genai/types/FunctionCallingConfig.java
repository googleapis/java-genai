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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** Function calling config. */
@AutoValue
@JsonDeserialize(builder = FunctionCallingConfig.Builder.class)
public abstract class FunctionCallingConfig extends JsonSerializable {
  /** Optional. Function calling mode. */
  @JsonProperty("mode")
  public abstract Optional<FunctionCallingConfigMode> mode();

  /**
   * Optional. Function names to call. Only set when the Mode is ANY. Function names should match
   * [FunctionDeclaration.name]. With mode set to ANY, model will predict a function call from the
   * set of function names provided.
   */
  @JsonProperty("allowedFunctionNames")
  public abstract Optional<List<String>> allowedFunctionNames();

  /** Instantiates a builder for FunctionCallingConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_FunctionCallingConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for FunctionCallingConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `FunctionCallingConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_FunctionCallingConfig.Builder();
    }

    /**
     * Setter for mode.
     *
     * <p>mode: Optional. Function calling mode.
     */
    @JsonProperty("mode")
    public abstract Builder mode(FunctionCallingConfigMode mode);

    /**
     * Setter for mode given a known enum.
     *
     * <p>mode: Optional. Function calling mode.
     */
    @CanIgnoreReturnValue
    public Builder mode(FunctionCallingConfigMode.Known knownType) {
      return mode(new FunctionCallingConfigMode(knownType));
    }

    /**
     * Setter for mode given a string.
     *
     * <p>mode: Optional. Function calling mode.
     */
    @CanIgnoreReturnValue
    public Builder mode(String mode) {
      return mode(new FunctionCallingConfigMode(mode));
    }

    /**
     * Setter for allowedFunctionNames.
     *
     * <p>allowedFunctionNames: Optional. Function names to call. Only set when the Mode is ANY.
     * Function names should match [FunctionDeclaration.name]. With mode set to ANY, model will
     * predict a function call from the set of function names provided.
     */
    @JsonProperty("allowedFunctionNames")
    public abstract Builder allowedFunctionNames(List<String> allowedFunctionNames);

    /**
     * Setter for allowedFunctionNames.
     *
     * <p>allowedFunctionNames: Optional. Function names to call. Only set when the Mode is ANY.
     * Function names should match [FunctionDeclaration.name]. With mode set to ANY, model will
     * predict a function call from the set of function names provided.
     */
    public Builder allowedFunctionNames(String... allowedFunctionNames) {
      return allowedFunctionNames(Arrays.asList(allowedFunctionNames));
    }

    public abstract FunctionCallingConfig build();
  }

  /** Deserializes a JSON string to a FunctionCallingConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static FunctionCallingConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, FunctionCallingConfig.class);
  }
}
