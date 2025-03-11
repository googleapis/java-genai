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

/** Tool to retrieve public web data for grounding, powered by Google. */
@GenerateTypeAdapter
@AutoValue
public abstract class GoogleSearchRetrieval extends JsonSerializable {
  /** Specifies the dynamic retrieval configuration for the given source. */
  @SerializedName("dynamicRetrievalConfig")
  public abstract Optional<DynamicRetrievalConfig> dynamicRetrievalConfig();

  /** Instantiates a builder for GoogleSearchRetrieval. */
  public static Builder builder() {
    return new AutoValue_GoogleSearchRetrieval.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for GoogleSearchRetrieval. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("dynamicRetrievalConfig")
    public abstract Builder dynamicRetrievalConfig(DynamicRetrievalConfig dynamicRetrievalConfig);

    @SerializedName("dynamicRetrievalConfig")
    abstract Builder dynamicRetrievalConfig(
        Optional<DynamicRetrievalConfig> dynamicRetrievalConfig);

    public abstract GoogleSearchRetrieval build();
  }

  /** Deserializes a JSON string to a GoogleSearchRetrieval object. */
  public static GoogleSearchRetrieval fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GoogleSearchRetrieval.class);
  }
}
