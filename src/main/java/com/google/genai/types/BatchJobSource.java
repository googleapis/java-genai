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
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** Config for `src` parameter. */
@AutoValue
@JsonDeserialize(builder = BatchJobSource.Builder.class)
public abstract class BatchJobSource extends JsonSerializable {
  /** Storage format of the input files. Must be one of: 'jsonl', 'bigquery'. */
  @JsonProperty("format")
  public abstract Optional<String> format();

  /** The Google Cloud Storage URIs to input files. */
  @JsonProperty("gcsUri")
  public abstract Optional<List<String>> gcsUri();

  /** The BigQuery URI to input table. */
  @JsonProperty("bigqueryUri")
  public abstract Optional<String> bigqueryUri();

  /** The Gemini Developer API's file resource name of the input data (e.g. "files/12345"). */
  @JsonProperty("fileName")
  public abstract Optional<String> fileName();

  /** The Gemini Developer API's inlined input data to run batch job. */
  @JsonProperty("inlinedRequests")
  public abstract Optional<List<InlinedRequest>> inlinedRequests();

  /** Instantiates a builder for BatchJobSource. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_BatchJobSource.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for BatchJobSource. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `BatchJobSource.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_BatchJobSource.Builder();
    }

    /**
     * Setter for format.
     *
     * <p>format: Storage format of the input files. Must be one of: 'jsonl', 'bigquery'.
     */
    @JsonProperty("format")
    public abstract Builder format(String format);

    /**
     * Setter for gcsUri.
     *
     * <p>gcsUri: The Google Cloud Storage URIs to input files.
     */
    @JsonProperty("gcsUri")
    public abstract Builder gcsUri(List<String> gcsUri);

    /**
     * Setter for gcsUri.
     *
     * <p>gcsUri: The Google Cloud Storage URIs to input files.
     */
    public Builder gcsUri(String... gcsUri) {
      return gcsUri(Arrays.asList(gcsUri));
    }

    /**
     * Setter for bigqueryUri.
     *
     * <p>bigqueryUri: The BigQuery URI to input table.
     */
    @JsonProperty("bigqueryUri")
    public abstract Builder bigqueryUri(String bigqueryUri);

    /**
     * Setter for fileName.
     *
     * <p>fileName: The Gemini Developer API's file resource name of the input data (e.g.
     * "files/12345").
     */
    @JsonProperty("fileName")
    public abstract Builder fileName(String fileName);

    /**
     * Setter for inlinedRequests.
     *
     * <p>inlinedRequests: The Gemini Developer API's inlined input data to run batch job.
     */
    @JsonProperty("inlinedRequests")
    public abstract Builder inlinedRequests(List<InlinedRequest> inlinedRequests);

    /**
     * Setter for inlinedRequests.
     *
     * <p>inlinedRequests: The Gemini Developer API's inlined input data to run batch job.
     */
    public Builder inlinedRequests(InlinedRequest... inlinedRequests) {
      return inlinedRequests(Arrays.asList(inlinedRequests));
    }

    /**
     * Setter for inlinedRequests builder.
     *
     * <p>inlinedRequests: The Gemini Developer API's inlined input data to run batch job.
     */
    public Builder inlinedRequests(InlinedRequest.Builder... inlinedRequestsBuilders) {
      return inlinedRequests(
          Arrays.asList(inlinedRequestsBuilders).stream()
              .map(InlinedRequest.Builder::build)
              .collect(toImmutableList()));
    }

    public abstract BatchJobSource build();
  }

  /** Deserializes a JSON string to a BatchJobSource object. */
  @ExcludeFromGeneratedCoverageReport
  public static BatchJobSource fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, BatchJobSource.class);
  }
}
