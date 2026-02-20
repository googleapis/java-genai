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

package com.google.genai.types.interactions.streaming.delta;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import com.google.genai.types.ExcludeFromGeneratedCoverageReport;
import java.util.Optional;

/**
 * Represents an incremental file search call update in a streaming response.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = FileSearchCallDelta.Builder.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("file_search_call")
public abstract class FileSearchCallDelta extends JsonSerializable implements Delta {

  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_FileSearchCallDelta.Builder();
  }

  public abstract Builder toBuilder();

  /** Returns the unique identifier for this file search call. */
  @JsonProperty("id")
  public abstract Optional<String> id();

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return new AutoValue_FileSearchCallDelta.Builder();
    }

    @JsonProperty("id")
    public abstract Builder id(String id);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder id(Optional<String> id);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearId() {
      return id(Optional.empty());
    }

    public abstract FileSearchCallDelta build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static FileSearchCallDelta fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, FileSearchCallDelta.class);
  }
}
