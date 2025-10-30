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

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** The configuration for the multi-speaker setup. This data type is not supported in Vertex AI. */
@AutoValue
@JsonDeserialize(builder = MultiSpeakerVoiceConfig.Builder.class)
public abstract class MultiSpeakerVoiceConfig extends JsonSerializable {
  /** Required. All the enabled speaker voices. */
  @JsonProperty("speakerVoiceConfigs")
  public abstract Optional<List<SpeakerVoiceConfig>> speakerVoiceConfigs();

  /** Instantiates a builder for MultiSpeakerVoiceConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_MultiSpeakerVoiceConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for MultiSpeakerVoiceConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `MultiSpeakerVoiceConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_MultiSpeakerVoiceConfig.Builder();
    }

    /**
     * Setter for speakerVoiceConfigs.
     *
     * <p>speakerVoiceConfigs: Required. All the enabled speaker voices.
     */
    @JsonProperty("speakerVoiceConfigs")
    public abstract Builder speakerVoiceConfigs(List<SpeakerVoiceConfig> speakerVoiceConfigs);

    /**
     * Setter for speakerVoiceConfigs.
     *
     * <p>speakerVoiceConfigs: Required. All the enabled speaker voices.
     */
    public Builder speakerVoiceConfigs(SpeakerVoiceConfig... speakerVoiceConfigs) {
      return speakerVoiceConfigs(Arrays.asList(speakerVoiceConfigs));
    }

    /**
     * Setter for speakerVoiceConfigs builder.
     *
     * <p>speakerVoiceConfigs: Required. All the enabled speaker voices.
     */
    public Builder speakerVoiceConfigs(SpeakerVoiceConfig.Builder... speakerVoiceConfigsBuilders) {
      return speakerVoiceConfigs(
          Arrays.asList(speakerVoiceConfigsBuilders).stream()
              .map(SpeakerVoiceConfig.Builder::build)
              .collect(toImmutableList()));
    }

    public abstract MultiSpeakerVoiceConfig build();
  }

  /** Deserializes a JSON string to a MultiSpeakerVoiceConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static MultiSpeakerVoiceConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, MultiSpeakerVoiceConfig.class);
  }
}
