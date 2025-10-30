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
import com.google.api.core.InternalApi;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** Parameters for computing tokens. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = ComputeTokensParameters.Builder.class)
public abstract class ComputeTokensParameters extends JsonSerializable {
  /**
   * ID of the model to use. For a list of models, see `Google models
   * <https://cloud.google.com/vertex-ai/generative-ai/docs/learn/models>`_.
   */
  @JsonProperty("model")
  public abstract Optional<String> model();

  /** Input content. */
  @JsonProperty("contents")
  public abstract Optional<List<Content>> contents();

  /** Optional parameters for the request. */
  @JsonProperty("config")
  public abstract Optional<ComputeTokensConfig> config();

  /** Instantiates a builder for ComputeTokensParameters. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ComputeTokensParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ComputeTokensParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ComputeTokensParameters.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ComputeTokensParameters.Builder();
    }

    /**
     * Setter for model.
     *
     * <p>model: ID of the model to use. For a list of models, see `Google models
     * <https://cloud.google.com/vertex-ai/generative-ai/docs/learn/models>`_.
     */
    @JsonProperty("model")
    public abstract Builder model(String model);

    /**
     * Setter for contents.
     *
     * <p>contents: Input content.
     */
    @JsonProperty("contents")
    public abstract Builder contents(List<Content> contents);

    /**
     * Setter for contents.
     *
     * <p>contents: Input content.
     */
    public Builder contents(Content... contents) {
      return contents(Arrays.asList(contents));
    }

    /**
     * Setter for contents builder.
     *
     * <p>contents: Input content.
     */
    public Builder contents(Content.Builder... contentsBuilders) {
      return contents(
          Arrays.asList(contentsBuilders).stream()
              .map(Content.Builder::build)
              .collect(toImmutableList()));
    }

    /**
     * Setter for config.
     *
     * <p>config: Optional parameters for the request.
     */
    @JsonProperty("config")
    public abstract Builder config(ComputeTokensConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config: Optional parameters for the request.
     */
    public Builder config(ComputeTokensConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    public abstract ComputeTokensParameters build();
  }

  /** Deserializes a JSON string to a ComputeTokensParameters object. */
  @ExcludeFromGeneratedCoverageReport
  public static ComputeTokensParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ComputeTokensParameters.class);
  }
}
