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
 * Define data stores within engine to filter on in a search call and configurations for those data
 * stores. For more information, see
 * https://cloud.google.com/generative-ai-app-builder/docs/reference/rpc/google.cloud.discoveryengine.v1#datastorespec.
 * This data type is not supported in Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = VertexAISearchDataStoreSpec.Builder.class)
public abstract class VertexAISearchDataStoreSpec extends JsonSerializable {
  /**
   * Full resource name of DataStore, such as Format:
   * `projects/{project}/locations/{location}/collections/{collection}/dataStores/{dataStore}`
   */
  @JsonProperty("dataStore")
  public abstract Optional<String> dataStore();

  /**
   * Optional. Filter specification to filter documents in the data store specified by data_store
   * field. For more information on filtering, see
   * [Filtering](https://cloud.google.com/generative-ai-app-builder/docs/filter-search-metadata)
   */
  @JsonProperty("filter")
  public abstract Optional<String> filter();

  /** Instantiates a builder for VertexAISearchDataStoreSpec. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_VertexAISearchDataStoreSpec.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for VertexAISearchDataStoreSpec. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `VertexAISearchDataStoreSpec.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_VertexAISearchDataStoreSpec.Builder();
    }

    /**
     * Setter for dataStore.
     *
     * <p>dataStore: Full resource name of DataStore, such as Format:
     * `projects/{project}/locations/{location}/collections/{collection}/dataStores/{dataStore}`
     */
    @JsonProperty("dataStore")
    public abstract Builder dataStore(String dataStore);

    /**
     * Setter for filter.
     *
     * <p>filter: Optional. Filter specification to filter documents in the data store specified by
     * data_store field. For more information on filtering, see
     * [Filtering](https://cloud.google.com/generative-ai-app-builder/docs/filter-search-metadata)
     */
    @JsonProperty("filter")
    public abstract Builder filter(String filter);

    public abstract VertexAISearchDataStoreSpec build();
  }

  /** Deserializes a JSON string to a VertexAISearchDataStoreSpec object. */
  @ExcludeFromGeneratedCoverageReport
  public static VertexAISearchDataStoreSpec fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, VertexAISearchDataStoreSpec.class);
  }
}
