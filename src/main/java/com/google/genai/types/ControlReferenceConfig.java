// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Configuration for a Control reference image. */
@AutoValue
@JsonDeserialize(builder = AutoValue_ControlReferenceConfig.Builder.class)
public abstract class ControlReferenceConfig extends JsonSerializable {
  /** The type of control reference image to use. */
  public abstract Optional<String> getControlType();

  /**
   * Defaults to False. When set to True, the control image will be computed by the model based on
   * the control type. When set to False, the control image must be provided by the user.
   */
  public abstract Optional<Boolean> getEnableControlImageComputation();

  /** Instantiates a builder for ControlReferenceConfig. */
  public static Builder builder() {
    return new AutoValue_ControlReferenceConfig.Builder();
  }

  /** Builder for ControlReferenceConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("controlType")
    public abstract Builder setControlType(String controlType);

    @JsonProperty("enableControlImageComputation")
    public abstract Builder setEnableControlImageComputation(boolean enableControlImageComputation);

    public abstract ControlReferenceConfig build();
  }

  /** Serializes the ControlReferenceConfig object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a ControlReferenceConfig object. */
  public static ControlReferenceConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ControlReferenceConfig.class);
  }
}
