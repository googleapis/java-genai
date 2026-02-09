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

import com.google.api.core.InternalApi;
import com.google.genai.errors.ApiException;
import okhttp3.Headers;
import okhttp3.Response;
import okhttp3.ResponseBody;

/** Wraps a real HTTP response to expose the methods needed by the GenAI SDK. */
@InternalApi
public final class HttpApiResponse extends ApiResponse {

  private final Response response;

  /** Constructs a HttpApiResponse instance with the response. */
  public HttpApiResponse(Response response) {
    this.response = response;
  }

  /** Returns the ResponseBody from the response. */
  @Override
  public ResponseBody getBody() {
    ApiException.throwFromResponse(response);
    return response.body();
  }

  /** Returns all of the headers from the response. */
  @Override
  public Headers getHeaders() {
    return response.headers();
  }

  /** Closes the Http response. */
  @Override
  public void close() {
    response.close();
  }
}
