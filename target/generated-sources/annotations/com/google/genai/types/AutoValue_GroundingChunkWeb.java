package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_GroundingChunkWeb extends GroundingChunkWeb {

  private final Optional<String> title;

  private final Optional<String> uri;

  private AutoValue_GroundingChunkWeb(
      Optional<String> title,
      Optional<String> uri) {
    this.title = title;
    this.uri = uri;
  }

  @JsonProperty("title")
  @Override
  public Optional<String> title() {
    return title;
  }

  @JsonProperty("uri")
  @Override
  public Optional<String> uri() {
    return uri;
  }

  @Override
  public String toString() {
    return "GroundingChunkWeb{"
        + "title=" + title + ", "
        + "uri=" + uri
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof GroundingChunkWeb) {
      GroundingChunkWeb that = (GroundingChunkWeb) o;
      return this.title.equals(that.title())
          && this.uri.equals(that.uri());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= title.hashCode();
    h$ *= 1000003;
    h$ ^= uri.hashCode();
    return h$;
  }

  @Override
  public GroundingChunkWeb.Builder toBuilder() {
    return new AutoValue_GroundingChunkWeb.Builder(this);
  }

  static final class Builder extends GroundingChunkWeb.Builder {
    private Optional<String> title = Optional.empty();
    private Optional<String> uri = Optional.empty();
    Builder() {
    }
    Builder(GroundingChunkWeb source) {
      this.title = source.title();
      this.uri = source.uri();
    }
    @Override
    public GroundingChunkWeb.Builder title(String title) {
      this.title = Optional.of(title);
      return this;
    }
    @Override
    public GroundingChunkWeb.Builder uri(String uri) {
      this.uri = Optional.of(uri);
      return this;
    }
    @Override
    public GroundingChunkWeb build() {
      return new AutoValue_GroundingChunkWeb(
          this.title,
          this.uri);
    }
  }

}
