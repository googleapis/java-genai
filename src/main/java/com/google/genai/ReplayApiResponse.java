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
import com.google.genai.errors.ApiException;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.ResponseBody;

/** Provides a simulated HTTP response from a replay file. */
@ExcludeFromGeneratedCoverageReport
final class ReplayApiResponse extends ApiResponse {

  private final JsonNode body;
  private final int statusCode;
  private final Headers headers;

  public ReplayApiResponse(JsonNode body, int statusCode, Headers headers) {
    this.body = body;
    this.statusCode = statusCode;
    this.headers = headers;
  }

  @Override
  public ResponseBody getBody() {
    ApiException.throwFromErrorNode(body, statusCode);
    return ResponseBody.create(
        JsonSerializable.toJsonString(body), MediaType.parse("application/json"));
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
