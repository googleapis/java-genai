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

import com.google.auth.oauth2.GoogleCredentials;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.HttpOptions;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/** Client class for GenAI. */
public final class Client implements AutoCloseable {
  private static Optional<String> geminiBaseUrl = Optional.empty();
  private static Optional<String> vertexBaseUrl = Optional.empty();

  public static Map<String, String> defaultEnvironmentVariables() {
    Map<String, String> variables = new HashMap<>();
    String value;
    value = System.getenv("GOOGLE_GENAI_USE_VERTEXAI");
    if (value != null) {
      variables.put("GOOGLE_GENAI_USE_VERTEXAI", value);
    }
    value = System.getenv("GOOGLE_GEMINI_BASE_URL");
    if (value != null) {
      variables.put("GOOGLE_GEMINI_BASE_URL", value);
    }
    value = System.getenv("GOOGLE_VERTEX_BASE_URL");
    if (value != null) {
      variables.put("GOOGLE_VERTEX_BASE_URL", value);
    }

    return variables;
  }

  /** Async class for GenAI. */
  public final class Async {
    public final AsyncModels models;
    public final AsyncOperations operations;
    public final AsyncLive live;
    public final AsyncFiles files;

    public Async(ApiClient apiClient) {
      this.models = new AsyncModels(apiClient);
      this.operations = new AsyncOperations(apiClient);
      this.live = new AsyncLive(apiClient);
      this.files = new AsyncFiles(apiClient);
    }
  }

  private final DebugConfig debugConfig;
  private final ApiClient apiClient;
  public final Models models;
  public final Operations operations;
  public final Chats chats;
  public final Files files;
  public final Async async;

  /** Builder for {@link Client}. */
  public static class Builder {
    private Optional<String> apiKey = Optional.empty();
    private Optional<String> project = Optional.empty();
    private Optional<String> location = Optional.empty();
    private Optional<GoogleCredentials> credentials = Optional.empty();
    private Optional<HttpOptions> httpOptions = Optional.empty();
    private Optional<Boolean> vertexAI = Optional.empty();
    private Optional<DebugConfig> debugConfig = Optional.empty();
    private Optional<Map<String, String>> environmentVariables = Optional.empty();

    /** Builds the {@link Client} instance. */
    public Client build() {
      return new Client(
          apiKey,
          project,
          location,
          credentials,
          httpOptions,
          vertexAI,
          debugConfig,
          environmentVariables);
    }

    /** Sets the API key for Google AI APIs. */
    public Builder apiKey(String apiKey) {
      checkNotNull(apiKey, "apiKey cannot be null");
      this.apiKey = Optional.of(apiKey);
      return this;
    }

    /** Sets the project ID for Vertex AI APIs. */
    public Builder project(String project) {
      checkNotNull(project, "project cannot be null");
      this.project = Optional.of(project);
      return this;
    }

    /** Sets the location for Vertex AI APIs. */
    public Builder location(String location) {
      checkNotNull(location, "location cannot be null");
      this.location = Optional.of(location);
      return this;
    }

    /** Sets the {@link GoogleCredentials} for Vertex AI APIs. */
    public Builder credentials(GoogleCredentials credentials) {
      checkNotNull(credentials, "credentials cannot be null");
      this.credentials = Optional.of(credentials);
      return this;
    }

    /** Sets the {@link HttpOptions} for the API client. */
    public Builder httpOptions(HttpOptions httpOptions) {
      checkNotNull(httpOptions, "httpOptions cannot be null");
      this.httpOptions = Optional.of(httpOptions);
      return this;
    }

    /** Sets whether to use Vertex AI APIs. */
    public Builder vertexAI(boolean vertexAI) {
      this.vertexAI = Optional.of(vertexAI);
      return this;
    }

    /**
     * Sets the {@link DebugConfig} for debugging or testing the Client. This is for internal use
     * only.
     */
    Builder debugConfig(DebugConfig debugConfig) {
      checkNotNull(debugConfig, "debugConfig cannot be null");
      this.debugConfig = Optional.of(debugConfig);
      return this;
    }

    /** Sets the environment variables for the API client. This is for internal use only. */
    Builder environmentVariables(Map<String, String> environmentVariables) {
      this.environmentVariables = Optional.of(environmentVariables);
      return this;
    }
  }

  /** Returns a {@link Builder} for {@link Client}. */
  public static Builder builder() {
    return new Builder();
  }

  /** Constructs a Client instance with environment variables. */
  public Client() {
    this(
        /* apiKey= */ Optional.empty(),
        /* project= */ Optional.empty(),
        /* location= */ Optional.empty(),
        /* credentials= */ Optional.empty(),
        /* httpOptions= */ Optional.empty(),
        /* vertexAI= */ Optional.empty(),
        /* debugConfig= */ Optional.empty(),
        /* environmentVariables= */ Optional.empty());
  }

  /**
   * Constructs a Client instance with the given parameters.
   *
   * @param apiKey Optional String for the <a href="https://ai.google.dev/gemini-api/docs/api-key">API key</a>.
   *     Google AI APIs only.
   * @param project Optional String for the project ID. Vertex AI APIs only.
   *     Find your <a href="https://cloud.google.com/resource-manager/docs/creating-managing-projects#identifying_projects">project ID</a>
   * @param location Optional String for the <a href="https://cloud.google.com/vertex-ai/generative-ai/docs/learn/locations">location</a>.
   *     Vertex AI APIs only.
   * @param credentials Optional {@link GoogleCredentials}. Vertex AI APIs only.
   * @param httpOptions Optional {@link HttpOptions} for sending HTTP requests.
   * @param vertexAI Optional Boolean for whether to use Vertex AI APIs. If not specified here nor
   *     in the environment variable, default to false.
   * @param debugConfig Optional {@link DebugConfig} for debugging or testing the Client.
   * @param environmentVariables Optional Map of environment variables.
   * @throws IllegalArgumentException if the project/location and API key are set together.
   */
  private Client(
      Optional<String> apiKey,
      Optional<String> project,
      Optional<String> location,
      Optional<GoogleCredentials> credentials,
      Optional<HttpOptions> httpOptions,
      Optional<Boolean> vertexAI,
      Optional<DebugConfig> debugConfig,
      Optional<Map<String, String>> environmentVariables) {
    checkNotNull(vertexAI, "vertexAI cannot be null");
    checkNotNull(debugConfig, "debugConfig cannot be null");
    if (!environmentVariables.isPresent()) {
      environmentVariables = Optional.of(defaultEnvironmentVariables());
    }

    boolean useVertexAI;
    if (vertexAI.isPresent()) {
      useVertexAI = vertexAI.get();
    } else {
      String envVar = environmentVariables.get().get("GOOGLE_GENAI_USE_VERTEXAI");
      useVertexAI = envVar != null && envVar.equalsIgnoreCase("true");
    }

    if (project.isPresent() || location.isPresent()) {
      if (apiKey.isPresent()) {
        throw new IllegalArgumentException(
            "Project/location and API key are mutually exclusive in the client initializer.");
      }
      if (!useVertexAI) {
        throw new IllegalArgumentException("Google AI APIs do not support project/location.");
      }
    }

    // TODO(jayceeli): Remove this check once we support EasyGCP.
    if (apiKey.isPresent() && useVertexAI) {
      throw new IllegalArgumentException("Vertex AI APIs do not support API key.");
    }

    Optional<String> baseUrl = Client.inferBaseUrl(useVertexAI, httpOptions, environmentVariables);
    if (baseUrl.isPresent()) {
      if (httpOptions.isPresent()) {
        httpOptions = Optional.of(httpOptions.get().toBuilder().baseUrl(baseUrl.get()).build());
      } else {
        httpOptions = Optional.of(HttpOptions.builder().baseUrl(baseUrl.get()).build());
      }
    }

    this.debugConfig = debugConfig.orElse(new DebugConfig());
    if (this.debugConfig.clientMode().equals("replay")
        || this.debugConfig.clientMode().equals("auto")) {
      System.out.println("Instantialing a client that will use Replay mode...");
      if (!useVertexAI) {
        System.out.println("Instantialing a replay client that will use Google AI APIs...");
        this.apiClient =
            new ReplayApiClient(
                /* apiKey= */ apiKey,
                /* httpOptions= */ httpOptions,
                this.debugConfig.replaysDirectory(),
                this.debugConfig.replayId(),
                this.debugConfig.clientMode());
      } else {
        System.out.println("Instantialing a replay client that will use Vertex AI APIs...");
        this.apiClient =
            new ReplayApiClient(
                /* project= */ project,
                /* location= */ location,
                /* credentials= */ credentials,
                /* httpOptions= */ httpOptions,
                this.debugConfig.replaysDirectory(),
                this.debugConfig.replayId(),
                this.debugConfig.clientMode());
      }
    } else {
      if (!useVertexAI) {
        this.apiClient = new HttpApiClient(/* apiKey= */ apiKey, /* httpOptions= */ httpOptions);
      } else {
        this.apiClient =
            new HttpApiClient(
                /* project= */ project,
                /* location= */ location,
                /* credentials= */ credentials,
                /* httpOptions= */ httpOptions);
      }
    }

    models = new Models(this.apiClient);
    operations = new Operations(this.apiClient);
    chats = new Chats(this.apiClient);
    async = new Async(this.apiClient);
    files = new Files(this.apiClient);
  }

  /** Returns whether the client is using Vertex AI APIs. */
  public boolean vertexAI() {
    return apiClient.vertexAI();
  }

  /** Returns the project ID for Vertex AI APIs. */
  public String project() {
    return apiClient.project();
  }

  /** Returns the location for Vertex AI APIs. */
  public String location() {
    return apiClient.location();
  }

  /** Returns the API key for Google AI APIs. */
  public String apiKey() {
    return apiClient.apiKey();
  }

  protected void setReplayId(String replayId) {
    if (this.apiClient instanceof ReplayApiClient) {
      ((ReplayApiClient) this.apiClient).initializeReplaySession(replayId);
    }
  }

  /** Returns the client mode. If it's "replay" or "auto", then the client is in testing mode. */
  String clientMode() {
    return debugConfig.clientMode();
  }

  /** Returns the base URL for the API client. */
  Optional<String> baseUrl() {
    if (apiClient.httpOptions.baseUrl().isPresent()) {
      return apiClient.httpOptions.baseUrl();
    }
    return Optional.empty();
  }

  /** Closes the Client instance together with its instantiated http client. */
  @Override
  public void close() {
    try {
      apiClient.httpClient().close();
    } catch (IOException e) {
      throw new GenAiIOException("Failed to close the HTTP client.", e);
    }
  }

  /**
   * Overrides the base URLs for the Gemini API and Vertex AI API.
   *
   * <p>Note: This function should be called before initializing the SDK. If the base URLs are set
   * after initializing the SDK, the base URLs will not be updated.
   */
  public static void setDefaultBaseUrls(
      Optional<String> geminiBaseUrl, Optional<String> vertexBaseUrl) {
    Client.geminiBaseUrl = geminiBaseUrl;
    Client.vertexBaseUrl = vertexBaseUrl;
  }

  /**
   * Returns the base URL for the Gemini API or Vertex AI API based on the following priority.
   *
   * <p>1. Base URL set via HttpOptions.
   *
   * <p>2. Base URL set via the latest call to setDefaultBaseUrls.
   *
   * <p>3. Base URL set via environment variables.
   */
  static Optional<String> inferBaseUrl(
      boolean vertexAI,
      Optional<HttpOptions> httpOptions,
      Optional<Map<String, String>> environmentVariables) {
    if (httpOptions.isPresent() && httpOptions.get().baseUrl().isPresent()) {
      return httpOptions.get().baseUrl();
    }

    if (vertexAI) {
      if (Client.vertexBaseUrl.isPresent()) {
        return Client.vertexBaseUrl;
      } else if (environmentVariables.isPresent()) {
        return Optional.ofNullable(environmentVariables.get().get("GOOGLE_VERTEX_BASE_URL"));
      }
    } else {
      if (Client.geminiBaseUrl.isPresent()) {
        return Client.geminiBaseUrl;
      } else if (environmentVariables.isPresent()) {
        return Optional.ofNullable(environmentVariables.get().get("GOOGLE_GEMINI_BASE_URL"));
      }
    }

    return Optional.empty();
  }
}
