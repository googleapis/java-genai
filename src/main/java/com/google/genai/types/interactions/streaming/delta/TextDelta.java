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

package com.google.genai.types.interactions.streaming.delta;

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
 * Represents an incremental text update in a streaming response.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = TextDelta.Builder.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("text")
public abstract class TextDelta extends JsonSerializable implements Delta {

  /** Instantiates a builder for TextDelta. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_TextDelta.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Returns the incremental text content. */
  @JsonProperty("text")
  public abstract Optional<String> text();

  /** Returns any annotations for the text. */
  @JsonProperty("annotations")
  public abstract Optional<List<Annotation>> annotations();

  /** Builder for TextDelta. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return new AutoValue_TextDelta.Builder();
    }

    @JsonProperty("text")
    public abstract Builder text(String text);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder text(Optional<String> text);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearText() {
      return text(Optional.empty());
    }

    @JsonProperty("annotations")
    public abstract Builder annotations(List<Annotation> annotations);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder annotations(Annotation... annotations) {
      return annotations(Arrays.asList(annotations));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder annotations(Optional<List<Annotation>> annotations);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAnnotations() {
      return annotations(Optional.empty());
    }

    public abstract TextDelta build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static TextDelta fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, TextDelta.class);
  }
}
