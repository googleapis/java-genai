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

/** Chunk from the web. */
@GenerateTypeAdapter
@AutoValue
public abstract class GroundingChunkWeb extends JsonSerializable {
  /** Title of the chunk. */
  @SerializedName("title")
  public abstract Optional<String> title();

  /** URI reference of the chunk. */
  @SerializedName("uri")
  public abstract Optional<String> uri();

  /** Instantiates a builder for GroundingChunkWeb. */
  public static Builder builder() {
    return new AutoValue_GroundingChunkWeb.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GroundingChunkWeb. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("title")
    public abstract Builder title(String title);

    @SerializedName("title")
    abstract Builder title(Optional<String> title);

    @SerializedName("uri")
    public abstract Builder uri(String uri);

    @SerializedName("uri")
    abstract Builder uri(Optional<String> uri);

    public abstract GroundingChunkWeb build();
  }

  /** Deserializes a JSON string to a GroundingChunkWeb object. */
  public static GroundingChunkWeb fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GroundingChunkWeb.class);
  }
}
