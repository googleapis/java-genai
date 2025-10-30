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

/** Response with generated videos. */
@AutoValue
@JsonDeserialize(builder = GenerateVideosResponse.Builder.class)
public abstract class GenerateVideosResponse extends JsonSerializable {
  /** List of the generated videos */
  @JsonProperty("generatedVideos")
  public abstract Optional<List<GeneratedVideo>> generatedVideos();

  /** Returns if any videos were filtered due to RAI policies. */
  @JsonProperty("raiMediaFilteredCount")
  public abstract Optional<Integer> raiMediaFilteredCount();

  /** Returns rai failure reasons if any. */
  @JsonProperty("raiMediaFilteredReasons")
  public abstract Optional<List<String>> raiMediaFilteredReasons();

  /** Instantiates a builder for GenerateVideosResponse. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GenerateVideosResponse.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerateVideosResponse. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `GenerateVideosResponse.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GenerateVideosResponse.Builder();
    }

    /**
     * Setter for generatedVideos.
     *
     * <p>generatedVideos: List of the generated videos
     */
    @JsonProperty("generatedVideos")
    public abstract Builder generatedVideos(List<GeneratedVideo> generatedVideos);

    /**
     * Setter for generatedVideos.
     *
     * <p>generatedVideos: List of the generated videos
     */
    public Builder generatedVideos(GeneratedVideo... generatedVideos) {
      return generatedVideos(Arrays.asList(generatedVideos));
    }

    /**
     * Setter for generatedVideos builder.
     *
     * <p>generatedVideos: List of the generated videos
     */
    public Builder generatedVideos(GeneratedVideo.Builder... generatedVideosBuilders) {
      return generatedVideos(
          Arrays.asList(generatedVideosBuilders).stream()
              .map(GeneratedVideo.Builder::build)
              .collect(toImmutableList()));
    }

    /**
     * Setter for raiMediaFilteredCount.
     *
     * <p>raiMediaFilteredCount: Returns if any videos were filtered due to RAI policies.
     */
    @JsonProperty("raiMediaFilteredCount")
    public abstract Builder raiMediaFilteredCount(Integer raiMediaFilteredCount);

    /**
     * Setter for raiMediaFilteredReasons.
     *
     * <p>raiMediaFilteredReasons: Returns rai failure reasons if any.
     */
    @JsonProperty("raiMediaFilteredReasons")
    public abstract Builder raiMediaFilteredReasons(List<String> raiMediaFilteredReasons);

    /**
     * Setter for raiMediaFilteredReasons.
     *
     * <p>raiMediaFilteredReasons: Returns rai failure reasons if any.
     */
    public Builder raiMediaFilteredReasons(String... raiMediaFilteredReasons) {
      return raiMediaFilteredReasons(Arrays.asList(raiMediaFilteredReasons));
    }

    public abstract GenerateVideosResponse build();
  }

  /** Deserializes a JSON string to a GenerateVideosResponse object. */
  @ExcludeFromGeneratedCoverageReport
  public static GenerateVideosResponse fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerateVideosResponse.class);
  }
}
