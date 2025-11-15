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
import java.util.Optional;

/** A segment of the content. */
@AutoValue
@JsonDeserialize(builder = Segment.Builder.class)
public abstract class Segment extends JsonSerializable {
  /**
   * Output only. The end index of the segment in the `Part`, measured in bytes. This marks the end
   * of the segment and is exclusive, meaning the segment includes content up to, but not including,
   * the byte at this index.
   */
  @JsonProperty("endIndex")
  public abstract Optional<Integer> endIndex();

  /**
   * Output only. The index of the `Part` object that this segment belongs to. This is useful for
   * associating the segment with a specific part of the content.
   */
  @JsonProperty("partIndex")
  public abstract Optional<Integer> partIndex();

  /**
   * Output only. The start index of the segment in the `Part`, measured in bytes. This marks the
   * beginning of the segment and is inclusive, meaning the byte at this index is the first byte of
   * the segment.
   */
  @JsonProperty("startIndex")
  public abstract Optional<Integer> startIndex();

  /** Output only. The text of the segment. */
  @JsonProperty("text")
  public abstract Optional<String> text();

  /** Instantiates a builder for Segment. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_Segment.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for Segment. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `Segment.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_Segment.Builder();
    }

    /**
     * Setter for endIndex.
     *
     * <p>endIndex: Output only. The end index of the segment in the `Part`, measured in bytes. This
     * marks the end of the segment and is exclusive, meaning the segment includes content up to,
     * but not including, the byte at this index.
     */
    @JsonProperty("endIndex")
    public abstract Builder endIndex(Integer endIndex);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder endIndex(Optional<Integer> endIndex);

    /** Clears the value of endIndex field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEndIndex() {
      return endIndex(Optional.empty());
    }

    /**
     * Setter for partIndex.
     *
     * <p>partIndex: Output only. The index of the `Part` object that this segment belongs to. This
     * is useful for associating the segment with a specific part of the content.
     */
    @JsonProperty("partIndex")
    public abstract Builder partIndex(Integer partIndex);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder partIndex(Optional<Integer> partIndex);

    /** Clears the value of partIndex field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPartIndex() {
      return partIndex(Optional.empty());
    }

    /**
     * Setter for startIndex.
     *
     * <p>startIndex: Output only. The start index of the segment in the `Part`, measured in bytes.
     * This marks the beginning of the segment and is inclusive, meaning the byte at this index is
     * the first byte of the segment.
     */
    @JsonProperty("startIndex")
    public abstract Builder startIndex(Integer startIndex);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder startIndex(Optional<Integer> startIndex);

    /** Clears the value of startIndex field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearStartIndex() {
      return startIndex(Optional.empty());
    }

    /**
     * Setter for text.
     *
     * <p>text: Output only. The text of the segment.
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

    public abstract Segment build();
  }

  /** Deserializes a JSON string to a Segment object. */
  @ExcludeFromGeneratedCoverageReport
  public static Segment fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Segment.class);
  }
}
