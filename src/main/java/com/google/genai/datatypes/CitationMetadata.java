// Auto-generated code. Do not edit.

package com.google.genai.datatypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.List;
import java.util.Optional;

/** Class for citation information when the model quotes another source. */
@AutoValue
@JsonDeserialize(builder = AutoValue_CitationMetadata.Builder.class)
public abstract class CitationMetadata extends JsonSerializable {
  /**
   * Contains citation information when the model directly quotes, at length, from another source.
   * Can include traditional websites and code repositories.
   */
  public abstract Optional<List<Citation>> getCitations();

  /** Instantiates a builder for CitationMetadata. */
  public static Builder builder() {
    return new AutoValue_CitationMetadata.Builder();
  }

  /** Builder for CitationMetadata. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("citations")
    public abstract Builder setCitations(List<Citation> citations);

    public abstract CitationMetadata build();
  }

  /** Serializes the CitationMetadata object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a CitationMetadata object. */
  public static CitationMetadata fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, CitationMetadata.class);
  }
}
