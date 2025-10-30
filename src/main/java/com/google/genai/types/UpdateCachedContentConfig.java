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
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

/** Optional parameters for caches.update method. */
@AutoValue
@JsonDeserialize(builder = UpdateCachedContentConfig.Builder.class)
public abstract class UpdateCachedContentConfig extends JsonSerializable {
  /** Used to override HTTP request options. */
  @JsonProperty("httpOptions")
  public abstract Optional<HttpOptions> httpOptions();

  /**
   * The TTL for this resource. The expiration time is computed: now + TTL. It is a duration string,
   * with up to nine fractional digits, terminated by 's'. Example: "3.5s".
   */
  @JsonProperty("ttl")
  public abstract Optional<Duration> ttl();

  /**
   * Timestamp of when this resource is considered expired. Uses RFC 3339 format, Example:
   * 2014-10-02T15:01:23Z.
   */
  @JsonProperty("expireTime")
  public abstract Optional<Instant> expireTime();

  /** Instantiates a builder for UpdateCachedContentConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_UpdateCachedContentConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for UpdateCachedContentConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `UpdateCachedContentConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_UpdateCachedContentConfig.Builder();
    }

    /**
     * Setter for httpOptions.
     *
     * <p>httpOptions: Used to override HTTP request options.
     */
    @JsonProperty("httpOptions")
    public abstract Builder httpOptions(HttpOptions httpOptions);

    /**
     * Setter for httpOptions builder.
     *
     * <p>httpOptions: Used to override HTTP request options.
     */
    public Builder httpOptions(HttpOptions.Builder httpOptionsBuilder) {
      return httpOptions(httpOptionsBuilder.build());
    }

    /**
     * Setter for ttl.
     *
     * <p>ttl: The TTL for this resource. The expiration time is computed: now + TTL. It is a
     * duration string, with up to nine fractional digits, terminated by 's'. Example: "3.5s".
     */
    @JsonProperty("ttl")
    public abstract Builder ttl(Duration ttl);

    /**
     * Setter for expireTime.
     *
     * <p>expireTime: Timestamp of when this resource is considered expired. Uses RFC 3339 format,
     * Example: 2014-10-02T15:01:23Z.
     */
    @JsonProperty("expireTime")
    public abstract Builder expireTime(Instant expireTime);

    public abstract UpdateCachedContentConfig build();
  }

  /** Deserializes a JSON string to a UpdateCachedContentConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static UpdateCachedContentConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, UpdateCachedContentConfig.class);
  }
}
