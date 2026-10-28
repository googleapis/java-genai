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

package com.google.genai;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.StreamReadConstraints;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.api.core.InternalApi;
import com.google.genai.errors.GenAiIOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/** A class that can be serialized to JSON and deserialized from JSON. */
public abstract class JsonSerializable {

  @InternalApi protected static final ObjectMapper objectMapper = new ObjectMapper();
  private static final Logger logger = Logger.getLogger(JsonSerializable.class.getName());

  /**
   * System property to override the default max JSON string length (20MB) in read constraints.
   * E.g., if you want to change the limit to 100MB, you can set it via
   * `-Dgenai.json.maxReadLength=100000000`.
   */
  public static final String MAX_READ_LENGTH_PROPERTY = "genai.json.maxReadLength";

  /**
   * Pattern of a duration in the protobuf JSON format: seconds with up to nine fractional digits,
   * followed by "s", e.g. "3s", "3.5s" or "-0.000000001s".
   */
  private static final Pattern DURATION_PATTERN = Pattern.compile("-?\\d+(\\.\\d{1,9})?s");

  /** Range of seconds that fits in a {@link Duration}. */
  private static final BigDecimal MIN_DURATION_SECONDS = BigDecimal.valueOf(Long.MIN_VALUE);

  private static final BigDecimal MAX_DURATION_SECONDS =
      BigDecimal.valueOf(Long.MAX_VALUE).add(BigDecimal.valueOf(999_999_999, 9));

  /**
   * Custom Jackson serializer for {@link Duration} to output the protobuf JSON duration format,
   * e.g. "3s" or "3.5s".
   */
  static class CustomDurationSerializer extends JsonSerializer<Duration> {
    @Override
    public void serialize(
        Duration duration, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
        throws java.io.IOException {
      if (duration == null) {
        jsonGenerator.writeNull();
      } else {
        // Keep the fractional seconds, e.g. 1500 ms is "1.5s", not "1s".
        BigDecimal seconds =
            BigDecimal.valueOf(duration.getSeconds())
                .add(BigDecimal.valueOf(duration.getNano(), 9));
        jsonGenerator.writeString(seconds.stripTrailingZeros().toPlainString() + "s");
      }
    }
  }

  /**
   * Custom Jackson deserializer for {@link Duration} to parse the protobuf JSON duration format,
   * e.g. "3s" or "3.5s".
   */
  static class CustomDurationDeserializer extends JsonDeserializer<Duration> {
    @Override
    public Duration deserialize(JsonParser p, DeserializationContext ctxt)
        throws java.io.IOException, JsonProcessingException {
      String value = p.getValueAsString();

      if (value == null || value.isEmpty()) {
        return null;
      }
      if (!DURATION_PATTERN.matcher(value).matches()) {
        throw ctxt.weirdStringException(
            value,
            Duration.class,
            "Expected duration in format like '3s' or '3.5s', but got: " + value);
      }
      BigDecimal seconds = new BigDecimal(value.substring(0, value.length() - 1));
      if (seconds.compareTo(MIN_DURATION_SECONDS) < 0
          || seconds.compareTo(MAX_DURATION_SECONDS) > 0) {
        throw ctxt.weirdStringException(
            value, Duration.class, "Duration is out of range: " + value);
      }
      long wholeSeconds = seconds.setScale(0, RoundingMode.DOWN).longValue();
      int nanos = seconds.subtract(BigDecimal.valueOf(wholeSeconds)).movePointRight(9).intValue();
      // For negative durations nanos is negative too; Duration.ofSeconds normalizes it.
      return Duration.ofSeconds(wholeSeconds, nanos);
    }
  }

  /** Configures the stream read constraints for the JSON parser. */
  private static void configureStreamReadConstraints(int maxReadLength) {
    if (maxReadLength <= 0) {
      throw new IllegalArgumentException("Invalid JSON max read length: " + maxReadLength);
    }
    logger.info("Overriding default JSON max string length. New value = " + maxReadLength);
    StreamReadConstraints constraints =
        StreamReadConstraints.builder().maxStringLength(maxReadLength).build();
    objectMapper.getFactory().setStreamReadConstraints(constraints);
  }

  static {
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
    objectMapper.registerModule(new Jdk8Module());
    // Disable writing dates as timestamps to use ISO-8601 string format for Instant
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    // Skip JSON nulls: AutoValue setters call Optional.of.
    objectMapper.setDefaultSetterInfo(JsonSetter.Value.forValueNulls(Nulls.SKIP));

    // Create a module for custom serializers/deserializers
    SimpleModule customModule = new SimpleModule();
    customModule.addSerializer(Duration.class, new CustomDurationSerializer());
    customModule.addDeserializer(Duration.class, new CustomDurationDeserializer());

    // Register JavaTimeModule for other java.time types *before* the custom module
    // This ensures our custom Duration handling takes precedence over the default one
    // provided by JavaTimeModule.
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.registerModule(customModule);

    try {
      String propertyValue = System.getProperty(MAX_READ_LENGTH_PROPERTY);
      if (propertyValue != null && !propertyValue.isEmpty()) {
        int maxStringLength = Integer.parseInt(propertyValue);
        configureStreamReadConstraints(maxStringLength);
      }
    } catch (NumberFormatException e) {
      logger.warning(
          "Failed to parse system property ["
              + MAX_READ_LENGTH_PROPERTY
              + "]. Using default 20MB limit.");
    }
  }

  /** Serializes the instance to a Json string. */
  public String toJson() {
    return toJsonString(this);
  }

  /** Serializes an object to a Json string. */
  public static String toJsonString(Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new GenAiIOException("Failed to serialize the object to JSON.", e);
    }
  }

  /** Serializes an object to a JsonNode. */
  public static JsonNode toJsonNode(Object object) {
    return objectMapper.valueToTree(object);
  }

  /** Deserializes a Json string to an object of the given type. This is for internal use only. */
  @InternalApi
  public static <T extends JsonSerializable> T fromJsonString(String jsonString, Class<T> clazz) {
    try {
      return objectMapper.readValue(jsonString, clazz);
    } catch (JsonProcessingException e) {
      throw new GenAiIOException("Failed to deserialize the JSON string.", e);
    }
  }

  /** Deserializes a JsonNode to an object of the given type. */
  @InternalApi
  public static <T extends JsonSerializable> T fromJsonNode(JsonNode jsonNode, Class<T> clazz) {
    try {
      return objectMapper.treeToValue(jsonNode, clazz);
    } catch (JsonProcessingException e) {
      throw new GenAiIOException("Failed to deserialize the JSON node.", e);
    }
  }

  /** Converts a Json string to a JsonNode. */
  public static JsonNode stringToJsonNode(String string) {
    try {
      return objectMapper.readTree(string);
    } catch (JsonProcessingException e) {
      throw new GenAiIOException("Failed to parse the JSON string.", e);
    }
  }

  /**
   * Overrides the default maximum JSON string length (20MB) for the JSON parser.
   *
   * <p><b>Warning:</b> This modifies a global static setting. It will overrides the system property
   * setting via {@link #MAX_READ_LENGTH_PROPERTY}. This method is <b>not thread-safe</b>.
   *
   * @param maxReadLength the new maximum string length in bytes (e.g., 100_000_000 for 100MB).
   */
  public static void setMaxReadLength(int maxReadLength) {
    configureStreamReadConstraints(maxReadLength);
  }

  public static ObjectMapper objectMapper() {
    return objectMapper;
  }
}
