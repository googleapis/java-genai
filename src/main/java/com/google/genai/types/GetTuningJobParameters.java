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

/** Parameters for the get method. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = GetTuningJobParameters.Builder.class)
public abstract class GetTuningJobParameters extends JsonSerializable {
  /** */
  @JsonProperty("name")
  public abstract Optional<String> name();

  /** Optional parameters for the request. */
  @JsonProperty("config")
  public abstract Optional<GetTuningJobConfig> config();

  /** Instantiates a builder for GetTuningJobParameters. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GetTuningJobParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GetTuningJobParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `GetTuningJobParameters.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GetTuningJobParameters.Builder();
    }

    /**
     * Setter for name.
     *
     * <p>name:
     */
    @JsonProperty("name")
    public abstract Builder name(String name);

    /**
     * Setter for config.
     *
     * <p>config: Optional parameters for the request.
     */
    @JsonProperty("config")
    public abstract Builder config(GetTuningJobConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config: Optional parameters for the request.
     */
    public Builder config(GetTuningJobConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    public abstract GetTuningJobParameters build();
  }

  /** Deserializes a JSON string to a GetTuningJobParameters object. */
  @ExcludeFromGeneratedCoverageReport
  public static GetTuningJobParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GetTuningJobParameters.class);
  }
}
