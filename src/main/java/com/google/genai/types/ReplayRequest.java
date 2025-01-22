// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** Represents a single request in a replay. */
@AutoValue
@JsonDeserialize(builder = AutoValue_ReplayRequest.Builder.class)
public abstract class ReplayRequest extends JsonSerializable {
  /** */
  public abstract Optional<String> getMethod();

  /** */
  public abstract Optional<String> getUrl();

  /** */
  public abstract Optional<Map<String, String>> getHeaders();

  /** */
  public abstract Optional<List<Map<String, Object>>> getBodySegments();

  /** Instantiates a builder for ReplayRequest. */
  public static Builder builder() {
    return new AutoValue_ReplayRequest.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ReplayRequest. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("method")
    public abstract Builder setMethod(String method);

    @JsonProperty("url")
    public abstract Builder setUrl(String url);

    @JsonProperty("headers")
    public abstract Builder setHeaders(Map<String, String> headers);

    @JsonProperty("bodySegments")
    public abstract Builder setBodySegments(List<Map<String, Object>> bodySegments);

    public abstract ReplayRequest build();
  }

  /** Deserializes a JSON string to a ReplayRequest object. */
  public static ReplayRequest fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ReplayRequest.class);
  }
}
