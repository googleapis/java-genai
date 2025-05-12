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
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.ComputeTokensConfig;
import com.google.genai.types.ComputeTokensParameters;
import com.google.genai.types.ComputeTokensResponse;
import com.google.genai.types.Content;
import com.google.genai.types.CountTokensConfig;
import com.google.genai.types.CountTokensParameters;
import com.google.genai.types.CountTokensResponse;
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
import com.google.genai.types.GeneratedImage;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.Image;
import com.google.genai.types.Part;
import com.google.genai.types.ReferenceImage;
import com.google.genai.types.ReferenceImageAPI;
import com.google.genai.types.SafetyAttributes;
import com.google.genai.types.UpscaleImageAPIConfig;
import com.google.genai.types.UpscaleImageAPIParameters;
import com.google.genai.types.UpscaleImageConfig;
import com.google.genai.types.UpscaleImageResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

/**
 * Provides methods for interacting with the available GenAI models. Instantiating this class is not
 * required. After instantiating a {@link Client}, access methods through
 * `client.models.methodName(...)` directly.
 */
public final class Models {
  private final ApiClient apiClient;

  public Models(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode partToMldev(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"videoMetadata"}))) {
      throw new IllegalArgumentException("videoMetadata parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"thought"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"thought"},
          Common.getValueByPath(fromObject, new String[] {"thought"}));
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
          Common.getValueByPath(fromObject, new String[] {"fileData"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"functionCall"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"functionCall"},
          Common.getValueByPath(fromObject, new String[] {"functionCall"}));
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
          Common.getValueByPath(fromObject, new String[] {"inlineData"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"text"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"text"},
          Common.getValueByPath(fromObject, new String[] {"text"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode contentToMldev(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"parts"}) != null) {
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"parts"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(partToMldev(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
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
  ObjectNode modelSelectionConfigToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (!Common.isZero(
        Common.getValueByPath(fromObject, new String[] {"featureSelectionPreference"}))) {
      throw new IllegalArgumentException(
          "featureSelectionPreference parameter is not supported in Gemini API.");
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode safetySettingToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"method"}))) {
      throw new IllegalArgumentException("method parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"category"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"category"},
          Common.getValueByPath(fromObject, new String[] {"category"}));
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
  ObjectNode googleSearchToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode dynamicRetrievalConfigToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"mode"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"mode"},
          Common.getValueByPath(fromObject, new String[] {"mode"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"dynamicThreshold"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"dynamicThreshold"},
          Common.getValueByPath(fromObject, new String[] {"dynamicThreshold"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode googleSearchRetrievalToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"dynamicRetrievalConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"dynamicRetrievalConfig"},
          dynamicRetrievalConfigToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"dynamicRetrievalConfig"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode enterpriseWebSearchToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode apiKeyConfigToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"apiKeyString"}))) {
      throw new IllegalArgumentException("apiKeyString parameter is not supported in Gemini API.");
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode authConfigToMldev(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"apiKeyConfig"}))) {
      throw new IllegalArgumentException("apiKeyConfig parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"authType"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"authType"},
          Common.getValueByPath(fromObject, new String[] {"authType"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"googleServiceAccountConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"googleServiceAccountConfig"},
          Common.getValueByPath(fromObject, new String[] {"googleServiceAccountConfig"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"httpBasicAuthConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"httpBasicAuthConfig"},
          Common.getValueByPath(fromObject, new String[] {"httpBasicAuthConfig"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"oauthConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"oauthConfig"},
          Common.getValueByPath(fromObject, new String[] {"oauthConfig"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"oidcConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"oidcConfig"},
          Common.getValueByPath(fromObject, new String[] {"oidcConfig"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode googleMapsToMldev(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"authConfig"}))) {
      throw new IllegalArgumentException("authConfig parameter is not supported in Gemini API.");
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode toolToMldev(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"retrieval"}))) {
      throw new IllegalArgumentException("retrieval parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"googleSearch"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"googleSearch"},
          googleSearchToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"googleSearch"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"googleSearchRetrieval"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"googleSearchRetrieval"},
          googleSearchRetrievalToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"googleSearchRetrieval"})),
              toObject));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"enterpriseWebSearch"}))) {
      throw new IllegalArgumentException(
          "enterpriseWebSearch parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"googleMaps"}))) {
      throw new IllegalArgumentException("googleMaps parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"codeExecution"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"codeExecution"},
          Common.getValueByPath(fromObject, new String[] {"codeExecution"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"functionDeclarations"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"functionDeclarations"},
          Common.getValueByPath(fromObject, new String[] {"functionDeclarations"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode functionCallingConfigToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"mode"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"mode"},
          Common.getValueByPath(fromObject, new String[] {"mode"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"allowedFunctionNames"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"allowedFunctionNames"},
          Common.getValueByPath(fromObject, new String[] {"allowedFunctionNames"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode latLngToMldev(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"latitude"}))) {
      throw new IllegalArgumentException("latitude parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"longitude"}))) {
      throw new IllegalArgumentException("longitude parameter is not supported in Gemini API.");
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode retrievalConfigToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"latLng"}))) {
      throw new IllegalArgumentException("latLng parameter is not supported in Gemini API.");
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode toolConfigToMldev(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"functionCallingConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"functionCallingConfig"},
          functionCallingConfigToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"functionCallingConfig"})),
              toObject));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"retrievalConfig"}))) {
      throw new IllegalArgumentException(
          "retrievalConfig parameter is not supported in Gemini API.");
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode prebuiltVoiceConfigToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"voiceName"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"voiceName"},
          Common.getValueByPath(fromObject, new String[] {"voiceName"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode voiceConfigToMldev(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"prebuiltVoiceConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"prebuiltVoiceConfig"},
          prebuiltVoiceConfigToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"prebuiltVoiceConfig"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode speechConfigToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"voiceConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"voiceConfig"},
          voiceConfigToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"voiceConfig"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"languageCode"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"languageCode"},
          Common.getValueByPath(fromObject, new String[] {"languageCode"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode thinkingConfigToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"includeThoughts"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"includeThoughts"},
          Common.getValueByPath(fromObject, new String[] {"includeThoughts"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"thinkingBudget"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"thinkingBudget"},
          Common.getValueByPath(fromObject, new String[] {"thinkingBudget"}));
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
              apiClient,
              JsonSerializable.toJsonNode(
                  Transformers.tContent(
                      this.apiClient,
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
          Transformers.tSchema(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"responseSchema"})));
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

      keyArray.forEach(
          item -> {
            result.add(
                safetySettingToMldev(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
      Common.setValueByPath(parentObject, new String[] {"safetySettings"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"tools"}) != null) {
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"tools"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(
                toolToMldev(
                    apiClient,
                    JsonSerializable.toJsonNode(Transformers.tTool(this.apiClient, item)),
                    toObject));
          });
      Common.setValueByPath(parentObject, new String[] {"tools"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"toolConfig"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"toolConfig"},
          toolConfigToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"toolConfig"})),
              toObject));
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
          speechConfigToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Transformers.tSpeechConfig(
                      this.apiClient,
                      Common.getValueByPath(fromObject, new String[] {"speechConfig"}))),
              toObject));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"audioTimestamp"}))) {
      throw new IllegalArgumentException(
          "audioTimestamp parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"thinkingConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"thinkingConfig"},
          thinkingConfigToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"thinkingConfig"})),
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
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"contents"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(contentToMldev(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
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
  ObjectNode embedContentConfigToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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
      Common.setValueByPath(
          toObject,
          new String[] {"config"},
          embedContentConfigToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject));
    }

    Common.setValueByPath(
        toObject,
        new String[] {"requests[]", "model"},
        Transformers.tModel(
            this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateImagesConfigToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"addWatermark"}))) {
      throw new IllegalArgumentException("addWatermark parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"enhancePrompt"}))) {
      throw new IllegalArgumentException("enhancePrompt parameter is not supported in Gemini API.");
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
      Common.setValueByPath(
          toObject,
          new String[] {"config"},
          generateImagesConfigToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode countTokensConfigToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"contents"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(contentToMldev(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
      Common.setValueByPath(toObject, new String[] {"contents"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"config"},
          countTokensConfigToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode imageToMldev(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"gcsUri"}))) {
      throw new IllegalArgumentException("gcsUri parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"imageBytes"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"bytesBase64Encoded"},
          Transformers.tBytes(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"imageBytes"})));
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
  ObjectNode generateVideosConfigToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"resolution"}))) {
      throw new IllegalArgumentException("resolution parameter is not supported in Gemini API.");
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

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"enhancePrompt"}))) {
      throw new IllegalArgumentException("enhancePrompt parameter is not supported in Gemini API.");
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
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"image"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"config"},
          generateVideosConfigToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode partToVertex(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"videoMetadata"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"videoMetadata"},
          Common.getValueByPath(fromObject, new String[] {"videoMetadata"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"thought"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"thought"},
          Common.getValueByPath(fromObject, new String[] {"thought"}));
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
          Common.getValueByPath(fromObject, new String[] {"fileData"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"functionCall"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"functionCall"},
          Common.getValueByPath(fromObject, new String[] {"functionCall"}));
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
          Common.getValueByPath(fromObject, new String[] {"inlineData"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"text"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"text"},
          Common.getValueByPath(fromObject, new String[] {"text"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode contentToVertex(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"parts"}) != null) {
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"parts"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(partToVertex(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
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
  ObjectNode modelSelectionConfigToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"featureSelectionPreference"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"featureSelectionPreference"},
          Common.getValueByPath(fromObject, new String[] {"featureSelectionPreference"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode safetySettingToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"method"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"method"},
          Common.getValueByPath(fromObject, new String[] {"method"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"category"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"category"},
          Common.getValueByPath(fromObject, new String[] {"category"}));
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
  ObjectNode googleSearchToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode dynamicRetrievalConfigToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"mode"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"mode"},
          Common.getValueByPath(fromObject, new String[] {"mode"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"dynamicThreshold"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"dynamicThreshold"},
          Common.getValueByPath(fromObject, new String[] {"dynamicThreshold"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode googleSearchRetrievalToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"dynamicRetrievalConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"dynamicRetrievalConfig"},
          dynamicRetrievalConfigToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"dynamicRetrievalConfig"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode enterpriseWebSearchToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode apiKeyConfigToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"apiKeyString"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"apiKeyString"},
          Common.getValueByPath(fromObject, new String[] {"apiKeyString"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode authConfigToVertex(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"apiKeyConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"apiKeyConfig"},
          apiKeyConfigToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"apiKeyConfig"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"authType"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"authType"},
          Common.getValueByPath(fromObject, new String[] {"authType"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"googleServiceAccountConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"googleServiceAccountConfig"},
          Common.getValueByPath(fromObject, new String[] {"googleServiceAccountConfig"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"httpBasicAuthConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"httpBasicAuthConfig"},
          Common.getValueByPath(fromObject, new String[] {"httpBasicAuthConfig"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"oauthConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"oauthConfig"},
          Common.getValueByPath(fromObject, new String[] {"oauthConfig"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"oidcConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"oidcConfig"},
          Common.getValueByPath(fromObject, new String[] {"oidcConfig"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode googleMapsToVertex(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"authConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"authConfig"},
          authConfigToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"authConfig"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode toolToVertex(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"retrieval"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"retrieval"},
          Common.getValueByPath(fromObject, new String[] {"retrieval"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"googleSearch"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"googleSearch"},
          googleSearchToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"googleSearch"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"googleSearchRetrieval"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"googleSearchRetrieval"},
          googleSearchRetrievalToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"googleSearchRetrieval"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"enterpriseWebSearch"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"enterpriseWebSearch"},
          enterpriseWebSearchToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"enterpriseWebSearch"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"googleMaps"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"googleMaps"},
          googleMapsToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"googleMaps"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"codeExecution"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"codeExecution"},
          Common.getValueByPath(fromObject, new String[] {"codeExecution"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"functionDeclarations"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"functionDeclarations"},
          Common.getValueByPath(fromObject, new String[] {"functionDeclarations"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode functionCallingConfigToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"mode"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"mode"},
          Common.getValueByPath(fromObject, new String[] {"mode"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"allowedFunctionNames"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"allowedFunctionNames"},
          Common.getValueByPath(fromObject, new String[] {"allowedFunctionNames"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode latLngToVertex(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"latitude"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"latitude"},
          Common.getValueByPath(fromObject, new String[] {"latitude"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"longitude"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"longitude"},
          Common.getValueByPath(fromObject, new String[] {"longitude"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode retrievalConfigToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"latLng"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"latLng"},
          latLngToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"latLng"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode toolConfigToVertex(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"functionCallingConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"functionCallingConfig"},
          functionCallingConfigToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"functionCallingConfig"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"retrievalConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"retrievalConfig"},
          retrievalConfigToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"retrievalConfig"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode prebuiltVoiceConfigToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"voiceName"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"voiceName"},
          Common.getValueByPath(fromObject, new String[] {"voiceName"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode voiceConfigToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"prebuiltVoiceConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"prebuiltVoiceConfig"},
          prebuiltVoiceConfigToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"prebuiltVoiceConfig"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode speechConfigToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"voiceConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"voiceConfig"},
          voiceConfigToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"voiceConfig"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"languageCode"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"languageCode"},
          Common.getValueByPath(fromObject, new String[] {"languageCode"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode thinkingConfigToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"includeThoughts"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"includeThoughts"},
          Common.getValueByPath(fromObject, new String[] {"includeThoughts"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"thinkingBudget"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"thinkingBudget"},
          Common.getValueByPath(fromObject, new String[] {"thinkingBudget"}));
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
          contentToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Transformers.tContent(
                      this.apiClient,
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
          Transformers.tSchema(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"responseSchema"})));
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
          modelSelectionConfigToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"modelSelectionConfig"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"safetySettings"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"safetySettings"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(
                safetySettingToVertex(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
      Common.setValueByPath(parentObject, new String[] {"safetySettings"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"tools"}) != null) {
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"tools"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(
                toolToVertex(
                    apiClient,
                    JsonSerializable.toJsonNode(Transformers.tTool(this.apiClient, item)),
                    toObject));
          });
      Common.setValueByPath(parentObject, new String[] {"tools"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"toolConfig"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"toolConfig"},
          toolConfigToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"toolConfig"})),
              toObject));
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
              apiClient,
              JsonSerializable.toJsonNode(
                  Transformers.tSpeechConfig(
                      this.apiClient,
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
          thinkingConfigToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"thinkingConfig"})),
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
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"contents"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(contentToVertex(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
      Common.setValueByPath(toObject, new String[] {"contents"}, result);
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
  ObjectNode embedContentConfigToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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
      Common.setValueByPath(
          toObject,
          new String[] {"config"},
          embedContentConfigToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateImagesConfigToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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

    if (Common.getValueByPath(fromObject, new String[] {"enhancePrompt"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "enhancePrompt"},
          Common.getValueByPath(fromObject, new String[] {"enhancePrompt"}));
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
      Common.setValueByPath(
          toObject,
          new String[] {"config"},
          generateImagesConfigToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode imageToVertex(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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
          Transformers.tBytes(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"imageBytes"})));
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
  ObjectNode maskReferenceConfigToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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
  ObjectNode controlReferenceConfigToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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
  ObjectNode styleReferenceConfigToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"styleDescription"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"styleDescription"},
          Common.getValueByPath(fromObject, new String[] {"styleDescription"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode subjectReferenceConfigToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"subjectType"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"subjectType"},
          Common.getValueByPath(fromObject, new String[] {"subjectType"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"subjectDescription"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"subjectDescription"},
          Common.getValueByPath(fromObject, new String[] {"subjectDescription"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode referenceImageAPIToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"referenceImage"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"referenceImage"},
          imageToVertex(
              apiClient,
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
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"maskImageConfig"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"controlImageConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"controlImageConfig"},
          controlReferenceConfigToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"controlImageConfig"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"styleImageConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"styleImageConfig"},
          styleReferenceConfigToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"styleImageConfig"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"subjectImageConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"subjectImageConfig"},
          subjectReferenceConfigToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"subjectImageConfig"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode editImageConfigToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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

      keyArray.forEach(
          item -> {
            result.add(
                referenceImageAPIToVertex(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
      Common.setValueByPath(toObject, new String[] {"instances[0]", "referenceImages"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"config"},
          editImageConfigToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode upscaleImageAPIConfigToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

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
              apiClient,
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
      Common.setValueByPath(
          toObject,
          new String[] {"config"},
          upscaleImageAPIConfigToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode countTokensConfigToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"systemInstruction"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"systemInstruction"},
          contentToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Transformers.tContent(
                      this.apiClient,
                      Common.getValueByPath(fromObject, new String[] {"systemInstruction"}))),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"tools"}) != null) {
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"tools"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(toolToVertex(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
      Common.setValueByPath(parentObject, new String[] {"tools"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"generationConfig"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"generationConfig"},
          Common.getValueByPath(fromObject, new String[] {"generationConfig"}));
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
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"contents"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(contentToVertex(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
      Common.setValueByPath(toObject, new String[] {"contents"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"config"},
          countTokensConfigToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject));
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
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"contents"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(contentToVertex(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
      Common.setValueByPath(toObject, new String[] {"contents"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"config"},
          Common.getValueByPath(fromObject, new String[] {"config"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateVideosConfigToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"image"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"config"},
          generateVideosConfigToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode partFromMldev(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"thought"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"thought"},
          Common.getValueByPath(fromObject, new String[] {"thought"}));
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
          Common.getValueByPath(fromObject, new String[] {"fileData"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"functionCall"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"functionCall"},
          Common.getValueByPath(fromObject, new String[] {"functionCall"}));
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
          Common.getValueByPath(fromObject, new String[] {"inlineData"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"text"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"text"},
          Common.getValueByPath(fromObject, new String[] {"text"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode contentFromMldev(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"parts"}) != null) {
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"parts"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(partFromMldev(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
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
  ObjectNode citationMetadataFromMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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
  ObjectNode candidateFromMldev(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"content"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"content"},
          contentFromMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"content"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"citationMetadata"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"citationMetadata"},
          citationMetadataFromMldev(
              apiClient,
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

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateContentResponseFromMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"candidates"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"candidates"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(candidateFromMldev(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
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

    if (Common.getValueByPath(fromObject, new String[] {"usageMetadata"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"usageMetadata"},
          Common.getValueByPath(fromObject, new String[] {"usageMetadata"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode contentEmbeddingStatisticsFromMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode contentEmbeddingFromMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"values"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"values"},
          Common.getValueByPath(fromObject, new String[] {"values"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode embedContentMetadataFromMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode embedContentResponseFromMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"embeddings"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"embeddings"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(
                contentEmbeddingFromMldev(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
      Common.setValueByPath(toObject, new String[] {"embeddings"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"metadata"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"metadata"},
          embedContentMetadataFromMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"metadata"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode imageFromMldev(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"bytesBase64Encoded"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"imageBytes"},
          Transformers.tBytes(
              this.apiClient,
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
  ObjectNode safetyAttributesFromMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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
  ObjectNode generatedImageFromMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"_self"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"image"},
          imageFromMldev(
              apiClient,
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
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"_self"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateImagesResponseFromMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"predictions"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"predictions"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(
                generatedImageFromMldev(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
      Common.setValueByPath(toObject, new String[] {"generatedImages"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"positivePromptSafetyAttributes"})
        != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"positivePromptSafetyAttributes"},
          safetyAttributesFromMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(
                      fromObject, new String[] {"positivePromptSafetyAttributes"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode countTokensResponseFromMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
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
  ObjectNode videoFromMldev(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"video", "uri"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"uri"},
          Common.getValueByPath(fromObject, new String[] {"video", "uri"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"video", "encodedVideo"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"videoBytes"},
          Transformers.tBytes(
              this.apiClient,
              Common.getValueByPath(fromObject, new String[] {"video", "encodedVideo"})));
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
  ObjectNode generatedVideoFromMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"_self"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"video"},
          videoFromMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"_self"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateVideosResponseFromMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"generatedSamples"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"generatedSamples"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(
                generatedVideoFromMldev(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
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
  ObjectNode generateVideosOperationFromMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(
                      fromObject, new String[] {"response", "generateVideoResponse"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode partFromVertex(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"videoMetadata"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"videoMetadata"},
          Common.getValueByPath(fromObject, new String[] {"videoMetadata"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"thought"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"thought"},
          Common.getValueByPath(fromObject, new String[] {"thought"}));
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
          Common.getValueByPath(fromObject, new String[] {"fileData"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"functionCall"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"functionCall"},
          Common.getValueByPath(fromObject, new String[] {"functionCall"}));
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
          Common.getValueByPath(fromObject, new String[] {"inlineData"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"text"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"text"},
          Common.getValueByPath(fromObject, new String[] {"text"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode contentFromVertex(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"parts"}) != null) {
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"parts"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(partFromVertex(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
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
  ObjectNode citationMetadataFromVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"citations"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"citations"},
          Common.getValueByPath(fromObject, new String[] {"citations"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode candidateFromVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"content"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"content"},
          contentFromVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"content"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"citationMetadata"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"citationMetadata"},
          citationMetadataFromVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"citationMetadata"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"finishMessage"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"finishMessage"},
          Common.getValueByPath(fromObject, new String[] {"finishMessage"}));
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

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateContentResponseFromVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"candidates"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"candidates"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(candidateFromVertex(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
      Common.setValueByPath(toObject, new String[] {"candidates"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"createTime"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"createTime"},
          Common.getValueByPath(fromObject, new String[] {"createTime"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"responseId"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"responseId"},
          Common.getValueByPath(fromObject, new String[] {"responseId"}));
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

    if (Common.getValueByPath(fromObject, new String[] {"usageMetadata"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"usageMetadata"},
          Common.getValueByPath(fromObject, new String[] {"usageMetadata"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode contentEmbeddingStatisticsFromVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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
  ObjectNode contentEmbeddingFromVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"statistics"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode embedContentMetadataFromVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"billableCharacterCount"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"billableCharacterCount"},
          Common.getValueByPath(fromObject, new String[] {"billableCharacterCount"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode embedContentResponseFromVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"predictions[]", "embeddings"}) != null) {
      ArrayNode keyArray =
          (ArrayNode)
              Common.getValueByPath(fromObject, new String[] {"predictions[]", "embeddings"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(
                contentEmbeddingFromVertex(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
      Common.setValueByPath(toObject, new String[] {"embeddings"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"metadata"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"metadata"},
          embedContentMetadataFromVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"metadata"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode imageFromVertex(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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
              this.apiClient,
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
  ObjectNode safetyAttributesFromVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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
  ObjectNode generatedImageFromVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"_self"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"image"},
          imageFromVertex(
              apiClient,
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
              apiClient,
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
  ObjectNode generateImagesResponseFromVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"predictions"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"predictions"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(
                generatedImageFromVertex(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
      Common.setValueByPath(toObject, new String[] {"generatedImages"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"positivePromptSafetyAttributes"})
        != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"positivePromptSafetyAttributes"},
          safetyAttributesFromVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(
                      fromObject, new String[] {"positivePromptSafetyAttributes"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode editImageResponseFromVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"predictions"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"predictions"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(
                generatedImageFromVertex(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
      Common.setValueByPath(toObject, new String[] {"generatedImages"}, result);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode upscaleImageResponseFromVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"predictions"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"predictions"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(
                generatedImageFromVertex(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
      Common.setValueByPath(toObject, new String[] {"generatedImages"}, result);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode countTokensResponseFromVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"totalTokens"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"totalTokens"},
          Common.getValueByPath(fromObject, new String[] {"totalTokens"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode computeTokensResponseFromVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"tokensInfo"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"tokensInfo"},
          Common.getValueByPath(fromObject, new String[] {"tokensInfo"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode videoFromVertex(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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
              this.apiClient,
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
  ObjectNode generatedVideoFromVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"_self"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"video"},
          videoFromVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"_self"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode generateVideosResponseFromVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"videos"}) != null) {
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"videos"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(
                generatedVideoFromVertex(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
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
  ObjectNode generateVideosOperationFromVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"response"})),
              toObject));
    }

    return toObject;
  }

  private GenerateContentResponse privateGenerateContent(
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
      path = Common.formatMap("{model}:generateContent", body.get("_url"));
    }
    body.remove("_url");

    // TODO: Handle "_query" in the body (for list support).

    // TODO: Remove the hack that removes config.
    body.remove("config");

    HttpOptions httpOptions = null;
    if (config != null) {
      httpOptions = config.httpOptions().orElse(null);
    }

    try (ApiResponse response =
        this.apiClient.request("post", path, JsonSerializable.toJsonString(body), httpOptions)) {
      HttpEntity entity = response.getEntity();
      String responseString;
      try {
        responseString = EntityUtils.toString(entity);
      } catch (IOException e) {
        throw new GenAiIOException("Failed to read HTTP response.", e);
      }
      JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);
      if (this.apiClient.vertexAI()) {
        responseNode = generateContentResponseFromVertex(this.apiClient, responseNode, null);
      } else {
        responseNode = generateContentResponseFromMldev(this.apiClient, responseNode, null);
      }
      return JsonSerializable.fromJsonNode(responseNode, GenerateContentResponse.class);
    }
  }

  private ResponseStream<GenerateContentResponse> privateGenerateContentStream(
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
      path = Common.formatMap("{model}:streamGenerateContent?alt=sse", body.get("_url"));
    }
    body.remove("_url");

    // TODO: Handle "_query" in the body (for list support).

    // TODO: Remove the hack that removes config.
    body.remove("config");

    HttpOptions httpOptions = null;
    if (config != null) {
      httpOptions = config.httpOptions().orElse(null);
    }

    ApiResponse response =
        this.apiClient.request("post", path, JsonSerializable.toJsonString(body), httpOptions);
    String converterName;
    if (this.apiClient.vertexAI()) {
      converterName = "generateContentResponseFromVertex";
    } else {
      converterName = "generateContentResponseFromMldev";
    }
    return new ResponseStream<GenerateContentResponse>(
        GenerateContentResponse.class, response, this, converterName);
  }

  private EmbedContentResponse privateEmbedContent(
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
      path = Common.formatMap("{model}:batchEmbedContents", body.get("_url"));
    }
    body.remove("_url");

    // TODO: Handle "_query" in the body (for list support).

    // TODO: Remove the hack that removes config.
    body.remove("config");

    HttpOptions httpOptions = null;
    if (config != null) {
      httpOptions = config.httpOptions().orElse(null);
    }

    try (ApiResponse response =
        this.apiClient.request("post", path, JsonSerializable.toJsonString(body), httpOptions)) {
      HttpEntity entity = response.getEntity();
      String responseString;
      try {
        responseString = EntityUtils.toString(entity);
      } catch (IOException e) {
        throw new GenAiIOException("Failed to read HTTP response.", e);
      }
      JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);
      if (this.apiClient.vertexAI()) {
        responseNode = embedContentResponseFromVertex(this.apiClient, responseNode, null);
      } else {
        responseNode = embedContentResponseFromMldev(this.apiClient, responseNode, null);
      }
      return JsonSerializable.fromJsonNode(responseNode, EmbedContentResponse.class);
    }
  }

  private GenerateImagesResponse privateGenerateImages(
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
      path = Common.formatMap("{model}:predict", body.get("_url"));
    }
    body.remove("_url");

    // TODO: Handle "_query" in the body (for list support).

    // TODO: Remove the hack that removes config.
    body.remove("config");

    HttpOptions httpOptions = null;
    if (config != null) {
      httpOptions = config.httpOptions().orElse(null);
    }

    try (ApiResponse response =
        this.apiClient.request("post", path, JsonSerializable.toJsonString(body), httpOptions)) {
      HttpEntity entity = response.getEntity();
      String responseString;
      try {
        responseString = EntityUtils.toString(entity);
      } catch (IOException e) {
        throw new GenAiIOException("Failed to read HTTP response.", e);
      }
      JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);
      if (this.apiClient.vertexAI()) {
        responseNode = generateImagesResponseFromVertex(this.apiClient, responseNode, null);
      } else {
        responseNode = generateImagesResponseFromMldev(this.apiClient, responseNode, null);
      }
      return JsonSerializable.fromJsonNode(responseNode, GenerateImagesResponse.class);
    }
  }

  private EditImageResponse privateEditImage(
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
          "This method is not supported by the Gemini Developer API.");
    }
    body.remove("_url");

    // TODO: Handle "_query" in the body (for list support).

    // TODO: Remove the hack that removes config.
    body.remove("config");

    HttpOptions httpOptions = null;
    if (config != null) {
      httpOptions = config.httpOptions().orElse(null);
    }

    try (ApiResponse response =
        this.apiClient.request("post", path, JsonSerializable.toJsonString(body), httpOptions)) {
      HttpEntity entity = response.getEntity();
      String responseString;
      try {
        responseString = EntityUtils.toString(entity);
      } catch (IOException e) {
        throw new GenAiIOException("Failed to read HTTP response.", e);
      }
      JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);
      if (this.apiClient.vertexAI()) {
        responseNode = editImageResponseFromVertex(this.apiClient, responseNode, null);
      } else {
        throw new UnsupportedOperationException(
            "This method is not supported by the Gemini Developer API.");
      }
      return JsonSerializable.fromJsonNode(responseNode, EditImageResponse.class);
    }
  }

  private UpscaleImageResponse privateUpscaleImage(
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
          "This method is not supported by the Gemini Developer API.");
    }
    body.remove("_url");

    // TODO: Handle "_query" in the body (for list support).

    // TODO: Remove the hack that removes config.
    body.remove("config");

    HttpOptions httpOptions = null;
    if (config != null) {
      httpOptions = config.httpOptions().orElse(null);
    }

    try (ApiResponse response =
        this.apiClient.request("post", path, JsonSerializable.toJsonString(body), httpOptions)) {
      HttpEntity entity = response.getEntity();
      String responseString;
      try {
        responseString = EntityUtils.toString(entity);
      } catch (IOException e) {
        throw new GenAiIOException("Failed to read HTTP response.", e);
      }
      JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);
      if (this.apiClient.vertexAI()) {
        responseNode = upscaleImageResponseFromVertex(this.apiClient, responseNode, null);
      } else {
        throw new UnsupportedOperationException(
            "This method is not supported by the Gemini Developer API.");
      }
      return JsonSerializable.fromJsonNode(responseNode, UpscaleImageResponse.class);
    }
  }

  public CountTokensResponse countTokens(
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
      path = Common.formatMap("{model}:countTokens", body.get("_url"));
    }
    body.remove("_url");

    // TODO: Handle "_query" in the body (for list support).

    // TODO: Remove the hack that removes config.
    body.remove("config");

    HttpOptions httpOptions = null;
    if (config != null) {
      httpOptions = config.httpOptions().orElse(null);
    }

    try (ApiResponse response =
        this.apiClient.request("post", path, JsonSerializable.toJsonString(body), httpOptions)) {
      HttpEntity entity = response.getEntity();
      String responseString;
      try {
        responseString = EntityUtils.toString(entity);
      } catch (IOException e) {
        throw new GenAiIOException("Failed to read HTTP response.", e);
      }
      JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);
      if (this.apiClient.vertexAI()) {
        responseNode = countTokensResponseFromVertex(this.apiClient, responseNode, null);
      } else {
        responseNode = countTokensResponseFromMldev(this.apiClient, responseNode, null);
      }
      return JsonSerializable.fromJsonNode(responseNode, CountTokensResponse.class);
    }
  }

  public ComputeTokensResponse computeTokens(
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
          "This method is not supported by the Gemini Developer API.");
    }
    body.remove("_url");

    // TODO: Handle "_query" in the body (for list support).

    // TODO: Remove the hack that removes config.
    body.remove("config");

    HttpOptions httpOptions = null;
    if (config != null) {
      httpOptions = config.httpOptions().orElse(null);
    }

    try (ApiResponse response =
        this.apiClient.request("post", path, JsonSerializable.toJsonString(body), httpOptions)) {
      HttpEntity entity = response.getEntity();
      String responseString;
      try {
        responseString = EntityUtils.toString(entity);
      } catch (IOException e) {
        throw new GenAiIOException("Failed to read HTTP response.", e);
      }
      JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);
      if (this.apiClient.vertexAI()) {
        responseNode = computeTokensResponseFromVertex(this.apiClient, responseNode, null);
      } else {
        throw new UnsupportedOperationException(
            "This method is not supported by the Gemini Developer API.");
      }
      return JsonSerializable.fromJsonNode(responseNode, ComputeTokensResponse.class);
    }
  }

  /**
   * Generates videos given a GenAI model, and a prompt or an image.
   *
   * <p>This method is experimental.
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
      path = Common.formatMap("{model}:predictLongRunning", body.get("_url"));
    }
    body.remove("_url");

    // TODO: Handle "_query" in the body (for list support).

    // TODO: Remove the hack that removes config.
    body.remove("config");

    HttpOptions httpOptions = null;
    if (config != null) {
      httpOptions = config.httpOptions().orElse(null);
    }

    try (ApiResponse response =
        this.apiClient.request("post", path, JsonSerializable.toJsonString(body), httpOptions)) {
      HttpEntity entity = response.getEntity();
      String responseString;
      try {
        responseString = EntityUtils.toString(entity);
      } catch (IOException e) {
        throw new GenAiIOException("Failed to read HTTP response.", e);
      }
      JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);
      if (this.apiClient.vertexAI()) {
        responseNode = generateVideosOperationFromVertex(this.apiClient, responseNode, null);
      } else {
        responseNode = generateVideosOperationFromMldev(this.apiClient, responseNode, null);
      }
      return JsonSerializable.fromJsonNode(responseNode, GenerateVideosOperation.class);
    }
  }

  /**
   * Generates content given a GenAI model and a content object.
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
    GenerateContentConfig transformedConfig =
        AfcUtil.transformGenerateContentConfig(apiClient, config);
    return privateGenerateContent(model, contents, transformedConfig);
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
    return generateContent(model, Transformers.tContents(this.apiClient, (Object) content), config);
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
    return generateContent(model, Transformers.tContents(this.apiClient, (Object) text), config);
  }

  /**
   * Generates content with streaming support given a GenAI model and a content object.
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
    GenerateContentConfig transformedConfig =
        AfcUtil.transformGenerateContentConfig(apiClient, config);
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
    return generateContentStream(
        model, Transformers.tContents(this.apiClient, (Object) content), config);
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
    return generateContentStream(
        model, Transformers.tContents(this.apiClient, (Object) text), config);
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
    return countTokens(model, Transformers.tContents(this.apiClient, (Object) text), config);
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
    return computeTokens(model, Transformers.tContents(this.apiClient, (Object) text), config);
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

    GenerateImagesResponse apiResponse = privateGenerateImages(model, prompt, config);

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

    GenerateImagesResponse response = builder.build();
    return response;
  }

  /**
   * Edits an image given a GenAI model, a prompt, and a list of reference images.
   *
   * @param model the name of the GenAI model to use for editing capabilities
   * @param prompt the prompt to edit the image
   * @param referenceImages a {@link List<com.google.genai.types.ReferenceImage>} to send to use for
   *     editing. The 5 types of reference images are: {@link
   *     com.google.genai.types.RawReferenceImage}, {@link
   *     com.google.genai.types.MaskReferenceImage}, {@link
   *     com.google.genai.types.ControlReferenceImage}, {@link
   *     com.google.genai.types.StyleReferenceImage}, {@link
   *     com.google.genai.types.SubjectReferenceImage},
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

    UpscaleImageAPIConfig.Builder builder = UpscaleImageAPIConfig.builder();
    if (config != null) {
      if (config.outputMimeType().isPresent()) {
        builder = builder.outputMimeType(config.outputMimeType().get());
      }
      if (config.outputCompressionQuality().isPresent()) {
        builder = builder.outputCompressionQuality(config.outputCompressionQuality().get());
      }
      if (config.includeRaiReason().isPresent()) {
        builder = builder.includeRaiReason(config.includeRaiReason().get());
      }
    }

    builder = builder.mode("upscale");
    builder = builder.numberOfImages(1);

    UpscaleImageAPIConfig apiConfig = builder.build();

    return privateUpscaleImage(model, image, upscaleFactor, apiConfig);
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
}
