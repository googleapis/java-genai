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

import static com.google.common.base.Preconditions.checkNotNull;

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
import com.google.genai.types.JobState;
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
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jspecify.annotations.Nullable;

/** Transformers for GenAI SDK. */
final class Transformers {
  private static final Logger logger = Logger.getLogger(Transformers.class.getName());

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
      throw new IllegalArgumentException("model is required.");
    } else if (origin instanceof String) {
      model = (String) origin;
    } else if (origin instanceof JsonNode) {
      model = JsonSerializable.toJsonString((JsonNode) origin);
      model = model.replace("\"", "");
    } else {
      throw new IllegalArgumentException("Unsupported model type: " + origin.getClass());
    }
    if (model.isEmpty()) {
      throw new IllegalArgumentException("model is required.");
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
   * Determines the appropriate models URL based on the API client type and whether base models are
   * requested.
   *
   * @param apiClient the API client to use for transformation
   * @param baseModels true or null if base models are requested, false otherwise.
   * @return The transformed model name
   */
  @SuppressWarnings("PatternMatchingInstanceof")
  public static String tModelsUrl(ApiClient apiClient, @Nullable Object baseModels) {
    if (baseModels == null) {
      return apiClient.vertexAI() ? "publishers/google/models" : "models";
    }
    if (!(baseModels instanceof JsonNode)) {
      throw new IllegalArgumentException("Unsupported response type: " + baseModels.getClass());
    }

    if (((JsonNode) baseModels).asBoolean()) {
      return apiClient.vertexAI() ? "publishers/google/models" : "models";
    } else {
      return apiClient.vertexAI() ? "models" : "tunedModels";
    }
  }

  /**
   * Extracts the models from the models.list response.
   *
   * <p>This is used in the converters.
   */
  @SuppressWarnings("PatternMatchingInstanceof")
  public static @Nullable JsonNode tExtractModels(Object origin) {
    if (origin == null) {
      return null;
    }
    if (!(origin instanceof JsonNode)) {
      throw new IllegalArgumentException("Unsupported response type: " + origin.getClass());
    }

    JsonNode response = (JsonNode) origin;
    JsonNode models = response.get("models");
    if (models != null) {
      return models;
    }
    models = response.get("tunedModels");
    if (models != null) {
      return models;
    }
    models = response.get("publisherModels");
    if (models != null) {
      return models;
    }
    if ((response.get("httpHeaders") == null) || (response.get("jsonPayload") != null)) {
      logger.warning("Cannot determine the models type for response: " + response.toPrettyString());
    }
    return JsonSerializable.objectMapper.createArrayNode();
  }

  /**
   * In other languages, this transformer is used for supporting union types. Java doesn't have
   * union types, so we define a dummy transformer here.
   *
   * <p>This is used in the converters.
   */
  public static Object tContents(Object origin) {
    return origin;
  }

  /**
   * Transforms a text string to a list of Content for the API.
   *
   * <p>Not intended to be used in converters.
   *
   * @param text the text string to transform
   * @return the transformed list of Content
   */
  public static ImmutableList<Content> tContents(String text) {
    checkNotNull(text, "text cannot be null");
    return ImmutableList.of(Content.fromParts(Part.fromText(text)));
  }

  /**
   * Transforms a Content to a list of Content for the API.
   *
   * <p>Not intended to be used in converters.
   *
   * @param content a {@link Content} to transform
   * @return the transformed list of Content
   */
  public static ImmutableList<Content> tContents(Content content) {
    checkNotNull(content, "text cannot be null");
    return ImmutableList.of(content);
  }

  /**
   * Transforms an object to a Content for the API.
   *
   * @param content the object to transform, can be a string or Content
   * @return the transformed Content
   * @throws IllegalArgumentException if the object is not a supported type
   */
  public static Content tContent(Object content) {
    if (content == null) {
      return null;
    } else if (content instanceof String) {
      return Content.fromParts(Part.fromText((String) content));
    } else if (content instanceof Content) {
      return (Content) content;
    } else if (content instanceof JsonNode) {
      return JsonSerializable.fromJsonNode((JsonNode) content, Content.class);
    }

    throw new IllegalArgumentException("Unsupported content type: " + content.getClass());
  }

  /** Transforms an object to a Schema for the API. */
  public static Schema tSchema(Object origin) {
    if (origin == null) {
      return null;
    } else if (origin instanceof Schema) {
      return (Schema) origin;
    } else if (origin instanceof JsonNode) {
      return JsonSerializable.fromJsonNode((JsonNode) origin, Schema.class);
    }
    throw new IllegalArgumentException("Unsupported schema type: " + origin.getClass());
  }

  public static SpeechConfig tSpeechConfig(Object speechConfig) {
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

  /**
   * Transforms a SpeechConfig object for the live API, validating it.
   *
   * @param origin the object to transform, can be a SpeechConfig or a JsonNode
   * @return the transformed SpeechConfig
   * @throws IllegalArgumentException if the object is not a supported type or if
   *     multiSpeakerVoiceConfig is present.
   */
  public static @Nullable SpeechConfig tLiveSpeechConfig(Object origin) {
    SpeechConfig speechConfig;
    if (origin == null) {
      return null;
    } else if (origin instanceof SpeechConfig) {
      speechConfig = (SpeechConfig) origin;
    } else if (origin instanceof JsonNode) {
      speechConfig = JsonSerializable.fromJsonNode((JsonNode) origin, SpeechConfig.class);
    } else {
      throw new IllegalArgumentException("Unsupported speechConfig type:" + origin.getClass());
    }

    if (speechConfig.multiSpeakerVoiceConfig().isPresent()) {
      throw new IllegalArgumentException(
          "multiSpeakerVoiceConfig is not supported in the live API.");
    }

    return speechConfig;
  }

  /**
   * In other languages, this transformer is used for supporting union types. Java doesn't have
   * union types, so we define a dummy transformer here. *
   *
   * <p>This is used in the converters.
   */
  public static Object tTools(Object origin) {
    return origin;
  }

  /** Transforms an object to a Tool for the API. */
  public static Tool tTool(Object origin) {
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
          JsonSerializable.objectMapper.convertValue(
              (JsonNode) origin, new TypeReference<Tool>() {}));
    }

    throw new IllegalArgumentException("Unsupported tool type: " + origin.getClass());
  }

  /** Dummy Blobs transformer. */
  public static ArrayNode tBlobs(Object origin) {
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
    arrayNode.add(JsonSerializable.toJsonNode(tBlob(origin)));
    return arrayNode;
  }

  public static Blob tBlob(Object blob) {
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
   * @param blob the object to transform, can be a Blob or a dictionary.
   * @return the transformed Blob if it is an image.
   * @throws IllegalArgumentException if the blob is not an image.
   */
  public static Blob tImageBlob(Object blob) {
    Blob transformedBlob = tBlob(blob);
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
   * @param blob the object to transform, can be a Blob or a dictionary.
   * @return the transformed Blob if it is an audio.
   * @throws IllegalArgumentException if the blob is not an audio.
   */
  public static Blob tAudioBlob(Object blob) {
    Blob transformedBlob = tBlob(blob);
    if (transformedBlob.mimeType().isPresent()
        && transformedBlob.mimeType().get().startsWith("audio/")) {
      return transformedBlob;
    }
    throw new IllegalArgumentException(
        "Unsupported mime type for audio blob: " + transformedBlob.mimeType().orElse("null"));
  }

  /** Dummy bytes transformer. */
  public static Object tBytes(Object origin) {
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

  /**
   * Transforms a model name to the correct format for the Caches API.
   *
   * @param apiClient the API client to use for transformation
   * @param origin the model name to transform, can be a string or JsonNode
   * @return the transformed model name, or null if the input is null
   * @throws IllegalArgumentException if the object is not a supported type
   */
  public static @Nullable String tCachesModel(ApiClient apiClient, Object origin) {
    String model = tModel(apiClient, origin);
    if (model == null) {
      return null;
    }

    if (apiClient.vertexAI()) {
      if (model.startsWith("publishers/")) {
        // Vertex caches only support model names starting with projects.
        return String.format(
            "projects/%s/locations/%s/%s", apiClient.project(), apiClient.location(), model);
      } else if (model.startsWith("models/")) {
        return String.format(
            "projects/%s/locations/%s/publishers/google/%s",
            apiClient.project(), apiClient.location(), model);
      }
    }
    return model;
  }

  public static @Nullable String tFileName(Object origin) {
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

  public static JobState tTuningJobStatus(Object origin) {
    String status;
    if (origin instanceof String) {
      status = (String) origin;
    } else if (origin instanceof TextNode) {
      status = ((TextNode) origin).textValue();
    } else {
      throw new IllegalArgumentException("Unsupported status type: " + origin.getClass());
    }

    switch (status) {
      case "STATE_UNSPECIFIED":
        return new JobState(JobState.Known.JOB_STATE_UNSPECIFIED);
      case "CREATING":
        return new JobState(JobState.Known.JOB_STATE_RUNNING);
      case "ACTIVE":
        return new JobState(JobState.Known.JOB_STATE_SUCCEEDED);
      case "FAILED":
        return new JobState(JobState.Known.JOB_STATE_FAILED);
      default:
        return new JobState(status);
    }
  }

  /** Formats a resource name given the resource name and resource prefix. */
  private static String getResourceName(
      ApiClient apiClient, String resourceName, String resourcePrefix) {
    boolean shouldPrependCollectionIdentifier =
        (!resourceName.startsWith(resourcePrefix + "/")
            && countCharInAString(String.format("%s/%s", resourcePrefix, resourceName), '/') == 1);
    if (apiClient.vertexAI()) {
      if (resourceName.startsWith("projects/")) {
        return resourceName;
      } else if (resourceName.startsWith("locations/")) {
        return String.format("projects/%s/%s", apiClient.project(), resourceName);
      } else if (resourceName.startsWith(resourcePrefix + "/")) {
        return String.format(
            "projects/%s/locations/%s/%s", apiClient.project(), apiClient.location(), resourceName);
      } else if (shouldPrependCollectionIdentifier) {
        return String.format(
            "projects/%s/locations/%s/%s/%s",
            apiClient.project(), apiClient.location(), resourcePrefix, resourceName);
      } else {
        return resourceName;
      }
    } else {
      if (shouldPrependCollectionIdentifier) {
        return String.format("%s/%s", resourcePrefix, resourceName);
      } else {
        return resourceName;
      }
    }
  }

  private static int countCharInAString(String string, char charToCount) {
    int count = 0;
    for (char c : string.toCharArray()) {
      if (c == charToCount) {
        count++;
      }
    }
    return count;
  }

  public static Object tBatchJobSource(Object src) {
    return src;
  }

  public static Object tBatchJobDestination(Object dest) {
    return dest;
  }

  public static Object tRecvBatchJobDestination(Object dest) {
    return dest;
  }

  /**
   * It validates and extracts the batch job name based on the backend (Vertex AI or MLDev).
   *
   * @param apiClient The ApiClient instance, used to determine the backend.
   * @param name The batch job name as an Object, expected to be a String.
   * @return The extracted name (e.g., job ID).
   * @throws IllegalArgumentException If the input name is not a String or does not match expected
   *     patterns.
   */
  public static String tBatchJobName(ApiClient apiClient, Object name) {
    String nameStr;
    if (name instanceof String) {
      nameStr = (String) name;
    } else if (name instanceof JsonNode) {
      nameStr = JsonSerializable.toJsonString((JsonNode) name);
      nameStr = nameStr.replace("\"", "");
    } else {
      throw new IllegalArgumentException("Unsupported batch job name type: " + name.getClass());
    }

    if (apiClient.vertexAI()) {
      String fullBatchName = getResourceName(apiClient, nameStr, "batchPredictionJobs");
      if (fullBatchName != null) {
        String[] parts = fullBatchName.split("/");
        String jobId = parts[parts.length - 1];
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(jobId);
        if (matcher.find() && parts.length == 6) {
          return jobId;
        }
      }
      throw new IllegalArgumentException(
          String.format(
              "Invalid batch job name: %s. Expected format like"
                  + " 'projects/123/locations/us-central1/batchPredictionJobs/456' or '456'.",
              nameStr));
    } else {
      nameStr = nameStr.replace("\"", "");
      if (nameStr.startsWith("batches/")) {
        return nameStr.split("/")[1];
      }
      throw new IllegalArgumentException(
          String.format("Invalid batch job name: %s. Expected format like 'batches/id'", nameStr));
    }
  }

  /**
   * Maps specific batch states to job states, otherwise returns the state as is.
   *
   * @param state The input state as an Object, expected to be a String.
   * @return The mapped or original state string.
   * @throws IllegalArgumentException If the input state is not a String.
   */
  public static Object tJobState(Object state) {
    String stateStr;
    if (state instanceof String) {
      stateStr = (String) state;
    } else if (state instanceof JsonNode) {
      stateStr = JsonSerializable.toJsonString(state);
      stateStr = stateStr.replace("\"", "");
    } else {
      throw new IllegalArgumentException("Unsupported job state type: " + state.getClass());
    }
    switch (stateStr) {
      case "BATCH_STATE_UNSPECIFIED":
        return JobState.Known.JOB_STATE_UNSPECIFIED;
      case "BATCH_STATE_PENDING":
        return JobState.Known.JOB_STATE_PENDING;
      case "BATCH_STATE_RUNNING":
        return JobState.Known.JOB_STATE_RUNNING;
      case "BATCH_STATE_SUCCEEDED":
        return JobState.Known.JOB_STATE_SUCCEEDED;
      case "BATCH_STATE_FAILED":
        return JobState.Known.JOB_STATE_FAILED;
      case "BATCH_STATE_CANCELLED":
        return JobState.Known.JOB_STATE_CANCELLED;
      case "BATCH_STATE_EXPIRED":
        return JobState.Known.JOB_STATE_EXPIRED;
      default:
        return state;
    }
  }

  /**
   * Updates a JSON node with a new value, handling potential conflicts.
   *
   * @param currentObject The ObjectNode to update.
   * @param keyToSet The key to set in the ObjectNode.
   * @param valueNode The new JsonNode value.
   * @throws IllegalArgumentException if a value cannot be set for an existing key.
   */
  public static void updateJsonNode(ObjectNode currentObject, String keyToSet, JsonNode valueNode) {
    JsonNode existingData = currentObject.get(keyToSet);

    if (existingData != null) {
      // Don't overwrite existing non-empty value with new empty value.
      if (valueNode == null || valueNode.isNull() || valueNode.isEmpty()) {
        return;
      }

      // Don't fail when overwriting value with same value
      if (valueNode.equals(existingData)) {
        return;
      }

      // Instead of overwriting dictionary with another dictionary, merge them.
      if (existingData.isObject() && valueNode.isObject()) {
        ((ObjectNode) existingData).setAll((ObjectNode) valueNode);
      } else {
        throw new IllegalArgumentException(
            "Cannot set value for an existing key. Key: "
                + keyToSet
                + "; Existing value: "
                + existingData
                + "; New value: "
                + valueNode);
      }
    } else {
      currentObject.set(keyToSet, valueNode);
    }
  }

  /**
   * Converts a camelCase string to snake_case.
   *
   * @param str The input string to convert.
   * @return The converted string in snake_case.
   */
  public static String camelToSnake(String str) {
    if (str == null || str.isEmpty()) {
      return str;
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (Character.isUpperCase(c)) {
        if (i > 0) {
          sb.append('_');
        }
      }
      sb.append(Character.toLowerCase(c));
    }

    return sb.toString();
  }
}
