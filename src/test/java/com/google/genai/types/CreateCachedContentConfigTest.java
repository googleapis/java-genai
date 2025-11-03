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

package com.google.genai.types;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.Instant;
import org.junit.jupiter.api.Test;

public class CreateCachedContentConfigTest {

  @Test
  public void testSerializationDeserialization_withInstantAndDuration() {
    Instant expireTime = Instant.parse("2023-10-26T10:15:30.123Z");
    Duration ttl = Duration.ofSeconds(3600); // 1 hour

    CreateCachedContentConfig original =
        CreateCachedContentConfig.builder().expireTime(expireTime).ttl(ttl).build();

    // Test serialization
    String json = original.toJson();
    assertTrue(json.contains("\"expireTime\":\"2023-10-26T10:15:30.123Z\""));
    assertTrue(json.contains("\"ttl\":\"3600s\""));

    // Test deserialization
    CreateCachedContentConfig deserialized = CreateCachedContentConfig.fromJson(json);

    assertTrue(deserialized.expireTime().isPresent());
    assertEquals(expireTime, deserialized.expireTime().get());
    assertTrue(deserialized.ttl().isPresent());
    assertEquals(ttl, deserialized.ttl().get());

    // Verify overall equality if equals/hashCode are implemented correctly in AutoValue
    assertEquals(original, deserialized);
  }

  @Test
  public void testSerializationDeserialization_nullFields() {
    CreateCachedContentConfig original = CreateCachedContentConfig.builder().build();

    // Test serialization
    String json = original.toJson();
    // Should not contain the fields due to JsonInclude.Include.NON_ABSENT
    assertFalse(json.contains("expireTime"));
    assertFalse(json.contains("ttl"));

    // Test deserialization
    CreateCachedContentConfig deserialized = CreateCachedContentConfig.fromJson(json);
    assertFalse(deserialized.expireTime().isPresent());
    assertFalse(deserialized.ttl().isPresent());

    assertEquals(original, deserialized);
  }

  @Test
  public void testClearMethods() {
    Instant expireTime = Instant.parse("2023-10-26T10:15:30.123Z");
    Duration ttl = Duration.ofSeconds(3600); // 1 hour
    CreateCachedContentConfig config =
        CreateCachedContentConfig.builder().expireTime(expireTime).ttl(ttl).build();

    assertEquals(expireTime, config.expireTime().get());
    assertEquals(ttl, config.ttl().get());

    config = config.toBuilder().clearExpireTime().clearTtl().build();

    assertFalse(config.expireTime().isPresent());
    assertFalse(config.ttl().isPresent());
  }
}