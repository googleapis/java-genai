// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.List;
import java.util.Optional;

/** The definition of the Rag resource. */
@AutoValue
@JsonDeserialize(builder = AutoValue_VertexRagStoreRagResource.Builder.class)
public abstract class VertexRagStoreRagResource extends JsonSerializable {
  /**
   * Optional. RagCorpora resource name. Format:
   * `projects/{project}/locations/{location}/ragCorpora/{rag_corpus}`
   */
  public abstract Optional<String> getRagCorpus();

  /** Optional. rag_file_id. The files should be in the same rag_corpus set in rag_corpus field. */
  public abstract Optional<List<String>> getRagFileIds();

  /** Instantiates a builder for VertexRagStoreRagResource. */
  public static Builder builder() {
    return new AutoValue_VertexRagStoreRagResource.Builder();
  }

  /** Builder for VertexRagStoreRagResource. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("ragCorpus")
    public abstract Builder setRagCorpus(String ragCorpus);

    @JsonProperty("ragFileIds")
    public abstract Builder setRagFileIds(List<String> ragFileIds);

    public abstract VertexRagStoreRagResource build();
  }

  /** Serializes the VertexRagStoreRagResource object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a VertexRagStoreRagResource object. */
  public static VertexRagStoreRagResource fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, VertexRagStoreRagResource.class);
  }
}
