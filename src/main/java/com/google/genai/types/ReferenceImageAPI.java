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
import com.google.api.core.InternalApi;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Private class that represents a Reference image that is sent to API. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = ReferenceImageAPI.Builder.class)
public abstract class ReferenceImageAPI extends JsonSerializable {
  /** The reference image for the editing operation. */
  @JsonProperty("referenceImage")
  public abstract Optional<Image> referenceImage();

  /** The id of the reference image. */
  @JsonProperty("referenceId")
  public abstract Optional<Integer> referenceId();

  /** The type of the reference image. Only set by the SDK. */
  @JsonProperty("referenceType")
  public abstract Optional<String> referenceType();

  /** Configuration for the mask reference image. */
  @JsonProperty("maskImageConfig")
  public abstract Optional<MaskReferenceConfig> maskImageConfig();

  /** Configuration for the control reference image. */
  @JsonProperty("controlImageConfig")
  public abstract Optional<ControlReferenceConfig> controlImageConfig();

  /** Configuration for the style reference image. */
  @JsonProperty("styleImageConfig")
  public abstract Optional<StyleReferenceConfig> styleImageConfig();

  /** Configuration for the subject reference image. */
  @JsonProperty("subjectImageConfig")
  public abstract Optional<SubjectReferenceConfig> subjectImageConfig();

  /** Instantiates a builder for ReferenceImageAPI. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ReferenceImageAPI.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ReferenceImageAPI. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ReferenceImageAPI.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ReferenceImageAPI.Builder();
    }

    /**
     * Setter for referenceImage.
     *
     * <p>referenceImage: The reference image for the editing operation.
     */
    @JsonProperty("referenceImage")
    public abstract Builder referenceImage(Image referenceImage);

    /**
     * Setter for referenceImage builder.
     *
     * <p>referenceImage: The reference image for the editing operation.
     */
    public Builder referenceImage(Image.Builder referenceImageBuilder) {
      return referenceImage(referenceImageBuilder.build());
    }

    /**
     * Setter for referenceId.
     *
     * <p>referenceId: The id of the reference image.
     */
    @JsonProperty("referenceId")
    public abstract Builder referenceId(Integer referenceId);

    /**
     * Setter for referenceType.
     *
     * <p>referenceType: The type of the reference image. Only set by the SDK.
     */
    @JsonProperty("referenceType")
    public abstract Builder referenceType(String referenceType);

    /**
     * Setter for maskImageConfig.
     *
     * <p>maskImageConfig: Configuration for the mask reference image.
     */
    @JsonProperty("maskImageConfig")
    public abstract Builder maskImageConfig(MaskReferenceConfig maskImageConfig);

    /**
     * Setter for maskImageConfig builder.
     *
     * <p>maskImageConfig: Configuration for the mask reference image.
     */
    public Builder maskImageConfig(MaskReferenceConfig.Builder maskImageConfigBuilder) {
      return maskImageConfig(maskImageConfigBuilder.build());
    }

    /**
     * Setter for controlImageConfig.
     *
     * <p>controlImageConfig: Configuration for the control reference image.
     */
    @JsonProperty("controlImageConfig")
    public abstract Builder controlImageConfig(ControlReferenceConfig controlImageConfig);

    /**
     * Setter for controlImageConfig builder.
     *
     * <p>controlImageConfig: Configuration for the control reference image.
     */
    public Builder controlImageConfig(ControlReferenceConfig.Builder controlImageConfigBuilder) {
      return controlImageConfig(controlImageConfigBuilder.build());
    }

    /**
     * Setter for styleImageConfig.
     *
     * <p>styleImageConfig: Configuration for the style reference image.
     */
    @JsonProperty("styleImageConfig")
    public abstract Builder styleImageConfig(StyleReferenceConfig styleImageConfig);

    /**
     * Setter for styleImageConfig builder.
     *
     * <p>styleImageConfig: Configuration for the style reference image.
     */
    public Builder styleImageConfig(StyleReferenceConfig.Builder styleImageConfigBuilder) {
      return styleImageConfig(styleImageConfigBuilder.build());
    }

    /**
     * Setter for subjectImageConfig.
     *
     * <p>subjectImageConfig: Configuration for the subject reference image.
     */
    @JsonProperty("subjectImageConfig")
    public abstract Builder subjectImageConfig(SubjectReferenceConfig subjectImageConfig);

    /**
     * Setter for subjectImageConfig builder.
     *
     * <p>subjectImageConfig: Configuration for the subject reference image.
     */
    public Builder subjectImageConfig(SubjectReferenceConfig.Builder subjectImageConfigBuilder) {
      return subjectImageConfig(subjectImageConfigBuilder.build());
    }

    public abstract ReferenceImageAPI build();
  }

  /** Deserializes a JSON string to a ReferenceImageAPI object. */
  @ExcludeFromGeneratedCoverageReport
  public static ReferenceImageAPI fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ReferenceImageAPI.class);
  }
}
