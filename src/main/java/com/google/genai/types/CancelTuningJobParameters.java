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
import com.google.api.core.InternalApi;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Parameters for the cancel method. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = CancelTuningJobParameters.Builder.class)
public abstract class CancelTuningJobParameters extends JsonSerializable {
  /** The resource name of the tuning job. */
  @JsonProperty("name")
  public abstract Optional<String> name();

  /** Optional parameters for the request. */
  @JsonProperty("config")
  public abstract Optional<CancelTuningJobConfig> config();

  /** Instantiates a builder for CancelTuningJobParameters. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_CancelTuningJobParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for CancelTuningJobParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `CancelTuningJobParameters.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_CancelTuningJobParameters.Builder();
    }

    /**
     * Setter for name.
     *
     * <p>name: The resource name of the tuning job.
     */
    @JsonProperty("name")
    public abstract Builder name(String name);

    /**
     * Setter for config.
     *
     * <p>config: Optional parameters for the request.
     */
    @JsonProperty("config")
    public abstract Builder config(CancelTuningJobConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config: Optional parameters for the request.
     */
    public Builder config(CancelTuningJobConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    public abstract CancelTuningJobParameters build();
  }

  /** Deserializes a JSON string to a CancelTuningJobParameters object. */
  @ExcludeFromGeneratedCoverageReport
  public static CancelTuningJobParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, CancelTuningJobParameters.class);
  }
}
