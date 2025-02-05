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
import java.util.Optional;

/** Configuration for a Control reference image. */
@AutoValue
@JsonDeserialize(builder = AutoValue_ControlReferenceConfig.Builder.class)
public abstract class ControlReferenceConfig extends JsonSerializable {
  /** The type of control reference image to use. */
  @JsonProperty("controlType")
  public abstract Optional<String> controlType();

  /**
   * Defaults to False. When set to True, the control image will be computed by the model based on
   * the control type. When set to False, the control image must be provided by the user.
   */
  @JsonProperty("enableControlImageComputation")
  public abstract Optional<Boolean> enableControlImageComputation();

  /** Instantiates a builder for ControlReferenceConfig. */
  public static Builder builder() {
    return new AutoValue_ControlReferenceConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ControlReferenceConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("controlType")
    public abstract Builder controlType(String controlType);

    @JsonProperty("enableControlImageComputation")
    public abstract Builder enableControlImageComputation(boolean enableControlImageComputation);

    public abstract ControlReferenceConfig build();
  }

  /** Deserializes a JSON string to a ControlReferenceConfig object. */
  public static ControlReferenceConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ControlReferenceConfig.class);
  }
}
