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

/** The responses to the requests in the batch. This data type is not supported in Vertex AI. */
@AutoValue
@JsonDeserialize(builder = InlinedEmbedContentResponses.Builder.class)
public abstract class InlinedEmbedContentResponses extends JsonSerializable {
  /** Output only. The responses to the requests in the batch. */
  @JsonProperty("inlinedResponses")
  public abstract Optional<List<InlinedEmbedContentResponse>> inlinedResponses();

  /** Instantiates a builder for InlinedEmbedContentResponses. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_InlinedEmbedContentResponses.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for InlinedEmbedContentResponses. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `InlinedEmbedContentResponses.builder()` for instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_InlinedEmbedContentResponses.Builder();
    }

    /**
     * Setter for inlinedResponses.
     *
     * <p>inlinedResponses: Output only. The responses to the requests in the batch.
     */
    @JsonProperty("inlinedResponses")
    public abstract Builder inlinedResponses(List<InlinedEmbedContentResponse> inlinedResponses);

    /**
     * Setter for inlinedResponses.
     *
     * <p>inlinedResponses: Output only. The responses to the requests in the batch.
     */
    @CanIgnoreReturnValue
    public Builder inlinedResponses(InlinedEmbedContentResponse... inlinedResponses) {
      return inlinedResponses(Arrays.asList(inlinedResponses));
    }

    /**
     * Setter for inlinedResponses builder.
     *
     * <p>inlinedResponses: Output only. The responses to the requests in the batch.
     */
    @CanIgnoreReturnValue
    public Builder inlinedResponses(
        InlinedEmbedContentResponse.Builder... inlinedResponsesBuilders) {
      return inlinedResponses(
          Arrays.asList(inlinedResponsesBuilders).stream()
              .map(InlinedEmbedContentResponse.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder inlinedResponses(Optional<List<InlinedEmbedContentResponse>> inlinedResponses);

    /** Clears the value of inlinedResponses field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearInlinedResponses() {
      return inlinedResponses(Optional.empty());
    }

    public abstract InlinedEmbedContentResponses build();
  }

  /** Deserializes a JSON string to a InlinedEmbedContentResponses object. */
  @ExcludeFromGeneratedCoverageReport
  public static InlinedEmbedContentResponses fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, InlinedEmbedContentResponses.class);
  }
}
