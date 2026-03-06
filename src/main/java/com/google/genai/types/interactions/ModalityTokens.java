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

/**
 * Token count for a specific modality in the Interactions API.
 *
 * <p>This class represents the number of tokens used for a particular modality (such as text,
 * image, or audio) in an interaction.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * ModalityTokens tokenCount = ModalityTokens.builder()
 *     .modality(new ResponseModality(ResponseModality.Known.TEXT))
 *     .tokens(100)
 *     .build();
 * }</pre>
 *
 * <p>The Interactions API is available in both Vertex AI and Gemini API.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = ModalityTokens.Builder.class)
public abstract class ModalityTokens extends JsonSerializable {

  /** The modality associated with the token count. */
  @JsonProperty("modality")
  public abstract Optional<ResponseModality> modality();

  /** Number of tokens for the modality. */
  @JsonProperty("tokens")
  public abstract Optional<Integer> tokens();

  /** Instantiates a builder for ModalityTokens. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ModalityTokens.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ModalityTokens. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use {@code ModalityTokens.builder()} for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ModalityTokens.Builder();
    }

    /**
     * Setter for modality.
     *
     * <p>modality: The modality associated with the token count.
     */
    @JsonProperty("modality")
    public abstract Builder modality(ResponseModality modality);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder modality(Optional<ResponseModality> modality);

    /** Clears the value of modality field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearModality() {
      return modality(Optional.empty());
    }

    /**
     * Setter for tokens.
     *
     * <p>tokens: Number of tokens for the modality.
     */
    @JsonProperty("tokens")
    public abstract Builder tokens(Integer tokens);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder tokens(Optional<Integer> tokens);

    /** Clears the value of tokens field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTokens() {
      return tokens(Optional.empty());
    }

    public abstract ModalityTokens build();
  }

  /** Deserializes a JSON string to a ModalityTokens object. */
  @ExcludeFromGeneratedCoverageReport
  public static ModalityTokens fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ModalityTokens.class);
  }
}
