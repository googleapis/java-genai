// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** Represents a single response in a replay. */
@AutoValue
@JsonDeserialize(builder = AutoValue_ReplayResponse.Builder.class)
public abstract class ReplayResponse extends JsonSerializable {
  /** */
  public abstract Optional<Integer> getStatusCode();

  /** */
  public abstract Optional<Map<String, String>> getHeaders();

  /** */
  public abstract Optional<List<Map<String, Object>>> getBodySegments();

  /** */
  public abstract Optional<List<Map<String, Object>>> getSdkResponseSegments();

  /** Instantiates a builder for ReplayResponse. */
  public static Builder builder() {
    return new AutoValue_ReplayResponse.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ReplayResponse. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("statusCode")
    public abstract Builder setStatusCode(Integer statusCode);

    @JsonProperty("headers")
    public abstract Builder setHeaders(Map<String, String> headers);

    @JsonProperty("bodySegments")
    public abstract Builder setBodySegments(List<Map<String, Object>> bodySegments);

    @JsonProperty("sdkResponseSegments")
    public abstract Builder setSdkResponseSegments(List<Map<String, Object>> sdkResponseSegments);

    public abstract ReplayResponse build();
  }

  /** Deserializes a JSON string to a ReplayResponse object. */
  public static ReplayResponse fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ReplayResponse.class);
  }
}
