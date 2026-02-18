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

import com.google.common.collect.ImmutableMap;
import com.google.genai.types.BatchJob;
import com.google.genai.types.BatchJobSource;
import com.google.genai.types.CachedContent;
import com.google.genai.types.Content;
import com.google.genai.types.CreateBatchJobConfig;
import com.google.genai.types.CreateCachedContentConfig;
import com.google.genai.types.CreateTuningJobConfig;
import com.google.genai.types.File;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.JobState;
import com.google.genai.types.Part;
import com.google.genai.types.TuningDataset;
import com.google.genai.types.TuningJob;
import com.google.genai.types.UpdateCachedContentConfig;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Custom multistep test methods for Java. */
public final class MultistepTest {
  @FunctionalInterface
  public interface MultistepFunction {
    Object apply(Client client, Map<String, Object> parameters) throws Exception;
  }

  public static final Map<String, MultistepFunction> customTestMethods =
      ImmutableMap.<String, MultistepFunction>builder()
          .put("shared/batches/create_delete", MultistepTest::createDelete)
          .put("shared/batches/create_get_cancel", MultistepTest::createGetCancelBatches)
          .put("shared/caches/create_get_delete", MultistepTest::createGetDelete)
          .put("shared/caches/create_update_get", MultistepTest::createUpdateGet)
          .put("shared/chats/send_message", MultistepTest::sendMessage)
          .put("shared/chats/send_message_stream", MultistepTest::sendMessageStream)
          .put("shared/files/upload_get_delete", MultistepTest::uploadGetDelete)
          .put("shared/models/generate_content_stream", MultistepTest::generateContentStream)
          .put("shared/tunings/create_get_cancel", MultistepTest::createGetCancelTunings)
          .build();

  private static Object createDelete(Client client, Map<String, Object> parameters)
      throws Exception {
    String model = (String) parameters.get("model");
    Object srcObj = parameters.get("src");
    if (srcObj instanceof List) {
      srcObj = Collections.singletonMap("inlinedRequests", srcObj);
    }
    BatchJobSource src =
        JsonSerializable.objectMapper().convertValue(srcObj, BatchJobSource.class);
    CreateBatchJobConfig config =
        JsonSerializable.objectMapper()
            .convertValue(parameters.get("config"), CreateBatchJobConfig.class);

    BatchJob batchJob = client.batches.create(model, src, config);
    batchJob = client.batches.get(batchJob.name().get(), null);
    if (batchJob.state().get().knownEnum() != JobState.Known.JOB_STATE_PENDING) {
      client.batches.delete(batchJob.name().get(), null);
      return null;
    }
    return batchJob;
  }

  private static Object createGetCancelBatches(Client client, Map<String, Object> parameters)
      throws Exception {
    String model = (String) parameters.get("model");
    Object srcObj = parameters.get("src");
    if (srcObj instanceof List) {
      srcObj = Collections.singletonMap("inlinedRequests", srcObj);
    }
    BatchJobSource src =
        JsonSerializable.objectMapper().convertValue(srcObj, BatchJobSource.class);
    CreateBatchJobConfig config =
        JsonSerializable.objectMapper()
            .convertValue(parameters.get("config"), CreateBatchJobConfig.class);

    BatchJob batchJob = client.batches.create(model, src, config);
    batchJob = client.batches.get(batchJob.name().get(), null);
    client.batches.cancel(batchJob.name().get(), null);
    return null;
  }

  private static Object createGetCancelTunings(Client client, Map<String, Object> parameters)
      throws Exception {
    String baseModel = (String) parameters.get("baseModel");
    TuningDataset trainingDataset =
        JsonSerializable.objectMapper()
            .convertValue(parameters.get("trainingDataset"), TuningDataset.class);
    CreateTuningJobConfig config =
        JsonSerializable.objectMapper()
            .convertValue(parameters.get("config"), CreateTuningJobConfig.class);

    TuningJob tuningJob = client.tunings.tune(baseModel, trainingDataset, config);
    tuningJob = client.tunings.get(tuningJob.name().get(), null);
    client.tunings.cancel(tuningJob.name().get(), null);
    return null;
  }

  @SuppressWarnings("unchecked")
  private static void fixContents(Map<String, Object> configMap) {
    if (configMap == null) return;
    Object contentsObj = configMap.get("contents");
    if (contentsObj instanceof List) {
      List<Object> contentsList = (List<Object>) contentsObj;
      List<Map<String, Object>> newContents = new ArrayList<>();
      for (Object item : contentsList) {
        if (item instanceof Map) {
          Map<String, Object> map = (Map<String, Object>) item;
          if (!map.containsKey("parts")) {
            // It's a Part, wrap it in a Content
            Map<String, Object> content = new HashMap<>();
            content.put("parts", Collections.singletonList(map));
            content.put("role", "user");
            newContents.add(content);
          } else {
            if (!map.containsKey("role")) {
              map.put("role", "user");
            }
            newContents.add(map);
          }
        }
      }
      configMap.put("contents", newContents);
    }
  }

  @SuppressWarnings("unchecked")
  private static Object createGetDelete(Client client, Map<String, Object> parameters)
      throws Exception {
    String model = (String) parameters.get("model");
    Map<String, Object> configMap = (Map<String, Object>) parameters.get("config");
    fixContents(configMap);
    CreateCachedContentConfig config =
        JsonSerializable.objectMapper().convertValue(configMap, CreateCachedContentConfig.class);

    CachedContent cache;
    if (client.vertexAI()) {
      cache = client.caches.create(model, config);
    } else {
      String filePath = "tests/data/google.png";
      if (!Files.exists(Paths.get(filePath))) {
        Files.createDirectories(Paths.get(filePath).getParent());
        Files.write(Paths.get(filePath), "fake content".getBytes());
      }
      File file = client.files.upload(filePath, null);
      List<Part> parts = new ArrayList<>();
      for (int i = 0; i < 5; i++) {
        parts.add(Part.fromUri(file.uri().get(), file.mimeType().get()));
      }
      CreateCachedContentConfig mldevConfig =
          CreateCachedContentConfig.builder()
              .contents(Collections.singletonList(Content.fromParts(parts.toArray(new Part[0]))))
              .build();
      cache = client.caches.create(model, mldevConfig);
    }
    CachedContent gotCache = client.caches.get(cache.name().get(), null);
    return client.caches.delete(gotCache.name().get(), null);
  }

  @SuppressWarnings("unchecked")
  private static Object createUpdateGet(Client client, Map<String, Object> parameters)
      throws Exception {
    String model = (String) parameters.get("model");
    Map<String, Object> configMap = (Map<String, Object>) parameters.get("config");
    fixContents(configMap);
    CreateCachedContentConfig config =
        JsonSerializable.objectMapper().convertValue(configMap, CreateCachedContentConfig.class);

    CachedContent cache;
    if (client.vertexAI()) {
      cache = client.caches.create(model, config);
    } else {
      String filePath = "tests/data/google.png";
      if (!Files.exists(Paths.get(filePath))) {
        Files.createDirectories(Paths.get(filePath).getParent());
        Files.write(Paths.get(filePath), "fake content".getBytes());
      }
      File file = client.files.upload(filePath, null);
      List<Part> parts = new ArrayList<>();
      for (int i = 0; i < 5; i++) {
        parts.add(Part.fromUri(file.uri().get(), file.mimeType().get()));
      }
      CreateCachedContentConfig mldevConfig =
          CreateCachedContentConfig.builder()
              .contents(Collections.singletonList(Content.fromParts(parts.toArray(new Part[0]))))
              .build();
      cache = client.caches.create(model, mldevConfig);
    }
    CachedContent updatedCache =
        client.caches.update(
            cache.name().get(), UpdateCachedContentConfig.builder().ttl(Duration.ofSeconds(7200)).build());
    return client.caches.get(updatedCache.name().get(), null);
  }

  private static Object sendMessage(Client client, Map<String, Object> parameters)
      throws Exception {
    String model = (String) parameters.get("model");
    String message = (String) parameters.get("message");

    Chat chat = client.chats.create(model);
    return chat.sendMessage(message);
  }

  private static Object sendMessageStream(Client client, Map<String, Object> parameters)
      throws Exception {
    String model = (String) parameters.get("model");
    String message = (String) parameters.get("message");

    Chat chat = client.chats.create(model);
    Iterable<GenerateContentResponse> response = chat.sendMessageStream(message);
    GenerateContentResponse lastResponse = null;
    for (GenerateContentResponse chunk : response) {
      lastResponse = chunk;
    }
    return lastResponse;
  }

  private static Object uploadGetDelete(Client client, Map<String, Object> parameters)
      throws Exception {
    String filePath = (String) parameters.get("filePath");
    if (!Files.exists(Paths.get(filePath))) {
      Files.createDirectories(Paths.get(filePath).getParent());
      Files.write(Paths.get(filePath), "fake content".getBytes());
    }
    File file = client.files.upload(filePath, null);
    File gotFile = client.files.get(file.name().get(), null);
    return client.files.delete(gotFile.name().get(), null);
  }

  @SuppressWarnings("unchecked")
  private static Object generateContentStream(Client client, Map<String, Object> parameters)
      throws Exception {
    String model = (String) parameters.get("model");
    Object contentsObj = parameters.get("contents");
    List<Content> contents = new ArrayList<>();
    if (contentsObj instanceof String) {
      contents.add(Content.fromParts(Part.fromText((String) contentsObj)));
    } else if (contentsObj instanceof List) {
      List<Object> contentMaps = (List<Object>) contentsObj;
      for (Object contentObj : contentMaps) {
        if (contentObj instanceof Map) {
          Map<String, Object> contentMap = (Map<String, Object>) contentObj;
          if (!contentMap.containsKey("role")) {
            contentMap.put("role", "user");
          }
        }
      }
      contents =
          JsonSerializable.objectMapper()
              .convertValue(
                  contentMaps,
                  JsonSerializable.objectMapper()
                      .getTypeFactory()
                      .constructCollectionType(List.class, Content.class));
    }

    Iterable<GenerateContentResponse> response =
        client.models.generateContentStream(model, contents, null);
    GenerateContentResponse lastResponse = null;
    for (GenerateContentResponse chunk : response) {
      lastResponse = chunk;
    }
    return lastResponse;
  }
}
