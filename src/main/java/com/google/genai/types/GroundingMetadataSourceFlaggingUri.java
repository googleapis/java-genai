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
 * A URI that can be used to flag a place or review for inappropriate content. This is populated
 * only when the grounding source is Google Maps. This data type is not supported in Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = GroundingMetadataSourceFlaggingUri.Builder.class)
public abstract class GroundingMetadataSourceFlaggingUri extends JsonSerializable {
  /** The URI that can be used to flag the content. */
  @JsonProperty("flagContentUri")
  public abstract Optional<String> flagContentUri();

  /** The ID of the place or review. */
  @JsonProperty("sourceId")
  public abstract Optional<String> sourceId();

  /** Instantiates a builder for GroundingMetadataSourceFlaggingUri. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GroundingMetadataSourceFlaggingUri.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GroundingMetadataSourceFlaggingUri. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `GroundingMetadataSourceFlaggingUri.builder()` for
     * instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GroundingMetadataSourceFlaggingUri.Builder();
    }

    /**
     * Setter for flagContentUri.
     *
     * <p>flagContentUri: The URI that can be used to flag the content.
     */
    @JsonProperty("flagContentUri")
    public abstract Builder flagContentUri(String flagContentUri);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder flagContentUri(Optional<String> flagContentUri);

    /** Clears the value of flagContentUri field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearFlagContentUri() {
      return flagContentUri(Optional.empty());
    }

    /**
     * Setter for sourceId.
     *
     * <p>sourceId: The ID of the place or review.
     */
    @JsonProperty("sourceId")
    public abstract Builder sourceId(String sourceId);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder sourceId(Optional<String> sourceId);

    /** Clears the value of sourceId field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSourceId() {
      return sourceId(Optional.empty());
    }

    public abstract GroundingMetadataSourceFlaggingUri build();
  }

  /** Deserializes a JSON string to a GroundingMetadataSourceFlaggingUri object. */
  @ExcludeFromGeneratedCoverageReport
  public static GroundingMetadataSourceFlaggingUri fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GroundingMetadataSourceFlaggingUri.class);
  }
}
