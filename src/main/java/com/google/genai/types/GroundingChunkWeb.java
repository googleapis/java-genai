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
 * A `Web` chunk is a piece of evidence that comes from a web page. It contains the URI of the web
 * page, the title of the page, and the domain of the page. This is used to provide the user with a
 * link to the source of the information.
 */
@AutoValue
@JsonDeserialize(builder = GroundingChunkWeb.Builder.class)
public abstract class GroundingChunkWeb extends JsonSerializable {
  /**
   * The domain of the web page that contains the evidence. This can be used to filter out
   * low-quality sources. This field is not supported in Gemini API.
   */
  @JsonProperty("domain")
  public abstract Optional<String> domain();

  /** The title of the web page that contains the evidence. */
  @JsonProperty("title")
  public abstract Optional<String> title();

  /** The URI of the web page that contains the evidence. */
  @JsonProperty("uri")
  public abstract Optional<String> uri();

  /** Instantiates a builder for GroundingChunkWeb. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GroundingChunkWeb.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GroundingChunkWeb. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `GroundingChunkWeb.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GroundingChunkWeb.Builder();
    }

    /**
     * Setter for domain.
     *
     * <p>domain: The domain of the web page that contains the evidence. This can be used to filter
     * out low-quality sources. This field is not supported in Gemini API.
     */
    @JsonProperty("domain")
    public abstract Builder domain(String domain);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder domain(Optional<String> domain);

    /** Clears the value of domain field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDomain() {
      return domain(Optional.empty());
    }

    /**
     * Setter for title.
     *
     * <p>title: The title of the web page that contains the evidence.
     */
    @JsonProperty("title")
    public abstract Builder title(String title);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder title(Optional<String> title);

    /** Clears the value of title field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTitle() {
      return title(Optional.empty());
    }

    /**
     * Setter for uri.
     *
     * <p>uri: The URI of the web page that contains the evidence.
     */
    @JsonProperty("uri")
    public abstract Builder uri(String uri);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder uri(Optional<String> uri);

    /** Clears the value of uri field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearUri() {
      return uri(Optional.empty());
    }

    public abstract GroundingChunkWeb build();
  }

  /** Deserializes a JSON string to a GroundingChunkWeb object. */
  @ExcludeFromGeneratedCoverageReport
  public static GroundingChunkWeb fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GroundingChunkWeb.class);
  }
}
