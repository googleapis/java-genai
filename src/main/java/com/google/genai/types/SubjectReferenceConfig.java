// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Configuration for a Subject reference image. */
@AutoValue
@JsonDeserialize(builder = AutoValue_SubjectReferenceConfig.Builder.class)
public abstract class SubjectReferenceConfig extends JsonSerializable {
  /** The subject type of a subject reference image. */
  public abstract Optional<String> getSubjectType();

  /** Subject description for the image. */
  public abstract Optional<String> getSubjectDescription();

  /** Instantiates a builder for SubjectReferenceConfig. */
  public static Builder builder() {
    return new AutoValue_SubjectReferenceConfig.Builder();
  }

  /** Builder for SubjectReferenceConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("subjectType")
    public abstract Builder setSubjectType(String subjectType);

    @JsonProperty("subjectDescription")
    public abstract Builder setSubjectDescription(String subjectDescription);

    public abstract SubjectReferenceConfig build();
  }

  /** Serializes the SubjectReferenceConfig object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a SubjectReferenceConfig object. */
  public static SubjectReferenceConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SubjectReferenceConfig.class);
  }
}
