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
import com.google.genai.types.interactions.MediaResolution;
import com.google.genai.types.interactions.VideoMimeType;
import java.util.Optional;

/**
 * Represents an incremental video update in a streaming response.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = VideoDelta.Builder.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("video")
public abstract class VideoDelta extends JsonSerializable implements Delta {

  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_VideoDelta.Builder();
  }

  public abstract Builder toBuilder();

  /** Returns the base64-encoded video data. */
  @JsonProperty("data")
  public abstract Optional<String> data();

  /** Returns the URI of the video. */
  @JsonProperty("uri")
  public abstract Optional<String> uri();

  /** Returns the MIME type of the video. */
  @JsonProperty("mime_type")
  public abstract Optional<VideoMimeType> mimeType();

  /** Returns the resolution of the video. */
  @JsonProperty("resolution")
  public abstract Optional<MediaResolution> resolution();

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return new AutoValue_VideoDelta.Builder();
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
    public abstract Builder mimeType(VideoMimeType mimeType);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder mimeType(Optional<VideoMimeType> mimeType);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMimeType() {
      return mimeType(Optional.empty());
    }

    @JsonProperty("resolution")
    public abstract Builder resolution(MediaResolution resolution);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder resolution(Optional<MediaResolution> resolution);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearResolution() {
      return resolution(Optional.empty());
    }

    public abstract VideoDelta build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static VideoDelta fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, VideoDelta.class);
  }
}
