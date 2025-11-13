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

/** Proxy configuration for the client. */
@AutoValue
@JsonDeserialize(builder = ProxyOptions.Builder.class)
public abstract class ProxyOptions extends JsonSerializable {
  /** Proxy type. Defaults to HTTP if unspecified. */
  @JsonProperty("type")
  public abstract Optional<ProxyType> type();

  /** Proxy server hostname or IP address. */
  @JsonProperty("host")
  public abstract Optional<String> host();

  /** Proxy server port. */
  @JsonProperty("port")
  public abstract Optional<Integer> port();

  /** Username for proxy authentication. If provided, `password` must also be specified. */
  @JsonProperty("username")
  public abstract Optional<String> username();

  /** Password for proxy authentication. If provided, `username` must also be specified. */
  @JsonProperty("password")
  public abstract Optional<String> password();

  /** Instantiates a builder for ProxyOptions. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_ProxyOptions.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for ProxyOptions. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `ProxyOptions.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_ProxyOptions.Builder();
    }

    /**
     * Setter for type.
     *
     * <p>type: Proxy type. Defaults to HTTP if unspecified.
     */
    @JsonProperty("type")
    public abstract Builder type(ProxyType type);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder type(Optional<ProxyType> type);

    /** Clears the value of type field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearType() {
      return type(Optional.empty());
    }

    /**
     * Setter for type given a known enum.
     *
     * <p>type: Proxy type. Defaults to HTTP if unspecified.
     */
    @CanIgnoreReturnValue
    public Builder type(ProxyType.Known knownType) {
      return type(new ProxyType(knownType));
    }

    /**
     * Setter for type given a string.
     *
     * <p>type: Proxy type. Defaults to HTTP if unspecified.
     */
    @CanIgnoreReturnValue
    public Builder type(String type) {
      return type(new ProxyType(type));
    }

    /**
     * Setter for host.
     *
     * <p>host: Proxy server hostname or IP address.
     */
    @JsonProperty("host")
    public abstract Builder host(String host);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder host(Optional<String> host);

    /** Clears the value of host field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearHost() {
      return host(Optional.empty());
    }

    /**
     * Setter for port.
     *
     * <p>port: Proxy server port.
     */
    @JsonProperty("port")
    public abstract Builder port(Integer port);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder port(Optional<Integer> port);

    /** Clears the value of port field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPort() {
      return port(Optional.empty());
    }

    /**
     * Setter for username.
     *
     * <p>username: Username for proxy authentication. If provided, `password` must also be
     * specified.
     */
    @JsonProperty("username")
    public abstract Builder username(String username);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder username(Optional<String> username);

    /** Clears the value of username field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearUsername() {
      return username(Optional.empty());
    }

    /**
     * Setter for password.
     *
     * <p>password: Password for proxy authentication. If provided, `username` must also be
     * specified.
     */
    @JsonProperty("password")
    public abstract Builder password(String password);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder password(Optional<String> password);

    /** Clears the value of password field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearPassword() {
      return password(Optional.empty());
    }

    public abstract ProxyOptions build();
  }

  /** Deserializes a JSON string to a ProxyOptions object. */
  @ExcludeFromGeneratedCoverageReport
  public static ProxyOptions fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, ProxyOptions.class);
  }
}
