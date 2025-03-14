package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_SafetySetting extends SafetySetting {

  private final Optional<String> method;

  private final Optional<String> category;

  private final Optional<String> threshold;

  private AutoValue_SafetySetting(
      Optional<String> method,
      Optional<String> category,
      Optional<String> threshold) {
    this.method = method;
    this.category = category;
    this.threshold = threshold;
  }

  @JsonProperty("method")
  @Override
  public Optional<String> method() {
    return method;
  }

  @JsonProperty("category")
  @Override
  public Optional<String> category() {
    return category;
  }

  @JsonProperty("threshold")
  @Override
  public Optional<String> threshold() {
    return threshold;
  }

  @Override
  public String toString() {
    return "SafetySetting{"
        + "method=" + method + ", "
        + "category=" + category + ", "
        + "threshold=" + threshold
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof SafetySetting) {
      SafetySetting that = (SafetySetting) o;
      return this.method.equals(that.method())
          && this.category.equals(that.category())
          && this.threshold.equals(that.threshold());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= method.hashCode();
    h$ *= 1000003;
    h$ ^= category.hashCode();
    h$ *= 1000003;
    h$ ^= threshold.hashCode();
    return h$;
  }

  @Override
  public SafetySetting.Builder toBuilder() {
    return new AutoValue_SafetySetting.Builder(this);
  }

  static final class Builder extends SafetySetting.Builder {
    private Optional<String> method = Optional.empty();
    private Optional<String> category = Optional.empty();
    private Optional<String> threshold = Optional.empty();
    Builder() {
    }
    Builder(SafetySetting source) {
      this.method = source.method();
      this.category = source.category();
      this.threshold = source.threshold();
    }
    @Override
    public SafetySetting.Builder method(String method) {
      this.method = Optional.of(method);
      return this;
    }
    @Override
    public SafetySetting.Builder category(String category) {
      this.category = Optional.of(category);
      return this;
    }
    @Override
    public SafetySetting.Builder threshold(String threshold) {
      this.threshold = Optional.of(threshold);
      return this;
    }
    @Override
    public SafetySetting build() {
      return new AutoValue_SafetySetting(
          this.method,
          this.category,
          this.threshold);
    }
  }

}
