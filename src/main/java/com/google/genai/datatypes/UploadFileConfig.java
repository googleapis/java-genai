// Auto-generated code. Do not edit.

package com.google.genai.datatypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Map;
import java.util.Optional;

/** Used to override the default configuration. */
@AutoValue
@JsonDeserialize(builder = AutoValue_UploadFileConfig.Builder.class)
public abstract class UploadFileConfig extends JsonSerializable {
  /** Used to override HTTP request options. */
  public abstract Optional<Map<String, Object>> getHttpOptions();

  /**
   * The name of the file in the destination (e.g., 'files/sample-image'. If not provided one will
   * be generated.
   */
  public abstract Optional<String> getName();

  /**
   * mime_type: The MIME type of the file. If not provided, it will be inferred from the file
   * extension.
   */
  public abstract Optional<String> getMimeType();

  /** Optional display name of the file. */
  public abstract Optional<String> getDisplayName();

  /** Instantiates a builder for UploadFileConfig. */
  public static Builder builder() {
    return new AutoValue_UploadFileConfig.Builder();
  }

  /** Builder for UploadFileConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("httpOptions")
    public abstract Builder setHttpOptions(Map<String, Object> httpOptions);

    @JsonProperty("name")
    public abstract Builder setName(String name);

    @JsonProperty("mimeType")
    public abstract Builder setMimeType(String mimeType);

    @JsonProperty("displayName")
    public abstract Builder setDisplayName(String displayName);

    public abstract UploadFileConfig build();
  }

  /** Serializes the UploadFileConfig object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a UploadFileConfig object. */
  public static UploadFileConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, UploadFileConfig.class);
  }
}
