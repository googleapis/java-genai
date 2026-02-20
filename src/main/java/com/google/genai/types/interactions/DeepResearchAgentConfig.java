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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import com.google.genai.types.ExcludeFromGeneratedCoverageReport;
import java.util.Optional;

/**
 * Configuration for the Deep Research agent.
 *
 * <p>DeepResearchAgentConfig allows configuration of the Deep Research agent, including control
 * over thinking summaries in the response.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * // Create with thinking summaries
 * AgentConfig config = DeepResearchAgentConfig.builder()
 *     .thinkingSummaries(new ThinkingSummaries(ThinkingSummaries.Known.AUTO))
 *     .build();
 *
 * // Create without thinking summaries
 * AgentConfig config = DeepResearchAgentConfig.builder()
 *     .thinkingSummaries(new ThinkingSummaries(ThinkingSummaries.Known.NONE))
 *     .build();
 *
 * CreateInteractionConfig interactionConfig = CreateInteractionConfig.builder()
 *     .agent("deep-research-pro-preview-12-2025")
 *     .input("Your research question")
 *     .agentConfig(config)
 *     .build();
 * }</pre>
 */
@AutoValue
@JsonDeserialize(builder = DeepResearchAgentConfig.Builder.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("deep-research")
public abstract class DeepResearchAgentConfig extends JsonSerializable implements AgentConfig {

  /** Instantiates a builder for DeepResearchAgentConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_DeepResearchAgentConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /**
   * Whether to include thought summaries in response.
   *
   * <p>Controls whether the Deep Research agent includes summaries of its thinking process. Use
   * AUTO to let the agent decide, or NONE to exclude summaries.
   */
  @JsonProperty("thinking_summaries")
  public abstract Optional<ThinkingSummaries> thinkingSummaries();

  /** Builder for DeepResearchAgentConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use {@code DeepResearchAgentConfig.builder()} for instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_DeepResearchAgentConfig.Builder();
    }

    /**
     * Setter for thinking_summaries.
     *
     * @param thinkingSummaries Whether to include thought summaries in response
     */
    @JsonProperty("thinking_summaries")
    public abstract Builder thinkingSummaries(ThinkingSummaries thinkingSummaries);

    /** Internal setter for thinking_summaries with Optional. */
    @ExcludeFromGeneratedCoverageReport
    abstract Builder thinkingSummaries(Optional<ThinkingSummaries> thinkingSummaries);

    /** Clears the value of thinking_summaries field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearThinkingSummaries() {
      return thinkingSummaries(Optional.empty());
    }

    /** Builds the DeepResearchAgentConfig instance. */
    public abstract DeepResearchAgentConfig build();
  }

  /** Deserializes a DeepResearchAgentConfig from a JSON string. */
  @ExcludeFromGeneratedCoverageReport
  public static DeepResearchAgentConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, DeepResearchAgentConfig.class);
  }
}
