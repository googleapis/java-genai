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

/** Supervised fine-tuning job creation request - optional fields. */
@AutoValue
@JsonDeserialize(builder = CreateTuningJobConfig.Builder.class)
public abstract class CreateTuningJobConfig extends JsonSerializable {
  /** Used to override HTTP request options. */
  @JsonProperty("httpOptions")
  public abstract Optional<HttpOptions> httpOptions();

  /**
   * Cloud Storage path to file containing training dataset for tuning. The dataset must be
   * formatted as a JSONL file.
   */
  @JsonProperty("validationDataset")
  public abstract Optional<TuningValidationDataset> validationDataset();

  /**
   * The display name of the tuned Model. The name can be up to 128 characters long and can consist
   * of any UTF-8 characters.
   */
  @JsonProperty("tunedModelDisplayName")
  public abstract Optional<String> tunedModelDisplayName();

  /** The description of the TuningJob */
  @JsonProperty("description")
  public abstract Optional<String> description();

  /** Number of complete passes the model makes over the entire training dataset during training. */
  @JsonProperty("epochCount")
  public abstract Optional<Integer> epochCount();

  /** Multiplier for adjusting the default learning rate. */
  @JsonProperty("learningRateMultiplier")
  public abstract Optional<Float> learningRateMultiplier();

  /**
   * If set to true, disable intermediate checkpoints for SFT and only the last checkpoint will be
   * exported. Otherwise, enable intermediate checkpoints for SFT.
   */
  @JsonProperty("exportLastCheckpointOnly")
  public abstract Optional<Boolean> exportLastCheckpointOnly();

  /** Adapter size for tuning. */
  @JsonProperty("adapterSize")
  public abstract Optional<AdapterSize> adapterSize();

  /** Instantiates a builder for CreateTuningJobConfig. */
  public static Builder builder() {
    return new AutoValue_CreateTuningJobConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for CreateTuningJobConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `CreateTuningJobConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_CreateTuningJobConfig.Builder();
    }

    /**
     * Setter for httpOptions.
     *
     * <p>httpOptions: Used to override HTTP request options.
     */
    @JsonProperty("httpOptions")
    public abstract Builder httpOptions(HttpOptions httpOptions);

    /**
     * Setter for httpOptions builder.
     *
     * <p>httpOptions: Used to override HTTP request options.
     */
    public Builder httpOptions(HttpOptions.Builder httpOptionsBuilder) {
      return httpOptions(httpOptionsBuilder.build());
    }

    /**
     * Setter for validationDataset.
     *
     * <p>validationDataset: Cloud Storage path to file containing training dataset for tuning. The
     * dataset must be formatted as a JSONL file.
     */
    @JsonProperty("validationDataset")
    public abstract Builder validationDataset(TuningValidationDataset validationDataset);

    /**
     * Setter for validationDataset builder.
     *
     * <p>validationDataset: Cloud Storage path to file containing training dataset for tuning. The
     * dataset must be formatted as a JSONL file.
     */
    public Builder validationDataset(TuningValidationDataset.Builder validationDatasetBuilder) {
      return validationDataset(validationDatasetBuilder.build());
    }

    /**
     * Setter for tunedModelDisplayName.
     *
     * <p>tunedModelDisplayName: The display name of the tuned Model. The name can be up to 128
     * characters long and can consist of any UTF-8 characters.
     */
    @JsonProperty("tunedModelDisplayName")
    public abstract Builder tunedModelDisplayName(String tunedModelDisplayName);

    /**
     * Setter for description.
     *
     * <p>description: The description of the TuningJob
     */
    @JsonProperty("description")
    public abstract Builder description(String description);

    /**
     * Setter for epochCount.
     *
     * <p>epochCount: Number of complete passes the model makes over the entire training dataset
     * during training.
     */
    @JsonProperty("epochCount")
    public abstract Builder epochCount(Integer epochCount);

    /**
     * Setter for learningRateMultiplier.
     *
     * <p>learningRateMultiplier: Multiplier for adjusting the default learning rate.
     */
    @JsonProperty("learningRateMultiplier")
    public abstract Builder learningRateMultiplier(Float learningRateMultiplier);

    /**
     * Setter for exportLastCheckpointOnly.
     *
     * <p>exportLastCheckpointOnly: If set to true, disable intermediate checkpoints for SFT and
     * only the last checkpoint will be exported. Otherwise, enable intermediate checkpoints for
     * SFT.
     */
    @JsonProperty("exportLastCheckpointOnly")
    public abstract Builder exportLastCheckpointOnly(boolean exportLastCheckpointOnly);

    /**
     * Setter for adapterSize.
     *
     * <p>adapterSize: Adapter size for tuning.
     */
    @JsonProperty("adapterSize")
    public abstract Builder adapterSize(AdapterSize adapterSize);

    /**
     * Setter for adapterSize given a known enum.
     *
     * <p>adapterSize: Adapter size for tuning.
     */
    @CanIgnoreReturnValue
    public Builder adapterSize(AdapterSize.Known knownType) {
      return adapterSize(new AdapterSize(knownType));
    }

    /**
     * Setter for adapterSize given a string.
     *
     * <p>adapterSize: Adapter size for tuning.
     */
    @CanIgnoreReturnValue
    public Builder adapterSize(String adapterSize) {
      return adapterSize(new AdapterSize(adapterSize));
    }

    public abstract CreateTuningJobConfig build();
  }

  /** Deserializes a JSON string to a CreateTuningJobConfig object. */
  public static CreateTuningJobConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, CreateTuningJobConfig.class);
  }
}
