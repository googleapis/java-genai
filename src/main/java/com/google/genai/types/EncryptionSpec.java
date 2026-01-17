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

/**
 * Represents a customer-managed encryption key specification that can be applied to a Vertex AI
 * resource. This data type is not supported in Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = EncryptionSpec.Builder.class)
public abstract class EncryptionSpec extends JsonSerializable {
  /**
   * Required. Resource name of the Cloud KMS key used to protect the resource. The Cloud KMS key
   * must be in the same region as the resource. It must have the format
   * `projects/{project}/locations/{location}/keyRings/{key_ring}/cryptoKeys/{crypto_key}`.
   */
  @JsonProperty("kmsKeyName")
  public abstract Optional<String> kmsKeyName();

  /** Instantiates a builder for EncryptionSpec. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_EncryptionSpec.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for EncryptionSpec. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `EncryptionSpec.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_EncryptionSpec.Builder();
    }

    /**
     * Setter for kmsKeyName.
     *
     * <p>kmsKeyName: Required. Resource name of the Cloud KMS key used to protect the resource. The
     * Cloud KMS key must be in the same region as the resource. It must have the format
     * `projects/{project}/locations/{location}/keyRings/{key_ring}/cryptoKeys/{crypto_key}`.
     */
    @JsonProperty("kmsKeyName")
    public abstract Builder kmsKeyName(String kmsKeyName);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder kmsKeyName(Optional<String> kmsKeyName);

    /** Clears the value of kmsKeyName field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearKmsKeyName() {
      return kmsKeyName(Optional.empty());
    }

    public abstract EncryptionSpec build();
  }

  /** Deserializes a JSON string to a EncryptionSpec object. */
  @ExcludeFromGeneratedCoverageReport
  public static EncryptionSpec fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, EncryptionSpec.class);
  }
}
