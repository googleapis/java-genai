/*
 * Copyright 2026 Google LLC
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

package com.google.genai.types;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AuthTokenTest {

  @Test
  public void testAuthToken() {
    AuthToken token = AuthToken.builder().name("test-token").uses(5).build();

    assertEquals("test-token", token.name().get());
    assertEquals(5, token.uses().get());

    String json = token.toJson();
    AuthToken deserialized = AuthToken.fromJson(json);
    assertEquals("test-token", deserialized.name().get());
    assertEquals(5, deserialized.uses().get());
  }
}
