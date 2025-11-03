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

/** Retrieval config. */
@AutoValue
@JsonDeserialize(builder = RetrievalConfig.Builder.class)
public abstract class RetrievalConfig extends JsonSerializable {
  /** Optional. The location of the user. */
  @JsonProperty("latLng")
  public abstract Optional<LatLng> latLng();

  /** The language code of the user. */
  @JsonProperty("languageCode")
  public abstract Optional<String> languageCode();

  /** Instantiates a builder for RetrievalConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_RetrievalConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for RetrievalConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `RetrievalConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_RetrievalConfig.Builder();
    }

    /**
     * Setter for latLng.
     *
     * <p>latLng: Optional. The location of the user.
     */
    @JsonProperty("latLng")
    public abstract Builder latLng(LatLng latLng);

    /**
     * Setter for latLng builder.
     *
     * <p>latLng: Optional. The location of the user.
     */
    @CanIgnoreReturnValue
    public Builder latLng(LatLng.Builder latLngBuilder) {
      return latLng(latLngBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder latLng(Optional<LatLng> latLng);

    /** Clears the value of latLng field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearLatLng() {
      return latLng(Optional.empty());
    }

    /**
     * Setter for languageCode.
     *
     * <p>languageCode: The language code of the user.
     */
    @JsonProperty("languageCode")
    public abstract Builder languageCode(String languageCode);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder languageCode(Optional<String> languageCode);

    /** Clears the value of languageCode field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearLanguageCode() {
      return languageCode(Optional.empty());
    }

    public abstract RetrievalConfig build();
  }

  /** Deserializes a JSON string to a RetrievalConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static RetrievalConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, RetrievalConfig.class);
  }
}
