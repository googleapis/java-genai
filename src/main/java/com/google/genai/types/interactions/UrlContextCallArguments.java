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
import java.util.List;
import java.util.Optional;

@AutoValue
@JsonDeserialize(builder = UrlContextCallArguments.Builder.class)
public abstract class UrlContextCallArguments extends JsonSerializable {

  @JsonProperty("urls")
  public abstract Optional<List<String>> urls();

  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_UrlContextCallArguments.Builder();
  }

  public abstract Builder toBuilder();

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return new AutoValue_UrlContextCallArguments.Builder();
    }

    @JsonProperty("urls")
    public abstract Builder urls(List<String> urls);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder urls(Optional<List<String>> urls);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearUrls() {
      return urls(Optional.empty());
    }

    public abstract UrlContextCallArguments build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static UrlContextCallArguments fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, UrlContextCallArguments.class);
  }

  @ExcludeFromGeneratedCoverageReport
  public static UrlContextCallArguments of(List<String> urls) {
    return builder().urls(urls).build();
  }
}
