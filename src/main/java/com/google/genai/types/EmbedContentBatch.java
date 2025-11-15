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
import java.util.Optional;

/** Parameters for the embed_content method. */
@AutoValue
@JsonDeserialize(builder = EmbedContentBatch.Builder.class)
public abstract class EmbedContentBatch extends JsonSerializable {
  /** The content to embed. Only the `parts.text` fields will be counted. */
  @JsonProperty("contents")
  public abstract Optional<List<Content>> contents();

  /** Configuration that contains optional parameters. */
  @JsonProperty("config")
  public abstract Optional<EmbedContentConfig> config();

  /** Output only. Stats about the batch. */
  @JsonProperty("batchStats")
  public abstract Optional<EmbedContentBatchStats> batchStats();

  /** Output only. The time at which the batch was created. */
  @JsonProperty("createTime")
  public abstract Optional<Instant> createTime();

  /** Required. The user-defined name of this batch. */
  @JsonProperty("displayName")
  public abstract Optional<String> displayName();

  /** Output only. The time at which the batch processing completed. */
  @JsonProperty("endTime")
  public abstract Optional<Instant> endTime();

  /** Required. Input configuration of the instances on which batch processing are performed. */
  @JsonProperty("inputConfig")
  public abstract Optional<InputEmbedContentConfig> inputConfig();

  /**
   * Required. The name of the `Model` to use for generating the completion. Format:
   * `models/{model}`.
   */
  @JsonProperty("model")
  public abstract Optional<String> model();

  /** Output only. Identifier. Resource name of the batch. Format: `batches/{batch_id}`. */
  @JsonProperty("name")
  public abstract Optional<String> name();

  /** Output only. The output of the batch request. */
  @JsonProperty("output")
  public abstract Optional<EmbedContentBatchOutput> output();

  /**
   * Optional. The priority of the batch. Batches with a higher priority value will be processed
   * before batches with a lower priority value. Negative values are allowed. Default is 0.
   */
  @JsonProperty("priority")
  public abstract Optional<Long> priority();

  /** Output only. The state of the batch. */
  @JsonProperty("state")
  public abstract Optional<BatchState> state();

  /** Output only. The time at which the batch was last updated. */
  @JsonProperty("updateTime")
  public abstract Optional<Instant> updateTime();

  /** Instantiates a builder for EmbedContentBatch. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_EmbedContentBatch.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for EmbedContentBatch. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `EmbedContentBatch.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_EmbedContentBatch.Builder();
    }

    /**
     * Setter for contents.
     *
     * <p>contents: The content to embed. Only the `parts.text` fields will be counted.
     */
    @JsonProperty("contents")
    public abstract Builder contents(List<Content> contents);

    /**
     * Setter for contents.
     *
     * <p>contents: The content to embed. Only the `parts.text` fields will be counted.
     */
    @CanIgnoreReturnValue
    public Builder contents(Content... contents) {
      return contents(Arrays.asList(contents));
    }

    /**
     * Setter for contents builder.
     *
     * <p>contents: The content to embed. Only the `parts.text` fields will be counted.
     */
    @CanIgnoreReturnValue
    public Builder contents(Content.Builder... contentsBuilders) {
      return contents(
          Arrays.asList(contentsBuilders).stream()
              .map(Content.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder contents(Optional<List<Content>> contents);

    /** Clears the value of contents field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearContents() {
      return contents(Optional.empty());
    }

    /**
     * Setter for config.
     *
     * <p>config: Configuration that contains optional parameters.
     */
    @JsonProperty("config")
    public abstract Builder config(EmbedContentConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config: Configuration that contains optional parameters.
     */
    @CanIgnoreReturnValue
    public Builder config(EmbedContentConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder config(Optional<EmbedContentConfig> config);

    /** Clears the value of config field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearConfig() {
      return config(Optional.empty());
    }

    /**
     * Setter for batchStats.
     *
     * <p>batchStats: Output only. Stats about the batch.
     */
    @JsonProperty("batchStats")
    public abstract Builder batchStats(EmbedContentBatchStats batchStats);

    /**
     * Setter for batchStats builder.
     *
     * <p>batchStats: Output only. Stats about the batch.
     */
    @CanIgnoreReturnValue
    public Builder batchStats(EmbedContentBatchStats.Builder batchStatsBuilder) {
      return batchStats(batchStatsBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder batchStats(Optional<EmbedContentBatchStats> batchStats);

    /** Clears the value of batchStats field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearBatchStats() {
      return batchStats(Optional.empty());
    }

    /**
     * Setter for createTime.
     *
     * <p>createTime: Output only. The time at which the batch was created.
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
     * Setter for displayName.
     *
     * <p>displayName: Required. The user-defined name of this batch.
     */
    @JsonProperty("displayName")
    public abstract Builder displayName(String displayName);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder displayName(Optional<String> displayName);

    /** Clears the value of displayName field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDisplayName() {
      return displayName(Optional.empty());
    }

    /**
     * Setter for endTime.
     *
     * <p>endTime: Output only. The time at which the batch processing completed.
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
     * Setter for inputConfig.
     *
     * <p>inputConfig: Required. Input configuration of the instances on which batch processing are
     * performed.
     */
    @JsonProperty("inputConfig")
    public abstract Builder inputConfig(InputEmbedContentConfig inputConfig);

    /**
     * Setter for inputConfig builder.
     *
     * <p>inputConfig: Required. Input configuration of the instances on which batch processing are
     * performed.
     */
    @CanIgnoreReturnValue
    public Builder inputConfig(InputEmbedContentConfig.Builder inputConfigBuilder) {
      return inputConfig(inputConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder inputConfig(Optional<InputEmbedContentConfig> inputConfig);

    /** Clears the value of inputConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearInputConfig() {
      return inputConfig(Optional.empty());
    }

    /**
     * Setter for model.
     *
     * <p>model: Required. The name of the `Model` to use for generating the completion. Format:
     * `models/{model}`.
     */
    @JsonProperty("model")
    public abstract Builder model(String model);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder model(Optional<String> model);

    /** Clears the value of model field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearModel() {
      return model(Optional.empty());
    }

    /**
     * Setter for name.
     *
     * <p>name: Output only. Identifier. Resource name of the batch. Format: `batches/{batch_id}`.
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
     * Setter for output.
     *
     * <p>output: Output only. The output of the batch request.
     */
    @JsonProperty("output")
    public abstract Builder output(EmbedContentBatchOutput output);

    /**
     * Setter for output builder.
     *
     * <p>output: Output only. The output of the batch request.
     */
    @CanIgnoreReturnValue
    public Builder output(EmbedContentBatchOutput.Builder outputBuilder) {
      return output(outputBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder output(Optional<EmbedContentBatchOutput> output);

    /** Clears the value of output field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearOutput() {
      return output(Optional.empty());
    }

    /**
     * Setter for priority.
     *
     * <p>priority: Optional. The priority of the batch. Batches with a higher priority value will
     * be processed before batches with a lower priority value. Negative values are allowed. Default
     * is 0.
     */
    @JsonProperty("priority")
    public abstract Builder priority(Long priority);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder priority(Optional<Long> priority);

    /** Clears the value of priority field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPriority() {
      return priority(Optional.empty());
    }

    /**
     * Setter for state.
     *
     * <p>state: Output only. The state of the batch.
     */
    @JsonProperty("state")
    public abstract Builder state(BatchState state);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder state(Optional<BatchState> state);

    /** Clears the value of state field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearState() {
      return state(Optional.empty());
    }

    /**
     * Setter for state given a known enum.
     *
     * <p>state: Output only. The state of the batch.
     */
    @CanIgnoreReturnValue
    public Builder state(BatchState.Known knownType) {
      return state(new BatchState(knownType));
    }

    /**
     * Setter for state given a string.
     *
     * <p>state: Output only. The state of the batch.
     */
    @CanIgnoreReturnValue
    public Builder state(String state) {
      return state(new BatchState(state));
    }

    /**
     * Setter for updateTime.
     *
     * <p>updateTime: Output only. The time at which the batch was last updated.
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

    public abstract EmbedContentBatch build();
  }

  /** Deserializes a JSON string to a EmbedContentBatch object. */
  @ExcludeFromGeneratedCoverageReport
  public static EmbedContentBatch fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, EmbedContentBatch.class);
  }
}
