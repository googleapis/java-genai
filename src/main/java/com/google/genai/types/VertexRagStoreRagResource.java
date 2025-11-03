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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** The definition of the Rag resource. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = VertexRagStoreRagResource.Builder.class)
public abstract class VertexRagStoreRagResource extends JsonSerializable {
  /**
   * Optional. RagCorpora resource name. Format:
   * `projects/{project}/locations/{location}/ragCorpora/{rag_corpus}`
   */
  @JsonProperty("ragCorpus")
  public abstract Optional<String> ragCorpus();

  /** Optional. rag_file_id. The files should be in the same rag_corpus set in rag_corpus field. */
  @JsonProperty("ragFileIds")
  public abstract Optional<List<String>> ragFileIds();

  /** Instantiates a builder for VertexRagStoreRagResource. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_VertexRagStoreRagResource.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for VertexRagStoreRagResource. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `VertexRagStoreRagResource.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_VertexRagStoreRagResource.Builder();
    }

    /**
     * Setter for ragCorpus.
     *
     * <p>ragCorpus: Optional. RagCorpora resource name. Format:
     * `projects/{project}/locations/{location}/ragCorpora/{rag_corpus}`
     */
    @JsonProperty("ragCorpus")
    public abstract Builder ragCorpus(String ragCorpus);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder ragCorpus(Optional<String> ragCorpus);

    /** Clears the value of ragCorpus field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRagCorpus() {
      return ragCorpus(Optional.empty());
    }

    /**
     * Setter for ragFileIds.
     *
     * <p>ragFileIds: Optional. rag_file_id. The files should be in the same rag_corpus set in
     * rag_corpus field.
     */
    @JsonProperty("ragFileIds")
    public abstract Builder ragFileIds(List<String> ragFileIds);

    /**
     * Setter for ragFileIds.
     *
     * <p>ragFileIds: Optional. rag_file_id. The files should be in the same rag_corpus set in
     * rag_corpus field.
     */
    @CanIgnoreReturnValue
    public Builder ragFileIds(String... ragFileIds) {
      return ragFileIds(Arrays.asList(ragFileIds));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder ragFileIds(Optional<List<String>> ragFileIds);

    /** Clears the value of ragFileIds field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRagFileIds() {
      return ragFileIds(Optional.empty());
    }

    public abstract VertexRagStoreRagResource build();
  }

  /** Deserializes a JSON string to a VertexRagStoreRagResource object. */
  @ExcludeFromGeneratedCoverageReport
  public static VertexRagStoreRagResource fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, VertexRagStoreRagResource.class);
  }
}
