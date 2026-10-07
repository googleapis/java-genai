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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/**
 * Updates to the context of the current session.
 *
 * <p>Only fields that are set will be updated. Updates are guaranteed to be processed *in order*
 * with the rest of the inputs.
 */
@AutoValue
@JsonDeserialize(builder = LiveClientContextUpdate.Builder.class)
public abstract class LiveClientContextUpdate extends JsonSerializable {
  /**
   * Updated system instruction for the model. If set, overrides
   * `BidiGenerateContentSetup.system_instruction`. The system instructions are part of the model
   * preamble, so updating them invalidates the prefix cache. Clients should only update this field
   * when strictly necessary as it might have a performance impact on the model generation.
   */
  @JsonProperty("systemInstruction")
  public abstract Optional<Content> systemInstruction();

  /**
   * An updated list of tools the model may use to generate the subsequent responses. If set, this
   * list replaces the previously provided tools. The tools are part of the model preamble, so
   * updating them invalidates the prefix cache. Clients should only update this field when strictly
   * necessary as it might have a performance impact on the model generation.
   */
  @JsonProperty("tools")
  public abstract Optional<LiveClientContextUpdateTools> tools();

  /** Instantiates a builder for LiveClientContextUpdate. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_LiveClientContextUpdate.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for LiveClientContextUpdate. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `LiveClientContextUpdate.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_LiveClientContextUpdate.Builder();
    }

    /**
     * Setter for systemInstruction.
     *
     * <p>systemInstruction: Updated system instruction for the model. If set, overrides
     * `BidiGenerateContentSetup.system_instruction`. The system instructions are part of the model
     * preamble, so updating them invalidates the prefix cache. Clients should only update this
     * field when strictly necessary as it might have a performance impact on the model generation.
     */
    @JsonProperty("systemInstruction")
    public abstract Builder systemInstruction(Content systemInstruction);

    /**
     * Setter for systemInstruction builder.
     *
     * <p>systemInstruction: Updated system instruction for the model. If set, overrides
     * `BidiGenerateContentSetup.system_instruction`. The system instructions are part of the model
     * preamble, so updating them invalidates the prefix cache. Clients should only update this
     * field when strictly necessary as it might have a performance impact on the model generation.
     */
    @CanIgnoreReturnValue
    public Builder systemInstruction(Content.Builder systemInstructionBuilder) {
      return systemInstruction(systemInstructionBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder systemInstruction(Optional<Content> systemInstruction);

    /** Clears the value of systemInstruction field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSystemInstruction() {
      return systemInstruction(Optional.empty());
    }

    /**
     * Setter for tools.
     *
     * <p>tools: An updated list of tools the model may use to generate the subsequent responses. If
     * set, this list replaces the previously provided tools. The tools are part of the model
     * preamble, so updating them invalidates the prefix cache. Clients should only update this
     * field when strictly necessary as it might have a performance impact on the model generation.
     */
    @JsonProperty("tools")
    public abstract Builder tools(LiveClientContextUpdateTools tools);

    /**
     * Setter for tools builder.
     *
     * <p>tools: An updated list of tools the model may use to generate the subsequent responses. If
     * set, this list replaces the previously provided tools. The tools are part of the model
     * preamble, so updating them invalidates the prefix cache. Clients should only update this
     * field when strictly necessary as it might have a performance impact on the model generation.
     */
    @CanIgnoreReturnValue
    public Builder tools(LiveClientContextUpdateTools.Builder toolsBuilder) {
      return tools(toolsBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder tools(Optional<LiveClientContextUpdateTools> tools);

    /** Clears the value of tools field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTools() {
      return tools(Optional.empty());
    }

    public abstract LiveClientContextUpdate build();
  }

  /** Deserializes a JSON string to a LiveClientContextUpdate object. */
  @ExcludeFromGeneratedCoverageReport
  public static LiveClientContextUpdate fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, LiveClientContextUpdate.class);
  }
}
