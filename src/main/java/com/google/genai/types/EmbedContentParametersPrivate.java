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
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** Parameters for the _embed_content method. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = EmbedContentParametersPrivate.Builder.class)
public abstract class EmbedContentParametersPrivate extends JsonSerializable {
  /**
   * ID of the model to use. For a list of models, see `Google models
   * <https://cloud.google.com/vertex-ai/generative-ai/docs/learn/models>`_.
   */
  @JsonProperty("model")
  public abstract Optional<String> model();

  /** The content to embed. Only the `parts.text` fields will be counted. */
  @JsonProperty("contents")
  public abstract Optional<List<Content>> contents();

  /** The single content to embed. Only the `parts.text` fields will be counted. */
  @JsonProperty("content")
  public abstract Optional<Content> content();

  /** The Vertex embedding API to use. */
  @JsonProperty("embeddingApiType")
  public abstract Optional<EmbeddingApiType> embeddingApiType();

  /** Configuration that contains optional parameters. */
  @JsonProperty("config")
  public abstract Optional<EmbedContentConfig> config();

  /** Instantiates a builder for EmbedContentParametersPrivate. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_EmbedContentParametersPrivate.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for EmbedContentParametersPrivate. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `EmbedContentParametersPrivate.builder()` for instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_EmbedContentParametersPrivate.Builder();
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
     * Setter for content.
     *
     * <p>content: The single content to embed. Only the `parts.text` fields will be counted.
     */
    @JsonProperty("content")
    public abstract Builder content(Content content);

    /**
     * Setter for content builder.
     *
     * <p>content: The single content to embed. Only the `parts.text` fields will be counted.
     */
    @CanIgnoreReturnValue
    public Builder content(Content.Builder contentBuilder) {
      return content(contentBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder content(Optional<Content> content);

    /** Clears the value of content field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearContent() {
      return content(Optional.empty());
    }

    /**
     * Setter for embeddingApiType.
     *
     * <p>embeddingApiType: The Vertex embedding API to use.
     */
    @JsonProperty("embeddingApiType")
    public abstract Builder embeddingApiType(EmbeddingApiType embeddingApiType);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder embeddingApiType(Optional<EmbeddingApiType> embeddingApiType);

    /** Clears the value of embeddingApiType field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEmbeddingApiType() {
      return embeddingApiType(Optional.empty());
    }

    /**
     * Setter for embeddingApiType given a known enum.
     *
     * <p>embeddingApiType: The Vertex embedding API to use.
     */
    @CanIgnoreReturnValue
    public Builder embeddingApiType(EmbeddingApiType.Known knownType) {
      return embeddingApiType(new EmbeddingApiType(knownType));
    }

    /**
     * Setter for embeddingApiType given a string.
     *
     * <p>embeddingApiType: The Vertex embedding API to use.
     */
    @CanIgnoreReturnValue
    public Builder embeddingApiType(String embeddingApiType) {
      return embeddingApiType(new EmbeddingApiType(embeddingApiType));
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

    public abstract EmbedContentParametersPrivate build();
  }

  /** Deserializes a JSON string to a EmbedContentParametersPrivate object. */
  @ExcludeFromGeneratedCoverageReport
  public static EmbedContentParametersPrivate fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, EmbedContentParametersPrivate.class);
  }
}
