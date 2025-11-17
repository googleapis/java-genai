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

// Auto-generated code. Do not edit.

package com.google.genai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.genai.Common.BuiltRequest;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.ComputeTokensConfig;
import com.google.genai.types.ComputeTokensParameters;
import com.google.genai.types.ComputeTokensResponse;
import com.google.genai.types.Content;
import com.google.genai.types.CountTokensConfig;
import com.google.genai.types.CountTokensParameters;
import com.google.genai.types.CountTokensResponse;
import com.google.genai.types.DeleteModelConfig;
import com.google.genai.types.DeleteModelParameters;
import com.google.genai.types.DeleteModelResponse;
import com.google.genai.types.EditImageConfig;
import com.google.genai.types.EditImageParameters;
import com.google.genai.types.EditImageResponse;
import com.google.genai.types.EmbedContentConfig;
import com.google.genai.types.EmbedContentParameters;
import com.google.genai.types.EmbedContentResponse;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentParameters;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.GenerateImagesConfig;
import com.google.genai.types.GenerateImagesParameters;
import com.google.genai.types.GenerateImagesResponse;
import com.google.genai.types.GenerateVideosConfig;
import com.google.genai.types.GenerateVideosOperation;
import com.google.genai.types.GenerateVideosParameters;
import com.google.genai.types.GenerateVideosSource;
import com.google.genai.types.GeneratedImage;
import com.google.genai.types.GetModelConfig;
import com.google.genai.types.GetModelParameters;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.HttpResponse;
import com.google.genai.types.Image;
import com.google.genai.types.ListModelsConfig;
import com.google.genai.types.ListModelsParameters;
import com.google.genai.types.ListModelsResponse;
import com.google.genai.types.Model;
import com.google.genai.types.Part;
import com.google.genai.types.RecontextImageConfig;
import com.google.genai.types.RecontextImageParameters;
import com.google.genai.types.RecontextImageResponse;
import com.google.genai.types.RecontextImageSource;
import com.google.genai.types.ReferenceImage;
import com.google.genai.types.ReferenceImageAPI;
import com.google.genai.types.SafetyAttributes;
import com.google.genai.types.SegmentImageConfig;
import com.google.genai.types.SegmentImageParameters;
import com.google.genai.types.SegmentImageResponse;
import com.google.genai.types.SegmentImageSource;
import com.google.genai.types.UpdateModelConfig;
import com.google.genai.types.UpdateModelParameters;
import com.google.genai.types.UpscaleImageAPIConfig;
import com.google.genai.types.UpscaleImageAPIParameters;
import com.google.genai.types.UpscaleImageConfig;
import com.google.genai.types.UpscaleImageResponse;
import com.google.genai.types.Video;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.logging.Logger;
import okhttp3.Headers;
import okhttp3.ResponseBody;

/**
 * Provides methods for interacting with the available GenAI models. Instantiating this class is not
 * required. After instantiating a {@link Client}, access methods through
 * `client.models.methodName(...)` directly.
 */
public final class Models {

  final ApiClient apiClient;

  public Models(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  @ExcludeFromGeneratedCoverageReport
  void personGenerationMldevEnumValidate(Object enumValue) {
    ImmutableSet<String> invalidEnumValues = ImmutableSet.of("ALLOW_ALL");
    if (invalidEnumValues.contains(enumValue.toString().replace("\"", ""))) {
      throw new IllegalArgumentException(
          String.format("%s enum value is not supported in Gemini API.", enumValue));
    }
  }

  @ExcludeFromGeneratedCoverageReport
  void safetyFilterLevelMldevEnumValidate(Object enumValue) {
    ImmutableSet<String> invalidEnumValues = ImmutableSet.of("BLOCK_NONE");
    if (invalidEnumValues.contains(enumValue.toString().replace("\"", ""))) {
      throw new IllegalArgumentException(
          String.format("%s enum value is not supported in Gemini API.", enumValue));
    }
  }

  @ExcludeFromGeneratedCoverageReport
  void videoGenerationReferenceTypeMldevEnumValidate(Object enumValue) {
    ImmutableSet<String> invalidEnumValues = ImmutableSet.of("STYLE");
    if (invalidEnumValues.contains(enumValue.toString().replace("\"", ""))) {
      throw new IllegalArgumentException(
          String.format("%s enum value is not supported in Gemini API.", enumValue));
    }
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode blobToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"data"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"data"},
          Common.getValueByPath(fromObject, new String[] {"data"}));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"displayName"}))) {
      throw new IllegalArgumentException("displayName parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"mimeType"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"mimeType"},
          Common.getValueByPath(fromObject, new String[] {"mimeType"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode candidateFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"content"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"content"},
          Common.getValueByPath(fromObject, new String[] {"content"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"citationMetadata"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"citationMetadata"},
          citationMetadataFromMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"citationMetadata"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"tokenCount"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"tokenCount"},
          Common.getValueByPath(fromObject, new String[] {"tokenCount"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"finishReason"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"finishReason"},
          Common.getValueByPath(fromObject, new String[] {"finishReason"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"avgLogprobs"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"avgLogprobs"},
          Common.getValueByPath(fromObject, new String[] {"avgLogprobs"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"groundingMetadata"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"groundingMetadata"},
          Common.getValueByPath(fromObject, new String[] {"groundingMetadata"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"index"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"index"},
          Common.getValueByPath(fromObject, new String[] {"index"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"logprobsResult"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"logprobsResult"},
          Common.getValueByPath(fromObject, new String[] {"logprobsResult"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"safetyRatings"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"safetyRatings"},
          Common.getValueByPath(fromObject, new String[] {"safetyRatings"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"urlContextMetadata"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"urlContextMetadata"},
          Common.getValueByPath(fromObject, new String[] {"urlContextMetadata"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode citationMetadataFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"citationSources"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"citations"},
          Common.getValueByPath(fromObject, new String[] {"citationSources"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode computeTokensParametersToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "model"},
          Transformers.tModel(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"contents"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"contents"},
          Transformers.tContents(Common.getValueByPath(fromObject, new String[] {"contents"})));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode computeTokensResponseFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"sdkHttpResponse"},
          Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"tokensInfo"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"tokensInfo"},
          Common.getValueByPath(fromObject, new String[] {"tokensInfo"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode contentEmbeddingFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"values"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"values"},
          Common.getValueByPath(fromObject, new String[] {"values"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"statistics"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"statistics"},
          contentEmbeddingStatisticsFromVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"statistics"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode contentEmbeddingStatisticsFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"truncated"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"truncated"},
          Common.getValueByPath(fromObject, new String[] {"truncated"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"token_count"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"tokenCount"},
          Common.getValueByPath(fromObject, new String[] {"token_count"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode contentToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"parts"}) != null) {
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"parts"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(partToMldev(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"parts"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"role"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"role"},
          Common.getValueByPath(fromObject, new String[] {"role"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode controlReferenceConfigToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"controlType"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"controlType"},
          Common.getValueByPath(fromObject, new String[] {"controlType"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"enableControlImageComputation"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"computeControl"},
          Common.getValueByPath(fromObject, new String[] {"enableControlImageComputation"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode countTokensConfigToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"systemInstruction"}))) {
      throw new IllegalArgumentException(
          "systemInstruction parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"tools"}))) {
      throw new IllegalArgumentException("tools parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"generationConfig"}))) {
      throw new IllegalArgumentException(
          "generationConfig parameter is not supported in Gemini API.");
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode countTokensConfigToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"systemInstruction"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"systemInstruction"},
          Transformers.tContent(
              Common.getValueByPath(fromObject, new String[] {"systemInstruction"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"tools"}) != null) {
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"tools"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(toolToVertex(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(parentObject, new String[] {"tools"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"generationConfig"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"generationConfig"},
          generationConfigToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"generationConfig"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode countTokensParametersToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "model"},
          Transformers.tModel(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"contents"}) != null) {
      ArrayNode keyArray =
          (ArrayNode)
              Transformers.tContents(Common.getValueByPath(fromObject, new String[] {"contents"}));
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(contentToMldev(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"contents"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          countTokensConfigToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode countTokensParametersToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "model"},
          Transformers.tModel(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"contents"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"contents"},
          Transformers.tContents(Common.getValueByPath(fromObject, new String[] {"contents"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          countTokensConfigToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode countTokensResponseFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"sdkHttpResponse"},
          Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"totalTokens"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"totalTokens"},
          Common.getValueByPath(fromObject, new String[] {"totalTokens"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"cachedContentTokenCount"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"cachedContentTokenCount"},
          Common.getValueByPath(fromObject, new String[] {"cachedContentTokenCount"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode countTokensResponseFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"sdkHttpResponse"},
          Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"totalTokens"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"totalTokens"},
          Common.getValueByPath(fromObject, new String[] {"totalTokens"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode deleteModelParametersToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "name"},
          Transformers.tModel(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode deleteModelParametersToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "name"},
          Transformers.tModel(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode deleteModelResponseFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"sdkHttpResponse"},
          Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode deleteModelResponseFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"sdkHttpResponse"},
          Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode editImageConfigToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"outputGcsUri"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "storageUri"},
          Common.getValueByPath(fromObject, new String[] {"outputGcsUri"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"negativePrompt"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "negativePrompt"},
          Common.getValueByPath(fromObject, new String[] {"negativePrompt"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"numberOfImages"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "sampleCount"},
          Common.getValueByPath(fromObject, new String[] {"numberOfImages"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"aspectRatio"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "aspectRatio"},
          Common.getValueByPath(fromObject, new String[] {"aspectRatio"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"guidanceScale"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "guidanceScale"},
          Common.getValueByPath(fromObject, new String[] {"guidanceScale"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"seed"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "seed"},
          Common.getValueByPath(fromObject, new String[] {"seed"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"safetyFilterLevel"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "safetySetting"},
          Common.getValueByPath(fromObject, new String[] {"safetyFilterLevel"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"personGeneration"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "personGeneration"},
          Common.getValueByPath(fromObject, new String[] {"personGeneration"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"includeSafetyAttributes"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "includeSafetyAttributes"},
          Common.getValueByPath(fromObject, new String[] {"includeSafetyAttributes"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"includeRaiReason"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "includeRaiReason"},
          Common.getValueByPath(fromObject, new String[] {"includeRaiReason"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"language"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "language"},
          Common.getValueByPath(fromObject, new String[] {"language"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"outputMimeType"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "outputOptions", "mimeType"},
          Common.getValueByPath(fromObject, new String[] {"outputMimeType"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"outputCompressionQuality"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "outputOptions", "compressionQuality"},
          Common.getValueByPath(fromObject, new String[] {"outputCompressionQuality"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"addWatermark"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "addWatermark"},
          Common.getValueByPath(fromObject, new String[] {"addWatermark"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"labels"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"labels"},
          Common.getValueByPath(fromObject, new String[] {"labels"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"editMode"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "editMode"},
          Common.getValueByPath(fromObject, new String[] {"editMode"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"baseSteps"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "editConfig", "baseSteps"},
          Common.getValueByPath(fromObject, new String[] {"baseSteps"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode editImageParametersToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "model"},
          Transformers.tModel(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"prompt"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"instances[0]", "prompt"},
          Common.getValueByPath(fromObject, new String[] {"prompt"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"referenceImages"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"referenceImages"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(referenceImageAPIToVertex(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"instances[0]", "referenceImages"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          editImageConfigToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode editImageResponseFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"sdkHttpResponse"},
          Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"predictions"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"predictions"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(generatedImageFromVertex(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"generatedImages"}, result);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode embedContentConfigToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"taskType"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"requests[]", "taskType"},
          Common.getValueByPath(fromObject, new String[] {"taskType"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"title"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"requests[]", "title"},
          Common.getValueByPath(fromObject, new String[] {"title"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"outputDimensionality"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"requests[]", "outputDimensionality"},
          Common.getValueByPath(fromObject, new String[] {"outputDimensionality"}));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"mimeType"}))) {
      throw new IllegalArgumentException("mimeType parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"autoTruncate"}))) {
      throw new IllegalArgumentException("autoTruncate parameter is not supported in Gemini API.");
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode embedContentConfigToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"taskType"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"instances[]", "task_type"},
          Common.getValueByPath(fromObject, new String[] {"taskType"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"title"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"instances[]", "title"},
          Common.getValueByPath(fromObject, new String[] {"title"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"outputDimensionality"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "outputDimensionality"},
          Common.getValueByPath(fromObject, new String[] {"outputDimensionality"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"mimeType"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"instances[]", "mimeType"},
          Common.getValueByPath(fromObject, new String[] {"mimeType"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"autoTruncate"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "autoTruncate"},
          Common.getValueByPath(fromObject, new String[] {"autoTruncate"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode embedContentParametersToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "model"},
          Transformers.tModel(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"contents"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"requests[]", "content"},
          Transformers.tContentsForEmbed(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"contents"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          embedContentConfigToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject);
    }

    Common.setValueByPath(
        toObject,
        new String[] {"requests[]", "model"},
        Transformers.tModel(
            this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode embedContentParametersToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "model"},
          Transformers.tModel(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"contents"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"instances[]", "content"},
          Transformers.tContentsForEmbed(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"contents"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          embedContentConfigToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode embedContentResponseFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"sdkHttpResponse"},
          Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"embeddings"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"embeddings"},
          Common.getValueByPath(fromObject, new String[] {"embeddings"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"metadata"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"metadata"},
          Common.getValueByPath(fromObject, new String[] {"metadata"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode embedContentResponseFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"sdkHttpResponse"},
          Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"predictions[]", "embeddings"}) != null) {
      ArrayNode keyArray =
          (ArrayNode)
              Common.getValueByPath(fromObject, new String[] {"predictions[]", "embeddings"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(contentEmbeddingFromVertex(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"embeddings"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"metadata"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"metadata"},
          Common.getValueByPath(fromObject, new String[] {"metadata"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode endpointFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"endpoint"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"name"},
          Common.getValueByPath(fromObject, new String[] {"endpoint"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"deployedModelId"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"deployedModelId"},
          Common.getValueByPath(fromObject, new String[] {"deployedModelId"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode fileDataToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"displayName"}))) {
      throw new IllegalArgumentException("displayName parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"fileUri"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"fileUri"},
          Common.getValueByPath(fromObject, new String[] {"fileUri"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"mimeType"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"mimeType"},
          Common.getValueByPath(fromObject, new String[] {"mimeType"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode functionDeclarationToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"behavior"}))) {
      throw new IllegalArgumentException("behavior parameter is not supported in Vertex AI.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"description"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"description"},
          Common.getValueByPath(fromObject, new String[] {"description"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"name"},
          Common.getValueByPath(fromObject, new String[] {"name"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"parameters"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"parameters"},
          Common.getValueByPath(fromObject, new String[] {"parameters"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"parametersJsonSchema"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"parametersJsonSchema"},
          Common.getValueByPath(fromObject, new String[] {"parametersJsonSchema"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"response"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"response"},
          Common.getValueByPath(fromObject, new String[] {"response"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"responseJsonSchema"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"responseJsonSchema"},
          Common.getValueByPath(fromObject, new String[] {"responseJsonSchema"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateContentConfigToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"systemInstruction"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"systemInstruction"},
          contentToMldev(
              JsonSerializable.toJsonNode(
                  Transformers.tContent(
                      Common.getValueByPath(fromObject, new String[] {"systemInstruction"}))),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"temperature"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"temperature"},
          Common.getValueByPath(fromObject, new String[] {"temperature"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"topP"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"topP"},
          Common.getValueByPath(fromObject, new String[] {"topP"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"topK"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"topK"},
          Common.getValueByPath(fromObject, new String[] {"topK"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"candidateCount"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"candidateCount"},
          Common.getValueByPath(fromObject, new String[] {"candidateCount"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"maxOutputTokens"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"maxOutputTokens"},
          Common.getValueByPath(fromObject, new String[] {"maxOutputTokens"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"stopSequences"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"stopSequences"},
          Common.getValueByPath(fromObject, new String[] {"stopSequences"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"responseLogprobs"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"responseLogprobs"},
          Common.getValueByPath(fromObject, new String[] {"responseLogprobs"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"logprobs"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"logprobs"},
          Common.getValueByPath(fromObject, new String[] {"logprobs"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"presencePenalty"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"presencePenalty"},
          Common.getValueByPath(fromObject, new String[] {"presencePenalty"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"frequencyPenalty"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"frequencyPenalty"},
          Common.getValueByPath(fromObject, new String[] {"frequencyPenalty"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"seed"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"seed"},
          Common.getValueByPath(fromObject, new String[] {"seed"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"responseMimeType"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"responseMimeType"},
          Common.getValueByPath(fromObject, new String[] {"responseMimeType"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"responseSchema"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"responseSchema"},
          Transformers.tSchema(Common.getValueByPath(fromObject, new String[] {"responseSchema"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"responseJsonSchema"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"responseJsonSchema"},
          Common.getValueByPath(fromObject, new String[] {"responseJsonSchema"}));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"routingConfig"}))) {
      throw new IllegalArgumentException("routingConfig parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"modelSelectionConfig"}))) {
      throw new IllegalArgumentException(
          "modelSelectionConfig parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"safetySettings"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"safetySettings"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(safetySettingToMldev(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(parentObject, new String[] {"safetySettings"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"tools"}) != null) {
      ArrayNode keyArray =
          (ArrayNode)
              Transformers.tTools(Common.getValueByPath(fromObject, new String[] {"tools"}));
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(toolToMldev(JsonSerializable.toJsonNode(Transformers.tTool(item)), toObject));
      }
      Common.setValueByPath(parentObject, new String[] {"tools"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"toolConfig"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"toolConfig"},
          Common.getValueByPath(fromObject, new String[] {"toolConfig"}));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"labels"}))) {
      throw new IllegalArgumentException("labels parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"cachedContent"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"cachedContent"},
          Transformers.tCachedContentName(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"cachedContent"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"responseModalities"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"responseModalities"},
          Common.getValueByPath(fromObject, new String[] {"responseModalities"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"mediaResolution"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"mediaResolution"},
          Common.getValueByPath(fromObject, new String[] {"mediaResolution"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"speechConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"speechConfig"},
          Transformers.tSpeechConfig(
              Common.getValueByPath(fromObject, new String[] {"speechConfig"})));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"audioTimestamp"}))) {
      throw new IllegalArgumentException(
          "audioTimestamp parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"thinkingConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"thinkingConfig"},
          Common.getValueByPath(fromObject, new String[] {"thinkingConfig"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"imageConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"imageConfig"},
          imageConfigToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"imageConfig"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateContentConfigToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"systemInstruction"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"systemInstruction"},
          Transformers.tContent(
              Common.getValueByPath(fromObject, new String[] {"systemInstruction"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"temperature"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"temperature"},
          Common.getValueByPath(fromObject, new String[] {"temperature"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"topP"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"topP"},
          Common.getValueByPath(fromObject, new String[] {"topP"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"topK"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"topK"},
          Common.getValueByPath(fromObject, new String[] {"topK"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"candidateCount"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"candidateCount"},
          Common.getValueByPath(fromObject, new String[] {"candidateCount"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"maxOutputTokens"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"maxOutputTokens"},
          Common.getValueByPath(fromObject, new String[] {"maxOutputTokens"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"stopSequences"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"stopSequences"},
          Common.getValueByPath(fromObject, new String[] {"stopSequences"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"responseLogprobs"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"responseLogprobs"},
          Common.getValueByPath(fromObject, new String[] {"responseLogprobs"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"logprobs"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"logprobs"},
          Common.getValueByPath(fromObject, new String[] {"logprobs"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"presencePenalty"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"presencePenalty"},
          Common.getValueByPath(fromObject, new String[] {"presencePenalty"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"frequencyPenalty"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"frequencyPenalty"},
          Common.getValueByPath(fromObject, new String[] {"frequencyPenalty"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"seed"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"seed"},
          Common.getValueByPath(fromObject, new String[] {"seed"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"responseMimeType"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"responseMimeType"},
          Common.getValueByPath(fromObject, new String[] {"responseMimeType"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"responseSchema"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"responseSchema"},
          Transformers.tSchema(Common.getValueByPath(fromObject, new String[] {"responseSchema"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"responseJsonSchema"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"responseJsonSchema"},
          Common.getValueByPath(fromObject, new String[] {"responseJsonSchema"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"routingConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"routingConfig"},
          Common.getValueByPath(fromObject, new String[] {"routingConfig"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"modelSelectionConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"modelConfig"},
          Common.getValueByPath(fromObject, new String[] {"modelSelectionConfig"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"safetySettings"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"safetySettings"},
          Common.getValueByPath(fromObject, new String[] {"safetySettings"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"tools"}) != null) {
      ArrayNode keyArray =
          (ArrayNode)
              Transformers.tTools(Common.getValueByPath(fromObject, new String[] {"tools"}));
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(toolToVertex(JsonSerializable.toJsonNode(Transformers.tTool(item)), toObject));
      }
      Common.setValueByPath(parentObject, new String[] {"tools"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"toolConfig"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"toolConfig"},
          Common.getValueByPath(fromObject, new String[] {"toolConfig"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"labels"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"labels"},
          Common.getValueByPath(fromObject, new String[] {"labels"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"cachedContent"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"cachedContent"},
          Transformers.tCachedContentName(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"cachedContent"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"responseModalities"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"responseModalities"},
          Common.getValueByPath(fromObject, new String[] {"responseModalities"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"mediaResolution"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"mediaResolution"},
          Common.getValueByPath(fromObject, new String[] {"mediaResolution"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"speechConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"speechConfig"},
          speechConfigToVertex(
              JsonSerializable.toJsonNode(
                  Transformers.tSpeechConfig(
                      Common.getValueByPath(fromObject, new String[] {"speechConfig"}))),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"audioTimestamp"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"audioTimestamp"},
          Common.getValueByPath(fromObject, new String[] {"audioTimestamp"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"thinkingConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"thinkingConfig"},
          Common.getValueByPath(fromObject, new String[] {"thinkingConfig"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"imageConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"imageConfig"},
          imageConfigToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"imageConfig"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateContentParametersToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "model"},
          Transformers.tModel(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"contents"}) != null) {
      ArrayNode keyArray =
          (ArrayNode)
              Transformers.tContents(Common.getValueByPath(fromObject, new String[] {"contents"}));
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(contentToMldev(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"contents"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"generationConfig"},
          generateContentConfigToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateContentParametersToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "model"},
          Transformers.tModel(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"contents"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"contents"},
          Transformers.tContents(Common.getValueByPath(fromObject, new String[] {"contents"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"generationConfig"},
          generateContentConfigToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateContentResponseFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"sdkHttpResponse"},
          Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"candidates"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"candidates"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(candidateFromMldev(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"candidates"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"modelVersion"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"modelVersion"},
          Common.getValueByPath(fromObject, new String[] {"modelVersion"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"promptFeedback"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"promptFeedback"},
          Common.getValueByPath(fromObject, new String[] {"promptFeedback"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"responseId"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"responseId"},
          Common.getValueByPath(fromObject, new String[] {"responseId"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"usageMetadata"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"usageMetadata"},
          Common.getValueByPath(fromObject, new String[] {"usageMetadata"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateContentResponseFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"sdkHttpResponse"},
          Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"candidates"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"candidates"},
          Common.getValueByPath(fromObject, new String[] {"candidates"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"createTime"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"createTime"},
          Common.getValueByPath(fromObject, new String[] {"createTime"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"modelVersion"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"modelVersion"},
          Common.getValueByPath(fromObject, new String[] {"modelVersion"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"promptFeedback"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"promptFeedback"},
          Common.getValueByPath(fromObject, new String[] {"promptFeedback"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"responseId"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"responseId"},
          Common.getValueByPath(fromObject, new String[] {"responseId"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"usageMetadata"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"usageMetadata"},
          Common.getValueByPath(fromObject, new String[] {"usageMetadata"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateImagesConfigToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"outputGcsUri"}))) {
      throw new IllegalArgumentException("outputGcsUri parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"negativePrompt"}))) {
      throw new IllegalArgumentException(
          "negativePrompt parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"numberOfImages"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "sampleCount"},
          Common.getValueByPath(fromObject, new String[] {"numberOfImages"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"aspectRatio"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "aspectRatio"},
          Common.getValueByPath(fromObject, new String[] {"aspectRatio"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"guidanceScale"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "guidanceScale"},
          Common.getValueByPath(fromObject, new String[] {"guidanceScale"}));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"seed"}))) {
      throw new IllegalArgumentException("seed parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"safetyFilterLevel"}) != null) {
      safetyFilterLevelMldevEnumValidate(
          Common.getValueByPath(fromObject, new String[] {"safetyFilterLevel"}));
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "safetySetting"},
          Common.getValueByPath(fromObject, new String[] {"safetyFilterLevel"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"personGeneration"}) != null) {
      personGenerationMldevEnumValidate(
          Common.getValueByPath(fromObject, new String[] {"personGeneration"}));
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "personGeneration"},
          Common.getValueByPath(fromObject, new String[] {"personGeneration"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"includeSafetyAttributes"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "includeSafetyAttributes"},
          Common.getValueByPath(fromObject, new String[] {"includeSafetyAttributes"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"includeRaiReason"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "includeRaiReason"},
          Common.getValueByPath(fromObject, new String[] {"includeRaiReason"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"language"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "language"},
          Common.getValueByPath(fromObject, new String[] {"language"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"outputMimeType"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "outputOptions", "mimeType"},
          Common.getValueByPath(fromObject, new String[] {"outputMimeType"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"outputCompressionQuality"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "outputOptions", "compressionQuality"},
          Common.getValueByPath(fromObject, new String[] {"outputCompressionQuality"}));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"addWatermark"}))) {
      throw new IllegalArgumentException("addWatermark parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"labels"}))) {
      throw new IllegalArgumentException("labels parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"imageSize"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "sampleImageSize"},
          Common.getValueByPath(fromObject, new String[] {"imageSize"}));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"enhancePrompt"}))) {
      throw new IllegalArgumentException("enhancePrompt parameter is not supported in Gemini API.");
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateImagesConfigToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"outputGcsUri"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "storageUri"},
          Common.getValueByPath(fromObject, new String[] {"outputGcsUri"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"negativePrompt"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "negativePrompt"},
          Common.getValueByPath(fromObject, new String[] {"negativePrompt"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"numberOfImages"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "sampleCount"},
          Common.getValueByPath(fromObject, new String[] {"numberOfImages"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"aspectRatio"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "aspectRatio"},
          Common.getValueByPath(fromObject, new String[] {"aspectRatio"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"guidanceScale"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "guidanceScale"},
          Common.getValueByPath(fromObject, new String[] {"guidanceScale"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"seed"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "seed"},
          Common.getValueByPath(fromObject, new String[] {"seed"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"safetyFilterLevel"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "safetySetting"},
          Common.getValueByPath(fromObject, new String[] {"safetyFilterLevel"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"personGeneration"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "personGeneration"},
          Common.getValueByPath(fromObject, new String[] {"personGeneration"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"includeSafetyAttributes"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "includeSafetyAttributes"},
          Common.getValueByPath(fromObject, new String[] {"includeSafetyAttributes"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"includeRaiReason"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "includeRaiReason"},
          Common.getValueByPath(fromObject, new String[] {"includeRaiReason"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"language"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "language"},
          Common.getValueByPath(fromObject, new String[] {"language"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"outputMimeType"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "outputOptions", "mimeType"},
          Common.getValueByPath(fromObject, new String[] {"outputMimeType"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"outputCompressionQuality"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "outputOptions", "compressionQuality"},
          Common.getValueByPath(fromObject, new String[] {"outputCompressionQuality"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"addWatermark"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "addWatermark"},
          Common.getValueByPath(fromObject, new String[] {"addWatermark"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"labels"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"labels"},
          Common.getValueByPath(fromObject, new String[] {"labels"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"imageSize"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "sampleImageSize"},
          Common.getValueByPath(fromObject, new String[] {"imageSize"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"enhancePrompt"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "enhancePrompt"},
          Common.getValueByPath(fromObject, new String[] {"enhancePrompt"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateImagesParametersToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "model"},
          Transformers.tModel(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"prompt"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"instances[0]", "prompt"},
          Common.getValueByPath(fromObject, new String[] {"prompt"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          generateImagesConfigToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateImagesParametersToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "model"},
          Transformers.tModel(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"prompt"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"instances[0]", "prompt"},
          Common.getValueByPath(fromObject, new String[] {"prompt"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          generateImagesConfigToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateImagesResponseFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"sdkHttpResponse"},
          Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"predictions"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"predictions"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(generatedImageFromMldev(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"generatedImages"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"positivePromptSafetyAttributes"})
        != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"positivePromptSafetyAttributes"},
          safetyAttributesFromMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(
                      fromObject, new String[] {"positivePromptSafetyAttributes"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateImagesResponseFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"sdkHttpResponse"},
          Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"predictions"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"predictions"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(generatedImageFromVertex(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"generatedImages"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"positivePromptSafetyAttributes"})
        != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"positivePromptSafetyAttributes"},
          safetyAttributesFromVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(
                      fromObject, new String[] {"positivePromptSafetyAttributes"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateVideosConfigToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"numberOfVideos"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "sampleCount"},
          Common.getValueByPath(fromObject, new String[] {"numberOfVideos"}));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"outputGcsUri"}))) {
      throw new IllegalArgumentException("outputGcsUri parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"fps"}))) {
      throw new IllegalArgumentException("fps parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"durationSeconds"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "durationSeconds"},
          Common.getValueByPath(fromObject, new String[] {"durationSeconds"}));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"seed"}))) {
      throw new IllegalArgumentException("seed parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"aspectRatio"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "aspectRatio"},
          Common.getValueByPath(fromObject, new String[] {"aspectRatio"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"resolution"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "resolution"},
          Common.getValueByPath(fromObject, new String[] {"resolution"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"personGeneration"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "personGeneration"},
          Common.getValueByPath(fromObject, new String[] {"personGeneration"}));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"pubsubTopic"}))) {
      throw new IllegalArgumentException("pubsubTopic parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"negativePrompt"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "negativePrompt"},
          Common.getValueByPath(fromObject, new String[] {"negativePrompt"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"enhancePrompt"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "enhancePrompt"},
          Common.getValueByPath(fromObject, new String[] {"enhancePrompt"}));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"generateAudio"}))) {
      throw new IllegalArgumentException("generateAudio parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"lastFrame"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"instances[0]", "lastFrame"},
          imageToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"lastFrame"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"referenceImages"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"referenceImages"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(
            videoGenerationReferenceImageToMldev(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(parentObject, new String[] {"instances[0]", "referenceImages"}, result);
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"mask"}))) {
      throw new IllegalArgumentException("mask parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"compressionQuality"}))) {
      throw new IllegalArgumentException(
          "compressionQuality parameter is not supported in Gemini API.");
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateVideosConfigToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"numberOfVideos"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "sampleCount"},
          Common.getValueByPath(fromObject, new String[] {"numberOfVideos"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"outputGcsUri"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "storageUri"},
          Common.getValueByPath(fromObject, new String[] {"outputGcsUri"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"fps"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "fps"},
          Common.getValueByPath(fromObject, new String[] {"fps"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"durationSeconds"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "durationSeconds"},
          Common.getValueByPath(fromObject, new String[] {"durationSeconds"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"seed"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "seed"},
          Common.getValueByPath(fromObject, new String[] {"seed"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"aspectRatio"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "aspectRatio"},
          Common.getValueByPath(fromObject, new String[] {"aspectRatio"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"resolution"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "resolution"},
          Common.getValueByPath(fromObject, new String[] {"resolution"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"personGeneration"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "personGeneration"},
          Common.getValueByPath(fromObject, new String[] {"personGeneration"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"pubsubTopic"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "pubsubTopic"},
          Common.getValueByPath(fromObject, new String[] {"pubsubTopic"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"negativePrompt"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "negativePrompt"},
          Common.getValueByPath(fromObject, new String[] {"negativePrompt"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"enhancePrompt"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "enhancePrompt"},
          Common.getValueByPath(fromObject, new String[] {"enhancePrompt"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"generateAudio"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "generateAudio"},
          Common.getValueByPath(fromObject, new String[] {"generateAudio"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"lastFrame"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"instances[0]", "lastFrame"},
          imageToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"lastFrame"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"referenceImages"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"referenceImages"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(
            videoGenerationReferenceImageToVertex(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(parentObject, new String[] {"instances[0]", "referenceImages"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"mask"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"instances[0]", "mask"},
          videoGenerationMaskToVertex(
              JsonSerializable.toJsonNode(Common.getValueByPath(fromObject, new String[] {"mask"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"compressionQuality"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "compressionQuality"},
          Common.getValueByPath(fromObject, new String[] {"compressionQuality"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateVideosOperationFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"name"},
          Common.getValueByPath(fromObject, new String[] {"name"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"metadata"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"metadata"},
          Common.getValueByPath(fromObject, new String[] {"metadata"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"done"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"done"},
          Common.getValueByPath(fromObject, new String[] {"done"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"error"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"error"},
          Common.getValueByPath(fromObject, new String[] {"error"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"response", "generateVideoResponse"})
        != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"response"},
          generateVideosResponseFromMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(
                      fromObject, new String[] {"response", "generateVideoResponse"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateVideosOperationFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"name"},
          Common.getValueByPath(fromObject, new String[] {"name"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"metadata"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"metadata"},
          Common.getValueByPath(fromObject, new String[] {"metadata"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"done"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"done"},
          Common.getValueByPath(fromObject, new String[] {"done"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"error"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"error"},
          Common.getValueByPath(fromObject, new String[] {"error"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"response"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"response"},
          generateVideosResponseFromVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"response"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateVideosParametersToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "model"},
          Transformers.tModel(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"prompt"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"instances[0]", "prompt"},
          Common.getValueByPath(fromObject, new String[] {"prompt"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"image"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"instances[0]", "image"},
          imageToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"image"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"video"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"instances[0]", "video"},
          videoToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"video"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"source"}) != null) {
      JsonNode unused =
          generateVideosSourceToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"source"})),
              toObject);
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          generateVideosConfigToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateVideosParametersToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "model"},
          Transformers.tModel(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"prompt"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"instances[0]", "prompt"},
          Common.getValueByPath(fromObject, new String[] {"prompt"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"image"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"instances[0]", "image"},
          imageToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"image"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"video"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"instances[0]", "video"},
          videoToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"video"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"source"}) != null) {
      JsonNode unused =
          generateVideosSourceToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"source"})),
              toObject);
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          generateVideosConfigToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateVideosResponseFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"generatedSamples"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"generatedSamples"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(generatedVideoFromMldev(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"generatedVideos"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"raiMediaFilteredCount"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"raiMediaFilteredCount"},
          Common.getValueByPath(fromObject, new String[] {"raiMediaFilteredCount"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"raiMediaFilteredReasons"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"raiMediaFilteredReasons"},
          Common.getValueByPath(fromObject, new String[] {"raiMediaFilteredReasons"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateVideosResponseFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"videos"}) != null) {
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"videos"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(generatedVideoFromVertex(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"generatedVideos"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"raiMediaFilteredCount"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"raiMediaFilteredCount"},
          Common.getValueByPath(fromObject, new String[] {"raiMediaFilteredCount"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"raiMediaFilteredReasons"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"raiMediaFilteredReasons"},
          Common.getValueByPath(fromObject, new String[] {"raiMediaFilteredReasons"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateVideosSourceToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"prompt"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"instances[0]", "prompt"},
          Common.getValueByPath(fromObject, new String[] {"prompt"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"image"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"instances[0]", "image"},
          imageToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"image"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"video"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"instances[0]", "video"},
          videoToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"video"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateVideosSourceToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"prompt"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"instances[0]", "prompt"},
          Common.getValueByPath(fromObject, new String[] {"prompt"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"image"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"instances[0]", "image"},
          imageToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"image"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"video"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"instances[0]", "video"},
          videoToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"video"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generatedImageFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"_self"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"image"},
          imageFromMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"_self"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"raiFilteredReason"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"raiFilteredReason"},
          Common.getValueByPath(fromObject, new String[] {"raiFilteredReason"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"_self"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"safetyAttributes"},
          safetyAttributesFromMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"_self"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generatedImageFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"_self"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"image"},
          imageFromVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"_self"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"raiFilteredReason"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"raiFilteredReason"},
          Common.getValueByPath(fromObject, new String[] {"raiFilteredReason"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"_self"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"safetyAttributes"},
          safetyAttributesFromVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"_self"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"prompt"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"enhancedPrompt"},
          Common.getValueByPath(fromObject, new String[] {"prompt"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generatedImageMaskFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"_self"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"mask"},
          imageFromVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"_self"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"labels"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"labels"},
          Common.getValueByPath(fromObject, new String[] {"labels"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generatedVideoFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"video"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"video"},
          videoFromMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"video"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generatedVideoFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"_self"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"video"},
          videoFromVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"_self"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generationConfigToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"modelSelectionConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"modelConfig"},
          Common.getValueByPath(fromObject, new String[] {"modelSelectionConfig"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"responseJsonSchema"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"responseJsonSchema"},
          Common.getValueByPath(fromObject, new String[] {"responseJsonSchema"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"audioTimestamp"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"audioTimestamp"},
          Common.getValueByPath(fromObject, new String[] {"audioTimestamp"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"candidateCount"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"candidateCount"},
          Common.getValueByPath(fromObject, new String[] {"candidateCount"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"enableAffectiveDialog"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"enableAffectiveDialog"},
          Common.getValueByPath(fromObject, new String[] {"enableAffectiveDialog"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"frequencyPenalty"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"frequencyPenalty"},
          Common.getValueByPath(fromObject, new String[] {"frequencyPenalty"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"logprobs"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"logprobs"},
          Common.getValueByPath(fromObject, new String[] {"logprobs"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"maxOutputTokens"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"maxOutputTokens"},
          Common.getValueByPath(fromObject, new String[] {"maxOutputTokens"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"mediaResolution"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"mediaResolution"},
          Common.getValueByPath(fromObject, new String[] {"mediaResolution"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"presencePenalty"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"presencePenalty"},
          Common.getValueByPath(fromObject, new String[] {"presencePenalty"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"responseLogprobs"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"responseLogprobs"},
          Common.getValueByPath(fromObject, new String[] {"responseLogprobs"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"responseMimeType"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"responseMimeType"},
          Common.getValueByPath(fromObject, new String[] {"responseMimeType"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"responseModalities"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"responseModalities"},
          Common.getValueByPath(fromObject, new String[] {"responseModalities"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"responseSchema"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"responseSchema"},
          Common.getValueByPath(fromObject, new String[] {"responseSchema"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"routingConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"routingConfig"},
          Common.getValueByPath(fromObject, new String[] {"routingConfig"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"seed"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"seed"},
          Common.getValueByPath(fromObject, new String[] {"seed"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"speechConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"speechConfig"},
          speechConfigToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"speechConfig"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"stopSequences"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"stopSequences"},
          Common.getValueByPath(fromObject, new String[] {"stopSequences"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"temperature"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"temperature"},
          Common.getValueByPath(fromObject, new String[] {"temperature"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"thinkingConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"thinkingConfig"},
          Common.getValueByPath(fromObject, new String[] {"thinkingConfig"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"topK"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"topK"},
          Common.getValueByPath(fromObject, new String[] {"topK"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"topP"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"topP"},
          Common.getValueByPath(fromObject, new String[] {"topP"}));
    }

    if (!Common.isZero(
        Common.getValueByPath(fromObject, new String[] {"enableEnhancedCivicAnswers"}))) {
      throw new IllegalArgumentException(
          "enableEnhancedCivicAnswers parameter is not supported in Vertex AI.");
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode getModelParametersToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "name"},
          Transformers.tModel(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode getModelParametersToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "name"},
          Transformers.tModel(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode googleMapsToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"authConfig"}))) {
      throw new IllegalArgumentException("authConfig parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"enableWidget"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"enableWidget"},
          Common.getValueByPath(fromObject, new String[] {"enableWidget"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode googleSearchToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"excludeDomains"}))) {
      throw new IllegalArgumentException(
          "excludeDomains parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"blockingConfidence"}))) {
      throw new IllegalArgumentException(
          "blockingConfidence parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"timeRangeFilter"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"timeRangeFilter"},
          Common.getValueByPath(fromObject, new String[] {"timeRangeFilter"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode imageConfigToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"aspectRatio"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"aspectRatio"},
          Common.getValueByPath(fromObject, new String[] {"aspectRatio"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"imageSize"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"imageSize"},
          Common.getValueByPath(fromObject, new String[] {"imageSize"}));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"outputMimeType"}))) {
      throw new IllegalArgumentException(
          "outputMimeType parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(
        Common.getValueByPath(fromObject, new String[] {"outputCompressionQuality"}))) {
      throw new IllegalArgumentException(
          "outputCompressionQuality parameter is not supported in Gemini API.");
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode imageConfigToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"aspectRatio"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"aspectRatio"},
          Common.getValueByPath(fromObject, new String[] {"aspectRatio"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"imageSize"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"imageSize"},
          Common.getValueByPath(fromObject, new String[] {"imageSize"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"outputMimeType"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"imageOutputOptions", "mimeType"},
          Common.getValueByPath(fromObject, new String[] {"outputMimeType"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"outputCompressionQuality"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"imageOutputOptions", "compressionQuality"},
          Common.getValueByPath(fromObject, new String[] {"outputCompressionQuality"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode imageFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"bytesBase64Encoded"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"imageBytes"},
          Transformers.tBytes(
              Common.getValueByPath(fromObject, new String[] {"bytesBase64Encoded"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"mimeType"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"mimeType"},
          Common.getValueByPath(fromObject, new String[] {"mimeType"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode imageFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"gcsUri"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"gcsUri"},
          Common.getValueByPath(fromObject, new String[] {"gcsUri"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"bytesBase64Encoded"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"imageBytes"},
          Transformers.tBytes(
              Common.getValueByPath(fromObject, new String[] {"bytesBase64Encoded"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"mimeType"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"mimeType"},
          Common.getValueByPath(fromObject, new String[] {"mimeType"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode imageToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"gcsUri"}))) {
      throw new IllegalArgumentException("gcsUri parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"imageBytes"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"bytesBase64Encoded"},
          Transformers.tBytes(Common.getValueByPath(fromObject, new String[] {"imageBytes"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"mimeType"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"mimeType"},
          Common.getValueByPath(fromObject, new String[] {"mimeType"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode imageToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"gcsUri"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"gcsUri"},
          Common.getValueByPath(fromObject, new String[] {"gcsUri"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"imageBytes"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"bytesBase64Encoded"},
          Transformers.tBytes(Common.getValueByPath(fromObject, new String[] {"imageBytes"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"mimeType"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"mimeType"},
          Common.getValueByPath(fromObject, new String[] {"mimeType"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode listModelsConfigToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"pageSize"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"_query", "pageSize"},
          Common.getValueByPath(fromObject, new String[] {"pageSize"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"pageToken"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"_query", "pageToken"},
          Common.getValueByPath(fromObject, new String[] {"pageToken"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"filter"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"_query", "filter"},
          Common.getValueByPath(fromObject, new String[] {"filter"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"queryBase"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"_url", "models_url"},
          Transformers.tModelsUrl(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"queryBase"})));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode listModelsConfigToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"pageSize"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"_query", "pageSize"},
          Common.getValueByPath(fromObject, new String[] {"pageSize"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"pageToken"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"_query", "pageToken"},
          Common.getValueByPath(fromObject, new String[] {"pageToken"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"filter"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"_query", "filter"},
          Common.getValueByPath(fromObject, new String[] {"filter"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"queryBase"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"_url", "models_url"},
          Transformers.tModelsUrl(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"queryBase"})));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode listModelsParametersToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          listModelsConfigToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode listModelsParametersToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          listModelsConfigToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode listModelsResponseFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"sdkHttpResponse"},
          Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"nextPageToken"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"nextPageToken"},
          Common.getValueByPath(fromObject, new String[] {"nextPageToken"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"_self"}) != null) {
      ArrayNode keyArray =
          (ArrayNode)
              Transformers.tExtractModels(
                  Common.getValueByPath(fromObject, new String[] {"_self"}));
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(modelFromMldev(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"models"}, result);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode listModelsResponseFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"sdkHttpResponse"},
          Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"nextPageToken"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"nextPageToken"},
          Common.getValueByPath(fromObject, new String[] {"nextPageToken"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"_self"}) != null) {
      ArrayNode keyArray =
          (ArrayNode)
              Transformers.tExtractModels(
                  Common.getValueByPath(fromObject, new String[] {"_self"}));
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(modelFromVertex(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"models"}, result);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode maskReferenceConfigToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"maskMode"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"maskMode"},
          Common.getValueByPath(fromObject, new String[] {"maskMode"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"segmentationClasses"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"maskClasses"},
          Common.getValueByPath(fromObject, new String[] {"segmentationClasses"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"maskDilation"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"dilation"},
          Common.getValueByPath(fromObject, new String[] {"maskDilation"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode modelFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"name"},
          Common.getValueByPath(fromObject, new String[] {"name"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"displayName"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"displayName"},
          Common.getValueByPath(fromObject, new String[] {"displayName"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"description"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"description"},
          Common.getValueByPath(fromObject, new String[] {"description"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"version"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"version"},
          Common.getValueByPath(fromObject, new String[] {"version"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"_self"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"tunedModelInfo"},
          Common.getValueByPath(fromObject, new String[] {"_self"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"inputTokenLimit"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"inputTokenLimit"},
          Common.getValueByPath(fromObject, new String[] {"inputTokenLimit"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"outputTokenLimit"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"outputTokenLimit"},
          Common.getValueByPath(fromObject, new String[] {"outputTokenLimit"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"supportedGenerationMethods"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"supportedActions"},
          Common.getValueByPath(fromObject, new String[] {"supportedGenerationMethods"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"temperature"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"temperature"},
          Common.getValueByPath(fromObject, new String[] {"temperature"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"maxTemperature"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"maxTemperature"},
          Common.getValueByPath(fromObject, new String[] {"maxTemperature"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"topP"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"topP"},
          Common.getValueByPath(fromObject, new String[] {"topP"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"topK"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"topK"},
          Common.getValueByPath(fromObject, new String[] {"topK"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"thinking"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"thinking"},
          Common.getValueByPath(fromObject, new String[] {"thinking"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode modelFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"name"},
          Common.getValueByPath(fromObject, new String[] {"name"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"displayName"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"displayName"},
          Common.getValueByPath(fromObject, new String[] {"displayName"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"description"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"description"},
          Common.getValueByPath(fromObject, new String[] {"description"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"versionId"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"version"},
          Common.getValueByPath(fromObject, new String[] {"versionId"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"deployedModels"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"deployedModels"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(endpointFromVertex(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"endpoints"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"labels"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"labels"},
          Common.getValueByPath(fromObject, new String[] {"labels"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"_self"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"tunedModelInfo"},
          tunedModelInfoFromVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"_self"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"defaultCheckpointId"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"defaultCheckpointId"},
          Common.getValueByPath(fromObject, new String[] {"defaultCheckpointId"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"checkpoints"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"checkpoints"},
          Common.getValueByPath(fromObject, new String[] {"checkpoints"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode partToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"functionCall"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"functionCall"},
          Common.getValueByPath(fromObject, new String[] {"functionCall"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"codeExecutionResult"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"codeExecutionResult"},
          Common.getValueByPath(fromObject, new String[] {"codeExecutionResult"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"executableCode"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"executableCode"},
          Common.getValueByPath(fromObject, new String[] {"executableCode"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"fileData"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"fileData"},
          fileDataToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"fileData"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"functionResponse"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"functionResponse"},
          Common.getValueByPath(fromObject, new String[] {"functionResponse"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"inlineData"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"inlineData"},
          blobToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"inlineData"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"text"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"text"},
          Common.getValueByPath(fromObject, new String[] {"text"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"thought"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"thought"},
          Common.getValueByPath(fromObject, new String[] {"thought"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"thoughtSignature"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"thoughtSignature"},
          Common.getValueByPath(fromObject, new String[] {"thoughtSignature"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"videoMetadata"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"videoMetadata"},
          Common.getValueByPath(fromObject, new String[] {"videoMetadata"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode productImageToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"productImage"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"image"},
          imageToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"productImage"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode recontextImageConfigToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"numberOfImages"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "sampleCount"},
          Common.getValueByPath(fromObject, new String[] {"numberOfImages"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"baseSteps"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "baseSteps"},
          Common.getValueByPath(fromObject, new String[] {"baseSteps"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"outputGcsUri"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "storageUri"},
          Common.getValueByPath(fromObject, new String[] {"outputGcsUri"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"seed"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "seed"},
          Common.getValueByPath(fromObject, new String[] {"seed"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"safetyFilterLevel"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "safetySetting"},
          Common.getValueByPath(fromObject, new String[] {"safetyFilterLevel"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"personGeneration"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "personGeneration"},
          Common.getValueByPath(fromObject, new String[] {"personGeneration"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"addWatermark"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "addWatermark"},
          Common.getValueByPath(fromObject, new String[] {"addWatermark"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"outputMimeType"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "outputOptions", "mimeType"},
          Common.getValueByPath(fromObject, new String[] {"outputMimeType"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"outputCompressionQuality"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "outputOptions", "compressionQuality"},
          Common.getValueByPath(fromObject, new String[] {"outputCompressionQuality"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"enhancePrompt"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "enhancePrompt"},
          Common.getValueByPath(fromObject, new String[] {"enhancePrompt"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"labels"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"labels"},
          Common.getValueByPath(fromObject, new String[] {"labels"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode recontextImageParametersToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "model"},
          Transformers.tModel(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"source"}) != null) {
      JsonNode unused =
          recontextImageSourceToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"source"})),
              toObject);
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          recontextImageConfigToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode recontextImageResponseFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"predictions"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"predictions"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(generatedImageFromVertex(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"generatedImages"}, result);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode recontextImageSourceToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"prompt"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"instances[0]", "prompt"},
          Common.getValueByPath(fromObject, new String[] {"prompt"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"personImage"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"instances[0]", "personImage", "image"},
          imageToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"personImage"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"productImages"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"productImages"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(productImageToVertex(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(parentObject, new String[] {"instances[0]", "productImages"}, result);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode referenceImageAPIToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"referenceImage"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"referenceImage"},
          imageToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"referenceImage"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"referenceId"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"referenceId"},
          Common.getValueByPath(fromObject, new String[] {"referenceId"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"referenceType"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"referenceType"},
          Common.getValueByPath(fromObject, new String[] {"referenceType"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"maskImageConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"maskImageConfig"},
          maskReferenceConfigToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"maskImageConfig"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"controlImageConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"controlImageConfig"},
          controlReferenceConfigToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"controlImageConfig"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"styleImageConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"styleImageConfig"},
          Common.getValueByPath(fromObject, new String[] {"styleImageConfig"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"subjectImageConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"subjectImageConfig"},
          Common.getValueByPath(fromObject, new String[] {"subjectImageConfig"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode safetyAttributesFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"safetyAttributes", "categories"})
        != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"categories"},
          Common.getValueByPath(fromObject, new String[] {"safetyAttributes", "categories"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"safetyAttributes", "scores"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"scores"},
          Common.getValueByPath(fromObject, new String[] {"safetyAttributes", "scores"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"contentType"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"contentType"},
          Common.getValueByPath(fromObject, new String[] {"contentType"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode safetyAttributesFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"safetyAttributes", "categories"})
        != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"categories"},
          Common.getValueByPath(fromObject, new String[] {"safetyAttributes", "categories"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"safetyAttributes", "scores"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"scores"},
          Common.getValueByPath(fromObject, new String[] {"safetyAttributes", "scores"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"contentType"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"contentType"},
          Common.getValueByPath(fromObject, new String[] {"contentType"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode safetySettingToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"category"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"category"},
          Common.getValueByPath(fromObject, new String[] {"category"}));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"method"}))) {
      throw new IllegalArgumentException("method parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"threshold"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"threshold"},
          Common.getValueByPath(fromObject, new String[] {"threshold"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode scribbleImageToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"image"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"image"},
          imageToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"image"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode segmentImageConfigToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"mode"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "mode"},
          Common.getValueByPath(fromObject, new String[] {"mode"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"maxPredictions"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "maxPredictions"},
          Common.getValueByPath(fromObject, new String[] {"maxPredictions"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"confidenceThreshold"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "confidenceThreshold"},
          Common.getValueByPath(fromObject, new String[] {"confidenceThreshold"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"maskDilation"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "maskDilation"},
          Common.getValueByPath(fromObject, new String[] {"maskDilation"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"binaryColorThreshold"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "binaryColorThreshold"},
          Common.getValueByPath(fromObject, new String[] {"binaryColorThreshold"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"labels"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"labels"},
          Common.getValueByPath(fromObject, new String[] {"labels"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode segmentImageParametersToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "model"},
          Transformers.tModel(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"source"}) != null) {
      JsonNode unused =
          segmentImageSourceToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"source"})),
              toObject);
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          segmentImageConfigToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode segmentImageResponseFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"predictions"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"predictions"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(generatedImageMaskFromVertex(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"generatedMasks"}, result);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode segmentImageSourceToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"prompt"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"instances[0]", "prompt"},
          Common.getValueByPath(fromObject, new String[] {"prompt"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"image"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"instances[0]", "image"},
          imageToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"image"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"scribbleImage"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"instances[0]", "scribble"},
          scribbleImageToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"scribbleImage"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode speechConfigToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"languageCode"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"languageCode"},
          Common.getValueByPath(fromObject, new String[] {"languageCode"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"voiceConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"voiceConfig"},
          Common.getValueByPath(fromObject, new String[] {"voiceConfig"}));
    }

    if (!Common.isZero(
        Common.getValueByPath(fromObject, new String[] {"multiSpeakerVoiceConfig"}))) {
      throw new IllegalArgumentException(
          "multiSpeakerVoiceConfig parameter is not supported in Vertex AI.");
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode toolToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"functionDeclarations"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"functionDeclarations"},
          Common.getValueByPath(fromObject, new String[] {"functionDeclarations"}));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"retrieval"}))) {
      throw new IllegalArgumentException("retrieval parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"googleSearchRetrieval"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"googleSearchRetrieval"},
          Common.getValueByPath(fromObject, new String[] {"googleSearchRetrieval"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"computerUse"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"computerUse"},
          Common.getValueByPath(fromObject, new String[] {"computerUse"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"fileSearch"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"fileSearch"},
          Common.getValueByPath(fromObject, new String[] {"fileSearch"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"codeExecution"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"codeExecution"},
          Common.getValueByPath(fromObject, new String[] {"codeExecution"}));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"enterpriseWebSearch"}))) {
      throw new IllegalArgumentException(
          "enterpriseWebSearch parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"googleMaps"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"googleMaps"},
          googleMapsToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"googleMaps"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"googleSearch"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"googleSearch"},
          googleSearchToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"googleSearch"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"urlContext"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"urlContext"},
          Common.getValueByPath(fromObject, new String[] {"urlContext"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode toolToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"functionDeclarations"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"functionDeclarations"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(functionDeclarationToVertex(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"functionDeclarations"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"retrieval"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"retrieval"},
          Common.getValueByPath(fromObject, new String[] {"retrieval"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"googleSearchRetrieval"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"googleSearchRetrieval"},
          Common.getValueByPath(fromObject, new String[] {"googleSearchRetrieval"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"computerUse"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"computerUse"},
          Common.getValueByPath(fromObject, new String[] {"computerUse"}));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"fileSearch"}))) {
      throw new IllegalArgumentException("fileSearch parameter is not supported in Vertex AI.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"codeExecution"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"codeExecution"},
          Common.getValueByPath(fromObject, new String[] {"codeExecution"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"enterpriseWebSearch"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"enterpriseWebSearch"},
          Common.getValueByPath(fromObject, new String[] {"enterpriseWebSearch"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"googleMaps"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"googleMaps"},
          Common.getValueByPath(fromObject, new String[] {"googleMaps"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"googleSearch"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"googleSearch"},
          Common.getValueByPath(fromObject, new String[] {"googleSearch"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"urlContext"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"urlContext"},
          Common.getValueByPath(fromObject, new String[] {"urlContext"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode tunedModelInfoFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(
            fromObject, new String[] {"labels", "google-vertex-llm-tuning-base-model-id"})
        != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"baseModel"},
          Common.getValueByPath(
              fromObject, new String[] {"labels", "google-vertex-llm-tuning-base-model-id"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"createTime"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"createTime"},
          Common.getValueByPath(fromObject, new String[] {"createTime"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"updateTime"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"updateTime"},
          Common.getValueByPath(fromObject, new String[] {"updateTime"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode updateModelConfigToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"displayName"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"displayName"},
          Common.getValueByPath(fromObject, new String[] {"displayName"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"description"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"description"},
          Common.getValueByPath(fromObject, new String[] {"description"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"defaultCheckpointId"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"defaultCheckpointId"},
          Common.getValueByPath(fromObject, new String[] {"defaultCheckpointId"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode updateModelConfigToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"displayName"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"displayName"},
          Common.getValueByPath(fromObject, new String[] {"displayName"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"description"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"description"},
          Common.getValueByPath(fromObject, new String[] {"description"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"defaultCheckpointId"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"defaultCheckpointId"},
          Common.getValueByPath(fromObject, new String[] {"defaultCheckpointId"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode updateModelParametersToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "name"},
          Transformers.tModel(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          updateModelConfigToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode updateModelParametersToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "model"},
          Transformers.tModel(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          updateModelConfigToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode upscaleImageAPIConfigToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"outputGcsUri"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "storageUri"},
          Common.getValueByPath(fromObject, new String[] {"outputGcsUri"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"safetyFilterLevel"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "safetySetting"},
          Common.getValueByPath(fromObject, new String[] {"safetyFilterLevel"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"personGeneration"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "personGeneration"},
          Common.getValueByPath(fromObject, new String[] {"personGeneration"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"includeRaiReason"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "includeRaiReason"},
          Common.getValueByPath(fromObject, new String[] {"includeRaiReason"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"outputMimeType"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "outputOptions", "mimeType"},
          Common.getValueByPath(fromObject, new String[] {"outputMimeType"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"outputCompressionQuality"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "outputOptions", "compressionQuality"},
          Common.getValueByPath(fromObject, new String[] {"outputCompressionQuality"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"enhanceInputImage"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "upscaleConfig", "enhanceInputImage"},
          Common.getValueByPath(fromObject, new String[] {"enhanceInputImage"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"imagePreservationFactor"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "upscaleConfig", "imagePreservationFactor"},
          Common.getValueByPath(fromObject, new String[] {"imagePreservationFactor"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"labels"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"labels"},
          Common.getValueByPath(fromObject, new String[] {"labels"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"numberOfImages"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "sampleCount"},
          Common.getValueByPath(fromObject, new String[] {"numberOfImages"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"mode"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "mode"},
          Common.getValueByPath(fromObject, new String[] {"mode"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode upscaleImageAPIParametersToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "model"},
          Transformers.tModel(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"image"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"instances[0]", "image"},
          imageToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"image"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"upscaleFactor"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"parameters", "upscaleConfig", "upscaleFactor"},
          Common.getValueByPath(fromObject, new String[] {"upscaleFactor"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          upscaleImageAPIConfigToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode upscaleImageResponseFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"sdkHttpResponse"},
          Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"predictions"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"predictions"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(generatedImageFromVertex(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"generatedImages"}, result);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode videoFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"uri"}) != null) {
      Common.setValueByPath(
          toObject, new String[] {"uri"}, Common.getValueByPath(fromObject, new String[] {"uri"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"encodedVideo"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"videoBytes"},
          Transformers.tBytes(Common.getValueByPath(fromObject, new String[] {"encodedVideo"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"encoding"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"mimeType"},
          Common.getValueByPath(fromObject, new String[] {"encoding"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode videoFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"gcsUri"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"uri"},
          Common.getValueByPath(fromObject, new String[] {"gcsUri"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"bytesBase64Encoded"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"videoBytes"},
          Transformers.tBytes(
              Common.getValueByPath(fromObject, new String[] {"bytesBase64Encoded"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"mimeType"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"mimeType"},
          Common.getValueByPath(fromObject, new String[] {"mimeType"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode videoGenerationMaskToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"image"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_self"},
          imageToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"image"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"maskMode"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"maskMode"},
          Common.getValueByPath(fromObject, new String[] {"maskMode"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode videoGenerationReferenceImageToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"image"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"image"},
          imageToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"image"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"referenceType"}) != null) {
      videoGenerationReferenceTypeMldevEnumValidate(
          Common.getValueByPath(fromObject, new String[] {"referenceType"}));
      Common.setValueByPath(
          toObject,
          new String[] {"referenceType"},
          Common.getValueByPath(fromObject, new String[] {"referenceType"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode videoGenerationReferenceImageToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"image"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"image"},
          imageToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"image"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"referenceType"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"referenceType"},
          Common.getValueByPath(fromObject, new String[] {"referenceType"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode videoToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"uri"}) != null) {
      Common.setValueByPath(
          toObject, new String[] {"uri"}, Common.getValueByPath(fromObject, new String[] {"uri"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"videoBytes"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"encodedVideo"},
          Transformers.tBytes(Common.getValueByPath(fromObject, new String[] {"videoBytes"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"mimeType"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"encoding"},
          Common.getValueByPath(fromObject, new String[] {"mimeType"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode videoToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"uri"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"gcsUri"},
          Common.getValueByPath(fromObject, new String[] {"uri"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"videoBytes"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"bytesBase64Encoded"},
          Transformers.tBytes(Common.getValueByPath(fromObject, new String[] {"videoBytes"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"mimeType"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"mimeType"},
          Common.getValueByPath(fromObject, new String[] {"mimeType"}));
    }

    return toObject;
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForPrivateGenerateContent(
      String model, List<Content> contents, GenerateContentConfig config) {

    GenerateContentParameters.Builder parameterBuilder = GenerateContentParameters.builder();

    if (!Common.isZero(model)) {
      parameterBuilder.model(model);
    }
    if (!Common.isZero(contents)) {
      parameterBuilder.contents(contents);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    if (this.apiClient.vertexAI()) {
      body = generateContentParametersToVertex(this.apiClient, parameterNode, null);
      path = Common.formatMap("{model}:generateContent", body.get("_url"));
    } else {
      body = generateContentParametersToMldev(this.apiClient, parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("{model}:generateContent", body.get("_url"));
      } else {
        path = "{model}:generateContent";
      }
    }
    body.remove("_url");

    JsonNode queryParams = body.get("_query");
    if (queryParams != null) {
      body.remove("_query");
      path = String.format("%s?%s", path, Common.urlEncode((ObjectNode) queryParams));
    }

    // TODO: Remove the hack that removes config.
    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    return new BuiltRequest(path, JsonSerializable.toJsonString(body), requestHttpOptions);
  }

  /** A shared processResponse function for both sync and async methods. */
  GenerateContentResponse processResponseForPrivateGenerateContent(
      ApiResponse response, GenerateContentConfig config) {
    ResponseBody responseBody = response.getBody();
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }

    if (config != null && config.shouldReturnHttpResponse().orElse(false)) {
      Headers responseHeaders = response.getHeaders();
      if (responseHeaders == null) {
        return GenerateContentResponse.builder()
            .sdkHttpResponse(HttpResponse.builder().body(responseString))
            .build();
      }
      Map<String, String> headers = new HashMap<>();
      for (String headerName : responseHeaders.names()) {
        headers.put(headerName, responseHeaders.get(headerName));
      }
      return GenerateContentResponse.builder()
          .sdkHttpResponse(HttpResponse.builder().headers(headers).body(responseString))
          .build();
    }

    JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);

    if (this.apiClient.vertexAI()) {
      responseNode = generateContentResponseFromVertex(responseNode, null);
    }

    if (!this.apiClient.vertexAI()) {
      responseNode = generateContentResponseFromMldev(responseNode, null);
    }

    GenerateContentResponse sdkResponse =
        JsonSerializable.fromJsonNode(responseNode, GenerateContentResponse.class);
    Headers responseHeaders = response.getHeaders();
    if (responseHeaders == null) {
      return sdkResponse;
    }
    Map<String, String> headers = new HashMap<>();
    for (String headerName : responseHeaders.names()) {
      headers.put(headerName, responseHeaders.get(headerName));
    }
    return sdkResponse.toBuilder().sdkHttpResponse(HttpResponse.builder().headers(headers)).build();
  }

  GenerateContentResponse privateGenerateContent(
      String model, List<Content> contents, GenerateContentConfig config) {
    BuiltRequest builtRequest = buildRequestForPrivateGenerateContent(model, contents, config);

    try (ApiResponse response =
        this.apiClient.request(
            "post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)) {
      return processResponseForPrivateGenerateContent(response, config);
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForPrivateGenerateContentStream(
      String model, List<Content> contents, GenerateContentConfig config) {

    GenerateContentParameters.Builder parameterBuilder = GenerateContentParameters.builder();

    if (!Common.isZero(model)) {
      parameterBuilder.model(model);
    }
    if (!Common.isZero(contents)) {
      parameterBuilder.contents(contents);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    if (this.apiClient.vertexAI()) {
      body = generateContentParametersToVertex(this.apiClient, parameterNode, null);
      path = Common.formatMap("{model}:streamGenerateContent?alt=sse", body.get("_url"));
    } else {
      body = generateContentParametersToMldev(this.apiClient, parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("{model}:streamGenerateContent?alt=sse", body.get("_url"));
      } else {
        path = "{model}:streamGenerateContent?alt=sse";
      }
    }
    body.remove("_url");

    JsonNode queryParams = body.get("_query");
    if (queryParams != null) {
      body.remove("_query");
      path = String.format("%s?%s", path, Common.urlEncode((ObjectNode) queryParams));
    }

    // TODO: Remove the hack that removes config.
    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    return new BuiltRequest(path, JsonSerializable.toJsonString(body), requestHttpOptions);
  }

  /** A shared processResponse function for both sync and async methods. */
  ResponseStream<GenerateContentResponse> processResponseForPrivateGenerateContentStream(
      ApiResponse response, GenerateContentConfig config) {
    String converterName;

    if (this.apiClient.vertexAI()) {
      converterName = "generateContentResponseFromVertex";
    } else {
      converterName = "generateContentResponseFromMldev";
    }
    return new ResponseStream<GenerateContentResponse>(
        GenerateContentResponse.class, response, this, converterName, false, true);
  }

  ResponseStream<GenerateContentResponse> privateGenerateContentStream(
      String model, List<Content> contents, GenerateContentConfig config) {
    BuiltRequest builtRequest =
        buildRequestForPrivateGenerateContentStream(model, contents, config);

    ApiResponse response =
        this.apiClient.request(
            "post", builtRequest.path, builtRequest.body, builtRequest.httpOptions);
    return processResponseForPrivateGenerateContentStream(response, config);
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForPrivateEmbedContent(
      String model, List<Content> contents, EmbedContentConfig config) {

    EmbedContentParameters.Builder parameterBuilder = EmbedContentParameters.builder();

    if (!Common.isZero(model)) {
      parameterBuilder.model(model);
    }
    if (!Common.isZero(contents)) {
      parameterBuilder.contents(contents);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    if (this.apiClient.vertexAI()) {
      body = embedContentParametersToVertex(this.apiClient, parameterNode, null);
      path = Common.formatMap("{model}:predict", body.get("_url"));
    } else {
      body = embedContentParametersToMldev(this.apiClient, parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("{model}:batchEmbedContents", body.get("_url"));
      } else {
        path = "{model}:batchEmbedContents";
      }
    }
    body.remove("_url");

    JsonNode queryParams = body.get("_query");
    if (queryParams != null) {
      body.remove("_query");
      path = String.format("%s?%s", path, Common.urlEncode((ObjectNode) queryParams));
    }

    // TODO: Remove the hack that removes config.
    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    return new BuiltRequest(path, JsonSerializable.toJsonString(body), requestHttpOptions);
  }

  /** A shared processResponse function for both sync and async methods. */
  EmbedContentResponse processResponseForPrivateEmbedContent(
      ApiResponse response, EmbedContentConfig config) {
    ResponseBody responseBody = response.getBody();
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }

    JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);

    if (this.apiClient.vertexAI()) {
      responseNode = embedContentResponseFromVertex(responseNode, null);
    }

    if (!this.apiClient.vertexAI()) {
      responseNode = embedContentResponseFromMldev(responseNode, null);
    }

    EmbedContentResponse sdkResponse =
        JsonSerializable.fromJsonNode(responseNode, EmbedContentResponse.class);
    Headers responseHeaders = response.getHeaders();
    if (responseHeaders == null) {
      return sdkResponse;
    }
    Map<String, String> headers = new HashMap<>();
    for (String headerName : responseHeaders.names()) {
      headers.put(headerName, responseHeaders.get(headerName));
    }
    return sdkResponse.toBuilder().sdkHttpResponse(HttpResponse.builder().headers(headers)).build();
  }

  EmbedContentResponse privateEmbedContent(
      String model, List<Content> contents, EmbedContentConfig config) {
    BuiltRequest builtRequest = buildRequestForPrivateEmbedContent(model, contents, config);

    try (ApiResponse response =
        this.apiClient.request(
            "post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)) {
      return processResponseForPrivateEmbedContent(response, config);
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForPrivateGenerateImages(
      String model, String prompt, GenerateImagesConfig config) {

    GenerateImagesParameters.Builder parameterBuilder = GenerateImagesParameters.builder();

    if (!Common.isZero(model)) {
      parameterBuilder.model(model);
    }
    if (!Common.isZero(prompt)) {
      parameterBuilder.prompt(prompt);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    if (this.apiClient.vertexAI()) {
      body = generateImagesParametersToVertex(this.apiClient, parameterNode, null);
      path = Common.formatMap("{model}:predict", body.get("_url"));
    } else {
      body = generateImagesParametersToMldev(this.apiClient, parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("{model}:predict", body.get("_url"));
      } else {
        path = "{model}:predict";
      }
    }
    body.remove("_url");

    JsonNode queryParams = body.get("_query");
    if (queryParams != null) {
      body.remove("_query");
      path = String.format("%s?%s", path, Common.urlEncode((ObjectNode) queryParams));
    }

    // TODO: Remove the hack that removes config.
    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    return new BuiltRequest(path, JsonSerializable.toJsonString(body), requestHttpOptions);
  }

  /** A shared processResponse function for both sync and async methods. */
  GenerateImagesResponse processResponseForPrivateGenerateImages(
      ApiResponse response, GenerateImagesConfig config) {
    ResponseBody responseBody = response.getBody();
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }

    JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);

    if (this.apiClient.vertexAI()) {
      responseNode = generateImagesResponseFromVertex(responseNode, null);
    }

    if (!this.apiClient.vertexAI()) {
      responseNode = generateImagesResponseFromMldev(responseNode, null);
    }

    GenerateImagesResponse sdkResponse =
        JsonSerializable.fromJsonNode(responseNode, GenerateImagesResponse.class);
    Headers responseHeaders = response.getHeaders();
    if (responseHeaders == null) {
      return sdkResponse;
    }
    Map<String, String> headers = new HashMap<>();
    for (String headerName : responseHeaders.names()) {
      headers.put(headerName, responseHeaders.get(headerName));
    }
    return sdkResponse.toBuilder().sdkHttpResponse(HttpResponse.builder().headers(headers)).build();
  }

  /** Private method for generating images. */
  GenerateImagesResponse privateGenerateImages(
      String model, String prompt, GenerateImagesConfig config) {
    BuiltRequest builtRequest = buildRequestForPrivateGenerateImages(model, prompt, config);

    try (ApiResponse response =
        this.apiClient.request(
            "post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)) {
      return processResponseForPrivateGenerateImages(response, config);
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForPrivateEditImage(
      String model,
      String prompt,
      List<ReferenceImageAPI> referenceImages,
      EditImageConfig config) {

    EditImageParameters.Builder parameterBuilder = EditImageParameters.builder();

    if (!Common.isZero(model)) {
      parameterBuilder.model(model);
    }
    if (!Common.isZero(prompt)) {
      parameterBuilder.prompt(prompt);
    }
    if (!Common.isZero(referenceImages)) {
      parameterBuilder.referenceImages(referenceImages);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    if (this.apiClient.vertexAI()) {
      body = editImageParametersToVertex(this.apiClient, parameterNode, null);
      path = Common.formatMap("{model}:predict", body.get("_url"));
    } else {
      throw new UnsupportedOperationException(
          "This method is only supported in the Vertex AI client.");
    }
    body.remove("_url");

    JsonNode queryParams = body.get("_query");
    if (queryParams != null) {
      body.remove("_query");
      path = String.format("%s?%s", path, Common.urlEncode((ObjectNode) queryParams));
    }

    // TODO: Remove the hack that removes config.
    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    return new BuiltRequest(path, JsonSerializable.toJsonString(body), requestHttpOptions);
  }

  /** A shared processResponse function for both sync and async methods. */
  EditImageResponse processResponseForPrivateEditImage(
      ApiResponse response, EditImageConfig config) {
    ResponseBody responseBody = response.getBody();
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }

    JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);

    if (this.apiClient.vertexAI()) {
      responseNode = editImageResponseFromVertex(responseNode, null);
    }

    if (!this.apiClient.vertexAI()) {
      throw new UnsupportedOperationException(
          "This method is only supported in the Vertex AI client.");
    }

    EditImageResponse sdkResponse =
        JsonSerializable.fromJsonNode(responseNode, EditImageResponse.class);
    Headers responseHeaders = response.getHeaders();
    if (responseHeaders == null) {
      return sdkResponse;
    }
    Map<String, String> headers = new HashMap<>();
    for (String headerName : responseHeaders.names()) {
      headers.put(headerName, responseHeaders.get(headerName));
    }
    return sdkResponse.toBuilder().sdkHttpResponse(HttpResponse.builder().headers(headers)).build();
  }

  /** Private method for editing an image. */
  EditImageResponse privateEditImage(
      String model,
      String prompt,
      List<ReferenceImageAPI> referenceImages,
      EditImageConfig config) {
    BuiltRequest builtRequest =
        buildRequestForPrivateEditImage(model, prompt, referenceImages, config);

    try (ApiResponse response =
        this.apiClient.request(
            "post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)) {
      return processResponseForPrivateEditImage(response, config);
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForPrivateUpscaleImage(
      String model, Image image, String upscaleFactor, UpscaleImageAPIConfig config) {

    UpscaleImageAPIParameters.Builder parameterBuilder = UpscaleImageAPIParameters.builder();

    if (!Common.isZero(model)) {
      parameterBuilder.model(model);
    }
    if (!Common.isZero(image)) {
      parameterBuilder.image(image);
    }
    if (!Common.isZero(upscaleFactor)) {
      parameterBuilder.upscaleFactor(upscaleFactor);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    if (this.apiClient.vertexAI()) {
      body = upscaleImageAPIParametersToVertex(this.apiClient, parameterNode, null);
      path = Common.formatMap("{model}:predict", body.get("_url"));
    } else {
      throw new UnsupportedOperationException(
          "This method is only supported in the Vertex AI client.");
    }
    body.remove("_url");

    JsonNode queryParams = body.get("_query");
    if (queryParams != null) {
      body.remove("_query");
      path = String.format("%s?%s", path, Common.urlEncode((ObjectNode) queryParams));
    }

    // TODO: Remove the hack that removes config.
    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    return new BuiltRequest(path, JsonSerializable.toJsonString(body), requestHttpOptions);
  }

  /** A shared processResponse function for both sync and async methods. */
  UpscaleImageResponse processResponseForPrivateUpscaleImage(
      ApiResponse response, UpscaleImageAPIConfig config) {
    ResponseBody responseBody = response.getBody();
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }

    JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);

    if (this.apiClient.vertexAI()) {
      responseNode = upscaleImageResponseFromVertex(responseNode, null);
    }

    if (!this.apiClient.vertexAI()) {
      throw new UnsupportedOperationException(
          "This method is only supported in the Vertex AI client.");
    }

    UpscaleImageResponse sdkResponse =
        JsonSerializable.fromJsonNode(responseNode, UpscaleImageResponse.class);
    Headers responseHeaders = response.getHeaders();
    if (responseHeaders == null) {
      return sdkResponse;
    }
    Map<String, String> headers = new HashMap<>();
    for (String headerName : responseHeaders.names()) {
      headers.put(headerName, responseHeaders.get(headerName));
    }
    return sdkResponse.toBuilder().sdkHttpResponse(HttpResponse.builder().headers(headers)).build();
  }

  /** Private method for upscaling an image. */
  UpscaleImageResponse privateUpscaleImage(
      String model, Image image, String upscaleFactor, UpscaleImageAPIConfig config) {
    BuiltRequest builtRequest =
        buildRequestForPrivateUpscaleImage(model, image, upscaleFactor, config);

    try (ApiResponse response =
        this.apiClient.request(
            "post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)) {
      return processResponseForPrivateUpscaleImage(response, config);
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForRecontextImage(
      String model, RecontextImageSource source, RecontextImageConfig config) {

    RecontextImageParameters.Builder parameterBuilder = RecontextImageParameters.builder();

    if (!Common.isZero(model)) {
      parameterBuilder.model(model);
    }
    if (!Common.isZero(source)) {
      parameterBuilder.source(source);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    if (this.apiClient.vertexAI()) {
      body = recontextImageParametersToVertex(this.apiClient, parameterNode, null);
      path = Common.formatMap("{model}:predict", body.get("_url"));
    } else {
      throw new UnsupportedOperationException(
          "This method is only supported in the Vertex AI client.");
    }
    body.remove("_url");

    JsonNode queryParams = body.get("_query");
    if (queryParams != null) {
      body.remove("_query");
      path = String.format("%s?%s", path, Common.urlEncode((ObjectNode) queryParams));
    }

    // TODO: Remove the hack that removes config.
    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    return new BuiltRequest(path, JsonSerializable.toJsonString(body), requestHttpOptions);
  }

  /** A shared processResponse function for both sync and async methods. */
  RecontextImageResponse processResponseForRecontextImage(
      ApiResponse response, RecontextImageConfig config) {
    ResponseBody responseBody = response.getBody();
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }

    JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);

    if (this.apiClient.vertexAI()) {
      responseNode = recontextImageResponseFromVertex(responseNode, null);
    }

    if (!this.apiClient.vertexAI()) {
      throw new UnsupportedOperationException(
          "This method is only supported in the Vertex AI client.");
    }

    return JsonSerializable.fromJsonNode(responseNode, RecontextImageResponse.class);
  }

  /**
   * Recontextualizes an image.
   *
   * <p>There are two types of recontextualization currently supported: 1) Imagen Product Recontext
   * - Generate images of products in new scenes and contexts. 2) Virtual Try-On: Generate images of
   * persons modeling fashion products.
   *
   * @param model the name of the GenAI model to use for image recontext
   * @param source a {@link com.google.genai.types.RecontextImageSource} An object containing the
   *     source inputs (prompt, personImage, productImages) for image recontext. prompt is optional
   *     for product recontext and disallowed for virtual try-on. personImage is required for
   *     virtual try-on, disallowed for product recontext. productImages is required for both
   *     product recontext and virtual try-on. Only one product image is supported for virtual
   *     try-on, and up to 3 product images (different angles of the same product) are supported for
   *     product recontext.
   * @param config a {@link com.google.genai.types.RecontextImageConfig} instance that specifies the
   *     optional configurations
   * @return a {@link com.google.genai.types.RecontextImageResponse} instance that contains the
   *     generated images.
   */
  public RecontextImageResponse recontextImage(
      String model, RecontextImageSource source, RecontextImageConfig config) {
    BuiltRequest builtRequest = buildRequestForRecontextImage(model, source, config);

    try (ApiResponse response =
        this.apiClient.request(
            "post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)) {
      return processResponseForRecontextImage(response, config);
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForSegmentImage(
      String model, SegmentImageSource source, SegmentImageConfig config) {

    SegmentImageParameters.Builder parameterBuilder = SegmentImageParameters.builder();

    if (!Common.isZero(model)) {
      parameterBuilder.model(model);
    }
    if (!Common.isZero(source)) {
      parameterBuilder.source(source);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    if (this.apiClient.vertexAI()) {
      body = segmentImageParametersToVertex(this.apiClient, parameterNode, null);
      path = Common.formatMap("{model}:predict", body.get("_url"));
    } else {
      throw new UnsupportedOperationException(
          "This method is only supported in the Vertex AI client.");
    }
    body.remove("_url");

    JsonNode queryParams = body.get("_query");
    if (queryParams != null) {
      body.remove("_query");
      path = String.format("%s?%s", path, Common.urlEncode((ObjectNode) queryParams));
    }

    // TODO: Remove the hack that removes config.
    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    return new BuiltRequest(path, JsonSerializable.toJsonString(body), requestHttpOptions);
  }

  /** A shared processResponse function for both sync and async methods. */
  SegmentImageResponse processResponseForSegmentImage(
      ApiResponse response, SegmentImageConfig config) {
    ResponseBody responseBody = response.getBody();
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }

    JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);

    if (this.apiClient.vertexAI()) {
      responseNode = segmentImageResponseFromVertex(responseNode, null);
    }

    if (!this.apiClient.vertexAI()) {
      throw new UnsupportedOperationException(
          "This method is only supported in the Vertex AI client.");
    }

    return JsonSerializable.fromJsonNode(responseNode, SegmentImageResponse.class);
  }

  /**
   * Segments an image, creating a mask of a specified area.
   *
   * @param model the name of the GenAI model to use for image segmentation
   * @param source a {@link com.google.genai.types.SegmentImageSource} An object containing the
   *     source inputs (prompt, image, scribbleImmage) for image segmentation. The prompt is
   *     required for prompt mode and semantic mode, disallowed for other modes. scribbleImage is
   *     required for the interactive mode, disallowed for other modes.
   * @param config a {@link com.google.genai.types.SegmentImageConfig} instance that specifies the
   *     optional configurations
   * @return a {@link com.google.genai.types.SegmentImageResponse} instance that contains the
   *     generated mask.
   */
  public SegmentImageResponse segmentImage(
      String model, SegmentImageSource source, SegmentImageConfig config) {
    BuiltRequest builtRequest = buildRequestForSegmentImage(model, source, config);

    try (ApiResponse response =
        this.apiClient.request(
            "post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)) {
      return processResponseForSegmentImage(response, config);
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForGet(String model, GetModelConfig config) {

    GetModelParameters.Builder parameterBuilder = GetModelParameters.builder();

    if (!Common.isZero(model)) {
      parameterBuilder.model(model);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    if (this.apiClient.vertexAI()) {
      body = getModelParametersToVertex(this.apiClient, parameterNode, null);
      path = Common.formatMap("{name}", body.get("_url"));
    } else {
      body = getModelParametersToMldev(this.apiClient, parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("{name}", body.get("_url"));
      } else {
        path = "{name}";
      }
    }
    body.remove("_url");

    JsonNode queryParams = body.get("_query");
    if (queryParams != null) {
      body.remove("_query");
      path = String.format("%s?%s", path, Common.urlEncode((ObjectNode) queryParams));
    }

    // TODO: Remove the hack that removes config.
    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    return new BuiltRequest(path, JsonSerializable.toJsonString(body), requestHttpOptions);
  }

  /** A shared processResponse function for both sync and async methods. */
  Model processResponseForGet(ApiResponse response, GetModelConfig config) {
    ResponseBody responseBody = response.getBody();
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }

    JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);

    if (this.apiClient.vertexAI()) {
      responseNode = modelFromVertex(responseNode, null);
    }

    if (!this.apiClient.vertexAI()) {
      responseNode = modelFromMldev(responseNode, null);
    }

    return JsonSerializable.fromJsonNode(responseNode, Model.class);
  }

  /**
   * Fetches information about a model by name.
   *
   * @example ```java Model model = client.models.get("gemini-2.0-flash"); ```
   */
  public Model get(String model, GetModelConfig config) {
    BuiltRequest builtRequest = buildRequestForGet(model, config);

    try (ApiResponse response =
        this.apiClient.request(
            "get", builtRequest.path, builtRequest.body, builtRequest.httpOptions)) {
      return processResponseForGet(response, config);
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForPrivateList(ListModelsConfig config) {

    ListModelsParameters.Builder parameterBuilder = ListModelsParameters.builder();

    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    if (this.apiClient.vertexAI()) {
      body = listModelsParametersToVertex(this.apiClient, parameterNode, null);
      path = Common.formatMap("{models_url}", body.get("_url"));
    } else {
      body = listModelsParametersToMldev(this.apiClient, parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("{models_url}", body.get("_url"));
      } else {
        path = "{models_url}";
      }
    }
    body.remove("_url");

    JsonNode queryParams = body.get("_query");
    if (queryParams != null) {
      body.remove("_query");
      path = String.format("%s?%s", path, Common.urlEncode((ObjectNode) queryParams));
    }

    // TODO: Remove the hack that removes config.
    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    return new BuiltRequest(path, JsonSerializable.toJsonString(body), requestHttpOptions);
  }

  /** A shared processResponse function for both sync and async methods. */
  ListModelsResponse processResponseForPrivateList(ApiResponse response, ListModelsConfig config) {
    ResponseBody responseBody = response.getBody();
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }

    JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);

    if (this.apiClient.vertexAI()) {
      responseNode = listModelsResponseFromVertex(responseNode, null);
    }

    if (!this.apiClient.vertexAI()) {
      responseNode = listModelsResponseFromMldev(responseNode, null);
    }

    ListModelsResponse sdkResponse =
        JsonSerializable.fromJsonNode(responseNode, ListModelsResponse.class);
    Headers responseHeaders = response.getHeaders();
    if (responseHeaders == null) {
      return sdkResponse;
    }
    Map<String, String> headers = new HashMap<>();
    for (String headerName : responseHeaders.names()) {
      headers.put(headerName, responseHeaders.get(headerName));
    }
    return sdkResponse.toBuilder().sdkHttpResponse(HttpResponse.builder().headers(headers)).build();
  }

  ListModelsResponse privateList(ListModelsConfig config) {
    BuiltRequest builtRequest = buildRequestForPrivateList(config);

    try (ApiResponse response =
        this.apiClient.request(
            "get", builtRequest.path, builtRequest.body, builtRequest.httpOptions)) {
      return processResponseForPrivateList(response, config);
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForUpdate(String model, UpdateModelConfig config) {

    UpdateModelParameters.Builder parameterBuilder = UpdateModelParameters.builder();

    if (!Common.isZero(model)) {
      parameterBuilder.model(model);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    if (this.apiClient.vertexAI()) {
      body = updateModelParametersToVertex(this.apiClient, parameterNode, null);
      path = Common.formatMap("{model}", body.get("_url"));
    } else {
      body = updateModelParametersToMldev(this.apiClient, parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("{name}", body.get("_url"));
      } else {
        path = "{name}";
      }
    }
    body.remove("_url");

    JsonNode queryParams = body.get("_query");
    if (queryParams != null) {
      body.remove("_query");
      path = String.format("%s?%s", path, Common.urlEncode((ObjectNode) queryParams));
    }

    // TODO: Remove the hack that removes config.
    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    return new BuiltRequest(path, JsonSerializable.toJsonString(body), requestHttpOptions);
  }

  /** A shared processResponse function for both sync and async methods. */
  Model processResponseForUpdate(ApiResponse response, UpdateModelConfig config) {
    ResponseBody responseBody = response.getBody();
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }

    JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);

    if (this.apiClient.vertexAI()) {
      responseNode = modelFromVertex(responseNode, null);
    }

    if (!this.apiClient.vertexAI()) {
      responseNode = modelFromMldev(responseNode, null);
    }

    return JsonSerializable.fromJsonNode(responseNode, Model.class);
  }

  /**
   * Updates a tuned model by its name.
   *
   * @param model The name of the tuned model to update
   * @param config A {@link com.google.genai.types.UpdateModelConfig} instance that specifies the
   *     optional configurations
   * @return A {@link com.google.genai.types.Model} instance
   * @example ```java Model model = client.models.update( "tunedModels/12345",
   *     UpdateModelConfig.builder() .displayName("New display name") .description("New
   *     description") .build()); ```
   */
  public Model update(String model, UpdateModelConfig config) {
    BuiltRequest builtRequest = buildRequestForUpdate(model, config);

    try (ApiResponse response =
        this.apiClient.request(
            "patch", builtRequest.path, builtRequest.body, builtRequest.httpOptions)) {
      return processResponseForUpdate(response, config);
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForDelete(String model, DeleteModelConfig config) {

    DeleteModelParameters.Builder parameterBuilder = DeleteModelParameters.builder();

    if (!Common.isZero(model)) {
      parameterBuilder.model(model);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    if (this.apiClient.vertexAI()) {
      body = deleteModelParametersToVertex(this.apiClient, parameterNode, null);
      path = Common.formatMap("{name}", body.get("_url"));
    } else {
      body = deleteModelParametersToMldev(this.apiClient, parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("{name}", body.get("_url"));
      } else {
        path = "{name}";
      }
    }
    body.remove("_url");

    JsonNode queryParams = body.get("_query");
    if (queryParams != null) {
      body.remove("_query");
      path = String.format("%s?%s", path, Common.urlEncode((ObjectNode) queryParams));
    }

    // TODO: Remove the hack that removes config.
    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    return new BuiltRequest(path, JsonSerializable.toJsonString(body), requestHttpOptions);
  }

  /** A shared processResponse function for both sync and async methods. */
  DeleteModelResponse processResponseForDelete(ApiResponse response, DeleteModelConfig config) {
    ResponseBody responseBody = response.getBody();
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }

    JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);

    if (this.apiClient.vertexAI()) {
      responseNode = deleteModelResponseFromVertex(responseNode, null);
    }

    if (!this.apiClient.vertexAI()) {
      responseNode = deleteModelResponseFromMldev(responseNode, null);
    }

    DeleteModelResponse sdkResponse =
        JsonSerializable.fromJsonNode(responseNode, DeleteModelResponse.class);
    Headers responseHeaders = response.getHeaders();
    if (responseHeaders == null) {
      return sdkResponse;
    }
    Map<String, String> headers = new HashMap<>();
    for (String headerName : responseHeaders.names()) {
      headers.put(headerName, responseHeaders.get(headerName));
    }
    return sdkResponse.toBuilder().sdkHttpResponse(HttpResponse.builder().headers(headers)).build();
  }

  /**
   * Fetches information about a model by name.
   *
   * @example ```java Model model = client.models.delete("tunedModels/12345"); ```
   */
  public DeleteModelResponse delete(String model, DeleteModelConfig config) {
    BuiltRequest builtRequest = buildRequestForDelete(model, config);

    try (ApiResponse response =
        this.apiClient.request(
            "delete", builtRequest.path, builtRequest.body, builtRequest.httpOptions)) {
      return processResponseForDelete(response, config);
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForCountTokens(
      String model, List<Content> contents, CountTokensConfig config) {

    CountTokensParameters.Builder parameterBuilder = CountTokensParameters.builder();

    if (!Common.isZero(model)) {
      parameterBuilder.model(model);
    }
    if (!Common.isZero(contents)) {
      parameterBuilder.contents(contents);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    if (this.apiClient.vertexAI()) {
      body = countTokensParametersToVertex(this.apiClient, parameterNode, null);
      path = Common.formatMap("{model}:countTokens", body.get("_url"));
    } else {
      body = countTokensParametersToMldev(this.apiClient, parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("{model}:countTokens", body.get("_url"));
      } else {
        path = "{model}:countTokens";
      }
    }
    body.remove("_url");

    JsonNode queryParams = body.get("_query");
    if (queryParams != null) {
      body.remove("_query");
      path = String.format("%s?%s", path, Common.urlEncode((ObjectNode) queryParams));
    }

    // TODO: Remove the hack that removes config.
    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    return new BuiltRequest(path, JsonSerializable.toJsonString(body), requestHttpOptions);
  }

  /** A shared processResponse function for both sync and async methods. */
  CountTokensResponse processResponseForCountTokens(
      ApiResponse response, CountTokensConfig config) {
    ResponseBody responseBody = response.getBody();
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }

    JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);

    if (this.apiClient.vertexAI()) {
      responseNode = countTokensResponseFromVertex(responseNode, null);
    }

    if (!this.apiClient.vertexAI()) {
      responseNode = countTokensResponseFromMldev(responseNode, null);
    }

    CountTokensResponse sdkResponse =
        JsonSerializable.fromJsonNode(responseNode, CountTokensResponse.class);
    Headers responseHeaders = response.getHeaders();
    if (responseHeaders == null) {
      return sdkResponse;
    }
    Map<String, String> headers = new HashMap<>();
    for (String headerName : responseHeaders.names()) {
      headers.put(headerName, responseHeaders.get(headerName));
    }
    return sdkResponse.toBuilder().sdkHttpResponse(HttpResponse.builder().headers(headers)).build();
  }

  /**
   * Counts tokens given a GenAI model and a list of content.
   *
   * @param model the name of the GenAI model to use.
   * @param contents a {@link List<com.google.genai.types.Content>} to send to count tokens for.
   * @param config a {@link com.google.genai.types.CountTokensConfig} instance that specifies the
   *     optional configurations
   * @return a {@link com.google.genai.types.CountTokensResponse} instance that contains tokens
   *     count.
   */
  public CountTokensResponse countTokens(
      String model, List<Content> contents, CountTokensConfig config) {
    BuiltRequest builtRequest = buildRequestForCountTokens(model, contents, config);

    try (ApiResponse response =
        this.apiClient.request(
            "post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)) {
      return processResponseForCountTokens(response, config);
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForComputeTokens(
      String model, List<Content> contents, ComputeTokensConfig config) {

    ComputeTokensParameters.Builder parameterBuilder = ComputeTokensParameters.builder();

    if (!Common.isZero(model)) {
      parameterBuilder.model(model);
    }
    if (!Common.isZero(contents)) {
      parameterBuilder.contents(contents);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    if (this.apiClient.vertexAI()) {
      body = computeTokensParametersToVertex(this.apiClient, parameterNode, null);
      path = Common.formatMap("{model}:computeTokens", body.get("_url"));
    } else {
      throw new UnsupportedOperationException(
          "This method is only supported in the Vertex AI client.");
    }
    body.remove("_url");

    JsonNode queryParams = body.get("_query");
    if (queryParams != null) {
      body.remove("_query");
      path = String.format("%s?%s", path, Common.urlEncode((ObjectNode) queryParams));
    }

    // TODO: Remove the hack that removes config.
    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    return new BuiltRequest(path, JsonSerializable.toJsonString(body), requestHttpOptions);
  }

  /** A shared processResponse function for both sync and async methods. */
  ComputeTokensResponse processResponseForComputeTokens(
      ApiResponse response, ComputeTokensConfig config) {
    ResponseBody responseBody = response.getBody();
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }

    JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);

    if (this.apiClient.vertexAI()) {
      responseNode = computeTokensResponseFromVertex(responseNode, null);
    }

    if (!this.apiClient.vertexAI()) {
      throw new UnsupportedOperationException(
          "This method is only supported in the Vertex AI client.");
    }

    ComputeTokensResponse sdkResponse =
        JsonSerializable.fromJsonNode(responseNode, ComputeTokensResponse.class);
    Headers responseHeaders = response.getHeaders();
    if (responseHeaders == null) {
      return sdkResponse;
    }
    Map<String, String> headers = new HashMap<>();
    for (String headerName : responseHeaders.names()) {
      headers.put(headerName, responseHeaders.get(headerName));
    }
    return sdkResponse.toBuilder().sdkHttpResponse(HttpResponse.builder().headers(headers)).build();
  }

  /**
   * Computes tokens given a GenAI model and a list of content.
   *
   * @param model the name of the GenAI model to use.
   * @param contents a {@link List<com.google.genai.types.Content>} to send to compute tokens for.
   * @param config a {@link com.google.genai.types.ComputeTokensConfig} instance that specifies the
   *     optional configurations
   * @return a {@link com.google.genai.types.ComputeTokensResponse} instance that contains tokens
   *     results.
   */
  public ComputeTokensResponse computeTokens(
      String model, List<Content> contents, ComputeTokensConfig config) {
    BuiltRequest builtRequest = buildRequestForComputeTokens(model, contents, config);

    try (ApiResponse response =
        this.apiClient.request(
            "post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)) {
      return processResponseForComputeTokens(response, config);
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForPrivateGenerateVideos(
      String model,
      String prompt,
      Image image,
      Video video,
      GenerateVideosSource source,
      GenerateVideosConfig config) {

    GenerateVideosParameters.Builder parameterBuilder = GenerateVideosParameters.builder();

    if (!Common.isZero(model)) {
      parameterBuilder.model(model);
    }
    if (!Common.isZero(prompt)) {
      parameterBuilder.prompt(prompt);
    }
    if (!Common.isZero(image)) {
      parameterBuilder.image(image);
    }
    if (!Common.isZero(video)) {
      parameterBuilder.video(video);
    }
    if (!Common.isZero(source)) {
      parameterBuilder.source(source);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    if (this.apiClient.vertexAI()) {
      body = generateVideosParametersToVertex(this.apiClient, parameterNode, null);
      path = Common.formatMap("{model}:predictLongRunning", body.get("_url"));
    } else {
      body = generateVideosParametersToMldev(this.apiClient, parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("{model}:predictLongRunning", body.get("_url"));
      } else {
        path = "{model}:predictLongRunning";
      }
    }
    body.remove("_url");

    JsonNode queryParams = body.get("_query");
    if (queryParams != null) {
      body.remove("_query");
      path = String.format("%s?%s", path, Common.urlEncode((ObjectNode) queryParams));
    }

    // TODO: Remove the hack that removes config.
    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    return new BuiltRequest(path, JsonSerializable.toJsonString(body), requestHttpOptions);
  }

  /** A shared processResponse function for both sync and async methods. */
  GenerateVideosOperation processResponseForPrivateGenerateVideos(
      ApiResponse response, GenerateVideosConfig config) {
    ResponseBody responseBody = response.getBody();
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }

    JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);

    if (this.apiClient.vertexAI()) {
      responseNode = generateVideosOperationFromVertex(responseNode, null);
    }

    if (!this.apiClient.vertexAI()) {
      responseNode = generateVideosOperationFromMldev(responseNode, null);
    }

    return JsonSerializable.fromJsonNode(responseNode, GenerateVideosOperation.class);
  }

  /** Private method for generating videos. */
  GenerateVideosOperation privateGenerateVideos(
      String model,
      String prompt,
      Image image,
      Video video,
      GenerateVideosSource source,
      GenerateVideosConfig config) {
    BuiltRequest builtRequest =
        buildRequestForPrivateGenerateVideos(model, prompt, image, video, source, config);

    try (ApiResponse response =
        this.apiClient.request(
            "post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)) {
      return processResponseForPrivateGenerateVideos(response, config);
    }
  }

  private static final Logger logger = Logger.getLogger(Models.class.getName());

  /**
   * Generates content given a GenAI model and a list of content.
   *
   * @param model the name of the GenAI model to use for generation
   * @param contents a {@link List<com.google.genai.types.Content>} to send to the generative model
   * @param config a {@link com.google.genai.types.GenerateContentConfig} instance that specifies
   *     the optional configurations
   * @return a {@link com.google.genai.types.GenerateContentResponse} instance that contains
   *     response contents and other metadata
   */
  public GenerateContentResponse generateContent(
      String model, List<Content> contents, GenerateContentConfig config) {
    ImmutableList<Integer> incompatibleToolsIndexes =
        AfcUtil.findAfcIncompatibleToolIndexes(config);
    GenerateContentConfig transformedConfig = AfcUtil.transformGenerateContentConfig(config);
    if (AfcUtil.shouldDisableAfc(transformedConfig)) {
      return privateGenerateContent(model, contents, transformedConfig);
    }
    if (!incompatibleToolsIndexes.isEmpty()) {
      int originalToolsSize = 0;
      if (config.tools().isPresent() && !config.tools().get().isEmpty()) {
        originalToolsSize = config.tools().get().size();
      }
      if (originalToolsSize != incompatibleToolsIndexes.size()) {
        logger.warning(
            String.format(
                "Automatic function calling (AFC) is enabled, but the following tools are not"
                    + " supported: %s. AFC will be disabled.",
                incompatibleToolsIndexes));
      }
      return privateGenerateContent(model, contents, transformedConfig);
    }
    ImmutableMap<String, Method> functionMap = AfcUtil.getFunctionMap(config);
    if (functionMap.isEmpty()) {
      return privateGenerateContent(model, contents, transformedConfig);
    }
    int remainingRemoteCalls = AfcUtil.getMaxRemoteCallsAfc(transformedConfig);
    int i = 0;
    logger.info(
        String.format(
            "Automatic function calling is enabled with max remote calls: %d",
            remainingRemoteCalls));
    GenerateContentResponse response = null;
    List<Content> automaticFunctionCallingHistory = new ArrayList<>(contents);
    while (remainingRemoteCalls > 0) {
      i++;
      response = privateGenerateContent(model, contents, transformedConfig);
      logger.info(String.format("Automatic function calling remote call %d is done", i));
      remainingRemoteCalls--;
      if (remainingRemoteCalls == 0) {
        logger.info("Reached max remote calls for automatic function calling.");
      }
      if (!response.candidates().isPresent()
          || response.candidates().get().isEmpty()
          || !response.candidates().get().get(0).content().isPresent()
          || !response.candidates().get().get(0).content().get().parts().isPresent()
          || response.candidates().get().get(0).content().get().parts().get().isEmpty()) {
        break;
      }
      ImmutableList<Part> functionResponseParts =
          AfcUtil.getFunctionResponseParts(response, functionMap);
      if (functionResponseParts.isEmpty()) {
        break;
      }
      Content functionCallContent = response.candidates().get().get(0).content().get();
      Content functionResponseContent =
          Content.builder().role("user").parts(functionResponseParts).build();
      automaticFunctionCallingHistory.add(functionCallContent);
      automaticFunctionCallingHistory.add(functionResponseContent);
      contents = automaticFunctionCallingHistory;
    }
    if (AfcUtil.shouldAppendAfcHistory(transformedConfig)) {
      ObjectNode responseNode = JsonSerializable.objectMapper.valueToTree(response);
      responseNode.set(
          "automaticFunctionCallingHistory",
          JsonSerializable.objectMapper.valueToTree(automaticFunctionCallingHistory));
      response = JsonSerializable.fromJsonNode(responseNode, GenerateContentResponse.class);
    }
    return response;
  }

  /**
   * Generates content given a GenAI model and a content object.
   *
   * @param model the name of the GenAI model to use for generation
   * @param content a {@link com.google.genai.types.Content} to send to the generative model
   * @param config a {@link com.google.genai.types.GenerateContentConfig} instance that specifies
   *     the optional configurations
   * @return a {@link com.google.genai.types.GenerateContentResponse} instance that contains
   *     response contents and other metadata
   */
  public GenerateContentResponse generateContent(
      String model, Content content, GenerateContentConfig config) {
    return generateContent(model, Transformers.tContents(content), config);
  }

  /**
   * Generates content given a GenAI model and a text string.
   *
   * @param model the name of the GenAI model to use for generation
   * @param text the text string to send to the generative model
   * @param config a {@link com.google.genai.types.GenerateContentConfig} instance that specifies
   *     the optional configurations
   * @return a {@link com.google.genai.types.GenerateContentResponse} instance that contains
   *     response contents and other metadata
   */
  public GenerateContentResponse generateContent(
      String model, String text, GenerateContentConfig config) {
    return generateContent(model, Transformers.tContents(text), config);
  }

  /**
   * Generates content with streaming support given a GenAI model and a list of content.
   *
   * @param model the name of the GenAI model to use for generation
   * @param contents a {@link List<com.google.genai.types.Content>} to send to the generative model
   * @param config a {@link com.google.genai.types.GenerateContentConfig} instance that specifies
   *     the optional configurations
   * @return a {@link com.google.genai.types.GenerateContentResponse} instance that contains
   *     response contents and other metadata
   */
  public ResponseStream<GenerateContentResponse> generateContentStream(
      String model, List<Content> contents, GenerateContentConfig config) {
    GenerateContentConfig transformedConfig = AfcUtil.transformGenerateContentConfig(config);
    if (AfcUtil.hasCallableTool(config) && !AfcUtil.shouldDisableAfc(transformedConfig)) {
      logger.warning(
          "In generateContentStream method, detected that automatic function calling is enabled in"
              + " the config.AutomaticFunctionCalling(), and callable tool is present in the"
              + " config.tools() list. Automatic function calling is not supported in streaming"
              + " methods at the moment, will just return the function call parts from model if"
              + " there is any.");
    }
    return privateGenerateContentStream(model, contents, transformedConfig);
  }

  /**
   * Generates content with streaming support given a GenAI model and a content object.
   *
   * @param model the name of the GenAI model to use for generation
   * @param content a {@link com.google.genai.types.Content} to send to the generative model
   * @param config a {@link com.google.genai.types.GenerateContentConfig} instance that specifies
   *     the optional configurations
   * @return a {@link com.google.genai.types.GenerateContentResponse} instance that contains
   *     response contents and other metadata
   */
  public ResponseStream<GenerateContentResponse> generateContentStream(
      String model, Content content, GenerateContentConfig config) {
    return generateContentStream(model, Transformers.tContents(content), config);
  }

  /**
   * Generates content with streaming support given a GenAI model and a text string.
   *
   * @param model the name of the GenAI model to use for generation
   * @param text the text string to send to the generative model
   * @param config a {@link com.google.genai.types.GenerateContentConfig} instance that specifies
   *     the optional configurations
   * @return a {@link com.google.genai.types.GenerateContentResponse} instance that contains
   *     response contents and other metadata
   */
  public ResponseStream<GenerateContentResponse> generateContentStream(
      String model, String text, GenerateContentConfig config) {
    return generateContentStream(model, Transformers.tContents(text), config);
  }

  /**
   * Counts tokens given a GenAI model and a text string.
   *
   * @param model the name of the GenAI model to use.
   * @param text the text string to send to count tokens for.
   * @param config a {@link com.google.genai.types.CountTokensConfig} instance that specifies the
   *     optional configurations
   * @return a {@link com.google.genai.types.CountTokensResponse} instance that contains tokens
   *     count.
   */
  public CountTokensResponse countTokens(String model, String text, CountTokensConfig config) {
    return countTokens(model, Transformers.tContents(text), config);
  }

  /**
   * Computes tokens given a GenAI model and a text string.
   *
   * @param model the name of the GenAI model to use.
   * @param text the text string to send to compute tokens for.
   * @param config a {@link com.google.genai.types.ComputeTokensConfig} instance that specifies the
   *     optional configurations
   * @return a {@link com.google.genai.types.ComputeTokensResponse} instance that contains tokens
   *     results.
   */
  public ComputeTokensResponse computeTokens(
      String model, String text, ComputeTokensConfig config) {
    return computeTokens(model, Transformers.tContents(text), config);
  }

  /** Post processes the GenerateImagesResponse from the API. */
  GenerateImagesResponse postProcessGenerateImagesResponse(GenerateImagesResponse apiResponse) {
    SafetyAttributes positivePromptSafetyAttributes = null;
    List<GeneratedImage> generatedImages = new ArrayList<>();

    if (apiResponse.generatedImages().isPresent()) {
      for (GeneratedImage generatedImage : apiResponse.generatedImages().get()) {
        if (generatedImage.safetyAttributes().isPresent()
            && generatedImage.safetyAttributes().get().contentType().isPresent()
            && generatedImage
                .safetyAttributes()
                .get()
                .contentType()
                .get()
                .equals("Positive Prompt")) {
          positivePromptSafetyAttributes = generatedImage.safetyAttributes().get();
        } else {
          generatedImages.add(generatedImage);
        }
      }
    }

    GenerateImagesResponse.Builder builder =
        GenerateImagesResponse.builder().generatedImages(generatedImages);

    if (positivePromptSafetyAttributes != null) {
      builder = builder.positivePromptSafetyAttributes(positivePromptSafetyAttributes);
    }

    return builder.build();
  }

  /**
   * Generates images given a GenAI model and a prompt.
   *
   * @param model the name of the GenAI model to use for generating images
   * @param prompt the prompt to generate images
   * @param config a {@link com.google.genai.types.GenerateImagesConfig} instance that specifies the
   *     optional configurations
   * @return a {@link com.google.genai.types.GenerateImagesResponse} instance that contains the
   *     generated images.
   */
  public GenerateImagesResponse generateImages(
      String model, String prompt, GenerateImagesConfig config) {
    return postProcessGenerateImagesResponse(privateGenerateImages(model, prompt, config));
  }

  /**
   * Edits an image given a GenAI model, a prompt, and a list of reference images.
   *
   * @param model the name of the GenAI model to use for editing capabilities
   * @param prompt the prompt to edit the image
   * @param referenceImages a {@link List<com.google.genai.types.ReferenceImage>} to send to use for
   *     editing. The 6 types of reference images are: {@link
   *     com.google.genai.types.RawReferenceImage}, {@link
   *     com.google.genai.types.MaskReferenceImage}, {@link
   *     com.google.genai.types.ControlReferenceImage}, {@link
   *     com.google.genai.types.StyleReferenceImage}, {@link
   *     com.google.genai.types.SubjectReferenceImage}, {@link
   *     com.google.genai.types.ContentReferenceImage}
   * @param config a {@link com.google.genai.types.EditImageConfig} instance that specifies the
   *     optional configurations
   * @return a {@link com.google.genai.types.EditImageResponse} instance that contains the edited
   *     image.
   */
  public EditImageResponse editImage(
      String model, String prompt, List<ReferenceImage> referenceImages, EditImageConfig config) {

    List<ReferenceImageAPI> referenceImagesAPI = new ArrayList<>();
    for (ReferenceImage referenceImage : referenceImages) {
      referenceImagesAPI.add(referenceImage.toReferenceImageAPI());
    }

    return privateEditImage(model, prompt, referenceImagesAPI, config);
  }

  /** Preprocesses the UpscaleImageConfig for the API. */
  UpscaleImageAPIConfig preProcessUpscaleImageConfig(UpscaleImageConfig config) {
    UpscaleImageAPIConfig.Builder builder = UpscaleImageAPIConfig.builder();
    if (config != null) {
      if (config.outputGcsUri().isPresent()) {
        builder = builder.outputGcsUri(config.outputGcsUri().get());
      }
      if (config.outputMimeType().isPresent()) {
        builder = builder.outputMimeType(config.outputMimeType().get());
      }
      if (config.outputCompressionQuality().isPresent()) {
        builder = builder.outputCompressionQuality(config.outputCompressionQuality().get());
      }
      if (config.safetyFilterLevel().isPresent()) {
        builder = builder.safetyFilterLevel(config.safetyFilterLevel().get());
      }
      if (config.personGeneration().isPresent()) {
        builder = builder.personGeneration(config.personGeneration().get());
      }
      if (config.includeRaiReason().isPresent()) {
        builder = builder.includeRaiReason(config.includeRaiReason().get());
      }
      if (config.enhanceInputImage().isPresent()) {
        builder = builder.enhanceInputImage(config.enhanceInputImage().get());
      }
      if (config.imagePreservationFactor().isPresent()) {
        builder = builder.imagePreservationFactor(config.imagePreservationFactor().get());
      }
      if (config.labels().isPresent()) {
        builder = builder.labels(config.labels().get());
      }
    }

    builder = builder.mode("upscale");
    builder = builder.numberOfImages(1);

    return builder.build();
  }

  /**
   * Upscales an image given a GenAI model and an image and an upscale factor.
   *
   * @param model the name of the GenAI model to use for upscaling
   * @param image a {@link com.google.genai.types.Image} to send to the generative model
   * @param upscaleFactor the factor to upscale the image
   * @param config a {@link com.google.genai.types.UpscaleImageConfig} instance that specifies the
   *     optional configurations
   * @return a {@link com.google.genai.types.UpscaleImageResponse} instance that contains the
   *     upscaled image.
   */
  public UpscaleImageResponse upscaleImage(
      String model, Image image, String upscaleFactor, UpscaleImageConfig config) {
    return privateUpscaleImage(model, image, upscaleFactor, preProcessUpscaleImageConfig(config));
  }

  /** Preprocesses the GenerateVideosSource for the API. */
  GenerateVideosSource preProcessGenerateVideosSource(GenerateVideosSource source) {
    if (!this.apiClient.vertexAI()) {
      if (source != null
          && source.video().isPresent()
          && source.video().get().uri().isPresent()
          && source.video().get().videoBytes().isPresent()) {

        Video.Builder videoBuilder = Video.builder().uri(source.video().get().uri().get());
        if (source.video().get().mimeType().isPresent()) {
          videoBuilder = videoBuilder.mimeType(source.video().get().mimeType().get());
        }

        GenerateVideosSource.Builder sourceBuilder =
            GenerateVideosSource.builder().video(videoBuilder.build());
        if (source.prompt().isPresent()) {
          sourceBuilder = sourceBuilder.prompt(source.prompt().get());
        }
        if (source.image().isPresent()) {
          sourceBuilder = sourceBuilder.image(source.image().get());
        }
        source = sourceBuilder.build();
      }
    }
    return source;
  }

  /**
   * Generates videos given a GenAI model, and a GenerateVideosSource source.
   *
   * <p>This method is experimental.
   *
   * @param model the name of the GenAI model to use for generating videos
   * @param source a {@link com.google.genai.types.GenerateVideosSource} that specifies the inputs
   *     (prompt, image, and/or video) to generate videos.
   * @param config a {@link com.google.genai.types.GenerateVideosConfig} instance that specifies the
   *     optional configurations
   * @return a {@link com.google.genai.types.GenerateVideosOperation} instance that contains the
   *     generated videos.
   */
  public GenerateVideosOperation generateVideos(
      String model, GenerateVideosSource source, GenerateVideosConfig config) {
    return privateGenerateVideos(
        model, null, null, null, preProcessGenerateVideosSource(source), config);
  }

  /** Preprocesses the Video for the API. */
  Video preProcessVideo(Video video) {
    if (!this.apiClient.vertexAI()) {
      if (video != null && video.uri().isPresent() && video.videoBytes().isPresent()) {

        Video.Builder videoBuilder = Video.builder().uri(video.uri().get());
        if (video.mimeType().isPresent()) {
          videoBuilder = videoBuilder.mimeType(video.mimeType().get());
        }
        video = videoBuilder.build();
      }
    }
    return video;
  }

  /**
   * Generates videos given a GenAI model, and an input (text, image, or video).
   *
   * <p>This method is experimental.
   *
   * @param model the name of the GenAI model to use for generating videos
   * @param prompt the text prompt for generating the videos. Optional for image to video and video
   *     extension use cases.
   * @param image the input image for generating the videos. Optional if prompt is provided.
   * @param video the input video for video extension use cases. Optional if prompt or image is
   *     provided.
   * @param config a {@link com.google.genai.types.GenerateVideosConfig} instance that specifies the
   *     optional configurations
   * @return a {@link com.google.genai.types.GenerateVideosOperation} instance that contains the
   *     generated videos.
   */
  public GenerateVideosOperation generateVideos(
      String model, String prompt, Image image, Video video, GenerateVideosConfig config) {
    return privateGenerateVideos(model, prompt, image, preProcessVideo(video), null, config);
  }

  /**
   * Generates videos given a GenAI model, and an input (text, image).
   *
   * <p>This method is experimental, and kept for backward compatibility.
   *
   * @param model the name of the GenAI model to use for generating videos
   * @param prompt the text prompt for generating the videos. Optional for image to video use cases.
   * @param image the input image for generating the videos. Optional if prompt is provided.
   * @param config a {@link com.google.genai.types.GenerateVideosConfig} instance that specifies the
   *     optional configurations
   * @return a {@link com.google.genai.types.GenerateVideosOperation} instance that contains the
   *     generated videos.
   */
  public GenerateVideosOperation generateVideos(
      String model, String prompt, Image image, GenerateVideosConfig config) {
    return generateVideos(model, prompt, image, null, config);
  }

  /**
   * Embeds content given a GenAI model and a text string.
   *
   * @param model the name of the GenAI model to use for embedding
   * @param text the text string to send to the embedding model
   * @return a {@link com.google.genai.types.EmbedContentResponse} instance that contains the
   *     embedding.
   */
  public EmbedContentResponse embedContent(String model, String text, EmbedContentConfig config) {
    return embedContent(model, ImmutableList.of(text), config);
  }

  /**
   * Embeds content given a GenAI model and a list of text strings.
   *
   * @param model the name of the GenAI model to use for embedding
   * @param texts the list of text strings to send to the embedding model
   * @return a {@link com.google.genai.types.EmbedContentResponse} instance that contains the
   *     embedding.
   */
  public EmbedContentResponse embedContent(
      String model, List<String> texts, EmbedContentConfig config) {
    List<Content> contents = new ArrayList<>();
    for (String text : texts) {
      contents.add(Content.fromParts(Part.fromText(text)));
    }
    return privateEmbedContent(model, contents, config);
  }

  /**
   * Makes an API request to list the available models.
   *
   * <p>If `queryBase` is set to True in the {@link ListModelsConfig} or not set (default), the API
   * will return all available base models. If set to False, it will return all tuned models.
   *
   * @param config A {@link ListModelsConfig} for configuring the list request.
   * @return A {@link Pager} object that contains the list of models. The pager is an iterable and
   *     automatically queries the next page once the current page is exhausted.
   */
  @SuppressWarnings("PatternMatchingInstanceof")
  public Pager<Model> list(ListModelsConfig config) {
    if (config == null) {
      config = ListModelsConfig.builder().build();
    }
    if (config.filter().isPresent()) {
      throw new IllegalArgumentException("Filter is currently not supported for list models.");
    }
    ListModelsConfig.Builder configBuilder = config.toBuilder();
    if (!config.queryBase().isPresent()) {
      configBuilder.queryBase(true);
    } else if (!config.queryBase().get() && this.apiClient.vertexAI()) {
      configBuilder.filter("labels.tune-type:*");
    }
    config = configBuilder.build();

    Function<JsonSerializable, Object> request =
        requestConfig -> {
          if (!(requestConfig instanceof ListModelsConfig)) {
            throw new GenAiIOException(
                "Internal error: Pager expected ListModelsConfig but received "
                    + requestConfig.getClass().getName());
          }
          return this.privateList((ListModelsConfig) requestConfig);
        };
    return new Pager<>(
        Pager.PagedItem.MODELS,
        request,
        (ObjectNode) JsonSerializable.toJsonNode(config),
        JsonSerializable.toJsonNode(privateList(config)));
  }
}
