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
import java.util.Map;
import java.util.Optional;

/** None */
@GenerateTypeAdapter
@AutoValue
public abstract class TestTableItem extends JsonSerializable {
  /** The name of the test. This is used to derive the replay id. */
  @SerializedName("name")
  public abstract Optional<String> name();

  /** The parameters to the test. Use pydantic models. */
  @SerializedName("parameters")
  public abstract Optional<Map<String, Object>> parameters();

  /** Expects an exception for MLDev matching the string. */
  @SerializedName("exceptionIfMldev")
  public abstract Optional<String> exceptionIfMldev();

  /** Expects an exception for Vertex matching the string. */
  @SerializedName("exceptionIfVertex")
  public abstract Optional<String> exceptionIfVertex();

  /** Use if you don't want to use the default replay id which is derived from the test name. */
  @SerializedName("overrideReplayId")
  public abstract Optional<String> overrideReplayId();

  /**
   * True if the parameters contain an unsupported union type. This test will be skipped for
   * languages that do not support the union type.
   */
  @SerializedName("hasUnion")
  public abstract Optional<Boolean> hasUnion();

  /**
   * When set to a reason string, this test will be skipped in the API mode. Use this flag for tests
   * that can not be reproduced with the real API. E.g. a test that deletes a resource.
   */
  @SerializedName("skipInApiMode")
  public abstract Optional<String> skipInApiMode();

  /** Instantiates a builder for TestTableItem. */
  public static Builder builder() {
    return new AutoValue_TestTableItem.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for TestTableItem. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("name")
    public abstract Builder name(String name);

    @SerializedName("name")
    abstract Builder name(Optional<String> name);

    @SerializedName("parameters")
    public abstract Builder parameters(Map<String, Object> parameters);

    @SerializedName("parameters")
    abstract Builder parameters(Optional<Map<String, Object>> parameters);

    @SerializedName("exceptionIfMldev")
    public abstract Builder exceptionIfMldev(String exceptionIfMldev);

    @SerializedName("exceptionIfMldev")
    abstract Builder exceptionIfMldev(Optional<String> exceptionIfMldev);

    @SerializedName("exceptionIfVertex")
    public abstract Builder exceptionIfVertex(String exceptionIfVertex);

    @SerializedName("exceptionIfVertex")
    abstract Builder exceptionIfVertex(Optional<String> exceptionIfVertex);

    @SerializedName("overrideReplayId")
    public abstract Builder overrideReplayId(String overrideReplayId);

    @SerializedName("overrideReplayId")
    abstract Builder overrideReplayId(Optional<String> overrideReplayId);

    @SerializedName("hasUnion")
    public abstract Builder hasUnion(boolean hasUnion);

    @SerializedName("hasUnion")
    abstract Builder hasUnion(Optional<Boolean> hasUnion);

    @SerializedName("skipInApiMode")
    public abstract Builder skipInApiMode(String skipInApiMode);

    @SerializedName("skipInApiMode")
    abstract Builder skipInApiMode(Optional<String> skipInApiMode);

    public abstract TestTableItem build();
  }

  /** Deserializes a JSON string to a TestTableItem object. */
  public static TestTableItem fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, TestTableItem.class);
  }
}
