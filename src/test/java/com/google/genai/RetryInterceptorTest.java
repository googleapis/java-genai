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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.genai.types.HttpRetryOptions;
import java.io.IOException;
import java.util.Random;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class RetryInterceptorTest {

  @Mock Interceptor.Chain chain;
  @Mock Random random;

  private Request baseRequest;
  private HttpRetryOptions retryOptions;
  private Response successResponse;
  private Response retryableErrorResponse;
  private Response nonRetryableErrorResponse;
  private IOException ioException;

  private AutoCloseable openMocks;

  @BeforeEach
  void setUp() {
    openMocks = MockitoAnnotations.openMocks(this);
    // A base request that our mock chain will return
    baseRequest = new Request.Builder().url("http://localhost").build();
    lenient().when(chain.request()).thenReturn(baseRequest);

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

  @AfterEach
  void tearDown() throws Exception {
    openMocks.close();
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
  void testCalculateDelay_FirstAttempt() {
    RetryInterceptor interceptor = new RetryInterceptor(retryOptions, random);
    when(random.nextDouble()).thenReturn(0.5);

    long delayMs = interceptor.calculateDelay(retryOptions, 1);

    assertEquals(100L, delayMs); // 0.1
  }

  @Test
  void testCalculateDelay_SecondAttempt() {
    RetryInterceptor interceptor = new RetryInterceptor(retryOptions, random);
    when(random.nextDouble()).thenReturn(1.0);

    long delayMs = interceptor.calculateDelay(retryOptions, 2);

    assertEquals(450L, delayMs); // 0.1 * (3^1) * (1.0 + 0.5 * (1.0)) = 0.45
  }

  @Test
  void testCalculateDelay_ThirdAttempt_ReachMaxDelay() {
    RetryInterceptor interceptor = new RetryInterceptor(retryOptions, random);
    when(random.nextDouble()).thenReturn(0.5);

    long delayMs = interceptor.calculateDelay(retryOptions, 3);

    assertEquals(500L, delayMs); // 0.1 * (3^2) = 0.9 > 0.5, so max delay is reached
  }

  @Test
  void testCalculateDelay_DefaultValues() {
    RetryInterceptor interceptor = new RetryInterceptor(HttpRetryOptions.builder().build(), random);
    when(random.nextDouble()).thenReturn(1.0);

    long delayMs = interceptor.calculateDelay(HttpRetryOptions.builder().build(), 2);

    // Default values: initialDelay = 1.0, maxDelay = 60.0, expBase = 2.0, jitter = 1.0
    // Delay = 1.0 * (2^1) * (1.0 + 1.0 * (1.0 * 2.0 - 1.0)) = 4.0
    assertEquals(4000L, delayMs);
  }

  @Test
  void testCalculateDelay_DefaultValues_MaxDelay() {
    RetryInterceptor interceptor = new RetryInterceptor(HttpRetryOptions.builder().build(), random);
    when(random.nextDouble()).thenReturn(1.0);

    long delayMs = interceptor.calculateDelay(HttpRetryOptions.builder().build(), 10);

    // Default maxDelay is 60.0
    assertEquals(60000L, delayMs);
  }
}
