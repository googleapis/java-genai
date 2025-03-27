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

/** Configures automatic detection of activity. */
@AutoValue
@JsonDeserialize(builder = AutomaticActivityDetection.Builder.class)
public abstract class AutomaticActivityDetection extends JsonSerializable {
  /**
   * If enbled, detected voice and text input count as activity. If disabled, the client must send
   * activity signals.
   */
  @JsonProperty("enabled")
  public abstract Optional<Boolean> enabled();

  /** Instantiates a builder for AutomaticActivityDetection. */
  public static Builder builder() {
    return new AutoValue_AutomaticActivityDetection.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for AutomaticActivityDetection. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `AutomaticActivityDetection.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_AutomaticActivityDetection.Builder();
    }

    @JsonProperty("enabled")
    public abstract Builder enabled(boolean enabled);

    public abstract AutomaticActivityDetection build();
  }

  /** Deserializes a JSON string to a AutomaticActivityDetection object. */
  public static AutomaticActivityDetection fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, AutomaticActivityDetection.class);
  }
}
