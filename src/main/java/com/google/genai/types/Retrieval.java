// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Defines a retrieval tool that model can call to access external knowledge. */
@AutoValue
@JsonDeserialize(builder = AutoValue_Retrieval.Builder.class)
public abstract class Retrieval extends JsonSerializable {
  /** Optional. Deprecated. This option is no longer supported. */
  public abstract Optional<Boolean> getDisableAttribution();

  /** Set to use data source powered by Vertex AI Search. */
  public abstract Optional<VertexAISearch> getVertexAiSearch();

  /**
   * Set to use data source powered by Vertex RAG store. User data is uploaded via the
   * VertexRagDataService.
   */
  public abstract Optional<VertexRagStore> getVertexRagStore();

  /** Instantiates a builder for Retrieval. */
  public static Builder builder() {
    return new AutoValue_Retrieval.Builder();
  }

  /** Builder for Retrieval. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("disableAttribution")
    public abstract Builder setDisableAttribution(boolean disableAttribution);

    @JsonProperty("vertexAiSearch")
    public abstract Builder setVertexAiSearch(VertexAISearch vertexAiSearch);

    @JsonProperty("vertexRagStore")
    public abstract Builder setVertexRagStore(VertexRagStore vertexRagStore);

    public abstract Retrieval build();
  }

  /** Serializes the Retrieval object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a Retrieval object. */
  public static Retrieval fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Retrieval.class);
  }
}
