package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_Content extends Content {

  private final Optional<List<Part>> parts;

  private final Optional<String> role;

  private AutoValue_Content(
      Optional<List<Part>> parts,
      Optional<String> role) {
    this.parts = parts;
    this.role = role;
  }

  @JsonProperty("parts")
  @Override
  public Optional<List<Part>> parts() {
    return parts;
  }

  @JsonProperty("role")
  @Override
  public Optional<String> role() {
    return role;
  }

  @Override
  public String toString() {
    return "Content{"
        + "parts=" + parts + ", "
        + "role=" + role
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof Content) {
      Content that = (Content) o;
      return this.parts.equals(that.parts())
          && this.role.equals(that.role());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= parts.hashCode();
    h$ *= 1000003;
    h$ ^= role.hashCode();
    return h$;
  }

  @Override
  public Content.Builder toBuilder() {
    return new AutoValue_Content.Builder(this);
  }

  static final class Builder extends Content.Builder {
    private Optional<List<Part>> parts = Optional.empty();
    private Optional<String> role = Optional.empty();
    Builder() {
    }
    Builder(Content source) {
      this.parts = source.parts();
      this.role = source.role();
    }
    @Override
    public Content.Builder parts(List<Part> parts) {
      this.parts = Optional.of(parts);
      return this;
    }
    @Override
    public Content.Builder role(String role) {
      this.role = Optional.of(role);
      return this;
    }
    @Override
    public Content build() {
      return new AutoValue_Content(
          this.parts,
          this.role);
    }
  }

}
