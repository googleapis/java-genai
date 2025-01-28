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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/** Google search entry point. */
@AutoValue
@JsonDeserialize(builder = AutoValue_SearchEntryPoint.Builder.class)
public abstract class SearchEntryPoint extends JsonSerializable {
  /** Optional. Web content snippet that can be embedded in a web page or an app webview. */
  public abstract Optional<String> getRenderedContent();

  /** Optional. Base64 encoded JSON representing array of tuple. */
  public abstract Optional<String> getSdkBlob();

  /** Instantiates a builder for SearchEntryPoint. */
  public static Builder builder() {
    return new AutoValue_SearchEntryPoint.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for SearchEntryPoint. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("renderedContent")
    public abstract Builder setRenderedContent(String renderedContent);

    @JsonProperty("sdkBlob")
    public abstract Builder setSdkBlob(String sdkBlob);

    public abstract SearchEntryPoint build();
  }

  /** Deserializes a JSON string to a SearchEntryPoint object. */
  public static SearchEntryPoint fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, SearchEntryPoint.class);
  }
}
