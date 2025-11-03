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
import java.util.Optional;

/** Config for batches.create return value. */
@AutoValue
@JsonDeserialize(builder = BatchJob.Builder.class)
public abstract class BatchJob extends JsonSerializable {
  /** The resource name of the BatchJob. Output only.". */
  @JsonProperty("name")
  public abstract Optional<String> name();

  /** The display name of the BatchJob. */
  @JsonProperty("displayName")
  public abstract Optional<String> displayName();

  /** The state of the BatchJob. */
  @JsonProperty("state")
  public abstract Optional<JobState> state();

  /**
   * Output only. Only populated when the job's state is JOB_STATE_FAILED or JOB_STATE_CANCELLED.
   */
  @JsonProperty("error")
  public abstract Optional<JobError> error();

  /** The time when the BatchJob was created. */
  @JsonProperty("createTime")
  public abstract Optional<Instant> createTime();

  /** Output only. Time when the Job for the first time entered the `JOB_STATE_RUNNING` state. */
  @JsonProperty("startTime")
  public abstract Optional<Instant> startTime();

  /** The time when the BatchJob was completed. */
  @JsonProperty("endTime")
  public abstract Optional<Instant> endTime();

  /** The time when the BatchJob was last updated. */
  @JsonProperty("updateTime")
  public abstract Optional<Instant> updateTime();

  /** The name of the model that produces the predictions via the BatchJob. */
  @JsonProperty("model")
  public abstract Optional<String> model();

  /** Configuration for the input data. */
  @JsonProperty("src")
  public abstract Optional<BatchJobSource> src();

  /** Configuration for the output data. */
  @JsonProperty("dest")
  public abstract Optional<BatchJobDestination> dest();

  /** Instantiates a builder for BatchJob. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_BatchJob.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for BatchJob. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `BatchJob.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_BatchJob.Builder();
    }

    /**
     * Setter for name.
     *
     * <p>name: The resource name of the BatchJob. Output only.".
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
     * Setter for displayName.
     *
     * <p>displayName: The display name of the BatchJob.
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
     * Setter for state.
     *
     * <p>state: The state of the BatchJob.
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
     * <p>state: The state of the BatchJob.
     */
    @CanIgnoreReturnValue
    public Builder state(JobState.Known knownType) {
      return state(new JobState(knownType));
    }

    /**
     * Setter for state given a string.
     *
     * <p>state: The state of the BatchJob.
     */
    @CanIgnoreReturnValue
    public Builder state(String state) {
      return state(new JobState(state));
    }

    /**
     * Setter for error.
     *
     * <p>error: Output only. Only populated when the job's state is JOB_STATE_FAILED or
     * JOB_STATE_CANCELLED.
     */
    @JsonProperty("error")
    public abstract Builder error(JobError error);

    /**
     * Setter for error builder.
     *
     * <p>error: Output only. Only populated when the job's state is JOB_STATE_FAILED or
     * JOB_STATE_CANCELLED.
     */
    @CanIgnoreReturnValue
    public Builder error(JobError.Builder errorBuilder) {
      return error(errorBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder error(Optional<JobError> error);

    /** Clears the value of error field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearError() {
      return error(Optional.empty());
    }

    /**
     * Setter for createTime.
     *
     * <p>createTime: The time when the BatchJob was created.
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
     * <p>startTime: Output only. Time when the Job for the first time entered the
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
     * <p>endTime: The time when the BatchJob was completed.
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
     * <p>updateTime: The time when the BatchJob was last updated.
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
     * Setter for model.
     *
     * <p>model: The name of the model that produces the predictions via the BatchJob.
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
     * Setter for src.
     *
     * <p>src: Configuration for the input data.
     */
    @JsonProperty("src")
    public abstract Builder src(BatchJobSource src);

    /**
     * Setter for src builder.
     *
     * <p>src: Configuration for the input data.
     */
    @CanIgnoreReturnValue
    public Builder src(BatchJobSource.Builder srcBuilder) {
      return src(srcBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder src(Optional<BatchJobSource> src);

    /** Clears the value of src field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSrc() {
      return src(Optional.empty());
    }

    /**
     * Setter for dest.
     *
     * <p>dest: Configuration for the output data.
     */
    @JsonProperty("dest")
    public abstract Builder dest(BatchJobDestination dest);

    /**
     * Setter for dest builder.
     *
     * <p>dest: Configuration for the output data.
     */
    @CanIgnoreReturnValue
    public Builder dest(BatchJobDestination.Builder destBuilder) {
      return dest(destBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder dest(Optional<BatchJobDestination> dest);

    /** Clears the value of dest field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDest() {
      return dest(Optional.empty());
    }

    public abstract BatchJob build();
  }

  /** Deserializes a JSON string to a BatchJob object. */
  @ExcludeFromGeneratedCoverageReport
  public static BatchJob fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, BatchJob.class);
  }
}
