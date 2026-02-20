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

package com.google.genai.types.interactions.content;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import com.google.genai.types.ExcludeFromGeneratedCoverageReport;
import com.google.genai.types.interactions.AudioMimeType;
import java.util.Optional;

/**
 * Audio content for the Interactions API.
 *
 * <p>Represents audio data that can be included in interaction inputs or outputs. Audio can be
 * provided either as base64-encoded data or as a URI.
 *
 * <p>Example usage with data:
 *
 * <pre>{@code
 * AudioContent audio = AudioContent.fromData(base64Data, "audio/mp3");
 * }</pre>
 *
 * <p>Example usage with URI:
 *
 * <pre>{@code
 * AudioContent audio = AudioContent.fromUri("https://example.com/audio.mp3", "audio/mp3");
 * }</pre>
 *
 * <p>The Interactions API is available in both Vertex AI and Gemini API.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = AudioContent.Builder.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("audio")
public abstract class AudioContent extends JsonSerializable implements Content {

  @JsonProperty("data")
  public abstract Optional<String> data();

  @JsonProperty("uri")
  public abstract Optional<String> uri();

  /**
   * The MIME type of the audio.
   *
   * <p>Supported values:
   *
   * <ul>
   *   <li>{@link AudioMimeType.Known#AUDIO_WAV} - WAV format
   *   <li>{@link AudioMimeType.Known#AUDIO_MP3} - MP3 format
   *   <li>{@link AudioMimeType.Known#AUDIO_AIFF} - AIFF format
   *   <li>{@link AudioMimeType.Known#AUDIO_AAC} - AAC format
   *   <li>{@link AudioMimeType.Known#AUDIO_OGG} - OGG format
   *   <li>{@link AudioMimeType.Known#AUDIO_FLAC} - FLAC format
   * </ul>
   *
   * <p>This field accepts any MIME type string to support future audio formats.
   */
  @JsonProperty("mime_type")
  public abstract Optional<AudioMimeType> mimeType();

  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_AudioContent.Builder();
  }

  public abstract Builder toBuilder();

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return new AutoValue_AudioContent.Builder();
    }

    @JsonProperty("data")
    public abstract Builder data(String data);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder data(Optional<String> data);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearData() {
      return data(Optional.empty());
    }

    @JsonProperty("uri")
    public abstract Builder uri(String uri);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder uri(Optional<String> uri);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearUri() {
      return uri(Optional.empty());
    }

    @JsonProperty("mime_type")
    public abstract Builder mimeType(AudioMimeType mimeType);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder mimeType(Optional<AudioMimeType> mimeType);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMimeType() {
      return mimeType(Optional.empty());
    }

    public abstract AudioContent build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static AudioContent fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, AudioContent.class);
  }

  @ExcludeFromGeneratedCoverageReport
  public static AudioContent fromData(String data, String mimeType) {
    return builder().data(data).mimeType(new AudioMimeType(mimeType)).build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static AudioContent fromUri(String uri, String mimeType) {
    return builder().uri(uri).mimeType(new AudioMimeType(mimeType)).build();
  }
}
