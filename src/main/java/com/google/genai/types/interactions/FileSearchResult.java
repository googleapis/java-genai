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
@JsonDeserialize(builder = FileSearchResult.Builder.class)
public abstract class FileSearchResult extends JsonSerializable {

  @JsonProperty("title")
  public abstract Optional<String> title();

  @JsonProperty("text")
  public abstract Optional<String> text();

  @JsonProperty("file_search_store")
  public abstract Optional<String> fileSearchStore();

  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_FileSearchResult.Builder();
  }

  public abstract Builder toBuilder();

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return new AutoValue_FileSearchResult.Builder();
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

    @JsonProperty("text")
    public abstract Builder text(String text);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder text(Optional<String> text);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearText() {
      return text(Optional.empty());
    }

    @JsonProperty("file_search_store")
    public abstract Builder fileSearchStore(String fileSearchStore);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder fileSearchStore(Optional<String> fileSearchStore);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearFileSearchStore() {
      return fileSearchStore(Optional.empty());
    }

    public abstract FileSearchResult build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static FileSearchResult fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, FileSearchResult.class);
  }

  @ExcludeFromGeneratedCoverageReport
  public static FileSearchResult of(String title, String text, String fileSearchStore) {
    return builder().title(title).text(text).fileSearchStore(fileSearchStore).build();
  }
}
