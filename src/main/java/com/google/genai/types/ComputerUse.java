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
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** A tool that enables the model to interact directly with a computer environment. */
@AutoValue
@JsonDeserialize(builder = ComputerUse.Builder.class)
public abstract class ComputerUse extends JsonSerializable {
  /** Required. The target environment where the computer use tool operates. */
  @JsonProperty("environment")
  public abstract Optional<Environment> environment();

  /**
   * Optional. A list of predefined functions to explicitly exclude from the model call. By default,
   * [predefined
   * functions](https://cloud.google.com/vertex-ai/generative-ai/docs/computer-use#supported-actions)
   * are included. Excluding functions allows for a more restricted action space or custom
   * definitions for predefined functions.
   */
  @JsonProperty("excludedPredefinedFunctions")
  public abstract Optional<List<String>> excludedPredefinedFunctions();

  /**
   * Optional. Whether to enable the prompt injection detection check on the computer use request.
   */
  @JsonProperty("enablePromptInjectionDetection")
  public abstract Optional<Boolean> enablePromptInjectionDetection();

  /** Optional. A list of safety policies to disable for the computer use tool. */
  @JsonProperty("disabledSafetyPolicies")
  public abstract Optional<List<SafetyPolicy>> disabledSafetyPolicies();

  /** Instantiates a builder for ComputerUse. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ComputerUse.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ComputerUse. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ComputerUse.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ComputerUse.Builder();
    }

    /**
     * Setter for environment.
     *
     * <p>environment: Required. The target environment where the computer use tool operates.
     */
    @JsonProperty("environment")
    public abstract Builder environment(Environment environment);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder environment(Optional<Environment> environment);

    /** Clears the value of environment field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEnvironment() {
      return environment(Optional.empty());
    }

    /**
     * Setter for environment given a known enum.
     *
     * <p>environment: Required. The target environment where the computer use tool operates.
     */
    @CanIgnoreReturnValue
    public Builder environment(Environment.Known knownType) {
      return environment(new Environment(knownType));
    }

    /**
     * Setter for environment given a string.
     *
     * <p>environment: Required. The target environment where the computer use tool operates.
     */
    @CanIgnoreReturnValue
    public Builder environment(String environment) {
      return environment(new Environment(environment));
    }

    /**
     * Setter for excludedPredefinedFunctions.
     *
     * <p>excludedPredefinedFunctions: Optional. A list of predefined functions to explicitly
     * exclude from the model call. By default, [predefined
     * functions](https://cloud.google.com/vertex-ai/generative-ai/docs/computer-use#supported-actions)
     * are included. Excluding functions allows for a more restricted action space or custom
     * definitions for predefined functions.
     */
    @JsonProperty("excludedPredefinedFunctions")
    public abstract Builder excludedPredefinedFunctions(List<String> excludedPredefinedFunctions);

    /**
     * Setter for excludedPredefinedFunctions.
     *
     * <p>excludedPredefinedFunctions: Optional. A list of predefined functions to explicitly
     * exclude from the model call. By default, [predefined
     * functions](https://cloud.google.com/vertex-ai/generative-ai/docs/computer-use#supported-actions)
     * are included. Excluding functions allows for a more restricted action space or custom
     * definitions for predefined functions.
     */
    @CanIgnoreReturnValue
    public Builder excludedPredefinedFunctions(String... excludedPredefinedFunctions) {
      return excludedPredefinedFunctions(Arrays.asList(excludedPredefinedFunctions));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder excludedPredefinedFunctions(
        Optional<List<String>> excludedPredefinedFunctions);

    /** Clears the value of excludedPredefinedFunctions field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearExcludedPredefinedFunctions() {
      return excludedPredefinedFunctions(Optional.empty());
    }

    /**
     * Setter for enablePromptInjectionDetection.
     *
     * <p>enablePromptInjectionDetection: Optional. Whether to enable the prompt injection detection
     * check on the computer use request.
     */
    @JsonProperty("enablePromptInjectionDetection")
    public abstract Builder enablePromptInjectionDetection(boolean enablePromptInjectionDetection);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder enablePromptInjectionDetection(
        Optional<Boolean> enablePromptInjectionDetection);

    /** Clears the value of enablePromptInjectionDetection field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEnablePromptInjectionDetection() {
      return enablePromptInjectionDetection(Optional.empty());
    }

    /**
     * Setter for disabledSafetyPolicies.
     *
     * <p>disabledSafetyPolicies: Optional. A list of safety policies to disable for the computer
     * use tool.
     */
    @JsonProperty("disabledSafetyPolicies")
    public abstract Builder disabledSafetyPolicies(List<SafetyPolicy> disabledSafetyPolicies);

    /**
     * Setter for disabledSafetyPolicies.
     *
     * <p>disabledSafetyPolicies: Optional. A list of safety policies to disable for the computer
     * use tool.
     */
    @CanIgnoreReturnValue
    public Builder disabledSafetyPolicies(SafetyPolicy... disabledSafetyPolicies) {
      return disabledSafetyPolicies(Arrays.asList(disabledSafetyPolicies));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder disabledSafetyPolicies(Optional<List<SafetyPolicy>> disabledSafetyPolicies);

    /** Clears the value of disabledSafetyPolicies field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDisabledSafetyPolicies() {
      return disabledSafetyPolicies(Optional.empty());
    }

    /**
     * Setter for disabledSafetyPolicies given a varargs of strings.
     *
     * <p>disabledSafetyPolicies: Optional. A list of safety policies to disable for the computer
     * use tool.
     */
    @CanIgnoreReturnValue
    public Builder disabledSafetyPolicies(String... disabledSafetyPolicies) {
      return disabledSafetyPoliciesFromString(Arrays.asList(disabledSafetyPolicies));
    }

    /**
     * Setter for disabledSafetyPolicies given a varargs of known enums.
     *
     * <p>disabledSafetyPolicies: Optional. A list of safety policies to disable for the computer
     * use tool.
     */
    @CanIgnoreReturnValue
    public Builder disabledSafetyPolicies(SafetyPolicy.Known... knownTypes) {
      return disabledSafetyPoliciesFromKnown(Arrays.asList(knownTypes));
    }

    /**
     * Setter for disabledSafetyPolicies given a list of known enums.
     *
     * <p>disabledSafetyPolicies: Optional. A list of safety policies to disable for the computer
     * use tool.
     */
    @CanIgnoreReturnValue
    public Builder disabledSafetyPoliciesFromKnown(List<SafetyPolicy.Known> knownTypes) {
      ImmutableList<SafetyPolicy> listItems =
          knownTypes.stream().map(SafetyPolicy::new).collect(toImmutableList());
      return disabledSafetyPolicies(listItems);
    }

    /**
     * Setter for disabledSafetyPolicies given a list of strings.
     *
     * <p>disabledSafetyPolicies: Optional. A list of safety policies to disable for the computer
     * use tool.
     */
    @CanIgnoreReturnValue
    public Builder disabledSafetyPoliciesFromString(List<String> disabledSafetyPolicies) {
      ImmutableList<SafetyPolicy> listItems =
          disabledSafetyPolicies.stream().map(SafetyPolicy::new).collect(toImmutableList());
      return disabledSafetyPolicies(listItems);
    }

    public abstract ComputerUse build();
  }

  /** Deserializes a JSON string to a ComputerUse object. */
  @ExcludeFromGeneratedCoverageReport
  public static ComputerUse fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ComputerUse.class);
  }
}
