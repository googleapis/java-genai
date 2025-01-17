// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Configuration for a Style reference image. */
@AutoValue
@JsonDeserialize(builder = AutoValue_StyleReferenceConfig.Builder.class)
public abstract class StyleReferenceConfig extends JsonSerializable {
  /** A text description of the style to use for the generated image. */
  public abstract Optional<String> getStyleDescription();

  /** Instantiates a builder for StyleReferenceConfig. */
  public static Builder builder() {
    return new AutoValue_StyleReferenceConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for StyleReferenceConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("styleDescription")
    public abstract Builder setStyleDescription(String styleDescription);

    public abstract StyleReferenceConfig build();
  }

  /** Deserializes a JSON string to a StyleReferenceConfig object. */
  public static StyleReferenceConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, StyleReferenceConfig.class);
  }
}
