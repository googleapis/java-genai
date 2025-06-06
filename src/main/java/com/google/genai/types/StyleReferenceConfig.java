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

/** Configuration for a Style reference image. */
@AutoValue
@JsonDeserialize(builder = StyleReferenceConfig.Builder.class)
public abstract class StyleReferenceConfig extends JsonSerializable {
  /** A text description of the style to use for the generated image. */
  @JsonProperty("styleDescription")
  public abstract Optional<String> styleDescription();

  /** Instantiates a builder for StyleReferenceConfig. */
  public static Builder builder() {
    return new AutoValue_StyleReferenceConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for StyleReferenceConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `StyleReferenceConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_StyleReferenceConfig.Builder();
    }

    /**
     * Setter for styleDescription.
     *
     * <p>styleDescription: A text description of the style to use for the generated image.
     */
    @JsonProperty("styleDescription")
    public abstract Builder styleDescription(String styleDescription);

    public abstract StyleReferenceConfig build();
  }

  /** Deserializes a JSON string to a StyleReferenceConfig object. */
  public static StyleReferenceConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, StyleReferenceConfig.class);
  }
}
