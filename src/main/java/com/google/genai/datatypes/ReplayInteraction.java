// Auto-generated code. Do not edit.

package com.google.genai.datatypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Represents a single interaction, request and response in a replay. */
@AutoValue
@JsonDeserialize(builder = AutoValue_ReplayInteraction.Builder.class)
public abstract class ReplayInteraction extends JsonSerializable {
  /** */
  public abstract Optional<ReplayRequest> getRequest();

  /** */
  public abstract Optional<ReplayResponse> getResponse();

  /** Instantiates a builder for ReplayInteraction. */
  public static Builder builder() {
    return new AutoValue_ReplayInteraction.Builder();
  }

  /** Builder for ReplayInteraction. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("request")
    public abstract Builder setRequest(ReplayRequest request);

    @JsonProperty("response")
    public abstract Builder setResponse(ReplayResponse response);

    public abstract ReplayInteraction build();
  }

  /** Serializes the ReplayInteraction object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a ReplayInteraction object. */
  public static ReplayInteraction fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ReplayInteraction.class);
  }
}
