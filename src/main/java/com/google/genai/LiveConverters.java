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

/** Internal SDK converter functions. */
final class LiveConverters {
  private final ApiClient apiClient;

  public LiveConverters(ApiClient apiClient) {
    this.apiClient = apiClient;
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

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"behavior"}))) {
      throw new IllegalArgumentException("behavior parameter is not supported in Vertex AI.");
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

    if (Common.getValueByPath(fromObject, new String[] {"responseJsonSchema"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"responseJsonSchema"},
          Common.getValueByPath(fromObject, new String[] {"responseJsonSchema"}));
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

    if (Common.getValueByPath(fromObject, new String[] {"timeRangeFilter"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"timeRangeFilter"},
          Common.getValueByPath(fromObject, new String[] {"timeRangeFilter"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode liveClientContentToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"turns"}) != null) {
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"turns"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(contentToMldev(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"turns"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"turnComplete"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"turnComplete"},
          Common.getValueByPath(fromObject, new String[] {"turnComplete"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode liveClientMessageToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"setup"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"setup"},
          liveClientSetupToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"setup"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"clientContent"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"clientContent"},
          liveClientContentToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"clientContent"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"realtimeInput"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"realtimeInput"},
          liveClientRealtimeInputToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"realtimeInput"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"realtimeInputParameters"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"realtime_input"},
          liveSendRealtimeInputParametersToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"realtimeInputParameters"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"toolResponse"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"toolResponse"},
          Common.getValueByPath(fromObject, new String[] {"toolResponse"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode liveClientMessageToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"setup"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"setup"},
          liveClientSetupToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"setup"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"clientContent"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"clientContent"},
          Common.getValueByPath(fromObject, new String[] {"clientContent"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"realtimeInput"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"realtimeInput"},
          liveClientRealtimeInputToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"realtimeInput"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"realtimeInputParameters"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"realtime_input"},
          liveSendRealtimeInputParametersToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"realtimeInputParameters"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"toolResponse"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"toolResponse"},
          Common.getValueByPath(fromObject, new String[] {"toolResponse"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode liveClientRealtimeInputToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"mediaChunks"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"mediaChunks"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(blobToMldev(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"mediaChunks"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"audio"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"audio"},
          blobToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"audio"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"audioStreamEnd"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"audioStreamEnd"},
          Common.getValueByPath(fromObject, new String[] {"audioStreamEnd"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"video"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"video"},
          blobToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"video"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"text"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"text"},
          Common.getValueByPath(fromObject, new String[] {"text"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"activityStart"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"activityStart"},
          Common.getValueByPath(fromObject, new String[] {"activityStart"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"activityEnd"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"activityEnd"},
          Common.getValueByPath(fromObject, new String[] {"activityEnd"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode liveClientRealtimeInputToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"mediaChunks"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"mediaChunks"},
          Common.getValueByPath(fromObject, new String[] {"mediaChunks"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"audio"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"audio"},
          Common.getValueByPath(fromObject, new String[] {"audio"}));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"audioStreamEnd"}))) {
      throw new IllegalArgumentException("audioStreamEnd parameter is not supported in Vertex AI.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"video"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"video"},
          Common.getValueByPath(fromObject, new String[] {"video"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"text"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"text"},
          Common.getValueByPath(fromObject, new String[] {"text"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"activityStart"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"activityStart"},
          Common.getValueByPath(fromObject, new String[] {"activityStart"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"activityEnd"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"activityEnd"},
          Common.getValueByPath(fromObject, new String[] {"activityEnd"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode liveClientSetupToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"model"},
          Common.getValueByPath(fromObject, new String[] {"model"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"generationConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"generationConfig"},
          Common.getValueByPath(fromObject, new String[] {"generationConfig"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"systemInstruction"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"systemInstruction"},
          contentToMldev(
              JsonSerializable.toJsonNode(
                  Transformers.tContent(
                      Common.getValueByPath(fromObject, new String[] {"systemInstruction"}))),
              toObject));
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
      Common.setValueByPath(toObject, new String[] {"tools"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"realtimeInputConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"realtimeInputConfig"},
          Common.getValueByPath(fromObject, new String[] {"realtimeInputConfig"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"sessionResumption"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"sessionResumption"},
          sessionResumptionConfigToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"sessionResumption"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"contextWindowCompression"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"contextWindowCompression"},
          Common.getValueByPath(fromObject, new String[] {"contextWindowCompression"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"inputAudioTranscription"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"inputAudioTranscription"},
          Common.getValueByPath(fromObject, new String[] {"inputAudioTranscription"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"outputAudioTranscription"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"outputAudioTranscription"},
          Common.getValueByPath(fromObject, new String[] {"outputAudioTranscription"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"proactivity"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"proactivity"},
          Common.getValueByPath(fromObject, new String[] {"proactivity"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode liveClientSetupToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"model"},
          Common.getValueByPath(fromObject, new String[] {"model"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"generationConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"generationConfig"},
          generationConfigToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"generationConfig"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"systemInstruction"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"systemInstruction"},
          Transformers.tContent(
              Common.getValueByPath(fromObject, new String[] {"systemInstruction"})));
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
      Common.setValueByPath(toObject, new String[] {"tools"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"realtimeInputConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"realtimeInputConfig"},
          Common.getValueByPath(fromObject, new String[] {"realtimeInputConfig"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"sessionResumption"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"sessionResumption"},
          Common.getValueByPath(fromObject, new String[] {"sessionResumption"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"contextWindowCompression"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"contextWindowCompression"},
          Common.getValueByPath(fromObject, new String[] {"contextWindowCompression"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"inputAudioTranscription"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"inputAudioTranscription"},
          Common.getValueByPath(fromObject, new String[] {"inputAudioTranscription"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"outputAudioTranscription"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"outputAudioTranscription"},
          Common.getValueByPath(fromObject, new String[] {"outputAudioTranscription"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"proactivity"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"proactivity"},
          Common.getValueByPath(fromObject, new String[] {"proactivity"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode liveConnectConfigToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"responseModalities"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "generationConfig", "responseModalities"},
          Common.getValueByPath(fromObject, new String[] {"responseModalities"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"temperature"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "generationConfig", "temperature"},
          Common.getValueByPath(fromObject, new String[] {"temperature"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"topP"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "generationConfig", "topP"},
          Common.getValueByPath(fromObject, new String[] {"topP"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"topK"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "generationConfig", "topK"},
          Common.getValueByPath(fromObject, new String[] {"topK"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"maxOutputTokens"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "generationConfig", "maxOutputTokens"},
          Common.getValueByPath(fromObject, new String[] {"maxOutputTokens"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"mediaResolution"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "generationConfig", "mediaResolution"},
          Common.getValueByPath(fromObject, new String[] {"mediaResolution"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"seed"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "generationConfig", "seed"},
          Common.getValueByPath(fromObject, new String[] {"seed"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"speechConfig"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "generationConfig", "speechConfig"},
          Transformers.tLiveSpeechConfig(
              Common.getValueByPath(fromObject, new String[] {"speechConfig"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"thinkingConfig"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "generationConfig", "thinkingConfig"},
          Common.getValueByPath(fromObject, new String[] {"thinkingConfig"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"enableAffectiveDialog"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "generationConfig", "enableAffectiveDialog"},
          Common.getValueByPath(fromObject, new String[] {"enableAffectiveDialog"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"systemInstruction"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "systemInstruction"},
          contentToMldev(
              JsonSerializable.toJsonNode(
                  Transformers.tContent(
                      Common.getValueByPath(fromObject, new String[] {"systemInstruction"}))),
              toObject));
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
      Common.setValueByPath(parentObject, new String[] {"setup", "tools"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"sessionResumption"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "sessionResumption"},
          sessionResumptionConfigToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"sessionResumption"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"inputAudioTranscription"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "inputAudioTranscription"},
          Common.getValueByPath(fromObject, new String[] {"inputAudioTranscription"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"outputAudioTranscription"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "outputAudioTranscription"},
          Common.getValueByPath(fromObject, new String[] {"outputAudioTranscription"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"realtimeInputConfig"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "realtimeInputConfig"},
          Common.getValueByPath(fromObject, new String[] {"realtimeInputConfig"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"contextWindowCompression"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "contextWindowCompression"},
          Common.getValueByPath(fromObject, new String[] {"contextWindowCompression"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"proactivity"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "proactivity"},
          Common.getValueByPath(fromObject, new String[] {"proactivity"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode liveConnectConfigToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"responseModalities"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "generationConfig", "responseModalities"},
          Common.getValueByPath(fromObject, new String[] {"responseModalities"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"temperature"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "generationConfig", "temperature"},
          Common.getValueByPath(fromObject, new String[] {"temperature"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"topP"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "generationConfig", "topP"},
          Common.getValueByPath(fromObject, new String[] {"topP"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"topK"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "generationConfig", "topK"},
          Common.getValueByPath(fromObject, new String[] {"topK"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"maxOutputTokens"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "generationConfig", "maxOutputTokens"},
          Common.getValueByPath(fromObject, new String[] {"maxOutputTokens"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"mediaResolution"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "generationConfig", "mediaResolution"},
          Common.getValueByPath(fromObject, new String[] {"mediaResolution"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"seed"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "generationConfig", "seed"},
          Common.getValueByPath(fromObject, new String[] {"seed"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"speechConfig"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "generationConfig", "speechConfig"},
          speechConfigToVertex(
              JsonSerializable.toJsonNode(
                  Transformers.tLiveSpeechConfig(
                      Common.getValueByPath(fromObject, new String[] {"speechConfig"}))),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"thinkingConfig"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "generationConfig", "thinkingConfig"},
          Common.getValueByPath(fromObject, new String[] {"thinkingConfig"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"enableAffectiveDialog"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "generationConfig", "enableAffectiveDialog"},
          Common.getValueByPath(fromObject, new String[] {"enableAffectiveDialog"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"systemInstruction"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "systemInstruction"},
          Transformers.tContent(
              Common.getValueByPath(fromObject, new String[] {"systemInstruction"})));
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
      Common.setValueByPath(parentObject, new String[] {"setup", "tools"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"sessionResumption"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "sessionResumption"},
          Common.getValueByPath(fromObject, new String[] {"sessionResumption"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"inputAudioTranscription"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "inputAudioTranscription"},
          Common.getValueByPath(fromObject, new String[] {"inputAudioTranscription"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"outputAudioTranscription"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "outputAudioTranscription"},
          Common.getValueByPath(fromObject, new String[] {"outputAudioTranscription"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"realtimeInputConfig"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "realtimeInputConfig"},
          Common.getValueByPath(fromObject, new String[] {"realtimeInputConfig"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"contextWindowCompression"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "contextWindowCompression"},
          Common.getValueByPath(fromObject, new String[] {"contextWindowCompression"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"proactivity"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"setup", "proactivity"},
          Common.getValueByPath(fromObject, new String[] {"proactivity"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode liveConnectParametersToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"setup", "model"},
          Transformers.tModel(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"config"},
          liveConnectConfigToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode liveConnectParametersToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"setup", "model"},
          Transformers.tModel(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"config"},
          liveConnectConfigToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode liveSendRealtimeInputParametersToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"media"}) != null) {
      ArrayNode keyArray =
          (ArrayNode)
              Transformers.tBlobs(Common.getValueByPath(fromObject, new String[] {"media"}));
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(blobToMldev(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"mediaChunks"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"audio"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"audio"},
          blobToMldev(
              JsonSerializable.toJsonNode(
                  Transformers.tAudioBlob(
                      Common.getValueByPath(fromObject, new String[] {"audio"}))),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"audioStreamEnd"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"audioStreamEnd"},
          Common.getValueByPath(fromObject, new String[] {"audioStreamEnd"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"video"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"video"},
          blobToMldev(
              JsonSerializable.toJsonNode(
                  Transformers.tImageBlob(
                      Common.getValueByPath(fromObject, new String[] {"video"}))),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"text"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"text"},
          Common.getValueByPath(fromObject, new String[] {"text"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"activityStart"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"activityStart"},
          Common.getValueByPath(fromObject, new String[] {"activityStart"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"activityEnd"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"activityEnd"},
          Common.getValueByPath(fromObject, new String[] {"activityEnd"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode liveSendRealtimeInputParametersToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"media"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"mediaChunks"},
          Transformers.tBlobs(Common.getValueByPath(fromObject, new String[] {"media"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"audio"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"audio"},
          Transformers.tAudioBlob(Common.getValueByPath(fromObject, new String[] {"audio"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"audioStreamEnd"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"audioStreamEnd"},
          Common.getValueByPath(fromObject, new String[] {"audioStreamEnd"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"video"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"video"},
          Transformers.tImageBlob(Common.getValueByPath(fromObject, new String[] {"video"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"text"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"text"},
          Common.getValueByPath(fromObject, new String[] {"text"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"activityStart"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"activityStart"},
          Common.getValueByPath(fromObject, new String[] {"activityStart"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"activityEnd"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"activityEnd"},
          Common.getValueByPath(fromObject, new String[] {"activityEnd"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode liveServerMessageFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"setupComplete"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"setupComplete"},
          Common.getValueByPath(fromObject, new String[] {"setupComplete"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"serverContent"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"serverContent"},
          Common.getValueByPath(fromObject, new String[] {"serverContent"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"toolCall"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"toolCall"},
          Common.getValueByPath(fromObject, new String[] {"toolCall"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"toolCallCancellation"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"toolCallCancellation"},
          Common.getValueByPath(fromObject, new String[] {"toolCallCancellation"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"usageMetadata"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"usageMetadata"},
          usageMetadataFromVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"usageMetadata"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"goAway"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"goAway"},
          Common.getValueByPath(fromObject, new String[] {"goAway"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"sessionResumptionUpdate"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"sessionResumptionUpdate"},
          Common.getValueByPath(fromObject, new String[] {"sessionResumptionUpdate"}));
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
  ObjectNode sessionResumptionConfigToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"handle"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"handle"},
          Common.getValueByPath(fromObject, new String[] {"handle"}));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"transparent"}))) {
      throw new IllegalArgumentException("transparent parameter is not supported in Gemini API.");
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

    if (Common.getValueByPath(fromObject, new String[] {"functionDeclarations"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"functionDeclarations"},
          Common.getValueByPath(fromObject, new String[] {"functionDeclarations"}));
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
  ObjectNode usageMetadataFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"promptTokenCount"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"promptTokenCount"},
          Common.getValueByPath(fromObject, new String[] {"promptTokenCount"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"cachedContentTokenCount"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"cachedContentTokenCount"},
          Common.getValueByPath(fromObject, new String[] {"cachedContentTokenCount"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"candidatesTokenCount"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"responseTokenCount"},
          Common.getValueByPath(fromObject, new String[] {"candidatesTokenCount"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"toolUsePromptTokenCount"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"toolUsePromptTokenCount"},
          Common.getValueByPath(fromObject, new String[] {"toolUsePromptTokenCount"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"thoughtsTokenCount"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"thoughtsTokenCount"},
          Common.getValueByPath(fromObject, new String[] {"thoughtsTokenCount"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"totalTokenCount"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"totalTokenCount"},
          Common.getValueByPath(fromObject, new String[] {"totalTokenCount"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"promptTokensDetails"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"promptTokensDetails"},
          Common.getValueByPath(fromObject, new String[] {"promptTokensDetails"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"cacheTokensDetails"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"cacheTokensDetails"},
          Common.getValueByPath(fromObject, new String[] {"cacheTokensDetails"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"candidatesTokensDetails"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"responseTokensDetails"},
          Common.getValueByPath(fromObject, new String[] {"candidatesTokensDetails"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"toolUsePromptTokensDetails"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"toolUsePromptTokensDetails"},
          Common.getValueByPath(fromObject, new String[] {"toolUsePromptTokensDetails"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"trafficType"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"trafficType"},
          Common.getValueByPath(fromObject, new String[] {"trafficType"}));
    }

    return toObject;
  }
}
