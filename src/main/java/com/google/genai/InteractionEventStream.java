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

import com.fasterxml.jackson.databind.JsonNode;
import com.google.genai.errors.GenAiIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An iterable stream of interaction events for the Interactions API.
 *
 * <p>This class is similar to {@link ResponseStream} but supports polymorphic event types where
 * the base type is an interface with Jackson {@code @JsonTypeInfo} and {@code @JsonSubTypes}
 * annotations.
 *
 * <p>This is used for streaming interactions where events are deserialized to their concrete types
 * based on the "event_type" discriminator field.
 *
 * <p>Example usage:
 * <pre>{@code
 * try (InteractionEventStream<InteractionSseEvent> stream =
 *     client.interactions.createStream(config)) {
 *   for (InteractionSseEvent event : stream) {
 *     // Process each event
 *   }
 * }
 * }</pre>
 */
public class InteractionEventStream<T> implements Iterable<T>, AutoCloseable {

  /** Iterator for the InteractionEventStream. */
  class InteractionEventStreamIterator implements Iterator<T> {
    private final BufferedReader reader;
    private final Class<T> clazz;
    private String nextJson;
    private boolean consumed = false;

    InteractionEventStreamIterator(Class<T> clazz, BufferedReader reader) {
      this.reader = reader;
      this.clazz = clazz;
      this.nextJson = readNextJson();
    }

    @Override
    public boolean hasNext() {
      if (nextJson == null) {
        consumed = true;
      }
      return nextJson != null;
    }

    @Override
    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException("No more JSON objects in the stream");
      }
      String currentJson = nextJson;
      nextJson = readNextJson();

      JsonNode currentJsonNode = JsonSerializable.stringToJsonNode(currentJson);
      return JsonSerializable.fromJsonNodePolymorphic(currentJsonNode, clazz);
    }

    private String readNextJson() {
      // SSE (Server-Sent Events) format:
      // event: interaction.start
      // data: {"event_type": "interaction.start", ...}
      //
      // event: content.delta
      // data: {"event_type": "content.delta", ...}
      //
      // data: [DONE]  <-- stream termination signal
      //
      // We only want the JSON from "data:" lines, skip "event:" and empty lines
      try {
        String line = reader.readLine();
        if (line == null) {
          return null;
        } else if (line.isEmpty()) {
          // Skip empty lines
          return readNextJson();
        } else if (line.startsWith("data: ")) {
          // Extract content from data line
          String data = line.substring("data: ".length());
          // Check for stream termination signal
          if ("[DONE]".equals(data)) {
            return null;  // Signal end of stream
          }
          return data;
        } else if (line.startsWith("event:") || line.startsWith(":")) {
          // Skip SSE event type lines and comment lines (starting with :)
          return readNextJson();
        } else {
          // Fallback: try to parse as JSON directly (for backwards compatibility)
          return line;
        }
      } catch (IOException e) {
        throw new GenAiIOException("Failed to read next JSON object from the stream", e);
      }
    }
  }

  private final InteractionEventStreamIterator iterator;
  private final ApiResponse response;
  private final BufferedReader reader;

  /**
   * Creates an InteractionEventStream for direct Jackson polymorphic deserialization.
   *
   * @param clazz the class (or interface) of the event type with Jackson type annotations
   * @param response the API response containing the stream
   */
  public InteractionEventStream(Class<T> clazz, ApiResponse response) {
    InputStream responseStream = response.getBody().byteStream();
    this.reader = new BufferedReader(new InputStreamReader(responseStream, StandardCharsets.UTF_8));
    this.iterator = new InteractionEventStreamIterator(clazz, this.reader);
    this.response = response;
  }

  @Override
  public Iterator<T> iterator() {
    return iterator;
  }

  @Override
  public void close() {
    try {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          throw new GenAiIOException("Failed to close the response stream.", e);
        }
      }
    } finally {
      if (response != null) {
        response.close();
      }
    }
  }

  boolean isConsumed() {
    return iterator.consumed;
  }
}
