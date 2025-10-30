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

/**
 * Incremental update of the current conversation delivered from the client.
 *
 * <p>All the content here will unconditionally be appended to the conversation history and used as
 * part of the prompt to the model to generate content.
 *
 * <p>A message here will interrupt any current model generation.
 */
@AutoValue
@JsonDeserialize(builder = LiveClientContent.Builder.class)
public abstract class LiveClientContent extends JsonSerializable {
  /**
   * The content appended to the current conversation with the model.
   *
   * <p>For single-turn queries, this is a single instance. For multi-turn queries, this is a
   * repeated field that contains conversation history and latest request.
   */
  @JsonProperty("turns")
  public abstract Optional<List<Content>> turns();

  /**
   * If true, indicates that the server content generation should start with the currently
   * accumulated prompt. Otherwise, the server will await additional messages before starting
   * generation.
   */
  @JsonProperty("turnComplete")
  public abstract Optional<Boolean> turnComplete();

  /** Instantiates a builder for LiveClientContent. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_LiveClientContent.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for LiveClientContent. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `LiveClientContent.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_LiveClientContent.Builder();
    }

    /**
     * Setter for turns.
     *
     * <p>turns: The content appended to the current conversation with the model.
     *
     * <p>For single-turn queries, this is a single instance. For multi-turn queries, this is a
     * repeated field that contains conversation history and latest request.
     */
    @JsonProperty("turns")
    public abstract Builder turns(List<Content> turns);

    /**
     * Setter for turns.
     *
     * <p>turns: The content appended to the current conversation with the model.
     *
     * <p>For single-turn queries, this is a single instance. For multi-turn queries, this is a
     * repeated field that contains conversation history and latest request.
     */
    public Builder turns(Content... turns) {
      return turns(Arrays.asList(turns));
    }

    /**
     * Setter for turns builder.
     *
     * <p>turns: The content appended to the current conversation with the model.
     *
     * <p>For single-turn queries, this is a single instance. For multi-turn queries, this is a
     * repeated field that contains conversation history and latest request.
     */
    public Builder turns(Content.Builder... turnsBuilders) {
      return turns(
          Arrays.asList(turnsBuilders).stream()
              .map(Content.Builder::build)
              .collect(toImmutableList()));
    }

    /**
     * Setter for turnComplete.
     *
     * <p>turnComplete: If true, indicates that the server content generation should start with the
     * currently accumulated prompt. Otherwise, the server will await additional messages before
     * starting generation.
     */
    @JsonProperty("turnComplete")
    public abstract Builder turnComplete(boolean turnComplete);

    public abstract LiveClientContent build();
  }

  /** Deserializes a JSON string to a LiveClientContent object. */
  @ExcludeFromGeneratedCoverageReport
  public static LiveClientContent fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, LiveClientContent.class);
  }
}
