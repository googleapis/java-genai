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
import java.time.Instant;
import java.util.Optional;

/**
 * A file that can be referenced by ID from subsequent Claude model requests. The JSON
 * representation of this message matches the Claude Files API File object field-for-field, so that
 * code written against that API parses these responses unchanged. This data type is not supported
 * in Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = ClaudeFile.Builder.class)
public abstract class ClaudeFile extends JsonSerializable {
  /** Output only. The time at which the file was created. */
  @JsonProperty("createdAt")
  public abstract Optional<Instant> createdAt();

  /**
   * Output only. Whether the contents of this file can be retrieved with `DownloadFile`. False for
   * user-uploaded files, true for tool-generated ones.
   */
  @JsonProperty("downloadable")
  public abstract Optional<Boolean> downloadable();

  /**
   * Output only. The time at which the file expires and is deleted, set from the
   * `expires_in_seconds` upload form field. Unset for files that do not expire, which is serialised
   * as `null`.
   */
  @JsonProperty("expiresAt")
  public abstract Optional<Instant> expiresAt();

  /**
   * Output only. The original name of the uploaded file, taken from the `Content-Disposition`
   * header on upload. Between 1 and 255 characters. Control characters and the characters
   * `&lt;&gt;:"|?*\/` are not accepted.
   */
  @JsonProperty("filename")
  public abstract Optional<String> filename();

  /**
   * Output only. The server-assigned identifier of the file, for example `file_abc123`. This is the
   * ID to pass in subsequent model requests.
   */
  @JsonProperty("id")
  public abstract Optional<String> id();

  /**
   * Output only. The media type of the file, taken from the `Content-Type` header of the file part
   * on upload.
   */
  @JsonProperty("mimeType")
  public abstract Optional<String> mimeType();

  /** Output only. The size of the file in bytes. */
  @JsonProperty("sizeBytes")
  public abstract Optional<Integer> sizeBytes();

  /** Output only. The object type. Always `file`. */
  @JsonProperty("type")
  public abstract Optional<String> type();

  /** Instantiates a builder for ClaudeFile. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ClaudeFile.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ClaudeFile. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ClaudeFile.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ClaudeFile.Builder();
    }

    /**
     * Setter for createdAt.
     *
     * <p>createdAt: Output only. The time at which the file was created.
     */
    @JsonProperty("createdAt")
    public abstract Builder createdAt(Instant createdAt);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder createdAt(Optional<Instant> createdAt);

    /** Clears the value of createdAt field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCreatedAt() {
      return createdAt(Optional.empty());
    }

    /**
     * Setter for downloadable.
     *
     * <p>downloadable: Output only. Whether the contents of this file can be retrieved with
     * `DownloadFile`. False for user-uploaded files, true for tool-generated ones.
     */
    @JsonProperty("downloadable")
    public abstract Builder downloadable(boolean downloadable);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder downloadable(Optional<Boolean> downloadable);

    /** Clears the value of downloadable field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDownloadable() {
      return downloadable(Optional.empty());
    }

    /**
     * Setter for expiresAt.
     *
     * <p>expiresAt: Output only. The time at which the file expires and is deleted, set from the
     * `expires_in_seconds` upload form field. Unset for files that do not expire, which is
     * serialised as `null`.
     */
    @JsonProperty("expiresAt")
    public abstract Builder expiresAt(Instant expiresAt);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder expiresAt(Optional<Instant> expiresAt);

    /** Clears the value of expiresAt field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearExpiresAt() {
      return expiresAt(Optional.empty());
    }

    /**
     * Setter for filename.
     *
     * <p>filename: Output only. The original name of the uploaded file, taken from the
     * `Content-Disposition` header on upload. Between 1 and 255 characters. Control characters and
     * the characters `&lt;&gt;:"|?*\/` are not accepted.
     */
    @JsonProperty("filename")
    public abstract Builder filename(String filename);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder filename(Optional<String> filename);

    /** Clears the value of filename field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearFilename() {
      return filename(Optional.empty());
    }

    /**
     * Setter for id.
     *
     * <p>id: Output only. The server-assigned identifier of the file, for example `file_abc123`.
     * This is the ID to pass in subsequent model requests.
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
     * Setter for mimeType.
     *
     * <p>mimeType: Output only. The media type of the file, taken from the `Content-Type` header of
     * the file part on upload.
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
     * Setter for sizeBytes.
     *
     * <p>sizeBytes: Output only. The size of the file in bytes.
     */
    @JsonProperty("sizeBytes")
    public abstract Builder sizeBytes(Integer sizeBytes);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder sizeBytes(Optional<Integer> sizeBytes);

    /** Clears the value of sizeBytes field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSizeBytes() {
      return sizeBytes(Optional.empty());
    }

    /**
     * Setter for type.
     *
     * <p>type: Output only. The object type. Always `file`.
     */
    @JsonProperty("type")
    public abstract Builder type(String type);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder type(Optional<String> type);

    /** Clears the value of type field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearType() {
      return type(Optional.empty());
    }

    public abstract ClaudeFile build();
  }

  /** Deserializes a JSON string to a ClaudeFile object. */
  @ExcludeFromGeneratedCoverageReport
  public static ClaudeFile fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ClaudeFile.class);
  }
}
