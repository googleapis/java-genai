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
import com.google.genai.types.interactions.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Text content for the Interactions API.
 *
 * <p>Represents textual information in interactions, either from user input or model output. Text
 * content can include optional annotations for citations and source attribution.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * // Simple text content
 * TextContent text = TextContent.of("Hello, how can I help you?");
 *
 * // Text with annotations
 * TextContent textWithAnnotations = TextContent.builder()
 *     .text("According to recent research...")
 *     .annotations(Annotation.of(0, 25, "https://example.com/research"))
 *     .build();
 * }</pre>
 *
 * <p>The Interactions API is available in both Vertex AI and Gemini API.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = TextContent.Builder.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("text")
public abstract class TextContent extends JsonSerializable
    implements Content, ThoughtSummaryContent {

  /** Instantiates a builder for TextContent. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_TextContent.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Returns the text content. */
  @JsonProperty("text")
  public abstract Optional<String> text();

  /** Returns citation information for model-generated content. */
  @JsonProperty("annotations")
  public abstract Optional<List<Annotation>> annotations();

  /** Builder for TextContent. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use {@code TextContent.builder()} for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_TextContent.Builder();
    }

    /**
     * Setter for text.
     *
     * <p>text: The text content.
     */
    @JsonProperty("text")
    public abstract Builder text(String text);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder text(Optional<String> text);

    /** Clears the value of text field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearText() {
      return text(Optional.empty());
    }

    /**
     * Setter for annotations.
     *
     * <p>annotations: Citation information for model-generated content.
     */
    @JsonProperty("annotations")
    public abstract Builder annotations(List<Annotation> annotations);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder annotations(Optional<List<Annotation>> annotations);

    /** Clears the value of annotations field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAnnotations() {
      return annotations(Optional.empty());
    }

    /**
     * Setter for annotations using varargs.
     *
     * <p>annotations: Citation information for model-generated content.
     */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder annotations(Annotation... annotations) {
      return annotations(Arrays.asList(annotations));
    }

    public abstract TextContent build();
  }

  /** Deserializes a JSON string to a TextContent object. */
  @ExcludeFromGeneratedCoverageReport
  public static TextContent fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, TextContent.class);
  }

  /** Convenience factory method. */
  @ExcludeFromGeneratedCoverageReport
  public static TextContent of(String text) {
    return builder().text(text).build();
  }
}
