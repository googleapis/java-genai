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

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Map;
import java.util.Optional;

/** Fine-tuning job creation request - optional fields. */
@AutoValue
@JsonDeserialize(builder = CreateTuningJobConfig.Builder.class)
public abstract class CreateTuningJobConfig extends JsonSerializable {
  /** Used to override HTTP request options. */
  @JsonProperty("httpOptions")
  public abstract Optional<HttpOptions> httpOptions();

  /**
   * The method to use for tuning (SUPERVISED_FINE_TUNING or PREFERENCE_TUNING). If not set, the
   * default method (SFT) will be used.
   */
  @JsonProperty("method")
  public abstract Optional<TuningMethod> method();

  /** Validation dataset for tuning. The dataset must be formatted as a JSONL file. */
  @JsonProperty("validationDataset")
  public abstract Optional<TuningValidationDataset> validationDataset();

  /**
   * The display name of the tuned Model. The name can be up to 128 characters long and can consist
   * of any UTF-8 characters.
   */
  @JsonProperty("tunedModelDisplayName")
  public abstract Optional<String> tunedModelDisplayName();

  /** The description of the TuningJob */
  @JsonProperty("description")
  public abstract Optional<String> description();

  /** Number of complete passes the model makes over the entire training dataset during training. */
  @JsonProperty("epochCount")
  public abstract Optional<Integer> epochCount();

  /** Multiplier for adjusting the default learning rate. */
  @JsonProperty("learningRateMultiplier")
  public abstract Optional<Float> learningRateMultiplier();

  /**
   * If set to true, disable intermediate checkpoints and only the last checkpoint will be exported.
   * Otherwise, enable intermediate checkpoints.
   */
  @JsonProperty("exportLastCheckpointOnly")
  public abstract Optional<Boolean> exportLastCheckpointOnly();

  /** The optional checkpoint id of the pre-tuned model to use for tuning, if applicable. */
  @JsonProperty("preTunedModelCheckpointId")
  public abstract Optional<String> preTunedModelCheckpointId();

  /** Adapter size for tuning. */
  @JsonProperty("adapterSize")
  public abstract Optional<AdapterSize> adapterSize();

  /**
   * The batch size hyperparameter for tuning. If not set, a default of 4 or 16 will be used based
   * on the number of training examples.
   */
  @JsonProperty("batchSize")
  public abstract Optional<Integer> batchSize();

  /**
   * The learning rate hyperparameter for tuning. If not set, a default of 0.001 or 0.0002 will be
   * calculated based on the number of training examples.
   */
  @JsonProperty("learningRate")
  public abstract Optional<Float> learningRate();

  /**
   * Optional. The labels with user-defined metadata to organize TuningJob and generated resources
   * such as Model and Endpoint. Label keys and values can be no longer than 64 characters (Unicode
   * codepoints), can only contain lowercase letters, numeric characters, underscores and dashes.
   * International characters are allowed. See https://goo.gl/xmQnxf for more information and
   * examples of labels.
   */
  @JsonProperty("labels")
  public abstract Optional<Map<String, String>> labels();

  /** Weight for KL Divergence regularization, Preference Optimization tuning only. */
  @JsonProperty("beta")
  public abstract Optional<Float> beta();

  /** Instantiates a builder for CreateTuningJobConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_CreateTuningJobConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for CreateTuningJobConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `CreateTuningJobConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_CreateTuningJobConfig.Builder();
    }

    /**
     * Setter for httpOptions.
     *
     * <p>httpOptions: Used to override HTTP request options.
     */
    @JsonProperty("httpOptions")
    public abstract Builder httpOptions(HttpOptions httpOptions);

    /**
     * Setter for httpOptions builder.
     *
     * <p>httpOptions: Used to override HTTP request options.
     */
    @CanIgnoreReturnValue
    public Builder httpOptions(HttpOptions.Builder httpOptionsBuilder) {
      return httpOptions(httpOptionsBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder httpOptions(Optional<HttpOptions> httpOptions);

    /** Clears the value of httpOptions field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearHttpOptions() {
      return httpOptions(Optional.empty());
    }

    /**
     * Setter for method.
     *
     * <p>method: The method to use for tuning (SUPERVISED_FINE_TUNING or PREFERENCE_TUNING). If not
     * set, the default method (SFT) will be used.
     */
    @JsonProperty("method")
    public abstract Builder method(TuningMethod method);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder method(Optional<TuningMethod> method);

    /** Clears the value of method field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMethod() {
      return method(Optional.empty());
    }

    /**
     * Setter for method given a known enum.
     *
     * <p>method: The method to use for tuning (SUPERVISED_FINE_TUNING or PREFERENCE_TUNING). If not
     * set, the default method (SFT) will be used.
     */
    @CanIgnoreReturnValue
    public Builder method(TuningMethod.Known knownType) {
      return method(new TuningMethod(knownType));
    }

    /**
     * Setter for method given a string.
     *
     * <p>method: The method to use for tuning (SUPERVISED_FINE_TUNING or PREFERENCE_TUNING). If not
     * set, the default method (SFT) will be used.
     */
    @CanIgnoreReturnValue
    public Builder method(String method) {
      return method(new TuningMethod(method));
    }

    /**
     * Setter for validationDataset.
     *
     * <p>validationDataset: Validation dataset for tuning. The dataset must be formatted as a JSONL
     * file.
     */
    @JsonProperty("validationDataset")
    public abstract Builder validationDataset(TuningValidationDataset validationDataset);

    /**
     * Setter for validationDataset builder.
     *
     * <p>validationDataset: Validation dataset for tuning. The dataset must be formatted as a JSONL
     * file.
     */
    @CanIgnoreReturnValue
    public Builder validationDataset(TuningValidationDataset.Builder validationDatasetBuilder) {
      return validationDataset(validationDatasetBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder validationDataset(Optional<TuningValidationDataset> validationDataset);

    /** Clears the value of validationDataset field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearValidationDataset() {
      return validationDataset(Optional.empty());
    }

    /**
     * Setter for tunedModelDisplayName.
     *
     * <p>tunedModelDisplayName: The display name of the tuned Model. The name can be up to 128
     * characters long and can consist of any UTF-8 characters.
     */
    @JsonProperty("tunedModelDisplayName")
    public abstract Builder tunedModelDisplayName(String tunedModelDisplayName);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder tunedModelDisplayName(Optional<String> tunedModelDisplayName);

    /** Clears the value of tunedModelDisplayName field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTunedModelDisplayName() {
      return tunedModelDisplayName(Optional.empty());
    }

    /**
     * Setter for description.
     *
     * <p>description: The description of the TuningJob
     */
    @JsonProperty("description")
    public abstract Builder description(String description);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder description(Optional<String> description);

    /** Clears the value of description field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDescription() {
      return description(Optional.empty());
    }

    /**
     * Setter for epochCount.
     *
     * <p>epochCount: Number of complete passes the model makes over the entire training dataset
     * during training.
     */
    @JsonProperty("epochCount")
    public abstract Builder epochCount(Integer epochCount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder epochCount(Optional<Integer> epochCount);

    /** Clears the value of epochCount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEpochCount() {
      return epochCount(Optional.empty());
    }

    /**
     * Setter for learningRateMultiplier.
     *
     * <p>learningRateMultiplier: Multiplier for adjusting the default learning rate.
     */
    @JsonProperty("learningRateMultiplier")
    public abstract Builder learningRateMultiplier(Float learningRateMultiplier);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder learningRateMultiplier(Optional<Float> learningRateMultiplier);

    /** Clears the value of learningRateMultiplier field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearLearningRateMultiplier() {
      return learningRateMultiplier(Optional.empty());
    }

    /**
     * Setter for exportLastCheckpointOnly.
     *
     * <p>exportLastCheckpointOnly: If set to true, disable intermediate checkpoints and only the
     * last checkpoint will be exported. Otherwise, enable intermediate checkpoints.
     */
    @JsonProperty("exportLastCheckpointOnly")
    public abstract Builder exportLastCheckpointOnly(boolean exportLastCheckpointOnly);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder exportLastCheckpointOnly(Optional<Boolean> exportLastCheckpointOnly);

    /** Clears the value of exportLastCheckpointOnly field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearExportLastCheckpointOnly() {
      return exportLastCheckpointOnly(Optional.empty());
    }

    /**
     * Setter for preTunedModelCheckpointId.
     *
     * <p>preTunedModelCheckpointId: The optional checkpoint id of the pre-tuned model to use for
     * tuning, if applicable.
     */
    @JsonProperty("preTunedModelCheckpointId")
    public abstract Builder preTunedModelCheckpointId(String preTunedModelCheckpointId);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder preTunedModelCheckpointId(Optional<String> preTunedModelCheckpointId);

    /** Clears the value of preTunedModelCheckpointId field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPreTunedModelCheckpointId() {
      return preTunedModelCheckpointId(Optional.empty());
    }

    /**
     * Setter for adapterSize.
     *
     * <p>adapterSize: Adapter size for tuning.
     */
    @JsonProperty("adapterSize")
    public abstract Builder adapterSize(AdapterSize adapterSize);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder adapterSize(Optional<AdapterSize> adapterSize);

    /** Clears the value of adapterSize field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAdapterSize() {
      return adapterSize(Optional.empty());
    }

    /**
     * Setter for adapterSize given a known enum.
     *
     * <p>adapterSize: Adapter size for tuning.
     */
    @CanIgnoreReturnValue
    public Builder adapterSize(AdapterSize.Known knownType) {
      return adapterSize(new AdapterSize(knownType));
    }

    /**
     * Setter for adapterSize given a string.
     *
     * <p>adapterSize: Adapter size for tuning.
     */
    @CanIgnoreReturnValue
    public Builder adapterSize(String adapterSize) {
      return adapterSize(new AdapterSize(adapterSize));
    }

    /**
     * Setter for batchSize.
     *
     * <p>batchSize: The batch size hyperparameter for tuning. If not set, a default of 4 or 16 will
     * be used based on the number of training examples.
     */
    @JsonProperty("batchSize")
    public abstract Builder batchSize(Integer batchSize);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder batchSize(Optional<Integer> batchSize);

    /** Clears the value of batchSize field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearBatchSize() {
      return batchSize(Optional.empty());
    }

    /**
     * Setter for learningRate.
     *
     * <p>learningRate: The learning rate hyperparameter for tuning. If not set, a default of 0.001
     * or 0.0002 will be calculated based on the number of training examples.
     */
    @JsonProperty("learningRate")
    public abstract Builder learningRate(Float learningRate);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder learningRate(Optional<Float> learningRate);

    /** Clears the value of learningRate field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearLearningRate() {
      return learningRate(Optional.empty());
    }

    /**
     * Setter for labels.
     *
     * <p>labels: Optional. The labels with user-defined metadata to organize TuningJob and
     * generated resources such as Model and Endpoint. Label keys and values can be no longer than
     * 64 characters (Unicode codepoints), can only contain lowercase letters, numeric characters,
     * underscores and dashes. International characters are allowed. See https://goo.gl/xmQnxf for
     * more information and examples of labels.
     */
    @JsonProperty("labels")
    public abstract Builder labels(Map<String, String> labels);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder labels(Optional<Map<String, String>> labels);

    /** Clears the value of labels field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearLabels() {
      return labels(Optional.empty());
    }

    /**
     * Setter for beta.
     *
     * <p>beta: Weight for KL Divergence regularization, Preference Optimization tuning only.
     */
    @JsonProperty("beta")
    public abstract Builder beta(Float beta);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder beta(Optional<Float> beta);

    /** Clears the value of beta field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearBeta() {
      return beta(Optional.empty());
    }

    public abstract CreateTuningJobConfig build();
  }

  /** Deserializes a JSON string to a CreateTuningJobConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static CreateTuningJobConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, CreateTuningJobConfig.class);
  }
}
