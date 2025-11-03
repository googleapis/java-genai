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

/** Status of a File that uses a common error model. */
@AutoValue
@JsonDeserialize(builder = FileStatus.Builder.class)
public abstract class FileStatus extends JsonSerializable {
  /**
   * A list of messages that carry the error details. There is a common set of message types for
   * APIs to use.
   */
  @JsonProperty("details")
  public abstract Optional<List<Map<String, Object>>> details();

  /**
   * A list of messages that carry the error details. There is a common set of message types for
   * APIs to use.
   */
  @JsonProperty("message")
  public abstract Optional<String> message();

  /** The status code. 0 for OK, 1 for CANCELLED */
  @JsonProperty("code")
  public abstract Optional<Integer> code();

  /** Instantiates a builder for FileStatus. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_FileStatus.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for FileStatus. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `FileStatus.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_FileStatus.Builder();
    }

    /**
     * Setter for details.
     *
     * <p>details: A list of messages that carry the error details. There is a common set of message
     * types for APIs to use.
     */
    @JsonProperty("details")
    public abstract Builder details(List<Map<String, Object>> details);

    /**
     * Setter for details.
     *
     * <p>details: A list of messages that carry the error details. There is a common set of message
     * types for APIs to use.
     */
    @CanIgnoreReturnValue
    public Builder details(Map<String, Object>... details) {
      return details(Arrays.asList(details));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder details(Optional<List<Map<String, Object>>> details);

    /** Clears the value of details field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDetails() {
      return details(Optional.empty());
    }

    /**
     * Setter for message.
     *
     * <p>message: A list of messages that carry the error details. There is a common set of message
     * types for APIs to use.
     */
    @JsonProperty("message")
    public abstract Builder message(String message);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder message(Optional<String> message);

    /** Clears the value of message field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMessage() {
      return message(Optional.empty());
    }

    /**
     * Setter for code.
     *
     * <p>code: The status code. 0 for OK, 1 for CANCELLED
     */
    @JsonProperty("code")
    public abstract Builder code(Integer code);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder code(Optional<Integer> code);

    /** Clears the value of code field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCode() {
      return code(Optional.empty());
    }

    public abstract FileStatus build();
  }

  /** Deserializes a JSON string to a FileStatus object. */
  @ExcludeFromGeneratedCoverageReport
  public static FileStatus fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, FileStatus.class);
  }
}
