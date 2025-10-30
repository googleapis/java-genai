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
import java.util.Optional;

/** Job error. */
@AutoValue
@JsonDeserialize(builder = JobError.Builder.class)
public abstract class JobError extends JsonSerializable {
  /**
   * A list of messages that carry the error details. There is a common set of message types for
   * APIs to use.
   */
  @JsonProperty("details")
  public abstract Optional<List<String>> details();

  /** The status code. */
  @JsonProperty("code")
  public abstract Optional<Integer> code();

  /**
   * A developer-facing error message, which should be in English. Any user-facing error message
   * should be localized and sent in the `details` field.
   */
  @JsonProperty("message")
  public abstract Optional<String> message();

  /** Instantiates a builder for JobError. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_JobError.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for JobError. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `JobError.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_JobError.Builder();
    }

    /**
     * Setter for details.
     *
     * <p>details: A list of messages that carry the error details. There is a common set of message
     * types for APIs to use.
     */
    @JsonProperty("details")
    public abstract Builder details(List<String> details);

    /**
     * Setter for details.
     *
     * <p>details: A list of messages that carry the error details. There is a common set of message
     * types for APIs to use.
     */
    public Builder details(String... details) {
      return details(Arrays.asList(details));
    }

    /**
     * Setter for code.
     *
     * <p>code: The status code.
     */
    @JsonProperty("code")
    public abstract Builder code(Integer code);

    /**
     * Setter for message.
     *
     * <p>message: A developer-facing error message, which should be in English. Any user-facing
     * error message should be localized and sent in the `details` field.
     */
    @JsonProperty("message")
    public abstract Builder message(String message);

    public abstract JobError build();
  }

  /** Deserializes a JSON string to a JobError object. */
  @ExcludeFromGeneratedCoverageReport
  public static JobError fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, JobError.class);
  }
}
