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
import com.google.genai.types.interactions.InteractionStatus;
import java.util.Optional;

/**
 * Event indicating that the interaction status has changed.
 *
 * <p>This event is emitted when the interaction transitions between states (e.g., from IN_PROGRESS
 * to REQUIRES_ACTION).
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = InteractionStatusUpdate.Builder.class)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "event_type")
@JsonTypeName("interaction.status_update")
public abstract class InteractionStatusUpdate extends JsonSerializable
    implements InteractionSseEvent {

  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_InteractionStatusUpdate.Builder();
  }

  public abstract Builder toBuilder();

  /** Returns the event ID for stream resumption. */
  @Override
  @JsonProperty("event_id")
  public abstract Optional<String> eventId();

  /** Returns the ID of the interaction. */
  @JsonProperty("interaction_id")
  public abstract Optional<String> interactionId();

  /** Returns the new status of the interaction. */
  @JsonProperty("status")
  public abstract Optional<InteractionStatus> status();

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return new AutoValue_InteractionStatusUpdate.Builder();
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

    @JsonProperty("interaction_id")
    public abstract Builder interactionId(String interactionId);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder interactionId(Optional<String> interactionId);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearInteractionId() {
      return interactionId(Optional.empty());
    }

    @JsonProperty("status")
    public abstract Builder status(InteractionStatus status);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder status(Optional<InteractionStatus> status);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearStatus() {
      return status(Optional.empty());
    }

    public abstract InteractionStatusUpdate build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static InteractionStatusUpdate fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, InteractionStatusUpdate.class);
  }
}
