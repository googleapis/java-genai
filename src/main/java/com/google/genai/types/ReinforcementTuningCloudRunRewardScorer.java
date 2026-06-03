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
import java.util.Optional;

/** Scores parsed responses by calling a Cloud Run service. */
@AutoValue
@JsonDeserialize(builder = ReinforcementTuningCloudRunRewardScorer.Builder.class)
public abstract class ReinforcementTuningCloudRunRewardScorer extends JsonSerializable {
  /**
   * URI of the Cloud Run service that will be used to compute the reward. The Vertex AI Secure Fine
   * Tuning Service Agent (`service-PROJECT_NUMBER@gcp-sa-vertex-tune.iam.gserviceaccount.com`,
   * where `PROJECT_NUMBER` is the numeric project number) must be granted the permission (e.g. by
   * granting `roles/run.invoker` in IAM) to invoke the Cloud Run service.
   */
  @JsonProperty("cloudRunUri")
  public abstract Optional<String> cloudRunUri();

  /** Instantiates a builder for ReinforcementTuningCloudRunRewardScorer. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ReinforcementTuningCloudRunRewardScorer.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ReinforcementTuningCloudRunRewardScorer. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `ReinforcementTuningCloudRunRewardScorer.builder()` for
     * instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ReinforcementTuningCloudRunRewardScorer.Builder();
    }

    /**
     * Setter for cloudRunUri.
     *
     * <p>cloudRunUri: URI of the Cloud Run service that will be used to compute the reward. The
     * Vertex AI Secure Fine Tuning Service Agent
     * (`service-PROJECT_NUMBER@gcp-sa-vertex-tune.iam.gserviceaccount.com`, where `PROJECT_NUMBER`
     * is the numeric project number) must be granted the permission (e.g. by granting
     * `roles/run.invoker` in IAM) to invoke the Cloud Run service.
     */
    @JsonProperty("cloudRunUri")
    public abstract Builder cloudRunUri(String cloudRunUri);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder cloudRunUri(Optional<String> cloudRunUri);

    /** Clears the value of cloudRunUri field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCloudRunUri() {
      return cloudRunUri(Optional.empty());
    }

    public abstract ReinforcementTuningCloudRunRewardScorer build();
  }

  /** Deserializes a JSON string to a ReinforcementTuningCloudRunRewardScorer object. */
  @ExcludeFromGeneratedCoverageReport
  public static ReinforcementTuningCloudRunRewardScorer fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(
        jsonString, ReinforcementTuningCloudRunRewardScorer.class);
  }
}
