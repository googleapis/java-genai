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

import com.google.genai.interactions.core.RequestOptions;
import com.google.genai.interactions.core.http.HttpClient;
import com.google.genai.interactions.core.http.HttpRequest;
import com.google.genai.interactions.core.http.HttpResponse;

import java.util.concurrent.CompletableFuture;

/**
 * A wrapper {@link HttpClient} that does not close its delegate when the wrapper is closed.
 *
 * <p>This is used to pass the client to other classes that may try to close it, but we don't want them to because we
 * want to retain ownership of the client.
 */
final class NonClosingDelegatingHttpClient implements HttpClient {
    private final HttpClient delegate;

    NonClosingDelegatingHttpClient(HttpClient delegate) {
        this.delegate = delegate;
    }

    @Override
    public HttpResponse execute(HttpRequest request, RequestOptions requestOptions) {
        return delegate.execute(request, requestOptions);
    }

    @Override
    public HttpResponse execute(HttpRequest request) {
        return delegate.execute(request);
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsync(HttpRequest request, RequestOptions requestOptions) {
        return delegate.executeAsync(request, requestOptions);
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsync(HttpRequest request) {
        return delegate.executeAsync(request);
    }

    @Override
    public void close() {
        // Purposefully empty.
    }
}
