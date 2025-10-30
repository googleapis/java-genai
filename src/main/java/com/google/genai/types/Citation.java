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

/** Source attributions for content. This data type is not supported in Gemini API. */
@AutoValue
@JsonDeserialize(builder = Citation.Builder.class)
public abstract class Citation extends JsonSerializable {
  /** Output only. End index into the content. */
  @JsonProperty("endIndex")
  public abstract Optional<Integer> endIndex();

  /** Output only. License of the attribution. */
  @JsonProperty("license")
  public abstract Optional<String> license();

  /** Output only. Publication date of the attribution. */
  @JsonProperty("publicationDate")
  public abstract Optional<GoogleTypeDate> publicationDate();

  /** Output only. Start index into the content. */
  @JsonProperty("startIndex")
  public abstract Optional<Integer> startIndex();

  /** Output only. Title of the attribution. */
  @JsonProperty("title")
  public abstract Optional<String> title();

  /** Output only. Url reference of the attribution. */
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
     * <p>endIndex: Output only. End index into the content.
     */
    @JsonProperty("endIndex")
    public abstract Builder endIndex(Integer endIndex);

    /**
     * Setter for license.
     *
     * <p>license: Output only. License of the attribution.
     */
    @JsonProperty("license")
    public abstract Builder license(String license);

    /**
     * Setter for publicationDate.
     *
     * <p>publicationDate: Output only. Publication date of the attribution.
     */
    @JsonProperty("publicationDate")
    public abstract Builder publicationDate(GoogleTypeDate publicationDate);

    /**
     * Setter for publicationDate builder.
     *
     * <p>publicationDate: Output only. Publication date of the attribution.
     */
    public Builder publicationDate(GoogleTypeDate.Builder publicationDateBuilder) {
      return publicationDate(publicationDateBuilder.build());
    }

    /**
     * Setter for startIndex.
     *
     * <p>startIndex: Output only. Start index into the content.
     */
    @JsonProperty("startIndex")
    public abstract Builder startIndex(Integer startIndex);

    /**
     * Setter for title.
     *
     * <p>title: Output only. Title of the attribution.
     */
    @JsonProperty("title")
    public abstract Builder title(String title);

    /**
     * Setter for uri.
     *
     * <p>uri: Output only. Url reference of the attribution.
     */
    @JsonProperty("uri")
    public abstract Builder uri(String uri);

    public abstract Citation build();
  }

  /** Deserializes a JSON string to a Citation object. */
  @ExcludeFromGeneratedCoverageReport
  public static Citation fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Citation.class);
  }
}
