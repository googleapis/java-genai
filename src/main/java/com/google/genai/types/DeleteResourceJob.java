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

/** The return value of delete operation. */
@AutoValue
@JsonDeserialize(builder = DeleteResourceJob.Builder.class)
public abstract class DeleteResourceJob extends JsonSerializable {
  /** Used to retain the full HTTP response. */
  @JsonProperty("sdkHttpResponse")
  public abstract Optional<HttpResponse> sdkHttpResponse();

  /** */
  @JsonProperty("name")
  public abstract Optional<String> name();

  /** */
  @JsonProperty("done")
  public abstract Optional<Boolean> done();

  /** */
  @JsonProperty("error")
  public abstract Optional<JobError> error();

  /** Instantiates a builder for DeleteResourceJob. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_DeleteResourceJob.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for DeleteResourceJob. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `DeleteResourceJob.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_DeleteResourceJob.Builder();
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
    public Builder sdkHttpResponse(HttpResponse.Builder sdkHttpResponseBuilder) {
      return sdkHttpResponse(sdkHttpResponseBuilder.build());
    }

    /**
     * Setter for name.
     *
     * <p>name:
     */
    @JsonProperty("name")
    public abstract Builder name(String name);

    /**
     * Setter for done.
     *
     * <p>done:
     */
    @JsonProperty("done")
    public abstract Builder done(boolean done);

    /**
     * Setter for error.
     *
     * <p>error:
     */
    @JsonProperty("error")
    public abstract Builder error(JobError error);

    /**
     * Setter for error builder.
     *
     * <p>error:
     */
    public Builder error(JobError.Builder errorBuilder) {
      return error(errorBuilder.build());
    }

    public abstract DeleteResourceJob build();
  }

  /** Deserializes a JSON string to a DeleteResourceJob object. */
  @ExcludeFromGeneratedCoverageReport
  public static DeleteResourceJob fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, DeleteResourceJob.class);
  }
}
