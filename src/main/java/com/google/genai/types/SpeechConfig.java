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

/** The speech generation configuration. */
@GenerateTypeAdapter
@AutoValue
public abstract class SpeechConfig extends JsonSerializable {
  /** The configuration for the speaker to use. */
  @SerializedName("voiceConfig")
  public abstract Optional<VoiceConfig> voiceConfig();

  /** Instantiates a builder for SpeechConfig. */
  public static Builder builder() {
    return new AutoValue_SpeechConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for SpeechConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("voiceConfig")
    public abstract Builder voiceConfig(VoiceConfig voiceConfig);

    @SerializedName("voiceConfig")
    abstract Builder voiceConfig(Optional<VoiceConfig> voiceConfig);

    public abstract SpeechConfig build();
  }

  /** Deserializes a JSON string to a SpeechConfig object. */
  public static SpeechConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SpeechConfig.class);
  }
}
