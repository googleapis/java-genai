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

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Schema is used to define the format of input/output data.
 *
 * <p>Represents a select subset of an [OpenAPI 3.0 schema
 * object](https://spec.openapis.org/oas/v3.0.3#schema-object). More fields may be added in the
 * future as needed.
 */
@AutoValue
@JsonDeserialize(builder = Schema.Builder.class)
public abstract class Schema extends JsonSerializable {
  /**
   * Optional. The instance must be valid against any (one or more) of the subschemas listed in
   * `any_of`.
   */
  @JsonProperty("anyOf")
  public abstract Optional<List<Schema>> anyOf();

  /** Optional. Default value to use if the field is not specified. */
  @JsonProperty("default")
  public abstract Optional<Object> default_();

  /** Optional. Description of the schema. */
  @JsonProperty("description")
  public abstract Optional<String> description();

  /**
   * Optional. Possible values of the field. This field can be used to restrict a value to a fixed
   * set of values. To mark a field as an enum, set `format` to `enum` and provide the list of
   * possible values in `enum`. For example: 1. To define directions: `{type:STRING, format:enum,
   * enum:["EAST", "NORTH", "SOUTH", "WEST"]}` 2. To define apartment numbers: `{type:INTEGER,
   * format:enum, enum:["101", "201", "301"]}`
   */
  @JsonProperty("enum")
  public abstract Optional<List<String>> enum_();

  /** Optional. Example of an instance of this schema. */
  @JsonProperty("example")
  public abstract Optional<Object> example();

  /**
   * Optional. The format of the data. For `NUMBER` type, format can be `float` or `double`. For
   * `INTEGER` type, format can be `int32` or `int64`. For `STRING` type, format can be `email`,
   * `byte`, `date`, `date-time`, `password`, and other formats to further refine the data type.
   */
  @JsonProperty("format")
  public abstract Optional<String> format();

  /** Optional. If type is `ARRAY`, `items` specifies the schema of elements in the array. */
  @JsonProperty("items")
  public abstract Optional<Schema> items();

  /**
   * Optional. If type is `ARRAY`, `max_items` specifies the maximum number of items in an array.
   */
  @JsonProperty("maxItems")
  public abstract Optional<Long> maxItems();

  /** Optional. If type is `STRING`, `max_length` specifies the maximum length of the string. */
  @JsonProperty("maxLength")
  public abstract Optional<Long> maxLength();

  /**
   * Optional. If type is `OBJECT`, `max_properties` specifies the maximum number of properties that
   * can be provided.
   */
  @JsonProperty("maxProperties")
  public abstract Optional<Long> maxProperties();

  /** Optional. If type is `INTEGER` or `NUMBER`, `maximum` specifies the maximum allowed value. */
  @JsonProperty("maximum")
  public abstract Optional<Double> maximum();

  /**
   * Optional. If type is `ARRAY`, `min_items` specifies the minimum number of items in an array.
   */
  @JsonProperty("minItems")
  public abstract Optional<Long> minItems();

  /** Optional. If type is `STRING`, `min_length` specifies the minimum length of the string. */
  @JsonProperty("minLength")
  public abstract Optional<Long> minLength();

  /**
   * Optional. If type is `OBJECT`, `min_properties` specifies the minimum number of properties that
   * can be provided.
   */
  @JsonProperty("minProperties")
  public abstract Optional<Long> minProperties();

  /** Optional. If type is `INTEGER` or `NUMBER`, `minimum` specifies the minimum allowed value. */
  @JsonProperty("minimum")
  public abstract Optional<Double> minimum();

  /** Optional. Indicates if the value of this field can be null. */
  @JsonProperty("nullable")
  public abstract Optional<Boolean> nullable();

  /**
   * Optional. If type is `STRING`, `pattern` specifies a regular expression that the string must
   * match.
   */
  @JsonProperty("pattern")
  public abstract Optional<String> pattern();

  /**
   * Optional. If type is `OBJECT`, `properties` is a map of property names to schema definitions
   * for each property of the object.
   */
  @JsonProperty("properties")
  public abstract Optional<Map<String, Schema>> properties();

  /**
   * Optional. Order of properties displayed or used where order matters. This is not a standard
   * field in OpenAPI specification, but can be used to control the order of properties.
   */
  @JsonProperty("propertyOrdering")
  public abstract Optional<List<String>> propertyOrdering();

  /**
   * Optional. If type is `OBJECT`, `required` lists the names of properties that must be present.
   */
  @JsonProperty("required")
  public abstract Optional<List<String>> required();

  /** Optional. Title for the schema. */
  @JsonProperty("title")
  public abstract Optional<String> title();

  /** Optional. Data type of the schema field. */
  @JsonProperty("type")
  public abstract Optional<Type> type();

  /** Instantiates a builder for Schema. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_Schema.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for Schema. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `Schema.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_Schema.Builder();
    }

    /**
     * Setter for anyOf.
     *
     * <p>anyOf: Optional. The instance must be valid against any (one or more) of the subschemas
     * listed in `any_of`.
     */
    @JsonProperty("anyOf")
    public abstract Builder anyOf(List<Schema> anyOf);

    /**
     * Setter for anyOf.
     *
     * <p>anyOf: Optional. The instance must be valid against any (one or more) of the subschemas
     * listed in `any_of`.
     */
    @CanIgnoreReturnValue
    public Builder anyOf(Schema... anyOf) {
      return anyOf(Arrays.asList(anyOf));
    }

    /**
     * Setter for anyOf builder.
     *
     * <p>anyOf: Optional. The instance must be valid against any (one or more) of the subschemas
     * listed in `any_of`.
     */
    @CanIgnoreReturnValue
    public Builder anyOf(Schema.Builder... anyOfBuilders) {
      return anyOf(
          Arrays.asList(anyOfBuilders).stream()
              .map(Schema.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder anyOf(Optional<List<Schema>> anyOf);

    /** Clears the value of anyOf field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAnyOf() {
      return anyOf(Optional.empty());
    }

    /**
     * Setter for default_.
     *
     * <p>default_: Optional. Default value to use if the field is not specified.
     */
    @JsonProperty("default")
    public abstract Builder default_(Object default_);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder default_(Optional<Object> default_);

    /** Clears the value of default_ field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDefault_() {
      return default_(Optional.empty());
    }

    /**
     * Setter for description.
     *
     * <p>description: Optional. Description of the schema.
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
     * Setter for enum_.
     *
     * <p>enum_: Optional. Possible values of the field. This field can be used to restrict a value
     * to a fixed set of values. To mark a field as an enum, set `format` to `enum` and provide the
     * list of possible values in `enum`. For example: 1. To define directions: `{type:STRING,
     * format:enum, enum:["EAST", "NORTH", "SOUTH", "WEST"]}` 2. To define apartment numbers:
     * `{type:INTEGER, format:enum, enum:["101", "201", "301"]}`
     */
    @JsonProperty("enum")
    public abstract Builder enum_(List<String> enum_);

    /**
     * Setter for enum_.
     *
     * <p>enum_: Optional. Possible values of the field. This field can be used to restrict a value
     * to a fixed set of values. To mark a field as an enum, set `format` to `enum` and provide the
     * list of possible values in `enum`. For example: 1. To define directions: `{type:STRING,
     * format:enum, enum:["EAST", "NORTH", "SOUTH", "WEST"]}` 2. To define apartment numbers:
     * `{type:INTEGER, format:enum, enum:["101", "201", "301"]}`
     */
    @CanIgnoreReturnValue
    public Builder enum_(String... enum_) {
      return enum_(Arrays.asList(enum_));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder enum_(Optional<List<String>> enum_);

    /** Clears the value of enum_ field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEnum_() {
      return enum_(Optional.empty());
    }

    /**
     * Setter for example.
     *
     * <p>example: Optional. Example of an instance of this schema.
     */
    @JsonProperty("example")
    public abstract Builder example(Object example);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder example(Optional<Object> example);

    /** Clears the value of example field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearExample() {
      return example(Optional.empty());
    }

    /**
     * Setter for format.
     *
     * <p>format: Optional. The format of the data. For `NUMBER` type, format can be `float` or
     * `double`. For `INTEGER` type, format can be `int32` or `int64`. For `STRING` type, format can
     * be `email`, `byte`, `date`, `date-time`, `password`, and other formats to further refine the
     * data type.
     */
    @JsonProperty("format")
    public abstract Builder format(String format);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder format(Optional<String> format);

    /** Clears the value of format field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearFormat() {
      return format(Optional.empty());
    }

    /**
     * Setter for items.
     *
     * <p>items: Optional. If type is `ARRAY`, `items` specifies the schema of elements in the
     * array.
     */
    @JsonProperty("items")
    public abstract Builder items(Schema items);

    /**
     * Setter for items builder.
     *
     * <p>items: Optional. If type is `ARRAY`, `items` specifies the schema of elements in the
     * array.
     */
    @CanIgnoreReturnValue
    public Builder items(Schema.Builder itemsBuilder) {
      return items(itemsBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder items(Optional<Schema> items);

    /** Clears the value of items field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearItems() {
      return items(Optional.empty());
    }

    /**
     * Setter for maxItems.
     *
     * <p>maxItems: Optional. If type is `ARRAY`, `max_items` specifies the maximum number of items
     * in an array.
     */
    @JsonProperty("maxItems")
    public abstract Builder maxItems(Long maxItems);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder maxItems(Optional<Long> maxItems);

    /** Clears the value of maxItems field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMaxItems() {
      return maxItems(Optional.empty());
    }

    /**
     * Setter for maxLength.
     *
     * <p>maxLength: Optional. If type is `STRING`, `max_length` specifies the maximum length of the
     * string.
     */
    @JsonProperty("maxLength")
    public abstract Builder maxLength(Long maxLength);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder maxLength(Optional<Long> maxLength);

    /** Clears the value of maxLength field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMaxLength() {
      return maxLength(Optional.empty());
    }

    /**
     * Setter for maxProperties.
     *
     * <p>maxProperties: Optional. If type is `OBJECT`, `max_properties` specifies the maximum
     * number of properties that can be provided.
     */
    @JsonProperty("maxProperties")
    public abstract Builder maxProperties(Long maxProperties);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder maxProperties(Optional<Long> maxProperties);

    /** Clears the value of maxProperties field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMaxProperties() {
      return maxProperties(Optional.empty());
    }

    /**
     * Setter for maximum.
     *
     * <p>maximum: Optional. If type is `INTEGER` or `NUMBER`, `maximum` specifies the maximum
     * allowed value.
     */
    @JsonProperty("maximum")
    public abstract Builder maximum(Double maximum);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder maximum(Optional<Double> maximum);

    /** Clears the value of maximum field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMaximum() {
      return maximum(Optional.empty());
    }

    /**
     * Setter for minItems.
     *
     * <p>minItems: Optional. If type is `ARRAY`, `min_items` specifies the minimum number of items
     * in an array.
     */
    @JsonProperty("minItems")
    public abstract Builder minItems(Long minItems);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder minItems(Optional<Long> minItems);

    /** Clears the value of minItems field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMinItems() {
      return minItems(Optional.empty());
    }

    /**
     * Setter for minLength.
     *
     * <p>minLength: Optional. If type is `STRING`, `min_length` specifies the minimum length of the
     * string.
     */
    @JsonProperty("minLength")
    public abstract Builder minLength(Long minLength);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder minLength(Optional<Long> minLength);

    /** Clears the value of minLength field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMinLength() {
      return minLength(Optional.empty());
    }

    /**
     * Setter for minProperties.
     *
     * <p>minProperties: Optional. If type is `OBJECT`, `min_properties` specifies the minimum
     * number of properties that can be provided.
     */
    @JsonProperty("minProperties")
    public abstract Builder minProperties(Long minProperties);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder minProperties(Optional<Long> minProperties);

    /** Clears the value of minProperties field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMinProperties() {
      return minProperties(Optional.empty());
    }

    /**
     * Setter for minimum.
     *
     * <p>minimum: Optional. If type is `INTEGER` or `NUMBER`, `minimum` specifies the minimum
     * allowed value.
     */
    @JsonProperty("minimum")
    public abstract Builder minimum(Double minimum);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder minimum(Optional<Double> minimum);

    /** Clears the value of minimum field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMinimum() {
      return minimum(Optional.empty());
    }

    /**
     * Setter for nullable.
     *
     * <p>nullable: Optional. Indicates if the value of this field can be null.
     */
    @JsonProperty("nullable")
    public abstract Builder nullable(boolean nullable);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder nullable(Optional<Boolean> nullable);

    /** Clears the value of nullable field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearNullable() {
      return nullable(Optional.empty());
    }

    /**
     * Setter for pattern.
     *
     * <p>pattern: Optional. If type is `STRING`, `pattern` specifies a regular expression that the
     * string must match.
     */
    @JsonProperty("pattern")
    public abstract Builder pattern(String pattern);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder pattern(Optional<String> pattern);

    /** Clears the value of pattern field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPattern() {
      return pattern(Optional.empty());
    }

    /**
     * Setter for properties.
     *
     * <p>properties: Optional. If type is `OBJECT`, `properties` is a map of property names to
     * schema definitions for each property of the object.
     */
    @JsonProperty("properties")
    public abstract Builder properties(Map<String, Schema> properties);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder properties(Optional<Map<String, Schema>> properties);

    /** Clears the value of properties field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearProperties() {
      return properties(Optional.empty());
    }

    /**
     * Setter for propertyOrdering.
     *
     * <p>propertyOrdering: Optional. Order of properties displayed or used where order matters.
     * This is not a standard field in OpenAPI specification, but can be used to control the order
     * of properties.
     */
    @JsonProperty("propertyOrdering")
    public abstract Builder propertyOrdering(List<String> propertyOrdering);

    /**
     * Setter for propertyOrdering.
     *
     * <p>propertyOrdering: Optional. Order of properties displayed or used where order matters.
     * This is not a standard field in OpenAPI specification, but can be used to control the order
     * of properties.
     */
    @CanIgnoreReturnValue
    public Builder propertyOrdering(String... propertyOrdering) {
      return propertyOrdering(Arrays.asList(propertyOrdering));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder propertyOrdering(Optional<List<String>> propertyOrdering);

    /** Clears the value of propertyOrdering field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPropertyOrdering() {
      return propertyOrdering(Optional.empty());
    }

    /**
     * Setter for required.
     *
     * <p>required: Optional. If type is `OBJECT`, `required` lists the names of properties that
     * must be present.
     */
    @JsonProperty("required")
    public abstract Builder required(List<String> required);

    /**
     * Setter for required.
     *
     * <p>required: Optional. If type is `OBJECT`, `required` lists the names of properties that
     * must be present.
     */
    @CanIgnoreReturnValue
    public Builder required(String... required) {
      return required(Arrays.asList(required));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder required(Optional<List<String>> required);

    /** Clears the value of required field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRequired() {
      return required(Optional.empty());
    }

    /**
     * Setter for title.
     *
     * <p>title: Optional. Title for the schema.
     */
    @JsonProperty("title")
    public abstract Builder title(String title);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder title(Optional<String> title);

    /** Clears the value of title field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTitle() {
      return title(Optional.empty());
    }

    /**
     * Setter for type.
     *
     * <p>type: Optional. Data type of the schema field.
     */
    @JsonProperty("type")
    public abstract Builder type(Type type);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder type(Optional<Type> type);

    /** Clears the value of type field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearType() {
      return type(Optional.empty());
    }

    /**
     * Setter for type given a known enum.
     *
     * <p>type: Optional. Data type of the schema field.
     */
    @CanIgnoreReturnValue
    public Builder type(Type.Known knownType) {
      return type(new Type(knownType));
    }

    /**
     * Setter for type given a string.
     *
     * <p>type: Optional. Data type of the schema field.
     */
    @CanIgnoreReturnValue
    public Builder type(String type) {
      return type(new Type(type));
    }

    public abstract Schema build();
  }

  /** Deserializes a JSON string to a Schema object. */
  @ExcludeFromGeneratedCoverageReport
  public static Schema fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Schema.class);
  }
}
