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

/** Represents a single interaction, request and response in a replay. */
@GenerateTypeAdapter
@AutoValue
public abstract class ReplayInteraction extends JsonSerializable {
  /** */
  @SerializedName("request")
  public abstract Optional<ReplayRequest> request();

  /** */
  @SerializedName("response")
  public abstract Optional<ReplayResponse> response();

  /** Instantiates a builder for ReplayInteraction. */
  public static Builder builder() {
    return new AutoValue_ReplayInteraction.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ReplayInteraction. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("request")
    public abstract Builder request(ReplayRequest request);

    @SerializedName("request")
    abstract Builder request(Optional<ReplayRequest> request);

    @SerializedName("response")
    public abstract Builder response(ReplayResponse response);

    @SerializedName("response")
    abstract Builder response(Optional<ReplayResponse> response);

    public abstract ReplayInteraction build();
  }

  /** Deserializes a JSON string to a ReplayInteraction object. */
  public static ReplayInteraction fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ReplayInteraction.class);
  }
}
