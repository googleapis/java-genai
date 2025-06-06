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

/** Config for ranking and reranking. */
@AutoValue
@JsonDeserialize(builder = RagRetrievalConfigRanking.Builder.class)
public abstract class RagRetrievalConfigRanking extends JsonSerializable {
  /** Optional. Config for LlmRanker. */
  @JsonProperty("llmRanker")
  public abstract Optional<RagRetrievalConfigRankingLlmRanker> llmRanker();

  /** Optional. Config for Rank Service. */
  @JsonProperty("rankService")
  public abstract Optional<RagRetrievalConfigRankingRankService> rankService();

  /** Instantiates a builder for RagRetrievalConfigRanking. */
  public static Builder builder() {
    return new AutoValue_RagRetrievalConfigRanking.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for RagRetrievalConfigRanking. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `RagRetrievalConfigRanking.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_RagRetrievalConfigRanking.Builder();
    }

    /**
     * Setter for llmRanker.
     *
     * <p>llmRanker: Optional. Config for LlmRanker.
     */
    @JsonProperty("llmRanker")
    public abstract Builder llmRanker(RagRetrievalConfigRankingLlmRanker llmRanker);

    /**
     * Setter for llmRanker builder.
     *
     * <p>llmRanker: Optional. Config for LlmRanker.
     */
    public Builder llmRanker(RagRetrievalConfigRankingLlmRanker.Builder llmRankerBuilder) {
      return llmRanker(llmRankerBuilder.build());
    }

    /**
     * Setter for rankService.
     *
     * <p>rankService: Optional. Config for Rank Service.
     */
    @JsonProperty("rankService")
    public abstract Builder rankService(RagRetrievalConfigRankingRankService rankService);

    /**
     * Setter for rankService builder.
     *
     * <p>rankService: Optional. Config for Rank Service.
     */
    public Builder rankService(RagRetrievalConfigRankingRankService.Builder rankServiceBuilder) {
      return rankService(rankServiceBuilder.build());
    }

    public abstract RagRetrievalConfigRanking build();
  }

  /** Deserializes a JSON string to a RagRetrievalConfigRanking object. */
  public static RagRetrievalConfigRanking fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, RagRetrievalConfigRanking.class);
  }
}
