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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/**
 * Defines a function that the model can generate JSON inputs for.
 *
 * <p>The inputs are based on `OpenAPI 3.0 specifications <https://spec.openapis.org/oas/v3.0.3>`_.
 */
@AutoValue
@JsonDeserialize(builder = AutoValue_FunctionDeclaration.Builder.class)
public abstract class FunctionDeclaration extends JsonSerializable {
  /** Describes the output from the function in the OpenAPI JSON Schema Object format. */
  @JsonProperty("response")
  public abstract Optional<Schema> response();

  /**
   * Optional. Description and purpose of the function. Model uses it to decide how and whether to
   * call the function.
   */
  @JsonProperty("description")
  public abstract Optional<String> description();

  /**
   * Required. The name of the function to call. Must start with a letter or an underscore. Must be
   * a-z, A-Z, 0-9, or contain underscores, dots and dashes, with a maximum length of 64.
   */
  @JsonProperty("name")
  public abstract Optional<String> name();

  /**
   * Optional. Describes the parameters to this function in JSON Schema Object format. Reflects the
   * Open API 3.03 Parameter Object. string Key: the name of the parameter. Parameter names are case
   * sensitive. Schema Value: the Schema defining the type used for the parameter. For function with
   * no parameters, this can be left unset. Parameter names must start with a letter or an
   * underscore and must only contain chars a-z, A-Z, 0-9, or underscores with a maximum length of
   * 64. Example with 1 required and 1 optional parameter: type: OBJECT properties: param1: type:
   * STRING param2: type: INTEGER required: - param1
   */
  @JsonProperty("parameters")
  public abstract Optional<Schema> parameters();

  /** Instantiates a builder for FunctionDeclaration. */
  public static Builder builder() {
    return new AutoValue_FunctionDeclaration.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for FunctionDeclaration. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("response")
    public abstract Builder response(Schema response);

    @JsonProperty("description")
    public abstract Builder description(String description);

    @JsonProperty("name")
    public abstract Builder name(String name);

    @JsonProperty("parameters")
    public abstract Builder parameters(Schema parameters);

    public abstract FunctionDeclaration build();
  }

  /** Deserializes a JSON string to a FunctionDeclaration object. */
  public static FunctionDeclaration fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, FunctionDeclaration.class);
  }
}
