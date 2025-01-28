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
import java.util.List;
import java.util.Optional;

/** Content filter results for a prompt sent in the request. */
@AutoValue
@JsonDeserialize(builder = AutoValue_GenerateContentResponsePromptFeedback.Builder.class)
public abstract class GenerateContentResponsePromptFeedback extends JsonSerializable {
  /** Output only. Blocked reason. */
  public abstract Optional<String> getBlockReason();

  /** Output only. A readable block reason message. */
  public abstract Optional<String> getBlockReasonMessage();

  /** Output only. Safety ratings. */
  public abstract Optional<List<SafetyRating>> getSafetyRatings();

  /** Instantiates a builder for GenerateContentResponsePromptFeedback. */
  public static Builder builder() {
    return new AutoValue_GenerateContentResponsePromptFeedback.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerateContentResponsePromptFeedback. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("blockReason")
    public abstract Builder setBlockReason(String blockReason);

    @JsonProperty("blockReasonMessage")
    public abstract Builder setBlockReasonMessage(String blockReasonMessage);

    @JsonProperty("safetyRatings")
    public abstract Builder setSafetyRatings(List<SafetyRating> safetyRatings);

    public abstract GenerateContentResponsePromptFeedback build();
  }

  /** Deserializes a JSON string to a GenerateContentResponsePromptFeedback object. */
  public static GenerateContentResponsePromptFeedback fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerateContentResponsePromptFeedback.class);
  }
}
