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

/** Config for LiveConnectConstraints for Auth Token creation. */
@AutoValue
@JsonDeserialize(builder = LiveConnectConstraints.Builder.class)
public abstract class LiveConnectConstraints extends JsonSerializable {
  /**
   * ID of the model to configure in the ephemeral token for Live API. For a list of models, see
   * `Gemini models <https://ai.google.dev/gemini-api/docs/models>`.
   */
  @JsonProperty("model")
  public abstract Optional<String> model();

  /** Configuration specific to Live API connections created using this token. */
  @JsonProperty("config")
  public abstract Optional<LiveConnectConfig> config();

  /** Instantiates a builder for LiveConnectConstraints. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_LiveConnectConstraints.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for LiveConnectConstraints. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `LiveConnectConstraints.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_LiveConnectConstraints.Builder();
    }

    /**
     * Setter for model.
     *
     * <p>model: ID of the model to configure in the ephemeral token for Live API. For a list of
     * models, see `Gemini models <https://ai.google.dev/gemini-api/docs/models>`.
     */
    @JsonProperty("model")
    public abstract Builder model(String model);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder model(Optional<String> model);

    /** Clears the value of model field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearModel() {
      return model(Optional.empty());
    }

    /**
     * Setter for config.
     *
     * <p>config: Configuration specific to Live API connections created using this token.
     */
    @JsonProperty("config")
    public abstract Builder config(LiveConnectConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config: Configuration specific to Live API connections created using this token.
     */
    @CanIgnoreReturnValue
    public Builder config(LiveConnectConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder config(Optional<LiveConnectConfig> config);

    /** Clears the value of config field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearConfig() {
      return config(Optional.empty());
    }

    public abstract LiveConnectConstraints build();
  }

  /** Deserializes a JSON string to a LiveConnectConstraints object. */
  @ExcludeFromGeneratedCoverageReport
  public static LiveConnectConstraints fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, LiveConnectConstraints.class);
  }
}
