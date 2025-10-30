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

/** The parameters for recontextualizing an image. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = RecontextImageParameters.Builder.class)
public abstract class RecontextImageParameters extends JsonSerializable {
  /**
   * ID of the model to use. For a list of models, see `Google models
   * <https://cloud.google.com/vertex-ai/generative-ai/docs/learn/models>`_.
   */
  @JsonProperty("model")
  public abstract Optional<String> model();

  /** A set of source input(s) for image recontextualization. */
  @JsonProperty("source")
  public abstract Optional<RecontextImageSource> source();

  /** Configuration for image recontextualization. */
  @JsonProperty("config")
  public abstract Optional<RecontextImageConfig> config();

  /** Instantiates a builder for RecontextImageParameters. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_RecontextImageParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for RecontextImageParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `RecontextImageParameters.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_RecontextImageParameters.Builder();
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
     * Setter for source.
     *
     * <p>source: A set of source input(s) for image recontextualization.
     */
    @JsonProperty("source")
    public abstract Builder source(RecontextImageSource source);

    /**
     * Setter for source builder.
     *
     * <p>source: A set of source input(s) for image recontextualization.
     */
    public Builder source(RecontextImageSource.Builder sourceBuilder) {
      return source(sourceBuilder.build());
    }

    /**
     * Setter for config.
     *
     * <p>config: Configuration for image recontextualization.
     */
    @JsonProperty("config")
    public abstract Builder config(RecontextImageConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config: Configuration for image recontextualization.
     */
    public Builder config(RecontextImageConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    public abstract RecontextImageParameters build();
  }

  /** Deserializes a JSON string to a RecontextImageParameters object. */
  @ExcludeFromGeneratedCoverageReport
  public static RecontextImageParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, RecontextImageParameters.class);
  }
}
