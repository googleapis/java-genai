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
 * Configuration for Model Armor. Model Armor is a Google Cloud service that provides safety and
 * security filtering for prompts and responses. It helps protect your AI applications from risks
 * such as harmful content, sensitive data leakage, and prompt injection attacks. This data type is
 * not supported in Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = ModelArmorConfig.Builder.class)
public abstract class ModelArmorConfig extends JsonSerializable {
  /**
   * Optional. The resource name of the Model Armor template to use for prompt screening. A Model
   * Armor template is a set of customized filters and thresholds that define how Model Armor
   * screens content. If specified, Model Armor will use this template to check the user's prompt
   * for safety and security risks before it is sent to the model. The name must be in the format
   * `projects/{project}/locations/{location}/templates/{template}`.
   */
  @JsonProperty("promptTemplateName")
  public abstract Optional<String> promptTemplateName();

  /**
   * Optional. The resource name of the Model Armor template to use for response screening. A Model
   * Armor template is a set of customized filters and thresholds that define how Model Armor
   * screens content. If specified, Model Armor will use this template to check the model's response
   * for safety and security risks before it is returned to the user. The name must be in the format
   * `projects/{project}/locations/{location}/templates/{template}`.
   */
  @JsonProperty("responseTemplateName")
  public abstract Optional<String> responseTemplateName();

  /** Instantiates a builder for ModelArmorConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ModelArmorConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ModelArmorConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ModelArmorConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ModelArmorConfig.Builder();
    }

    /**
     * Setter for promptTemplateName.
     *
     * <p>promptTemplateName: Optional. The resource name of the Model Armor template to use for
     * prompt screening. A Model Armor template is a set of customized filters and thresholds that
     * define how Model Armor screens content. If specified, Model Armor will use this template to
     * check the user's prompt for safety and security risks before it is sent to the model. The
     * name must be in the format `projects/{project}/locations/{location}/templates/{template}`.
     */
    @JsonProperty("promptTemplateName")
    public abstract Builder promptTemplateName(String promptTemplateName);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder promptTemplateName(Optional<String> promptTemplateName);

    /** Clears the value of promptTemplateName field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPromptTemplateName() {
      return promptTemplateName(Optional.empty());
    }

    /**
     * Setter for responseTemplateName.
     *
     * <p>responseTemplateName: Optional. The resource name of the Model Armor template to use for
     * response screening. A Model Armor template is a set of customized filters and thresholds that
     * define how Model Armor screens content. If specified, Model Armor will use this template to
     * check the model's response for safety and security risks before it is returned to the user.
     * The name must be in the format
     * `projects/{project}/locations/{location}/templates/{template}`.
     */
    @JsonProperty("responseTemplateName")
    public abstract Builder responseTemplateName(String responseTemplateName);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder responseTemplateName(Optional<String> responseTemplateName);

    /** Clears the value of responseTemplateName field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearResponseTemplateName() {
      return responseTemplateName(Optional.empty());
    }

    public abstract ModelArmorConfig build();
  }

  /** Deserializes a JSON string to a ModelArmorConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static ModelArmorConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ModelArmorConfig.class);
  }
}
