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

package com.google.genai.types.interactions.streaming;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import com.google.genai.types.ExcludeFromGeneratedCoverageReport;
import java.util.Optional;

/**
 * Represents an error that occurred during streaming.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = StreamError.Builder.class)
public abstract class StreamError extends JsonSerializable {

  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_StreamError.Builder();
  }

  public abstract Builder toBuilder();

  /** Returns the error code. */
  @JsonProperty("code")
  public abstract Optional<String> code();

  /** Returns the error message. */
  @JsonProperty("message")
  public abstract Optional<String> message();

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return new AutoValue_StreamError.Builder();
    }

    @JsonProperty("code")
    public abstract Builder code(String code);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder code(Optional<String> code);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCode() {
      return code(Optional.empty());
    }

    @JsonProperty("message")
    public abstract Builder message(String message);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder message(Optional<String> message);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMessage() {
      return message(Optional.empty());
    }

    public abstract StreamError build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static StreamError fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, StreamError.class);
  }
}
