package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_Schema extends Schema {

  private final Optional<Object> example;

  private final Optional<String> pattern;

  private final Optional<Object> default_;

  private final Optional<Long> maxLength;

  private final Optional<String> title;

  private final Optional<Long> minLength;

  private final Optional<Long> minProperties;

  private final Optional<Long> maxProperties;

  private final Optional<List<Schema>> anyOf;

  private final Optional<String> description;

  private final Optional<List<String>> enum_;

  private final Optional<String> format;

  private final Optional<Schema> items;

  private final Optional<Long> maxItems;

  private final Optional<Double> maximum;

  private final Optional<Long> minItems;

  private final Optional<Double> minimum;

  private final Optional<Boolean> nullable;

  private final Optional<Map<String, Schema>> properties;

  private final Optional<List<String>> propertyOrdering;

  private final Optional<List<String>> required;

  private final Optional<String> type;

  private AutoValue_Schema(
      Optional<Object> example,
      Optional<String> pattern,
      Optional<Object> default_,
      Optional<Long> maxLength,
      Optional<String> title,
      Optional<Long> minLength,
      Optional<Long> minProperties,
      Optional<Long> maxProperties,
      Optional<List<Schema>> anyOf,
      Optional<String> description,
      Optional<List<String>> enum_,
      Optional<String> format,
      Optional<Schema> items,
      Optional<Long> maxItems,
      Optional<Double> maximum,
      Optional<Long> minItems,
      Optional<Double> minimum,
      Optional<Boolean> nullable,
      Optional<Map<String, Schema>> properties,
      Optional<List<String>> propertyOrdering,
      Optional<List<String>> required,
      Optional<String> type) {
    this.example = example;
    this.pattern = pattern;
    this.default_ = default_;
    this.maxLength = maxLength;
    this.title = title;
    this.minLength = minLength;
    this.minProperties = minProperties;
    this.maxProperties = maxProperties;
    this.anyOf = anyOf;
    this.description = description;
    this.enum_ = enum_;
    this.format = format;
    this.items = items;
    this.maxItems = maxItems;
    this.maximum = maximum;
    this.minItems = minItems;
    this.minimum = minimum;
    this.nullable = nullable;
    this.properties = properties;
    this.propertyOrdering = propertyOrdering;
    this.required = required;
    this.type = type;
  }

  @JsonProperty("example")
  @Override
  public Optional<Object> example() {
    return example;
  }

  @JsonProperty("pattern")
  @Override
  public Optional<String> pattern() {
    return pattern;
  }

  @JsonProperty("default")
  @Override
  public Optional<Object> default_() {
    return default_;
  }

  @JsonProperty("maxLength")
  @Override
  public Optional<Long> maxLength() {
    return maxLength;
  }

  @JsonProperty("title")
  @Override
  public Optional<String> title() {
    return title;
  }

  @JsonProperty("minLength")
  @Override
  public Optional<Long> minLength() {
    return minLength;
  }

  @JsonProperty("minProperties")
  @Override
  public Optional<Long> minProperties() {
    return minProperties;
  }

  @JsonProperty("maxProperties")
  @Override
  public Optional<Long> maxProperties() {
    return maxProperties;
  }

  @JsonProperty("anyOf")
  @Override
  public Optional<List<Schema>> anyOf() {
    return anyOf;
  }

  @JsonProperty("description")
  @Override
  public Optional<String> description() {
    return description;
  }

  @JsonProperty("enum")
  @Override
  public Optional<List<String>> enum_() {
    return enum_;
  }

  @JsonProperty("format")
  @Override
  public Optional<String> format() {
    return format;
  }

  @JsonProperty("items")
  @Override
  public Optional<Schema> items() {
    return items;
  }

  @JsonProperty("maxItems")
  @Override
  public Optional<Long> maxItems() {
    return maxItems;
  }

  @JsonProperty("maximum")
  @Override
  public Optional<Double> maximum() {
    return maximum;
  }

  @JsonProperty("minItems")
  @Override
  public Optional<Long> minItems() {
    return minItems;
  }

  @JsonProperty("minimum")
  @Override
  public Optional<Double> minimum() {
    return minimum;
  }

  @JsonProperty("nullable")
  @Override
  public Optional<Boolean> nullable() {
    return nullable;
  }

  @JsonProperty("properties")
  @Override
  public Optional<Map<String, Schema>> properties() {
    return properties;
  }

  @JsonProperty("propertyOrdering")
  @Override
  public Optional<List<String>> propertyOrdering() {
    return propertyOrdering;
  }

  @JsonProperty("required")
  @Override
  public Optional<List<String>> required() {
    return required;
  }

  @JsonProperty("type")
  @Override
  public Optional<String> type() {
    return type;
  }

  @Override
  public String toString() {
    return "Schema{"
        + "example=" + example + ", "
        + "pattern=" + pattern + ", "
        + "default_=" + default_ + ", "
        + "maxLength=" + maxLength + ", "
        + "title=" + title + ", "
        + "minLength=" + minLength + ", "
        + "minProperties=" + minProperties + ", "
        + "maxProperties=" + maxProperties + ", "
        + "anyOf=" + anyOf + ", "
        + "description=" + description + ", "
        + "enum_=" + enum_ + ", "
        + "format=" + format + ", "
        + "items=" + items + ", "
        + "maxItems=" + maxItems + ", "
        + "maximum=" + maximum + ", "
        + "minItems=" + minItems + ", "
        + "minimum=" + minimum + ", "
        + "nullable=" + nullable + ", "
        + "properties=" + properties + ", "
        + "propertyOrdering=" + propertyOrdering + ", "
        + "required=" + required + ", "
        + "type=" + type
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof Schema) {
      Schema that = (Schema) o;
      return this.example.equals(that.example())
          && this.pattern.equals(that.pattern())
          && this.default_.equals(that.default_())
          && this.maxLength.equals(that.maxLength())
          && this.title.equals(that.title())
          && this.minLength.equals(that.minLength())
          && this.minProperties.equals(that.minProperties())
          && this.maxProperties.equals(that.maxProperties())
          && this.anyOf.equals(that.anyOf())
          && this.description.equals(that.description())
          && this.enum_.equals(that.enum_())
          && this.format.equals(that.format())
          && this.items.equals(that.items())
          && this.maxItems.equals(that.maxItems())
          && this.maximum.equals(that.maximum())
          && this.minItems.equals(that.minItems())
          && this.minimum.equals(that.minimum())
          && this.nullable.equals(that.nullable())
          && this.properties.equals(that.properties())
          && this.propertyOrdering.equals(that.propertyOrdering())
          && this.required.equals(that.required())
          && this.type.equals(that.type());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= example.hashCode();
    h$ *= 1000003;
    h$ ^= pattern.hashCode();
    h$ *= 1000003;
    h$ ^= default_.hashCode();
    h$ *= 1000003;
    h$ ^= maxLength.hashCode();
    h$ *= 1000003;
    h$ ^= title.hashCode();
    h$ *= 1000003;
    h$ ^= minLength.hashCode();
    h$ *= 1000003;
    h$ ^= minProperties.hashCode();
    h$ *= 1000003;
    h$ ^= maxProperties.hashCode();
    h$ *= 1000003;
    h$ ^= anyOf.hashCode();
    h$ *= 1000003;
    h$ ^= description.hashCode();
    h$ *= 1000003;
    h$ ^= enum_.hashCode();
    h$ *= 1000003;
    h$ ^= format.hashCode();
    h$ *= 1000003;
    h$ ^= items.hashCode();
    h$ *= 1000003;
    h$ ^= maxItems.hashCode();
    h$ *= 1000003;
    h$ ^= maximum.hashCode();
    h$ *= 1000003;
    h$ ^= minItems.hashCode();
    h$ *= 1000003;
    h$ ^= minimum.hashCode();
    h$ *= 1000003;
    h$ ^= nullable.hashCode();
    h$ *= 1000003;
    h$ ^= properties.hashCode();
    h$ *= 1000003;
    h$ ^= propertyOrdering.hashCode();
    h$ *= 1000003;
    h$ ^= required.hashCode();
    h$ *= 1000003;
    h$ ^= type.hashCode();
    return h$;
  }

  @Override
  public Schema.Builder toBuilder() {
    return new AutoValue_Schema.Builder(this);
  }

  static final class Builder extends Schema.Builder {
    private Optional<Object> example = Optional.empty();
    private Optional<String> pattern = Optional.empty();
    private Optional<Object> default_ = Optional.empty();
    private Optional<Long> maxLength = Optional.empty();
    private Optional<String> title = Optional.empty();
    private Optional<Long> minLength = Optional.empty();
    private Optional<Long> minProperties = Optional.empty();
    private Optional<Long> maxProperties = Optional.empty();
    private Optional<List<Schema>> anyOf = Optional.empty();
    private Optional<String> description = Optional.empty();
    private Optional<List<String>> enum_ = Optional.empty();
    private Optional<String> format = Optional.empty();
    private Optional<Schema> items = Optional.empty();
    private Optional<Long> maxItems = Optional.empty();
    private Optional<Double> maximum = Optional.empty();
    private Optional<Long> minItems = Optional.empty();
    private Optional<Double> minimum = Optional.empty();
    private Optional<Boolean> nullable = Optional.empty();
    private Optional<Map<String, Schema>> properties = Optional.empty();
    private Optional<List<String>> propertyOrdering = Optional.empty();
    private Optional<List<String>> required = Optional.empty();
    private Optional<String> type = Optional.empty();
    Builder() {
    }
    Builder(Schema source) {
      this.example = source.example();
      this.pattern = source.pattern();
      this.default_ = source.default_();
      this.maxLength = source.maxLength();
      this.title = source.title();
      this.minLength = source.minLength();
      this.minProperties = source.minProperties();
      this.maxProperties = source.maxProperties();
      this.anyOf = source.anyOf();
      this.description = source.description();
      this.enum_ = source.enum_();
      this.format = source.format();
      this.items = source.items();
      this.maxItems = source.maxItems();
      this.maximum = source.maximum();
      this.minItems = source.minItems();
      this.minimum = source.minimum();
      this.nullable = source.nullable();
      this.properties = source.properties();
      this.propertyOrdering = source.propertyOrdering();
      this.required = source.required();
      this.type = source.type();
    }
    @Override
    public Schema.Builder example(Object example) {
      this.example = Optional.of(example);
      return this;
    }
    @Override
    public Schema.Builder pattern(String pattern) {
      this.pattern = Optional.of(pattern);
      return this;
    }
    @Override
    public Schema.Builder default_(Object default_) {
      this.default_ = Optional.of(default_);
      return this;
    }
    @Override
    public Schema.Builder maxLength(Long maxLength) {
      this.maxLength = Optional.of(maxLength);
      return this;
    }
    @Override
    public Schema.Builder title(String title) {
      this.title = Optional.of(title);
      return this;
    }
    @Override
    public Schema.Builder minLength(Long minLength) {
      this.minLength = Optional.of(minLength);
      return this;
    }
    @Override
    public Schema.Builder minProperties(Long minProperties) {
      this.minProperties = Optional.of(minProperties);
      return this;
    }
    @Override
    public Schema.Builder maxProperties(Long maxProperties) {
      this.maxProperties = Optional.of(maxProperties);
      return this;
    }
    @Override
    public Schema.Builder anyOf(List<Schema> anyOf) {
      this.anyOf = Optional.of(anyOf);
      return this;
    }
    @Override
    public Schema.Builder description(String description) {
      this.description = Optional.of(description);
      return this;
    }
    @Override
    public Schema.Builder enum_(List<String> enum_) {
      this.enum_ = Optional.of(enum_);
      return this;
    }
    @Override
    public Schema.Builder format(String format) {
      this.format = Optional.of(format);
      return this;
    }
    @Override
    public Schema.Builder items(Schema items) {
      this.items = Optional.of(items);
      return this;
    }
    @Override
    public Schema.Builder maxItems(Long maxItems) {
      this.maxItems = Optional.of(maxItems);
      return this;
    }
    @Override
    public Schema.Builder maximum(Double maximum) {
      this.maximum = Optional.of(maximum);
      return this;
    }
    @Override
    public Schema.Builder minItems(Long minItems) {
      this.minItems = Optional.of(minItems);
      return this;
    }
    @Override
    public Schema.Builder minimum(Double minimum) {
      this.minimum = Optional.of(minimum);
      return this;
    }
    @Override
    public Schema.Builder nullable(boolean nullable) {
      this.nullable = Optional.of(nullable);
      return this;
    }
    @Override
    public Schema.Builder properties(Map<String, Schema> properties) {
      this.properties = Optional.of(properties);
      return this;
    }
    @Override
    public Schema.Builder propertyOrdering(List<String> propertyOrdering) {
      this.propertyOrdering = Optional.of(propertyOrdering);
      return this;
    }
    @Override
    public Schema.Builder required(List<String> required) {
      this.required = Optional.of(required);
      return this;
    }
    @Override
    public Schema.Builder type(String type) {
      this.type = Optional.of(type);
      return this;
    }
    @Override
    public Schema build() {
      return new AutoValue_Schema(
          this.example,
          this.pattern,
          this.default_,
          this.maxLength,
          this.title,
          this.minLength,
          this.minProperties,
          this.maxProperties,
          this.anyOf,
          this.description,
          this.enum_,
          this.format,
          this.items,
          this.maxItems,
          this.maximum,
          this.minItems,
          this.minimum,
          this.nullable,
          this.properties,
          this.propertyOrdering,
          this.required,
          this.type);
    }
  }

}
