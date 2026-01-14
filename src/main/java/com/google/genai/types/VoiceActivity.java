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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Voice activity signal. */
@AutoValue
@JsonDeserialize(builder = VoiceActivity.Builder.class)
public abstract class VoiceActivity extends JsonSerializable {
  /** The type of the voice activity signal. */
  @JsonProperty("voiceActivityType")
  public abstract Optional<VoiceActivityType> voiceActivityType();

  /** Instantiates a builder for VoiceActivity. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_VoiceActivity.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for VoiceActivity. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `VoiceActivity.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_VoiceActivity.Builder();
    }

    /**
     * Setter for voiceActivityType.
     *
     * <p>voiceActivityType: The type of the voice activity signal.
     */
    @JsonProperty("voiceActivityType")
    public abstract Builder voiceActivityType(VoiceActivityType voiceActivityType);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder voiceActivityType(Optional<VoiceActivityType> voiceActivityType);

    /** Clears the value of voiceActivityType field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearVoiceActivityType() {
      return voiceActivityType(Optional.empty());
    }

    /**
     * Setter for voiceActivityType given a known enum.
     *
     * <p>voiceActivityType: The type of the voice activity signal.
     */
    @CanIgnoreReturnValue
    public Builder voiceActivityType(VoiceActivityType.Known knownType) {
      return voiceActivityType(new VoiceActivityType(knownType));
    }

    /**
     * Setter for voiceActivityType given a string.
     *
     * <p>voiceActivityType: The type of the voice activity signal.
     */
    @CanIgnoreReturnValue
    public Builder voiceActivityType(String voiceActivityType) {
      return voiceActivityType(new VoiceActivityType(voiceActivityType));
    }

    public abstract VoiceActivity build();
  }

  /** Deserializes a JSON string to a VoiceActivity object. */
  @ExcludeFromGeneratedCoverageReport
  public static VoiceActivity fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, VoiceActivity.class);
  }
}
