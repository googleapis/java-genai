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
import com.google.genai.types.interactions.content.Content;
import java.util.Optional;

/**
 * Event indicating that a new content item has started streaming.
 *
 * <p>This event is emitted at the beginning of each content item in the outputs. It provides the
 * index of the content and initial content metadata.
 *
 * <p>Note: The Interactions API is in beta and subject to change.
 */
@AutoValue
@JsonDeserialize(builder = ContentStart.Builder.class)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "event_type")
@JsonTypeName("content.start")
public abstract class ContentStart extends JsonSerializable
    implements InteractionSseEvent {

  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ContentStart.Builder();
  }

  public abstract Builder toBuilder();

  /** Returns the event ID for stream resumption. */
  @Override
  @JsonProperty("event_id")
  public abstract Optional<String> eventId();

  /** Returns the index of the content item in the outputs array. */
  @JsonProperty("index")
  public abstract Optional<Integer> index();

  /** Returns the initial content metadata. */
  @JsonProperty("content")
  public abstract Optional<Content> content();

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ContentStart.Builder();
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

    @JsonProperty("content")
    public abstract Builder content(Content content);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder content(Optional<Content> content);

    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearContent() {
      return content(Optional.empty());
    }

    public abstract ContentStart build();
  }

  @ExcludeFromGeneratedCoverageReport
  public static ContentStart fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ContentStart.class);
  }
}
