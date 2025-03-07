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

/** Content filter results for a prompt sent in the request. */
@GenerateTypeAdapter
@AutoValue
public abstract class GenerateContentResponsePromptFeedback extends JsonSerializable {
  /** Output only. Blocked reason. */
  @SerializedName("blockReason")
  public abstract Optional<String> blockReason();

  /** Output only. A readable block reason message. */
  @SerializedName("blockReasonMessage")
  public abstract Optional<String> blockReasonMessage();

  /** Output only. Safety ratings. */
  @SerializedName("safetyRatings")
  public abstract Optional<List<SafetyRating>> safetyRatings();

  /** Instantiates a builder for GenerateContentResponsePromptFeedback. */
  public static Builder builder() {
    return new AutoValue_GenerateContentResponsePromptFeedback.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerateContentResponsePromptFeedback. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("blockReason")
    public abstract Builder blockReason(String blockReason);

    @SerializedName("blockReason")
    abstract Builder blockReason(Optional<String> blockReason);

    @SerializedName("blockReasonMessage")
    public abstract Builder blockReasonMessage(String blockReasonMessage);

    @SerializedName("blockReasonMessage")
    abstract Builder blockReasonMessage(Optional<String> blockReasonMessage);

    @SerializedName("safetyRatings")
    public abstract Builder safetyRatings(List<SafetyRating> safetyRatings);

    @SerializedName("safetyRatings")
    abstract Builder safetyRatings(Optional<List<SafetyRating>> safetyRatings);

    public abstract GenerateContentResponsePromptFeedback build();
  }

  /** Deserializes a JSON string to a GenerateContentResponsePromptFeedback object. */
  public static GenerateContentResponsePromptFeedback fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerateContentResponsePromptFeedback.class);
  }
}
