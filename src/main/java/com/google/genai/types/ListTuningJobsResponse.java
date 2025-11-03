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

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** Response for the list tuning jobs method. */
@AutoValue
@JsonDeserialize(builder = ListTuningJobsResponse.Builder.class)
public abstract class ListTuningJobsResponse extends JsonSerializable {
  /** Used to retain the full HTTP response. */
  @JsonProperty("sdkHttpResponse")
  public abstract Optional<HttpResponse> sdkHttpResponse();

  /**
   * A token to retrieve the next page of results. Pass to ListTuningJobsRequest.page_token to
   * obtain that page.
   */
  @JsonProperty("nextPageToken")
  public abstract Optional<String> nextPageToken();

  /** List of TuningJobs in the requested page. */
  @JsonProperty("tuningJobs")
  public abstract Optional<List<TuningJob>> tuningJobs();

  /** Instantiates a builder for ListTuningJobsResponse. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ListTuningJobsResponse.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ListTuningJobsResponse. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ListTuningJobsResponse.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ListTuningJobsResponse.Builder();
    }

    /**
     * Setter for sdkHttpResponse.
     *
     * <p>sdkHttpResponse: Used to retain the full HTTP response.
     */
    @JsonProperty("sdkHttpResponse")
    public abstract Builder sdkHttpResponse(HttpResponse sdkHttpResponse);

    /**
     * Setter for sdkHttpResponse builder.
     *
     * <p>sdkHttpResponse: Used to retain the full HTTP response.
     */
    @CanIgnoreReturnValue
    public Builder sdkHttpResponse(HttpResponse.Builder sdkHttpResponseBuilder) {
      return sdkHttpResponse(sdkHttpResponseBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder sdkHttpResponse(Optional<HttpResponse> sdkHttpResponse);

    /** Clears the value of sdkHttpResponse field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSdkHttpResponse() {
      return sdkHttpResponse(Optional.empty());
    }

    /**
     * Setter for nextPageToken.
     *
     * <p>nextPageToken: A token to retrieve the next page of results. Pass to
     * ListTuningJobsRequest.page_token to obtain that page.
     */
    @JsonProperty("nextPageToken")
    public abstract Builder nextPageToken(String nextPageToken);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder nextPageToken(Optional<String> nextPageToken);

    /** Clears the value of nextPageToken field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearNextPageToken() {
      return nextPageToken(Optional.empty());
    }

    /**
     * Setter for tuningJobs.
     *
     * <p>tuningJobs: List of TuningJobs in the requested page.
     */
    @JsonProperty("tuningJobs")
    public abstract Builder tuningJobs(List<TuningJob> tuningJobs);

    /**
     * Setter for tuningJobs.
     *
     * <p>tuningJobs: List of TuningJobs in the requested page.
     */
    @CanIgnoreReturnValue
    public Builder tuningJobs(TuningJob... tuningJobs) {
      return tuningJobs(Arrays.asList(tuningJobs));
    }

    /**
     * Setter for tuningJobs builder.
     *
     * <p>tuningJobs: List of TuningJobs in the requested page.
     */
    @CanIgnoreReturnValue
    public Builder tuningJobs(TuningJob.Builder... tuningJobsBuilders) {
      return tuningJobs(
          Arrays.asList(tuningJobsBuilders).stream()
              .map(TuningJob.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder tuningJobs(Optional<List<TuningJob>> tuningJobs);

    /** Clears the value of tuningJobs field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTuningJobs() {
      return tuningJobs(Optional.empty());
    }

    public abstract ListTuningJobsResponse build();
  }

  /** Deserializes a JSON string to a ListTuningJobsResponse object. */
  @ExcludeFromGeneratedCoverageReport
  public static ListTuningJobsResponse fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ListTuningJobsResponse.class);
  }
}
