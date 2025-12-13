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

/** Metadata returned to client when grounding is enabled. */
@AutoValue
@JsonDeserialize(builder = GroundingMetadata.Builder.class)
public abstract class GroundingMetadata extends JsonSerializable {
  /** List of supporting references retrieved from specified grounding source. */
  @JsonProperty("groundingChunks")
  public abstract Optional<List<GroundingChunk>> groundingChunks();

  /** List of grounding support. */
  @JsonProperty("groundingSupports")
  public abstract Optional<List<GroundingSupport>> groundingSupports();

  /** Metadata related to retrieval in the grounding flow. */
  @JsonProperty("retrievalMetadata")
  public abstract Optional<RetrievalMetadata> retrievalMetadata();

  /** Optional. Google search entry for the following-up web searches. */
  @JsonProperty("searchEntryPoint")
  public abstract Optional<SearchEntryPoint> searchEntryPoint();

  /** Web search queries for the following-up web search. */
  @JsonProperty("webSearchQueries")
  public abstract Optional<List<String>> webSearchQueries();

  /**
   * Optional. Output only. A token that can be used to render a Google Maps widget with the
   * contextual data. This field is populated only when the grounding source is Google Maps.
   */
  @JsonProperty("googleMapsWidgetContextToken")
  public abstract Optional<String> googleMapsWidgetContextToken();

  /**
   * Optional. The queries that were executed by the retrieval tools. This field is populated only
   * when the grounding source is a retrieval tool, such as Vertex AI Search. This field is not
   * supported in Gemini API.
   */
  @JsonProperty("retrievalQueries")
  public abstract Optional<List<String>> retrievalQueries();

  /**
   * Optional. Output only. A list of URIs that can be used to flag a place or review for
   * inappropriate content. This field is populated only when the grounding source is Google Maps.
   * This field is not supported in Gemini API.
   */
  @JsonProperty("sourceFlaggingUris")
  public abstract Optional<List<GroundingMetadataSourceFlaggingUri>> sourceFlaggingUris();

  /** Instantiates a builder for GroundingMetadata. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GroundingMetadata.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GroundingMetadata. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `GroundingMetadata.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GroundingMetadata.Builder();
    }

    /**
     * Setter for groundingChunks.
     *
     * <p>groundingChunks: List of supporting references retrieved from specified grounding source.
     */
    @JsonProperty("groundingChunks")
    public abstract Builder groundingChunks(List<GroundingChunk> groundingChunks);

    /**
     * Setter for groundingChunks.
     *
     * <p>groundingChunks: List of supporting references retrieved from specified grounding source.
     */
    @CanIgnoreReturnValue
    public Builder groundingChunks(GroundingChunk... groundingChunks) {
      return groundingChunks(Arrays.asList(groundingChunks));
    }

    /**
     * Setter for groundingChunks builder.
     *
     * <p>groundingChunks: List of supporting references retrieved from specified grounding source.
     */
    @CanIgnoreReturnValue
    public Builder groundingChunks(GroundingChunk.Builder... groundingChunksBuilders) {
      return groundingChunks(
          Arrays.asList(groundingChunksBuilders).stream()
              .map(GroundingChunk.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder groundingChunks(Optional<List<GroundingChunk>> groundingChunks);

    /** Clears the value of groundingChunks field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearGroundingChunks() {
      return groundingChunks(Optional.empty());
    }

    /**
     * Setter for groundingSupports.
     *
     * <p>groundingSupports: List of grounding support.
     */
    @JsonProperty("groundingSupports")
    public abstract Builder groundingSupports(List<GroundingSupport> groundingSupports);

    /**
     * Setter for groundingSupports.
     *
     * <p>groundingSupports: List of grounding support.
     */
    @CanIgnoreReturnValue
    public Builder groundingSupports(GroundingSupport... groundingSupports) {
      return groundingSupports(Arrays.asList(groundingSupports));
    }

    /**
     * Setter for groundingSupports builder.
     *
     * <p>groundingSupports: List of grounding support.
     */
    @CanIgnoreReturnValue
    public Builder groundingSupports(GroundingSupport.Builder... groundingSupportsBuilders) {
      return groundingSupports(
          Arrays.asList(groundingSupportsBuilders).stream()
              .map(GroundingSupport.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder groundingSupports(Optional<List<GroundingSupport>> groundingSupports);

    /** Clears the value of groundingSupports field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearGroundingSupports() {
      return groundingSupports(Optional.empty());
    }

    /**
     * Setter for retrievalMetadata.
     *
     * <p>retrievalMetadata: Metadata related to retrieval in the grounding flow.
     */
    @JsonProperty("retrievalMetadata")
    public abstract Builder retrievalMetadata(RetrievalMetadata retrievalMetadata);

    /**
     * Setter for retrievalMetadata builder.
     *
     * <p>retrievalMetadata: Metadata related to retrieval in the grounding flow.
     */
    @CanIgnoreReturnValue
    public Builder retrievalMetadata(RetrievalMetadata.Builder retrievalMetadataBuilder) {
      return retrievalMetadata(retrievalMetadataBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder retrievalMetadata(Optional<RetrievalMetadata> retrievalMetadata);

    /** Clears the value of retrievalMetadata field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRetrievalMetadata() {
      return retrievalMetadata(Optional.empty());
    }

    /**
     * Setter for searchEntryPoint.
     *
     * <p>searchEntryPoint: Optional. Google search entry for the following-up web searches.
     */
    @JsonProperty("searchEntryPoint")
    public abstract Builder searchEntryPoint(SearchEntryPoint searchEntryPoint);

    /**
     * Setter for searchEntryPoint builder.
     *
     * <p>searchEntryPoint: Optional. Google search entry for the following-up web searches.
     */
    @CanIgnoreReturnValue
    public Builder searchEntryPoint(SearchEntryPoint.Builder searchEntryPointBuilder) {
      return searchEntryPoint(searchEntryPointBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder searchEntryPoint(Optional<SearchEntryPoint> searchEntryPoint);

    /** Clears the value of searchEntryPoint field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSearchEntryPoint() {
      return searchEntryPoint(Optional.empty());
    }

    /**
     * Setter for webSearchQueries.
     *
     * <p>webSearchQueries: Web search queries for the following-up web search.
     */
    @JsonProperty("webSearchQueries")
    public abstract Builder webSearchQueries(List<String> webSearchQueries);

    /**
     * Setter for webSearchQueries.
     *
     * <p>webSearchQueries: Web search queries for the following-up web search.
     */
    @CanIgnoreReturnValue
    public Builder webSearchQueries(String... webSearchQueries) {
      return webSearchQueries(Arrays.asList(webSearchQueries));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder webSearchQueries(Optional<List<String>> webSearchQueries);

    /** Clears the value of webSearchQueries field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearWebSearchQueries() {
      return webSearchQueries(Optional.empty());
    }

    /**
     * Setter for googleMapsWidgetContextToken.
     *
     * <p>googleMapsWidgetContextToken: Optional. Output only. A token that can be used to render a
     * Google Maps widget with the contextual data. This field is populated only when the grounding
     * source is Google Maps.
     */
    @JsonProperty("googleMapsWidgetContextToken")
    public abstract Builder googleMapsWidgetContextToken(String googleMapsWidgetContextToken);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder googleMapsWidgetContextToken(Optional<String> googleMapsWidgetContextToken);

    /** Clears the value of googleMapsWidgetContextToken field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearGoogleMapsWidgetContextToken() {
      return googleMapsWidgetContextToken(Optional.empty());
    }

    /**
     * Setter for retrievalQueries.
     *
     * <p>retrievalQueries: Optional. The queries that were executed by the retrieval tools. This
     * field is populated only when the grounding source is a retrieval tool, such as Vertex AI
     * Search. This field is not supported in Gemini API.
     */
    @JsonProperty("retrievalQueries")
    public abstract Builder retrievalQueries(List<String> retrievalQueries);

    /**
     * Setter for retrievalQueries.
     *
     * <p>retrievalQueries: Optional. The queries that were executed by the retrieval tools. This
     * field is populated only when the grounding source is a retrieval tool, such as Vertex AI
     * Search. This field is not supported in Gemini API.
     */
    @CanIgnoreReturnValue
    public Builder retrievalQueries(String... retrievalQueries) {
      return retrievalQueries(Arrays.asList(retrievalQueries));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder retrievalQueries(Optional<List<String>> retrievalQueries);

    /** Clears the value of retrievalQueries field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRetrievalQueries() {
      return retrievalQueries(Optional.empty());
    }

    /**
     * Setter for sourceFlaggingUris.
     *
     * <p>sourceFlaggingUris: Optional. Output only. A list of URIs that can be used to flag a place
     * or review for inappropriate content. This field is populated only when the grounding source
     * is Google Maps. This field is not supported in Gemini API.
     */
    @JsonProperty("sourceFlaggingUris")
    public abstract Builder sourceFlaggingUris(
        List<GroundingMetadataSourceFlaggingUri> sourceFlaggingUris);

    /**
     * Setter for sourceFlaggingUris.
     *
     * <p>sourceFlaggingUris: Optional. Output only. A list of URIs that can be used to flag a place
     * or review for inappropriate content. This field is populated only when the grounding source
     * is Google Maps. This field is not supported in Gemini API.
     */
    @CanIgnoreReturnValue
    public Builder sourceFlaggingUris(GroundingMetadataSourceFlaggingUri... sourceFlaggingUris) {
      return sourceFlaggingUris(Arrays.asList(sourceFlaggingUris));
    }

    /**
     * Setter for sourceFlaggingUris builder.
     *
     * <p>sourceFlaggingUris: Optional. Output only. A list of URIs that can be used to flag a place
     * or review for inappropriate content. This field is populated only when the grounding source
     * is Google Maps. This field is not supported in Gemini API.
     */
    @CanIgnoreReturnValue
    public Builder sourceFlaggingUris(
        GroundingMetadataSourceFlaggingUri.Builder... sourceFlaggingUrisBuilders) {
      return sourceFlaggingUris(
          Arrays.asList(sourceFlaggingUrisBuilders).stream()
              .map(GroundingMetadataSourceFlaggingUri.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder sourceFlaggingUris(
        Optional<List<GroundingMetadataSourceFlaggingUri>> sourceFlaggingUris);

    /** Clears the value of sourceFlaggingUris field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSourceFlaggingUris() {
      return sourceFlaggingUris(Optional.empty());
    }

    public abstract GroundingMetadata build();
  }

  /** Deserializes a JSON string to a GroundingMetadata object. */
  @ExcludeFromGeneratedCoverageReport
  public static GroundingMetadata fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GroundingMetadata.class);
  }
}
