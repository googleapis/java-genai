package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_SafetyRating extends SafetyRating {

  private final Optional<Boolean> blocked;

  private final Optional<String> category;

  private final Optional<String> probability;

  private final Optional<Float> probabilityScore;

  private final Optional<String> severity;

  private final Optional<Float> severityScore;

  private AutoValue_SafetyRating(
      Optional<Boolean> blocked,
      Optional<String> category,
      Optional<String> probability,
      Optional<Float> probabilityScore,
      Optional<String> severity,
      Optional<Float> severityScore) {
    this.blocked = blocked;
    this.category = category;
    this.probability = probability;
    this.probabilityScore = probabilityScore;
    this.severity = severity;
    this.severityScore = severityScore;
  }

  @JsonProperty("blocked")
  @Override
  public Optional<Boolean> blocked() {
    return blocked;
  }

  @JsonProperty("category")
  @Override
  public Optional<String> category() {
    return category;
  }

  @JsonProperty("probability")
  @Override
  public Optional<String> probability() {
    return probability;
  }

  @JsonProperty("probabilityScore")
  @Override
  public Optional<Float> probabilityScore() {
    return probabilityScore;
  }

  @JsonProperty("severity")
  @Override
  public Optional<String> severity() {
    return severity;
  }

  @JsonProperty("severityScore")
  @Override
  public Optional<Float> severityScore() {
    return severityScore;
  }

  @Override
  public String toString() {
    return "SafetyRating{"
        + "blocked=" + blocked + ", "
        + "category=" + category + ", "
        + "probability=" + probability + ", "
        + "probabilityScore=" + probabilityScore + ", "
        + "severity=" + severity + ", "
        + "severityScore=" + severityScore
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof SafetyRating) {
      SafetyRating that = (SafetyRating) o;
      return this.blocked.equals(that.blocked())
          && this.category.equals(that.category())
          && this.probability.equals(that.probability())
          && this.probabilityScore.equals(that.probabilityScore())
          && this.severity.equals(that.severity())
          && this.severityScore.equals(that.severityScore());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= blocked.hashCode();
    h$ *= 1000003;
    h$ ^= category.hashCode();
    h$ *= 1000003;
    h$ ^= probability.hashCode();
    h$ *= 1000003;
    h$ ^= probabilityScore.hashCode();
    h$ *= 1000003;
    h$ ^= severity.hashCode();
    h$ *= 1000003;
    h$ ^= severityScore.hashCode();
    return h$;
  }

  @Override
  public SafetyRating.Builder toBuilder() {
    return new AutoValue_SafetyRating.Builder(this);
  }

  static final class Builder extends SafetyRating.Builder {
    private Optional<Boolean> blocked = Optional.empty();
    private Optional<String> category = Optional.empty();
    private Optional<String> probability = Optional.empty();
    private Optional<Float> probabilityScore = Optional.empty();
    private Optional<String> severity = Optional.empty();
    private Optional<Float> severityScore = Optional.empty();
    Builder() {
    }
    Builder(SafetyRating source) {
      this.blocked = source.blocked();
      this.category = source.category();
      this.probability = source.probability();
      this.probabilityScore = source.probabilityScore();
      this.severity = source.severity();
      this.severityScore = source.severityScore();
    }
    @Override
    public SafetyRating.Builder blocked(boolean blocked) {
      this.blocked = Optional.of(blocked);
      return this;
    }
    @Override
    public SafetyRating.Builder category(String category) {
      this.category = Optional.of(category);
      return this;
    }
    @Override
    public SafetyRating.Builder probability(String probability) {
      this.probability = Optional.of(probability);
      return this;
    }
    @Override
    public SafetyRating.Builder probabilityScore(Float probabilityScore) {
      this.probabilityScore = Optional.of(probabilityScore);
      return this;
    }
    @Override
    public SafetyRating.Builder severity(String severity) {
      this.severity = Optional.of(severity);
      return this;
    }
    @Override
    public SafetyRating.Builder severityScore(Float severityScore) {
      this.severityScore = Optional.of(severityScore);
      return this;
    }
    @Override
    public SafetyRating build() {
      return new AutoValue_SafetyRating(
          this.blocked,
          this.category,
          this.probability,
          this.probabilityScore,
          this.severity,
          this.severityScore);
    }
  }

}
