// Auto-generated code. Do not edit.

package com.google.genai.datatypes;

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

  /** Serializes the VertexRagStore object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a VertexRagStore object. */
  public static VertexRagStore fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, VertexRagStore.class);
  }
}
