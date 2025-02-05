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
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentParameters;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.GenerateImagesConfig;
import com.google.genai.types.GenerateImagesParameters;
import com.google.genai.types.GenerateImagesResponse;
import java.io.IOException;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.util.EntityUtils;

public final class Models {
  private final ApiClient apiClient;

  public Models(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  ObjectNode PartToMldev(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"videoMetadata"}))) {
      throw new Error("videoMetadata parameter is not supported in Gemini API.");
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

  ObjectNode PartToVertex(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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

  ObjectNode ContentToMldev(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"parts"}) != null) {
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"parts"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(PartToMldev(apiClient, JsonSerializable.toJsonNode(item), toObject));
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

  ObjectNode ContentToVertex(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"parts"}) != null) {
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"parts"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(PartToVertex(apiClient, JsonSerializable.toJsonNode(item), toObject));
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

  ObjectNode SchemaToMldev(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"minItems"}))) {
      throw new Error("minItems parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"example"}))) {
      throw new Error("example parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"propertyOrdering"}))) {
      throw new Error("propertyOrdering parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"pattern"}))) {
      throw new Error("pattern parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"minimum"}))) {
      throw new Error("minimum parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"default"}))) {
      throw new Error("default parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"anyOf"}))) {
      throw new Error("anyOf parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"maxLength"}))) {
      throw new Error("maxLength parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"title"}))) {
      throw new Error("title parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"minLength"}))) {
      throw new Error("minLength parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"minProperties"}))) {
      throw new Error("minProperties parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"maxItems"}))) {
      throw new Error("maxItems parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"maximum"}))) {
      throw new Error("maximum parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"nullable"}))) {
      throw new Error("nullable parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"maxProperties"}))) {
      throw new Error("maxProperties parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"type"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"type"},
          Common.getValueByPath(fromObject, new String[] {"type"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"description"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"description"},
          Common.getValueByPath(fromObject, new String[] {"description"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"enum"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"enum"},
          Common.getValueByPath(fromObject, new String[] {"enum"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"format"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"format"},
          Common.getValueByPath(fromObject, new String[] {"format"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"items"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"items"},
          Common.getValueByPath(fromObject, new String[] {"items"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"properties"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"properties"},
          Common.getValueByPath(fromObject, new String[] {"properties"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"required"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"required"},
          Common.getValueByPath(fromObject, new String[] {"required"}));
    }

    return toObject;
  }

  ObjectNode SchemaToVertex(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"minItems"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"minItems"},
          Common.getValueByPath(fromObject, new String[] {"minItems"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"example"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"example"},
          Common.getValueByPath(fromObject, new String[] {"example"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"propertyOrdering"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"propertyOrdering"},
          Common.getValueByPath(fromObject, new String[] {"propertyOrdering"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"pattern"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"pattern"},
          Common.getValueByPath(fromObject, new String[] {"pattern"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"minimum"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"minimum"},
          Common.getValueByPath(fromObject, new String[] {"minimum"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"default"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"default"},
          Common.getValueByPath(fromObject, new String[] {"default"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"anyOf"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"anyOf"},
          Common.getValueByPath(fromObject, new String[] {"anyOf"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"maxLength"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"maxLength"},
          Common.getValueByPath(fromObject, new String[] {"maxLength"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"title"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"title"},
          Common.getValueByPath(fromObject, new String[] {"title"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"minLength"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"minLength"},
          Common.getValueByPath(fromObject, new String[] {"minLength"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"minProperties"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"minProperties"},
          Common.getValueByPath(fromObject, new String[] {"minProperties"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"maxItems"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"maxItems"},
          Common.getValueByPath(fromObject, new String[] {"maxItems"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"maximum"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"maximum"},
          Common.getValueByPath(fromObject, new String[] {"maximum"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"nullable"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"nullable"},
          Common.getValueByPath(fromObject, new String[] {"nullable"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"maxProperties"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"maxProperties"},
          Common.getValueByPath(fromObject, new String[] {"maxProperties"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"type"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"type"},
          Common.getValueByPath(fromObject, new String[] {"type"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"description"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"description"},
          Common.getValueByPath(fromObject, new String[] {"description"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"enum"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"enum"},
          Common.getValueByPath(fromObject, new String[] {"enum"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"format"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"format"},
          Common.getValueByPath(fromObject, new String[] {"format"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"items"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"items"},
          Common.getValueByPath(fromObject, new String[] {"items"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"properties"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"properties"},
          Common.getValueByPath(fromObject, new String[] {"properties"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"required"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"required"},
          Common.getValueByPath(fromObject, new String[] {"required"}));
    }

    return toObject;
  }

  ObjectNode SafetySettingToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"method"}))) {
      throw new Error("method parameter is not supported in Gemini API.");
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

  ObjectNode SafetySettingToVertex(
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

  ObjectNode FunctionDeclarationToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"response"}))) {
      throw new Error("response parameter is not supported in Gemini API.");
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

    return toObject;
  }

  ObjectNode FunctionDeclarationToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"response"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"response"},
          SchemaToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"response"})),
              toObject));
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

    return toObject;
  }

  ObjectNode GoogleSearchToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    return toObject;
  }

  ObjectNode GoogleSearchToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    return toObject;
  }

  ObjectNode DynamicRetrievalConfigToMldev(
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

  ObjectNode DynamicRetrievalConfigToVertex(
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

  ObjectNode GoogleSearchRetrievalToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"dynamicRetrievalConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"dynamicRetrievalConfig"},
          DynamicRetrievalConfigToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"dynamicRetrievalConfig"})),
              toObject));
    }

    return toObject;
  }

  ObjectNode GoogleSearchRetrievalToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"dynamicRetrievalConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"dynamicRetrievalConfig"},
          DynamicRetrievalConfigToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"dynamicRetrievalConfig"})),
              toObject));
    }

    return toObject;
  }

  ObjectNode ToolToMldev(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"functionDeclarations"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"functionDeclarations"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(
                FunctionDeclarationToMldev(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
      Common.setValueByPath(toObject, new String[] {"functionDeclarations"}, result);
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"retrieval"}))) {
      throw new Error("retrieval parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"googleSearch"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"googleSearch"},
          GoogleSearchToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"googleSearch"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"googleSearchRetrieval"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"googleSearchRetrieval"},
          GoogleSearchRetrievalToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"googleSearchRetrieval"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"codeExecution"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"codeExecution"},
          Common.getValueByPath(fromObject, new String[] {"codeExecution"}));
    }

    return toObject;
  }

  ObjectNode ToolToVertex(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"functionDeclarations"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"functionDeclarations"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(
                FunctionDeclarationToVertex(
                    apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
      Common.setValueByPath(toObject, new String[] {"functionDeclarations"}, result);
    }

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
          GoogleSearchToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"googleSearch"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"googleSearchRetrieval"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"googleSearchRetrieval"},
          GoogleSearchRetrievalToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"googleSearchRetrieval"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"codeExecution"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"codeExecution"},
          Common.getValueByPath(fromObject, new String[] {"codeExecution"}));
    }

    return toObject;
  }

  ObjectNode FunctionCallingConfigToMldev(
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

  ObjectNode FunctionCallingConfigToVertex(
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

  ObjectNode ToolConfigToMldev(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"functionCallingConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"functionCallingConfig"},
          FunctionCallingConfigToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"functionCallingConfig"})),
              toObject));
    }

    return toObject;
  }

  ObjectNode ToolConfigToVertex(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"functionCallingConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"functionCallingConfig"},
          FunctionCallingConfigToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"functionCallingConfig"})),
              toObject));
    }

    return toObject;
  }

  ObjectNode PrebuiltVoiceConfigToMldev(
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

  ObjectNode PrebuiltVoiceConfigToVertex(
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

  ObjectNode VoiceConfigToMldev(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"prebuiltVoiceConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"prebuiltVoiceConfig"},
          PrebuiltVoiceConfigToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"prebuiltVoiceConfig"})),
              toObject));
    }

    return toObject;
  }

  ObjectNode VoiceConfigToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"prebuiltVoiceConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"prebuiltVoiceConfig"},
          PrebuiltVoiceConfigToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"prebuiltVoiceConfig"})),
              toObject));
    }

    return toObject;
  }

  ObjectNode SpeechConfigToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"voiceConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"voiceConfig"},
          VoiceConfigToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"voiceConfig"})),
              toObject));
    }

    return toObject;
  }

  ObjectNode SpeechConfigToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"voiceConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"voiceConfig"},
          VoiceConfigToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"voiceConfig"})),
              toObject));
    }

    return toObject;
  }

  ObjectNode ThinkingConfigToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"includeThoughts"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"includeThoughts"},
          Common.getValueByPath(fromObject, new String[] {"includeThoughts"}));
    }

    return toObject;
  }

  ObjectNode ThinkingConfigToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"includeThoughts"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"includeThoughts"},
          Common.getValueByPath(fromObject, new String[] {"includeThoughts"}));
    }

    return toObject;
  }

  ObjectNode GenerateContentConfigToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"systemInstruction"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"systemInstruction"},
          ContentToMldev(
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
          SchemaToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Transformers.tSchema(
                      this.apiClient,
                      Common.getValueByPath(fromObject, new String[] {"responseSchema"}))),
              toObject));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"routingConfig"}))) {
      throw new Error("routingConfig parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"safetySettings"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"safetySettings"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(
                SafetySettingToMldev(apiClient, JsonSerializable.toJsonNode(item), toObject));
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
                ToolToMldev(
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
          ToolConfigToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"toolConfig"})),
              toObject));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"labels"}))) {
      throw new Error("labels parameter is not supported in Gemini API.");
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

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"mediaResolution"}))) {
      throw new Error("mediaResolution parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"speechConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"speechConfig"},
          SpeechConfigToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Transformers.tSpeechConfig(
                      this.apiClient,
                      Common.getValueByPath(fromObject, new String[] {"speechConfig"}))),
              toObject));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"audioTimestamp"}))) {
      throw new Error("audioTimestamp parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"thinkingConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"thinkingConfig"},
          ThinkingConfigToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"thinkingConfig"})),
              toObject));
    }

    return toObject;
  }

  ObjectNode GenerateContentConfigToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"systemInstruction"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"systemInstruction"},
          ContentToVertex(
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
          SchemaToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Transformers.tSchema(
                      this.apiClient,
                      Common.getValueByPath(fromObject, new String[] {"responseSchema"}))),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"routingConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"routingConfig"},
          Common.getValueByPath(fromObject, new String[] {"routingConfig"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"safetySettings"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"safetySettings"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(
                SafetySettingToVertex(apiClient, JsonSerializable.toJsonNode(item), toObject));
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
                ToolToVertex(
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
          ToolConfigToVertex(
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
          SpeechConfigToVertex(
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
          ThinkingConfigToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"thinkingConfig"})),
              toObject));
    }

    return toObject;
  }

  ObjectNode GenerateContentParametersToMldev(
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
            result.add(ContentToMldev(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
      Common.setValueByPath(toObject, new String[] {"contents"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"generationConfig"},
          GenerateContentConfigToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject));
    }

    return toObject;
  }

  ObjectNode GenerateContentParametersToVertex(
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
            result.add(ContentToVertex(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
      Common.setValueByPath(toObject, new String[] {"contents"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"generationConfig"},
          GenerateContentConfigToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject));
    }

    return toObject;
  }

  ObjectNode GenerateImagesConfigToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"outputGcsUri"}))) {
      throw new Error("outputGcsUri parameter is not supported in Gemini API.");
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

    if (Common.getValueByPath(fromObject, new String[] {"guidanceScale"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "guidanceScale"},
          Common.getValueByPath(fromObject, new String[] {"guidanceScale"}));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"seed"}))) {
      throw new Error("seed parameter is not supported in Gemini API.");
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
      throw new Error("addWatermark parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"aspectRatio"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "aspectRatio"},
          Common.getValueByPath(fromObject, new String[] {"aspectRatio"}));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"enhancePrompt"}))) {
      throw new Error("enhancePrompt parameter is not supported in Gemini API.");
    }

    return toObject;
  }

  ObjectNode GenerateImagesConfigToVertex(
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

    if (Common.getValueByPath(fromObject, new String[] {"aspectRatio"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "aspectRatio"},
          Common.getValueByPath(fromObject, new String[] {"aspectRatio"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"enhancePrompt"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"parameters", "enhancePrompt"},
          Common.getValueByPath(fromObject, new String[] {"enhancePrompt"}));
    }

    return toObject;
  }

  ObjectNode GenerateImagesParametersToMldev(
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
          new String[] {"instances", "prompt"},
          Common.getValueByPath(fromObject, new String[] {"prompt"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"config"},
          GenerateImagesConfigToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject));
    }

    return toObject;
  }

  ObjectNode GenerateImagesParametersToVertex(
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
          new String[] {"instances", "prompt"},
          Common.getValueByPath(fromObject, new String[] {"prompt"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"config"},
          GenerateImagesConfigToVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject));
    }

    return toObject;
  }

  ObjectNode PartFromMldev(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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

  ObjectNode PartFromVertex(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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

  ObjectNode ContentFromMldev(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"parts"}) != null) {
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"parts"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(PartFromMldev(apiClient, JsonSerializable.toJsonNode(item), toObject));
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

  ObjectNode ContentFromVertex(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"parts"}) != null) {
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"parts"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(PartFromVertex(apiClient, JsonSerializable.toJsonNode(item), toObject));
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

  ObjectNode CitationMetadataFromMldev(
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

  ObjectNode CitationMetadataFromVertex(
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

  ObjectNode CandidateFromMldev(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"content"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"content"},
          ContentFromMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"content"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"citationMetadata"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"citationMetadata"},
          CitationMetadataFromMldev(
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

    if (Common.getValueByPath(fromObject, new String[] {"avgLogprobs"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"avgLogprobs"},
          Common.getValueByPath(fromObject, new String[] {"avgLogprobs"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"finishReason"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"finishReason"},
          Common.getValueByPath(fromObject, new String[] {"finishReason"}));
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

  ObjectNode CandidateFromVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"content"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"content"},
          ContentFromVertex(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"content"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"citationMetadata"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"citationMetadata"},
          CitationMetadataFromVertex(
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

    if (Common.getValueByPath(fromObject, new String[] {"avgLogprobs"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"avgLogprobs"},
          Common.getValueByPath(fromObject, new String[] {"avgLogprobs"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"finishReason"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"finishReason"},
          Common.getValueByPath(fromObject, new String[] {"finishReason"}));
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

  ObjectNode GenerateContentResponseFromMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"candidates"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"candidates"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(CandidateFromMldev(apiClient, JsonSerializable.toJsonNode(item), toObject));
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

  ObjectNode GenerateContentResponseFromVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"candidates"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"candidates"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      keyArray.forEach(
          item -> {
            result.add(CandidateFromVertex(apiClient, JsonSerializable.toJsonNode(item), toObject));
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

  ObjectNode ImageFromMldev(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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

  ObjectNode ImageFromVertex(ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
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

  ObjectNode GeneratedImageFromMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"_self"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"image"},
          ImageFromMldev(
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

    return toObject;
  }

  ObjectNode GeneratedImageFromVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"_self"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"image"},
          ImageFromVertex(
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

    if (Common.getValueByPath(fromObject, new String[] {"prompt"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"enhancedPrompt"},
          Common.getValueByPath(fromObject, new String[] {"prompt"}));
    }

    return toObject;
  }

  ObjectNode GenerateImagesResponseFromMldev(
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
                GeneratedImageFromMldev(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
      Common.setValueByPath(toObject, new String[] {"generatedImages"}, result);
    }

    return toObject;
  }

  ObjectNode GenerateImagesResponseFromVertex(
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
                GeneratedImageFromVertex(apiClient, JsonSerializable.toJsonNode(item), toObject));
          });
      Common.setValueByPath(toObject, new String[] {"generatedImages"}, result);
    }

    return toObject;
  }

  public GenerateContentResponse generateContent(
      String model, List<Content> contents, GenerateContentConfig config)
      throws IOException, HttpException {

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
    if (this.apiClient.isVertexAI()) {
      body = GenerateContentParametersToVertex(this.apiClient, parameterNode, null);
      path = Common.formatMap("{model}:generateContent", body.get("_url"));
    } else {
      body = GenerateContentParametersToMldev(this.apiClient, parameterNode, null);
      path = Common.formatMap("{model}:generateContent", body.get("_url"));
    }
    body.remove("_url");
    // TODO: Remove the hack that removes config.
    body.remove("config");

    try (ApiResponse response = this.apiClient.post(path, JsonSerializable.toJsonString(body))) {
      HttpEntity entity = response.getEntity();
      String responseString = EntityUtils.toString(entity);
      JsonNode responseNode = JsonSerializable.objectMapper.readTree(responseString);
      if (this.apiClient.isVertexAI()) {
        responseNode = GenerateContentResponseFromVertex(this.apiClient, responseNode, null);
      } else {
        responseNode = GenerateContentResponseFromMldev(this.apiClient, responseNode, null);
      }
      return JsonSerializable.fromJsonNode(responseNode, GenerateContentResponse.class);
    }
  }

  public ResponseStream<GenerateContentResponse> generateContentStream(
      String model, List<Content> contents, GenerateContentConfig config)
      throws IOException, HttpException {

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
    if (this.apiClient.isVertexAI()) {
      body = GenerateContentParametersToVertex(this.apiClient, parameterNode, null);
      path = Common.formatMap("{model}:streamGenerateContent?alt=sse", body.get("_url"));
    } else {
      body = GenerateContentParametersToMldev(this.apiClient, parameterNode, null);
      path = Common.formatMap("{model}:streamGenerateContent?alt=sse", body.get("_url"));
    }
    body.remove("_url");
    // TODO: Remove the hack that removes config.
    body.remove("config");

    ApiResponse response = this.apiClient.post(path, JsonSerializable.toJsonString(body));
    String converterName;
    if (this.apiClient.isVertexAI()) {
      converterName = "GenerateContentResponseFromVertex";
    } else {
      converterName = "GenerateContentResponseFromMldev";
    }
    return new ResponseStream<GenerateContentResponse>(
        GenerateContentResponse.class, response, this, converterName);
  }

  public GenerateImagesResponse generateImages(
      String model, String prompt, GenerateImagesConfig config) throws IOException, HttpException {

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
    if (this.apiClient.isVertexAI()) {
      body = GenerateImagesParametersToVertex(this.apiClient, parameterNode, null);
      path = Common.formatMap("{model}:predict", body.get("_url"));
    } else {
      body = GenerateImagesParametersToMldev(this.apiClient, parameterNode, null);
      path = Common.formatMap("{model}:predict", body.get("_url"));
    }
    body.remove("_url");
    // TODO: Remove the hack that removes config.
    body.remove("config");

    try (ApiResponse response = this.apiClient.post(path, JsonSerializable.toJsonString(body))) {
      HttpEntity entity = response.getEntity();
      String responseString = EntityUtils.toString(entity);
      JsonNode responseNode = JsonSerializable.objectMapper.readTree(responseString);
      if (this.apiClient.isVertexAI()) {
        responseNode = GenerateImagesResponseFromVertex(this.apiClient, responseNode, null);
      } else {
        responseNode = GenerateImagesResponseFromMldev(this.apiClient, responseNode, null);
      }
      return JsonSerializable.fromJsonNode(responseNode, GenerateImagesResponse.class);
    }
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
   * @throws IOException if an I/O error occurs while making the API call
   * @throws HttpException if an HTTP error occurs while making the API call
   */
  public GenerateContentResponse generateContent(
      String model, Content content, GenerateContentConfig config)
      throws IOException, HttpException {
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
   * @throws IOException if an I/O error occurs while making the API call
   * @throws HttpException if an HTTP error occurs while making the API call
   */
  public GenerateContentResponse generateContent(
      String model, String text, GenerateContentConfig config) throws IOException, HttpException {
    return generateContent(model, Transformers.tContents(this.apiClient, (Object) text), config);
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
   * @throws IOException if an I/O error occurs while making the API call
   * @throws HttpException if an HTTP error occurs while making the API call
   */
  public ResponseStream<GenerateContentResponse> generateContentStream(
      String model, Content content, GenerateContentConfig config)
      throws IOException, HttpException {
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
   * @throws IOException if an I/O error occurs while making the API call
   * @throws HttpException if an HTTP error occurs while making the API call
   */
  public ResponseStream<GenerateContentResponse> generateContentStream(
      String model, String text, GenerateContentConfig config) throws IOException, HttpException {
    return generateContentStream(
        model, Transformers.tContents(this.apiClient, (Object) text), config);
  }
}
