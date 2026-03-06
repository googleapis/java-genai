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
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import com.google.genai.types.ExcludeFromGeneratedCoverageReport;
import java.util.Optional;

/**
 * Configuration for deleting an interaction in the Interactions API.
 *
 * <p>Optional parameters for the interactions.delete method.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * DeleteInteractionConfig config = DeleteInteractionConfig.builder()
 *     .apiVersion("v1alpha")
 *     .build();
 * client.interactions.delete("interaction-id", config);
 * }</pre>
 *
 * <p>The Interactions API is available in both Vertex AI and Gemini API.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = DeleteInteractionConfig.Builder.class)
public abstract class DeleteInteractionConfig extends JsonSerializable {
  /** Optional API version to use for this request. Overrides client-level api_version. */
  @JsonProperty("api_version")
  public abstract Optional<String> apiVersion();

  /** Instantiates a builder for DeleteInteractionConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_DeleteInteractionConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for DeleteInteractionConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `DeleteInteractionConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_DeleteInteractionConfig.Builder();
    }

    /**
     * Setter for api_version.
     *
     * <p>api_version: Optional API version to use for this request.
     */
    @JsonProperty("api_version")
    public abstract Builder apiVersion(String apiVersion);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder apiVersion(Optional<String> apiVersion);

    /** Clears the value of api_version field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearApiVersion() {
      return apiVersion(Optional.empty());
    }

    public abstract DeleteInteractionConfig build();
  }

  /** Deserializes a JSON string to a DeleteInteractionConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static DeleteInteractionConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, DeleteInteractionConfig.class);
  }
}
