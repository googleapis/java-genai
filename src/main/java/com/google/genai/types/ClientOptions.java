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
import java.util.Optional;

/** Client options to be used in the client instantiation. */
@AutoValue
@JsonDeserialize(builder = ClientOptions.Builder.class)
public abstract class ClientOptions extends JsonSerializable {
  /** The maximum number of connections allowed in the pool. */
  @JsonProperty("maxConnections")
  public abstract Optional<Integer> maxConnections();

  /** The maximum number of connections allowed per host. */
  @JsonProperty("maxConnectionsPerHost")
  public abstract Optional<Integer> maxConnectionsPerHost();

  /** Proxy configuration to be used in the client. */
  @JsonProperty("proxyOptions")
  public abstract Optional<ProxyOptions> proxyOptions();

  /** Instantiates a builder for ClientOptions. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ClientOptions.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ClientOptions. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ClientOptions.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ClientOptions.Builder();
    }

    /**
     * Setter for maxConnections.
     *
     * <p>maxConnections: The maximum number of connections allowed in the pool.
     */
    @JsonProperty("maxConnections")
    public abstract Builder maxConnections(Integer maxConnections);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder maxConnections(Optional<Integer> maxConnections);

    /** Clears the value of maxConnections field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMaxConnections() {
      return maxConnections(Optional.empty());
    }

    /**
     * Setter for maxConnectionsPerHost.
     *
     * <p>maxConnectionsPerHost: The maximum number of connections allowed per host.
     */
    @JsonProperty("maxConnectionsPerHost")
    public abstract Builder maxConnectionsPerHost(Integer maxConnectionsPerHost);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder maxConnectionsPerHost(Optional<Integer> maxConnectionsPerHost);

    /** Clears the value of maxConnectionsPerHost field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearMaxConnectionsPerHost() {
      return maxConnectionsPerHost(Optional.empty());
    }

    /**
     * Setter for proxyOptions.
     *
     * <p>proxyOptions: Proxy configuration to be used in the client.
     */
    @JsonProperty("proxyOptions")
    public abstract Builder proxyOptions(ProxyOptions proxyOptions);

    /**
     * Setter for proxyOptions builder.
     *
     * <p>proxyOptions: Proxy configuration to be used in the client.
     */
    @CanIgnoreReturnValue
    public Builder proxyOptions(ProxyOptions.Builder proxyOptionsBuilder) {
      return proxyOptions(proxyOptionsBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder proxyOptions(Optional<ProxyOptions> proxyOptions);

    /** Clears the value of proxyOptions field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearProxyOptions() {
      return proxyOptions(Optional.empty());
    }

    public abstract ClientOptions build();
  }

  /** Deserializes a JSON string to a ClientOptions object. */
  @ExcludeFromGeneratedCoverageReport
  public static ClientOptions fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ClientOptions.class);
  }
}
