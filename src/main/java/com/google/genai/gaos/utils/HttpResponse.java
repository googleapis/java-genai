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

import java.io.InputStream;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@SuppressWarnings("all")
public interface HttpResponse<T> {
    int statusCode();
    HttpRequest request();
    Optional<HttpResponse<T>> previousResponse();
    HttpHeaders headers();
    T body();
    
    default Optional<javax.net.ssl.SSLSession> sslSession() {
        return Optional.empty();
    }
    
    default URI uri() {
        return request().uri();
    }
    
    default HttpClient.Version version() {
        return HttpClient.Version.HTTP_1_1;
    }

    interface BodyHandler<T> {
        BodySubscriber<T> apply(ResponseInfo responseInfo);
    }

    interface ResponseInfo {
        int statusCode();
        HttpHeaders headers();
        default HttpClient.Version version() {
            return HttpClient.Version.HTTP_1_1;
        }
    }

    interface BodySubscriber<T> extends Flow.Subscriber<List<ByteBuffer>> {
        CompletionStage<T> getBody();
    }

    class BodyHandlers {
        public static BodyHandler<InputStream> ofInputStream() {
            return responseInfo -> BodySubscribers.ofInputStream();
        }
        public static BodyHandler<Flow.Publisher<List<ByteBuffer>>> ofPublisher() {
            return responseInfo -> new PublisherBodySubscriber();
        }
    }

    class BodySubscribers {
        public static BodySubscriber<byte[]> ofByteArray() {
            return new ByteArraySubscriber();
        }
        public static BodySubscriber<Path> ofFile(Path path) {
            return new FileSubscriber(path);
        }
        public static BodySubscriber<InputStream> ofInputStream() {
            return new InputStreamSubscriber();
        }
    }
}

@SuppressWarnings("all")
class PublisherBodySubscriber implements HttpResponse.BodySubscriber<Flow.Publisher<List<ByteBuffer>>> {
    private final CompletableFuture<Flow.Publisher<List<ByteBuffer>>> bodyFuture = new CompletableFuture<>();
    private final SimplePublisher publisher = new SimplePublisher();

    PublisherBodySubscriber() {
        bodyFuture.complete(publisher);
    }

    @Override
    public CompletionStage<Flow.Publisher<List<ByteBuffer>>> getBody() {
        return bodyFuture;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        publisher.setSubscription(subscription);
    }

    @Override
    public void onNext(List<ByteBuffer> item) {
        publisher.onNext(item);
    }

    @Override
    public void onError(Throwable throwable) {
        publisher.onError(throwable);
    }

    @Override
    public void onComplete() {
        publisher.onComplete();
    }
}

@SuppressWarnings("all")
class SimplePublisher implements Flow.Publisher<List<ByteBuffer>> {
    private Flow.Subscription subscription;
    private Flow.Subscriber<? super List<ByteBuffer>> subscriber;

    synchronized void setSubscription(Flow.Subscription subscription) {
        this.subscription = subscription;
        if (subscriber != null) {
            subscriber.onSubscribe(subscription);
        }
    }

    @Override
    public synchronized void subscribe(Flow.Subscriber<? super List<ByteBuffer>> subscriber) {
        this.subscriber = subscriber;
        if (subscription != null) {
            subscriber.onSubscribe(subscription);
        }
    }

    synchronized void onNext(List<ByteBuffer> item) {
        if (subscriber != null) {
            subscriber.onNext(item);
        }
    }

    synchronized void onError(Throwable throwable) {
        if (subscriber != null) {
            subscriber.onError(throwable);
        }
    }

    synchronized void onComplete() {
        if (subscriber != null) {
            subscriber.onComplete();
        }
    }
}

@SuppressWarnings("all")
class ByteArraySubscriber implements HttpResponse.BodySubscriber<byte[]> {
    private final CompletableFuture<byte[]> body = new CompletableFuture<>();
    private final java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
    private Flow.Subscription subscription;

    @Override
    public CompletionStage<byte[]> getBody() {
        return body;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(List<ByteBuffer> item) {
        for (ByteBuffer bb : item) {
            if (bb.hasArray()) {
                bos.write(bb.array(), bb.arrayOffset() + bb.position(), bb.remaining());
            } else {
                byte[] bytes = new byte[bb.remaining()];
                bb.get(bytes);
                bos.write(bytes, 0, bytes.length);
            }
        }
    }

    @Override
    public void onError(Throwable throwable) {
        body.completeExceptionally(throwable);
    }

    @Override
    public void onComplete() {
        body.complete(bos.toByteArray());
    }
}

@SuppressWarnings("all")
class FileSubscriber implements HttpResponse.BodySubscriber<Path> {
    private final Path path;
    private final CompletableFuture<Path> body = new CompletableFuture<>();
    private java.nio.channels.FileChannel channel;
    private Flow.Subscription subscription;

    FileSubscriber(Path path) {
        this.path = path;
    }

    @Override
    public CompletionStage<Path> getBody() {
        return body;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        try {
            channel = java.nio.channels.FileChannel.open(path,
                    java.nio.file.StandardOpenOption.CREATE,
                    java.nio.file.StandardOpenOption.WRITE,
                    java.nio.file.StandardOpenOption.TRUNCATE_EXISTING);
            subscription.request(Long.MAX_VALUE);
        } catch (java.io.IOException e) {
            body.completeExceptionally(e);
            subscription.cancel();
        }
    }

    @Override
    public void onNext(List<ByteBuffer> item) {
        for (ByteBuffer bb : item) {
            try {
                while (bb.hasRemaining()) {
                    channel.write(bb);
                }
            } catch (java.io.IOException e) {
                onError(e);
                subscription.cancel();
                return;
            }
        }
    }

    @Override
    public void onError(Throwable throwable) {
        closeChannel();
        body.completeExceptionally(throwable);
    }

    @Override
    public void onComplete() {
        closeChannel();
        body.complete(path);
    }

    private void closeChannel() {
        if (channel != null) {
            try { channel.close(); } catch (java.io.IOException ignored) {}
        }
    }
}

@SuppressWarnings("all")
class InputStreamSubscriber implements HttpResponse.BodySubscriber<InputStream> {
    private final java.io.PipedInputStream pis = new java.io.PipedInputStream(16384);
    private java.io.PipedOutputStream pos;
    private final CompletableFuture<InputStream> body = new CompletableFuture<>();
    private Flow.Subscription subscription;

    InputStreamSubscriber() {
        try {
            pos = new java.io.PipedOutputStream(pis);
            body.complete(pis);
        } catch (java.io.IOException e) {
            body.completeExceptionally(e);
        }
    }

    @Override
    public CompletionStage<InputStream> getBody() {
        return body;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        if (body.isCompletedExceptionally()) {
            subscription.cancel();
            return;
        }
        subscription.request(1);
    }

    @Override
    public void onNext(List<ByteBuffer> item) {
        try {
            for (ByteBuffer bb : item) {
                if (bb.hasArray()) {
                    pos.write(bb.array(), bb.arrayOffset() + bb.position(), bb.remaining());
                } else {
                    byte[] bytes = new byte[bb.remaining()];
                    bb.get(bytes);
                    pos.write(bytes);
                }
            }
            pos.flush();
            subscription.request(1);
        } catch (java.io.IOException e) {
            onError(e);
            subscription.cancel();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        closeOut();
    }

    @Override
    public void onComplete() {
        closeOut();
    }

    private void closeOut() {
        if (pos != null) {
            try { pos.close(); } catch (java.io.IOException ignored) {}
        }
    }
}
