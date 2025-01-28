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
import java.util.Iterator;
import java.util.Map;

/** Common utility methods for the GenAI SDK. */
public final class Common {

  private Common() {}

  static void setValueByPath(ObjectNode jsonObject, String[] path, Object value) {
    if (path == null || path.length == 0) {
      throw new IllegalArgumentException("Path cannot be empty.");
    }
    if (jsonObject == null) {
      throw new IllegalArgumentException("JsonObject cannot be null.");
    }

    ObjectNode currentObject = jsonObject;
    for (int i = 0; i < path.length - 1; i++) {
      String key = path[i];
      if (!currentObject.has(key)) {
        currentObject.putObject(key);
      }
      currentObject = (ObjectNode) currentObject.get(key);
    }

    currentObject.put(path[path.length - 1], JsonSerializable.toJsonNode(value));
  }

  static String formatMap(String template, JsonNode data) {
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

  static boolean isZero(Object obj) {
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

  static Object getValueByPath(JsonNode object, String[] keys) {
    if (object == null || keys == null) {
      return null;
    }
    if (keys.length == 1 && keys[0].equals("_self")) {
      return object;
    }

    JsonNode currentObject = object;

    for (String key : keys) {
      if (currentObject instanceof ObjectNode) {
        currentObject = currentObject.get(key);
      } else if (currentObject instanceof ArrayNode) {
        try {
          int index = Integer.parseInt(key);
          currentObject = currentObject.get(index);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
          return null;
        }
      } else {
        return null;
      }
    }

    return currentObject;
  }
}
