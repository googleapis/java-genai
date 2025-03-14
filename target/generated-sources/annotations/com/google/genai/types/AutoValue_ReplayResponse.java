package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_ReplayResponse extends ReplayResponse {

  private final Optional<Integer> statusCode;

  private final Optional<Map<String, String>> headers;

  private final Optional<List<Map<String, Object>>> bodySegments;

  private final Optional<List<Map<String, Object>>> sdkResponseSegments;

  private AutoValue_ReplayResponse(
      Optional<Integer> statusCode,
      Optional<Map<String, String>> headers,
      Optional<List<Map<String, Object>>> bodySegments,
      Optional<List<Map<String, Object>>> sdkResponseSegments) {
    this.statusCode = statusCode;
    this.headers = headers;
    this.bodySegments = bodySegments;
    this.sdkResponseSegments = sdkResponseSegments;
  }

  @JsonProperty("statusCode")
  @Override
  public Optional<Integer> statusCode() {
    return statusCode;
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

  @JsonProperty("sdkResponseSegments")
  @Override
  public Optional<List<Map<String, Object>>> sdkResponseSegments() {
    return sdkResponseSegments;
  }

  @Override
  public String toString() {
    return "ReplayResponse{"
        + "statusCode=" + statusCode + ", "
        + "headers=" + headers + ", "
        + "bodySegments=" + bodySegments + ", "
        + "sdkResponseSegments=" + sdkResponseSegments
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof ReplayResponse) {
      ReplayResponse that = (ReplayResponse) o;
      return this.statusCode.equals(that.statusCode())
          && this.headers.equals(that.headers())
          && this.bodySegments.equals(that.bodySegments())
          && this.sdkResponseSegments.equals(that.sdkResponseSegments());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= statusCode.hashCode();
    h$ *= 1000003;
    h$ ^= headers.hashCode();
    h$ *= 1000003;
    h$ ^= bodySegments.hashCode();
    h$ *= 1000003;
    h$ ^= sdkResponseSegments.hashCode();
    return h$;
  }

  @Override
  public ReplayResponse.Builder toBuilder() {
    return new AutoValue_ReplayResponse.Builder(this);
  }

  static final class Builder extends ReplayResponse.Builder {
    private Optional<Integer> statusCode = Optional.empty();
    private Optional<Map<String, String>> headers = Optional.empty();
    private Optional<List<Map<String, Object>>> bodySegments = Optional.empty();
    private Optional<List<Map<String, Object>>> sdkResponseSegments = Optional.empty();
    Builder() {
    }
    Builder(ReplayResponse source) {
      this.statusCode = source.statusCode();
      this.headers = source.headers();
      this.bodySegments = source.bodySegments();
      this.sdkResponseSegments = source.sdkResponseSegments();
    }
    @Override
    public ReplayResponse.Builder statusCode(Integer statusCode) {
      this.statusCode = Optional.of(statusCode);
      return this;
    }
    @Override
    public ReplayResponse.Builder headers(Map<String, String> headers) {
      this.headers = Optional.of(headers);
      return this;
    }
    @Override
    public ReplayResponse.Builder bodySegments(List<Map<String, Object>> bodySegments) {
      this.bodySegments = Optional.of(bodySegments);
      return this;
    }
    @Override
    public ReplayResponse.Builder sdkResponseSegments(List<Map<String, Object>> sdkResponseSegments) {
      this.sdkResponseSegments = Optional.of(sdkResponseSegments);
      return this;
    }
    @Override
    public ReplayResponse build() {
      return new AutoValue_ReplayResponse(
          this.statusCode,
          this.headers,
          this.bodySegments,
          this.sdkResponseSegments);
    }
  }

}
