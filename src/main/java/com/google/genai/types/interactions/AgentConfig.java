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

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Base interface for agent configuration types using a type discriminator.
 *
 * <p>AgentConfig is a polymorphic type that configures agent behavior. Alternative to {@code
 * generation_config}. Only applicable when {@code agent} is set.
 *
 * <p>Concrete subtypes:
 *
 * <ul>
 *   <li>{@link DynamicAgentConfig} - Configuration for dynamic agents
 *   <li>{@link DeepResearchAgentConfig} - Configuration for Deep Research agent
 * </ul>
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * // Dynamic agent configuration
 * AgentConfig config = DynamicAgentConfig.create();
 *
 * // Deep Research agent with thinking summaries
 * AgentConfig config = DeepResearchAgentConfig.builder()
 *     .thinkingSummaries(new ThinkingSummaries(ThinkingSummaries.Known.AUTO))
 *     .build();
 *
 * // Use in CreateInteractionConfig
 * CreateInteractionConfig interactionConfig = CreateInteractionConfig.builder()
 *     .agent("agent-name")
 *     .input("Your input")
 *     .agentConfig(config)
 *     .build();
 * }</pre>
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = DynamicAgentConfig.class, name = "dynamic"),
  @JsonSubTypes.Type(value = DeepResearchAgentConfig.class, name = "deep-research")
})
public interface AgentConfig {}
