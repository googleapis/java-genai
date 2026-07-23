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

package com.google.genai.gaos.utils;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@SuppressWarnings("all")
public interface HttpClient {
    enum Version {
        HTTP_1_1,
        HTTP_2
    }

    enum Redirect {
        ALWAYS,
        NEVER,
        NORMAL
    }

    <T> HttpResponse<T> send(HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler)
            throws IOException, InterruptedException;

    <T> CompletableFuture<HttpResponse<T>> sendAsync(HttpRequest request,
                                                    HttpResponse.BodyHandler<T> responseBodyHandler);

    static Builder newBuilder() {
        return new Builder();
    }

    static HttpClient newHttpClient() {
        return newBuilder().build();
    }

    class Builder {
        private Duration connectTimeout;
        private Executor executor;
        private Redirect redirect;

        public Builder connectTimeout(Duration connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder executor(Executor executor) {
            this.executor = executor;
            return this;
        }

        public Builder followRedirects(Redirect redirect) {
            this.redirect = redirect;
            return this;
        }

        public HttpClient build() {
            return new OkHttpClientWrapper(connectTimeout, executor, redirect);
        }
    }
}
