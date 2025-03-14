package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_Citation extends Citation {

  private final Optional<Integer> endIndex;

  private final Optional<String> license;

  private final Optional<GoogleTypeDate> publicationDate;

  private final Optional<Integer> startIndex;

  private final Optional<String> title;

  private final Optional<String> uri;

  private AutoValue_Citation(
      Optional<Integer> endIndex,
      Optional<String> license,
      Optional<GoogleTypeDate> publicationDate,
      Optional<Integer> startIndex,
      Optional<String> title,
      Optional<String> uri) {
    this.endIndex = endIndex;
    this.license = license;
    this.publicationDate = publicationDate;
    this.startIndex = startIndex;
    this.title = title;
    this.uri = uri;
  }

  @JsonProperty("endIndex")
  @Override
  public Optional<Integer> endIndex() {
    return endIndex;
  }

  @JsonProperty("license")
  @Override
  public Optional<String> license() {
    return license;
  }

  @JsonProperty("publicationDate")
  @Override
  public Optional<GoogleTypeDate> publicationDate() {
    return publicationDate;
  }

  @JsonProperty("startIndex")
  @Override
  public Optional<Integer> startIndex() {
    return startIndex;
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
    return "Citation{"
        + "endIndex=" + endIndex + ", "
        + "license=" + license + ", "
        + "publicationDate=" + publicationDate + ", "
        + "startIndex=" + startIndex + ", "
        + "title=" + title + ", "
        + "uri=" + uri
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof Citation) {
      Citation that = (Citation) o;
      return this.endIndex.equals(that.endIndex())
          && this.license.equals(that.license())
          && this.publicationDate.equals(that.publicationDate())
          && this.startIndex.equals(that.startIndex())
          && this.title.equals(that.title())
          && this.uri.equals(that.uri());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= endIndex.hashCode();
    h$ *= 1000003;
    h$ ^= license.hashCode();
    h$ *= 1000003;
    h$ ^= publicationDate.hashCode();
    h$ *= 1000003;
    h$ ^= startIndex.hashCode();
    h$ *= 1000003;
    h$ ^= title.hashCode();
    h$ *= 1000003;
    h$ ^= uri.hashCode();
    return h$;
  }

  @Override
  public Citation.Builder toBuilder() {
    return new AutoValue_Citation.Builder(this);
  }

  static final class Builder extends Citation.Builder {
    private Optional<Integer> endIndex = Optional.empty();
    private Optional<String> license = Optional.empty();
    private Optional<GoogleTypeDate> publicationDate = Optional.empty();
    private Optional<Integer> startIndex = Optional.empty();
    private Optional<String> title = Optional.empty();
    private Optional<String> uri = Optional.empty();
    Builder() {
    }
    Builder(Citation source) {
      this.endIndex = source.endIndex();
      this.license = source.license();
      this.publicationDate = source.publicationDate();
      this.startIndex = source.startIndex();
      this.title = source.title();
      this.uri = source.uri();
    }
    @Override
    public Citation.Builder endIndex(Integer endIndex) {
      this.endIndex = Optional.of(endIndex);
      return this;
    }
    @Override
    public Citation.Builder license(String license) {
      this.license = Optional.of(license);
      return this;
    }
    @Override
    public Citation.Builder publicationDate(GoogleTypeDate publicationDate) {
      this.publicationDate = Optional.of(publicationDate);
      return this;
    }
    @Override
    public Citation.Builder startIndex(Integer startIndex) {
      this.startIndex = Optional.of(startIndex);
      return this;
    }
    @Override
    public Citation.Builder title(String title) {
      this.title = Optional.of(title);
      return this;
    }
    @Override
    public Citation.Builder uri(String uri) {
      this.uri = Optional.of(uri);
      return this;
    }
    @Override
    public Citation build() {
      return new AutoValue_Citation(
          this.endIndex,
          this.license,
          this.publicationDate,
          this.startIndex,
          this.title,
          this.uri);
    }
  }

}
