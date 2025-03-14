package com.google.genai.types;

import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_LiveServerSetupComplete extends LiveServerSetupComplete {

  private AutoValue_LiveServerSetupComplete() {
  }

  @Override
  public String toString() {
    return "LiveServerSetupComplete{"
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof LiveServerSetupComplete) {
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
  public LiveServerSetupComplete.Builder toBuilder() {
    return new AutoValue_LiveServerSetupComplete.Builder(this);
  }

  static final class Builder extends LiveServerSetupComplete.Builder {
    Builder() {
    }
    Builder(LiveServerSetupComplete source) {
    }
    @Override
    public LiveServerSetupComplete build() {
      return new AutoValue_LiveServerSetupComplete();
    }
  }

}
