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

/** Config for Rank Service. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = RagRetrievalConfigRankingRankService.Builder.class)
public abstract class RagRetrievalConfigRankingRankService extends JsonSerializable {
  /** Optional. The model name of the rank service. Format: `semantic-ranker-512@latest` */
  @JsonProperty("modelName")
  public abstract Optional<String> modelName();

  /** Instantiates a builder for RagRetrievalConfigRankingRankService. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_RagRetrievalConfigRankingRankService.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for RagRetrievalConfigRankingRankService. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `RagRetrievalConfigRankingRankService.builder()` for
     * instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_RagRetrievalConfigRankingRankService.Builder();
    }

    /**
     * Setter for modelName.
     *
     * <p>modelName: Optional. The model name of the rank service. Format:
     * `semantic-ranker-512@latest`
     */
    @JsonProperty("modelName")
    public abstract Builder modelName(String modelName);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder modelName(Optional<String> modelName);

    /** Clears the value of modelName field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearModelName() {
      return modelName(Optional.empty());
    }

    public abstract RagRetrievalConfigRankingRankService build();
  }

  /** Deserializes a JSON string to a RagRetrievalConfigRankingRankService object. */
  @ExcludeFromGeneratedCoverageReport
  public static RagRetrievalConfigRankingRankService fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, RagRetrievalConfigRankingRankService.class);
  }
}
