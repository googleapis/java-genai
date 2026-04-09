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

/** Configures the customized avatar to be used in the session. */
@AutoValue
@JsonDeserialize(builder = CustomizedAvatar.Builder.class)
public abstract class CustomizedAvatar extends JsonSerializable {
  /** The mime type of the reference image, e.g., "image/jpeg". */
  @JsonProperty("imageMimeType")
  public abstract Optional<String> imageMimeType();

  /**
   * The data of the reference image. The dimensions of the reference image should be 9:16
   * (portrait) with a minimum resolution of 704x1280.
   */
  @JsonProperty("imageData")
  public abstract Optional<byte[]> imageData();

  /** Instantiates a builder for CustomizedAvatar. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_CustomizedAvatar.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for CustomizedAvatar. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `CustomizedAvatar.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_CustomizedAvatar.Builder();
    }

    /**
     * Setter for imageMimeType.
     *
     * <p>imageMimeType: The mime type of the reference image, e.g., "image/jpeg".
     */
    @JsonProperty("imageMimeType")
    public abstract Builder imageMimeType(String imageMimeType);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder imageMimeType(Optional<String> imageMimeType);

    /** Clears the value of imageMimeType field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearImageMimeType() {
      return imageMimeType(Optional.empty());
    }

    /**
     * Setter for imageData.
     *
     * <p>imageData: The data of the reference image. The dimensions of the reference image should
     * be 9:16 (portrait) with a minimum resolution of 704x1280.
     */
    @JsonProperty("imageData")
    public abstract Builder imageData(byte[] imageData);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder imageData(Optional<byte[]> imageData);

    /** Clears the value of imageData field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearImageData() {
      return imageData(Optional.empty());
    }

    public abstract CustomizedAvatar build();
  }

  /** Deserializes a JSON string to a CustomizedAvatar object. */
  @ExcludeFromGeneratedCoverageReport
  public static CustomizedAvatar fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, CustomizedAvatar.class);
  }
}
