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

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.ImmutableMap.toImmutableMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.base.Ascii;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.ClientOptions;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.HttpRetryOptions;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;
import java.util.stream.Stream;
import okhttp3.Dispatcher;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.jspecify.annotations.Nullable;

/** Interface for an API client which issues HTTP requests to the GenAI APIs. */
abstract class ApiClient {

  // {x-version-update-start:google-genai:released}
  private static final String SDK_VERSION = "1.28.0";
  // {x-version-update-end:google-genai:released}
  private static final Logger logger = Logger.getLogger(ApiClient.class.getName());

  private static final ImmutableSet<String> METHODS_WITH_BODY =
      ImmutableSet.of("POST", "PATCH", "PUT");

  private static final ImmutableSet<String> VALID_HTTP_METHODS =
      ImmutableSet.<String>builder().addAll(METHODS_WITH_BODY).add("GET").add("DELETE").build();

  private static Optional<String> geminiBaseUrl = Optional.empty();
  private static Optional<String> vertexBaseUrl = Optional.empty();

  final OkHttpClient httpClient;
  HttpOptions httpOptions;
  final boolean vertexAI;
  final Optional<ClientOptions> clientOptions;
  // For Gemini APIs
  final Optional<String> apiKey;
  // For Vertex AI APIs
  final Optional<String> project;
  final Optional<String> location;
  final Optional<GoogleCredentials> credentials;

  /** Constructs an ApiClient for Google AI APIs. */
  protected ApiClient(
      Optional<String> apiKey,
      Optional<HttpOptions> customHttpOptions,
      Optional<ClientOptions> clientOptions) {
    checkNotNull(apiKey, "API Key cannot be null");
    checkNotNull(customHttpOptions, "customHttpOptions cannot be null");
    checkNotNull(clientOptions, "clientOptions cannot be null");

    try {
      this.apiKey = Optional.of(apiKey.orElse(getApiKeyFromEnv()));
    } catch (NullPointerException e) {
      throw new IllegalArgumentException(
          "API key must either be provided or set in the environment variable"
              + " GOOGLE_API_KEY or GEMINI_API_KEY. If both are set, GOOGLE_API_KEY will be used.",
          e);
    }

    this.project = Optional.empty();
    this.location = Optional.empty();
    this.credentials = Optional.empty();
    this.vertexAI = false;
    this.clientOptions = clientOptions;

    this.httpOptions = defaultHttpOptions(/* vertexAI= */ false, this.location, this.apiKey);

    if (customHttpOptions.isPresent()) {
      this.httpOptions = mergeHttpOptions(customHttpOptions.get());
    }

    this.httpClient = createHttpClient(httpOptions, clientOptions);
  }

  ApiClient(
      Optional<String> apiKey,
      Optional<String> project,
      Optional<String> location,
      Optional<GoogleCredentials> credentials,
      Optional<HttpOptions> customHttpOptions,
      Optional<ClientOptions> clientOptions) {
    checkNotNull(apiKey, "API Key cannot be null");
    checkNotNull(project, "project cannot be null");
    checkNotNull(location, "location cannot be null");
    checkNotNull(credentials, "credentials cannot be null");
    checkNotNull(customHttpOptions, "customHttpOptions cannot be null");
    checkNotNull(clientOptions, "clientOptions cannot be null");

    ImmutableMap<String, String> environmentVariables = defaultEnvironmentVariables();

    // Retrieve implicitly set values from the environment.
    String envApiKeyValue = getApiKeyFromEnv();
    String envProjectValue = environmentVariables.get("project");
    String envLocationValue = environmentVariables.get("location");

    // Constructor arguments take priority over environment variables.
    String apiKeyValue = apiKey.orElse(envApiKeyValue);
    String projectValue = project.orElse(envProjectValue);
    String locationValue = location.orElse(envLocationValue);

    // Has environment variable values.
    boolean hasEnvApiKeyValue = envApiKeyValue != null && !envApiKeyValue.isEmpty();
    boolean hasEnvProjectValue = envProjectValue != null && !envProjectValue.isEmpty();
    boolean hasEnvLocationValue = envLocationValue != null && !envLocationValue.isEmpty();

    // Constructor arguments.
    boolean hasApiKey = apiKey != null && apiKey.isPresent();
    boolean hasCredentials = credentials != null && credentials.isPresent();
    boolean hasProject = project != null && project.isPresent();
    boolean hasLocation = location != null && location.isPresent();

    // Validate constructor arguments combinations.
    if (hasProject && hasApiKey) {
      throw new IllegalArgumentException(
          "For Vertex AI APIs, project and API key are mutually exclusive in the client"
              + " initializer. Please provide only one of them.");
    }

    if (hasLocation && hasApiKey) {
      throw new IllegalArgumentException(
          "For Vertex AI APIs, location and API key are mutually exclusive in the client"
              + " initializer. Please provide only one of them.");
    }

    if (hasCredentials && hasApiKey) {
      throw new IllegalArgumentException(
          "For Vertex AI APIs, API key cannot be set together with credentials. Please provide"
              + " only one of them.");
    }

    // Handle when to use Vertex AI in express mode (api key).
    // Explicit initializer arguments are already validated above.
    if (hasCredentials && hasEnvApiKeyValue) {
      logger.warning(
          "Warning: The user provided Google Cloud credentials will take precedence over the API"
              + " key from the environment variable.");
      apiKeyValue = null;
    }
    if (hasApiKey && (hasEnvProjectValue || hasEnvLocationValue)) {
      // Explicit API key takes precedence over implicit project/location.
      logger.warning(
          "Warning: The user provided Vertex AI API key will take precedence over the"
              + " project/location from the environment variables.");
      projectValue = null;
      locationValue = null;
    } else if ((hasProject || hasLocation) && hasEnvApiKeyValue) {
      // Explicit project/location takes precedence over implicit API key.
      logger.warning(
          "Warning: The user provided project/location will take precedence over the API key from"
              + " the environment variable.");
      apiKeyValue = null;
    } else if ((hasEnvProjectValue || hasEnvLocationValue) && hasEnvApiKeyValue) {
      // Implicit project/location takes precedence over implicit API key.
      logger.warning(
          "Warning: The project/location from the environment variables will take precedence over"
              + " the API key from the environment variable.");
      apiKeyValue = null;
    }

    if (locationValue == null && apiKeyValue == null) {
      locationValue = "global";
    }

    this.apiKey = Optional.ofNullable(apiKeyValue);
    this.project = Optional.ofNullable(projectValue);
    this.location = Optional.ofNullable(locationValue);

    // Validate that either project and location or API key is set.
    if (!(this.project.isPresent() || this.apiKey.isPresent())) {
      throw new IllegalArgumentException(
          "For Vertex AI APIs, either project or API key must be set.");
    }

    // Only set credentials if using project/location.
    this.credentials =
        !this.project.isPresent()
            ? Optional.empty()
            : Optional.of(credentials.orElseGet(() -> defaultCredentials()));

    this.clientOptions = clientOptions;

    this.httpOptions = defaultHttpOptions(/* vertexAI= */ true, this.location, this.apiKey);

    if (customHttpOptions.isPresent()) {
      this.httpOptions = mergeHttpOptions(customHttpOptions.get());
    }
    this.vertexAI = true;
    this.httpClient = createHttpClient(httpOptions, clientOptions);
  }

  private OkHttpClient createHttpClient(
      HttpOptions httpOptions, Optional<ClientOptions> clientOptions) {
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    // Remove timeouts by default (OkHttp has a default of 10 seconds)
    builder.connectTimeout(Duration.ofMillis(0));
    builder.readTimeout(Duration.ofMillis(0));
    builder.writeTimeout(Duration.ofMillis(0));

    httpOptions
        .timeout()
        .ifPresent(timeout -> builder.callTimeout(Duration.ofMillis(timeout)));

    HttpRetryOptions retryOptions =
        httpOptions.retryOptions().orElse(HttpRetryOptions.builder().build());
    builder.addInterceptor(new RetryInterceptor(retryOptions));

    clientOptions.ifPresent(
        options -> {
          Dispatcher dispatcher = new Dispatcher();
          options.maxConnections().ifPresent(dispatcher::setMaxRequests);
          options.maxConnectionsPerHost().ifPresent(dispatcher::setMaxRequestsPerHost);
          builder.dispatcher(dispatcher);
        });

    return builder.build();
  }

  /** Builds a HTTP request given the http method, path, and request json string. */
  @SuppressWarnings("unchecked")
  protected Request buildRequest(
      String httpMethod,
      String path,
      String requestJson,
      Optional<HttpOptions> requestHttpOptions) {
    String capitalizedHttpMethod = Ascii.toUpperCase(httpMethod);
    boolean queryBaseModel =
        capitalizedHttpMethod.equals("GET") && path.startsWith("publishers/google/models");
    if (this.vertexAI()
        && !this.apiKey.isPresent()
        && !path.startsWith("projects/")
        && !queryBaseModel) {
      path =
          String.format("projects/%s/locations/%s/", this.project.get(), this.location.get())
              + path;
    }

    HttpOptions mergedHttpOptions = mergeHttpOptions(requestHttpOptions.orElse(null));

    String requestUrl;

    String baseUrl =
        mergedHttpOptions
            .baseUrl()
            .orElseThrow(() -> new IllegalStateException("baseUrl is required."));
    if (baseUrl.endsWith("/")) {
      // Sometimes users set the base URL with a trailing slash, so we need to remove it.
      baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
    }
    String apiVersion =
        mergedHttpOptions
            .apiVersion()
            .orElseThrow(() -> new IllegalStateException("apiVersion is required."));

    if (apiVersion.isEmpty()) {
      requestUrl = String.format("%s/%s", baseUrl, path);
    } else {
      requestUrl = String.format("%s/%s/%s", baseUrl, apiVersion, path);
    }

    if (!VALID_HTTP_METHODS.contains(capitalizedHttpMethod)) {
      throw new IllegalArgumentException("Unsupported HTTP method: " + capitalizedHttpMethod);
    }

    ObjectMapper objectMapper = new ObjectMapper();
    RequestBody body;
    if (METHODS_WITH_BODY.contains(capitalizedHttpMethod)) {
      body = RequestBody.create(requestJson, MediaType.parse("application/json"));
    } else {
      body = null;
    }

    if (mergedHttpOptions.extraBody().isPresent() && body != null) {
      try {
        Map<String, Object> requestBodyMap = objectMapper.readValue(requestJson, Map.class);
        mergeMaps(requestBodyMap, mergedHttpOptions.extraBody().get());
        requestJson = objectMapper.writeValueAsString(requestBodyMap);
        body = RequestBody.create(requestJson, MediaType.parse("application/json"));
      } catch (JsonProcessingException e) {
        logger.warning("Failed to merge extraBody into request body: " + e.getMessage());
        // If merging fails, proceed with the original request body
        body = RequestBody.create(requestJson, MediaType.parse("application/json"));
      }
    } else if (mergedHttpOptions.extraBody().isPresent()) {
      logger.warning(
          "HttpOptions.extraBody is set, but the HTTP method does not support a request body. "
              + "The extraBody will be ignored.");
    }

    Request.Builder requestBuilder =
        new Request.Builder().url(requestUrl).method(capitalizedHttpMethod, body);

    requestHttpOptions.ifPresent(
        httpOptions -> {
          if (httpOptions.retryOptions().isPresent()) {
            requestBuilder.tag(HttpRetryOptions.class, mergedHttpOptions.retryOptions().get());
          }
        });

    setHeaders(requestBuilder, mergedHttpOptions);
    return requestBuilder.build();
  }

  /** Builds a HTTP request given the http method, url, and request bytes. */
  protected Request buildRequest(
      String httpMethod,
      String url,
      byte[] requestBytes,
      Optional<HttpOptions> requestHttpOptions) {
    HttpOptions mergedHttpOptions = mergeHttpOptions(requestHttpOptions.orElse(null));
    if (httpMethod.equalsIgnoreCase("POST")) {
      RequestBody body =
          RequestBody.create(requestBytes, MediaType.get("application/octet-stream"));
      Request.Builder requestBuilder = new Request.Builder().url(url).post(body);
      requestHttpOptions.ifPresent(
          httpOptions -> {
            if (httpOptions.retryOptions().isPresent()) {
              requestBuilder.tag(HttpRetryOptions.class, mergedHttpOptions.retryOptions().get());
            }
          });
      setHeaders(requestBuilder, mergedHttpOptions);
      return requestBuilder.build();
    } else {
      throw new IllegalArgumentException(
          "The request method with bytes is only supported for POST. Unsupported HTTP method: "
              + httpMethod);
    }
  }

  /** Sets the required headers (including auth) on the request object. */
  private void setHeaders(Request.Builder request, HttpOptions requestHttpOptions) {
    for (Map.Entry<String, String> header :
        requestHttpOptions.headers().orElse(ImmutableMap.of()).entrySet()) {
      request.header(header.getKey(), header.getValue());
    }

    if (apiKey.isPresent()) {
      // Sets API key for Gemini Developer API or Vertex AI Express mode
      request.header("x-goog-api-key", apiKey.get());
    } else {
      GoogleCredentials cred =
          credentials.orElseThrow(() -> new IllegalStateException("credentials is required"));
      try {
        cred.refreshIfExpired();
      } catch (IOException e) {
        throw new GenAiIOException("Failed to refresh credentials.", e);
      }
      String accessToken = cred.getAccessToken().getTokenValue();
      request.header("Authorization", "Bearer " + accessToken);

      if (cred.getQuotaProjectId() != null) {
        request.header("x-goog-user-project", cred.getQuotaProjectId());
      }
    }
  }

  /** Sends a Http request given the http method, path, and request json string. */
  public abstract ApiResponse request(
      String httpMethod, String path, String requestJson, Optional<HttpOptions> httpOptions);

  /** Sends a Http request given the http method, path, and request bytes. */
  public abstract ApiResponse request(
      String httpMethod, String path, byte[] requestBytes, Optional<HttpOptions> httpOptions);

  /**
   * Sends an asynchronous Http request given the http method, path, request json string, and http
   * options.
   */
  public abstract CompletableFuture<ApiResponse> asyncRequest(
      String httpMethod, String path, String requestJson, Optional<HttpOptions> httpOptions);

  /**
   * Sends an asynchronous Http request given the http method, path, request bytes, and http
   * options.
   */
  public abstract CompletableFuture<ApiResponse> asyncRequest(
      String httpMethod, String path, byte[] requestBytes, Optional<HttpOptions> httpOptions);

  /** Returns the library version. */
  static String libraryVersion() {
    // TODO: Automate revisions to the SDK library version.
    String libraryLabel = String.format("google-genai-sdk/%s", SDK_VERSION);
    String languageLabel = "gl-java/" + System.getProperty("java.version");
    return libraryLabel + " " + languageLabel;
  }

  /** Returns whether the client is using Vertex AI APIs. */
  public boolean vertexAI() {
    return vertexAI;
  }

  /** Returns the project ID for Vertex AI APIs. */
  public @Nullable String project() {
    return project.orElse(null);
  }

  /** Returns the location for Vertex AI APIs. */
  public @Nullable String location() {
    return location.orElse(null);
  }

  /** Returns the API key for Google AI APIs. */
  public @Nullable String apiKey() {
    return apiKey.orElse(null);
  }

  /** Returns the HttpClient for API calls. */
  OkHttpClient httpClient() {
    return httpClient;
  }

  /**
   * Merges two maps recursively. If a key exists in both maps, the value from `source` overwrites
   * the value in `target`. If the value is a list, then update the whole list. A warning is logged
   * if the types of the values for the same key are different.
   *
   * @param target The target map to merge into.
   * @param source The source map to merge from.
   */
  @SuppressWarnings("unchecked")
  private void mergeMaps(Map<String, Object> target, Map<String, Object> source) {
    for (Map.Entry<String, Object> entry : source.entrySet()) {
      String key = entry.getKey();
      Object sourceValue = entry.getValue();

      if (target.containsKey(key)) {
        Object targetValue = target.get(key);

        if (targetValue instanceof Map && sourceValue instanceof Map) {
          // Both values are maps, recursively merge them
          mergeMaps((Map<String, Object>) targetValue, (Map<String, Object>) sourceValue);
        } else if (targetValue instanceof List && sourceValue instanceof List) {
          // Both values are lists, replace the target list with the source list
          target.put(key, sourceValue);
        } else {
          // Values are not both maps or both lists, check if they have the same type
          if (targetValue.getClass() != sourceValue.getClass()) {
            logger.warning(
                String.format(
                    "Type mismatch for key '%s'. Original type: %s, new type: %s.  Overwriting"
                        + " with the new value.",
                    key, targetValue.getClass().getName(), sourceValue.getClass().getName()));
          }
          target.put(key, sourceValue);
        }
      } else {
        // Key does not exist in target, add it
        target.put(key, sourceValue);
      }
    }
  }

  private Optional<Map<String, String>> getTimeoutHeader(HttpOptions httpOptionsToApply) {
    if (httpOptionsToApply.timeout().isPresent()) {
      int timeoutInSeconds = (int) Math.ceil((double) httpOptionsToApply.timeout().get() / 1000.0);
      // TODO(b/329147724): Document the usage of X-Server-Timeout header.
      return Optional.of(ImmutableMap.of("X-Server-Timeout", Integer.toString(timeoutInSeconds)));
    }
    return Optional.empty();
  }

  /**
   * Merges the http options to the client's http options.
   *
   * @param httpOptionsToApply the http options to apply
   * @return the merged http options
   */
  HttpOptions mergeHttpOptions(HttpOptions httpOptionsToApply) {
    if (httpOptionsToApply == null) {
      return this.httpOptions;
    }
    HttpOptions.Builder mergedHttpOptionsBuilder = this.httpOptions.toBuilder();

    httpOptionsToApply.baseUrl().ifPresent(mergedHttpOptionsBuilder::baseUrl);
    httpOptionsToApply.apiVersion().ifPresent(mergedHttpOptionsBuilder::apiVersion);
    httpOptionsToApply.timeout().ifPresent(mergedHttpOptionsBuilder::timeout);
    httpOptionsToApply.extraBody().ifPresent(mergedHttpOptionsBuilder::extraBody);
    httpOptionsToApply.retryOptions().ifPresent(mergedHttpOptionsBuilder::retryOptions);

    if (httpOptionsToApply.headers().isPresent()) {
      Stream<Map.Entry<String, String>> headersStream =
          Stream.concat(
              Stream.concat(
                  this.httpOptions.headers().orElse(ImmutableMap.of()).entrySet().stream(),
                  getTimeoutHeader(httpOptionsToApply)
                      .orElse(ImmutableMap.of())
                      .entrySet()
                      .stream()),
              httpOptionsToApply.headers().orElse(ImmutableMap.of()).entrySet().stream());
      Map<String, String> mergedHeaders =
          headersStream.collect(
              toImmutableMap(Map.Entry::getKey, Map.Entry::getValue, (val1, val2) -> val2));
      mergedHttpOptionsBuilder.headers(mergedHeaders);
    }

    return mergedHttpOptionsBuilder.build();
  }

  static HttpOptions defaultHttpOptions(
      boolean vertexAI, Optional<String> location, Optional<String> apiKey) {
    ImmutableMap.Builder<String, String> defaultHeaders = ImmutableMap.builder();
    defaultHeaders.put("Content-Type", "application/json");
    defaultHeaders.put("user-agent", libraryVersion());
    defaultHeaders.put("x-goog-api-client", libraryVersion());

    HttpOptions.Builder defaultHttpOptionsBuilder =
        HttpOptions.builder().headers(defaultHeaders.build());

    ImmutableMap<String, String> defaultEnvironmentVariables = defaultEnvironmentVariables();
    if (vertexAI) {
      defaultHttpOptionsBuilder.apiVersion("v1beta1");
      String defaultBaseUrl =
          vertexBaseUrl.orElseGet(() -> defaultEnvironmentVariables.get("vertexBaseUrl"));
      if (defaultBaseUrl != null) {
        defaultHttpOptionsBuilder.baseUrl(defaultBaseUrl);
      } else if (apiKey.isPresent() || location.get().equalsIgnoreCase("global")) {
        defaultHttpOptionsBuilder.baseUrl("https://aiplatform.googleapis.com");
      } else {
        defaultHttpOptionsBuilder.baseUrl(
            String.format("https://%s-aiplatform.googleapis.com", location.get()));
      }
    } else {
      defaultHttpOptionsBuilder.apiVersion("v1beta");
      String defaultBaseUrl =
          geminiBaseUrl.orElseGet(() -> defaultEnvironmentVariables.get("geminiBaseUrl"));
      if (defaultBaseUrl != null) {
        defaultHttpOptionsBuilder.baseUrl(defaultBaseUrl);
      } else {
        defaultHttpOptionsBuilder.baseUrl("https://generativelanguage.googleapis.com");
      }
    }

    return defaultHttpOptionsBuilder.build();
  }

  GoogleCredentials defaultCredentials() {
    try {
      return GoogleCredentials.getApplicationDefault()
          .createScoped("https://www.googleapis.com/auth/cloud-platform");
    } catch (IOException e) {
      throw new GenAiIOException(
          "Failed to get application default credentials, please explicitly provide credentials.",
          e);
    }
  }

  /** Returns the API key from defaultEnvironmentVariables. */
  static String getApiKeyFromEnv() {

    ImmutableMap<String, String> environmentVariables = defaultEnvironmentVariables();

    String googleApiKey = environmentVariables.get("googleApiKey");
    if (googleApiKey != null && googleApiKey.isEmpty()) {
      googleApiKey = null;
    }
    String geminiApiKey = environmentVariables.get("geminiApiKey");
    if (geminiApiKey != null && geminiApiKey.isEmpty()) {
      geminiApiKey = null;
    }
    if (googleApiKey != null && geminiApiKey != null) {
      logger.warning("Both GOOGLE_API_KEY and GEMINI_API_KEY are set. Using GOOGLE_API_KEY.");
    }
    if (googleApiKey != null) {
      return googleApiKey;
    }
    return geminiApiKey;
  }

  /**
   * Returns the default environment variables for the client. Supported environment variables:
   *
   * <ul>
   *   <li>googleApiKey -> GOOGLE_API_KEY environment variable.
   *   <li>geminiApiKey -> GEMINI_API_KEY environment variable.
   *   <li>project -> GOOGLE_CLOUD_PROJECT: Project ID for Vertex AI APIs.
   *   <li>location -> GOOGLE_CLOUD_LOCATION: Location for Vertex AI APIs.
   *   <li>vertexAI -> GOOGLE_GENAI_USE_VERTEXAI: Whether to use Vertex AI APIs(true or false).
   *   <li>geminiBaseUrl -> GOOGLE_GEMINI_BASE_URL: Base URL for Gemini APIs.
   *   <li>vertexBaseUrl -> GOOGLE_VERTEX_BASE_URL: Base URL for Vertex AI APIs.
   * </ul>
   */
  static ImmutableMap<String, String> defaultEnvironmentVariables() {
    ImmutableMap.Builder<String, String> mapBuilder = ImmutableMap.builder();
    String value;
    value = System.getenv("GOOGLE_API_KEY");
    if (value != null && !value.isEmpty()) {
      mapBuilder.put("googleApiKey", value);
    }
    value = System.getenv("GEMINI_API_KEY");
    if (value != null && !value.isEmpty()) {
      mapBuilder.put("geminiApiKey", value);
    }
    value = System.getenv("GOOGLE_CLOUD_PROJECT");
    if (value != null && !value.isEmpty()) {
      mapBuilder.put("project", value);
    }
    value = System.getenv("GOOGLE_CLOUD_LOCATION");
    if (value != null && !value.isEmpty()) {
      mapBuilder.put("location", value);
    }
    value = System.getenv("GOOGLE_GENAI_USE_VERTEXAI");
    if (value != null && !value.isEmpty()) {
      mapBuilder.put("vertexAI", value);
    }
    value = System.getenv("GOOGLE_GEMINI_BASE_URL");
    if (value != null && !value.isEmpty()) {
      mapBuilder.put("geminiBaseUrl", value);
    }
    value = System.getenv("GOOGLE_VERTEX_BASE_URL");
    if (value != null && !value.isEmpty()) {
      mapBuilder.put("vertexBaseUrl", value);
    }
    return mapBuilder.buildOrThrow();
  }

  /** Overrides the base URLs for the Gemini API and/or Vertex AI API. */
  static void setDefaultBaseUrls(Optional<String> geminiBaseUrl, Optional<String> vertexBaseUrl) {
    ApiClient.geminiBaseUrl = geminiBaseUrl;
    ApiClient.vertexBaseUrl = vertexBaseUrl;
  }
}
