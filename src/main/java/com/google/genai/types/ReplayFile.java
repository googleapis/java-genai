/*
 * Copyright 2025 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// Auto-generated code. Do not edit.

package com.google.genai.types;

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** Represents a recorded session. */
@AutoValue
@JsonDeserialize(builder = ReplayFile.Builder.class)
public abstract class ReplayFile extends JsonSerializable {
  /** */
  @JsonProperty("replay_id")
  public abstract Optional<String> replayId();

  /** */
  @JsonProperty("interactions")
  public abstract Optional<List<ReplayInteraction>> interactions();

  /** Instantiates a builder for ReplayFile. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ReplayFile.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ReplayFile. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ReplayFile.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ReplayFile.Builder();
    }

    /**
     * Setter for replayId.
     *
     * <p>replayId:
     */
    @JsonProperty("replay_id")
    public abstract Builder replayId(String replayId);

    /**
     * Setter for interactions.
     *
     * <p>interactions:
     */
    @JsonProperty("interactions")
    public abstract Builder interactions(List<ReplayInteraction> interactions);

    /**
     * Setter for interactions.
     *
     * <p>interactions:
     */
    public Builder interactions(ReplayInteraction... interactions) {
      return interactions(Arrays.asList(interactions));
    }

    /**
     * Setter for interactions builder.
     *
     * <p>interactions:
     */
    public Builder interactions(ReplayInteraction.Builder... interactionsBuilders) {
      return interactions(
          Arrays.asList(interactionsBuilders).stream()
              .map(ReplayInteraction.Builder::build)
              .collect(toImmutableList()));
    }

    public abstract ReplayFile build();
  }

  /** Deserializes a JSON string to a ReplayFile object. */
  @ExcludeFromGeneratedCoverageReport
  public static ReplayFile fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ReplayFile.class);
  }
}
