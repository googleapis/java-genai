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

/** Configuration for a Subject reference image. */
@AutoValue
@JsonDeserialize(builder = AutoValue_SubjectReferenceConfig.Builder.class)
public abstract class SubjectReferenceConfig extends JsonSerializable {
  /** The subject type of a subject reference image. */
  @JsonProperty("subjectType")
  public abstract Optional<String> subjectType();

  /** Subject description for the image. */
  @JsonProperty("subjectDescription")
  public abstract Optional<String> subjectDescription();

  /** Instantiates a builder for SubjectReferenceConfig. */
  public static Builder builder() {
    return new AutoValue_SubjectReferenceConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for SubjectReferenceConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("subjectType")
    public abstract Builder subjectType(String subjectType);

    @JsonProperty("subjectDescription")
    public abstract Builder subjectDescription(String subjectDescription);

    public abstract SubjectReferenceConfig build();
  }

  /** Deserializes a JSON string to a SubjectReferenceConfig object. */
  public static SubjectReferenceConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SubjectReferenceConfig.class);
  }
}
