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

import com.google.common.collect.ImmutableMap;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

/** Sample class to prototype GenAI SDK functionalities. */
public final class TableTest {

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
      String msg = " => Test skipped: multistep test " + testMethod + " not supported in Java";
      List<DynamicTest> dynamicTests = new ArrayList<>();
      for (TestTableItem testTableItem : testTableFile.testTable().get()) {
        String testName =
            String.format("%s.%s.%s", testMethod, testTableItem.name().get(), suffix);
        dynamicTests.add(DynamicTest.dynamicTest(testName + msg, () -> {}));
      }
      return dynamicTests;
    }
    String originalModuleName = segments[0];
    String moduleName = Common.snakeToCamel(originalModuleName);
    String originalMethodName = segments[1];
    String methodName = Common.snakeToCamel(originalMethodName);

    if (methodName.equals("privateTune") && !vertexAI) {
      methodName = "privateTuneMldev";
    }

    String[] replayPathSegments = path.split("/");
    String replayFilePath = "";
    for (int i = 0; i < replayPathSegments.length; i++) {
      if (replayPathSegments[i].endsWith("_test_table.json")) {
        break;
      }
      replayFilePath += replayPathSegments[i] + "/";
    }

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
        } else if (methodName.equals("generateVideos")
            && candidate.getName().equals("privateGenerateVideos")) {
          candidate.setAccessible(true);
          methods.add(candidate);
        }
      }
      if (methods.isEmpty()) {
        throw new NoSuchMethodException();
      }
    } catch (NoSuchFieldException | NoSuchMethodException e) {
      String msg = " => Test skipped: method " + testMethod + " is not supported in Java";
      String testName = String.format("%s.%s.%s", originalModuleName, originalMethodName, suffix);
      return Collections.singletonList(DynamicTest.dynamicTest(testName + msg, () -> {}));
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

    Client client = createClient(vertexAI);
    if (testTableItem.hasUnion().isPresent() && testTableItem.hasUnion().get()) {
      String msg = " => Test skipped: parameters contain unsupported union type";
      return Collections.singletonList(DynamicTest.dynamicTest(testName + msg, () -> {}));
    }
    // Edit image ReferenceImages are not correctly deserialized for replay tests
    if (testName.contains("models.edit_image")
        || testName.contains("batches.create.test_with_image_blob")) { // TODO(b/431798111)
      String msg = " => Test skipped: replay tests are not supported for edit_image";
      return Collections.singletonList(DynamicTest.dynamicTest(testName + msg, () -> {}));
    }

    Map<String, Object> fromParameters = (Map<String, Object>) normalizeKeys((Map<String, Object>) testTableItem.parameters().get());
    ReplaySanitizer.sanitizeMapByPath(
        fromParameters, "image.imageBytes", new ReplayBase64Sanitizer(), false);
    ReplaySanitizer.sanitizeMapByPath(
        fromParameters, "source.image.imageBytes", new ReplayBase64Sanitizer(), false);
    ReplaySanitizer.sanitizeMapByPath(
        fromParameters,
        "source.scribbleImage.image.imageBytes",
        new ReplayBase64Sanitizer(),
        false);
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
          String parameterName = Common.snakeToCamel(parameterNames.get(i));
          Object fromValue = fromParameters.getOrDefault(parameterName, null);
          // May throw IllegalArgumentException here
          Object parameter =
              JsonSerializable.objectMapper.convertValue(fromValue, method.getParameterTypes()[i]);
          if (method.getName().equals("embedContent") && parameter instanceof List) {
            throw new IllegalArgumentException();
          }
          parameters.add(parameter);
        }
        Optional<String> skipInApiMode = testTableItem.skipInApiMode();
        if (skipInApiMode.isPresent()
            && (client.clientMode().equals("api") || client.clientMode().isEmpty())) {
          String msg = " => Test skipped: " + skipInApiMode.get();
          dynamicTests.add(DynamicTest.dynamicTest(testName + msg, () -> {}));
          continue;
        }
        dynamicTests.add(
            DynamicTest.dynamicTest(
                testName,
                () -> {
                  try {
                    client.setReplayId(replayId);
                    // May throw IllegalAccessException or InvocationTargetException here
                    // InvocationTargetException is sometimes expected, when exceptionIfMldev or
                    // exceptionIfVertex is present.
                    Object response = method.invoke(module.get(client), parameters.toArray());
                  } catch (IllegalAccessException
                      | InvocationTargetException
                      | IllegalArgumentException e) {
                    // Handle expected exceptions here
                    Optional<String> exceptionIfMldev = testTableItem.exceptionIfMldev();
                    Optional<String> exceptionIfVertex = testTableItem.exceptionIfVertex();
                    if (exceptionIfMldev.isPresent() && !client.vertexAI()) {
                      String exceptionMessage = e.getCause().getMessage();

                      // TODO(fix in future): hack for camelCase variable name mismatch with
                      // expected snake_case name in exception messages.
                      String geminiParameterException =
                          " parameter is not supported in Gemini API.";
                      if (exceptionMessage.endsWith(geminiParameterException)) {
                        // camel to snake case the variable name in the exception message.
                        String camelCaseVariable = exceptionMessage.split(" ")[0];
                        String snakeCaseVariable = Transformers.camelToSnake(camelCaseVariable);
                        exceptionMessage =
                            exceptionMessage.replace(camelCaseVariable, snakeCaseVariable);
                      }

                      if (!exceptionMessage.contains(exceptionIfMldev.get())) {
                        fail(
                            String.format(
                                "'%s' failed to match expected exception:\n"
                                    + "Expected exception: %s\n"
                                    + " Actual exception: %s\n",
                                testName, exceptionIfMldev.get(), e.getCause().getMessage()));
                      }
                    } else if (exceptionIfVertex.isPresent() && client.vertexAI()) {
                      String exceptionMessage = e.getCause().getMessage();

                      // TODO(fix in future): hack for camelCase variable name mismatch with
                      // expected snake_case name in exception messages.
                      String vertexParameterException = " parameter is not supported in Vertex AI.";
                      if (exceptionMessage.endsWith(vertexParameterException)) {
                        // camel to snake case the variable name in the exception message.
                        String camelCaseVariable = exceptionMessage.split(" ")[0];
                        String snakeCaseVariable = Transformers.camelToSnake(camelCaseVariable);
                        exceptionMessage =
                            exceptionMessage.replace(camelCaseVariable, snakeCaseVariable);
                      }

                      if (!exceptionMessage.contains(exceptionIfVertex.get())) {
                        fail(
                            String.format(
                                "'%s' failed to match expected exception:\n"
                                    + "Expected exception: %s\n"
                                    + " Actual exception: %s\n",
                                testName, exceptionIfVertex.get(), e.getCause().getMessage()));
                      }
                    } else {
                      fail(String.format("'%s' failed: %s", testName, e.getCause()));
                    }
                  } finally {
                    client.close();
                  }
                }));
      } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
        // Method parameters do not match, continue to the next overloading method
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
  @EnabledIfEnvironmentVariable(
      named = "GOOGLE_GENAI_REPLAYS_DIRECTORY",
      matches = ".*genai/replays.*")
  Collection<DynamicTest> createTests() throws IOException {
    String replaysPath = System.getenv("GOOGLE_GENAI_REPLAYS_DIRECTORY");
    String testsReplaysPath = replaysPath + "/tests";
    String testsSubDir = System.getenv("GOOGLE_GENAI_TESTS_SUBDIR");
    if (testsSubDir != null) {
      testsReplaysPath += "/" + testsSubDir;
    }
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

  static Client createClient(boolean vertexAI) {
    String replaysPath = System.getenv("GOOGLE_GENAI_REPLAYS_DIRECTORY");
    if (replaysPath == null) {
      throw new RuntimeException("GOOGLE_GENAI_REPLAYS_DIRECTORY is not set");
    }
    String testsReplaysPath = replaysPath + "/tests";
    String replayMode = System.getenv("GOOGLE_GENAI_CLIENT_MODE");
    if (replayMode == null || replayMode == "auto") {
      replayMode = "replay";
    }
    DebugConfig debugConfig = new DebugConfig(replayMode, "", testsReplaysPath);

    if (replayMode.equals("replay")) {
      // Mock the default environment variables to avoid reading the actual environment variables in
      // replay mode.
      MockedStatic<ApiClient> mockedStaticApiClient =
          Mockito.mockStatic(ApiClient.class, Mockito.CALLS_REAL_METHODS);
      // Mock the default environment variables to avoid reading the actual environment variables.
      mockedStaticApiClient
          .when(ApiClient::defaultEnvironmentVariables)
          .thenReturn(
              ImmutableMap.builder()
                  .put("googleApiKey", "google-api-key")
                  .put("project", "project")
                  .put("location", "location")
                  .build());

      Client client = Client.builder().vertexAI(vertexAI).debugConfig(debugConfig).build();
      mockedStaticApiClient.close();
      return client;
    }

    return Client.builder().vertexAI(vertexAI).debugConfig(debugConfig).build();
  }

  private static Object normalizeKeys(Object data) {
    if (data instanceof Map) {
      @SuppressWarnings("unchecked")
      Map<String, Object> originalMap = (Map<String, Object>) data;
      Map<String, Object> newNormalizedMap = new HashMap<>();
      for (Map.Entry<String, Object> entry : originalMap.entrySet()) {
        String normalizedKey = Common.snakeToCamel(entry.getKey());
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
}
