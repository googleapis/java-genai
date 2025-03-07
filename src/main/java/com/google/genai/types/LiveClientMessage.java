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

/** Messages sent by the client in the API call. */
@GenerateTypeAdapter
@AutoValue
public abstract class LiveClientMessage extends JsonSerializable {
  /**
   * Message to be sent by the system when connecting to the API. SDK users should not send this
   * message.
   */
  @SerializedName("setup")
  public abstract Optional<LiveClientSetup> setup();

  /** Incremental update of the current conversation delivered from the client. */
  @SerializedName("clientContent")
  public abstract Optional<LiveClientContent> clientContent();

  /** User input that is sent in real time. */
  @SerializedName("realtimeInput")
  public abstract Optional<LiveClientRealtimeInput> realtimeInput();

  /** Response to a `ToolCallMessage` received from the server. */
  @SerializedName("toolResponse")
  public abstract Optional<LiveClientToolResponse> toolResponse();

  /** Instantiates a builder for LiveClientMessage. */
  public static Builder builder() {
    return new AutoValue_LiveClientMessage.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for LiveClientMessage. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("setup")
    public abstract Builder setup(LiveClientSetup setup);

    @SerializedName("setup")
    abstract Builder setup(Optional<LiveClientSetup> setup);

    @SerializedName("clientContent")
    public abstract Builder clientContent(LiveClientContent clientContent);

    @SerializedName("clientContent")
    abstract Builder clientContent(Optional<LiveClientContent> clientContent);

    @SerializedName("realtimeInput")
    public abstract Builder realtimeInput(LiveClientRealtimeInput realtimeInput);

    @SerializedName("realtimeInput")
    abstract Builder realtimeInput(Optional<LiveClientRealtimeInput> realtimeInput);

    @SerializedName("toolResponse")
    public abstract Builder toolResponse(LiveClientToolResponse toolResponse);

    @SerializedName("toolResponse")
    abstract Builder toolResponse(Optional<LiveClientToolResponse> toolResponse);

    public abstract LiveClientMessage build();
  }

  /** Deserializes a JSON string to a LiveClientMessage object. */
  public static LiveClientMessage fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, LiveClientMessage.class);
  }
}
