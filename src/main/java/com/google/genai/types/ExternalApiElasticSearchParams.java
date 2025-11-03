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
 * The search parameters to use for the ELASTIC_SEARCH spec. This data type is not supported in
 * Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = ExternalApiElasticSearchParams.Builder.class)
public abstract class ExternalApiElasticSearchParams extends JsonSerializable {
  /** The ElasticSearch index to use. */
  @JsonProperty("index")
  public abstract Optional<String> index();

  /**
   * Optional. Number of hits (chunks) to request. When specified, it is passed to Elasticsearch as
   * the `num_hits` param.
   */
  @JsonProperty("numHits")
  public abstract Optional<Integer> numHits();

  /** The ElasticSearch search template to use. */
  @JsonProperty("searchTemplate")
  public abstract Optional<String> searchTemplate();

  /** Instantiates a builder for ExternalApiElasticSearchParams. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ExternalApiElasticSearchParams.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ExternalApiElasticSearchParams. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `ExternalApiElasticSearchParams.builder()` for instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ExternalApiElasticSearchParams.Builder();
    }

    /**
     * Setter for index.
     *
     * <p>index: The ElasticSearch index to use.
     */
    @JsonProperty("index")
    public abstract Builder index(String index);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder index(Optional<String> index);

    /** Clears the value of index field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearIndex() {
      return index(Optional.empty());
    }

    /**
     * Setter for numHits.
     *
     * <p>numHits: Optional. Number of hits (chunks) to request. When specified, it is passed to
     * Elasticsearch as the `num_hits` param.
     */
    @JsonProperty("numHits")
    public abstract Builder numHits(Integer numHits);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder numHits(Optional<Integer> numHits);

    /** Clears the value of numHits field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearNumHits() {
      return numHits(Optional.empty());
    }

    /**
     * Setter for searchTemplate.
     *
     * <p>searchTemplate: The ElasticSearch search template to use.
     */
    @JsonProperty("searchTemplate")
    public abstract Builder searchTemplate(String searchTemplate);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder searchTemplate(Optional<String> searchTemplate);

    /** Clears the value of searchTemplate field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSearchTemplate() {
      return searchTemplate(Optional.empty());
    }

    public abstract ExternalApiElasticSearchParams build();
  }

  /** Deserializes a JSON string to a ExternalApiElasticSearchParams object. */
  @ExcludeFromGeneratedCoverageReport
  public static ExternalApiElasticSearchParams fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ExternalApiElasticSearchParams.class);
  }
}
