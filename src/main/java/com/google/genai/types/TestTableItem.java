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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** None */
@AutoValue
@JsonDeserialize(builder = TestTableItem.Builder.class)
public abstract class TestTableItem extends JsonSerializable {
  /** The name of the test. This is used to derive the replay id. */
  @JsonProperty("name")
  public abstract Optional<String> name();

  /** The parameters to the test. Use pydantic models. */
  @JsonProperty("parameters")
  public abstract Optional<Map<String, Object>> parameters();

  /** Expects an exception for MLDev matching the string. */
  @JsonProperty("exceptionIfMldev")
  public abstract Optional<String> exceptionIfMldev();

  /** Expects an exception for Vertex matching the string. */
  @JsonProperty("exceptionIfVertex")
  public abstract Optional<String> exceptionIfVertex();

  /** Use if you don't want to use the default replay id which is derived from the test name. */
  @JsonProperty("overrideReplayId")
  public abstract Optional<String> overrideReplayId();

  /**
   * True if the parameters contain an unsupported union type. This test will be skipped for
   * languages that do not support the union type.
   */
  @JsonProperty("hasUnion")
  public abstract Optional<Boolean> hasUnion();

  /**
   * When set to a reason string, this test will be skipped in the API mode. Use this flag for tests
   * that can not be reproduced with the real API. E.g. a test that deletes a resource.
   */
  @JsonProperty("skipInApiMode")
  public abstract Optional<String> skipInApiMode();

  /**
   * Keys to ignore when comparing the request and response. This is useful for tests that are not
   * deterministic.
   */
  @JsonProperty("ignoreKeys")
  public abstract Optional<List<String>> ignoreKeys();

  /** Instantiates a builder for TestTableItem. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_TestTableItem.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for TestTableItem. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `TestTableItem.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_TestTableItem.Builder();
    }

    /**
     * Setter for name.
     *
     * <p>name: The name of the test. This is used to derive the replay id.
     */
    @JsonProperty("name")
    public abstract Builder name(String name);

    /**
     * Setter for parameters.
     *
     * <p>parameters: The parameters to the test. Use pydantic models.
     */
    @JsonProperty("parameters")
    public abstract Builder parameters(Map<String, Object> parameters);

    /**
     * Setter for exceptionIfMldev.
     *
     * <p>exceptionIfMldev: Expects an exception for MLDev matching the string.
     */
    @JsonProperty("exceptionIfMldev")
    public abstract Builder exceptionIfMldev(String exceptionIfMldev);

    /**
     * Setter for exceptionIfVertex.
     *
     * <p>exceptionIfVertex: Expects an exception for Vertex matching the string.
     */
    @JsonProperty("exceptionIfVertex")
    public abstract Builder exceptionIfVertex(String exceptionIfVertex);

    /**
     * Setter for overrideReplayId.
     *
     * <p>overrideReplayId: Use if you don't want to use the default replay id which is derived from
     * the test name.
     */
    @JsonProperty("overrideReplayId")
    public abstract Builder overrideReplayId(String overrideReplayId);

    /**
     * Setter for hasUnion.
     *
     * <p>hasUnion: True if the parameters contain an unsupported union type. This test will be
     * skipped for languages that do not support the union type.
     */
    @JsonProperty("hasUnion")
    public abstract Builder hasUnion(boolean hasUnion);

    /**
     * Setter for skipInApiMode.
     *
     * <p>skipInApiMode: When set to a reason string, this test will be skipped in the API mode. Use
     * this flag for tests that can not be reproduced with the real API. E.g. a test that deletes a
     * resource.
     */
    @JsonProperty("skipInApiMode")
    public abstract Builder skipInApiMode(String skipInApiMode);

    /**
     * Setter for ignoreKeys.
     *
     * <p>ignoreKeys: Keys to ignore when comparing the request and response. This is useful for
     * tests that are not deterministic.
     */
    @JsonProperty("ignoreKeys")
    public abstract Builder ignoreKeys(List<String> ignoreKeys);

    /**
     * Setter for ignoreKeys.
     *
     * <p>ignoreKeys: Keys to ignore when comparing the request and response. This is useful for
     * tests that are not deterministic.
     */
    public Builder ignoreKeys(String... ignoreKeys) {
      return ignoreKeys(Arrays.asList(ignoreKeys));
    }

    public abstract TestTableItem build();
  }

  /** Deserializes a JSON string to a TestTableItem object. */
  @ExcludeFromGeneratedCoverageReport
  public static TestTableItem fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, TestTableItem.class);
  }
}
