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

package com.google.genai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.InternalApi;
import java.time.Duration;

/** A class that can be serialized to JSON and deserialized from JSON. */
public abstract class JsonSerializable {

  @InternalApi protected static final ObjectMapper objectMapper = Common.objectMapper;

  /**
   * System property to override the default max JSON string length (20MB) in read constraints.
   * E.g., if you want to change the limit to 100MB, you can set it via
   * `-Dgenai.json.maxReadLength=100000000`.
   */
  public static final String MAX_READ_LENGTH_PROPERTY = Common.MAX_READ_LENGTH_PROPERTY;

  /** Custom Jackson serializer for {@link Duration} to output "Xs" format. */
  public static class CustomDurationSerializer extends Common.CustomDurationSerializer {
    public CustomDurationSerializer() {}
  }

  /** Custom Jackson deserializer for {@link Duration} to parse "Xs" format. */
  public static class CustomDurationDeserializer extends Common.CustomDurationDeserializer {
    public CustomDurationDeserializer() {}
  }

  /** Serializes the instance to a Json string. */
  public String toJson() {
    return Common.toJsonString(this);
  }

  /** Serializes an object to a Json string. */
  public static String toJsonString(Object object) {
    return Common.toJsonString(object);
  }

  /** Serializes an object to a JsonNode. */
  public static JsonNode toJsonNode(Object object) {
    return Common.toJsonNode(object);
  }

  /** Deserializes a Json string to an object of the given type. This is for internal use only. */
  @InternalApi
  public static <T extends JsonSerializable> T fromJsonString(String jsonString, Class<T> clazz) {
    return Common.fromJsonString(jsonString, clazz);
  }

  /** Deserializes a JsonNode to an object of the given type. */
  @InternalApi
  public static <T extends JsonSerializable> T fromJsonNode(JsonNode jsonNode, Class<T> clazz) {
    return Common.fromJsonNode(jsonNode, clazz);
  }

  /** Converts a Json string to a JsonNode. */
  public static JsonNode stringToJsonNode(String string) {
    return Common.stringToJsonNode(string);
  }

  /**
   * Overrides the default maximum JSON string length (20MB) for the JSON parser.
   *
   * <p><b>Warning:</b> This modifies a global static setting. It will overrides the system property
   * setting via {@link #MAX_READ_LENGTH_PROPERTY}. This method is <b>not thread-safe</b>.
   *
   * @param maxReadLength the new maximum string length in bytes (e.g., 100_000_000 for 100MB).
   */
  public static void setMaxReadLength(int maxReadLength) {
    Common.setMaxReadLength(maxReadLength);
  }

  /** Returns the shared {@link ObjectMapper} instance used by the SDK. */
  public static ObjectMapper objectMapper() {
    return Common.objectMapper();
  }
}
