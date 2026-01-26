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

package com.google.genai.types.interactions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import com.google.genai.types.ExcludeFromGeneratedCoverageReport;

/**
 * Configuration for dynamic agents.
 *
 * <p>DynamicAgentConfig allows configuration of agents with dynamic behavior. This is a minimal
 * configuration type with extensibility support.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * AgentConfig config = DynamicAgentConfig.create();
 *
 * CreateInteractionConfig interactionConfig = CreateInteractionConfig.builder()
 *     .agent("agent-name")
 *     .input("Your input")
 *     .agentConfig(config)
 *     .build();
 * }</pre>
 */
@AutoValue
@JsonDeserialize(builder = DynamicAgentConfig.Builder.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("dynamic")
public abstract class DynamicAgentConfig extends JsonSerializable implements AgentConfig {

  /** Creates a new DynamicAgentConfig instance. */
  @ExcludeFromGeneratedCoverageReport
  public static DynamicAgentConfig create() {
    return new AutoValue_DynamicAgentConfig.Builder().build();
  }

  /** Instantiates a builder for DynamicAgentConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_DynamicAgentConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for DynamicAgentConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use {@code DynamicAgentConfig.builder()} for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_DynamicAgentConfig.Builder();
    }

    /** Builds the DynamicAgentConfig instance. */
    public abstract DynamicAgentConfig build();
  }

  /** Deserializes a DynamicAgentConfig from a JSON string. */
  @ExcludeFromGeneratedCoverageReport
  public static DynamicAgentConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, DynamicAgentConfig.class);
  }
}
