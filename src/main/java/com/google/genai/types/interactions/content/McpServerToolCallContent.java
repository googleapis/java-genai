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
import com.google.genai.JsonSerializable;
import com.google.genai.types.ExcludeFromGeneratedCoverageReport;
import java.util.Map;

@AutoValue
@JsonDeserialize(builder = McpServerToolCallContent.Builder.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("mcp_server_tool_call")
public abstract class McpServerToolCallContent extends JsonSerializable implements Content {

  @JsonProperty("id")
  public abstract String id();

  @JsonProperty("name")
  public abstract String name();

  @JsonProperty("server_name")
  public abstract String serverName();

  @JsonProperty("arguments")
  public abstract Map<String, Object> arguments();

  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_McpServerToolCallContent.Builder();
  }

  public abstract Builder toBuilder();

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return new AutoValue_McpServerToolCallContent.Builder();
    }

    @JsonProperty("id")
    public abstract Builder id(String id);

    @JsonProperty("name")
    public abstract Builder name(String name);

    @JsonProperty("server_name")
    public abstract Builder serverName(String serverName);

    @JsonProperty("arguments")
    public abstract Builder arguments(Map<String, Object> arguments);

    public abstract McpServerToolCallContent build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static McpServerToolCallContent fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, McpServerToolCallContent.class);
  }

  @ExcludeFromGeneratedCoverageReport
  public static McpServerToolCallContent of(
      String id, String name, String serverName, Map<String, Object> arguments) {
    return builder().id(id).name(name).serverName(serverName).arguments(arguments).build();
  }
}
