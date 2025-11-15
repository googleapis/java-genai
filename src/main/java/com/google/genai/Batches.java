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
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.genai.Common.BuiltRequest;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.BatchJob;
import com.google.genai.types.BatchJobSource;
import com.google.genai.types.CancelBatchJobConfig;
import com.google.genai.types.CancelBatchJobParameters;
import com.google.genai.types.CreateBatchJobConfig;
import com.google.genai.types.CreateBatchJobParameters;
import com.google.genai.types.CreateEmbeddingsBatchJobConfig;
import com.google.genai.types.CreateEmbeddingsBatchJobParameters;
import com.google.genai.types.DeleteBatchJobConfig;
import com.google.genai.types.DeleteBatchJobParameters;
import com.google.genai.types.DeleteResourceJob;
import com.google.genai.types.EmbeddingsBatchJobSource;
import com.google.genai.types.GetBatchJobConfig;
import com.google.genai.types.GetBatchJobParameters;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.HttpResponse;
import com.google.genai.types.ListBatchJobsConfig;
import com.google.genai.types.ListBatchJobsParameters;
import com.google.genai.types.ListBatchJobsResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import okhttp3.Headers;
import okhttp3.ResponseBody;

/**
 * Provides methods for managing the batch jobs. Instantiating this class is not required. After
 * instantiating a {@link Client}, access methods through `client.batches.methodName(...)` directly.
 */
public final class Batches {

  final ApiClient apiClient;

  public Batches(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  @ExcludeFromGeneratedCoverageReport
  void taskTypeMldevEnumValidate(Object enumValue) {
    ImmutableSet<String> invalidEnumValues = ImmutableSet.of("UNSPECIFIED");
    if (invalidEnumValues.contains(enumValue.toString().replace("\"", ""))) {
      throw new IllegalArgumentException(
          String.format("%s enum value is not supported in Gemini API.", enumValue));
    }
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode batchJobDestinationFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"responsesFile"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"fileName"},
          Common.getValueByPath(fromObject, new String[] {"responsesFile"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"inlinedResponses", "inlinedResponses"})
        != null) {
      ArrayNode keyArray =
          (ArrayNode)
              Common.getValueByPath(
                  fromObject, new String[] {"inlinedResponses", "inlinedResponses"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(inlinedResponseFromMldev(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"inlinedResponses"}, result);
    }

    if (Common.getValueByPath(
            fromObject, new String[] {"inlinedEmbedContentResponses", "inlinedResponses"})
        != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"inlinedEmbedContentResponses"},
          Common.getValueByPath(
              fromObject, new String[] {"inlinedEmbedContentResponses", "inlinedResponses"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode batchJobDestinationFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"predictionsFormat"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"format"},
          Common.getValueByPath(fromObject, new String[] {"predictionsFormat"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"gcsDestination", "outputUriPrefix"})
        != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"gcsUri"},
          Common.getValueByPath(fromObject, new String[] {"gcsDestination", "outputUriPrefix"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"bigqueryDestination", "outputUri"})
        != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"bigqueryUri"},
          Common.getValueByPath(fromObject, new String[] {"bigqueryDestination", "outputUri"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode batchJobDestinationToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"format"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"predictionsFormat"},
          Common.getValueByPath(fromObject, new String[] {"format"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"gcsUri"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"gcsDestination", "outputUriPrefix"},
          Common.getValueByPath(fromObject, new String[] {"gcsUri"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"bigqueryUri"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"bigqueryDestination", "outputUri"},
          Common.getValueByPath(fromObject, new String[] {"bigqueryUri"}));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"fileName"}))) {
      throw new IllegalArgumentException("fileName parameter is not supported in Vertex AI.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"inlinedResponses"}))) {
      throw new IllegalArgumentException(
          "inlinedResponses parameter is not supported in Vertex AI.");
    }

    if (!Common.isZero(
        Common.getValueByPath(fromObject, new String[] {"inlinedEmbedContentResponses"}))) {
      throw new IllegalArgumentException(
          "inlinedEmbedContentResponses parameter is not supported in Vertex AI.");
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode batchJobFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"name"},
          Common.getValueByPath(fromObject, new String[] {"name"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"metadata", "displayName"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"displayName"},
          Common.getValueByPath(fromObject, new String[] {"metadata", "displayName"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"metadata", "state"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"state"},
          Transformers.tJobState(
              Common.getValueByPath(fromObject, new String[] {"metadata", "state"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"metadata", "createTime"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"createTime"},
          Common.getValueByPath(fromObject, new String[] {"metadata", "createTime"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"metadata", "endTime"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"endTime"},
          Common.getValueByPath(fromObject, new String[] {"metadata", "endTime"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"metadata", "updateTime"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"updateTime"},
          Common.getValueByPath(fromObject, new String[] {"metadata", "updateTime"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"metadata", "model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"model"},
          Common.getValueByPath(fromObject, new String[] {"metadata", "model"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"metadata", "output"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"dest"},
          batchJobDestinationFromMldev(
              JsonSerializable.toJsonNode(
                  Transformers.tRecvBatchJobDestination(
                      Common.getValueByPath(fromObject, new String[] {"metadata", "output"}))),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode batchJobFromVertex(JsonNode fromObject, ObjectNode parentObject) {
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

    if (Common.getValueByPath(fromObject, new String[] {"state"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"state"},
          Transformers.tJobState(Common.getValueByPath(fromObject, new String[] {"state"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"error"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"error"},
          Common.getValueByPath(fromObject, new String[] {"error"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"createTime"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"createTime"},
          Common.getValueByPath(fromObject, new String[] {"createTime"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"startTime"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"startTime"},
          Common.getValueByPath(fromObject, new String[] {"startTime"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"endTime"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"endTime"},
          Common.getValueByPath(fromObject, new String[] {"endTime"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"updateTime"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"updateTime"},
          Common.getValueByPath(fromObject, new String[] {"updateTime"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"model"},
          Common.getValueByPath(fromObject, new String[] {"model"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"inputConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"src"},
          batchJobSourceFromVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"inputConfig"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"outputConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"dest"},
          batchJobDestinationFromVertex(
              JsonSerializable.toJsonNode(
                  Transformers.tRecvBatchJobDestination(
                      Common.getValueByPath(fromObject, new String[] {"outputConfig"}))),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"completionStats"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"completionStats"},
          Common.getValueByPath(fromObject, new String[] {"completionStats"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode batchJobSourceFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"instancesFormat"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"format"},
          Common.getValueByPath(fromObject, new String[] {"instancesFormat"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"gcsSource", "uris"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"gcsUri"},
          Common.getValueByPath(fromObject, new String[] {"gcsSource", "uris"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"bigquerySource", "inputUri"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"bigqueryUri"},
          Common.getValueByPath(fromObject, new String[] {"bigquerySource", "inputUri"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode batchJobSourceToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"format"}))) {
      throw new IllegalArgumentException("format parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"gcsUri"}))) {
      throw new IllegalArgumentException("gcsUri parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"bigqueryUri"}))) {
      throw new IllegalArgumentException("bigqueryUri parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"fileName"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"fileName"},
          Common.getValueByPath(fromObject, new String[] {"fileName"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"inlinedRequests"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"inlinedRequests"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(inlinedRequestToMldev(apiClient, JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"requests", "requests"}, result);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode batchJobSourceToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"format"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"instancesFormat"},
          Common.getValueByPath(fromObject, new String[] {"format"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"gcsUri"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"gcsSource", "uris"},
          Common.getValueByPath(fromObject, new String[] {"gcsUri"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"bigqueryUri"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"bigquerySource", "inputUri"},
          Common.getValueByPath(fromObject, new String[] {"bigqueryUri"}));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"fileName"}))) {
      throw new IllegalArgumentException("fileName parameter is not supported in Vertex AI.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"inlinedRequests"}))) {
      throw new IllegalArgumentException(
          "inlinedRequests parameter is not supported in Vertex AI.");
    }

    return toObject;
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
  ObjectNode cancelBatchJobParametersToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "name"},
          Transformers.tBatchJobName(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"name"})));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode cancelBatchJobParametersToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "name"},
          Transformers.tBatchJobName(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"name"})));
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
  ObjectNode contentEmbeddingToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"values"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"values"},
          Common.getValueByPath(fromObject, new String[] {"values"}));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"statistics"}))) {
      throw new IllegalArgumentException("statistics parameter is not supported in Gemini API.");
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
  ObjectNode createBatchJobConfigToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"displayName"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"batch", "displayName"},
          Common.getValueByPath(fromObject, new String[] {"displayName"}));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"dest"}))) {
      throw new IllegalArgumentException("dest parameter is not supported in Gemini API.");
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode createBatchJobConfigToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"displayName"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"displayName"},
          Common.getValueByPath(fromObject, new String[] {"displayName"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"dest"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"outputConfig"},
          batchJobDestinationToVertex(
              JsonSerializable.toJsonNode(
                  Transformers.tBatchJobDestination(
                      Common.getValueByPath(fromObject, new String[] {"dest"}))),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode createBatchJobParametersToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "model"},
          Transformers.tModel(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"src"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"batch", "inputConfig"},
          batchJobSourceToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Transformers.tBatchJobSource(
                      Common.getValueByPath(fromObject, new String[] {"src"}))),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          createBatchJobConfigToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode createBatchJobParametersToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"model"},
          Transformers.tModel(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"src"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"inputConfig"},
          batchJobSourceToVertex(
              JsonSerializable.toJsonNode(
                  Transformers.tBatchJobSource(
                      Common.getValueByPath(fromObject, new String[] {"src"}))),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          createBatchJobConfigToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode createEmbeddingsBatchJobConfigToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"displayName"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"batch", "displayName"},
          Common.getValueByPath(fromObject, new String[] {"displayName"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode createEmbeddingsBatchJobParametersToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "model"},
          Transformers.tModel(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"model"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"src"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"batch", "inputConfig"},
          embeddingsBatchJobSourceToMldev(
              apiClient,
              JsonSerializable.toJsonNode(Common.getValueByPath(fromObject, new String[] {"src"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          createEmbeddingsBatchJobConfigToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode deleteBatchJobParametersToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "name"},
          Transformers.tBatchJobName(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"name"})));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode deleteBatchJobParametersToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "name"},
          Transformers.tBatchJobName(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"name"})));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode deleteResourceJobFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"sdkHttpResponse"},
          Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"name"},
          Common.getValueByPath(fromObject, new String[] {"name"}));
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

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode deleteResourceJobFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"sdkHttpResponse"},
          Common.getValueByPath(fromObject, new String[] {"sdkHttpResponse"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"name"},
          Common.getValueByPath(fromObject, new String[] {"name"}));
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

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode embedContentBatchOutputToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"inlinedResponses"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"inlinedResponses"},
          inlinedEmbedContentResponsesToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"inlinedResponses"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"responsesFile"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"responsesFile"},
          Common.getValueByPath(fromObject, new String[] {"responsesFile"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode embedContentBatchToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"contents"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"requests[]", "request", "content"},
          Transformers.tContentsForEmbed(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"contents"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_self"},
          embedContentConfigToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject));
      Common.moveValueByPath(toObject, ImmutableMap.of("requests[].*", "requests[].request.*"));
    }

    if (Common.getValueByPath(fromObject, new String[] {"batchStats"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"batchStats"},
          Common.getValueByPath(fromObject, new String[] {"batchStats"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"createTime"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"createTime"},
          Common.getValueByPath(fromObject, new String[] {"createTime"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"displayName"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"displayName"},
          Common.getValueByPath(fromObject, new String[] {"displayName"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"endTime"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"endTime"},
          Common.getValueByPath(fromObject, new String[] {"endTime"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"inputConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"inputConfig"},
          inputEmbedContentConfigToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"inputConfig"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"model"},
          Common.getValueByPath(fromObject, new String[] {"model"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"name"},
          Common.getValueByPath(fromObject, new String[] {"name"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"output"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"output"},
          embedContentBatchOutputToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"output"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"priority"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"priority"},
          Common.getValueByPath(fromObject, new String[] {"priority"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"state"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"state"},
          Common.getValueByPath(fromObject, new String[] {"state"}));
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
  ObjectNode embedContentRequestToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"autoTruncate"}))) {
      throw new IllegalArgumentException("autoTruncate parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"content"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"content"},
          contentToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"content"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"outputDimensionality"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"outputDimensionality"},
          Common.getValueByPath(fromObject, new String[] {"outputDimensionality"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"taskType"}) != null) {
      taskTypeMldevEnumValidate(Common.getValueByPath(fromObject, new String[] {"taskType"}));
      Common.setValueByPath(
          toObject,
          new String[] {"taskType"},
          Common.getValueByPath(fromObject, new String[] {"taskType"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"title"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"title"},
          Common.getValueByPath(fromObject, new String[] {"title"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"model"},
          Common.getValueByPath(fromObject, new String[] {"model"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode embeddingsBatchJobSourceToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"fileName"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"file_name"},
          Common.getValueByPath(fromObject, new String[] {"fileName"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"inlinedRequests"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"requests"},
          embedContentBatchToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"inlinedRequests"})),
              toObject));
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
  ObjectNode functionResponseFileDataToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
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

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"displayName"}))) {
      throw new IllegalArgumentException("displayName parameter is not supported in Gemini API.");
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode functionResponsePartToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"inlineData"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"inlineData"},
          Common.getValueByPath(fromObject, new String[] {"inlineData"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"fileData"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"fileData"},
          functionResponseFileDataToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"fileData"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode functionResponseToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"willContinue"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"willContinue"},
          Common.getValueByPath(fromObject, new String[] {"willContinue"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"scheduling"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"scheduling"},
          Common.getValueByPath(fromObject, new String[] {"scheduling"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"parts"}) != null) {
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"parts"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(functionResponsePartToMldev(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"parts"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"id"}) != null) {
      Common.setValueByPath(
          toObject, new String[] {"id"}, Common.getValueByPath(fromObject, new String[] {"id"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"name"},
          Common.getValueByPath(fromObject, new String[] {"name"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"response"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"response"},
          Common.getValueByPath(fromObject, new String[] {"response"}));
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
  ObjectNode getBatchJobParametersToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "name"},
          Transformers.tBatchJobName(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"name"})));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode getBatchJobParametersToVertex(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "name"},
          Transformers.tBatchJobName(
              this.apiClient, Common.getValueByPath(fromObject, new String[] {"name"})));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode googleSearchToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"blockingConfidence"}))) {
      throw new IllegalArgumentException(
          "blockingConfidence parameter is not supported in Gemini API.");
    }

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

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"imageOutputOptions"}))) {
      throw new IllegalArgumentException(
          "imageOutputOptions parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"personGeneration"}))) {
      throw new IllegalArgumentException(
          "personGeneration parameter is not supported in Gemini API.");
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode inlinedEmbedContentRequestToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"metadata"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"metadata"},
          Common.getValueByPath(fromObject, new String[] {"metadata"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"request"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"request"},
          embedContentRequestToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"request"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode inlinedEmbedContentRequestsToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"requests"}) != null) {
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"requests"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(inlinedEmbedContentRequestToMldev(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"requests"}, result);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode inlinedEmbedContentResponseToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"response"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"response"},
          singleEmbedContentResponseToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"response"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"error"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"error"},
          Common.getValueByPath(fromObject, new String[] {"error"}));
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
  ObjectNode inlinedEmbedContentResponsesToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"inlinedResponses"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"inlinedResponses"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(inlinedEmbedContentResponseToMldev(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"inlinedResponses"}, result);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode inlinedRequestToMldev(
      ApiClient apiClient, JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"request", "model"},
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
      Common.setValueByPath(toObject, new String[] {"request", "contents"}, result);
    }

    if (Common.getValueByPath(fromObject, new String[] {"metadata"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"metadata"},
          Common.getValueByPath(fromObject, new String[] {"metadata"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"request", "generationConfig"},
          generateContentConfigToMldev(
              apiClient,
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              (ObjectNode)
                  Common.getValueByPath(
                      toObject,
                      new String[] {"request"},
                      (new ObjectMapper()).createObjectNode())));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode inlinedResponseFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"response"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"response"},
          generateContentResponseFromMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"response"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"error"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"error"},
          Common.getValueByPath(fromObject, new String[] {"error"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode inputEmbedContentConfigToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"fileName"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"fileName"},
          Common.getValueByPath(fromObject, new String[] {"fileName"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"requests"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"requests"},
          inlinedEmbedContentRequestsToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"requests"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode listBatchJobsConfigToMldev(JsonNode fromObject, ObjectNode parentObject) {
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

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"filter"}))) {
      throw new IllegalArgumentException("filter parameter is not supported in Gemini API.");
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode listBatchJobsConfigToVertex(JsonNode fromObject, ObjectNode parentObject) {
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

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode listBatchJobsParametersToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          listBatchJobsConfigToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode listBatchJobsParametersToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          listBatchJobsConfigToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode listBatchJobsResponseFromMldev(JsonNode fromObject, ObjectNode parentObject) {
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

    if (Common.getValueByPath(fromObject, new String[] {"operations"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"operations"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(batchJobFromMldev(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"batchJobs"}, result);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode listBatchJobsResponseFromVertex(JsonNode fromObject, ObjectNode parentObject) {
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

    if (Common.getValueByPath(fromObject, new String[] {"batchPredictionJobs"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"batchPredictionJobs"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(batchJobFromVertex(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"batchJobs"}, result);
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
          functionResponseToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"functionResponse"})),
              toObject));
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

    if (Common.getValueByPath(fromObject, new String[] {"partMetadata"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"partMetadata"},
          Common.getValueByPath(fromObject, new String[] {"partMetadata"}));
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
  ObjectNode singleEmbedContentResponseToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"embedding"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"embedding"},
          contentEmbeddingToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"embedding"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"tokenCount"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"tokenCount"},
          Common.getValueByPath(fromObject, new String[] {"tokenCount"}));
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
          Common.getValueByPath(fromObject, new String[] {"googleMaps"}));
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

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForPrivateCreate(
      String model, BatchJobSource src, CreateBatchJobConfig config) {

    CreateBatchJobParameters.Builder parameterBuilder = CreateBatchJobParameters.builder();

    if (!Common.isZero(model)) {
      parameterBuilder.model(model);
    }
    if (!Common.isZero(src)) {
      parameterBuilder.src(src);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    if (this.apiClient.vertexAI()) {
      body = createBatchJobParametersToVertex(this.apiClient, parameterNode, null);
      path = Common.formatMap("batchPredictionJobs", body.get("_url"));
    } else {
      body = createBatchJobParametersToMldev(this.apiClient, parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("{model}:batchGenerateContent", body.get("_url"));
      } else {
        path = "{model}:batchGenerateContent";
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
  BatchJob processResponseForPrivateCreate(ApiResponse response, CreateBatchJobConfig config) {
    ResponseBody responseBody = response.getBody();
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }

    JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);

    if (this.apiClient.vertexAI()) {
      responseNode = batchJobFromVertex(responseNode, null);
    }

    if (!this.apiClient.vertexAI()) {
      responseNode = batchJobFromMldev(responseNode, null);
    }

    return JsonSerializable.fromJsonNode(responseNode, BatchJob.class);
  }

  BatchJob privateCreate(String model, BatchJobSource src, CreateBatchJobConfig config) {
    BuiltRequest builtRequest = buildRequestForPrivateCreate(model, src, config);

    try (ApiResponse response =
        this.apiClient.request(
            "post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)) {
      return processResponseForPrivateCreate(response, config);
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForPrivateCreateEmbeddings(
      String model, EmbeddingsBatchJobSource src, CreateEmbeddingsBatchJobConfig config) {

    CreateEmbeddingsBatchJobParameters.Builder parameterBuilder =
        CreateEmbeddingsBatchJobParameters.builder();

    if (!Common.isZero(model)) {
      parameterBuilder.model(model);
    }
    if (!Common.isZero(src)) {
      parameterBuilder.src(src);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    if (this.apiClient.vertexAI()) {
      throw new UnsupportedOperationException(
          "This method is only supported in the Gemini Developer client.");
    } else {
      body = createEmbeddingsBatchJobParametersToMldev(this.apiClient, parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("{model}:asyncBatchEmbedContent", body.get("_url"));
      } else {
        path = "{model}:asyncBatchEmbedContent";
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
  BatchJob processResponseForPrivateCreateEmbeddings(
      ApiResponse response, CreateEmbeddingsBatchJobConfig config) {
    ResponseBody responseBody = response.getBody();
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }

    JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);

    if (this.apiClient.vertexAI()) {
      throw new UnsupportedOperationException(
          "This method is only supported in the Gemini Developer client.");
    }

    if (!this.apiClient.vertexAI()) {
      responseNode = batchJobFromMldev(responseNode, null);
    }

    return JsonSerializable.fromJsonNode(responseNode, BatchJob.class);
  }

  BatchJob privateCreateEmbeddings(
      String model, EmbeddingsBatchJobSource src, CreateEmbeddingsBatchJobConfig config) {
    BuiltRequest builtRequest = buildRequestForPrivateCreateEmbeddings(model, src, config);

    try (ApiResponse response =
        this.apiClient.request(
            "post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)) {
      return processResponseForPrivateCreateEmbeddings(response, config);
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForGet(String name, GetBatchJobConfig config) {

    GetBatchJobParameters.Builder parameterBuilder = GetBatchJobParameters.builder();

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
      body = getBatchJobParametersToVertex(this.apiClient, parameterNode, null);
      path = Common.formatMap("batchPredictionJobs/{name}", body.get("_url"));
    } else {
      body = getBatchJobParametersToMldev(this.apiClient, parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("batches/{name}", body.get("_url"));
      } else {
        path = "batches/{name}";
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
  BatchJob processResponseForGet(ApiResponse response, GetBatchJobConfig config) {
    ResponseBody responseBody = response.getBody();
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }

    JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);

    if (this.apiClient.vertexAI()) {
      responseNode = batchJobFromVertex(responseNode, null);
    }

    if (!this.apiClient.vertexAI()) {
      responseNode = batchJobFromMldev(responseNode, null);
    }

    return JsonSerializable.fromJsonNode(responseNode, BatchJob.class);
  }

  /**
   * Gets a batch job resource.
   *
   * @param name A fully-qualified BatchJob resource name or ID. Example:
   *     "projects/.../locations/.../batchPredictionJobs/456" or "456" when project and location are
   *     initialized in the Vertex AI client. Or "batches/abc" using the Gemini Developer AI client.
   * @param config A {@link GetBatchJobConfig} for configuring the get request.
   * @return A {@link BatchJob} object that contains the info of the batch job.
   */
  public BatchJob get(String name, GetBatchJobConfig config) {
    BuiltRequest builtRequest = buildRequestForGet(name, config);

    try (ApiResponse response =
        this.apiClient.request(
            "get", builtRequest.path, builtRequest.body, builtRequest.httpOptions)) {
      return processResponseForGet(response, config);
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForCancel(String name, CancelBatchJobConfig config) {

    CancelBatchJobParameters.Builder parameterBuilder = CancelBatchJobParameters.builder();

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
      body = cancelBatchJobParametersToVertex(this.apiClient, parameterNode, null);
      path = Common.formatMap("batchPredictionJobs/{name}:cancel", body.get("_url"));
    } else {
      body = cancelBatchJobParametersToMldev(this.apiClient, parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("batches/{name}:cancel", body.get("_url"));
      } else {
        path = "batches/{name}:cancel";
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

  /**
   * Cancels a batch job resource.
   *
   * @param name A fully-qualified BatchJob resource name or ID. Example:
   *     "projects/.../locations/.../batchPredictionJobs/456" or "456" when project and location are
   *     initialized in the Vertex AI client. Or "batches/abc" using the Gemini Developer AI client.
   * @param config A {@link CancelBatchJobConfig} for configuring the cancel request.
   */
  public void cancel(String name, CancelBatchJobConfig config) {
    BuiltRequest builtRequest = buildRequestForCancel(name, config);

    try (ApiResponse response =
        this.apiClient.request(
            "post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)) {
      return;
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForPrivateList(ListBatchJobsConfig config) {

    ListBatchJobsParameters.Builder parameterBuilder = ListBatchJobsParameters.builder();

    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    if (this.apiClient.vertexAI()) {
      body = listBatchJobsParametersToVertex(parameterNode, null);
      path = Common.formatMap("batchPredictionJobs", body.get("_url"));
    } else {
      body = listBatchJobsParametersToMldev(parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("batches", body.get("_url"));
      } else {
        path = "batches";
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
  ListBatchJobsResponse processResponseForPrivateList(
      ApiResponse response, ListBatchJobsConfig config) {
    ResponseBody responseBody = response.getBody();
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }

    JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);

    if (this.apiClient.vertexAI()) {
      responseNode = listBatchJobsResponseFromVertex(responseNode, null);
    }

    if (!this.apiClient.vertexAI()) {
      responseNode = listBatchJobsResponseFromMldev(responseNode, null);
    }

    ListBatchJobsResponse sdkResponse =
        JsonSerializable.fromJsonNode(responseNode, ListBatchJobsResponse.class);
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

  ListBatchJobsResponse privateList(ListBatchJobsConfig config) {
    BuiltRequest builtRequest = buildRequestForPrivateList(config);

    try (ApiResponse response =
        this.apiClient.request(
            "get", builtRequest.path, builtRequest.body, builtRequest.httpOptions)) {
      return processResponseForPrivateList(response, config);
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForDelete(String name, DeleteBatchJobConfig config) {

    DeleteBatchJobParameters.Builder parameterBuilder = DeleteBatchJobParameters.builder();

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
      body = deleteBatchJobParametersToVertex(this.apiClient, parameterNode, null);
      path = Common.formatMap("batchPredictionJobs/{name}", body.get("_url"));
    } else {
      body = deleteBatchJobParametersToMldev(this.apiClient, parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("batches/{name}", body.get("_url"));
      } else {
        path = "batches/{name}";
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
  DeleteResourceJob processResponseForDelete(ApiResponse response, DeleteBatchJobConfig config) {
    ResponseBody responseBody = response.getBody();
    String responseString;
    try {
      responseString = responseBody.string();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to read HTTP response.", e);
    }

    JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);

    if (this.apiClient.vertexAI()) {
      responseNode = deleteResourceJobFromVertex(responseNode, null);
    }

    if (!this.apiClient.vertexAI()) {
      responseNode = deleteResourceJobFromMldev(responseNode, null);
    }

    DeleteResourceJob sdkResponse =
        JsonSerializable.fromJsonNode(responseNode, DeleteResourceJob.class);
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
   * Deletes a batch job resource.
   *
   * @param name A fully-qualified BatchJob resource name or ID. Example:
   *     "projects/.../locations/.../batchPredictionJobs/456" or "456" when project and location are
   *     initialized in the Vertex AI client. Or "batches/abc" using the Gemini Developer AI client.
   * @param config A {@link DeleteBatchJobConfig} for configuring the delete request.
   */
  public DeleteResourceJob delete(String name, DeleteBatchJobConfig config) {
    BuiltRequest builtRequest = buildRequestForDelete(name, config);

    try (ApiResponse response =
        this.apiClient.request(
            "delete", builtRequest.path, builtRequest.body, builtRequest.httpOptions)) {
      return processResponseForDelete(response, config);
    }
  }

  /**
   * Makes an API request to create the batch job.
   *
   * @param model the name of the GenAI model to use for generation
   * @param src The {@link BatchJobSource} of the batch job.
   * @param config The configuration {@link CreateBatchJobConfig} for the batch job.
   * @return A BatchJob.
   */
  public BatchJob create(String model, BatchJobSource src, CreateBatchJobConfig config) {
    if (this.apiClient.vertexAI()) {
      if (src.inlinedRequests().isPresent()) {
        throw new GenAiIOException("inlinedRequests is not supported for Vertex AI.");
      }
      if (src.fileName().isPresent()) {
        throw new GenAiIOException("fileName is not supported for Vertex AI.");
      }
      if (src.gcsUri().isPresent() && src.bigqueryUri().isPresent()) {
        throw new GenAiIOException("Only one of gcsUri and bigqueryUri can be set.");
      }
      if (!src.gcsUri().isPresent() && !src.bigqueryUri().isPresent()) {
        throw new GenAiIOException("One of gcsUri and bigqueryUri must be set.");
      }
    } else {
      if (src.fileName().isPresent() && src.inlinedRequests().isPresent()) {
        throw new GenAiIOException("Only one of fileName and InlinedRequests can be set.");
      }
      if (!src.fileName().isPresent() && !src.inlinedRequests().isPresent()) {
        throw new GenAiIOException("one of fileName and InlinedRequests must be set.");
      }
    }
    return this.privateCreate(model, src, config);
  }

  /**
   * Makes an API request to create the batch embeddings job.
   *
   * <p>This method is experimental.
   *
   * @param model the name of the GenAI model to use for generation
   * @param src The {@link EmbeddingsBatchJobSource} of the batch job.
   * @param config The configuration {@link CreateEmbeddingsBatchJobConfig} for the batch job.
   * @return A BatchJob.
   */
  public BatchJob createEmbeddings(
      String model, EmbeddingsBatchJobSource src, CreateEmbeddingsBatchJobConfig config) {
    if (this.apiClient.vertexAI()) {
      throw new UnsupportedOperationException(
          "Vertex AI does not support batches.createEmbeddings.");
    }
    return this.privateCreateEmbeddings(model, src, config);
  }

  /**
   * Makes an API request to list the available batch jobs.
   *
   * @param config A {@link ListBatchJobsConfig} for configuring the list request.
   * @return A {@link Pager} object that contains the list of batch jobs. The pager is an iterable
   *     and automatically queries the next page once the current page is exhausted.
   */
  @SuppressWarnings("PatternMatchingInstanceof")
  public Pager<BatchJob> list(ListBatchJobsConfig config) {
    Function<JsonSerializable, Object> request =
        requestConfig -> {
          if (!(requestConfig instanceof ListBatchJobsConfig)) {
            throw new GenAiIOException(
                "Internal error: Pager expected ListBatchJobsConfig but received "
                    + requestConfig.getClass().getName());
          }
          return this.privateList((ListBatchJobsConfig) requestConfig);
        };
    return new Pager<>(
        Pager.PagedItem.BATCH_JOBS,
        request,
        (ObjectNode) JsonSerializable.toJsonNode(config),
        JsonSerializable.toJsonNode(privateList(config)));
  }
}
