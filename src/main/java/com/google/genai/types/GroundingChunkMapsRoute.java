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
import java.time.Duration;
import java.util.Optional;

/** Route information from Google Maps. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = GroundingChunkMapsRoute.Builder.class)
public abstract class GroundingChunkMapsRoute extends JsonSerializable {
  /** The total distance of the route, in meters. */
  @JsonProperty("distanceMeters")
  public abstract Optional<Integer> distanceMeters();

  /** The total duration of the route. */
  @JsonProperty("duration")
  public abstract Optional<Duration> duration();

  /**
   * An encoded polyline of the route. See
   * https://developers.google.com/maps/documentation/utilities/polylinealgorithm
   */
  @JsonProperty("encodedPolyline")
  public abstract Optional<String> encodedPolyline();

  /** Instantiates a builder for GroundingChunkMapsRoute. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GroundingChunkMapsRoute.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GroundingChunkMapsRoute. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `GroundingChunkMapsRoute.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GroundingChunkMapsRoute.Builder();
    }

    /**
     * Setter for distanceMeters.
     *
     * <p>distanceMeters: The total distance of the route, in meters.
     */
    @JsonProperty("distanceMeters")
    public abstract Builder distanceMeters(Integer distanceMeters);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder distanceMeters(Optional<Integer> distanceMeters);

    /** Clears the value of distanceMeters field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDistanceMeters() {
      return distanceMeters(Optional.empty());
    }

    /**
     * Setter for duration.
     *
     * <p>duration: The total duration of the route.
     */
    @JsonProperty("duration")
    public abstract Builder duration(Duration duration);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder duration(Optional<Duration> duration);

    /** Clears the value of duration field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDuration() {
      return duration(Optional.empty());
    }

    /**
     * Setter for encodedPolyline.
     *
     * <p>encodedPolyline: An encoded polyline of the route. See
     * https://developers.google.com/maps/documentation/utilities/polylinealgorithm
     */
    @JsonProperty("encodedPolyline")
    public abstract Builder encodedPolyline(String encodedPolyline);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder encodedPolyline(Optional<String> encodedPolyline);

    /** Clears the value of encodedPolyline field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEncodedPolyline() {
      return encodedPolyline(Optional.empty());
    }

    public abstract GroundingChunkMapsRoute build();
  }

  /** Deserializes a JSON string to a GroundingChunkMapsRoute object. */
  @ExcludeFromGeneratedCoverageReport
  public static GroundingChunkMapsRoute fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GroundingChunkMapsRoute.class);
  }
}
