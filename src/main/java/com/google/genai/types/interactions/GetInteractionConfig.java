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
 * Configuration for retrieving an interaction, with support for both get and getStream operations.
 *
 * <p>This configuration supports stream resumption by allowing you to specify the last event ID
 * received. When resuming, the stream will continue from after that event.
 *
 * <p>Example usage for stream resumption:
 *
 * <pre>{@code
 * String lastEventId = null;
 *
 * // First attempt - connection may be interrupted
 * try (var stream = client.interactions.createStream(config)) {
 *     for (InteractionSseEvent event : stream) {
 *         lastEventId = event.eventId().orElse(lastEventId);
 *         // Process event...
 *     }
 * } catch (Exception e) {
 *     // Connection lost - resume from last known event
 *     GetInteractionConfig resumeConfig = GetInteractionConfig.builder()
 *         .lastEventId(lastEventId)
 *         .build();
 *     try (var stream = client.interactions.getStream(interactionId, resumeConfig)) {
 *         for (InteractionSseEvent event : stream) {
 *             // Continue processing from where we left off...
 *         }
 *     }
 * }
 * }</pre>
 *
 * <p>Note: The {@code lastEventId} parameter is only used by the {@code getStream()} method for
 * stream resumption. It is ignored by the {@code get()} method.
 *
 * <p>The Interactions API is available in both Vertex AI and Gemini API.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = GetInteractionConfig.Builder.class)
public abstract class GetInteractionConfig extends JsonSerializable {
  /**
   * The ID of the last event received.
   *
   * <p>When provided, the stream will resume from after this event. This is useful for recovering
   * from connection interruptions without missing or re-processing events.
   *
   * <p>Note: This parameter is only used by {@code getStream()}. It is ignored by {@code get()}.
   */
  @JsonProperty("last_event_id")
  public abstract Optional<String> lastEventId();

  /** Optional API version to use for this request. Overrides client-level api_version. */
  @JsonProperty("api_version")
  public abstract Optional<String> apiVersion();

  /** Instantiates a builder for GetInteractionConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GetInteractionConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GetInteractionConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `GetInteractionConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GetInteractionConfig.Builder();
    }

    /**
     * Setter for lastEventId.
     *
     * <p>lastEventId: The ID of the last event received, for stream resumption.
     */
    @JsonProperty("last_event_id")
    public abstract Builder lastEventId(String lastEventId);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder lastEventId(Optional<String> lastEventId);

    /** Clears the value of lastEventId field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearLastEventId() {
      return lastEventId(Optional.empty());
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

    public abstract GetInteractionConfig build();
  }

  /** Deserializes a JSON string to a GetInteractionConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static GetInteractionConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GetInteractionConfig.class);
  }
}
