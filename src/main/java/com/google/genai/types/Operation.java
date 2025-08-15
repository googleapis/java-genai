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
import com.fasterxml.jackson.databind.JsonNode;
import com.google.genai.JsonSerializable;
import java.util.Map;
import java.util.Optional;

/** A long-running operation. */
public abstract class Operation<T, O extends Operation<T, O>> extends JsonSerializable {
  /**
   * The server-assigned name, which is only unique within the same service that originally returns
   * it. If you use the default HTTP mapping, the `name` should be a resource name ending with
   * `operations/{unique_id}`.
   */
  @JsonProperty("name")
  public abstract Optional<String> name();

  /**
   * Service-specific metadata associated with the operation. It typically contains progress
   * information and common metadata such as create time. Some services might not provide such
   * metadata. Any method that returns a long-running operation should document the metadata type,
   * if any.
   */
  @JsonProperty("metadata")
  public abstract Optional<Map<String, Object>> metadata();

  /**
   * If the value is `false`, it means the operation is still in progress. If `true`, the operation
   * is completed, and either `error` or `response` is available.
   */
  @JsonProperty("done")
  public abstract Optional<Boolean> done();

  /** The error result of the operation in case of failure or cancellation. */
  @JsonProperty("error")
  public abstract Optional<Map<String, Object>> error();

  /** The generated videos. */
  @JsonProperty("response")
  public abstract Optional<T> response();

  /**
   * Creates a new Operation object from an API response.
   *
   * @param apiResponse The API response.
   * @param isVertexAi Whether the API response is from Vertex AI.
   * @return The new Operation object.
   */
  public abstract O fromApiResponse(JsonNode apiResponse, boolean isVertexAi);
}
