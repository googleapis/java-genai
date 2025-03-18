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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;

public class ContentTest {

  @Test
  public void testContentFromParts() {
    Part textPart = Part.fromText("test-text");
    Part filePart = Part.fromUri("test-uri", "test-mime-type");
    Part inlineDataPart = Part.fromBytes(new byte[] {1, 2, 3}, "test-mime-type");
    Part functionCallPart =
        Part.fromFunctionCall("test-function-name", ImmutableMap.of("test-key", "test-value"));
    Part functionResponsePart =
        Part.fromFunctionResponse("test-function-name", ImmutableMap.of("test-key", "test-value"));
    Part videoMetadataPart = Part.fromVideoMetadata("test-start-offset", "test-end-offset");

    Content content =
        Content.fromParts(
            textPart,
            filePart,
            inlineDataPart,
            functionCallPart,
            functionResponsePart,
            videoMetadataPart);

    assertEquals(
        ImmutableList.of(
            textPart,
            filePart,
            inlineDataPart,
            functionCallPart,
            functionResponsePart,
            videoMetadataPart),
        content.parts().get());
    assertEquals("user", content.role().get());
  }
}
