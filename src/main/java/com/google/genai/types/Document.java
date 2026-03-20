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

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** A Document is a collection of Chunks. */
@AutoValue
@JsonDeserialize(builder = Document.Builder.class)
public abstract class Document extends JsonSerializable {
  /**
   * Immutable. Identifier. The `Document` resource name. The ID (name excluding the
   * "fileSearchStores/&#42;/documents/" prefix) can contain up to 40 characters that are lowercase
   * alphanumeric or dashes (-). The ID cannot start or end with a dash. If the name is empty on
   * create, a unique name will be derived from `display_name` along with a 12 character random
   * suffix. Example:
   * `fileSearchStores/{file_search_store_id}/documents/my-awesome-doc-123a456b789c`
   */
  @JsonProperty("name")
  public abstract Optional<String> name();

  /**
   * Optional. The human-readable display name for the `Document`. The display name must be no more
   * than 512 characters in length, including spaces. Example: "Semantic Retriever Documentation".
   */
  @JsonProperty("displayName")
  public abstract Optional<String> displayName();

  /** Output only. Current state of the `Document`. */
  @JsonProperty("state")
  public abstract Optional<DocumentState> state();

  /** Output only. The size of raw bytes ingested into the Document. */
  @JsonProperty("sizeBytes")
  public abstract Optional<Long> sizeBytes();

  /** Output only. The mime type of the Document. */
  @JsonProperty("mimeType")
  public abstract Optional<String> mimeType();

  /** Output only. The Timestamp of when the `Document` was created. */
  @JsonProperty("createTime")
  public abstract Optional<Instant> createTime();

  /**
   * Optional. User provided custom metadata stored as key-value pairs used for querying. A
   * `Document` can have a maximum of 20 `CustomMetadata`.
   */
  @JsonProperty("customMetadata")
  public abstract Optional<List<CustomMetadata>> customMetadata();

  /** Output only. The Timestamp of when the `Document` was last updated. */
  @JsonProperty("updateTime")
  public abstract Optional<Instant> updateTime();

  /** Instantiates a builder for Document. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_Document.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for Document. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `Document.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_Document.Builder();
    }

    /**
     * Setter for name.
     *
     * <p>name: Immutable. Identifier. The `Document` resource name. The ID (name excluding the
     * "fileSearchStores/&#42;/documents/" prefix) can contain up to 40 characters that are
     * lowercase alphanumeric or dashes (-). The ID cannot start or end with a dash. If the name is
     * empty on create, a unique name will be derived from `display_name` along with a 12 character
     * random suffix. Example:
     * `fileSearchStores/{file_search_store_id}/documents/my-awesome-doc-123a456b789c`
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
     * <p>displayName: Optional. The human-readable display name for the `Document`. The display
     * name must be no more than 512 characters in length, including spaces. Example: "Semantic
     * Retriever Documentation".
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
     * Setter for state.
     *
     * <p>state: Output only. Current state of the `Document`.
     */
    @JsonProperty("state")
    public abstract Builder state(DocumentState state);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder state(Optional<DocumentState> state);

    /** Clears the value of state field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearState() {
      return state(Optional.empty());
    }

    /**
     * Setter for state given a known enum.
     *
     * <p>state: Output only. Current state of the `Document`.
     */
    @CanIgnoreReturnValue
    public Builder state(DocumentState.Known knownType) {
      return state(new DocumentState(knownType));
    }

    /**
     * Setter for state given a string.
     *
     * <p>state: Output only. Current state of the `Document`.
     */
    @CanIgnoreReturnValue
    public Builder state(String state) {
      return state(new DocumentState(state));
    }

    /**
     * Setter for sizeBytes.
     *
     * <p>sizeBytes: Output only. The size of raw bytes ingested into the Document.
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
     * Setter for mimeType.
     *
     * <p>mimeType: Output only. The mime type of the Document.
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
     * Setter for createTime.
     *
     * <p>createTime: Output only. The Timestamp of when the `Document` was created.
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
     * Setter for customMetadata.
     *
     * <p>customMetadata: Optional. User provided custom metadata stored as key-value pairs used for
     * querying. A `Document` can have a maximum of 20 `CustomMetadata`.
     */
    @JsonProperty("customMetadata")
    public abstract Builder customMetadata(List<CustomMetadata> customMetadata);

    /**
     * Setter for customMetadata.
     *
     * <p>customMetadata: Optional. User provided custom metadata stored as key-value pairs used for
     * querying. A `Document` can have a maximum of 20 `CustomMetadata`.
     */
    @CanIgnoreReturnValue
    public Builder customMetadata(CustomMetadata... customMetadata) {
      return customMetadata(Arrays.asList(customMetadata));
    }

    /**
     * Setter for customMetadata builder.
     *
     * <p>customMetadata: Optional. User provided custom metadata stored as key-value pairs used for
     * querying. A `Document` can have a maximum of 20 `CustomMetadata`.
     */
    @CanIgnoreReturnValue
    public Builder customMetadata(CustomMetadata.Builder... customMetadataBuilders) {
      return customMetadata(
          Arrays.asList(customMetadataBuilders).stream()
              .map(CustomMetadata.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder customMetadata(Optional<List<CustomMetadata>> customMetadata);

    /** Clears the value of customMetadata field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCustomMetadata() {
      return customMetadata(Optional.empty());
    }

    /**
     * Setter for updateTime.
     *
     * <p>updateTime: Output only. The Timestamp of when the `Document` was last updated.
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

    public abstract Document build();
  }

  /** Deserializes a JSON string to a Document object. */
  @ExcludeFromGeneratedCoverageReport
  public static Document fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Document.class);
  }
}
