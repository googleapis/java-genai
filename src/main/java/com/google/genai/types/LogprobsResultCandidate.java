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

/** A single token and its associated log probability. */
@AutoValue
@JsonDeserialize(builder = LogprobsResultCandidate.Builder.class)
public abstract class LogprobsResultCandidate extends JsonSerializable {
  /**
   * The log probability of this token. A higher value indicates that the model was more confident
   * in this token. The log probability can be used to assess the relative likelihood of different
   * tokens and to identify when the model is uncertain.
   */
  @JsonProperty("logProbability")
  public abstract Optional<Float> logProbability();

  /** The token's string representation. */
  @JsonProperty("token")
  public abstract Optional<String> token();

  /**
   * The token's numerical ID. While the `token` field provides the string representation of the
   * token, the `token_id` is the numerical representation that the model uses internally. This can
   * be useful for developers who want to build custom logic based on the model's vocabulary.
   */
  @JsonProperty("tokenId")
  public abstract Optional<Integer> tokenId();

  /** Instantiates a builder for LogprobsResultCandidate. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_LogprobsResultCandidate.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for LogprobsResultCandidate. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `LogprobsResultCandidate.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_LogprobsResultCandidate.Builder();
    }

    /**
     * Setter for logProbability.
     *
     * <p>logProbability: The log probability of this token. A higher value indicates that the model
     * was more confident in this token. The log probability can be used to assess the relative
     * likelihood of different tokens and to identify when the model is uncertain.
     */
    @JsonProperty("logProbability")
    public abstract Builder logProbability(Float logProbability);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder logProbability(Optional<Float> logProbability);

    /** Clears the value of logProbability field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearLogProbability() {
      return logProbability(Optional.empty());
    }

    /**
     * Setter for token.
     *
     * <p>token: The token's string representation.
     */
    @JsonProperty("token")
    public abstract Builder token(String token);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder token(Optional<String> token);

    /** Clears the value of token field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearToken() {
      return token(Optional.empty());
    }

    /**
     * Setter for tokenId.
     *
     * <p>tokenId: The token's numerical ID. While the `token` field provides the string
     * representation of the token, the `token_id` is the numerical representation that the model
     * uses internally. This can be useful for developers who want to build custom logic based on
     * the model's vocabulary.
     */
    @JsonProperty("tokenId")
    public abstract Builder tokenId(Integer tokenId);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder tokenId(Optional<Integer> tokenId);

    /** Clears the value of tokenId field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTokenId() {
      return tokenId(Optional.empty());
    }

    public abstract LogprobsResultCandidate build();
  }

  /** Deserializes a JSON string to a LogprobsResultCandidate object. */
  @ExcludeFromGeneratedCoverageReport
  public static LogprobsResultCandidate fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, LogprobsResultCandidate.class);
  }
}
