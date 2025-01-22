// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Describes the options to customize dynamic retrieval. */
@AutoValue
@JsonDeserialize(builder = AutoValue_DynamicRetrievalConfig.Builder.class)
public abstract class DynamicRetrievalConfig extends JsonSerializable {
  /** The mode of the predictor to be used in dynamic retrieval. */
  public abstract Optional<String> getMode();

  /**
   * Optional. The threshold to be used in dynamic retrieval. If not set, a system default value is
   * used.
   */
  public abstract Optional<Float> getDynamicThreshold();

  /** Instantiates a builder for DynamicRetrievalConfig. */
  public static Builder builder() {
    return new AutoValue_DynamicRetrievalConfig.Builder();
  }

  /** Builder for DynamicRetrievalConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("mode")
    public abstract Builder setMode(String mode);

    @JsonProperty("dynamicThreshold")
    public abstract Builder setDynamicThreshold(Float dynamicThreshold);

    public abstract DynamicRetrievalConfig build();
  }

  /** Serializes the DynamicRetrievalConfig object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a DynamicRetrievalConfig object. */
  public static DynamicRetrievalConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, DynamicRetrievalConfig.class);
  }
}
