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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.List;
import java.util.Optional;

/** Retrieve from Vertex RAG Store for grounding. */
@AutoValue
@JsonDeserialize(builder = AutoValue_VertexRagStore.Builder.class)
public abstract class VertexRagStore extends JsonSerializable {
  /** Optional. Deprecated. Please use rag_resources instead. */
  public abstract Optional<List<String>> getRagCorpora();

  /**
   * Optional. The representation of the rag source. It can be used to specify corpus only or
   * ragfiles. Currently only support one corpus or multiple files from one corpus. In the future we
   * may open up multiple corpora support.
   */
  public abstract Optional<List<VertexRagStoreRagResource>> getRagResources();

  /** Optional. Number of top k results to return from the selected corpora. */
  public abstract Optional<Integer> getSimilarityTopK();

  /** Optional. Only return results with vector distance smaller than the threshold. */
  public abstract Optional<Double> getVectorDistanceThreshold();

  /** Instantiates a builder for VertexRagStore. */
  public static Builder builder() {
    return new AutoValue_VertexRagStore.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for VertexRagStore. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("ragCorpora")
    public abstract Builder setRagCorpora(List<String> ragCorpora);

    @JsonProperty("ragResources")
    public abstract Builder setRagResources(List<VertexRagStoreRagResource> ragResources);

    @JsonProperty("similarityTopK")
    public abstract Builder setSimilarityTopK(Integer similarityTopK);

    @JsonProperty("vectorDistanceThreshold")
    public abstract Builder setVectorDistanceThreshold(Double vectorDistanceThreshold);

    public abstract VertexRagStore build();
  }

  /** Deserializes a JSON string to a VertexRagStore object. */
  public static VertexRagStore fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, VertexRagStore.class);
  }
}
