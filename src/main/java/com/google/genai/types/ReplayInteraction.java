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

/** Represents a single interaction, request and response in a replay. */
@AutoValue
@JsonDeserialize(builder = ReplayInteraction.Builder.class)
public abstract class ReplayInteraction extends JsonSerializable {
  /** */
  @JsonProperty("request")
  public abstract Optional<ReplayRequest> request();

  /** */
  @JsonProperty("response")
  public abstract Optional<ReplayResponse> response();

  /** Instantiates a builder for ReplayInteraction. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ReplayInteraction.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ReplayInteraction. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ReplayInteraction.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ReplayInteraction.Builder();
    }

    /**
     * Setter for request.
     *
     * <p>request:
     */
    @JsonProperty("request")
    public abstract Builder request(ReplayRequest request);

    /**
     * Setter for request builder.
     *
     * <p>request:
     */
    public Builder request(ReplayRequest.Builder requestBuilder) {
      return request(requestBuilder.build());
    }

    /**
     * Setter for response.
     *
     * <p>response:
     */
    @JsonProperty("response")
    public abstract Builder response(ReplayResponse response);

    /**
     * Setter for response builder.
     *
     * <p>response:
     */
    public Builder response(ReplayResponse.Builder responseBuilder) {
      return response(responseBuilder.build());
    }

    public abstract ReplayInteraction build();
  }

  /** Deserializes a JSON string to a ReplayInteraction object. */
  @ExcludeFromGeneratedCoverageReport
  public static ReplayInteraction fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ReplayInteraction.class);
  }
}
