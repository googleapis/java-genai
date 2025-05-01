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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.genai.types.FunctionDeclaration;
import com.google.genai.types.GoogleSearch;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.Schema;
import com.google.genai.types.Tool;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class TransformersTest {
  private static final ApiClient GEMINI_API_CLIENT =
      new HttpApiClient(Optional.of("api_key"), Optional.of(HttpOptions.builder().build()));
  private static final ApiClient VERTEX_AI_CLIENT =
      new HttpApiClient(
          Optional.of("project"),
          Optional.of("location"),
          Optional.empty(),
          Optional.of(HttpOptions.builder().build()));

  /** A function (static method) to test fromMethod functionalities. */
  public static int functionUnderTest(
      String stringParam,
      int integerParam,
      double doubleParam,
      float floatParam,
      boolean booleanParam) {
    return 0;
  }

  @Test
  public void testTSchema_GeminiAPI_defaultValue_noChange() {
    Schema schema =
        Schema.builder()
            .properties(ImmutableMap.of("test", Schema.builder().default_(1).build()))
            .build();
    Schema transformedSchema = Transformers.tSchema(GEMINI_API_CLIENT, schema);
    assertEquals(schema, transformedSchema);
  }

  @Test
  public void testTSchema_VertexAI_defaultValue_noChange() {
    Schema schema =
        Schema.builder()
            .properties(ImmutableMap.of("test", Schema.builder().default_(1).build()))
            .build();
    Schema transformedSchema = Transformers.tSchema(VERTEX_AI_CLIENT, schema);
    assertEquals(schema, transformedSchema);
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

  @Test
  public void testTSchema_Required_success() {
    Schema schema = Schema.builder()
            .type("ARRAY")
            .items(
                Schema.builder()
                    .type("OBJECT")
                    .properties(
                        ImmutableMap.of(
                            "recipe_name",
                            Schema.builder().type("STRING").build(),
                            "ingredients",
                            Schema.builder()
                                .type("ARRAY")
                                .items(Schema.builder().type("STRING").build())
                                .build()
                            ))
                    .required(ImmutableList.of("recipe_name", "ingredients"))
                    .build())
            .build();
    Schema transformedSchema = Transformers.tSchema(GEMINI_API_CLIENT, schema);
    assertEquals(schema, transformedSchema);
  }

  @Test
  public void testTTool_noReflectMethods_success() {
    Tool tool =
        Tool.builder()
            .functionDeclarations(
                ImmutableList.of(
                    FunctionDeclaration.builder()
                        .name("functionName1")
                        .description("functionDescription1")
                        .parameters(
                            Schema.builder()
                                .type("OBJECT")
                                .properties(
                                    ImmutableMap.of(
                                        "recipeName",
                                        Schema.builder().type("STRING").build(),
                                        "ingredients",
                                        Schema.builder()
                                            .type("ARRAY")
                                            .items(Schema.builder().type("STRING").build())
                                            .build()))
                                .required(ImmutableList.of("recipeName", "ingredients"))
                                .build())
                        .build()))
            .googleSearch(GoogleSearch.builder().build())
            .build();
    Tool geminiTransformedTool = Transformers.tTool(GEMINI_API_CLIENT, tool);
    Tool vertexTransformedTool = Transformers.tTool(VERTEX_AI_CLIENT, tool);
    assertEquals(tool, geminiTransformedTool);
    assertEquals(tool, vertexTransformedTool);
  }

  @Test
  public void testTTool_reflectMethods_success() throws NoSuchMethodException {
    Method method = TransformersTest.class.getMethod(
        "functionUnderTest", String.class, int.class, double.class, float.class, boolean.class);
    Map<String, Schema> properties = new HashMap<>();
    properties.put("stringParam", Schema.builder().type("STRING").title("stringParam").build());
    properties.put("integerParam", Schema.builder().type("INTEGER").title("integerParam").build());
    properties.put("doubleParam", Schema.builder().type("NUMBER").title("doubleParam").build());
    properties.put("floatParam", Schema.builder().type("NUMBER").title("floatParam").build());
    properties.put("booleanParam", Schema.builder().type("BOOLEAN").title("booleanParam").build());
    Tool originalTool =
        Tool.builder()
            .functionDeclarations(
                ImmutableList.of(
                    FunctionDeclaration.builder()
                        .name("functionName1")
                        .description("functionDescription1")
                        .parameters(
                            Schema.builder()
                                .type("OBJECT")
                                .properties(
                                    ImmutableMap.of(
                                        "recipeName",
                                        Schema.builder().type("STRING").build(),
                                        "ingredients",
                                        Schema.builder()
                                            .type("ARRAY")
                                            .items(Schema.builder().type("STRING").build())
                                            .build()))
                                .required(ImmutableList.of("recipeName", "ingredients"))
                                .build())
                        .build()))
            .googleSearch(GoogleSearch.builder().build())
            .reflectMethods(ImmutableList.of(method))
            .build();
    Tool geminiTransformedTool = Transformers.tTool(GEMINI_API_CLIENT, originalTool);
    Tool vertexTransformedTool = Transformers.tTool(VERTEX_AI_CLIENT, originalTool);
    Tool expectedTool =
        Tool.builder()
            .functionDeclarations(
                ImmutableList.of(
                    FunctionDeclaration.builder()
                        .name("functionUnderTest")
                        .description("")
                        .parameters(
                            Schema.builder()
                                .type("OBJECT")
                                .properties(properties)
                                .required(
                                    ImmutableList.of(
                                        "stringParam",
                                        "integerParam",
                                        "doubleParam",
                                        "floatParam",
                                        "booleanParam"))
                                .build())
                        .build(),
                    FunctionDeclaration.builder()
                        .name("functionName1")
                        .description("functionDescription1")
                        .parameters(
                            Schema.builder()
                                .type("OBJECT")
                                .properties(
                                    ImmutableMap.of(
                                        "recipeName",
                                        Schema.builder().type("STRING").build(),
                                        "ingredients",
                                        Schema.builder()
                                            .type("ARRAY")
                                            .items(Schema.builder().type("STRING").build())
                                            .build()))
                                .required(ImmutableList.of("recipeName", "ingredients"))
                                .build())
                        .build()))
            .googleSearch(GoogleSearch.builder().build())
            .build();
    assertEquals(expectedTool, geminiTransformedTool);
    assertEquals(expectedTool, vertexTransformedTool);
  }

  @Test
  public void testTTool_reflectMethodsEmpty_success() {
    Tool originalTool =
        Tool.builder()
            .functionDeclarations(
                ImmutableList.of(
                    FunctionDeclaration.builder()
                        .name("functionName1")
                        .description("functionDescription1")
                        .parameters(
                            Schema.builder()
                                .type("OBJECT")
                                .properties(
                                    ImmutableMap.of(
                                        "recipeName",
                                        Schema.builder().type("STRING").build(),
                                        "ingredients",
                                        Schema.builder()
                                            .type("ARRAY")
                                            .items(Schema.builder().type("STRING").build())
                                            .build()))
                                .required(ImmutableList.of("recipeName", "ingredients"))
                                .build())
                        .build()))
            .googleSearch(GoogleSearch.builder().build())
            .reflectMethods(ImmutableList.of())
            .build();
    Tool geminiTransformedTool = Transformers.tTool(GEMINI_API_CLIENT, originalTool);
    Tool vertexTransformedTool = Transformers.tTool(VERTEX_AI_CLIENT, originalTool);
    Tool expectedTool =
        Tool.builder()
            .functionDeclarations(
                ImmutableList.of(
                    FunctionDeclaration.builder()
                        .name("functionName1")
                        .description("functionDescription1")
                        .parameters(
                            Schema.builder()
                                .type("OBJECT")
                                .properties(
                                    ImmutableMap.of(
                                        "recipeName",
                                        Schema.builder().type("STRING").build(),
                                        "ingredients",
                                        Schema.builder()
                                            .type("ARRAY")
                                            .items(Schema.builder().type("STRING").build())
                                            .build()))
                                .required(ImmutableList.of("recipeName", "ingredients"))
                                .build())
                        .build()))
            .googleSearch(GoogleSearch.builder().build())
            .build();
    assertEquals(expectedTool, geminiTransformedTool);
    assertEquals(expectedTool, vertexTransformedTool);
  }
}
