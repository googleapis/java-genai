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

/** Parameters for the embed_content method. */
@AutoValue
@JsonDeserialize(builder = EmbedContentBatch.Builder.class)
public abstract class EmbedContentBatch extends JsonSerializable {
  /** The content to embed. Only the `parts.text` fields will be counted. */
  @JsonProperty("contents")
  public abstract Optional<List<Content>> contents();

  /** Configuration that contains optional parameters. */
  @JsonProperty("config")
  public abstract Optional<EmbedContentConfig> config();

  /** Instantiates a builder for EmbedContentBatch. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_EmbedContentBatch.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for EmbedContentBatch. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `EmbedContentBatch.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_EmbedContentBatch.Builder();
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
    public Builder contents(Content... contents) {
      return contents(Arrays.asList(contents));
    }

    /**
     * Setter for contents builder.
     *
     * <p>contents: The content to embed. Only the `parts.text` fields will be counted.
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
     * <p>config: Configuration that contains optional parameters.
     */
    @JsonProperty("config")
    public abstract Builder config(EmbedContentConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config: Configuration that contains optional parameters.
     */
    public Builder config(EmbedContentConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    public abstract EmbedContentBatch build();
  }

  /** Deserializes a JSON string to a EmbedContentBatch object. */
  @ExcludeFromGeneratedCoverageReport
  public static EmbedContentBatch fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, EmbedContentBatch.class);
  }
}
