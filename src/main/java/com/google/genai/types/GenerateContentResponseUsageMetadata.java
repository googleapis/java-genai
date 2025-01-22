// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Usage metadata about response(s). */
@AutoValue
@JsonDeserialize(builder = AutoValue_GenerateContentResponseUsageMetadata.Builder.class)
public abstract class GenerateContentResponseUsageMetadata extends JsonSerializable {
  /** Output only. Number of tokens in the cached part in the input (the cached content). */
  public abstract Optional<Integer> getCachedContentTokenCount();

  /** Number of tokens in the response(s). */
  public abstract Optional<Integer> getCandidatesTokenCount();

  /**
   * Number of tokens in the request. When `cached_content` is set, this is still the total
   * effective prompt size meaning this includes the number of tokens in the cached content.
   */
  public abstract Optional<Integer> getPromptTokenCount();

  /** Total token count for prompt and response candidates. */
  public abstract Optional<Integer> getTotalTokenCount();

  /** Instantiates a builder for GenerateContentResponseUsageMetadata. */
  public static Builder builder() {
    return new AutoValue_GenerateContentResponseUsageMetadata.Builder();
  }

  /** Builder for GenerateContentResponseUsageMetadata. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("cachedContentTokenCount")
    public abstract Builder setCachedContentTokenCount(Integer cachedContentTokenCount);

    @JsonProperty("candidatesTokenCount")
    public abstract Builder setCandidatesTokenCount(Integer candidatesTokenCount);

    @JsonProperty("promptTokenCount")
    public abstract Builder setPromptTokenCount(Integer promptTokenCount);

    @JsonProperty("totalTokenCount")
    public abstract Builder setTotalTokenCount(Integer totalTokenCount);

    public abstract GenerateContentResponseUsageMetadata build();
  }

  /** Serializes the GenerateContentResponseUsageMetadata object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a GenerateContentResponseUsageMetadata object. */
  public static GenerateContentResponseUsageMetadata fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerateContentResponseUsageMetadata.class);
  }
}
