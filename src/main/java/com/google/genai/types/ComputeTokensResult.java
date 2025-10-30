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
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** Local tokenizer compute tokens result. */
@AutoValue
@JsonDeserialize(builder = ComputeTokensResult.Builder.class)
public abstract class ComputeTokensResult extends JsonSerializable {
  /** Lists of tokens info from the input. */
  @JsonProperty("tokensInfo")
  public abstract Optional<List<TokensInfo>> tokensInfo();

  /** Instantiates a builder for ComputeTokensResult. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ComputeTokensResult.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ComputeTokensResult. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ComputeTokensResult.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ComputeTokensResult.Builder();
    }

    /**
     * Setter for tokensInfo.
     *
     * <p>tokensInfo: Lists of tokens info from the input.
     */
    @JsonProperty("tokensInfo")
    public abstract Builder tokensInfo(List<TokensInfo> tokensInfo);

    /**
     * Setter for tokensInfo.
     *
     * <p>tokensInfo: Lists of tokens info from the input.
     */
    public Builder tokensInfo(TokensInfo... tokensInfo) {
      return tokensInfo(Arrays.asList(tokensInfo));
    }

    /**
     * Setter for tokensInfo builder.
     *
     * <p>tokensInfo: Lists of tokens info from the input.
     */
    public Builder tokensInfo(TokensInfo.Builder... tokensInfoBuilders) {
      return tokensInfo(
          Arrays.asList(tokensInfoBuilders).stream()
              .map(TokensInfo.Builder::build)
              .collect(toImmutableList()));
    }

    public abstract ComputeTokensResult build();
  }

  /** Deserializes a JSON string to a ComputeTokensResult object. */
  @ExcludeFromGeneratedCoverageReport
  public static ComputeTokensResult fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ComputeTokensResult.class);
  }
}
