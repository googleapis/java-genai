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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import com.google.genai.types.ExcludeFromGeneratedCoverageReport;
import com.google.genai.types.interactions.Interaction;
import java.util.Optional;

/**
 * Event for interaction lifecycle updates.
 *
 * <p>This event is emitted when a streaming interaction starts or completes. The {@code eventType}
 * field indicates whether this is a start or complete event:
 *
 * <ul>
 *   <li>{@code "interaction.start"} - Interaction has begun, status is typically IN_PROGRESS
 *   <li>{@code "interaction.complete"} - Interaction has finished, contains final state with all
 *       outputs
 * </ul>
 *
 * <p>Use {@link #isStart()} and {@link #isComplete()} helper methods to check the event type.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = InteractionEvent.Builder.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
public abstract class InteractionEvent extends JsonSerializable
    implements InteractionSseEvent {

  /** Event type value for interaction start. */
  public static final String EVENT_TYPE_START = "interaction.start";

  /** Event type value for interaction complete. */
  public static final String EVENT_TYPE_COMPLETE = "interaction.complete";

  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_InteractionEvent.Builder();
  }

  public abstract Builder toBuilder();

  /**
   * Returns the event type.
   *
   * <p>Possible values: {@code "interaction.start"} or {@code "interaction.complete"}.
   */
  @JsonProperty("event_type")
  public abstract Optional<String> eventType();

  /** Returns the event ID for stream resumption. */
  @Override
  @JsonProperty("event_id")
  public abstract Optional<String> eventId();

  /** Returns the interaction object. */
  @JsonProperty("interaction")
  public abstract Optional<Interaction> interaction();

  /**
   * Returns {@code true} if this is an interaction start event.
   *
   * <p>The interaction start event is emitted when a streaming interaction begins. At this point,
   * the interaction status is typically IN_PROGRESS.
   */
  @JsonIgnore
  public boolean isStart() {
    return EVENT_TYPE_START.equals(eventType().orElse(null));
  }

  /**
   * Returns {@code true} if this is an interaction complete event.
   *
   * <p>The interaction complete event is emitted when a streaming interaction finishes. The
   * interaction contains the final state with all outputs.
   */
  @JsonIgnore
  public boolean isComplete() {
    return EVENT_TYPE_COMPLETE.equals(eventType().orElse(null));
  }

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return new AutoValue_InteractionEvent.Builder();
    }

    @JsonProperty("event_type")
    public abstract Builder eventType(String eventType);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder eventType(Optional<String> eventType);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEventType() {
      return eventType(Optional.empty());
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

    @JsonProperty("interaction")
    public abstract Builder interaction(Interaction interaction);

    @CanIgnoreReturnValue
    public Builder interaction(Interaction.Builder interactionBuilder) {
      return interaction(interactionBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder interaction(Optional<Interaction> interaction);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearInteraction() {
      return interaction(Optional.empty());
    }

    public abstract InteractionEvent build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static InteractionEvent fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, InteractionEvent.class);
  }
}
