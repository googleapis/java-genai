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
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.Optional;

/** Segment of the content. */
@AutoValue
public abstract class Segment extends JsonSerializable {
  /**
   * Output only. End index in the given Part, measured in bytes. Offset from the start of the Part,
   * exclusive, starting at zero.
   */
  @SerializedName("endIndex")
  public abstract Optional<Integer> endIndex();

  /** Output only. The index of a Part object within its parent Content object. */
  @SerializedName("partIndex")
  public abstract Optional<Integer> partIndex();

  /**
   * Output only. Start index in the given Part, measured in bytes. Offset from the start of the
   * Part, inclusive, starting at zero.
   */
  @SerializedName("startIndex")
  public abstract Optional<Integer> startIndex();

  /** Output only. The text corresponding to the segment from the response. */
  @SerializedName("text")
  public abstract Optional<String> text();

  /** Instantiates a builder for Segment. */
  public static Builder builder() {
    return new AutoValue_Segment.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for Segment. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("endIndex")
    public abstract Builder endIndex(Integer endIndex);

    @SerializedName("endIndex")
    public abstract Builder endIndex(Optional<Integer> endIndex);

    @SerializedName("partIndex")
    public abstract Builder partIndex(Integer partIndex);

    @SerializedName("partIndex")
    public abstract Builder partIndex(Optional<Integer> partIndex);

    @SerializedName("startIndex")
    public abstract Builder startIndex(Integer startIndex);

    @SerializedName("startIndex")
    public abstract Builder startIndex(Optional<Integer> startIndex);

    @SerializedName("text")
    public abstract Builder text(String text);

    @SerializedName("text")
    public abstract Builder text(Optional<String> text);

    public abstract Segment build();
  }

  /** Returns a TypeAdapter for Segment. */
  public static TypeAdapter<Segment> typeAdapter(Gson gson) {
    return new AutoValue_Segment.GsonTypeAdapter(gson);
  }

  /** Deserializes a JSON string to a Segment object. */
  public static Segment fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Segment.class);
  }
}
