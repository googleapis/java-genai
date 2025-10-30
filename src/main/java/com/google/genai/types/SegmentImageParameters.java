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

/** The parameters for segmenting an image. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = SegmentImageParameters.Builder.class)
public abstract class SegmentImageParameters extends JsonSerializable {
  /**
   * ID of the model to use. For a list of models, see `Google models
   * <https://cloud.google.com/vertex-ai/generative-ai/docs/learn/models>`_.
   */
  @JsonProperty("model")
  public abstract Optional<String> model();

  /** A set of source input(s) for image segmentation. */
  @JsonProperty("source")
  public abstract Optional<SegmentImageSource> source();

  /** Configuration for image segmentation. */
  @JsonProperty("config")
  public abstract Optional<SegmentImageConfig> config();

  /** Instantiates a builder for SegmentImageParameters. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_SegmentImageParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for SegmentImageParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `SegmentImageParameters.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_SegmentImageParameters.Builder();
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
     * <p>source: A set of source input(s) for image segmentation.
     */
    @JsonProperty("source")
    public abstract Builder source(SegmentImageSource source);

    /**
     * Setter for source builder.
     *
     * <p>source: A set of source input(s) for image segmentation.
     */
    public Builder source(SegmentImageSource.Builder sourceBuilder) {
      return source(sourceBuilder.build());
    }

    /**
     * Setter for config.
     *
     * <p>config: Configuration for image segmentation.
     */
    @JsonProperty("config")
    public abstract Builder config(SegmentImageConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config: Configuration for image segmentation.
     */
    public Builder config(SegmentImageConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    public abstract SegmentImageParameters build();
  }

  /** Deserializes a JSON string to a SegmentImageParameters object. */
  @ExcludeFromGeneratedCoverageReport
  public static SegmentImageParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SegmentImageParameters.class);
  }
}
