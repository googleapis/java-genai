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

/** Usage metadata about response(s). */
@GenerateTypeAdapter
@AutoValue
public abstract class GenerateContentResponseUsageMetadata extends JsonSerializable {
  /** Output only. Number of tokens in the cached part in the input (the cached content). */
  @SerializedName("cachedContentTokenCount")
  public abstract Optional<Integer> cachedContentTokenCount();

  /** Number of tokens in the response(s). */
  @SerializedName("candidatesTokenCount")
  public abstract Optional<Integer> candidatesTokenCount();

  /**
   * Number of tokens in the request. When `cached_content` is set, this is still the total
   * effective prompt size meaning this includes the number of tokens in the cached content.
   */
  @SerializedName("promptTokenCount")
  public abstract Optional<Integer> promptTokenCount();

  /** Total token count for prompt and response candidates. */
  @SerializedName("totalTokenCount")
  public abstract Optional<Integer> totalTokenCount();

  /** Instantiates a builder for GenerateContentResponseUsageMetadata. */
  public static Builder builder() {
    return new AutoValue_GenerateContentResponseUsageMetadata.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GenerateContentResponseUsageMetadata. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("cachedContentTokenCount")
    public abstract Builder cachedContentTokenCount(Integer cachedContentTokenCount);

    @SerializedName("cachedContentTokenCount")
    abstract Builder cachedContentTokenCount(Optional<Integer> cachedContentTokenCount);

    @SerializedName("candidatesTokenCount")
    public abstract Builder candidatesTokenCount(Integer candidatesTokenCount);

    @SerializedName("candidatesTokenCount")
    abstract Builder candidatesTokenCount(Optional<Integer> candidatesTokenCount);

    @SerializedName("promptTokenCount")
    public abstract Builder promptTokenCount(Integer promptTokenCount);

    @SerializedName("promptTokenCount")
    abstract Builder promptTokenCount(Optional<Integer> promptTokenCount);

    @SerializedName("totalTokenCount")
    public abstract Builder totalTokenCount(Integer totalTokenCount);

    @SerializedName("totalTokenCount")
    abstract Builder totalTokenCount(Optional<Integer> totalTokenCount);

    public abstract GenerateContentResponseUsageMetadata build();
  }

  /** Deserializes a JSON string to a GenerateContentResponseUsageMetadata object. */
  public static GenerateContentResponseUsageMetadata fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GenerateContentResponseUsageMetadata.class);
  }
}
