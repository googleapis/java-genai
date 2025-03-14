package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.jspecify.annotations.Nullable;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_GenerateContentResponse extends GenerateContentResponse {

  private final Optional<List<Candidate>> candidates;

  private final Optional<String> createTime;

  private final Optional<String> responseId;

  private final Optional<String> modelVersion;

  private final Optional<GenerateContentResponsePromptFeedback> promptFeedback;

  private final Optional<GenerateContentResponseUsageMetadata> usageMetadata;

  private AutoValue_GenerateContentResponse(
      Optional<List<Candidate>> candidates,
      Optional<String> createTime,
      Optional<String> responseId,
      Optional<String> modelVersion,
      Optional<GenerateContentResponsePromptFeedback> promptFeedback,
      Optional<GenerateContentResponseUsageMetadata> usageMetadata) {
    this.candidates = candidates;
    this.createTime = createTime;
    this.responseId = responseId;
    this.modelVersion = modelVersion;
    this.promptFeedback = promptFeedback;
    this.usageMetadata = usageMetadata;
  }

  @JsonProperty("candidates")
  @Override
  public Optional<List<Candidate>> candidates() {
    return candidates;
  }

  @JsonProperty("createTime")
  @Override
  public Optional<String> createTime() {
    return createTime;
  }

  @JsonProperty("responseId")
  @Override
  public Optional<String> responseId() {
    return responseId;
  }

  @JsonProperty("modelVersion")
  @Override
  public Optional<String> modelVersion() {
    return modelVersion;
  }

  @JsonProperty("promptFeedback")
  @Override
  public Optional<GenerateContentResponsePromptFeedback> promptFeedback() {
    return promptFeedback;
  }

  @JsonProperty("usageMetadata")
  @Override
  public Optional<GenerateContentResponseUsageMetadata> usageMetadata() {
    return usageMetadata;
  }

  @Override
  public String toString() {
    return "GenerateContentResponse{"
        + "candidates=" + candidates + ", "
        + "createTime=" + createTime + ", "
        + "responseId=" + responseId + ", "
        + "modelVersion=" + modelVersion + ", "
        + "promptFeedback=" + promptFeedback + ", "
        + "usageMetadata=" + usageMetadata
        + "}";
  }

  @Override
  public boolean equals(@Nullable Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof GenerateContentResponse) {
      GenerateContentResponse that = (GenerateContentResponse) o;
      return this.candidates.equals(that.candidates())
          && this.createTime.equals(that.createTime())
          && this.responseId.equals(that.responseId())
          && this.modelVersion.equals(that.modelVersion())
          && this.promptFeedback.equals(that.promptFeedback())
          && this.usageMetadata.equals(that.usageMetadata());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= candidates.hashCode();
    h$ *= 1000003;
    h$ ^= createTime.hashCode();
    h$ *= 1000003;
    h$ ^= responseId.hashCode();
    h$ *= 1000003;
    h$ ^= modelVersion.hashCode();
    h$ *= 1000003;
    h$ ^= promptFeedback.hashCode();
    h$ *= 1000003;
    h$ ^= usageMetadata.hashCode();
    return h$;
  }

  @Override
  public GenerateContentResponse.Builder toBuilder() {
    return new AutoValue_GenerateContentResponse.Builder(this);
  }

  static final class Builder extends GenerateContentResponse.Builder {
    private Optional<List<Candidate>> candidates = Optional.empty();
    private Optional<String> createTime = Optional.empty();
    private Optional<String> responseId = Optional.empty();
    private Optional<String> modelVersion = Optional.empty();
    private Optional<GenerateContentResponsePromptFeedback> promptFeedback = Optional.empty();
    private Optional<GenerateContentResponseUsageMetadata> usageMetadata = Optional.empty();
    Builder() {
    }
    Builder(GenerateContentResponse source) {
      this.candidates = source.candidates();
      this.createTime = source.createTime();
      this.responseId = source.responseId();
      this.modelVersion = source.modelVersion();
      this.promptFeedback = source.promptFeedback();
      this.usageMetadata = source.usageMetadata();
    }
    @Override
    public GenerateContentResponse.Builder candidates(List<Candidate> candidates) {
      this.candidates = Optional.of(candidates);
      return this;
    }
    @Override
    public GenerateContentResponse.Builder createTime(String createTime) {
      this.createTime = Optional.of(createTime);
      return this;
    }
    @Override
    public GenerateContentResponse.Builder responseId(String responseId) {
      this.responseId = Optional.of(responseId);
      return this;
    }
    @Override
    public GenerateContentResponse.Builder modelVersion(String modelVersion) {
      this.modelVersion = Optional.of(modelVersion);
      return this;
    }
    @Override
    public GenerateContentResponse.Builder promptFeedback(GenerateContentResponsePromptFeedback promptFeedback) {
      this.promptFeedback = Optional.of(promptFeedback);
      return this;
    }
    @Override
    public GenerateContentResponse.Builder usageMetadata(GenerateContentResponseUsageMetadata usageMetadata) {
      this.usageMetadata = Optional.of(usageMetadata);
      return this;
    }
    @Override
    public GenerateContentResponse build() {
      return new AutoValue_GenerateContentResponse(
          this.candidates,
          this.createTime,
          this.responseId,
          this.modelVersion,
          this.promptFeedback,
          this.usageMetadata);
    }
  }

}
