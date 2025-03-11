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

/** Represents a recorded session. */
@GenerateTypeAdapter
@AutoValue
public abstract class ReplayFile extends JsonSerializable {
  /** */
  @SerializedName("replayId")
  public abstract Optional<String> replayId();

  /** */
  @SerializedName("interactions")
  public abstract Optional<List<ReplayInteraction>> interactions();

  /** Instantiates a builder for ReplayFile. */
  public static Builder builder() {
    return new AutoValue_ReplayFile.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ReplayFile. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("replayId")
    public abstract Builder replayId(String replayId);

    @SerializedName("replayId")
    abstract Builder replayId(Optional<String> replayId);

    @SerializedName("interactions")
    public abstract Builder interactions(List<ReplayInteraction> interactions);

    @SerializedName("interactions")
    abstract Builder interactions(Optional<List<ReplayInteraction>> interactions);

    public abstract ReplayFile build();
  }

  /** Deserializes a JSON string to a ReplayFile object. */
  public static ReplayFile fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ReplayFile.class);
  }
}
