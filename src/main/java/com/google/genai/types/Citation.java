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

import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.util.Optional;

/** Source attributions for content. */
@GenerateTypeAdapter
@AutoValue
public abstract class Citation extends JsonSerializable {
  /** Output only. End index into the content. */
  @SerializedName("endIndex")
  public abstract Optional<Integer> endIndex();

  /** Output only. License of the attribution. */
  @SerializedName("license")
  public abstract Optional<String> license();

  /** Output only. Publication date of the attribution. */
  @SerializedName("publicationDate")
  public abstract Optional<GoogleTypeDate> publicationDate();

  /** Output only. Start index into the content. */
  @SerializedName("startIndex")
  public abstract Optional<Integer> startIndex();

  /** Output only. Title of the attribution. */
  @SerializedName("title")
  public abstract Optional<String> title();

  /** Output only. Url reference of the attribution. */
  @SerializedName("uri")
  public abstract Optional<String> uri();

  /** Instantiates a builder for Citation. */
  public static Builder builder() {
    return new AutoValue_Citation.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for Citation. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("endIndex")
    public abstract Builder endIndex(Integer endIndex);

    @SerializedName("endIndex")
    abstract Builder endIndex(Optional<Integer> endIndex);

    @SerializedName("license")
    public abstract Builder license(String license);

    @SerializedName("license")
    abstract Builder license(Optional<String> license);

    @SerializedName("publicationDate")
    public abstract Builder publicationDate(GoogleTypeDate publicationDate);

    @SerializedName("publicationDate")
    abstract Builder publicationDate(Optional<GoogleTypeDate> publicationDate);

    @SerializedName("startIndex")
    public abstract Builder startIndex(Integer startIndex);

    @SerializedName("startIndex")
    abstract Builder startIndex(Optional<Integer> startIndex);

    @SerializedName("title")
    public abstract Builder title(String title);

    @SerializedName("title")
    abstract Builder title(Optional<String> title);

    @SerializedName("uri")
    public abstract Builder uri(String uri);

    @SerializedName("uri")
    abstract Builder uri(Optional<String> uri);

    public abstract Citation build();
  }

  /** Deserializes a JSON string to a Citation object. */
  public static Citation fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Citation.class);
  }
}
