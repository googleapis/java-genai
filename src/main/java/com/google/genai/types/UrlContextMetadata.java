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
 * Metadata returned when the model uses the `url_context` tool to get information from a
 * user-provided URL.
 */
@AutoValue
@JsonDeserialize(builder = UrlContextMetadata.Builder.class)
public abstract class UrlContextMetadata extends JsonSerializable {
  /** Output only. A list of URL metadata, with one entry for each URL retrieved by the tool. */
  @JsonProperty("urlMetadata")
  public abstract Optional<List<UrlMetadata>> urlMetadata();

  /** Instantiates a builder for UrlContextMetadata. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_UrlContextMetadata.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for UrlContextMetadata. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `UrlContextMetadata.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_UrlContextMetadata.Builder();
    }

    /**
     * Setter for urlMetadata.
     *
     * <p>urlMetadata: Output only. A list of URL metadata, with one entry for each URL retrieved by
     * the tool.
     */
    @JsonProperty("urlMetadata")
    public abstract Builder urlMetadata(List<UrlMetadata> urlMetadata);

    /**
     * Setter for urlMetadata.
     *
     * <p>urlMetadata: Output only. A list of URL metadata, with one entry for each URL retrieved by
     * the tool.
     */
    @CanIgnoreReturnValue
    public Builder urlMetadata(UrlMetadata... urlMetadata) {
      return urlMetadata(Arrays.asList(urlMetadata));
    }

    /**
     * Setter for urlMetadata builder.
     *
     * <p>urlMetadata: Output only. A list of URL metadata, with one entry for each URL retrieved by
     * the tool.
     */
    @CanIgnoreReturnValue
    public Builder urlMetadata(UrlMetadata.Builder... urlMetadataBuilders) {
      return urlMetadata(
          Arrays.asList(urlMetadataBuilders).stream()
              .map(UrlMetadata.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder urlMetadata(Optional<List<UrlMetadata>> urlMetadata);

    /** Clears the value of urlMetadata field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearUrlMetadata() {
      return urlMetadata(Optional.empty());
    }

    public abstract UrlContextMetadata build();
  }

  /** Deserializes a JSON string to a UrlContextMetadata object. */
  @ExcludeFromGeneratedCoverageReport
  public static UrlContextMetadata fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, UrlContextMetadata.class);
  }
}
