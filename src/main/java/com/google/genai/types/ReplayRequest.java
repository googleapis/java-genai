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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** Represents a single request in a replay. */
@AutoValue
@JsonDeserialize(builder = ReplayRequest.Builder.class)
public abstract class ReplayRequest extends JsonSerializable {
  /** */
  @JsonProperty("method")
  public abstract Optional<String> method();

  /** */
  @JsonProperty("url")
  public abstract Optional<String> url();

  /** */
  @JsonProperty("headers")
  public abstract Optional<Map<String, String>> headers();

  /** */
  @JsonProperty("body_segments")
  public abstract Optional<List<Map<String, Object>>> bodySegments();

  /** Instantiates a builder for ReplayRequest. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ReplayRequest.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ReplayRequest. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ReplayRequest.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ReplayRequest.Builder();
    }

    /**
     * Setter for method.
     *
     * <p>method:
     */
    @JsonProperty("method")
    public abstract Builder method(String method);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder method(Optional<String> method);

    /** Clears the value of method field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMethod() {
      return method(Optional.empty());
    }

    /**
     * Setter for url.
     *
     * <p>url:
     */
    @JsonProperty("url")
    public abstract Builder url(String url);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder url(Optional<String> url);

    /** Clears the value of url field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearUrl() {
      return url(Optional.empty());
    }

    /**
     * Setter for headers.
     *
     * <p>headers:
     */
    @JsonProperty("headers")
    public abstract Builder headers(Map<String, String> headers);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder headers(Optional<Map<String, String>> headers);

    /** Clears the value of headers field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearHeaders() {
      return headers(Optional.empty());
    }

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
    @CanIgnoreReturnValue
    public Builder bodySegments(Map<String, Object>... bodySegments) {
      return bodySegments(Arrays.asList(bodySegments));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder bodySegments(Optional<List<Map<String, Object>>> bodySegments);

    /** Clears the value of bodySegments field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearBodySegments() {
      return bodySegments(Optional.empty());
    }

    public abstract ReplayRequest build();
  }

  /** Deserializes a JSON string to a ReplayRequest object. */
  @ExcludeFromGeneratedCoverageReport
  public static ReplayRequest fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ReplayRequest.class);
  }
}
