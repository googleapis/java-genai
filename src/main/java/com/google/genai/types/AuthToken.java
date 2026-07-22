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

/** Config for auth_tokens.create parameters. */
@AutoValue
@JsonDeserialize(builder = AuthToken.Builder.class)
public abstract class AuthToken extends JsonSerializable {
  /** Output only. Identifier. The token itself. */
  @JsonProperty("name")
  public abstract Optional<String> name();

  /** Optional. Input only. Immutable. Configuration specific to `BidiGenerateContent`. */
  @JsonProperty("bidiGenerateContentSetup")
  public abstract Optional<BidiGenerateContentSetup> bidiGenerateContentSetup();

  /**
   * Optional. Input only. Immutable. An optional time after which, when using the resulting token,
   * messages in BidiGenerateContent sessions will be rejected. (Gemini may preemptively close the
   * session after this time.) If not set then this defaults to 30 minutes in the future. If set,
   * this value must be less than 20 hours in the future.
   */
  @JsonProperty("expireTime")
  public abstract Optional<Instant> expireTime();

  /**
   * Optional. Input only. Immutable. If field_mask is empty, and `bidi_generate_content_setup` is
   * not present, then the effective `BidiGenerateContentSetup` message is taken from the Live API
   * connection. If field_mask is empty, and `bidi_generate_content_setup` _is_ present, then the
   * effective `BidiGenerateContentSetup` message is taken entirely from
   * `bidi_generate_content_setup` in this request. The setup message from the Live API connection
   * is ignored. If field_mask is not empty, then the corresponding fields from
   * `bidi_generate_content_setup` will overwrite the fields from the setup message in the Live API
   * connection.
   */
  @JsonProperty("fieldMask")
  public abstract Optional<String> fieldMask();

  /**
   * Optional. Input only. Immutable. The time after which new Live API sessions using the token
   * resulting from this request will be rejected. If not set this defaults to 60 seconds in the
   * future. If set, this value must be less than 20 hours in the future.
   */
  @JsonProperty("newSessionExpireTime")
  public abstract Optional<Instant> newSessionExpireTime();

  /**
   * Optional. Input only. Immutable. The number of times the token can be used. If this value is
   * zero then no limit is applied. Resuming a Live API session does not count as a use. If
   * unspecified, the default is 1.
   */
  @JsonProperty("uses")
  public abstract Optional<Integer> uses();

  /** Instantiates a builder for AuthToken. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_AuthToken.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for AuthToken. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `AuthToken.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_AuthToken.Builder();
    }

    /**
     * Setter for name.
     *
     * <p>name: Output only. Identifier. The token itself.
     */
    @JsonProperty("name")
    public abstract Builder name(String name);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder name(Optional<String> name);

    /** Clears the value of name field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearName() {
      return name(Optional.empty());
    }

    /**
     * Setter for bidiGenerateContentSetup.
     *
     * <p>bidiGenerateContentSetup: Optional. Input only. Immutable. Configuration specific to
     * `BidiGenerateContent`.
     */
    @JsonProperty("bidiGenerateContentSetup")
    public abstract Builder bidiGenerateContentSetup(
        BidiGenerateContentSetup bidiGenerateContentSetup);

    /**
     * Setter for bidiGenerateContentSetup builder.
     *
     * <p>bidiGenerateContentSetup: Optional. Input only. Immutable. Configuration specific to
     * `BidiGenerateContent`.
     */
    @CanIgnoreReturnValue
    public Builder bidiGenerateContentSetup(
        BidiGenerateContentSetup.Builder bidiGenerateContentSetupBuilder) {
      return bidiGenerateContentSetup(bidiGenerateContentSetupBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder bidiGenerateContentSetup(
        Optional<BidiGenerateContentSetup> bidiGenerateContentSetup);

    /** Clears the value of bidiGenerateContentSetup field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearBidiGenerateContentSetup() {
      return bidiGenerateContentSetup(Optional.empty());
    }

    /**
     * Setter for expireTime.
     *
     * <p>expireTime: Optional. Input only. Immutable. An optional time after which, when using the
     * resulting token, messages in BidiGenerateContent sessions will be rejected. (Gemini may
     * preemptively close the session after this time.) If not set then this defaults to 30 minutes
     * in the future. If set, this value must be less than 20 hours in the future.
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
     * Setter for fieldMask.
     *
     * <p>fieldMask: Optional. Input only. Immutable. If field_mask is empty, and
     * `bidi_generate_content_setup` is not present, then the effective `BidiGenerateContentSetup`
     * message is taken from the Live API connection. If field_mask is empty, and
     * `bidi_generate_content_setup` _is_ present, then the effective `BidiGenerateContentSetup`
     * message is taken entirely from `bidi_generate_content_setup` in this request. The setup
     * message from the Live API connection is ignored. If field_mask is not empty, then the
     * corresponding fields from `bidi_generate_content_setup` will overwrite the fields from the
     * setup message in the Live API connection.
     */
    @JsonProperty("fieldMask")
    public abstract Builder fieldMask(String fieldMask);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder fieldMask(Optional<String> fieldMask);

    /** Clears the value of fieldMask field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearFieldMask() {
      return fieldMask(Optional.empty());
    }

    /**
     * Setter for newSessionExpireTime.
     *
     * <p>newSessionExpireTime: Optional. Input only. Immutable. The time after which new Live API
     * sessions using the token resulting from this request will be rejected. If not set this
     * defaults to 60 seconds in the future. If set, this value must be less than 20 hours in the
     * future.
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
     * <p>uses: Optional. Input only. Immutable. The number of times the token can be used. If this
     * value is zero then no limit is applied. Resuming a Live API session does not count as a use.
     * If unspecified, the default is 1.
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

    public abstract AuthToken build();
  }

  /** Deserializes a JSON string to a AuthToken object. */
  @ExcludeFromGeneratedCoverageReport
  public static AuthToken fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, AuthToken.class);
  }
}
