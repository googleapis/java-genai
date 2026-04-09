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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Configures the avatar to be used in the session. */
@AutoValue
@JsonDeserialize(builder = AvatarConfig.Builder.class)
public abstract class AvatarConfig extends JsonSerializable {
  /** Pre-built avatar id. */
  @JsonProperty("avatarName")
  public abstract Optional<String> avatarName();

  /** Customized avatar appearance with a reference image. */
  @JsonProperty("customizedAvatar")
  public abstract Optional<CustomizedAvatar> customizedAvatar();

  /** The bitrate of compressed audio. */
  @JsonProperty("audioBitrateBps")
  public abstract Optional<Integer> audioBitrateBps();

  /** The bitrate of compressed video output. */
  @JsonProperty("videoBitrateBps")
  public abstract Optional<Integer> videoBitrateBps();

  /** Instantiates a builder for AvatarConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_AvatarConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for AvatarConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `AvatarConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_AvatarConfig.Builder();
    }

    /**
     * Setter for avatarName.
     *
     * <p>avatarName: Pre-built avatar id.
     */
    @JsonProperty("avatarName")
    public abstract Builder avatarName(String avatarName);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder avatarName(Optional<String> avatarName);

    /** Clears the value of avatarName field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAvatarName() {
      return avatarName(Optional.empty());
    }

    /**
     * Setter for customizedAvatar.
     *
     * <p>customizedAvatar: Customized avatar appearance with a reference image.
     */
    @JsonProperty("customizedAvatar")
    public abstract Builder customizedAvatar(CustomizedAvatar customizedAvatar);

    /**
     * Setter for customizedAvatar builder.
     *
     * <p>customizedAvatar: Customized avatar appearance with a reference image.
     */
    @CanIgnoreReturnValue
    public Builder customizedAvatar(CustomizedAvatar.Builder customizedAvatarBuilder) {
      return customizedAvatar(customizedAvatarBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder customizedAvatar(Optional<CustomizedAvatar> customizedAvatar);

    /** Clears the value of customizedAvatar field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCustomizedAvatar() {
      return customizedAvatar(Optional.empty());
    }

    /**
     * Setter for audioBitrateBps.
     *
     * <p>audioBitrateBps: The bitrate of compressed audio.
     */
    @JsonProperty("audioBitrateBps")
    public abstract Builder audioBitrateBps(Integer audioBitrateBps);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder audioBitrateBps(Optional<Integer> audioBitrateBps);

    /** Clears the value of audioBitrateBps field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearAudioBitrateBps() {
      return audioBitrateBps(Optional.empty());
    }

    /**
     * Setter for videoBitrateBps.
     *
     * <p>videoBitrateBps: The bitrate of compressed video output.
     */
    @JsonProperty("videoBitrateBps")
    public abstract Builder videoBitrateBps(Integer videoBitrateBps);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder videoBitrateBps(Optional<Integer> videoBitrateBps);

    /** Clears the value of videoBitrateBps field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearVideoBitrateBps() {
      return videoBitrateBps(Optional.empty());
    }

    public abstract AvatarConfig build();
  }

  /** Deserializes a JSON string to a AvatarConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static AvatarConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, AvatarConfig.class);
  }
}
