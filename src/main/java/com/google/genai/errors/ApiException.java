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

package com.google.genai.errors;

import static com.google.common.base.Strings.isNullOrEmpty;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.api.core.InternalApi;
import java.io.IOException;
import okhttp3.Response;
import okhttp3.ResponseBody;

/** General exception class for all exceptions originating from the GenAI API side. */
public class ApiException extends BaseException {

  private final int code;
  private final String status;
  private final String message;

  /**
   * Creates a new ApiException with the specified code, status, and message.
   *
   * @param code The status code from the API response.
   * @param status The status from the API response.
   * @param message The error message from the API response.
   */
  public ApiException(int code, String status, String message) {
    super(String.format("%d %s. %s", code, status, message));
    this.code = code;
    this.status = status;
    this.message = message;
  }

  /**
   * Throws an ApiException from the response if the response is not a OK status.
   *
   * @param response The response from the API call.
   */
  @InternalApi
  public static void throwFromResponse(Response response) {
    int code = response.code();
    if (code >= 200 && code < 300) {
      return;
    }
    String status = response.message();
    String message = getErrorMessageFromResponse(response);
    if (code >= 400 && code < 500) { // Client errors.
      throw new ClientException(code, status, message);
    } else if (code >= 500 && code < 600) { // Server errors.
      throw new ServerException(code, status, message);
    } else {
      throw new ApiException(code, status, message);
    }
  }

  /**
   * Returns the error message from the response, if no error or error message is not found, then
   * returns an empty string.
   */
  static String getErrorMessageFromResponse(Response response) {
    ResponseBody responseBody = response.body();
    try {
      if (responseBody == null) {
        return "";
      }
      String responseBodyString = responseBody.string();
      if (isNullOrEmpty(responseBodyString)) {
        return "";
      }
      ObjectMapper mapper = new ObjectMapper();
      JsonNode errorNode = mapper.readTree(responseBodyString).get("error");
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

  /** Throws an ApiException from a {@link ArrayNode}. This method is for internal use only. */
  @InternalApi
  public static void throwFromErrorNode(ArrayNode errorNode, int code) {
    if (code == 200) {
      return;
    }

    String message = "";
    try {
      JsonNode messageNode = errorNode.get(0).get("error").get("message");
      if (messageNode != null && messageNode.isTextual()) {
        message = messageNode.asText();
      }
    } catch (NullPointerException | IndexOutOfBoundsException ignored) {
      // If message is not found, do nothing and fallback to default message "".
    }

    String status = "UNKNOWN";
    try {
      JsonNode statusNode = errorNode.get(0).get("error").get("status");
      if (statusNode != null && statusNode.isTextual()) {
        status = statusNode.asText();
      }
    } catch (NullPointerException | IndexOutOfBoundsException ignored) {
      // If status is not found, do nothing and fallback to default value "UNKNOWN".
    }

    if (code >= 400 && code < 500) { // Client errors.
      throw new ClientException(code, status, message);
    } else if (code >= 500 && code < 600) { // Server errors.
      throw new ServerException(code, status, message);
    } else {
      throw new ApiException(code, status, message);
    }
  }

  /** Returns the status code from the API response. */
  public int code() {
    return code;
  }

  /** Returns the status from the API response. */
  public String status() {
    return status;
  }

  /** Returns the error message from the API response. */
  public String message() {
    return message;
  }
}
