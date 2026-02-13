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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.genai.JsonSerializable;
import com.google.genai.types.interactions.Interaction;
import com.google.genai.types.interactions.InteractionStatus;
import com.google.genai.types.interactions.content.TextContent;
import com.google.genai.types.interactions.streaming.delta.TextDelta;
import org.junit.jupiter.api.Test;

/**
 * Tests for streaming event types in the Interactions API.
 *
 * <p>Tests the following event types:
 * <ul>
 *   <li>ContentDelta - Incremental content updates</li>
 *   <li>ContentStart - Content item start events</li>
 *   <li>ContentStop - Content item completion events</li>
 *   <li>ErrorEvent - Error events with StreamError details</li>
 *   <li>InteractionEvent - Start/complete lifecycle events</li>
 *   <li>InteractionStatusUpdate - Status change events</li>
 *   <li>StreamError - Error code and message</li>
 *   <li>InteractionSseEvent - Polymorphic deserialization</li>
 * </ul>
 *
 * <p>Note: Methods annotated with @ExcludeFromGeneratedCoverageReport are not directly tested.
 */
public class StreamingEventsTest {

  // ========== ContentDelta Tests ==========

  @Test
  public void testContentDeltaFields() {
    // Arrange & Act
    ContentDelta delta = ContentDelta.builder()
        .eventId("evt-123")
        .index(0)
        .delta(TextDelta.builder().text("Hello").build())
        .build();

    // Assert
    assertTrue(delta.eventId().isPresent());
    assertEquals("evt-123", delta.eventId().get());
    assertTrue(delta.index().isPresent());
    assertEquals(0, delta.index().get());
    assertTrue(delta.delta().isPresent());
  }

  @Test
  public void testContentDeltaJsonSerialization() {
    // Arrange
    ContentDelta delta = ContentDelta.builder()
        .eventId("evt-456")
        .index(1)
        .build();

    // Act
    String json = delta.toJson();

    // Assert
    assertTrue(json.contains("\"event_id\":\"evt-456\""));
    assertTrue(json.contains("\"index\":1"));
  }

  @Test
  public void testContentDeltaToBuilder() {
    // Arrange
    ContentDelta original = ContentDelta.builder()
        .eventId("evt-original")
        .index(3)
        .build();

    // Act
    ContentDelta modified = original.toBuilder()
        .index(7)
        .build();

    // Assert
    assertEquals("evt-original", modified.eventId().get());
    assertEquals(7, modified.index().get());
  }

  // ========== ContentStart Tests ==========

  @Test
  public void testContentStartFields() {
    // Arrange & Act
    ContentStart start = ContentStart.builder()
        .eventId("start-evt-1")
        .index(0)
        .content(TextContent.builder().text("Initial").build())
        .build();

    // Assert
    assertTrue(start.eventId().isPresent());
    assertEquals("start-evt-1", start.eventId().get());
    assertTrue(start.index().isPresent());
    assertEquals(0, start.index().get());
    assertTrue(start.content().isPresent());
  }

  @Test
  public void testContentStartJsonSerialization() {
    // Arrange
    ContentStart start = ContentStart.builder()
        .eventId("start-evt-2")
        .index(1)
        .build();

    // Act
    String json = start.toJson();

    // Assert
    assertTrue(json.contains("\"event_id\":\"start-evt-2\""));
    assertTrue(json.contains("\"index\":1"));
  }

  @Test
  public void testContentStartToBuilder() {
    // Arrange
    ContentStart original = ContentStart.builder()
        .eventId("start-original")
        .index(1)
        .build();

    // Act
    ContentStart modified = original.toBuilder()
        .index(9)
        .build();

    // Assert
    assertEquals("start-original", modified.eventId().get());
    assertEquals(9, modified.index().get());
  }

  // ========== ContentStop Tests ==========

  @Test
  public void testContentStopFields() {
    // Arrange & Act
    ContentStop stop = ContentStop.builder()
        .eventId("stop-evt-1")
        .index(0)
        .build();

    // Assert
    assertTrue(stop.eventId().isPresent());
    assertEquals("stop-evt-1", stop.eventId().get());
    assertTrue(stop.index().isPresent());
    assertEquals(0, stop.index().get());
  }

  @Test
  public void testContentStopJsonSerialization() {
    // Arrange
    ContentStop stop = ContentStop.builder()
        .eventId("stop-evt-2")
        .index(3)
        .build();

    // Act
    String json = stop.toJson();

    // Assert
    assertTrue(json.contains("\"event_id\":\"stop-evt-2\""));
    assertTrue(json.contains("\"index\":3"));
  }

  @Test
  public void testContentStopToBuilder() {
    // Arrange
    ContentStop original = ContentStop.builder()
        .eventId("stop-original")
        .index(1)
        .build();

    // Act
    ContentStop modified = original.toBuilder()
        .index(11)
        .build();

    // Assert
    assertEquals("stop-original", modified.eventId().get());
    assertEquals(11, modified.index().get());
  }

  // ========== StreamError Tests ==========

  @Test
  public void testStreamErrorFields() {
    // Arrange & Act
    StreamError error = StreamError.builder()
        .code("RATE_LIMIT_EXCEEDED")
        .message("Too many requests")
        .build();

    // Assert
    assertTrue(error.code().isPresent());
    assertEquals("RATE_LIMIT_EXCEEDED", error.code().get());
    assertTrue(error.message().isPresent());
    assertEquals("Too many requests", error.message().get());
  }

  @Test
  public void testStreamErrorJsonSerialization() {
    // Arrange
    StreamError error = StreamError.builder()
        .code("INVALID_ARGUMENT")
        .message("Invalid parameter")
        .build();

    // Act
    String json = error.toJson();

    // Assert
    assertTrue(json.contains("\"code\":\"INVALID_ARGUMENT\""));
    assertTrue(json.contains("\"message\":\"Invalid parameter\""));
  }

  @Test
  public void testStreamErrorToBuilder() {
    // Arrange
    StreamError original = StreamError.builder()
        .code("ORIGINAL_CODE")
        .message("Original message")
        .build();

    // Act
    StreamError modified = original.toBuilder()
        .message("Modified message")
        .build();

    // Assert
    assertEquals("ORIGINAL_CODE", modified.code().get());
    assertEquals("Modified message", modified.message().get());
  }

  // ========== ErrorEvent Tests ==========

  @Test
  public void testErrorEventFields() {
    // Arrange
    StreamError error = StreamError.builder()
        .code("TIMEOUT")
        .message("Request timed out")
        .build();

    // Act
    ErrorEvent event = ErrorEvent.builder()
        .eventId("error-evt-1")
        .error(error)
        .build();

    // Assert
    assertTrue(event.eventId().isPresent());
    assertEquals("error-evt-1", event.eventId().get());
    assertTrue(event.error().isPresent());
    assertEquals("TIMEOUT", event.error().get().code().get());
  }

  @Test
  public void testErrorEventWithBuilderConvenience() {
    // Arrange & Act
    ErrorEvent event = ErrorEvent.builder()
        .eventId("error-evt-2")
        .error(StreamError.builder()
            .code("TEST")
            .message("Test"))
        .build();

    // Assert
    assertTrue(event.error().isPresent());
    assertEquals("TEST", event.error().get().code().get());
  }

  @Test
  public void testErrorEventJsonSerialization() {
    // Arrange
    ErrorEvent event = ErrorEvent.builder()
        .eventId("error-evt-3")
        .error(StreamError.builder()
            .code("SERIALIZATION_TEST")
            .message("Test serialization")
            .build())
        .build();

    // Act
    String json = event.toJson();

    // Assert
    assertTrue(json.contains("\"event_id\":\"error-evt-3\""));
    assertTrue(json.contains("\"code\":\"SERIALIZATION_TEST\""));
  }

  // ========== InteractionEvent Tests ==========

  @Test
  public void testInteractionEventStartType() {
    // Arrange & Act
    InteractionEvent event = InteractionEvent.builder()
        .eventType(InteractionEvent.EVENT_TYPE_START)
        .eventId("int-evt-start")
        .build();

    // Assert
    assertTrue(event.eventType().isPresent());
    assertEquals("interaction.start", event.eventType().get());
    assertTrue(event.isStart());
    assertFalse(event.isComplete());
  }

  @Test
  public void testInteractionEventCompleteType() {
    // Arrange & Act
    InteractionEvent event = InteractionEvent.builder()
        .eventType(InteractionEvent.EVENT_TYPE_COMPLETE)
        .eventId("int-evt-complete")
        .build();

    // Assert
    assertTrue(event.eventType().isPresent());
    assertEquals("interaction.complete", event.eventType().get());
    assertFalse(event.isStart());
    assertTrue(event.isComplete());
  }

  @Test
  public void testInteractionEventWithInteraction() {
    // Arrange
    Interaction interaction = Interaction.builder()
        .id("interaction-123")
        .status(new InteractionStatus(InteractionStatus.Known.COMPLETED))
        .build();

    // Act
    InteractionEvent event = InteractionEvent.builder()
        .eventType(InteractionEvent.EVENT_TYPE_COMPLETE)
        .eventId("int-evt-1")
        .interaction(interaction)
        .build();

    // Assert
    assertTrue(event.interaction().isPresent());
    assertEquals("interaction-123", event.interaction().get().id());
  }

  @Test
  public void testInteractionEventWithInteractionBuilder() {
    // Act
    InteractionEvent event = InteractionEvent.builder()
        .eventType(InteractionEvent.EVENT_TYPE_START)
        .eventId("int-evt-2")
        .interaction(Interaction.builder()
            .id("interaction-456")
            .status(new InteractionStatus(InteractionStatus.Known.IN_PROGRESS)))
        .build();

    // Assert
    assertTrue(event.interaction().isPresent());
    assertEquals("interaction-456", event.interaction().get().id());
  }

  @Test
  public void testInteractionEventJsonSerialization() {
    // Arrange
    InteractionEvent event = InteractionEvent.builder()
        .eventType(InteractionEvent.EVENT_TYPE_START)
        .eventId("int-evt-ser")
        .build();

    // Act
    String json = event.toJson();

    // Assert
    assertTrue(json.contains("\"event_type\":\"interaction.start\""));
    assertTrue(json.contains("\"event_id\":\"int-evt-ser\""));
  }

  @Test
  public void testInteractionEventIsStartAndIsCompleteWithNoEventType() {
    // Arrange & Act
    InteractionEvent event = InteractionEvent.builder().build();

    // Assert
    assertFalse(event.isStart());
    assertFalse(event.isComplete());
  }

  @Test
  public void testInteractionEventConstants() {
    // Assert
    assertEquals("interaction.start", InteractionEvent.EVENT_TYPE_START);
    assertEquals("interaction.complete", InteractionEvent.EVENT_TYPE_COMPLETE);
  }

  // ========== InteractionStatusUpdate Tests ==========

  @Test
  public void testInteractionStatusUpdateFields() {
    // Arrange & Act
    InteractionStatusUpdate update = InteractionStatusUpdate.builder()
        .eventId("status-evt-1")
        .interactionId("interaction-789")
        .status(new InteractionStatus(InteractionStatus.Known.IN_PROGRESS))
        .build();

    // Assert
    assertTrue(update.eventId().isPresent());
    assertEquals("status-evt-1", update.eventId().get());
    assertTrue(update.interactionId().isPresent());
    assertEquals("interaction-789", update.interactionId().get());
    assertTrue(update.status().isPresent());
    assertEquals(new InteractionStatus(InteractionStatus.Known.IN_PROGRESS), update.status().get());
  }

  @Test
  public void testInteractionStatusUpdateAllStatuses() {
    // Test IN_PROGRESS
    InteractionStatusUpdate inProgress = InteractionStatusUpdate.builder()
        .status(new InteractionStatus(InteractionStatus.Known.IN_PROGRESS))
        .build();
    assertEquals(new InteractionStatus(InteractionStatus.Known.IN_PROGRESS), inProgress.status().get());

    // Test REQUIRES_ACTION
    InteractionStatusUpdate requiresAction = InteractionStatusUpdate.builder()
        .status(new InteractionStatus(InteractionStatus.Known.REQUIRES_ACTION))
        .build();
    assertEquals(new InteractionStatus(InteractionStatus.Known.REQUIRES_ACTION), requiresAction.status().get());

    // Test COMPLETED
    InteractionStatusUpdate completed = InteractionStatusUpdate.builder()
        .status(new InteractionStatus(InteractionStatus.Known.COMPLETED))
        .build();
    assertEquals(new InteractionStatus(InteractionStatus.Known.COMPLETED), completed.status().get());

    // Test FAILED
    InteractionStatusUpdate failed = InteractionStatusUpdate.builder()
        .status(new InteractionStatus(InteractionStatus.Known.FAILED))
        .build();
    assertEquals(new InteractionStatus(InteractionStatus.Known.FAILED), failed.status().get());

    // Test CANCELLED
    InteractionStatusUpdate cancelled = InteractionStatusUpdate.builder()
        .status(new InteractionStatus(InteractionStatus.Known.CANCELLED))
        .build();
    assertEquals(new InteractionStatus(InteractionStatus.Known.CANCELLED), cancelled.status().get());
  }

  @Test
  public void testInteractionStatusUpdateJsonSerialization() {
    // Arrange
    InteractionStatusUpdate update = InteractionStatusUpdate.builder()
        .eventId("status-evt-2")
        .interactionId("int-123")
        .status(new InteractionStatus(InteractionStatus.Known.COMPLETED))
        .build();

    // Act
    String json = update.toJson();

    // Assert
    assertTrue(json.contains("\"event_id\":\"status-evt-2\""));
    assertTrue(json.contains("\"interaction_id\":\"int-123\""));
    assertTrue(json.contains("\"status\":\"completed\""));
  }

  @Test
  public void testInteractionStatusUpdateToBuilder() {
    // Arrange
    InteractionStatusUpdate original = InteractionStatusUpdate.builder()
        .eventId("original-evt")
        .interactionId("original-int")
        .status(new InteractionStatus(InteractionStatus.Known.IN_PROGRESS))
        .build();

    // Act
    InteractionStatusUpdate modified = original.toBuilder()
        .status(new InteractionStatus(InteractionStatus.Known.COMPLETED))
        .build();

    // Assert
    assertEquals("original-evt", modified.eventId().get());
    assertEquals("original-int", modified.interactionId().get());
    assertEquals(new InteractionStatus(InteractionStatus.Known.COMPLETED), modified.status().get());
  }

  // ========== InteractionSseEvent Polymorphic Deserialization Tests ==========

  @Test
  public void testPolymorphicDeserializationContentDelta() {
    // Arrange
    String json = "{\"event_type\":\"content.delta\",\"event_id\":\"poly-delta\",\"index\":0}";
    JsonNode jsonNode = JsonSerializable.stringToJsonNode(json);

    // Act
    InteractionSseEvent event = JsonSerializable.fromJsonNodePolymorphic(jsonNode, InteractionSseEvent.class);

    // Assert
    assertNotNull(event);
    assertTrue(event instanceof ContentDelta);
    assertEquals("poly-delta", event.eventId().get());
  }

  @Test
  public void testPolymorphicDeserializationContentStart() {
    // Arrange
    String json = "{\"event_type\":\"content.start\",\"event_id\":\"poly-start\",\"index\":0}";
    JsonNode jsonNode = JsonSerializable.stringToJsonNode(json);

    // Act
    InteractionSseEvent event = JsonSerializable.fromJsonNodePolymorphic(jsonNode, InteractionSseEvent.class);

    // Assert
    assertNotNull(event);
    assertTrue(event instanceof ContentStart);
    assertEquals("poly-start", event.eventId().get());
  }

  @Test
  public void testPolymorphicDeserializationContentStop() {
    // Arrange
    String json = "{\"event_type\":\"content.stop\",\"event_id\":\"poly-stop\",\"index\":0}";
    JsonNode jsonNode = JsonSerializable.stringToJsonNode(json);

    // Act
    InteractionSseEvent event = JsonSerializable.fromJsonNodePolymorphic(jsonNode, InteractionSseEvent.class);

    // Assert
    assertNotNull(event);
    assertTrue(event instanceof ContentStop);
    assertEquals("poly-stop", event.eventId().get());
  }

  @Test
  public void testPolymorphicDeserializationError() {
    // Arrange
    String json = "{\"event_type\":\"error\",\"event_id\":\"poly-error\",\"error\":{\"code\":\"TEST\",\"message\":\"test\"}}";
    JsonNode jsonNode = JsonSerializable.stringToJsonNode(json);

    // Act
    InteractionSseEvent event = JsonSerializable.fromJsonNodePolymorphic(jsonNode, InteractionSseEvent.class);

    // Assert
    assertNotNull(event);
    assertTrue(event instanceof ErrorEvent);
    assertEquals("poly-error", event.eventId().get());
  }

  @Test
  public void testPolymorphicDeserializationInteractionStart() {
    // Arrange
    String json = "{\"event_type\":\"interaction.start\",\"event_id\":\"poly-int-start\"}";
    JsonNode jsonNode = JsonSerializable.stringToJsonNode(json);

    // Act
    InteractionSseEvent event = JsonSerializable.fromJsonNodePolymorphic(jsonNode, InteractionSseEvent.class);

    // Assert
    assertNotNull(event);
    assertTrue(event instanceof InteractionEvent);
    InteractionEvent intEvent = (InteractionEvent) event;
    assertTrue(intEvent.isStart());
  }

  @Test
  public void testPolymorphicDeserializationInteractionComplete() {
    // Arrange
    String json = "{\"event_type\":\"interaction.complete\",\"event_id\":\"poly-int-complete\"}";
    JsonNode jsonNode = JsonSerializable.stringToJsonNode(json);

    // Act
    InteractionSseEvent event = JsonSerializable.fromJsonNodePolymorphic(jsonNode, InteractionSseEvent.class);

    // Assert
    assertNotNull(event);
    assertTrue(event instanceof InteractionEvent);
    InteractionEvent intEvent = (InteractionEvent) event;
    assertTrue(intEvent.isComplete());
  }

  @Test
  public void testPolymorphicDeserializationStatusUpdate() {
    // Arrange
    String json = "{\"event_type\":\"interaction.status_update\",\"event_id\":\"poly-status\",\"interaction_id\":\"int-1\",\"status\":\"in_progress\"}";
    JsonNode jsonNode = JsonSerializable.stringToJsonNode(json);

    // Act
    InteractionSseEvent event = JsonSerializable.fromJsonNodePolymorphic(jsonNode, InteractionSseEvent.class);

    // Assert
    assertNotNull(event);
    assertTrue(event instanceof InteractionStatusUpdate);
    assertEquals("poly-status", event.eventId().get());
  }

  // ========== All Event Types Implement Interface ==========

  @Test
  public void testAllEventTypesImplementInteractionSseEvent() {
    // Verify all event types implement the interface
    InteractionSseEvent contentDelta = ContentDelta.builder().eventId("test").build();
    InteractionSseEvent contentStart = ContentStart.builder().eventId("test").build();
    InteractionSseEvent contentStop = ContentStop.builder().eventId("test").build();
    InteractionSseEvent errorEvent = ErrorEvent.builder().eventId("test").build();
    InteractionSseEvent interactionEvent = InteractionEvent.builder().eventId("test").build();
    InteractionSseEvent statusUpdate = InteractionStatusUpdate.builder().eventId("test").build();

    assertNotNull(contentDelta);
    assertNotNull(contentStart);
    assertNotNull(contentStop);
    assertNotNull(errorEvent);
    assertNotNull(interactionEvent);
    assertNotNull(statusUpdate);

    // Verify eventId() is accessible through the interface
    assertEquals("test", contentDelta.eventId().get());
    assertEquals("test", contentStart.eventId().get());
    assertEquals("test", contentStop.eventId().get());
    assertEquals("test", errorEvent.eventId().get());
    assertEquals("test", interactionEvent.eventId().get());
    assertEquals("test", statusUpdate.eventId().get());
  }
}
