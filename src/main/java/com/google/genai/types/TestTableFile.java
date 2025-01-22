// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.List;
import java.util.Optional;

/** None */
@AutoValue
@JsonDeserialize(builder = AutoValue_TestTableFile.Builder.class)
public abstract class TestTableFile extends JsonSerializable {
  /** */
  public abstract Optional<String> getComment();

  /** */
  public abstract Optional<String> getTestMethod();

  /** */
  public abstract Optional<List<String>> getParameterNames();

  /** */
  public abstract Optional<List<TestTableItem>> getTestTable();

  /** Instantiates a builder for TestTableFile. */
  public static Builder builder() {
    return new AutoValue_TestTableFile.Builder();
  }

  /** Builder for TestTableFile. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("comment")
    public abstract Builder setComment(String comment);

    @JsonProperty("testMethod")
    public abstract Builder setTestMethod(String testMethod);

    @JsonProperty("parameterNames")
    public abstract Builder setParameterNames(List<String> parameterNames);

    @JsonProperty("testTable")
    public abstract Builder setTestTable(List<TestTableItem> testTable);

    public abstract TestTableFile build();
  }

  /** Serializes the TestTableFile object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a TestTableFile object. */
  public static TestTableFile fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, TestTableFile.class);
  }
}
