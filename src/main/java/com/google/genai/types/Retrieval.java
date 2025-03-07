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

/** Defines a retrieval tool that model can call to access external knowledge. */
@GenerateTypeAdapter
@AutoValue
public abstract class Retrieval extends JsonSerializable {
  /** Optional. Deprecated. This option is no longer supported. */
  @SerializedName("disableAttribution")
  public abstract Optional<Boolean> disableAttribution();

  /** Set to use data source powered by Vertex AI Search. */
  @SerializedName("vertexAiSearch")
  public abstract Optional<VertexAISearch> vertexAiSearch();

  /**
   * Set to use data source powered by Vertex RAG store. User data is uploaded via the
   * VertexRagDataService.
   */
  @SerializedName("vertexRagStore")
  public abstract Optional<VertexRagStore> vertexRagStore();

  /** Instantiates a builder for Retrieval. */
  public static Builder builder() {
    return new AutoValue_Retrieval.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for Retrieval. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("disableAttribution")
    public abstract Builder disableAttribution(boolean disableAttribution);

    @SerializedName("disableAttribution")
    abstract Builder disableAttribution(Optional<Boolean> disableAttribution);

    @SerializedName("vertexAiSearch")
    public abstract Builder vertexAiSearch(VertexAISearch vertexAiSearch);

    @SerializedName("vertexAiSearch")
    abstract Builder vertexAiSearch(Optional<VertexAISearch> vertexAiSearch);

    @SerializedName("vertexRagStore")
    public abstract Builder vertexRagStore(VertexRagStore vertexRagStore);

    @SerializedName("vertexRagStore")
    abstract Builder vertexRagStore(Optional<VertexRagStore> vertexRagStore);

    public abstract Retrieval build();
  }

  /** Deserializes a JSON string to a Retrieval object. */
  public static Retrieval fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Retrieval.class);
  }
}
