package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_ReplayFile extends ReplayFile {

  private final Optional<String> replayId;

  private final Optional<List<ReplayInteraction>> interactions;

  private AutoValue_ReplayFile(
      Optional<String> replayId,
      Optional<List<ReplayInteraction>> interactions) {
    this.replayId = replayId;
    this.interactions = interactions;
  }

  @JsonProperty("replayId")
  @Override
  public Optional<String> replayId() {
    return replayId;
  }

  @JsonProperty("interactions")
  @Override
  public Optional<List<ReplayInteraction>> interactions() {
    return interactions;
  }

  @Override
  public String toString() {
    return "ReplayFile{"
        + "replayId=" + replayId + ", "
        + "interactions=" + interactions
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof ReplayFile) {
      ReplayFile that = (ReplayFile) o;
      return this.replayId.equals(that.replayId())
          && this.interactions.equals(that.interactions());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= replayId.hashCode();
    h$ *= 1000003;
    h$ ^= interactions.hashCode();
    return h$;
  }

  @Override
  public ReplayFile.Builder toBuilder() {
    return new AutoValue_ReplayFile.Builder(this);
  }

  static final class Builder extends ReplayFile.Builder {
    private Optional<String> replayId = Optional.empty();
    private Optional<List<ReplayInteraction>> interactions = Optional.empty();
    Builder() {
    }
    Builder(ReplayFile source) {
      this.replayId = source.replayId();
      this.interactions = source.interactions();
    }
    @Override
    public ReplayFile.Builder replayId(String replayId) {
      this.replayId = Optional.of(replayId);
      return this;
    }
    @Override
    public ReplayFile.Builder interactions(List<ReplayInteraction> interactions) {
      this.interactions = Optional.of(interactions);
      return this;
    }
    @Override
    public ReplayFile build() {
      return new AutoValue_ReplayFile(
          this.replayId,
          this.interactions);
    }
  }

}
