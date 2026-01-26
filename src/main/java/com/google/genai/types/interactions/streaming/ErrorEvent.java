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
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import com.google.genai.types.ExcludeFromGeneratedCoverageReport;
import java.util.Optional;

/**
 * Event indicating that an error occurred during streaming.
 *
 * <p>This event may be emitted if something goes wrong during the streaming process. The stream
 * may or may not continue after this event depending on the error type.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = ErrorEvent.Builder.class)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "event_type")
@JsonTypeName("error")
public abstract class ErrorEvent extends JsonSerializable
    implements InteractionSseEvent {

  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ErrorEvent.Builder();
  }

  public abstract Builder toBuilder();

  /** Returns the event ID for stream resumption. */
  @Override
  @JsonProperty("event_id")
  public abstract Optional<String> eventId();

  /** Returns the error details. */
  @JsonProperty("error")
  public abstract Optional<StreamError> error();

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ErrorEvent.Builder();
    }

    @JsonProperty("event_id")
    public abstract Builder eventId(String eventId);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder eventId(Optional<String> eventId);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEventId() {
      return eventId(Optional.empty());
    }

    @JsonProperty("error")
    public abstract Builder error(StreamError error);

    @CanIgnoreReturnValue
    public Builder error(StreamError.Builder errorBuilder) {
      return error(errorBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder error(Optional<StreamError> error);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearError() {
      return error(Optional.empty());
    }

    public abstract ErrorEvent build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static ErrorEvent fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ErrorEvent.class);
  }
}
