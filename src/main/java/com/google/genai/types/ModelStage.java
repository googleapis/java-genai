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
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Ascii;
import java.util.Objects;

/** The stage of the underlying model. This enum is not supported in Vertex AI. */
public class ModelStage {

  /** Enum representing the known values for ModelStage. */
  public enum Known {
    /** Unspecified model stage. */
    MODEL_STAGE_UNSPECIFIED,

    /** The underlying model is subject to lots of tunings. */
    UNSTABLE_EXPERIMENTAL,

    /** Models in this stage are for experimental purposes only. */
    EXPERIMENTAL,

    /** Models in this stage are more mature than experimental models. */
    PREVIEW,

    /** Models in this stage are considered stable and ready for production use. */
    STABLE,

    /**
     * If the model is on this stage, it means that this model is on the path to deprecation in near
     * future. Only existing customers can use this model.
     */
    LEGACY,

    /** Models in this stage are deprecated. These models cannot be used. */
    DEPRECATED,

    /** Models in this stage are retired. These models cannot be used. */
    RETIRED
  }

  private Known modelStageEnum;
  private final String value;

  @JsonCreator
  public ModelStage(String value) {
    this.value = value;
    for (Known modelStageEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(modelStageEnum.toString(), value)) {
        this.modelStageEnum = modelStageEnum;
        break;
      }
    }
    if (this.modelStageEnum == null) {
      this.modelStageEnum = Known.MODEL_STAGE_UNSPECIFIED;
    }
  }

  public ModelStage(Known knownValue) {
    this.modelStageEnum = knownValue;
    this.value = knownValue.toString();
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  @JsonValue
  public String toString() {
    return this.value;
  }

  @ExcludeFromGeneratedCoverageReport
  @SuppressWarnings("PatternMatchingInstanceof")
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return false;
    }

    if (!(o instanceof ModelStage)) {
      return false;
    }

    ModelStage other = (ModelStage) o;

    if (this.modelStageEnum != Known.MODEL_STAGE_UNSPECIFIED
        && other.modelStageEnum != Known.MODEL_STAGE_UNSPECIFIED) {
      return this.modelStageEnum == other.modelStageEnum;
    } else if (this.modelStageEnum == Known.MODEL_STAGE_UNSPECIFIED
        && other.modelStageEnum == Known.MODEL_STAGE_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.modelStageEnum != Known.MODEL_STAGE_UNSPECIFIED) {
      return this.modelStageEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.modelStageEnum;
  }
}
