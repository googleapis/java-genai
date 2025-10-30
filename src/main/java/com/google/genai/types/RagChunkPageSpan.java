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

/**
 * Represents where the chunk starts and ends in the document. This data type is not supported in
 * Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = RagChunkPageSpan.Builder.class)
public abstract class RagChunkPageSpan extends JsonSerializable {
  /** Page where chunk starts in the document. Inclusive. 1-indexed. */
  @JsonProperty("firstPage")
  public abstract Optional<Integer> firstPage();

  /** Page where chunk ends in the document. Inclusive. 1-indexed. */
  @JsonProperty("lastPage")
  public abstract Optional<Integer> lastPage();

  /** Instantiates a builder for RagChunkPageSpan. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_RagChunkPageSpan.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for RagChunkPageSpan. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `RagChunkPageSpan.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_RagChunkPageSpan.Builder();
    }

    /**
     * Setter for firstPage.
     *
     * <p>firstPage: Page where chunk starts in the document. Inclusive. 1-indexed.
     */
    @JsonProperty("firstPage")
    public abstract Builder firstPage(Integer firstPage);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder firstPage(Optional<Integer> firstPage);

    /** Clears the value of firstPage field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearFirstPage() {
      return firstPage(Optional.empty());
    }

    /**
     * Setter for lastPage.
     *
     * <p>lastPage: Page where chunk ends in the document. Inclusive. 1-indexed.
     */
    @JsonProperty("lastPage")
    public abstract Builder lastPage(Integer lastPage);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder lastPage(Optional<Integer> lastPage);

    /** Clears the value of lastPage field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearLastPage() {
      return lastPage(Optional.empty());
    }

    public abstract RagChunkPageSpan build();
  }

  /** Deserializes a JSON string to a RagChunkPageSpan object. */
  @ExcludeFromGeneratedCoverageReport
  public static RagChunkPageSpan fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, RagChunkPageSpan.class);
  }
}
