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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Specification for how rubrics should be generated. This data type is not supported in Gemini API.
 */
@AutoValue
@JsonDeserialize(builder = RubricGenerationSpec.Builder.class)
public abstract class RubricGenerationSpec extends JsonSerializable {
  /**
   * Configuration for the model used in rubric generation. Configs including sampling count and
   * base model can be specified here. Flipping is not supported for rubric generation.
   */
  @JsonProperty("modelConfig")
  public abstract Optional<AutoraterConfig> modelConfig();

  /**
   * Template for the prompt used to generate rubrics. The details should be updated based on the
   * most-recent recipe requirements.
   */
  @JsonProperty("promptTemplate")
  public abstract Optional<String> promptTemplate();

  /** The type of rubric content to be generated. */
  @JsonProperty("rubricContentType")
  public abstract Optional<RubricContentType> rubricContentType();

  /**
   * Optional. An optional, pre-defined list of allowed types for generated rubrics. If this field
   * is provided, it implies `include_rubric_type` should be true, and the generated rubric types
   * should be chosen from this ontology.
   */
  @JsonProperty("rubricTypeOntology")
  public abstract Optional<List<String>> rubricTypeOntology();

  /** Instantiates a builder for RubricGenerationSpec. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_RubricGenerationSpec.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for RubricGenerationSpec. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `RubricGenerationSpec.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_RubricGenerationSpec.Builder();
    }

    /**
     * Setter for modelConfig.
     *
     * <p>modelConfig: Configuration for the model used in rubric generation. Configs including
     * sampling count and base model can be specified here. Flipping is not supported for rubric
     * generation.
     */
    @JsonProperty("modelConfig")
    public abstract Builder modelConfig(AutoraterConfig modelConfig);

    /**
     * Setter for modelConfig builder.
     *
     * <p>modelConfig: Configuration for the model used in rubric generation. Configs including
     * sampling count and base model can be specified here. Flipping is not supported for rubric
     * generation.
     */
    @CanIgnoreReturnValue
    public Builder modelConfig(AutoraterConfig.Builder modelConfigBuilder) {
      return modelConfig(modelConfigBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder modelConfig(Optional<AutoraterConfig> modelConfig);

    /** Clears the value of modelConfig field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearModelConfig() {
      return modelConfig(Optional.empty());
    }

    /**
     * Setter for promptTemplate.
     *
     * <p>promptTemplate: Template for the prompt used to generate rubrics. The details should be
     * updated based on the most-recent recipe requirements.
     */
    @JsonProperty("promptTemplate")
    public abstract Builder promptTemplate(String promptTemplate);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder promptTemplate(Optional<String> promptTemplate);

    /** Clears the value of promptTemplate field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPromptTemplate() {
      return promptTemplate(Optional.empty());
    }

    /**
     * Setter for rubricContentType.
     *
     * <p>rubricContentType: The type of rubric content to be generated.
     */
    @JsonProperty("rubricContentType")
    public abstract Builder rubricContentType(RubricContentType rubricContentType);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder rubricContentType(Optional<RubricContentType> rubricContentType);

    /** Clears the value of rubricContentType field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRubricContentType() {
      return rubricContentType(Optional.empty());
    }

    /**
     * Setter for rubricContentType given a known enum.
     *
     * <p>rubricContentType: The type of rubric content to be generated.
     */
    @CanIgnoreReturnValue
    public Builder rubricContentType(RubricContentType.Known knownType) {
      return rubricContentType(new RubricContentType(knownType));
    }

    /**
     * Setter for rubricContentType given a string.
     *
     * <p>rubricContentType: The type of rubric content to be generated.
     */
    @CanIgnoreReturnValue
    public Builder rubricContentType(String rubricContentType) {
      return rubricContentType(new RubricContentType(rubricContentType));
    }

    /**
     * Setter for rubricTypeOntology.
     *
     * <p>rubricTypeOntology: Optional. An optional, pre-defined list of allowed types for generated
     * rubrics. If this field is provided, it implies `include_rubric_type` should be true, and the
     * generated rubric types should be chosen from this ontology.
     */
    @JsonProperty("rubricTypeOntology")
    public abstract Builder rubricTypeOntology(List<String> rubricTypeOntology);

    /**
     * Setter for rubricTypeOntology.
     *
     * <p>rubricTypeOntology: Optional. An optional, pre-defined list of allowed types for generated
     * rubrics. If this field is provided, it implies `include_rubric_type` should be true, and the
     * generated rubric types should be chosen from this ontology.
     */
    @CanIgnoreReturnValue
    public Builder rubricTypeOntology(String... rubricTypeOntology) {
      return rubricTypeOntology(Arrays.asList(rubricTypeOntology));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder rubricTypeOntology(Optional<List<String>> rubricTypeOntology);

    /** Clears the value of rubricTypeOntology field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRubricTypeOntology() {
      return rubricTypeOntology(Optional.empty());
    }

    public abstract RubricGenerationSpec build();
  }

  /** Deserializes a JSON string to a RubricGenerationSpec object. */
  @ExcludeFromGeneratedCoverageReport
  public static RubricGenerationSpec fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, RubricGenerationSpec.class);
  }
}
