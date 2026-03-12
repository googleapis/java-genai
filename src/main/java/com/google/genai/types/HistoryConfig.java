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

/** Configuration for history exchange between client and server. */
@AutoValue
@JsonDeserialize(builder = HistoryConfig.Builder.class)
public abstract class HistoryConfig extends JsonSerializable {
  /**
   * If true, after sending `setup_complete`, the server will wait and at first process
   * `client_content` messages until `turn_complete` is `true`. This initial history will not
   * trigger a model call and may end with role `MODEL`. After `turn_complete` is `true`, the client
   * can start the realtime conversation via `realtime_input`.
   */
  @JsonProperty("initialHistoryInClientContent")
  public abstract Optional<Boolean> initialHistoryInClientContent();

  /** Instantiates a builder for HistoryConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_HistoryConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for HistoryConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `HistoryConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_HistoryConfig.Builder();
    }

    /**
     * Setter for initialHistoryInClientContent.
     *
     * <p>initialHistoryInClientContent: If true, after sending `setup_complete`, the server will
     * wait and at first process `client_content` messages until `turn_complete` is `true`. This
     * initial history will not trigger a model call and may end with role `MODEL`. After
     * `turn_complete` is `true`, the client can start the realtime conversation via
     * `realtime_input`.
     */
    @JsonProperty("initialHistoryInClientContent")
    public abstract Builder initialHistoryInClientContent(boolean initialHistoryInClientContent);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder initialHistoryInClientContent(Optional<Boolean> initialHistoryInClientContent);

    /** Clears the value of initialHistoryInClientContent field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearInitialHistoryInClientContent() {
      return initialHistoryInClientContent(Optional.empty());
    }

    public abstract HistoryConfig build();
  }

  /** Deserializes a JSON string to a HistoryConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static HistoryConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, HistoryConfig.class);
  }
}
