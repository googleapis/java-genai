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

/**
 * A review snippet that is used to generate the answer. This data type is not supported in Gemini
 * API.
 */
@AutoValue
@JsonDeserialize(builder = GroundingChunkMapsPlaceAnswerSourcesReviewSnippet.Builder.class)
public abstract class GroundingChunkMapsPlaceAnswerSourcesReviewSnippet extends JsonSerializable {
  /** A link to show the review on Google Maps. */
  @JsonProperty("googleMapsUri")
  public abstract Optional<String> googleMapsUri();

  /** The ID of the review that is being referenced. */
  @JsonProperty("reviewId")
  public abstract Optional<String> reviewId();

  /** The title of the review. */
  @JsonProperty("title")
  public abstract Optional<String> title();

  /** Instantiates a builder for GroundingChunkMapsPlaceAnswerSourcesReviewSnippet. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GroundingChunkMapsPlaceAnswerSourcesReviewSnippet.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GroundingChunkMapsPlaceAnswerSourcesReviewSnippet. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `GroundingChunkMapsPlaceAnswerSourcesReviewSnippet.builder()`
     * for instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GroundingChunkMapsPlaceAnswerSourcesReviewSnippet.Builder();
    }

    /**
     * Setter for googleMapsUri.
     *
     * <p>googleMapsUri: A link to show the review on Google Maps.
     */
    @JsonProperty("googleMapsUri")
    public abstract Builder googleMapsUri(String googleMapsUri);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder googleMapsUri(Optional<String> googleMapsUri);

    /** Clears the value of googleMapsUri field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearGoogleMapsUri() {
      return googleMapsUri(Optional.empty());
    }

    /**
     * Setter for reviewId.
     *
     * <p>reviewId: The ID of the review that is being referenced.
     */
    @JsonProperty("reviewId")
    public abstract Builder reviewId(String reviewId);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder reviewId(Optional<String> reviewId);

    /** Clears the value of reviewId field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearReviewId() {
      return reviewId(Optional.empty());
    }

    /**
     * Setter for title.
     *
     * <p>title: The title of the review.
     */
    @JsonProperty("title")
    public abstract Builder title(String title);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder title(Optional<String> title);

    /** Clears the value of title field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTitle() {
      return title(Optional.empty());
    }

    public abstract GroundingChunkMapsPlaceAnswerSourcesReviewSnippet build();
  }

  /** Deserializes a JSON string to a GroundingChunkMapsPlaceAnswerSourcesReviewSnippet object. */
  @ExcludeFromGeneratedCoverageReport
  public static GroundingChunkMapsPlaceAnswerSourcesReviewSnippet fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(
        jsonString, GroundingChunkMapsPlaceAnswerSourcesReviewSnippet.class);
  }
}
