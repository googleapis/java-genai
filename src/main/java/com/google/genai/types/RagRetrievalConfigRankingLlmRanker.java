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

/** Config for LlmRanker. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = RagRetrievalConfigRankingLlmRanker.Builder.class)
public abstract class RagRetrievalConfigRankingLlmRanker extends JsonSerializable {
  /**
   * Optional. The model name used for ranking. See [Supported
   * models](https://cloud.google.com/vertex-ai/generative-ai/docs/model-reference/inference#supported-models).
   */
  @JsonProperty("modelName")
  public abstract Optional<String> modelName();

  /** Instantiates a builder for RagRetrievalConfigRankingLlmRanker. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_RagRetrievalConfigRankingLlmRanker.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for RagRetrievalConfigRankingLlmRanker. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `RagRetrievalConfigRankingLlmRanker.builder()` for
     * instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_RagRetrievalConfigRankingLlmRanker.Builder();
    }

    /**
     * Setter for modelName.
     *
     * <p>modelName: Optional. The model name used for ranking. See [Supported
     * models](https://cloud.google.com/vertex-ai/generative-ai/docs/model-reference/inference#supported-models).
     */
    @JsonProperty("modelName")
    public abstract Builder modelName(String modelName);

    public abstract RagRetrievalConfigRankingLlmRanker build();
  }

  /** Deserializes a JSON string to a RagRetrievalConfigRankingLlmRanker object. */
  @ExcludeFromGeneratedCoverageReport
  public static RagRetrievalConfigRankingLlmRanker fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, RagRetrievalConfigRankingLlmRanker.class);
  }
}
