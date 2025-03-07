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
import java.util.List;
import java.util.Optional;

/** Retrieve from Vertex RAG Store for grounding. */
@GenerateTypeAdapter
@AutoValue
public abstract class VertexRagStore extends JsonSerializable {
  /** Optional. Deprecated. Please use rag_resources instead. */
  @SerializedName("ragCorpora")
  public abstract Optional<List<String>> ragCorpora();

  /**
   * Optional. The representation of the rag source. It can be used to specify corpus only or
   * ragfiles. Currently only support one corpus or multiple files from one corpus. In the future we
   * may open up multiple corpora support.
   */
  @SerializedName("ragResources")
  public abstract Optional<List<VertexRagStoreRagResource>> ragResources();

  /** Optional. Number of top k results to return from the selected corpora. */
  @SerializedName("similarityTopK")
  public abstract Optional<Integer> similarityTopK();

  /** Optional. Only return results with vector distance smaller than the threshold. */
  @SerializedName("vectorDistanceThreshold")
  public abstract Optional<Double> vectorDistanceThreshold();

  /** Instantiates a builder for VertexRagStore. */
  public static Builder builder() {
    return new AutoValue_VertexRagStore.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for VertexRagStore. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("ragCorpora")
    public abstract Builder ragCorpora(List<String> ragCorpora);

    @SerializedName("ragCorpora")
    abstract Builder ragCorpora(Optional<List<String>> ragCorpora);

    @SerializedName("ragResources")
    public abstract Builder ragResources(List<VertexRagStoreRagResource> ragResources);

    @SerializedName("ragResources")
    abstract Builder ragResources(Optional<List<VertexRagStoreRagResource>> ragResources);

    @SerializedName("similarityTopK")
    public abstract Builder similarityTopK(Integer similarityTopK);

    @SerializedName("similarityTopK")
    abstract Builder similarityTopK(Optional<Integer> similarityTopK);

    @SerializedName("vectorDistanceThreshold")
    public abstract Builder vectorDistanceThreshold(Double vectorDistanceThreshold);

    @SerializedName("vectorDistanceThreshold")
    abstract Builder vectorDistanceThreshold(Optional<Double> vectorDistanceThreshold);

    public abstract VertexRagStore build();
  }

  /** Deserializes a JSON string to a VertexRagStore object. */
  public static VertexRagStore fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, VertexRagStore.class);
  }
}
