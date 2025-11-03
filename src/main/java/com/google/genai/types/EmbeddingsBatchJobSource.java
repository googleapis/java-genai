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

/** None */
@AutoValue
@JsonDeserialize(builder = EmbeddingsBatchJobSource.Builder.class)
public abstract class EmbeddingsBatchJobSource extends JsonSerializable {
  /** The Gemini Developer API's file resource name of the input data (e.g. "files/12345"). */
  @JsonProperty("fileName")
  public abstract Optional<String> fileName();

  /** The Gemini Developer API's inlined input data to run batch job. */
  @JsonProperty("inlinedRequests")
  public abstract Optional<EmbedContentBatch> inlinedRequests();

  /** Instantiates a builder for EmbeddingsBatchJobSource. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_EmbeddingsBatchJobSource.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for EmbeddingsBatchJobSource. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `EmbeddingsBatchJobSource.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_EmbeddingsBatchJobSource.Builder();
    }

    /**
     * Setter for fileName.
     *
     * <p>fileName: The Gemini Developer API's file resource name of the input data (e.g.
     * "files/12345").
     */
    @JsonProperty("fileName")
    public abstract Builder fileName(String fileName);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder fileName(Optional<String> fileName);

    /** Clears the value of fileName field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearFileName() {
      return fileName(Optional.empty());
    }

    /**
     * Setter for inlinedRequests.
     *
     * <p>inlinedRequests: The Gemini Developer API's inlined input data to run batch job.
     */
    @JsonProperty("inlinedRequests")
    public abstract Builder inlinedRequests(EmbedContentBatch inlinedRequests);

    /**
     * Setter for inlinedRequests builder.
     *
     * <p>inlinedRequests: The Gemini Developer API's inlined input data to run batch job.
     */
    @CanIgnoreReturnValue
    public Builder inlinedRequests(EmbedContentBatch.Builder inlinedRequestsBuilder) {
      return inlinedRequests(inlinedRequestsBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder inlinedRequests(Optional<EmbedContentBatch> inlinedRequests);

    /** Clears the value of inlinedRequests field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearInlinedRequests() {
      return inlinedRequests(Optional.empty());
    }

    public abstract EmbeddingsBatchJobSource build();
  }

  /** Deserializes a JSON string to a EmbeddingsBatchJobSource object. */
  @ExcludeFromGeneratedCoverageReport
  public static EmbeddingsBatchJobSource fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, EmbeddingsBatchJobSource.class);
  }
}
