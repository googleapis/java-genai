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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Source attributions for content. */
@AutoValue
@JsonDeserialize(builder = AutoValue_Citation.Builder.class)
public abstract class Citation extends JsonSerializable {
  /** Output only. End index into the content. */
  public abstract Optional<Integer> getEndIndex();

  /** Output only. License of the attribution. */
  public abstract Optional<String> getLicense();

  /** Output only. Publication date of the attribution. */
  public abstract Optional<GoogleTypeDate> getPublicationDate();

  /** Output only. Start index into the content. */
  public abstract Optional<Integer> getStartIndex();

  /** Output only. Title of the attribution. */
  public abstract Optional<String> getTitle();

  /** Output only. Url reference of the attribution. */
  public abstract Optional<String> getUri();

  /** Instantiates a builder for Citation. */
  public static Builder builder() {
    return new AutoValue_Citation.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for Citation. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("endIndex")
    public abstract Builder setEndIndex(Integer endIndex);

    @JsonProperty("license")
    public abstract Builder setLicense(String license);

    @JsonProperty("publicationDate")
    public abstract Builder setPublicationDate(GoogleTypeDate publicationDate);

    @JsonProperty("startIndex")
    public abstract Builder setStartIndex(Integer startIndex);

    @JsonProperty("title")
    public abstract Builder setTitle(String title);

    @JsonProperty("uri")
    public abstract Builder setUri(String uri);

    public abstract Citation build();
  }

  /** Deserializes a JSON string to a Citation object. */
  public static Citation fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Citation.class);
  }
}
