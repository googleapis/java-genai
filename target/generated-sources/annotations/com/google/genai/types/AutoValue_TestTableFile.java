package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_TestTableFile extends TestTableFile {

  private final Optional<String> comment;

  private final Optional<String> testMethod;

  private final Optional<List<String>> parameterNames;

  private final Optional<List<TestTableItem>> testTable;

  private AutoValue_TestTableFile(
      Optional<String> comment,
      Optional<String> testMethod,
      Optional<List<String>> parameterNames,
      Optional<List<TestTableItem>> testTable) {
    this.comment = comment;
    this.testMethod = testMethod;
    this.parameterNames = parameterNames;
    this.testTable = testTable;
  }

  @JsonProperty("comment")
  @Override
  public Optional<String> comment() {
    return comment;
  }

  @JsonProperty("testMethod")
  @Override
  public Optional<String> testMethod() {
    return testMethod;
  }

  @JsonProperty("parameterNames")
  @Override
  public Optional<List<String>> parameterNames() {
    return parameterNames;
  }

  @JsonProperty("testTable")
  @Override
  public Optional<List<TestTableItem>> testTable() {
    return testTable;
  }

  @Override
  public String toString() {
    return "TestTableFile{"
        + "comment=" + comment + ", "
        + "testMethod=" + testMethod + ", "
        + "parameterNames=" + parameterNames + ", "
        + "testTable=" + testTable
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof TestTableFile) {
      TestTableFile that = (TestTableFile) o;
      return this.comment.equals(that.comment())
          && this.testMethod.equals(that.testMethod())
          && this.parameterNames.equals(that.parameterNames())
          && this.testTable.equals(that.testTable());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= comment.hashCode();
    h$ *= 1000003;
    h$ ^= testMethod.hashCode();
    h$ *= 1000003;
    h$ ^= parameterNames.hashCode();
    h$ *= 1000003;
    h$ ^= testTable.hashCode();
    return h$;
  }

  @Override
  public TestTableFile.Builder toBuilder() {
    return new AutoValue_TestTableFile.Builder(this);
  }

  static final class Builder extends TestTableFile.Builder {
    private Optional<String> comment = Optional.empty();
    private Optional<String> testMethod = Optional.empty();
    private Optional<List<String>> parameterNames = Optional.empty();
    private Optional<List<TestTableItem>> testTable = Optional.empty();
    Builder() {
    }
    Builder(TestTableFile source) {
      this.comment = source.comment();
      this.testMethod = source.testMethod();
      this.parameterNames = source.parameterNames();
      this.testTable = source.testTable();
    }
    @Override
    public TestTableFile.Builder comment(String comment) {
      this.comment = Optional.of(comment);
      return this;
    }
    @Override
    public TestTableFile.Builder testMethod(String testMethod) {
      this.testMethod = Optional.of(testMethod);
      return this;
    }
    @Override
    public TestTableFile.Builder parameterNames(List<String> parameterNames) {
      this.parameterNames = Optional.of(parameterNames);
      return this;
    }
    @Override
    public TestTableFile.Builder testTable(List<TestTableItem> testTable) {
      this.testTable = Optional.of(testTable);
      return this;
    }
    @Override
    public TestTableFile build() {
      return new AutoValue_TestTableFile(
          this.comment,
          this.testMethod,
          this.parameterNames,
          this.testTable);
    }
  }

}
