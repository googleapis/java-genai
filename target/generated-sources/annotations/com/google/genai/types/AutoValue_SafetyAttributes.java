package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_SafetyAttributes extends SafetyAttributes {

  private final Optional<List<String>> categories;

  private final Optional<List<Float>> scores;

  private AutoValue_SafetyAttributes(
      Optional<List<String>> categories,
      Optional<List<Float>> scores) {
    this.categories = categories;
    this.scores = scores;
  }

  @JsonProperty("categories")
  @Override
  public Optional<List<String>> categories() {
    return categories;
  }

  @JsonProperty("scores")
  @Override
  public Optional<List<Float>> scores() {
    return scores;
  }

  @Override
  public String toString() {
    return "SafetyAttributes{"
        + "categories=" + categories + ", "
        + "scores=" + scores
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof SafetyAttributes) {
      SafetyAttributes that = (SafetyAttributes) o;
      return this.categories.equals(that.categories())
          && this.scores.equals(that.scores());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= categories.hashCode();
    h$ *= 1000003;
    h$ ^= scores.hashCode();
    return h$;
  }

  @Override
  public SafetyAttributes.Builder toBuilder() {
    return new AutoValue_SafetyAttributes.Builder(this);
  }

  static final class Builder extends SafetyAttributes.Builder {
    private Optional<List<String>> categories = Optional.empty();
    private Optional<List<Float>> scores = Optional.empty();
    Builder() {
    }
    Builder(SafetyAttributes source) {
      this.categories = source.categories();
      this.scores = source.scores();
    }
    @Override
    public SafetyAttributes.Builder categories(List<String> categories) {
      this.categories = Optional.of(categories);
      return this;
    }
    @Override
    public SafetyAttributes.Builder scores(List<Float> scores) {
      this.scores = Optional.of(scores);
      return this;
    }
    @Override
    public SafetyAttributes build() {
      return new AutoValue_SafetyAttributes(
          this.categories,
          this.scores);
    }
  }

}
