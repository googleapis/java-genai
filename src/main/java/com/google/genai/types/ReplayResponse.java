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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** Represents a single response in a replay. */
@AutoValue
@JsonDeserialize(builder = ReplayResponse.Builder.class)
public abstract class ReplayResponse extends JsonSerializable {
  /** */
  @JsonProperty("status_code")
  public abstract Optional<Integer> statusCode();

  /** */
  @JsonProperty("headers")
  public abstract Optional<Map<String, String>> headers();

  /** */
  @JsonProperty("body_segments")
  public abstract Optional<List<Map<String, Object>>> bodySegments();

  /** */
  @JsonProperty("sdk_response_segments")
  public abstract Optional<List<Map<String, Object>>> sdkResponseSegments();

  /** Instantiates a builder for ReplayResponse. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ReplayResponse.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ReplayResponse. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ReplayResponse.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ReplayResponse.Builder();
    }

    /**
     * Setter for statusCode.
     *
     * <p>statusCode:
     */
    @JsonProperty("status_code")
    public abstract Builder statusCode(Integer statusCode);

    /**
     * Setter for headers.
     *
     * <p>headers:
     */
    @JsonProperty("headers")
    public abstract Builder headers(Map<String, String> headers);

    /**
     * Setter for bodySegments.
     *
     * <p>bodySegments:
     */
    @JsonProperty("body_segments")
    public abstract Builder bodySegments(List<Map<String, Object>> bodySegments);

    /**
     * Setter for bodySegments.
     *
     * <p>bodySegments:
     */
    public Builder bodySegments(Map<String, Object>... bodySegments) {
      return bodySegments(Arrays.asList(bodySegments));
    }

    /**
     * Setter for sdkResponseSegments.
     *
     * <p>sdkResponseSegments:
     */
    @JsonProperty("sdk_response_segments")
    public abstract Builder sdkResponseSegments(List<Map<String, Object>> sdkResponseSegments);

    /**
     * Setter for sdkResponseSegments.
     *
     * <p>sdkResponseSegments:
     */
    public Builder sdkResponseSegments(Map<String, Object>... sdkResponseSegments) {
      return sdkResponseSegments(Arrays.asList(sdkResponseSegments));
    }

    public abstract ReplayResponse build();
  }

  /** Deserializes a JSON string to a ReplayResponse object. */
  @ExcludeFromGeneratedCoverageReport
  public static ReplayResponse fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ReplayResponse.class);
  }
}
