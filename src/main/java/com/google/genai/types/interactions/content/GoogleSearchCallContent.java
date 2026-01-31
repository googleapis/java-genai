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

package com.google.genai.types.interactions.content;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import com.google.genai.types.ExcludeFromGeneratedCoverageReport;
import com.google.genai.types.interactions.GoogleSearchCallArguments;
import java.util.List;
import java.util.Optional;

/**
 * Google Search call content for the Interactions API.
 *
 * <p>Represents a request from the model to perform a Google Search. This content type appears in
 * interaction outputs when the model wants to search the web for information.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * GoogleSearchCallContent searchCall = GoogleSearchCallContent.builder()
 *     .id("call_123")
 *     .arguments(GoogleSearchCallArguments.builder()
 *         .query("latest AI developments")
 *         .build())
 *     .build();
 * }</pre>
 *
 * <p>The Interactions API is available in both Vertex AI and Gemini API.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = GoogleSearchCallContent.Builder.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("google_search_call")
public abstract class GoogleSearchCallContent extends JsonSerializable implements Content {

  @JsonProperty("arguments")
  public abstract Optional<GoogleSearchCallArguments> arguments();

  @JsonProperty("id")
  public abstract Optional<String> id();

  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GoogleSearchCallContent.Builder();
  }

  public abstract Builder toBuilder();

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GoogleSearchCallContent.Builder();
    }

    @JsonProperty("arguments")
    public abstract Builder arguments(GoogleSearchCallArguments arguments);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder arguments(Optional<GoogleSearchCallArguments> arguments);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearArguments() {
      return arguments(Optional.empty());
    }

    @JsonProperty("id")
    public abstract Builder id(String id);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder id(Optional<String> id);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearId() {
      return id(Optional.empty());
    }

    public abstract GoogleSearchCallContent build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static GoogleSearchCallContent fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GoogleSearchCallContent.class);
  }

  @ExcludeFromGeneratedCoverageReport
  public static GoogleSearchCallContent of(List<String> queries, String id) {
    return builder().arguments(GoogleSearchCallArguments.of(queries)).id(id).build();
  }
}
