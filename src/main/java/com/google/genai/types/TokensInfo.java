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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** Tokens info with a list of tokens and the corresponding list of token ids. */
@AutoValue
@JsonDeserialize(builder = TokensInfo.Builder.class)
public abstract class TokensInfo extends JsonSerializable {
  /** Optional fields for the role from the corresponding Content. */
  @JsonProperty("role")
  public abstract Optional<String> role();

  /** A list of token ids from the input. */
  @JsonProperty("tokenIds")
  public abstract Optional<List<Long>> tokenIds();

  /** A list of tokens from the input. */
  @JsonProperty("tokens")
  public abstract Optional<List<byte[]>> tokens();

  /** Instantiates a builder for TokensInfo. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_TokensInfo.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for TokensInfo. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `TokensInfo.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_TokensInfo.Builder();
    }

    /**
     * Setter for role.
     *
     * <p>role: Optional fields for the role from the corresponding Content.
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
     * Setter for tokenIds.
     *
     * <p>tokenIds: A list of token ids from the input.
     */
    @JsonProperty("tokenIds")
    public abstract Builder tokenIds(List<Long> tokenIds);

    /**
     * Setter for tokenIds.
     *
     * <p>tokenIds: A list of token ids from the input.
     */
    @CanIgnoreReturnValue
    public Builder tokenIds(Long... tokenIds) {
      return tokenIds(Arrays.asList(tokenIds));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder tokenIds(Optional<List<Long>> tokenIds);

    /** Clears the value of tokenIds field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTokenIds() {
      return tokenIds(Optional.empty());
    }

    /**
     * Setter for tokens.
     *
     * <p>tokens: A list of tokens from the input.
     */
    @JsonProperty("tokens")
    public abstract Builder tokens(List<byte[]> tokens);

    /**
     * Setter for tokens.
     *
     * <p>tokens: A list of tokens from the input.
     */
    @CanIgnoreReturnValue
    public Builder tokens(byte[]... tokens) {
      return tokens(Arrays.asList(tokens));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder tokens(Optional<List<byte[]>> tokens);

    /** Clears the value of tokens field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTokens() {
      return tokens(Optional.empty());
    }

    public abstract TokensInfo build();
  }

  /** Deserializes a JSON string to a TokensInfo object. */
  @ExcludeFromGeneratedCoverageReport
  public static TokensInfo fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, TokensInfo.class);
  }
}
