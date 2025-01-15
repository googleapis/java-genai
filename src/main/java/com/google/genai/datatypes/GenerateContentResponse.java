// Auto-generated code. Do not edit.

package com.google.genai.datatypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import com.google.genai.JsonSerializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/** Response message for PredictionService.GenerateContent. */
@AutoValue
@JsonDeserialize(builder = AutoValue_GenerateContentResponse.Builder.class)
public abstract class GenerateContentResponse extends JsonSerializable {
  /** Response variations returned by the model. */
  public abstract Optional<List<Candidate>> getCandidates();

  /** Output only. The model version used to generate the response. */
  public abstract Optional<String> getModelVersion();

  /**
   * Output only. Content filter results for a prompt sent in the request. Note: Sent only in the
   * first stream chunk. Only happens when no candidates were generated due to content violations.
   */
  public abstract Optional<GenerateContentResponsePromptFeedback> getPromptFeedback();

  /** Usage metadata about the response(s). */
  public abstract Optional<GenerateContentResponseUsageMetadata> getUsageMetadata();

  /** Instantiates a builder for GenerateContentResponse. */
  public static Builder builder() {
    return new AutoValue_GenerateContentResponse.Builder();
  }

  /** Builder for GenerateContentResponse. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("candidates")
    public abstract Builder setCandidates(List<Candidate> candidates);

    @JsonProperty("modelVersion")
    public abstract Builder setModelVersion(String modelVersion);

    @JsonProperty("promptFeedback")
    public abstract Builder setPromptFeedback(GenerateContentResponsePromptFeedback promptFeedback);

    @JsonProperty("usageMetadata")
    public abstract Builder setUsageMetadata(GenerateContentResponseUsageMetadata usageMetadata);

    public abstract GenerateContentResponse build();
  }

  /** Serializes the GenerateContentResponse object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
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
   * <p>Returns an empty list if there is no content in the candidate or there are no parts in the
   * content.
   *
   * @throws IllegalArgumentException if the response has no candidate or finishes unexpectedly.
   */
  public ImmutableList<Part> parts() {
    checkFinishReason();
    return ImmutableList.copyOf(
        getCandidates()
            .get()
            .get(0)
            .getContent()
            .orElse(Content.builder().build())
            .getParts()
            .orElse(new ArrayList<>()));
  }

  /**
   * Returns the concatenation of all text parts in the first candidate of the response.
   *
   * <p>Returns an empty string if there are no text parts.
   *
   * @throws IllegalArgumentException if the response has no candidate or finishes unexpectedly.
   */
  public String text() {
    String text = "";
    for (Part part : parts()) {
      if (part.getThought().orElse(false) == true) {
        continue;
      }
      text += part.getText().orElse("");
    }

    return text;
  }

  /**
   * Returns the list of function calls in the response.
   *
   * <p>Returns an empty list if there are no function calls.
   *
   * @throws IllegalArgumentException if the response has no candidate or finishes unexpectedly.
   */
  public ImmutableList<FunctionCall> functionCalls() {
    return ImmutableList.copyOf(
        parts().stream()
            .filter((part) -> part.getFunctionCall().isPresent())
            .map((part) -> part.getFunctionCall().get())
            .collect(Collectors.toList()));
  }

  /** Gets the finish reason in a GenerateContentResponse. */
  private String finishReason() {
    List<Candidate> candidates = getCandidates().orElse(new ArrayList<>());
    if (candidates.isEmpty()) {
      throw new IllegalArgumentException("This response has no candidate.");
    } else if (candidates.size() > 1) {
      logger.warning(
          String.format(
              "This response has %d candidates, will only use the first candidate",
              candidates.size()));
    }
    return candidates.get(0).getFinishReason().orElse("");
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
