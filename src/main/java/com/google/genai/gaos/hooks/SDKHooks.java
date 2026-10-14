/*
 * Copyright 2026 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.genai.gaos.hooks;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.genai.gaos.models.shared.Security;
import com.google.genai.gaos.utils.HasSecurity;
import com.google.genai.gaos.utils.Hook.BeforeRequestContext;
import com.google.genai.gaos.utils.JSON;
import com.google.genai.gaos.utils.transport.HttpRequest;
import java.io.InputStream;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//
// This file is written once by speakeasy code generation and
// thereafter will not be overwritten by speakeasy updates. As a
// consequence any customization of this class will be preserved.
//

public final class SDKHooks {

  private static final Logger logger = Logger.getLogger(SDKHooks.class.getName());
  private static final Pattern GEMINI_VERSION_PATTERN =
      Pattern.compile("^gemini-(\\d+)(?:\\.(\\d+))?(?:-|$)");
  public static final AtomicBoolean loggedTemperatureWarning = new AtomicBoolean(false);
  public static final AtomicBoolean loggedTopPWarning = new AtomicBoolean(false);
  public static final AtomicBoolean loggedTopKWarning = new AtomicBoolean(false);

    private SDKHooks() {
        // prevent instantiation
    }

  static boolean isGemini36OrNewer(String model) {
    if (model == null || model.isEmpty()) {
      return false;
    }
    int lastSlash = model.lastIndexOf('/');
    String modelId =
        (lastSlash >= 0 ? model.substring(lastSlash + 1) : model).toLowerCase(Locale.ROOT);
    Matcher matcher = GEMINI_VERSION_PATTERN.matcher(modelId);
    if (!matcher.find()) {
      return false;
    }
    int major = Integer.parseInt(matcher.group(1));
    int minor = matcher.group(2) != null ? Integer.parseInt(matcher.group(2)) : 0;
    return major > 3 || (major == 3 && minor >= 6);
  }

  private static void warnUnsupportedInteractionParameters(
      BeforeRequestContext context, HttpRequest request) {
    if (!"CreateInteraction".equals(context.operationId())
        || !request.body().isPresent()
        || !request.body().get().isRepeatable()) {
      return;
    }
    try (InputStream in = request.body().get().stream()) {
      JsonNode root = JSON.getMapper().readTree(in);
      if (root == null || !root.isObject()) {
        return;
      }
      JsonNode modelNode = root.get("model");
      if (modelNode == null || !modelNode.isTextual() || !isGemini36OrNewer(modelNode.asText())) {
        return;
      }
      JsonNode generationConfig = root.get("generation_config");
      if (generationConfig == null || !generationConfig.isObject()) {
        return;
      }
      JsonNode temperature = generationConfig.get("temperature");
      if (temperature != null
          && !temperature.isNull()
          && loggedTemperatureWarning.compareAndSet(false, true)) {
        logger.warning(
            "temperature is not supported on Gemini 3.6 and newer models and is ignored.");
      }
      JsonNode topP = generationConfig.get("top_p");
      if (topP != null && !topP.isNull() && loggedTopPWarning.compareAndSet(false, true)) {
        logger.warning("top_p is not supported on Gemini 3.6 and newer models and is ignored.");
      }
      JsonNode topK = generationConfig.get("top_k");
      if (topK != null && !topK.isNull() && loggedTopKWarning.compareAndSet(false, true)) {
        logger.warning("top_k is not supported on Gemini 3.6 and newer models and is ignored.");
      }
    } catch (Exception ignored) {
      // Ignore body parsing errors in pre-request warning check.
    }
  }

    public static void initialize(com.google.genai.gaos.utils.Hooks hooks) {
    hooks.registerBeforeRequest(
        (context, request) -> {
          warnUnsupportedInteractionParameters(context, request);
          if (context.securitySource().isPresent()) {
            HasSecurity hasSecurity = context.securitySource().get().getSecurity();
            if (hasSecurity instanceof Security) {
              Security security = (Security) hasSecurity;
              HttpRequest.Builder builder = request.toBuilder();

              if (security.defaultHeaders().isPresent()) {
                for (Map.Entry<String, String> entry : security.defaultHeaders().get().entrySet()) {
                  builder.setHeader(entry.getKey(), entry.getValue());
                }
              }
              if (security.apiKey().isPresent()) {
                builder.setHeader("x-goog-api-key", security.apiKey().get());
              } else if (security.accessToken().isPresent()) {
                builder.setHeader("Authorization", "Bearer " + security.accessToken().get());
              }
              return builder.build();
            }
          }
          return request;
        });
    }

    public static void initialize(com.google.genai.gaos.utils.AsyncHooks asyncHooks) {
    asyncHooks.registerBeforeRequest(
        (context, request) -> {
          warnUnsupportedInteractionParameters(context, request);
          if (context.securitySource().isPresent()) {
            HasSecurity hasSecurity = context.securitySource().get().getSecurity();
            if (hasSecurity instanceof Security) {
              Security security = (Security) hasSecurity;
              HttpRequest.Builder builder = request.toBuilder();

              if (security.defaultHeaders().isPresent()) {
                for (Map.Entry<String, String> entry : security.defaultHeaders().get().entrySet()) {
                  builder.setHeader(entry.getKey(), entry.getValue());
                }
              }
              if (security.apiKey().isPresent()) {
                builder.setHeader("x-goog-api-key", security.apiKey().get());
              } else if (security.accessToken().isPresent()) {
                builder.setHeader("Authorization", "Bearer " + security.accessToken().get());
              }
              return CompletableFuture.completedFuture(builder.build());
            }
          }
          return CompletableFuture.completedFuture(request);
        });
    }

}
