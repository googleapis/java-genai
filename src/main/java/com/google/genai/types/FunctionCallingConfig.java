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
import java.util.List;
import java.util.Optional;

/** Function calling config. */
@GenerateTypeAdapter
@AutoValue
public abstract class FunctionCallingConfig extends JsonSerializable {
  /** Optional. Function calling mode. */
  @SerializedName("mode")
  public abstract Optional<String> mode();

  /**
   * Optional. Function names to call. Only set when the Mode is ANY. Function names should match
   * [FunctionDeclaration.name]. With mode set to ANY, model will predict a function call from the
   * set of function names provided.
   */
  @SerializedName("allowedFunctionNames")
  public abstract Optional<List<String>> allowedFunctionNames();

  /** Instantiates a builder for FunctionCallingConfig. */
  public static Builder builder() {
    return new AutoValue_FunctionCallingConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for FunctionCallingConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("mode")
    public abstract Builder mode(String mode);

    @SerializedName("mode")
    abstract Builder mode(Optional<String> mode);

    @SerializedName("allowedFunctionNames")
    public abstract Builder allowedFunctionNames(List<String> allowedFunctionNames);

    @SerializedName("allowedFunctionNames")
    abstract Builder allowedFunctionNames(Optional<List<String>> allowedFunctionNames);

    public abstract FunctionCallingConfig build();
  }

  /** Deserializes a JSON string to a FunctionCallingConfig object. */
  public static FunctionCallingConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, FunctionCallingConfig.class);
  }
}
