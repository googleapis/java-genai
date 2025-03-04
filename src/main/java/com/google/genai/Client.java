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
import com.google.genai.types.HttpOptions;
import java.io.IOException;
import java.util.Optional;

/** Client class for GenAI. */
public final class Client implements AutoCloseable {

  /** Async class for GenAI. */
  public final class Async {
    public final AsyncModels models;
    public final AsyncLive live;

    public Async(ApiClient apiClient) {
      this.models = new AsyncModels(apiClient);
      this.live = new AsyncLive(apiClient);
    }
  }

  private final DebugConfig debugConfig;
  private final ApiClient apiClient;
  public final Models models;
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

    /** Builds the {@link Client} instance. */
    public Client build() {
      return new Client(apiKey, project, location, credentials, httpOptions, vertexAI, debugConfig);
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
        /* debugConfig= */ Optional.empty());
  }

  /**
   * Constructs a Client instance with the given parameters.
   *
   * @param apiKey Optional String for the API key. Google AI APIs only.
   * @param project Optional String for the project ID. Vertex AI APIs only.
   * @param location Optional String for the location. Vertex AI APIs only.
   * @param credentials Optional {@link GoogleCredentials}. Vertex AI APIs only.
   * @param httpOptions Optional {@link HttpOptions} for sending HTTP requests.
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
      Optional<Boolean> vertexAI,
      Optional<DebugConfig> debugConfig) {
    checkNotNull(vertexAI, "vertexAI cannot be null");
    checkNotNull(debugConfig, "debugConfig cannot be null");

    boolean useVertexAI;
    if (vertexAI.isPresent()) {
      useVertexAI = vertexAI.get();
    } else {
      String envVar = System.getenv("GOOGLE_GENAI_USE_VERTEXAI");
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
    async = new Async(this.apiClient);
  }

  /** Returns whether the client is using Vertex AI APIs. */
  public boolean isVertexAI() {
    return apiClient.isVertexAI();
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

  /** Closes the Client instance together with its instantiated http client. */
  @Override
  public void close() throws IOException {
    apiClient.httpClient().close();
  }
}
