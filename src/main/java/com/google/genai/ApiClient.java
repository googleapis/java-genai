package com.google.genai;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.auth.oauth2.GoogleCredentials;
import java.io.IOException;
import java.util.Optional;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

/** Interface for an API client which issues HTTP requests to the GenAI APIs. */
abstract class ApiClient {
  CloseableHttpClient httpClient;
  // For Google AI APIs
  final Optional<String> apiKey;
  // For Vertex AI APIs
  final Optional<String> project;
  final Optional<String> location;
  final Optional<GoogleCredentials> credentials;
  final HttpOptions httpOptions;
  final boolean vertexAI;

  /** Constructs an ApiClient for Google AI APIs. */
  ApiClient(Optional<String> apiKey, Optional<HttpOptions> customHttpOptions) {
    checkNotNull(apiKey, "API Key cannot be null");
    checkNotNull(customHttpOptions, "customHttpOptions cannot be null");

    try {
      this.apiKey = Optional.of(apiKey.orElse(System.getenv("GOOGLE_API_KEY")));
    } catch (NullPointerException e) {
      throw new IllegalArgumentException(
          "API key must either be provided or set in the environment variable" + " GOOGLE_API_KEY.",
          e);
    }

    HttpOptions httpOptions = customHttpOptions.orElse(HttpOptions.builder().build());
    this.httpOptions =
        HttpOptions.builder()
            .setBaseUrl(
                httpOptions.getBaseUrl().orElse("https://generativelanguage.googleapis.com/"))
            .setApiVersion(httpOptions.getApiVersion().orElse("v1beta"))
            .setHeaders(httpOptions.getHeaders())
            // Add Content-Type header
            .addHeader("Content-Type", "application/json")
            // Add library version headers
            .addHeader("user-agent", getLibraryVersion())
            .addHeader("x-goog-api-client", getLibraryVersion())
            .build();

    this.project = Optional.empty();
    this.location = Optional.empty();
    this.credentials = Optional.empty();
    this.vertexAI = false;
    this.httpClient = createHttpClient(httpOptions.getTimeout());
  }

  ApiClient(
      Optional<String> project,
      Optional<String> location,
      Optional<GoogleCredentials> credentials,
      Optional<HttpOptions> customHttpOptions) {
    checkNotNull(project, "project cannot be null");
    checkNotNull(location, "location cannot be null");
    checkNotNull(credentials, "credentials cannot be null");
    checkNotNull(customHttpOptions, "customHttpOptions cannot be null");

    try {
      this.project = Optional.of(project.orElse(System.getenv("GOOGLE_CLOUD_PROJECT")));
    } catch (NullPointerException e) {
      throw new IllegalArgumentException(
          "Project must either be provided or set in the environment variable"
              + " GOOGLE_CLOUD_PROJECT.",
          e);
    }
    if (this.project.get().isEmpty()) {
      throw new IllegalArgumentException("Project must not be empty.");
    }

    try {
      this.location = Optional.of(location.orElse(System.getenv("GOOGLE_CLOUD_LOCATION")));
    } catch (NullPointerException e) {
      throw new IllegalArgumentException(
          "Location must either be provided or set in the environment variable"
              + " GOOGLE_CLOUD_LOCATION.",
          e);
    }
    if (this.location.get().isEmpty()) {
      throw new IllegalArgumentException("Location must not be empty.");
    }

    try {
      this.credentials = Optional.of(credentials.orElse(GoogleCredentials.getApplicationDefault()));
    } catch (IOException e) {
      throw new IllegalArgumentException(
          "Failed to get application default credentials, please explicitly provide credentials.",
          e);
    }

    HttpOptions httpOptions = customHttpOptions.orElse(HttpOptions.builder().build());
    this.httpOptions =
        HttpOptions.builder()
            .setBaseUrl(
                httpOptions
                    .getBaseUrl()
                    .orElse(
                        String.format(
                            "https://%s-aiplatform.googleapis.com/", this.location.get())))
            .setApiVersion(httpOptions.getApiVersion().orElse("v1beta1"))
            .setHeaders(httpOptions.getHeaders())
            // Add Content-Type header
            .addHeader("Content-Type", "application/json")
            // Add library version headers
            .addHeader("user-agent", getLibraryVersion())
            .addHeader("x-goog-api-client", getLibraryVersion())
            .build();

    this.apiKey = Optional.empty();
    this.vertexAI = true;
    this.httpClient = createHttpClient(httpOptions.getTimeout());
  }

  private CloseableHttpClient createHttpClient(Optional<Integer> timeout) {
    if (!timeout.isPresent()) {
      return HttpClients.createDefault();
    }
    RequestConfig config =
        RequestConfig.custom()
            .setConnectTimeout(timeout.get())
            .setConnectionRequestTimeout(timeout.get())
            .setSocketTimeout(timeout.get())
            .build();
    return HttpClientBuilder.create().setDefaultRequestConfig(config).build();
  }

  /** Sends a Http Post request given the path and request json string. */
  public abstract ApiResponse post(String path, String requestJson) throws IOException;

  /** Returns the library version. */
  String getLibraryVersion() {
    // TODO: Automate revisions to the SDK library version.
    String libraryLabel = "google-genai-sdk/0.1.0";
    String languageLabel = "gl-java/" + System.getProperty("java.version");
    return libraryLabel + " " + languageLabel;
  }

  /** Returns whether the client is using Vertex AI APIs. */
  public boolean isVertexAI() {
    return vertexAI;
  }

  /** Returns the project ID for Vertex AI APIs. */
  public String getProject() {
    return project.get();
  }

  /** Returns the location for Vertex AI APIs. */
  public String getLocation() {
    return location.get();
  }

  /** Returns the API key for Google AI APIs. */
  public String getApiKey() {
    return apiKey.get();
  }

  /** Returns the HttpClient for API calls. */
  CloseableHttpClient getHttpClient() {
    return httpClient;
  }
}
