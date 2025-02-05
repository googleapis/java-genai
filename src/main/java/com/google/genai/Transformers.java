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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableList;
import com.google.genai.types.Content;
import com.google.genai.types.Part;
import com.google.genai.types.PrebuiltVoiceConfig;
import com.google.genai.types.Schema;
import com.google.genai.types.SpeechConfig;
import com.google.genai.types.Tool;
import com.google.genai.types.VoiceConfig;
import java.util.List;

/** Transformers for GenAI SDK. */
final class Transformers {

  private Transformers() {}

  /**
   * Transforms a model name to the correct format for the API.
   *
   * @param apiClient the API client to use for transformation
   * @param origin the model name to transform, can only be a string
   * @return the transformed model name
   */
  public static String tModel(ApiClient apiClient, Object origin) {
    String model;
    if (origin == null) {
      return null;
    } else if (origin instanceof String) {
      model = (String) origin;
    } else if (origin instanceof JsonNode) {
      model = JsonSerializable.toJsonString((JsonNode) origin);
      model = model.replace("\"", "");
    } else {
      throw new IllegalArgumentException("Unsupported model type: " + origin.getClass());
    }
    if (apiClient.isVertexAI()) {
      if (model.startsWith("publishers/")
          || model.startsWith("projects/")
          || model.startsWith("models/")) {
        return model;
      } else if (model.contains("/")) {
        String[] parts = model.split("/", 2);
        return String.format("publishers/%s/models/%s", parts[0], parts[1]);
      } else {
        return "publishers/google/models/" + model;
      }
    } else {
      if (model.startsWith("models/") || model.startsWith("tunedModels/")) {
        return model;
      } else {
        return "models/" + model;
      }
    }
  }

  /**
   * Transforms an object to a list of Content for the API.
   *
   * @param apiClient the API client to use for transformation
   * @param contents the object to transform, can be a string, Content, or List<Content>
   * @return the transformed list of Content
   * @throws IllegalArgumentException if the object is not a supported type
   */
  public static List<Content> tContents(ApiClient apiClient, Object contents) {
    if (contents == null) {
      return null;
    }
    if (contents instanceof String) {
      return ImmutableList.of(
          Content.builder()
              .role("user")
              .parts(ImmutableList.of(Part.builder().text((String) contents).build()))
              .build());
    } else if (contents instanceof Content) {
      return ImmutableList.of((Content) contents);
    } else if (contents instanceof List) {
      return (List<Content>) contents;
    } else if (contents instanceof JsonNode) {
      return JsonSerializable.objectMapper.convertValue(
          (JsonNode) contents, new TypeReference<List<Content>>() {});
    }

    throw new IllegalArgumentException("Unsupported contents type: " + contents.getClass());
  }

  /**
   * Transforms an object to a Content for the API.
   *
   * @param apiClient the API client to use for transformation
   * @param content the object to transform, can be a string or Content
   * @return the transformed Content
   * @throws IllegalArgumentException if the object is not a supported type
   */
  public static Content tContent(ApiClient apiClient, Object content) {
    if (content == null) {
      return null;
    } else if (content instanceof String) {
      return Content.builder()
          .role("user")
          .parts(ImmutableList.of(Part.builder().text((String) content).build()))
          .build();
    } else if (content instanceof Content) {
      return (Content) content;
    } else if (content instanceof JsonNode) {
      return JsonSerializable.fromJsonNode((JsonNode) content, Content.class);
    }

    throw new IllegalArgumentException("Unsupported content type: " + content.getClass());
  }

  /** Transforms an object to a Schema for the API. */
  public static Schema tSchema(ApiClient apiClient, Object origin) {
    if (origin == null) {
      return null;
    } else if (origin instanceof Schema) {
      return (Schema) origin;
    } else if (origin instanceof JsonNode) {
      return JsonSerializable.fromJsonNode((JsonNode) origin, Schema.class);
    }

    throw new IllegalArgumentException("Unsupported schema type: " + origin.getClass());
  }

  public static SpeechConfig tSpeechConfig(ApiClient apiClient, Object speechConfig) {
    if (speechConfig == null) {
      return null;
    } else if (speechConfig instanceof String) {
      return SpeechConfig.builder()
          .voiceConfig(
              VoiceConfig.builder()
                  .prebuiltVoiceConfig(
                      PrebuiltVoiceConfig.builder().voiceName((String) speechConfig).build())
                  .build())
          .build();
    } else if (speechConfig instanceof SpeechConfig) {
      return (SpeechConfig) speechConfig;
    } else if (speechConfig instanceof JsonNode) {
      return JsonSerializable.fromJsonNode((JsonNode) speechConfig, SpeechConfig.class);
    }

    throw new IllegalArgumentException("Unsupported speechConfig type:" + speechConfig.getClass());
  }

  /** Transforms an object to a list of Tools for the API. */
  public static List<Tool> tTools(ApiClient apiClient, Object origin) {
    if (origin == null) {
      return null;
    } else if (origin instanceof List) {
      return (List<Tool>) origin;
    } else if (origin instanceof JsonNode) {
      return JsonSerializable.objectMapper.convertValue(
          (JsonNode) origin, new TypeReference<List<Tool>>() {});
    }

    throw new IllegalArgumentException("Unsupported tools type: " + origin.getClass());
  }

  /** Transforms an object to a Tool for the API. */
  public static Tool tTool(ApiClient apiClient, Object origin) {
    if (origin == null) {
      return null;
    } else if (origin instanceof Tool) {
      return (Tool) origin;
    } else if (origin instanceof JsonNode) {
      return JsonSerializable.objectMapper.convertValue(
          (JsonNode) origin, new TypeReference<Tool>() {});
    }

    throw new IllegalArgumentException("Unsupported tool type: " + origin.getClass());
  }

  /** Dummy bytes transformer. */
  public static Object tBytes(ApiClient apiClient, Object origin) {
    // TODO(b/389133914): Remove dummy bytes converter.
    return origin;
  }

  /** Transforms an object to a cached content name for the API. */
  public static String tCachedContentName(ApiClient apiClient, Object origin) {
    if (origin == null) {
      return null;
    } else if (origin instanceof String) {
      return getResourceName(apiClient, (String) origin, "cachedContents");
    } else if (origin instanceof JsonNode) {
      String cachedContentName = JsonSerializable.toJsonString((JsonNode) origin);
      cachedContentName = cachedContentName.replace("\"", "");
      return getResourceName(apiClient, cachedContentName, "cachedContents");
    }

    throw new IllegalArgumentException(
        "Unsupported cached content name type: " + origin.getClass());
  }

  /** Formats a resource name given the resource name and resource prefix. */
  private static String getResourceName(
      ApiClient apiClient, String resourceName, String resourcePrefix) {
    if (apiClient.isVertexAI()) {
      if (resourceName.startsWith("projects/")) {
        return resourceName;
      } else if (resourceName.startsWith("locations/")) {
        return String.format("projects/%s/%s", apiClient.project(), resourceName);
      } else if (resourceName.startsWith(resourcePrefix + "/")) {
        return String.format(
            "projects/%s/locations/%s/%s", apiClient.project(), apiClient.location(), resourceName);
      } else {
        return String.format(
            "projects/%s/locations/%s/%s/%s",
            apiClient.project(), apiClient.location(), resourcePrefix, resourceName);
      }
    } else {
      if (resourceName.startsWith(resourcePrefix + "/")) {
        return resourceName;
      } else {
        return String.format("%s/%s", resourcePrefix, resourceName);
      }
    }
  }
}
