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
import java.time.Duration;
import java.util.Map;
import java.util.Optional;

/**
 * A transport that can stream HTTP requests and responses. Next ID: 6. This data type is not
 * supported in Vertex AI.
 */
@AutoValue
@JsonDeserialize(builder = StreamableHttpTransport.Builder.class)
public abstract class StreamableHttpTransport extends JsonSerializable {
  /** Optional: Fields for authentication headers, timeouts, etc., if needed. */
  @JsonProperty("headers")
  public abstract Optional<Map<String, String>> headers();

  /** Timeout for SSE read operations. */
  @JsonProperty("sseReadTimeout")
  public abstract Optional<Duration> sseReadTimeout();

  /** Whether to close the client session when the transport closes. */
  @JsonProperty("terminateOnClose")
  public abstract Optional<Boolean> terminateOnClose();

  /** HTTP timeout for regular operations. */
  @JsonProperty("timeout")
  public abstract Optional<Duration> timeout();

  /** The full URL for the MCPServer endpoint. Example: "https://api.example.com/mcp". */
  @JsonProperty("url")
  public abstract Optional<String> url();

  /** Instantiates a builder for StreamableHttpTransport. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_StreamableHttpTransport.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for StreamableHttpTransport. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `StreamableHttpTransport.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_StreamableHttpTransport.Builder();
    }

    /**
     * Setter for headers.
     *
     * <p>headers: Optional: Fields for authentication headers, timeouts, etc., if needed.
     */
    @JsonProperty("headers")
    public abstract Builder headers(Map<String, String> headers);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder headers(Optional<Map<String, String>> headers);

    /** Clears the value of headers field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearHeaders() {
      return headers(Optional.empty());
    }

    /**
     * Setter for sseReadTimeout.
     *
     * <p>sseReadTimeout: Timeout for SSE read operations.
     */
    @JsonProperty("sseReadTimeout")
    public abstract Builder sseReadTimeout(Duration sseReadTimeout);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder sseReadTimeout(Optional<Duration> sseReadTimeout);

    /** Clears the value of sseReadTimeout field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearSseReadTimeout() {
      return sseReadTimeout(Optional.empty());
    }

    /**
     * Setter for terminateOnClose.
     *
     * <p>terminateOnClose: Whether to close the client session when the transport closes.
     */
    @JsonProperty("terminateOnClose")
    public abstract Builder terminateOnClose(boolean terminateOnClose);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder terminateOnClose(Optional<Boolean> terminateOnClose);

    /** Clears the value of terminateOnClose field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTerminateOnClose() {
      return terminateOnClose(Optional.empty());
    }

    /**
     * Setter for timeout.
     *
     * <p>timeout: HTTP timeout for regular operations.
     */
    @JsonProperty("timeout")
    public abstract Builder timeout(Duration timeout);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder timeout(Optional<Duration> timeout);

    /** Clears the value of timeout field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearTimeout() {
      return timeout(Optional.empty());
    }

    /**
     * Setter for url.
     *
     * <p>url: The full URL for the MCPServer endpoint. Example: "https://api.example.com/mcp".
     */
    @JsonProperty("url")
    public abstract Builder url(String url);

    @ExcludeFromGeneratedCoverageReport
    abstract Builder url(Optional<String> url);

    /** Clears the value of url field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearUrl() {
      return url(Optional.empty());
    }

    public abstract StreamableHttpTransport build();
  }

  /** Deserializes a JSON string to a StreamableHttpTransport object. */
  @ExcludeFromGeneratedCoverageReport
  public static StreamableHttpTransport fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, StreamableHttpTransport.class);
  }
}
