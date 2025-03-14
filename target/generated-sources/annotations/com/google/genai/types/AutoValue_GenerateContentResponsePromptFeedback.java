package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_GenerateContentResponsePromptFeedback extends GenerateContentResponsePromptFeedback {

  private final Optional<String> blockReason;

  private final Optional<String> blockReasonMessage;

  private final Optional<List<SafetyRating>> safetyRatings;

  private AutoValue_GenerateContentResponsePromptFeedback(
      Optional<String> blockReason,
      Optional<String> blockReasonMessage,
      Optional<List<SafetyRating>> safetyRatings) {
    this.blockReason = blockReason;
    this.blockReasonMessage = blockReasonMessage;
    this.safetyRatings = safetyRatings;
  }

  @JsonProperty("blockReason")
  @Override
  public Optional<String> blockReason() {
    return blockReason;
  }

  @JsonProperty("blockReasonMessage")
  @Override
  public Optional<String> blockReasonMessage() {
    return blockReasonMessage;
  }

  @JsonProperty("safetyRatings")
  @Override
  public Optional<List<SafetyRating>> safetyRatings() {
    return safetyRatings;
  }

  @Override
  public String toString() {
    return "GenerateContentResponsePromptFeedback{"
        + "blockReason=" + blockReason + ", "
        + "blockReasonMessage=" + blockReasonMessage + ", "
        + "safetyRatings=" + safetyRatings
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof GenerateContentResponsePromptFeedback) {
      GenerateContentResponsePromptFeedback that = (GenerateContentResponsePromptFeedback) o;
      return this.blockReason.equals(that.blockReason())
          && this.blockReasonMessage.equals(that.blockReasonMessage())
          && this.safetyRatings.equals(that.safetyRatings());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= blockReason.hashCode();
    h$ *= 1000003;
    h$ ^= blockReasonMessage.hashCode();
    h$ *= 1000003;
    h$ ^= safetyRatings.hashCode();
    return h$;
  }

  @Override
  public GenerateContentResponsePromptFeedback.Builder toBuilder() {
    return new AutoValue_GenerateContentResponsePromptFeedback.Builder(this);
  }

  static final class Builder extends GenerateContentResponsePromptFeedback.Builder {
    private Optional<String> blockReason = Optional.empty();
    private Optional<String> blockReasonMessage = Optional.empty();
    private Optional<List<SafetyRating>> safetyRatings = Optional.empty();
    Builder() {
    }
    Builder(GenerateContentResponsePromptFeedback source) {
      this.blockReason = source.blockReason();
      this.blockReasonMessage = source.blockReasonMessage();
      this.safetyRatings = source.safetyRatings();
    }
    @Override
    public GenerateContentResponsePromptFeedback.Builder blockReason(String blockReason) {
      this.blockReason = Optional.of(blockReason);
      return this;
    }
    @Override
    public GenerateContentResponsePromptFeedback.Builder blockReasonMessage(String blockReasonMessage) {
      this.blockReasonMessage = Optional.of(blockReasonMessage);
      return this;
    }
    @Override
    public GenerateContentResponsePromptFeedback.Builder safetyRatings(List<SafetyRating> safetyRatings) {
      this.safetyRatings = Optional.of(safetyRatings);
      return this;
    }
    @Override
    public GenerateContentResponsePromptFeedback build() {
      return new AutoValue_GenerateContentResponsePromptFeedback(
          this.blockReason,
          this.blockReasonMessage,
          this.safetyRatings);
    }
  }

}
