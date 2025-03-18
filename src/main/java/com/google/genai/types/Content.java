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
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** Contains the multi-part content of a message. */
@AutoValue
@JsonDeserialize(builder = Content.Builder.class)
public abstract class Content extends JsonSerializable {
  /**
   * List of parts that constitute a single message. Each part may have a different IANA MIME type.
   */
  @JsonProperty("parts")
  public abstract Optional<List<Part>> parts();

  /**
   * Optional. The producer of the content. Must be either 'user' or 'model'. Useful to set for
   * multi-turn conversations, otherwise can be left blank or unset. If role is not specified, SDK
   * will determine the role.
   */
  @JsonProperty("role")
  public abstract Optional<String> role();

  /** Instantiates a builder for Content. */
  public static Builder builder() {
    return new AutoValue_Content.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for Content. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `Content.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_Content.Builder();
    }

    @JsonProperty("parts")
    public abstract Builder parts(List<Part> parts);

    @JsonProperty("role")
    public abstract Builder role(String role);

    public abstract Content build();
  }

  /** Deserializes a JSON string to a Content object. */
  public static Content fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Content.class);
  }

  /** Constructs a Content from parts, assuming the role is "user". */
  public static Content fromParts(Part... parts) {
    return builder().role("user").parts(Arrays.asList(parts)).build();
  }
}
