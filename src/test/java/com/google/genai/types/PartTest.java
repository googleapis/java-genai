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

package com.google.genai.types;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;

public class PartTest {

  @Test
  public void testPartFromText() {
    Part part = Part.fromText("test-text");

    assertEquals("test-text", part.text().get());

    assertTrue(part.fileData().isEmpty());
    assertTrue(part.functionCall().isEmpty());
    assertTrue(part.functionResponse().isEmpty());
    assertTrue(part.inlineData().isEmpty());
    assertTrue(part.videoMetadata().isEmpty());
  }

  @Test
  public void testPartFromUri() {
    Part part = Part.fromUri("test-uri", "test-mime-type");

    assertEquals("test-uri", part.fileData().get().fileUri().get());
    assertEquals("test-mime-type", part.fileData().get().mimeType().get());

    assertTrue(part.text().isEmpty());
    assertTrue(part.functionCall().isEmpty());
    assertTrue(part.functionResponse().isEmpty());
    assertTrue(part.inlineData().isEmpty());
    assertTrue(part.videoMetadata().isEmpty());
  }

  @Test
  public void testPartFromBytes() {
    byte[] bytes = new byte[] {1, 2, 3};
    Part part = Part.fromBytes(bytes, "test-mime-type");

    assertEquals(bytes, part.inlineData().get().data().get());
    assertEquals("test-mime-type", part.inlineData().get().mimeType().get());

    assertTrue(part.text().isEmpty());
    assertTrue(part.fileData().isEmpty());
    assertTrue(part.functionCall().isEmpty());
    assertTrue(part.functionResponse().isEmpty());
    assertTrue(part.videoMetadata().isEmpty());
  }

  @Test
  public void testPartFromFunctionCall() {
    Part part =
        Part.fromFunctionCall("test-function-name", ImmutableMap.of("test-key", "test-value"));

    assertEquals("test-function-name", part.functionCall().get().name().get());
    assertEquals("test-value", part.functionCall().get().args().get().get("test-key"));

    assertTrue(part.text().isEmpty());
    assertTrue(part.fileData().isEmpty());
    assertTrue(part.functionResponse().isEmpty());
    assertTrue(part.inlineData().isEmpty());
    assertTrue(part.videoMetadata().isEmpty());
  }

  @Test
  public void testPartFromFunctionResponse() {
    Part part =
        Part.fromFunctionResponse("test-function-name", ImmutableMap.of("test-key", "test-value"));

    assertEquals("test-function-name", part.functionResponse().get().name().get());
    assertEquals("test-value", part.functionResponse().get().response().get().get("test-key"));

    assertTrue(part.text().isEmpty());
    assertTrue(part.fileData().isEmpty());
    assertTrue(part.functionCall().isEmpty());
    assertTrue(part.inlineData().isEmpty());
    assertTrue(part.videoMetadata().isEmpty());
  }

  @Test
  public void testPartFromVideoMetadata() {
    Part part = Part.fromVideoMetadata("test-start-offset", "test-end-offset");

    assertEquals("test-start-offset", part.videoMetadata().get().startOffset().get());
    assertEquals("test-end-offset", part.videoMetadata().get().endOffset().get());

    assertTrue(part.text().isEmpty());
    assertTrue(part.fileData().isEmpty());
    assertTrue(part.functionCall().isEmpty());
    assertTrue(part.functionResponse().isEmpty());
    assertTrue(part.inlineData().isEmpty());
  }
}
