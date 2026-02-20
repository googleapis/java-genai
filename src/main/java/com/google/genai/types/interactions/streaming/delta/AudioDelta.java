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

package com.google.genai.types.interactions.streaming.delta;

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
 * Represents an incremental audio update in a streaming response.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = AudioDelta.Builder.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("audio")
public abstract class AudioDelta extends JsonSerializable implements Delta {

  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_AudioDelta.Builder();
  }

  public abstract Builder toBuilder();

  /** Returns the base64-encoded audio data. */
  @JsonProperty("data")
  public abstract Optional<String> data();

  /** Returns the URI of the audio. */
  @JsonProperty("uri")
  public abstract Optional<String> uri();

  /** Returns the MIME type of the audio. */
  @JsonProperty("mime_type")
  public abstract Optional<AudioMimeType> mimeType();

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return new AutoValue_AudioDelta.Builder();
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

    public abstract AudioDelta build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static AudioDelta fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, AudioDelta.class);
  }
}
