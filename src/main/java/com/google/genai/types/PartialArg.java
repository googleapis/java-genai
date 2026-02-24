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

/** Partial argument value of the function call. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = PartialArg.Builder.class)
public abstract class PartialArg extends JsonSerializable {
  /** Optional. Represents a boolean value. */
  @JsonProperty("boolValue")
  public abstract Optional<Boolean> boolValue();

  /**
   * Required. A JSON Path (RFC 9535) to the argument being streamed.
   * https://datatracker.ietf.org/doc/html/rfc9535. e.g. "$.foo.bar[0].data".
   */
  @JsonProperty("jsonPath")
  public abstract Optional<String> jsonPath();

  /** Optional. Represents a null value. */
  @JsonProperty("nullValue")
  public abstract Optional<NullValue> nullValue();

  /** Optional. Represents a double value. */
  @JsonProperty("numberValue")
  public abstract Optional<Double> numberValue();

  /** Optional. Represents a string value. */
  @JsonProperty("stringValue")
  public abstract Optional<String> stringValue();

  /**
   * Optional. Whether this is not the last part of the same json_path. If true, another PartialArg
   * message for the current json_path is expected to follow.
   */
  @JsonProperty("willContinue")
  public abstract Optional<Boolean> willContinue();

  /** Instantiates a builder for PartialArg. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_PartialArg.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for PartialArg. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `PartialArg.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_PartialArg.Builder();
    }

    /**
     * Setter for boolValue.
     *
     * <p>boolValue: Optional. Represents a boolean value.
     */
    @JsonProperty("boolValue")
    public abstract Builder boolValue(boolean boolValue);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder boolValue(Optional<Boolean> boolValue);

    /** Clears the value of boolValue field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearBoolValue() {
      return boolValue(Optional.empty());
    }

    /**
     * Setter for jsonPath.
     *
     * <p>jsonPath: Required. A JSON Path (RFC 9535) to the argument being streamed.
     * https://datatracker.ietf.org/doc/html/rfc9535. e.g. "$.foo.bar[0].data".
     */
    @JsonProperty("jsonPath")
    public abstract Builder jsonPath(String jsonPath);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder jsonPath(Optional<String> jsonPath);

    /** Clears the value of jsonPath field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearJsonPath() {
      return jsonPath(Optional.empty());
    }

    /**
     * Setter for nullValue.
     *
     * <p>nullValue: Optional. Represents a null value.
     */
    @JsonProperty("nullValue")
    public abstract Builder nullValue(NullValue nullValue);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder nullValue(Optional<NullValue> nullValue);

    /** Clears the value of nullValue field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearNullValue() {
      return nullValue(Optional.empty());
    }

    /**
     * Setter for nullValue given a known enum.
     *
     * <p>nullValue: Optional. Represents a null value.
     */
    @CanIgnoreReturnValue
    public Builder nullValue(NullValue.Known knownType) {
      return nullValue(new NullValue(knownType));
    }

    /**
     * Setter for nullValue given a string.
     *
     * <p>nullValue: Optional. Represents a null value.
     */
    @CanIgnoreReturnValue
    public Builder nullValue(String nullValue) {
      return nullValue(new NullValue(nullValue));
    }

    /**
     * Setter for numberValue.
     *
     * <p>numberValue: Optional. Represents a double value.
     */
    @JsonProperty("numberValue")
    public abstract Builder numberValue(Double numberValue);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder numberValue(Optional<Double> numberValue);

    /** Clears the value of numberValue field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearNumberValue() {
      return numberValue(Optional.empty());
    }

    /**
     * Setter for stringValue.
     *
     * <p>stringValue: Optional. Represents a string value.
     */
    @JsonProperty("stringValue")
    public abstract Builder stringValue(String stringValue);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder stringValue(Optional<String> stringValue);

    /** Clears the value of stringValue field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearStringValue() {
      return stringValue(Optional.empty());
    }

    /**
     * Setter for willContinue.
     *
     * <p>willContinue: Optional. Whether this is not the last part of the same json_path. If true,
     * another PartialArg message for the current json_path is expected to follow.
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

    public abstract PartialArg build();
  }

  /** Deserializes a JSON string to a PartialArg object. */
  @ExcludeFromGeneratedCoverageReport
  public static PartialArg fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, PartialArg.class);
  }
}
