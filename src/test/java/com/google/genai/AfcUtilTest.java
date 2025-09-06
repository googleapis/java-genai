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
 * example test
 */

package com.google.genai;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.genai.types.AutomaticFunctionCallingConfig;
import com.google.genai.types.Candidate;
import com.google.genai.types.Content;
import com.google.genai.types.FunctionCall;
import com.google.genai.types.FunctionDeclaration;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import com.google.genai.types.Schema;
import com.google.genai.types.Tool;
import com.google.genai.types.Type;
import java.lang.reflect.Method;
import org.junit.jupiter.api.Test;

public final class AfcUtilTest {
  public static String testFunction1(String input) {
    return input + "testFunction1";
  }

  public static Integer testFunction2(Integer a, Integer b) {
    return a / b;
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
}
