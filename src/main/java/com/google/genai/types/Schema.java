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
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Schema that defines the format of input and output data.
 *
 * <p>Represents a select subset of an OpenAPI 3.0 schema object.
 */
@AutoValue
@JsonDeserialize(builder = AutoValue_Schema.Builder.class)
public abstract class Schema extends JsonSerializable {
  /** Optional. Minimum number of the elements for Type.ARRAY. */
  @JsonProperty("minItems")
  public abstract Optional<Long> minItems();

  /** Optional. Example of the object. Will only populated when the object is the root. */
  @JsonProperty("example")
  public abstract Optional<Object> example();

  /**
   * Optional. The order of the properties. Not a standard field in open api spec. Only used to
   * support the order of the properties.
   */
  @JsonProperty("propertyOrdering")
  public abstract Optional<List<String>> propertyOrdering();

  /** Optional. Pattern of the Type.STRING to restrict a string to a regular expression. */
  @JsonProperty("pattern")
  public abstract Optional<String> pattern();

  /**
   * Optional. SCHEMA FIELDS FOR TYPE INTEGER and NUMBER Minimum value of the Type.INTEGER and
   * Type.NUMBER
   */
  @JsonProperty("minimum")
  public abstract Optional<Double> minimum();

  /** Optional. Default value of the data. */
  @JsonProperty("default")
  public abstract Optional<Object> default_();

  /**
   * Optional. The value should be validated against any (one or more) of the subschemas in the
   * list.
   */
  @JsonProperty("anyOf")
  public abstract Optional<List<Schema>> anyOf();

  /** Optional. Maximum length of the Type.STRING */
  @JsonProperty("maxLength")
  public abstract Optional<Long> maxLength();

  /** Optional. The title of the Schema. */
  @JsonProperty("title")
  public abstract Optional<String> title();

  /** Optional. SCHEMA FIELDS FOR TYPE STRING Minimum length of the Type.STRING */
  @JsonProperty("minLength")
  public abstract Optional<Long> minLength();

  /** Optional. Minimum number of the properties for Type.OBJECT. */
  @JsonProperty("minProperties")
  public abstract Optional<Long> minProperties();

  /** Optional. Maximum number of the elements for Type.ARRAY. */
  @JsonProperty("maxItems")
  public abstract Optional<Long> maxItems();

  /** Optional. Maximum value of the Type.INTEGER and Type.NUMBER */
  @JsonProperty("maximum")
  public abstract Optional<Double> maximum();

  /** Optional. Indicates if the value may be null. */
  @JsonProperty("nullable")
  public abstract Optional<Boolean> nullable();

  /** Optional. Maximum number of the properties for Type.OBJECT. */
  @JsonProperty("maxProperties")
  public abstract Optional<Long> maxProperties();

  /** Optional. The type of the data. */
  @JsonProperty("type")
  public abstract Optional<String> type();

  /** Optional. The description of the data. */
  @JsonProperty("description")
  public abstract Optional<String> description();

  /**
   * Optional. Possible values of the element of primitive type with enum format. Examples: 1. We
   * can define direction as : {type:STRING, format:enum, enum:["EAST", NORTH", "SOUTH", "WEST"]} 2.
   * We can define apartment number as : {type:INTEGER, format:enum, enum:["101", "201", "301"]}
   */
  @JsonProperty("enum")
  public abstract Optional<List<String>> enum_();

  /**
   * Optional. The format of the data. Supported formats: for NUMBER type: "float", "double" for
   * INTEGER type: "int32", "int64" for STRING type: "email", "byte", etc
   */
  @JsonProperty("format")
  public abstract Optional<String> format();

  /** Optional. SCHEMA FIELDS FOR TYPE ARRAY Schema of the elements of Type.ARRAY. */
  @JsonProperty("items")
  public abstract Optional<Schema> items();

  /** Optional. SCHEMA FIELDS FOR TYPE OBJECT Properties of Type.OBJECT. */
  @JsonProperty("properties")
  public abstract Optional<Map<String, Schema>> properties();

  /** Optional. Required properties of Type.OBJECT. */
  @JsonProperty("required")
  public abstract Optional<List<String>> required();

  /** Instantiates a builder for Schema. */
  public static Builder builder() {
    return new AutoValue_Schema.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for Schema. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("minItems")
    public abstract Builder minItems(Long minItems);

    @JsonProperty("example")
    public abstract Builder example(Object example);

    @JsonProperty("propertyOrdering")
    public abstract Builder propertyOrdering(List<String> propertyOrdering);

    @JsonProperty("pattern")
    public abstract Builder pattern(String pattern);

    @JsonProperty("minimum")
    public abstract Builder minimum(Double minimum);

    @JsonProperty("default")
    public abstract Builder default_(Object default_);

    @JsonProperty("anyOf")
    public abstract Builder anyOf(List<Schema> anyOf);

    @JsonProperty("maxLength")
    public abstract Builder maxLength(Long maxLength);

    @JsonProperty("title")
    public abstract Builder title(String title);

    @JsonProperty("minLength")
    public abstract Builder minLength(Long minLength);

    @JsonProperty("minProperties")
    public abstract Builder minProperties(Long minProperties);

    @JsonProperty("maxItems")
    public abstract Builder maxItems(Long maxItems);

    @JsonProperty("maximum")
    public abstract Builder maximum(Double maximum);

    @JsonProperty("nullable")
    public abstract Builder nullable(boolean nullable);

    @JsonProperty("maxProperties")
    public abstract Builder maxProperties(Long maxProperties);

    @JsonProperty("type")
    public abstract Builder type(String type);

    @JsonProperty("description")
    public abstract Builder description(String description);

    @JsonProperty("enum")
    public abstract Builder enum_(List<String> enum_);

    @JsonProperty("format")
    public abstract Builder format(String format);

    @JsonProperty("items")
    public abstract Builder items(Schema items);

    @JsonProperty("properties")
    public abstract Builder properties(Map<String, Schema> properties);

    @JsonProperty("required")
    public abstract Builder required(List<String> required);

    public abstract Schema build();
  }

  /** Deserializes a JSON string to a Schema object. */
  public static Schema fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Schema.class);
  }
}
