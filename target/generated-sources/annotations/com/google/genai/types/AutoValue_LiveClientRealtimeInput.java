package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_LiveClientRealtimeInput extends LiveClientRealtimeInput {

  private final Optional<List<Blob>> mediaChunks;

  private AutoValue_LiveClientRealtimeInput(
      Optional<List<Blob>> mediaChunks) {
    this.mediaChunks = mediaChunks;
  }

  @JsonProperty("mediaChunks")
  @Override
  public Optional<List<Blob>> mediaChunks() {
    return mediaChunks;
  }

  @Override
  public String toString() {
    return "LiveClientRealtimeInput{"
        + "mediaChunks=" + mediaChunks
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof LiveClientRealtimeInput) {
      LiveClientRealtimeInput that = (LiveClientRealtimeInput) o;
      return this.mediaChunks.equals(that.mediaChunks());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= mediaChunks.hashCode();
    return h$;
  }

  @Override
  public LiveClientRealtimeInput.Builder toBuilder() {
    return new AutoValue_LiveClientRealtimeInput.Builder(this);
  }

  static final class Builder extends LiveClientRealtimeInput.Builder {
    private Optional<List<Blob>> mediaChunks = Optional.empty();
    Builder() {
    }
    Builder(LiveClientRealtimeInput source) {
      this.mediaChunks = source.mediaChunks();
    }
    @Override
    public LiveClientRealtimeInput.Builder mediaChunks(List<Blob> mediaChunks) {
      this.mediaChunks = Optional.of(mediaChunks);
      return this;
    }
    @Override
    public LiveClientRealtimeInput build() {
      return new AutoValue_LiveClientRealtimeInput(
          this.mediaChunks);
    }
  }

}
