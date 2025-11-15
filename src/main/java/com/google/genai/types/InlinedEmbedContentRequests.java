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

/**
 * The requests to be processed in the batch if provided as part of the batch creation request. This
 * data type is not supported in Vertex AI.
 */
@AutoValue
@JsonDeserialize(builder = InlinedEmbedContentRequests.Builder.class)
public abstract class InlinedEmbedContentRequests extends JsonSerializable {
  /** Required. The requests to be processed in the batch. */
  @JsonProperty("requests")
  public abstract Optional<List<InlinedEmbedContentRequest>> requests();

  /** Instantiates a builder for InlinedEmbedContentRequests. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_InlinedEmbedContentRequests.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for InlinedEmbedContentRequests. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `InlinedEmbedContentRequests.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_InlinedEmbedContentRequests.Builder();
    }

    /**
     * Setter for requests.
     *
     * <p>requests: Required. The requests to be processed in the batch.
     */
    @JsonProperty("requests")
    public abstract Builder requests(List<InlinedEmbedContentRequest> requests);

    /**
     * Setter for requests.
     *
     * <p>requests: Required. The requests to be processed in the batch.
     */
    @CanIgnoreReturnValue
    public Builder requests(InlinedEmbedContentRequest... requests) {
      return requests(Arrays.asList(requests));
    }

    /**
     * Setter for requests builder.
     *
     * <p>requests: Required. The requests to be processed in the batch.
     */
    @CanIgnoreReturnValue
    public Builder requests(InlinedEmbedContentRequest.Builder... requestsBuilders) {
      return requests(
          Arrays.asList(requestsBuilders).stream()
              .map(InlinedEmbedContentRequest.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder requests(Optional<List<InlinedEmbedContentRequest>> requests);

    /** Clears the value of requests field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRequests() {
      return requests(Optional.empty());
    }

    public abstract InlinedEmbedContentRequests build();
  }

  /** Deserializes a JSON string to a InlinedEmbedContentRequests object. */
  @ExcludeFromGeneratedCoverageReport
  public static InlinedEmbedContentRequests fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, InlinedEmbedContentRequests.class);
  }
}
