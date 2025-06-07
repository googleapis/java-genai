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

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.ImmutableSet;
import com.google.genai.types.HttpOptions;
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
import java.util.HashMap;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.WireMockServer;

/** Sample class to prototype GenAI SDK functionalities. */
public final class TableTest {

  // TODO(b/384946033): Automate url safe parameter names retrieval.
  private static final ImmutableSet<String> URL_SAFE_BASE64_PARAMETER_NAMES =
      ImmutableSet.of("image.imageBytes");

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

  private static Collection<DynamicTest> createTableTests(String path, boolean vertexAI)
      throws IOException {
    String suffix = vertexAI ? "vertex" : "mldev";

    // Reads JSON.
    String data = ReplayApiClient.readString(Paths.get(path));
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
      for (Method candidate : moduleClass.getDeclaredMethods()) {
        if (candidate.getName().equals(methodName)) {
          methods.add(candidate);
        }
        if (methodName.equals("embedContent")
            && candidate.getName().equals("privateEmbedContent")) {
          candidate.setAccessible(true);
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
      String clientReplayId = testDirectory + "/" + replayId + "." + suffix + ".json";
      List<String> parameterNames = testTableFile.parameterNames().get();
      // TODO(b/417799716): Fix generate_image tests in API mode.
      if (testName.contains("models.generate_images.test_simple_prompt_001.mldev")) {
        continue;
      }
      dynamicTests.addAll(
          createTestCases(
              testName, testTableItem, module, vertexAI, methods, parameterNames, clientReplayId));
    }
    return dynamicTests;
  }

  @SuppressWarnings("unchecked")
  private static Collection<DynamicTest> createTestCases(
      String testName,
      TestTableItem testTableItem,
      Field module,
      boolean vertexAI,
      List<Method> methods,
      List<String> parameterNames,
      String replayId) {

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
    // Edit image ReferenceImages are not correctly deserialized for replay tests
    if (testName.contains("models.edit_image")
        || testName.contains("models.generate_content.test_speech_with_config")
        || testName.contains(
            "models.generate_content.test_logprobs_zero_with_response_logprobs_true")) {
      String msg = "    === Skipped: replay tests are not supported for edit_image";
      return Collections.singletonList(
          DynamicTest.dynamicTest(
              testName,
              () -> {
                System.out.println(msg);
                assumeTrue(false, msg);
              }));
    }

    Map<String, Object> fromParameters = (Map<String, Object>) normalizeKeys((Map<String, Object>) testTableItem.parameters().get());
    ReplaySanitizer.sanitizeMapByPath(
        fromParameters, "image.imageBytes", new ReplayBase64Sanitizer(), false);
    // TODO(b/403368643): Support interface param types in Java replay tests.
    // ReplaySanitizer.sanitizeMapByPath(
    // fromParameters,
    // "[]referenceImages.referenceImage.imageBytes",
    // new ReplayBase64Sanitizer(),
    // true);

    List<DynamicTest> dynamicTests = new ArrayList<>();
    // Iterate through overloading methods and find a match.
    for (Method method : methods) {
      try {
        List<Object> parameters = new ArrayList<>();
        for (int i = 0; i < parameterNames.size(); i++) {
          // May throw IndexOutOfBoundsException here
          String parameterName = snakeToCamel(parameterNames.get(i));
          Object fromValue = fromParameters.getOrDefault(parameterName, null);
          // May throw IllegalArgumentException here
          Object parameter =
              JsonSerializable.objectMapper.convertValue(fromValue, method.getParameterTypes()[i]);
          if (method.getName().equals("embedContent") && parameter instanceof List) {
            throw new IllegalArgumentException();
          }
          parameters.add(parameter);
        }
        dynamicTests.add(
            DynamicTest.dynamicTest(
                testName,
                () -> {
                  final Map<String, Object>[] normalizedReplayData = new Map[1];
                  final Map<String, Object>[] normalizedRequestData = new Map[1];
                  WireMockServer wireMockServer = createMockServer(vertexAI);
                  Map<String, Object> replayData = ReplayApiClient.loadReplayData(replayId);
                  Client client = createClient(vertexAI, wireMockServer);

                  // Capture the request and store it to normalizedReplayData and
                  // normalizedRequestData
                  // so that we can check it later.
                  wireMockServer.addMockServiceRequestListener(
                      (request, response) -> {
                        try {
                          Map<String, Object> requestData =
                              JsonSerializable.objectMapper.readValue(
                                  request.getBodyAsString(),
                                  new TypeReference<Map<String, Object>>() {});
                          normalizedReplayData[0] = (Map<String, Object>) normalizeKeys(replayData);
                          normalizedRequestData[0] =
                              (Map<String, Object>) normalizeKeys(requestData);
                        } catch (IOException e) {
                          throw new RuntimeException(e);
                        }
                      });

                  try {
                    client.setReplayId(replayId);

                    // May throw IllegalAccessException or InvocationTargetException here
                    // InvocationTargetException is sometimes expected, when exceptionIfMldev or
                    // exceptionIfVertex is present.
                    Object response = method.invoke(module.get(client), parameters.toArray());

                    Object expectedRequest =
                        getAtPath(
                            normalizedReplayData[0],
                            "interactions",
                            0,
                            "request",
                            "bodySegments",
                            0);
                    assertEquals(
                        expectedRequest,
                        normalizedRequestData[0],
                        "Normalized request data should match expected data from replay.");
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
                    if (exceptionIfMldev.isPresent() && !client.vertexAI()) {
                      assumeTrue(false, "Skipped: test case is expected to fail in MLDev");
                      System.out.println("    === Skipped: test case is expected to fail in MLDev");
                    } else if (exceptionIfVertex.isPresent() && client.vertexAI()) {
                      assumeTrue(false, "Skipped: test case is expected to fail in Vertex AI");
                      System.out.println(
                          "    === Skipped: test case is expected to fail in Vertex AI");
                    }
                    e.printStackTrace();
                    fail(String.format("'%s' failed: %s", testName, e));
                  } finally {
                    wireMockServer.stop();
                    client.close();
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
    Collection<DynamicTest> dynamicTests = new ArrayList<>();
    Files.walk(Paths.get(testsReplaysPath))
        .filter(Files::isRegularFile)
        .filter(path -> path.toString().endsWith("/_test_table.json"))
        .forEach(
            path -> {
              try {
                dynamicTests.addAll(createTableTests(path.toString(), false));
                dynamicTests.addAll(createTableTests(path.toString(), true));
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
            });
    return dynamicTests;
  }

  static WireMockServer createMockServer(boolean vertexAI) {
    WireMockServer wireMockServer =
        new WireMockServer(WireMockConfiguration.options().dynamicPort());
    wireMockServer.start();
    WireMock.configureFor("localhost", wireMockServer.port());

    if (vertexAI) {
      wireMockServer.stubFor(
          any(anyUrl())
              .willReturn(aResponse().proxiedFrom("https://us-central1-aiplatform.googleapis.com"))
              .atPriority(1));
    } else {
      wireMockServer.stubFor(
          any(anyUrl())
              .willReturn(aResponse().proxiedFrom("https://generativelanguage.googleapis.com"))
              .atPriority(1));
    }
    return wireMockServer;
  }

  static Client createClient(boolean vertexAI, WireMockServer wireMockServer) {
    String replaysPath = System.getenv("GOOGLE_GENAI_REPLAYS_DIRECTORY");
    if (replaysPath == null) {
      throw new RuntimeException("GOOGLE_GENAI_REPLAYS_DIRECTORY is not set");
    }
    String testsReplaysPath = replaysPath + "/tests";
    String replayMode = System.getenv("GOOGLE_GENAI_CLIENT_MODE");
    if (replayMode == null) {
      // TODO(b/369384123): Make the default replay mode or auto once loading replays
      // is complete.
      replayMode = "api";
    }
    DebugConfig debugConfig = new DebugConfig(replayMode, "", testsReplaysPath);

    return Client.builder()
        .vertexAI(vertexAI)
        .debugConfig(debugConfig)
        .httpOptions(
            HttpOptions.builder().baseUrl("http://localhost:" + wireMockServer.port()).build())
        .build();
  }

  private static Object normalizeKeys(Object data) {
    if (data instanceof Map) {
      @SuppressWarnings("unchecked")
      Map<String, Object> originalMap = (Map<String, Object>) data;
      Map<String, Object> newNormalizedMap = new HashMap<>();
      for (Map.Entry<String, Object> entry : originalMap.entrySet()) {
        String normalizedKey = snakeToCamel(entry.getKey());
        Object normalizedValue = normalizeKeys(entry.getValue());
        newNormalizedMap.put(normalizedKey, normalizedValue);
      }
      return newNormalizedMap;
    } else if (data instanceof List) {
      @SuppressWarnings("unchecked")
      List<Object> originalList = (List<Object>) data;
      List<Object> newNormalizedList = new ArrayList<>(originalList.size());
      for (Object item : originalList) {
        newNormalizedList.add(normalizeKeys(item));
      }
      return newNormalizedList;
    } else {
      return data; // Return as is for non-map/non-list types
    }
  }

  private static Object getAtPath(Object root, Object... pathComponents) {
    Object current = root;
    for (Object component : pathComponents) {
      if (current == null) {
        return null;
      }

      if (component instanceof String) {
        String key = (String) component;
        if (current instanceof Map) {
          current = ((Map<?, ?>) current).get(key);
        } else {
          return null;
        }
      } else if (component instanceof Integer) {
        Integer index = (Integer) component;
        if (current instanceof List && index >= 0 && index < ((List<?>) current).size()) {
          current = ((List<?>) current).get(index);
        } else {
          return null;
        }
      } else {
        return null;
      }
    }
    return current;
  }

}
