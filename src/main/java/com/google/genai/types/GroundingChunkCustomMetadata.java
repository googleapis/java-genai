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

/** User provided metadata about the GroundingFact. This data type is not supported in Vertex AI. */
@AutoValue
@JsonDeserialize(builder = GroundingChunkCustomMetadata.Builder.class)
public abstract class GroundingChunkCustomMetadata extends JsonSerializable {
  /** The key of the metadata. */
  @JsonProperty("key")
  public abstract Optional<String> key();

  /**
   * Optional. The numeric value of the metadata. The expected range for this value depends on the
   * specific `key` used.
   */
  @JsonProperty("numericValue")
  public abstract Optional<Float> numericValue();

  /** Optional. A list of string values for the metadata. */
  @JsonProperty("stringListValue")
  public abstract Optional<GroundingChunkStringList> stringListValue();

  /** Optional. The string value of the metadata. */
  @JsonProperty("stringValue")
  public abstract Optional<String> stringValue();

  /** Instantiates a builder for GroundingChunkCustomMetadata. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GroundingChunkCustomMetadata.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GroundingChunkCustomMetadata. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `GroundingChunkCustomMetadata.builder()` for instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GroundingChunkCustomMetadata.Builder();
    }

    /**
     * Setter for key.
     *
     * <p>key: The key of the metadata.
     */
    @JsonProperty("key")
    public abstract Builder key(String key);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder key(Optional<String> key);

    /** Clears the value of key field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearKey() {
      return key(Optional.empty());
    }

    /**
     * Setter for numericValue.
     *
     * <p>numericValue: Optional. The numeric value of the metadata. The expected range for this
     * value depends on the specific `key` used.
     */
    @JsonProperty("numericValue")
    public abstract Builder numericValue(Float numericValue);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder numericValue(Optional<Float> numericValue);

    /** Clears the value of numericValue field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearNumericValue() {
      return numericValue(Optional.empty());
    }

    /**
     * Setter for stringListValue.
     *
     * <p>stringListValue: Optional. A list of string values for the metadata.
     */
    @JsonProperty("stringListValue")
    public abstract Builder stringListValue(GroundingChunkStringList stringListValue);

    /**
     * Setter for stringListValue builder.
     *
     * <p>stringListValue: Optional. A list of string values for the metadata.
     */
    @CanIgnoreReturnValue
    public Builder stringListValue(GroundingChunkStringList.Builder stringListValueBuilder) {
      return stringListValue(stringListValueBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder stringListValue(Optional<GroundingChunkStringList> stringListValue);

    /** Clears the value of stringListValue field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearStringListValue() {
      return stringListValue(Optional.empty());
    }

    /**
     * Setter for stringValue.
     *
     * <p>stringValue: Optional. The string value of the metadata.
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

    public abstract GroundingChunkCustomMetadata build();
  }

  /** Deserializes a JSON string to a GroundingChunkCustomMetadata object. */
  @ExcludeFromGeneratedCoverageReport
  public static GroundingChunkCustomMetadata fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GroundingChunkCustomMetadata.class);
  }
}
