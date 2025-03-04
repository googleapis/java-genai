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

/** Chunk from context retrieved by the retrieval tools. */
@AutoValue
public abstract class GroundingChunkRetrievedContext extends JsonSerializable {
  /** Text of the attribution. */
  @SerializedName("text")
  public abstract Optional<String> text();

  /** Title of the attribution. */
  @SerializedName("title")
  public abstract Optional<String> title();

  /** URI reference of the attribution. */
  @SerializedName("uri")
  public abstract Optional<String> uri();

  /** Instantiates a builder for GroundingChunkRetrievedContext. */
  public static Builder builder() {
    return new AutoValue_GroundingChunkRetrievedContext.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GroundingChunkRetrievedContext. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("text")
    public abstract Builder text(String text);

    @SerializedName("text")
    public abstract Builder text(Optional<String> text);

    @SerializedName("title")
    public abstract Builder title(String title);

    @SerializedName("title")
    public abstract Builder title(Optional<String> title);

    @SerializedName("uri")
    public abstract Builder uri(String uri);

    @SerializedName("uri")
    public abstract Builder uri(Optional<String> uri);

    public abstract GroundingChunkRetrievedContext build();
  }

  /** Returns a TypeAdapter for GroundingChunkRetrievedContext. */
  public static TypeAdapter<GroundingChunkRetrievedContext> typeAdapter(Gson gson) {
    return new AutoValue_GroundingChunkRetrievedContext.GsonTypeAdapter(gson);
  }

  /** Deserializes a JSON string to a GroundingChunkRetrievedContext object. */
  public static GroundingChunkRetrievedContext fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GroundingChunkRetrievedContext.class);
  }
}
