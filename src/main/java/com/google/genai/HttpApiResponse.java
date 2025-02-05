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
    } catch (IOException ignored) {
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
