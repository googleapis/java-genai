package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_GroundingChunkRetrievedContext extends GroundingChunkRetrievedContext {

  private final Optional<String> text;

  private final Optional<String> title;

  private final Optional<String> uri;

  private AutoValue_GroundingChunkRetrievedContext(
      Optional<String> text,
      Optional<String> title,
      Optional<String> uri) {
    this.text = text;
    this.title = title;
    this.uri = uri;
  }

  @JsonProperty("text")
  @Override
  public Optional<String> text() {
    return text;
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
    return "GroundingChunkRetrievedContext{"
        + "text=" + text + ", "
        + "title=" + title + ", "
        + "uri=" + uri
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof GroundingChunkRetrievedContext) {
      GroundingChunkRetrievedContext that = (GroundingChunkRetrievedContext) o;
      return this.text.equals(that.text())
          && this.title.equals(that.title())
          && this.uri.equals(that.uri());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= text.hashCode();
    h$ *= 1000003;
    h$ ^= title.hashCode();
    h$ *= 1000003;
    h$ ^= uri.hashCode();
    return h$;
  }

  @Override
  public GroundingChunkRetrievedContext.Builder toBuilder() {
    return new AutoValue_GroundingChunkRetrievedContext.Builder(this);
  }

  static final class Builder extends GroundingChunkRetrievedContext.Builder {
    private Optional<String> text = Optional.empty();
    private Optional<String> title = Optional.empty();
    private Optional<String> uri = Optional.empty();
    Builder() {
    }
    Builder(GroundingChunkRetrievedContext source) {
      this.text = source.text();
      this.title = source.title();
      this.uri = source.uri();
    }
    @Override
    public GroundingChunkRetrievedContext.Builder text(String text) {
      this.text = Optional.of(text);
      return this;
    }
    @Override
    public GroundingChunkRetrievedContext.Builder title(String title) {
      this.title = Optional.of(title);
      return this;
    }
    @Override
    public GroundingChunkRetrievedContext.Builder uri(String uri) {
      this.uri = Optional.of(uri);
      return this;
    }
    @Override
    public GroundingChunkRetrievedContext build() {
      return new AutoValue_GroundingChunkRetrievedContext(
          this.text,
          this.title,
          this.uri);
    }
  }

}
