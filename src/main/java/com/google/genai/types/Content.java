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
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import org.jspecify.annotations.Nullable;

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
   * Optional. The producer of the content. Must be either 'user' or 'model'. If not set, the
   * service will default to 'user'.
   */
  @JsonProperty("role")
  public abstract Optional<String> role();

  /** Instantiates a builder for Content. */
  @ExcludeFromGeneratedCoverageReport
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

    /**
     * Setter for parts.
     *
     * <p>parts: List of parts that constitute a single message. Each part may have a different IANA
     * MIME type.
     */
    @JsonProperty("parts")
    public abstract Builder parts(List<Part> parts);

    /**
     * Setter for parts.
     *
     * <p>parts: List of parts that constitute a single message. Each part may have a different IANA
     * MIME type.
     */
    @CanIgnoreReturnValue
    public Builder parts(Part... parts) {
      return parts(Arrays.asList(parts));
    }

    /**
     * Setter for parts builder.
     *
     * <p>parts: List of parts that constitute a single message. Each part may have a different IANA
     * MIME type.
     */
    @CanIgnoreReturnValue
    public Builder parts(Part.Builder... partsBuilders) {
      return parts(
          Arrays.asList(partsBuilders).stream()
              .map(Part.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder parts(Optional<List<Part>> parts);

    /** Clears the value of parts field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearParts() {
      return parts(Optional.empty());
    }

    /**
     * Setter for role.
     *
     * <p>role: Optional. The producer of the content. Must be either 'user' or 'model'. If not set,
     * the service will default to 'user'.
     */
    @JsonProperty("role")
    public abstract Builder role(String role);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder role(Optional<String> role);

    /** Clears the value of role field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRole() {
      return role(Optional.empty());
    }

    public abstract Content build();
  }

  /** Deserializes a JSON string to a Content object. */
  @ExcludeFromGeneratedCoverageReport
  public static Content fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Content.class);
  }

  private static final Logger logger = Logger.getLogger(Content.class.getName());

  /** Constructs a Content from parts, assuming the role is "user". */
  public static Content fromParts(Part... parts) {
    return builder().role("user").parts(Arrays.asList(parts)).build();
  }

  /**
   * Returns the concatenation of all text parts in this content.
   *
   * <p>Returns null if there are no parts in the content. Returns an empty string if parts exists
   * but none of the parts contain text.
   */
  public @Nullable String text() {
    return aggregateTextFromParts(parts().orElse(null));
  }

  /**
   * Aggregates all text parts in a list of parts.
   *
   * <p>Returns null if there are no parts in the list. Returns an empty string if parts exists but
   * none of the parts contain text.
   */
  static @Nullable String aggregateTextFromParts(List<Part> parts) {
    if (parts == null || parts.isEmpty()) {
      return null;
    }

    StringBuilder sb = new StringBuilder();
    ArrayList<String> nonTextParts = new ArrayList<>();
    for (Part part : parts) {
      if (part.inlineData().isPresent()) {
        nonTextParts.add("inlineData");
      }
      if (part.codeExecutionResult().isPresent()) {
        nonTextParts.add("codeExecutionResult");
      }
      if (part.executableCode().isPresent()) {
        nonTextParts.add("executableCode");
      }
      if (part.fileData().isPresent()) {
        nonTextParts.add("fileData");
      }
      if (part.functionCall().isPresent()) {
        nonTextParts.add("functionCall");
      }
      if (part.functionResponse().isPresent()) {
        nonTextParts.add("functionResponse");
      }
      if (part.videoMetadata().isPresent()) {
        nonTextParts.add("videoMetadata");
      }
      if (part.thought().orElse(false)) {
        continue;
      }
      sb.append(part.text().orElse(""));
    }

    if (!nonTextParts.isEmpty()) {
      logger.warning(
          String.format(
              "There are non-text parts %s in the content, returning concatenation of all text"
                  + " parts. Please refer to the non text parts for a full response from model.",
              String.join(", ", nonTextParts)));
    }

    return sb.toString();
  }
}
