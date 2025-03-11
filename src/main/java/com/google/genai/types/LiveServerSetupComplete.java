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
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;

/** Sent in response to a `LiveGenerateContentSetup` message from the client. */
@GenerateTypeAdapter
@AutoValue
public abstract class LiveServerSetupComplete extends JsonSerializable {
  /** Instantiates a builder for LiveServerSetupComplete. */
  public static Builder builder() {
    return new AutoValue_LiveServerSetupComplete.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for LiveServerSetupComplete. */
  @AutoValue.Builder
  public abstract static class Builder {
    public abstract LiveServerSetupComplete build();
  }

  /** Deserializes a JSON string to a LiveServerSetupComplete object. */
  public static LiveServerSetupComplete fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, LiveServerSetupComplete.class);
  }
}
