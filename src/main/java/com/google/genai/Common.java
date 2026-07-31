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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.StreamReadConstraints;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.api.core.InternalApi;
import com.google.common.base.Strings;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.HttpOptions;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;
import java.util.logging.Logger;
import org.jspecify.annotations.Nullable;

/**
 * Common utility methods for the GenAI SDK.
 *
 * <p>All utility methods in this class are for internal use only and are subject to change without
 * notice.
 */
@InternalApi
public final class Common {

  @InternalApi public static final ObjectMapper objectMapper = new ObjectMapper();
  private static final Logger logger = Logger.getLogger(Common.class.getName());

  /**
   * System property to override the default max JSON string length (20MB) in read constraints.
   * E.g., if you want to change the limit to 100MB, you can set it via
   * `-Dgenai.json.maxReadLength=100000000`.
   */
  public static final String MAX_READ_LENGTH_PROPERTY = "genai.json.maxReadLength";

  /** Custom Jackson serializer for {@link Duration} to output "Xs" format. */
  @SuppressWarnings("JavaDurationGetSecondsToToSeconds")
  public static class CustomDurationSerializer extends JsonSerializer<Duration> {
    public CustomDurationSerializer() {}

    @Override
    public void serialize(
        Duration duration, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
        throws IOException {
      if (duration == null) {
        jsonGenerator.writeNull();
      } else {
        jsonGenerator.writeString(duration.getSeconds() + "s");
      }
    }
  }

  /** Custom Jackson deserializer for {@link Duration} to parse "Xs" format. */
  public static class CustomDurationDeserializer extends JsonDeserializer<Duration> {
    public CustomDurationDeserializer() {}

    @Override
    public @Nullable Duration deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      String value = p.getValueAsString();

      if (Strings.isNullOrEmpty(value)) {
        return null;
      }
      if (value.endsWith("s")) {
        String secondsPart = value.substring(0, value.length() - 1);
        try {
          long seconds = Long.parseLong(secondsPart);
          return Duration.ofSeconds(seconds);
        } catch (NumberFormatException e) {
          JsonMappingException exception =
              ctxt.weirdStringException(
                  value,
                  Duration.class,
                  "Cannot parse duration from string: " + value + ". Expected format 'Xs'.");
          exception.initCause(e);
          throw exception;
        }
      } else {
        throw ctxt.weirdStringException(
            value, Duration.class, "Expected duration in format 'Xs', but got: " + value);
      }
    }
  }

  /** Configures the stream read constraints for the JSON parser. */
  private static void configureStreamReadConstraints(int maxReadLength) {
    if (maxReadLength <= 0) {
      throw new IllegalArgumentException("Invalid JSON max read length: " + maxReadLength);
    }
    logger.info("Setting Jackson max read length to " + maxReadLength);

    StreamReadConstraints streamReadConstraints =
        StreamReadConstraints.builder().maxStringLength(maxReadLength).build();

    objectMapper.getFactory().setStreamReadConstraints(streamReadConstraints);
  }

  static {
    // Configure default stream read constraints.
    int maxReadLength = 20000000; // 20MB
    String maxReadLengthString = System.getProperty(MAX_READ_LENGTH_PROPERTY);
    if (maxReadLengthString != null) {
      try {
        maxReadLength = Integer.parseInt(maxReadLengthString);
      } catch (NumberFormatException e) {
        logger.warning(
            "Invalid JSON max read length in property "
                + MAX_READ_LENGTH_PROPERTY
                + ": "
                + maxReadLengthString
                + ". Using default value: "
                + maxReadLength);
      }
    }
    configureStreamReadConstraints(maxReadLength);

    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
    objectMapper.registerModule(new Jdk8Module());
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    SimpleModule durationModule = new SimpleModule();
    durationModule.addSerializer(Duration.class, new CustomDurationSerializer());
    durationModule.addDeserializer(Duration.class, new CustomDurationDeserializer());

    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.registerModule(durationModule);
  }

  /** Sets the maximum allowed string length for Jackson JSON parsing. */
  public static void setMaxReadLength(int maxReadLength) {
    configureStreamReadConstraints(maxReadLength);
  }

  /** Returns the shared {@link ObjectMapper} instance used by the SDK. */
  public static ObjectMapper objectMapper() {
    return objectMapper;
  }

  /** Serializes the given object into a JSON string. */
  public static String toJsonString(Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new GenAiIOException("Failed to serialize the object to JSON.", e);
    }
  }

  /** Serializes the given object into a Jackson {@link JsonNode}. */
  public static JsonNode toJsonNode(Object object) {
    return objectMapper.valueToTree(object);
  }

  /** Deserializes the given JSON string into an instance of {@code clazz}. */
  public static <T> T fromJsonString(String jsonString, Class<T> clazz) {
    try {
      return objectMapper.readValue(jsonString, clazz);
    } catch (JsonProcessingException e) {
      throw new GenAiIOException("Failed to deserialize the JSON string.", e);
    }
  }

  /** Deserializes the given Jackson {@link JsonNode} into an instance of {@code clazz}. */
  public static <T> T fromJsonNode(JsonNode jsonNode, Class<T> clazz) {
    try {
      return objectMapper.treeToValue(jsonNode, clazz);
    } catch (JsonProcessingException e) {
      throw new GenAiIOException("Failed to deserialize the JSON node.", e);
    }
  }

  /** Parses the given JSON string into a Jackson {@link JsonNode}. */
  public static JsonNode stringToJsonNode(String string) {
    try {
      return objectMapper.readTree(string);
    } catch (JsonProcessingException e) {
      throw new GenAiIOException("Failed to parse the JSON string.", e);
    }
  }

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
      JsonNode valueNode = toJsonNode(value);
      updateJsonNode(currentObject, keyToSet, valueNode);
    }
  }

  /** Updates an ObjectNode with a key and value, merging objects or avoiding empty overwrites. */
  public static void updateJsonNode(ObjectNode currentObject, String keyToSet, JsonNode valueNode) {
    JsonNode existingData = currentObject.get(keyToSet);

    if (existingData != null) {
      // Don't overwrite existing non-empty value with new empty value.
      if (valueNode == null || valueNode.isNull() || valueNode.isEmpty()) {
        return;
      }

      // Don't fail when overwriting value with same value
      if (valueNode.equals(existingData)) {
        return;
      }

      // Instead of overwriting dictionary with another dictionary, merge them.
      if (existingData.isObject() && valueNode.isObject()) {
        ((ObjectNode) existingData).setAll((ObjectNode) valueNode);
      } else {
        throw new IllegalArgumentException(
            "Cannot set value for an existing key. Key: "
                + keyToSet
                + "; Existing value: "
                + existingData
                + "; New value: "
                + valueNode);
      }
    } else {
      currentObject.set(keyToSet, valueNode);
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

  /**
   * Formats a template string by replacing {@code {key}} placeholders with values from {@code
   * data}.
   */
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

  /**
   * Checks whether the given object represents a zero or default empty value (e.g., null, 0, false,
   * '\0').
   */
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
    if (Strings.isNullOrEmpty(str)) {
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
      Set<String> excludeKeys = new HashSet<>();
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
      JsonNode data, String[] sourceKeys, String[] destKeys, int keyIdx, Set<String> excludeKeys) {
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
        List<String> keysToMove = new ArrayList<>();
        Iterator<String> fieldNames = objectNode.fieldNames();
        while (fieldNames.hasNext()) {
          String fieldName = fieldNames.next();
          if (!fieldName.startsWith("_") && !excludeKeys.contains(fieldName)) {
            keysToMove.add(fieldName);
          }
        }

        // Collect values to move
        Map<String, JsonNode> valuesToMove = new HashMap<>();
        for (String k : keysToMove) {
          valuesToMove.put(k, objectNode.get(k));
        }

        // Set values at destination
        for (Map.Entry<String, JsonNode> entry : valuesToMove.entrySet()) {
          String k = entry.getKey();
          JsonNode v = entry.getValue();

          // Build destination keys with the field name
          List<String> newDestKeysList = new ArrayList<>();
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
