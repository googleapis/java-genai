// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;

/** Tool to support Google Search in Model. Powered by Google. */
@AutoValue
@JsonDeserialize(builder = AutoValue_GoogleSearch.Builder.class)
public abstract class GoogleSearch extends JsonSerializable {
  /** Instantiates a builder for GoogleSearch. */
  public static Builder builder() {
    return new AutoValue_GoogleSearch.Builder();
  }

  /** Builder for GoogleSearch. */
  @AutoValue.Builder
  public abstract static class Builder {
    public abstract GoogleSearch build();
  }

  /** Serializes the GoogleSearch object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a GoogleSearch object. */
  public static GoogleSearch fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GoogleSearch.class);
  }
}
