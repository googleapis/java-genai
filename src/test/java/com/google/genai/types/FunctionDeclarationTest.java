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
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.google.common.collect.ImmutableList;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class FunctionDeclarationTest {
  private static final String FUNCTION_NAME = "functionName";
  private static final String FUNCTION_DESCRIPTION = "functionDescription";
  private static final String STRING_PARAM_NAME = "stringParam";
  private static final String INTEGER_PARAM_NAME = "integerParam";
  private static final String DOUBLE_PARAM_NAME = "doubleParam";
  private static final String FLOAT_PARAM_NAME = "floatParam";
  private static final String BOOLEAN_PARAM_NAME = "booleanParam";
  private static final ImmutableList<String> REQUIRED_PARAM_NAMES =
      ImmutableList.of(
          STRING_PARAM_NAME,
          INTEGER_PARAM_NAME,
          DOUBLE_PARAM_NAME,
          FLOAT_PARAM_NAME,
          BOOLEAN_PARAM_NAME);

  private static final FunctionDeclaration EXPECTED_FUNCTION_DECLARATION =
      FunctionDeclaration.builder()
          .name(FUNCTION_NAME)
          .description(FUNCTION_DESCRIPTION)
          .parameters(
              Schema.builder()
                  .type(Type.Known.OBJECT)
                  .properties(buildPropertiesMap())
                  .required(REQUIRED_PARAM_NAMES))
          .build();

  /** Helper method to build the properties map. */
  private static Map<String, Schema> buildPropertiesMap() {
    Map<String, Schema> properties = new HashMap<>();
    properties.put(
        STRING_PARAM_NAME,
        Schema.builder().type(Type.Known.STRING).title(STRING_PARAM_NAME).build());
    properties.put(
        INTEGER_PARAM_NAME,
        Schema.builder().type(Type.Known.INTEGER).title(INTEGER_PARAM_NAME).build());
    properties.put(
        DOUBLE_PARAM_NAME,
        Schema.builder().type(Type.Known.NUMBER).title(DOUBLE_PARAM_NAME).build());
    properties.put(
        FLOAT_PARAM_NAME, Schema.builder().type(Type.Known.NUMBER).title(FLOAT_PARAM_NAME).build());
    properties.put(
        BOOLEAN_PARAM_NAME,
        Schema.builder().type(Type.Known.BOOLEAN).title(BOOLEAN_PARAM_NAME).build());
    return properties;
  }

  /** A function (static method) to test fromMethod functionalities. */
  public static int functionName(
      String stringParam,
      int integerParam,
      double doubleParam,
      float floatParam,
      boolean booleanParam) {
    return 0;
  }

  /** An instance method to test fromMethod. */
  public int instanceMethod(String stringParam) {
    return 1;
  }

  /** A function with invalid parameter type to test fromMethod. */
  public static int functionWithInvalidType(Object objectParam) {
    return 2;
  }

  @Disabled(
      "Skipping because configured compiler args to include parameters for the purpose of"
          + " automatically parsing a Java method into a FunctionDeclaration.")
  @Test
  public void fromMethodWithoutParameterNamesWithoutReflection_throwsIllegalStateException()
      throws NoSuchMethodException {
    Method method =
        FunctionDeclarationTest.class.getMethod(
            FUNCTION_NAME, String.class, int.class, double.class, float.class, boolean.class);

    IllegalStateException thrown =
        assertThrows(
            IllegalStateException.class,
            () -> FunctionDeclaration.fromMethod(FUNCTION_DESCRIPTION, method));
    assertEquals(
        "Failed to retrieve the parameter name from reflection. Please compile your"
            + " code with the \"-parameters\" flag or provide parameter names manually.",
        thrown.getMessage());
  }

  @Test
  public void fromMethodWithParameterNames_returnsFunctionDeclaration()
      throws NoSuchMethodException {
    Method method =
        FunctionDeclarationTest.class.getMethod(
            FUNCTION_NAME, String.class, int.class, double.class, float.class, boolean.class);

    FunctionDeclaration functionDeclaration =
        FunctionDeclaration.fromMethod(
            FUNCTION_DESCRIPTION,
            method,
            STRING_PARAM_NAME,
            INTEGER_PARAM_NAME,
            DOUBLE_PARAM_NAME,
            FLOAT_PARAM_NAME,
            BOOLEAN_PARAM_NAME);

    assertEquals(EXPECTED_FUNCTION_DECLARATION.toString(), functionDeclaration.toString());
  }

  @Test
  public void fromMethodWithInstanceMethod_throwsIllegalArgumentException()
      throws NoSuchMethodException {
    Method method = FunctionDeclarationTest.class.getMethod("instanceMethod", String.class);

    IllegalArgumentException thrown =
        assertThrows(
            IllegalArgumentException.class,
            () -> FunctionDeclaration.fromMethod(FUNCTION_DESCRIPTION, method, STRING_PARAM_NAME));
    assertEquals(
        "Instance methods are not supported. Please use static methods.", thrown.getMessage());
  }

  @Test
  public void fromMethodWithInvalidParameterType_throwsIllegalArgumentException()
      throws NoSuchMethodException {
    Method method =
        FunctionDeclarationTest.class.getMethod("functionWithInvalidType", Object.class);

    IllegalArgumentException thrown =
        assertThrows(
            IllegalArgumentException.class,
            () -> FunctionDeclaration.fromMethod(FUNCTION_DESCRIPTION, method, "objectParam"));
    assertEquals(
        "Unsupported parameter type "
            + Object.class.getName()
            + " for parameter objectParam. Currently, supported types are String, boolean, Boolean,"
            + " int, Integer, Long, double, Double, float, Float.",
        thrown.getMessage());
  }

  @Test
  public void fromMethodWithUnmatchedParameterNames_throwsIllegalArgumentException()
      throws NoSuchMethodException {
    Method method =
        FunctionDeclarationTest.class.getMethod(
            FUNCTION_NAME, String.class, int.class, double.class, float.class, boolean.class);

    IllegalArgumentException thrown =
        assertThrows(
            IllegalArgumentException.class,
            () -> FunctionDeclaration.fromMethod(FUNCTION_DESCRIPTION, method, STRING_PARAM_NAME));
    assertEquals(
        "The number of parameter names passed to the orderedParameterNames"
            + " argument does not match the number of parameters in the method.",
        thrown.getMessage());
  }

  @Test
  public void testClearMethods() {
    FunctionDeclaration functionDeclaration =
        FunctionDeclaration.builder().name(FUNCTION_NAME).description(FUNCTION_DESCRIPTION).build();

    assertEquals(FUNCTION_NAME, functionDeclaration.name().get());
    assertEquals(FUNCTION_DESCRIPTION, functionDeclaration.description().get());

    functionDeclaration = functionDeclaration.toBuilder().clearName().clearDescription().build();

    assertFalse(functionDeclaration.name().isPresent());
    assertFalse(functionDeclaration.description().isPresent());
  }
}
