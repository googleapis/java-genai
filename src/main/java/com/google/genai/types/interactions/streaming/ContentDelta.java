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

package com.google.genai.types.interactions.streaming;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import com.google.genai.types.ExcludeFromGeneratedCoverageReport;
import com.google.genai.types.interactions.streaming.delta.Delta;
import java.util.Optional;

/**
 * Event containing an incremental content update (delta).
 *
 * <p>This event is emitted multiple times during streaming to provide incremental updates to the
 * content. The delta contains the actual content fragment (e.g., text chunk, partial function
 * call).
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * for (InteractionSseEvent event : stream) {
 *     if (event instanceof ContentDelta deltaEvent) {
 *         Delta delta = deltaEvent.delta().orElse(null);
 *         if (delta instanceof TextDelta textDelta) {
 *             System.out.print(textDelta.text().orElse(""));
 *         }
 *     }
 * }
 * }</pre>
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = ContentDelta.Builder.class)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "event_type")
@JsonTypeName("content.delta")
public abstract class ContentDelta extends JsonSerializable
    implements InteractionSseEvent {

  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ContentDelta.Builder();
  }

  public abstract Builder toBuilder();

  /** Returns the event ID for stream resumption. */
  @Override
  @JsonProperty("event_id")
  public abstract Optional<String> eventId();

  /** Returns the index of the content item this delta belongs to. */
  @JsonProperty("index")
  public abstract Optional<Integer> index();

  /** Returns the incremental content update. */
  @JsonProperty("delta")
  public abstract Optional<Delta> delta();

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ContentDelta.Builder();
    }

    @JsonProperty("event_id")
    public abstract Builder eventId(String eventId);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder eventId(Optional<String> eventId);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEventId() {
      return eventId(Optional.empty());
    }

    @JsonProperty("index")
    public abstract Builder index(Integer index);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder index(Optional<Integer> index);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearIndex() {
      return index(Optional.empty());
    }

    @JsonProperty("delta")
    public abstract Builder delta(Delta delta);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder delta(Optional<Delta> delta);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDelta() {
      return delta(Optional.empty());
    }

    public abstract ContentDelta build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static ContentDelta fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ContentDelta.class);
  }
}
