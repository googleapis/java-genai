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

@AutoValue
@JsonDeserialize(builder = McpServerToolResultContent.Builder.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("mcp_server_tool_result")
public abstract class McpServerToolResultContent extends JsonSerializable implements Content {

  @JsonProperty("call_id")
  public abstract String callId();

  /**
   * The result returned by the MCP server tool.
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

  @JsonProperty("name")
  public abstract Optional<String> name();

  @JsonProperty("server_name")
  public abstract Optional<String> serverName();

  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_McpServerToolResultContent.Builder();
  }

  public abstract Builder toBuilder();

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return new AutoValue_McpServerToolResultContent.Builder();
    }

    @JsonProperty("call_id")
    public abstract Builder callId(String callId);

    @JsonProperty("result")
    public abstract Builder result(Object result);

    @JsonProperty("name")
    public abstract Builder name(String name);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder name(Optional<String> name);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearName() {
      return name(Optional.empty());
    }

    @JsonProperty("server_name")
    public abstract Builder serverName(String serverName);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder serverName(Optional<String> serverName);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearServerName() {
      return serverName(Optional.empty());
    }

    public abstract McpServerToolResultContent build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static McpServerToolResultContent fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, McpServerToolResultContent.class);
  }

  @ExcludeFromGeneratedCoverageReport
  public static McpServerToolResultContent of(String callId, Object result) {
    return builder().callId(callId).result(result).build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static McpServerToolResultContent of(
      String callId, Object result, String name, String serverName) {
    return builder().callId(callId).result(result).name(name).serverName(serverName).build();
  }

  /** Convenience factory method with String result. */
  @ExcludeFromGeneratedCoverageReport
  public static McpServerToolResultContent of(String callId, String result) {
    return builder().callId(callId).result(result).build();
  }

  /** Convenience factory method with ResultItems result. */
  @ExcludeFromGeneratedCoverageReport
  public static McpServerToolResultContent of(String callId, ResultItems result) {
    return builder().callId(callId).result(result).build();
  }

  /** Convenience factory method with Map result. */
  @ExcludeFromGeneratedCoverageReport
  public static McpServerToolResultContent of(String callId, Map<String, Object> result) {
    return builder().callId(callId).result(result).build();
  }

  /** Convenience factory method with String result and all fields. */
  @ExcludeFromGeneratedCoverageReport
  public static McpServerToolResultContent of(
      String callId, String result, String name, String serverName) {
    return builder().callId(callId).result(result).name(name).serverName(serverName).build();
  }

  /** Convenience factory method with ResultItems result and all fields. */
  @ExcludeFromGeneratedCoverageReport
  public static McpServerToolResultContent of(
      String callId, ResultItems result, String name, String serverName) {
    return builder().callId(callId).result(result).name(name).serverName(serverName).build();
  }

  /** Convenience factory method with Map result and all fields. */
  @ExcludeFromGeneratedCoverageReport
  public static McpServerToolResultContent of(
      String callId, Map<String, Object> result, String name, String serverName) {
    return builder().callId(callId).result(result).name(name).serverName(serverName).build();
  }
}
