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

/** GoogleSearch tool type. Tool to support Google Search in Model. Powered by Google. */
@AutoValue
@JsonDeserialize(builder = GoogleSearch.Builder.class)
public abstract class GoogleSearch extends JsonSerializable {
  /**
   * Optional. Sites with confidence level chosen & above this value will be blocked from the search
   * results. This field is not supported in Gemini API.
   */
  @JsonProperty("blockingConfidence")
  public abstract Optional<PhishBlockThreshold> blockingConfidence();

  /**
   * Optional. List of domains to be excluded from the search results. The default limit is 2000
   * domains. Example: ["amazon.com", "facebook.com"]. This field is not supported in Gemini API.
   */
  @JsonProperty("excludeDomains")
  public abstract Optional<List<String>> excludeDomains();

  /**
   * Optional. Filter search results to a specific time range. If customers set a start time, they
   * must set an end time (and vice versa). This field is not supported in Vertex AI.
   */
  @JsonProperty("timeRangeFilter")
  public abstract Optional<Interval> timeRangeFilter();

  /** Instantiates a builder for GoogleSearch. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GoogleSearch.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GoogleSearch. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `GoogleSearch.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GoogleSearch.Builder();
    }

    /**
     * Setter for blockingConfidence.
     *
     * <p>blockingConfidence: Optional. Sites with confidence level chosen & above this value will
     * be blocked from the search results. This field is not supported in Gemini API.
     */
    @JsonProperty("blockingConfidence")
    public abstract Builder blockingConfidence(PhishBlockThreshold blockingConfidence);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder blockingConfidence(Optional<PhishBlockThreshold> blockingConfidence);

    /** Clears the value of blockingConfidence field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearBlockingConfidence() {
      return blockingConfidence(Optional.empty());
    }

    /**
     * Setter for blockingConfidence given a known enum.
     *
     * <p>blockingConfidence: Optional. Sites with confidence level chosen & above this value will
     * be blocked from the search results. This field is not supported in Gemini API.
     */
    @CanIgnoreReturnValue
    public Builder blockingConfidence(PhishBlockThreshold.Known knownType) {
      return blockingConfidence(new PhishBlockThreshold(knownType));
    }

    /**
     * Setter for blockingConfidence given a string.
     *
     * <p>blockingConfidence: Optional. Sites with confidence level chosen & above this value will
     * be blocked from the search results. This field is not supported in Gemini API.
     */
    @CanIgnoreReturnValue
    public Builder blockingConfidence(String blockingConfidence) {
      return blockingConfidence(new PhishBlockThreshold(blockingConfidence));
    }

    /**
     * Setter for excludeDomains.
     *
     * <p>excludeDomains: Optional. List of domains to be excluded from the search results. The
     * default limit is 2000 domains. Example: ["amazon.com", "facebook.com"]. This field is not
     * supported in Gemini API.
     */
    @JsonProperty("excludeDomains")
    public abstract Builder excludeDomains(List<String> excludeDomains);

    /**
     * Setter for excludeDomains.
     *
     * <p>excludeDomains: Optional. List of domains to be excluded from the search results. The
     * default limit is 2000 domains. Example: ["amazon.com", "facebook.com"]. This field is not
     * supported in Gemini API.
     */
    @CanIgnoreReturnValue
    public Builder excludeDomains(String... excludeDomains) {
      return excludeDomains(Arrays.asList(excludeDomains));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder excludeDomains(Optional<List<String>> excludeDomains);

    /** Clears the value of excludeDomains field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearExcludeDomains() {
      return excludeDomains(Optional.empty());
    }

    /**
     * Setter for timeRangeFilter.
     *
     * <p>timeRangeFilter: Optional. Filter search results to a specific time range. If customers
     * set a start time, they must set an end time (and vice versa). This field is not supported in
     * Vertex AI.
     */
    @JsonProperty("timeRangeFilter")
    public abstract Builder timeRangeFilter(Interval timeRangeFilter);

    /**
     * Setter for timeRangeFilter builder.
     *
     * <p>timeRangeFilter: Optional. Filter search results to a specific time range. If customers
     * set a start time, they must set an end time (and vice versa). This field is not supported in
     * Vertex AI.
     */
    @CanIgnoreReturnValue
    public Builder timeRangeFilter(Interval.Builder timeRangeFilterBuilder) {
      return timeRangeFilter(timeRangeFilterBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder timeRangeFilter(Optional<Interval> timeRangeFilter);

    /** Clears the value of timeRangeFilter field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTimeRangeFilter() {
      return timeRangeFilter(Optional.empty());
    }

    public abstract GoogleSearch build();
  }

  /** Deserializes a JSON string to a GoogleSearch object. */
  @ExcludeFromGeneratedCoverageReport
  public static GoogleSearch fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GoogleSearch.class);
  }
}
