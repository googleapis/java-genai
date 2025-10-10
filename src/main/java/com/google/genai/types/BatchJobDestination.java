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

/** Config for `des` parameter. */
@AutoValue
@JsonDeserialize(builder = BatchJobDestination.Builder.class)
public abstract class BatchJobDestination extends JsonSerializable {
  /** Storage format of the output files. Must be one of: 'jsonl', 'bigquery'. */
  @JsonProperty("format")
  public abstract Optional<String> format();

  /** The Google Cloud Storage URI to the output file. */
  @JsonProperty("gcsUri")
  public abstract Optional<String> gcsUri();

  /** The BigQuery URI to the output table. */
  @JsonProperty("bigqueryUri")
  public abstract Optional<String> bigqueryUri();

  /**
   * The Gemini Developer API's file resource name of the output data (e.g. "files/12345"). The file
   * will be a JSONL file with a single response per line. The responses will be
   * GenerateContentResponse messages formatted as JSON. The responses will be written in the same
   * order as the input requests.
   */
  @JsonProperty("fileName")
  public abstract Optional<String> fileName();

  /**
   * The responses to the requests in the batch. Returned when the batch was built using inlined
   * requests. The responses will be in the same order as the input requests.
   */
  @JsonProperty("inlinedResponses")
  public abstract Optional<List<InlinedResponse>> inlinedResponses();

  /**
   * The responses to the requests in the batch. Returned when the batch was built using inlined
   * requests. The responses will be in the same order as the input requests.
   */
  @JsonProperty("inlinedEmbedContentResponses")
  public abstract Optional<List<InlinedEmbedContentResponse>> inlinedEmbedContentResponses();

  /** Instantiates a builder for BatchJobDestination. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_BatchJobDestination.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for BatchJobDestination. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `BatchJobDestination.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_BatchJobDestination.Builder();
    }

    /**
     * Setter for format.
     *
     * <p>format: Storage format of the output files. Must be one of: 'jsonl', 'bigquery'.
     */
    @JsonProperty("format")
    public abstract Builder format(String format);

    /**
     * Setter for gcsUri.
     *
     * <p>gcsUri: The Google Cloud Storage URI to the output file.
     */
    @JsonProperty("gcsUri")
    public abstract Builder gcsUri(String gcsUri);

    /**
     * Setter for bigqueryUri.
     *
     * <p>bigqueryUri: The BigQuery URI to the output table.
     */
    @JsonProperty("bigqueryUri")
    public abstract Builder bigqueryUri(String bigqueryUri);

    /**
     * Setter for fileName.
     *
     * <p>fileName: The Gemini Developer API's file resource name of the output data (e.g.
     * "files/12345"). The file will be a JSONL file with a single response per line. The responses
     * will be GenerateContentResponse messages formatted as JSON. The responses will be written in
     * the same order as the input requests.
     */
    @JsonProperty("fileName")
    public abstract Builder fileName(String fileName);

    /**
     * Setter for inlinedResponses.
     *
     * <p>inlinedResponses: The responses to the requests in the batch. Returned when the batch was
     * built using inlined requests. The responses will be in the same order as the input requests.
     */
    @JsonProperty("inlinedResponses")
    public abstract Builder inlinedResponses(List<InlinedResponse> inlinedResponses);

    /**
     * Setter for inlinedResponses.
     *
     * <p>inlinedResponses: The responses to the requests in the batch. Returned when the batch was
     * built using inlined requests. The responses will be in the same order as the input requests.
     */
    public Builder inlinedResponses(InlinedResponse... inlinedResponses) {
      return inlinedResponses(Arrays.asList(inlinedResponses));
    }

    /**
     * Setter for inlinedResponses builder.
     *
     * <p>inlinedResponses: The responses to the requests in the batch. Returned when the batch was
     * built using inlined requests. The responses will be in the same order as the input requests.
     */
    public Builder inlinedResponses(InlinedResponse.Builder... inlinedResponsesBuilders) {
      return inlinedResponses(
          Arrays.asList(inlinedResponsesBuilders).stream()
              .map(InlinedResponse.Builder::build)
              .collect(toImmutableList()));
    }

    /**
     * Setter for inlinedEmbedContentResponses.
     *
     * <p>inlinedEmbedContentResponses: The responses to the requests in the batch. Returned when
     * the batch was built using inlined requests. The responses will be in the same order as the
     * input requests.
     */
    @JsonProperty("inlinedEmbedContentResponses")
    public abstract Builder inlinedEmbedContentResponses(
        List<InlinedEmbedContentResponse> inlinedEmbedContentResponses);

    /**
     * Setter for inlinedEmbedContentResponses.
     *
     * <p>inlinedEmbedContentResponses: The responses to the requests in the batch. Returned when
     * the batch was built using inlined requests. The responses will be in the same order as the
     * input requests.
     */
    public Builder inlinedEmbedContentResponses(
        InlinedEmbedContentResponse... inlinedEmbedContentResponses) {
      return inlinedEmbedContentResponses(Arrays.asList(inlinedEmbedContentResponses));
    }

    /**
     * Setter for inlinedEmbedContentResponses builder.
     *
     * <p>inlinedEmbedContentResponses: The responses to the requests in the batch. Returned when
     * the batch was built using inlined requests. The responses will be in the same order as the
     * input requests.
     */
    public Builder inlinedEmbedContentResponses(
        InlinedEmbedContentResponse.Builder... inlinedEmbedContentResponsesBuilders) {
      return inlinedEmbedContentResponses(
          Arrays.asList(inlinedEmbedContentResponsesBuilders).stream()
              .map(InlinedEmbedContentResponse.Builder::build)
              .collect(toImmutableList()));
    }

    public abstract BatchJobDestination build();
  }

  /** Deserializes a JSON string to a BatchJobDestination object. */
  @ExcludeFromGeneratedCoverageReport
  public static BatchJobDestination fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, BatchJobDestination.class);
  }
}
