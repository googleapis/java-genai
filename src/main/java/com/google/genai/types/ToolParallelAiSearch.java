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
 * ParallelAiSearch tool type. A tool that uses the Parallel.ai search engine for grounding. This
 * data type is not supported in Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = ToolParallelAiSearch.Builder.class)
public abstract class ToolParallelAiSearch extends JsonSerializable {
  /**
   * Optional. The API key for ParallelAiSearch. If an API key is not provided, the system will
   * attempt to verify access by checking for an active Parallel.ai subscription through the Google
   * Cloud Marketplace. See https://docs.parallel.ai/search/search-quickstart for more details.
   */
  @JsonProperty("apiKey")
  public abstract Optional<String> apiKey();

  /**
   * Optional. Custom configs for ParallelAiSearch. This field can be used to pass any parameter
   * from the Parallel.ai Search API. See the Parallel.ai documentation for the full list of
   * available parameters and their usage: https://docs.parallel.ai/api-reference/search-beta/search
   * Currently only `source_policy`, `excerpts`, `max_results`, `mode`, `fetch_policy` can be set
   * via this field. For example: { "source_policy": { "include_domains": ["google.com",
   * "wikipedia.org"], "exclude_domains": ["example.com"] }, "fetch_policy": { "max_age_seconds":
   * 3600 } }
   */
  @JsonProperty("customConfigs")
  public abstract Optional<Map<String, Object>> customConfigs();

  /** Instantiates a builder for ToolParallelAiSearch. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ToolParallelAiSearch.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ToolParallelAiSearch. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ToolParallelAiSearch.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ToolParallelAiSearch.Builder();
    }

    /**
     * Setter for apiKey.
     *
     * <p>apiKey: Optional. The API key for ParallelAiSearch. If an API key is not provided, the
     * system will attempt to verify access by checking for an active Parallel.ai subscription
     * through the Google Cloud Marketplace. See https://docs.parallel.ai/search/search-quickstart
     * for more details.
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
     * <p>customConfigs: Optional. Custom configs for ParallelAiSearch. This field can be used to
     * pass any parameter from the Parallel.ai Search API. See the Parallel.ai documentation for the
     * full list of available parameters and their usage:
     * https://docs.parallel.ai/api-reference/search-beta/search Currently only `source_policy`,
     * `excerpts`, `max_results`, `mode`, `fetch_policy` can be set via this field. For example: {
     * "source_policy": { "include_domains": ["google.com", "wikipedia.org"], "exclude_domains":
     * ["example.com"] }, "fetch_policy": { "max_age_seconds": 3600 } }
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

    public abstract ToolParallelAiSearch build();
  }

  /** Deserializes a JSON string to a ToolParallelAiSearch object. */
  @ExcludeFromGeneratedCoverageReport
  public static ToolParallelAiSearch fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ToolParallelAiSearch.class);
  }
}
