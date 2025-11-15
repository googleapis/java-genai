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

/**
 * The output of a batch request. This is returned in the `AsyncBatchEmbedContentResponse` or the
 * `EmbedContentBatch.output` field. This data type is not supported in Vertex AI.
 */
@AutoValue
@JsonDeserialize(builder = EmbedContentBatchOutput.Builder.class)
public abstract class EmbedContentBatchOutput extends JsonSerializable {
  /**
   * Output only. The responses to the requests in the batch. Returned when the batch was built
   * using inlined requests. The responses will be in the same order as the input requests.
   */
  @JsonProperty("inlinedResponses")
  public abstract Optional<InlinedEmbedContentResponses> inlinedResponses();

  /**
   * Output only. The file ID of the file containing the responses. The file will be a JSONL file
   * with a single response per line. The responses will be `EmbedContentResponse` messages
   * formatted as JSON. The responses will be written in the same order as the input requests.
   */
  @JsonProperty("responsesFile")
  public abstract Optional<String> responsesFile();

  /** Instantiates a builder for EmbedContentBatchOutput. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_EmbedContentBatchOutput.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for EmbedContentBatchOutput. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `EmbedContentBatchOutput.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_EmbedContentBatchOutput.Builder();
    }

    /**
     * Setter for inlinedResponses.
     *
     * <p>inlinedResponses: Output only. The responses to the requests in the batch. Returned when
     * the batch was built using inlined requests. The responses will be in the same order as the
     * input requests.
     */
    @JsonProperty("inlinedResponses")
    public abstract Builder inlinedResponses(InlinedEmbedContentResponses inlinedResponses);

    /**
     * Setter for inlinedResponses builder.
     *
     * <p>inlinedResponses: Output only. The responses to the requests in the batch. Returned when
     * the batch was built using inlined requests. The responses will be in the same order as the
     * input requests.
     */
    @CanIgnoreReturnValue
    public Builder inlinedResponses(InlinedEmbedContentResponses.Builder inlinedResponsesBuilder) {
      return inlinedResponses(inlinedResponsesBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder inlinedResponses(Optional<InlinedEmbedContentResponses> inlinedResponses);

    /** Clears the value of inlinedResponses field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearInlinedResponses() {
      return inlinedResponses(Optional.empty());
    }

    /**
     * Setter for responsesFile.
     *
     * <p>responsesFile: Output only. The file ID of the file containing the responses. The file
     * will be a JSONL file with a single response per line. The responses will be
     * `EmbedContentResponse` messages formatted as JSON. The responses will be written in the same
     * order as the input requests.
     */
    @JsonProperty("responsesFile")
    public abstract Builder responsesFile(String responsesFile);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder responsesFile(Optional<String> responsesFile);

    /** Clears the value of responsesFile field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearResponsesFile() {
      return responsesFile(Optional.empty());
    }

    public abstract EmbedContentBatchOutput build();
  }

  /** Deserializes a JSON string to a EmbedContentBatchOutput object. */
  @ExcludeFromGeneratedCoverageReport
  public static EmbedContentBatchOutput fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, EmbedContentBatchOutput.class);
  }
}
