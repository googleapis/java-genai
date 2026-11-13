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
 * An entry point for displaying Google Search results. A `SearchEntryPoint` is populated when the
 * grounding source for a model's response is Google Search. It provides information that you can
 * use to display the search results in your application.
 */
@AutoValue
@JsonDeserialize(builder = SearchEntryPoint.Builder.class)
public abstract class SearchEntryPoint extends JsonSerializable {
  /**
   * Optional. An HTML snippet that can be embedded in a web page or an application's webview. This
   * snippet displays a search result, including the title, URL, and a brief description of the
   * search result.
   */
  @JsonProperty("renderedContent")
  public abstract Optional<String> renderedContent();

  /**
   * Optional. A base64-encoded JSON object that contains a list of search queries and their
   * corresponding search URLs. This information can be used to build a custom search UI.
   */
  @JsonProperty("sdkBlob")
  public abstract Optional<byte[]> sdkBlob();

  /** Instantiates a builder for SearchEntryPoint. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_SearchEntryPoint.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for SearchEntryPoint. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `SearchEntryPoint.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_SearchEntryPoint.Builder();
    }

    /**
     * Setter for renderedContent.
     *
     * <p>renderedContent: Optional. An HTML snippet that can be embedded in a web page or an
     * application's webview. This snippet displays a search result, including the title, URL, and a
     * brief description of the search result.
     */
    @JsonProperty("renderedContent")
    public abstract Builder renderedContent(String renderedContent);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder renderedContent(Optional<String> renderedContent);

    /** Clears the value of renderedContent field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRenderedContent() {
      return renderedContent(Optional.empty());
    }

    /**
     * Setter for sdkBlob.
     *
     * <p>sdkBlob: Optional. A base64-encoded JSON object that contains a list of search queries and
     * their corresponding search URLs. This information can be used to build a custom search UI.
     */
    @JsonProperty("sdkBlob")
    public abstract Builder sdkBlob(byte[] sdkBlob);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder sdkBlob(Optional<byte[]> sdkBlob);

    /** Clears the value of sdkBlob field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSdkBlob() {
      return sdkBlob(Optional.empty());
    }

    public abstract SearchEntryPoint build();
  }

  /** Deserializes a JSON string to a SearchEntryPoint object. */
  @ExcludeFromGeneratedCoverageReport
  public static SearchEntryPoint fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SearchEntryPoint.class);
  }
}
