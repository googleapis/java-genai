// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.Optional;

/**
 * Represents a whole or partial calendar date, such as a birthday. The time of day and time zone
 * are either specified elsewhere or are insignificant. The date is relative to the Gregorian
 * Calendar. This can represent one of the following: * A full date, with non-zero year, month, and
 * day values. * A month and day, with a zero year (for example, an anniversary). * A year on its
 * own, with a zero month and a zero day. * A year and month, with a zero day (for example, a credit
 * card expiration date). Related types: * google.type.TimeOfDay * google.type.DateTime *
 * google.protobuf.Timestamp
 */
@AutoValue
@JsonDeserialize(builder = AutoValue_GoogleTypeDate.Builder.class)
public abstract class GoogleTypeDate extends JsonSerializable {
  /**
   * Day of a month. Must be from 1 to 31 and valid for the year and month, or 0 to specify a year
   * by itself or a year and month where the day isn't significant.
   */
  public abstract Optional<Integer> getDay();

  /** Month of a year. Must be from 1 to 12, or 0 to specify a year without a month and day. */
  public abstract Optional<Integer> getMonth();

  /** Year of the date. Must be from 1 to 9999, or 0 to specify a date without a year. */
  public abstract Optional<Integer> getYear();

  /** Instantiates a builder for GoogleTypeDate. */
  public static Builder builder() {
    return new AutoValue_GoogleTypeDate.Builder();
  }

  /** Builder for GoogleTypeDate. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("day")
    public abstract Builder setDay(Integer day);

    @JsonProperty("month")
    public abstract Builder setMonth(Integer month);

    @JsonProperty("year")
    public abstract Builder setYear(Integer year);

    public abstract GoogleTypeDate build();
  }

  /** Serializes the GoogleTypeDate object to a JSON string. */
  public String toJson() {
    return JsonSerializable.toJsonString(this);
  }

  /** Deserializes a JSON string to a GoogleTypeDate object. */
  public static GoogleTypeDate fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, GoogleTypeDate.class);
  }
}
