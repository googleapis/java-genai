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

package com.google.genai.gaos.hooks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.google.genai.gaos.SDKConfiguration;
import com.google.genai.gaos.SecuritySource;
import com.google.genai.gaos.models.errors.GaosApiException;
import com.google.genai.gaos.models.shared.Security;
import com.google.genai.gaos.utils.AsyncHooks;
import com.google.genai.gaos.utils.Headers;
import com.google.genai.gaos.utils.Hook;
import com.google.genai.gaos.utils.Hooks;
import com.google.genai.gaos.utils.transport.HttpRequest;
import com.google.genai.gaos.utils.transport.HttpResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link SDKHooks} to verify that request authorization headers (x-goog-api-key,
 * Authorization, defaultHeaders) are properly configured and not accidentally cleared during SDK
 * regeneration.
 */
public final class SdkHooksTest {

  private static final String API_KEY = "test-api-key-12345";
  private static final String ACCESS_TOKEN = "test-access-token-abcde";

  private Hook.BeforeRequestContext createContext(Security security) {
    return new Hook.BeforeRequestContextImpl(
        null,
        "https://generativelanguage.googleapis.com",
        "createInteraction",
        Optional.empty(),
        security != null ? Optional.of(SecuritySource.of(security)) : Optional.empty());
  }

  private HttpRequest createBaseRequest() {
    return HttpRequest.builder()
        .method("POST")
        .uri(URI.create("https://generativelanguage.googleapis.com/v1beta/interactions"))
        .build();
  }

  @Test
  public void testSyncHookSetsApiKeyHeader() throws Exception {
    Hooks hooks = new Hooks();
    SDKHooks.initialize(hooks);

    Security security = Security.builder().apiKey(API_KEY).build();
    Hook.BeforeRequestContext context = createContext(security);
    HttpRequest request = createBaseRequest();

    HttpRequest modified = hooks.beforeRequest(context, request);
    assertEquals(API_KEY, modified.headers().firstValue("x-goog-api-key").orElse(null));
  }

  @Test
  public void testSyncHookSetsAuthorizationHeader() throws Exception {
    Hooks hooks = new Hooks();
    SDKHooks.initialize(hooks);

    Security security = Security.builder().accessToken(ACCESS_TOKEN).build();
    Hook.BeforeRequestContext context = createContext(security);
    HttpRequest request = createBaseRequest();

    HttpRequest modified = hooks.beforeRequest(context, request);
    assertEquals(
        "Bearer " + ACCESS_TOKEN, modified.headers().firstValue("Authorization").orElse(null));
  }

  @Test
  public void testSyncHookSetsDefaultHeaders() throws Exception {
    Hooks hooks = new Hooks();
    SDKHooks.initialize(hooks);

    Map<String, String> defaultHeaders = new HashMap<>();
    defaultHeaders.put("X-Custom-Header", "custom-value");
    defaultHeaders.put("Api-Revision", "2026-05-20");

    Security security = Security.builder().defaultHeaders(defaultHeaders).build();
    Hook.BeforeRequestContext context = createContext(security);
    HttpRequest request = createBaseRequest();

    HttpRequest modified = hooks.beforeRequest(context, request);
    assertEquals("custom-value", modified.headers().firstValue("X-Custom-Header").orElse(null));
    assertEquals("2026-05-20", modified.headers().firstValue("Api-Revision").orElse(null));
  }

  @Test
  public void testAsyncHookSetsApiKeyHeader() throws Exception {
    AsyncHooks asyncHooks = new AsyncHooks();
    SDKHooks.initialize(asyncHooks);

    Security security = Security.builder().apiKey(API_KEY).build();
    Hook.BeforeRequestContext context = createContext(security);
    HttpRequest request = createBaseRequest();

    HttpRequest modified = asyncHooks.beforeRequest(context, request).get();
    assertEquals(API_KEY, modified.headers().firstValue("x-goog-api-key").orElse(null));
  }

  @Test
  public void testAsyncHookSetsAuthorizationHeader() throws Exception {
    AsyncHooks asyncHooks = new AsyncHooks();
    SDKHooks.initialize(asyncHooks);

    Security security = Security.builder().accessToken(ACCESS_TOKEN).build();
    Hook.BeforeRequestContext context = createContext(security);
    HttpRequest request = createBaseRequest();

    HttpRequest modified = asyncHooks.beforeRequest(context, request).get();
    assertEquals(
        "Bearer " + ACCESS_TOKEN, modified.headers().firstValue("Authorization").orElse(null));
  }

  @Test
  public void testAsyncHookSetsDefaultHeaders() throws Exception {
    AsyncHooks asyncHooks = new AsyncHooks();
    SDKHooks.initialize(asyncHooks);

    Map<String, String> defaultHeaders = new HashMap<>();
    defaultHeaders.put("X-Custom-Header", "custom-value");

    Security security = Security.builder().defaultHeaders(defaultHeaders).build();
    Hook.BeforeRequestContext context = createContext(security);
    HttpRequest request = createBaseRequest();

    HttpRequest modified = asyncHooks.beforeRequest(context, request).get();
    assertEquals("custom-value", modified.headers().firstValue("X-Custom-Header").orElse(null));
  }

  @Test
  public void testHookWithoutSecurityPassesRequestThrough() throws Exception {
    Hooks hooks = new Hooks();
    SDKHooks.initialize(hooks);

    Hook.BeforeRequestContext context = createContext(null);
    HttpRequest request = createBaseRequest();

    HttpRequest modified = hooks.beforeRequest(context, request);
    assertSame(request, modified);
  }

  @Test
  public void testSyncAndAsyncHooksPropagateUserProjectFromGlobals() throws Exception {
    Hooks hooks = new Hooks();
    SDKHooks.initialize(hooks);
    AsyncHooks asyncHooks = new AsyncHooks();
    SDKHooks.initialize(asyncHooks);

    SDKConfiguration config = new SDKConfiguration();
    config.globals.putParam("header", "x-goog-user-project", "my-quota-project");
    Hook.BeforeRequestContext context =
        new Hook.BeforeRequestContextImpl(
            config,
            "https://generativelanguage.googleapis.com",
            "createInteraction",
            Optional.empty(),
            Optional.empty());

    HttpRequest request = createBaseRequest();
    HttpRequest syncModified = hooks.beforeRequest(context, request);
    assertEquals(
        "my-quota-project", syncModified.headers().firstValue("x-goog-user-project").orElse(null));

    HttpRequest asyncModified = asyncHooks.beforeRequest(context, request).get();
    assertEquals(
        "my-quota-project", asyncModified.headers().firstValue("x-goog-user-project").orElse(null));

    // Existing x-goog-user-project header on the request should not be overwritten.
    HttpRequest presetRequest =
        request.toBuilder().setHeader("x-goog-user-project", "override-project").build();
    HttpRequest syncPreset = hooks.beforeRequest(context, presetRequest);
    assertEquals(
        "override-project", syncPreset.headers().firstValue("x-goog-user-project").orElse(null));
    HttpRequest asyncPreset = asyncHooks.beforeRequest(context, presetRequest).get();
    assertEquals(
        "override-project", asyncPreset.headers().firstValue("x-goog-user-project").orElse(null));
  }

  private GaosApiException errorFromBody(int code, String json) {
    byte[] body = json.getBytes(StandardCharsets.UTF_8);
    HttpResponse<InputStream> rawResponse =
        new HttpResponse<>(createBaseRequest(), code, new Headers(), new ByteArrayInputStream(body));
    return new GaosApiException("API error occurred", code, body, rawResponse, null);
  }

  @Test
  public void testGaosApiExceptionUsesInteractionsErrorBody() {
    // Shape returned by the live Interactions-family endpoints: string `code`, no `status`.
    GaosApiException ex =
        errorFromBody(
            400, "{\"error\":{\"message\":\"Model name cannot be empty.\",\"code\":\"invalid_request\"}}");

    assertEquals(400, ex.code());
    assertEquals("invalid_request", ex.status());
    assertEquals("400 invalid_request. Model name cannot be empty.", ex.getMessage());
  }

  @Test
  public void testGaosApiExceptionUsesOnePlatformErrorBody() {
    GaosApiException ex =
        errorFromBody(
            400,
            "{\"error\":{\"code\":400,\"message\":\"Bad thing\",\"status\":\"INVALID_ARGUMENT\"}}");

    assertEquals("INVALID_ARGUMENT", ex.status());
    assertEquals("400 INVALID_ARGUMENT. Bad thing", ex.getMessage());
  }

  @Test
  public void testGaosApiExceptionFallsBackForNonJsonBody() {
    GaosApiException ex = errorFromBody(502, "<html>Bad Gateway</html>");

    assertEquals(502, ex.code());
    assertEquals("", ex.status());
    assertEquals("502 . API error occurred", ex.getMessage());
  }
}
