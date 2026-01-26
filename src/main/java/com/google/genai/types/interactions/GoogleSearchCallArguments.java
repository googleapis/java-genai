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
import java.util.List;
import java.util.Optional;

/**
 * Arguments for Google Search tool calls in the Interactions API.
 *
 * <p>Specifies the search queries to be executed when the model invokes the Google Search tool.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * GoogleSearchCallArguments args = GoogleSearchCallArguments.builder()
 *     .queries(List.of("latest AI research", "machine learning trends"))
 *     .build();
 * }</pre>
 *
 * <p>The Interactions API is available in both Vertex AI and Gemini API.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = GoogleSearchCallArguments.Builder.class)
public abstract class GoogleSearchCallArguments extends JsonSerializable {

  @JsonProperty("queries")
  public abstract Optional<List<String>> queries();

  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GoogleSearchCallArguments.Builder();
  }

  public abstract Builder toBuilder();

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GoogleSearchCallArguments.Builder();
    }

    @JsonProperty("queries")
    public abstract Builder queries(List<String> queries);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder queries(Optional<List<String>> queries);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearQueries() {
      return queries(Optional.empty());
    }

    public abstract GoogleSearchCallArguments build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static GoogleSearchCallArguments fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GoogleSearchCallArguments.class);
  }

  @ExcludeFromGeneratedCoverageReport
  public static GoogleSearchCallArguments of(List<String> queries) {
    return builder().queries(queries).build();
  }
}
