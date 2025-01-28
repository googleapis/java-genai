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
  public abstract Optional<Long> getMinItems();

  /** Optional. Example of the object. Will only populated when the object is the root. */
  public abstract Optional<Object> getExample();

  /**
   * Optional. The order of the properties. Not a standard field in open api spec. Only used to
   * support the order of the properties.
   */
  public abstract Optional<List<String>> getPropertyOrdering();

  /** Optional. Pattern of the Type.STRING to restrict a string to a regular expression. */
  public abstract Optional<String> getPattern();

  /**
   * Optional. SCHEMA FIELDS FOR TYPE INTEGER and NUMBER Minimum value of the Type.INTEGER and
   * Type.NUMBER
   */
  public abstract Optional<Double> getMinimum();

  /** Optional. Default value of the data. */
  public abstract Optional<Object> getDefault();

  /**
   * Optional. The value should be validated against any (one or more) of the subschemas in the
   * list.
   */
  public abstract Optional<List<Schema>> getAnyOf();

  /** Optional. Maximum length of the Type.STRING */
  public abstract Optional<Long> getMaxLength();

  /** Optional. The title of the Schema. */
  public abstract Optional<String> getTitle();

  /** Optional. SCHEMA FIELDS FOR TYPE STRING Minimum length of the Type.STRING */
  public abstract Optional<Long> getMinLength();

  /** Optional. Minimum number of the properties for Type.OBJECT. */
  public abstract Optional<Long> getMinProperties();

  /** Optional. Maximum number of the elements for Type.ARRAY. */
  public abstract Optional<Long> getMaxItems();

  /** Optional. Maximum value of the Type.INTEGER and Type.NUMBER */
  public abstract Optional<Double> getMaximum();

  /** Optional. Indicates if the value may be null. */
  public abstract Optional<Boolean> getNullable();

  /** Optional. Maximum number of the properties for Type.OBJECT. */
  public abstract Optional<Long> getMaxProperties();

  /** Optional. The type of the data. */
  public abstract Optional<String> getType();

  /** Optional. The description of the data. */
  public abstract Optional<String> getDescription();

  /**
   * Optional. Possible values of the element of primitive type with enum format. Examples: 1. We
   * can define direction as : {type:STRING, format:enum, enum:["EAST", NORTH", "SOUTH", "WEST"]} 2.
   * We can define apartment number as : {type:INTEGER, format:enum, enum:["101", "201", "301"]}
   */
  public abstract Optional<List<String>> getEnum();

  /**
   * Optional. The format of the data. Supported formats: for NUMBER type: "float", "double" for
   * INTEGER type: "int32", "int64" for STRING type: "email", "byte", etc
   */
  public abstract Optional<String> getFormat();

  /** Optional. SCHEMA FIELDS FOR TYPE ARRAY Schema of the elements of Type.ARRAY. */
  public abstract Optional<Schema> getItems();

  /** Optional. SCHEMA FIELDS FOR TYPE OBJECT Properties of Type.OBJECT. */
  public abstract Optional<Map<String, Schema>> getProperties();

  /** Optional. Required properties of Type.OBJECT. */
  public abstract Optional<List<String>> getRequired();

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
    public abstract Builder setMinItems(Long minItems);

    @JsonProperty("example")
    public abstract Builder setExample(Object example);

    @JsonProperty("propertyOrdering")
    public abstract Builder setPropertyOrdering(List<String> propertyOrdering);

    @JsonProperty("pattern")
    public abstract Builder setPattern(String pattern);

    @JsonProperty("minimum")
    public abstract Builder setMinimum(Double minimum);

    @JsonProperty("default")
    public abstract Builder setDefault(Object default_);

    @JsonProperty("anyOf")
    public abstract Builder setAnyOf(List<Schema> anyOf);

    @JsonProperty("maxLength")
    public abstract Builder setMaxLength(Long maxLength);

    @JsonProperty("title")
    public abstract Builder setTitle(String title);

    @JsonProperty("minLength")
    public abstract Builder setMinLength(Long minLength);

    @JsonProperty("minProperties")
    public abstract Builder setMinProperties(Long minProperties);

    @JsonProperty("maxItems")
    public abstract Builder setMaxItems(Long maxItems);

    @JsonProperty("maximum")
    public abstract Builder setMaximum(Double maximum);

    @JsonProperty("nullable")
    public abstract Builder setNullable(boolean nullable);

    @JsonProperty("maxProperties")
    public abstract Builder setMaxProperties(Long maxProperties);

    @JsonProperty("type")
    public abstract Builder setType(String type);

    @JsonProperty("description")
    public abstract Builder setDescription(String description);

    @JsonProperty("enum")
    public abstract Builder setEnum(List<String> enum_);

    @JsonProperty("format")
    public abstract Builder setFormat(String format);

    @JsonProperty("items")
    public abstract Builder setItems(Schema items);

    @JsonProperty("properties")
    public abstract Builder setProperties(Map<String, Schema> properties);

    @JsonProperty("required")
    public abstract Builder setRequired(List<String> required);

    public abstract Schema build();
  }

  /** Deserializes a JSON string to a Schema object. */
  public static Schema fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Schema.class);
  }
}
