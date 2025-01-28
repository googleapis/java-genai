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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Safety rating corresponding to the generated content. */
@AutoValue
@JsonDeserialize(builder = AutoValue_SafetyRating.Builder.class)
public abstract class SafetyRating extends JsonSerializable {
  /** Output only. Indicates whether the content was filtered out because of this rating. */
  public abstract Optional<Boolean> getBlocked();

  /** Output only. Harm category. */
  public abstract Optional<String> getCategory();

  /** Output only. Harm probability levels in the content. */
  public abstract Optional<String> getProbability();

  /** Output only. Harm probability score. */
  public abstract Optional<Float> getProbabilityScore();

  /** Output only. Harm severity levels in the content. */
  public abstract Optional<String> getSeverity();

  /** Output only. Harm severity score. */
  public abstract Optional<Float> getSeverityScore();

  /** Instantiates a builder for SafetyRating. */
  public static Builder builder() {
    return new AutoValue_SafetyRating.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for SafetyRating. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("blocked")
    public abstract Builder setBlocked(boolean blocked);

    @JsonProperty("category")
    public abstract Builder setCategory(String category);

    @JsonProperty("probability")
    public abstract Builder setProbability(String probability);

    @JsonProperty("probabilityScore")
    public abstract Builder setProbabilityScore(Float probabilityScore);

    @JsonProperty("severity")
    public abstract Builder setSeverity(String severity);

    @JsonProperty("severityScore")
    public abstract Builder setSeverityScore(Float severityScore);

    public abstract SafetyRating build();
  }

  /** Deserializes a JSON string to a SafetyRating object. */
  public static SafetyRating fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SafetyRating.class);
  }
}
