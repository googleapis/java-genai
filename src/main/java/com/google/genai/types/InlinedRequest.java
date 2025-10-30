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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** Config for inlined request. */
@AutoValue
@JsonDeserialize(builder = InlinedRequest.Builder.class)
public abstract class InlinedRequest extends JsonSerializable {
  /**
   * ID of the model to use. For a list of models, see `Google models
   * <https://cloud.google.com/vertex-ai/generative-ai/docs/learn/models>`_.
   */
  @JsonProperty("model")
  public abstract Optional<String> model();

  /** Content of the request. */
  @JsonProperty("contents")
  public abstract Optional<List<Content>> contents();

  /** The metadata to be associated with the request. */
  @JsonProperty("metadata")
  public abstract Optional<Map<String, String>> metadata();

  /** Configuration that contains optional model parameters. */
  @JsonProperty("config")
  public abstract Optional<GenerateContentConfig> config();

  /** Instantiates a builder for InlinedRequest. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_InlinedRequest.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for InlinedRequest. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `InlinedRequest.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_InlinedRequest.Builder();
    }

    /**
     * Setter for model.
     *
     * <p>model: ID of the model to use. For a list of models, see `Google models
     * <https://cloud.google.com/vertex-ai/generative-ai/docs/learn/models>`_.
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
     * Setter for contents.
     *
     * <p>contents: Content of the request.
     */
    @JsonProperty("contents")
    public abstract Builder contents(List<Content> contents);

    /**
     * Setter for contents.
     *
     * <p>contents: Content of the request.
     */
    @CanIgnoreReturnValue
    public Builder contents(Content... contents) {
      return contents(Arrays.asList(contents));
    }

    /**
     * Setter for contents builder.
     *
     * <p>contents: Content of the request.
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
     * Setter for metadata.
     *
     * <p>metadata: The metadata to be associated with the request.
     */
    @JsonProperty("metadata")
    public abstract Builder metadata(Map<String, String> metadata);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder metadata(Optional<Map<String, String>> metadata);

    /** Clears the value of metadata field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMetadata() {
      return metadata(Optional.empty());
    }

    /**
     * Setter for config.
     *
     * <p>config: Configuration that contains optional model parameters.
     */
    @JsonProperty("config")
    public abstract Builder config(GenerateContentConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config: Configuration that contains optional model parameters.
     */
    @CanIgnoreReturnValue
    public Builder config(GenerateContentConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder config(Optional<GenerateContentConfig> config);

    /** Clears the value of config field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearConfig() {
      return config(Optional.empty());
    }

    public abstract InlinedRequest build();
  }

  /** Deserializes a JSON string to a InlinedRequest object. */
  @ExcludeFromGeneratedCoverageReport
  public static InlinedRequest fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, InlinedRequest.class);
  }
}
