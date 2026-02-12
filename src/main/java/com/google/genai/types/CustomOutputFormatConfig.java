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

/** Config for custom output format. */
@AutoValue
@JsonDeserialize(builder = CustomOutputFormatConfig.Builder.class)
public abstract class CustomOutputFormatConfig extends JsonSerializable {
  /** Optional. Whether to return raw output. */
  @JsonProperty("returnRawOutput")
  public abstract Optional<Boolean> returnRawOutput();

  /** Instantiates a builder for CustomOutputFormatConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_CustomOutputFormatConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for CustomOutputFormatConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `CustomOutputFormatConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_CustomOutputFormatConfig.Builder();
    }

    /**
     * Setter for returnRawOutput.
     *
     * <p>returnRawOutput: Optional. Whether to return raw output.
     */
    @JsonProperty("returnRawOutput")
    public abstract Builder returnRawOutput(boolean returnRawOutput);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder returnRawOutput(Optional<Boolean> returnRawOutput);

    /** Clears the value of returnRawOutput field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearReturnRawOutput() {
      return returnRawOutput(Optional.empty());
    }

    public abstract CustomOutputFormatConfig build();
  }

  /** Deserializes a JSON string to a CustomOutputFormatConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static CustomOutputFormatConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, CustomOutputFormatConfig.class);
  }
}
