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
import com.google.genai.JsonSerializable;
import java.util.Optional;

/**
 * Retrieve from Vertex AI Search datastore or engine for grounding. datastore and engine are
 * mutually exclusive. See https://cloud.google.com/products/agent-builder
 */
@AutoValue
@JsonDeserialize(builder = VertexAISearch.Builder.class)
public abstract class VertexAISearch extends JsonSerializable {
  /**
   * Optional. Fully-qualified Vertex AI Search data store resource ID. Format:
   * `projects/{project}/locations/{location}/collections/{collection}/dataStores/{dataStore}`
   */
  @JsonProperty("datastore")
  public abstract Optional<String> datastore();

  /**
   * Optional. Fully-qualified Vertex AI Search engine resource ID. Format:
   * `projects/{project}/locations/{location}/collections/{collection}/engines/{engine}`
   */
  @JsonProperty("engine")
  public abstract Optional<String> engine();

  /** Instantiates a builder for VertexAISearch. */
  public static Builder builder() {
    return new AutoValue_VertexAISearch.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for VertexAISearch. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `VertexAISearch.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_VertexAISearch.Builder();
    }

    @JsonProperty("datastore")
    public abstract Builder datastore(String datastore);

    @JsonProperty("engine")
    public abstract Builder engine(String engine);

    public abstract VertexAISearch build();
  }

  /** Deserializes a JSON string to a VertexAISearch object. */
  public static VertexAISearch fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, VertexAISearch.class);
  }
}
