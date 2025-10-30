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

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** Optional configuration for cached content creation. */
@AutoValue
@JsonDeserialize(builder = CreateCachedContentConfig.Builder.class)
public abstract class CreateCachedContentConfig extends JsonSerializable {
  /** Used to override HTTP request options. */
  @JsonProperty("httpOptions")
  public abstract Optional<HttpOptions> httpOptions();

  /**
   * The TTL for this resource. The expiration time is computed: now + TTL. It is a duration string,
   * with up to nine fractional digits, terminated by 's'. Example: "3.5s".
   */
  @JsonProperty("ttl")
  public abstract Optional<Duration> ttl();

  /**
   * Timestamp of when this resource is considered expired. Uses RFC 3339 format, Example:
   * 2014-10-02T15:01:23Z.
   */
  @JsonProperty("expireTime")
  public abstract Optional<Instant> expireTime();

  /** The user-generated meaningful display name of the cached content. */
  @JsonProperty("displayName")
  public abstract Optional<String> displayName();

  /** The content to cache. */
  @JsonProperty("contents")
  public abstract Optional<List<Content>> contents();

  /** Developer set system instruction. */
  @JsonProperty("systemInstruction")
  public abstract Optional<Content> systemInstruction();

  /** A list of `Tools` the model may use to generate the next response. */
  @JsonProperty("tools")
  public abstract Optional<List<Tool>> tools();

  /** Configuration for the tools to use. This config is shared for all tools. */
  @JsonProperty("toolConfig")
  public abstract Optional<ToolConfig> toolConfig();

  /**
   * The Cloud KMS resource identifier of the customer managed encryption key used to protect a
   * resource. The key needs to be in the same region as where the compute resource is created. See
   * https://cloud.google.com/vertex-ai/docs/general/cmek for more details. If this is set, then all
   * created CachedContent objects will be encrypted with the provided encryption key. Allowed
   * formats: projects/{project}/locations/{location}/keyRings/{key_ring}/cryptoKeys/{crypto_key}
   */
  @JsonProperty("kmsKeyName")
  public abstract Optional<String> kmsKeyName();

  /** Instantiates a builder for CreateCachedContentConfig. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_CreateCachedContentConfig.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for CreateCachedContentConfig. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `CreateCachedContentConfig.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_CreateCachedContentConfig.Builder();
    }

    /**
     * Setter for httpOptions.
     *
     * <p>httpOptions: Used to override HTTP request options.
     */
    @JsonProperty("httpOptions")
    public abstract Builder httpOptions(HttpOptions httpOptions);

    /**
     * Setter for httpOptions builder.
     *
     * <p>httpOptions: Used to override HTTP request options.
     */
    public Builder httpOptions(HttpOptions.Builder httpOptionsBuilder) {
      return httpOptions(httpOptionsBuilder.build());
    }

    /**
     * Setter for ttl.
     *
     * <p>ttl: The TTL for this resource. The expiration time is computed: now + TTL. It is a
     * duration string, with up to nine fractional digits, terminated by 's'. Example: "3.5s".
     */
    @JsonProperty("ttl")
    public abstract Builder ttl(Duration ttl);

    /**
     * Setter for expireTime.
     *
     * <p>expireTime: Timestamp of when this resource is considered expired. Uses RFC 3339 format,
     * Example: 2014-10-02T15:01:23Z.
     */
    @JsonProperty("expireTime")
    public abstract Builder expireTime(Instant expireTime);

    /**
     * Setter for displayName.
     *
     * <p>displayName: The user-generated meaningful display name of the cached content.
     */
    @JsonProperty("displayName")
    public abstract Builder displayName(String displayName);

    /**
     * Setter for contents.
     *
     * <p>contents: The content to cache.
     */
    @JsonProperty("contents")
    public abstract Builder contents(List<Content> contents);

    /**
     * Setter for contents.
     *
     * <p>contents: The content to cache.
     */
    public Builder contents(Content... contents) {
      return contents(Arrays.asList(contents));
    }

    /**
     * Setter for contents builder.
     *
     * <p>contents: The content to cache.
     */
    public Builder contents(Content.Builder... contentsBuilders) {
      return contents(
          Arrays.asList(contentsBuilders).stream()
              .map(Content.Builder::build)
              .collect(toImmutableList()));
    }

    /**
     * Setter for systemInstruction.
     *
     * <p>systemInstruction: Developer set system instruction.
     */
    @JsonProperty("systemInstruction")
    public abstract Builder systemInstruction(Content systemInstruction);

    /**
     * Setter for systemInstruction builder.
     *
     * <p>systemInstruction: Developer set system instruction.
     */
    public Builder systemInstruction(Content.Builder systemInstructionBuilder) {
      return systemInstruction(systemInstructionBuilder.build());
    }

    /**
     * Setter for tools.
     *
     * <p>tools: A list of `Tools` the model may use to generate the next response.
     */
    @JsonProperty("tools")
    public abstract Builder tools(List<Tool> tools);

    /**
     * Setter for tools.
     *
     * <p>tools: A list of `Tools` the model may use to generate the next response.
     */
    public Builder tools(Tool... tools) {
      return tools(Arrays.asList(tools));
    }

    /**
     * Setter for tools builder.
     *
     * <p>tools: A list of `Tools` the model may use to generate the next response.
     */
    public Builder tools(Tool.Builder... toolsBuilders) {
      return tools(
          Arrays.asList(toolsBuilders).stream()
              .map(Tool.Builder::build)
              .collect(toImmutableList()));
    }

    /**
     * Setter for toolConfig.
     *
     * <p>toolConfig: Configuration for the tools to use. This config is shared for all tools.
     */
    @JsonProperty("toolConfig")
    public abstract Builder toolConfig(ToolConfig toolConfig);

    /**
     * Setter for toolConfig builder.
     *
     * <p>toolConfig: Configuration for the tools to use. This config is shared for all tools.
     */
    public Builder toolConfig(ToolConfig.Builder toolConfigBuilder) {
      return toolConfig(toolConfigBuilder.build());
    }

    /**
     * Setter for kmsKeyName.
     *
     * <p>kmsKeyName: The Cloud KMS resource identifier of the customer managed encryption key used
     * to protect a resource. The key needs to be in the same region as where the compute resource
     * is created. See https://cloud.google.com/vertex-ai/docs/general/cmek for more details. If
     * this is set, then all created CachedContent objects will be encrypted with the provided
     * encryption key. Allowed formats:
     * projects/{project}/locations/{location}/keyRings/{key_ring}/cryptoKeys/{crypto_key}
     */
    @JsonProperty("kmsKeyName")
    public abstract Builder kmsKeyName(String kmsKeyName);

    public abstract CreateCachedContentConfig build();
  }

  /** Deserializes a JSON string to a CreateCachedContentConfig object. */
  @ExcludeFromGeneratedCoverageReport
  public static CreateCachedContentConfig fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, CreateCachedContentConfig.class);
  }
}
