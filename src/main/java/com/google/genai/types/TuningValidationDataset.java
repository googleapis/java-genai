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

/** None */
@AutoValue
@JsonDeserialize(builder = TuningValidationDataset.Builder.class)
public abstract class TuningValidationDataset extends JsonSerializable {
  /** GCS URI of the file containing validation dataset in JSONL format. */
  @JsonProperty("gcsUri")
  public abstract Optional<String> gcsUri();

  /**
   * The resource name of the Vertex Multimodal Dataset that is used as validation dataset. Example:
   * 'projects/my-project-id-or-number/locations/my-location/datasets/my-dataset-id'.
   */
  @JsonProperty("vertexDatasetResource")
  public abstract Optional<String> vertexDatasetResource();

  /** Instantiates a builder for TuningValidationDataset. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_TuningValidationDataset.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for TuningValidationDataset. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `TuningValidationDataset.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_TuningValidationDataset.Builder();
    }

    /**
     * Setter for gcsUri.
     *
     * <p>gcsUri: GCS URI of the file containing validation dataset in JSONL format.
     */
    @JsonProperty("gcsUri")
    public abstract Builder gcsUri(String gcsUri);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder gcsUri(Optional<String> gcsUri);

    /** Clears the value of gcsUri field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearGcsUri() {
      return gcsUri(Optional.empty());
    }

    /**
     * Setter for vertexDatasetResource.
     *
     * <p>vertexDatasetResource: The resource name of the Vertex Multimodal Dataset that is used as
     * validation dataset. Example:
     * 'projects/my-project-id-or-number/locations/my-location/datasets/my-dataset-id'.
     */
    @JsonProperty("vertexDatasetResource")
    public abstract Builder vertexDatasetResource(String vertexDatasetResource);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder vertexDatasetResource(Optional<String> vertexDatasetResource);

    /** Clears the value of vertexDatasetResource field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearVertexDatasetResource() {
      return vertexDatasetResource(Optional.empty());
    }

    public abstract TuningValidationDataset build();
  }

  /** Deserializes a JSON string to a TuningValidationDataset object. */
  @ExcludeFromGeneratedCoverageReport
  public static TuningValidationDataset fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, TuningValidationDataset.class);
  }
}
