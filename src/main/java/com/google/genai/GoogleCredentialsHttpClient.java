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

package com.google.genai;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.interactions.core.RequestOptions;
import com.google.genai.interactions.core.http.HttpClient;
import com.google.genai.interactions.core.http.HttpRequest;
import com.google.genai.interactions.core.http.HttpResponse;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/** A wrapper {@link HttpClient} that adds request headers from {@link GoogleCredentials}. */
final class GoogleCredentialsHttpClient implements HttpClient {
    private final HttpClient delegate;
    private final GoogleCredentials credentials;

    GoogleCredentialsHttpClient(HttpClient delegate, GoogleCredentials credentials) {
        this.delegate = delegate;
        this.credentials = credentials;
    }

    @Override
    public HttpResponse execute(HttpRequest request, RequestOptions requestOptions) {
        return delegate.execute(withGoogleCredentialsHeaders(request), requestOptions);
    }

    @Override
    public HttpResponse execute(HttpRequest request) {
        return delegate.execute(withGoogleCredentialsHeaders(request));
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsync(HttpRequest request, RequestOptions requestOptions) {
        return CompletableFuture.supplyAsync(() -> withGoogleCredentialsHeaders(request))
                .thenCompose(newRequest -> delegate.executeAsync(newRequest, requestOptions));
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsync(HttpRequest request) {
        return CompletableFuture.supplyAsync(() -> withGoogleCredentialsHeaders(request))
                .thenCompose(delegate::executeAsync);
    }

    private HttpRequest withGoogleCredentialsHeaders(HttpRequest request) {
        Set<String> headerNames = request.headers().names();
        if (headerNames.contains("Authorization") || headerNames.contains("x-goog-api-key")) {
            return request;
        }

        try {
            credentials.refreshIfExpired();
        } catch (IOException e) {
            throw new GenAiIOException("Failed to refresh credentials.", e);
        }

        String accessToken = credentials.getAccessToken().getTokenValue();
        HttpRequest.Builder builder = request.toBuilder().putHeader("Authorization", "Bearer " + accessToken);
        if (credentials.getQuotaProjectId() != null && !headerNames.contains("x-goog-user-project")) {
            builder = builder.putHeader("x-goog-user-project", credentials.getQuotaProjectId());
        }
        return builder.build();
    }

    @Override
    public void close() {
        delegate.close();
    }
}

