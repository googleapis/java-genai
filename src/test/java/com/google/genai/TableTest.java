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

    int testTableIndex = path.lastIndexOf("/_test_table.json");
    int replaysTestsIndex = path.lastIndexOf("/replays/tests/");
    String testDirectory =
        path.substring(replaysTestsIndex + "/replays/tests/".length(), testTableIndex);

    // Gets module name and method name.
    String testMethod = testTableFile.testMethod().get();
    String[] segments = testMethod.split("\\.");

    if (segments.length == 1) {
      if (MultistepTest.customTestMethods.containsKey(testDirectory)) {
        List<DynamicTest> dynamicTests = new ArrayList<>();
        for (TestTableItem testTableItem : testTableFile.testTable().get()) {
          String testName =
              String.format("%s.%s.%s", testMethod, testTableItem.name().get(), suffix);
          String replayId = testTableItem.name().get();
          if (testTableItem.overrideReplayId().isPresent()) {
            replayId = testTableItem.overrideReplayId().get();
          }
          String clientReplayId = testDirectory + "/" + replayId + "." + suffix + ".json";
          dynamicTests.addAll(
              createTestCasesForMultistep(
                  testName, testTableItem, vertexAI, testDirectory, clientReplayId));
        }
        return dynamicTests;
      }
      String msg =
          " => Test skipped: multistep test "
              + testMethod
              + " ("
              + testDirectory
              + ") not supported in Java";
      List<DynamicTest> dynamicTests = new ArrayList<>();
      for (TestTableItem testTableItem : testTableFile.testTable().get()) {
        String testName = String.format("%s.%s.%s", testMethod, testTableItem.name().get(), suffix);
        dynamicTests.add(DynamicTest.dynamicTest(testName + msg, () -> {}));
      }
      return dynamicTests;
    }
    String originalMethodName = segments[segments.length - 1];
    String methodName = Common.snakeToCamel(originalMethodName);

    if (methodName.equals("embedContent")) {
      methodName = "embedContentTest";
    }

    List<String> modulePath = new ArrayList<>();
    for (int i = 0; i < segments.length - 1; i++) {
      modulePath.add(segments[i]);
    }
    String originalModuleName = String.join(".", modulePath);

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
    List<Method> methods = new ArrayList<>();
    try {
      // Traverse the module path to find the final module class.
      Class<?> currentClass = Client.class;
      for (String moduleSegment : modulePath) {
        String camelSegment = Common.snakeToCamel(moduleSegment);
        Field field = currentClass.getDeclaredField(camelSegment);
        currentClass = field.getType();
      }
      Class<?> moduleClass = currentClass;

      for (Method candidate : moduleClass.getDeclaredMethods()) {
        if (candidate.getName().equals(methodName)) {
          methods.add(candidate);
        }
        if (methodName.equals("generateVideos")
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
              testName,
              testTableItem,
              modulePath,
              vertexAI,
              methods,
              parameterNames,
              clientReplayId));
    }
    return dynamicTests;
  }

  @SuppressWarnings("unchecked")
  private static Collection<DynamicTest> createTestCases(
      String testName,
      TestTableItem testTableItem,
      List<String> modulePath,
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
    if (testName.contains("models.embed_content.test_vertex_new_api_inline_pdf")) {
      String msg = " => Test skipped: inline byte deserialization fails";
      return Collections.singletonList(DynamicTest.dynamicTest(testName + msg, () -> {}));
    }
    // TODO(b/457846189): Support models.list filter parameter
    if (testName.contains("models.list.test_tuned_models_with_filter")
        || testName.contains("models.list.test_tuned_models.vertex")) {
      String msg = " => Test skipped: replay tests are not supported for models.list with filter";
      return Collections.singletonList(DynamicTest.dynamicTest(testName + msg, () -> {}));
    }

    Map<String, Object> fromParameters = prepareParameters(testTableItem);

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
        Optional<String> skipMsg = getSkipMessageInApiMode(testTableItem, client);
        if (skipMsg.isPresent()) {
          dynamicTests.add(DynamicTest.dynamicTest(testName + skipMsg.get(), () -> {}));
          continue;
        }
        dynamicTests.add(
            DynamicTest.dynamicTest(
                testName,
                () -> {
                  try {
                    client.setReplayId(replayId);

                    // Get the target module instance by traversing the client object
                    Object targetModuleInstance = client;
                    for (String moduleSegment : modulePath) {
                      String camelSegment = Common.snakeToCamel(moduleSegment);
                      Field field = targetModuleInstance.getClass().getDeclaredField(camelSegment);
                      field.setAccessible(true);
                      targetModuleInstance = field.get(targetModuleInstance);
                    }

                    // May throw IllegalAccessException or InvocationTargetException here
                    // InvocationTargetException is sometimes expected, when exceptionIfMldev or
                    // exceptionIfVertex is present.
                    Object response = method.invoke(targetModuleInstance, parameters.toArray());
                  } catch (IllegalAccessException
                      | InvocationTargetException
                      | IllegalArgumentException
                      | NoSuchFieldException e) {
                    Throwable cause = e instanceof InvocationTargetException ? e.getCause() : e;
                    handleException(cause, testTableItem, client, testName);
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

  @SuppressWarnings("unchecked")
  private static Collection<DynamicTest> createTestCasesForMultistep(
      String testName,
      TestTableItem testTableItem,
      boolean vertexAI,
      String customMethodKey,
      String replayId) {

    Client client = createClient(vertexAI);
    List<DynamicTest> dynamicTests = new ArrayList<>();

    if (client.clientMode().equals("replay")) {
      String msg = " => Test skipped: multistep tests run in api mode only";
      dynamicTests.add(DynamicTest.dynamicTest(testName + msg, () -> {}));
      return dynamicTests;
    }

    Map<String, Object> fromParameters = prepareParameters(testTableItem);

    Optional<String> skipMsg = getSkipMessageInApiMode(testTableItem, client);
    if (skipMsg.isPresent()) {
      dynamicTests.add(DynamicTest.dynamicTest(testName + skipMsg.get(), () -> {}));
      return dynamicTests;
    }

    dynamicTests.add(
        DynamicTest.dynamicTest(
            testName,
            () -> {
              try {
                client.setReplayId(replayId);
                MultistepTest.MultistepFunction method =
                    MultistepTest.customTestMethods.get(customMethodKey);
                Object response = method.apply(client, fromParameters);
              } catch (Exception e) {
                Throwable cause = e instanceof InvocationTargetException ? e.getCause() : e;
                handleException(cause, testTableItem, client, testName);
              } finally {
                client.close();
              }
            }));

    return dynamicTests;
  }

  @SuppressWarnings("unchecked")
  private static Map<String, Object> prepareParameters(TestTableItem testTableItem) {
    Map<String, Object> fromParameters =
        (Map<String, Object>) normalizeKeys((Map<String, Object>) testTableItem.parameters().get());
    ReplaySanitizer.sanitizeMapByPath(
        fromParameters, "image.imageBytes", new ReplayBase64Sanitizer(), false);
    ReplaySanitizer.sanitizeMapByPath(
        fromParameters, "source.image.imageBytes", new ReplayBase64Sanitizer(), false);
    ReplaySanitizer.sanitizeMapByPath(
        fromParameters,
        "source.scribbleImage.image.imageBytes",
        new ReplayBase64Sanitizer(),
        false);
    return fromParameters;
  }

  private static void handleException(
      Throwable cause, TestTableItem testTableItem, Client client, String testName) {
    Optional<String> exceptionIfMldev = testTableItem.exceptionIfMldev();
    Optional<String> exceptionIfVertex = testTableItem.exceptionIfVertex();
    if (exceptionIfMldev.isPresent() && !client.vertexAI()) {
      verifyExceptionMatch(testName, cause, exceptionIfMldev.get(), "Gemini API");
    } else if (exceptionIfVertex.isPresent() && client.vertexAI()) {
      verifyExceptionMatch(testName, cause, exceptionIfVertex.get(), "Vertex AI");
    } else {
      fail(String.format("'%s' failed: %s", testName, cause));
    }
  }

  private static void verifyExceptionMatch(
      String testName, Throwable cause, String expectedException, String platformName) {
    String exceptionMessage = cause.getMessage();
    String parameterException = " parameter is not supported in " + platformName + ".";
    if (exceptionMessage != null && exceptionMessage.endsWith(parameterException)) {
      String camelCaseVariable = exceptionMessage.split(" ")[0];
      String snakeCaseVariable = Transformers.camelToSnake(camelCaseVariable);
      exceptionMessage = exceptionMessage.replace(camelCaseVariable, snakeCaseVariable);
    }

    if (!exceptionMessage.contains(expectedException)) {
      String expected = expectedException.replace(" in ", " ");
      String actual =
          exceptionMessage == null
              ? ""
              : exceptionMessage.replace(" in ", " ").replace(" for ", " ");
      if (!actual.contains(expected)) {
        fail(
            String.format(
                "'%s' failed to match expected exception:\n"
                    + "Expected exception: %s\n"
                    + " Actual exception: %s\n",
                testName, expectedException, cause.getMessage()));
      }
    }
  }

  private static Optional<String> getSkipMessageInApiMode(
      TestTableItem testTableItem, Client client) {
    Optional<String> skipInApiMode = testTableItem.skipInApiMode();
    if (skipInApiMode.isPresent()
        && (client.clientMode().equals("api") || client.clientMode().isEmpty())) {
      return Optional.of(" => Test skipped: " + skipInApiMode.get());
    }
    return Optional.empty();
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
