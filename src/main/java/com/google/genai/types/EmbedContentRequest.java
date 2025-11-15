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

/** Request message for PredictionService.EmbedContent. */
@AutoValue
@JsonDeserialize(builder = EmbedContentRequest.Builder.class)
public abstract class EmbedContentRequest extends JsonSerializable {
  /**
   * Optional. Whether to silently truncate the input content if it's longer than the maximum
   * sequence length. This field is not supported in Gemini API.
   */
  @JsonProperty("autoTruncate")
  public abstract Optional<Boolean> autoTruncate();

  /** Required. Input content to be embedded. Required. */
  @JsonProperty("content")
  public abstract Optional<Content> content();

  /**
   * Optional. Optional reduced dimension for the output embedding. If set, excessive values in the
   * output embedding are truncated from the end.
   */
  @JsonProperty("outputDimensionality")
  public abstract Optional<Integer> outputDimensionality();

  /** Optional. The task type of the embedding. */
  @JsonProperty("taskType")
  public abstract Optional<TaskType> taskType();

  /** Optional. An optional title for the text. */
  @JsonProperty("title")
  public abstract Optional<String> title();

  /**
   * Required. The model's resource name. This serves as an ID for the Model to use. This name
   * should match a model name returned by the `ListModels` method. Format: `models/{model}`. This
   * field is not supported in Vertex AI.
   */
  @JsonProperty("model")
  public abstract Optional<String> model();

  /** Instantiates a builder for EmbedContentRequest. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_EmbedContentRequest.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for EmbedContentRequest. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `EmbedContentRequest.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_EmbedContentRequest.Builder();
    }

    /**
     * Setter for autoTruncate.
     *
     * <p>autoTruncate: Optional. Whether to silently truncate the input content if it's longer than
     * the maximum sequence length. This field is not supported in Gemini API.
     */
    @JsonProperty("autoTruncate")
    public abstract Builder autoTruncate(boolean autoTruncate);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder autoTruncate(Optional<Boolean> autoTruncate);

    /** Clears the value of autoTruncate field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAutoTruncate() {
      return autoTruncate(Optional.empty());
    }

    /**
     * Setter for content.
     *
     * <p>content: Required. Input content to be embedded. Required.
     */
    @JsonProperty("content")
    public abstract Builder content(Content content);

    /**
     * Setter for content builder.
     *
     * <p>content: Required. Input content to be embedded. Required.
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
     * Setter for outputDimensionality.
     *
     * <p>outputDimensionality: Optional. Optional reduced dimension for the output embedding. If
     * set, excessive values in the output embedding are truncated from the end.
     */
    @JsonProperty("outputDimensionality")
    public abstract Builder outputDimensionality(Integer outputDimensionality);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder outputDimensionality(Optional<Integer> outputDimensionality);

    /** Clears the value of outputDimensionality field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearOutputDimensionality() {
      return outputDimensionality(Optional.empty());
    }

    /**
     * Setter for taskType.
     *
     * <p>taskType: Optional. The task type of the embedding.
     */
    @JsonProperty("taskType")
    public abstract Builder taskType(TaskType taskType);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder taskType(Optional<TaskType> taskType);

    /** Clears the value of taskType field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTaskType() {
      return taskType(Optional.empty());
    }

    /**
     * Setter for taskType given a known enum.
     *
     * <p>taskType: Optional. The task type of the embedding.
     */
    @CanIgnoreReturnValue
    public Builder taskType(TaskType.Known knownType) {
      return taskType(new TaskType(knownType));
    }

    /**
     * Setter for taskType given a string.
     *
     * <p>taskType: Optional. The task type of the embedding.
     */
    @CanIgnoreReturnValue
    public Builder taskType(String taskType) {
      return taskType(new TaskType(taskType));
    }

    /**
     * Setter for title.
     *
     * <p>title: Optional. An optional title for the text.
     */
    @JsonProperty("title")
    public abstract Builder title(String title);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder title(Optional<String> title);

    /** Clears the value of title field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTitle() {
      return title(Optional.empty());
    }

    /**
     * Setter for model.
     *
     * <p>model: Required. The model's resource name. This serves as an ID for the Model to use.
     * This name should match a model name returned by the `ListModels` method. Format:
     * `models/{model}`. This field is not supported in Vertex AI.
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

    public abstract EmbedContentRequest build();
  }

  /** Deserializes a JSON string to a EmbedContentRequest object. */
  @ExcludeFromGeneratedCoverageReport
  public static EmbedContentRequest fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, EmbedContentRequest.class);
  }
}
