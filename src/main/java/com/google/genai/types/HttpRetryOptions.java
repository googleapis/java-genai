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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** HTTP retry options to be used in each of the requests. */
@AutoValue
@JsonDeserialize(builder = HttpRetryOptions.Builder.class)
public abstract class HttpRetryOptions extends JsonSerializable {
  /**
   * Maximum number of attempts, including the original request. If 0 or 1, it means no retries. If
   * not specified, default to 5.
   */
  @JsonProperty("attempts")
  public abstract Optional<Integer> attempts();

  /**
   * Initial delay before the first retry, in fractions of a second. If not specified, default to
   * 1.0 second.
   */
  @JsonProperty("initialDelay")
  public abstract Optional<Double> initialDelay();

  /**
   * Maximum delay between retries, in fractions of a second. If not specified, default to 60.0
   * seconds.
   */
  @JsonProperty("maxDelay")
  public abstract Optional<Double> maxDelay();

  /**
   * Multiplier by which the delay increases after each attempt. If not specified, default to 2.0.
   */
  @JsonProperty("expBase")
  public abstract Optional<Double> expBase();

  /** Randomness factor for the delay. If not specified, default to 1.0. */
  @JsonProperty("jitter")
  public abstract Optional<Double> jitter();

  /**
   * List of HTTP status codes that should trigger a retry. If not specified, a default set of
   * retryable codes (408, 429, and 5xx) may be used.
   */
  @JsonProperty("httpStatusCodes")
  public abstract Optional<List<Integer>> httpStatusCodes();

  /** Instantiates a builder for HttpRetryOptions. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_HttpRetryOptions.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for HttpRetryOptions. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `HttpRetryOptions.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_HttpRetryOptions.Builder();
    }

    /**
     * Setter for attempts.
     *
     * <p>attempts: Maximum number of attempts, including the original request. If 0 or 1, it means
     * no retries. If not specified, default to 5.
     */
    @JsonProperty("attempts")
    public abstract Builder attempts(Integer attempts);

    /**
     * Setter for initialDelay.
     *
     * <p>initialDelay: Initial delay before the first retry, in fractions of a second. If not
     * specified, default to 1.0 second.
     */
    @JsonProperty("initialDelay")
    public abstract Builder initialDelay(Double initialDelay);

    /**
     * Setter for maxDelay.
     *
     * <p>maxDelay: Maximum delay between retries, in fractions of a second. If not specified,
     * default to 60.0 seconds.
     */
    @JsonProperty("maxDelay")
    public abstract Builder maxDelay(Double maxDelay);

    /**
     * Setter for expBase.
     *
     * <p>expBase: Multiplier by which the delay increases after each attempt. If not specified,
     * default to 2.0.
     */
    @JsonProperty("expBase")
    public abstract Builder expBase(Double expBase);

    /**
     * Setter for jitter.
     *
     * <p>jitter: Randomness factor for the delay. If not specified, default to 1.0.
     */
    @JsonProperty("jitter")
    public abstract Builder jitter(Double jitter);

    /**
     * Setter for httpStatusCodes.
     *
     * <p>httpStatusCodes: List of HTTP status codes that should trigger a retry. If not specified,
     * a default set of retryable codes (408, 429, and 5xx) may be used.
     */
    @JsonProperty("httpStatusCodes")
    public abstract Builder httpStatusCodes(List<Integer> httpStatusCodes);

    /**
     * Setter for httpStatusCodes.
     *
     * <p>httpStatusCodes: List of HTTP status codes that should trigger a retry. If not specified,
     * a default set of retryable codes (408, 429, and 5xx) may be used.
     */
    public Builder httpStatusCodes(Integer... httpStatusCodes) {
      return httpStatusCodes(Arrays.asList(httpStatusCodes));
    }

    public abstract HttpRetryOptions build();
  }

  /** Deserializes a JSON string to a HttpRetryOptions object. */
  @ExcludeFromGeneratedCoverageReport
  public static HttpRetryOptions fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, HttpRetryOptions.class);
  }
}
