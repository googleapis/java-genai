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
 * Content filter results for a prompt sent in the request. Note: This is sent only in the first
 * stream chunk and only if no candidates were generated due to content violations.
 */
@AutoValue
@JsonDeserialize(builder = GenerateContentResponsePromptFeedback.Builder.class)
public abstract class GenerateContentResponsePromptFeedback extends JsonSerializable {
  /** Output only. The reason why the prompt was blocked. */
  @JsonProperty("blockReason")
  public abstract Optional<BlockedReason> blockReason();

  /**
   * Output only. A readable message that explains the reason why the prompt was blocked. This field
   * is not supported in Gemini API.
   */
  @JsonProperty("blockReasonMessage")
  public abstract Optional<String> blockReasonMessage();

  /** Output only. A list of safety ratings for the prompt. There is one rating per category. */
  @JsonProperty("safetyRatings")
  public abstract Optional<List<SafetyRating>> safetyRatings();

  /** Instantiates a builder for GenerateContentResponsePromptFeedback. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GenerateContentResponsePromptFeedback.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerateContentResponsePromptFeedback. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `GenerateContentResponsePromptFeedback.builder()` for
     * instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GenerateContentResponsePromptFeedback.Builder();
    }

    /**
     * Setter for blockReason.
     *
     * <p>blockReason: Output only. The reason why the prompt was blocked.
     */
    @JsonProperty("blockReason")
    public abstract Builder blockReason(BlockedReason blockReason);

    /**
     * Setter for blockReason given a known enum.
     *
     * <p>blockReason: Output only. The reason why the prompt was blocked.
     */
    @CanIgnoreReturnValue
    public Builder blockReason(BlockedReason.Known knownType) {
      return blockReason(new BlockedReason(knownType));
    }

    /**
     * Setter for blockReason given a string.
     *
     * <p>blockReason: Output only. The reason why the prompt was blocked.
     */
    @CanIgnoreReturnValue
    public Builder blockReason(String blockReason) {
      return blockReason(new BlockedReason(blockReason));
    }

    /**
     * Setter for blockReasonMessage.
     *
     * <p>blockReasonMessage: Output only. A readable message that explains the reason why the
     * prompt was blocked. This field is not supported in Gemini API.
     */
    @JsonProperty("blockReasonMessage")
    public abstract Builder blockReasonMessage(String blockReasonMessage);

    /**
     * Setter for safetyRatings.
     *
     * <p>safetyRatings: Output only. A list of safety ratings for the prompt. There is one rating
     * per category.
     */
    @JsonProperty("safetyRatings")
    public abstract Builder safetyRatings(List<SafetyRating> safetyRatings);

    /**
     * Setter for safetyRatings.
     *
     * <p>safetyRatings: Output only. A list of safety ratings for the prompt. There is one rating
     * per category.
     */
    public Builder safetyRatings(SafetyRating... safetyRatings) {
      return safetyRatings(Arrays.asList(safetyRatings));
    }

    /**
     * Setter for safetyRatings builder.
     *
     * <p>safetyRatings: Output only. A list of safety ratings for the prompt. There is one rating
     * per category.
     */
    public Builder safetyRatings(SafetyRating.Builder... safetyRatingsBuilders) {
      return safetyRatings(
          Arrays.asList(safetyRatingsBuilders).stream()
              .map(SafetyRating.Builder::build)
              .collect(toImmutableList()));
    }

    public abstract GenerateContentResponsePromptFeedback build();
  }

  /** Deserializes a JSON string to a GenerateContentResponsePromptFeedback object. */
  @ExcludeFromGeneratedCoverageReport
  public static GenerateContentResponsePromptFeedback fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerateContentResponsePromptFeedback.class);
  }
}
