package com.google.genai;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

/** Wraps a real HTTP response to expose the methods needed by the GenAI SDK. */
final class HttpApiResponse extends ApiResponse {

  private final CloseableHttpResponse response;

  /** Constructs a HttpApiResponse instance with the response. */
  public HttpApiResponse(CloseableHttpResponse response) {
    this.response = response;
  }

  /**
   * Returns the error message from the response, if no error or error message is not found, then
   * returns an empty string.
   */
  private String getErrorMessage() {
    HttpEntity entity = this.response.getEntity();
    try {
      String responseBody = EntityUtils.toString(entity);
      if (responseBody == null || responseBody.isEmpty()) {
        return "";
      }

      JsonNode errorNode = JsonSerializable.objectMapper.readTree(responseBody).get("error");
      if (errorNode != null && errorNode.isObject()) {
        JsonNode messageNode = errorNode.get("message");
        if (messageNode != null && messageNode.isTextual()) {
          return messageNode.asText();
        }
      }
      return "";
    } catch (IOException e) {
      return "";
    }
  }

  /**
   * Returns the HttpEntity from the response.
   *
   * @throws HttpException if the HTTP status code is not 200 OK.
   */
  public HttpEntity getEntity() throws HttpException {
    StatusLine statusLine = response.getStatusLine();
    if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
      // TODO(jayceeli): Define custom exception classes for better error handling.
      throw new HttpException(
          String.format(
              "%s %s. %s",
              statusLine.getStatusCode(), statusLine.getReasonPhrase(), getErrorMessage()));
    }
    return this.response.getEntity();
  }

  /** Closes the Http response. */
  public void close() throws IOException {
    this.response.close();
  }
}
