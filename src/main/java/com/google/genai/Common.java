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
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.api.core.InternalApi;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.HttpOptions;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;
import org.jspecify.annotations.Nullable;

/** Common utility methods for the GenAI SDK. */
@InternalApi
public final class Common {

  private Common() {}

  /** A class that holds the path, body, and http options of an API request. */
  public static class BuiltRequest {
    final String path;
    final String body;
    final Optional<HttpOptions> httpOptions;

    /** Constructor for BuiltRequest. */
    public BuiltRequest(String path, String body, Optional<HttpOptions> httpOptions) {
      this.path = path;
      this.body = body;
      this.httpOptions = httpOptions;
    }

    public String body() {
      return body;
    }

    public String path() {
      return path;
    }

    public Optional<HttpOptions> httpOptions() {
      return httpOptions;
    }
  }

  /**
   * Sets the value of an object by a path.
   *
   * <p>setValueByPath({}, ['a', 'b'], v) -> {'a': {'b': v}}
   *
   * <p>setValueByPath({}, ['a', 'b[]', c], [v1, v2]) -> {'a': {'b': [{'c': v1}, {'c': v2}]}}
   *
   * <p>setValueByPath({'a': {'b':[{'c': v1}, {'c': v2}]}}, ['a', 'b[]', 'd'], v3) -> {'a': {'b':
   * [{'c': v1, 'd': v3}, {'c': v2,'d': v3}]}}
   */
  public static void setValueByPath(ObjectNode jsonObject, String[] path, Object value) {
    if (path == null || path.length == 0) {
      throw new IllegalArgumentException("Path cannot be empty.");
    }
    if (jsonObject == null) {
      throw new IllegalArgumentException("JsonObject cannot be null.");
    }

    ObjectNode currentObject = jsonObject;
    for (int i = 0; i < path.length - 1; i++) {
      String key = path[i];

      if (key.endsWith("[]")) {
        String keyName = key.substring(0, key.length() - 2);
        if (!currentObject.has(keyName)) {
          currentObject.putArray(keyName);
        }
        ArrayNode arrayNode = (ArrayNode) currentObject.get(keyName);
        if (value instanceof List) {
          List<?> listValue = (List<?>) value;
          if (arrayNode.size() != listValue.size()) {
            arrayNode.removeAll();
            for (int j = 0; j < listValue.size(); j++) {
              arrayNode.addObject();
            }
          }
          for (int j = 0; j < arrayNode.size(); j++) {
            setValueByPath(
                (ObjectNode) arrayNode.get(j),
                Arrays.copyOfRange(path, i + 1, path.length),
                listValue.get(j));
          }
        } else {
          if (arrayNode.size() == 0) {
            arrayNode.addObject();
          }
          for (int j = 0; j < arrayNode.size(); j++) {
            setValueByPath(
                (ObjectNode) arrayNode.get(j), Arrays.copyOfRange(path, i + 1, path.length), value);
          }
        }
        return;
      } else if (key.endsWith("[0]")) {
        String keyName = key.substring(0, key.length() - 3);
        if (!currentObject.has(keyName)) {
          currentObject.putArray(keyName).addObject();
        }
        currentObject = (ObjectNode) ((ArrayNode) currentObject.get(keyName)).get(0);
      } else {
        if (!currentObject.has(key)) {
          currentObject.putObject(key);
        }
        currentObject = (ObjectNode) currentObject.get(key);
      }
    }

    String keyToSet = path[path.length - 1];
    if (keyToSet.equals("_self") && value instanceof ObjectNode) {
      ObjectNode sourceNode = (ObjectNode) value;
      currentObject.setAll(sourceNode);
    } else {
      JsonNode valueNode = JsonSerializable.toJsonNode(value);
      Transformers.updateJsonNode(currentObject, keyToSet, valueNode);
    }
  }

  /**
   * Gets the value of an object by a path.
   *
   * <p>getValueByPath({'a': {'b': v}}, ['a', 'b']) -> v
   *
   * <p>getValueByPath({'a': {'b': [{'c': v1}, {'c': v2}]}}, ['a', 'b[]', 'c']) -> [v1, v2]
   */
  public static @Nullable Object getValueByPath(JsonNode object, String[] keys) {
    return getValueByPath(object, keys, null);
  }

  /**
   * Gets the value of an object by a path, returning a default value if the path does not exist.
   *
   * <p>getValueByPath({'a': {'b': v}}, ['a', 'b'], 'default') -> v
   *
   * <p>getValueByPath({'a': {'c': v}}, ['a', 'b'], 'default') -> 'default'
   */
  public static @Nullable Object getValueByPath(
      JsonNode object, String[] keys, @Nullable Object defaultValue) {
    if (object == null || keys == null) {
      return defaultValue;
    }
    if (keys.length == 1 && keys[0].equals("_self")) {
      return object;
    }

    JsonNode currentObject = object;
    for (int i = 0; i < keys.length; i++) {
      String key = keys[i];

      if (currentObject == null) {
        return defaultValue;
      }

      if (key.endsWith("[]")) {
        String keyName = key.substring(0, key.length() - 2);
        if (currentObject.isObject()
            && ((ObjectNode) currentObject).has(keyName)
            && ((ObjectNode) currentObject).get(keyName).isArray()) {
          ArrayNode arrayNode = (ArrayNode) ((ObjectNode) currentObject).get(keyName);
          if (keys.length - 1 == i) {
            return arrayNode;
          }
          ArrayNode result = JsonSerializable.objectMapper().createArrayNode();
          for (JsonNode element : arrayNode) {
            JsonNode node =
                (JsonNode)
                    getValueByPath(
                        element, Arrays.copyOfRange(keys, i + 1, keys.length), defaultValue);
            if (node != null) {
              result.add(node);
            }
          }
          return result;
        } else {
          return defaultValue;
        }
      } else if (key.endsWith("[0]")) {
        String keyName = key.substring(0, key.length() - 3);
        if (currentObject.isObject()
            && ((ObjectNode) currentObject).has(keyName)
            && ((ObjectNode) currentObject).get(keyName).isArray()
            && ((ArrayNode) ((ObjectNode) currentObject).get(keyName)).size() > 0) {
          currentObject = ((ArrayNode) ((ObjectNode) currentObject).get(keyName)).get(0);
        } else {
          return defaultValue;
        }
      } else {
        if (currentObject.isObject() && ((ObjectNode) currentObject).has(key)) {
          currentObject = ((ObjectNode) currentObject).get(key);
        } else {
          return defaultValue;
        }
      }
    }

    return currentObject;
  }

  public static String formatMap(String template, JsonNode data) {
    if (data == null) {
      return template;
    }

    Iterator<Map.Entry<String, JsonNode>> fields = data.fields();
    while (fields.hasNext()) {
      Map.Entry<String, JsonNode> field = fields.next();
      String key = field.getKey();
      String placeholder = "{" + key + "}";
      if (template.contains(placeholder)) {
        template = template.replace(placeholder, data.get(key).asText());
      }
    }
    return template;
  }

  public static boolean isZero(Object obj) {
    if (obj == null) {
      return true;
    }

    if (obj instanceof Number) {
      Number num = (Number) obj;
      return num.doubleValue() == 0.0;
    } else if (obj instanceof Character) {
      Character ch = (Character) obj;
      return ch == '\0';
    } else if (obj instanceof Boolean) {
      Boolean bool = (Boolean) obj;
      return !bool;
    }

    return false;
  }

  /**
   * Converts a Jackson ObjectNode into a URL-encoded query string. Assumes values are simple types
   * (text, number, boolean, or null) that can be represented as a single string.
   *
   * @param paramsNode The ObjectNode containing the parameters to encode.
   * @return A URL-encoded string (e.g., "key1=value1&key2=value2").
   */
  public static String urlEncode(ObjectNode paramsNode) {
    if (paramsNode == null || paramsNode.size() == 0) {
      return "";
    }

    StringJoiner queryBuilder = new StringJoiner("&");
    String utf8 = StandardCharsets.UTF_8.name();

    try {
      Iterator<Map.Entry<String, JsonNode>> fields = paramsNode.fields();
      while (fields.hasNext()) {
        Map.Entry<String, JsonNode> entry = fields.next();
        String encodedKey = URLEncoder.encode(entry.getKey(), utf8);
        JsonNode valueNode = entry.getValue();

        if (valueNode.isNull()) {
          queryBuilder.add(encodedKey + "=");
        } else {
          // In python(and replay files), "*" is encoded as "%2A" although it is not required.
          // So we keep the same behavior here.
          String encodedValue = URLEncoder.encode(valueNode.asText(""), utf8).replace("*", "%2A");
          queryBuilder.add(encodedKey + "=" + encodedValue);
        }
      }
    } catch (UnsupportedEncodingException e) {
      throw new GenAiIOException("UTF-8 encoding not supported", e);
    }
    return queryBuilder.toString();
  }

  /** Converts a snake_case string to camelCase. */
  public static String snakeToCamel(String str) {
    if (str == null || str.isEmpty()) {
      return str;
    }

    StringBuilder sb = new StringBuilder();
    boolean capitalizeNext = false;
    for (char c : str.toCharArray()) {
      if (c == '_') {
        capitalizeNext = true;
      } else {
        sb.append(capitalizeNext ? Character.toUpperCase(c) : c);
        capitalizeNext = false;
      }
    }
    return sb.toString();
  }

  /**
   * Moves values from source paths to destination paths.
   *
   * <p>Example: moveValueByPath( {'requests': [{'content': v1}, {'content': v2}]}, {'requests[].*':
   * 'requests[].request.*'} ) -> {'requests': [{'request': {'content': v1}}, {'request':
   * {'content': v2}}]}
   */
  public static void moveValueByPath(JsonNode data, Map<String, String> paths) {
    if (data == null || paths == null) {
      return;
    }

    for (Map.Entry<String, String> entry : paths.entrySet()) {
      String sourcePath = entry.getKey();
      String destPath = entry.getValue();

      String[] sourceKeys = sourcePath.split("\\.");
      String[] destKeys = destPath.split("\\.");

      // Determine keys to exclude from wildcard to avoid cyclic references
      java.util.Set<String> excludeKeys = new java.util.HashSet<>();
      int wildcardIdx = -1;

      for (int i = 0; i < sourceKeys.length; i++) {
        if (sourceKeys[i].equals("*")) {
          wildcardIdx = i;
          break;
        }
      }

      if (wildcardIdx != -1 && destKeys.length > wildcardIdx) {
        // Extract the intermediate key between source and dest paths
        // Example: source=['requests[]', '*'], dest=['requests[]', 'request', '*']
        // We want to exclude 'request'
        for (int i = wildcardIdx; i < destKeys.length; i++) {
          String key = destKeys[i];
          if (!key.equals("*") && !key.endsWith("[]") && !key.endsWith("[0]")) {
            excludeKeys.add(key);
          }
        }
      }

      // Move values recursively
      moveValueRecursive(data, sourceKeys, destKeys, 0, excludeKeys);
    }
  }

  /**
   * Recursively moves values from source path to destination path.
   *
   * @param data The current node being processed
   * @param sourceKeys The source path keys
   * @param destKeys The destination path keys
   * @param keyIdx The current index in the key arrays
   * @param excludeKeys Keys to exclude when processing wildcards
   */
  public static void moveValueRecursive(
      JsonNode data,
      String[] sourceKeys,
      String[] destKeys,
      int keyIdx,
      java.util.Set<String> excludeKeys) {
    if (keyIdx >= sourceKeys.length || data == null) {
      return;
    }

    String key = sourceKeys[keyIdx];

    if (key.endsWith("[]")) {
      // Handle array iteration
      String keyName = key.substring(0, key.length() - 2);
      if (data.isObject()
          && ((ObjectNode) data).has(keyName)
          && ((ObjectNode) data).get(keyName).isArray()) {
        ArrayNode arrayNode = (ArrayNode) ((ObjectNode) data).get(keyName);
        for (JsonNode item : arrayNode) {
          moveValueRecursive(item, sourceKeys, destKeys, keyIdx + 1, excludeKeys);
        }
      }
    } else if (key.equals("*")) {
      // Handle wildcard - move all fields
      if (data.isObject()) {
        ObjectNode objectNode = (ObjectNode) data;

        // Get all keys to move (excluding specified keys)
        java.util.List<String> keysToMove = new java.util.ArrayList<>();
        Iterator<String> fieldNames = objectNode.fieldNames();
        while (fieldNames.hasNext()) {
          String fieldName = fieldNames.next();
          if (!fieldName.startsWith("_") && !excludeKeys.contains(fieldName)) {
            keysToMove.add(fieldName);
          }
        }

        // Collect values to move
        java.util.Map<String, JsonNode> valuesToMove = new java.util.HashMap<>();
        for (String k : keysToMove) {
          valuesToMove.put(k, objectNode.get(k));
        }

        // Set values at destination
        for (Map.Entry<String, JsonNode> entry : valuesToMove.entrySet()) {
          String k = entry.getKey();
          JsonNode v = entry.getValue();

          // Build destination keys with the field name
          java.util.List<String> newDestKeysList = new java.util.ArrayList<>();
          for (int i = keyIdx; i < destKeys.length; i++) {
            String dk = destKeys[i];
            if (dk.equals("*")) {
              newDestKeysList.add(k);
            } else {
              newDestKeysList.add(dk);
            }
          }

          String[] newDestKeys = newDestKeysList.toArray(new String[0]);
          setValueByPath(objectNode, newDestKeys, v);
        }

        // Delete from source
        for (String k : keysToMove) {
          objectNode.remove(k);
        }
      }
    } else {
      // Navigate to next level
      if (data.isObject() && ((ObjectNode) data).has(key)) {
        JsonNode nextNode = ((ObjectNode) data).get(key);
        moveValueRecursive(nextNode, sourceKeys, destKeys, keyIdx + 1, excludeKeys);
      }
    }
  }
}
