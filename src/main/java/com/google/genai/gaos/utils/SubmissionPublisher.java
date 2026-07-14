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

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class SubmissionPublisher<T> implements Flow.Publisher<T> {
    private final List<T> items = new ArrayList<>();
    private boolean closed = false;
    private Throwable error;
    private final List<SubscriptionImpl> subscriptions = new ArrayList<>();

    public synchronized void submit(T item) {
        if (closed) throw new IllegalStateException("closed");
        items.add(item);
        for (SubscriptionImpl sub : subscriptions) {
            sub.emit();
        }
    }

    public synchronized void close() {
        closed = true;
        for (SubscriptionImpl sub : subscriptions) {
            sub.emit();
        }
    }

    public synchronized void closeExceptionally(Throwable error) {
        this.error = error;
        closed = true;
        for (SubscriptionImpl sub : subscriptions) {
            sub.emit();
        }
    }

    @Override
    public void subscribe(Flow.Subscriber<? super T> subscriber) {
        SubscriptionImpl subscription = new SubscriptionImpl(subscriber);
        synchronized (this) {
            subscriptions.add(subscription);
        }
        subscriber.onSubscribe(subscription);
    }

    private class SubscriptionImpl implements Flow.Subscription {
        private final Flow.Subscriber<? super T> subscriber;
        private int index = 0;
        private boolean cancelled = false;
        private final java.util.concurrent.atomic.AtomicLong requested = new java.util.concurrent.atomic.AtomicLong(0);

        SubscriptionImpl(Flow.Subscriber<? super T> subscriber) {
            this.subscriber = subscriber;
        }

        @Override
        public void request(long n) {
            if (n <= 0) {
                subscriber.onError(new IllegalArgumentException());
                return;
            }
            requested.getAndAdd(n);
            emit();
        }

        @Override
        public void cancel() {
            cancelled = true;
            synchronized (SubmissionPublisher.this) {
                subscriptions.remove(this);
            }
        }

        void emit() {
            synchronized (SubmissionPublisher.this) {
                while (!cancelled) {
                    if (index < items.size()) {
                        if (requested.get() > 0) {
                            subscriber.onNext(items.get(index++));
                            requested.decrementAndGet();
                        } else {
                            break;
                        }
                    } else if (closed) {
                        if (error != null) {
                            subscriber.onError(error);
                        } else {
                            subscriber.onComplete();
                        }
                        break;
                    } else {
                        break;
                    }
                }
            }
        }
    }
}
