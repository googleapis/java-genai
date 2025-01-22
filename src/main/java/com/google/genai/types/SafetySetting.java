// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Safety settings. */
@AutoValue
@JsonDeserialize(builder = AutoValue_SafetySetting.Builder.class)
public abstract class SafetySetting extends JsonSerializable {
  /** Determines if the harm block method uses probability or probability and severity scores. */
  public abstract Optional<String> getMethod();

  /** Required. Harm category. */
  public abstract Optional<String> getCategory();

  /** Required. The harm block threshold. */
  public abstract Optional<String> getThreshold();

  /** Instantiates a builder for SafetySetting. */
  public static Builder builder() {
    return new AutoValue_SafetySetting.Builder();
  }

  /** Builder for SafetySetting. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("method")
    public abstract Builder setMethod(String method);

    @JsonProperty("category")
    public abstract Builder setCategory(String category);

    @JsonProperty("threshold")
    public abstract Builder setThreshold(String threshold);

    public abstract SafetySetting build();
  }

  /** Serializes the SafetySetting object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a SafetySetting object. */
  public static SafetySetting fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SafetySetting.class);
  }
}
