// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.List;
import java.util.Optional;

/** Represents a recorded session. */
@AutoValue
@JsonDeserialize(builder = AutoValue_ReplayFile.Builder.class)
public abstract class ReplayFile extends JsonSerializable {
  /** */
  public abstract Optional<String> getReplayId();

  /** */
  public abstract Optional<List<ReplayInteraction>> getInteractions();

  /** Instantiates a builder for ReplayFile. */
  public static Builder builder() {
    return new AutoValue_ReplayFile.Builder();
  }

  /** Builder for ReplayFile. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("replayId")
    public abstract Builder setReplayId(String replayId);

    @JsonProperty("interactions")
    public abstract Builder setInteractions(List<ReplayInteraction> interactions);

    public abstract ReplayFile build();
  }

  /** Serializes the ReplayFile object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a ReplayFile object. */
  public static ReplayFile fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ReplayFile.class);
  }
}
