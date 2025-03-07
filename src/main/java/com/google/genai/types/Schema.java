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
import java.util.Map;
import java.util.Optional;

/**
 * Schema that defines the format of input and output data.
 *
 * <p>Represents a select subset of an OpenAPI 3.0 schema object.
 */
@GenerateTypeAdapter
@AutoValue
public abstract class Schema extends JsonSerializable {
  /** Optional. Example of the object. Will only populated when the object is the root. */
  @SerializedName("example")
  public abstract Optional<Object> example();

  /** Optional. Pattern of the Type.STRING to restrict a string to a regular expression. */
  @SerializedName("pattern")
  public abstract Optional<String> pattern();

  /**
   * Optional. SCHEMA FIELDS FOR TYPE INTEGER and NUMBER Minimum value of the Type.INTEGER and
   * Type.NUMBER
   */
  @SerializedName("minimum")
  public abstract Optional<Double> minimum();

  /** Optional. Default value of the data. */
  @SerializedName("default")
  public abstract Optional<Object> default_();

  /**
   * Optional. The value should be validated against any (one or more) of the subschemas in the
   * list.
   */
  @SerializedName("anyOf")
  public abstract Optional<List<Schema>> anyOf();

  /** Optional. Maximum length of the Type.STRING */
  @SerializedName("maxLength")
  public abstract Optional<Long> maxLength();

  /** Optional. The title of the Schema. */
  @SerializedName("title")
  public abstract Optional<String> title();

  /** Optional. SCHEMA FIELDS FOR TYPE STRING Minimum length of the Type.STRING */
  @SerializedName("minLength")
  public abstract Optional<Long> minLength();

  /** Optional. Minimum number of the properties for Type.OBJECT. */
  @SerializedName("minProperties")
  public abstract Optional<Long> minProperties();

  /** Optional. Maximum value of the Type.INTEGER and Type.NUMBER */
  @SerializedName("maximum")
  public abstract Optional<Double> maximum();

  /** Optional. Maximum number of the properties for Type.OBJECT. */
  @SerializedName("maxProperties")
  public abstract Optional<Long> maxProperties();

  /** Optional. The description of the data. */
  @SerializedName("description")
  public abstract Optional<String> description();

  /**
   * Optional. Possible values of the element of primitive type with enum format. Examples: 1. We
   * can define direction as : {type:STRING, format:enum, enum:["EAST", NORTH", "SOUTH", "WEST"]} 2.
   * We can define apartment number as : {type:INTEGER, format:enum, enum:["101", "201", "301"]}
   */
  @SerializedName("enum")
  public abstract Optional<List<String>> enum_();

  /**
   * Optional. The format of the data. Supported formats: for NUMBER type: "float", "double" for
   * INTEGER type: "int32", "int64" for STRING type: "email", "byte", etc
   */
  @SerializedName("format")
  public abstract Optional<String> format();

  /** Optional. SCHEMA FIELDS FOR TYPE ARRAY Schema of the elements of Type.ARRAY. */
  @SerializedName("items")
  public abstract Optional<Schema> items();

  /** Optional. Maximum number of the elements for Type.ARRAY. */
  @SerializedName("maxItems")
  public abstract Optional<Long> maxItems();

  /** Optional. Minimum number of the elements for Type.ARRAY. */
  @SerializedName("minItems")
  public abstract Optional<Long> minItems();

  /** Optional. Indicates if the value may be null. */
  @SerializedName("nullable")
  public abstract Optional<Boolean> nullable();

  /** Optional. SCHEMA FIELDS FOR TYPE OBJECT Properties of Type.OBJECT. */
  @SerializedName("properties")
  public abstract Optional<Map<String, Schema>> properties();

  /**
   * Optional. The order of the properties. Not a standard field in open api spec. Only used to
   * support the order of the properties.
   */
  @SerializedName("propertyOrdering")
  public abstract Optional<List<String>> propertyOrdering();

  /** Optional. Required properties of Type.OBJECT. */
  @SerializedName("required")
  public abstract Optional<List<String>> required();

  /** Optional. The type of the data. */
  @SerializedName("type")
  public abstract Optional<String> type();

  /** Instantiates a builder for Schema. */
  public static Builder builder() {
    return new AutoValue_Schema.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for Schema. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("example")
    public abstract Builder example(Object example);

    @SerializedName("example")
    abstract Builder example(Optional<Object> example);

    @SerializedName("pattern")
    public abstract Builder pattern(String pattern);

    @SerializedName("pattern")
    abstract Builder pattern(Optional<String> pattern);

    @SerializedName("minimum")
    public abstract Builder minimum(Double minimum);

    @SerializedName("minimum")
    abstract Builder minimum(Optional<Double> minimum);

    @SerializedName("default")
    public abstract Builder default_(Object default_);

    @SerializedName("default")
    abstract Builder default_(Optional<Object> default_);

    @SerializedName("anyOf")
    public abstract Builder anyOf(List<Schema> anyOf);

    @SerializedName("anyOf")
    abstract Builder anyOf(Optional<List<Schema>> anyOf);

    @SerializedName("maxLength")
    public abstract Builder maxLength(Long maxLength);

    @SerializedName("maxLength")
    abstract Builder maxLength(Optional<Long> maxLength);

    @SerializedName("title")
    public abstract Builder title(String title);

    @SerializedName("title")
    abstract Builder title(Optional<String> title);

    @SerializedName("minLength")
    public abstract Builder minLength(Long minLength);

    @SerializedName("minLength")
    abstract Builder minLength(Optional<Long> minLength);

    @SerializedName("minProperties")
    public abstract Builder minProperties(Long minProperties);

    @SerializedName("minProperties")
    abstract Builder minProperties(Optional<Long> minProperties);

    @SerializedName("maximum")
    public abstract Builder maximum(Double maximum);

    @SerializedName("maximum")
    abstract Builder maximum(Optional<Double> maximum);

    @SerializedName("maxProperties")
    public abstract Builder maxProperties(Long maxProperties);

    @SerializedName("maxProperties")
    abstract Builder maxProperties(Optional<Long> maxProperties);

    @SerializedName("description")
    public abstract Builder description(String description);

    @SerializedName("description")
    abstract Builder description(Optional<String> description);

    @SerializedName("enum")
    public abstract Builder enum_(List<String> enum_);

    @SerializedName("enum")
    abstract Builder enum_(Optional<List<String>> enum_);

    @SerializedName("format")
    public abstract Builder format(String format);

    @SerializedName("format")
    abstract Builder format(Optional<String> format);

    @SerializedName("items")
    public abstract Builder items(Schema items);

    @SerializedName("items")
    abstract Builder items(Optional<Schema> items);

    @SerializedName("maxItems")
    public abstract Builder maxItems(Long maxItems);

    @SerializedName("maxItems")
    abstract Builder maxItems(Optional<Long> maxItems);

    @SerializedName("minItems")
    public abstract Builder minItems(Long minItems);

    @SerializedName("minItems")
    abstract Builder minItems(Optional<Long> minItems);

    @SerializedName("nullable")
    public abstract Builder nullable(boolean nullable);

    @SerializedName("nullable")
    abstract Builder nullable(Optional<Boolean> nullable);

    @SerializedName("properties")
    public abstract Builder properties(Map<String, Schema> properties);

    @SerializedName("properties")
    abstract Builder properties(Optional<Map<String, Schema>> properties);

    @SerializedName("propertyOrdering")
    public abstract Builder propertyOrdering(List<String> propertyOrdering);

    @SerializedName("propertyOrdering")
    abstract Builder propertyOrdering(Optional<List<String>> propertyOrdering);

    @SerializedName("required")
    public abstract Builder required(List<String> required);

    @SerializedName("required")
    abstract Builder required(Optional<List<String>> required);

    @SerializedName("type")
    public abstract Builder type(String type);

    @SerializedName("type")
    abstract Builder type(Optional<String> type);

    public abstract Schema build();
  }

  /** Deserializes a JSON string to a Schema object. */
  public static Schema fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Schema.class);
  }
}
