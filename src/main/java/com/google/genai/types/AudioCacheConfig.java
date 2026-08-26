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

/** Configuration for audio caching behavior. */
@AutoValue
@JsonDeserialize(builder = AudioCacheConfig.Builder.class)
public abstract class AudioCacheConfig extends JsonSerializable {
  /** Maximum cache size in bytes before auto-flush. */
  @JsonProperty("maxCacheSizeBytes")
  public abstract Optional<Integer> maxCacheSizeBytes();

  /** Maximum duration to keep data in cache. */
  @JsonProperty("maxCacheDurationSeconds")
  public abstract Optional<Float> maxCacheDurationSeconds();

  /** Number of chunks that triggers auto-flush. */
  @JsonProperty("autoFlushThreshold")
  public abstract Optional<Integer> autoFlushThreshold();

  /** Instantiates a builder for AudioCacheConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_AudioCacheConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for AudioCacheConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `AudioCacheConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_AudioCacheConfig.Builder();
    }

    /**
     * Setter for maxCacheSizeBytes.
     *
     * <p>maxCacheSizeBytes: Maximum cache size in bytes before auto-flush.
     */
    @JsonProperty("maxCacheSizeBytes")
    public abstract Builder maxCacheSizeBytes(Integer maxCacheSizeBytes);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder maxCacheSizeBytes(Optional<Integer> maxCacheSizeBytes);

    /** Clears the value of maxCacheSizeBytes field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMaxCacheSizeBytes() {
      return maxCacheSizeBytes(Optional.empty());
    }

    /**
     * Setter for maxCacheDurationSeconds.
     *
     * <p>maxCacheDurationSeconds: Maximum duration to keep data in cache.
     */
    @JsonProperty("maxCacheDurationSeconds")
    public abstract Builder maxCacheDurationSeconds(Float maxCacheDurationSeconds);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder maxCacheDurationSeconds(Optional<Float> maxCacheDurationSeconds);

    /** Clears the value of maxCacheDurationSeconds field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMaxCacheDurationSeconds() {
      return maxCacheDurationSeconds(Optional.empty());
    }

    /**
     * Setter for autoFlushThreshold.
     *
     * <p>autoFlushThreshold: Number of chunks that triggers auto-flush.
     */
    @JsonProperty("autoFlushThreshold")
    public abstract Builder autoFlushThreshold(Integer autoFlushThreshold);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder autoFlushThreshold(Optional<Integer> autoFlushThreshold);

    /** Clears the value of autoFlushThreshold field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAutoFlushThreshold() {
      return autoFlushThreshold(Optional.empty());
    }

    public abstract AudioCacheConfig build();
  }

  /** Deserializes a JSON string to a AudioCacheConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static AudioCacheConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, AudioCacheConfig.class);
  }
}
