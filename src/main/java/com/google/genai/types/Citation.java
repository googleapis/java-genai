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

/** A citation for a piece of generatedcontent. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = Citation.Builder.class)
public abstract class Citation extends JsonSerializable {
  /** Output only. The end index of the citation in the content. */
  @JsonProperty("endIndex")
  public abstract Optional<Integer> endIndex();

  /** Output only. The license of the source of the citation. */
  @JsonProperty("license")
  public abstract Optional<String> license();

  /** Output only. The publication date of the source of the citation. */
  @JsonProperty("publicationDate")
  public abstract Optional<GoogleTypeDate> publicationDate();

  /** Output only. The start index of the citation in the content. */
  @JsonProperty("startIndex")
  public abstract Optional<Integer> startIndex();

  /** Output only. The title of the source of the citation. */
  @JsonProperty("title")
  public abstract Optional<String> title();

  /** Output only. The URI of the source of the citation. */
  @JsonProperty("uri")
  public abstract Optional<String> uri();

  /** Instantiates a builder for Citation. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_Citation.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for Citation. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `Citation.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_Citation.Builder();
    }

    /**
     * Setter for endIndex.
     *
     * <p>endIndex: Output only. The end index of the citation in the content.
     */
    @JsonProperty("endIndex")
    public abstract Builder endIndex(Integer endIndex);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder endIndex(Optional<Integer> endIndex);

    /** Clears the value of endIndex field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEndIndex() {
      return endIndex(Optional.empty());
    }

    /**
     * Setter for license.
     *
     * <p>license: Output only. The license of the source of the citation.
     */
    @JsonProperty("license")
    public abstract Builder license(String license);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder license(Optional<String> license);

    /** Clears the value of license field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearLicense() {
      return license(Optional.empty());
    }

    /**
     * Setter for publicationDate.
     *
     * <p>publicationDate: Output only. The publication date of the source of the citation.
     */
    @JsonProperty("publicationDate")
    public abstract Builder publicationDate(GoogleTypeDate publicationDate);

    /**
     * Setter for publicationDate builder.
     *
     * <p>publicationDate: Output only. The publication date of the source of the citation.
     */
    @CanIgnoreReturnValue
    public Builder publicationDate(GoogleTypeDate.Builder publicationDateBuilder) {
      return publicationDate(publicationDateBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder publicationDate(Optional<GoogleTypeDate> publicationDate);

    /** Clears the value of publicationDate field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPublicationDate() {
      return publicationDate(Optional.empty());
    }

    /**
     * Setter for startIndex.
     *
     * <p>startIndex: Output only. The start index of the citation in the content.
     */
    @JsonProperty("startIndex")
    public abstract Builder startIndex(Integer startIndex);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder startIndex(Optional<Integer> startIndex);

    /** Clears the value of startIndex field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearStartIndex() {
      return startIndex(Optional.empty());
    }

    /**
     * Setter for title.
     *
     * <p>title: Output only. The title of the source of the citation.
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
     * <p>uri: Output only. The URI of the source of the citation.
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

    public abstract Citation build();
  }

  /** Deserializes a JSON string to a Citation object. */
  @ExcludeFromGeneratedCoverageReport
  public static Citation fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Citation.class);
  }
}
