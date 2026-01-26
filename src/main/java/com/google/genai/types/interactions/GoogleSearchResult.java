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

@AutoValue
@JsonDeserialize(builder = GoogleSearchResult.Builder.class)
public abstract class GoogleSearchResult extends JsonSerializable {

  @JsonProperty("url")
  public abstract Optional<String> url();

  @JsonProperty("title")
  public abstract Optional<String> title();

  @JsonProperty("rendered_content")
  public abstract Optional<String> renderedContent();

  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_GoogleSearchResult.Builder();
  }

  public abstract Builder toBuilder();

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return new AutoValue_GoogleSearchResult.Builder();
    }

    @JsonProperty("url")
    public abstract Builder url(String url);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder url(Optional<String> url);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearUrl() {
      return url(Optional.empty());
    }

    @JsonProperty("title")
    public abstract Builder title(String title);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder title(Optional<String> title);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTitle() {
      return title(Optional.empty());
    }

    @JsonProperty("rendered_content")
    public abstract Builder renderedContent(String renderedContent);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder renderedContent(Optional<String> renderedContent);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRenderedContent() {
      return renderedContent(Optional.empty());
    }

    public abstract GoogleSearchResult build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static GoogleSearchResult fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GoogleSearchResult.class);
  }

  @ExcludeFromGeneratedCoverageReport
  public static GoogleSearchResult of(String url, String title, String renderedContent) {
    return builder().url(url).title(title).renderedContent(renderedContent).build();
  }
}
