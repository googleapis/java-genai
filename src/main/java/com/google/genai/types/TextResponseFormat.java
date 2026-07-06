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

/** Configuration for text-specific output formatting. */
@AutoValue
@JsonDeserialize(builder = TextResponseFormat.Builder.class)
public abstract class TextResponseFormat extends JsonSerializable {
  /** Optional. The IANA standard MIME type of the response. */
  @JsonProperty("mimeType")
  public abstract Optional<String> mimeType();

  /**
   * Optional. The JSON schema that the output should conform to. Only applicable when mime_type is
   * APPLICATION_JSON.
   */
  @JsonProperty("schema")
  public abstract Optional<Object> schema();

  /** Instantiates a builder for TextResponseFormat. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_TextResponseFormat.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for TextResponseFormat. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `TextResponseFormat.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_TextResponseFormat.Builder();
    }

    /**
     * Setter for mimeType.
     *
     * <p>mimeType: Optional. The IANA standard MIME type of the response.
     */
    @JsonProperty("mimeType")
    public abstract Builder mimeType(String mimeType);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder mimeType(Optional<String> mimeType);

    /** Clears the value of mimeType field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMimeType() {
      return mimeType(Optional.empty());
    }

    /**
     * Setter for schema.
     *
     * <p>schema: Optional. The JSON schema that the output should conform to. Only applicable when
     * mime_type is APPLICATION_JSON.
     */
    @JsonProperty("schema")
    public abstract Builder schema(Object schema);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder schema(Optional<Object> schema);

    /** Clears the value of schema field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSchema() {
      return schema(Optional.empty());
    }

    public abstract TextResponseFormat build();
  }

  /** Deserializes a JSON string to a TextResponseFormat object. */
  @ExcludeFromGeneratedCoverageReport
  public static TextResponseFormat fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, TextResponseFormat.class);
  }
}
