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

/** Message contains configuration that will apply for the duration of the streaming session. */
@GenerateTypeAdapter
@AutoValue
public abstract class LiveClientSetup extends JsonSerializable {
  /** The fully qualified name of the publisher model or tuned model endpoint to use. */
  @SerializedName("model")
  public abstract Optional<String> model();

  /**
   * The generation configuration for the session.
   *
   * <p>The following fields are supported: - `response_logprobs` - `response_mime_type` -
   * `logprobs` - `response_schema` - `stop_sequence` - `routing_config` - `audio_timestamp`
   */
  @SerializedName("generationConfig")
  public abstract Optional<GenerationConfig> generationConfig();

  /**
   * The user provided system instructions for the model. Note: only text should be used in parts
   * and content in each part will be in a separate paragraph.
   */
  @SerializedName("systemInstruction")
  public abstract Optional<Content> systemInstruction();

  /**
   * A list of `Tools` the model may use to generate the next response.
   *
   * <p>A `Tool` is a piece of code that enables the system to interact with external systems to
   * perform an action, or set of actions, outside of knowledge and scope of the model.
   */
  @SerializedName("tools")
  public abstract Optional<List<Tool>> tools();

  /** Instantiates a builder for LiveClientSetup. */
  public static Builder builder() {
    return new AutoValue_LiveClientSetup.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for LiveClientSetup. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("model")
    public abstract Builder model(String model);

    @SerializedName("model")
    abstract Builder model(Optional<String> model);

    @SerializedName("generationConfig")
    public abstract Builder generationConfig(GenerationConfig generationConfig);

    @SerializedName("generationConfig")
    abstract Builder generationConfig(Optional<GenerationConfig> generationConfig);

    @SerializedName("systemInstruction")
    public abstract Builder systemInstruction(Content systemInstruction);

    @SerializedName("systemInstruction")
    abstract Builder systemInstruction(Optional<Content> systemInstruction);

    @SerializedName("tools")
    public abstract Builder tools(List<Tool> tools);

    @SerializedName("tools")
    abstract Builder tools(Optional<List<Tool>> tools);

    public abstract LiveClientSetup build();
  }

  /** Deserializes a JSON string to a LiveClientSetup object. */
  public static LiveClientSetup fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, LiveClientSetup.class);
  }
}
