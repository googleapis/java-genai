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
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@SuppressWarnings("all")
class OkHttpClientWrapper implements HttpClient {
    private final okhttp3.OkHttpClient okHttpClient;
    private final Executor executor;

    public OkHttpClientWrapper(Duration connectTimeout, Executor executor, HttpClient.Redirect redirect) {
        okhttp3.OkHttpClient.Builder builder = new okhttp3.OkHttpClient.Builder();
        if (connectTimeout != null) {
            builder.connectTimeout(connectTimeout.toMillis(), java.util.concurrent.TimeUnit.MILLISECONDS);
        }
        if (redirect != null) {
            boolean follow = redirect != HttpClient.Redirect.NEVER;
            builder.followRedirects(follow);
            builder.followSslRedirects(follow);
        }
        this.okHttpClient = builder.build();
        this.executor = executor != null ? executor : java.util.concurrent.ForkJoinPool.commonPool();
    }

    @Override
    public <T> HttpResponse<T> send(HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler)
            throws IOException, InterruptedException {
        okhttp3.Request okHttpRequest = toOkHttpRequest(request);
        okhttp3.Call call = okHttpClient.newCall(okHttpRequest);
        // We cannot use try-with-resources here because the body might be read lazily
        // after this method returns (e.g. if T is InputStream).
        // The handleResponse method will ensure the okHttpResponse is closed when the body is completed or cancelled.
        okhttp3.Response okHttpResponse = call.execute();
        try {
            return handleResponse(request, okHttpResponse, responseBodyHandler);
        } catch (Exception e) {
            okHttpResponse.close();
            throw e;
        }
    }

    @Override
    public <T> CompletableFuture<HttpResponse<T>> sendAsync(
            HttpRequest request,
            HttpResponse.BodyHandler<T> responseBodyHandler) {
        CompletableFuture<HttpResponse<T>> future = new CompletableFuture<>();
        try {
            okhttp3.Request okHttpRequest = toOkHttpRequest(request);
            okhttp3.Call call = okHttpClient.newCall(okHttpRequest);
            call.enqueue(new okhttp3.Callback() {
                @Override
                public void onFailure(okhttp3.Call call, IOException e) {
                    future.completeExceptionally(e);
                }

                @Override
                public void onResponse(okhttp3.Call call, okhttp3.Response okHttpResponse) throws IOException {
                    try {
                        HttpResponse<T> response = handleResponse(request, okHttpResponse, responseBodyHandler);
                        future.complete(response);
                    } catch (Exception e) {
                        okHttpResponse.close();
                        future.completeExceptionally(e);
                    }
                }
            });
        } catch (Exception e) {
            future.completeExceptionally(e);
        }
        return future;
    }

    private okhttp3.Request toOkHttpRequest(HttpRequest request) throws IOException {
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
        builder.url(request.uri().toString());

        request.headers().map().forEach((name, values) -> {
            for (String value : values) {
                builder.addHeader(name, value);
            }
        });

        String method = request.method();
        okhttp3.RequestBody okHttpBody = null;
        if (request.bodyPublisher().isPresent()) {
            okHttpBody = toOkHttpRequestBody(request.bodyPublisher().get(), request.headers().firstValue("Content-Type").orElse(null));
        } else if (requiresRequestBody(method)) {
             okHttpBody = okhttp3.RequestBody.create(new byte[0], null);
        }
        builder.method(method, okHttpBody);

        return builder.build();
    }

    private boolean requiresRequestBody(String method) {
        return method.equals("POST") || method.equals("PUT") || method.equals("PATCH");
    }

    private okhttp3.RequestBody toOkHttpRequestBody(HttpRequest.BodyPublisher publisher, String contentType) {
        final okhttp3.MediaType mediaType = contentType != null ? okhttp3.MediaType.parse(contentType) : null;
        
        if (publisher instanceof ByteArrayPublisher) {
             return okhttp3.RequestBody.create(((ByteArrayPublisher) publisher).getBytes(), mediaType);
        }
        if (publisher instanceof NoBodyPublisher) {
             return okhttp3.RequestBody.create(new byte[0], mediaType);
        }

        return new okhttp3.RequestBody() {
            @Override
            public okhttp3.MediaType contentType() {
                return mediaType;
            }

            @Override
            public long contentLength() {
                return publisher.contentLength();
            }

            @Override
            public void writeTo(okio.BufferedSink sink) throws IOException {
                BlockingSubscriber subscriber = new BlockingSubscriber(sink);
                publisher.subscribe(subscriber);
                subscriber.awaitCompletion();
            }
        };
    }

    private <T> HttpResponse<T> handleResponse(
            HttpRequest request,
            okhttp3.Response okHttpResponse,
            HttpResponse.BodyHandler<T> responseBodyHandler) {

        HttpResponse.ResponseInfo responseInfo = new HttpResponse.ResponseInfo() {
            @Override public int statusCode() { return okHttpResponse.code(); }
            @Override public HttpHeaders headers() { return HttpHeaders.of(okHttpResponse.headers().toMultimap()); }
        };

        HttpResponse.BodySubscriber<T> subscriber = responseBodyHandler.apply(responseInfo);
        
        HttpResponse<T> response = new HttpResponse<T>() {
            @Override public int statusCode() { return okHttpResponse.code(); }
            @Override public HttpRequest request() { return request; }
            @Override public Optional<HttpResponse<T>> previousResponse() { return Optional.empty(); }
            @Override public HttpHeaders headers() { return HttpHeaders.of(okHttpResponse.headers().toMultimap()); }
            @Override public T body() { 
                try {
                    return subscriber.getBody().toCompletableFuture().get(); 
                } catch (Exception e) {
                    throw new RuntimeException("Failed to get body", e);
                }
            }
        };

        executor.execute(() -> {
            Flow.Subscription subscription = new Flow.Subscription() {
                private final java.util.concurrent.atomic.AtomicLong requested = new java.util.concurrent.atomic.AtomicLong(0);
                private final java.util.concurrent.atomic.AtomicBoolean cancelled = new java.util.concurrent.atomic.AtomicBoolean(false);
                private final java.util.concurrent.atomic.AtomicBoolean running = new java.util.concurrent.atomic.AtomicBoolean(false);
                private okhttp3.ResponseBody responseBody;
                private okio.BufferedSource source;

                @Override
                public void request(long n) {
                    if (n <= 0) {
                        subscriber.onError(new IllegalArgumentException("non-positive request"));
                        return;
                    }
                    requested.getAndAdd(n);
                    startReadLoop();
                }

                @Override
                public void cancel() {
                    cancelled.set(true);
                    closeAll();
                }

                private synchronized void initSource() {
                    if (responseBody == null) {
                        responseBody = okHttpResponse.body();
                        if (responseBody != null) {
                            source = responseBody.source();
                        }
                    }
                }

                private void closeAll() {
                    if (responseBody != null) {
                        responseBody.close();
                    }
                    okHttpResponse.close();
                }

                private void startReadLoop() {
                    if (running.compareAndSet(false, true)) {
                        executor.execute(this::readLoop);
                    }
                }

                private void readLoop() {
                    try {
                        initSource();
                        if (source == null) {
                            subscriber.onComplete();
                            return;
                        }
                        byte[] buffer = new byte[16384];
                        while (!cancelled.get()) {
                            long demand = requested.get();
                            if (demand <= 0) {
                                running.set(false);
                                if (requested.get() > 0 && running.compareAndSet(false, true)) {
                                    continue;
                                }
                                return;
                            }

                            int read = source.read(buffer);
                            if (read < 0) {
                                subscriber.onComplete();
                                closeAll();
                                return;
                            }
                            if (read > 0) {
                                byte[] copy = new byte[read];
                                System.arraycopy(buffer, 0, copy, 0, read);
                                 subscriber.onNext(java.util.Collections.singletonList(ByteBuffer.wrap(copy)));
                                requested.decrementAndGet();
                            }
                        }
                    } catch (IOException e) {
                        subscriber.onError(e);
                        closeAll();
                    }
                }
            };
            subscriber.onSubscribe(subscription);
        });

        return response;
    }

    private static class BlockingSubscriber implements Flow.Subscriber<ByteBuffer> {
        private final okio.BufferedSink sink;
        private Flow.Subscription subscription;
        private final java.util.concurrent.CountDownLatch latch = new java.util.concurrent.CountDownLatch(1);
        private Throwable error;

        BlockingSubscriber(okio.BufferedSink sink) {
            this.sink = sink;
        }

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            subscription.request(1);
        }

        @Override
        public void onNext(ByteBuffer item) {
            try {
                if (item.hasArray()) {
                    sink.write(item.array(), item.arrayOffset() + item.position(), item.remaining());
                } else {
                    byte[] bytes = new byte[item.remaining()];
                    item.get(bytes);
                    sink.write(bytes);
                }
                subscription.request(1);
            } catch (IOException e) {
                subscription.cancel();
                error = e;
                latch.countDown();
            }
        }

        @Override
        public void onError(Throwable throwable) {
            error = throwable;
            latch.countDown();
        }

        @Override
        public void onComplete() {
            latch.countDown();
        }

        void awaitCompletion() throws IOException {
            try {
                latch.await();
                if (error != null) {
                    if (error instanceof IOException) {
                        throw (IOException) error;
                    }
                    throw new IOException(error);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IOException("Interrupted waiting for body write", e);
            }
        }
    }
}
