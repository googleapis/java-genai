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

/** Specifies the context retrieval config. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = RagRetrievalConfig.Builder.class)
public abstract class RagRetrievalConfig extends JsonSerializable {
  /** Optional. Config for filters. */
  @JsonProperty("filter")
  public abstract Optional<RagRetrievalConfigFilter> filter();

  /** Optional. Config for Hybrid Search. */
  @JsonProperty("hybridSearch")
  public abstract Optional<RagRetrievalConfigHybridSearch> hybridSearch();

  /** Optional. Config for ranking and reranking. */
  @JsonProperty("ranking")
  public abstract Optional<RagRetrievalConfigRanking> ranking();

  /** Optional. The number of contexts to retrieve. */
  @JsonProperty("topK")
  public abstract Optional<Integer> topK();

  /** Instantiates a builder for RagRetrievalConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_RagRetrievalConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for RagRetrievalConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `RagRetrievalConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_RagRetrievalConfig.Builder();
    }

    /**
     * Setter for filter.
     *
     * <p>filter: Optional. Config for filters.
     */
    @JsonProperty("filter")
    public abstract Builder filter(RagRetrievalConfigFilter filter);

    /**
     * Setter for filter builder.
     *
     * <p>filter: Optional. Config for filters.
     */
    @CanIgnoreReturnValue
    public Builder filter(RagRetrievalConfigFilter.Builder filterBuilder) {
      return filter(filterBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder filter(Optional<RagRetrievalConfigFilter> filter);

    /** Clears the value of filter field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearFilter() {
      return filter(Optional.empty());
    }

    /**
     * Setter for hybridSearch.
     *
     * <p>hybridSearch: Optional. Config for Hybrid Search.
     */
    @JsonProperty("hybridSearch")
    public abstract Builder hybridSearch(RagRetrievalConfigHybridSearch hybridSearch);

    /**
     * Setter for hybridSearch builder.
     *
     * <p>hybridSearch: Optional. Config for Hybrid Search.
     */
    @CanIgnoreReturnValue
    public Builder hybridSearch(RagRetrievalConfigHybridSearch.Builder hybridSearchBuilder) {
      return hybridSearch(hybridSearchBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder hybridSearch(Optional<RagRetrievalConfigHybridSearch> hybridSearch);

    /** Clears the value of hybridSearch field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearHybridSearch() {
      return hybridSearch(Optional.empty());
    }

    /**
     * Setter for ranking.
     *
     * <p>ranking: Optional. Config for ranking and reranking.
     */
    @JsonProperty("ranking")
    public abstract Builder ranking(RagRetrievalConfigRanking ranking);

    /**
     * Setter for ranking builder.
     *
     * <p>ranking: Optional. Config for ranking and reranking.
     */
    @CanIgnoreReturnValue
    public Builder ranking(RagRetrievalConfigRanking.Builder rankingBuilder) {
      return ranking(rankingBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder ranking(Optional<RagRetrievalConfigRanking> ranking);

    /** Clears the value of ranking field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRanking() {
      return ranking(Optional.empty());
    }

    /**
     * Setter for topK.
     *
     * <p>topK: Optional. The number of contexts to retrieve.
     */
    @JsonProperty("topK")
    public abstract Builder topK(Integer topK);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder topK(Optional<Integer> topK);

    /** Clears the value of topK field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTopK() {
      return topK(Optional.empty());
    }

    public abstract RagRetrievalConfig build();
  }

  /** Deserializes a JSON string to a RagRetrievalConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static RagRetrievalConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, RagRetrievalConfig.class);
  }
}
