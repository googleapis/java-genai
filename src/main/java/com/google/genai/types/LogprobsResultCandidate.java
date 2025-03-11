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

import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.util.Optional;

/** Candidate for the logprobs token and score. */
@GenerateTypeAdapter
@AutoValue
public abstract class LogprobsResultCandidate extends JsonSerializable {
  /** The candidate's log probability. */
  @SerializedName("logProbability")
  public abstract Optional<Float> logProbability();

  /** The candidate's token string value. */
  @SerializedName("token")
  public abstract Optional<String> token();

  /** The candidate's token id value. */
  @SerializedName("tokenId")
  public abstract Optional<Integer> tokenId();

  /** Instantiates a builder for LogprobsResultCandidate. */
  public static Builder builder() {
    return new AutoValue_LogprobsResultCandidate.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for LogprobsResultCandidate. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("logProbability")
    public abstract Builder logProbability(Float logProbability);

    @SerializedName("logProbability")
    abstract Builder logProbability(Optional<Float> logProbability);

    @SerializedName("token")
    public abstract Builder token(String token);

    @SerializedName("token")
    abstract Builder token(Optional<String> token);

    @SerializedName("tokenId")
    public abstract Builder tokenId(Integer tokenId);

    @SerializedName("tokenId")
    abstract Builder tokenId(Optional<Integer> tokenId);

    public abstract LogprobsResultCandidate build();
  }

  /** Deserializes a JSON string to a LogprobsResultCandidate object. */
  public static LogprobsResultCandidate fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, LogprobsResultCandidate.class);
  }
}
