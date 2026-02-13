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
import com.google.genai.types.interactions.content.Content;
import com.google.genai.types.interactions.content.TextContent;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Represents a conversation turn in the Interactions API.
 *
 * <p>A turn contains the content of a single message in a conversation along with the role (either
 * 'user' or 'model') that produced it. Turns are used to build conversation history and maintain
 * context across multiple interactions.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * Turn userTurn = Turn.builder()
 *     .role("user")
 *     .content(TextContent.of("What is the weather today?"))
 *     .build();
 *
 * Turn modelTurn = Turn.builder()
 *     .role("model")
 *     .content(TextContent.of("The weather is sunny and warm."))
 *     .build();
 * }</pre>
 *
 * <p>The Interactions API is available in both Vertex AI and Gemini API.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = Turn.Builder.class)
public abstract class Turn extends JsonSerializable {
  /** The content of the turn as a list of Content objects. */
  @JsonProperty("content")
  public abstract Optional<List<Content>> content();

  /** Optional. The role in the conversation ('user' or 'model'). */
  @JsonProperty("role")
  public abstract Optional<String> role();

  /** Instantiates a builder for Turn. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_Turn.Builder();
  }

  /**
   * Creates a user turn with simple text content.
   *
   * <p>Convenience factory method for the common case of a user message with text.
   *
   * <p>Example usage:
   *
   * <pre>{@code
   * Input conversation = Input.fromTurns(
   *     Turn.user("Hello! My name is Alice."),
   *     Turn.model("Hello Alice! Nice to meet you."),
   *     Turn.user("What's my name?")
   * );
   * }</pre>
   *
   * @param text The user's message text
   * @return A Turn with role="user" and the text as content
   */
  public static Turn user(String text) {
    return builder()
        .role("user")
        .content(TextContent.of(text))
        .build();
  }

  /**
   * Creates a model turn with simple text content.
   *
   * <p>Convenience factory method for the common case of a model response with text.
   *
   * <p>Example usage:
   *
   * <pre>{@code
   * Input conversation = Input.fromTurns(
   *     Turn.user("What's the capital of France?"),
   *     Turn.model("The capital of France is Paris."),
   *     Turn.user("What about Germany?")
   * );
   * }</pre>
   *
   * @param text The model's response text
   * @return A Turn with role="model" and the text as content
   */
  public static Turn model(String text) {
    return builder()
        .role("model")
        .content(TextContent.of(text))
        .build();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for Turn. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `Turn.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_Turn.Builder();
    }

    /**
     * Setter for content.
     *
     * <p>content: The content of the turn as a list of Content objects.
     */
    @JsonProperty("content")
    public abstract Builder content(List<Content> content);

    /**
     * Setter for content (varargs convenience method).
     *
     * <p>content: The content of the turn.
     */
    @CanIgnoreReturnValue
    public Builder content(Content... content) {
      return content(Arrays.asList(content));
    }

    /** Internal setter for content with Optional. */
    @ExcludeFromGeneratedCoverageReport
    abstract Builder content(Optional<List<Content>> content);

    /**
     * Clear method for content.
     *
     * <p>Removes the content field.
     */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearContent() {
      return content(Optional.empty());
    }

    /**
     * Setter for role.
     *
     * <p>role: The role in the conversation ('user' or 'model').
     */
    @JsonProperty("role")
    public abstract Builder role(String role);

    /** Internal setter for role with Optional. */
    @ExcludeFromGeneratedCoverageReport
    abstract Builder role(Optional<String> role);

    /**
     * Clear method for role.
     *
     * <p>Removes the role field.
     */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRole() {
      return role(Optional.empty());
    }

    /** Builds the Turn instance. */
    public abstract Turn build();
  }

  /** Deserializes an Turn from a JSON string. */
  @ExcludeFromGeneratedCoverageReport
  public static Turn fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Turn.class);
  }
}
