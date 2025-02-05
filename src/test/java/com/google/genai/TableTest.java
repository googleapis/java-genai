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

/** Runs the test table. */
package com.google.genai;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import com.google.genai.types.TestTableFile;
import com.google.genai.types.TestTableItem;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

/** Sample class to prototype GenAI SDK functionalities. */
public final class TableTest {
  private static String snakeToPascal(String s) {
    String[] parts = s.split("_");
    for (int i = 0; i < parts.length; i++) {
      parts[i] = parts[i].substring(0, 1).toUpperCase() + parts[i].substring(1);
    }
    return String.join("", parts);
  }

  private static String snakeToCamel(String str) {
    if (str == null || str.isEmpty()) {
      return str;
    }

    StringBuilder sb = new StringBuilder();
    boolean capitalizeNext = false;
    for (char c : str.toCharArray()) {
      if (c == '_') {
        capitalizeNext = true;
      } else {
        sb.append(capitalizeNext ? Character.toUpperCase(c) : c);
        capitalizeNext = false;
      }
    }
    return sb.toString();
  }

  private static Collection<DynamicTest> createTableTests(String path, Client client)
      throws IOException {
    String suffix = client.isVertexAI() ? "vertex" : "mldev";

    // Reads JSON.
    String data = Files.readString(Paths.get(path));
    TestTableFile testTableFile = TestTableFile.fromJson(data);

    // Gets module name and method name.
    String testMethod = testTableFile.testMethod().get();
    String[] segments = testMethod.split("\\.");
    if (segments.length != 2) {
      throw new RuntimeException("Invalid test method: " + testMethod);
    }
    String originalModuleName = segments[0];
    String moduleName = snakeToCamel(originalModuleName);
    String originalMethodName = segments[1];
    String methodName = snakeToCamel(originalMethodName);

    String[] replayPathSegments = path.split("/");
    String replayFilePath = "";
    for (int i = 0; i < replayPathSegments.length; i++) {
      if (replayPathSegments[i].endsWith("_test_table.json")) {
        break;
      }
      replayFilePath += replayPathSegments[i] + "/";
    }
    System.out.println("replayFilePath: " + replayFilePath);

    System.out.println("Running test table: " + testMethod);

    // Finds the module and methods.
    Field module = null;
    List<Method> methods = new ArrayList<>();
    try {
      module = Client.class.getDeclaredField(moduleName);
      Class<?> moduleClass = module.getType();
      for (Method candidate : moduleClass.getMethods()) {
        if (candidate.getName().equals(methodName)) {
          methods.add(candidate);
        }
      }
      if (methods.isEmpty()) {
        throw new NoSuchMethodException();
      }
    } catch (NoSuchFieldException | NoSuchMethodException e) {
      String msg = "    === Skipped: method " + testMethod + " is not supported in Java";
      String testName = String.format("%s.%s.%s", originalModuleName, originalMethodName, suffix);
      return Collections.singletonList(
          DynamicTest.dynamicTest(
              testName,
              () -> {
                System.out.println(msg);
                assumeTrue(false, msg);
              }));
    }

    List<DynamicTest> dynamicTests = new ArrayList<>();
    // Processes.
    for (TestTableItem testTableItem : testTableFile.testTable().get()) {
      String testName =
          String.format(
              "%s.%s.%s.%s",
              originalModuleName, originalMethodName, testTableItem.name().get(), suffix);
      int testTableIndex = path.lastIndexOf("/_test_table.json");
      int replaysTestsIndex = path.lastIndexOf("/replays/tests/");
      String testDirectory =
          path.substring(replaysTestsIndex + "/replays/tests/".length(), testTableIndex);
      String replayId = testTableItem.name().get();
      if (testTableItem.overrideReplayId().isPresent()) {
        replayId = testTableItem.overrideReplayId().get();
      }
      client.setReplayId(testDirectory + "/" + replayId + "." + suffix + ".json");
      List<String> parameterNames = testTableFile.parameterNames().get();
      dynamicTests.addAll(
          createTestCases(testName, testTableItem, module, client, methods, parameterNames));
    }
    return dynamicTests;
  }

  private static Collection<DynamicTest> createTestCases(
      String testName,
      TestTableItem testTableItem,
      Field module,
      Client client,
      List<Method> methods,
      List<String> parameterNames) {

    System.out.printf("    === Calling method: %s\n", testName);
    if (testTableItem.hasUnion().isPresent() && testTableItem.hasUnion().get()) {
      String msg = "    === Skipped: parameters contain unsupported union type";
      return Collections.singletonList(
          DynamicTest.dynamicTest(
              testName,
              () -> {
                System.out.println(msg);
                assumeTrue(false, msg);
              }));
    }

    Map<String, Object> fromParameters = testTableItem.parameters().get();

    List<DynamicTest> dynamicTests = new ArrayList<>();
    // Iterate through overloading methods and find a match.
    for (Method method : methods) {
      try {
        List<Object> parameters = new ArrayList<>();
        for (int i = 0; i < parameterNames.size(); i++) {
          // May throw IndexOutOfBoundsException here
          String parameterName = parameterNames.get(i);
          Object fromValue = fromParameters.getOrDefault(parameterName, null);
          // May throw IllegalArgumentException here
          parameters.add(
              JsonSerializable.objectMapper.convertValue(fromValue, method.getParameterTypes()[i]));
        }
        dynamicTests.add(
            DynamicTest.dynamicTest(
                testName,
                () -> {
                  try {
                    // May throw IllegalAccessException or InvocationTargetException here
                    // InvocationTargetException is sometimes expected, when exceptionIfMldev or
                    // exceptionIfVertex is present.
                    Object response = method.invoke(module.get(client), parameters.toArray());
                  } catch (IllegalAccessException | InvocationTargetException e) {
                    // Handle expected exceptions here
                    Optional<String> skipInApiMode = testTableItem.skipInApiMode();
                    Optional<String> exceptionIfMldev = testTableItem.exceptionIfMldev();
                    Optional<String> exceptionIfVertex = testTableItem.exceptionIfVertex();
                    if (skipInApiMode.isPresent()
                        && (client.clientMode().equals("api") || client.clientMode().isEmpty())) {
                      System.out.printf("    === Skipped: %s\n", skipInApiMode.get());
                      assumeTrue(false, String.format("Skipped: %s", skipInApiMode.get()));
                    }
                    // TODO(jayceeli): Check if the error message is expected, once we update the
                    // error
                    // messages from the ApiClient.
                    if (exceptionIfMldev.isPresent() && !client.isVertexAI()) {
                      assumeTrue(false, "Skipped: test case is expected to fail in MLDev");
                      System.out.println("    === Skipped: test case is expected to fail in MLDev");
                    } else if (exceptionIfVertex.isPresent() && client.isVertexAI()) {
                      assumeTrue(false, "Skipped: test case is expected to fail in Vertex AI");
                      System.out.println(
                          "    === Skipped: test case is expected to fail in Vertex AI");
                    }
                    e.printStackTrace();
                    fail(String.format("'%s' failed: %s", testName, e));
                  }
                }));
      } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
        // Method parameters do not match, continue to the next overloading method
      }

      // TODO(jayceeli): Remove this once replay mode is fully functional.
      if (testName.contains(
          "models.generate_content.test_logprobs_zero_with_response_logprobs_true.vertex")) {
        String msg = "    === Skipped: test case is expected to fail in Vertex AI";
        dynamicTests.add(
            DynamicTest.dynamicTest(
                testName,
                () -> {
                  assumeTrue(false, msg);
                }));
      }
    }
    if (dynamicTests.isEmpty()) {
      String msg =
          String.format(
              "Could not find a method '%s' that matches the parameters: %s",
              testName, fromParameters);
      dynamicTests.add(DynamicTest.dynamicTest(testName, () -> fail(msg)));
    }
    return dynamicTests;
  }

  private static String getReplayFilePath(String testName) {
    String[] replayPathSegments = testName.split("\\.");
    String replayFilePath = "";
    for (int i = 0; i < replayPathSegments.length; i++) {
      if (replayPathSegments[i].endsWith("_test_table.json")) {
        break;
      }
      replayFilePath += replayPathSegments[i] + "/";
    }
    return replayFilePath;
  }

  @TestFactory
  @DisplayName("TableTest")
  Collection<DynamicTest> createTests() throws IOException {
    String replaysPath = System.getenv("GOOGLE_GENAI_REPLAYS_DIRECTORY");
    if (replaysPath == null) {
      throw new RuntimeException("GOOGLE_GENAI_REPLAYS_DIRECTORY is not set");
    }
    String testsReplaysPath = replaysPath + "/tests";
    String replayMode = System.getenv("GOOGLE_GENAI_CLIENT_MODE");
    if (replayMode == null) {
      // TODO(b/369384123): Make the default replay mode or auto once loading replays is complete.
      replayMode = "api";
    }

    DebugConfig debugConfig = new DebugConfig(replayMode, "", testsReplaysPath);
    Client mlDevClient = Client.builder().vertexAI(false).debugConfig(debugConfig).build();
    Client vertexClient = Client.builder().vertexAI(true).debugConfig(debugConfig).build();

    Collection<DynamicTest> dynamicTests = new ArrayList<>();
    Files.walk(Paths.get(testsReplaysPath))
        .filter(Files::isRegularFile)
        .filter(path -> path.toString().endsWith("/_test_table.json"))
        .forEach(
            path -> {
              try {
                dynamicTests.addAll(createTableTests(path.toString(), mlDevClient));
                dynamicTests.addAll(createTableTests(path.toString(), vertexClient));
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
            });
    return dynamicTests;
  }
}
