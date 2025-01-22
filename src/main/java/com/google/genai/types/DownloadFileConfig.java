// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Map;
import java.util.Optional;

/** Used to override the default configuration. */
@AutoValue
@JsonDeserialize(builder = AutoValue_DownloadFileConfig.Builder.class)
public abstract class DownloadFileConfig extends JsonSerializable {
  /** Used to override HTTP request options. */
  public abstract Optional<Map<String, Object>> getHttpOptions();

  /** Instantiates a builder for DownloadFileConfig. */
  public static Builder builder() {
    return new AutoValue_DownloadFileConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for DownloadFileConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("httpOptions")
    public abstract Builder setHttpOptions(Map<String, Object> httpOptions);

    public abstract DownloadFileConfig build();
  }

  /** Deserializes a JSON string to a DownloadFileConfig object. */
  public static DownloadFileConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, DownloadFileConfig.class);
  }
}
