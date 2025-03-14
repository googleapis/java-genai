package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_FunctionDeclaration extends FunctionDeclaration {

  private final Optional<Schema> response;

  private final Optional<String> description;

  private final Optional<String> name;

  private final Optional<Schema> parameters;

  private AutoValue_FunctionDeclaration(
      Optional<Schema> response,
      Optional<String> description,
      Optional<String> name,
      Optional<Schema> parameters) {
    this.response = response;
    this.description = description;
    this.name = name;
    this.parameters = parameters;
  }

  @JsonProperty("response")
  @Override
  public Optional<Schema> response() {
    return response;
  }

  @JsonProperty("description")
  @Override
  public Optional<String> description() {
    return description;
  }

  @JsonProperty("name")
  @Override
  public Optional<String> name() {
    return name;
  }

  @JsonProperty("parameters")
  @Override
  public Optional<Schema> parameters() {
    return parameters;
  }

  @Override
  public String toString() {
    return "FunctionDeclaration{"
        + "response=" + response + ", "
        + "description=" + description + ", "
        + "name=" + name + ", "
        + "parameters=" + parameters
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof FunctionDeclaration) {
      FunctionDeclaration that = (FunctionDeclaration) o;
      return this.response.equals(that.response())
          && this.description.equals(that.description())
          && this.name.equals(that.name())
          && this.parameters.equals(that.parameters());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= response.hashCode();
    h$ *= 1000003;
    h$ ^= description.hashCode();
    h$ *= 1000003;
    h$ ^= name.hashCode();
    h$ *= 1000003;
    h$ ^= parameters.hashCode();
    return h$;
  }

  @Override
  public FunctionDeclaration.Builder toBuilder() {
    return new AutoValue_FunctionDeclaration.Builder(this);
  }

  static final class Builder extends FunctionDeclaration.Builder {
    private Optional<Schema> response = Optional.empty();
    private Optional<String> description = Optional.empty();
    private Optional<String> name = Optional.empty();
    private Optional<Schema> parameters = Optional.empty();
    Builder() {
    }
    Builder(FunctionDeclaration source) {
      this.response = source.response();
      this.description = source.description();
      this.name = source.name();
      this.parameters = source.parameters();
    }
    @Override
    public FunctionDeclaration.Builder response(Schema response) {
      this.response = Optional.of(response);
      return this;
    }
    @Override
    public FunctionDeclaration.Builder description(String description) {
      this.description = Optional.of(description);
      return this;
    }
    @Override
    public FunctionDeclaration.Builder name(String name) {
      this.name = Optional.of(name);
      return this;
    }
    @Override
    public FunctionDeclaration.Builder parameters(Schema parameters) {
      this.parameters = Optional.of(parameters);
      return this;
    }
    @Override
    public FunctionDeclaration build() {
      return new AutoValue_FunctionDeclaration(
          this.response,
          this.description,
          this.name,
          this.parameters);
    }
  }

}
