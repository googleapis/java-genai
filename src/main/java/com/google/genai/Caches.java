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
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.CachedContent;
import com.google.genai.types.CreateCachedContentConfig;
import com.google.genai.types.CreateCachedContentParameters;
import com.google.genai.types.DeleteCachedContentConfig;
import com.google.genai.types.DeleteCachedContentParameters;
import com.google.genai.types.DeleteCachedContentResponse;
import com.google.genai.types.GetCachedContentConfig;
import com.google.genai.types.GetCachedContentParameters;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

public final class Caches {
  private final ApiClient apiClient;

  public Caches(ApiClient apiClient) {
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

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode createCachedContentConfigToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"ttl"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"ttl"},
          Common.getValueByPath(fromObject, new String[] {"ttl"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"expireTime"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"expireTime"},
          Common.getValueByPath(fromObject, new String[] {"expireTime"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"displayName"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"displayName"},
          Common.getValueByPath(fromObject, new String[] {"displayName"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"contents"}) != null) {
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"contents"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(contentToMldev(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
      Common.setValueByPath(parentObject, new String[] {"contents"}, result);
    }

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

    if (Common.getValueByPath(fromObject, new String[] {"tools"}) != null) {
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"tools"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(toolToMldev(apiClient, JsonSerializable.toJsonNode(item), toObject));
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

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode createCachedContentParametersToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"model"},
          Transformers.tCachesModel(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"config"},
          createCachedContentConfigToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode getCachedContentParametersToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "name"},
          Transformers.tCachedContentName(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"name"})));
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
  ObjectNode deleteCachedContentParametersToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "name"},
          Transformers.tCachedContentName(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"name"})));
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

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode createCachedContentConfigToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"ttl"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"ttl"},
          Common.getValueByPath(fromObject, new String[] {"ttl"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"expireTime"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"expireTime"},
          Common.getValueByPath(fromObject, new String[] {"expireTime"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"displayName"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"displayName"},
          Common.getValueByPath(fromObject, new String[] {"displayName"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"contents"}) != null) {
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"contents"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(contentToVertex(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
      Common.setValueByPath(parentObject, new String[] {"contents"}, result);
    }

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

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode createCachedContentParametersToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"model"},
          Transformers.tCachesModel(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"config"},
          createCachedContentConfigToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode getCachedContentParametersToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "name"},
          Transformers.tCachedContentName(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"name"})));
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
  ObjectNode deleteCachedContentParametersToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "name"},
          Transformers.tCachedContentName(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"name"})));
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
  ObjectNode cachedContentFromMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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

    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"model"},
          Common.getValueByPath(fromObject, new String[] {"model"}));
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

    if (Common.getValueByPath(fromObject, new String[] {"expireTime"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"expireTime"},
          Common.getValueByPath(fromObject, new String[] {"expireTime"}));
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
  ObjectNode deleteCachedContentResponseFromMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode cachedContentFromVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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

    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"model"},
          Common.getValueByPath(fromObject, new String[] {"model"}));
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

    if (Common.getValueByPath(fromObject, new String[] {"expireTime"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"expireTime"},
          Common.getValueByPath(fromObject, new String[] {"expireTime"}));
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
  ObjectNode deleteCachedContentResponseFromVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    return toObject;
  }

  public CachedContent create(String model, CreateCachedContentConfig config) {

    CreateCachedContentParameters.Builder parameterBuilder =
        CreateCachedContentParameters.builder();

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
      body = createCachedContentParametersToVertex(this.apiClient, parameterNode, null);
      path = Common.formatMap("cachedContents", body.get("_url"));
    } else {
      body = createCachedContentParametersToMldev(this.apiClient, parameterNode, null);
      path = Common.formatMap("cachedContents", body.get("_url"));
    }
    body.remove("_url");

    // TODO: Handle "_query" in the body (for list support).

    // TODO: Remove the hack that removes config.
    body.remove("config");

    try (ApiResponse response =
        this.apiClient.request("post", path, JsonSerializable.toJsonString(body))) {
      HttpEntity entity = response.getEntity();
      String responseString;
      try {
        responseString = EntityUtils.toString(entity);
      } catch (IOException e) {
        throw new GenAiIOException("Failed to read HTTP response.", e);
      }
      JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);
      if (this.apiClient.vertexAI()) {
        responseNode = cachedContentFromVertex(this.apiClient, responseNode, null);
      } else {
        responseNode = cachedContentFromMldev(this.apiClient, responseNode, null);
      }
      return JsonSerializable.fromJsonNode(responseNode, CachedContent.class);
    }
  }

  public CachedContent get(String name, GetCachedContentConfig config) {

    GetCachedContentParameters.Builder parameterBuilder = GetCachedContentParameters.builder();

    if (!Common.isZero(name)) {
      parameterBuilder.name(name);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    if (this.apiClient.vertexAI()) {
      body = getCachedContentParametersToVertex(this.apiClient, parameterNode, null);
      path = Common.formatMap("{name}", body.get("_url"));
    } else {
      body = getCachedContentParametersToMldev(this.apiClient, parameterNode, null);
      path = Common.formatMap("{name}", body.get("_url"));
    }
    body.remove("_url");

    // TODO: Handle "_query" in the body (for list support).

    // TODO: Remove the hack that removes config.
    body.remove("config");

    try (ApiResponse response =
        this.apiClient.request("get", path, JsonSerializable.toJsonString(body))) {
      HttpEntity entity = response.getEntity();
      String responseString;
      try {
        responseString = EntityUtils.toString(entity);
      } catch (IOException e) {
        throw new GenAiIOException("Failed to read HTTP response.", e);
      }
      JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);
      if (this.apiClient.vertexAI()) {
        responseNode = cachedContentFromVertex(this.apiClient, responseNode, null);
      } else {
        responseNode = cachedContentFromMldev(this.apiClient, responseNode, null);
      }
      return JsonSerializable.fromJsonNode(responseNode, CachedContent.class);
    }
  }

  public DeleteCachedContentResponse delete(String name, DeleteCachedContentConfig config) {

    DeleteCachedContentParameters.Builder parameterBuilder =
        DeleteCachedContentParameters.builder();

    if (!Common.isZero(name)) {
      parameterBuilder.name(name);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    if (this.apiClient.vertexAI()) {
      body = deleteCachedContentParametersToVertex(this.apiClient, parameterNode, null);
      path = Common.formatMap("{name}", body.get("_url"));
    } else {
      body = deleteCachedContentParametersToMldev(this.apiClient, parameterNode, null);
      path = Common.formatMap("{name}", body.get("_url"));
    }
    body.remove("_url");

    // TODO: Handle "_query" in the body (for list support).

    // TODO: Remove the hack that removes config.
    body.remove("config");

    try (ApiResponse response =
        this.apiClient.request("delete", path, JsonSerializable.toJsonString(body))) {
      HttpEntity entity = response.getEntity();
      String responseString;
      try {
        responseString = EntityUtils.toString(entity);
      } catch (IOException e) {
        throw new GenAiIOException("Failed to read HTTP response.", e);
      }
      JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);
      if (this.apiClient.vertexAI()) {
        responseNode = deleteCachedContentResponseFromVertex(this.apiClient, responseNode, null);
      } else {
        responseNode = deleteCachedContentResponseFromMldev(this.apiClient, responseNode, null);
      }
      return JsonSerializable.fromJsonNode(responseNode, DeleteCachedContentResponse.class);
    }
  }
}
