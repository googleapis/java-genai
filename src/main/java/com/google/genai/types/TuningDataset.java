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

/** Supervised fine-tuning training dataset. */
@AutoValue
@JsonDeserialize(builder = TuningDataset.Builder.class)
public abstract class TuningDataset extends JsonSerializable {
  /** GCS URI of the file containing training dataset in JSONL format. */
  @JsonProperty("gcsUri")
  public abstract Optional<String> gcsUri();

  /**
   * The resource name of the Vertex Multimodal Dataset that is used as training dataset. Example:
   * 'projects/my-project-id-or-number/locations/my-location/datasets/my-dataset-id'.
   */
  @JsonProperty("vertexDatasetResource")
  public abstract Optional<String> vertexDatasetResource();

  /** Inline examples with simple input/output text. */
  @JsonProperty("examples")
  public abstract Optional<List<TuningExample>> examples();

  /** Instantiates a builder for TuningDataset. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_TuningDataset.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for TuningDataset. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `TuningDataset.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_TuningDataset.Builder();
    }

    /**
     * Setter for gcsUri.
     *
     * <p>gcsUri: GCS URI of the file containing training dataset in JSONL format.
     */
    @JsonProperty("gcsUri")
    public abstract Builder gcsUri(String gcsUri);

    /**
     * Setter for vertexDatasetResource.
     *
     * <p>vertexDatasetResource: The resource name of the Vertex Multimodal Dataset that is used as
     * training dataset. Example:
     * 'projects/my-project-id-or-number/locations/my-location/datasets/my-dataset-id'.
     */
    @JsonProperty("vertexDatasetResource")
    public abstract Builder vertexDatasetResource(String vertexDatasetResource);

    /**
     * Setter for examples.
     *
     * <p>examples: Inline examples with simple input/output text.
     */
    @JsonProperty("examples")
    public abstract Builder examples(List<TuningExample> examples);

    /**
     * Setter for examples.
     *
     * <p>examples: Inline examples with simple input/output text.
     */
    public Builder examples(TuningExample... examples) {
      return examples(Arrays.asList(examples));
    }

    /**
     * Setter for examples builder.
     *
     * <p>examples: Inline examples with simple input/output text.
     */
    public Builder examples(TuningExample.Builder... examplesBuilders) {
      return examples(
          Arrays.asList(examplesBuilders).stream()
              .map(TuningExample.Builder::build)
              .collect(toImmutableList()));
    }

    public abstract TuningDataset build();
  }

  /** Deserializes a JSON string to a TuningDataset object. */
  @ExcludeFromGeneratedCoverageReport
  public static TuningDataset fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, TuningDataset.class);
  }
}
