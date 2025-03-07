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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.Nullable;

/** Common utility methods for the GenAI SDK. */
public final class Common {

  private Common() {}

  static void setValueByPath(JsonObject jsonObject, String[] path, Object value) {
    if (path == null || path.length == 0) {
      throw new IllegalArgumentException("Path cannot be empty.");
    }
    if (jsonObject == null) {
      throw new IllegalArgumentException("JsonObject cannot be null.");
    }

    JsonObject currentObject = jsonObject;
    for (int i = 0; i < path.length - 1; i++) {
      String key = path[i];
      if (key.endsWith("[0]")) {
        String keyName = key.substring(0, key.length() - 3);
        JsonArray arrayNode;
        if (!currentObject.has(keyName)) {
          arrayNode = new JsonArray();
          arrayNode.add(new JsonObject());
          currentObject.add(keyName, arrayNode);
        }
        arrayNode = (JsonArray) currentObject.get(keyName);
        currentObject = arrayNode.get(0).getAsJsonObject();
      } else {
        if (!currentObject.has(key)) {
          currentObject.add(key, new JsonObject());
        }
        currentObject = currentObject.getAsJsonObject(key);
      }
    }

    currentObject.add(path[path.length - 1], toJsonElement(value));
  }

  static String formatMap(String template, JsonObject data) {
    Set<Map.Entry<String, JsonElement>> entries = data.entrySet();
    for (Map.Entry<String, JsonElement> entry : entries) {
      String key = entry.getKey();
      String placeholder = "{" + key + "}";
      if (template.contains(placeholder)) {
        template = template.replace(placeholder, entry.getValue().getAsString());
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

  static JsonElement getValueByPath(JsonElement object, String[] keys) {
    if (object == null || keys == null) {
      return null;
    }
    if (keys.length == 1 && keys[0].equals("_self")) {
      return object;
    }

    JsonElement currentElement = object;

    for (String key : keys) {
      if (currentElement.isJsonObject()) {
        currentElement = currentElement.getAsJsonObject().get(key);
      } else if (currentElement.isJsonArray()) {
        try {
          int index = Integer.parseInt(key);
          currentElement = currentElement.getAsJsonArray().get(index);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
          return null;
        }
      } else {
        return null;
      }
    }

    return currentElement;
  }

  private static @Nullable JsonElement toJsonElement(Object value) {
    if (value == null) {
      return null;
    } else if (value instanceof Number) {
      return new JsonPrimitive((Number) value);
    } else if (value instanceof String) {
      return new JsonPrimitive((String) value);
    } else if (value instanceof Boolean) {
      return new JsonPrimitive((Boolean) value);
    } else if (value instanceof Character) {
      return new JsonPrimitive(String.valueOf(value));
    } else if (value instanceof JsonObject) {
      return (JsonObject) value;
    } else if (value instanceof JsonArray) {
      return (JsonArray) value;
    } else {
      Gson gson = new Gson();
      return gson.toJsonTree(value);
    }
  }
}
