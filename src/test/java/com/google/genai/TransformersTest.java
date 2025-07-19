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
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.google.common.collect.ImmutableMap;
import com.google.genai.types.File;
import com.google.genai.types.FunctionDeclaration;
import com.google.genai.types.GeneratedVideo;
import com.google.genai.types.GoogleSearch;
import com.google.genai.types.JobState;
import com.google.genai.types.Schema;
import com.google.genai.types.Tool;
import com.google.genai.types.Video;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class TransformersTest {
  private static final String FILE_NAME = "12tsygtx2";

  private class UnsupportedType {}

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
    Schema transformedSchema = Transformers.tSchema(schema);
    assertEquals(schema, transformedSchema);
  }

  @Test
  public void testTSchema_VertexAI_defaultValue_noChange() {
    Schema schema =
        Schema.builder()
            .properties(ImmutableMap.of("test", Schema.builder().default_(1).build()))
            .build();
    Schema transformedSchema = Transformers.tSchema(schema);
    assertEquals(schema, transformedSchema);
  }

  @Test
  public void testTSchema_GeminiAPI_title_success() {
    Schema schema = Schema.builder().title("test").build();
    Schema transformedSchema = Transformers.tSchema(schema);
    assertEquals(true, transformedSchema.title().isPresent());
  }

  @Test
  public void testTSchema_AnyOf_success() {
    Schema schema =
        Schema.builder()
            .type("OBJECT")
            .anyOf(Schema.builder().type("STRING"), Schema.builder().type("NUMBER"))
            .build();
    Schema transformedSchema = Transformers.tSchema(schema);
    assertEquals(2, transformedSchema.anyOf().get().size());
    assertEquals("STRING", transformedSchema.anyOf().get().get(0).type().get().toString());
    assertEquals("NUMBER", transformedSchema.anyOf().get().get(1).type().get().toString());
    assertEquals("OBJECT", transformedSchema.type().get().toString());
  }

  @Test
  public void testTSchema_Items_success() {
    Schema schema = Schema.builder().type("ARRAY").items(Schema.builder().type("STRING")).build();
    Schema transformedSchema = Transformers.tSchema(schema);
    assertEquals("STRING", transformedSchema.items().get().type().get().toString());
    assertEquals("ARRAY", transformedSchema.type().get().toString());
  }

  @Test
  public void testTSchema_Required_success() {
    Schema schema =
        Schema.builder()
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
                                .items(Schema.builder().type("STRING"))
                                .build()))
                    .required("recipe_name", "ingredients"))
            .build();
    Schema transformedSchema = Transformers.tSchema(schema);
    assertEquals(schema, transformedSchema);
  }

  @Test
  public void testTTool_noFunctions_success() {
    Tool tool =
        Tool.builder()
            .functionDeclarations(
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
                                        .items(Schema.builder().type("STRING"))
                                        .build()))
                            .required("recipeName", "ingredients")))
            .googleSearch(GoogleSearch.builder())
            .build();
    Tool geminiTransformedTool = Transformers.tTool(tool);
    Tool vertexTransformedTool = Transformers.tTool(tool);
    assertEquals(tool, geminiTransformedTool);
    assertEquals(tool, vertexTransformedTool);
  }

  @Test
  public void testTTool_Functions_success() throws NoSuchMethodException {
    Method method =
        TransformersTest.class.getMethod(
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
                                        .items(Schema.builder().type("STRING"))
                                        .build()))
                            .required("recipeName", "ingredients")))
            .googleSearch(GoogleSearch.builder())
            .functions(method)
            .build();
    Tool geminiTransformedTool = Transformers.tTool(originalTool);
    Tool vertexTransformedTool = Transformers.tTool(originalTool);
    Tool expectedTool =
        Tool.builder()
            .functionDeclarations(
                FunctionDeclaration.builder()
                    .name("functionUnderTest")
                    .description("")
                    .parameters(
                        Schema.builder()
                            .type("OBJECT")
                            .properties(properties)
                            .required(
                                "stringParam",
                                "integerParam",
                                "doubleParam",
                                "floatParam",
                                "booleanParam")),
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
                                        .items(Schema.builder().type("STRING"))
                                        .build()))
                            .required("recipeName", "ingredients")))
            .googleSearch(GoogleSearch.builder())
            .build();
    assertEquals(expectedTool, geminiTransformedTool);
    assertEquals(expectedTool, vertexTransformedTool);
  }

  @Test
  public void testTTool_functionsEmpty_success() {
    Tool originalTool =
        Tool.builder()
            .functionDeclarations(
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
                                        .items(Schema.builder().type("STRING"))
                                        .build()))
                            .required("recipeName", "ingredients")))
            .googleSearch(GoogleSearch.builder())
            .functions()
            .build();
    Tool geminiTransformedTool = Transformers.tTool(originalTool);
    Tool vertexTransformedTool = Transformers.tTool(originalTool);
    Tool expectedTool =
        Tool.builder()
            .functionDeclarations(
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
                                        .items(Schema.builder().type("STRING"))
                                        .build()))
                            .required("recipeName", "ingredients")))
            .googleSearch(GoogleSearch.builder())
            .build();
    assertEquals(expectedTool, geminiTransformedTool);
    assertEquals(expectedTool, vertexTransformedTool);
  }

  @Test
  public void tFileName_string_success() {
    String transformedFileName = Transformers.tFileName(FILE_NAME);
    assertEquals(FILE_NAME, transformedFileName);
  }

  @Test
  public void tFileName_string_file_success() {
    String fileName = "files/" + FILE_NAME;
    String transformedFileName = Transformers.tFileName(FILE_NAME);
    assertEquals(FILE_NAME, transformedFileName);
  }

  @Test
  public void tFileName_textNode_file_success() {
    String fileName = "files/" + FILE_NAME;
    TextNode textNode = JsonNodeFactory.instance.textNode(fileName);
    String transformedFileName = Transformers.tFileName(FILE_NAME);
    assertEquals(FILE_NAME, transformedFileName);
  }

  @Test
  public void tFileName_video_success() {
    Video video =
        Video.builder()
            .uri(
                "https://generativelanguage.googleapis.com/v1beta/files/"
                    + FILE_NAME
                    + ":download?alt=media")
            .build();
    String transformedFileName = Transformers.tFileName(video);
    assertEquals(FILE_NAME, transformedFileName);
  }

  @Test
  public void tFileName_generatedVideo_success() {
    GeneratedVideo generatedVideo =
        GeneratedVideo.builder()
            .video(
                Video.builder()
                    .uri(
                        "https://generativelanguage.googleapis.com/v1beta/files/"
                            + FILE_NAME
                            + ":download?alt=media"))
            .build();
    String transformedFileName = Transformers.tFileName(generatedVideo);
    assertEquals(FILE_NAME, transformedFileName);
  }

  @Test
  public void tFileName_generatedVideo_noUri_returnNull() {
    GeneratedVideo generatedVideo = GeneratedVideo.builder().video(Video.builder()).build();
    String transformedFileName = Transformers.tFileName(generatedVideo);
    assertEquals(null, transformedFileName);
  }

  @Test
  public void tFileName_video_noUri_returnNull() {
    Video video = Video.builder().build();
    String transformedFileName = Transformers.tFileName(video);
    assertEquals(null, transformedFileName);
  }

  @Test
  public void tFileName_file_noName_throwsException() {
    File file = File.builder().build();
    assertThrows(IllegalArgumentException.class, () -> Transformers.tFileName(file));
  }

  @Test
  public void tFileName_generatedVideo_noVideo_throwsException() {
    GeneratedVideo generatedVideo = GeneratedVideo.builder().build();
    assertThrows(IllegalArgumentException.class, () -> Transformers.tFileName(generatedVideo));
  }

  @Test
  public void tFileName_unsupportedType_throwsException() {
    assertThrows(
        IllegalArgumentException.class, () -> Transformers.tFileName(new UnsupportedType()));
  }

  @Test
  public void tExtractModels_nullResponse_returnNull() {
    assertTrue(Transformers.tExtractModels(null) == null);
  }

  @Test
  public void tExtractModels_unsupportedType_throwsException() {
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> Transformers.tExtractModels(new UnsupportedType()));

    assertTrue(exception.getMessage().contains("Unsupported response type"));
  }

  @Test
  public void tExtractModels_noModels_returnEmptyArrayNode() {
    ObjectNode origin = JsonSerializable.objectMapper.createObjectNode();

    JsonNode models = Transformers.tExtractModels(origin);
    assertTrue(models.isEmpty());
  }

  @Test
  public void tExtractModels_responseWithModels() {
    ObjectNode origin = JsonSerializable.objectMapper.createObjectNode();
    origin.set(
        "models", JsonSerializable.objectMapper.createArrayNode().add("model-1").add("model-2"));

    JsonNode models = Transformers.tExtractModels(origin);
    assertTrue(models instanceof ArrayNode);
    assertEquals(2, models.size());
  }

  @Test
  public void tExtractModels_responseWithTunedModels() {
    ObjectNode origin = JsonSerializable.objectMapper.createObjectNode();
    origin.set("tunedModels", JsonSerializable.objectMapper.createArrayNode().add("tuned-model-1"));

    JsonNode models = Transformers.tExtractModels(origin);
    assertTrue(models instanceof ArrayNode);
    assertEquals(1, models.size());
  }

  @Test
  public void tExtractModels_responseWithPublisherModels() {
    ObjectNode origin = JsonSerializable.objectMapper.createObjectNode();
    origin.set(
        "publisherModels",
        JsonSerializable.objectMapper.createArrayNode().add("publisher-model-1"));

    JsonNode models = Transformers.tExtractModels(origin);
    assertTrue(models instanceof ArrayNode);
    assertEquals(1, models.size());
  }

  @Test
  public void tTuningJobStatus_stringInput() {
    assertEquals(
        JobState.Known.JOB_STATE_UNSPECIFIED,
        Transformers.tTuningJobStatus("STATE_UNSPECIFIED").knownEnum());
    assertEquals(
        JobState.Known.JOB_STATE_RUNNING, Transformers.tTuningJobStatus("CREATING").knownEnum());
    assertEquals(
        JobState.Known.JOB_STATE_SUCCEEDED, Transformers.tTuningJobStatus("ACTIVE").knownEnum());
    assertEquals(
        JobState.Known.JOB_STATE_FAILED, Transformers.tTuningJobStatus("FAILED").knownEnum());
  }

  @Test
  public void tTuningJobStatus_textNodeInput() {
    assertEquals(
        JobState.Known.JOB_STATE_UNSPECIFIED,
        Transformers.tTuningJobStatus(JsonNodeFactory.instance.textNode("STATE_UNSPECIFIED"))
            .knownEnum());
    assertEquals(
        JobState.Known.JOB_STATE_RUNNING,
        Transformers.tTuningJobStatus(JsonNodeFactory.instance.textNode("CREATING")).knownEnum());
    assertEquals(
        JobState.Known.JOB_STATE_SUCCEEDED,
        Transformers.tTuningJobStatus(JsonNodeFactory.instance.textNode("ACTIVE")).knownEnum());
    assertEquals(
        JobState.Known.JOB_STATE_FAILED,
        Transformers.tTuningJobStatus(JsonNodeFactory.instance.textNode("FAILED")).knownEnum());
  }

  @Test
  public void tTuningJobStatus_unsupportedType_throwsException() {
    assertThrows(
        IllegalArgumentException.class, () -> Transformers.tTuningJobStatus(new UnsupportedType()));
  }

  @Test
  public void updateJsonNode_addNewKey() {
    ObjectNode node = JsonNodeFactory.instance.objectNode();
    TextNode value = JsonNodeFactory.instance.textNode("testValue");
    Transformers.updateJsonNode(node, "newKey", value);
    assertEquals(value, node.get("newKey"));
  }

  @Test
  public void updateJsonNode_overwriteWithNull_noChange() {
    ObjectNode node = JsonNodeFactory.instance.objectNode();
    TextNode initialValue = JsonNodeFactory.instance.textNode("initialValue");
    node.set("existingKey", initialValue);
    Transformers.updateJsonNode(node, "existingKey", null);
    assertEquals(initialValue, node.get("existingKey"));
  }

  @Test
  public void updateJsonNode_overwriteWithSameValue_noChange() {
    ObjectNode node = JsonNodeFactory.instance.objectNode();
    TextNode value = JsonNodeFactory.instance.textNode("sameValue");
    node.set("existingKey", value);
    Transformers.updateJsonNode(
        node, "existingKey", JsonNodeFactory.instance.textNode("sameValue"));
    assertEquals(value, node.get("existingKey"));
  }

  @Test
  public void updateJsonNode_mergeObjects() {
    ObjectNode node = JsonNodeFactory.instance.objectNode();
    ObjectNode initialObj = JsonNodeFactory.instance.objectNode();
    initialObj.put("a", "1");
    node.set("objectKey", initialObj);

    ObjectNode newObj = JsonNodeFactory.instance.objectNode();
    newObj.put("b", "2");
    Transformers.updateJsonNode(node, "objectKey", newObj);

    ObjectNode expectedObj = JsonNodeFactory.instance.objectNode();
    expectedObj.put("a", "1");
    expectedObj.put("b", "2");
    assertEquals(expectedObj, node.get("objectKey"));
  }

  @Test
  public void updateJsonNode_overwriteWithEmptyNode_noChange() {
    ObjectNode node = JsonNodeFactory.instance.objectNode();
    TextNode initialValue = JsonNodeFactory.instance.textNode("initial");
    node.set("key", initialValue);
    Transformers.updateJsonNode(node, "key", JsonNodeFactory.instance.objectNode());
    assertEquals(initialValue, node.get("key"));
  }

  @Test
  public void camelToSnake_emptyString() {
    assertEquals("", Transformers.camelToSnake(""));
  }

  @Test
  public void camelToSnake_nullString() {
    assertEquals(null, Transformers.camelToSnake(null));
  }

  @Test
  public void camelToSnake_singleWord() {
    assertEquals("test", Transformers.camelToSnake("test"));
  }

  @Test
  public void camelToSnake_camelCase() {
    assertEquals("test_four_word_string", Transformers.camelToSnake("testFourWordString"));
  }

  @Test
  public void camelToSnake_withNumbers() {
    assertEquals("test_string123", Transformers.camelToSnake("testString123"));
  }
}
