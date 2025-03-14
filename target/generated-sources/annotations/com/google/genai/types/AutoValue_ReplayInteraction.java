package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_ReplayInteraction extends ReplayInteraction {

  private final Optional<ReplayRequest> request;

  private final Optional<ReplayResponse> response;

  private AutoValue_ReplayInteraction(
      Optional<ReplayRequest> request,
      Optional<ReplayResponse> response) {
    this.request = request;
    this.response = response;
  }

  @JsonProperty("request")
  @Override
  public Optional<ReplayRequest> request() {
    return request;
  }

  @JsonProperty("response")
  @Override
  public Optional<ReplayResponse> response() {
    return response;
  }

  @Override
  public String toString() {
    return "ReplayInteraction{"
        + "request=" + request + ", "
        + "response=" + response
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof ReplayInteraction) {
      ReplayInteraction that = (ReplayInteraction) o;
      return this.request.equals(that.request())
          && this.response.equals(that.response());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= request.hashCode();
    h$ *= 1000003;
    h$ ^= response.hashCode();
    return h$;
  }

  @Override
  public ReplayInteraction.Builder toBuilder() {
    return new AutoValue_ReplayInteraction.Builder(this);
  }

  static final class Builder extends ReplayInteraction.Builder {
    private Optional<ReplayRequest> request = Optional.empty();
    private Optional<ReplayResponse> response = Optional.empty();
    Builder() {
    }
    Builder(ReplayInteraction source) {
      this.request = source.request();
      this.response = source.response();
    }
    @Override
    public ReplayInteraction.Builder request(ReplayRequest request) {
      this.request = Optional.of(request);
      return this;
    }
    @Override
    public ReplayInteraction.Builder response(ReplayResponse response) {
      this.response = Optional.of(response);
      return this;
    }
    @Override
    public ReplayInteraction build() {
      return new AutoValue_ReplayInteraction(
          this.request,
          this.response);
    }
  }

}
