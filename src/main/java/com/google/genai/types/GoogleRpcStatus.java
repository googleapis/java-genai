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

/**
 * The `Status` type defines a logical error model that is suitable for different programming
 * environments, including REST APIs and RPC APIs. It is used by [gRPC](https://github.com/grpc).
 * Each `Status` message contains three pieces of data: error code, error message, and error
 * details. You can find out more about this error model and how to work with it in the [API Design
 * Guide](https://cloud.google.com/apis/design/errors). This data type is not supported in Gemini
 * API.
 */
@AutoValue
@JsonDeserialize(builder = GoogleRpcStatus.Builder.class)
public abstract class GoogleRpcStatus extends JsonSerializable {
  /** The status code, which should be an enum value of google.rpc.Code. */
  @JsonProperty("code")
  public abstract Optional<Integer> code();

  /**
   * A list of messages that carry the error details. There is a common set of message types for
   * APIs to use.
   */
  @JsonProperty("details")
  public abstract Optional<List<Map<String, Object>>> details();

  /**
   * A developer-facing error message, which should be in English. Any user-facing error message
   * should be localized and sent in the google.rpc.Status.details field, or localized by the
   * client.
   */
  @JsonProperty("message")
  public abstract Optional<String> message();

  /** Instantiates a builder for GoogleRpcStatus. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GoogleRpcStatus.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GoogleRpcStatus. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `GoogleRpcStatus.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GoogleRpcStatus.Builder();
    }

    /**
     * Setter for code.
     *
     * <p>code: The status code, which should be an enum value of google.rpc.Code.
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
     * <p>message: A developer-facing error message, which should be in English. Any user-facing
     * error message should be localized and sent in the google.rpc.Status.details field, or
     * localized by the client.
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

    public abstract GoogleRpcStatus build();
  }

  /** Deserializes a JSON string to a GoogleRpcStatus object. */
  @ExcludeFromGeneratedCoverageReport
  public static GoogleRpcStatus fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GoogleRpcStatus.class);
  }
}
