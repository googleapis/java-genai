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

import org.junit.jupiter.api.Test;

public class FunctionResponsePartTest {

  @Test
  public void testFunctionResponsePartFromUri() {
    FunctionResponsePart part = FunctionResponsePart.fromUri("test-uri", "test-mime-type");

    assertEquals("test-uri", part.fileData().get().fileUri().get());
    assertEquals("test-mime-type", part.fileData().get().mimeType().get());
  }

  @Test
  public void testPartFromBytes() {
    byte[] bytes = new byte[] {1, 2, 3};
    FunctionResponsePart part =
        FunctionResponsePart.fromBytes(bytes, "test-mime-type");

    assertEquals(bytes, part.inlineData().get().data().get());
    assertEquals("test-mime-type", part.inlineData().get().mimeType().get());
  }
}
