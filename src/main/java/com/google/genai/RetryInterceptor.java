/*
 * Copyright 2025 Google LLC
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

import com.google.common.collect.ImmutableList;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.HttpRetryOptions;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/** Retry interceptor for the API requests. */
class RetryInterceptor implements Interceptor {

  // Default retry options.
  private static final int RETRY_MAX_ATTEMPTS = 5;
  private static final double RETRY_INITIAL_DELAY = 1.0; // in seconds
  private static final double RETRY_MAX_DELAY = 60.0; // in seconds
  private static final double RETRY_EXP_BASE = 2.0;
  private static final double RETRY_JITTER = 1.0;
  private static final ImmutableList<Integer> RETRY_HTTP_STATUS_CODES =
      ImmutableList.of(
          408, // Request timeout.
          429, // Too many requests.
          500, // Internal server error.
          502, // Bad gateway.
          503, // Service unavailable.
          504 // Gateway timeout
          );

  // How often a backoff sleep checks whether the call was canceled.
  private static final long CANCELLATION_CHECK_INTERVAL_MILLIS = 100;

  private final HttpRetryOptions retryOptions;
  private final Optional<Integer> timeout;
  private final Random random;

  /** Creates an interceptor with a retry strategy. */
  RetryInterceptor(HttpRetryOptions retryOptions) {
    this(retryOptions, Optional.empty());
  }

  /**
   * Creates an interceptor with a retry strategy and a timeout in milliseconds for each attempt.
   */
  RetryInterceptor(HttpRetryOptions retryOptions, Optional<Integer> timeout) {
    this(retryOptions, timeout, new Random());
  }

  /** Constructor for testing. Allows injecting a mock Random. */
  RetryInterceptor(HttpRetryOptions retryOptions, Random random) {
    this(retryOptions, Optional.empty(), random);
  }

  private RetryInterceptor(
      HttpRetryOptions retryOptions, Optional<Integer> timeout, Random random) {
    this.retryOptions = retryOptions;
    this.timeout = timeout;
    this.random = random;
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request request = chain.request();

    // Check if the request has a *per-request* retry options object attached as a tag.
    HttpRetryOptions perRequestOptions = request.tag(HttpRetryOptions.class);

    // Use per-request options if present, otherwise fall back to client-default options.
    HttpRetryOptions options = (perRequestOptions != null) ? perRequestOptions : this.retryOptions;

    // A per-request timeout overrides the client-level one.
    HttpOptions perRequestHttpOptions = request.tag(HttpOptions.class);
    Optional<Integer> attemptTimeout =
        (perRequestHttpOptions != null && perRequestHttpOptions.timeout().isPresent())
            ? perRequestHttpOptions.timeout()
            : this.timeout;
    Chain attemptChain = chain;
    if (attemptTimeout.isPresent()) {
      // Timeouts set on the chain bound each attempt. OkHttp's callTimeout would instead cancel the
      // whole call, and every retry with it.
      int timeoutMillis = attemptTimeout.get();
      attemptChain =
          chain
              .withConnectTimeout(timeoutMillis, TimeUnit.MILLISECONDS)
              .withReadTimeout(timeoutMillis, TimeUnit.MILLISECONDS)
              .withWriteTimeout(timeoutMillis, TimeUnit.MILLISECONDS);
    }

    if (options == null) {
      return attemptChain.proceed(request);
    }

    Call call = chain.call();
    Response response = null;
    int maxAttempts = options.attempts().orElse(RETRY_MAX_ATTEMPTS);
    maxAttempts = Math.max(maxAttempts, 1);
    List<Integer> httpStatusCodes = options.httpStatusCodes().orElse(RETRY_HTTP_STATUS_CODES);

    for (int attempt = 1; attempt <= maxAttempts; attempt++) {
      // A canceled call, for example one whose OkHttp callTimeout expired, can never succeed.
      if (call.isCanceled()) {
        throw new IOException("Canceled");
      }
      try {
        response = attemptChain.proceed(request);
        // If the response is successful, the response code is not in the retry list, or this was
        // the last attempt, exit the attempt loop.
        if (response.isSuccessful()
            || !httpStatusCodes.contains(response.code())
            || attempt == maxAttempts) {
          break;
        }
        // Close the unsuccessful response so that the connection can be reused for the next
        // attempt.
        response.close();
      } catch (IOException e) {
        // An attempt that ran out of time is not retried, so that retries don't multiply how long
        // the caller waits.
        if (attempt == maxAttempts
            || call.isCanceled()
            || (attemptTimeout.isPresent() && e instanceof SocketTimeoutException)) {
          throw e;
        }
      }

      try {
        sleepUnlessCanceled(call, calculateDelay(options, attempt));
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new IOException("Retry was interrupted.", e);
      }
    }
    return response;
  }

  /** Calculates the delay (in milliseconds) using exponential backoff with jitter. */
  long calculateDelay(HttpRetryOptions options, int attempt) {
    double initialDelay = options.initialDelay().orElse(RETRY_INITIAL_DELAY);
    double maxDelay = options.maxDelay().orElse(RETRY_MAX_DELAY);
    double expBase = options.expBase().orElse(RETRY_EXP_BASE);
    double jitter = options.jitter().orElse(RETRY_JITTER);

    double factor =
        Math.pow(expBase, attempt - 1) * (1.0 + jitter * (random.nextDouble() * 2.0 - 1.0));

    double delay = Math.min(maxDelay, initialDelay * factor);

    return (long) (delay * 1000); // Convert to milliseconds.
  }

  /** Returns the client-level retry options. */
  HttpRetryOptions retryOptions() {
    return retryOptions;
  }

  /** Returns the client-level timeout in milliseconds for each attempt. */
  Optional<Integer> timeout() {
    return timeout;
  }

  /** Sleeps for the given delay, returning early if the call is canceled in the meantime. */
  private static void sleepUnlessCanceled(Call call, long delayMillis) throws InterruptedException {
    long deadlineNanos = System.nanoTime() + TimeUnit.MILLISECONDS.toNanos(delayMillis);
    while (!call.isCanceled()) {
      long remainingMillis = TimeUnit.NANOSECONDS.toMillis(deadlineNanos - System.nanoTime());
      if (remainingMillis <= 0) {
        return;
      }
      Thread.sleep(Math.min(remainingMillis, CANCELLATION_CHECK_INTERVAL_MILLIS));
    }
  }
}
