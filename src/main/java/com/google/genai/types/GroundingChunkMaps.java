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
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Chunk from Google Maps. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = GroundingChunkMaps.Builder.class)
public abstract class GroundingChunkMaps extends JsonSerializable {
  /**
   * Sources used to generate the place answer. This includes review snippets and photos that were
   * used to generate the answer, as well as uris to flag content.
   */
  @JsonProperty("placeAnswerSources")
  public abstract Optional<GroundingChunkMapsPlaceAnswerSources> placeAnswerSources();

  /**
   * This Place's resource name, in `places/{place_id}` format. Can be used to look up the Place.
   */
  @JsonProperty("placeId")
  public abstract Optional<String> placeId();

  /** Text of the chunk. */
  @JsonProperty("text")
  public abstract Optional<String> text();

  /** Title of the chunk. */
  @JsonProperty("title")
  public abstract Optional<String> title();

  /** URI reference of the chunk. */
  @JsonProperty("uri")
  public abstract Optional<String> uri();

  /** Instantiates a builder for GroundingChunkMaps. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GroundingChunkMaps.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GroundingChunkMaps. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `GroundingChunkMaps.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GroundingChunkMaps.Builder();
    }

    /**
     * Setter for placeAnswerSources.
     *
     * <p>placeAnswerSources: Sources used to generate the place answer. This includes review
     * snippets and photos that were used to generate the answer, as well as uris to flag content.
     */
    @JsonProperty("placeAnswerSources")
    public abstract Builder placeAnswerSources(
        GroundingChunkMapsPlaceAnswerSources placeAnswerSources);

    /**
     * Setter for placeAnswerSources builder.
     *
     * <p>placeAnswerSources: Sources used to generate the place answer. This includes review
     * snippets and photos that were used to generate the answer, as well as uris to flag content.
     */
    public Builder placeAnswerSources(
        GroundingChunkMapsPlaceAnswerSources.Builder placeAnswerSourcesBuilder) {
      return placeAnswerSources(placeAnswerSourcesBuilder.build());
    }

    /**
     * Setter for placeId.
     *
     * <p>placeId: This Place's resource name, in `places/{place_id}` format. Can be used to look up
     * the Place.
     */
    @JsonProperty("placeId")
    public abstract Builder placeId(String placeId);

    /**
     * Setter for text.
     *
     * <p>text: Text of the chunk.
     */
    @JsonProperty("text")
    public abstract Builder text(String text);

    /**
     * Setter for title.
     *
     * <p>title: Title of the chunk.
     */
    @JsonProperty("title")
    public abstract Builder title(String title);

    /**
     * Setter for uri.
     *
     * <p>uri: URI reference of the chunk.
     */
    @JsonProperty("uri")
    public abstract Builder uri(String uri);

    public abstract GroundingChunkMaps build();
  }

  /** Deserializes a JSON string to a GroundingChunkMaps object. */
  @ExcludeFromGeneratedCoverageReport
  public static GroundingChunkMaps fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GroundingChunkMaps.class);
  }
}
