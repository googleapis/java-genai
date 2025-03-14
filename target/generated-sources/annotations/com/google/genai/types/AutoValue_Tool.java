package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_Tool extends Tool {

  private final Optional<List<FunctionDeclaration>> functionDeclarations;

  private final Optional<Retrieval> retrieval;

  private final Optional<GoogleSearch> googleSearch;

  private final Optional<GoogleSearchRetrieval> googleSearchRetrieval;

  private final Optional<ToolCodeExecution> codeExecution;

  private AutoValue_Tool(
      Optional<List<FunctionDeclaration>> functionDeclarations,
      Optional<Retrieval> retrieval,
      Optional<GoogleSearch> googleSearch,
      Optional<GoogleSearchRetrieval> googleSearchRetrieval,
      Optional<ToolCodeExecution> codeExecution) {
    this.functionDeclarations = functionDeclarations;
    this.retrieval = retrieval;
    this.googleSearch = googleSearch;
    this.googleSearchRetrieval = googleSearchRetrieval;
    this.codeExecution = codeExecution;
  }

  @JsonProperty("functionDeclarations")
  @Override
  public Optional<List<FunctionDeclaration>> functionDeclarations() {
    return functionDeclarations;
  }

  @JsonProperty("retrieval")
  @Override
  public Optional<Retrieval> retrieval() {
    return retrieval;
  }

  @JsonProperty("googleSearch")
  @Override
  public Optional<GoogleSearch> googleSearch() {
    return googleSearch;
  }

  @JsonProperty("googleSearchRetrieval")
  @Override
  public Optional<GoogleSearchRetrieval> googleSearchRetrieval() {
    return googleSearchRetrieval;
  }

  @JsonProperty("codeExecution")
  @Override
  public Optional<ToolCodeExecution> codeExecution() {
    return codeExecution;
  }

  @Override
  public String toString() {
    return "Tool{"
        + "functionDeclarations=" + functionDeclarations + ", "
        + "retrieval=" + retrieval + ", "
        + "googleSearch=" + googleSearch + ", "
        + "googleSearchRetrieval=" + googleSearchRetrieval + ", "
        + "codeExecution=" + codeExecution
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof Tool) {
      Tool that = (Tool) o;
      return this.functionDeclarations.equals(that.functionDeclarations())
          && this.retrieval.equals(that.retrieval())
          && this.googleSearch.equals(that.googleSearch())
          && this.googleSearchRetrieval.equals(that.googleSearchRetrieval())
          && this.codeExecution.equals(that.codeExecution());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= functionDeclarations.hashCode();
    h$ *= 1000003;
    h$ ^= retrieval.hashCode();
    h$ *= 1000003;
    h$ ^= googleSearch.hashCode();
    h$ *= 1000003;
    h$ ^= googleSearchRetrieval.hashCode();
    h$ *= 1000003;
    h$ ^= codeExecution.hashCode();
    return h$;
  }

  @Override
  public Tool.Builder toBuilder() {
    return new AutoValue_Tool.Builder(this);
  }

  static final class Builder extends Tool.Builder {
    private Optional<List<FunctionDeclaration>> functionDeclarations = Optional.empty();
    private Optional<Retrieval> retrieval = Optional.empty();
    private Optional<GoogleSearch> googleSearch = Optional.empty();
    private Optional<GoogleSearchRetrieval> googleSearchRetrieval = Optional.empty();
    private Optional<ToolCodeExecution> codeExecution = Optional.empty();
    Builder() {
    }
    Builder(Tool source) {
      this.functionDeclarations = source.functionDeclarations();
      this.retrieval = source.retrieval();
      this.googleSearch = source.googleSearch();
      this.googleSearchRetrieval = source.googleSearchRetrieval();
      this.codeExecution = source.codeExecution();
    }
    @Override
    public Tool.Builder functionDeclarations(List<FunctionDeclaration> functionDeclarations) {
      this.functionDeclarations = Optional.of(functionDeclarations);
      return this;
    }
    @Override
    public Tool.Builder retrieval(Retrieval retrieval) {
      this.retrieval = Optional.of(retrieval);
      return this;
    }
    @Override
    public Tool.Builder googleSearch(GoogleSearch googleSearch) {
      this.googleSearch = Optional.of(googleSearch);
      return this;
    }
    @Override
    public Tool.Builder googleSearchRetrieval(GoogleSearchRetrieval googleSearchRetrieval) {
      this.googleSearchRetrieval = Optional.of(googleSearchRetrieval);
      return this;
    }
    @Override
    public Tool.Builder codeExecution(ToolCodeExecution codeExecution) {
      this.codeExecution = Optional.of(codeExecution);
      return this;
    }
    @Override
    public Tool build() {
      return new AutoValue_Tool(
          this.functionDeclarations,
          this.retrieval,
          this.googleSearch,
          this.googleSearchRetrieval,
          this.codeExecution);
    }
  }

}
