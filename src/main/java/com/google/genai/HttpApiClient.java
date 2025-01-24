package com.google.genai;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.collect.ImmutableMap;
import com.google.genai.types.HttpOptions;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

/** Base client for the HTTP APIs. */
final class HttpApiClient extends ApiClient {

  /** Constructs an ApiClient for Google AI APIs. */
  HttpApiClient(Optional<String> apiKey, Optional<HttpOptions> httpOptions) {
    super(apiKey, httpOptions);
  }

  /** Constructs an ApiClient for Vertex AI APIs. */
  HttpApiClient(
      Optional<String> project,
      Optional<String> location,
      Optional<GoogleCredentials> credentials,
      Optional<HttpOptions> httpOptions) {
    super(project, location, credentials, httpOptions);
  }

  /** Sends a Http Post request given the path and request json string. */
  public ApiResponse post(String path, String requestJson) throws IOException {
    if (this.isVertexAI() && !path.startsWith("projects/")) {
      path =
          String.format("projects/%s/locations/%s/", this.project.get(), this.location.get())
              + path;
    }
    HttpPost httpPost =
        new HttpPost(
            String.format(
                "%s/%s/%s",
                httpOptions.getBaseUrl().get(), httpOptions.getApiVersion().get(), path));

    for (Map.Entry<String, String> header :
        httpOptions.getHeaders().orElse(ImmutableMap.of()).entrySet()) {
      httpPost.setHeader(header.getKey(), header.getValue());
    }
    if (apiKey.isPresent()) {
      httpPost.setHeader("x-goog-api-key", apiKey.get());
    } else {
      GoogleCredentials cred =
          credentials.orElseThrow(() -> new IllegalStateException("credentials is required"));
      cred.refreshIfExpired();
      httpPost.setHeader("Authorization", "Bearer " + cred.getAccessToken().getTokenValue());
    }

    httpPost.setEntity(new StringEntity(requestJson));

    HttpApiResponse httpApiResponse = new HttpApiResponse(httpClient.execute(httpPost));
    return httpApiResponse;
  }
}
