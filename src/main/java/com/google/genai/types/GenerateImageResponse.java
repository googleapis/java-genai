// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.List;
import java.util.Optional;

/** Class that represents the output image response. */
@AutoValue
@JsonDeserialize(builder = AutoValue_GenerateImageResponse.Builder.class)
public abstract class GenerateImageResponse extends JsonSerializable {
  /** List of generated images. */
  public abstract Optional<List<GeneratedImage>> getGeneratedImages();

  /** Instantiates a builder for GenerateImageResponse. */
  public static Builder builder() {
    return new AutoValue_GenerateImageResponse.Builder();
  }

  /** Builder for GenerateImageResponse. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("generatedImages")
    public abstract Builder setGeneratedImages(List<GeneratedImage> generatedImages);

    public abstract GenerateImageResponse build();
  }

  /** Serializes the GenerateImageResponse object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a GenerateImageResponse object. */
  public static GenerateImageResponse fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerateImageResponse.class);
  }
}
