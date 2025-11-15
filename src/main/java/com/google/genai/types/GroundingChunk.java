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

/**
 * A piece of evidence that supports a claim made by the model. This is used to show a citation for
 * a claim made by the model. When grounding is enabled, the model returns a `GroundingChunk` that
 * contains a reference to the source of the information.
 */
@AutoValue
@JsonDeserialize(builder = GroundingChunk.Builder.class)
public abstract class GroundingChunk extends JsonSerializable {
  /** A grounding chunk from Google Maps. See the `Maps` message for details. */
  @JsonProperty("maps")
  public abstract Optional<GroundingChunkMaps> maps();

  /**
   * A grounding chunk from a data source retrieved by a retrieval tool, such as Vertex AI Search.
   * See the `RetrievedContext` message for details
   */
  @JsonProperty("retrievedContext")
  public abstract Optional<GroundingChunkRetrievedContext> retrievedContext();

  /**
   * A grounding chunk from a web page, typically from Google Search. See the `Web` message for
   * details.
   */
  @JsonProperty("web")
  public abstract Optional<GroundingChunkWeb> web();

  /** Instantiates a builder for GroundingChunk. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GroundingChunk.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GroundingChunk. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `GroundingChunk.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GroundingChunk.Builder();
    }

    /**
     * Setter for maps.
     *
     * <p>maps: A grounding chunk from Google Maps. See the `Maps` message for details.
     */
    @JsonProperty("maps")
    public abstract Builder maps(GroundingChunkMaps maps);

    /**
     * Setter for maps builder.
     *
     * <p>maps: A grounding chunk from Google Maps. See the `Maps` message for details.
     */
    @CanIgnoreReturnValue
    public Builder maps(GroundingChunkMaps.Builder mapsBuilder) {
      return maps(mapsBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder maps(Optional<GroundingChunkMaps> maps);

    /** Clears the value of maps field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMaps() {
      return maps(Optional.empty());
    }

    /**
     * Setter for retrievedContext.
     *
     * <p>retrievedContext: A grounding chunk from a data source retrieved by a retrieval tool, such
     * as Vertex AI Search. See the `RetrievedContext` message for details
     */
    @JsonProperty("retrievedContext")
    public abstract Builder retrievedContext(GroundingChunkRetrievedContext retrievedContext);

    /**
     * Setter for retrievedContext builder.
     *
     * <p>retrievedContext: A grounding chunk from a data source retrieved by a retrieval tool, such
     * as Vertex AI Search. See the `RetrievedContext` message for details
     */
    @CanIgnoreReturnValue
    public Builder retrievedContext(
        GroundingChunkRetrievedContext.Builder retrievedContextBuilder) {
      return retrievedContext(retrievedContextBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder retrievedContext(Optional<GroundingChunkRetrievedContext> retrievedContext);

    /** Clears the value of retrievedContext field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRetrievedContext() {
      return retrievedContext(Optional.empty());
    }

    /**
     * Setter for web.
     *
     * <p>web: A grounding chunk from a web page, typically from Google Search. See the `Web`
     * message for details.
     */
    @JsonProperty("web")
    public abstract Builder web(GroundingChunkWeb web);

    /**
     * Setter for web builder.
     *
     * <p>web: A grounding chunk from a web page, typically from Google Search. See the `Web`
     * message for details.
     */
    @CanIgnoreReturnValue
    public Builder web(GroundingChunkWeb.Builder webBuilder) {
      return web(webBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder web(Optional<GroundingChunkWeb> web);

    /** Clears the value of web field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearWeb() {
      return web(Optional.empty());
    }

    public abstract GroundingChunk build();
  }

  /** Deserializes a JSON string to a GroundingChunk object. */
  @ExcludeFromGeneratedCoverageReport
  public static GroundingChunk fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GroundingChunk.class);
  }
}
