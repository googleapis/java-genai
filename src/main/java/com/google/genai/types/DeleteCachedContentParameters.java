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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.api.core.InternalApi;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Parameters for caches.delete method. */
@AutoValue
@InternalApi
@JsonDeserialize(builder = DeleteCachedContentParameters.Builder.class)
public abstract class DeleteCachedContentParameters extends JsonSerializable {
  /** The server-generated resource name of the cached content. */
  @JsonProperty("name")
  public abstract Optional<String> name();

  /** Optional parameters for the request. */
  @JsonProperty("config")
  public abstract Optional<DeleteCachedContentConfig> config();

  /** Instantiates a builder for DeleteCachedContentParameters. */
  public static Builder builder() {
    return new AutoValue_DeleteCachedContentParameters.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for DeleteCachedContentParameters. */
  @AutoValue.Builder
  public abstract static class Builder {
    /**
     * For internal usage. Please use `DeleteCachedContentParameters.builder()` for instantiation.
     */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_DeleteCachedContentParameters.Builder();
    }

    /**
     * Setter for name.
     *
     * <p>name: The server-generated resource name of the cached content.
     */
    @JsonProperty("name")
    public abstract Builder name(String name);

    /**
     * Setter for config.
     *
     * <p>config: Optional parameters for the request.
     */
    @JsonProperty("config")
    public abstract Builder config(DeleteCachedContentConfig config);

    /**
     * Setter for config builder.
     *
     * <p>config: Optional parameters for the request.
     */
    public Builder config(DeleteCachedContentConfig.Builder configBuilder) {
      return config(configBuilder.build());
    }

    public abstract DeleteCachedContentParameters build();
  }

  /** Deserializes a JSON string to a DeleteCachedContentParameters object. */
  public static DeleteCachedContentParameters fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, DeleteCachedContentParameters.class);
  }
}
