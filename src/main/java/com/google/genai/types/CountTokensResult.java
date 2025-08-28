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
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Local tokenizer count tokens result. */
@AutoValue
@JsonDeserialize(builder = CountTokensResult.Builder.class)
public abstract class CountTokensResult extends JsonSerializable {
  /** The total number of tokens. */
  @JsonProperty("totalTokens")
  public abstract Optional<Integer> totalTokens();

  /** Instantiates a builder for CountTokensResult. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_CountTokensResult.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for CountTokensResult. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `CountTokensResult.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_CountTokensResult.Builder();
    }

    /**
     * Setter for totalTokens.
     *
     * <p>totalTokens: The total number of tokens.
     */
    @JsonProperty("totalTokens")
    public abstract Builder totalTokens(Integer totalTokens);

    public abstract CountTokensResult build();
  }

  /** Deserializes a JSON string to a CountTokensResult object. */
  @ExcludeFromGeneratedCoverageReport
  public static CountTokensResult fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, CountTokensResult.class);
  }
}
