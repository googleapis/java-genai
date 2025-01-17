// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.List;
import java.util.Optional;

/** Metadata returned to client when grounding is enabled. */
@AutoValue
@JsonDeserialize(builder = AutoValue_GroundingMetadata.Builder.class)
public abstract class GroundingMetadata extends JsonSerializable {
  /** List of supporting references retrieved from specified grounding source. */
  public abstract Optional<List<GroundingChunk>> getGroundingChunks();

  /** Optional. List of grounding support. */
  public abstract Optional<List<GroundingSupport>> getGroundingSupports();

  /** Optional. Output only. Retrieval metadata. */
  public abstract Optional<RetrievalMetadata> getRetrievalMetadata();

  /** Optional. Queries executed by the retrieval tools. */
  public abstract Optional<List<String>> getRetrievalQueries();

  /** Optional. Google search entry for the following-up web searches. */
  public abstract Optional<SearchEntryPoint> getSearchEntryPoint();

  /** Optional. Web search queries for the following-up web search. */
  public abstract Optional<List<String>> getWebSearchQueries();

  /** Instantiates a builder for GroundingMetadata. */
  public static Builder builder() {
    return new AutoValue_GroundingMetadata.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GroundingMetadata. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("groundingChunks")
    public abstract Builder setGroundingChunks(List<GroundingChunk> groundingChunks);

    @JsonProperty("groundingSupports")
    public abstract Builder setGroundingSupports(List<GroundingSupport> groundingSupports);

    @JsonProperty("retrievalMetadata")
    public abstract Builder setRetrievalMetadata(RetrievalMetadata retrievalMetadata);

    @JsonProperty("retrievalQueries")
    public abstract Builder setRetrievalQueries(List<String> retrievalQueries);

    @JsonProperty("searchEntryPoint")
    public abstract Builder setSearchEntryPoint(SearchEntryPoint searchEntryPoint);

    @JsonProperty("webSearchQueries")
    public abstract Builder setWebSearchQueries(List<String> webSearchQueries);

    public abstract GroundingMetadata build();
  }

  /** Deserializes a JSON string to a GroundingMetadata object. */
  public static GroundingMetadata fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GroundingMetadata.class);
  }
}
