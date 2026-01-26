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

package com.google.genai;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.interactions.streaming.ContentDelta;
import com.google.genai.types.interactions.streaming.ContentStart;
import com.google.genai.types.interactions.streaming.ContentStop;
import com.google.genai.types.interactions.streaming.ErrorEvent;
import com.google.genai.types.interactions.streaming.InteractionEvent;
import com.google.genai.types.interactions.streaming.InteractionSseEvent;
import com.google.genai.types.interactions.streaming.delta.TextDelta;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Unit tests for InteractionEventStream.
 *
 * <p>Tests SSE parsing, iterator behavior, resource cleanup, error handling, and event
 * deserialization.
 */
public class InteractionEventStreamTest {

  private ApiResponse mockedResponse;
  private ResponseBody mockedBody;

  @BeforeEach
  void setUp() {
    mockedResponse = mock(ApiResponse.class);
    mockedBody = mock(ResponseBody.class);
    when(mockedResponse.getBody()).thenReturn(mockedBody);
  }

  private InteractionEventStream<InteractionSseEvent> createStream(String sseData) {
    InputStream inputStream = new ByteArrayInputStream(sseData.getBytes(StandardCharsets.UTF_8));
    when(mockedBody.byteStream()).thenReturn(inputStream);
    return new InteractionEventStream<>(InteractionSseEvent.class, mockedResponse);
  }

  // ==================== SSE Parsing Tests ====================

  @Test
  public void testParseDataLine() {
    String sseData =
        "data: {\"event_type\":\"content.delta\",\"index\":0,\"delta\":{\"type\":\"text\",\"text\":\"Hello\"}}\n\n"
            + "data: [DONE]\n";

    try (InteractionEventStream<InteractionSseEvent> stream = createStream(sseData)) {
      Iterator<InteractionSseEvent> iterator = stream.iterator();
      assertTrue(iterator.hasNext());

      InteractionSseEvent event = iterator.next();
      assertInstanceOf(ContentDelta.class, event);
    }
  }

  @Test
  public void testParseEventLine() {
    // SSE with "event:" line followed by "data:" line
    String sseData =
        "event: content.delta\n"
            + "data: {\"event_type\":\"content.delta\",\"index\":0,\"delta\":{\"type\":\"text\",\"text\":\"World\"}}\n\n"
            + "data: [DONE]\n";

    try (InteractionEventStream<InteractionSseEvent> stream = createStream(sseData)) {
      Iterator<InteractionSseEvent> iterator = stream.iterator();
      assertTrue(iterator.hasNext());

      InteractionSseEvent event = iterator.next();
      assertInstanceOf(ContentDelta.class, event);
    }
  }

  @Test
  public void testParseCommentLine() {
    // SSE with comment line (starts with :)
    String sseData =
        ": this is a comment\n"
            + "data: {\"event_type\":\"content.delta\",\"index\":0,\"delta\":{\"type\":\"text\",\"text\":\"Test\"}}\n\n"
            + "data: [DONE]\n";

    try (InteractionEventStream<InteractionSseEvent> stream = createStream(sseData)) {
      Iterator<InteractionSseEvent> iterator = stream.iterator();
      assertTrue(iterator.hasNext());

      InteractionSseEvent event = iterator.next();
      assertInstanceOf(ContentDelta.class, event);
    }
  }

  @Test
  public void testParseEmptyLine() {
    // Empty lines between events
    String sseData =
        "data: {\"event_type\":\"content.delta\",\"index\":0,\"delta\":{\"type\":\"text\",\"text\":\"A\"}}\n"
            + "\n"
            + "\n"
            + "data: {\"event_type\":\"content.delta\",\"index\":0,\"delta\":{\"type\":\"text\",\"text\":\"B\"}}\n\n"
            + "data: [DONE]\n";

    try (InteractionEventStream<InteractionSseEvent> stream = createStream(sseData)) {
      List<InteractionSseEvent> events = new ArrayList<>();
      for (InteractionSseEvent event : stream) {
        events.add(event);
      }
      assertEquals(2, events.size());
    }
  }

  @Test
  public void testParseDoneSignal() {
    String sseData =
        "data: {\"event_type\":\"content.delta\",\"index\":0,\"delta\":{\"type\":\"text\",\"text\":\"End\"}}\n\n"
            + "data: [DONE]\n";

    try (InteractionEventStream<InteractionSseEvent> stream = createStream(sseData)) {
      List<InteractionSseEvent> events = new ArrayList<>();
      for (InteractionSseEvent event : stream) {
        events.add(event);
      }
      // Only one event, [DONE] terminates the stream
      assertEquals(1, events.size());
    }
  }

  @Test
  public void testParseFallbackRawJson() {
    // Direct JSON without "data:" prefix (fallback mode)
    String sseData =
        "{\"event_type\":\"content.delta\",\"index\":0,\"delta\":{\"type\":\"text\",\"text\":\"Raw\"}}\n"
            + "data: [DONE]\n";

    try (InteractionEventStream<InteractionSseEvent> stream = createStream(sseData)) {
      Iterator<InteractionSseEvent> iterator = stream.iterator();
      assertTrue(iterator.hasNext());

      InteractionSseEvent event = iterator.next();
      assertInstanceOf(ContentDelta.class, event);
    }
  }

  // ==================== Iterator Behavior Tests ====================

  @Test
  public void testHasNextWithEvents() {
    String sseData =
        "data: {\"event_type\":\"content.delta\",\"index\":0,\"delta\":{\"type\":\"text\",\"text\":\"Test\"}}\n\n"
            + "data: [DONE]\n";

    try (InteractionEventStream<InteractionSseEvent> stream = createStream(sseData)) {
      Iterator<InteractionSseEvent> iterator = stream.iterator();
      assertTrue(iterator.hasNext());
      // Calling hasNext multiple times should not advance
      assertTrue(iterator.hasNext());
      assertTrue(iterator.hasNext());

      iterator.next();
      assertFalse(iterator.hasNext());
    }
  }

  @Test
  public void testHasNextWhenEmpty() {
    String sseData = "data: [DONE]\n";

    try (InteractionEventStream<InteractionSseEvent> stream = createStream(sseData)) {
      Iterator<InteractionSseEvent> iterator = stream.iterator();
      assertFalse(iterator.hasNext());
    }
  }

  @Test
  public void testNextReturnsEvents() {
    String sseData =
        "data: {\"event_type\":\"content.start\",\"index\":0}\n\n"
            + "data: {\"event_type\":\"content.delta\",\"index\":0,\"delta\":{\"type\":\"text\",\"text\":\"Hi\"}}\n\n"
            + "data: {\"event_type\":\"content.stop\",\"index\":0}\n\n"
            + "data: [DONE]\n";

    try (InteractionEventStream<InteractionSseEvent> stream = createStream(sseData)) {
      List<InteractionSseEvent> events = new ArrayList<>();
      for (InteractionSseEvent event : stream) {
        events.add(event);
      }

      assertEquals(3, events.size());
      assertInstanceOf(ContentStart.class, events.get(0));
      assertInstanceOf(ContentDelta.class, events.get(1));
      assertInstanceOf(ContentStop.class, events.get(2));
    }
  }

  @Test
  public void testNextThrowsNoSuchElement() {
    String sseData = "data: [DONE]\n";

    try (InteractionEventStream<InteractionSseEvent> stream = createStream(sseData)) {
      Iterator<InteractionSseEvent> iterator = stream.iterator();
      assertFalse(iterator.hasNext());
      assertThrows(NoSuchElementException.class, iterator::next);
    }
  }

  @Test
  public void testConsumedFlag() {
    String sseData =
        "data: {\"event_type\":\"content.delta\",\"index\":0,\"delta\":{\"type\":\"text\",\"text\":\"Test\"}}\n\n"
            + "data: [DONE]\n";

    try (InteractionEventStream<InteractionSseEvent> stream = createStream(sseData)) {
      assertFalse(stream.isConsumed());

      Iterator<InteractionSseEvent> iterator = stream.iterator();
      iterator.next();

      assertFalse(stream.isConsumed()); // Not consumed until hasNext returns false

      assertFalse(iterator.hasNext()); // This sets consumed to true
      assertTrue(stream.isConsumed());
    }
  }

  // ==================== Resource Cleanup Tests ====================

  @Test
  public void testCloseClosesReader() throws IOException {
    String sseData =
        "data: {\"event_type\":\"content.delta\",\"index\":0,\"delta\":{\"type\":\"text\",\"text\":\"Test\"}}\n\n"
            + "data: [DONE]\n";

    InteractionEventStream<InteractionSseEvent> stream = createStream(sseData);
    stream.close();

    // Verify the response was closed
    verify(mockedResponse).close();
  }

  @Test
  public void testCloseClosesResponse() {
    String sseData = "data: [DONE]\n";

    InteractionEventStream<InteractionSseEvent> stream = createStream(sseData);
    stream.close();

    verify(mockedResponse).close();
  }

  @Test
  public void testTryWithResources() {
    String sseData =
        "data: {\"event_type\":\"content.delta\",\"index\":0,\"delta\":{\"type\":\"text\",\"text\":\"Test\"}}\n\n"
            + "data: [DONE]\n";

    try (InteractionEventStream<InteractionSseEvent> stream = createStream(sseData)) {
      for (InteractionSseEvent event : stream) {
        assertNotNull(event);
      }
    }
    // After try-with-resources, close should be called
    verify(mockedResponse).close();
  }

  // ==================== Error Handling Tests ====================

  @Test
  public void testIOExceptionDuringRead() throws IOException {
    // Create a stream that throws IOException when read
    InputStream failingStream = mock(InputStream.class);
    when(failingStream.read()).thenThrow(new IOException("Read failed"));
    when(failingStream.read(Mockito.any(byte[].class))).thenThrow(new IOException("Read failed"));
    when(failingStream.read(Mockito.any(byte[].class), Mockito.anyInt(), Mockito.anyInt()))
        .thenThrow(new IOException("Read failed"));
    when(mockedBody.byteStream()).thenReturn(failingStream);

    // The exception is thrown during stream construction (when the iterator reads the first line)
    assertThrows(
        GenAiIOException.class,
        () -> new InteractionEventStream<>(InteractionSseEvent.class, mockedResponse));
  }

  @Test
  public void testMalformedJson() {
    String sseData = "data: {not valid json}\n\ndata: [DONE]\n";

    try (InteractionEventStream<InteractionSseEvent> stream = createStream(sseData)) {
      Iterator<InteractionSseEvent> iterator = stream.iterator();
      assertTrue(iterator.hasNext());
      // Malformed JSON should throw during deserialization
      assertThrows(Exception.class, iterator::next);
    }
  }

  @Test
  public void testCloseWithIOException() throws IOException {
    // Create a reader that throws IOException on close
    String sseData = "data: [DONE]\n";
    InputStream inputStream = new ByteArrayInputStream(sseData.getBytes(StandardCharsets.UTF_8)) {
      @Override
      public void close() throws IOException {
        throw new IOException("Close failed");
      }
    };
    when(mockedBody.byteStream()).thenReturn(inputStream);

    InteractionEventStream<InteractionSseEvent> stream =
        new InteractionEventStream<>(InteractionSseEvent.class, mockedResponse);

    // close() should wrap IOException in GenAiIOException
    assertThrows(GenAiIOException.class, stream::close);

    // Response should still be closed even if reader close fails
    verify(mockedResponse).close();
  }

  @Test
  public void testStreamEndsWithEOF() {
    // Stream that ends without [DONE] signal (EOF)
    String sseData =
        "data: {\"event_type\":\"content.delta\",\"index\":0,\"delta\":{\"type\":\"text\",\"text\":\"Test\"}}\n\n";

    try (InteractionEventStream<InteractionSseEvent> stream = createStream(sseData)) {
      Iterator<InteractionSseEvent> iterator = stream.iterator();
      assertTrue(iterator.hasNext());
      assertNotNull(iterator.next());
      // After EOF, hasNext should return false
      assertFalse(iterator.hasNext());
    }
  }

  // ==================== Event Deserialization Tests ====================

  @Test
  public void testDeserializeContentDelta() {
    String sseData =
        "data: {\"event_type\":\"content.delta\",\"event_id\":\"evt-1\",\"index\":0,\"delta\":{\"type\":\"text\",\"text\":\"Hello World\"}}\n\n"
            + "data: [DONE]\n";

    try (InteractionEventStream<InteractionSseEvent> stream = createStream(sseData)) {
      Iterator<InteractionSseEvent> iterator = stream.iterator();
      InteractionSseEvent event = iterator.next();

      assertInstanceOf(ContentDelta.class, event);
      ContentDelta delta = (ContentDelta) event;

      assertTrue(delta.eventId().isPresent());
      assertEquals("evt-1", delta.eventId().get());
      assertTrue(delta.index().isPresent());
      assertEquals(0, delta.index().get());
      assertTrue(delta.delta().isPresent());
      assertInstanceOf(TextDelta.class, delta.delta().get());

      TextDelta textDelta = (TextDelta) delta.delta().get();
      assertTrue(textDelta.text().isPresent());
      assertEquals("Hello World", textDelta.text().get());
    }
  }

  @Test
  public void testDeserializeContentStart() {
    String sseData =
        "data: {\"event_type\":\"content.start\",\"event_id\":\"evt-2\",\"index\":0}\n\n"
            + "data: [DONE]\n";

    try (InteractionEventStream<InteractionSseEvent> stream = createStream(sseData)) {
      Iterator<InteractionSseEvent> iterator = stream.iterator();
      InteractionSseEvent event = iterator.next();

      assertInstanceOf(ContentStart.class, event);
      ContentStart start = (ContentStart) event;

      assertTrue(start.eventId().isPresent());
      assertEquals("evt-2", start.eventId().get());
      assertTrue(start.index().isPresent());
      assertEquals(0, start.index().get());
    }
  }

  @Test
  public void testDeserializeContentStop() {
    String sseData =
        "data: {\"event_type\":\"content.stop\",\"event_id\":\"evt-3\",\"index\":0}\n\n"
            + "data: [DONE]\n";

    try (InteractionEventStream<InteractionSseEvent> stream = createStream(sseData)) {
      Iterator<InteractionSseEvent> iterator = stream.iterator();
      InteractionSseEvent event = iterator.next();

      assertInstanceOf(ContentStop.class, event);
      ContentStop stop = (ContentStop) event;

      assertTrue(stop.eventId().isPresent());
      assertEquals("evt-3", stop.eventId().get());
      assertTrue(stop.index().isPresent());
      assertEquals(0, stop.index().get());
    }
  }

  @Test
  public void testDeserializeInteractionStart() {
    String sseData =
        "data: {\"event_type\":\"interaction.start\",\"event_id\":\"evt-4\",\"interaction\":{\"id\":\"int-123\",\"status\":\"in_progress\"}}\n\n"
            + "data: [DONE]\n";

    try (InteractionEventStream<InteractionSseEvent> stream = createStream(sseData)) {
      Iterator<InteractionSseEvent> iterator = stream.iterator();
      InteractionSseEvent event = iterator.next();

      assertInstanceOf(InteractionEvent.class, event);
      InteractionEvent interactionEvent = (InteractionEvent) event;

      assertTrue(interactionEvent.eventId().isPresent());
      assertEquals("evt-4", interactionEvent.eventId().get());
      assertTrue(interactionEvent.isStart());
      assertFalse(interactionEvent.isComplete());
      assertTrue(interactionEvent.interaction().isPresent());
      assertEquals("int-123", interactionEvent.interaction().get().id());
    }
  }

  @Test
  public void testDeserializeInteractionComplete() {
    String sseData =
        "data: {\"event_type\":\"interaction.complete\",\"event_id\":\"evt-5\",\"interaction\":{\"id\":\"int-456\",\"status\":\"completed\"}}\n\n"
            + "data: [DONE]\n";

    try (InteractionEventStream<InteractionSseEvent> stream = createStream(sseData)) {
      Iterator<InteractionSseEvent> iterator = stream.iterator();
      InteractionSseEvent event = iterator.next();

      assertInstanceOf(InteractionEvent.class, event);
      InteractionEvent interactionEvent = (InteractionEvent) event;

      assertTrue(interactionEvent.eventId().isPresent());
      assertEquals("evt-5", interactionEvent.eventId().get());
      assertTrue(interactionEvent.isComplete());
      assertFalse(interactionEvent.isStart());
      assertTrue(interactionEvent.interaction().isPresent());
      assertEquals("int-456", interactionEvent.interaction().get().id());
    }
  }

  @Test
  public void testDeserializeErrorEvent() {
    String sseData =
        "data: {\"event_type\":\"error\",\"event_id\":\"evt-6\",\"error\":{\"code\":\"internal_error\",\"message\":\"Something went wrong\"}}\n\n"
            + "data: [DONE]\n";

    try (InteractionEventStream<InteractionSseEvent> stream = createStream(sseData)) {
      Iterator<InteractionSseEvent> iterator = stream.iterator();
      InteractionSseEvent event = iterator.next();

      assertInstanceOf(ErrorEvent.class, event);
      ErrorEvent errorEvent = (ErrorEvent) event;

      assertTrue(errorEvent.eventId().isPresent());
      assertEquals("evt-6", errorEvent.eventId().get());
      assertTrue(errorEvent.error().isPresent());
    }
  }

  // ==================== Full Stream Sequence Test ====================

  @Test
  public void testFullStreamSequence() {
    // Test a complete streaming sequence
    String sseData =
        "event: interaction.start\n"
            + "data: {\"event_type\":\"interaction.start\",\"event_id\":\"e1\",\"interaction\":{\"id\":\"int-001\",\"status\":\"in_progress\"}}\n\n"
            + "event: content.start\n"
            + "data: {\"event_type\":\"content.start\",\"event_id\":\"e2\",\"index\":0}\n\n"
            + "event: content.delta\n"
            + "data: {\"event_type\":\"content.delta\",\"event_id\":\"e3\",\"index\":0,\"delta\":{\"type\":\"text\",\"text\":\"Hello \"}}\n\n"
            + "event: content.delta\n"
            + "data: {\"event_type\":\"content.delta\",\"event_id\":\"e4\",\"index\":0,\"delta\":{\"type\":\"text\",\"text\":\"World!\"}}\n\n"
            + "event: content.stop\n"
            + "data: {\"event_type\":\"content.stop\",\"event_id\":\"e5\",\"index\":0}\n\n"
            + "event: interaction.complete\n"
            + "data: {\"event_type\":\"interaction.complete\",\"event_id\":\"e6\",\"interaction\":{\"id\":\"int-001\",\"status\":\"completed\"}}\n\n"
            + "data: [DONE]\n";

    try (InteractionEventStream<InteractionSseEvent> stream = createStream(sseData)) {
      List<InteractionSseEvent> events = new ArrayList<>();
      for (InteractionSseEvent event : stream) {
        events.add(event);
      }

      assertEquals(6, events.size());

      // Verify event types in order
      assertInstanceOf(InteractionEvent.class, events.get(0));
      assertTrue(((InteractionEvent) events.get(0)).isStart());

      assertInstanceOf(ContentStart.class, events.get(1));

      assertInstanceOf(ContentDelta.class, events.get(2));
      assertInstanceOf(ContentDelta.class, events.get(3));

      assertInstanceOf(ContentStop.class, events.get(4));

      assertInstanceOf(InteractionEvent.class, events.get(5));
      assertTrue(((InteractionEvent) events.get(5)).isComplete());

      // Verify text concatenation
      StringBuilder text = new StringBuilder();
      for (InteractionSseEvent event : events) {
        if (event instanceof ContentDelta) {
          ContentDelta delta = (ContentDelta) event;
          if (delta.delta().isPresent() && delta.delta().get() instanceof TextDelta) {
            TextDelta textDelta = (TextDelta) delta.delta().get();
            if (textDelta.text().isPresent()) {
              text.append(textDelta.text().get());
            }
          }
        }
      }
      assertEquals("Hello World!", text.toString());
    }
  }

  // ==================== Multiple Content Items Test ====================

  @Test
  public void testMultipleContentItems() {
    String sseData =
        "data: {\"event_type\":\"content.start\",\"event_id\":\"e1\",\"index\":0}\n\n"
            + "data: {\"event_type\":\"content.delta\",\"event_id\":\"e2\",\"index\":0,\"delta\":{\"type\":\"text\",\"text\":\"First\"}}\n\n"
            + "data: {\"event_type\":\"content.stop\",\"event_id\":\"e3\",\"index\":0}\n\n"
            + "data: {\"event_type\":\"content.start\",\"event_id\":\"e4\",\"index\":1}\n\n"
            + "data: {\"event_type\":\"content.delta\",\"event_id\":\"e5\",\"index\":1,\"delta\":{\"type\":\"text\",\"text\":\"Second\"}}\n\n"
            + "data: {\"event_type\":\"content.stop\",\"event_id\":\"e6\",\"index\":1}\n\n"
            + "data: [DONE]\n";

    try (InteractionEventStream<InteractionSseEvent> stream = createStream(sseData)) {
      List<InteractionSseEvent> events = new ArrayList<>();
      for (InteractionSseEvent event : stream) {
        events.add(event);
      }

      assertEquals(6, events.size());

      // Verify indices
      ContentStart start1 = (ContentStart) events.get(0);
      assertEquals(0, start1.index().get());

      ContentDelta delta1 = (ContentDelta) events.get(1);
      assertEquals(0, delta1.index().get());

      ContentStart start2 = (ContentStart) events.get(3);
      assertEquals(1, start2.index().get());

      ContentDelta delta2 = (ContentDelta) events.get(4);
      assertEquals(1, delta2.index().get());
    }
  }
}
