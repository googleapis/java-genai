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

/** URI based data. */
@GenerateTypeAdapter
@AutoValue
public abstract class FileData extends JsonSerializable {
  /** Required. URI. */
  @SerializedName("fileUri")
  public abstract Optional<String> fileUri();

  /** Required. The IANA standard MIME type of the source data. */
  @SerializedName("mimeType")
  public abstract Optional<String> mimeType();

  /** Instantiates a builder for FileData. */
  public static Builder builder() {
    return new AutoValue_FileData.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for FileData. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("fileUri")
    public abstract Builder fileUri(String fileUri);

    @SerializedName("fileUri")
    abstract Builder fileUri(Optional<String> fileUri);

    @SerializedName("mimeType")
    public abstract Builder mimeType(String mimeType);

    @SerializedName("mimeType")
    abstract Builder mimeType(Optional<String> mimeType);

    public abstract FileData build();
  }

  /** Deserializes a JSON string to a FileData object. */
  public static FileData fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, FileData.class);
  }
}
