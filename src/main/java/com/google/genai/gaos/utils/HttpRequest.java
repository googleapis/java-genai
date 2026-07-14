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
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@SuppressWarnings("all")
public interface HttpRequest {
    URI uri();
    String method();
    Optional<BodyPublisher> bodyPublisher();
    HttpHeaders headers();
    boolean expectContinue();
    Optional<HttpClient.Version> version();
    Optional<Duration> timeout();

    interface BodyPublisher extends Flow.Publisher<ByteBuffer> {
        long contentLength();
    }

    class BodyPublishers {
        public static BodyPublisher noBody() {
             return new NoBodyPublisher();
        }
        public static BodyPublisher ofByteArray(byte[] buf) {
             return new ByteArrayPublisher(buf);
        }
        public static BodyPublisher ofString(String s, Charset charset) {
             return new ByteArrayPublisher(s.getBytes(charset));
        }
        public static BodyPublisher ofString(String s) {
             return ofString(s, java.nio.charset.StandardCharsets.UTF_8);
        }
        public static BodyPublisher ofFile(Path path) {
             return new FilePublisher(path);
        }
        public static BodyPublisher ofInputStream(Supplier<? extends InputStream> streamSupplier) {
             return new InputStreamPublisher(streamSupplier);
        }
        public static BodyPublisher fromPublisher(Flow.Publisher<? extends ByteBuffer> publisher) {
             if (publisher instanceof BodyPublisher) {
                 return (BodyPublisher) publisher;
             }
             return new DelegatingPublisher(publisher);
        }
    }

    static Builder newBuilder() {
        return new Builder();
    }

    static Builder newBuilder(URI uri) {
        return new Builder().uri(uri);
    }

    class Builder {
        private URI uri;
        private String method = "GET";
        private BodyPublisher bodyPublisher;
        private final Map<String, List<String>> headers = new LinkedHashMap<>();
        private boolean expectContinue;
        private HttpClient.Version version;
        private Duration timeout;

        public Builder uri(URI uri) {
            this.uri = uri;
            return this;
        }

        public Builder header(String name, String value) {
            this.headers.computeIfAbsent(name, k -> new ArrayList<>()).add(value);
            return this;
        }

        public Builder setHeader(String name, String value) {
            List<String> values = new ArrayList<>();
            values.add(value);
            this.headers.put(name, values);
            return this;
        }

        public Builder method(String method, BodyPublisher bodyPublisher) {
            this.method = method;
            this.bodyPublisher = bodyPublisher;
            return this;
        }

        public Builder GET() {
            return method("GET", BodyPublishers.noBody());
        }

        public Builder POST(BodyPublisher bodyPublisher) {
            return method("POST", bodyPublisher);
        }

        public Builder PUT(BodyPublisher bodyPublisher) {
            return method("PUT", bodyPublisher);
        }

        public Builder DELETE() {
            return method("DELETE", BodyPublishers.noBody());
        }

        public Builder expectContinue(boolean expectContinue) {
            this.expectContinue = expectContinue;
            return this;
        }

        public Builder version(HttpClient.Version version) {
            this.version = version;
            return this;
        }

        public Builder timeout(Duration timeout) {
            this.timeout = timeout;
            return this;
        }

        public HttpRequest build() {
            return new ImmutableHttpRequest(uri, method, bodyPublisher, HttpHeaders.of(headers), expectContinue, version, timeout);
        }
    }
}

@SuppressWarnings("all")
class ImmutableHttpRequest implements HttpRequest {
    private final URI uri;
    private final String method;
    private final HttpRequest.BodyPublisher bodyPublisher;
    private final HttpHeaders headers;
    private final boolean expectContinue;
    private final HttpClient.Version version;
    private final Duration timeout;

    ImmutableHttpRequest(URI uri, String method, HttpRequest.BodyPublisher bodyPublisher, HttpHeaders headers,
                         boolean expectContinue, HttpClient.Version version, Duration timeout) {
        this.uri = uri;
        this.method = method;
        this.bodyPublisher = bodyPublisher;
        this.headers = headers;
        this.expectContinue = expectContinue;
        this.version = version;
        this.timeout = timeout;
    }

    @Override public URI uri() { return uri; }
    @Override public String method() { return method; }
    @Override public Optional<HttpRequest.BodyPublisher> bodyPublisher() { return Optional.ofNullable(bodyPublisher); }
    @Override public HttpHeaders headers() { return headers; }
    @Override public boolean expectContinue() { return expectContinue; }
    @Override public Optional<HttpClient.Version> version() { return Optional.ofNullable(version); }
    @Override public Optional<Duration> timeout() { return Optional.ofNullable(timeout); }
}

@SuppressWarnings("all")
class NoBodyPublisher implements HttpRequest.BodyPublisher {
    @Override
    public long contentLength() { return 0; }
    @Override
    public void subscribe(Flow.Subscriber<? super ByteBuffer> subscriber) {
        subscriber.onSubscribe(new Flow.Subscription() {
            @Override
            public void request(long n) {
                if (n > 0) {
                    subscriber.onComplete();
                }
            }
            @Override
            public void cancel() {}
        });
    }
}

@SuppressWarnings("all")
class ByteArrayPublisher implements HttpRequest.BodyPublisher {
    private final byte[] bytes;
    ByteArrayPublisher(byte[] bytes) { this.bytes = bytes; }
    @Override
    public long contentLength() { return bytes.length; }
    public byte[] getBytes() { return bytes; }

    @Override
    public void subscribe(Flow.Subscriber<? super ByteBuffer> subscriber) {
        subscriber.onSubscribe(new Flow.Subscription() {
            private boolean done = false;
            @Override
            public void request(long n) {
                if (n > 0 && !done) {
                    done = true;
                    subscriber.onNext(ByteBuffer.wrap(bytes));
                    subscriber.onComplete();
                }
            }
            @Override
            public void cancel() { done = true; }
        });
    }
}

@SuppressWarnings("all")
class FilePublisher implements HttpRequest.BodyPublisher {
    private final Path path;
    FilePublisher(Path path) { this.path = path; }
    @Override
    public long contentLength() {
        try {
            return java.nio.file.Files.size(path);
        } catch (java.io.IOException e) {
            return -1;
        }
    }
    public Path getPath() { return path; }

    @Override
    public void subscribe(Flow.Subscriber<? super ByteBuffer> subscriber) {
        subscriber.onSubscribe(new Flow.Subscription() {
            private java.nio.channels.SeekableByteChannel channel;
            private boolean cancelled = false;

            @Override
            public void request(long n) {
                if (n <= 0 || cancelled) return;
                try {
                    if (channel == null) {
                        channel = java.nio.file.Files.newByteChannel(path);
                    }
                    ByteBuffer buffer = ByteBuffer.allocate(16384);
                    long requested = n;
                    while (requested > 0 && !cancelled) {
                        buffer.clear();
                        int read = channel.read(buffer);
                        if (read < 0) {
                            channel.close();
                            subscriber.onComplete();
                            return;
                        }
                        buffer.flip();
                        subscriber.onNext(buffer.duplicate());
                        requested--;
                    }
                } catch (java.io.IOException e) {
                    if (channel != null) {
                        try { channel.close(); } catch (java.io.IOException ignored) {}
                    }
                    subscriber.onError(e);
                }
            }

            @Override
            public void cancel() {
                cancelled = true;
                if (channel != null) {
                    try { channel.close(); } catch (java.io.IOException ignored) {}
                }
            }
        });
    }
}

@SuppressWarnings("all")
class InputStreamPublisher implements HttpRequest.BodyPublisher {
    private final Supplier<? extends InputStream> streamSupplier;
    InputStreamPublisher(Supplier<? extends InputStream> streamSupplier) { this.streamSupplier = streamSupplier; }
    @Override
    public long contentLength() { return -1; }
    public InputStream getInputStream() { return streamSupplier.get(); }

    @Override
    public void subscribe(Flow.Subscriber<? super ByteBuffer> subscriber) {
        subscriber.onSubscribe(new Flow.Subscription() {
            private InputStream stream;
            private boolean cancelled = false;

            @Override
            public void request(long n) {
                if (n <= 0 || cancelled) return;
                try {
                    if (stream == null) {
                        stream = streamSupplier.get();
                        if (stream == null) {
                            subscriber.onError(new NullPointerException("InputStream supplier returned null"));
                            return;
                        }
                    }
                    byte[] buffer = new byte[16384];
                    long requested = n;
                    while (requested > 0 && !cancelled) {
                        int read = stream.read(buffer);
                        if (read < 0) {
                            stream.close();
                            subscriber.onComplete();
                            return;
                        }
                        subscriber.onNext(ByteBuffer.wrap(buffer, 0, read));
                        requested--;
                    }
                } catch (java.io.IOException e) {
                    if (stream != null) {
                        try { stream.close(); } catch (java.io.IOException ignored) {}
                    }
                    subscriber.onError(e);
                }
            }

            @Override
            public void cancel() {
                cancelled = true;
                if (stream != null) {
                    try { stream.close(); } catch (java.io.IOException ignored) {}
                }
            }
        });
    }
}

@SuppressWarnings("all")
class DelegatingPublisher implements HttpRequest.BodyPublisher {
    private final Flow.Publisher<? extends ByteBuffer> delegate;
    DelegatingPublisher(Flow.Publisher<? extends ByteBuffer> delegate) { this.delegate = delegate; }
    @Override
    public long contentLength() { return -1; }
    @Override
    public void subscribe(Flow.Subscriber<? super ByteBuffer> subscriber) {
        delegate.subscribe(new Flow.Subscriber<ByteBuffer>() {
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                subscriber.onSubscribe(subscription);
            }
            @Override
            public void onNext(ByteBuffer item) {
                subscriber.onNext(item);
            }
            @Override
            public void onError(Throwable throwable) {
                subscriber.onError(throwable);
            }
            @Override
            public void onComplete() {
                subscriber.onComplete();
            }
        });
    }
}
