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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** Optional parameters. */
@AutoValue
@JsonDeserialize(builder = CreateAuthTokenConfig.Builder.class)
public abstract class CreateAuthTokenConfig extends JsonSerializable {
  /** Used to override HTTP request options. */
  @JsonProperty("httpOptions")
  public abstract Optional<HttpOptions> httpOptions();

  /**
   * An optional time after which, when using the resulting token, messages in Live API sessions
   * will be rejected. (Gemini may preemptively close the session after this time.)
   *
   * <p>If not set then this defaults to 30 minutes in the future. If set, this value must be less
   * than 20 hours in the future.
   */
  @JsonProperty("expireTime")
  public abstract Optional<Instant> expireTime();

  /**
   * The time after which new Live API sessions using the token resulting from this request will be
   * rejected.
   *
   * <p>If not set this defaults to 60 seconds in the future. If set, this value must be less than
   * 20 hours in the future.
   */
  @JsonProperty("newSessionExpireTime")
  public abstract Optional<Instant> newSessionExpireTime();

  /**
   * The number of times the token can be used. If this value is zero then no limit is applied.
   * Default is 1. Resuming a Live API session does not count as a use.
   */
  @JsonProperty("uses")
  public abstract Optional<Integer> uses();

  /** Configuration specific to Live API connections created using this token. */
  @JsonProperty("liveConnectConstraints")
  public abstract Optional<LiveConnectConstraints> liveConnectConstraints();

  /** Additional fields to lock in the effective LiveConnectParameters. */
  @JsonProperty("lockAdditionalFields")
  public abstract Optional<List<String>> lockAdditionalFields();

  /** Instantiates a builder for CreateAuthTokenConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_CreateAuthTokenConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for CreateAuthTokenConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `CreateAuthTokenConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_CreateAuthTokenConfig.Builder();
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
    @CanIgnoreReturnValue
    public Builder httpOptions(HttpOptions.Builder httpOptionsBuilder) {
      return httpOptions(httpOptionsBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder httpOptions(Optional<HttpOptions> httpOptions);

    /** Clears the value of httpOptions field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearHttpOptions() {
      return httpOptions(Optional.empty());
    }

    /**
     * Setter for expireTime.
     *
     * <p>expireTime: An optional time after which, when using the resulting token, messages in Live
     * API sessions will be rejected. (Gemini may preemptively close the session after this time.)
     *
     * <p>If not set then this defaults to 30 minutes in the future. If set, this value must be less
     * than 20 hours in the future.
     */
    @JsonProperty("expireTime")
    public abstract Builder expireTime(Instant expireTime);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder expireTime(Optional<Instant> expireTime);

    /** Clears the value of expireTime field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearExpireTime() {
      return expireTime(Optional.empty());
    }

    /**
     * Setter for newSessionExpireTime.
     *
     * <p>newSessionExpireTime: The time after which new Live API sessions using the token resulting
     * from this request will be rejected.
     *
     * <p>If not set this defaults to 60 seconds in the future. If set, this value must be less than
     * 20 hours in the future.
     */
    @JsonProperty("newSessionExpireTime")
    public abstract Builder newSessionExpireTime(Instant newSessionExpireTime);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder newSessionExpireTime(Optional<Instant> newSessionExpireTime);

    /** Clears the value of newSessionExpireTime field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearNewSessionExpireTime() {
      return newSessionExpireTime(Optional.empty());
    }

    /**
     * Setter for uses.
     *
     * <p>uses: The number of times the token can be used. If this value is zero then no limit is
     * applied. Default is 1. Resuming a Live API session does not count as a use.
     */
    @JsonProperty("uses")
    public abstract Builder uses(Integer uses);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder uses(Optional<Integer> uses);

    /** Clears the value of uses field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearUses() {
      return uses(Optional.empty());
    }

    /**
     * Setter for liveConnectConstraints.
     *
     * <p>liveConnectConstraints: Configuration specific to Live API connections created using this
     * token.
     */
    @JsonProperty("liveConnectConstraints")
    public abstract Builder liveConnectConstraints(LiveConnectConstraints liveConnectConstraints);

    /**
     * Setter for liveConnectConstraints builder.
     *
     * <p>liveConnectConstraints: Configuration specific to Live API connections created using this
     * token.
     */
    @CanIgnoreReturnValue
    public Builder liveConnectConstraints(
        LiveConnectConstraints.Builder liveConnectConstraintsBuilder) {
      return liveConnectConstraints(liveConnectConstraintsBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder liveConnectConstraints(
        Optional<LiveConnectConstraints> liveConnectConstraints);

    /** Clears the value of liveConnectConstraints field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearLiveConnectConstraints() {
      return liveConnectConstraints(Optional.empty());
    }

    /**
     * Setter for lockAdditionalFields.
     *
     * <p>lockAdditionalFields: Additional fields to lock in the effective LiveConnectParameters.
     */
    @JsonProperty("lockAdditionalFields")
    public abstract Builder lockAdditionalFields(List<String> lockAdditionalFields);

    /**
     * Setter for lockAdditionalFields.
     *
     * <p>lockAdditionalFields: Additional fields to lock in the effective LiveConnectParameters.
     */
    @CanIgnoreReturnValue
    public Builder lockAdditionalFields(String... lockAdditionalFields) {
      return lockAdditionalFields(Arrays.asList(lockAdditionalFields));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder lockAdditionalFields(Optional<List<String>> lockAdditionalFields);

    /** Clears the value of lockAdditionalFields field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearLockAdditionalFields() {
      return lockAdditionalFields(Optional.empty());
    }

    public abstract CreateAuthTokenConfig build();
  }

  /** Deserializes a JSON string to a CreateAuthTokenConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static CreateAuthTokenConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, CreateAuthTokenConfig.class);
  }
}
