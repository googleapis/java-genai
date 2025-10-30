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

/** A generated image mask. */
@AutoValue
@JsonDeserialize(builder = GeneratedImageMask.Builder.class)
public abstract class GeneratedImageMask extends JsonSerializable {
  /** The generated image mask. */
  @JsonProperty("mask")
  public abstract Optional<Image> mask();

  /** The detected entities on the segmented area. */
  @JsonProperty("labels")
  public abstract Optional<List<EntityLabel>> labels();

  /** Instantiates a builder for GeneratedImageMask. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GeneratedImageMask.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GeneratedImageMask. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `GeneratedImageMask.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GeneratedImageMask.Builder();
    }

    /**
     * Setter for mask.
     *
     * <p>mask: The generated image mask.
     */
    @JsonProperty("mask")
    public abstract Builder mask(Image mask);

    /**
     * Setter for mask builder.
     *
     * <p>mask: The generated image mask.
     */
    public Builder mask(Image.Builder maskBuilder) {
      return mask(maskBuilder.build());
    }

    /**
     * Setter for labels.
     *
     * <p>labels: The detected entities on the segmented area.
     */
    @JsonProperty("labels")
    public abstract Builder labels(List<EntityLabel> labels);

    /**
     * Setter for labels.
     *
     * <p>labels: The detected entities on the segmented area.
     */
    public Builder labels(EntityLabel... labels) {
      return labels(Arrays.asList(labels));
    }

    /**
     * Setter for labels builder.
     *
     * <p>labels: The detected entities on the segmented area.
     */
    public Builder labels(EntityLabel.Builder... labelsBuilders) {
      return labels(
          Arrays.asList(labelsBuilders).stream()
              .map(EntityLabel.Builder::build)
              .collect(toImmutableList()));
    }

    public abstract GeneratedImageMask build();
  }

  /** Deserializes a JSON string to a GeneratedImageMask object. */
  @ExcludeFromGeneratedCoverageReport
  public static GeneratedImageMask fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GeneratedImageMask.class);
  }
}
