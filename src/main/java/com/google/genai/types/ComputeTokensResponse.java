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

/** Response for computing tokens. */
@AutoValue
@JsonDeserialize(builder = ComputeTokensResponse.Builder.class)
public abstract class ComputeTokensResponse extends JsonSerializable {
  /** Used to retain the full HTTP response. */
  @JsonProperty("sdkHttpResponse")
  public abstract Optional<HttpResponse> sdkHttpResponse();

  /**
   * Lists of tokens info from the input. A ComputeTokensRequest could have multiple instances with
   * a prompt in each instance. We also need to return lists of tokens info for the request with
   * multiple instances.
   */
  @JsonProperty("tokensInfo")
  public abstract Optional<List<TokensInfo>> tokensInfo();

  /** Instantiates a builder for ComputeTokensResponse. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ComputeTokensResponse.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ComputeTokensResponse. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ComputeTokensResponse.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ComputeTokensResponse.Builder();
    }

    /**
     * Setter for sdkHttpResponse.
     *
     * <p>sdkHttpResponse: Used to retain the full HTTP response.
     */
    @JsonProperty("sdkHttpResponse")
    public abstract Builder sdkHttpResponse(HttpResponse sdkHttpResponse);

    /**
     * Setter for sdkHttpResponse builder.
     *
     * <p>sdkHttpResponse: Used to retain the full HTTP response.
     */
    public Builder sdkHttpResponse(HttpResponse.Builder sdkHttpResponseBuilder) {
      return sdkHttpResponse(sdkHttpResponseBuilder.build());
    }

    /**
     * Setter for tokensInfo.
     *
     * <p>tokensInfo: Lists of tokens info from the input. A ComputeTokensRequest could have
     * multiple instances with a prompt in each instance. We also need to return lists of tokens
     * info for the request with multiple instances.
     */
    @JsonProperty("tokensInfo")
    public abstract Builder tokensInfo(List<TokensInfo> tokensInfo);

    /**
     * Setter for tokensInfo.
     *
     * <p>tokensInfo: Lists of tokens info from the input. A ComputeTokensRequest could have
     * multiple instances with a prompt in each instance. We also need to return lists of tokens
     * info for the request with multiple instances.
     */
    public Builder tokensInfo(TokensInfo... tokensInfo) {
      return tokensInfo(Arrays.asList(tokensInfo));
    }

    /**
     * Setter for tokensInfo builder.
     *
     * <p>tokensInfo: Lists of tokens info from the input. A ComputeTokensRequest could have
     * multiple instances with a prompt in each instance. We also need to return lists of tokens
     * info for the request with multiple instances.
     */
    public Builder tokensInfo(TokensInfo.Builder... tokensInfoBuilders) {
      return tokensInfo(
          Arrays.asList(tokensInfoBuilders).stream()
              .map(TokensInfo.Builder::build)
              .collect(toImmutableList()));
    }

    public abstract ComputeTokensResponse build();
  }

  /** Deserializes a JSON string to a ComputeTokensResponse object. */
  @ExcludeFromGeneratedCoverageReport
  public static ComputeTokensResponse fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ComputeTokensResponse.class);
  }
}
