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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** Provides hints to the model about possible languages present in the audio. */
@AutoValue
@JsonDeserialize(builder = LanguageHints.Builder.class)
public abstract class LanguageHints extends JsonSerializable {
  /** BCP-47 language codes. At least one must be specified. */
  @JsonProperty("languageCodes")
  public abstract Optional<List<String>> languageCodes();

  /** Instantiates a builder for LanguageHints. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_LanguageHints.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for LanguageHints. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `LanguageHints.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_LanguageHints.Builder();
    }

    /**
     * Setter for languageCodes.
     *
     * <p>languageCodes: BCP-47 language codes. At least one must be specified.
     */
    @JsonProperty("languageCodes")
    public abstract Builder languageCodes(List<String> languageCodes);

    /**
     * Setter for languageCodes.
     *
     * <p>languageCodes: BCP-47 language codes. At least one must be specified.
     */
    @CanIgnoreReturnValue
    public Builder languageCodes(String... languageCodes) {
      return languageCodes(Arrays.asList(languageCodes));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder languageCodes(Optional<List<String>> languageCodes);

    /** Clears the value of languageCodes field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearLanguageCodes() {
      return languageCodes(Optional.empty());
    }

    public abstract LanguageHints build();
  }

  /** Deserializes a JSON string to a LanguageHints object. */
  @ExcludeFromGeneratedCoverageReport
  public static LanguageHints fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, LanguageHints.class);
  }
}
