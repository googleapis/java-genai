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

import okhttp3.Headers;
import okhttp3.ResponseBody;

/** */
public final class FakeApiResponse extends ApiResponse {

  private final Headers headers;
  private final ResponseBody body;

  public FakeApiResponse(Headers headers, ResponseBody body) {
    this.headers = headers;
    this.body = body;
  }

  @Override
  public Headers getHeaders() {
    return headers;
  }

  @Override
  public ResponseBody getBody() {
    return body;
  }

  @Override
  public void close() {
    // No-op for fake response.
  }
}
