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
import com.google.genai.types.CreateTuningJobConfig;
import com.google.genai.types.CreateTuningJobParametersPrivate;
import com.google.genai.types.GetTuningJobConfig;
import com.google.genai.types.GetTuningJobParameters;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.JobState;
import com.google.genai.types.ListTuningJobsConfig;
import com.google.genai.types.ListTuningJobsParameters;
import com.google.genai.types.ListTuningJobsResponse;
import com.google.genai.types.PreTunedModel;
import com.google.genai.types.TuningDataset;
import com.google.genai.types.TuningJob;
import com.google.genai.types.TuningOperation;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Function;
import okhttp3.ResponseBody;

public final class Tunings {
  final ApiClient apiClient;

  public Tunings(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode getTuningJobParametersToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "name"},
          Common.getValueByPath(fromObject, new String[] {"name"}));
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
  ObjectNode listTuningJobsConfigToMldev(JsonNode fromObject, ObjectNode parentObject) {
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
  ObjectNode listTuningJobsParametersToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"config"},
          listTuningJobsConfigToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode tuningExampleToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"textInput"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"textInput"},
          Common.getValueByPath(fromObject, new String[] {"textInput"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"output"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"output"},
          Common.getValueByPath(fromObject, new String[] {"output"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode tuningDatasetToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"gcsUri"}))) {
      throw new IllegalArgumentException("gcsUri parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"vertexDatasetResource"}))) {
      throw new IllegalArgumentException(
          "vertexDatasetResource parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"examples"}) != null) {
      ArrayNode keyArray = (ArrayNode) Common.getValueByPath(fromObject, new String[] {"examples"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(tuningExampleToMldev(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"examples", "examples"}, result);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode createTuningJobConfigToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"validationDataset"}))) {
      throw new IllegalArgumentException(
          "validationDataset parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"tunedModelDisplayName"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"displayName"},
          Common.getValueByPath(fromObject, new String[] {"tunedModelDisplayName"}));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"description"}))) {
      throw new IllegalArgumentException("description parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"epochCount"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"tuningTask", "hyperparameters", "epochCount"},
          Common.getValueByPath(fromObject, new String[] {"epochCount"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"learningRateMultiplier"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"tuningTask", "hyperparameters", "learningRateMultiplier"},
          Common.getValueByPath(fromObject, new String[] {"learningRateMultiplier"}));
    }

    if (!Common.isZero(
        Common.getValueByPath(fromObject, new String[] {"exportLastCheckpointOnly"}))) {
      throw new IllegalArgumentException(
          "exportLastCheckpointOnly parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(
        Common.getValueByPath(fromObject, new String[] {"preTunedModelCheckpointId"}))) {
      throw new IllegalArgumentException(
          "preTunedModelCheckpointId parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"adapterSize"}))) {
      throw new IllegalArgumentException("adapterSize parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"batchSize"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"tuningTask", "hyperparameters", "batchSize"},
          Common.getValueByPath(fromObject, new String[] {"batchSize"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"learningRate"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"tuningTask", "hyperparameters", "learningRate"},
          Common.getValueByPath(fromObject, new String[] {"learningRate"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode createTuningJobParametersPrivateToMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"baseModel"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"baseModel"},
          Common.getValueByPath(fromObject, new String[] {"baseModel"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"preTunedModel"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"preTunedModel"},
          Common.getValueByPath(fromObject, new String[] {"preTunedModel"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"trainingDataset"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"tuningTask", "trainingData"},
          tuningDatasetToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"trainingDataset"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"config"},
          createTuningJobConfigToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode getTuningJobParametersToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "name"},
          Common.getValueByPath(fromObject, new String[] {"name"}));
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
  ObjectNode listTuningJobsConfigToVertex(JsonNode fromObject, ObjectNode parentObject) {
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
  ObjectNode listTuningJobsParametersToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"config"},
          listTuningJobsConfigToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode tuningDatasetToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"gcsUri"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"supervisedTuningSpec", "trainingDatasetUri"},
          Common.getValueByPath(fromObject, new String[] {"gcsUri"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"vertexDatasetResource"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"supervisedTuningSpec", "trainingDatasetUri"},
          Common.getValueByPath(fromObject, new String[] {"vertexDatasetResource"}));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"examples"}))) {
      throw new IllegalArgumentException("examples parameter is not supported in Vertex AI.");
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode tuningValidationDatasetToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"gcsUri"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"validationDatasetUri"},
          Common.getValueByPath(fromObject, new String[] {"gcsUri"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"vertexDatasetResource"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"supervisedTuningSpec", "trainingDatasetUri"},
          Common.getValueByPath(fromObject, new String[] {"vertexDatasetResource"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode createTuningJobConfigToVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"validationDataset"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"supervisedTuningSpec"},
          tuningValidationDatasetToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"validationDataset"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"tunedModelDisplayName"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"tunedModelDisplayName"},
          Common.getValueByPath(fromObject, new String[] {"tunedModelDisplayName"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"description"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"description"},
          Common.getValueByPath(fromObject, new String[] {"description"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"epochCount"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"supervisedTuningSpec", "hyperParameters", "epochCount"},
          Common.getValueByPath(fromObject, new String[] {"epochCount"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"learningRateMultiplier"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"supervisedTuningSpec", "hyperParameters", "learningRateMultiplier"},
          Common.getValueByPath(fromObject, new String[] {"learningRateMultiplier"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"exportLastCheckpointOnly"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"supervisedTuningSpec", "exportLastCheckpointOnly"},
          Common.getValueByPath(fromObject, new String[] {"exportLastCheckpointOnly"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"preTunedModelCheckpointId"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"preTunedModel", "checkpointId"},
          Common.getValueByPath(fromObject, new String[] {"preTunedModelCheckpointId"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"adapterSize"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"supervisedTuningSpec", "hyperParameters", "adapterSize"},
          Common.getValueByPath(fromObject, new String[] {"adapterSize"}));
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"batchSize"}))) {
      throw new IllegalArgumentException("batchSize parameter is not supported in Vertex AI.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"learningRate"}))) {
      throw new IllegalArgumentException("learningRate parameter is not supported in Vertex AI.");
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode createTuningJobParametersPrivateToVertex(
      JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"baseModel"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"baseModel"},
          Common.getValueByPath(fromObject, new String[] {"baseModel"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"preTunedModel"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"preTunedModel"},
          Common.getValueByPath(fromObject, new String[] {"preTunedModel"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"trainingDataset"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"supervisedTuningSpec", "trainingDatasetUri"},
          tuningDatasetToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"trainingDataset"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"config"},
          createTuningJobConfigToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode tunedModelFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"model"},
          Common.getValueByPath(fromObject, new String[] {"name"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"endpoint"},
          Common.getValueByPath(fromObject, new String[] {"name"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode tuningJobFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"name"},
          Common.getValueByPath(fromObject, new String[] {"name"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"state"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"state"},
          Transformers.tTuningJobStatus(Common.getValueByPath(fromObject, new String[] {"state"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"createTime"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"createTime"},
          Common.getValueByPath(fromObject, new String[] {"createTime"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"tuningTask", "startTime"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"startTime"},
          Common.getValueByPath(fromObject, new String[] {"tuningTask", "startTime"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"tuningTask", "completeTime"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"endTime"},
          Common.getValueByPath(fromObject, new String[] {"tuningTask", "completeTime"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"updateTime"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"updateTime"},
          Common.getValueByPath(fromObject, new String[] {"updateTime"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"description"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"description"},
          Common.getValueByPath(fromObject, new String[] {"description"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"baseModel"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"baseModel"},
          Common.getValueByPath(fromObject, new String[] {"baseModel"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"_self"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"tunedModel"},
          tunedModelFromMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"_self"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"customBaseModel"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"customBaseModel"},
          Common.getValueByPath(fromObject, new String[] {"customBaseModel"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"experiment"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"experiment"},
          Common.getValueByPath(fromObject, new String[] {"experiment"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"labels"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"labels"},
          Common.getValueByPath(fromObject, new String[] {"labels"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"outputUri"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"outputUri"},
          Common.getValueByPath(fromObject, new String[] {"outputUri"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"pipelineJob"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"pipelineJob"},
          Common.getValueByPath(fromObject, new String[] {"pipelineJob"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"serviceAccount"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"serviceAccount"},
          Common.getValueByPath(fromObject, new String[] {"serviceAccount"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"tunedModelDisplayName"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"tunedModelDisplayName"},
          Common.getValueByPath(fromObject, new String[] {"tunedModelDisplayName"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode listTuningJobsResponseFromMldev(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"nextPageToken"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"nextPageToken"},
          Common.getValueByPath(fromObject, new String[] {"nextPageToken"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"tunedModels"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"tunedModels"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(tuningJobFromMldev(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"tuningJobs"}, result);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode tuningOperationFromMldev(JsonNode fromObject, ObjectNode parentObject) {
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

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode tunedModelCheckpointFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"checkpointId"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"checkpointId"},
          Common.getValueByPath(fromObject, new String[] {"checkpointId"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"epoch"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"epoch"},
          Common.getValueByPath(fromObject, new String[] {"epoch"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"step"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"step"},
          Common.getValueByPath(fromObject, new String[] {"step"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"endpoint"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"endpoint"},
          Common.getValueByPath(fromObject, new String[] {"endpoint"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode tunedModelFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"model"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"model"},
          Common.getValueByPath(fromObject, new String[] {"model"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"endpoint"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"endpoint"},
          Common.getValueByPath(fromObject, new String[] {"endpoint"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"checkpoints"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"checkpoints"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(tunedModelCheckpointFromVertex(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"checkpoints"}, result);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode tuningJobFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"name"},
          Common.getValueByPath(fromObject, new String[] {"name"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"state"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"state"},
          Transformers.tTuningJobStatus(Common.getValueByPath(fromObject, new String[] {"state"})));
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

    if (Common.getValueByPath(fromObject, new String[] {"error"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"error"},
          Common.getValueByPath(fromObject, new String[] {"error"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"description"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"description"},
          Common.getValueByPath(fromObject, new String[] {"description"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"baseModel"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"baseModel"},
          Common.getValueByPath(fromObject, new String[] {"baseModel"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"tunedModel"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"tunedModel"},
          tunedModelFromVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"tunedModel"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"preTunedModel"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"preTunedModel"},
          Common.getValueByPath(fromObject, new String[] {"preTunedModel"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"supervisedTuningSpec"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"supervisedTuningSpec"},
          Common.getValueByPath(fromObject, new String[] {"supervisedTuningSpec"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"tuningDataStats"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"tuningDataStats"},
          Common.getValueByPath(fromObject, new String[] {"tuningDataStats"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"encryptionSpec"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"encryptionSpec"},
          Common.getValueByPath(fromObject, new String[] {"encryptionSpec"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"partnerModelTuningSpec"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"partnerModelTuningSpec"},
          Common.getValueByPath(fromObject, new String[] {"partnerModelTuningSpec"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"customBaseModel"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"customBaseModel"},
          Common.getValueByPath(fromObject, new String[] {"customBaseModel"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"experiment"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"experiment"},
          Common.getValueByPath(fromObject, new String[] {"experiment"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"labels"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"labels"},
          Common.getValueByPath(fromObject, new String[] {"labels"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"outputUri"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"outputUri"},
          Common.getValueByPath(fromObject, new String[] {"outputUri"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"pipelineJob"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"pipelineJob"},
          Common.getValueByPath(fromObject, new String[] {"pipelineJob"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"serviceAccount"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"serviceAccount"},
          Common.getValueByPath(fromObject, new String[] {"serviceAccount"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"tunedModelDisplayName"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"tunedModelDisplayName"},
          Common.getValueByPath(fromObject, new String[] {"tunedModelDisplayName"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode listTuningJobsResponseFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    if (Common.getValueByPath(fromObject, new String[] {"nextPageToken"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"nextPageToken"},
          Common.getValueByPath(fromObject, new String[] {"nextPageToken"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"tuningJobs"}) != null) {
      ArrayNode keyArray =
          (ArrayNode) Common.getValueByPath(fromObject, new String[] {"tuningJobs"});
      ObjectMapper objectMapper = new ObjectMapper();
      ArrayNode result = objectMapper.createArrayNode();

      for (JsonNode item : keyArray) {
        result.add(tuningJobFromVertex(JsonSerializable.toJsonNode(item), toObject));
      }
      Common.setValueByPath(toObject, new String[] {"tuningJobs"}, result);
    }

    return toObject;
  }

  TuningJob privateGet(String name, GetTuningJobConfig config) {

    GetTuningJobParameters.Builder parameterBuilder = GetTuningJobParameters.builder();

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
      body = getTuningJobParametersToVertex(parameterNode, null);
      path = Common.formatMap("{name}", body.get("_url"));
    } else {
      body = getTuningJobParametersToMldev(parameterNode, null);
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
    body.remove("config");

    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    try (ApiResponse response =
        this.apiClient.request(
            "get", path, JsonSerializable.toJsonString(body), requestHttpOptions)) {
      ResponseBody responseBody = response.getBody();
      String responseString;
      try {
        responseString = responseBody.string();
      } catch (IOException e) {
        throw new GenAiIOException("Failed to read HTTP response.", e);
      }

      JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);
      if (this.apiClient.vertexAI()) {
        responseNode = tuningJobFromVertex(responseNode, null);
      } else {
        responseNode = tuningJobFromMldev(responseNode, null);
      }
      return JsonSerializable.fromJsonNode(responseNode, TuningJob.class);
    }
  }

  ListTuningJobsResponse privateList(ListTuningJobsConfig config) {

    ListTuningJobsParameters.Builder parameterBuilder = ListTuningJobsParameters.builder();

    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    if (this.apiClient.vertexAI()) {
      body = listTuningJobsParametersToVertex(parameterNode, null);
      path = Common.formatMap("tuningJobs", body.get("_url"));
    } else {
      body = listTuningJobsParametersToMldev(parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("tunedModels", body.get("_url"));
      } else {
        path = "tunedModels";
      }
    }
    body.remove("_url");

    JsonNode queryParams = body.get("_query");
    if (queryParams != null) {
      body.remove("_query");
      path = String.format("%s?%s", path, Common.urlEncode((ObjectNode) queryParams));
    }

    // TODO: Remove the hack that removes config.
    body.remove("config");

    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    try (ApiResponse response =
        this.apiClient.request(
            "get", path, JsonSerializable.toJsonString(body), requestHttpOptions)) {
      ResponseBody responseBody = response.getBody();
      String responseString;
      try {
        responseString = responseBody.string();
      } catch (IOException e) {
        throw new GenAiIOException("Failed to read HTTP response.", e);
      }

      JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);
      if (this.apiClient.vertexAI()) {
        responseNode = listTuningJobsResponseFromVertex(responseNode, null);
      } else {
        responseNode = listTuningJobsResponseFromMldev(responseNode, null);
      }
      return JsonSerializable.fromJsonNode(responseNode, ListTuningJobsResponse.class);
    }
  }

  TuningJob privateTune(
      String baseModel,
      PreTunedModel preTunedModel,
      TuningDataset trainingDataset,
      CreateTuningJobConfig config) {

    CreateTuningJobParametersPrivate.Builder parameterBuilder =
        CreateTuningJobParametersPrivate.builder();

    if (!Common.isZero(baseModel)) {
      parameterBuilder.baseModel(baseModel);
    }
    if (!Common.isZero(preTunedModel)) {
      parameterBuilder.preTunedModel(preTunedModel);
    }
    if (!Common.isZero(trainingDataset)) {
      parameterBuilder.trainingDataset(trainingDataset);
    }
    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    if (this.apiClient.vertexAI()) {
      body = createTuningJobParametersPrivateToVertex(parameterNode, null);
      path = Common.formatMap("tuningJobs", body.get("_url"));
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
    body.remove("config");

    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    try (ApiResponse response =
        this.apiClient.request(
            "post", path, JsonSerializable.toJsonString(body), requestHttpOptions)) {
      ResponseBody responseBody = response.getBody();
      String responseString;
      try {
        responseString = responseBody.string();
      } catch (IOException e) {
        throw new GenAiIOException("Failed to read HTTP response.", e);
      }

      JsonNode responseNode = JsonSerializable.stringToJsonNode(responseString);
      if (this.apiClient.vertexAI()) {
        responseNode = tuningJobFromVertex(responseNode, null);
      } else {
        throw new UnsupportedOperationException(
            "This method is only supported in the Vertex AI client.");
      }
      return JsonSerializable.fromJsonNode(responseNode, TuningJob.class);
    }
  }

  TuningOperation privateTuneMldev(
      String baseModel,
      PreTunedModel preTunedModel,
      TuningDataset trainingDataset,
      CreateTuningJobConfig config) {

    CreateTuningJobParametersPrivate.Builder parameterBuilder =
        CreateTuningJobParametersPrivate.builder();

    if (!Common.isZero(baseModel)) {
      parameterBuilder.baseModel(baseModel);
    }
    if (!Common.isZero(preTunedModel)) {
      parameterBuilder.preTunedModel(preTunedModel);
    }
    if (!Common.isZero(trainingDataset)) {
      parameterBuilder.trainingDataset(trainingDataset);
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
      body = createTuningJobParametersPrivateToMldev(parameterNode, null);
      if (body.get("_url") != null) {
        path = Common.formatMap("tunedModels", body.get("_url"));
      } else {
        path = "tunedModels";
      }
    }
    body.remove("_url");

    JsonNode queryParams = body.get("_query");
    if (queryParams != null) {
      body.remove("_query");
      path = String.format("%s?%s", path, Common.urlEncode((ObjectNode) queryParams));
    }

    // TODO: Remove the hack that removes config.
    body.remove("config");

    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    try (ApiResponse response =
        this.apiClient.request(
            "post", path, JsonSerializable.toJsonString(body), requestHttpOptions)) {
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
      } else {
        responseNode = tuningOperationFromMldev(responseNode, null);
      }
      return JsonSerializable.fromJsonNode(responseNode, TuningOperation.class);
    }
  }

  /**
   * Makes an API request to get a tuning job.
   *
   * @param name The resource name of the tuning job.
   * @param config A {@link GetTuningJobConfig} for configuring the get request.
   * @return A {@link TuningJob} object.
   */
  public TuningJob get(String name, GetTuningJobConfig config) {
    return this.privateGet(name, config);
  }

  /**
   * Makes an API request to list the available tuning jobs.
   *
   * @param config A {@link ListTuningJobsConfig} for configuring the list request.
   * @return A {@link Pager} object that contains the list of tuning jobs. The pager is an iterable
   *     and automatically queries the next page once the current page is exhausted.
   */
  @SuppressWarnings("PatternMatchingInstanceof")
  public Pager<TuningJob> list(ListTuningJobsConfig config) {
    Function<JsonSerializable, Object> request =
        requestConfig -> {
          if (!(requestConfig instanceof ListTuningJobsConfig)) {
            throw new GenAiIOException(
                "Internal error: Pager expected ListTuningJobsConfig but received "
                    + requestConfig.getClass().getName());
          }
          return this.privateList((ListTuningJobsConfig) requestConfig);
        };
    if (config == null) {
      config = ListTuningJobsConfig.builder().build();
    }
    return new Pager<>(
        Pager.PagedItem.TUNING_JOBS,
        request,
        (ObjectNode) JsonSerializable.toJsonNode(config),
        JsonSerializable.toJsonNode(privateList(config)));
  }

  /**
   * Makes an API request to create a supervised fine-tuning job.
   *
   * <p>This method is experimental.
   *
   * @param baseModel The base model to tune.
   * @param trainingDataset The training dataset to use for tuning.
   * @param config A {@link CreateTuningJobConfig} for configuring the create request.
   * @return A {@link TuningJob} object.
   */
  public TuningJob tune(
      String baseModel, TuningDataset trainingDataset, CreateTuningJobConfig config) {
    if (this.apiClient.vertexAI()) {
      if (baseModel.startsWith("projects/")) {
        PreTunedModel preTunedModel = PreTunedModel.builder().tunedModelName(baseModel).build();
        return this.privateTune(null, preTunedModel, trainingDataset, config);
      } else {
        return this.privateTune(baseModel, null, trainingDataset, config);
      }
    } else {
      TuningOperation operation = this.privateTuneMldev(baseModel, null, trainingDataset, config);
      String tunedModelName = "";
      if (operation.metadata().isPresent()
          && operation.metadata().get().containsKey("tunedModel")) {
        tunedModelName = (String) operation.metadata().get().get("tunedModel");
      } else {
        if (!operation.name().isPresent()) {
          throw new IllegalArgumentException("Operation name is required.");
        }
        tunedModelName = operation.name().get().split("/operations/")[0];
      }
      return TuningJob.builder()
          .name(tunedModelName)
          .state(JobState.Known.JOB_STATE_QUEUED)
          .build();
    }
  }
}
