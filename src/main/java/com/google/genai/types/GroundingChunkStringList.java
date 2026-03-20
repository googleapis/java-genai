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

/** A list of string values. This data type is not supported in Vertex AI. */
@AutoValue
@JsonDeserialize(builder = GroundingChunkStringList.Builder.class)
public abstract class GroundingChunkStringList extends JsonSerializable {
  /** The string values of the list. */
  @JsonProperty("values")
  public abstract Optional<List<String>> values();

  /** Instantiates a builder for GroundingChunkStringList. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GroundingChunkStringList.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GroundingChunkStringList. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `GroundingChunkStringList.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GroundingChunkStringList.Builder();
    }

    /**
     * Setter for values.
     *
     * <p>values: The string values of the list.
     */
    @JsonProperty("values")
    public abstract Builder values(List<String> values);

    /**
     * Setter for values.
     *
     * <p>values: The string values of the list.
     */
    @CanIgnoreReturnValue
    public Builder values(String... values) {
      return values(Arrays.asList(values));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder values(Optional<List<String>> values);

    /** Clears the value of values field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearValues() {
      return values(Optional.empty());
    }

    public abstract GroundingChunkStringList build();
  }

  /** Deserializes a JSON string to a GroundingChunkStringList object. */
  @ExcludeFromGeneratedCoverageReport
  public static GroundingChunkStringList fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GroundingChunkStringList.class);
  }
}
