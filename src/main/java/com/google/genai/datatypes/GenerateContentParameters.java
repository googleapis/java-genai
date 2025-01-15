// Auto-generated code. Do not edit.

package com.google.genai.datatypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.List;
import java.util.Optional;

/** Class for configuring the content of the request to the model. */
@AutoValue
@JsonDeserialize(builder = AutoValue_GenerateContentParameters.Builder.class)
public abstract class GenerateContentParameters extends JsonSerializable {
  /**
   * ID of the model to use. For a list of models, see `Google models
   * <https://cloud.google.com/vertex-ai/generative-ai/docs/learn/models>`_.
   */
  public abstract Optional<String> getModel();

  /** Content of the request. */
  public abstract Optional<List<Content>> getContents();

  /** Configuration that contains optional model parameters. */
  public abstract Optional<GenerateContentConfig> getConfig();

  /** Instantiates a builder for GenerateContentParameters. */
  public static Builder builder() {
    return new AutoValue_GenerateContentParameters.Builder();
  }

  /** Builder for GenerateContentParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("model")
    public abstract Builder setModel(String model);

    @JsonProperty("contents")
    public abstract Builder setContents(List<Content> contents);

    @JsonProperty("config")
    public abstract Builder setConfig(GenerateContentConfig config);

    public abstract GenerateContentParameters build();
  }

  /** Serializes the GenerateContentParameters object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a GenerateContentParameters object. */
  public static GenerateContentParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerateContentParameters.class);
  }
}
