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

/** Represents token counting info for a single modality. */
@AutoValue
@JsonDeserialize(builder = ModalityTokenCount.Builder.class)
public abstract class ModalityTokenCount extends JsonSerializable {
  /** The modality associated with this token count. */
  @JsonProperty("modality")
  public abstract Optional<MediaModality> modality();

  /** The number of tokens counted for this modality. */
  @JsonProperty("tokenCount")
  public abstract Optional<Integer> tokenCount();

  /** Instantiates a builder for ModalityTokenCount. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ModalityTokenCount.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ModalityTokenCount. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ModalityTokenCount.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ModalityTokenCount.Builder();
    }

    /**
     * Setter for modality.
     *
     * <p>modality: The modality associated with this token count.
     */
    @JsonProperty("modality")
    public abstract Builder modality(MediaModality modality);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder modality(Optional<MediaModality> modality);

    /** Clears the value of modality field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearModality() {
      return modality(Optional.empty());
    }

    /**
     * Setter for modality given a known enum.
     *
     * <p>modality: The modality associated with this token count.
     */
    @CanIgnoreReturnValue
    public Builder modality(MediaModality.Known knownType) {
      return modality(new MediaModality(knownType));
    }

    /**
     * Setter for modality given a string.
     *
     * <p>modality: The modality associated with this token count.
     */
    @CanIgnoreReturnValue
    public Builder modality(String modality) {
      return modality(new MediaModality(modality));
    }

    /**
     * Setter for tokenCount.
     *
     * <p>tokenCount: The number of tokens counted for this modality.
     */
    @JsonProperty("tokenCount")
    public abstract Builder tokenCount(Integer tokenCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder tokenCount(Optional<Integer> tokenCount);

    /** Clears the value of tokenCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTokenCount() {
      return tokenCount(Optional.empty());
    }

    public abstract ModalityTokenCount build();
  }

  /** Deserializes a JSON string to a ModalityTokenCount object. */
  @ExcludeFromGeneratedCoverageReport
  public static ModalityTokenCount fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ModalityTokenCount.class);
  }
}
