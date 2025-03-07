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

/** The configuration for routing the request to a specific model. */
@GenerateTypeAdapter
@AutoValue
public abstract class GenerationConfigRoutingConfig extends JsonSerializable {
  /** Automated routing. */
  @SerializedName("autoMode")
  public abstract Optional<GenerationConfigRoutingConfigAutoRoutingMode> autoMode();

  /** Manual routing. */
  @SerializedName("manualMode")
  public abstract Optional<GenerationConfigRoutingConfigManualRoutingMode> manualMode();

  /** Instantiates a builder for GenerationConfigRoutingConfig. */
  public static Builder builder() {
    return new AutoValue_GenerationConfigRoutingConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerationConfigRoutingConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("autoMode")
    public abstract Builder autoMode(GenerationConfigRoutingConfigAutoRoutingMode autoMode);

    @SerializedName("autoMode")
    abstract Builder autoMode(Optional<GenerationConfigRoutingConfigAutoRoutingMode> autoMode);

    @SerializedName("manualMode")
    public abstract Builder manualMode(GenerationConfigRoutingConfigManualRoutingMode manualMode);

    @SerializedName("manualMode")
    abstract Builder manualMode(
        Optional<GenerationConfigRoutingConfigManualRoutingMode> manualMode);

    public abstract GenerationConfigRoutingConfig build();
  }

  /** Deserializes a JSON string to a GenerationConfigRoutingConfig object. */
  public static GenerationConfigRoutingConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerationConfigRoutingConfig.class);
  }
}
