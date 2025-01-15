// Auto-generated code. Do not edit.

package com.google.genai.datatypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.List;
import java.util.Optional;

/** Contains the multi-part content of a message. */
@AutoValue
@JsonDeserialize(builder = AutoValue_Content.Builder.class)
public abstract class Content extends JsonSerializable {
  /**
   * List of parts that constitute a single message. Each part may have a different IANA MIME type.
   */
  public abstract Optional<List<Part>> getParts();

  /**
   * Optional. The producer of the content. Must be either 'user' or 'model'. Useful to set for
   * multi-turn conversations, otherwise can be left blank or unset. If role is not specified, SDK
   * will determine the role.
   */
  public abstract Optional<String> getRole();

  /** Instantiates a builder for Content. */
  public static Builder builder() {
    return new AutoValue_Content.Builder();
  }

  /** Builder for Content. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("parts")
    public abstract Builder setParts(List<Part> parts);

    @JsonProperty("role")
    public abstract Builder setRole(String role);

    public abstract Content build();
  }

  /** Serializes the Content object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a Content object. */
  public static Content fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Content.class);
  }
}
