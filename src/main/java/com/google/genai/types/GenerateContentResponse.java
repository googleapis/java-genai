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

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import com.google.genai.JsonSerializable;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.jspecify.annotations.Nullable;

/** Response message for PredictionService.GenerateContent. */
@GenerateTypeAdapter
@AutoValue
public abstract class GenerateContentResponse extends JsonSerializable {
  /** Response variations returned by the model. */
  @SerializedName("candidates")
  public abstract Optional<List<Candidate>> candidates();

  /** Timestamp when the request is made to the server. */
  @SerializedName("createTime")
  public abstract Optional<String> createTime();

  /** Identifier for each response. */
  @SerializedName("responseId")
  public abstract Optional<String> responseId();

  /** Output only. The model version used to generate the response. */
  @SerializedName("modelVersion")
  public abstract Optional<String> modelVersion();

  /**
   * Output only. Content filter results for a prompt sent in the request. Note: Sent only in the
   * first stream chunk. Only happens when no candidates were generated due to content violations.
   */
  @SerializedName("promptFeedback")
  public abstract Optional<GenerateContentResponsePromptFeedback> promptFeedback();

  /** Usage metadata about the response(s). */
  @SerializedName("usageMetadata")
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
    @SerializedName("candidates")
    public abstract Builder candidates(List<Candidate> candidates);

    @SerializedName("candidates")
    abstract Builder candidates(Optional<List<Candidate>> candidates);

    @SerializedName("createTime")
    public abstract Builder createTime(String createTime);

    @SerializedName("createTime")
    abstract Builder createTime(Optional<String> createTime);

    @SerializedName("responseId")
    public abstract Builder responseId(String responseId);

    @SerializedName("responseId")
    abstract Builder responseId(Optional<String> responseId);

    @SerializedName("modelVersion")
    public abstract Builder modelVersion(String modelVersion);

    @SerializedName("modelVersion")
    abstract Builder modelVersion(Optional<String> modelVersion);

    @SerializedName("promptFeedback")
    public abstract Builder promptFeedback(GenerateContentResponsePromptFeedback promptFeedback);

    @SerializedName("promptFeedback")
    abstract Builder promptFeedback(Optional<GenerateContentResponsePromptFeedback> promptFeedback);

    @SerializedName("usageMetadata")
    public abstract Builder usageMetadata(GenerateContentResponseUsageMetadata usageMetadata);

    @SerializedName("usageMetadata")
    abstract Builder usageMetadata(Optional<GenerateContentResponseUsageMetadata> usageMetadata);

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
  public @Nullable ImmutableList<Part> parts() {
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
  public @Nullable String text() {
    ImmutableList<Part> parts = parts();
    if (parts == null || parts.isEmpty()) {
      return null;
    }

    String text = "";
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
      text += part.text().orElse("");
    }

    if (!nonTextParts.isEmpty()) {
      logger.warning(
          String.format(
              "There are non-text parts %s in the response, returning concatenation of all text"
                  + " parts. Please refer to the non text parts for a full response from model.",
              String.join(", ", nonTextParts)));
    }

    return text;
  }

  /**
   * Returns the list of function calls in the response.
   *
   * <p>Returns null if there is no candidate, no content in the first candidate, or no parts in the
   * content.
   */
  public @Nullable ImmutableList<FunctionCall> functionCalls() {
    ImmutableList<Part> parts = parts();
    if (parts == null || parts.isEmpty()) {
      return null;
    }

    return ImmutableList.copyOf(
        parts.stream()
            .filter(part -> part.functionCall().isPresent())
            .map(part -> part.functionCall().get())
            .collect(Collectors.toList()));
  }

  /**
   * Returns the executable code in the response.
   *
   * <p>Returns null if there is no candidate, no content in the first candidate, or no parts in the
   * content, or no executable code in the parts.
   */
  public @Nullable String executableCode() {
    ImmutableList<Part> parts = parts();
    if (parts == null || parts.isEmpty()) {
      return null;
    }

    for (Part part : parts) {
      if (part.executableCode().isPresent()) {
        return part.executableCode().get().code().orElse("");
      }
    }

    return null;
  }

  /**
   * Returns the code execution result in the response.
   *
   * <p>Returns null if there is no candidate, no content in the first candidate, or no parts in the
   * content, or no code execution result in the parts.
   */
  public @Nullable String codeExecutionResult() {
    ImmutableList<Part> parts = parts();
    if (parts == null || parts.isEmpty()) {
      return null;
    }

    for (Part part : parts) {
      if (part.codeExecutionResult().isPresent()) {
        return part.codeExecutionResult().get().output().orElse("");
      }
    }

    return null;
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
