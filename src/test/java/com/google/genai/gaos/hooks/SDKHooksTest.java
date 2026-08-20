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

import com.google.genai.gaos.SecuritySource;
import com.google.genai.gaos.models.shared.Security;
import com.google.genai.gaos.utils.AsyncHooks;
import com.google.genai.gaos.utils.Hook;
import com.google.genai.gaos.utils.Hooks;
import com.google.genai.gaos.utils.transport.HttpRequest;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link SDKHooks} to verify that request authorization headers
 * (x-goog-api-key, Authorization, defaultHeaders) are properly configured
 * and not accidentally cleared during SDK regeneration.
 */
public final class SDKHooksTest {

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
    assertEquals("Bearer " + ACCESS_TOKEN, modified.headers().firstValue("Authorization").orElse(null));
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
    assertEquals("Bearer " + ACCESS_TOKEN, modified.headers().firstValue("Authorization").orElse(null));
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
}
