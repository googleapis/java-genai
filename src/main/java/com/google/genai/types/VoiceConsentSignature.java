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
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** The signature of the voice consent check. */
@AutoValue
@JsonDeserialize(builder = VoiceConsentSignature.Builder.class)
public abstract class VoiceConsentSignature extends JsonSerializable {
  /** The signature string. */
  @JsonProperty("signature")
  public abstract Optional<String> signature();

  /** Instantiates a builder for VoiceConsentSignature. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_VoiceConsentSignature.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for VoiceConsentSignature. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `VoiceConsentSignature.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_VoiceConsentSignature.Builder();
    }

    /**
     * Setter for signature.
     *
     * <p>signature: The signature string.
     */
    @JsonProperty("signature")
    public abstract Builder signature(String signature);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder signature(Optional<String> signature);

    /** Clears the value of signature field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSignature() {
      return signature(Optional.empty());
    }

    public abstract VoiceConsentSignature build();
  }

  /** Deserializes a JSON string to a VoiceConsentSignature object. */
  @ExcludeFromGeneratedCoverageReport
  public static VoiceConsentSignature fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, VoiceConsentSignature.class);
  }
}
