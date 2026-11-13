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

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.genai.types.HttpRetryOptions;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RetryInterceptorTest {

  private Interceptor.Chain chain;
  private Call call;
  private AtomicBoolean canceled;
  private FakeRandom random;

  private Request baseRequest;
  private HttpRetryOptions retryOptions;
  private Response successResponse;
  private Response retryableErrorResponse;
  private Response nonRetryableErrorResponse;
  private IOException ioException;

  @BeforeEach
  void setUp() {
    chain = mock(Interceptor.Chain.class);
    call = mock(Call.class);
    canceled = new AtomicBoolean(false);
    random = new FakeRandom();
    // A base request that our mock chain will return
    baseRequest = new Request.Builder().url("http://localhost").build();
    lenient().when(chain.request()).thenReturn(baseRequest);
    lenient().when(chain.call()).thenReturn(call);
    lenient().when(call.isCanceled()).thenAnswer(invocation -> canceled.get());

    // Default retry options
    retryOptions =
        HttpRetryOptions.builder()
            .attempts(3)
            .httpStatusCodes(503)
            // Set to small values to make the test faster
            .initialDelay(0.1)
            .maxDelay(0.5)
            .expBase(3.0)
            .jitter(0.5)
            .build();

    // A successful 200 OK response
    successResponse = createMockResponse(200);

    // A 503 error, which is in the default retry list
    retryableErrorResponse = createMockResponse(503);

    // A 404 error, which is NOT in the default retry list
    nonRetryableErrorResponse = createMockResponse(404);

    // An IOException
    ioException = new IOException("Network failed");
  }


  /** Helper to create mock OkHttp Responses. */
  private Response createMockResponse(int code) {
    return new Response.Builder()
        .request(baseRequest)
        .code(code)
        .protocol(Protocol.HTTP_1_1)
        .message("Mock response")
        .body(ResponseBody.create("", MediaType.get("application/json")))
        .build();
  }

  @Test
  void testIntercept_SuccessfulOnFirstTry() throws Exception {
    RetryInterceptor interceptorSpy = spy(new RetryInterceptor(retryOptions));
    when(chain.proceed(baseRequest)).thenReturn(successResponse);

    Response response = interceptorSpy.intercept(chain);

    verify(chain, times(1)).proceed(baseRequest);
    assertEquals(200, response.code());
  }

  @Test
  void testIntercept_NonRetryableError_NoRetry() throws Exception {
    RetryInterceptor interceptorSpy = spy(new RetryInterceptor(retryOptions));
    when(chain.proceed(baseRequest)).thenReturn(nonRetryableErrorResponse);

    Response response = interceptorSpy.intercept(chain);

    verify(chain, times(1)).proceed(baseRequest);
    assertEquals(404, response.code());
  }

  @Test
  void testIntercept_RetryableError_ThenSuccess() throws Exception {
    RetryInterceptor interceptorSpy = spy(new RetryInterceptor(retryOptions));
    when(chain.proceed(baseRequest)).thenReturn(retryableErrorResponse).thenReturn(successResponse);

    Response response = interceptorSpy.intercept(chain);

    verify(chain, times(2)).proceed(baseRequest);
    assertEquals(200, response.code());
  }

  @Test
  void testIntercept_RetryableError_ExhaustsAttempts() throws Exception {
    RetryInterceptor interceptorSpy = spy(new RetryInterceptor(retryOptions));
    when(chain.proceed(baseRequest))
        .thenReturn(retryableErrorResponse)
        .thenReturn(retryableErrorResponse)
        .thenReturn(retryableErrorResponse)
        .thenReturn(successResponse);

    Response response = interceptorSpy.intercept(chain);

    verify(chain, times(3)).proceed(baseRequest);
    verify(interceptorSpy, times(2)).calculateDelay(any(), anyInt());
    assertEquals(503, response.code());
  }

  @Test
  void testIntercept_NonRetryableErrorWithCustomCodeList_thenSuccess() throws Exception {
    HttpRetryOptions customRetryOptions =
        HttpRetryOptions.builder().attempts(3).httpStatusCodes(400, 404).build();
    RetryInterceptor interceptorSpy = spy(new RetryInterceptor(customRetryOptions));
    when(chain.proceed(baseRequest))
        .thenReturn(nonRetryableErrorResponse)
        .thenReturn(successResponse);

    Response response = interceptorSpy.intercept(chain);

    verify(chain, times(2)).proceed(baseRequest);
    assertEquals(200, response.code());
  }

  @Test
  void testIntercept_defaultAttempts_retriesFiveTimes() throws Exception {
    HttpRetryOptions defaultRetryOptions = HttpRetryOptions.builder().initialDelay(0.0).build();
    RetryInterceptor interceptorSpy = spy(new RetryInterceptor(defaultRetryOptions));
    when(chain.proceed(baseRequest)).thenReturn(retryableErrorResponse);

    Response response = interceptorSpy.intercept(chain);

    verify(chain, times(5)).proceed(baseRequest);
    assertEquals(503, response.code());
  }

  @Test
  void testIntercept_defaultHttpStatusCodes_retriesWithDefaultCodes() throws Exception {
    HttpRetryOptions defaultRetryOptions =
        HttpRetryOptions.builder().attempts(7).initialDelay(0.0).build();
    RetryInterceptor interceptorSpy = spy(new RetryInterceptor(defaultRetryOptions));
    when(chain.proceed(baseRequest))
        .thenReturn(createMockResponse(408))
        .thenReturn(createMockResponse(429))
        .thenReturn(createMockResponse(500))
        .thenReturn(createMockResponse(502))
        .thenReturn(createMockResponse(503))
        .thenReturn(createMockResponse(504))
        .thenReturn(successResponse);

    Response response = interceptorSpy.intercept(chain);

    verify(chain, times(7)).proceed(baseRequest);
    assertEquals(200, response.code());
  }

  @Test
  void testIntercept_IOException_ThenSuccess() throws Exception {
    RetryInterceptor interceptorSpy = spy(new RetryInterceptor(retryOptions));
    when(chain.proceed(baseRequest)).thenThrow(ioException).thenReturn(successResponse);

    Response response = interceptorSpy.intercept(chain);

    verify(chain, times(2)).proceed(baseRequest);
    assertEquals(200, response.code());
  }

  @Test
  void testIntercept_IOException_ExhaustsAttempts() throws Exception {
    RetryInterceptor interceptorSpy = spy(new RetryInterceptor(retryOptions));
    when(chain.proceed(baseRequest))
        .thenThrow(ioException)
        .thenThrow(ioException)
        .thenThrow(ioException)
        .thenReturn(successResponse);

    assertThrows(IOException.class, () -> interceptorSpy.intercept(chain));
    verify(chain, times(3)).proceed(baseRequest);
  }

  @Test
  void testIntercept_PerRequestOptions_OverridesClientLevelOptions() throws Exception {
    HttpRetryOptions perRequestOptions = HttpRetryOptions.builder().attempts(2).build();
    Request customRequest =
        baseRequest.newBuilder().tag(HttpRetryOptions.class, perRequestOptions).build();
    when(chain.request()).thenReturn(customRequest);
    when(chain.proceed(customRequest)).thenReturn(retryableErrorResponse);

    RetryInterceptor interceptorSpy = spy(new RetryInterceptor(retryOptions));

    Response response = interceptorSpy.intercept(chain);

    verify(chain, times(2)).proceed(customRequest);
    assertEquals(503, response.code());
  }

  @Test
  void testIntercept_IOExceptionAfterCancel_RethrowsWithoutRetry() throws Exception {
    RetryInterceptor interceptorSpy = spy(new RetryInterceptor(retryOptions));
    when(chain.proceed(baseRequest))
        .thenAnswer(
            invocation -> {
              canceled.set(true);
              throw ioException;
            });

    IOException thrown = assertThrows(IOException.class, () -> interceptorSpy.intercept(chain));

    assertSame(ioException, thrown);
    verify(chain, times(1)).proceed(baseRequest);
    verify(interceptorSpy, never()).calculateDelay(any(), anyInt());
  }

  @Test
  void testIntercept_RetryableErrorAfterCancel_ThrowsWithoutRetry() throws Exception {
    RetryInterceptor interceptorSpy = spy(new RetryInterceptor(retryOptions));
    when(chain.proceed(baseRequest))
        .thenAnswer(
            invocation -> {
              canceled.set(true);
              return retryableErrorResponse;
            });

    IOException thrown = assertThrows(IOException.class, () -> interceptorSpy.intercept(chain));

    assertEquals("Canceled", thrown.getMessage());
    verify(chain, times(1)).proceed(baseRequest);
  }

  @Test
  void testIntercept_CanceledDuringBackoff_StopsWithoutWaitingOutTheDelay() throws Exception {
    RetryInterceptor interceptorSpy = spy(new RetryInterceptor(retryOptions));
    when(chain.proceed(baseRequest)).thenReturn(retryableErrorResponse);
    doReturn(30_000L).when(interceptorSpy).calculateDelay(any(), anyInt());
    ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    try {
      executor.schedule(() -> canceled.set(true), 200, MILLISECONDS);

      IOException thrown =
          assertTimeoutPreemptively(
              Duration.ofSeconds(5),
              () -> assertThrows(IOException.class, () -> interceptorSpy.intercept(chain)));

      assertEquals("Canceled", thrown.getMessage());
      verify(chain, times(1)).proceed(baseRequest);
    } finally {
      executor.shutdownNow();
    }
  }

  @Test
  void testIntercept_CallTimeoutExpires_StopsRetrying() throws Exception {
    HttpRetryOptions slowRetryOptions =
        HttpRetryOptions.builder().attempts(3).initialDelay(30.0).jitter(0.0).build();
    AtomicInteger attempts = new AtomicInteger();
    // Accepts connections through the listen backlog but never responds.
    try (ServerSocket server = new ServerSocket(0, 50, InetAddress.getByName("127.0.0.1"))) {
      OkHttpClient client =
          new OkHttpClient.Builder()
              .callTimeout(Duration.ofMillis(200))
              .addInterceptor(new RetryInterceptor(slowRetryOptions))
              .addInterceptor(
                  attemptChain -> {
                    attempts.incrementAndGet();
                    return attemptChain.proceed(attemptChain.request());
                  })
              .build();
      Request request =
          new Request.Builder().url("http://127.0.0.1:" + server.getLocalPort()).build();

      assertTimeoutPreemptively(
          Duration.ofSeconds(10),
          () ->
              assertThrows(InterruptedIOException.class, () -> client.newCall(request).execute()));
    }

    assertEquals(1, attempts.get());
  }

  @Test
  void testCalculateDelay_FirstAttempt() {
    RetryInterceptor interceptor = new RetryInterceptor(retryOptions, random);
    random.setNextDouble(0.5);

    long delayMs = interceptor.calculateDelay(retryOptions, 1);

    assertEquals(100L, delayMs); // 0.1
  }

  @Test
  void testCalculateDelay_SecondAttempt() {
    RetryInterceptor interceptor = new RetryInterceptor(retryOptions, random);
    random.setNextDouble(1.0);

    long delayMs = interceptor.calculateDelay(retryOptions, 2);

    assertEquals(450L, delayMs); // 0.1 * (3^1) * (1.0 + 0.5 * (1.0)) = 0.45
  }

  @Test
  void testCalculateDelay_ThirdAttempt_ReachMaxDelay() {
    RetryInterceptor interceptor = new RetryInterceptor(retryOptions, random);
    random.setNextDouble(0.5);

    long delayMs = interceptor.calculateDelay(retryOptions, 3);

    assertEquals(500L, delayMs); // 0.1 * (3^2) = 0.9 > 0.5, so max delay is reached
  }

  @Test
  void testCalculateDelay_DefaultValues() {
    RetryInterceptor interceptor = new RetryInterceptor(HttpRetryOptions.builder().build(), random);
    random.setNextDouble(1.0);

    long delayMs = interceptor.calculateDelay(HttpRetryOptions.builder().build(), 2);

    // Default values: initialDelay = 1.0, maxDelay = 60.0, expBase = 2.0, jitter = 1.0
    // Delay = 1.0 * (2^1) * (1.0 + 1.0 * (1.0 * 2.0 - 1.0)) = 4.0
    assertEquals(4000L, delayMs);
  }

  @Test
  void testCalculateDelay_DefaultValues_MaxDelay() {
    RetryInterceptor interceptor = new RetryInterceptor(HttpRetryOptions.builder().build(), random);
    random.setNextDouble(1.0);

    long delayMs = interceptor.calculateDelay(HttpRetryOptions.builder().build(), 10);

    // Default maxDelay is 60.0
    assertEquals(60000L, delayMs);
  }

  private static class FakeRandom extends Random {
    private double nextDoubleValue = 0.0;

    void setNextDouble(double value) {
      this.nextDoubleValue = value;
    }

    @Override
    public double nextDouble() {
      return nextDoubleValue;
    }
  }
}
