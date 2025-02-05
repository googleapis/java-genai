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
 * When automated routing is specified, the routing will be determined by the pretrained routing
 * model and customer provided model routing preference.
 */
@AutoValue
@JsonDeserialize(builder = AutoValue_GenerationConfigRoutingConfigAutoRoutingMode.Builder.class)
public abstract class GenerationConfigRoutingConfigAutoRoutingMode extends JsonSerializable {
  /** The model routing preference. */
  @JsonProperty("modelRoutingPreference")
  public abstract Optional<String> modelRoutingPreference();

  /** Instantiates a builder for GenerationConfigRoutingConfigAutoRoutingMode. */
  public static Builder builder() {
    return new AutoValue_GenerationConfigRoutingConfigAutoRoutingMode.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerationConfigRoutingConfigAutoRoutingMode. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("modelRoutingPreference")
    public abstract Builder modelRoutingPreference(String modelRoutingPreference);

    public abstract GenerationConfigRoutingConfigAutoRoutingMode build();
  }

  /** Deserializes a JSON string to a GenerationConfigRoutingConfigAutoRoutingMode object. */
  public static GenerationConfigRoutingConfigAutoRoutingMode fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(
        jsonString, GenerationConfigRoutingConfigAutoRoutingMode.class);
  }
}
