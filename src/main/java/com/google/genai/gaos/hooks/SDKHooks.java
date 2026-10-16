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

import com.google.genai.gaos.models.shared.Security;
import com.google.genai.gaos.utils.HasSecurity;
import com.google.genai.gaos.utils.transport.HttpRequest;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

//
// This file is written once by speakeasy code generation and
// thereafter will not be overwritten by speakeasy updates. As a
// consequence any customization of this class will be preserved.
//

public final class SDKHooks {

    private SDKHooks() {
        // prevent instantiation
    }

    public static void initialize(com.google.genai.gaos.utils.Hooks hooks) {
        hooks.registerBeforeRequest(
            (context, request) -> {
              HttpRequest.Builder builder = null;
              if (context.securitySource().isPresent()) {
                HasSecurity hasSecurity = context.securitySource().get().getSecurity();
                if (hasSecurity instanceof Security) {
                  Security security = (Security) hasSecurity;
                  builder = request.toBuilder();

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
                }
              }
              if (context.sdkConfiguration() != null
                  && context.sdkConfiguration().globals != null
                  && !request.headers().firstValue("x-goog-user-project").isPresent()) {
                java.util.Optional<String> userProject =
                    context.sdkConfiguration().globals.getParam("header", "x-goog-user-project");
                if (userProject.isPresent()) {
                  if (builder == null) {
                    builder = request.toBuilder();
                  }
                  builder.setHeader("x-goog-user-project", userProject.get());
                }
              }
              return builder != null ? builder.build() : request;
            });
    }

    public static void initialize(com.google.genai.gaos.utils.AsyncHooks asyncHooks) {
        asyncHooks.registerBeforeRequest(
            (context, request) -> {
              HttpRequest.Builder builder = null;
              if (context.securitySource().isPresent()) {
                HasSecurity hasSecurity = context.securitySource().get().getSecurity();
                if (hasSecurity instanceof Security) {
                  Security security = (Security) hasSecurity;
                  builder = request.toBuilder();

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
                }
              }
              if (context.sdkConfiguration() != null
                  && context.sdkConfiguration().globals != null
                  && !request.headers().firstValue("x-goog-user-project").isPresent()) {
                java.util.Optional<String> userProject =
                    context.sdkConfiguration().globals.getParam("header", "x-goog-user-project");
                if (userProject.isPresent()) {
                  if (builder == null) {
                    builder = request.toBuilder();
                  }
                  builder.setHeader("x-goog-user-project", userProject.get());
                }
              }
              return CompletableFuture.completedFuture(
                  builder != null ? builder.build() : request);
            });
    }

}
