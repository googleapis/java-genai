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

/**
 * A `Maps` chunk is a piece of evidence that comes from Google Maps. It contains information about
 * a place, such as its name, address, and reviews. This is used to provide the user with rich,
 * location-based information. This data type is not supported in Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = GroundingChunkMaps.Builder.class)
public abstract class GroundingChunkMaps extends JsonSerializable {
  /**
   * The sources that were used to generate the place answer. This includes review snippets and
   * photos that were used to generate the answer, as well as URIs to flag content.
   */
  @JsonProperty("placeAnswerSources")
  public abstract Optional<GroundingChunkMapsPlaceAnswerSources> placeAnswerSources();

  /**
   * This Place's resource name, in `places/{place_id}` format. This can be used to look up the
   * place in the Google Maps API.
   */
  @JsonProperty("placeId")
  public abstract Optional<String> placeId();

  /** The text of the place answer. */
  @JsonProperty("text")
  public abstract Optional<String> text();

  /** The title of the place. */
  @JsonProperty("title")
  public abstract Optional<String> title();

  /** The URI of the place. */
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
     * <p>placeAnswerSources: The sources that were used to generate the place answer. This includes
     * review snippets and photos that were used to generate the answer, as well as URIs to flag
     * content.
     */
    @JsonProperty("placeAnswerSources")
    public abstract Builder placeAnswerSources(
        GroundingChunkMapsPlaceAnswerSources placeAnswerSources);

    /**
     * Setter for placeAnswerSources builder.
     *
     * <p>placeAnswerSources: The sources that were used to generate the place answer. This includes
     * review snippets and photos that were used to generate the answer, as well as URIs to flag
     * content.
     */
    @CanIgnoreReturnValue
    public Builder placeAnswerSources(
        GroundingChunkMapsPlaceAnswerSources.Builder placeAnswerSourcesBuilder) {
      return placeAnswerSources(placeAnswerSourcesBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder placeAnswerSources(
        Optional<GroundingChunkMapsPlaceAnswerSources> placeAnswerSources);

    /** Clears the value of placeAnswerSources field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPlaceAnswerSources() {
      return placeAnswerSources(Optional.empty());
    }

    /**
     * Setter for placeId.
     *
     * <p>placeId: This Place's resource name, in `places/{place_id}` format. This can be used to
     * look up the place in the Google Maps API.
     */
    @JsonProperty("placeId")
    public abstract Builder placeId(String placeId);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder placeId(Optional<String> placeId);

    /** Clears the value of placeId field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPlaceId() {
      return placeId(Optional.empty());
    }

    /**
     * Setter for text.
     *
     * <p>text: The text of the place answer.
     */
    @JsonProperty("text")
    public abstract Builder text(String text);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder text(Optional<String> text);

    /** Clears the value of text field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearText() {
      return text(Optional.empty());
    }

    /**
     * Setter for title.
     *
     * <p>title: The title of the place.
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
     * Setter for uri.
     *
     * <p>uri: The URI of the place.
     */
    @JsonProperty("uri")
    public abstract Builder uri(String uri);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder uri(Optional<String> uri);

    /** Clears the value of uri field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearUri() {
      return uri(Optional.empty());
    }

    public abstract GroundingChunkMaps build();
  }

  /** Deserializes a JSON string to a GroundingChunkMaps object. */
  @ExcludeFromGeneratedCoverageReport
  public static GroundingChunkMaps fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GroundingChunkMaps.class);
  }
}
