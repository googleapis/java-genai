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
import java.util.Map;
import java.util.Optional;

/**
 * ExaAiSearch tool type. A tool that uses the Exa.ai search engine for grounding. This data type is
 * not supported in Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = ToolExaAiSearch.Builder.class)
public abstract class ToolExaAiSearch extends JsonSerializable {
  /** Required. The API key for ExaAiSearch. */
  @JsonProperty("apiKey")
  public abstract Optional<String> apiKey();

  /** Optional. This field can be used to pass any parameter from the Exa.ai Search API. */
  @JsonProperty("customConfigs")
  public abstract Optional<Map<String, Object>> customConfigs();

  /** Instantiates a builder for ToolExaAiSearch. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ToolExaAiSearch.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ToolExaAiSearch. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ToolExaAiSearch.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ToolExaAiSearch.Builder();
    }

    /**
     * Setter for apiKey.
     *
     * <p>apiKey: Required. The API key for ExaAiSearch.
     */
    @JsonProperty("apiKey")
    public abstract Builder apiKey(String apiKey);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder apiKey(Optional<String> apiKey);

    /** Clears the value of apiKey field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearApiKey() {
      return apiKey(Optional.empty());
    }

    /**
     * Setter for customConfigs.
     *
     * <p>customConfigs: Optional. This field can be used to pass any parameter from the Exa.ai
     * Search API.
     */
    @JsonProperty("customConfigs")
    public abstract Builder customConfigs(Map<String, Object> customConfigs);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder customConfigs(Optional<Map<String, Object>> customConfigs);

    /** Clears the value of customConfigs field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCustomConfigs() {
      return customConfigs(Optional.empty());
    }

    public abstract ToolExaAiSearch build();
  }

  /** Deserializes a JSON string to a ToolExaAiSearch object. */
  @ExcludeFromGeneratedCoverageReport
  public static ToolExaAiSearch fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ToolExaAiSearch.class);
  }
}
