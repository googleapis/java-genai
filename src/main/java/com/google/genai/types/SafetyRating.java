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
  @JsonProperty("blocked")
  public abstract Optional<Boolean> blocked();

  /** Output only. Harm category. */
  @JsonProperty("category")
  public abstract Optional<String> category();

  /** Output only. Harm probability levels in the content. */
  @JsonProperty("probability")
  public abstract Optional<String> probability();

  /** Output only. Harm probability score. */
  @JsonProperty("probabilityScore")
  public abstract Optional<Float> probabilityScore();

  /** Output only. Harm severity levels in the content. */
  @JsonProperty("severity")
  public abstract Optional<String> severity();

  /** Output only. Harm severity score. */
  @JsonProperty("severityScore")
  public abstract Optional<Float> severityScore();

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
    public abstract Builder blocked(boolean blocked);

    @JsonProperty("category")
    public abstract Builder category(String category);

    @JsonProperty("probability")
    public abstract Builder probability(String probability);

    @JsonProperty("probabilityScore")
    public abstract Builder probabilityScore(Float probabilityScore);

    @JsonProperty("severity")
    public abstract Builder severity(String severity);

    @JsonProperty("severityScore")
    public abstract Builder severityScore(Float severityScore);

    public abstract SafetyRating build();
  }

  /** Deserializes a JSON string to a SafetyRating object. */
  public static SafetyRating fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SafetyRating.class);
  }
}
