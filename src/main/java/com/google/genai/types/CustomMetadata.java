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

/**
 * User provided metadata stored as key-value pairs. This data type is not supported in Vertex AI.
 */
@AutoValue
@JsonDeserialize(builder = CustomMetadata.Builder.class)
public abstract class CustomMetadata extends JsonSerializable {
  /** Required. The key of the metadata to store. */
  @JsonProperty("key")
  public abstract Optional<String> key();

  /** The numeric value of the metadata to store. */
  @JsonProperty("numericValue")
  public abstract Optional<Float> numericValue();

  /** The StringList value of the metadata to store. */
  @JsonProperty("stringListValue")
  public abstract Optional<StringList> stringListValue();

  /** The string value of the metadata to store. */
  @JsonProperty("stringValue")
  public abstract Optional<String> stringValue();

  /** Instantiates a builder for CustomMetadata. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_CustomMetadata.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for CustomMetadata. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `CustomMetadata.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_CustomMetadata.Builder();
    }

    /**
     * Setter for key.
     *
     * <p>key: Required. The key of the metadata to store.
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
     * <p>numericValue: The numeric value of the metadata to store.
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
     * <p>stringListValue: The StringList value of the metadata to store.
     */
    @JsonProperty("stringListValue")
    public abstract Builder stringListValue(StringList stringListValue);

    /**
     * Setter for stringListValue builder.
     *
     * <p>stringListValue: The StringList value of the metadata to store.
     */
    @CanIgnoreReturnValue
    public Builder stringListValue(StringList.Builder stringListValueBuilder) {
      return stringListValue(stringListValueBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder stringListValue(Optional<StringList> stringListValue);

    /** Clears the value of stringListValue field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearStringListValue() {
      return stringListValue(Optional.empty());
    }

    /**
     * Setter for stringValue.
     *
     * <p>stringValue: The string value of the metadata to store.
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

    public abstract CustomMetadata build();
  }

  /** Deserializes a JSON string to a CustomMetadata object. */
  @ExcludeFromGeneratedCoverageReport
  public static CustomMetadata fromJson(String jsonString) {
    return Common.fromJsonString(jsonString, CustomMetadata.class);
  }
}
