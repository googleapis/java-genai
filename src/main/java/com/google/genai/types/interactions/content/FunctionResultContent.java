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

package com.google.genai.types.interactions.content;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import com.google.genai.types.ExcludeFromGeneratedCoverageReport;
import com.google.genai.types.interactions.ResultItems;
import java.util.Map;
import java.util.Optional;

/** Function result content representing the result of a function call. */
@AutoValue
@JsonDeserialize(builder = FunctionResultContent.Builder.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("function_result")
public abstract class FunctionResultContent extends JsonSerializable implements Content {

  /**
   * The unique identifier matching the corresponding FunctionCallContent.
   *
   * <p>This field is always present and is required.
   */
  @JsonProperty("call_id")
  public abstract String id();

  /**
   * The name of the function that was called.
   *
   * <p>This field is optional.
   */
  @JsonProperty("name")
  public abstract Optional<String> name();

  /**
   * Whether the tool call resulted in an error.
   *
   * <p>This field is optional.
   */
  @JsonProperty("is_error")
  public abstract Optional<Boolean> isError();

  /**
   * The result returned by the function.
   *
   * <p>This field is always present and is required. The result can be one of three types:
   *
   * <ul>
   *   <li>{@link ResultItems} - structured data with an items array
   *   <li>{@link String} - a plain string result
   *   <li>{@link Map} or other object - arbitrary structured data
   * </ul>
   *
   * <p>This matches the Python SDK's Result type: {@code Union[ResultItems, str, object]}
   *
   * <p>Use {@link #resultAsString()}, {@link #resultAsResultItems()}, or {@link #resultAsMap()} for
   * type-safe access.
   */
  @JsonProperty("result")
  public abstract Object result();

  /**
   * Returns the result as a String if it is a String, otherwise returns empty.
   *
   * @return Optional containing the result as a String, or empty if result is not a String
   */
  public Optional<String> resultAsString() {
    if (result() instanceof String) {
      return Optional.of((String) result());
    }
    return Optional.empty();
  }

  /**
   * Returns the result as a ResultItems if it matches the ResultItems structure, otherwise returns
   * empty.
   *
   * @return Optional containing the result as ResultItems, or empty if result is not ResultItems
   */
  public Optional<ResultItems> resultAsResultItems() {
    if (result() instanceof ResultItems) {
      return Optional.of((ResultItems) result());
    }
    // Handle case where Jackson deserializes as Map with "items" key
    if (result() instanceof Map) {
      @SuppressWarnings("unchecked")
      Map<String, Object> map = (Map<String, Object>) result();
      if (map.containsKey("items") && map.get("items") instanceof java.util.List) {
        @SuppressWarnings("unchecked")
        java.util.List<Object> items = (java.util.List<Object>) map.get("items");
        return Optional.of(ResultItems.of(items));
      }
    }
    return Optional.empty();
  }

  /**
   * Returns the result as a Map if it is a Map, otherwise returns empty.
   *
   * @return Optional containing the result as a Map, or empty if result is not a Map
   */
  @SuppressWarnings("unchecked")
  public Optional<Map<String, Object>> resultAsMap() {
    if (result() instanceof Map) {
      return Optional.of((Map<String, Object>) result());
    }
    return Optional.empty();
  }

  /** Instantiates a builder for FunctionResultContent. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_FunctionResultContent.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for FunctionResultContent. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use {@code FunctionResultContent.builder()} for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_FunctionResultContent.Builder();
    }

    /**
     * Setter for id.
     *
     * <p>id: The unique identifier matching the corresponding FunctionCallContent. This field is
     * required.
     */
    @JsonProperty("call_id")
    public abstract Builder id(String id);

    /**
     * Setter for name.
     *
     * <p>name: The name of the function that was called.
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
     * Setter for isError.
     *
     * <p>isError: Whether the tool call resulted in an error.
     */
    @JsonProperty("is_error")
    public abstract Builder isError(Boolean isError);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder isError(Optional<Boolean> isError);

    /** Clears the value of isError field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearIsError() {
      return isError(Optional.empty());
    }

    /**
     * Setter for result.
     *
     * <p>result: The result returned by the function. This field is required. Can be a string, an
     * object, or structured data.
     */
    @JsonProperty("result")
    public abstract Builder result(Object result);

    public abstract FunctionResultContent build();
  }

  /** Deserializes a JSON string to a FunctionResultContent object. */
  @ExcludeFromGeneratedCoverageReport
  public static FunctionResultContent fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, FunctionResultContent.class);
  }

  /** Convenience factory method. */
  @ExcludeFromGeneratedCoverageReport
  public static FunctionResultContent of(String id, String name, Map<String, Object> result) {
    return builder().id(id).name(name).result(result).build();
  }

  /** Convenience factory method for successful result. */
  @ExcludeFromGeneratedCoverageReport
  public static FunctionResultContent ofSuccess(
      String callId, String name, Map<String, Object> result) {
    return builder().id(callId).name(name).result(result).isError(false).build();
  }

  /** Convenience factory method for error result. */
  @ExcludeFromGeneratedCoverageReport
  public static FunctionResultContent ofError(
      String callId, String name, Map<String, Object> result) {
    return builder().id(callId).name(name).result(result).isError(true).build();
  }

  /** Convenience factory method with String result. */
  @ExcludeFromGeneratedCoverageReport
  public static FunctionResultContent of(String callId, String name, String result) {
    return builder().id(callId).name(name).result(result).build();
  }

  /** Convenience factory method with ResultItems result. */
  @ExcludeFromGeneratedCoverageReport
  public static FunctionResultContent of(String callId, String name, ResultItems result) {
    return builder().id(callId).name(name).result(result).build();
  }

  /** Convenience factory method for successful result with String. */
  @ExcludeFromGeneratedCoverageReport
  public static FunctionResultContent ofSuccess(String callId, String name, String result) {
    return builder().id(callId).name(name).result(result).isError(false).build();
  }

  /** Convenience factory method for successful result with ResultItems. */
  @ExcludeFromGeneratedCoverageReport
  public static FunctionResultContent ofSuccess(String callId, String name, ResultItems result) {
    return builder().id(callId).name(name).result(result).isError(false).build();
  }

  /** Convenience factory method for error result with String. */
  @ExcludeFromGeneratedCoverageReport
  public static FunctionResultContent ofError(String callId, String name, String result) {
    return builder().id(callId).name(name).result(result).isError(true).build();
  }

  /** Convenience factory method for error result with ResultItems. */
  @ExcludeFromGeneratedCoverageReport
  public static FunctionResultContent ofError(String callId, String name, ResultItems result) {
    return builder().id(callId).name(name).result(result).isError(true).build();
  }
}
