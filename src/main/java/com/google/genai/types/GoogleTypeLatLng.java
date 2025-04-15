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
import com.google.genai.JsonSerializable;
import java.util.Optional;

/**
 * An object that represents a latitude/longitude pair. This is expressed as a pair of doubles to
 * represent degrees latitude and degrees longitude. Unless specified otherwise, this object must
 * conform to the WGS84 standard. Values must be within normalized ranges.
 */
@AutoValue
@JsonDeserialize(builder = GoogleTypeLatLng.Builder.class)
public abstract class GoogleTypeLatLng extends JsonSerializable {
  /** The latitude in degrees. It must be in the range [-90.0, +90.0]. */
  @JsonProperty("latitude")
  public abstract Optional<Double> latitude();

  /** The longitude in degrees. It must be in the range [-180.0, +180.0]. */
  @JsonProperty("longitude")
  public abstract Optional<Double> longitude();

  /** Instantiates a builder for GoogleTypeLatLng. */
  public static Builder builder() {
    return new AutoValue_GoogleTypeLatLng.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GoogleTypeLatLng. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `GoogleTypeLatLng.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GoogleTypeLatLng.Builder();
    }

    @JsonProperty("latitude")
    public abstract Builder latitude(Double latitude);

    @JsonProperty("longitude")
    public abstract Builder longitude(Double longitude);

    public abstract GoogleTypeLatLng build();
  }

  /** Deserializes a JSON string to a GoogleTypeLatLng object. */
  public static GoogleTypeLatLng fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GoogleTypeLatLng.class);
  }
}
