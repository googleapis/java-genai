package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_SubjectReferenceConfig extends SubjectReferenceConfig {

  private final Optional<String> subjectType;

  private final Optional<String> subjectDescription;

  private AutoValue_SubjectReferenceConfig(
      Optional<String> subjectType,
      Optional<String> subjectDescription) {
    this.subjectType = subjectType;
    this.subjectDescription = subjectDescription;
  }

  @JsonProperty("subjectType")
  @Override
  public Optional<String> subjectType() {
    return subjectType;
  }

  @JsonProperty("subjectDescription")
  @Override
  public Optional<String> subjectDescription() {
    return subjectDescription;
  }

  @Override
  public String toString() {
    return "SubjectReferenceConfig{"
        + "subjectType=" + subjectType + ", "
        + "subjectDescription=" + subjectDescription
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof SubjectReferenceConfig) {
      SubjectReferenceConfig that = (SubjectReferenceConfig) o;
      return this.subjectType.equals(that.subjectType())
          && this.subjectDescription.equals(that.subjectDescription());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= subjectType.hashCode();
    h$ *= 1000003;
    h$ ^= subjectDescription.hashCode();
    return h$;
  }

  @Override
  public SubjectReferenceConfig.Builder toBuilder() {
    return new AutoValue_SubjectReferenceConfig.Builder(this);
  }

  static final class Builder extends SubjectReferenceConfig.Builder {
    private Optional<String> subjectType = Optional.empty();
    private Optional<String> subjectDescription = Optional.empty();
    Builder() {
    }
    Builder(SubjectReferenceConfig source) {
      this.subjectType = source.subjectType();
      this.subjectDescription = source.subjectDescription();
    }
    @Override
    public SubjectReferenceConfig.Builder subjectType(String subjectType) {
      this.subjectType = Optional.of(subjectType);
      return this;
    }
    @Override
    public SubjectReferenceConfig.Builder subjectDescription(String subjectDescription) {
      this.subjectDescription = Optional.of(subjectDescription);
      return this;
    }
    @Override
    public SubjectReferenceConfig build() {
      return new AutoValue_SubjectReferenceConfig(
          this.subjectType,
          this.subjectDescription);
    }
  }

}
