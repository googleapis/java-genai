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

import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.util.List;
import java.util.Optional;

/** None */
@GenerateTypeAdapter
@AutoValue
public abstract class TestTableFile extends JsonSerializable {
  /** */
  @SerializedName("comment")
  public abstract Optional<String> comment();

  /** */
  @SerializedName("testMethod")
  public abstract Optional<String> testMethod();

  /** */
  @SerializedName("parameterNames")
  public abstract Optional<List<String>> parameterNames();

  /** */
  @SerializedName("testTable")
  public abstract Optional<List<TestTableItem>> testTable();

  /** Instantiates a builder for TestTableFile. */
  public static Builder builder() {
    return new AutoValue_TestTableFile.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for TestTableFile. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("comment")
    public abstract Builder comment(String comment);

    @SerializedName("comment")
    abstract Builder comment(Optional<String> comment);

    @SerializedName("testMethod")
    public abstract Builder testMethod(String testMethod);

    @SerializedName("testMethod")
    abstract Builder testMethod(Optional<String> testMethod);

    @SerializedName("parameterNames")
    public abstract Builder parameterNames(List<String> parameterNames);

    @SerializedName("parameterNames")
    abstract Builder parameterNames(Optional<List<String>> parameterNames);

    @SerializedName("testTable")
    public abstract Builder testTable(List<TestTableItem> testTable);

    @SerializedName("testTable")
    abstract Builder testTable(Optional<List<TestTableItem>> testTable);

    public abstract TestTableFile build();
  }

  /** Deserializes a JSON string to a TestTableFile object. */
  public static TestTableFile fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, TestTableFile.class);
  }
}
