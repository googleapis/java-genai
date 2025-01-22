// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/**
 * Retrieve from Vertex AI Search datastore for grounding. See
 * https://cloud.google.com/products/agent-builder
 */
@AutoValue
@JsonDeserialize(builder = AutoValue_VertexAISearch.Builder.class)
public abstract class VertexAISearch extends JsonSerializable {
  /**
   * Required. Fully-qualified Vertex AI Search data store resource ID. Format:
   * `projects/{project}/locations/{location}/collections/{collection}/dataStores/{dataStore}`
   */
  public abstract Optional<String> getDatastore();

  /** Instantiates a builder for VertexAISearch. */
  public static Builder builder() {
    return new AutoValue_VertexAISearch.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for VertexAISearch. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("datastore")
    public abstract Builder setDatastore(String datastore);

    public abstract VertexAISearch build();
  }

  /** Deserializes a JSON string to a VertexAISearch object. */
  public static VertexAISearch fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, VertexAISearch.class);
  }
}
