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
 * Defines the types of Google Maps grounding that can be enabled and their configurations. This
 * data type is not supported in Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = GoogleMapsGroundingTypes.Builder.class)
public abstract class GoogleMapsGroundingTypes extends JsonSerializable {
  /**
   * Optional. Enables grounding with Google Maps Places. This is the default grounding type when no
   * `GroundingTypes` are specified.
   */
  @JsonProperty("places")
  public abstract Optional<GoogleMapsPlaces> places();

  /**
   * Optional. Enables grounding with Google Maps Routing APIs (ComputeRoutes and SearchAlongRoute).
   */
  @JsonProperty("routing")
  public abstract Optional<GoogleMapsRouting> routing();

  /** Instantiates a builder for GoogleMapsGroundingTypes. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GoogleMapsGroundingTypes.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GoogleMapsGroundingTypes. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `GoogleMapsGroundingTypes.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GoogleMapsGroundingTypes.Builder();
    }

    /**
     * Setter for places.
     *
     * <p>places: Optional. Enables grounding with Google Maps Places. This is the default grounding
     * type when no `GroundingTypes` are specified.
     */
    @JsonProperty("places")
    public abstract Builder places(GoogleMapsPlaces places);

    /**
     * Setter for places builder.
     *
     * <p>places: Optional. Enables grounding with Google Maps Places. This is the default grounding
     * type when no `GroundingTypes` are specified.
     */
    @CanIgnoreReturnValue
    public Builder places(GoogleMapsPlaces.Builder placesBuilder) {
      return places(placesBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder places(Optional<GoogleMapsPlaces> places);

    /** Clears the value of places field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPlaces() {
      return places(Optional.empty());
    }

    /**
     * Setter for routing.
     *
     * <p>routing: Optional. Enables grounding with Google Maps Routing APIs (ComputeRoutes and
     * SearchAlongRoute).
     */
    @JsonProperty("routing")
    public abstract Builder routing(GoogleMapsRouting routing);

    /**
     * Setter for routing builder.
     *
     * <p>routing: Optional. Enables grounding with Google Maps Routing APIs (ComputeRoutes and
     * SearchAlongRoute).
     */
    @CanIgnoreReturnValue
    public Builder routing(GoogleMapsRouting.Builder routingBuilder) {
      return routing(routingBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder routing(Optional<GoogleMapsRouting> routing);

    /** Clears the value of routing field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRouting() {
      return routing(Optional.empty());
    }

    public abstract GoogleMapsGroundingTypes build();
  }

  /** Deserializes a JSON string to a GoogleMapsGroundingTypes object. */
  @ExcludeFromGeneratedCoverageReport
  public static GoogleMapsGroundingTypes fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GoogleMapsGroundingTypes.class);
  }
}
