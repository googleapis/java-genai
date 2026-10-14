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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.genai.gaos.SecuritySource;
import com.google.genai.gaos.models.shared.Security;
import com.google.genai.gaos.utils.AsyncHooks;
import com.google.genai.gaos.utils.Hook;
import com.google.genai.gaos.utils.Hooks;
import com.google.genai.gaos.utils.transport.HttpBody;
import com.google.genai.gaos.utils.transport.HttpRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
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

  private static void resetUnsupportedParameterWarnings() {
    SDKHooks.loggedTemperatureWarning.set(false);
    SDKHooks.loggedTopPWarning.set(false);
    SDKHooks.loggedTopKWarning.set(false);
  }

  private Hook.BeforeRequestContext createInteractionContext() {
    return new Hook.BeforeRequestContextImpl(
        null,
        "https://generativelanguage.googleapis.com",
        "CreateInteraction",
        Optional.empty(),
        Optional.empty());
  }

  private HttpRequest createInteractionRequest(String jsonBody) {
    return HttpRequest.builder()
        .method("POST")
        .uri(URI.create("https://generativelanguage.googleapis.com/v1beta/interactions"))
        .body(HttpBody.of(jsonBody))
        .build();
  }

  @Test
  public void testIsGemini36OrNewer() {
    assertTrue(SDKHooks.isGemini36OrNewer("gemini-3.6-flash"));
    assertTrue(SDKHooks.isGemini36OrNewer("publishers/google/models/gemini-3.6-pro"));
    assertTrue(SDKHooks.isGemini36OrNewer("gemini-3.8-flash"));

    assertFalse(SDKHooks.isGemini36OrNewer("gemini-3.1-pro"));
    assertFalse(SDKHooks.isGemini36OrNewer("gemini-3-flash"));
    assertFalse(SDKHooks.isGemini36OrNewer("gemini-2.5-flash"));
    assertFalse(SDKHooks.isGemini36OrNewer("gemini-flash-latest"));
    assertFalse(SDKHooks.isGemini36OrNewer(""));
    assertFalse(SDKHooks.isGemini36OrNewer(null));
  }

  @Test
  public void testWarnUnsupportedInteractionParameters_logsOnceOnGemini36AndNewer()
      throws Exception {
    resetUnsupportedParameterWarnings();
    Logger logger = Logger.getLogger(SDKHooks.class.getName());
    List<String> messages = new ArrayList<>();
    Handler handler =
        new Handler() {
          @Override
          public void publish(LogRecord record) {
            messages.add(record.getMessage());
          }

          @Override
          public void flush() {}

          @Override
          public void close() {}
        };
    logger.addHandler(handler);
    try {
      Hooks hooks = new Hooks();
      SDKHooks.initialize(hooks);
      Hook.BeforeRequestContext context = createInteractionContext();

      // Older model does not warn or consume the one-time flags.
      HttpRequest olderModelRequest =
          createInteractionRequest(
              "{\"model\":\"gemini-3.1-pro\","
                  + "\"generation_config\":{\"temperature\":0.5,\"top_p\":0.9,\"top_k\":40}}");
      hooks.beforeRequest(context, olderModelRequest);
      assertTrue(messages.isEmpty());
      assertFalse(SDKHooks.loggedTemperatureWarning.get());
      assertFalse(SDKHooks.loggedTopPWarning.get());
      assertFalse(SDKHooks.loggedTopKWarning.get());

      // First call on Gemini 3.6+ logs all 3 warnings.
      HttpRequest gemini36Request =
          createInteractionRequest(
              "{\"model\":\"gemini-3.6-flash\","
                  + "\"generation_config\":{\"temperature\":0.5,\"top_p\":0.9,\"top_k\":40}}");
      hooks.beforeRequest(context, gemini36Request);
      assertEquals(
          Arrays.asList(
              "temperature is not supported on Gemini 3.6 and newer models and is ignored.",
              "top_p is not supported on Gemini 3.6 and newer models and is ignored.",
              "top_k is not supported on Gemini 3.6 and newer models and is ignored."),
          messages);
      assertTrue(SDKHooks.loggedTemperatureWarning.get());
      assertTrue(SDKHooks.loggedTopPWarning.get());
      assertTrue(SDKHooks.loggedTopKWarning.get());

      // Second call does not log again.
      messages.clear();
      hooks.beforeRequest(context, gemini36Request);
      assertTrue(messages.isEmpty());
    } finally {
      logger.removeHandler(handler);
      resetUnsupportedParameterWarnings();
    }
  }
}
