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
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** TunedModel for the Tuned Model of a Tuning Job. */
@AutoValue
@JsonDeserialize(builder = TunedModel.Builder.class)
public abstract class TunedModel extends JsonSerializable {
  /**
   * Output only. The resource name of the TunedModel. Format:
   * `projects/{project}/locations/{location}/models/{model}@{version_id}` When tuning from a base
   * model, the version_id will be 1. For continuous tuning, the version id will be incremented by 1
   * from the last version id in the parent model. E.g.,
   * `projects/{project}/locations/{location}/models/{model}@{last_version_id + 1}`
   */
  @JsonProperty("model")
  public abstract Optional<String> model();

  /**
   * Output only. A resource name of an Endpoint. Format:
   * `projects/{project}/locations/{location}/endpoints/{endpoint}`.
   */
  @JsonProperty("endpoint")
  public abstract Optional<String> endpoint();

  /**
   * The checkpoints associated with this TunedModel. This field is only populated for tuning jobs
   * that enable intermediate checkpoints.
   */
  @JsonProperty("checkpoints")
  public abstract Optional<List<TunedModelCheckpoint>> checkpoints();

  /** Instantiates a builder for TunedModel. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_TunedModel.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for TunedModel. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `TunedModel.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_TunedModel.Builder();
    }

    /**
     * Setter for model.
     *
     * <p>model: Output only. The resource name of the TunedModel. Format:
     * `projects/{project}/locations/{location}/models/{model}@{version_id}` When tuning from a base
     * model, the version_id will be 1. For continuous tuning, the version id will be incremented by
     * 1 from the last version id in the parent model. E.g.,
     * `projects/{project}/locations/{location}/models/{model}@{last_version_id + 1}`
     */
    @JsonProperty("model")
    public abstract Builder model(String model);

    /**
     * Setter for endpoint.
     *
     * <p>endpoint: Output only. A resource name of an Endpoint. Format:
     * `projects/{project}/locations/{location}/endpoints/{endpoint}`.
     */
    @JsonProperty("endpoint")
    public abstract Builder endpoint(String endpoint);

    /**
     * Setter for checkpoints.
     *
     * <p>checkpoints: The checkpoints associated with this TunedModel. This field is only populated
     * for tuning jobs that enable intermediate checkpoints.
     */
    @JsonProperty("checkpoints")
    public abstract Builder checkpoints(List<TunedModelCheckpoint> checkpoints);

    /**
     * Setter for checkpoints.
     *
     * <p>checkpoints: The checkpoints associated with this TunedModel. This field is only populated
     * for tuning jobs that enable intermediate checkpoints.
     */
    public Builder checkpoints(TunedModelCheckpoint... checkpoints) {
      return checkpoints(Arrays.asList(checkpoints));
    }

    /**
     * Setter for checkpoints builder.
     *
     * <p>checkpoints: The checkpoints associated with this TunedModel. This field is only populated
     * for tuning jobs that enable intermediate checkpoints.
     */
    public Builder checkpoints(TunedModelCheckpoint.Builder... checkpointsBuilders) {
      return checkpoints(
          Arrays.asList(checkpointsBuilders).stream()
              .map(TunedModelCheckpoint.Builder::build)
              .collect(toImmutableList()));
    }

    public abstract TunedModel build();
  }

  /** Deserializes a JSON string to a TunedModel object. */
  @ExcludeFromGeneratedCoverageReport
  public static TunedModel fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, TunedModel.class);
  }
}
