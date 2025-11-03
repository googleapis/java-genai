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
import java.time.Instant;
import java.util.Optional;

/**
 * Represents a time interval, encoded as a Timestamp start (inclusive) and a Timestamp end
 * (exclusive). The start must be less than or equal to the end. When the start equals the end, the
 * interval is empty (matches no time). When both start and end are unspecified, the interval
 * matches any time.
 */
@AutoValue
@JsonDeserialize(builder = Interval.Builder.class)
public abstract class Interval extends JsonSerializable {
  /**
   * Optional. Exclusive end of the interval. If specified, a Timestamp matching this interval will
   * have to be before the end.
   */
  @JsonProperty("endTime")
  public abstract Optional<Instant> endTime();

  /**
   * Optional. Inclusive start of the interval. If specified, a Timestamp matching this interval
   * will have to be the same or after the start.
   */
  @JsonProperty("startTime")
  public abstract Optional<Instant> startTime();

  /** Instantiates a builder for Interval. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_Interval.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for Interval. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `Interval.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_Interval.Builder();
    }

    /**
     * Setter for endTime.
     *
     * <p>endTime: Optional. Exclusive end of the interval. If specified, a Timestamp matching this
     * interval will have to be before the end.
     */
    @JsonProperty("endTime")
    public abstract Builder endTime(Instant endTime);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder endTime(Optional<Instant> endTime);

    /** Clears the value of endTime field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEndTime() {
      return endTime(Optional.empty());
    }

    /**
     * Setter for startTime.
     *
     * <p>startTime: Optional. Inclusive start of the interval. If specified, a Timestamp matching
     * this interval will have to be the same or after the start.
     */
    @JsonProperty("startTime")
    public abstract Builder startTime(Instant startTime);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder startTime(Optional<Instant> startTime);

    /** Clears the value of startTime field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearStartTime() {
      return startTime(Optional.empty());
    }

    public abstract Interval build();
  }

  /** Deserializes a JSON string to a Interval object. */
  @ExcludeFromGeneratedCoverageReport
  public static Interval fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Interval.class);
  }
}
