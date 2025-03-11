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

/** A function response. */
@GenerateTypeAdapter
@AutoValue
public abstract class FunctionResponse extends JsonSerializable {
  /**
   * The id of the function call this response is for. Populated by the client to match the
   * corresponding function call `id`.
   */
  @SerializedName("id")
  public abstract Optional<String> id();

  /**
   * Required. The name of the function to call. Matches [FunctionDeclaration.name] and
   * [FunctionCall.name].
   */
  @SerializedName("name")
  public abstract Optional<String> name();

  /**
   * Required. The function response in JSON object format. Use "output" key to specify function
   * output and "error" key to specify error details (if any). If "output" and "error" keys are not
   * specified, then whole "response" is treated as function output.
   */
  @SerializedName("response")
  public abstract Optional<Map<String, Object>> response();

  /** Instantiates a builder for FunctionResponse. */
  public static Builder builder() {
    return new AutoValue_FunctionResponse.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for FunctionResponse. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("id")
    public abstract Builder id(String id);

    @SerializedName("id")
    abstract Builder id(Optional<String> id);

    @SerializedName("name")
    public abstract Builder name(String name);

    @SerializedName("name")
    abstract Builder name(Optional<String> name);

    @SerializedName("response")
    public abstract Builder response(Map<String, Object> response);

    @SerializedName("response")
    abstract Builder response(Optional<Map<String, Object>> response);

    public abstract FunctionResponse build();
  }

  /** Deserializes a JSON string to a FunctionResponse object. */
  public static FunctionResponse fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, FunctionResponse.class);
  }
}
