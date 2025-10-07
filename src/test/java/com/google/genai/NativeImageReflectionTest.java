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

import com.google.genai.types.*;
import org.junit.jupiter.api.Test;

/**
 * Test to generate comprehensive reflection metadata for native image.
 * This test instantiates all builder types to ensure they're captured by the agent.
 */
public class NativeImageReflectionTest {

  @Test
  public void testAllBuilderTypes() {
    // Exercise all the builder types that might be used in streaming responses
    try {
      // FunctionResponsePart and related types
      FunctionResponseBlob.builder().build();
      FunctionResponseFileData.builder().build();
      FunctionResponsePart.builder().build();

      // Content and Part types
      Content.builder().build();
      Part.builder().build();

      // Response types
      GenerateContentResponse.builder().build();
      Candidate.builder().build();

      // Safety and metadata types
      SafetyRating.builder().build();
      CitationMetadata.builder().build();

      // Usage metadata
      UsageMetadata.builder().build();

      // Grounding metadata
      GroundingMetadata.builder().build();
      GroundingChunk.builder().build();

      // Model types
      Model.builder().build();

    } catch (Exception e) {
      // Expected - we're just ensuring reflection metadata is generated
    }
  }

  @Test
  public void testDeserializationTypes() {
    // Test JSON deserialization to ensure Jackson reflection is captured
    try {
      String jsonResponse = "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"test\"}]}}]}";
      GenerateContentResponse.fromJson(jsonResponse);

      String jsonPart = "{\"inlineData\":{\"mimeType\":\"text/plain\",\"data\":\"test\"}}";
      FunctionResponsePart.fromJson(jsonPart);

    } catch (Exception e) {
      // Expected - we're just ensuring reflection metadata is generated
    }
  }
}