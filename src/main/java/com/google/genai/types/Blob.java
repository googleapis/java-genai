// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Content blob. */
@AutoValue
@JsonDeserialize(builder = AutoValue_Blob.Builder.class)
public abstract class Blob extends JsonSerializable {
  /** Required. Raw bytes. */
  public abstract Optional<String> getData();

  /** Required. The IANA standard MIME type of the source data. */
  public abstract Optional<String> getMimeType();

  /** Instantiates a builder for Blob. */
  public static Builder builder() {
    return new AutoValue_Blob.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for Blob. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("data")
    public abstract Builder setData(String data);

    @JsonProperty("mimeType")
    public abstract Builder setMimeType(String mimeType);

    public abstract Blob build();
  }

  /** Deserializes a JSON string to a Blob object. */
  public static Blob fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Blob.class);
  }
}
