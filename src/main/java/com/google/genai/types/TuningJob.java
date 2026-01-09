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

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** A tuning job. */
@AutoValue
@JsonDeserialize(builder = TuningJob.Builder.class)
public abstract class TuningJob extends JsonSerializable {
  /** Used to retain the full HTTP response. */
  @JsonProperty("sdkHttpResponse")
  public abstract Optional<HttpResponse> sdkHttpResponse();

  /**
   * Output only. Identifier. Resource name of a TuningJob. Format:
   * `projects/{project}/locations/{location}/tuningJobs/{tuning_job}`
   */
  @JsonProperty("name")
  public abstract Optional<String> name();

  /** Output only. The detailed state of the job. */
  @JsonProperty("state")
  public abstract Optional<JobState> state();

  /** Output only. Time when the TuningJob was created. */
  @JsonProperty("createTime")
  public abstract Optional<Instant> createTime();

  /**
   * Output only. Time when the TuningJob for the first time entered the `JOB_STATE_RUNNING` state.
   */
  @JsonProperty("startTime")
  public abstract Optional<Instant> startTime();

  /**
   * Output only. Time when the TuningJob entered any of the following JobStates:
   * `JOB_STATE_SUCCEEDED`, `JOB_STATE_FAILED`, `JOB_STATE_CANCELLED`, `JOB_STATE_EXPIRED`.
   */
  @JsonProperty("endTime")
  public abstract Optional<Instant> endTime();

  /** Output only. Time when the TuningJob was most recently updated. */
  @JsonProperty("updateTime")
  public abstract Optional<Instant> updateTime();

  /**
   * Output only. Only populated when job's state is `JOB_STATE_FAILED` or `JOB_STATE_CANCELLED`.
   */
  @JsonProperty("error")
  public abstract Optional<GoogleRpcStatus> error();

  /** Optional. The description of the TuningJob. */
  @JsonProperty("description")
  public abstract Optional<String> description();

  /**
   * The base model that is being tuned. See [Supported
   * models](https://cloud.google.com/vertex-ai/generative-ai/docs/model-reference/tuning#supported_models).
   */
  @JsonProperty("baseModel")
  public abstract Optional<String> baseModel();

  /** Output only. The tuned model resources associated with this TuningJob. */
  @JsonProperty("tunedModel")
  public abstract Optional<TunedModel> tunedModel();

  /** The pre-tuned model for continuous tuning. */
  @JsonProperty("preTunedModel")
  public abstract Optional<PreTunedModel> preTunedModel();

  /** Tuning Spec for Supervised Fine Tuning. */
  @JsonProperty("supervisedTuningSpec")
  public abstract Optional<SupervisedTuningSpec> supervisedTuningSpec();

  /** Tuning Spec for Preference Optimization. */
  @JsonProperty("preferenceOptimizationSpec")
  public abstract Optional<PreferenceOptimizationSpec> preferenceOptimizationSpec();

  /** Output only. The tuning data statistics associated with this TuningJob. */
  @JsonProperty("tuningDataStats")
  public abstract Optional<TuningDataStats> tuningDataStats();

  /**
   * Customer-managed encryption key options for a TuningJob. If this is set, then all resources
   * created by the TuningJob will be encrypted with the provided encryption key.
   */
  @JsonProperty("encryptionSpec")
  public abstract Optional<EncryptionSpec> encryptionSpec();

  /** Tuning Spec for open sourced and third party Partner models. */
  @JsonProperty("partnerModelTuningSpec")
  public abstract Optional<PartnerModelTuningSpec> partnerModelTuningSpec();

  /** Evaluation config for the tuning job. */
  @JsonProperty("evaluationConfig")
  public abstract Optional<EvaluationConfig> evaluationConfig();

  /**
   * Optional. The user-provided path to custom model weights. Set this field to tune a custom
   * model. The path must be a Cloud Storage directory that contains the model weights in
   * .safetensors format along with associated model metadata files. If this field is set, the
   * base_model field must still be set to indicate which base model the custom model is derived
   * from. This feature is only available for open source models.
   */
  @JsonProperty("customBaseModel")
  public abstract Optional<String> customBaseModel();

  /** Output only. Evaluation runs for the Tuning Job. */
  @JsonProperty("evaluateDatasetRuns")
  public abstract Optional<List<EvaluateDatasetRun>> evaluateDatasetRuns();

  /** Output only. The Experiment associated with this TuningJob. */
  @JsonProperty("experiment")
  public abstract Optional<String> experiment();

  /** Tuning Spec for Full Fine Tuning. */
  @JsonProperty("fullFineTuningSpec")
  public abstract Optional<FullFineTuningSpec> fullFineTuningSpec();

  /**
   * Optional. The labels with user-defined metadata to organize TuningJob and generated resources
   * such as Model and Endpoint. Label keys and values can be no longer than 64 characters (Unicode
   * codepoints), can only contain lowercase letters, numeric characters, underscores and dashes.
   * International characters are allowed. See https://goo.gl/xmQnxf for more information and
   * examples of labels.
   */
  @JsonProperty("labels")
  public abstract Optional<Map<String, String>> labels();

  /**
   * Optional. Cloud Storage path to the directory where tuning job outputs are written to. This
   * field is only available and required for open source models.
   */
  @JsonProperty("outputUri")
  public abstract Optional<String> outputUri();

  /**
   * Output only. The resource name of the PipelineJob associated with the TuningJob. Format:
   * `projects/{project}/locations/{location}/pipelineJobs/{pipeline_job}`.
   */
  @JsonProperty("pipelineJob")
  public abstract Optional<String> pipelineJob();

  /**
   * The service account that the tuningJob workload runs as. If not specified, the Vertex AI Secure
   * Fine-Tuned Service Agent in the project will be used. See
   * https://cloud.google.com/iam/docs/service-agents#vertex-ai-secure-fine-tuning-service-agent
   * Users starting the pipeline must have the `iam.serviceAccounts.actAs` permission on this
   * service account.
   */
  @JsonProperty("serviceAccount")
  public abstract Optional<String> serviceAccount();

  /**
   * Optional. The display name of the TunedModel. The name can be up to 128 characters long and can
   * consist of any UTF-8 characters. For continuous tuning, tuned_model_display_name will by
   * default use the same display name as the pre-tuned model. If a new display name is provided,
   * the tuning job will create a new model instead of a new version.
   */
  @JsonProperty("tunedModelDisplayName")
  public abstract Optional<String> tunedModelDisplayName();

  /** Output only. The detail state of the tuning job (while the overall `JobState` is running). */
  @JsonProperty("tuningJobState")
  public abstract Optional<TuningJobState> tuningJobState();

  /** Tuning Spec for Veo Tuning. */
  @JsonProperty("veoTuningSpec")
  public abstract Optional<VeoTuningSpec> veoTuningSpec();

  /** Instantiates a builder for TuningJob. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_TuningJob.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for TuningJob. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `TuningJob.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_TuningJob.Builder();
    }

    /**
     * Setter for sdkHttpResponse.
     *
     * <p>sdkHttpResponse: Used to retain the full HTTP response.
     */
    @JsonProperty("sdkHttpResponse")
    public abstract Builder sdkHttpResponse(HttpResponse sdkHttpResponse);

    /**
     * Setter for sdkHttpResponse builder.
     *
     * <p>sdkHttpResponse: Used to retain the full HTTP response.
     */
    @CanIgnoreReturnValue
    public Builder sdkHttpResponse(HttpResponse.Builder sdkHttpResponseBuilder) {
      return sdkHttpResponse(sdkHttpResponseBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder sdkHttpResponse(Optional<HttpResponse> sdkHttpResponse);

    /** Clears the value of sdkHttpResponse field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSdkHttpResponse() {
      return sdkHttpResponse(Optional.empty());
    }

    /**
     * Setter for name.
     *
     * <p>name: Output only. Identifier. Resource name of a TuningJob. Format:
     * `projects/{project}/locations/{location}/tuningJobs/{tuning_job}`
     */
    @JsonProperty("name")
    public abstract Builder name(String name);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder name(Optional<String> name);

    /** Clears the value of name field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearName() {
      return name(Optional.empty());
    }

    /**
     * Setter for state.
     *
     * <p>state: Output only. The detailed state of the job.
     */
    @JsonProperty("state")
    public abstract Builder state(JobState state);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder state(Optional<JobState> state);

    /** Clears the value of state field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearState() {
      return state(Optional.empty());
    }

    /**
     * Setter for state given a known enum.
     *
     * <p>state: Output only. The detailed state of the job.
     */
    @CanIgnoreReturnValue
    public Builder state(JobState.Known knownType) {
      return state(new JobState(knownType));
    }

    /**
     * Setter for state given a string.
     *
     * <p>state: Output only. The detailed state of the job.
     */
    @CanIgnoreReturnValue
    public Builder state(String state) {
      return state(new JobState(state));
    }

    /**
     * Setter for createTime.
     *
     * <p>createTime: Output only. Time when the TuningJob was created.
     */
    @JsonProperty("createTime")
    public abstract Builder createTime(Instant createTime);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder createTime(Optional<Instant> createTime);

    /** Clears the value of createTime field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCreateTime() {
      return createTime(Optional.empty());
    }

    /**
     * Setter for startTime.
     *
     * <p>startTime: Output only. Time when the TuningJob for the first time entered the
     * `JOB_STATE_RUNNING` state.
     */
    @JsonProperty("startTime")
    public abstract Builder startTime(Instant startTime);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder startTime(Optional<Instant> startTime);

    /** Clears the value of startTime field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearStartTime() {
      return startTime(Optional.empty());
    }

    /**
     * Setter for endTime.
     *
     * <p>endTime: Output only. Time when the TuningJob entered any of the following JobStates:
     * `JOB_STATE_SUCCEEDED`, `JOB_STATE_FAILED`, `JOB_STATE_CANCELLED`, `JOB_STATE_EXPIRED`.
     */
    @JsonProperty("endTime")
    public abstract Builder endTime(Instant endTime);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder endTime(Optional<Instant> endTime);

    /** Clears the value of endTime field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEndTime() {
      return endTime(Optional.empty());
    }

    /**
     * Setter for updateTime.
     *
     * <p>updateTime: Output only. Time when the TuningJob was most recently updated.
     */
    @JsonProperty("updateTime")
    public abstract Builder updateTime(Instant updateTime);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder updateTime(Optional<Instant> updateTime);

    /** Clears the value of updateTime field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearUpdateTime() {
      return updateTime(Optional.empty());
    }

    /**
     * Setter for error.
     *
     * <p>error: Output only. Only populated when job's state is `JOB_STATE_FAILED` or
     * `JOB_STATE_CANCELLED`.
     */
    @JsonProperty("error")
    public abstract Builder error(GoogleRpcStatus error);

    /**
     * Setter for error builder.
     *
     * <p>error: Output only. Only populated when job's state is `JOB_STATE_FAILED` or
     * `JOB_STATE_CANCELLED`.
     */
    @CanIgnoreReturnValue
    public Builder error(GoogleRpcStatus.Builder errorBuilder) {
      return error(errorBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder error(Optional<GoogleRpcStatus> error);

    /** Clears the value of error field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearError() {
      return error(Optional.empty());
    }

    /**
     * Setter for description.
     *
     * <p>description: Optional. The description of the TuningJob.
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
     * Setter for baseModel.
     *
     * <p>baseModel: The base model that is being tuned. See [Supported
     * models](https://cloud.google.com/vertex-ai/generative-ai/docs/model-reference/tuning#supported_models).
     */
    @JsonProperty("baseModel")
    public abstract Builder baseModel(String baseModel);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder baseModel(Optional<String> baseModel);

    /** Clears the value of baseModel field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearBaseModel() {
      return baseModel(Optional.empty());
    }

    /**
     * Setter for tunedModel.
     *
     * <p>tunedModel: Output only. The tuned model resources associated with this TuningJob.
     */
    @JsonProperty("tunedModel")
    public abstract Builder tunedModel(TunedModel tunedModel);

    /**
     * Setter for tunedModel builder.
     *
     * <p>tunedModel: Output only. The tuned model resources associated with this TuningJob.
     */
    @CanIgnoreReturnValue
    public Builder tunedModel(TunedModel.Builder tunedModelBuilder) {
      return tunedModel(tunedModelBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder tunedModel(Optional<TunedModel> tunedModel);

    /** Clears the value of tunedModel field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTunedModel() {
      return tunedModel(Optional.empty());
    }

    /**
     * Setter for preTunedModel.
     *
     * <p>preTunedModel: The pre-tuned model for continuous tuning.
     */
    @JsonProperty("preTunedModel")
    public abstract Builder preTunedModel(PreTunedModel preTunedModel);

    /**
     * Setter for preTunedModel builder.
     *
     * <p>preTunedModel: The pre-tuned model for continuous tuning.
     */
    @CanIgnoreReturnValue
    public Builder preTunedModel(PreTunedModel.Builder preTunedModelBuilder) {
      return preTunedModel(preTunedModelBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder preTunedModel(Optional<PreTunedModel> preTunedModel);

    /** Clears the value of preTunedModel field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPreTunedModel() {
      return preTunedModel(Optional.empty());
    }

    /**
     * Setter for supervisedTuningSpec.
     *
     * <p>supervisedTuningSpec: Tuning Spec for Supervised Fine Tuning.
     */
    @JsonProperty("supervisedTuningSpec")
    public abstract Builder supervisedTuningSpec(SupervisedTuningSpec supervisedTuningSpec);

    /**
     * Setter for supervisedTuningSpec builder.
     *
     * <p>supervisedTuningSpec: Tuning Spec for Supervised Fine Tuning.
     */
    @CanIgnoreReturnValue
    public Builder supervisedTuningSpec(SupervisedTuningSpec.Builder supervisedTuningSpecBuilder) {
      return supervisedTuningSpec(supervisedTuningSpecBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder supervisedTuningSpec(Optional<SupervisedTuningSpec> supervisedTuningSpec);

    /** Clears the value of supervisedTuningSpec field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSupervisedTuningSpec() {
      return supervisedTuningSpec(Optional.empty());
    }

    /**
     * Setter for preferenceOptimizationSpec.
     *
     * <p>preferenceOptimizationSpec: Tuning Spec for Preference Optimization.
     */
    @JsonProperty("preferenceOptimizationSpec")
    public abstract Builder preferenceOptimizationSpec(
        PreferenceOptimizationSpec preferenceOptimizationSpec);

    /**
     * Setter for preferenceOptimizationSpec builder.
     *
     * <p>preferenceOptimizationSpec: Tuning Spec for Preference Optimization.
     */
    @CanIgnoreReturnValue
    public Builder preferenceOptimizationSpec(
        PreferenceOptimizationSpec.Builder preferenceOptimizationSpecBuilder) {
      return preferenceOptimizationSpec(preferenceOptimizationSpecBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder preferenceOptimizationSpec(
        Optional<PreferenceOptimizationSpec> preferenceOptimizationSpec);

    /** Clears the value of preferenceOptimizationSpec field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPreferenceOptimizationSpec() {
      return preferenceOptimizationSpec(Optional.empty());
    }

    /**
     * Setter for tuningDataStats.
     *
     * <p>tuningDataStats: Output only. The tuning data statistics associated with this TuningJob.
     */
    @JsonProperty("tuningDataStats")
    public abstract Builder tuningDataStats(TuningDataStats tuningDataStats);

    /**
     * Setter for tuningDataStats builder.
     *
     * <p>tuningDataStats: Output only. The tuning data statistics associated with this TuningJob.
     */
    @CanIgnoreReturnValue
    public Builder tuningDataStats(TuningDataStats.Builder tuningDataStatsBuilder) {
      return tuningDataStats(tuningDataStatsBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder tuningDataStats(Optional<TuningDataStats> tuningDataStats);

    /** Clears the value of tuningDataStats field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTuningDataStats() {
      return tuningDataStats(Optional.empty());
    }

    /**
     * Setter for encryptionSpec.
     *
     * <p>encryptionSpec: Customer-managed encryption key options for a TuningJob. If this is set,
     * then all resources created by the TuningJob will be encrypted with the provided encryption
     * key.
     */
    @JsonProperty("encryptionSpec")
    public abstract Builder encryptionSpec(EncryptionSpec encryptionSpec);

    /**
     * Setter for encryptionSpec builder.
     *
     * <p>encryptionSpec: Customer-managed encryption key options for a TuningJob. If this is set,
     * then all resources created by the TuningJob will be encrypted with the provided encryption
     * key.
     */
    @CanIgnoreReturnValue
    public Builder encryptionSpec(EncryptionSpec.Builder encryptionSpecBuilder) {
      return encryptionSpec(encryptionSpecBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder encryptionSpec(Optional<EncryptionSpec> encryptionSpec);

    /** Clears the value of encryptionSpec field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEncryptionSpec() {
      return encryptionSpec(Optional.empty());
    }

    /**
     * Setter for partnerModelTuningSpec.
     *
     * <p>partnerModelTuningSpec: Tuning Spec for open sourced and third party Partner models.
     */
    @JsonProperty("partnerModelTuningSpec")
    public abstract Builder partnerModelTuningSpec(PartnerModelTuningSpec partnerModelTuningSpec);

    /**
     * Setter for partnerModelTuningSpec builder.
     *
     * <p>partnerModelTuningSpec: Tuning Spec for open sourced and third party Partner models.
     */
    @CanIgnoreReturnValue
    public Builder partnerModelTuningSpec(
        PartnerModelTuningSpec.Builder partnerModelTuningSpecBuilder) {
      return partnerModelTuningSpec(partnerModelTuningSpecBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder partnerModelTuningSpec(
        Optional<PartnerModelTuningSpec> partnerModelTuningSpec);

    /** Clears the value of partnerModelTuningSpec field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPartnerModelTuningSpec() {
      return partnerModelTuningSpec(Optional.empty());
    }

    /**
     * Setter for evaluationConfig.
     *
     * <p>evaluationConfig: Evaluation config for the tuning job.
     */
    @JsonProperty("evaluationConfig")
    public abstract Builder evaluationConfig(EvaluationConfig evaluationConfig);

    /**
     * Setter for evaluationConfig builder.
     *
     * <p>evaluationConfig: Evaluation config for the tuning job.
     */
    @CanIgnoreReturnValue
    public Builder evaluationConfig(EvaluationConfig.Builder evaluationConfigBuilder) {
      return evaluationConfig(evaluationConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder evaluationConfig(Optional<EvaluationConfig> evaluationConfig);

    /** Clears the value of evaluationConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEvaluationConfig() {
      return evaluationConfig(Optional.empty());
    }

    /**
     * Setter for customBaseModel.
     *
     * <p>customBaseModel: Optional. The user-provided path to custom model weights. Set this field
     * to tune a custom model. The path must be a Cloud Storage directory that contains the model
     * weights in .safetensors format along with associated model metadata files. If this field is
     * set, the base_model field must still be set to indicate which base model the custom model is
     * derived from. This feature is only available for open source models.
     */
    @JsonProperty("customBaseModel")
    public abstract Builder customBaseModel(String customBaseModel);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder customBaseModel(Optional<String> customBaseModel);

    /** Clears the value of customBaseModel field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCustomBaseModel() {
      return customBaseModel(Optional.empty());
    }

    /**
     * Setter for evaluateDatasetRuns.
     *
     * <p>evaluateDatasetRuns: Output only. Evaluation runs for the Tuning Job.
     */
    @JsonProperty("evaluateDatasetRuns")
    public abstract Builder evaluateDatasetRuns(List<EvaluateDatasetRun> evaluateDatasetRuns);

    /**
     * Setter for evaluateDatasetRuns.
     *
     * <p>evaluateDatasetRuns: Output only. Evaluation runs for the Tuning Job.
     */
    @CanIgnoreReturnValue
    public Builder evaluateDatasetRuns(EvaluateDatasetRun... evaluateDatasetRuns) {
      return evaluateDatasetRuns(Arrays.asList(evaluateDatasetRuns));
    }

    /**
     * Setter for evaluateDatasetRuns builder.
     *
     * <p>evaluateDatasetRuns: Output only. Evaluation runs for the Tuning Job.
     */
    @CanIgnoreReturnValue
    public Builder evaluateDatasetRuns(EvaluateDatasetRun.Builder... evaluateDatasetRunsBuilders) {
      return evaluateDatasetRuns(
          Arrays.asList(evaluateDatasetRunsBuilders).stream()
              .map(EvaluateDatasetRun.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder evaluateDatasetRuns(Optional<List<EvaluateDatasetRun>> evaluateDatasetRuns);

    /** Clears the value of evaluateDatasetRuns field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEvaluateDatasetRuns() {
      return evaluateDatasetRuns(Optional.empty());
    }

    /**
     * Setter for experiment.
     *
     * <p>experiment: Output only. The Experiment associated with this TuningJob.
     */
    @JsonProperty("experiment")
    public abstract Builder experiment(String experiment);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder experiment(Optional<String> experiment);

    /** Clears the value of experiment field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearExperiment() {
      return experiment(Optional.empty());
    }

    /**
     * Setter for fullFineTuningSpec.
     *
     * <p>fullFineTuningSpec: Tuning Spec for Full Fine Tuning.
     */
    @JsonProperty("fullFineTuningSpec")
    public abstract Builder fullFineTuningSpec(FullFineTuningSpec fullFineTuningSpec);

    /**
     * Setter for fullFineTuningSpec builder.
     *
     * <p>fullFineTuningSpec: Tuning Spec for Full Fine Tuning.
     */
    @CanIgnoreReturnValue
    public Builder fullFineTuningSpec(FullFineTuningSpec.Builder fullFineTuningSpecBuilder) {
      return fullFineTuningSpec(fullFineTuningSpecBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder fullFineTuningSpec(Optional<FullFineTuningSpec> fullFineTuningSpec);

    /** Clears the value of fullFineTuningSpec field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearFullFineTuningSpec() {
      return fullFineTuningSpec(Optional.empty());
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
     * Setter for outputUri.
     *
     * <p>outputUri: Optional. Cloud Storage path to the directory where tuning job outputs are
     * written to. This field is only available and required for open source models.
     */
    @JsonProperty("outputUri")
    public abstract Builder outputUri(String outputUri);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder outputUri(Optional<String> outputUri);

    /** Clears the value of outputUri field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearOutputUri() {
      return outputUri(Optional.empty());
    }

    /**
     * Setter for pipelineJob.
     *
     * <p>pipelineJob: Output only. The resource name of the PipelineJob associated with the
     * TuningJob. Format: `projects/{project}/locations/{location}/pipelineJobs/{pipeline_job}`.
     */
    @JsonProperty("pipelineJob")
    public abstract Builder pipelineJob(String pipelineJob);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder pipelineJob(Optional<String> pipelineJob);

    /** Clears the value of pipelineJob field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPipelineJob() {
      return pipelineJob(Optional.empty());
    }

    /**
     * Setter for serviceAccount.
     *
     * <p>serviceAccount: The service account that the tuningJob workload runs as. If not specified,
     * the Vertex AI Secure Fine-Tuned Service Agent in the project will be used. See
     * https://cloud.google.com/iam/docs/service-agents#vertex-ai-secure-fine-tuning-service-agent
     * Users starting the pipeline must have the `iam.serviceAccounts.actAs` permission on this
     * service account.
     */
    @JsonProperty("serviceAccount")
    public abstract Builder serviceAccount(String serviceAccount);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder serviceAccount(Optional<String> serviceAccount);

    /** Clears the value of serviceAccount field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearServiceAccount() {
      return serviceAccount(Optional.empty());
    }

    /**
     * Setter for tunedModelDisplayName.
     *
     * <p>tunedModelDisplayName: Optional. The display name of the TunedModel. The name can be up to
     * 128 characters long and can consist of any UTF-8 characters. For continuous tuning,
     * tuned_model_display_name will by default use the same display name as the pre-tuned model. If
     * a new display name is provided, the tuning job will create a new model instead of a new
     * version.
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
     * Setter for tuningJobState.
     *
     * <p>tuningJobState: Output only. The detail state of the tuning job (while the overall
     * `JobState` is running).
     */
    @JsonProperty("tuningJobState")
    public abstract Builder tuningJobState(TuningJobState tuningJobState);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder tuningJobState(Optional<TuningJobState> tuningJobState);

    /** Clears the value of tuningJobState field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTuningJobState() {
      return tuningJobState(Optional.empty());
    }

    /**
     * Setter for tuningJobState given a known enum.
     *
     * <p>tuningJobState: Output only. The detail state of the tuning job (while the overall
     * `JobState` is running).
     */
    @CanIgnoreReturnValue
    public Builder tuningJobState(TuningJobState.Known knownType) {
      return tuningJobState(new TuningJobState(knownType));
    }

    /**
     * Setter for tuningJobState given a string.
     *
     * <p>tuningJobState: Output only. The detail state of the tuning job (while the overall
     * `JobState` is running).
     */
    @CanIgnoreReturnValue
    public Builder tuningJobState(String tuningJobState) {
      return tuningJobState(new TuningJobState(tuningJobState));
    }

    /**
     * Setter for veoTuningSpec.
     *
     * <p>veoTuningSpec: Tuning Spec for Veo Tuning.
     */
    @JsonProperty("veoTuningSpec")
    public abstract Builder veoTuningSpec(VeoTuningSpec veoTuningSpec);

    /**
     * Setter for veoTuningSpec builder.
     *
     * <p>veoTuningSpec: Tuning Spec for Veo Tuning.
     */
    @CanIgnoreReturnValue
    public Builder veoTuningSpec(VeoTuningSpec.Builder veoTuningSpecBuilder) {
      return veoTuningSpec(veoTuningSpecBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder veoTuningSpec(Optional<VeoTuningSpec> veoTuningSpec);

    /** Clears the value of veoTuningSpec field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearVeoTuningSpec() {
      return veoTuningSpec(Optional.empty());
    }

    public abstract TuningJob build();
  }

  /** Deserializes a JSON string to a TuningJob object. */
  @ExcludeFromGeneratedCoverageReport
  public static TuningJob fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, TuningJob.class);
  }
}
