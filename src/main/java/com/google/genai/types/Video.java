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

/** A generated video. */
@AutoValue
@JsonDeserialize(builder = Video.Builder.class)
public abstract class Video extends JsonSerializable {
  /** Path to another storage. */
  @JsonProperty("uri")
  public abstract Optional<String> uri();

  /** Video bytes. */
  @JsonProperty("videoBytes")
  public abstract Optional<byte[]> videoBytes();

  /** Video encoding, for example ``video/mp4``. */
  @JsonProperty("mimeType")
  public abstract Optional<String> mimeType();

  /** Instantiates a builder for Video. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_Video.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for Video. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `Video.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_Video.Builder();
    }

    /**
     * Setter for uri.
     *
     * <p>uri: Path to another storage.
     */
    @JsonProperty("uri")
    public abstract Builder uri(String uri);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder uri(Optional<String> uri);

    /** Clears the value of uri field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearUri() {
      return uri(Optional.empty());
    }

    /**
     * Setter for videoBytes.
     *
     * <p>videoBytes: Video bytes.
     */
    @JsonProperty("videoBytes")
    public abstract Builder videoBytes(byte[] videoBytes);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder videoBytes(Optional<byte[]> videoBytes);

    /** Clears the value of videoBytes field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearVideoBytes() {
      return videoBytes(Optional.empty());
    }

    /**
     * Setter for mimeType.
     *
     * <p>mimeType: Video encoding, for example ``video/mp4``.
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

    public abstract Video build();
  }

  /** Deserializes a JSON string to a Video object. */
  @ExcludeFromGeneratedCoverageReport
  public static Video fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Video.class);
  }
}
