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

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Optional;

/**
 * Base interface for interaction SSE (Server-Sent Events) event types.
 *
 * <p>Events are emitted during a streaming interaction to provide real-time updates about the
 * interaction's progress. Each event type represents a specific phase or update in the streaming
 * lifecycle.
 *
 * <p>Event sequence for a typical streaming interaction:
 *
 * <ol>
 *   <li>{@link InteractionEvent} with {@code event_type="interaction.start"} - Interaction has
 *       begun
 *   <li>{@link ContentStart} - A new content item is starting (one per output)
 *   <li>{@link ContentDelta} - Incremental content updates (multiple per content)
 *   <li>{@link ContentStop} - Content item has completed
 *   <li>{@link InteractionEvent} with {@code event_type="interaction.complete"} - Interaction has
 *       finished
 * </ol>
 *
 * <p>Additionally:
 *
 * <ul>
 *   <li>{@link InteractionStatusUpdate} - Status changes during the interaction
 *   <li>{@link ErrorEvent} - Error occurred during streaming
 * </ul>
 *
 * <p>This follows Jackson best practices for polymorphism:
 *
 * <ul>
 *   <li>{@code @JsonTypeInfo} on the base type with property "event_type"
 *   <li>Explicit {@code @JsonSubTypes} registration
 *   <li>Jackson handles type discrimination via annotations
 * </ul>
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "event_type",
    visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = InteractionEvent.class, name = "interaction.start"),
  @JsonSubTypes.Type(value = InteractionEvent.class, name = "interaction.complete"),
  @JsonSubTypes.Type(value = InteractionStatusUpdate.class, name = "interaction.status_update"),
  @JsonSubTypes.Type(value = ContentStart.class, name = "content.start"),
  @JsonSubTypes.Type(value = ContentDelta.class, name = "content.delta"),
  @JsonSubTypes.Type(value = ContentStop.class, name = "content.stop"),
  @JsonSubTypes.Type(value = ErrorEvent.class, name = "error")
})
public interface InteractionSseEvent {
  /**
   * Returns the unique identifier for this event.
   *
   * <p>Can be used for stream resumption via {@code lastEventId} in
   * {@link com.google.genai.types.interactions.GetInteractionConfig}.
   */
  Optional<String> eventId();
}
