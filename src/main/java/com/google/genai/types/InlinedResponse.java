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
import java.util.Optional;

/** Config for `inlined_responses` parameter. */
@AutoValue
@JsonDeserialize(builder = InlinedResponse.Builder.class)
public abstract class InlinedResponse extends JsonSerializable {
  /** The response to the request. */
  @JsonProperty("response")
  public abstract Optional<GenerateContentResponse> response();

  /** The error encountered while processing the request. */
  @JsonProperty("error")
  public abstract Optional<JobError> error();

  /** Instantiates a builder for InlinedResponse. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_InlinedResponse.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for InlinedResponse. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `InlinedResponse.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_InlinedResponse.Builder();
    }

    /**
     * Setter for response.
     *
     * <p>response: The response to the request.
     */
    @JsonProperty("response")
    public abstract Builder response(GenerateContentResponse response);

    /**
     * Setter for response builder.
     *
     * <p>response: The response to the request.
     */
    public Builder response(GenerateContentResponse.Builder responseBuilder) {
      return response(responseBuilder.build());
    }

    /**
     * Setter for error.
     *
     * <p>error: The error encountered while processing the request.
     */
    @JsonProperty("error")
    public abstract Builder error(JobError error);

    /**
     * Setter for error builder.
     *
     * <p>error: The error encountered while processing the request.
     */
    public Builder error(JobError.Builder errorBuilder) {
      return error(errorBuilder.build());
    }

    public abstract InlinedResponse build();
  }

  /** Deserializes a JSON string to a InlinedResponse object. */
  @ExcludeFromGeneratedCoverageReport
  public static InlinedResponse fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, InlinedResponse.class);
  }
}
