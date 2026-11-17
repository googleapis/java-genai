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

package com.google.genai.gaos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.genai.gaos.models.interactions.EnvironmentMode;
import com.google.genai.gaos.models.operations.ListEnvironmentsRequest;
import com.google.genai.gaos.models.operations.ListEnvironmentsResponse;
import com.google.genai.gaos.utils.Options;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import org.junit.jupiter.api.Test;

/**
 * Keeps the pre-{@code mode} positional {@code listEnvironments} signatures source compatible.
 *
 * <p>Adding the {@code mode} query parameter inserted a new positional argument before {@code
 * pageSize}. The old overloads are restored by Speakeasy patches; this test fails to compile if a
 * regeneration drops them.
 */
@SuppressWarnings("deprecation")
public final class ListEnvironmentsCompatTest {

  @Test
  public void legacyRequestConstructor_leavesModeUnset() {
    ListEnvironmentsRequest legacy = new ListEnvironmentsRequest("v1beta", 10, "tok");

    assertEquals(Optional.empty(), legacy.mode());
    assertEquals(
        ListEnvironmentsRequest.builder()
            .apiVersion("v1beta")
            .pageSize(10)
            .pageToken("tok")
            .build(),
        legacy);
  }

  @Test
  public void newRequestConstructor_setsMode() {
    ListEnvironmentsRequest request =
        new ListEnvironmentsRequest("v1beta", EnvironmentMode.SELF_HOSTED, 10, "tok");

    assertEquals(Optional.of(EnvironmentMode.SELF_HOSTED), request.mode());
  }

  @Test
  public void legacyListEnvironmentsOverloads_compile() throws Exception {
    // Never invoked: these only need to compile against the old 4-argument signatures.
    Function<Environments, ListEnvironmentsResponse> sync =
        e -> e.listEnvironments("v1beta", 10, "tok", (Options) null);
    Function<
            AsyncEnvironments,
            CompletableFuture<com.google.genai.gaos.models.operations.async.ListEnvironmentsResponse>>
        async =
        e -> e.listEnvironments("v1beta", 10, "tok", (Options) null);

    assertTrue(
        Environments.class
            .getMethod("listEnvironments", String.class, Integer.class, String.class, Options.class)
            .isAnnotationPresent(Deprecated.class));
    assertTrue(
        AsyncEnvironments.class
            .getMethod("listEnvironments", String.class, Integer.class, String.class, Options.class)
            .isAnnotationPresent(Deprecated.class));
    assertTrue(sync != null && async != null);
  }
}
