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

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;
import org.jspecify.annotations.Nullable;

/** A class that can be serialized to JSON and deserialized from JSON. */
public abstract class JsonSerializable {

  protected static final Gson gson;

  static {
    gson =
        new GsonBuilder()
            .registerTypeAdapterFactory(GenerateTypeAdapter.FACTORY)
            .registerTypeAdapter(
                Optional.class,
                new JsonSerializer<Optional<?>>() {
                  @Override
                  public @Nullable JsonElement serialize(
                      Optional<?> src, Type typeOfSrc, JsonSerializationContext context) {
                    return src.isPresent() ? context.serialize(src.get()) : null;
                  }
                })
            .registerTypeAdapter(
                Optional.class,
                new JsonDeserializer<Optional<?>>() {
                  @Override
                  public Optional<?> deserialize(
                      JsonElement json, Type typeOfT, JsonDeserializationContext context)
                      throws JsonParseException {
                    if (json.isJsonNull()) {
                      return Optional.empty();
                    } else {
                      Type actualType = null;
                      if (typeOfT instanceof ParameterizedType) {
                        actualType = ((ParameterizedType) typeOfT).getActualTypeArguments()[0];
                      }
                      return Optional.of(context.deserialize(json, actualType));
                    }
                  }
                })
            .setExclusionStrategies(
                new ExclusionStrategy() {
                  @Override
                  public boolean shouldSkipField(FieldAttributes f) {
                    // Skip fields with null values
                    try {
                      Object value = f.getDeclaringClass().getDeclaredField(f.getName()).get(f);
                      return value == null; // If field is null, exclude it
                    } catch (IllegalAccessException | NoSuchFieldException e) {
                      return false;
                    }
                  }

                  @Override
                  public boolean shouldSkipClass(Class<?> clazz) {
                    return false; // Don't skip classes by default
                  }
                })
            .create();
  }

  /** Serializes an object to a Json string. */
  protected static String toJsonString(Object object) {
    return gson.toJson(object);
  }

  /** Serializes the instance to a Json string. */
  public String toJson() {
    return toJsonString(this);
  }

  /** Deserializes a Json string to an object of the given type. */
  public static <T extends JsonSerializable> T fromJsonString(String jsonString, Class<T> clazz) {
    return gson.fromJson(jsonString, clazz);
  }

  /** Deserializes a Json string to an object of the given type. */
  public static Object fromJsonString(String jsonString, Type typeOfT) {
    return gson.fromJson(jsonString, typeOfT);
  }

  /** Deserializes a Json string to an object of the given type. */
  public static JsonElement fromJsonString(String jsonString) {
    return JsonParser.parseString(jsonString);
  }

  /** Deserializes a JsonElement to an object of the given type. */
  public static <T extends JsonSerializable> T fromJsonElement(
      JsonElement jsonElement, Class<T> clazz) {
    return gson.fromJson(jsonElement, clazz);
  }

  /** Serializes the instance to a Json string. */
  public JsonElement toJsonElement() {
    return gson.toJsonTree(this);
  }

  /** Serializes an object to a JsonElement. */
  protected static JsonElement toJsonElement(Object object) {
    return gson.toJsonTree(object);
  }
}
