// Auto-generated code. Do not edit.

package com.google.genai.datatypes;

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

  /** Serializes the SafetyRating object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a SafetyRating object. */
  public static SafetyRating fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SafetyRating.class);
  }
}
