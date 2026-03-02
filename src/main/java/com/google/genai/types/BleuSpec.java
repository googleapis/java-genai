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

/** Spec for bleu metric. */
@AutoValue
@JsonDeserialize(builder = BleuSpec.Builder.class)
public abstract class BleuSpec extends JsonSerializable {
  /** Optional. Whether to use_effective_order to compute bleu score. */
  @JsonProperty("useEffectiveOrder")
  public abstract Optional<Boolean> useEffectiveOrder();

  /** Instantiates a builder for BleuSpec. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_BleuSpec.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for BleuSpec. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `BleuSpec.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_BleuSpec.Builder();
    }

    /**
     * Setter for useEffectiveOrder.
     *
     * <p>useEffectiveOrder: Optional. Whether to use_effective_order to compute bleu score.
     */
    @JsonProperty("useEffectiveOrder")
    public abstract Builder useEffectiveOrder(boolean useEffectiveOrder);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder useEffectiveOrder(Optional<Boolean> useEffectiveOrder);

    /** Clears the value of useEffectiveOrder field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearUseEffectiveOrder() {
      return useEffectiveOrder(Optional.empty());
    }

    public abstract BleuSpec build();
  }

  /** Deserializes a JSON string to a BleuSpec object. */
  @ExcludeFromGeneratedCoverageReport
  public static BleuSpec fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, BleuSpec.class);
  }
}
