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

package com.google.genai.types.interactions;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.genai.JsonSerializable;
import com.google.genai.types.interactions.content.Content;
import java.util.Arrays;
import java.util.List;

/**
 * Union type for interaction input. Can be a string, list of Content objects, or list of Turn
 * objects.
 */
@JsonSerialize(using = InputSerializer.class)
public final class Input extends JsonSerializable {
  private final Object value;

  private Input(Object value) {
    this.value = value;
  }

  /**
   * Creates an Input from a string.
   *
   * @param text The input text
   * @return An Input instance wrapping the string
   */
  public static Input fromString(String text) {
    return new Input(text);
  }

  /**
   * Creates an Input from a single Content object.
   *
   * @param content The interaction content object
   * @return An Input instance wrapping the content
   */
  public static Input fromContent(Content content) {
    return new Input(content);
  }

  /**
   * Creates an Input from a list of Content objects.
   *
   * @param contents The list of interaction content objects
   * @return An Input instance wrapping the contents
   */
  public static Input fromContents(List<Content> contents) {
    return new Input(contents);
  }

  /**
   * Creates an Input from Content objects (varargs).
   *
   * @param contents The interaction content objects
   * @return An Input instance wrapping the contents
   */
  public static Input fromContents(Content... contents) {
    return new Input(Arrays.asList(contents));
  }

  /**
   * Creates an Input from a list of Turn objects.
   *
   * @param turns The list of conversation turns
   * @return An Input instance wrapping the turns
   */
  public static Input fromTurns(List<Turn> turns) {
    return new Input(turns);
  }

  /**
   * Creates an Input from Turn objects (varargs).
   *
   * @param turns The conversation turns
   * @return An Input instance wrapping the turns
   */
  public static Input fromTurns(Turn... turns) {
    return new Input(Arrays.asList(turns));
  }

  /**
   * Gets the underlying value.
   *
   * <p>Package-private for use by InputSerializer. Users should not need to call this method
   * directly - the Input object is automatically serialized when passed to CreateInteractionConfig.
   *
   * @return The wrapped value (String, List&lt;Content&gt;, or List&lt;Turn&gt;)
   */
  Object getValue() {
    return value;
  }
}
