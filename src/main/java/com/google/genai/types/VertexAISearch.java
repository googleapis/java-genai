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

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Retrieve from Vertex AI Search datastore or engine for grounding. datastore and engine are
 * mutually exclusive. See https://cloud.google.com/products/agent-builder. This data type is not
 * supported in Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = VertexAISearch.Builder.class)
public abstract class VertexAISearch extends JsonSerializable {
  /**
   * Specifications that define the specific DataStores to be searched, along with configurations
   * for those data stores. This is only considered for Engines with multiple data stores. It should
   * only be set if engine is used.
   */
  @JsonProperty("dataStoreSpecs")
  public abstract Optional<List<VertexAISearchDataStoreSpec>> dataStoreSpecs();

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

  /** Optional. Filter strings to be passed to the search API. */
  @JsonProperty("filter")
  public abstract Optional<String> filter();

  /**
   * Optional. Number of search results to return per query. The default value is 10. The maximumm
   * allowed value is 10.
   */
  @JsonProperty("maxResults")
  public abstract Optional<Integer> maxResults();

  /** Instantiates a builder for VertexAISearch. */
  @ExcludeFromGeneratedCoverageReport
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

    /**
     * Setter for dataStoreSpecs.
     *
     * <p>dataStoreSpecs: Specifications that define the specific DataStores to be searched, along
     * with configurations for those data stores. This is only considered for Engines with multiple
     * data stores. It should only be set if engine is used.
     */
    @JsonProperty("dataStoreSpecs")
    public abstract Builder dataStoreSpecs(List<VertexAISearchDataStoreSpec> dataStoreSpecs);

    /**
     * Setter for dataStoreSpecs.
     *
     * <p>dataStoreSpecs: Specifications that define the specific DataStores to be searched, along
     * with configurations for those data stores. This is only considered for Engines with multiple
     * data stores. It should only be set if engine is used.
     */
    @CanIgnoreReturnValue
    public Builder dataStoreSpecs(VertexAISearchDataStoreSpec... dataStoreSpecs) {
      return dataStoreSpecs(Arrays.asList(dataStoreSpecs));
    }

    /**
     * Setter for dataStoreSpecs builder.
     *
     * <p>dataStoreSpecs: Specifications that define the specific DataStores to be searched, along
     * with configurations for those data stores. This is only considered for Engines with multiple
     * data stores. It should only be set if engine is used.
     */
    @CanIgnoreReturnValue
    public Builder dataStoreSpecs(VertexAISearchDataStoreSpec.Builder... dataStoreSpecsBuilders) {
      return dataStoreSpecs(
          Arrays.asList(dataStoreSpecsBuilders).stream()
              .map(VertexAISearchDataStoreSpec.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder dataStoreSpecs(Optional<List<VertexAISearchDataStoreSpec>> dataStoreSpecs);

    /** Clears the value of dataStoreSpecs field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDataStoreSpecs() {
      return dataStoreSpecs(Optional.empty());
    }

    /**
     * Setter for datastore.
     *
     * <p>datastore: Optional. Fully-qualified Vertex AI Search data store resource ID. Format:
     * `projects/{project}/locations/{location}/collections/{collection}/dataStores/{dataStore}`
     */
    @JsonProperty("datastore")
    public abstract Builder datastore(String datastore);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder datastore(Optional<String> datastore);

    /** Clears the value of datastore field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDatastore() {
      return datastore(Optional.empty());
    }

    /**
     * Setter for engine.
     *
     * <p>engine: Optional. Fully-qualified Vertex AI Search engine resource ID. Format:
     * `projects/{project}/locations/{location}/collections/{collection}/engines/{engine}`
     */
    @JsonProperty("engine")
    public abstract Builder engine(String engine);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder engine(Optional<String> engine);

    /** Clears the value of engine field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEngine() {
      return engine(Optional.empty());
    }

    /**
     * Setter for filter.
     *
     * <p>filter: Optional. Filter strings to be passed to the search API.
     */
    @JsonProperty("filter")
    public abstract Builder filter(String filter);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder filter(Optional<String> filter);

    /** Clears the value of filter field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearFilter() {
      return filter(Optional.empty());
    }

    /**
     * Setter for maxResults.
     *
     * <p>maxResults: Optional. Number of search results to return per query. The default value is
     * 10. The maximumm allowed value is 10.
     */
    @JsonProperty("maxResults")
    public abstract Builder maxResults(Integer maxResults);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder maxResults(Optional<Integer> maxResults);

    /** Clears the value of maxResults field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMaxResults() {
      return maxResults(Optional.empty());
    }

    public abstract VertexAISearch build();
  }

  /** Deserializes a JSON string to a VertexAISearch object. */
  @ExcludeFromGeneratedCoverageReport
  public static VertexAISearch fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, VertexAISearch.class);
  }
}
