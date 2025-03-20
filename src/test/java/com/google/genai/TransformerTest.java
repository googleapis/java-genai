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
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.Schema;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class TransformerTest {
  private static final ApiClient GEMINI_API_CLIENT =
      new HttpApiClient(Optional.of("api_key"), Optional.of(HttpOptions.builder().build()));
  private static final ApiClient VERTEX_AI_CLIENT =
      new HttpApiClient(
          Optional.of("project"),
          Optional.of("location"),
          Optional.empty(),
          Optional.of(HttpOptions.builder().build()));

  @Test
  public void testTSchema_GeminiAPI_defaultValue_throwsException() {
    Schema schema =
        Schema.builder()
            .properties(ImmutableMap.of("test", Schema.builder().default_(1).build()))
            .build();
    assertThrows(
        IllegalArgumentException.class, () -> Transformers.tSchema(GEMINI_API_CLIENT, schema));
  }

  @Test
  public void testTSchema_VertexAI_defaultValue_success() {
    Schema schema =
        Schema.builder()
            .properties(ImmutableMap.of("test", Schema.builder().default_(1).build()))
            .build();
    Schema transformedSchema = Transformers.tSchema(VERTEX_AI_CLIENT, schema);
    assertEquals(1, transformedSchema.properties().get().get("test").default_().get());
  }

  @Test
  public void testTSchema_GeminiAPI_title_success() {
    Schema schema = Schema.builder().title("test").build();
    Schema transformedSchema = Transformers.tSchema(GEMINI_API_CLIENT, schema);
    assertEquals(true, transformedSchema.title().isPresent());
  }

  @Test
  public void testTSchema_AnyOf_success() {
    Schema schema =
        Schema.builder()
            .type("OBJECT")
            .anyOf(
                ImmutableList.of(
                    Schema.builder().type("STRING").build(),
                    Schema.builder().type("NUMBER").build()))
            .build();
    Schema transformedSchema = Transformers.tSchema(VERTEX_AI_CLIENT, schema);
    assertEquals(2, transformedSchema.anyOf().get().size());
    assertEquals("STRING", transformedSchema.anyOf().get().get(0).type().get());
    assertEquals("NUMBER", transformedSchema.anyOf().get().get(1).type().get());
    assertEquals("OBJECT", transformedSchema.type().get());
  }

  @Test
  public void testTSchema_Items_success() {
    Schema schema =
        Schema.builder().type("ARRAY").items(Schema.builder().type("STRING").build()).build();
    Schema transformedSchema = Transformers.tSchema(GEMINI_API_CLIENT, schema);
    assertEquals("STRING", transformedSchema.items().get().type().get());
    assertEquals("ARRAY", transformedSchema.type().get());
  }
}
