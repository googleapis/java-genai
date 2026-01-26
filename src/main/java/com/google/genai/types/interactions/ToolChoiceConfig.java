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
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import com.google.genai.types.ExcludeFromGeneratedCoverageReport;
import java.util.Optional;

/**
 * Configuration for fine-grained tool choice behavior.
 *
 * <p>This class provides detailed control over which specific tools the model is allowed to use
 * during generation. It is used as part of a {@link ToolChoice} union type when you need more
 * granular control than the simple type-based modes (auto, any, none, validated).
 *
 * <p>The configuration allows you to specify exactly which tools are permitted through the {@link
 * AllowedTools} field, enabling scenarios where you want to restrict the model to a subset of
 * available tools rather than allowing all or none.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * // Allow only specific function tools
 * AllowedTools allowed = AllowedTools.builder()
 *     .functionNames(Arrays.asList("get_weather", "search_web"))
 *     .build();
 *
 * ToolChoiceConfig config = ToolChoiceConfig.builder()
 *     .allowedTools(allowed)
 *     .build();
 *
 * // Use with ToolChoice
 * ToolChoice toolChoice = ToolChoice.fromConfig(config);
 *
 * // Include in generation config
 * GenerationConfig genConfig = GenerationConfig.builder()
 *     .toolChoice(toolChoice)
 *     .build();
 * }</pre>
 */
@AutoValue
@JsonDeserialize(builder = ToolChoiceConfig.Builder.class)
public abstract class ToolChoiceConfig extends JsonSerializable {

  /**
   * Configuration specifying which tools are allowed.
   *
   * <p>When set, this restricts the model to only use the specified tools. If not set, the behavior
   * depends on the overall tool choice type.
   */
  @JsonProperty("allowed_tools")
  public abstract Optional<AllowedTools> allowedTools();

  /** Instantiates a builder for ToolChoiceConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ToolChoiceConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ToolChoiceConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use {@code ToolChoiceConfig.builder()} for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ToolChoiceConfig.Builder();
    }

    /**
     * Setter for allowedTools.
     *
     * <p>allowedTools: Configuration for allowed tools.
     */
    @JsonProperty("allowed_tools")
    public abstract Builder allowedTools(AllowedTools allowedTools);

    /**
     * Convenience setter for allowedTools using a builder.
     *
     * <p>allowedTools: Configuration for allowed tools.
     */
    @CanIgnoreReturnValue
    public Builder allowedTools(AllowedTools.Builder allowedToolsBuilder) {
      return allowedTools(allowedToolsBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder allowedTools(Optional<AllowedTools> allowedTools);

    /** Clears the value of allowedTools field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAllowedTools() {
      return allowedTools(Optional.empty());
    }

    public abstract ToolChoiceConfig build();
  }

  /** Deserializes a JSON string to a ToolChoiceConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static ToolChoiceConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ToolChoiceConfig.class);
  }
}
