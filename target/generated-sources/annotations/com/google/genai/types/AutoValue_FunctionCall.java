package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_FunctionCall extends FunctionCall {

  private final Optional<String> id;

  private final Optional<Map<String, Object>> args;

  private final Optional<String> name;

  private AutoValue_FunctionCall(
      Optional<String> id,
      Optional<Map<String, Object>> args,
      Optional<String> name) {
    this.id = id;
    this.args = args;
    this.name = name;
  }

  @JsonProperty("id")
  @Override
  public Optional<String> id() {
    return id;
  }

  @JsonProperty("args")
  @Override
  public Optional<Map<String, Object>> args() {
    return args;
  }

  @JsonProperty("name")
  @Override
  public Optional<String> name() {
    return name;
  }

  @Override
  public String toString() {
    return "FunctionCall{"
        + "id=" + id + ", "
        + "args=" + args + ", "
        + "name=" + name
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof FunctionCall) {
      FunctionCall that = (FunctionCall) o;
      return this.id.equals(that.id())
          && this.args.equals(that.args())
          && this.name.equals(that.name());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= id.hashCode();
    h$ *= 1000003;
    h$ ^= args.hashCode();
    h$ *= 1000003;
    h$ ^= name.hashCode();
    return h$;
  }

  @Override
  public FunctionCall.Builder toBuilder() {
    return new AutoValue_FunctionCall.Builder(this);
  }

  static final class Builder extends FunctionCall.Builder {
    private Optional<String> id = Optional.empty();
    private Optional<Map<String, Object>> args = Optional.empty();
    private Optional<String> name = Optional.empty();
    Builder() {
    }
    Builder(FunctionCall source) {
      this.id = source.id();
      this.args = source.args();
      this.name = source.name();
    }
    @Override
    public FunctionCall.Builder id(String id) {
      this.id = Optional.of(id);
      return this;
    }
    @Override
    public FunctionCall.Builder args(Map<String, Object> args) {
      this.args = Optional.of(args);
      return this;
    }
    @Override
    public FunctionCall.Builder name(String name) {
      this.name = Optional.of(name);
      return this;
    }
    @Override
    public FunctionCall build() {
      return new AutoValue_FunctionCall(
          this.id,
          this.args,
          this.name);
    }
  }

}
