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
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.api.core.InternalApi;
import com.google.genai.errors.ApiException;
import com.google.genai.errors.GenAiIOException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.ResponseBody;

/** Provides a simulated HTTP response from a replay file. */
@InternalApi
@ExcludeFromGeneratedCoverageReport
public final class ReplayApiResponse extends ApiResponse {

  private final ResponseBody body;
  private final int statusCode;
  private final Headers headers;
  private final ArrayNode bodySegments;

  public ReplayApiResponse(ArrayNode bodySegments, int statusCode, Headers headers) {
    this.bodySegments = bodySegments;
    this.statusCode = statusCode;
    this.headers = headers;
    if (bodySegments.size() == 0) {
      this.body = ResponseBody.create(MediaType.parse("application/json"), "");
    } else if (bodySegments.size() == 1) {
      // For unary response
      this.body =
          ResponseBody.create(
              JsonSerializable.toJsonString(bodySegments.get(0)),
              MediaType.parse("application/json"));
    } else {
      // For streaming response
      try {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] newline = "\n".getBytes(StandardCharsets.UTF_8);
        for (JsonNode segment : bodySegments) {
          outputStream.write(JsonSerializable.objectMapper.writeValueAsBytes(segment));
          outputStream.write(newline);
        }
        this.body =
            ResponseBody.create(outputStream.toByteArray(), MediaType.parse("application/json"));
      } catch (IOException e) {
        throw new GenAiIOException("Failed to convert body segments to a JSON string.", e);
      }
    }
  }

  @Override
  public ResponseBody getBody() {
    ApiException.throwFromErrorNode(bodySegments, statusCode);
    return this.body;
  }

  @Override
  public Headers getHeaders() {
    return this.headers;
  }

  public int getStatusCode() {
    return this.statusCode;
  }

  @Override
  public void close() {}
}
