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

/**
 * A safety setting that affects the safety-blocking behavior. A SafetySetting consists of a harm
 * category and a threshold for that category.
 */
@AutoValue
@JsonDeserialize(builder = SafetySetting.Builder.class)
public abstract class SafetySetting extends JsonSerializable {
  /** Required. The harm category to be blocked. */
  @JsonProperty("category")
  public abstract Optional<HarmCategory> category();

  /**
   * Optional. The method for blocking content. If not specified, the default behavior is to use the
   * probability score. This field is not supported in Gemini API.
   */
  @JsonProperty("method")
  public abstract Optional<HarmBlockMethod> method();

  /**
   * Required. The threshold for blocking content. If the harm probability exceeds this threshold,
   * the content will be blocked.
   */
  @JsonProperty("threshold")
  public abstract Optional<HarmBlockThreshold> threshold();

  /** Instantiates a builder for SafetySetting. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_SafetySetting.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for SafetySetting. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `SafetySetting.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_SafetySetting.Builder();
    }

    /**
     * Setter for category.
     *
     * <p>category: Required. The harm category to be blocked.
     */
    @JsonProperty("category")
    public abstract Builder category(HarmCategory category);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder category(Optional<HarmCategory> category);

    /** Clears the value of category field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCategory() {
      return category(Optional.empty());
    }

    /**
     * Setter for category given a known enum.
     *
     * <p>category: Required. The harm category to be blocked.
     */
    @CanIgnoreReturnValue
    public Builder category(HarmCategory.Known knownType) {
      return category(new HarmCategory(knownType));
    }

    /**
     * Setter for category given a string.
     *
     * <p>category: Required. The harm category to be blocked.
     */
    @CanIgnoreReturnValue
    public Builder category(String category) {
      return category(new HarmCategory(category));
    }

    /**
     * Setter for method.
     *
     * <p>method: Optional. The method for blocking content. If not specified, the default behavior
     * is to use the probability score. This field is not supported in Gemini API.
     */
    @JsonProperty("method")
    public abstract Builder method(HarmBlockMethod method);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder method(Optional<HarmBlockMethod> method);

    /** Clears the value of method field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMethod() {
      return method(Optional.empty());
    }

    /**
     * Setter for method given a known enum.
     *
     * <p>method: Optional. The method for blocking content. If not specified, the default behavior
     * is to use the probability score. This field is not supported in Gemini API.
     */
    @CanIgnoreReturnValue
    public Builder method(HarmBlockMethod.Known knownType) {
      return method(new HarmBlockMethod(knownType));
    }

    /**
     * Setter for method given a string.
     *
     * <p>method: Optional. The method for blocking content. If not specified, the default behavior
     * is to use the probability score. This field is not supported in Gemini API.
     */
    @CanIgnoreReturnValue
    public Builder method(String method) {
      return method(new HarmBlockMethod(method));
    }

    /**
     * Setter for threshold.
     *
     * <p>threshold: Required. The threshold for blocking content. If the harm probability exceeds
     * this threshold, the content will be blocked.
     */
    @JsonProperty("threshold")
    public abstract Builder threshold(HarmBlockThreshold threshold);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder threshold(Optional<HarmBlockThreshold> threshold);

    /** Clears the value of threshold field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearThreshold() {
      return threshold(Optional.empty());
    }

    /**
     * Setter for threshold given a known enum.
     *
     * <p>threshold: Required. The threshold for blocking content. If the harm probability exceeds
     * this threshold, the content will be blocked.
     */
    @CanIgnoreReturnValue
    public Builder threshold(HarmBlockThreshold.Known knownType) {
      return threshold(new HarmBlockThreshold(knownType));
    }

    /**
     * Setter for threshold given a string.
     *
     * <p>threshold: Required. The threshold for blocking content. If the harm probability exceeds
     * this threshold, the content will be blocked.
     */
    @CanIgnoreReturnValue
    public Builder threshold(String threshold) {
      return threshold(new HarmBlockThreshold(threshold));
    }

    public abstract SafetySetting build();
  }

  /** Deserializes a JSON string to a SafetySetting object. */
  @ExcludeFromGeneratedCoverageReport
  public static SafetySetting fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SafetySetting.class);
  }
}
