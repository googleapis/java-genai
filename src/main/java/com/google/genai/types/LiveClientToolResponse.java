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
import java.util.Optional;

/**
 * Client generated response to a `ToolCall` received from the server.
 *
 * <p>Individual `FunctionResponse` objects are matched to the respective `FunctionCall` objects by
 * the `id` field.
 *
 * <p>Note that in the unary and server-streaming GenerateContent APIs function calling happens by
 * exchanging the `Content` parts, while in the bidi GenerateContent APIs function calling happens
 * over this dedicated set of messages.
 */
@AutoValue
@JsonDeserialize(builder = AutoValue_LiveClientToolResponse.Builder.class)
public abstract class LiveClientToolResponse extends JsonSerializable {
  /** The response to the function calls. */
  @JsonProperty("functionResponses")
  public abstract Optional<List<FunctionResponse>> functionResponses();

  /** Instantiates a builder for LiveClientToolResponse. */
  public static Builder builder() {
    return new AutoValue_LiveClientToolResponse.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for LiveClientToolResponse. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("functionResponses")
    public abstract Builder functionResponses(List<FunctionResponse> functionResponses);

    public abstract LiveClientToolResponse build();
  }

  /** Deserializes a JSON string to a LiveClientToolResponse object. */
  public static LiveClientToolResponse fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, LiveClientToolResponse.class);
  }
}
