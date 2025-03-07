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
import java.util.List;
import java.util.Optional;

/**
 * Incremental update of the current conversation delivered from the client.
 *
 * <p>All the content here will unconditionally be appended to the conversation history and used as
 * part of the prompt to the model to generate content.
 *
 * <p>A message here will interrupt any current model generation.
 */
@GenerateTypeAdapter
@AutoValue
public abstract class LiveClientContent extends JsonSerializable {
  /**
   * The content appended to the current conversation with the model.
   *
   * <p>For single-turn queries, this is a single instance. For multi-turn queries, this is a
   * repeated field that contains conversation history and latest request.
   */
  @SerializedName("turns")
  public abstract Optional<List<Content>> turns();

  /**
   * If true, indicates that the server content generation should start with the currently
   * accumulated prompt. Otherwise, the server will await additional messages before starting
   * generation.
   */
  @SerializedName("turnComplete")
  public abstract Optional<Boolean> turnComplete();

  /** Instantiates a builder for LiveClientContent. */
  public static Builder builder() {
    return new AutoValue_LiveClientContent.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for LiveClientContent. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("turns")
    public abstract Builder turns(List<Content> turns);

    @SerializedName("turns")
    abstract Builder turns(Optional<List<Content>> turns);

    @SerializedName("turnComplete")
    public abstract Builder turnComplete(boolean turnComplete);

    @SerializedName("turnComplete")
    abstract Builder turnComplete(Optional<Boolean> turnComplete);

    public abstract LiveClientContent build();
  }

  /** Deserializes a JSON string to a LiveClientContent object. */
  public static LiveClientContent fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, LiveClientContent.class);
  }
}
