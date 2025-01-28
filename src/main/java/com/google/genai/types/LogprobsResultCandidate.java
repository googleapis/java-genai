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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Candidate for the logprobs token and score. */
@AutoValue
@JsonDeserialize(builder = AutoValue_LogprobsResultCandidate.Builder.class)
public abstract class LogprobsResultCandidate extends JsonSerializable {
  /** The candidate's log probability. */
  public abstract Optional<Float> getLogProbability();

  /** The candidate's token string value. */
  public abstract Optional<String> getToken();

  /** The candidate's token id value. */
  public abstract Optional<Integer> getTokenId();

  /** Instantiates a builder for LogprobsResultCandidate. */
  public static Builder builder() {
    return new AutoValue_LogprobsResultCandidate.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for LogprobsResultCandidate. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("logProbability")
    public abstract Builder setLogProbability(Float logProbability);

    @JsonProperty("token")
    public abstract Builder setToken(String token);

    @JsonProperty("tokenId")
    public abstract Builder setTokenId(Integer tokenId);

    public abstract LogprobsResultCandidate build();
  }

  /** Deserializes a JSON string to a LogprobsResultCandidate object. */
  public static LogprobsResultCandidate fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, LogprobsResultCandidate.class);
  }
}
