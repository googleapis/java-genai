package com.google.genai.types;

import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_ToolCodeExecution extends ToolCodeExecution {

  private AutoValue_ToolCodeExecution() {
  }

  @Override
  public String toString() {
    return "ToolCodeExecution{"
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof ToolCodeExecution) {
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    return h$;
  }

  @Override
  public ToolCodeExecution.Builder toBuilder() {
    return new AutoValue_ToolCodeExecution.Builder(this);
  }

  static final class Builder extends ToolCodeExecution.Builder {
    Builder() {
    }
    Builder(ToolCodeExecution source) {
    }
    @Override
    public ToolCodeExecution build() {
      return new AutoValue_ToolCodeExecution();
    }
  }

}
