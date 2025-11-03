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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;

public class ContentTest {

  private static final String TEXT_1 = "test-text-1";
  private static final String TEXT_2 = "test-text-2";
  private static final Part TEXT_PART_1 = Part.fromText(TEXT_1);
  private static final Part TEXT_PART_2 = Part.fromText(TEXT_2);
  private static final Part FILE_PART = Part.fromUri("test-uri", "test-mime-type");
  private static final Part INLINE_DATA_PART =
      Part.fromBytes(new byte[] {1, 2, 3}, "test-mime-type");
  private static final Part FUNCTION_CALL_PART =
      Part.fromFunctionCall("test-function-name", ImmutableMap.of("test-key", "test-value"));
  private static final Part FUNCTION_RESPONSE_PART =
      Part.fromFunctionResponse("test-function-name", ImmutableMap.of("test-key", "test-value"));

  @Test
  public void testContentFromParts() {
    Content content =
        Content.fromParts(
            TEXT_PART_1, FILE_PART, INLINE_DATA_PART, FUNCTION_CALL_PART, FUNCTION_RESPONSE_PART);

    assertEquals(
        ImmutableList.of(
            TEXT_PART_1, FILE_PART, INLINE_DATA_PART, FUNCTION_CALL_PART, FUNCTION_RESPONSE_PART),
        content.parts().get());
    assertEquals("user", content.role().get());
  }

  @Test
  public void testText_ConcatenatesTextParts() {
    Content content = Content.fromParts(TEXT_PART_1, TEXT_PART_2);
    String text = content.text();

    assertEquals(TEXT_1 + TEXT_2, text);
  }

  @Test
  public void testText_NullParts() {
    Content content = Content.builder().build();
    String text = content.text();

    assertEquals(null, text);
  }

  @Test
  public void testText_EmptyParts() {
    Content content = Content.builder().parts(ImmutableList.of()).build();
    String text = content.text();

    assertEquals(null, text);
  }

  @Test
  public void testText_NonTextParts() {
    Content content =
        Content.fromParts(
            TEXT_PART_1, FILE_PART, INLINE_DATA_PART, FUNCTION_CALL_PART, FUNCTION_RESPONSE_PART);
    String text = content.text();

    assertEquals(TEXT_1, text);
  }

  @Test
  public void testText_NoTextParts() {
    Content content =
        Content.fromParts(FILE_PART, INLINE_DATA_PART, FUNCTION_CALL_PART, FUNCTION_RESPONSE_PART);
    String text = content.text();

    assertEquals("", text);
  }

  @Test
  public void testClearMethods() {
    Content content = Content.builder().parts(TEXT_PART_1).role("user").build();

    assertEquals(TEXT_PART_1, content.parts().get().get(0));
    assertEquals("user", content.role().get());

    content = content.toBuilder().clearParts().clearRole().build();

    assertFalse(content.parts().isPresent());
    assertFalse(content.role().isPresent());
  }
}
