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
import com.google.genai.types.AutomaticFunctionCallingConfig;
import com.google.genai.types.Candidate;
import com.google.genai.types.ComputerUse;
import com.google.genai.types.Content;
import com.google.genai.types.EnterpriseWebSearch;
import com.google.genai.types.FunctionCall;
import com.google.genai.types.FunctionDeclaration;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.GoogleMaps;
import com.google.genai.types.GoogleSearch;
import com.google.genai.types.GoogleSearchRetrieval;
import com.google.genai.types.McpServer;
import com.google.genai.types.Part;
import com.google.genai.types.Retrieval;
import com.google.genai.types.Schema;
import com.google.genai.types.Tool;
import com.google.genai.types.ToolCodeExecution;
import com.google.genai.types.Type;
import com.google.genai.types.UrlContext;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public final class AfcUtilTest {
  public static String testFunction1(String input) {
    return input + "testFunction1";
  }

  public static Integer testFunction2(Integer a, Integer b) {
    return a / b;
  }

  public static String testFunctionJoinListOfStrings(List<String> items) {
    return String.join(",", items);
  }

  public static Integer testFunctionSumListOfIntegers(List<Integer> items) {
    int sum = 0;
    for (Integer item : items) {
      sum += item;
    }
    return sum;
  }

  public static List<String> testFunctionReturnListOfStrings(List<List<String>> items) {
    List<String> result = new ArrayList<>();
    for (List<String> item : items) {
      result.add(String.join("", item));
    }
    return result;
  }

  private static FunctionDeclaration testFunctionDeclaration1 =
      FunctionDeclaration.builder()
          .name("testFunction1")
          .description("")
          .parameters(
              Schema.builder()
                  .type(Type.Known.OBJECT)
                  .properties(
                      ImmutableMap.of(
                          "input", Schema.builder().type(Type.Known.STRING).title("input").build()))
                  .required("input"))
          .response(Schema.builder().type(Type.Known.STRING).title("return type"))
          .build();

  private static FunctionDeclaration testFunctionDeclaration2 =
      FunctionDeclaration.builder()
          .name("testFunction2")
          .parameters(
              Schema.builder()
                  .type(Type.Known.OBJECT)
                  .properties(
                      ImmutableMap.of(
                          "input2",
                          Schema.builder().type(Type.Known.STRING).title("input2").build()))
                  .required("input2"))
          .response(Schema.builder().type(Type.Known.INTEGER).title("return type"))
          .build();

  @Test
  public void transformGenerateContentConfig_emptyConfig_returnsTransformedConfig() {
    GenerateContentConfig config = GenerateContentConfig.builder().build();
    GenerateContentConfig transformedConfig = AfcUtil.transformGenerateContentConfig(config);
    assertEquals(config, transformedConfig);
  }

  @Test
  public void transformGenerateContentConfig_functionDeclaration_returnsTransformedConfig() {
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .tools(Tool.builder().functionDeclarations(testFunctionDeclaration1))
            .build();
    GenerateContentConfig transformedConfig = AfcUtil.transformGenerateContentConfig(config);
    assertEquals(config.toString(), transformedConfig.toString());
  }

  @Test
  public void transformGenerateContentConfig_functionDeclarationAndMethod_returnsTransformedConfig()
      throws NoSuchMethodException {
    Method testMethod1 = AfcUtilTest.class.getMethod("testFunction1", String.class);
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .tools(
                Tool.builder()
                    .functionDeclarations(testFunctionDeclaration2)
                    .functions(testMethod1))
            .build();
    GenerateContentConfig expectedConfig =
        GenerateContentConfig.builder()
            .tools(
                Tool.builder()
                    .functionDeclarations(testFunctionDeclaration1, testFunctionDeclaration2))
            .build();
    GenerateContentConfig transformedConfig = AfcUtil.transformGenerateContentConfig(config);
    assertEquals(expectedConfig.toString(), transformedConfig.toString());
  }

  @Test
  public void getFunctionMap_returnsFunctionMap() throws NoSuchMethodException {
    Method testMethod1 = AfcUtilTest.class.getMethod("testFunction1", String.class);
    GenerateContentConfig config =
        GenerateContentConfig.builder().tools(Tool.builder().functions(testMethod1)).build();
    ImmutableMap<String, Method> actualFunctionMap = AfcUtil.getFunctionMap(config);
    ImmutableMap<String, Method> expectedFunctionMap =
        ImmutableMap.of("testFunction1", testMethod1);
    assertEquals(expectedFunctionMap, actualFunctionMap);
  }

  @Test
  public void getFunctionMap_nullConfig_returnsEmptyFunctionMap() {
    ImmutableMap<String, Method> actualFunctionMap = AfcUtil.getFunctionMap(null);
    assertEquals(ImmutableMap.of(), actualFunctionMap);
  }

  @Test
  public void getFunctionMap_emptyConfig_returnsEmptyFunctionMap() {
    GenerateContentConfig config = GenerateContentConfig.builder().build();
    ImmutableMap<String, Method> actualFunctionMap = AfcUtil.getFunctionMap(config);
    assertEquals(ImmutableMap.of(), actualFunctionMap);
  }

  @Test
  public void shouldDisableAfc_nullConfig_returnsFalse() {
    boolean shouldDisableAfc = AfcUtil.shouldDisableAfc(null);
    assertEquals(false, shouldDisableAfc);
  }

  @Test
  public void shouldDisableAfc_emptyConfig_returnsFalse() {
    GenerateContentConfig config = GenerateContentConfig.builder().build();
    boolean shouldDisableAfc = AfcUtil.shouldDisableAfc(config);
    assertEquals(false, shouldDisableAfc);
  }

  @Test
  public void shouldDisableAfc_disableAfc_returnsTrue() {
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .automaticFunctionCalling(AutomaticFunctionCallingConfig.builder().disable(true))
            .build();
    boolean shouldDisableAfc = AfcUtil.shouldDisableAfc(config);
    assertEquals(true, shouldDisableAfc);
  }

  @Test
  public void shouldDisableAfc_disableAfcAndMaxRemoteCalls_returnsTrue() {
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .automaticFunctionCalling(
                AutomaticFunctionCallingConfig.builder().disable(true).maximumRemoteCalls(10))
            .build();
    boolean shouldDisableAfc = AfcUtil.shouldDisableAfc(config);
    assertEquals(true, shouldDisableAfc);
  }

  @Test
  public void shouldDisableAfc_enableAfcMaxRemoteCallsNegative_returnsTrue() {
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .automaticFunctionCalling(
                AutomaticFunctionCallingConfig.builder().disable(false).maximumRemoteCalls(-1))
            .build();
    boolean shouldDisableAfc = AfcUtil.shouldDisableAfc(config);
    assertEquals(true, shouldDisableAfc);
  }

  @Test
  public void getMaxRemoteCallsAfc_nullConfig_returnsDefaultMaxRemoteCallsAfc() {
    int maxRemoteCallsAfc = AfcUtil.getMaxRemoteCallsAfc(null);
    assertEquals(10, maxRemoteCallsAfc);
  }

  @Test
  public void getMaxRemoteCallsAfc_emptyConfig_returnsDefaultMaxRemoteCallsAfc() {
    GenerateContentConfig config = GenerateContentConfig.builder().build();
    int maxRemoteCallsAfc = AfcUtil.getMaxRemoteCallsAfc(config);
    assertEquals(10, maxRemoteCallsAfc);
  }

  @Test
  public void getMaxRemoteCallsAfc_returnsMaxRemoteCallsAfc() {
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .automaticFunctionCalling(
                AutomaticFunctionCallingConfig.builder().maximumRemoteCalls(5))
            .build();
    int maxRemoteCallsAfc = AfcUtil.getMaxRemoteCallsAfc(config);
    assertEquals(5, maxRemoteCallsAfc);
  }

  @Test
  public void getMaxRemoteCallsAfc_emptyMaxRemoteCalls_returnsDefaultMaxRemoteCallsAfc() {
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .automaticFunctionCalling(AutomaticFunctionCallingConfig.builder())
            .build();
    int maxRemoteCallsAfc = AfcUtil.getMaxRemoteCallsAfc(config);
    assertEquals(10, maxRemoteCallsAfc);
  }

  @Test
  public void shouldAppendAfcHistory_nullConfig_returnsTrue() {
    boolean shouldAppendAfcHistory = AfcUtil.shouldAppendAfcHistory(null);
    assertEquals(true, shouldAppendAfcHistory);
  }

  @Test
  public void shouldAppendAfcHistory_emptyConfig_returnsTrue() {
    GenerateContentConfig config = GenerateContentConfig.builder().build();
    boolean shouldAppendAfcHistory = AfcUtil.shouldAppendAfcHistory(config);
    assertEquals(true, shouldAppendAfcHistory);
  }

  @Test
  public void shouldAppendAfcHistory_ignoreCallHistory_returnsFalse() {
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .automaticFunctionCalling(
                AutomaticFunctionCallingConfig.builder().ignoreCallHistory(true))
            .build();
    boolean shouldAppendAfcHistory = AfcUtil.shouldAppendAfcHistory(config);
    assertEquals(false, shouldAppendAfcHistory);
  }

  @Test
  public void getFunctionResponseParts_returnsFunctionResponseParts() throws NoSuchMethodException {
    FunctionCall functionCall =
        FunctionCall.builder().name("testFunction1").args(ImmutableMap.of("input", "test")).build();
    ImmutableMap<String, Method> functionMap =
        ImmutableMap.of(
            "testFunction1", AfcUtilTest.class.getMethod("testFunction1", String.class));
    Content content = Content.builder().parts(Part.builder().functionCall(functionCall)).build();
    GenerateContentResponse response =
        GenerateContentResponse.builder().candidates(Candidate.builder().content(content)).build();
    ImmutableList<Part> functionResponseParts =
        AfcUtil.getFunctionResponseParts(response, functionMap);
    ImmutableList<Part> expectedFunctionResponseParts =
        ImmutableList.of(
            Part.fromFunctionResponse(
                "testFunction1", ImmutableMap.of("result", "testtestFunction1")));
    assertEquals(1, functionResponseParts.size());
    assertEquals(expectedFunctionResponseParts.toString(), functionResponseParts.toString());
  }

  @Test
  public void getFunctionResponseParts_emptyResponse_returnsEmptyFunctionResponseParts() {
    ImmutableMap<String, Method> functionMap = ImmutableMap.of();
    GenerateContentResponse response = GenerateContentResponse.builder().build();
    ImmutableList<Part> functionResponseParts =
        AfcUtil.getFunctionResponseParts(response, functionMap);
    assertEquals(0, functionResponseParts.size());
  }

  @Test
  public void getFunctionResponseParts_emptyFunctionMap_returnsEmptyFunctionResponseParts() {
    FunctionCall functionCall =
        FunctionCall.builder().name("testFunction1").args(ImmutableMap.of("input", "test")).build();
    ImmutableMap<String, Method> functionMap = ImmutableMap.of();
    Content content = Content.builder().parts(Part.builder().functionCall(functionCall)).build();
    GenerateContentResponse response =
        GenerateContentResponse.builder().candidates(Candidate.builder().content(content)).build();
    ImmutableList<Part> functionResponseParts =
        AfcUtil.getFunctionResponseParts(response, functionMap);
    assertEquals(0, functionResponseParts.size());
  }

  @Test
  public void getFunctionResponseParts_emptyFunctionCall_returnsEmptyFunctionResponseParts()
      throws NoSuchMethodException {
    ImmutableMap<String, Method> functionMap =
        ImmutableMap.of(
            "testFunction1", AfcUtilTest.class.getMethod("testFunction1", String.class));
    Content content = Content.fromParts(Part.fromText("test"));
    GenerateContentResponse response =
        GenerateContentResponse.builder().candidates(Candidate.builder().content(content)).build();
    ImmutableList<Part> functionResponseParts =
        AfcUtil.getFunctionResponseParts(response, functionMap);
    assertEquals(0, functionResponseParts.size());
  }

  @Test
  public void getFunctionResponseParts_exceptionThrown_returnsFunctionResponseParts()
      throws NoSuchMethodException {
    FunctionCall functionCall =
        FunctionCall.builder().name("testFunction2").args(ImmutableMap.of("a", 1, "b", 0)).build();
    ImmutableMap<String, Method> functionMap =
        ImmutableMap.of(
            "testFunction2",
            AfcUtilTest.class.getMethod("testFunction2", Integer.class, Integer.class));
    Content content = Content.fromParts(Part.builder().functionCall(functionCall).build());
    GenerateContentResponse response =
        GenerateContentResponse.builder().candidates(Candidate.builder().content(content)).build();
    ImmutableList<Part> functionResponseParts =
        AfcUtil.getFunctionResponseParts(response, functionMap);
    ImmutableList<Part> expectedFunctionResponseParts =
        ImmutableList.of(
            Part.fromFunctionResponse(
                "testFunction2",
                ImmutableMap.of("error", "java.lang.reflect.InvocationTargetException")));
    assertEquals(1, functionResponseParts.size());
    assertEquals(expectedFunctionResponseParts.toString(), functionResponseParts.toString());
  }

  @Test
  public void getFunctionResponseParts_withListOfStringsParameter_returnsFunctionResponseParts()
      throws NoSuchMethodException {
    FunctionCall functionCall =
        FunctionCall.builder()
            .name("testFunctionJoinListOfStrings")
            .args(ImmutableMap.of("items", ImmutableList.of("a", "b", "c")))
            .build();
    ImmutableMap<String, Method> functionMap =
        ImmutableMap.of(
            "testFunctionJoinListOfStrings",
            AfcUtilTest.class.getMethod("testFunctionJoinListOfStrings", java.util.List.class));
    Content content = Content.builder().parts(Part.builder().functionCall(functionCall)).build();
    GenerateContentResponse response =
        GenerateContentResponse.builder().candidates(Candidate.builder().content(content)).build();
    ImmutableList<Part> functionResponseParts =
        AfcUtil.getFunctionResponseParts(response, functionMap);
    ImmutableList<Part> expectedFunctionResponseParts =
        ImmutableList.of(
            Part.fromFunctionResponse(
                "testFunctionJoinListOfStrings", ImmutableMap.of("result", "a,b,c")));
    assertEquals(1, functionResponseParts.size());
    assertEquals(expectedFunctionResponseParts.toString(), functionResponseParts.toString());
  }

  @Test
  public void getFunctionResponseParts_withListOfIntsParameter_returnsFunctionResponseParts()
      throws NoSuchMethodException {
    FunctionCall functionCall =
        FunctionCall.builder()
            .name("testFunctionSumListOfIntegers")
            .args(ImmutableMap.of("items", ImmutableList.of(1, 2, 3)))
            .build();
    ImmutableMap<String, Method> functionMap =
        ImmutableMap.of(
            "testFunctionSumListOfIntegers",
            AfcUtilTest.class.getMethod("testFunctionSumListOfIntegers", java.util.List.class));
    Content content = Content.builder().parts(Part.builder().functionCall(functionCall)).build();
    GenerateContentResponse response =
        GenerateContentResponse.builder().candidates(Candidate.builder().content(content)).build();
    ImmutableList<Part> functionResponseParts =
        AfcUtil.getFunctionResponseParts(response, functionMap);
    ImmutableList<Part> expectedFunctionResponseParts =
        ImmutableList.of(
            Part.fromFunctionResponse(
                "testFunctionSumListOfIntegers", ImmutableMap.of("result", 6)));
    assertEquals(1, functionResponseParts.size());
    assertEquals(expectedFunctionResponseParts.toString(), functionResponseParts.toString());
  }

  @Test
  public void getFunctionResponseParts_withListOfListsParameter_returnsFunctionResponseParts()
      throws NoSuchMethodException {
    FunctionCall functionCall =
        FunctionCall.builder()
            .name("testFunctionReturnListOfStrings")
            .args(
                ImmutableMap.of(
                    "items",
                    ImmutableList.of(ImmutableList.of("a", "b"), ImmutableList.of("c", "d"))))
            .build();
    ImmutableMap<String, Method> functionMap =
        ImmutableMap.of(
            "testFunctionReturnListOfStrings",
            AfcUtilTest.class.getMethod("testFunctionReturnListOfStrings", java.util.List.class));
    Content content = Content.builder().parts(Part.builder().functionCall(functionCall)).build();
    GenerateContentResponse response =
        GenerateContentResponse.builder().candidates(Candidate.builder().content(content)).build();
    ImmutableList<Part> functionResponseParts =
        AfcUtil.getFunctionResponseParts(response, functionMap);
    ImmutableList<Part> expectedFunctionResponseParts =
        ImmutableList.of(
            Part.fromFunctionResponse(
                "testFunctionReturnListOfStrings",
                ImmutableMap.of("result", ImmutableList.of("ab", "cd"))));
    assertEquals(1, functionResponseParts.size());
    assertEquals(expectedFunctionResponseParts.toString(), functionResponseParts.toString());
  }

  @Test
  public void hasCallableTool_nullConfig_returnsFalse() {
    boolean hasCallableTool = AfcUtil.hasCallableTool(null);
    assertEquals(false, hasCallableTool);
  }

  @Test
  public void hasCallableTool_emptyTools_returnsFalse() {
    GenerateContentConfig config = GenerateContentConfig.builder().build();
    boolean hasCallableTool = AfcUtil.hasCallableTool(config);
    assertEquals(false, hasCallableTool);
  }

  @Test
  public void hasCallableTool_noFunctions_returnsFalse() {
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .tools(Tool.builder().functionDeclarations(testFunctionDeclaration1))
            .build();
    boolean hasCallableTool = AfcUtil.hasCallableTool(config);
    assertEquals(false, hasCallableTool);
  }

  @Test
  public void hasCallableTool_returnsTrue() throws NoSuchMethodException {
    Method testMethod1 = AfcUtilTest.class.getMethod("testFunction1", String.class);
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .tools(
                Tool.builder()
                    .functionDeclarations(testFunctionDeclaration2)
                    .functions(testMethod1))
            .build();
    boolean hasCallableTool = AfcUtil.hasCallableTool(config);
    assertEquals(true, hasCallableTool);
  }

  @Test
  public void findAfcIncompatibleToolIndexes_withNullConfig_returnsEmptyList() {
    // Act
    ImmutableList<Integer> result = AfcUtil.findAfcIncompatibleToolIndexes(null);

    // Assert
    assertEquals(0, result.size());
  }

  @Test
  public void findAfcIncompatibleToolIndexes_withNoToolsPresent_returnsEmptyList() {
    // Arrange
    GenerateContentConfig config = GenerateContentConfig.builder().build();

    // Act
    ImmutableList<Integer> result = AfcUtil.findAfcIncompatibleToolIndexes(config);

    // Assert
    assertEquals(0, result.size());
  }

  @Test
  public void findAfcIncompatibleToolIndexes_withEmptyToolsList_returnsEmptyList() {
    // Arrange
    GenerateContentConfig config = GenerateContentConfig.builder().tools(Tool.builder()).build();
    // Act
    ImmutableList<Integer> result = AfcUtil.findAfcIncompatibleToolIndexes(config);

    // Assert
    assertEquals(0, result.size());
  }

  @Test
  public void findAfcIncompatibleToolIndexes_withOnlyCompatibleTools_returnsEmptyList()
      throws NoSuchMethodException {
    // Arrange
    Method testMethod1 = AfcUtilTest.class.getMethod("testFunction1", String.class);
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .tools(Tool.builder().functions(testMethod1))
            .tools(Tool.builder().googleSearch(GoogleSearch.builder()))
            .tools(Tool.builder().googleSearchRetrieval(GoogleSearchRetrieval.builder()))
            .tools(Tool.builder().retrieval(Retrieval.builder()))
            .tools(Tool.builder().googleMaps(GoogleMaps.builder()))
            .tools(Tool.builder().urlContext(UrlContext.builder()))
            .tools(Tool.builder().codeExecution(ToolCodeExecution.builder()))
            .tools(Tool.builder().computerUse(ComputerUse.builder()))
            .tools(Tool.builder().enterpriseWebSearch(EnterpriseWebSearch.builder()))
            .build();

    // Act
    ImmutableList<Integer> result = AfcUtil.findAfcIncompatibleToolIndexes(config);

    // Assert
    assertEquals(0, result.size());
  }

  @Test
  public void findAfcIncompatibleToolIndexes_withOnlyOneIncompatibleTools_returnsAllIndexes()
      throws NoSuchMethodException {
    // Arrange
    Method testMethod1 = AfcUtilTest.class.getMethod("testFunction1", String.class);
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .tools(
                Tool.builder().functions(testMethod1).build(),
                Tool.builder().googleSearch(GoogleSearch.builder()).build(),
                Tool.builder().googleSearchRetrieval(GoogleSearchRetrieval.builder()).build(),
                Tool.builder().retrieval(Retrieval.builder()).build(),
                Tool.builder().googleMaps(GoogleMaps.builder()).build(),
                Tool.builder().urlContext(UrlContext.builder()).build(),
                Tool.builder().codeExecution(ToolCodeExecution.builder()).build(),
                Tool.builder().computerUse(ComputerUse.builder()).build(),
                Tool.builder().enterpriseWebSearch(EnterpriseWebSearch.builder()).build(),
                Tool.builder()
                    .functionDeclarations(testFunctionDeclaration1, testFunctionDeclaration2)
                    .build())
            .build();

    // Act
    ImmutableList<Integer> result = AfcUtil.findAfcIncompatibleToolIndexes(config);

    // Assert
    assertEquals(1, result.size());
    assertEquals(9, result.get(0).intValue());
  }

  @Test
  void findAfcIncompatibleToolIndexes_withMixedTools_returnsOnlyIncompatibleIndexes()
      throws NoSuchMethodException {
    // Arrange
    Method testMethod1 = AfcUtilTest.class.getMethod("testFunction1", String.class);
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .tools(
                Tool.builder().functions(testMethod1).build(),
                Tool.builder().googleSearch(GoogleSearch.builder()).build(),
                Tool.builder().functionDeclarations(testFunctionDeclaration1).build(),
                Tool.builder().googleSearchRetrieval(GoogleSearchRetrieval.builder()).build(),
                Tool.builder().retrieval(Retrieval.builder()).build(),
                Tool.builder().googleMaps(GoogleMaps.builder()).build(),
                Tool.builder().urlContext(UrlContext.builder()).build(),
                Tool.builder().codeExecution(ToolCodeExecution.builder()).build(),
                Tool.builder().computerUse(ComputerUse.builder()).build(),
                Tool.builder().enterpriseWebSearch(EnterpriseWebSearch.builder()).build(),
                Tool.builder()
                    .functionDeclarations(testFunctionDeclaration1, testFunctionDeclaration2)
                    .build())
            .build();

    // Act
    ImmutableList<Integer> result = AfcUtil.findAfcIncompatibleToolIndexes(config);

    // Assert
    assertEquals(2, result.size());
    assertEquals(2, result.get(0).intValue());
    assertEquals(10, result.get(1).intValue());
  }

  @Test
  void findAfcIncompatibleToolIndexes_withSercerSideMcpTools_returnsOnlyIncompatibleIndexes()
      throws NoSuchMethodException {
    // Arrange
    Method testMethod1 = AfcUtilTest.class.getMethod("testFunction1", String.class);
    GenerateContentConfig config =
        GenerateContentConfig.builder()
            .tools(
                Tool.builder().functions(testMethod1).build(),
                Tool.builder().googleSearch(GoogleSearch.builder()).build(),
                Tool.builder().functionDeclarations(testFunctionDeclaration1).build(),
                Tool.builder().googleSearchRetrieval(GoogleSearchRetrieval.builder()).build(),
                Tool.builder()
                    .mcpServers(ImmutableList.of(McpServer.builder().name("test").build()))
                    .build(),
                Tool.builder().googleMaps(GoogleMaps.builder()).build(),
                Tool.builder().urlContext(UrlContext.builder()).build(),
                Tool.builder().codeExecution(ToolCodeExecution.builder()).build(),
                Tool.builder().computerUse(ComputerUse.builder()).build(),
                Tool.builder().enterpriseWebSearch(EnterpriseWebSearch.builder()).build(),
                Tool.builder()
                    .functionDeclarations(testFunctionDeclaration1, testFunctionDeclaration2)
                    .build())
            .build();

    // Act
    ImmutableList<Integer> result = AfcUtil.findAfcIncompatibleToolIndexes(config);

    // Assert
    assertEquals(3, result.size());
    assertEquals(2, result.get(0).intValue());
    assertEquals(4, result.get(1).intValue());
    assertEquals(10, result.get(2).intValue());
  }
}
