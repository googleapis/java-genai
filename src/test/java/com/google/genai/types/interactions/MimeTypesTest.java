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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests for MIME type enums in the Interactions API.
 *
 * <p>Tests all four MIME type classes: AudioMimeType, DocumentMimeType, ImageMimeType, and
 * VideoMimeType to ensure proper value validation and serialization.
 */
public class MimeTypesTest {

  // ========== AudioMimeType Tests ==========

  @Test
  public void testAudioMimeTypeKnownValues() {
    // Test all known audio MIME types
    assertEquals("audio/aac", AudioMimeType.Known.AUDIO_AAC.toString());
    assertEquals("audio/flac", AudioMimeType.Known.AUDIO_FLAC.toString());
    assertEquals("audio/mp3", AudioMimeType.Known.AUDIO_MP3.toString());
    assertEquals("audio/wav", AudioMimeType.Known.AUDIO_WAV.toString());
    assertEquals("audio/aiff", AudioMimeType.Known.AUDIO_AIFF.toString());
    assertEquals("audio/ogg", AudioMimeType.Known.AUDIO_OGG.toString());
  }

  @Test
  public void testAudioMimeTypeConstructor() {
    // Test creating AudioMimeType from string value
    AudioMimeType mimeType = new AudioMimeType("audio/mp3");
    assertEquals("audio/mp3", mimeType.toString());
  }

  @Test
  public void testAudioMimeTypeConstructorFromKnown() {
    // Test creating AudioMimeType from Known enum
    AudioMimeType mimeType = new AudioMimeType(AudioMimeType.Known.AUDIO_WAV);
    assertEquals("audio/wav", mimeType.toString());
  }

  @Test
  public void testAudioMimeTypeToString() {
    // Test toString returns the MIME type value
    assertEquals("audio/wav", AudioMimeType.Known.AUDIO_WAV.toString());
  }

  @Test
  public void testAudioMimeTypeCustomValue() {
    // Test with custom/unknown MIME type
    AudioMimeType customType = new AudioMimeType("audio/custom");
    assertEquals("audio/custom", customType.toString());
  }

  // ========== DocumentMimeType Tests ==========

  @Test
  public void testDocumentMimeTypeKnownValues() {
    // Test all known document MIME types
    assertEquals("application/pdf", DocumentMimeType.Known.APPLICATION_PDF.toString());
  }

  @Test
  public void testDocumentMimeTypeConstructor() {
    // Test creating DocumentMimeType from string value
    DocumentMimeType mimeType = new DocumentMimeType("application/pdf");
    assertEquals("application/pdf", mimeType.toString());
  }

  @Test
  public void testDocumentMimeTypeConstructorFromKnown() {
    // Test creating DocumentMimeType from Known enum
    DocumentMimeType mimeType = new DocumentMimeType(DocumentMimeType.Known.APPLICATION_PDF);
    assertEquals("application/pdf", mimeType.toString());
  }

  @Test
  public void testDocumentMimeTypeConstructorCustomValue() {
    // Test with custom document MIME type (e.g., text/plain)
    DocumentMimeType customType = new DocumentMimeType("text/plain");
    assertEquals("text/plain", customType.toString());
  }

  @Test
  public void testDocumentMimeTypeToString() {
    // Test toString returns the MIME type value
    assertEquals("application/pdf", DocumentMimeType.Known.APPLICATION_PDF.toString());
  }

  // ========== ImageMimeType Tests ==========

  @Test
  public void testImageMimeTypeKnownValues() {
    // Test all known image MIME types
    assertEquals("image/png", ImageMimeType.Known.IMAGE_PNG.toString());
    assertEquals("image/jpeg", ImageMimeType.Known.IMAGE_JPEG.toString());
    assertEquals("image/webp", ImageMimeType.Known.IMAGE_WEBP.toString());
    assertEquals("image/heic", ImageMimeType.Known.IMAGE_HEIC.toString());
    assertEquals("image/heif", ImageMimeType.Known.IMAGE_HEIF.toString());
  }

  @Test
  public void testImageMimeTypeConstructor() {
    // Test creating ImageMimeType from string value
    ImageMimeType mimeType = new ImageMimeType("image/png");
    assertEquals("image/png", mimeType.toString());
  }

  @Test
  public void testImageMimeTypeConstructorFromKnown() {
    // Test creating ImageMimeType from Known enum
    ImageMimeType mimeType = new ImageMimeType(ImageMimeType.Known.IMAGE_JPEG);
    assertEquals("image/jpeg", mimeType.toString());
  }

  @Test
  public void testImageMimeTypeToString() {
    // Test toString returns the MIME type value
    assertEquals("image/webp", ImageMimeType.Known.IMAGE_WEBP.toString());
  }

  // ========== VideoMimeType Tests ==========

  @Test
  public void testVideoMimeTypeKnownValues() {
    // Test all known video MIME types
    assertEquals("video/mp4", VideoMimeType.Known.VIDEO_MP4.toString());
    assertEquals("video/mpeg", VideoMimeType.Known.VIDEO_MPEG.toString());
    assertEquals("video/mov", VideoMimeType.Known.VIDEO_MOV.toString());
    assertEquals("video/avi", VideoMimeType.Known.VIDEO_AVI.toString());
    assertEquals("video/x-flv", VideoMimeType.Known.VIDEO_X_FLV.toString());
    assertEquals("video/mpg", VideoMimeType.Known.VIDEO_MPG.toString());
    assertEquals("video/webm", VideoMimeType.Known.VIDEO_WEBM.toString());
    assertEquals("video/wmv", VideoMimeType.Known.VIDEO_WMV.toString());
    assertEquals("video/3gpp", VideoMimeType.Known.VIDEO_3GPP.toString());
  }

  @Test
  public void testVideoMimeTypeConstructor() {
    // Test creating VideoMimeType from string value
    VideoMimeType mimeType = new VideoMimeType("video/mp4");
    assertEquals("video/mp4", mimeType.toString());
  }

  @Test
  public void testVideoMimeTypeConstructorFromKnown() {
    // Test creating VideoMimeType from Known enum
    VideoMimeType mimeType = new VideoMimeType(VideoMimeType.Known.VIDEO_WEBM);
    assertEquals("video/webm", mimeType.toString());
  }

  @Test
  public void testVideoMimeTypeToString() {
    // Test toString returns the MIME type value
    assertEquals("video/webm", VideoMimeType.Known.VIDEO_WEBM.toString());
  }

  // ========== MediaResolution Tests ==========

  @Test
  public void testMediaResolutionKnownValues() {
    // Test all media resolution Known enum values
    assertNotNull(MediaResolution.Known.LOW);
    assertNotNull(MediaResolution.Known.HIGH);
    assertNotNull(MediaResolution.Known.ULTRA_HIGH);
    assertNotNull(MediaResolution.Known.MEDIA_RESOLUTION_UNSPECIFIED);
  }

  @Test
  public void testMediaResolutionConstructor() {
    // Test creating MediaResolution from string value
    MediaResolution resolution = new MediaResolution("high");
    assertEquals("high", resolution.toString());
  }

  @Test
  public void testMediaResolutionConstructorFromKnown() {
    // Test creating MediaResolution from Known enum
    MediaResolution resolution = new MediaResolution(MediaResolution.Known.HIGH);
    assertEquals("high", resolution.toString());
  }

  @Test
  public void testMediaResolutionToString() {
    // Test toString returns the resolution value
    assertEquals("low", MediaResolution.Known.LOW.toString());
    assertEquals("high", MediaResolution.Known.HIGH.toString());
    assertEquals("ultra_high", MediaResolution.Known.ULTRA_HIGH.toString());
  }

  @Test
  public void testMediaResolutionKnownEnum() {
    // Test knownEnum returns the correct Known value
    MediaResolution resolution = new MediaResolution(MediaResolution.Known.HIGH);
    assertEquals(MediaResolution.Known.HIGH, resolution.knownEnum());
  }

  @Test
  public void testMediaResolutionUnknownFallback() {
    // Test unknown values fall back to MEDIA_RESOLUTION_UNSPECIFIED
    MediaResolution resolution = new MediaResolution("custom_resolution");
    assertEquals(MediaResolution.Known.MEDIA_RESOLUTION_UNSPECIFIED, resolution.knownEnum());
    assertEquals("custom_resolution", resolution.toString());
  }

  // ========== Cross-Enum Validation Tests ==========

  @Test
  public void testAllMimeTypeEnumsNotNull() {
    // Verify all known MIME type values are not null
    assertNotNull(AudioMimeType.Known.AUDIO_MP3.toString());
    assertNotNull(DocumentMimeType.Known.APPLICATION_PDF.toString());
    assertNotNull(ImageMimeType.Known.IMAGE_PNG.toString());
    assertNotNull(VideoMimeType.Known.VIDEO_MP4.toString());
    assertNotNull(MediaResolution.Known.HIGH.toString());
  }

  @Test
  public void testMimeTypeValuesAreNotEmpty() {
    // Verify MIME type values are not empty strings
    assertTrue(!AudioMimeType.Known.AUDIO_WAV.toString().isEmpty());
    assertTrue(!DocumentMimeType.Known.APPLICATION_PDF.toString().isEmpty());
    assertTrue(!ImageMimeType.Known.IMAGE_JPEG.toString().isEmpty());
    assertTrue(!VideoMimeType.Known.VIDEO_WEBM.toString().isEmpty());
  }

  @Test
  public void testMimeTypeEquality() {
    // Test that two instances with the same value are equal
    AudioMimeType audio1 = new AudioMimeType("audio/mp3");
    AudioMimeType audio2 = new AudioMimeType("audio/mp3");
    assertEquals(audio1, audio2);
  }

  @Test
  public void testMimeTypeHashCode() {
    // Test that two instances with the same value have the same hash code
    AudioMimeType audio1 = new AudioMimeType("audio/mp3");
    AudioMimeType audio2 = new AudioMimeType("audio/mp3");
    assertEquals(audio1.hashCode(), audio2.hashCode());
  }
}
