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

/** Stats about the batch. This data type is not supported in Vertex AI. */
@AutoValue
@JsonDeserialize(builder = EmbedContentBatchStats.Builder.class)
public abstract class EmbedContentBatchStats extends JsonSerializable {
  /** Output only. The number of requests that failed to be processed. */
  @JsonProperty("failedRequestCount")
  public abstract Optional<Long> failedRequestCount();

  /** Output only. The number of requests that are still pending processing. */
  @JsonProperty("pendingRequestCount")
  public abstract Optional<Long> pendingRequestCount();

  /** Output only. The number of requests in the batch. */
  @JsonProperty("requestCount")
  public abstract Optional<Long> requestCount();

  /** Output only. The number of requests that were successfully processed. */
  @JsonProperty("successfulRequestCount")
  public abstract Optional<Long> successfulRequestCount();

  /** Instantiates a builder for EmbedContentBatchStats. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_EmbedContentBatchStats.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for EmbedContentBatchStats. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `EmbedContentBatchStats.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_EmbedContentBatchStats.Builder();
    }

    /**
     * Setter for failedRequestCount.
     *
     * <p>failedRequestCount: Output only. The number of requests that failed to be processed.
     */
    @JsonProperty("failedRequestCount")
    public abstract Builder failedRequestCount(Long failedRequestCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder failedRequestCount(Optional<Long> failedRequestCount);

    /** Clears the value of failedRequestCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearFailedRequestCount() {
      return failedRequestCount(Optional.empty());
    }

    /**
     * Setter for pendingRequestCount.
     *
     * <p>pendingRequestCount: Output only. The number of requests that are still pending
     * processing.
     */
    @JsonProperty("pendingRequestCount")
    public abstract Builder pendingRequestCount(Long pendingRequestCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder pendingRequestCount(Optional<Long> pendingRequestCount);

    /** Clears the value of pendingRequestCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPendingRequestCount() {
      return pendingRequestCount(Optional.empty());
    }

    /**
     * Setter for requestCount.
     *
     * <p>requestCount: Output only. The number of requests in the batch.
     */
    @JsonProperty("requestCount")
    public abstract Builder requestCount(Long requestCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder requestCount(Optional<Long> requestCount);

    /** Clears the value of requestCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRequestCount() {
      return requestCount(Optional.empty());
    }

    /**
     * Setter for successfulRequestCount.
     *
     * <p>successfulRequestCount: Output only. The number of requests that were successfully
     * processed.
     */
    @JsonProperty("successfulRequestCount")
    public abstract Builder successfulRequestCount(Long successfulRequestCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder successfulRequestCount(Optional<Long> successfulRequestCount);

    /** Clears the value of successfulRequestCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSuccessfulRequestCount() {
      return successfulRequestCount(Optional.empty());
    }

    public abstract EmbedContentBatchStats build();
  }

  /** Deserializes a JSON string to a EmbedContentBatchStats object. */
  @ExcludeFromGeneratedCoverageReport
  public static EmbedContentBatchStats fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, EmbedContentBatchStats.class);
  }
}
