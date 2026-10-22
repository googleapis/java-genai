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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.genai.errors.GenAiIOException;
import java.time.Duration;
import org.junit.jupiter.api.Test;

public class VideoMetadataTest {

  @Test
  public void testSerializationDeserialization_withDurations() {
    Duration start = Duration.ofSeconds(60);
    Duration end = Duration.ofSeconds(125);

    VideoMetadata original =
        VideoMetadata.builder().startOffset(start).endOffset(end).build();

    // Test serialization
    String json = original.toJson();
    assertTrue(json.contains("\"startOffset\":\"60s\""));
    assertTrue(json.contains("\"endOffset\":\"125s\""));

    // Test deserialization
    VideoMetadata deserialized = VideoMetadata.fromJson(json);
    assertTrue(deserialized.startOffset().isPresent());
    assertEquals(start, deserialized.startOffset().get());
    assertTrue(deserialized.endOffset().isPresent());
    assertEquals(end, deserialized.endOffset().get());
  }

  @Test
  public void testSerializationDeserialization_nullDurations() {
    VideoMetadata original = VideoMetadata.builder().build();

    // Test serialization
    String json = original.toJson();
    // Should not contain the fields due to JsonInclude.Include.NON_ABSENT
    assertFalse(json.contains("startOffset"));
    assertFalse(json.contains("endOffset"));

    // Test deserialization
    VideoMetadata deserialized = VideoMetadata.fromJson(json);
    assertFalse(deserialized.startOffset().isPresent());
    assertFalse(deserialized.endOffset().isPresent());
  }

  @Test
  public void testDeserialization_fractionalSeconds() {
    VideoMetadata deserialized =
        VideoMetadata.fromJson("{\"startOffset\":\"1.5s\",\"endOffset\":\"10.250s\"}");

    assertEquals(Duration.ofMillis(1500), deserialized.startOffset().get());
    assertEquals(Duration.ofMillis(10250), deserialized.endOffset().get());
  }

  @Test
  public void testDeserialization_nanosecondPrecisionAndNegative() {
    VideoMetadata deserialized =
        VideoMetadata.fromJson("{\"startOffset\":\"0.000000001s\",\"endOffset\":\"-1.5s\"}");

    assertEquals(Duration.ofNanos(1), deserialized.startOffset().get());
    assertEquals(Duration.ofMillis(-1500), deserialized.endOffset().get());
  }

  @Test
  public void testSerializationDeserialization_fractionalSeconds() {
    VideoMetadata original =
        VideoMetadata.builder()
            .startOffset(Duration.ofMillis(1500))
            .endOffset(Duration.ofMillis(-10250))
            .build();

    String json = original.toJson();
    assertTrue(json.contains("\"startOffset\":\"1.5s\""));
    assertTrue(json.contains("\"endOffset\":\"-10.25s\""));

    assertEquals(original, VideoMetadata.fromJson(json));
  }

  @Test
  public void testDeserialization_invalidDuration_throws() {
    for (String value :
        new String[] {"1.5", "abc", "s", "1.s", ".5s", "1.5.5s", "+1s", "1.0000000001s", "1e3s"}) {
      assertThrows(
          GenAiIOException.class,
          () -> VideoMetadata.fromJson("{\"startOffset\":\"" + value + "\"}"),
          value);
    }
  }

  @Test
  public void testDeserialization_outOfRangeDuration_throws() {
    assertThrows(
        GenAiIOException.class,
        () -> VideoMetadata.fromJson("{\"startOffset\":\"99999999999999999999s\"}"));
    assertThrows(
        GenAiIOException.class,
        () -> VideoMetadata.fromJson("{\"startOffset\":\"-9223372036854775808.5s\"}"));
  }

  @Test
  public void testDeserialization_durationRangeLimits() {
    VideoMetadata deserialized =
        VideoMetadata.fromJson(
            "{\"startOffset\":\"-9223372036854775808s\","
                + "\"endOffset\":\"9223372036854775807.999999999s\"}");

    assertEquals(Duration.ofSeconds(Long.MIN_VALUE), deserialized.startOffset().get());
    assertEquals(Duration.ofSeconds(Long.MAX_VALUE, 999_999_999), deserialized.endOffset().get());
  }
}