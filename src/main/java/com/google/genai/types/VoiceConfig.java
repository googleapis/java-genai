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
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** The configuration for the voice to use. */
@AutoValue
@JsonDeserialize(builder = VoiceConfig.Builder.class)
public abstract class VoiceConfig extends JsonSerializable {
  /** The configuration for the prebuilt voice to use. */
  @JsonProperty("prebuiltVoiceConfig")
  public abstract Optional<PrebuiltVoiceConfig> prebuiltVoiceConfig();

  /** Instantiates a builder for VoiceConfig. */
  public static Builder builder() {
    return new AutoValue_VoiceConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for VoiceConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `VoiceConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_VoiceConfig.Builder();
    }

    @JsonProperty("prebuiltVoiceConfig")
    public abstract Builder prebuiltVoiceConfig(PrebuiltVoiceConfig prebuiltVoiceConfig);

    public abstract VoiceConfig build();
  }

  /** Deserializes a JSON string to a VoiceConfig object. */
  public static VoiceConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, VoiceConfig.class);
  }
}
