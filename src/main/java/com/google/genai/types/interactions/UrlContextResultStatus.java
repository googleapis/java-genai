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

package com.google.genai.types.interactions;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Represents the status of URL context retrieval in an interaction. */
public enum UrlContextResultStatus {
  /** URL retrieval is successful. */
  @JsonProperty("success")
  SUCCESS,

  /** URL retrieval failed due to an error. */
  @JsonProperty("error")
  ERROR,

  /** URL retrieval failed because content is behind a paywall. */
  @JsonProperty("paywall")
  PAYWALL,

  /** URL retrieval failed because content is unsafe. */
  @JsonProperty("unsafe")
  UNSAFE
}
