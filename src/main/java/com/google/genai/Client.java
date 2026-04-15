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
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.interactions.core.ExperimentalGenAiApi;
// interactions:strip_begin
import com.google.genai.interactions.services.async.InteractionServiceAsync;
import com.google.genai.interactions.services.async.InteractionServiceAsyncImpl;
import com.google.genai.interactions.services.blocking.InteractionService;
import com.google.genai.interactions.services.blocking.InteractionServiceImpl;
// interactions:strip_end
import com.google.common.collect.ImmutableMap;
import com.google.genai.types.ClientOptions;
import com.google.genai.types.HttpOptions;
import java.util.Optional;
import java.util.logging.Logger;

/** Client class for GenAI. This class is thread-safe. */
public final class Client implements AutoCloseable {

  private static final Logger logger = Logger.getLogger(Client.class.getName());

  /** Async class for GenAI. */
  public final class Async {
    public final AsyncModels models;
    public final AsyncBatches batches;
    public final AsyncCaches caches;
    public final AsyncOperations operations;
    public final AsyncLive live;
    public final AsyncChats chats;
    public final AsyncFiles files;
    public final AsyncTokens authTokens;
    public final AsyncTunings tunings;
    public final AsyncFileSearchStores fileSearchStores;

    // interactions:strip_begin
    /** **EXPERIMENTAL:** The interactions service is experimental and subject to change. */
    @ExperimentalGenAiApi
    public final InteractionServiceAsync interactions;

    // interactions:strip_end

    public Async(ApiClient apiClient) {
      this.models = new AsyncModels(apiClient);
      this.batches = new AsyncBatches(apiClient);
      this.caches = new AsyncCaches(apiClient);
      this.operations = new AsyncOperations(apiClient);
      this.live = new AsyncLive(apiClient);
      this.files = new AsyncFiles(apiClient);
      this.chats = new AsyncChats(apiClient);
      this.authTokens = new AsyncTokens(apiClient);
      this.tunings = new AsyncTunings(apiClient);
      this.fileSearchStores = new AsyncFileSearchStores(apiClient);
      // interactions:strip_begin
      this.interactions = new InteractionServiceAsyncImpl(apiClient.interactionsClientOptions);
      // interactions:strip_end
    }
  }

  private final DebugConfig debugConfig;
  private final ApiClient apiClient;
  public final Models models;
  public final Batches batches;
  public final Caches caches;
  public final Operations operations;
  public final Chats chats;
  public final Files files;
  public final Async async;
  public final Tokens authTokens;
  public final Tunings tunings;
  public final FileSearchStores fileSearchStores;

  // interactions:strip_begin
  /** **EXPERIMENTAL:** The interactions service is experimental and subject to change. */
  @ExperimentalGenAiApi
  public final InteractionService interactions;

  // interactions:strip_end

  /** Builder for {@link Client}. */
  public static class Builder {
    private Optional<String> apiKey = Optional.empty();
    private Optional<String> project = Optional.empty();
    private Optional<String> location = Optional.empty();
    private Optional<GoogleCredentials> credentials = Optional.empty();
    private Optional<ClientOptions> clientOptions = Optional.empty();
    private Optional<HttpOptions> httpOptions = Optional.empty();
    private Optional<Boolean> enterprise = Optional.empty();
    private Optional<Boolean> vertexAI = Optional.empty();
    private Optional<DebugConfig> debugConfig = Optional.empty();

    /** Builds the {@link Client} instance. */
    public Client build() {
      return new Client(
          apiKey,
          project,
          location,
          credentials,
          httpOptions,
          clientOptions,
          enterprise,
          vertexAI,
          debugConfig);
    }

    /** Sets the API key for Gemini API. */
    @CanIgnoreReturnValue
    public Builder apiKey(String apiKey) {
      checkNotNull(apiKey, "apiKey cannot be null");
      this.apiKey = Optional.of(apiKey);
      return this;
    }

    /** Sets the project ID for Vertex AI APIs. */
    @CanIgnoreReturnValue
    public Builder project(String project) {
      checkNotNull(project, "project cannot be null");
      this.project = Optional.of(project);
      return this;
    }

    /** Sets the location for Vertex AI APIs. */
    @CanIgnoreReturnValue
    public Builder location(String location) {
      checkNotNull(location, "location cannot be null");
      this.location = Optional.of(location);
      return this;
    }

    /** Sets the {@link GoogleCredentials} for Vertex AI APIs. */
    @CanIgnoreReturnValue
    public Builder credentials(GoogleCredentials credentials) {
      checkNotNull(credentials, "credentials cannot be null");
      this.credentials = Optional.of(credentials);
      return this;
    }

    /** Sets the {@link ClientOptions} for the API client. */
    @CanIgnoreReturnValue
    public Builder clientOptions(ClientOptions clientOptions) {
      checkNotNull(clientOptions, "clientOptions cannot be null");
      this.clientOptions = Optional.of(clientOptions);
      return this;
    }

    /** Sets the {@link HttpOptions} for the API client. */
    @CanIgnoreReturnValue
    public Builder httpOptions(HttpOptions httpOptions) {
      checkNotNull(httpOptions, "httpOptions cannot be null");
      this.httpOptions = Optional.of(httpOptions);
      return this;
    }

    /** Sets whether to use Gemini Enterprise Agent Platform.
     * When both enterprise and vertexAI are set, and they have different values, an IllegalArgumentException will be thrown.
     */
    @CanIgnoreReturnValue
    public Builder enterprise(boolean enterprise) {
      this.enterprise = Optional.of(enterprise);
      return this;
    }

    /** Sets whether to use Vertex AI APIs.
     * When both enterprise and vertexAI are set, and they have different values, an IllegalArgumentException will be thrown.
     */
    @CanIgnoreReturnValue
    public Builder vertexAI(boolean vertexAI) {
      this.vertexAI = Optional.of(vertexAI);
      return this;
    }

    /**
     * Sets the {@link DebugConfig} for debugging or testing the Client. This is for internal use
     * only.
     */
    @CanIgnoreReturnValue
    Builder debugConfig(DebugConfig debugConfig) {
      checkNotNull(debugConfig, "debugConfig cannot be null");
      this.debugConfig = Optional.of(debugConfig);
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
        /* clientOptions= */ Optional.empty(),
        /* enterprise= */ Optional.empty(),
        /* vertexAI= */ Optional.empty(),
        /* debugConfig= */ Optional.empty());
  }

  /**
   * Constructs a Client instance with the given parameters.
   *
   * @param apiKey Optional String for the <a
   *     href="https://ai.google.dev/gemini-api/docs/api-key">API key</a>. Gemini API only.
   * @param project Optional String for the project ID. Vertex AI APIs only. Find your <a
   *     href="https://cloud.google.com/resource-manager/docs/creating-managing-projects#identifying_projects">project
   *     ID</a>
   * @param location Optional String for the <a
   *     href="https://cloud.google.com/vertex-ai/generative-ai/docs/learn/locations">location</a>.
   *     Vertex AI APIs only.
   * @param credentials Optional {@link GoogleCredentials}. Vertex AI APIs only.
   * @param httpOptions Optional {@link HttpOptions} for sending HTTP requests.
   * @param clientOptions Optional {@link ClientOptions} for the API client.
   * @param vertexAI Optional Boolean for whether to use Vertex AI APIs. If not specified here nor
   *     in the environment variable, default to false.
   * @param debugConfig Optional {@link DebugConfig} for debugging or testing the Client.
   * @throws IllegalArgumentException if the project/location and API key are set together.
   */
  private Client(
      Optional<String> apiKey,
      Optional<String> project,
      Optional<String> location,
      Optional<GoogleCredentials> credentials,
      Optional<HttpOptions> httpOptions,
      Optional<ClientOptions> clientOptions,
      Optional<Boolean> enterprise,
      Optional<Boolean> vertexAI,
      Optional<DebugConfig> debugConfig) {
    checkNotNull(vertexAI, "vertexAI cannot be null");
    checkNotNull(enterprise, "enterprise cannot be null");
    checkNotNull(debugConfig, "debugConfig cannot be null");

    if (enterprise.isPresent() && vertexAI.isPresent() && !enterprise.get().equals(vertexAI.get())) {
      throw new IllegalArgumentException(
          "enterprise and vertexAI flags have conflicting values, please set enterprise value only.");
    }

    boolean useVertexAI;
    if (enterprise.isPresent()) {
      useVertexAI = enterprise.get();
    } else if (vertexAI.isPresent()) {
      useVertexAI = vertexAI.get();
    } else {
      ImmutableMap<String, String> envVars = ApiClient.defaultEnvironmentVariables();
      String enterpriseEnv = envVars.get("enterprise");
      String vertexEnv = envVars.get("vertexAI");

      boolean enterpriseEnvPresent = enterpriseEnv != null;
      boolean vertexEnvPresent = vertexEnv != null;

      if (enterpriseEnvPresent && vertexEnvPresent && !enterpriseEnv.equalsIgnoreCase(vertexEnv)) {
        logger.warning(
            "Warning: Both GOOGLE_GENAI_USE_ENTERPRISE and GOOGLE_GENAI_USE_VERTEXAI are set with conflicting values. The value of GOOGLE_GENAI_USE_ENTERPRISE will be used.");
      }

      if (enterpriseEnvPresent) {
        useVertexAI = enterpriseEnv.equalsIgnoreCase("true");
      } else if (vertexEnvPresent) {
        useVertexAI = vertexEnv.equalsIgnoreCase("true");
      } else {
        useVertexAI = false;
      }
    }

    if (project.isPresent() || location.isPresent()) {
      if (apiKey.isPresent()) {
        throw new IllegalArgumentException(
            "Project/location and API key are mutually exclusive in the client initializer.");
      }
      if (!useVertexAI) {
        throw new IllegalArgumentException("Gemini API do not support project/location.");
      }
    }

    this.debugConfig = debugConfig.orElse(new DebugConfig());
    if (this.debugConfig.clientMode().equals("replay")) {
      if (!useVertexAI) {
        this.apiClient =
            new ReplayApiClient(
                /* apiKey= */ apiKey,
                /* httpOptions= */ httpOptions,
                /* clientOptions= */ clientOptions,
                this.debugConfig.replaysDirectory(),
                this.debugConfig.replayId(),
                this.debugConfig.clientMode());
      } else {
        this.apiClient =
            new ReplayApiClient(
                /* apiKey= */ apiKey,
                /* project= */ project,
                /* location= */ location,
                /* credentials= */ credentials,
                /* httpOptions= */ httpOptions,
                /* clientOptions= */ clientOptions,
                this.debugConfig.replaysDirectory(),
                this.debugConfig.replayId(),
                this.debugConfig.clientMode());
      }
    } else {
      if (!useVertexAI) {
        this.apiClient =
            new HttpApiClient(
                /* apiKey= */ apiKey,
                /* httpOptions= */ httpOptions,
                /* clientOptions= */ clientOptions);
      } else {
        this.apiClient =
            new HttpApiClient(
                /* apiKey= */ apiKey,
                /* project= */ project,
                /* location= */ location,
                /* credentials= */ credentials,
                /* httpOptions= */ httpOptions,
                /* clientOptions= */ clientOptions);
      }
    }

    models = new Models(this.apiClient);
    batches = new Batches(this.apiClient);
    caches = new Caches(apiClient);
    operations = new Operations(this.apiClient);
    chats = new Chats(this.apiClient);
    async = new Async(this.apiClient);
    files = new Files(this.apiClient);
    authTokens = new Tokens(this.apiClient);
    tunings = new Tunings(this.apiClient);
    fileSearchStores = new FileSearchStores(this.apiClient);
    // interactions:strip_begin
    interactions = new InteractionServiceImpl(this.apiClient.interactionsClientOptions);
    // interactions:strip_end
  }

  /** Returns whether the client is using Gemini Enterprise Agent Platform. */
  public boolean enterprise() {
    return apiClient.vertexAI();
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

  /** Returns the API key for Gemini API. */
  public String apiKey() {
    return apiClient.apiKey();
  }

  /** Sets the replay ID for the replay client. For internal testing only. */
  void setReplayId(String replayId) {
    if (this.apiClient instanceof ReplayApiClient) {
      ((ReplayApiClient) this.apiClient).initializeReplaySession(replayId);
    }
  }

  /** Returns the debug config for the client. */
  DebugConfig debugConfig() {
    return debugConfig;
  }

  /** Returns the client mode. If it's "replay", then the client is in testing mode. */
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
    apiClient.close();
  }

  /**
   * Overrides the base URLs for the Gemini API and/or Vertex AI API.
   *
   * <p>Note: This function should be called before initializing the SDK. If the base URLs are set
   * after initializing the SDK, the base URLs will not be updated.
   *
   * <p>Precedence rule for base URL:
   *
   * <p>1. Base URL set via HttpOptions.
   *
   * <p>2. Base URL set via the latest call to setDefaultBaseUrls.
   *
   * <p>3. Base URL set via environment variables GOOGLE_GEMINI_BASE_URL or GOOGLE_VERTEX_BASE_URL.
   */
  public static void setDefaultBaseUrls(
      Optional<String> geminiBaseUrl, Optional<String> vertexBaseUrl) {
    ApiClient.setDefaultBaseUrls(geminiBaseUrl, vertexBaseUrl);
  }
}
