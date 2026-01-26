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

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** Represents a conversation turn with role and content. */
@AutoValue
@JsonDeserialize(builder = Turn.Builder.class)
public abstract class Turn extends JsonSerializable {
  /** The content of the turn. Can be a string or list of Content objects. */
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

    /**
     * Setter for content builder (varargs convenience method).
     *
     * <p>content: The content of the turn.
     */
    @CanIgnoreReturnValue
    public Builder content(Content.Builder... contentBuilders) {
      return content(
          Arrays.asList(contentBuilders).stream()
              .map(Content.Builder::build)
              .collect(toImmutableList()));
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

  /** Deserializes a Turn from a JSON string. */
  @ExcludeFromGeneratedCoverageReport
  public static Turn fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Turn.class);
  }
}
