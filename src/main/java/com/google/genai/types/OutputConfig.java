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

/** Config for evaluation output. */
@AutoValue
@JsonDeserialize(builder = OutputConfig.Builder.class)
public abstract class OutputConfig extends JsonSerializable {
  /** Cloud storage destination for evaluation output. */
  @JsonProperty("gcsDestination")
  public abstract Optional<GcsDestination> gcsDestination();

  /** Instantiates a builder for OutputConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_OutputConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for OutputConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `OutputConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_OutputConfig.Builder();
    }

    /**
     * Setter for gcsDestination.
     *
     * <p>gcsDestination: Cloud storage destination for evaluation output.
     */
    @JsonProperty("gcsDestination")
    public abstract Builder gcsDestination(GcsDestination gcsDestination);

    /**
     * Setter for gcsDestination builder.
     *
     * <p>gcsDestination: Cloud storage destination for evaluation output.
     */
    @CanIgnoreReturnValue
    public Builder gcsDestination(GcsDestination.Builder gcsDestinationBuilder) {
      return gcsDestination(gcsDestinationBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder gcsDestination(Optional<GcsDestination> gcsDestination);

    /** Clears the value of gcsDestination field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearGcsDestination() {
      return gcsDestination(Optional.empty());
    }

    public abstract OutputConfig build();
  }

  /** Deserializes a JSON string to a OutputConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static OutputConfig fromJson(String jsonString) {
    return Common.fromJsonString(jsonString, OutputConfig.class);
  }
}
