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

package com.google.genai.types.interactions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import com.google.genai.types.ExcludeFromGeneratedCoverageReport;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Represents a structured result containing an array of items.
 *
 * <p>This type is used for function and MCP tool results that return structured data. Each item in
 * the array can be a string, an ImageContent object, or any other object.
 *
 * <p>This matches the Python SDK's ResultItems type which is defined as:
 *
 * <pre>
 * class ResultItems(BaseModel):
 *     items: Optional[List[ResultItemsItem]] = None
 *
 * where ResultItemsItem = Union[str, ImageContent, object]
 * </pre>
 */
@AutoValue
@JsonDeserialize(builder = ResultItems.Builder.class)
public abstract class ResultItems extends JsonSerializable {

  /**
   * The list of result items.
   *
   * <p>Each item can be a String, an ImageContent, or any other Object.
   */
  @JsonProperty("items")
  public abstract Optional<List<Object>> items();

  /** Instantiates a builder for ResultItems. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ResultItems.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ResultItems. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use {@code ResultItems.builder()} for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ResultItems.Builder();
    }

    /**
     * Setter for items.
     *
     * <p>items: The list of result items.
     */
    @JsonProperty("items")
    public abstract Builder items(List<Object> items);

    /**
     * Setter for items (varargs convenience method).
     *
     * <p>items: The list of result items.
     */
    @CanIgnoreReturnValue
    public Builder items(Object... items) {
      return items(Arrays.asList(items));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder items(Optional<List<Object>> items);

    /** Clears the value of items field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearItems() {
      return items(Optional.empty());
    }

    public abstract ResultItems build();
  }

  /** Deserializes a JSON string to a ResultItems object. */
  @ExcludeFromGeneratedCoverageReport
  public static ResultItems fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ResultItems.class);
  }

  /** Convenience factory method. */
  @ExcludeFromGeneratedCoverageReport
  public static ResultItems of(List<Object> items) {
    return builder().items(items).build();
  }

  /** Convenience factory method (varargs). */
  @ExcludeFromGeneratedCoverageReport
  public static ResultItems of(Object... items) {
    return builder().items(Arrays.asList(items)).build();
  }
}
