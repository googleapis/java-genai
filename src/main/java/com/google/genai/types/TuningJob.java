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
import java.time.Instant;
import java.util.Map;
import java.util.Optional;

/** A tuning job. */
@AutoValue
@JsonDeserialize(builder = TuningJob.Builder.class)
public abstract class TuningJob extends JsonSerializable {
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

  /**
   * Optional. The user-provided path to custom model weights. Set this field to tune a custom
   * model. The path must be a Cloud Storage directory that contains the model weights in
   * .safetensors format along with associated model metadata files. If this field is set, the
   * base_model field must still be set to indicate which base model the custom model is derived
   * from. This feature is only available for open source models.
   */
  @JsonProperty("customBaseModel")
  public abstract Optional<String> customBaseModel();

  /** Output only. The Experiment associated with this TuningJob. */
  @JsonProperty("experiment")
  public abstract Optional<String> experiment();

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
   * consist of any UTF-8 characters.
   */
  @JsonProperty("tunedModelDisplayName")
  public abstract Optional<String> tunedModelDisplayName();

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
     * Setter for name.
     *
     * <p>name: Output only. Identifier. Resource name of a TuningJob. Format:
     * `projects/{project}/locations/{location}/tuningJobs/{tuning_job}`
     */
    @JsonProperty("name")
    public abstract Builder name(String name);

    /**
     * Setter for state.
     *
     * <p>state: Output only. The detailed state of the job.
     */
    @JsonProperty("state")
    public abstract Builder state(JobState state);

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

    /**
     * Setter for startTime.
     *
     * <p>startTime: Output only. Time when the TuningJob for the first time entered the
     * `JOB_STATE_RUNNING` state.
     */
    @JsonProperty("startTime")
    public abstract Builder startTime(Instant startTime);

    /**
     * Setter for endTime.
     *
     * <p>endTime: Output only. Time when the TuningJob entered any of the following JobStates:
     * `JOB_STATE_SUCCEEDED`, `JOB_STATE_FAILED`, `JOB_STATE_CANCELLED`, `JOB_STATE_EXPIRED`.
     */
    @JsonProperty("endTime")
    public abstract Builder endTime(Instant endTime);

    /**
     * Setter for updateTime.
     *
     * <p>updateTime: Output only. Time when the TuningJob was most recently updated.
     */
    @JsonProperty("updateTime")
    public abstract Builder updateTime(Instant updateTime);

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
    public Builder error(GoogleRpcStatus.Builder errorBuilder) {
      return error(errorBuilder.build());
    }

    /**
     * Setter for description.
     *
     * <p>description: Optional. The description of the TuningJob.
     */
    @JsonProperty("description")
    public abstract Builder description(String description);

    /**
     * Setter for baseModel.
     *
     * <p>baseModel: The base model that is being tuned. See [Supported
     * models](https://cloud.google.com/vertex-ai/generative-ai/docs/model-reference/tuning#supported_models).
     */
    @JsonProperty("baseModel")
    public abstract Builder baseModel(String baseModel);

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
    public Builder tunedModel(TunedModel.Builder tunedModelBuilder) {
      return tunedModel(tunedModelBuilder.build());
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
    public Builder preTunedModel(PreTunedModel.Builder preTunedModelBuilder) {
      return preTunedModel(preTunedModelBuilder.build());
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
    public Builder supervisedTuningSpec(SupervisedTuningSpec.Builder supervisedTuningSpecBuilder) {
      return supervisedTuningSpec(supervisedTuningSpecBuilder.build());
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
    public Builder tuningDataStats(TuningDataStats.Builder tuningDataStatsBuilder) {
      return tuningDataStats(tuningDataStatsBuilder.build());
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
    public Builder encryptionSpec(EncryptionSpec.Builder encryptionSpecBuilder) {
      return encryptionSpec(encryptionSpecBuilder.build());
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
    public Builder partnerModelTuningSpec(
        PartnerModelTuningSpec.Builder partnerModelTuningSpecBuilder) {
      return partnerModelTuningSpec(partnerModelTuningSpecBuilder.build());
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

    /**
     * Setter for experiment.
     *
     * <p>experiment: Output only. The Experiment associated with this TuningJob.
     */
    @JsonProperty("experiment")
    public abstract Builder experiment(String experiment);

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

    /**
     * Setter for outputUri.
     *
     * <p>outputUri: Optional. Cloud Storage path to the directory where tuning job outputs are
     * written to. This field is only available and required for open source models.
     */
    @JsonProperty("outputUri")
    public abstract Builder outputUri(String outputUri);

    /**
     * Setter for pipelineJob.
     *
     * <p>pipelineJob: Output only. The resource name of the PipelineJob associated with the
     * TuningJob. Format: `projects/{project}/locations/{location}/pipelineJobs/{pipeline_job}`.
     */
    @JsonProperty("pipelineJob")
    public abstract Builder pipelineJob(String pipelineJob);

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

    /**
     * Setter for tunedModelDisplayName.
     *
     * <p>tunedModelDisplayName: Optional. The display name of the TunedModel. The name can be up to
     * 128 characters long and can consist of any UTF-8 characters.
     */
    @JsonProperty("tunedModelDisplayName")
    public abstract Builder tunedModelDisplayName(String tunedModelDisplayName);

    public abstract TuningJob build();
  }

  /** Deserializes a JSON string to a TuningJob object. */
  @ExcludeFromGeneratedCoverageReport
  public static TuningJob fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, TuningJob.class);
  }
}
