package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_FunctionResponse extends FunctionResponse {

  private final Optional<String> id;

  private final Optional<String> name;

  private final Optional<Map<String, Object>> response;

  private AutoValue_FunctionResponse(
      Optional<String> id,
      Optional<String> name,
      Optional<Map<String, Object>> response) {
    this.id = id;
    this.name = name;
    this.response = response;
  }

  @JsonProperty("id")
  @Override
  public Optional<String> id() {
    return id;
  }

  @JsonProperty("name")
  @Override
  public Optional<String> name() {
    return name;
  }

  @JsonProperty("response")
  @Override
  public Optional<Map<String, Object>> response() {
    return response;
  }

  @Override
  public String toString() {
    return "FunctionResponse{"
        + "id=" + id + ", "
        + "name=" + name + ", "
        + "response=" + response
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof FunctionResponse) {
      FunctionResponse that = (FunctionResponse) o;
      return this.id.equals(that.id())
          && this.name.equals(that.name())
          && this.response.equals(that.response());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= id.hashCode();
    h$ *= 1000003;
    h$ ^= name.hashCode();
    h$ *= 1000003;
    h$ ^= response.hashCode();
    return h$;
  }

  @Override
  public FunctionResponse.Builder toBuilder() {
    return new AutoValue_FunctionResponse.Builder(this);
  }

  static final class Builder extends FunctionResponse.Builder {
    private Optional<String> id = Optional.empty();
    private Optional<String> name = Optional.empty();
    private Optional<Map<String, Object>> response = Optional.empty();
    Builder() {
    }
    Builder(FunctionResponse source) {
      this.id = source.id();
      this.name = source.name();
      this.response = source.response();
    }
    @Override
    public FunctionResponse.Builder id(String id) {
      this.id = Optional.of(id);
      return this;
    }
    @Override
    public FunctionResponse.Builder name(String name) {
      this.name = Optional.of(name);
      return this;
    }
    @Override
    public FunctionResponse.Builder response(Map<String, Object> response) {
      this.response = Optional.of(response);
      return this;
    }
    @Override
    public FunctionResponse build() {
      return new AutoValue_FunctionResponse(
          this.id,
          this.name,
          this.response);
    }
  }

}
