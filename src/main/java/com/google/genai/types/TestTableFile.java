/*
 * Copyright 2025 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// Auto-generated code. Do not edit.

package com.google.genai.types;

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** None */
@AutoValue
@JsonDeserialize(builder = TestTableFile.Builder.class)
public abstract class TestTableFile extends JsonSerializable {
  /** */
  @JsonProperty("comment")
  public abstract Optional<String> comment();

  /** */
  @JsonProperty("testMethod")
  public abstract Optional<String> testMethod();

  /** */
  @JsonProperty("parameterNames")
  public abstract Optional<List<String>> parameterNames();

  /** */
  @JsonProperty("testTable")
  public abstract Optional<List<TestTableItem>> testTable();

  /** Instantiates a builder for TestTableFile. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_TestTableFile.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for TestTableFile. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `TestTableFile.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_TestTableFile.Builder();
    }

    /**
     * Setter for comment.
     *
     * <p>comment:
     */
    @JsonProperty("comment")
    public abstract Builder comment(String comment);

    /**
     * Setter for testMethod.
     *
     * <p>testMethod:
     */
    @JsonProperty("testMethod")
    public abstract Builder testMethod(String testMethod);

    /**
     * Setter for parameterNames.
     *
     * <p>parameterNames:
     */
    @JsonProperty("parameterNames")
    public abstract Builder parameterNames(List<String> parameterNames);

    /**
     * Setter for parameterNames.
     *
     * <p>parameterNames:
     */
    public Builder parameterNames(String... parameterNames) {
      return parameterNames(Arrays.asList(parameterNames));
    }

    /**
     * Setter for testTable.
     *
     * <p>testTable:
     */
    @JsonProperty("testTable")
    public abstract Builder testTable(List<TestTableItem> testTable);

    /**
     * Setter for testTable.
     *
     * <p>testTable:
     */
    public Builder testTable(TestTableItem... testTable) {
      return testTable(Arrays.asList(testTable));
    }

    /**
     * Setter for testTable builder.
     *
     * <p>testTable:
     */
    public Builder testTable(TestTableItem.Builder... testTableBuilders) {
      return testTable(
          Arrays.asList(testTableBuilders).stream()
              .map(TestTableItem.Builder::build)
              .collect(toImmutableList()));
    }

    public abstract TestTableFile build();
  }

  /** Deserializes a JSON string to a TestTableFile object. */
  @ExcludeFromGeneratedCoverageReport
  public static TestTableFile fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, TestTableFile.class);
  }
}
