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

// Auto-generated code. Do not edit.

package com.google.genai.types;

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * User input that is sent in real time.
 *
 * <p>This is different from `LiveClientContent` in a few ways:
 *
 * <p>- Can be sent continuously without interruption to model generation. - If there is a need to
 * mix data interleaved across the `LiveClientContent` and the `LiveClientRealtimeInput`, server
 * attempts to optimize for best response, but there are no guarantees. - End of turn is not
 * explicitly specified, but is rather derived from user activity (for example, end of speech). -
 * Even before the end of turn, the data is processed incrementally to optimize for a fast start of
 * the response from the model. - Is always assumed to be the user's input (cannot be used to
 * populate conversation history).
 */
@AutoValue
@JsonDeserialize(builder = LiveClientRealtimeInput.Builder.class)
public abstract class LiveClientRealtimeInput extends JsonSerializable {
  /** Inlined bytes data for media input. */
  @JsonProperty("mediaChunks")
  public abstract Optional<List<Blob>> mediaChunks();

  /** The realtime audio input stream. */
  @JsonProperty("audio")
  public abstract Optional<Blob> audio();

  /**
   * Indicates that the audio stream has ended, e.g. because the microphone was turned off.
   *
   * <p>This should only be sent when automatic activity detection is enabled (which is the
   * default).
   *
   * <p>The client can reopen the stream by sending an audio message.
   */
  @JsonProperty("audioStreamEnd")
  public abstract Optional<Boolean> audioStreamEnd();

  /** The realtime video input stream. */
  @JsonProperty("video")
  public abstract Optional<Blob> video();

  /** The realtime text input stream. */
  @JsonProperty("text")
  public abstract Optional<String> text();

  /** Marks the start of user activity. */
  @JsonProperty("activityStart")
  public abstract Optional<ActivityStart> activityStart();

  /** Marks the end of user activity. */
  @JsonProperty("activityEnd")
  public abstract Optional<ActivityEnd> activityEnd();

  /** Instantiates a builder for LiveClientRealtimeInput. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_LiveClientRealtimeInput.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for LiveClientRealtimeInput. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `LiveClientRealtimeInput.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_LiveClientRealtimeInput.Builder();
    }

    /**
     * Setter for mediaChunks.
     *
     * <p>mediaChunks: Inlined bytes data for media input.
     */
    @JsonProperty("mediaChunks")
    public abstract Builder mediaChunks(List<Blob> mediaChunks);

    /**
     * Setter for mediaChunks.
     *
     * <p>mediaChunks: Inlined bytes data for media input.
     */
    public Builder mediaChunks(Blob... mediaChunks) {
      return mediaChunks(Arrays.asList(mediaChunks));
    }

    /**
     * Setter for mediaChunks builder.
     *
     * <p>mediaChunks: Inlined bytes data for media input.
     */
    public Builder mediaChunks(Blob.Builder... mediaChunksBuilders) {
      return mediaChunks(
          Arrays.asList(mediaChunksBuilders).stream()
              .map(Blob.Builder::build)
              .collect(toImmutableList()));
    }

    /**
     * Setter for audio.
     *
     * <p>audio: The realtime audio input stream.
     */
    @JsonProperty("audio")
    public abstract Builder audio(Blob audio);

    /**
     * Setter for audio builder.
     *
     * <p>audio: The realtime audio input stream.
     */
    public Builder audio(Blob.Builder audioBuilder) {
      return audio(audioBuilder.build());
    }

    /**
     * Setter for audioStreamEnd.
     *
     * <p>audioStreamEnd: Indicates that the audio stream has ended, e.g. because the microphone was
     * turned off.
     *
     * <p>This should only be sent when automatic activity detection is enabled (which is the
     * default).
     *
     * <p>The client can reopen the stream by sending an audio message.
     */
    @JsonProperty("audioStreamEnd")
    public abstract Builder audioStreamEnd(boolean audioStreamEnd);

    /**
     * Setter for video.
     *
     * <p>video: The realtime video input stream.
     */
    @JsonProperty("video")
    public abstract Builder video(Blob video);

    /**
     * Setter for video builder.
     *
     * <p>video: The realtime video input stream.
     */
    public Builder video(Blob.Builder videoBuilder) {
      return video(videoBuilder.build());
    }

    /**
     * Setter for text.
     *
     * <p>text: The realtime text input stream.
     */
    @JsonProperty("text")
    public abstract Builder text(String text);

    /**
     * Setter for activityStart.
     *
     * <p>activityStart: Marks the start of user activity.
     */
    @JsonProperty("activityStart")
    public abstract Builder activityStart(ActivityStart activityStart);

    /**
     * Setter for activityStart builder.
     *
     * <p>activityStart: Marks the start of user activity.
     */
    public Builder activityStart(ActivityStart.Builder activityStartBuilder) {
      return activityStart(activityStartBuilder.build());
    }

    /**
     * Setter for activityEnd.
     *
     * <p>activityEnd: Marks the end of user activity.
     */
    @JsonProperty("activityEnd")
    public abstract Builder activityEnd(ActivityEnd activityEnd);

    /**
     * Setter for activityEnd builder.
     *
     * <p>activityEnd: Marks the end of user activity.
     */
    public Builder activityEnd(ActivityEnd.Builder activityEndBuilder) {
      return activityEnd(activityEndBuilder.build());
    }

    public abstract LiveClientRealtimeInput build();
  }

  /** Deserializes a JSON string to a LiveClientRealtimeInput object. */
  @ExcludeFromGeneratedCoverageReport
  public static LiveClientRealtimeInput fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, LiveClientRealtimeInput.class);
  }
}
