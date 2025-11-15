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

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * The sources that were used to generate the place answer. This includes review snippets and photos
 * that were used to generate the answer, as well as URIs to flag content. This data type is not
 * supported in Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = GroundingChunkMapsPlaceAnswerSources.Builder.class)
public abstract class GroundingChunkMapsPlaceAnswerSources extends JsonSerializable {
  /** Snippets of reviews that were used to generate the answer. */
  @JsonProperty("reviewSnippets")
  public abstract Optional<List<GroundingChunkMapsPlaceAnswerSourcesReviewSnippet>>
      reviewSnippets();

  /** Instantiates a builder for GroundingChunkMapsPlaceAnswerSources. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GroundingChunkMapsPlaceAnswerSources.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GroundingChunkMapsPlaceAnswerSources. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `GroundingChunkMapsPlaceAnswerSources.builder()` for
     * instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GroundingChunkMapsPlaceAnswerSources.Builder();
    }

    /**
     * Setter for reviewSnippets.
     *
     * <p>reviewSnippets: Snippets of reviews that were used to generate the answer.
     */
    @JsonProperty("reviewSnippets")
    public abstract Builder reviewSnippets(
        List<GroundingChunkMapsPlaceAnswerSourcesReviewSnippet> reviewSnippets);

    /**
     * Setter for reviewSnippets.
     *
     * <p>reviewSnippets: Snippets of reviews that were used to generate the answer.
     */
    @CanIgnoreReturnValue
    public Builder reviewSnippets(
        GroundingChunkMapsPlaceAnswerSourcesReviewSnippet... reviewSnippets) {
      return reviewSnippets(Arrays.asList(reviewSnippets));
    }

    /**
     * Setter for reviewSnippets builder.
     *
     * <p>reviewSnippets: Snippets of reviews that were used to generate the answer.
     */
    @CanIgnoreReturnValue
    public Builder reviewSnippets(
        GroundingChunkMapsPlaceAnswerSourcesReviewSnippet.Builder... reviewSnippetsBuilders) {
      return reviewSnippets(
          Arrays.asList(reviewSnippetsBuilders).stream()
              .map(GroundingChunkMapsPlaceAnswerSourcesReviewSnippet.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder reviewSnippets(
        Optional<List<GroundingChunkMapsPlaceAnswerSourcesReviewSnippet>> reviewSnippets);

    /** Clears the value of reviewSnippets field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearReviewSnippets() {
      return reviewSnippets(Optional.empty());
    }

    public abstract GroundingChunkMapsPlaceAnswerSources build();
  }

  /** Deserializes a JSON string to a GroundingChunkMapsPlaceAnswerSources object. */
  @ExcludeFromGeneratedCoverageReport
  public static GroundingChunkMapsPlaceAnswerSources fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GroundingChunkMapsPlaceAnswerSources.class);
  }
}
