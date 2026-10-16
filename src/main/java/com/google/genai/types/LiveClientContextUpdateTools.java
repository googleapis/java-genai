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
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * A wrapper around the list of tools.
 *
 * <p>This wrapper exists because a bare `repeated Tool` field cannot tell apart "not sending a
 * tools update" from "clearing all tools": an unset repeated field and an empty repeated field look
 * identical on the wire. Wrapping the list in a message adds a presence bit, so the two cases
 * become: - `tools` field unset: no update; keep the previously provided tools. - `tools` field set
 * (even with an empty list): replace the current tools with the provided list, which may be empty
 * to clear all tools.
 */
@AutoValue
@JsonDeserialize(builder = LiveClientContextUpdateTools.Builder.class)
public abstract class LiveClientContextUpdateTools extends JsonSerializable {
  /** The list of tools the model may use to generate the next response. */
  @JsonProperty("tools")
  public abstract Optional<List<Tool>> tools();

  /** Instantiates a builder for LiveClientContextUpdateTools. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_LiveClientContextUpdateTools.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for LiveClientContextUpdateTools. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `LiveClientContextUpdateTools.builder()` for instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_LiveClientContextUpdateTools.Builder();
    }

    /**
     * Setter for tools.
     *
     * <p>tools: The list of tools the model may use to generate the next response.
     */
    @JsonProperty("tools")
    public abstract Builder tools(List<Tool> tools);

    /**
     * Setter for tools.
     *
     * <p>tools: The list of tools the model may use to generate the next response.
     */
    @CanIgnoreReturnValue
    public Builder tools(Tool... tools) {
      return tools(Arrays.asList(tools));
    }

    /**
     * Setter for tools builder.
     *
     * <p>tools: The list of tools the model may use to generate the next response.
     */
    @CanIgnoreReturnValue
    public Builder tools(Tool.Builder... toolsBuilders) {
      return tools(
          Arrays.asList(toolsBuilders).stream()
              .map(Tool.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder tools(Optional<List<Tool>> tools);

    /** Clears the value of tools field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTools() {
      return tools(Optional.empty());
    }

    public abstract LiveClientContextUpdateTools build();
  }

  /** Deserializes a JSON string to a LiveClientContextUpdateTools object. */
  @ExcludeFromGeneratedCoverageReport
  public static LiveClientContextUpdateTools fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, LiveClientContextUpdateTools.class);
  }
}
