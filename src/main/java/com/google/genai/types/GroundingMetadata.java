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

import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.util.List;
import java.util.Optional;

/** Metadata returned to client when grounding is enabled. */
@GenerateTypeAdapter
@AutoValue
public abstract class GroundingMetadata extends JsonSerializable {
  /** List of supporting references retrieved from specified grounding source. */
  @SerializedName("groundingChunks")
  public abstract Optional<List<GroundingChunk>> groundingChunks();

  /** Optional. List of grounding support. */
  @SerializedName("groundingSupports")
  public abstract Optional<List<GroundingSupport>> groundingSupports();

  /** Optional. Output only. Retrieval metadata. */
  @SerializedName("retrievalMetadata")
  public abstract Optional<RetrievalMetadata> retrievalMetadata();

  /** Optional. Queries executed by the retrieval tools. */
  @SerializedName("retrievalQueries")
  public abstract Optional<List<String>> retrievalQueries();

  /** Optional. Google search entry for the following-up web searches. */
  @SerializedName("searchEntryPoint")
  public abstract Optional<SearchEntryPoint> searchEntryPoint();

  /** Optional. Web search queries for the following-up web search. */
  @SerializedName("webSearchQueries")
  public abstract Optional<List<String>> webSearchQueries();

  /** Instantiates a builder for GroundingMetadata. */
  public static Builder builder() {
    return new AutoValue_GroundingMetadata.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GroundingMetadata. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("groundingChunks")
    public abstract Builder groundingChunks(List<GroundingChunk> groundingChunks);

    @SerializedName("groundingChunks")
    abstract Builder groundingChunks(Optional<List<GroundingChunk>> groundingChunks);

    @SerializedName("groundingSupports")
    public abstract Builder groundingSupports(List<GroundingSupport> groundingSupports);

    @SerializedName("groundingSupports")
    abstract Builder groundingSupports(Optional<List<GroundingSupport>> groundingSupports);

    @SerializedName("retrievalMetadata")
    public abstract Builder retrievalMetadata(RetrievalMetadata retrievalMetadata);

    @SerializedName("retrievalMetadata")
    abstract Builder retrievalMetadata(Optional<RetrievalMetadata> retrievalMetadata);

    @SerializedName("retrievalQueries")
    public abstract Builder retrievalQueries(List<String> retrievalQueries);

    @SerializedName("retrievalQueries")
    abstract Builder retrievalQueries(Optional<List<String>> retrievalQueries);

    @SerializedName("searchEntryPoint")
    public abstract Builder searchEntryPoint(SearchEntryPoint searchEntryPoint);

    @SerializedName("searchEntryPoint")
    abstract Builder searchEntryPoint(Optional<SearchEntryPoint> searchEntryPoint);

    @SerializedName("webSearchQueries")
    public abstract Builder webSearchQueries(List<String> webSearchQueries);

    @SerializedName("webSearchQueries")
    abstract Builder webSearchQueries(Optional<List<String>> webSearchQueries);

    public abstract GroundingMetadata build();
  }

  /** Deserializes a JSON string to a GroundingMetadata object. */
  public static GroundingMetadata fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GroundingMetadata.class);
  }
}
