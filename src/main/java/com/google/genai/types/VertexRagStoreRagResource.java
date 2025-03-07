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

/** The definition of the Rag resource. */
@GenerateTypeAdapter
@AutoValue
public abstract class VertexRagStoreRagResource extends JsonSerializable {
  /**
   * Optional. RagCorpora resource name. Format:
   * `projects/{project}/locations/{location}/ragCorpora/{rag_corpus}`
   */
  @SerializedName("ragCorpus")
  public abstract Optional<String> ragCorpus();

  /** Optional. rag_file_id. The files should be in the same rag_corpus set in rag_corpus field. */
  @SerializedName("ragFileIds")
  public abstract Optional<List<String>> ragFileIds();

  /** Instantiates a builder for VertexRagStoreRagResource. */
  public static Builder builder() {
    return new AutoValue_VertexRagStoreRagResource.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for VertexRagStoreRagResource. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("ragCorpus")
    public abstract Builder ragCorpus(String ragCorpus);

    @SerializedName("ragCorpus")
    abstract Builder ragCorpus(Optional<String> ragCorpus);

    @SerializedName("ragFileIds")
    public abstract Builder ragFileIds(List<String> ragFileIds);

    @SerializedName("ragFileIds")
    abstract Builder ragFileIds(Optional<List<String>> ragFileIds);

    public abstract VertexRagStoreRagResource build();
  }

  /** Deserializes a JSON string to a VertexRagStoreRagResource object. */
  public static VertexRagStoreRagResource fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, VertexRagStoreRagResource.class);
  }
}
