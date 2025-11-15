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

/** Configures the input to the batch request. This data type is not supported in Vertex AI. */
@AutoValue
@JsonDeserialize(builder = InputEmbedContentConfig.Builder.class)
public abstract class InputEmbedContentConfig extends JsonSerializable {
  /** The name of the `File` containing the input requests. */
  @JsonProperty("fileName")
  public abstract Optional<String> fileName();

  /** The requests to be processed in the batch. */
  @JsonProperty("requests")
  public abstract Optional<InlinedEmbedContentRequests> requests();

  /** Instantiates a builder for InputEmbedContentConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_InputEmbedContentConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for InputEmbedContentConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `InputEmbedContentConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_InputEmbedContentConfig.Builder();
    }

    /**
     * Setter for fileName.
     *
     * <p>fileName: The name of the `File` containing the input requests.
     */
    @JsonProperty("fileName")
    public abstract Builder fileName(String fileName);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder fileName(Optional<String> fileName);

    /** Clears the value of fileName field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearFileName() {
      return fileName(Optional.empty());
    }

    /**
     * Setter for requests.
     *
     * <p>requests: The requests to be processed in the batch.
     */
    @JsonProperty("requests")
    public abstract Builder requests(InlinedEmbedContentRequests requests);

    /**
     * Setter for requests builder.
     *
     * <p>requests: The requests to be processed in the batch.
     */
    @CanIgnoreReturnValue
    public Builder requests(InlinedEmbedContentRequests.Builder requestsBuilder) {
      return requests(requestsBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder requests(Optional<InlinedEmbedContentRequests> requests);

    /** Clears the value of requests field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRequests() {
      return requests(Optional.empty());
    }

    public abstract InputEmbedContentConfig build();
  }

  /** Deserializes a JSON string to a InputEmbedContentConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static InputEmbedContentConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, InputEmbedContentConfig.class);
  }
}
