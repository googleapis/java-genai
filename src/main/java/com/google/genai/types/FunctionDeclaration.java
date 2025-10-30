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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Structured representation of a function declaration as defined by the [OpenAPI 3.0
 * specification](https://spec.openapis.org/oas/v3.0.3). Included in this declaration are the
 * function name, description, parameters and response type. This FunctionDeclaration is a
 * representation of a block of code that can be used as a `Tool` by the model and executed by the
 * client.
 */
@AutoValue
@JsonDeserialize(builder = FunctionDeclaration.Builder.class)
public abstract class FunctionDeclaration extends JsonSerializable {
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

  /**
   * Optional. Specifies the function Behavior. Currently only supported by the BidiGenerateContent
   * method. This field is not supported in Vertex AI.
   */
  @JsonProperty("behavior")
  public abstract Optional<Behavior> behavior();

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
     * underscore. Must be a-z, A-Z, 0-9, or contain underscores, dots and dashes, with a maximum
     * length of 64.
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

    /**
     * Setter for behavior.
     *
     * <p>behavior: Optional. Specifies the function Behavior. Currently only supported by the
     * BidiGenerateContent method. This field is not supported in Vertex AI.
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
     * <p>behavior: Optional. Specifies the function Behavior. Currently only supported by the
     * BidiGenerateContent method. This field is not supported in Vertex AI.
     */
    @CanIgnoreReturnValue
    public Builder behavior(Behavior.Known knownType) {
      return behavior(new Behavior(knownType));
    }

    /**
     * Setter for behavior given a string.
     *
     * <p>behavior: Optional. Specifies the function Behavior. Currently only supported by the
     * BidiGenerateContent method. This field is not supported in Vertex AI.
     */
    @CanIgnoreReturnValue
    public Builder behavior(String behavior) {
      return behavior(new Behavior(behavior));
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
      properties.put(parameterName, buildTypeSchema(parameterName, parameters[i].getType()));
      required.add(parameterName);
    }
    parametersBuilder.properties(properties).required(required);

    return FunctionDeclaration.builder()
        .name(method.getName())
        .description(functionDescription)
        .parameters(parametersBuilder.build())
        .build();
  }

  /**
   * Builds a Schema object for a given parameter name and type.
   *
   * @param parameterName The name of the parameter.
   * @param parameterType The type of the parameter as a Class object.
   * @return A Schema object representing the parameter's type and metadata.
   * @throws IllegalArgumentException If the parameter type is unsupported.
   */
  private static Schema buildTypeSchema(String parameterName, Class<?> parameterType) {
    Schema.Builder parameterSchemaBuilder = Schema.builder().title(parameterName);

    switch (parameterType.getName()) {
      case "java.lang.String":
        parameterSchemaBuilder = parameterSchemaBuilder.type("STRING");
        break;
      case "boolean":
      case "java.lang.Boolean":
        parameterSchemaBuilder = parameterSchemaBuilder.type("BOOLEAN");
        break;
      case "int":
      case "java.lang.Integer":
      case "java.lang.Long":
        parameterSchemaBuilder = parameterSchemaBuilder.type("INTEGER");
        break;
      case "double":
      case "java.lang.Double":
      case "float":
      case "java.lang.Float":
        parameterSchemaBuilder = parameterSchemaBuilder.type("NUMBER");
        break;
      default:
        throw new IllegalArgumentException(
            "Unsupported parameter type "
                + parameterType.getName()
                + " for parameter "
                + parameterName
                + ". Currently, supported types are String, boolean, Boolean, int, Integer, Long,"
                + " double, Double, float, Float.");
    }
    return parameterSchemaBuilder.build();
  }
}
