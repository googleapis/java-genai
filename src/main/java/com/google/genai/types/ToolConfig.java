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

/**
 * Tool config.
 *
 * <p>This config is shared for all tools provided in the request.
 */
@AutoValue
@JsonDeserialize(builder = AutoValue_ToolConfig.Builder.class)
public abstract class ToolConfig extends JsonSerializable {
  /** Optional. Function calling config. */
  @JsonProperty("functionCallingConfig")
  public abstract Optional<FunctionCallingConfig> functionCallingConfig();

  /** Instantiates a builder for ToolConfig. */
  public static Builder builder() {
    return new AutoValue_ToolConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ToolConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("functionCallingConfig")
    public abstract Builder functionCallingConfig(FunctionCallingConfig functionCallingConfig);

    public abstract ToolConfig build();
  }

  /** Deserializes a JSON string to a ToolConfig object. */
  public static ToolConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ToolConfig.class);
  }
}
