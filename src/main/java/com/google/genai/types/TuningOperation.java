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
import java.util.Map;
import java.util.Optional;

/** A long-running operation. */
@AutoValue
@JsonDeserialize(builder = TuningOperation.Builder.class)
public abstract class TuningOperation extends JsonSerializable {
  /** Used to retain the full HTTP response. */
  @JsonProperty("sdkHttpResponse")
  public abstract Optional<HttpResponse> sdkHttpResponse();

  /**
   * The server-assigned name, which is only unique within the same service that originally returns
   * it. If you use the default HTTP mapping, the `name` should be a resource name ending with
   * `operations/{unique_id}`.
   */
  @JsonProperty("name")
  public abstract Optional<String> name();

  /**
   * Service-specific metadata associated with the operation. It typically contains progress
   * information and common metadata such as create time. Some services might not provide such
   * metadata. Any method that returns a long-running operation should document the metadata type,
   * if any.
   */
  @JsonProperty("metadata")
  public abstract Optional<Map<String, Object>> metadata();

  /**
   * If the value is `false`, it means the operation is still in progress. If `true`, the operation
   * is completed, and either `error` or `response` is available.
   */
  @JsonProperty("done")
  public abstract Optional<Boolean> done();

  /** The error result of the operation in case of failure or cancellation. */
  @JsonProperty("error")
  public abstract Optional<Map<String, Object>> error();

  /** Instantiates a builder for TuningOperation. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_TuningOperation.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for TuningOperation. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `TuningOperation.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_TuningOperation.Builder();
    }

    /**
     * Setter for sdkHttpResponse.
     *
     * <p>sdkHttpResponse: Used to retain the full HTTP response.
     */
    @JsonProperty("sdkHttpResponse")
    public abstract Builder sdkHttpResponse(HttpResponse sdkHttpResponse);

    /**
     * Setter for sdkHttpResponse builder.
     *
     * <p>sdkHttpResponse: Used to retain the full HTTP response.
     */
    @CanIgnoreReturnValue
    public Builder sdkHttpResponse(HttpResponse.Builder sdkHttpResponseBuilder) {
      return sdkHttpResponse(sdkHttpResponseBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder sdkHttpResponse(Optional<HttpResponse> sdkHttpResponse);

    /** Clears the value of sdkHttpResponse field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSdkHttpResponse() {
      return sdkHttpResponse(Optional.empty());
    }

    /**
     * Setter for name.
     *
     * <p>name: The server-assigned name, which is only unique within the same service that
     * originally returns it. If you use the default HTTP mapping, the `name` should be a resource
     * name ending with `operations/{unique_id}`.
     */
    @JsonProperty("name")
    public abstract Builder name(String name);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder name(Optional<String> name);

    /** Clears the value of name field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearName() {
      return name(Optional.empty());
    }

    /**
     * Setter for metadata.
     *
     * <p>metadata: Service-specific metadata associated with the operation. It typically contains
     * progress information and common metadata such as create time. Some services might not provide
     * such metadata. Any method that returns a long-running operation should document the metadata
     * type, if any.
     */
    @JsonProperty("metadata")
    public abstract Builder metadata(Map<String, Object> metadata);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder metadata(Optional<Map<String, Object>> metadata);

    /** Clears the value of metadata field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMetadata() {
      return metadata(Optional.empty());
    }

    /**
     * Setter for done.
     *
     * <p>done: If the value is `false`, it means the operation is still in progress. If `true`, the
     * operation is completed, and either `error` or `response` is available.
     */
    @JsonProperty("done")
    public abstract Builder done(boolean done);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder done(Optional<Boolean> done);

    /** Clears the value of done field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDone() {
      return done(Optional.empty());
    }

    /**
     * Setter for error.
     *
     * <p>error: The error result of the operation in case of failure or cancellation.
     */
    @JsonProperty("error")
    public abstract Builder error(Map<String, Object> error);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder error(Optional<Map<String, Object>> error);

    /** Clears the value of error field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearError() {
      return error(Optional.empty());
    }

    public abstract TuningOperation build();
  }

  /** Deserializes a JSON string to a TuningOperation object. */
  @ExcludeFromGeneratedCoverageReport
  public static TuningOperation fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, TuningOperation.class);
  }
}
