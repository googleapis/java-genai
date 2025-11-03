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

/** Author attribution for a photo or review. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = GroundingChunkMapsPlaceAnswerSourcesAuthorAttribution.Builder.class)
public abstract class GroundingChunkMapsPlaceAnswerSourcesAuthorAttribution
    extends JsonSerializable {
  /** Name of the author of the Photo or Review. */
  @JsonProperty("displayName")
  public abstract Optional<String> displayName();

  /** Profile photo URI of the author of the Photo or Review. */
  @JsonProperty("photoUri")
  public abstract Optional<String> photoUri();

  /** URI of the author of the Photo or Review. */
  @JsonProperty("uri")
  public abstract Optional<String> uri();

  /** Instantiates a builder for GroundingChunkMapsPlaceAnswerSourcesAuthorAttribution. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GroundingChunkMapsPlaceAnswerSourcesAuthorAttribution.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GroundingChunkMapsPlaceAnswerSourcesAuthorAttribution. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use
     * `GroundingChunkMapsPlaceAnswerSourcesAuthorAttribution.builder()` for instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GroundingChunkMapsPlaceAnswerSourcesAuthorAttribution.Builder();
    }

    /**
     * Setter for displayName.
     *
     * <p>displayName: Name of the author of the Photo or Review.
     */
    @JsonProperty("displayName")
    public abstract Builder displayName(String displayName);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder displayName(Optional<String> displayName);

    /** Clears the value of displayName field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDisplayName() {
      return displayName(Optional.empty());
    }

    /**
     * Setter for photoUri.
     *
     * <p>photoUri: Profile photo URI of the author of the Photo or Review.
     */
    @JsonProperty("photoUri")
    public abstract Builder photoUri(String photoUri);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder photoUri(Optional<String> photoUri);

    /** Clears the value of photoUri field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPhotoUri() {
      return photoUri(Optional.empty());
    }

    /**
     * Setter for uri.
     *
     * <p>uri: URI of the author of the Photo or Review.
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

    public abstract GroundingChunkMapsPlaceAnswerSourcesAuthorAttribution build();
  }

  /**
   * Deserializes a JSON string to a GroundingChunkMapsPlaceAnswerSourcesAuthorAttribution object.
   */
  @ExcludeFromGeneratedCoverageReport
  public static GroundingChunkMapsPlaceAnswerSourcesAuthorAttribution fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(
        jsonString, GroundingChunkMapsPlaceAnswerSourcesAuthorAttribution.class);
  }
}
