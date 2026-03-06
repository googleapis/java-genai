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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

/**
 * Custom deserializer for ToolChoice that handles the oneOf union type.
 *
 * <p>This deserializer handles two cases:
 *
 * <ul>
 *   <li>String value - deserializes as ToolChoiceType (e.g., "auto", "any", "none", "validated")
 *   <li>Object value - deserializes as ToolChoiceConfig with allowed_tools field
 * </ul>
 */
public class ToolChoiceDeserializer extends JsonDeserializer<ToolChoice> {

  @Override
  public ToolChoice deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    JsonToken token = p.currentToken();

    if (token == JsonToken.VALUE_NULL) {
      return null;
    } else if (token == JsonToken.VALUE_STRING) {
      // String value - deserialize as ToolChoiceType
      String value = p.getText();
      ToolChoiceType type = new ToolChoiceType(value);
      return ToolChoice.fromType(type);
    } else if (token == JsonToken.START_OBJECT) {
      // Object value - deserialize as ToolChoiceConfig
      ToolChoiceConfig config = p.readValueAs(ToolChoiceConfig.class);
      return ToolChoice.fromConfig(config);
    } else {
      throw ctxt.wrongTokenException(p, ToolChoice.class, JsonToken.VALUE_STRING,
          "Expected string or object for ToolChoice");
    }
  }
}
