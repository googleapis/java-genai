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

/** Describes the options to customize dynamic retrieval. */
@AutoValue
@JsonDeserialize(builder = AutoValue_DynamicRetrievalConfig.Builder.class)
public abstract class DynamicRetrievalConfig extends JsonSerializable {
  /** The mode of the predictor to be used in dynamic retrieval. */
  @JsonProperty("mode")
  public abstract Optional<String> mode();

  /**
   * Optional. The threshold to be used in dynamic retrieval. If not set, a system default value is
   * used.
   */
  @JsonProperty("dynamicThreshold")
  public abstract Optional<Float> dynamicThreshold();

  /** Instantiates a builder for DynamicRetrievalConfig. */
  public static Builder builder() {
    return new AutoValue_DynamicRetrievalConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for DynamicRetrievalConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("mode")
    public abstract Builder mode(String mode);

    @JsonProperty("dynamicThreshold")
    public abstract Builder dynamicThreshold(Float dynamicThreshold);

    public abstract DynamicRetrievalConfig build();
  }

  /** Deserializes a JSON string to a DynamicRetrievalConfig object. */
  public static DynamicRetrievalConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, DynamicRetrievalConfig.class);
  }
}
