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
 * An `Image` chunk is a piece of evidence that comes from an image search result. It contains the
 * URI of the image search result and the URI of the image. This is used to provide the user with a
 * link to the source of the information.
 */
@AutoValue
@JsonDeserialize(builder = GroundingChunkImage.Builder.class)
public abstract class GroundingChunkImage extends JsonSerializable {
  /** The URI of the image search result page. */
  @JsonProperty("sourceUri")
  public abstract Optional<String> sourceUri();

  /** The URI of the image. */
  @JsonProperty("imageUri")
  public abstract Optional<String> imageUri();

  /** The title of the image search result page. */
  @JsonProperty("title")
  public abstract Optional<String> title();

  /** The domain of the image search result page. */
  @JsonProperty("domain")
  public abstract Optional<String> domain();

  /** Instantiates a builder for GroundingChunkImage. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GroundingChunkImage.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GroundingChunkImage. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `GroundingChunkImage.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GroundingChunkImage.Builder();
    }

    /**
     * Setter for sourceUri.
     *
     * <p>sourceUri: The URI of the image search result page.
     */
    @JsonProperty("sourceUri")
    public abstract Builder sourceUri(String sourceUri);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder sourceUri(Optional<String> sourceUri);

    /** Clears the value of sourceUri field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSourceUri() {
      return sourceUri(Optional.empty());
    }

    /**
     * Setter for imageUri.
     *
     * <p>imageUri: The URI of the image.
     */
    @JsonProperty("imageUri")
    public abstract Builder imageUri(String imageUri);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder imageUri(Optional<String> imageUri);

    /** Clears the value of imageUri field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearImageUri() {
      return imageUri(Optional.empty());
    }

    /**
     * Setter for title.
     *
     * <p>title: The title of the image search result page.
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
     * Setter for domain.
     *
     * <p>domain: The domain of the image search result page.
     */
    @JsonProperty("domain")
    public abstract Builder domain(String domain);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder domain(Optional<String> domain);

    /** Clears the value of domain field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDomain() {
      return domain(Optional.empty());
    }

    public abstract GroundingChunkImage build();
  }

  /** Deserializes a JSON string to a GroundingChunkImage object. */
  @ExcludeFromGeneratedCoverageReport
  public static GroundingChunkImage fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GroundingChunkImage.class);
  }
}
