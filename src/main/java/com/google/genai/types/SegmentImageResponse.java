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

/** The output images response. */
@AutoValue
@JsonDeserialize(builder = SegmentImageResponse.Builder.class)
public abstract class SegmentImageResponse extends JsonSerializable {
  /** List of generated image masks. */
  @JsonProperty("generatedMasks")
  public abstract Optional<List<GeneratedImageMask>> generatedMasks();

  /** Instantiates a builder for SegmentImageResponse. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_SegmentImageResponse.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for SegmentImageResponse. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `SegmentImageResponse.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_SegmentImageResponse.Builder();
    }

    /**
     * Setter for generatedMasks.
     *
     * <p>generatedMasks: List of generated image masks.
     */
    @JsonProperty("generatedMasks")
    public abstract Builder generatedMasks(List<GeneratedImageMask> generatedMasks);

    /**
     * Setter for generatedMasks.
     *
     * <p>generatedMasks: List of generated image masks.
     */
    public Builder generatedMasks(GeneratedImageMask... generatedMasks) {
      return generatedMasks(Arrays.asList(generatedMasks));
    }

    /**
     * Setter for generatedMasks builder.
     *
     * <p>generatedMasks: List of generated image masks.
     */
    public Builder generatedMasks(GeneratedImageMask.Builder... generatedMasksBuilders) {
      return generatedMasks(
          Arrays.asList(generatedMasksBuilders).stream()
              .map(GeneratedImageMask.Builder::build)
              .collect(toImmutableList()));
    }

    public abstract SegmentImageResponse build();
  }

  /** Deserializes a JSON string to a SegmentImageResponse object. */
  @ExcludeFromGeneratedCoverageReport
  public static SegmentImageResponse fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SegmentImageResponse.class);
  }
}
