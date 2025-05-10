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

package com.google.genai.fakes;

import com.google.genai.ApiResponse;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

/** */
public final class FakeApiResponse extends ApiResponse {

  private final Header[] headers;
  private final HttpEntity entity;

  public FakeApiResponse(Header[] headers, HttpEntity entity) {
    this.headers = headers;
    this.entity = entity;
  }

  @Override
  public Header[] getHeaders() {
    return headers;
  }

  @Override
  public HttpEntity getEntity() {
    return entity;
  }

  @Override
  public void close() {
    throw new UnsupportedOperationException("Not implemented yet.");
  }
}
