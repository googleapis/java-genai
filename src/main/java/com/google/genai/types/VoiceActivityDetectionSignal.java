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

/** None */
@AutoValue
@JsonDeserialize(builder = VoiceActivityDetectionSignal.Builder.class)
public abstract class VoiceActivityDetectionSignal extends JsonSerializable {
  /** The type of the VAD signal. */
  @JsonProperty("vadSignalType")
  public abstract Optional<VadSignalType> vadSignalType();

  /** Instantiates a builder for VoiceActivityDetectionSignal. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_VoiceActivityDetectionSignal.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for VoiceActivityDetectionSignal. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `VoiceActivityDetectionSignal.builder()` for instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_VoiceActivityDetectionSignal.Builder();
    }

    /**
     * Setter for vadSignalType.
     *
     * <p>vadSignalType: The type of the VAD signal.
     */
    @JsonProperty("vadSignalType")
    public abstract Builder vadSignalType(VadSignalType vadSignalType);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder vadSignalType(Optional<VadSignalType> vadSignalType);

    /** Clears the value of vadSignalType field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearVadSignalType() {
      return vadSignalType(Optional.empty());
    }

    /**
     * Setter for vadSignalType given a known enum.
     *
     * <p>vadSignalType: The type of the VAD signal.
     */
    @CanIgnoreReturnValue
    public Builder vadSignalType(VadSignalType.Known knownType) {
      return vadSignalType(new VadSignalType(knownType));
    }

    /**
     * Setter for vadSignalType given a string.
     *
     * <p>vadSignalType: The type of the VAD signal.
     */
    @CanIgnoreReturnValue
    public Builder vadSignalType(String vadSignalType) {
      return vadSignalType(new VadSignalType(vadSignalType));
    }

    public abstract VoiceActivityDetectionSignal build();
  }

  /** Deserializes a JSON string to a VoiceActivityDetectionSignal object. */
  @ExcludeFromGeneratedCoverageReport
  public static VoiceActivityDetectionSignal fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, VoiceActivityDetectionSignal.class);
  }
}
