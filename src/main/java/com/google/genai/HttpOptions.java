package com.google.genai;

import com.google.auto.value.AutoValue;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/** HTTP options for the api client. */
@AutoValue
public abstract class HttpOptions {

  /** The base URL for the service endpoint. */
  public abstract Optional<String> getBaseUrl();

  /** The API version for the service endpoint. */
  public abstract Optional<String> getApiVersion();

  /** Additional HTTP headers for the request. */
  public abstract Map<String, String> getHeaders();

  /** Timeout for the HTTP requests in milliseconds. */
  public abstract Optional<Integer> getTimeout();

  /** Instantiates a builder for {@link HttpOptions}. */
  public static Builder builder() {
    return new AutoValue_HttpOptions.Builder().setHeaders(new HashMap<>());
  }

  /** Builder for {@link HttpOptions}. */
  @AutoValue.Builder
  public abstract static class Builder {

    /** Sets the base URL for the service endpoint. */
    public abstract Builder setBaseUrl(String baseUrl);

    /** Sets the API version for the service endpoint. */
    public abstract Builder setApiVersion(String apiVersion);

    /** Sets the additional HTTP headers for the request. */
    public abstract Builder setHeaders(Map<String, String> headers);

    /** Sets the timeout for the HTTP requests in milliseconds. */
    public abstract Builder setTimeout(Integer timeout);

    abstract Map<String, String> getHeaders();

    /** Adds an additional HTTP header for the request. */
    public Builder addHeader(String key, String value) {
      Map<String, String> headers = getHeaders();
      if (headers.containsKey(key)) {
        headers.put(key, headers.get(key) + " " + value);
      } else {
        headers.put(key, value);
      }
      return this;
    }

    /** Builds the {@link HttpOptions} instance. */
    public abstract HttpOptions build();
  }
}
