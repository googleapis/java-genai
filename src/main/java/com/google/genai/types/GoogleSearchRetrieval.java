// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Tool to retrieve public web data for grounding, powered by Google. */
@AutoValue
@JsonDeserialize(builder = AutoValue_GoogleSearchRetrieval.Builder.class)
public abstract class GoogleSearchRetrieval extends JsonSerializable {
  /** Specifies the dynamic retrieval configuration for the given source. */
  public abstract Optional<DynamicRetrievalConfig> getDynamicRetrievalConfig();

  /** Instantiates a builder for GoogleSearchRetrieval. */
  public static Builder builder() {
    return new AutoValue_GoogleSearchRetrieval.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GoogleSearchRetrieval. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("dynamicRetrievalConfig")
    public abstract Builder setDynamicRetrievalConfig(
        DynamicRetrievalConfig dynamicRetrievalConfig);

    public abstract GoogleSearchRetrieval build();
  }

  /** Deserializes a JSON string to a GoogleSearchRetrieval object. */
  public static GoogleSearchRetrieval fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GoogleSearchRetrieval.class);
  }
}
