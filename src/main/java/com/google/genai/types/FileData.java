// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** URI based data. */
@AutoValue
@JsonDeserialize(builder = AutoValue_FileData.Builder.class)
public abstract class FileData extends JsonSerializable {
  /** Required. URI. */
  public abstract Optional<String> getFileUri();

  /** Required. The IANA standard MIME type of the source data. */
  public abstract Optional<String> getMimeType();

  /** Instantiates a builder for FileData. */
  public static Builder builder() {
    return new AutoValue_FileData.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for FileData. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("fileUri")
    public abstract Builder setFileUri(String fileUri);

    @JsonProperty("mimeType")
    public abstract Builder setMimeType(String mimeType);

    public abstract FileData build();
  }

  /** Deserializes a JSON string to a FileData object. */
  public static FileData fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, FileData.class);
  }
}
