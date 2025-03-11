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

import com.google.api.core.InternalApi;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.util.Optional;

/**
 * API config for UpscaleImage with fields not exposed to users.
 *
 * <p>These fields require default values sent to the API which are not intended to be modifiable or
 * exposed to users in the SDK method.
 */
@GenerateTypeAdapter
@AutoValue
@InternalApi
public abstract class UpscaleImageAPIConfig extends JsonSerializable {
  /** Whether to include a reason for filtered-out images in the response. */
  @SerializedName("includeRaiReason")
  public abstract Optional<Boolean> includeRaiReason();

  /** The image format that the output should be saved as. */
  @SerializedName("outputMimeType")
  public abstract Optional<String> outputMimeType();

  /** The level of compression if the ``output_mime_type`` is ``image/jpeg``. */
  @SerializedName("outputCompressionQuality")
  public abstract Optional<Integer> outputCompressionQuality();

  /** */
  @SerializedName("numberOfImages")
  public abstract Optional<Integer> numberOfImages();

  /** */
  @SerializedName("mode")
  public abstract Optional<String> mode();

  /** Instantiates a builder for UpscaleImageAPIConfig. */
  public static Builder builder() {
    return new AutoValue_UpscaleImageAPIConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for UpscaleImageAPIConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("includeRaiReason")
    public abstract Builder includeRaiReason(boolean includeRaiReason);

    @SerializedName("includeRaiReason")
    abstract Builder includeRaiReason(Optional<Boolean> includeRaiReason);

    @SerializedName("outputMimeType")
    public abstract Builder outputMimeType(String outputMimeType);

    @SerializedName("outputMimeType")
    abstract Builder outputMimeType(Optional<String> outputMimeType);

    @SerializedName("outputCompressionQuality")
    public abstract Builder outputCompressionQuality(Integer outputCompressionQuality);

    @SerializedName("outputCompressionQuality")
    abstract Builder outputCompressionQuality(Optional<Integer> outputCompressionQuality);

    @SerializedName("numberOfImages")
    public abstract Builder numberOfImages(Integer numberOfImages);

    @SerializedName("numberOfImages")
    abstract Builder numberOfImages(Optional<Integer> numberOfImages);

    @SerializedName("mode")
    public abstract Builder mode(String mode);

    @SerializedName("mode")
    abstract Builder mode(Optional<String> mode);

    public abstract UpscaleImageAPIConfig build();
  }

  /** Deserializes a JSON string to a UpscaleImageAPIConfig object. */
  public static UpscaleImageAPIConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, UpscaleImageAPIConfig.class);
  }
}
