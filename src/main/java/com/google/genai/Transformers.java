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
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.google.common.collect.ImmutableList;
import com.google.genai.types.Blob;
import com.google.genai.types.Content;
import com.google.genai.types.File;
import com.google.genai.types.FunctionDeclaration;
import com.google.genai.types.GeneratedVideo;
import com.google.genai.types.Part;
import com.google.genai.types.PrebuiltVoiceConfig;
import com.google.genai.types.Schema;
import com.google.genai.types.SpeechConfig;
import com.google.genai.types.Tool;
import com.google.genai.types.Video;
import com.google.genai.types.VoiceConfig;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jspecify.annotations.Nullable;

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
    if (apiClient.vertexAI()) {
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
  @SuppressWarnings("unchecked")
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
  @SuppressWarnings("unchecked")
  public static List<Tool> tTools(ApiClient apiClient, Object origin) {
    if (origin == null) {
      return null;
    } else if (origin instanceof List) {
      List<Tool> tools = (List<Tool>) origin;
      List<Tool> transformedTools = new ArrayList<>();
      for (Tool tool : tools) {
        transformedTools.add(tTool(apiClient, tool));
      }
      return transformedTools;
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
      Tool tool = (Tool) origin;
      if (!tool.functions().isPresent()) {
        return tool;
      }
      List<FunctionDeclaration> combinedFunctionDeclarations = new ArrayList<>();
      for (Method method : tool.functions().get()) {
        combinedFunctionDeclarations.add(FunctionDeclaration.fromMethod(method));
      }
      if (tool.functionDeclarations().isPresent()) {
        combinedFunctionDeclarations.addAll(tool.functionDeclarations().get());
      }
      ObjectNode toolNode = JsonSerializable.objectMapper.valueToTree(tool);
      toolNode.remove("functions");
      toolNode.set(
          "functionDeclarations",
          JsonSerializable.objectMapper.valueToTree(combinedFunctionDeclarations));
      return JsonSerializable.fromJsonNode(toolNode, Tool.class);
    } else if (origin instanceof JsonNode) {
      // in case reflectMethods is present in the json node, call tTool to parse it and remove it
      // from the json node.
      return tTool(
          apiClient,
          JsonSerializable.objectMapper.convertValue(
              (JsonNode) origin, new TypeReference<Tool>() {}));
    }

    throw new IllegalArgumentException("Unsupported tool type: " + origin.getClass());
  }

  /** Dummy Blobs transformer. */
  public static ArrayNode tBlobs(ApiClient apiClient, Object origin) {
    // 1. Check if the origin is a JsonNode
    if (!(origin instanceof JsonNode)) {
      // If origin is not a JsonNode, we create one from the object.
      origin = JsonSerializable.toJsonNode(origin);
    }

    JsonNode inputNode = (JsonNode) origin;
    // 2. Check if the input is already an array
    if (inputNode.isArray()) {
      // If it's already an array, return it as is.
      return (ArrayNode) inputNode;
    }

    // 2. If it's not an array, create a new array and add the input to it.
    ArrayNode arrayNode = JsonNodeFactory.instance.arrayNode();
    arrayNode.add(JsonSerializable.toJsonNode(tBlob(apiClient, origin)));
    return arrayNode;
  }

  public static Blob tBlob(ApiClient apiClient, Object blob) {
    if (blob instanceof JsonNode) {
      blob =
          JsonSerializable.objectMapper.convertValue((JsonNode) blob, new TypeReference<Blob>() {});
    }

    if (blob instanceof Blob) {
      return (Blob) blob;
    } else {
      throw new IllegalArgumentException("Unsupported blob type: " + blob.getClass());
    }
  }

  /**
   * Transforms a blob to an image blob, validating its mime type.
   *
   * @param apiClient the API client to use for transformation
   * @param blob the object to transform, can be a Blob or a dictionary.
   * @return the transformed Blob if it is an image.
   * @throws IllegalArgumentException if the blob is not an image.
   */
  public static Blob tImageBlob(ApiClient apiClient, Object blob) {
    Blob transformedBlob = tBlob(apiClient, blob);
    if (transformedBlob.mimeType().isPresent()
        && transformedBlob.mimeType().get().startsWith("image/")) {
      return transformedBlob;
    }
    throw new IllegalArgumentException(
        "Unsupported mime type for image blob: " + transformedBlob.mimeType().orElse("null"));
  }

  /**
   * Transforms a blob to an audio blob, validating its mime type.
   *
   * @param apiClient the API client to use for transformation
   * @param blob the object to transform, can be a Blob or a dictionary.
   * @return the transformed Blob if it is an audio.
   * @throws IllegalArgumentException if the blob is not an audio.
   */
  public static Blob tAudioBlob(ApiClient apiClient, Object blob) {
    Blob transformedBlob = tBlob(apiClient, blob);
    if (transformedBlob.mimeType().isPresent()
        && transformedBlob.mimeType().get().startsWith("audio/")) {
      return transformedBlob;
    }
    throw new IllegalArgumentException(
        "Unsupported mime type for audio blob: " + transformedBlob.mimeType().orElse("null"));
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

  /** Transforms an object to a list of Content for the embedding API. */
  @SuppressWarnings("unchecked")
  public static @Nullable List<Object> tContentsForEmbed(ApiClient apiClient, Object origin) {
    if (origin == null) {
      return null;
    }

    List<Content> contents;
    if (origin instanceof List) {
      contents = (List<Content>) origin;
    } else if (origin instanceof JsonNode) {
      contents =
          JsonSerializable.objectMapper.convertValue(
              (JsonNode) origin, new TypeReference<List<Content>>() {});
    } else {
      throw new IllegalArgumentException("Unsupported contents type: " + origin.getClass());
    }

    List<Object> result = new ArrayList<>();
    for (Content content : contents) {
      if (!apiClient.vertexAI()) {
        result.add(content);
      } else {
        for (Part part : content.parts().orElse(ImmutableList.of())) {
          part.text().ifPresent(result::add);
        }
      }
    }
    return result;
  }

  public static @Nullable String tFileName(ApiClient apiClient, Object origin) {
    String name = null;

    if (origin instanceof String) {
      name = (String) origin;
    } else if (origin instanceof File) {
      name =
          ((File) origin)
              .name()
              .orElseThrow(() -> new IllegalArgumentException("File name is required."));
    } else if (origin instanceof Video) {
      name = ((Video) origin).uri().orElse(null);
      if (name == null) {
        return null;
      }
    } else if (origin instanceof GeneratedVideo) {
      Video video =
          ((GeneratedVideo) origin)
              .video()
              .orElseThrow(() -> new IllegalArgumentException("Video is required."));
      name = video.uri().orElse(null);
      if (name == null) {
        return null;
      }
    } else if (origin instanceof TextNode) {
      name = ((TextNode) origin).textValue();
    } else {
      throw new IllegalArgumentException("Unsupported file name type: " + origin.getClass());
    }

    if (name.startsWith("https://")) {
      String suffix = name.split("files/")[1];
      Pattern pattern = Pattern.compile("[a-z0-9]+");
      Matcher matcher = pattern.matcher(suffix);
      if (matcher.find()) {
        name = matcher.group();
      } else {
        throw new IllegalArgumentException("Could not extract file name from " + name);
      }
    } else if (name.startsWith("files/")) {
      name = name.split("files/")[1];
    }

    return name;
  }

  /** Formats a resource name given the resource name and resource prefix. */
  private static String getResourceName(
      ApiClient apiClient, String resourceName, String resourcePrefix) {
    if (apiClient.vertexAI()) {
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
