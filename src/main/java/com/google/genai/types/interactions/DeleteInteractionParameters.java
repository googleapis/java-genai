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

/** Parameters for interactions.delete method. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = DeleteInteractionParameters.Builder.class)
public abstract class DeleteInteractionParameters extends JsonSerializable {
  /** The ID of the interaction to delete. */
  @JsonProperty("id")
  public abstract Optional<String> id();

  /** Optional parameters for the request. */
  @JsonProperty("config")
  public abstract Optional<DeleteInteractionConfig> config();

  /** Instantiates a builder for DeleteInteractionParameters. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_DeleteInteractionParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for DeleteInteractionParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `DeleteInteractionParameters.builder()` for instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_DeleteInteractionParameters.Builder();
    }

    /**
     * Setter for id.
     *
     * <p>id: The ID of the interaction to delete.
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
    public abstract Builder config(DeleteInteractionConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config: Optional parameters for the request.
     */
    @CanIgnoreReturnValue
    public Builder config(DeleteInteractionConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder config(Optional<DeleteInteractionConfig> config);

    /** Clears the value of config field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearConfig() {
      return config(Optional.empty());
    }

    public abstract DeleteInteractionParameters build();
  }

  /** Deserializes a JSON string to a DeleteInteractionParameters object. */
  @ExcludeFromGeneratedCoverageReport
  public static DeleteInteractionParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, DeleteInteractionParameters.class);
  }
}
