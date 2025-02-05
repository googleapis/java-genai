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
import com.google.common.collect.ImmutableList;
import com.google.genai.JsonSerializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/** Response message for PredictionService.GenerateContent. */
@AutoValue
@JsonDeserialize(builder = AutoValue_GenerateContentResponse.Builder.class)
public abstract class GenerateContentResponse extends JsonSerializable {
  /** Response variations returned by the model. */
  @JsonProperty("candidates")
  public abstract Optional<List<Candidate>> candidates();

  /** Output only. The model version used to generate the response. */
  @JsonProperty("modelVersion")
  public abstract Optional<String> modelVersion();

  /**
   * Output only. Content filter results for a prompt sent in the request. Note: Sent only in the
   * first stream chunk. Only happens when no candidates were generated due to content violations.
   */
  @JsonProperty("promptFeedback")
  public abstract Optional<GenerateContentResponsePromptFeedback> promptFeedback();

  /** Usage metadata about the response(s). */
  @JsonProperty("usageMetadata")
  public abstract Optional<GenerateContentResponseUsageMetadata> usageMetadata();

  /** Instantiates a builder for GenerateContentResponse. */
  public static Builder builder() {
    return new AutoValue_GenerateContentResponse.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerateContentResponse. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("candidates")
    public abstract Builder candidates(List<Candidate> candidates);

    @JsonProperty("modelVersion")
    public abstract Builder modelVersion(String modelVersion);

    @JsonProperty("promptFeedback")
    public abstract Builder promptFeedback(GenerateContentResponsePromptFeedback promptFeedback);

    @JsonProperty("usageMetadata")
    public abstract Builder usageMetadata(GenerateContentResponseUsageMetadata usageMetadata);

    public abstract GenerateContentResponse build();
  }

  /** Deserializes a JSON string to a GenerateContentResponse object. */
  public static GenerateContentResponse fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerateContentResponse.class);
  }

  private static final Logger logger = Logger.getLogger(GenerateContentResponse.class.getName());

  private static final ImmutableList<String> EXPECTED_FINISH_REASONS =
      ImmutableList.of("FINISH_REASON_UNSPECIFIED", "STOP", "MAX_TOKENS", "");

  /**
   * Returns the list of parts in the first candidate of the response.
   *
   * <p>Returns null if there is no candidate or no content in the first candidate
   *
   * @throws IllegalArgumentException if the response finishes unexpectedly.
   */
  public ImmutableList<Part> parts() {
    checkFinishReason();

    Optional<List<Candidate>> candidates = candidates();
    if (candidates.isEmpty() || candidates.get().isEmpty()) {
      return null;
    }

    Optional<Content> content = candidates.get().get(0).content();
    if (content.isEmpty()) {
      return null;
    }

    return ImmutableList.copyOf(content.get().parts().orElse(new ArrayList<>()));
  }

  /**
   * Returns the concatenation of all text parts in the first candidate of the response.
   *
   * <p>Returns null if there is no candidate, no content in the first candidate, or no parts in the
   * content.
   *
   * @throws IllegalArgumentException if the response has non-text parts or finishes unexpectedly.
   */
  public String text() {
    ImmutableList<Part> parts = parts();
    if (parts == null || parts.isEmpty()) {
      return null;
    }

    String text = "";
    for (Part part : parts) {
      if (part.inlineData().isPresent()
          || part.codeExecutionResult().isPresent()
          || part.executableCode().isPresent()
          || part.fileData().isPresent()
          || part.functionCall().isPresent()
          || part.functionResponse().isPresent()) {
        throw new IllegalArgumentException(
            String.format("Only text parts are supported, but got %s", part));
      }
      if (part.thought().orElse(false)) {
        continue;
      }
      text += part.text().orElse("");
    }

    return text;
  }

  /**
   * Returns the list of function calls in the response.
   *
   * <p>Returns null if there is no candidate, no content in the first candidate, or no parts in the
   * content.
   *
   * @throws IllegalArgumentException if the response has non-function-call parts or finishes
   *     unexpectedly.
   */
  public ImmutableList<FunctionCall> functionCalls() {
    ImmutableList<Part> parts = parts();
    if (parts == null || parts.isEmpty()) {
      return null;
    }

    return ImmutableList.copyOf(
        parts.stream()
            .filter(
                part -> {
                  if (!part.functionCall().isPresent()
                      && (part.inlineData().isPresent()
                          || part.codeExecutionResult().isPresent()
                          || part.executableCode().isPresent()
                          || part.fileData().isPresent()
                          || part.functionResponse().isPresent()
                          || part.text().isPresent())) {
                    throw new IllegalArgumentException(
                        String.format("Only function call parts are supported, but got %s", part));
                  }
                  return part.functionCall().isPresent();
                })
            .map(part -> part.functionCall().get())
            .collect(Collectors.toList()));
  }

  /** Gets the finish reason in a GenerateContentResponse. */
  private String finishReason() {
    List<Candidate> candidates = candidates().orElse(Arrays.asList(Candidate.builder().build()));
    if (candidates.size() > 1) {
      logger.warning(
          String.format(
              "This response has %d candidates, will only use the first candidate",
              candidates.size()));
    }
    return candidates.get(0).finishReason().orElse("");
  }

  /** Throws an exception if the response finishes unexpectedly. */
  private void checkFinishReason() {
    String finishReason = finishReason();
    if (!EXPECTED_FINISH_REASONS.contains(finishReason)) {
      throw new IllegalArgumentException(
          String.format("The response finished unexpectedly with reason %s.", finishReason));
    }
  }
}
