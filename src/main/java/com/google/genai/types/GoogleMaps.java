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

/** Tool to retrieve public maps data for grounding, powered by Google. */
@AutoValue
@JsonDeserialize(builder = GoogleMaps.Builder.class)
public abstract class GoogleMaps extends JsonSerializable {
  /** Optional. If true, include the widget context token in the response. */
  @JsonProperty("enableWidget")
  public abstract Optional<Boolean> enableWidget();

  /** Instantiates a builder for GoogleMaps. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GoogleMaps.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GoogleMaps. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `GoogleMaps.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GoogleMaps.Builder();
    }

    /**
     * Setter for enableWidget.
     *
     * <p>enableWidget: Optional. If true, include the widget context token in the response.
     */
    @JsonProperty("enableWidget")
    public abstract Builder enableWidget(boolean enableWidget);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder enableWidget(Optional<Boolean> enableWidget);

    /** Clears the value of enableWidget field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEnableWidget() {
      return enableWidget(Optional.empty());
    }

    public abstract GoogleMaps build();
  }

  /** Deserializes a JSON string to a GoogleMaps object. */
  @ExcludeFromGeneratedCoverageReport
  public static GoogleMaps fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GoogleMaps.class);
  }
}
