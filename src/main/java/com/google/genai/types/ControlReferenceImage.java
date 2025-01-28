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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/**
 * A control reference image.
 *
 * <p>The image of the control reference image is either a control image provided by the user, or a
 * regular image which the backend will use to generate a control image of. In the case of the
 * latter, the enable_control_image_computation field in the config should be set to True.
 *
 * <p>A control image is an image that represents a sketch image of areas for the model to fill in
 * based on the prompt.
 */
@AutoValue
@JsonDeserialize(builder = AutoValue_ControlReferenceImage.Builder.class)
public abstract class ControlReferenceImage extends JsonSerializable {
  /** The reference image for the editing operation. */
  public abstract Optional<Image> getReferenceImage();

  /** The id of the reference image. */
  public abstract Optional<Integer> getReferenceId();

  /** The type of the reference image. */
  public abstract Optional<String> getReferenceType();

  /** Configuration for the control reference image. */
  public abstract Optional<ControlReferenceConfig> getConfig();

  /** Instantiates a builder for ControlReferenceImage. */
  public static Builder builder() {
    return new AutoValue_ControlReferenceImage.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ControlReferenceImage. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("referenceImage")
    public abstract Builder setReferenceImage(Image referenceImage);

    @JsonProperty("referenceId")
    public abstract Builder setReferenceId(Integer referenceId);

    @JsonProperty("referenceType")
    public abstract Builder setReferenceType(String referenceType);

    @JsonProperty("config")
    public abstract Builder setConfig(ControlReferenceConfig config);

    public abstract ControlReferenceImage build();
  }

  /** Deserializes a JSON string to a ControlReferenceImage object. */
  public static ControlReferenceImage fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ControlReferenceImage.class);
  }
}
