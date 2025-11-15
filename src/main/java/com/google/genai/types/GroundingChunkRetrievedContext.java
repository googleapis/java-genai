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
 * Context retrieved from a data source to ground the model's response. This is used when a
 * retrieval tool fetches information from a user-provided corpus or a public dataset. This data
 * type is not supported in Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = GroundingChunkRetrievedContext.Builder.class)
public abstract class GroundingChunkRetrievedContext extends JsonSerializable {
  /**
   * Output only. The full resource name of the referenced Vertex AI Search document. This is used
   * to identify the specific document that was retrieved. The format is
   * `projects/{project}/locations/{location}/collections/{collection}/dataStores/{data_store}/branches/{branch}/documents/{document}`.
   */
  @JsonProperty("documentName")
  public abstract Optional<String> documentName();

  /**
   * Additional context for a Retrieval-Augmented Generation (RAG) retrieval result. This is
   * populated only when the RAG retrieval tool is used.
   */
  @JsonProperty("ragChunk")
  public abstract Optional<RagChunk> ragChunk();

  /** The content of the retrieved data source. */
  @JsonProperty("text")
  public abstract Optional<String> text();

  /** The title of the retrieved data source. */
  @JsonProperty("title")
  public abstract Optional<String> title();

  /** The URI of the retrieved data source. */
  @JsonProperty("uri")
  public abstract Optional<String> uri();

  /** Instantiates a builder for GroundingChunkRetrievedContext. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GroundingChunkRetrievedContext.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GroundingChunkRetrievedContext. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `GroundingChunkRetrievedContext.builder()` for instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GroundingChunkRetrievedContext.Builder();
    }

    /**
     * Setter for documentName.
     *
     * <p>documentName: Output only. The full resource name of the referenced Vertex AI Search
     * document. This is used to identify the specific document that was retrieved. The format is
     * `projects/{project}/locations/{location}/collections/{collection}/dataStores/{data_store}/branches/{branch}/documents/{document}`.
     */
    @JsonProperty("documentName")
    public abstract Builder documentName(String documentName);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder documentName(Optional<String> documentName);

    /** Clears the value of documentName field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDocumentName() {
      return documentName(Optional.empty());
    }

    /**
     * Setter for ragChunk.
     *
     * <p>ragChunk: Additional context for a Retrieval-Augmented Generation (RAG) retrieval result.
     * This is populated only when the RAG retrieval tool is used.
     */
    @JsonProperty("ragChunk")
    public abstract Builder ragChunk(RagChunk ragChunk);

    /**
     * Setter for ragChunk builder.
     *
     * <p>ragChunk: Additional context for a Retrieval-Augmented Generation (RAG) retrieval result.
     * This is populated only when the RAG retrieval tool is used.
     */
    @CanIgnoreReturnValue
    public Builder ragChunk(RagChunk.Builder ragChunkBuilder) {
      return ragChunk(ragChunkBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder ragChunk(Optional<RagChunk> ragChunk);

    /** Clears the value of ragChunk field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRagChunk() {
      return ragChunk(Optional.empty());
    }

    /**
     * Setter for text.
     *
     * <p>text: The content of the retrieved data source.
     */
    @JsonProperty("text")
    public abstract Builder text(String text);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder text(Optional<String> text);

    /** Clears the value of text field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearText() {
      return text(Optional.empty());
    }

    /**
     * Setter for title.
     *
     * <p>title: The title of the retrieved data source.
     */
    @JsonProperty("title")
    public abstract Builder title(String title);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder title(Optional<String> title);

    /** Clears the value of title field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTitle() {
      return title(Optional.empty());
    }

    /**
     * Setter for uri.
     *
     * <p>uri: The URI of the retrieved data source.
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

    public abstract GroundingChunkRetrievedContext build();
  }

  /** Deserializes a JSON string to a GroundingChunkRetrievedContext object. */
  @ExcludeFromGeneratedCoverageReport
  public static GroundingChunkRetrievedContext fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GroundingChunkRetrievedContext.class);
  }
}
