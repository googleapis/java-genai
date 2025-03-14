package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_GoogleTypeDate extends GoogleTypeDate {

  private final Optional<Integer> day;

  private final Optional<Integer> month;

  private final Optional<Integer> year;

  private AutoValue_GoogleTypeDate(
      Optional<Integer> day,
      Optional<Integer> month,
      Optional<Integer> year) {
    this.day = day;
    this.month = month;
    this.year = year;
  }

  @JsonProperty("day")
  @Override
  public Optional<Integer> day() {
    return day;
  }

  @JsonProperty("month")
  @Override
  public Optional<Integer> month() {
    return month;
  }

  @JsonProperty("year")
  @Override
  public Optional<Integer> year() {
    return year;
  }

  @Override
  public String toString() {
    return "GoogleTypeDate{"
        + "day=" + day + ", "
        + "month=" + month + ", "
        + "year=" + year
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof GoogleTypeDate) {
      GoogleTypeDate that = (GoogleTypeDate) o;
      return this.day.equals(that.day())
          && this.month.equals(that.month())
          && this.year.equals(that.year());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= day.hashCode();
    h$ *= 1000003;
    h$ ^= month.hashCode();
    h$ *= 1000003;
    h$ ^= year.hashCode();
    return h$;
  }

  @Override
  public GoogleTypeDate.Builder toBuilder() {
    return new AutoValue_GoogleTypeDate.Builder(this);
  }

  static final class Builder extends GoogleTypeDate.Builder {
    private Optional<Integer> day = Optional.empty();
    private Optional<Integer> month = Optional.empty();
    private Optional<Integer> year = Optional.empty();
    Builder() {
    }
    Builder(GoogleTypeDate source) {
      this.day = source.day();
      this.month = source.month();
      this.year = source.year();
    }
    @Override
    public GoogleTypeDate.Builder day(Integer day) {
      this.day = Optional.of(day);
      return this;
    }
    @Override
    public GoogleTypeDate.Builder month(Integer month) {
      this.month = Optional.of(month);
      return this;
    }
    @Override
    public GoogleTypeDate.Builder year(Integer year) {
      this.year = Optional.of(year);
      return this;
    }
    @Override
    public GoogleTypeDate build() {
      return new AutoValue_GoogleTypeDate(
          this.day,
          this.month,
          this.year);
    }
  }

}
