package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_GenerateContentResponseUsageMetadata extends GenerateContentResponseUsageMetadata {

  private final Optional<Integer> cachedContentTokenCount;

  private final Optional<Integer> candidatesTokenCount;

  private final Optional<Integer> promptTokenCount;

  private final Optional<Integer> totalTokenCount;

  private AutoValue_GenerateContentResponseUsageMetadata(
      Optional<Integer> cachedContentTokenCount,
      Optional<Integer> candidatesTokenCount,
      Optional<Integer> promptTokenCount,
      Optional<Integer> totalTokenCount) {
    this.cachedContentTokenCount = cachedContentTokenCount;
    this.candidatesTokenCount = candidatesTokenCount;
    this.promptTokenCount = promptTokenCount;
    this.totalTokenCount = totalTokenCount;
  }

  @JsonProperty("cachedContentTokenCount")
  @Override
  public Optional<Integer> cachedContentTokenCount() {
    return cachedContentTokenCount;
  }

  @JsonProperty("candidatesTokenCount")
  @Override
  public Optional<Integer> candidatesTokenCount() {
    return candidatesTokenCount;
  }

  @JsonProperty("promptTokenCount")
  @Override
  public Optional<Integer> promptTokenCount() {
    return promptTokenCount;
  }

  @JsonProperty("totalTokenCount")
  @Override
  public Optional<Integer> totalTokenCount() {
    return totalTokenCount;
  }

  @Override
  public String toString() {
    return "GenerateContentResponseUsageMetadata{"
        + "cachedContentTokenCount=" + cachedContentTokenCount + ", "
        + "candidatesTokenCount=" + candidatesTokenCount + ", "
        + "promptTokenCount=" + promptTokenCount + ", "
        + "totalTokenCount=" + totalTokenCount
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof GenerateContentResponseUsageMetadata) {
      GenerateContentResponseUsageMetadata that = (GenerateContentResponseUsageMetadata) o;
      return this.cachedContentTokenCount.equals(that.cachedContentTokenCount())
          && this.candidatesTokenCount.equals(that.candidatesTokenCount())
          && this.promptTokenCount.equals(that.promptTokenCount())
          && this.totalTokenCount.equals(that.totalTokenCount());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= cachedContentTokenCount.hashCode();
    h$ *= 1000003;
    h$ ^= candidatesTokenCount.hashCode();
    h$ *= 1000003;
    h$ ^= promptTokenCount.hashCode();
    h$ *= 1000003;
    h$ ^= totalTokenCount.hashCode();
    return h$;
  }

  @Override
  public GenerateContentResponseUsageMetadata.Builder toBuilder() {
    return new AutoValue_GenerateContentResponseUsageMetadata.Builder(this);
  }

  static final class Builder extends GenerateContentResponseUsageMetadata.Builder {
    private Optional<Integer> cachedContentTokenCount = Optional.empty();
    private Optional<Integer> candidatesTokenCount = Optional.empty();
    private Optional<Integer> promptTokenCount = Optional.empty();
    private Optional<Integer> totalTokenCount = Optional.empty();
    Builder() {
    }
    Builder(GenerateContentResponseUsageMetadata source) {
      this.cachedContentTokenCount = source.cachedContentTokenCount();
      this.candidatesTokenCount = source.candidatesTokenCount();
      this.promptTokenCount = source.promptTokenCount();
      this.totalTokenCount = source.totalTokenCount();
    }
    @Override
    public GenerateContentResponseUsageMetadata.Builder cachedContentTokenCount(Integer cachedContentTokenCount) {
      this.cachedContentTokenCount = Optional.of(cachedContentTokenCount);
      return this;
    }
    @Override
    public GenerateContentResponseUsageMetadata.Builder candidatesTokenCount(Integer candidatesTokenCount) {
      this.candidatesTokenCount = Optional.of(candidatesTokenCount);
      return this;
    }
    @Override
    public GenerateContentResponseUsageMetadata.Builder promptTokenCount(Integer promptTokenCount) {
      this.promptTokenCount = Optional.of(promptTokenCount);
      return this;
    }
    @Override
    public GenerateContentResponseUsageMetadata.Builder totalTokenCount(Integer totalTokenCount) {
      this.totalTokenCount = Optional.of(totalTokenCount);
      return this;
    }
    @Override
    public GenerateContentResponseUsageMetadata build() {
      return new AutoValue_GenerateContentResponseUsageMetadata(
          this.cachedContentTokenCount,
          this.candidatesTokenCount,
          this.promptTokenCount,
          this.totalTokenCount);
    }
  }

}
