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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Configuration for allowed tools in an MCP server.
 *
 * <p>This type matches the Python SDK's AllowedTools:
 *
 * <pre>
 * class AllowedTools(BaseModel):
 *     mode: Optional[ToolChoiceType] = None  # Literal["auto", "any", "none", "validated"]
 *     tools: Optional[List[str]] = None
 * </pre>
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * AllowedTools allowedTools = AllowedTools.builder()
 *     .mode("auto")
 *     .tools("get_weather", "get_forecast")
 *     .build();
 * }</pre>
 */
@AutoValue
@JsonDeserialize(builder = AllowedTools.Builder.class)
public abstract class AllowedTools extends JsonSerializable {

  /**
   * The mode of the tool choice.
   *
   * <p>Valid values: "auto", "any", "none", "validated"
   */
  @JsonProperty("mode")
  public abstract Optional<String> mode();

  /** The names of the allowed tools. */
  @JsonProperty("tools")
  public abstract Optional<List<String>> tools();

  /** Instantiates a builder for AllowedTools. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_AllowedTools.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for AllowedTools. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use {@code AllowedTools.builder()} for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_AllowedTools.Builder();
    }

    /**
     * Setter for mode.
     *
     * <p>mode: The mode of the tool choice. Valid values: "auto", "any", "none", "validated"
     */
    @JsonProperty("mode")
    public abstract Builder mode(String mode);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder mode(Optional<String> mode);

    /** Clears the value of mode field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMode() {
      return mode(Optional.empty());
    }

    /**
     * Setter for tools.
     *
     * <p>tools: The names of the allowed tools.
     */
    @JsonProperty("tools")
    public abstract Builder tools(List<String> tools);

    /**
     * Setter for tools (varargs convenience method).
     *
     * <p>tools: The names of the allowed tools.
     */
    @CanIgnoreReturnValue
    public Builder tools(String... tools) {
      return tools(Arrays.asList(tools));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder tools(Optional<List<String>> tools);

    /** Clears the value of tools field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTools() {
      return tools(Optional.empty());
    }

    public abstract AllowedTools build();
  }

  /** Deserializes a JSON string to an AllowedTools object. */
  @ExcludeFromGeneratedCoverageReport
  public static AllowedTools fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, AllowedTools.class);
  }

  /** Convenience factory method. */
  @ExcludeFromGeneratedCoverageReport
  public static AllowedTools of(String mode, List<String> tools) {
    return builder().mode(mode).tools(tools).build();
  }

  /** Convenience factory method (varargs). */
  @ExcludeFromGeneratedCoverageReport
  public static AllowedTools of(String mode, String... tools) {
    return builder().mode(mode).tools(Arrays.asList(tools)).build();
  }
}
