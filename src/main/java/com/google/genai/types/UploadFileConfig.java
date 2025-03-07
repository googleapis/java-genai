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

/** Used to override the default configuration. */
@GenerateTypeAdapter
@AutoValue
public abstract class UploadFileConfig extends JsonSerializable {
  /**
   * The name of the file in the destination (e.g., 'files/sample-image'. If not provided one will
   * be generated.
   */
  @SerializedName("name")
  public abstract Optional<String> name();

  /**
   * mime_type: The MIME type of the file. If not provided, it will be inferred from the file
   * extension.
   */
  @SerializedName("mimeType")
  public abstract Optional<String> mimeType();

  /** Optional display name of the file. */
  @SerializedName("displayName")
  public abstract Optional<String> displayName();

  /** Instantiates a builder for UploadFileConfig. */
  public static Builder builder() {
    return new AutoValue_UploadFileConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for UploadFileConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("name")
    public abstract Builder name(String name);

    @SerializedName("name")
    abstract Builder name(Optional<String> name);

    @SerializedName("mimeType")
    public abstract Builder mimeType(String mimeType);

    @SerializedName("mimeType")
    abstract Builder mimeType(Optional<String> mimeType);

    @SerializedName("displayName")
    public abstract Builder displayName(String displayName);

    @SerializedName("displayName")
    abstract Builder displayName(Optional<String> displayName);

    public abstract UploadFileConfig build();
  }

  /** Deserializes a JSON string to a UploadFileConfig object. */
  public static UploadFileConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, UploadFileConfig.class);
  }
}
