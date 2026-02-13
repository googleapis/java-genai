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
 * Event indicating that a content item has finished streaming.
 *
 * <p>This event is emitted when all deltas for a particular content item have been sent. It
 * signals that the content at the given index is complete.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = ContentStop.Builder.class)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "event_type")
@JsonTypeName("content.stop")
public abstract class ContentStop extends JsonSerializable
    implements InteractionSseEvent {

  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ContentStop.Builder();
  }

  public abstract Builder toBuilder();

  /** Returns the event ID for stream resumption. */
  @Override
  @JsonProperty("event_id")
  public abstract Optional<String> eventId();

  /** Returns the index of the content item that has completed. */
  @JsonProperty("index")
  public abstract Optional<Integer> index();

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ContentStop.Builder();
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

    @JsonProperty("index")
    public abstract Builder index(Integer index);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder index(Optional<Integer> index);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearIndex() {
      return index(Optional.empty());
    }

    public abstract ContentStop build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static ContentStop fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ContentStop.class);
  }
}
