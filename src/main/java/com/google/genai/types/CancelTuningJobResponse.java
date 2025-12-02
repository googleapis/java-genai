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

/** Empty response for tunings.cancel method. */
@AutoValue
@JsonDeserialize(builder = CancelTuningJobResponse.Builder.class)
public abstract class CancelTuningJobResponse extends JsonSerializable {
  /** Used to retain the full HTTP response. */
  @JsonProperty("sdkHttpResponse")
  public abstract Optional<HttpResponse> sdkHttpResponse();

  /** Instantiates a builder for CancelTuningJobResponse. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_CancelTuningJobResponse.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for CancelTuningJobResponse. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `CancelTuningJobResponse.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_CancelTuningJobResponse.Builder();
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

    public abstract CancelTuningJobResponse build();
  }

  /** Deserializes a JSON string to a CancelTuningJobResponse object. */
  @ExcludeFromGeneratedCoverageReport
  public static CancelTuningJobResponse fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, CancelTuningJobResponse.class);
  }
}
