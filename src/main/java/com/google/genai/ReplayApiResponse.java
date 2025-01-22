package com.google.genai;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.client.methods.CloseableHttpResponse;

// TODO(b/369384123): Change the replay API response to use the ReplayFile.
/** Provides a simulated HTTP response from a replay file. */
final class ReplayApiResponse extends ApiResponse {

  private final CloseableHttpResponse response;

  public ReplayApiResponse(CloseableHttpResponse response) {
    this.response = response;
  }

  public HttpEntity getEntity() throws HttpException {
    return this.response.getEntity();
  }

  public void close() throws IOException {
    this.response.close();
  }
}
