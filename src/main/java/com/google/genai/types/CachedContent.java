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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.time.Instant;
import java.util.Optional;

/** A resource used in LLM queries for users to explicitly specify what to cache. */
@AutoValue
@JsonDeserialize(builder = CachedContent.Builder.class)
public abstract class CachedContent extends JsonSerializable {
  /** The server-generated resource name of the cached content. */
  @JsonProperty("name")
  public abstract Optional<String> name();

  /** The user-generated meaningful display name of the cached content. */
  @JsonProperty("displayName")
  public abstract Optional<String> displayName();

  /** The name of the publisher model to use for cached content. */
  @JsonProperty("model")
  public abstract Optional<String> model();

  /** Creation time of the cache entry. */
  @JsonProperty("createTime")
  public abstract Optional<Instant> createTime();

  /** When the cache entry was last updated in UTC time. */
  @JsonProperty("updateTime")
  public abstract Optional<Instant> updateTime();

  /** Expiration time of the cached content. */
  @JsonProperty("expireTime")
  public abstract Optional<Instant> expireTime();

  /** Metadata on the usage of the cached content. */
  @JsonProperty("usageMetadata")
  public abstract Optional<CachedContentUsageMetadata> usageMetadata();

  /** Instantiates a builder for CachedContent. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_CachedContent.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for CachedContent. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `CachedContent.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_CachedContent.Builder();
    }

    /**
     * Setter for name.
     *
     * <p>name: The server-generated resource name of the cached content.
     */
    @JsonProperty("name")
    public abstract Builder name(String name);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder name(Optional<String> name);

    /** Clears the value of name field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearName() {
      return name(Optional.empty());
    }

    /**
     * Setter for displayName.
     *
     * <p>displayName: The user-generated meaningful display name of the cached content.
     */
    @JsonProperty("displayName")
    public abstract Builder displayName(String displayName);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder displayName(Optional<String> displayName);

    /** Clears the value of displayName field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearDisplayName() {
      return displayName(Optional.empty());
    }

    /**
     * Setter for model.
     *
     * <p>model: The name of the publisher model to use for cached content.
     */
    @JsonProperty("model")
    public abstract Builder model(String model);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder model(Optional<String> model);

    /** Clears the value of model field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearModel() {
      return model(Optional.empty());
    }

    /**
     * Setter for createTime.
     *
     * <p>createTime: Creation time of the cache entry.
     */
    @JsonProperty("createTime")
    public abstract Builder createTime(Instant createTime);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder createTime(Optional<Instant> createTime);

    /** Clears the value of createTime field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCreateTime() {
      return createTime(Optional.empty());
    }

    /**
     * Setter for updateTime.
     *
     * <p>updateTime: When the cache entry was last updated in UTC time.
     */
    @JsonProperty("updateTime")
    public abstract Builder updateTime(Instant updateTime);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder updateTime(Optional<Instant> updateTime);

    /** Clears the value of updateTime field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearUpdateTime() {
      return updateTime(Optional.empty());
    }

    /**
     * Setter for expireTime.
     *
     * <p>expireTime: Expiration time of the cached content.
     */
    @JsonProperty("expireTime")
    public abstract Builder expireTime(Instant expireTime);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder expireTime(Optional<Instant> expireTime);

    /** Clears the value of expireTime field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearExpireTime() {
      return expireTime(Optional.empty());
    }

    /**
     * Setter for usageMetadata.
     *
     * <p>usageMetadata: Metadata on the usage of the cached content.
     */
    @JsonProperty("usageMetadata")
    public abstract Builder usageMetadata(CachedContentUsageMetadata usageMetadata);

    /**
     * Setter for usageMetadata builder.
     *
     * <p>usageMetadata: Metadata on the usage of the cached content.
     */
    @CanIgnoreReturnValue
    public Builder usageMetadata(CachedContentUsageMetadata.Builder usageMetadataBuilder) {
      return usageMetadata(usageMetadataBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder usageMetadata(Optional<CachedContentUsageMetadata> usageMetadata);

    /** Clears the value of usageMetadata field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearUsageMetadata() {
      return usageMetadata(Optional.empty());
    }

    public abstract CachedContent build();
  }

  /** Deserializes a JSON string to a CachedContent object. */
  @ExcludeFromGeneratedCoverageReport
  public static CachedContent fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, CachedContent.class);
  }
}
