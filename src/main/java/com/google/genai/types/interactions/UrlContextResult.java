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
import com.google.genai.types.interactions.UrlContextResultStatus;
import java.util.Optional;

@AutoValue
@JsonDeserialize(builder = UrlContextResult.Builder.class)
public abstract class UrlContextResult extends JsonSerializable {

  @JsonProperty("url")
  public abstract Optional<String> url();

  @JsonProperty("status")
  public abstract Optional<UrlContextResultStatus> status();

  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_UrlContextResult.Builder();
  }

  public abstract Builder toBuilder();

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return new AutoValue_UrlContextResult.Builder();
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

    @JsonProperty("status")
    public abstract Builder status(UrlContextResultStatus status);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder status(Optional<UrlContextResultStatus> status);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearStatus() {
      return status(Optional.empty());
    }

    public abstract UrlContextResult build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static UrlContextResult fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, UrlContextResult.class);
  }

  @ExcludeFromGeneratedCoverageReport
  public static UrlContextResult of(String url, UrlContextResultStatus status) {
    return builder().url(url).status(status).build();
  }
}
