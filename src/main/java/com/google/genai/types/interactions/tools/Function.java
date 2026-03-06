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

package com.google.genai.types.interactions.tools;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import com.google.genai.types.ExcludeFromGeneratedCoverageReport;
import com.google.genai.types.Schema;
import java.util.Optional;

/**
 * Function tool for the Interactions API.
 *
 * <p>Represents a callable function that the model can invoke. Unlike the GenerateContent API where
 * one Tool can contain multiple FunctionDeclarations, in the Interactions API each Function
 * represents a single function.
 *
 * <p>Example usage with manual declaration:
 *
 * <pre>{@code
 * Function weatherTool = Function.builder()
 *     .name("get_weather")
 *     .description("Get the current weather for a location")
 *     .parameters(Schema.builder()
 *         .type("object")
 *         .properties(Map.of(
 *             "location", Schema.builder().type("string").build()))
 *         .required("location")
 *         .build())
 *     .build();
 * }</pre>
 *
 * <p>The Interactions API does not support Automatic Function Calling (AFC). All function execution
 * must be handled manually by the application.
 *
 * <p>The Interactions API is available in both Vertex AI and Gemini API.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = Function.Builder.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("function")
public abstract class Function extends JsonSerializable implements Tool {

  /** The name of the function to call. */
  @JsonProperty("name")
  public abstract Optional<String> name();

  /** A description of what the function does, used by the model to decide when to call it. */
  @JsonProperty("description")
  public abstract Optional<String> description();

  /** The parameters schema for the function in JSON Schema format. */
  @JsonProperty("parameters")
  public abstract Optional<Schema> parameters();

  /** Instantiates a builder for Function. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_Function.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for Function. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use {@code Function.builder()} for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_Function.Builder();
    }

    /**
     * Setter for name.
     *
     * <p>name: The name of the function to call.
     */
    @JsonProperty("name")
    public abstract Builder name(String name);

    abstract Builder name(Optional<String> name);

    /** Clears the value of name field. */
    @CanIgnoreReturnValue
    public Builder clearName() {
      return name(Optional.empty());
    }

    /**
     * Setter for description.
     *
     * <p>description: A description of what the function does.
     */
    @JsonProperty("description")
    public abstract Builder description(String description);

    abstract Builder description(Optional<String> description);

    /** Clears the value of description field. */
    @CanIgnoreReturnValue
    public Builder clearDescription() {
      return description(Optional.empty());
    }

    /**
     * Setter for parameters.
     *
     * <p>parameters: The parameters schema for the function.
     */
    @JsonProperty("parameters")
    public abstract Builder parameters(Schema parameters);

    /**
     * Setter for parameters builder.
     *
     * <p>parameters: The parameters schema for the function.
     */
    @CanIgnoreReturnValue
    public Builder parameters(Schema.Builder parametersBuilder) {
      return parameters(parametersBuilder.build());
    }

    abstract Builder parameters(Optional<Schema> parameters);

    /** Clears the value of parameters field. */
    @CanIgnoreReturnValue
    public Builder clearParameters() {
      return parameters(Optional.empty());
    }

    public abstract Function build();
  }

  /** Deserializes a JSON string to a Function object. */
  @ExcludeFromGeneratedCoverageReport
  public static Function fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Function.class);
  }
}
