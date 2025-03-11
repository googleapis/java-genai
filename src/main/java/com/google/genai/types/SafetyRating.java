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

import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.util.Optional;

/** Safety rating corresponding to the generated content. */
@GenerateTypeAdapter
@AutoValue
public abstract class SafetyRating extends JsonSerializable {
  /** Output only. Indicates whether the content was filtered out because of this rating. */
  @SerializedName("blocked")
  public abstract Optional<Boolean> blocked();

  /** Output only. Harm category. */
  @SerializedName("category")
  public abstract Optional<String> category();

  /** Output only. Harm probability levels in the content. */
  @SerializedName("probability")
  public abstract Optional<String> probability();

  /** Output only. Harm probability score. */
  @SerializedName("probabilityScore")
  public abstract Optional<Float> probabilityScore();

  /** Output only. Harm severity levels in the content. */
  @SerializedName("severity")
  public abstract Optional<String> severity();

  /** Output only. Harm severity score. */
  @SerializedName("severityScore")
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
    @SerializedName("blocked")
    public abstract Builder blocked(boolean blocked);

    @SerializedName("blocked")
    abstract Builder blocked(Optional<Boolean> blocked);

    @SerializedName("category")
    public abstract Builder category(String category);

    @SerializedName("category")
    abstract Builder category(Optional<String> category);

    @SerializedName("probability")
    public abstract Builder probability(String probability);

    @SerializedName("probability")
    abstract Builder probability(Optional<String> probability);

    @SerializedName("probabilityScore")
    public abstract Builder probabilityScore(Float probabilityScore);

    @SerializedName("probabilityScore")
    abstract Builder probabilityScore(Optional<Float> probabilityScore);

    @SerializedName("severity")
    public abstract Builder severity(String severity);

    @SerializedName("severity")
    abstract Builder severity(Optional<String> severity);

    @SerializedName("severityScore")
    public abstract Builder severityScore(Float severityScore);

    @SerializedName("severityScore")
    abstract Builder severityScore(Optional<Float> severityScore);

    public abstract SafetyRating build();
  }

  /** Deserializes a JSON string to a SafetyRating object. */
  public static SafetyRating fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SafetyRating.class);
  }
}
