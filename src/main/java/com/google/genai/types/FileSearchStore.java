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

/** A collection of Documents. */
@AutoValue
@JsonDeserialize(builder = FileSearchStore.Builder.class)
public abstract class FileSearchStore extends JsonSerializable {
  /**
   * Output only. Immutable. Identifier. The `FileSearchStore` resource name. It is an ID (name
   * excluding the "fileSearchStores/" prefix) that can contain up to 40 characters that are
   * lowercase alphanumeric or dashes (-). It is output only. The unique name will be derived from
   * `display_name` along with a 12 character random suffix. Example:
   * `fileSearchStores/my-awesome-file-search-store-123a456b789c` If `display_name` is not provided,
   * the name will be randomly generated.
   */
  @JsonProperty("name")
  public abstract Optional<String> name();

  /**
   * Optional. The human-readable display name for the `FileSearchStore`. The display name must be
   * no more than 512 characters in length, including spaces. Example: "Docs on Semantic Retriever".
   */
  @JsonProperty("displayName")
  public abstract Optional<String> displayName();

  /** Output only. The Timestamp of when the `FileSearchStore` was created. */
  @JsonProperty("createTime")
  public abstract Optional<Instant> createTime();

  /** Output only. The Timestamp of when the `FileSearchStore` was last updated. */
  @JsonProperty("updateTime")
  public abstract Optional<Instant> updateTime();

  /**
   * Output only. The number of documents in the `FileSearchStore` that are active and ready for
   * retrieval.
   */
  @JsonProperty("activeDocumentsCount")
  public abstract Optional<Long> activeDocumentsCount();

  /** Output only. The number of documents in the `FileSearchStore` that are being processed. */
  @JsonProperty("pendingDocumentsCount")
  public abstract Optional<Long> pendingDocumentsCount();

  /** Output only. The number of documents in the `FileSearchStore` that have failed processing. */
  @JsonProperty("failedDocumentsCount")
  public abstract Optional<Long> failedDocumentsCount();

  /**
   * Output only. The size of raw bytes ingested into the `FileSearchStore`. This is the total size
   * of all the documents in the `FileSearchStore`.
   */
  @JsonProperty("sizeBytes")
  public abstract Optional<Long> sizeBytes();

  /** The embedding model used by the FileSearchStore. */
  @JsonProperty("embeddingModel")
  public abstract Optional<String> embeddingModel();

  /** Instantiates a builder for FileSearchStore. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_FileSearchStore.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for FileSearchStore. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `FileSearchStore.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_FileSearchStore.Builder();
    }

    /**
     * Setter for name.
     *
     * <p>name: Output only. Immutable. Identifier. The `FileSearchStore` resource name. It is an ID
     * (name excluding the "fileSearchStores/" prefix) that can contain up to 40 characters that are
     * lowercase alphanumeric or dashes (-). It is output only. The unique name will be derived from
     * `display_name` along with a 12 character random suffix. Example:
     * `fileSearchStores/my-awesome-file-search-store-123a456b789c` If `display_name` is not
     * provided, the name will be randomly generated.
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
     * <p>displayName: Optional. The human-readable display name for the `FileSearchStore`. The
     * display name must be no more than 512 characters in length, including spaces. Example: "Docs
     * on Semantic Retriever".
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
     * Setter for createTime.
     *
     * <p>createTime: Output only. The Timestamp of when the `FileSearchStore` was created.
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
     * Setter for updateTime.
     *
     * <p>updateTime: Output only. The Timestamp of when the `FileSearchStore` was last updated.
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
     * Setter for activeDocumentsCount.
     *
     * <p>activeDocumentsCount: Output only. The number of documents in the `FileSearchStore` that
     * are active and ready for retrieval.
     */
    @JsonProperty("activeDocumentsCount")
    public abstract Builder activeDocumentsCount(Long activeDocumentsCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder activeDocumentsCount(Optional<Long> activeDocumentsCount);

    /** Clears the value of activeDocumentsCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearActiveDocumentsCount() {
      return activeDocumentsCount(Optional.empty());
    }

    /**
     * Setter for pendingDocumentsCount.
     *
     * <p>pendingDocumentsCount: Output only. The number of documents in the `FileSearchStore` that
     * are being processed.
     */
    @JsonProperty("pendingDocumentsCount")
    public abstract Builder pendingDocumentsCount(Long pendingDocumentsCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder pendingDocumentsCount(Optional<Long> pendingDocumentsCount);

    /** Clears the value of pendingDocumentsCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPendingDocumentsCount() {
      return pendingDocumentsCount(Optional.empty());
    }

    /**
     * Setter for failedDocumentsCount.
     *
     * <p>failedDocumentsCount: Output only. The number of documents in the `FileSearchStore` that
     * have failed processing.
     */
    @JsonProperty("failedDocumentsCount")
    public abstract Builder failedDocumentsCount(Long failedDocumentsCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder failedDocumentsCount(Optional<Long> failedDocumentsCount);

    /** Clears the value of failedDocumentsCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearFailedDocumentsCount() {
      return failedDocumentsCount(Optional.empty());
    }

    /**
     * Setter for sizeBytes.
     *
     * <p>sizeBytes: Output only. The size of raw bytes ingested into the `FileSearchStore`. This is
     * the total size of all the documents in the `FileSearchStore`.
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
     * Setter for embeddingModel.
     *
     * <p>embeddingModel: The embedding model used by the FileSearchStore.
     */
    @JsonProperty("embeddingModel")
    public abstract Builder embeddingModel(String embeddingModel);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder embeddingModel(Optional<String> embeddingModel);

    /** Clears the value of embeddingModel field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEmbeddingModel() {
      return embeddingModel(Optional.empty());
    }

    public abstract FileSearchStore build();
  }

  /** Deserializes a JSON string to a FileSearchStore object. */
  @ExcludeFromGeneratedCoverageReport
  public static FileSearchStore fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, FileSearchStore.class);
  }
}
