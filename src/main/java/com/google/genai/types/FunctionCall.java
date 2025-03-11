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
import java.util.Map;
import java.util.Optional;

/** A function call. */
@GenerateTypeAdapter
@AutoValue
public abstract class FunctionCall extends JsonSerializable {
  /**
   * The unique id of the function call. If populated, the client to execute the `function_call` and
   * return the response with the matching `id`.
   */
  @SerializedName("id")
  public abstract Optional<String> id();

  /**
   * Optional. Required. The function parameters and values in JSON object format. See
   * [FunctionDeclaration.parameters] for parameter details.
   */
  @SerializedName("args")
  public abstract Optional<Map<String, Object>> args();

  /** Required. The name of the function to call. Matches [FunctionDeclaration.name]. */
  @SerializedName("name")
  public abstract Optional<String> name();

  /** Instantiates a builder for FunctionCall. */
  public static Builder builder() {
    return new AutoValue_FunctionCall.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for FunctionCall. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("id")
    public abstract Builder id(String id);

    @SerializedName("id")
    abstract Builder id(Optional<String> id);

    @SerializedName("args")
    public abstract Builder args(Map<String, Object> args);

    @SerializedName("args")
    abstract Builder args(Optional<Map<String, Object>> args);

    @SerializedName("name")
    public abstract Builder name(String name);

    @SerializedName("name")
    abstract Builder name(Optional<String> name);

    public abstract FunctionCall build();
  }

  /** Deserializes a JSON string to a FunctionCall object. */
  public static FunctionCall fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, FunctionCall.class);
  }
}
