// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** The parameters for generating an image. */
@AutoValue
@JsonDeserialize(builder = AutoValue_GenerateImageParameters.Builder.class)
public abstract class GenerateImageParameters extends JsonSerializable {
  /**
   * ID of the model to use. For a list of models, see `Google models
   * <https://cloud.google.com/vertex-ai/generative-ai/docs/learn/models>`_.
   */
  public abstract Optional<String> getModel();

  /** Text prompt that typically describes the image to output. */
  public abstract Optional<String> getPrompt();

  /** Configuration for generating an image. */
  public abstract Optional<GenerateImageConfig> getConfig();

  /** Instantiates a builder for GenerateImageParameters. */
  public static Builder builder() {
    return new AutoValue_GenerateImageParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerateImageParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("model")
    public abstract Builder setModel(String model);

    @JsonProperty("prompt")
    public abstract Builder setPrompt(String prompt);

    @JsonProperty("config")
    public abstract Builder setConfig(GenerateImageConfig config);

    public abstract GenerateImageParameters build();
  }

  /** Deserializes a JSON string to a GenerateImageParameters object. */
  public static GenerateImageParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerateImageParameters.class);
  }
}
