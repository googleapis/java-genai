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

package com.google.genai.types.interactions.content;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import com.google.genai.types.ExcludeFromGeneratedCoverageReport;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Thought content for the Interactions API.
 *
 * <p>Represents the model's internal reasoning process when thinking is enabled. This content type
 * captures the model's thought signatures and summaries that explain its reasoning steps.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * ThoughtContent thought = ThoughtContent.builder()
 *     .signature("reasoning-step-1")
 *     .summary(TextContent.of("Analyzing the problem..."))
 *     .build();
 * }</pre>
 *
 * <p>The Interactions API is available in both Vertex AI and Gemini API.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = ThoughtContent.Builder.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("thought")
public abstract class ThoughtContent extends JsonSerializable implements Content {

  @JsonProperty("signature")
  public abstract Optional<String> signature();

  @JsonProperty("summary")
  public abstract Optional<List<ThoughtSummaryContent>> summary();

  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ThoughtContent.Builder();
  }

  public abstract Builder toBuilder();

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ThoughtContent.Builder();
    }

    @JsonProperty("signature")
    public abstract Builder signature(String signature);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder signature(Optional<String> signature);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSignature() {
      return signature(Optional.empty());
    }

    @JsonProperty("summary")
    public abstract Builder summary(List<ThoughtSummaryContent> summary);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder summary(Optional<List<ThoughtSummaryContent>> summary);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSummary() {
      return summary(Optional.empty());
    }

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder summary(ThoughtSummaryContent... summary) {
      return summary(Arrays.asList(summary));
    }

    public abstract ThoughtContent build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static ThoughtContent fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ThoughtContent.class);
  }

  @ExcludeFromGeneratedCoverageReport
  public static ThoughtContent of(List<ThoughtSummaryContent> summary) {
    return builder().summary(summary).build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static ThoughtContent of(ThoughtSummaryContent... summary) {
    return builder().summary(summary).build();
  }
}
