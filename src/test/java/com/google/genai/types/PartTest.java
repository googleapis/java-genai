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
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;

public class PartTest {

  @Test
  public void testPartFromText() {
    Part part = Part.fromText("test-text");

    assertEquals("test-text", part.text().get());

    assertFalse(part.fileData().isPresent());
    assertFalse(part.functionCall().isPresent());
    assertFalse(part.functionResponse().isPresent());
    assertFalse(part.inlineData().isPresent());
    assertFalse(part.videoMetadata().isPresent());
  }

  @Test
  public void testPartFromUri() {
    Part part = Part.fromUri("test-uri", "test-mime-type");

    assertEquals("test-uri", part.fileData().get().fileUri().get());
    assertEquals("test-mime-type", part.fileData().get().mimeType().get());

    assertFalse(part.text().isPresent());
    assertFalse(part.functionCall().isPresent());
    assertFalse(part.functionResponse().isPresent());
    assertFalse(part.inlineData().isPresent());
    assertFalse(part.videoMetadata().isPresent());
  }

  @Test
  public void testPartFromBytes() {
    byte[] bytes = new byte[] {1, 2, 3};
    Part part = Part.fromBytes(bytes, "test-mime-type");

    assertEquals(bytes, part.inlineData().get().data().get());
    assertEquals("test-mime-type", part.inlineData().get().mimeType().get());

    assertFalse(part.text().isPresent());
    assertFalse(part.fileData().isPresent());
    assertFalse(part.functionCall().isPresent());
    assertFalse(part.functionResponse().isPresent());
    assertFalse(part.videoMetadata().isPresent());
  }

  @Test
  public void testPartFromFunctionCall() {
    Part part =
        Part.fromFunctionCall("test-function-name", ImmutableMap.of("test-key", "test-value"));

    assertEquals("test-function-name", part.functionCall().get().name().get());
    assertEquals("test-value", part.functionCall().get().args().get().get("test-key"));

    assertFalse(part.text().isPresent());
    assertFalse(part.fileData().isPresent());
    assertFalse(part.functionResponse().isPresent());
    assertFalse(part.inlineData().isPresent());
    assertFalse(part.videoMetadata().isPresent());
  }

  @Test
  public void testPartFromFunctionResponse() {
    Part part =
        Part.fromFunctionResponse("test-function-name", ImmutableMap.of("test-key", "test-value"));

    assertEquals("test-function-name", part.functionResponse().get().name().get());
    assertEquals("test-value", part.functionResponse().get().response().get().get("test-key"));

    assertFalse(part.text().isPresent());
    assertFalse(part.fileData().isPresent());
    assertFalse(part.functionCall().isPresent());
    assertFalse(part.inlineData().isPresent());
    assertFalse(part.videoMetadata().isPresent());
  }

  @Test
  public void testPartFromFunctionResponseWithFunctionResponseParts() {
    FunctionResponsePart functionResponsePart =
        FunctionResponsePart.fromBytes(new byte[] {1, 2, 3}, "image/png");
    FunctionResponsePart[] functionResponseParts =
        new FunctionResponsePart[] {functionResponsePart};
    Part part =
        Part.fromFunctionResponse(
            "test-function-name", ImmutableMap.of("test-key", "test-value"), functionResponseParts);

    assertEquals("test-function-name", part.functionResponse().get().name().get());
    assertEquals("test-value", part.functionResponse().get().response().get().get("test-key"));
    assertEquals(functionResponsePart, part.functionResponse().get().parts().get().get(0));

    assertFalse(part.text().isPresent());
    assertFalse(part.fileData().isPresent());
    assertFalse(part.functionCall().isPresent());
    assertFalse(part.inlineData().isPresent());
    assertFalse(part.videoMetadata().isPresent());
  }
}
