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
import com.google.genai.types.interactions.MediaResolution;
import com.google.genai.types.interactions.VideoMimeType;
import java.util.Optional;

/**
 * Video content for the Interactions API.
 *
 * <p>Represents video data that can be included in interaction inputs or outputs. Videos can be
 * provided either as base64-encoded data or as a URI.
 *
 * <p>Example usage with data:
 *
 * <pre>{@code
 * VideoContent video = VideoContent.fromData(base64Data, "video/mp4");
 * }</pre>
 *
 * <p>Example usage with URI:
 *
 * <pre>{@code
 * VideoContent video = VideoContent.fromUri("https://example.com/video.mp4", "video/mp4");
 * }</pre>
 *
 * <p>The Interactions API is available in both Vertex AI and Gemini API.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = VideoContent.Builder.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("video")
public abstract class VideoContent extends JsonSerializable implements Content {

  @JsonProperty("data")
  public abstract Optional<String> data();

  @JsonProperty("uri")
  public abstract Optional<String> uri();

  /**
   * The MIME type of the video.
   *
   * <p>Supported values:
   *
   * <ul>
   *   <li>{@link VideoMimeType.Known#VIDEO_MP4} - MP4 format
   *   <li>{@link VideoMimeType.Known#VIDEO_MPEG} - MPEG format
   *   <li>{@link VideoMimeType.Known#VIDEO_MOV} - MOV format
   *   <li>{@link VideoMimeType.Known#VIDEO_AVI} - AVI format
   *   <li>{@link VideoMimeType.Known#VIDEO_X_FLV} - FLV format
   *   <li>{@link VideoMimeType.Known#VIDEO_MPG} - MPG format
   *   <li>{@link VideoMimeType.Known#VIDEO_WEBM} - WebM format
   *   <li>{@link VideoMimeType.Known#VIDEO_WMV} - WMV format
   *   <li>{@link VideoMimeType.Known#VIDEO_3GPP} - 3GPP format
   * </ul>
   *
   * <p>This field accepts any MIME type string to support future video formats.
   */
  @JsonProperty("mime_type")
  public abstract Optional<VideoMimeType> mimeType();

  /**
   * The resolution of the video.
   *
   * <p>Possible values:
   *
   * <ul>
   *   <li>{@link MediaResolution#LOW} - Low resolution
   *   <li>{@link MediaResolution#MEDIUM} - Medium resolution
   *   <li>{@link MediaResolution#HIGH} - High resolution
   *   <li>{@link MediaResolution#ULTRA_HIGH} - Ultra high resolution
   * </ul>
   */
  @JsonProperty("resolution")
  public abstract Optional<MediaResolution> resolution();

  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_VideoContent.Builder();
  }

  public abstract Builder toBuilder();

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return new AutoValue_VideoContent.Builder();
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

    public abstract VideoContent build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static VideoContent fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, VideoContent.class);
  }

  @ExcludeFromGeneratedCoverageReport
  public static VideoContent fromData(String data, String mimeType) {
    return builder().data(data).mimeType(new VideoMimeType(mimeType)).build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static VideoContent fromUri(String uri, String mimeType) {
    return builder().uri(uri).mimeType(new VideoMimeType(mimeType)).build();
  }
}
