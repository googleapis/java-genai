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
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** A function call. */
@AutoValue
@JsonDeserialize(builder = FunctionCall.Builder.class)
public abstract class FunctionCall extends JsonSerializable {
  /**
   * The unique id of the function call. If populated, the client to execute the `function_call` and
   * return the response with the matching `id`.
   */
  @JsonProperty("id")
  public abstract Optional<String> id();

  /**
   * Optional. The function parameters and values in JSON object format. See
   * [FunctionDeclaration.parameters] for parameter details.
   */
  @JsonProperty("args")
  public abstract Optional<Map<String, Object>> args();

  /** Optional. The name of the function to call. Matches [FunctionDeclaration.name]. */
  @JsonProperty("name")
  public abstract Optional<String> name();

  /**
   * Optional. The partial argument value of the function call. If provided, represents the
   * arguments/fields that are streamed incrementally. This field is not supported in Gemini API.
   */
  @JsonProperty("partialArgs")
  public abstract Optional<List<PartialArg>> partialArgs();

  /**
   * Optional. Whether this is the last part of the FunctionCall. If true, another partial message
   * for the current FunctionCall is expected to follow. This field is not supported in Gemini API.
   */
  @JsonProperty("willContinue")
  public abstract Optional<Boolean> willContinue();

  /** Instantiates a builder for FunctionCall. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_FunctionCall.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for FunctionCall. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `FunctionCall.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_FunctionCall.Builder();
    }

    /**
     * Setter for id.
     *
     * <p>id: The unique id of the function call. If populated, the client to execute the
     * `function_call` and return the response with the matching `id`.
     */
    @JsonProperty("id")
    public abstract Builder id(String id);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder id(Optional<String> id);

    /** Clears the value of id field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearId() {
      return id(Optional.empty());
    }

    /**
     * Setter for args.
     *
     * <p>args: Optional. The function parameters and values in JSON object format. See
     * [FunctionDeclaration.parameters] for parameter details.
     */
    @JsonProperty("args")
    public abstract Builder args(Map<String, Object> args);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder args(Optional<Map<String, Object>> args);

    /** Clears the value of args field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearArgs() {
      return args(Optional.empty());
    }

    /**
     * Setter for name.
     *
     * <p>name: Optional. The name of the function to call. Matches [FunctionDeclaration.name].
     */
    @JsonProperty("name")
    public abstract Builder name(String name);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder name(Optional<String> name);

    /** Clears the value of name field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearName() {
      return name(Optional.empty());
    }

    /**
     * Setter for partialArgs.
     *
     * <p>partialArgs: Optional. The partial argument value of the function call. If provided,
     * represents the arguments/fields that are streamed incrementally. This field is not supported
     * in Gemini API.
     */
    @ExcludeFromGeneratedCoverageReport
    public abstract Builder partialArgs(List<PartialArg> partialArgs);

    /**
     * Setter for partialArgs.
     *
     * <p>partialArgs: Optional. The partial argument value of the function call. If provided,
     * represents the arguments/fields that are streamed incrementally. This field is not supported
     * in Gemini API.
     */
    @CanIgnoreReturnValue
    public Builder partialArgs(PartialArg... partialArgs) {
      return partialArgs(Arrays.asList(partialArgs));
    }

    /**
     * Setter for partialArgs builder.
     *
     * <p>partialArgs: Optional. The partial argument value of the function call. If provided,
     * represents the arguments/fields that are streamed incrementally. This field is not supported
     * in Gemini API.
     */
    @CanIgnoreReturnValue
    public Builder partialArgs(PartialArg.Builder... partialArgsBuilders) {
      return partialArgs(
          Arrays.asList(partialArgsBuilders).stream()
              .map(PartialArg.Builder::build)
              .collect(toImmutableList()));
    }

    @JsonProperty("partialArgs")
    abstract Builder partialArgs(Optional<List<PartialArg>> partialArgs);

    /** Clears the value of partialArgs field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPartialArgs() {
      return partialArgs(Optional.empty());
    }

    /**
     * Setter for willContinue.
     *
     * <p>willContinue: Optional. Whether this is the last part of the FunctionCall. If true,
     * another partial message for the current FunctionCall is expected to follow. This field is not
     * supported in Gemini API.
     */
    @JsonProperty("willContinue")
    public abstract Builder willContinue(boolean willContinue);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder willContinue(Optional<Boolean> willContinue);

    /** Clears the value of willContinue field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearWillContinue() {
      return willContinue(Optional.empty());
    }

    public abstract FunctionCall build();
  }

  /** Deserializes a JSON string to a FunctionCall object. */
  @ExcludeFromGeneratedCoverageReport
  public static FunctionCall fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, FunctionCall.class);
  }
}
