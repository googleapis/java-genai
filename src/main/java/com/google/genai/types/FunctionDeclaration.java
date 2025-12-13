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
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Defines a function that the model can generate JSON inputs for.
 *
 * <p>The inputs are based on `OpenAPI 3.0 specifications <https://spec.openapis.org/oas/v3.0.3>`_.
 */
@AutoValue
@JsonDeserialize(builder = FunctionDeclaration.Builder.class)
public abstract class FunctionDeclaration extends JsonSerializable {
  /** Defines the function behavior. */
  @JsonProperty("behavior")
  public abstract Optional<Behavior> behavior();

  /**
   * Optional. Description and purpose of the function. Model uses it to decide how and whether to
   * call the function.
   */
  @JsonProperty("description")
  public abstract Optional<String> description();

  /**
   * Required. The name of the function to call. Must start with a letter or an underscore. Must be
   * a-z, A-Z, 0-9, or contain underscores, dots, colons and dashes, with a maximum length of 64.
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

  /**
   * Optional. Describes the parameters to the function in JSON Schema format. The schema must
   * describe an object where the properties are the parameters to the function. For example: ``` {
   * "type": "object", "properties": { "name": { "type": "string" }, "age": { "type": "integer" } },
   * "additionalProperties": false, "required": ["name", "age"], "propertyOrdering": ["name", "age"]
   * } ``` This field is mutually exclusive with `parameters`.
   */
  @JsonProperty("parametersJsonSchema")
  public abstract Optional<Object> parametersJsonSchema();

  /**
   * Optional. Describes the output from this function in JSON Schema format. Reflects the Open API
   * 3.03 Response Object. The Schema defines the type used for the response value of the function.
   */
  @JsonProperty("response")
  public abstract Optional<Schema> response();

  /**
   * Optional. Describes the output from this function in JSON Schema format. The value specified by
   * the schema is the response value of the function. This field is mutually exclusive with
   * `response`.
   */
  @JsonProperty("responseJsonSchema")
  public abstract Optional<Object> responseJsonSchema();

  /** Instantiates a builder for FunctionDeclaration. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_FunctionDeclaration.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for FunctionDeclaration. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `FunctionDeclaration.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_FunctionDeclaration.Builder();
    }

    /**
     * Setter for behavior.
     *
     * <p>behavior: Defines the function behavior.
     */
    @JsonProperty("behavior")
    public abstract Builder behavior(Behavior behavior);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder behavior(Optional<Behavior> behavior);

    /** Clears the value of behavior field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearBehavior() {
      return behavior(Optional.empty());
    }

    /**
     * Setter for behavior given a known enum.
     *
     * <p>behavior: Defines the function behavior.
     */
    @CanIgnoreReturnValue
    public Builder behavior(Behavior.Known knownType) {
      return behavior(new Behavior(knownType));
    }

    /**
     * Setter for behavior given a string.
     *
     * <p>behavior: Defines the function behavior.
     */
    @CanIgnoreReturnValue
    public Builder behavior(String behavior) {
      return behavior(new Behavior(behavior));
    }

    /**
     * Setter for description.
     *
     * <p>description: Optional. Description and purpose of the function. Model uses it to decide
     * how and whether to call the function.
     */
    @JsonProperty("description")
    public abstract Builder description(String description);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder description(Optional<String> description);

    /** Clears the value of description field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDescription() {
      return description(Optional.empty());
    }

    /**
     * Setter for name.
     *
     * <p>name: Required. The name of the function to call. Must start with a letter or an
     * underscore. Must be a-z, A-Z, 0-9, or contain underscores, dots, colons and dashes, with a
     * maximum length of 64.
     */
    @JsonProperty("name")
    public abstract Builder name(String name);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder name(Optional<String> name);

    /** Clears the value of name field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearName() {
      return name(Optional.empty());
    }

    /**
     * Setter for parameters.
     *
     * <p>parameters: Optional. Describes the parameters to this function in JSON Schema Object
     * format. Reflects the Open API 3.03 Parameter Object. string Key: the name of the parameter.
     * Parameter names are case sensitive. Schema Value: the Schema defining the type used for the
     * parameter. For function with no parameters, this can be left unset. Parameter names must
     * start with a letter or an underscore and must only contain chars a-z, A-Z, 0-9, or
     * underscores with a maximum length of 64. Example with 1 required and 1 optional parameter:
     * type: OBJECT properties: param1: type: STRING param2: type: INTEGER required: - param1
     */
    @JsonProperty("parameters")
    public abstract Builder parameters(Schema parameters);

    /**
     * Setter for parameters builder.
     *
     * <p>parameters: Optional. Describes the parameters to this function in JSON Schema Object
     * format. Reflects the Open API 3.03 Parameter Object. string Key: the name of the parameter.
     * Parameter names are case sensitive. Schema Value: the Schema defining the type used for the
     * parameter. For function with no parameters, this can be left unset. Parameter names must
     * start with a letter or an underscore and must only contain chars a-z, A-Z, 0-9, or
     * underscores with a maximum length of 64. Example with 1 required and 1 optional parameter:
     * type: OBJECT properties: param1: type: STRING param2: type: INTEGER required: - param1
     */
    @CanIgnoreReturnValue
    public Builder parameters(Schema.Builder parametersBuilder) {
      return parameters(parametersBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder parameters(Optional<Schema> parameters);

    /** Clears the value of parameters field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearParameters() {
      return parameters(Optional.empty());
    }

    /**
     * Setter for parametersJsonSchema.
     *
     * <p>parametersJsonSchema: Optional. Describes the parameters to the function in JSON Schema
     * format. The schema must describe an object where the properties are the parameters to the
     * function. For example: ``` { "type": "object", "properties": { "name": { "type": "string" },
     * "age": { "type": "integer" } }, "additionalProperties": false, "required": ["name", "age"],
     * "propertyOrdering": ["name", "age"] } ``` This field is mutually exclusive with `parameters`.
     */
    @JsonProperty("parametersJsonSchema")
    public abstract Builder parametersJsonSchema(Object parametersJsonSchema);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder parametersJsonSchema(Optional<Object> parametersJsonSchema);

    /** Clears the value of parametersJsonSchema field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearParametersJsonSchema() {
      return parametersJsonSchema(Optional.empty());
    }

    /**
     * Setter for response.
     *
     * <p>response: Optional. Describes the output from this function in JSON Schema format.
     * Reflects the Open API 3.03 Response Object. The Schema defines the type used for the response
     * value of the function.
     */
    @JsonProperty("response")
    public abstract Builder response(Schema response);

    /**
     * Setter for response builder.
     *
     * <p>response: Optional. Describes the output from this function in JSON Schema format.
     * Reflects the Open API 3.03 Response Object. The Schema defines the type used for the response
     * value of the function.
     */
    @CanIgnoreReturnValue
    public Builder response(Schema.Builder responseBuilder) {
      return response(responseBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder response(Optional<Schema> response);

    /** Clears the value of response field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearResponse() {
      return response(Optional.empty());
    }

    /**
     * Setter for responseJsonSchema.
     *
     * <p>responseJsonSchema: Optional. Describes the output from this function in JSON Schema
     * format. The value specified by the schema is the response value of the function. This field
     * is mutually exclusive with `response`.
     */
    @JsonProperty("responseJsonSchema")
    public abstract Builder responseJsonSchema(Object responseJsonSchema);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder responseJsonSchema(Optional<Object> responseJsonSchema);

    /** Clears the value of responseJsonSchema field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearResponseJsonSchema() {
      return responseJsonSchema(Optional.empty());
    }

    public abstract FunctionDeclaration build();
  }

  /** Deserializes a JSON string to a FunctionDeclaration object. */
  @ExcludeFromGeneratedCoverageReport
  public static FunctionDeclaration fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, FunctionDeclaration.class);
  }

  /**
   * Creates a FunctionDeclaration instance from a {@link Method} instance.
   *
   * @param method The {@link Method} instance to be parsed into the FunctionDeclaration instance.
   *     Only static method is supported.
   * @param orderedParameterNames Optional ordered parameter names. If not provided, parameter names
   *     will be retrieved via reflection.
   * @return A FunctionDeclaration instance.
   */
  public static FunctionDeclaration fromMethod(Method method, String... orderedParameterNames) {
    return fromMethod("", method, orderedParameterNames);
  }

  /**
   * Creates a FunctionDeclaration instance from a {@link Method} instance.
   *
   * @param functionDescription Description of the function.
   * @param method The {@link Method} instance to be parsed into the FunctionDeclaration instance.
   *     Only static method is supported.
   * @param orderedParameterNames Optional ordered parameter names. If not provided, parameter names
   *     will be retrieved via reflection.
   * @return A FunctionDeclaration instance.
   */
  public static FunctionDeclaration fromMethod(
      String functionDescription, Method method, String... orderedParameterNames) {
    if (!Modifier.isStatic(method.getModifiers())) {
      throw new IllegalArgumentException(
          "Instance methods are not supported. Please use static methods.");
    }

    Schema.Builder parametersBuilder = Schema.builder().type("OBJECT");

    Parameter[] parameters = method.getParameters();

    if (orderedParameterNames.length > 0 && orderedParameterNames.length != parameters.length) {
      throw new IllegalArgumentException(
          "The number of parameter names passed to the orderedParameterNames argument "
              + "does not match the number of parameters in the method.");
    }

    Map<String, Schema> properties = new HashMap<>();
    List<String> required = new ArrayList<>();
    for (int i = 0; i < parameters.length; i++) {
      String parameterName;
      if (orderedParameterNames.length == 0) {

        if (!parameters[i].isNamePresent()) {
          throw new IllegalStateException(
              "Failed to retrieve the parameter name from reflection. Please compile your"
                  + " code with the \"-parameters\" flag or provide parameter names manually.");
        }
        parameterName = parameters[i].getName();
      } else {
        parameterName = orderedParameterNames[i];
      }
      properties.put(
          parameterName,
          buildTypeSchema(parameterName, parameters[i].getParameterizedType(), "parameter "));
      required.add(parameterName);
    }
    parametersBuilder.properties(properties).required(required);

    Schema responseSchema;
    try {
      responseSchema = buildTypeSchema("return type", method.getReturnType(), "");
    } catch (IllegalArgumentException e) {

      responseSchema = Schema.builder().title("return type").type("OBJECT").build();
    }

    return FunctionDeclaration.builder()
        .name(method.getName())
        .description(functionDescription)
        .parameters(parametersBuilder.build())
        .response(responseSchema)
        .build();
  }

  /**
   * Builds a Schema object for a parameter or return type given its name and type.
   *
   * @param name The name of the parameter or return type.
   * @param type The type of the parameter or return type as a Type object.
   * @param prefix The prefix to add to the error message.
   * @return A Schema object representing the parameter's type and metadata.
   * @throws IllegalArgumentException If the parameter type is unsupported.
   */
  private static Schema buildTypeSchema(String name, Type type, String prefix) {
    String errorMessage =
        "Unsupported type %s for %s%s. Currently, supported types are String, boolean, Boolean,"
            + " int, Integer, Long, double, Double, float, Float, and List<T>.";
    Schema.Builder schemaBuilder = Schema.builder().title(name);

    String typeName = type.getTypeName();

    if (type instanceof Class) {
      Class<?> parameterClass = (Class<?>) type;
      typeName = parameterClass.getName();
      switch (typeName) {
        case "java.lang.String":
          schemaBuilder = schemaBuilder.type("STRING");
          break;
        case "boolean":
        case "java.lang.Boolean":
          schemaBuilder = schemaBuilder.type("BOOLEAN");
          break;
        case "int":
        case "java.lang.Integer":
        case "java.lang.Long":
          schemaBuilder = schemaBuilder.type("INTEGER");
          break;
        case "double":
        case "java.lang.Double":
        case "float":
        case "java.lang.Float":
          schemaBuilder = schemaBuilder.type("NUMBER");
          break;
        default:
          throw new IllegalArgumentException(String.format(errorMessage, typeName, prefix, name));
      }
    } else if (type instanceof ParameterizedType) {
      ParameterizedType pType = (ParameterizedType) type;
      if (pType.getRawType().equals(List.class)) {
        Type itemType = pType.getActualTypeArguments()[0];
        Schema itemSchema = buildTypeSchema(name + "Item", itemType, prefix);
        schemaBuilder.type("ARRAY").items(itemSchema);
      } else {
        throw new IllegalArgumentException(
            String.format(errorMessage, pType.getRawType().getTypeName(), prefix + "item ", name));
      }
    } else {
      throw new IllegalArgumentException(String.format(errorMessage, typeName, prefix, name));
    }
    return schemaBuilder.build();
  }
}
