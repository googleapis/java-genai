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

/** Safety settings. */
@GenerateTypeAdapter
@AutoValue
public abstract class SafetySetting extends JsonSerializable {
  /** Determines if the harm block method uses probability or probability and severity scores. */
  @SerializedName("method")
  public abstract Optional<String> method();

  /** Required. Harm category. */
  @SerializedName("category")
  public abstract Optional<String> category();

  /** Required. The harm block threshold. */
  @SerializedName("threshold")
  public abstract Optional<String> threshold();

  /** Instantiates a builder for SafetySetting. */
  public static Builder builder() {
    return new AutoValue_SafetySetting.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for SafetySetting. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("method")
    public abstract Builder method(String method);

    @SerializedName("method")
    abstract Builder method(Optional<String> method);

    @SerializedName("category")
    public abstract Builder category(String category);

    @SerializedName("category")
    abstract Builder category(Optional<String> category);

    @SerializedName("threshold")
    public abstract Builder threshold(String threshold);

    @SerializedName("threshold")
    abstract Builder threshold(Optional<String> threshold);

    public abstract SafetySetting build();
  }

  /** Deserializes a JSON string to a SafetySetting object. */
  public static SafetySetting fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SafetySetting.class);
  }
}
