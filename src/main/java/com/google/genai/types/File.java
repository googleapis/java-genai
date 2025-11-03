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
import java.util.Map;
import java.util.Optional;

/** A file uploaded to the API. */
@AutoValue
@JsonDeserialize(builder = File.Builder.class)
public abstract class File extends JsonSerializable {
  /**
   * The `File` resource name. The ID (name excluding the "files/" prefix) can contain up to 40
   * characters that are lowercase alphanumeric or dashes (-). The ID cannot start or end with a
   * dash. If the name is empty on create, a unique name will be generated. Example: `files/123-456`
   */
  @JsonProperty("name")
  public abstract Optional<String> name();

  /**
   * Optional. The human-readable display name for the `File`. The display name must be no more than
   * 512 characters in length, including spaces. Example: 'Welcome Image'
   */
  @JsonProperty("displayName")
  public abstract Optional<String> displayName();

  /** Output only. MIME type of the file. */
  @JsonProperty("mimeType")
  public abstract Optional<String> mimeType();

  /** Output only. Size of the file in bytes. */
  @JsonProperty("sizeBytes")
  public abstract Optional<Long> sizeBytes();

  /** Output only. The timestamp of when the `File` was created. */
  @JsonProperty("createTime")
  public abstract Optional<Instant> createTime();

  /**
   * Output only. The timestamp of when the `File` will be deleted. Only set if the `File` is
   * scheduled to expire.
   */
  @JsonProperty("expirationTime")
  public abstract Optional<Instant> expirationTime();

  /** Output only. The timestamp of when the `File` was last updated. */
  @JsonProperty("updateTime")
  public abstract Optional<Instant> updateTime();

  /**
   * Output only. SHA-256 hash of the uploaded bytes. The hash value is encoded in base64 format.
   */
  @JsonProperty("sha256Hash")
  public abstract Optional<String> sha256Hash();

  /** Output only. The URI of the `File`. */
  @JsonProperty("uri")
  public abstract Optional<String> uri();

  /** Output only. The URI of the `File`, only set for downloadable (generated) files. */
  @JsonProperty("downloadUri")
  public abstract Optional<String> downloadUri();

  /** Output only. Processing state of the File. */
  @JsonProperty("state")
  public abstract Optional<FileState> state();

  /** Output only. The source of the `File`. */
  @JsonProperty("source")
  public abstract Optional<FileSource> source();

  /** Output only. Metadata for a video. */
  @JsonProperty("videoMetadata")
  public abstract Optional<Map<String, Object>> videoMetadata();

  /** Output only. Error status if File processing failed. */
  @JsonProperty("error")
  public abstract Optional<FileStatus> error();

  /** Instantiates a builder for File. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_File.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for File. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `File.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_File.Builder();
    }

    /**
     * Setter for name.
     *
     * <p>name: The `File` resource name. The ID (name excluding the "files/" prefix) can contain up
     * to 40 characters that are lowercase alphanumeric or dashes (-). The ID cannot start or end
     * with a dash. If the name is empty on create, a unique name will be generated. Example:
     * `files/123-456`
     */
    @JsonProperty("name")
    public abstract Builder name(String name);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder name(Optional<String> name);

    /** Clears the value of name field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearName() {
      return name(Optional.empty());
    }

    /**
     * Setter for displayName.
     *
     * <p>displayName: Optional. The human-readable display name for the `File`. The display name
     * must be no more than 512 characters in length, including spaces. Example: 'Welcome Image'
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
     * <p>mimeType: Output only. MIME type of the file.
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
     * <p>sizeBytes: Output only. Size of the file in bytes.
     */
    @JsonProperty("sizeBytes")
    public abstract Builder sizeBytes(Long sizeBytes);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder sizeBytes(Optional<Long> sizeBytes);

    /** Clears the value of sizeBytes field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSizeBytes() {
      return sizeBytes(Optional.empty());
    }

    /**
     * Setter for createTime.
     *
     * <p>createTime: Output only. The timestamp of when the `File` was created.
     */
    @JsonProperty("createTime")
    public abstract Builder createTime(Instant createTime);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder createTime(Optional<Instant> createTime);

    /** Clears the value of createTime field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCreateTime() {
      return createTime(Optional.empty());
    }

    /**
     * Setter for expirationTime.
     *
     * <p>expirationTime: Output only. The timestamp of when the `File` will be deleted. Only set if
     * the `File` is scheduled to expire.
     */
    @JsonProperty("expirationTime")
    public abstract Builder expirationTime(Instant expirationTime);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder expirationTime(Optional<Instant> expirationTime);

    /** Clears the value of expirationTime field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearExpirationTime() {
      return expirationTime(Optional.empty());
    }

    /**
     * Setter for updateTime.
     *
     * <p>updateTime: Output only. The timestamp of when the `File` was last updated.
     */
    @JsonProperty("updateTime")
    public abstract Builder updateTime(Instant updateTime);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder updateTime(Optional<Instant> updateTime);

    /** Clears the value of updateTime field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearUpdateTime() {
      return updateTime(Optional.empty());
    }

    /**
     * Setter for sha256Hash.
     *
     * <p>sha256Hash: Output only. SHA-256 hash of the uploaded bytes. The hash value is encoded in
     * base64 format.
     */
    @JsonProperty("sha256Hash")
    public abstract Builder sha256Hash(String sha256Hash);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder sha256Hash(Optional<String> sha256Hash);

    /** Clears the value of sha256Hash field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSha256Hash() {
      return sha256Hash(Optional.empty());
    }

    /**
     * Setter for uri.
     *
     * <p>uri: Output only. The URI of the `File`.
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
     * Setter for downloadUri.
     *
     * <p>downloadUri: Output only. The URI of the `File`, only set for downloadable (generated)
     * files.
     */
    @JsonProperty("downloadUri")
    public abstract Builder downloadUri(String downloadUri);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder downloadUri(Optional<String> downloadUri);

    /** Clears the value of downloadUri field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDownloadUri() {
      return downloadUri(Optional.empty());
    }

    /**
     * Setter for state.
     *
     * <p>state: Output only. Processing state of the File.
     */
    @JsonProperty("state")
    public abstract Builder state(FileState state);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder state(Optional<FileState> state);

    /** Clears the value of state field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearState() {
      return state(Optional.empty());
    }

    /**
     * Setter for state given a known enum.
     *
     * <p>state: Output only. Processing state of the File.
     */
    @CanIgnoreReturnValue
    public Builder state(FileState.Known knownType) {
      return state(new FileState(knownType));
    }

    /**
     * Setter for state given a string.
     *
     * <p>state: Output only. Processing state of the File.
     */
    @CanIgnoreReturnValue
    public Builder state(String state) {
      return state(new FileState(state));
    }

    /**
     * Setter for source.
     *
     * <p>source: Output only. The source of the `File`.
     */
    @JsonProperty("source")
    public abstract Builder source(FileSource source);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder source(Optional<FileSource> source);

    /** Clears the value of source field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSource() {
      return source(Optional.empty());
    }

    /**
     * Setter for source given a known enum.
     *
     * <p>source: Output only. The source of the `File`.
     */
    @CanIgnoreReturnValue
    public Builder source(FileSource.Known knownType) {
      return source(new FileSource(knownType));
    }

    /**
     * Setter for source given a string.
     *
     * <p>source: Output only. The source of the `File`.
     */
    @CanIgnoreReturnValue
    public Builder source(String source) {
      return source(new FileSource(source));
    }

    /**
     * Setter for videoMetadata.
     *
     * <p>videoMetadata: Output only. Metadata for a video.
     */
    @JsonProperty("videoMetadata")
    public abstract Builder videoMetadata(Map<String, Object> videoMetadata);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder videoMetadata(Optional<Map<String, Object>> videoMetadata);

    /** Clears the value of videoMetadata field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearVideoMetadata() {
      return videoMetadata(Optional.empty());
    }

    /**
     * Setter for error.
     *
     * <p>error: Output only. Error status if File processing failed.
     */
    @JsonProperty("error")
    public abstract Builder error(FileStatus error);

    /**
     * Setter for error builder.
     *
     * <p>error: Output only. Error status if File processing failed.
     */
    @CanIgnoreReturnValue
    public Builder error(FileStatus.Builder errorBuilder) {
      return error(errorBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder error(Optional<FileStatus> error);

    /** Clears the value of error field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearError() {
      return error(Optional.empty());
    }

    public abstract File build();
  }

  /** Deserializes a JSON string to a File object. */
  @ExcludeFromGeneratedCoverageReport
  public static File fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, File.class);
  }
}
