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

/** Spec for rouge metric. */
@AutoValue
@JsonDeserialize(builder = RougeSpec.Builder.class)
public abstract class RougeSpec extends JsonSerializable {
  /** Optional. Supported rouge types are rougen[1-9], rougeL, and rougeLsum. */
  @JsonProperty("rougeType")
  public abstract Optional<String> rougeType();

  /** Optional. Whether to split summaries while using rougeLsum. */
  @JsonProperty("splitSummaries")
  public abstract Optional<Boolean> splitSummaries();

  /** Optional. Whether to use stemmer to compute rouge score. */
  @JsonProperty("useStemmer")
  public abstract Optional<Boolean> useStemmer();

  /** Instantiates a builder for RougeSpec. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_RougeSpec.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for RougeSpec. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `RougeSpec.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_RougeSpec.Builder();
    }

    /**
     * Setter for rougeType.
     *
     * <p>rougeType: Optional. Supported rouge types are rougen[1-9], rougeL, and rougeLsum.
     */
    @JsonProperty("rougeType")
    public abstract Builder rougeType(String rougeType);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder rougeType(Optional<String> rougeType);

    /** Clears the value of rougeType field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRougeType() {
      return rougeType(Optional.empty());
    }

    /**
     * Setter for splitSummaries.
     *
     * <p>splitSummaries: Optional. Whether to split summaries while using rougeLsum.
     */
    @JsonProperty("splitSummaries")
    public abstract Builder splitSummaries(boolean splitSummaries);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder splitSummaries(Optional<Boolean> splitSummaries);

    /** Clears the value of splitSummaries field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSplitSummaries() {
      return splitSummaries(Optional.empty());
    }

    /**
     * Setter for useStemmer.
     *
     * <p>useStemmer: Optional. Whether to use stemmer to compute rouge score.
     */
    @JsonProperty("useStemmer")
    public abstract Builder useStemmer(boolean useStemmer);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder useStemmer(Optional<Boolean> useStemmer);

    /** Clears the value of useStemmer field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearUseStemmer() {
      return useStemmer(Optional.empty());
    }

    public abstract RougeSpec build();
  }

  /** Deserializes a JSON string to a RougeSpec object. */
  @ExcludeFromGeneratedCoverageReport
  public static RougeSpec fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, RougeSpec.class);
  }
}
