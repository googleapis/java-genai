package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_ReplayRequest extends ReplayRequest {

  private final Optional<String> method;

  private final Optional<String> url;

  private final Optional<Map<String, String>> headers;

  private final Optional<List<Map<String, Object>>> bodySegments;

  private AutoValue_ReplayRequest(
      Optional<String> method,
      Optional<String> url,
      Optional<Map<String, String>> headers,
      Optional<List<Map<String, Object>>> bodySegments) {
    this.method = method;
    this.url = url;
    this.headers = headers;
    this.bodySegments = bodySegments;
  }

  @JsonProperty("method")
  @Override
  public Optional<String> method() {
    return method;
  }

  @JsonProperty("url")
  @Override
  public Optional<String> url() {
    return url;
  }

  @JsonProperty("headers")
  @Override
  public Optional<Map<String, String>> headers() {
    return headers;
  }

  @JsonProperty("bodySegments")
  @Override
  public Optional<List<Map<String, Object>>> bodySegments() {
    return bodySegments;
  }

  @Override
  public String toString() {
    return "ReplayRequest{"
        + "method=" + method + ", "
        + "url=" + url + ", "
        + "headers=" + headers + ", "
        + "bodySegments=" + bodySegments
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof ReplayRequest) {
      ReplayRequest that = (ReplayRequest) o;
      return this.method.equals(that.method())
          && this.url.equals(that.url())
          && this.headers.equals(that.headers())
          && this.bodySegments.equals(that.bodySegments());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= method.hashCode();
    h$ *= 1000003;
    h$ ^= url.hashCode();
    h$ *= 1000003;
    h$ ^= headers.hashCode();
    h$ *= 1000003;
    h$ ^= bodySegments.hashCode();
    return h$;
  }

  @Override
  public ReplayRequest.Builder toBuilder() {
    return new AutoValue_ReplayRequest.Builder(this);
  }

  static final class Builder extends ReplayRequest.Builder {
    private Optional<String> method = Optional.empty();
    private Optional<String> url = Optional.empty();
    private Optional<Map<String, String>> headers = Optional.empty();
    private Optional<List<Map<String, Object>>> bodySegments = Optional.empty();
    Builder() {
    }
    Builder(ReplayRequest source) {
      this.method = source.method();
      this.url = source.url();
      this.headers = source.headers();
      this.bodySegments = source.bodySegments();
    }
    @Override
    public ReplayRequest.Builder method(String method) {
      this.method = Optional.of(method);
      return this;
    }
    @Override
    public ReplayRequest.Builder url(String url) {
      this.url = Optional.of(url);
      return this;
    }
    @Override
    public ReplayRequest.Builder headers(Map<String, String> headers) {
      this.headers = Optional.of(headers);
      return this;
    }
    @Override
    public ReplayRequest.Builder bodySegments(List<Map<String, Object>> bodySegments) {
      this.bodySegments = Optional.of(bodySegments);
      return this;
    }
    @Override
    public ReplayRequest build() {
      return new AutoValue_ReplayRequest(
          this.method,
          this.url,
          this.headers,
          this.bodySegments);
    }
  }

}
