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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/** Tests for ResponseModality class. */
public class ResponseModalityTest {

  @Test
  public void testResponseModalityFromKnownEnum_TEXT() {
    ResponseModality modality = new ResponseModality(ResponseModality.Known.TEXT);
    assertEquals("text", modality.toString());
    assertEquals(ResponseModality.Known.TEXT, modality.knownEnum());
  }

  @Test
  public void testResponseModalityFromKnownEnum_IMAGE() {
    ResponseModality modality = new ResponseModality(ResponseModality.Known.IMAGE);
    assertEquals("image", modality.toString());
    assertEquals(ResponseModality.Known.IMAGE, modality.knownEnum());
  }

  @Test
  public void testResponseModalityFromKnownEnum_AUDIO() {
    ResponseModality modality = new ResponseModality(ResponseModality.Known.AUDIO);
    assertEquals("audio", modality.toString());
    assertEquals(ResponseModality.Known.AUDIO, modality.knownEnum());
  }

  @Test
  public void testResponseModalityFromString_TEXT() {
    ResponseModality modality = new ResponseModality("text");
    assertEquals("text", modality.toString());
    assertEquals(ResponseModality.Known.TEXT, modality.knownEnum());
  }

  @Test
  public void testResponseModalityFromString_IMAGE() {
    ResponseModality modality = new ResponseModality("image");
    assertEquals("image", modality.toString());
    assertEquals(ResponseModality.Known.IMAGE, modality.knownEnum());
  }

  @Test
  public void testResponseModalityFromString_AUDIO() {
    ResponseModality modality = new ResponseModality("audio");
    assertEquals("audio", modality.toString());
    assertEquals(ResponseModality.Known.AUDIO, modality.knownEnum());
  }

  @Test
  public void testResponseModalityFromString_caseInsensitive() {
    ResponseModality modality1 = new ResponseModality("TEXT");
    ResponseModality modality2 = new ResponseModality("Text");
    ResponseModality modality3 = new ResponseModality("text");

    assertEquals(ResponseModality.Known.TEXT, modality1.knownEnum());
    assertEquals(ResponseModality.Known.TEXT, modality2.knownEnum());
    assertEquals(ResponseModality.Known.TEXT, modality3.knownEnum());
  }

  @Test
  public void testResponseModalityFromString_unknownValue() {
    ResponseModality modality = new ResponseModality("video");
    assertEquals("video", modality.toString());
    assertEquals(ResponseModality.Known.RESPONSE_MODALITY_UNSPECIFIED, modality.knownEnum());
  }

  @Test
  public void testResponseModalityEquals_sameKnownEnum() {
    ResponseModality modality1 = new ResponseModality(ResponseModality.Known.TEXT);
    ResponseModality modality2 = new ResponseModality(ResponseModality.Known.TEXT);
    assertEquals(modality1, modality2);
  }

  @Test
  public void testResponseModalityEquals_differentKnownEnum() {
    ResponseModality modality1 = new ResponseModality(ResponseModality.Known.TEXT);
    ResponseModality modality2 = new ResponseModality(ResponseModality.Known.IMAGE);
    assertNotEquals(modality1, modality2);
  }

  @Test
  public void testResponseModalityEquals_sameUnknownValue() {
    ResponseModality modality1 = new ResponseModality("custom_modality");
    ResponseModality modality2 = new ResponseModality("custom_modality");
    assertEquals(modality1, modality2);
  }

  @Test
  public void testResponseModalityEquals_differentUnknownValue() {
    ResponseModality modality1 = new ResponseModality("custom1");
    ResponseModality modality2 = new ResponseModality("custom2");
    assertNotEquals(modality1, modality2);
  }

  @Test
  public void testResponseModalityHashCode_sameKnownEnum() {
    ResponseModality modality1 = new ResponseModality(ResponseModality.Known.TEXT);
    ResponseModality modality2 = new ResponseModality(ResponseModality.Known.TEXT);
    assertEquals(modality1.hashCode(), modality2.hashCode());
  }

  @Test
  public void testResponseModalityInCreateInteractionConfig() {
    CreateInteractionConfig config = CreateInteractionConfig.builder()
        .input("test input")
        .model("gemini-2.0-flash")
        .responseModalities(ResponseModality.Known.TEXT, ResponseModality.Known.IMAGE)
        .build();

    assertTrue(config.responseModalities().isPresent());
    List<ResponseModality> modalities = config.responseModalities().get();
    assertEquals(2, modalities.size());
    assertEquals("text", modalities.get(0).toString());
    assertEquals("image", modalities.get(1).toString());
  }

  @Test
  public void testResponseModalityJsonSerialization_lowercase() {
    CreateInteractionConfig config = CreateInteractionConfig.builder()
        .input("test input")
        .model("gemini-2.0-flash")
        .responseModalities(ResponseModality.Known.TEXT, ResponseModality.Known.AUDIO)
        .build();

    String json = config.toJson();
    // Verify that response_modalities contains lowercase values
    assertTrue(json.contains("\"response_modalities\":[\"text\",\"audio\"]"));
    // Verify uppercase is NOT present
    assertTrue(!json.contains("\"TEXT\""));
    assertTrue(!json.contains("\"AUDIO\""));
  }

  @Test
  public void testResponseModalityFromStrings() {
    CreateInteractionConfig config = CreateInteractionConfig.builder()
        .input("test input")
        .model("gemini-2.0-flash")
        .responseModalities("text", "image", "audio")
        .build();

    assertTrue(config.responseModalities().isPresent());
    List<ResponseModality> modalities = config.responseModalities().get();
    assertEquals(3, modalities.size());
    assertEquals(ResponseModality.Known.TEXT, modalities.get(0).knownEnum());
    assertEquals(ResponseModality.Known.IMAGE, modalities.get(1).knownEnum());
    assertEquals(ResponseModality.Known.AUDIO, modalities.get(2).knownEnum());
  }

  @Test
  public void testResponseModalitiesFromKnownList() {
    List<ResponseModality.Known> knownList = Arrays.asList(
        ResponseModality.Known.TEXT,
        ResponseModality.Known.IMAGE
    );

    CreateInteractionConfig config = CreateInteractionConfig.builder()
        .input("test input")
        .model("gemini-2.0-flash")
        .responseModalitiesFromKnown(knownList)
        .build();

    assertTrue(config.responseModalities().isPresent());
    assertEquals(2, config.responseModalities().get().size());
  }
}
