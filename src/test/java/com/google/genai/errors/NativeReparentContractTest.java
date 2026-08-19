/*
 * Copyright 2026 Google LLC
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.genai.gaos.models.errors.GaosApiException;
import com.google.genai.gaos.models.errors.GaosClientException;
import com.google.genai.gaos.models.errors.GaosServerException;
import org.junit.jupiter.api.Test;

/**
 * CI trip-wire for the error-hierarchy reparent's <b>native prerequisites</b> — the hand edits to
 * {@code com.google.genai.errors} in the checked-in wrapper (de-finalized
 * {@code ClientException}/{@code ServerException} + four-arg cause constructors) that
 * {@code scripts/sync_speakeasy_outputs.py} relies on.
 */
final class NativeReparentContractTest {

  @Test
  void nativeExceptionsExposeCauseCarryingConstructors() {
    Throwable cause = new IllegalStateException("origin");

    // These four-arg constructors are the hand edit the reparent rides on. Referencing them here is
    // the compile-time guard; the assertions double as a runtime check that the cause is retained.
    ApiException api = new ApiException(400, "", "boom", cause);
    ClientException client = new ClientException(429, "", "slow down", cause);
    ServerException server = new ServerException(500, "", "kaboom", cause);

    assertSame(cause, api.getCause(), "ApiException must retain the reparented cause");
    assertSame(cause, client.getCause(), "ClientException must retain the reparented cause");
    assertSame(cause, server.getCause(), "ServerException must retain the reparented cause");
    assertEquals(429, client.code(), "code preserved through the cause constructor");
    assertEquals(500, server.code(), "code preserved through the cause constructor");
  }

  @Test
  void gaosCarriersRemainReparentedOntoNativeTree() {
    // 4xx carrier -> native ClientException, 5xx carrier -> native ServerException, generic ->
    // bare ApiException. If de-finalization or the sync reparent regresses, these detach.
    assertTrue(
        ClientException.class.isAssignableFrom(GaosClientException.class),
        "GaosClientException must extend native ClientException");
    assertTrue(
        ServerException.class.isAssignableFrom(GaosServerException.class),
        "GaosServerException must extend native ServerException");
    assertTrue(
        ApiException.class.isAssignableFrom(GaosApiException.class),
        "GaosApiException must extend native ApiException");

    // And the carriers are themselves ApiExceptions, so a single catch (ApiException) covers all.
    assertTrue(ApiException.class.isAssignableFrom(GaosClientException.class));
    assertTrue(ApiException.class.isAssignableFrom(GaosServerException.class));
  }
}
