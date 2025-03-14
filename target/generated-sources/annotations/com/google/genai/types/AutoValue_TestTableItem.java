package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_TestTableItem extends TestTableItem {

  private final Optional<String> name;

  private final Optional<Map<String, Object>> parameters;

  private final Optional<String> exceptionIfMldev;

  private final Optional<String> exceptionIfVertex;

  private final Optional<String> overrideReplayId;

  private final Optional<Boolean> hasUnion;

  private final Optional<String> skipInApiMode;

  private AutoValue_TestTableItem(
      Optional<String> name,
      Optional<Map<String, Object>> parameters,
      Optional<String> exceptionIfMldev,
      Optional<String> exceptionIfVertex,
      Optional<String> overrideReplayId,
      Optional<Boolean> hasUnion,
      Optional<String> skipInApiMode) {
    this.name = name;
    this.parameters = parameters;
    this.exceptionIfMldev = exceptionIfMldev;
    this.exceptionIfVertex = exceptionIfVertex;
    this.overrideReplayId = overrideReplayId;
    this.hasUnion = hasUnion;
    this.skipInApiMode = skipInApiMode;
  }

  @JsonProperty("name")
  @Override
  public Optional<String> name() {
    return name;
  }

  @JsonProperty("parameters")
  @Override
  public Optional<Map<String, Object>> parameters() {
    return parameters;
  }

  @JsonProperty("exceptionIfMldev")
  @Override
  public Optional<String> exceptionIfMldev() {
    return exceptionIfMldev;
  }

  @JsonProperty("exceptionIfVertex")
  @Override
  public Optional<String> exceptionIfVertex() {
    return exceptionIfVertex;
  }

  @JsonProperty("overrideReplayId")
  @Override
  public Optional<String> overrideReplayId() {
    return overrideReplayId;
  }

  @JsonProperty("hasUnion")
  @Override
  public Optional<Boolean> hasUnion() {
    return hasUnion;
  }

  @JsonProperty("skipInApiMode")
  @Override
  public Optional<String> skipInApiMode() {
    return skipInApiMode;
  }

  @Override
  public String toString() {
    return "TestTableItem{"
        + "name=" + name + ", "
        + "parameters=" + parameters + ", "
        + "exceptionIfMldev=" + exceptionIfMldev + ", "
        + "exceptionIfVertex=" + exceptionIfVertex + ", "
        + "overrideReplayId=" + overrideReplayId + ", "
        + "hasUnion=" + hasUnion + ", "
        + "skipInApiMode=" + skipInApiMode
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof TestTableItem) {
      TestTableItem that = (TestTableItem) o;
      return this.name.equals(that.name())
          && this.parameters.equals(that.parameters())
          && this.exceptionIfMldev.equals(that.exceptionIfMldev())
          && this.exceptionIfVertex.equals(that.exceptionIfVertex())
          && this.overrideReplayId.equals(that.overrideReplayId())
          && this.hasUnion.equals(that.hasUnion())
          && this.skipInApiMode.equals(that.skipInApiMode());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= name.hashCode();
    h$ *= 1000003;
    h$ ^= parameters.hashCode();
    h$ *= 1000003;
    h$ ^= exceptionIfMldev.hashCode();
    h$ *= 1000003;
    h$ ^= exceptionIfVertex.hashCode();
    h$ *= 1000003;
    h$ ^= overrideReplayId.hashCode();
    h$ *= 1000003;
    h$ ^= hasUnion.hashCode();
    h$ *= 1000003;
    h$ ^= skipInApiMode.hashCode();
    return h$;
  }

  @Override
  public TestTableItem.Builder toBuilder() {
    return new AutoValue_TestTableItem.Builder(this);
  }

  static final class Builder extends TestTableItem.Builder {
    private Optional<String> name = Optional.empty();
    private Optional<Map<String, Object>> parameters = Optional.empty();
    private Optional<String> exceptionIfMldev = Optional.empty();
    private Optional<String> exceptionIfVertex = Optional.empty();
    private Optional<String> overrideReplayId = Optional.empty();
    private Optional<Boolean> hasUnion = Optional.empty();
    private Optional<String> skipInApiMode = Optional.empty();
    Builder() {
    }
    Builder(TestTableItem source) {
      this.name = source.name();
      this.parameters = source.parameters();
      this.exceptionIfMldev = source.exceptionIfMldev();
      this.exceptionIfVertex = source.exceptionIfVertex();
      this.overrideReplayId = source.overrideReplayId();
      this.hasUnion = source.hasUnion();
      this.skipInApiMode = source.skipInApiMode();
    }
    @Override
    public TestTableItem.Builder name(String name) {
      this.name = Optional.of(name);
      return this;
    }
    @Override
    public TestTableItem.Builder parameters(Map<String, Object> parameters) {
      this.parameters = Optional.of(parameters);
      return this;
    }
    @Override
    public TestTableItem.Builder exceptionIfMldev(String exceptionIfMldev) {
      this.exceptionIfMldev = Optional.of(exceptionIfMldev);
      return this;
    }
    @Override
    public TestTableItem.Builder exceptionIfVertex(String exceptionIfVertex) {
      this.exceptionIfVertex = Optional.of(exceptionIfVertex);
      return this;
    }
    @Override
    public TestTableItem.Builder overrideReplayId(String overrideReplayId) {
      this.overrideReplayId = Optional.of(overrideReplayId);
      return this;
    }
    @Override
    public TestTableItem.Builder hasUnion(boolean hasUnion) {
      this.hasUnion = Optional.of(hasUnion);
      return this;
    }
    @Override
    public TestTableItem.Builder skipInApiMode(String skipInApiMode) {
      this.skipInApiMode = Optional.of(skipInApiMode);
      return this;
    }
    @Override
    public TestTableItem build() {
      return new AutoValue_TestTableItem(
          this.name,
          this.parameters,
          this.exceptionIfMldev,
          this.exceptionIfVertex,
          this.overrideReplayId,
          this.hasUnion,
          this.skipInApiMode);
    }
  }

}
