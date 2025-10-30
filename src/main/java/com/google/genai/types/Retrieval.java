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
import com.google.genai.JsonSerializable;
import java.util.Optional;

/**
 * Defines a retrieval tool that model can call to access external knowledge. This data type is not
 * supported in Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = Retrieval.Builder.class)
public abstract class Retrieval extends JsonSerializable {
  /** Optional. Deprecated. This option is no longer supported. */
  @JsonProperty("disableAttribution")
  public abstract Optional<Boolean> disableAttribution();

  /** Use data source powered by external API for grounding. */
  @JsonProperty("externalApi")
  public abstract Optional<ExternalApi> externalApi();

  /** Set to use data source powered by Vertex AI Search. */
  @JsonProperty("vertexAiSearch")
  public abstract Optional<VertexAISearch> vertexAiSearch();

  /**
   * Set to use data source powered by Vertex RAG store. User data is uploaded via the
   * VertexRagDataService.
   */
  @JsonProperty("vertexRagStore")
  public abstract Optional<VertexRagStore> vertexRagStore();

  /** Instantiates a builder for Retrieval. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_Retrieval.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for Retrieval. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `Retrieval.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_Retrieval.Builder();
    }

    /**
     * Setter for disableAttribution.
     *
     * <p>disableAttribution: Optional. Deprecated. This option is no longer supported.
     */
    @JsonProperty("disableAttribution")
    public abstract Builder disableAttribution(boolean disableAttribution);

    /**
     * Setter for externalApi.
     *
     * <p>externalApi: Use data source powered by external API for grounding.
     */
    @JsonProperty("externalApi")
    public abstract Builder externalApi(ExternalApi externalApi);

    /**
     * Setter for externalApi builder.
     *
     * <p>externalApi: Use data source powered by external API for grounding.
     */
    public Builder externalApi(ExternalApi.Builder externalApiBuilder) {
      return externalApi(externalApiBuilder.build());
    }

    /**
     * Setter for vertexAiSearch.
     *
     * <p>vertexAiSearch: Set to use data source powered by Vertex AI Search.
     */
    @JsonProperty("vertexAiSearch")
    public abstract Builder vertexAiSearch(VertexAISearch vertexAiSearch);

    /**
     * Setter for vertexAiSearch builder.
     *
     * <p>vertexAiSearch: Set to use data source powered by Vertex AI Search.
     */
    public Builder vertexAiSearch(VertexAISearch.Builder vertexAiSearchBuilder) {
      return vertexAiSearch(vertexAiSearchBuilder.build());
    }

    /**
     * Setter for vertexRagStore.
     *
     * <p>vertexRagStore: Set to use data source powered by Vertex RAG store. User data is uploaded
     * via the VertexRagDataService.
     */
    @JsonProperty("vertexRagStore")
    public abstract Builder vertexRagStore(VertexRagStore vertexRagStore);

    /**
     * Setter for vertexRagStore builder.
     *
     * <p>vertexRagStore: Set to use data source powered by Vertex RAG store. User data is uploaded
     * via the VertexRagDataService.
     */
    public Builder vertexRagStore(VertexRagStore.Builder vertexRagStoreBuilder) {
      return vertexRagStore(vertexRagStoreBuilder.build());
    }

    public abstract Retrieval build();
  }

  /** Deserializes a JSON string to a Retrieval object. */
  @ExcludeFromGeneratedCoverageReport
  public static Retrieval fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Retrieval.class);
  }
}
