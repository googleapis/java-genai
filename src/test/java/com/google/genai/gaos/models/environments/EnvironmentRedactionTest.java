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

package com.google.genai.gaos.models.environments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import org.junit.jupiter.api.Test;

/** Tests that {@link Environment#toString()} redacts {@code environmentKey}. */
public final class EnvironmentRedactionTest {

  @Test
  public void toString_redactsNonNullEnvironmentKey() {
    String secret = "ek_secret_token_12345";
    Environment env = Environment.builder().id("env_1").environmentKey(secret).build();

    assertEquals(Optional.of(secret), env.environmentKey());
    String rendered = env.toString();
    assertFalse(rendered.contains(secret), "toString() leaked raw environmentKey: " + rendered);
    assertTrue(
        rendered.contains("environmentKey=[REDACTED]"), "missing redaction marker: " + rendered);
  }

  @Test
  public void toString_preservesNullWhenEnvironmentKeyOmitted() {
    Environment env = Environment.builder().id("env_1").build();

    assertEquals(Optional.empty(), env.environmentKey());
    String rendered = env.toString();
    assertTrue(rendered.contains("environmentKey=null"));
  }
}
