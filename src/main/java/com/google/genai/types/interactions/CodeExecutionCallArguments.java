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

@AutoValue
@JsonDeserialize(builder = CodeExecutionCallArguments.Builder.class)
public abstract class CodeExecutionCallArguments extends JsonSerializable {

  @JsonProperty("language")
  public abstract Optional<String> language();

  @JsonProperty("code")
  public abstract Optional<String> code();

  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_CodeExecutionCallArguments.Builder();
  }

  public abstract Builder toBuilder();

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return new AutoValue_CodeExecutionCallArguments.Builder();
    }

    @JsonProperty("language")
    public abstract Builder language(String language);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder language(Optional<String> language);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearLanguage() {
      return language(Optional.empty());
    }

    @JsonProperty("code")
    public abstract Builder code(String code);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder code(Optional<String> code);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCode() {
      return code(Optional.empty());
    }

    public abstract CodeExecutionCallArguments build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static CodeExecutionCallArguments fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, CodeExecutionCallArguments.class);
  }

  @ExcludeFromGeneratedCoverageReport
  public static CodeExecutionCallArguments of(String language, String code) {
    return builder().language(language).code(code).build();
  }
}
