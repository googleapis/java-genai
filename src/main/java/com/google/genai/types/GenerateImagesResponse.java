// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.List;
import java.util.Optional;

/** The output images response. */
@AutoValue
@JsonDeserialize(builder = AutoValue_GenerateImagesResponse.Builder.class)
public abstract class GenerateImagesResponse extends JsonSerializable {
  /** List of generated images. */
  public abstract Optional<List<GeneratedImage>> getGeneratedImages();

  /** Instantiates a builder for GenerateImagesResponse. */
  public static Builder builder() {
    return new AutoValue_GenerateImagesResponse.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerateImagesResponse. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("generatedImages")
    public abstract Builder setGeneratedImages(List<GeneratedImage> generatedImages);

    public abstract GenerateImagesResponse build();
  }

  /** Deserializes a JSON string to a GenerateImagesResponse object. */
  public static GenerateImagesResponse fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerateImagesResponse.class);
  }
}
