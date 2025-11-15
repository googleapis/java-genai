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
import com.google.genai.Common.BuiltRequest;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.CancelTuningJobConfig;
import com.google.genai.types.CancelTuningJobParameters;
import com.google.genai.types.CreateTuningJobConfig;
import com.google.genai.types.CreateTuningJobParametersPrivate;
import com.google.genai.types.GetTuningJobConfig;
import com.google.genai.types.GetTuningJobParameters;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.HttpResponse;
import com.google.genai.types.JobState;
import com.google.genai.types.ListTuningJobsConfig;
import com.google.genai.types.ListTuningJobsParameters;
import com.google.genai.types.ListTuningJobsResponse;
import com.google.genai.types.PreTunedModel;
import com.google.genai.types.TuningDataset;
import com.google.genai.types.TuningJob;
import com.google.genai.types.TuningOperation;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import okhttp3.Headers;
import okhttp3.ResponseBody;

public final class Tunings {

  final ApiClient apiClient;

  public Tunings(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode cancelTuningJobParametersToMldev(
      JsonNode fromObject, ObjectNode parentObject, JsonNode rootObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "name"},
          Common.getValueByPath(fromObject, new String[] {"name"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode cancelTuningJobParametersToVertex(
      JsonNode fromObject, ObjectNode parentObject, JsonNode rootObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "name"},
          Common.getValueByPath(fromObject, new String[] {"name"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode createTuningJobConfigToMldev(
      JsonNode fromObject, ObjectNode parentObject, JsonNode rootObject) {
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

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"evaluationConfig"}))) {
      throw new IllegalArgumentException(
          "evaluationConfig parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"labels"}))) {
      throw new IllegalArgumentException("labels parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"beta"}))) {
      throw new IllegalArgumentException("beta parameter is not supported in Gemini API.");
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode createTuningJobConfigToVertex(
      JsonNode fromObject, ObjectNode parentObject, JsonNode rootObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    JsonNode discriminatorValidationDataset =
        (JsonNode) Common.getValueByPath(rootObject, new String[] {"config", "method"});
    String discriminatorValueValidationDataset =
        discriminatorValidationDataset == null
            ? "SUPERVISED_FINE_TUNING"
            : discriminatorValidationDataset.asText();
    if (discriminatorValueValidationDataset.equals("SUPERVISED_FINE_TUNING")) {
      if (Common.getValueByPath(fromObject, new String[] {"validationDataset"}) != null) {
        Common.setValueByPath(
            parentObject,
            new String[] {"supervisedTuningSpec"},
            tuningValidationDatasetToVertex(
                JsonSerializable.toJsonNode(
                    Common.getValueByPath(fromObject, new String[] {"validationDataset"})),
                toObject,
                rootObject));
      }
    } else if (discriminatorValueValidationDataset.equals("PREFERENCE_TUNING")) {
      if (Common.getValueByPath(fromObject, new String[] {"validationDataset"}) != null) {
        Common.setValueByPath(
            parentObject,
            new String[] {"preferenceOptimizationSpec"},
            tuningValidationDatasetToVertex(
                JsonSerializable.toJsonNode(
                    Common.getValueByPath(fromObject, new String[] {"validationDataset"})),
                toObject,
                rootObject));
      }
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

    JsonNode discriminatorEpochCount =
        (JsonNode) Common.getValueByPath(rootObject, new String[] {"config", "method"});
    String discriminatorValueEpochCount =
        discriminatorEpochCount == null
            ? "SUPERVISED_FINE_TUNING"
            : discriminatorEpochCount.asText();
    if (discriminatorValueEpochCount.equals("SUPERVISED_FINE_TUNING")) {
      if (Common.getValueByPath(fromObject, new String[] {"epochCount"}) != null) {
        Common.setValueByPath(
            parentObject,
            new String[] {"supervisedTuningSpec", "hyperParameters", "epochCount"},
            Common.getValueByPath(fromObject, new String[] {"epochCount"}));
      }
    } else if (discriminatorValueEpochCount.equals("PREFERENCE_TUNING")) {
      if (Common.getValueByPath(fromObject, new String[] {"epochCount"}) != null) {
        Common.setValueByPath(
            parentObject,
            new String[] {"preferenceOptimizationSpec", "hyperParameters", "epochCount"},
            Common.getValueByPath(fromObject, new String[] {"epochCount"}));
      }
    }

    JsonNode discriminatorLearningRateMultiplier =
        (JsonNode) Common.getValueByPath(rootObject, new String[] {"config", "method"});
    String discriminatorValueLearningRateMultiplier =
        discriminatorLearningRateMultiplier == null
            ? "SUPERVISED_FINE_TUNING"
            : discriminatorLearningRateMultiplier.asText();
    if (discriminatorValueLearningRateMultiplier.equals("SUPERVISED_FINE_TUNING")) {
      if (Common.getValueByPath(fromObject, new String[] {"learningRateMultiplier"}) != null) {
        Common.setValueByPath(
            parentObject,
            new String[] {"supervisedTuningSpec", "hyperParameters", "learningRateMultiplier"},
            Common.getValueByPath(fromObject, new String[] {"learningRateMultiplier"}));
      }
    } else if (discriminatorValueLearningRateMultiplier.equals("PREFERENCE_TUNING")) {
      if (Common.getValueByPath(fromObject, new String[] {"learningRateMultiplier"}) != null) {
        Common.setValueByPath(
            parentObject,
            new String[] {
              "preferenceOptimizationSpec", "hyperParameters", "learningRateMultiplier"
            },
            Common.getValueByPath(fromObject, new String[] {"learningRateMultiplier"}));
      }
    }

    JsonNode discriminatorExportLastCheckpointOnly =
        (JsonNode) Common.getValueByPath(rootObject, new String[] {"config", "method"});
    String discriminatorValueExportLastCheckpointOnly =
        discriminatorExportLastCheckpointOnly == null
            ? "SUPERVISED_FINE_TUNING"
            : discriminatorExportLastCheckpointOnly.asText();
    if (discriminatorValueExportLastCheckpointOnly.equals("SUPERVISED_FINE_TUNING")) {
      if (Common.getValueByPath(fromObject, new String[] {"exportLastCheckpointOnly"}) != null) {
        Common.setValueByPath(
            parentObject,
            new String[] {"supervisedTuningSpec", "exportLastCheckpointOnly"},
            Common.getValueByPath(fromObject, new String[] {"exportLastCheckpointOnly"}));
      }
    } else if (discriminatorValueExportLastCheckpointOnly.equals("PREFERENCE_TUNING")) {
      if (Common.getValueByPath(fromObject, new String[] {"exportLastCheckpointOnly"}) != null) {
        Common.setValueByPath(
            parentObject,
            new String[] {"preferenceOptimizationSpec", "exportLastCheckpointOnly"},
            Common.getValueByPath(fromObject, new String[] {"exportLastCheckpointOnly"}));
      }
    }

    JsonNode discriminatorAdapterSize =
        (JsonNode) Common.getValueByPath(rootObject, new String[] {"config", "method"});
    String discriminatorValueAdapterSize =
        discriminatorAdapterSize == null
            ? "SUPERVISED_FINE_TUNING"
            : discriminatorAdapterSize.asText();
    if (discriminatorValueAdapterSize.equals("SUPERVISED_FINE_TUNING")) {
      if (Common.getValueByPath(fromObject, new String[] {"adapterSize"}) != null) {
        Common.setValueByPath(
            parentObject,
            new String[] {"supervisedTuningSpec", "hyperParameters", "adapterSize"},
            Common.getValueByPath(fromObject, new String[] {"adapterSize"}));
      }
    } else if (discriminatorValueAdapterSize.equals("PREFERENCE_TUNING")) {
      if (Common.getValueByPath(fromObject, new String[] {"adapterSize"}) != null) {
        Common.setValueByPath(
            parentObject,
            new String[] {"preferenceOptimizationSpec", "hyperParameters", "adapterSize"},
            Common.getValueByPath(fromObject, new String[] {"adapterSize"}));
      }
    }
    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"batchSize"}))) {
      throw new IllegalArgumentException("batchSize parameter is not supported in Vertex AI.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"learningRate"}))) {
      throw new IllegalArgumentException("learningRate parameter is not supported in Vertex AI.");
    }

    JsonNode discriminatorEvaluationConfig =
        (JsonNode) Common.getValueByPath(rootObject, new String[] {"config", "method"});
    String discriminatorValueEvaluationConfig =
        discriminatorEvaluationConfig == null
            ? "SUPERVISED_FINE_TUNING"
            : discriminatorEvaluationConfig.asText();
    if (discriminatorValueEvaluationConfig.equals("SUPERVISED_FINE_TUNING")) {
      if (Common.getValueByPath(fromObject, new String[] {"evaluationConfig"}) != null) {
        Common.setValueByPath(
            parentObject,
            new String[] {"supervisedTuningSpec", "evaluationConfig"},
            evaluationConfigToVertex(
                JsonSerializable.toJsonNode(
                    Common.getValueByPath(fromObject, new String[] {"evaluationConfig"})),
                toObject,
                rootObject));
      }
    } else if (discriminatorValueEvaluationConfig.equals("PREFERENCE_TUNING")) {
      if (Common.getValueByPath(fromObject, new String[] {"evaluationConfig"}) != null) {
        Common.setValueByPath(
            parentObject,
            new String[] {"preferenceOptimizationSpec", "evaluationConfig"},
            evaluationConfigToVertex(
                JsonSerializable.toJsonNode(
                    Common.getValueByPath(fromObject, new String[] {"evaluationConfig"})),
                toObject,
                rootObject));
      }
    }
    if (Common.getValueByPath(fromObject, new String[] {"labels"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"labels"},
          Common.getValueByPath(fromObject, new String[] {"labels"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"beta"}) != null) {
      Common.setValueByPath(
          parentObject,
          new String[] {"preferenceOptimizationSpec", "hyperParameters", "beta"},
          Common.getValueByPath(fromObject, new String[] {"beta"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode createTuningJobParametersPrivateToMldev(
      JsonNode fromObject, ObjectNode parentObject, JsonNode rootObject) {
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
      JsonNode unused =
          tuningDatasetToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"trainingDataset"})),
              toObject,
              rootObject);
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          createTuningJobConfigToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject,
              rootObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode createTuningJobParametersPrivateToVertex(
      JsonNode fromObject, ObjectNode parentObject, JsonNode rootObject) {
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
      JsonNode unused =
          tuningDatasetToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"trainingDataset"})),
              toObject,
              rootObject);
    }

    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          createTuningJobConfigToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject,
              rootObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode evaluationConfigFromVertex(JsonNode fromObject, ObjectNode parentObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"metrics"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"metrics"},
          Transformers.tMetrics(Common.getValueByPath(fromObject, new String[] {"metrics"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"outputConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"outputConfig"},
          Common.getValueByPath(fromObject, new String[] {"outputConfig"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"autoraterConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"autoraterConfig"},
          Common.getValueByPath(fromObject, new String[] {"autoraterConfig"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode evaluationConfigToVertex(
      JsonNode fromObject, ObjectNode parentObject, JsonNode rootObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"metrics"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"metrics"},
          Transformers.tMetrics(Common.getValueByPath(fromObject, new String[] {"metrics"})));
    }

    if (Common.getValueByPath(fromObject, new String[] {"outputConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"outputConfig"},
          Common.getValueByPath(fromObject, new String[] {"outputConfig"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"autoraterConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"autoraterConfig"},
          Common.getValueByPath(fromObject, new String[] {"autoraterConfig"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode getTuningJobParametersToMldev(
      JsonNode fromObject, ObjectNode parentObject, JsonNode rootObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "name"},
          Common.getValueByPath(fromObject, new String[] {"name"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode getTuningJobParametersToVertex(
      JsonNode fromObject, ObjectNode parentObject, JsonNode rootObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"name"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"_url", "name"},
          Common.getValueByPath(fromObject, new String[] {"name"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode listTuningJobsConfigToMldev(
      JsonNode fromObject, ObjectNode parentObject, JsonNode rootObject) {
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
  ObjectNode listTuningJobsConfigToVertex(
      JsonNode fromObject, ObjectNode parentObject, JsonNode rootObject) {
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
  ObjectNode listTuningJobsParametersToMldev(
      JsonNode fromObject, ObjectNode parentObject, JsonNode rootObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          listTuningJobsConfigToMldev(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject,
              rootObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode listTuningJobsParametersToVertex(
      JsonNode fromObject, ObjectNode parentObject, JsonNode rootObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"config"}) != null) {
      JsonNode unused =
          listTuningJobsConfigToVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"config"})),
              toObject,
              rootObject);
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode listTuningJobsResponseFromMldev(JsonNode fromObject, ObjectNode parentObject) {
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
  ObjectNode listTuningJobsResponseFromVertex(JsonNode fromObject, ObjectNode parentObject) {
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
  ObjectNode tuningDatasetToMldev(
      JsonNode fromObject, ObjectNode parentObject, JsonNode rootObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"gcsUri"}))) {
      throw new IllegalArgumentException("gcsUri parameter is not supported in Gemini API.");
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"vertexDatasetResource"}))) {
      throw new IllegalArgumentException(
          "vertexDatasetResource parameter is not supported in Gemini API.");
    }

    if (Common.getValueByPath(fromObject, new String[] {"examples"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"examples", "examples"},
          Common.getValueByPath(fromObject, new String[] {"examples"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode tuningDatasetToVertex(
      JsonNode fromObject, ObjectNode parentObject, JsonNode rootObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();

    JsonNode discriminatorGcsUri =
        (JsonNode) Common.getValueByPath(rootObject, new String[] {"config", "method"});
    String discriminatorValueGcsUri =
        discriminatorGcsUri == null ? "SUPERVISED_FINE_TUNING" : discriminatorGcsUri.asText();
    if (discriminatorValueGcsUri.equals("SUPERVISED_FINE_TUNING")) {
      if (Common.getValueByPath(fromObject, new String[] {"gcsUri"}) != null) {
        Common.setValueByPath(
            parentObject,
            new String[] {"supervisedTuningSpec", "trainingDatasetUri"},
            Common.getValueByPath(fromObject, new String[] {"gcsUri"}));
      }
    } else if (discriminatorValueGcsUri.equals("PREFERENCE_TUNING")) {
      if (Common.getValueByPath(fromObject, new String[] {"gcsUri"}) != null) {
        Common.setValueByPath(
            parentObject,
            new String[] {"preferenceOptimizationSpec", "trainingDatasetUri"},
            Common.getValueByPath(fromObject, new String[] {"gcsUri"}));
      }
    }

    JsonNode discriminatorVertexDatasetResource =
        (JsonNode) Common.getValueByPath(rootObject, new String[] {"config", "method"});
    String discriminatorValueVertexDatasetResource =
        discriminatorVertexDatasetResource == null
            ? "SUPERVISED_FINE_TUNING"
            : discriminatorVertexDatasetResource.asText();
    if (discriminatorValueVertexDatasetResource.equals("SUPERVISED_FINE_TUNING")) {
      if (Common.getValueByPath(fromObject, new String[] {"vertexDatasetResource"}) != null) {
        Common.setValueByPath(
            parentObject,
            new String[] {"supervisedTuningSpec", "trainingDatasetUri"},
            Common.getValueByPath(fromObject, new String[] {"vertexDatasetResource"}));
      }
    } else if (discriminatorValueVertexDatasetResource.equals("PREFERENCE_TUNING")) {
      if (Common.getValueByPath(fromObject, new String[] {"vertexDatasetResource"}) != null) {
        Common.setValueByPath(
            parentObject,
            new String[] {"preferenceOptimizationSpec", "trainingDatasetUri"},
            Common.getValueByPath(fromObject, new String[] {"vertexDatasetResource"}));
      }
    }
    if (!Common.isZero(Common.getValueByPath(fromObject, new String[] {"examples"}))) {
      throw new IllegalArgumentException("examples parameter is not supported in Vertex AI.");
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode tuningJobFromMldev(JsonNode fromObject, ObjectNode parentObject) {
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

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode tuningJobFromVertex(JsonNode fromObject, ObjectNode parentObject) {
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
          Common.getValueByPath(fromObject, new String[] {"tunedModel"}));
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

    if (Common.getValueByPath(fromObject, new String[] {"preferenceOptimizationSpec"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"preferenceOptimizationSpec"},
          Common.getValueByPath(fromObject, new String[] {"preferenceOptimizationSpec"}));
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

    if (Common.getValueByPath(fromObject, new String[] {"evaluationConfig"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"evaluationConfig"},
          evaluationConfigFromVertex(
              JsonSerializable.toJsonNode(
                  Common.getValueByPath(fromObject, new String[] {"evaluationConfig"})),
              toObject));
    }

    if (Common.getValueByPath(fromObject, new String[] {"customBaseModel"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"customBaseModel"},
          Common.getValueByPath(fromObject, new String[] {"customBaseModel"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"evaluateDatasetRuns"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"evaluateDatasetRuns"},
          Common.getValueByPath(fromObject, new String[] {"evaluateDatasetRuns"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"experiment"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"experiment"},
          Common.getValueByPath(fromObject, new String[] {"experiment"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"fullFineTuningSpec"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"fullFineTuningSpec"},
          Common.getValueByPath(fromObject, new String[] {"fullFineTuningSpec"}));
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

    if (Common.getValueByPath(fromObject, new String[] {"tuningJobState"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"tuningJobState"},
          Common.getValueByPath(fromObject, new String[] {"tuningJobState"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"veoTuningSpec"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"veoTuningSpec"},
          Common.getValueByPath(fromObject, new String[] {"veoTuningSpec"}));
    }

    return toObject;
  }

  @ExcludeFromGeneratedCoverageReport
  ObjectNode tuningOperationFromMldev(JsonNode fromObject, ObjectNode parentObject) {
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
  ObjectNode tuningValidationDatasetToVertex(
      JsonNode fromObject, ObjectNode parentObject, JsonNode rootObject) {
    ObjectNode toObject = JsonSerializable.objectMapper.createObjectNode();
    if (Common.getValueByPath(fromObject, new String[] {"gcsUri"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"validationDatasetUri"},
          Common.getValueByPath(fromObject, new String[] {"gcsUri"}));
    }

    if (Common.getValueByPath(fromObject, new String[] {"vertexDatasetResource"}) != null) {
      Common.setValueByPath(
          toObject,
          new String[] {"validationDatasetUri"},
          Common.getValueByPath(fromObject, new String[] {"vertexDatasetResource"}));
    }

    return toObject;
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForPrivateGet(String name, GetTuningJobConfig config) {

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
      body = getTuningJobParametersToVertex(parameterNode, null, parameterNode);
      path = Common.formatMap("{name}", body.get("_url"));
    } else {
      body = getTuningJobParametersToMldev(parameterNode, null, parameterNode);
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
  TuningJob processResponseForPrivateGet(ApiResponse response, GetTuningJobConfig config) {
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
    }

    if (!this.apiClient.vertexAI()) {
      responseNode = tuningJobFromMldev(responseNode, null);
    }

    TuningJob sdkResponse = JsonSerializable.fromJsonNode(responseNode, TuningJob.class);
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

  TuningJob privateGet(String name, GetTuningJobConfig config) {
    BuiltRequest builtRequest = buildRequestForPrivateGet(name, config);

    try (ApiResponse response =
        this.apiClient.request(
            "get", builtRequest.path, builtRequest.body, builtRequest.httpOptions)) {
      return processResponseForPrivateGet(response, config);
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForPrivateList(ListTuningJobsConfig config) {

    ListTuningJobsParameters.Builder parameterBuilder = ListTuningJobsParameters.builder();

    if (!Common.isZero(config)) {
      parameterBuilder.config(config);
    }
    JsonNode parameterNode = JsonSerializable.toJsonNode(parameterBuilder.build());

    ObjectNode body;
    String path;
    if (this.apiClient.vertexAI()) {
      body = listTuningJobsParametersToVertex(parameterNode, null, parameterNode);
      path = Common.formatMap("tuningJobs", body.get("_url"));
    } else {
      body = listTuningJobsParametersToMldev(parameterNode, null, parameterNode);
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
    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    return new BuiltRequest(path, JsonSerializable.toJsonString(body), requestHttpOptions);
  }

  /** A shared processResponse function for both sync and async methods. */
  ListTuningJobsResponse processResponseForPrivateList(
      ApiResponse response, ListTuningJobsConfig config) {
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
    }

    if (!this.apiClient.vertexAI()) {
      responseNode = listTuningJobsResponseFromMldev(responseNode, null);
    }

    ListTuningJobsResponse sdkResponse =
        JsonSerializable.fromJsonNode(responseNode, ListTuningJobsResponse.class);
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

  ListTuningJobsResponse privateList(ListTuningJobsConfig config) {
    BuiltRequest builtRequest = buildRequestForPrivateList(config);

    try (ApiResponse response =
        this.apiClient.request(
            "get", builtRequest.path, builtRequest.body, builtRequest.httpOptions)) {
      return processResponseForPrivateList(response, config);
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForCancel(String name, CancelTuningJobConfig config) {

    CancelTuningJobParameters.Builder parameterBuilder = CancelTuningJobParameters.builder();

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
      body = cancelTuningJobParametersToVertex(parameterNode, null, parameterNode);
      path = Common.formatMap("{name}:cancel", body.get("_url"));
    } else {
      body = cancelTuningJobParametersToMldev(parameterNode, null, parameterNode);
      if (body.get("_url") != null) {
        path = Common.formatMap("{name}:cancel", body.get("_url"));
      } else {
        path = "{name}:cancel";
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
   * Cancels a tuning job resource.
   *
   * @param name The resource name of the tuning job. For Vertex, this is the full resource name.
   *     For Gemini API, this is `tunedModels/{id}`.
   * @param config A {@link CancelTuningJobConfig} for configuring the cancel request.
   */
  public void cancel(String name, CancelTuningJobConfig config) {
    BuiltRequest builtRequest = buildRequestForCancel(name, config);

    try (ApiResponse response =
        this.apiClient.request(
            "post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)) {
      return;
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForPrivateTune(
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
      body = createTuningJobParametersPrivateToVertex(parameterNode, null, parameterNode);
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
    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    return new BuiltRequest(path, JsonSerializable.toJsonString(body), requestHttpOptions);
  }

  /** A shared processResponse function for both sync and async methods. */
  TuningJob processResponseForPrivateTune(ApiResponse response, CreateTuningJobConfig config) {
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
    }

    if (!this.apiClient.vertexAI()) {
      throw new UnsupportedOperationException(
          "This method is only supported in the Vertex AI client.");
    }

    TuningJob sdkResponse = JsonSerializable.fromJsonNode(responseNode, TuningJob.class);
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

  TuningJob privateTune(
      String baseModel,
      PreTunedModel preTunedModel,
      TuningDataset trainingDataset,
      CreateTuningJobConfig config) {
    BuiltRequest builtRequest =
        buildRequestForPrivateTune(baseModel, preTunedModel, trainingDataset, config);

    try (ApiResponse response =
        this.apiClient.request(
            "post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)) {
      return processResponseForPrivateTune(response, config);
    }
  }

  /** A shared buildRequest method for both sync and async methods. */
  BuiltRequest buildRequestForPrivateTuneMldev(
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
      body = createTuningJobParametersPrivateToMldev(parameterNode, null, parameterNode);
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
    Optional<HttpOptions> requestHttpOptions = Optional.empty();
    if (config != null) {
      requestHttpOptions = config.httpOptions();
    }

    return new BuiltRequest(path, JsonSerializable.toJsonString(body), requestHttpOptions);
  }

  /** A shared processResponse function for both sync and async methods. */
  TuningOperation processResponseForPrivateTuneMldev(
      ApiResponse response, CreateTuningJobConfig config) {
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
      responseNode = tuningOperationFromMldev(responseNode, null);
    }

    TuningOperation sdkResponse =
        JsonSerializable.fromJsonNode(responseNode, TuningOperation.class);
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

  TuningOperation privateTuneMldev(
      String baseModel,
      PreTunedModel preTunedModel,
      TuningDataset trainingDataset,
      CreateTuningJobConfig config) {
    BuiltRequest builtRequest =
        buildRequestForPrivateTuneMldev(baseModel, preTunedModel, trainingDataset, config);

    try (ApiResponse response =
        this.apiClient.request(
            "post", builtRequest.path, builtRequest.body, builtRequest.httpOptions)) {
      return processResponseForPrivateTuneMldev(response, config);
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
      TuningJob tuningJob;
      if (baseModel.startsWith("projects/")) {
        PreTunedModel.Builder preTunedModelBuilder =
            PreTunedModel.builder().tunedModelName(baseModel);
        if (config != null && config.preTunedModelCheckpointId().isPresent()) {
          preTunedModelBuilder.checkpointId(config.preTunedModelCheckpointId().get());
        }
        tuningJob = this.privateTune(null, preTunedModelBuilder.build(), trainingDataset, config);
      } else {
        tuningJob = this.privateTune(baseModel, null, trainingDataset, config);
      }
      if (config != null && config.evaluationConfig().isPresent()) {
        tuningJob = tuningJob.toBuilder().evaluationConfig(config.evaluationConfig().get()).build();
      }
      return tuningJob;
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
