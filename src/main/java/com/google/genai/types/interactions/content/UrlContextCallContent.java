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
import com.google.genai.types.interactions.UrlContextCallArguments;
import java.util.List;
import java.util.Optional;

/**
 * URL context call content for the Interactions API.
 *
 * <p>Represents a request from the model to retrieve and analyze content from URLs. This content
 * type appears in interaction outputs when the model wants to access web pages for context.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * UrlContextCallContent urlCall = UrlContextCallContent.builder()
 *     .id("call_123")
 *     .arguments(UrlContextCallArguments.builder()
 *         .urls(Arrays.asList("https://example.com"))
 *         .build())
 *     .build();
 * }</pre>
 *
 * <p>The Interactions API is available in both Vertex AI and Gemini API.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = UrlContextCallContent.Builder.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("url_context_call")
public abstract class UrlContextCallContent extends JsonSerializable implements Content {

  @JsonProperty("arguments")
  public abstract Optional<UrlContextCallArguments> arguments();

  @JsonProperty("id")
  public abstract Optional<String> id();

  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_UrlContextCallContent.Builder();
  }

  public abstract Builder toBuilder();

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return new AutoValue_UrlContextCallContent.Builder();
    }

    @JsonProperty("arguments")
    public abstract Builder arguments(UrlContextCallArguments arguments);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder arguments(Optional<UrlContextCallArguments> arguments);

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

    public abstract UrlContextCallContent build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static UrlContextCallContent fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, UrlContextCallContent.class);
  }

  @ExcludeFromGeneratedCoverageReport
  public static UrlContextCallContent of(List<String> urls, String id) {
    return builder().arguments(UrlContextCallArguments.of(urls)).id(id).build();
  }
}
