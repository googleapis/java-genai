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
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** Retrieve from Vertex RAG Store for grounding. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = VertexRagStore.Builder.class)
public abstract class VertexRagStore extends JsonSerializable {
  /** Optional. Deprecated. Please use rag_resources instead. */
  @JsonProperty("ragCorpora")
  public abstract Optional<List<String>> ragCorpora();

  /**
   * Optional. The representation of the rag source. It can be used to specify corpus only or
   * ragfiles. Currently only support one corpus or multiple files from one corpus. In the future we
   * may open up multiple corpora support.
   */
  @JsonProperty("ragResources")
  public abstract Optional<List<VertexRagStoreRagResource>> ragResources();

  /** Optional. The retrieval config for the Rag query. */
  @JsonProperty("ragRetrievalConfig")
  public abstract Optional<RagRetrievalConfig> ragRetrievalConfig();

  /** Optional. Number of top k results to return from the selected corpora. */
  @JsonProperty("similarityTopK")
  public abstract Optional<Integer> similarityTopK();

  /**
   * Optional. Currently only supported for Gemini Multimodal Live API. In Gemini Multimodal Live
   * API, if `store_context` bool is specified, Gemini will leverage it to automatically memorize
   * the interactions between the client and Gemini, and retrieve context when needed to augment the
   * response generation for users' ongoing and future interactions.
   */
  @JsonProperty("storeContext")
  public abstract Optional<Boolean> storeContext();

  /** Optional. Only return results with vector distance smaller than the threshold. */
  @JsonProperty("vectorDistanceThreshold")
  public abstract Optional<Double> vectorDistanceThreshold();

  /** Instantiates a builder for VertexRagStore. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_VertexRagStore.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for VertexRagStore. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `VertexRagStore.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_VertexRagStore.Builder();
    }

    /**
     * Setter for ragCorpora.
     *
     * <p>ragCorpora: Optional. Deprecated. Please use rag_resources instead.
     */
    @JsonProperty("ragCorpora")
    public abstract Builder ragCorpora(List<String> ragCorpora);

    /**
     * Setter for ragCorpora.
     *
     * <p>ragCorpora: Optional. Deprecated. Please use rag_resources instead.
     */
    public Builder ragCorpora(String... ragCorpora) {
      return ragCorpora(Arrays.asList(ragCorpora));
    }

    /**
     * Setter for ragResources.
     *
     * <p>ragResources: Optional. The representation of the rag source. It can be used to specify
     * corpus only or ragfiles. Currently only support one corpus or multiple files from one corpus.
     * In the future we may open up multiple corpora support.
     */
    @JsonProperty("ragResources")
    public abstract Builder ragResources(List<VertexRagStoreRagResource> ragResources);

    /**
     * Setter for ragResources.
     *
     * <p>ragResources: Optional. The representation of the rag source. It can be used to specify
     * corpus only or ragfiles. Currently only support one corpus or multiple files from one corpus.
     * In the future we may open up multiple corpora support.
     */
    public Builder ragResources(VertexRagStoreRagResource... ragResources) {
      return ragResources(Arrays.asList(ragResources));
    }

    /**
     * Setter for ragResources builder.
     *
     * <p>ragResources: Optional. The representation of the rag source. It can be used to specify
     * corpus only or ragfiles. Currently only support one corpus or multiple files from one corpus.
     * In the future we may open up multiple corpora support.
     */
    public Builder ragResources(VertexRagStoreRagResource.Builder... ragResourcesBuilders) {
      return ragResources(
          Arrays.asList(ragResourcesBuilders).stream()
              .map(VertexRagStoreRagResource.Builder::build)
              .collect(toImmutableList()));
    }

    /**
     * Setter for ragRetrievalConfig.
     *
     * <p>ragRetrievalConfig: Optional. The retrieval config for the Rag query.
     */
    @JsonProperty("ragRetrievalConfig")
    public abstract Builder ragRetrievalConfig(RagRetrievalConfig ragRetrievalConfig);

    /**
     * Setter for ragRetrievalConfig builder.
     *
     * <p>ragRetrievalConfig: Optional. The retrieval config for the Rag query.
     */
    public Builder ragRetrievalConfig(RagRetrievalConfig.Builder ragRetrievalConfigBuilder) {
      return ragRetrievalConfig(ragRetrievalConfigBuilder.build());
    }

    /**
     * Setter for similarityTopK.
     *
     * <p>similarityTopK: Optional. Number of top k results to return from the selected corpora.
     */
    @JsonProperty("similarityTopK")
    public abstract Builder similarityTopK(Integer similarityTopK);

    /**
     * Setter for storeContext.
     *
     * <p>storeContext: Optional. Currently only supported for Gemini Multimodal Live API. In Gemini
     * Multimodal Live API, if `store_context` bool is specified, Gemini will leverage it to
     * automatically memorize the interactions between the client and Gemini, and retrieve context
     * when needed to augment the response generation for users' ongoing and future interactions.
     */
    @JsonProperty("storeContext")
    public abstract Builder storeContext(boolean storeContext);

    /**
     * Setter for vectorDistanceThreshold.
     *
     * <p>vectorDistanceThreshold: Optional. Only return results with vector distance smaller than
     * the threshold.
     */
    @JsonProperty("vectorDistanceThreshold")
    public abstract Builder vectorDistanceThreshold(Double vectorDistanceThreshold);

    public abstract VertexRagStore build();
  }

  /** Deserializes a JSON string to a VertexRagStore object. */
  @ExcludeFromGeneratedCoverageReport
  public static VertexRagStore fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, VertexRagStore.class);
  }
}
