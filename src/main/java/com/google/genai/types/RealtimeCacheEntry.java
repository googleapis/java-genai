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

/** Entry for caching realtime audio chunks. */
@AutoValue
@JsonDeserialize(builder = RealtimeCacheEntry.Builder.class)
public abstract class RealtimeCacheEntry extends JsonSerializable {
  /** The role of the participant ('user' or 'model'). */
  @JsonProperty("role")
  public abstract Optional<String> role();

  /** The audio chunk data. */
  @JsonProperty("data")
  public abstract Optional<Blob> data();

  /** Timestamp in seconds when the chunk was received or generated. */
  @JsonProperty("timestamp")
  public abstract Optional<Float> timestamp();

  /** Instantiates a builder for RealtimeCacheEntry. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_RealtimeCacheEntry.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for RealtimeCacheEntry. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `RealtimeCacheEntry.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_RealtimeCacheEntry.Builder();
    }

    /**
     * Setter for role.
     *
     * <p>role: The role of the participant ('user' or 'model').
     */
    @JsonProperty("role")
    public abstract Builder role(String role);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder role(Optional<String> role);

    /** Clears the value of role field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRole() {
      return role(Optional.empty());
    }

    /**
     * Setter for data.
     *
     * <p>data: The audio chunk data.
     */
    @JsonProperty("data")
    public abstract Builder data(Blob data);

    /**
     * Setter for data builder.
     *
     * <p>data: The audio chunk data.
     */
    @CanIgnoreReturnValue
    public Builder data(Blob.Builder dataBuilder) {
      return data(dataBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder data(Optional<Blob> data);

    /** Clears the value of data field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearData() {
      return data(Optional.empty());
    }

    /**
     * Setter for timestamp.
     *
     * <p>timestamp: Timestamp in seconds when the chunk was received or generated.
     */
    @JsonProperty("timestamp")
    public abstract Builder timestamp(Float timestamp);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder timestamp(Optional<Float> timestamp);

    /** Clears the value of timestamp field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTimestamp() {
      return timestamp(Optional.empty());
    }

    public abstract RealtimeCacheEntry build();
  }

  /** Deserializes a JSON string to a RealtimeCacheEntry object. */
  @ExcludeFromGeneratedCoverageReport
  public static RealtimeCacheEntry fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, RealtimeCacheEntry.class);
  }
}
