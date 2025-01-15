// Auto-generated code. Do not edit.

package com.google.genai.datatypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Map;
import java.util.Optional;

/** None */
@AutoValue
@JsonDeserialize(builder = AutoValue_TestTableItem.Builder.class)
public abstract class TestTableItem extends JsonSerializable {
  /** The name of the test. This is used to derive the replay id. */
  public abstract Optional<String> getName();

  /** The parameters to the test. Use pydantic models. */
  public abstract Optional<Map<String, Object>> getParameters();

  /** Expects an exception for MLDev matching the string. */
  public abstract Optional<String> getExceptionIfMldev();

  /** Expects an exception for Vertex matching the string. */
  public abstract Optional<String> getExceptionIfVertex();

  /** Use if you don't want to use the default replay id which is derived from the test name. */
  public abstract Optional<String> getOverrideReplayId();

  /**
   * True if the parameters contain an unsupported union type. This test will be skipped for
   * languages that do not support the union type.
   */
  public abstract Optional<Boolean> getHasUnion();

  /**
   * When set to a reason string, this test will be skipped in the API mode. Use this flag for tests
   * that can not be reproduced with the real API. E.g. a test that deletes a resource.
   */
  public abstract Optional<String> getSkipInApiMode();

  /** Instantiates a builder for TestTableItem. */
  public static Builder builder() {
    return new AutoValue_TestTableItem.Builder();
  }

  /** Builder for TestTableItem. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("name")
    public abstract Builder setName(String name);

    @JsonProperty("parameters")
    public abstract Builder setParameters(Map<String, Object> parameters);

    @JsonProperty("exceptionIfMldev")
    public abstract Builder setExceptionIfMldev(String exceptionIfMldev);

    @JsonProperty("exceptionIfVertex")
    public abstract Builder setExceptionIfVertex(String exceptionIfVertex);

    @JsonProperty("overrideReplayId")
    public abstract Builder setOverrideReplayId(String overrideReplayId);

    @JsonProperty("hasUnion")
    public abstract Builder setHasUnion(boolean hasUnion);

    @JsonProperty("skipInApiMode")
    public abstract Builder setSkipInApiMode(String skipInApiMode);

    public abstract TestTableItem build();
  }

  /** Serializes the TestTableItem object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a TestTableItem object. */
  public static TestTableItem fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, TestTableItem.class);
  }
}
