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

package com.google.genai.types.interactions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.api.core.InternalApi;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import com.google.genai.types.ExcludeFromGeneratedCoverageReport;
import java.util.Optional;

/** Parameters for interactions.get method. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = GetInteractionParameters.Builder.class)
public abstract class GetInteractionParameters extends JsonSerializable {
  /** The ID of the interaction to retrieve. */
  @JsonProperty("id")
  public abstract Optional<String> id();

  /** Optional parameters for the request. */
  @JsonProperty("config")
  public abstract Optional<GetInteractionConfig> config();

  /** Instantiates a builder for GetInteractionParameters. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GetInteractionParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GetInteractionParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `GetInteractionParameters.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GetInteractionParameters.Builder();
    }

    /**
     * Setter for id.
     *
     * <p>id: The ID of the interaction to retrieve.
     */
    @JsonProperty("id")
    public abstract Builder id(String id);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder id(Optional<String> id);

    /** Clears the value of id field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearId() {
      return id(Optional.empty());
    }

    /**
     * Setter for config.
     *
     * <p>config: Optional parameters for the request.
     */
    @JsonProperty("config")
    public abstract Builder config(GetInteractionConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config: Optional parameters for the request.
     */
    @CanIgnoreReturnValue
    public Builder config(GetInteractionConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder config(Optional<GetInteractionConfig> config);

    /** Clears the value of config field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearConfig() {
      return config(Optional.empty());
    }

    public abstract GetInteractionParameters build();
  }

  /** Deserializes a JSON string to a GetInteractionParameters object. */
  @ExcludeFromGeneratedCoverageReport
  public static GetInteractionParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GetInteractionParameters.class);
  }
}
