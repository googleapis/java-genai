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

/**
 * A content blob. A Blob contains data of a specific media type. It is used to represent images,
 * audio, and video.
 */
@AutoValue
@JsonDeserialize(builder = Blob.Builder.class)
public abstract class Blob extends JsonSerializable {
  /** Required. The raw bytes of the data. */
  @JsonProperty("data")
  public abstract Optional<byte[]> data();

  /**
   * Optional. The display name of the blob. Used to provide a label or filename to distinguish
   * blobs. This field is only returned in `PromptMessage` for prompt management. It is used in the
   * Gemini calls only when server-side tools (`code_execution`, `google_search`, and `url_context`)
   * are enabled. This field is not supported in Gemini API.
   */
  @JsonProperty("displayName")
  public abstract Optional<String> displayName();

  /** Required. The IANA standard MIME type of the source data. */
  @JsonProperty("mimeType")
  public abstract Optional<String> mimeType();

  /** Instantiates a builder for Blob. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_Blob.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for Blob. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `Blob.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_Blob.Builder();
    }

    /**
     * Setter for data.
     *
     * <p>data: Required. The raw bytes of the data.
     */
    @JsonProperty("data")
    public abstract Builder data(byte[] data);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder data(Optional<byte[]> data);

    /** Clears the value of data field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearData() {
      return data(Optional.empty());
    }

    /**
     * Setter for displayName.
     *
     * <p>displayName: Optional. The display name of the blob. Used to provide a label or filename
     * to distinguish blobs. This field is only returned in `PromptMessage` for prompt management.
     * It is used in the Gemini calls only when server-side tools (`code_execution`,
     * `google_search`, and `url_context`) are enabled. This field is not supported in Gemini API.
     */
    @JsonProperty("displayName")
    public abstract Builder displayName(String displayName);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder displayName(Optional<String> displayName);

    /** Clears the value of displayName field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDisplayName() {
      return displayName(Optional.empty());
    }

    /**
     * Setter for mimeType.
     *
     * <p>mimeType: Required. The IANA standard MIME type of the source data.
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

    public abstract Blob build();
  }

  /** Deserializes a JSON string to a Blob object. */
  @ExcludeFromGeneratedCoverageReport
  public static Blob fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Blob.class);
  }
}
