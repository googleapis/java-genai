// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/**
 * Configuration for upscaling an image.
 *
 * <p>For more information on this configuration, refer to the `Imagen API reference documentation
 * <https://cloud.google.com/vertex-ai/generative-ai/docs/model-reference/imagen-api>`_.
 */
@AutoValue
@JsonDeserialize(builder = AutoValue_UpscaleImageConfig.Builder.class)
public abstract class UpscaleImageConfig extends JsonSerializable {
  /** Whether to include a reason for filtered-out images in the response. */
  public abstract Optional<Boolean> getIncludeRaiReason();

  /** The image format that the output should be saved as. */
  public abstract Optional<String> getOutputMimeType();

  /** The level of compression if the ``output_mime_type`` is ``image/jpeg``. */
  public abstract Optional<Integer> getOutputCompressionQuality();

  /** Instantiates a builder for UpscaleImageConfig. */
  public static Builder builder() {
    return new AutoValue_UpscaleImageConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for UpscaleImageConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("includeRaiReason")
    public abstract Builder setIncludeRaiReason(boolean includeRaiReason);

    @JsonProperty("outputMimeType")
    public abstract Builder setOutputMimeType(String outputMimeType);

    @JsonProperty("outputCompressionQuality")
    public abstract Builder setOutputCompressionQuality(Integer outputCompressionQuality);

    public abstract UpscaleImageConfig build();
  }

  /** Deserializes a JSON string to a UpscaleImageConfig object. */
  public static UpscaleImageConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, UpscaleImageConfig.class);
  }
}
