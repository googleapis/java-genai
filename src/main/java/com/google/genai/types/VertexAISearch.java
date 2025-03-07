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

import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.util.Optional;

/**
 * Retrieve from Vertex AI Search datastore for grounding. See
 * https://cloud.google.com/products/agent-builder
 */
@GenerateTypeAdapter
@AutoValue
public abstract class VertexAISearch extends JsonSerializable {
  /**
   * Required. Fully-qualified Vertex AI Search data store resource ID. Format:
   * `projects/{project}/locations/{location}/collections/{collection}/dataStores/{dataStore}`
   */
  @SerializedName("datastore")
  public abstract Optional<String> datastore();

  /** Instantiates a builder for VertexAISearch. */
  public static Builder builder() {
    return new AutoValue_VertexAISearch.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for VertexAISearch. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("datastore")
    public abstract Builder datastore(String datastore);

    @SerializedName("datastore")
    abstract Builder datastore(Optional<String> datastore);

    public abstract VertexAISearch build();
  }

  /** Deserializes a JSON string to a VertexAISearch object. */
  public static VertexAISearch fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, VertexAISearch.class);
  }
}
