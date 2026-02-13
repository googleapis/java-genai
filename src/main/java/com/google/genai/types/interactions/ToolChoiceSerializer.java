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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

/**
 * Custom serializer for ToolChoice that handles the oneOf union type.
 *
 * <p>This serializer handles two cases:
 *
 * <ul>
 *   <li>ToolChoiceType - serializes as a string (e.g., "auto", "any", "none", "validated")
 *   <li>ToolChoiceConfig - serializes as an object with allowed_tools field
 * </ul>
 */
public class ToolChoiceSerializer extends JsonSerializer<ToolChoice> {

  @Override
  public void serialize(ToolChoice value, JsonGenerator gen, SerializerProvider serializers)
      throws IOException {
    Object innerValue = value.getValue();

    if (innerValue == null) {
      gen.writeNull();
    } else if (innerValue instanceof ToolChoiceType) {
      // ToolChoiceType - serialize as string
      serializers.defaultSerializeValue(innerValue, gen);
    } else if (innerValue instanceof ToolChoiceConfig) {
      // ToolChoiceConfig - serialize as object
      serializers.defaultSerializeValue(innerValue, gen);
    } else {
      // Fallback for unknown types
      serializers.defaultSerializeValue(innerValue, gen);
    }
  }
}
