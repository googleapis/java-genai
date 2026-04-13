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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.interactions.core.RequestOptions;
import com.google.genai.interactions.core.http.HttpClient;
import com.google.genai.interactions.core.http.HttpMethod;
import com.google.genai.interactions.core.http.HttpRequest;
import com.google.genai.interactions.core.http.HttpResponse;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

public class GoogleCredentialsHttpClientTest {

    private static final String ACCESS_TOKEN = "test-access-token";
    private static final String QUOTA_PROJECT = "test-quota-project";

    private HttpClient mockDelegate;
    private GoogleCredentials mockCredentials;
    private HttpResponse mockResponse;

    @BeforeEach
    void setUp() {
        mockDelegate = mock(HttpClient.class);
        mockCredentials = mock(GoogleCredentials.class);
        mockResponse = mock(HttpResponse.class);

        AccessToken accessToken = AccessToken.newBuilder().setTokenValue(ACCESS_TOKEN).build();
        when(mockCredentials.getAccessToken()).thenReturn(accessToken);
    }

    private HttpRequest createRequest() {
        return HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("https://example.com")
                .build();
    }

    private HttpRequest createRequestWithHeader(String name, String value) {
        return HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("https://example.com")
                .putHeader(name, value)
                .build();
    }

    @Test
    void execute_addsAuthorizationHeader() throws IOException {
        GoogleCredentialsHttpClient client = new GoogleCredentialsHttpClient(mockDelegate, mockCredentials);
        when(mockDelegate.execute(any(HttpRequest.class), any(RequestOptions.class))).thenReturn(mockResponse);

        client.execute(createRequest(), RequestOptions.none());

        ArgumentCaptor<HttpRequest> requestCaptor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(mockDelegate).execute(requestCaptor.capture(), any(RequestOptions.class));
        verify(mockCredentials).refreshIfExpired();
        HttpRequest capturedRequest = requestCaptor.getValue();
        assertEquals("Bearer " + ACCESS_TOKEN, capturedRequest.headers().values("Authorization").get(0));
    }

    @Test
    void execute_withoutRequestOptions_addsAuthorizationHeader() throws IOException {
        GoogleCredentialsHttpClient client = new GoogleCredentialsHttpClient(mockDelegate, mockCredentials);
        when(mockDelegate.execute(any(HttpRequest.class))).thenReturn(mockResponse);

        client.execute(createRequest());

        ArgumentCaptor<HttpRequest> requestCaptor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(mockDelegate).execute(requestCaptor.capture());
        verify(mockCredentials).refreshIfExpired();
        HttpRequest capturedRequest = requestCaptor.getValue();
        assertEquals("Bearer " + ACCESS_TOKEN, capturedRequest.headers().values("Authorization").get(0));
    }

    @Test
    void execute_withExistingAuthorizationHeader_doesNotModify() throws IOException {
        GoogleCredentialsHttpClient client = new GoogleCredentialsHttpClient(mockDelegate, mockCredentials);
        when(mockDelegate.execute(any(HttpRequest.class), any(RequestOptions.class))).thenReturn(mockResponse);

        HttpRequest requestWithAuth = createRequestWithHeader("Authorization", "Bearer existing-token");
        client.execute(requestWithAuth, RequestOptions.none());

        ArgumentCaptor<HttpRequest> requestCaptor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(mockDelegate).execute(requestCaptor.capture(), any(RequestOptions.class));
        HttpRequest capturedRequest = requestCaptor.getValue();
        assertEquals("Bearer existing-token", capturedRequest.headers().values("Authorization").get(0));
    }

    @Test
    void execute_withExistingApiKeyHeader_doesNotModify() throws IOException {
        GoogleCredentialsHttpClient client = new GoogleCredentialsHttpClient(mockDelegate, mockCredentials);
        when(mockDelegate.execute(any(HttpRequest.class), any(RequestOptions.class))).thenReturn(mockResponse);

        HttpRequest requestWithApiKey = createRequestWithHeader("x-goog-api-key", "test-api-key");
        client.execute(requestWithApiKey, RequestOptions.none());

        ArgumentCaptor<HttpRequest> requestCaptor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(mockDelegate).execute(requestCaptor.capture(), any(RequestOptions.class));
        HttpRequest capturedRequest = requestCaptor.getValue();
        assertEquals("test-api-key", capturedRequest.headers().values("x-goog-api-key").get(0));
        assertTrue(capturedRequest.headers().values("Authorization").isEmpty());
    }

    @Test
    void execute_withQuotaProject_addsUserProjectHeader() throws IOException {
        GoogleCredentialsHttpClient client = new GoogleCredentialsHttpClient(mockDelegate, mockCredentials);
        when(mockDelegate.execute(any(HttpRequest.class), any(RequestOptions.class))).thenReturn(mockResponse);
        when(mockCredentials.getQuotaProjectId()).thenReturn(QUOTA_PROJECT);

        client.execute(createRequest(), RequestOptions.none());

        ArgumentCaptor<HttpRequest> requestCaptor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(mockDelegate).execute(requestCaptor.capture(), any(RequestOptions.class));
        HttpRequest capturedRequest = requestCaptor.getValue();
        assertEquals("Bearer " + ACCESS_TOKEN, capturedRequest.headers().values("Authorization").get(0));
        assertEquals(QUOTA_PROJECT, capturedRequest.headers().values("x-goog-user-project").get(0));
    }

    @Test
    void execute_withExistingUserProjectHeader_doesNotOverride() throws IOException {
        GoogleCredentialsHttpClient client = new GoogleCredentialsHttpClient(mockDelegate, mockCredentials);
        when(mockDelegate.execute(any(HttpRequest.class), any(RequestOptions.class))).thenReturn(mockResponse);
        when(mockCredentials.getQuotaProjectId()).thenReturn(QUOTA_PROJECT);

        HttpRequest requestWithProject = createRequestWithHeader("x-goog-user-project", "existing-project");
        client.execute(requestWithProject, RequestOptions.none());

        ArgumentCaptor<HttpRequest> requestCaptor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(mockDelegate).execute(requestCaptor.capture(), any(RequestOptions.class));
        HttpRequest capturedRequest = requestCaptor.getValue();
        assertEquals("existing-project", capturedRequest.headers().values("x-goog-user-project").get(0));
    }

    @Test
    void execute_whenRefreshFails_throwsGenAiIOException() throws IOException {
        GoogleCredentialsHttpClient client = new GoogleCredentialsHttpClient(mockDelegate, mockCredentials);
        doThrow(new IOException("Refresh failed")).when(mockCredentials).refreshIfExpired();

        GenAiIOException exception = assertThrows(GenAiIOException.class,
                () -> client.execute(createRequest(), RequestOptions.none()));

        assertEquals("Failed to refresh credentials.", exception.getMessage());
        assertInstanceOf(IOException.class, exception.getCause());
    }

    @Test
    void executeAsync_addsAuthorizationHeader() throws Exception {
        GoogleCredentialsHttpClient client = new GoogleCredentialsHttpClient(mockDelegate, mockCredentials);
        when(mockDelegate.executeAsync(any(HttpRequest.class), any(RequestOptions.class)))
                .thenReturn(CompletableFuture.completedFuture(mockResponse));

        client.executeAsync(createRequest(), RequestOptions.none()).get();

        ArgumentCaptor<HttpRequest> requestCaptor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(mockDelegate).executeAsync(requestCaptor.capture(), any(RequestOptions.class));
        HttpRequest capturedRequest = requestCaptor.getValue();
        assertEquals("Bearer " + ACCESS_TOKEN, capturedRequest.headers().values("Authorization").get(0));
    }

    @Test
    void executeAsync_withoutRequestOptions_addsAuthorizationHeader() throws Exception {
        GoogleCredentialsHttpClient client = new GoogleCredentialsHttpClient(mockDelegate, mockCredentials);
        when(mockDelegate.executeAsync(any(HttpRequest.class)))
                .thenReturn(CompletableFuture.completedFuture(mockResponse));

        client.executeAsync(createRequest()).get();

        ArgumentCaptor<HttpRequest> requestCaptor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(mockDelegate).executeAsync(requestCaptor.capture());
        HttpRequest capturedRequest = requestCaptor.getValue();
        assertEquals("Bearer " + ACCESS_TOKEN, capturedRequest.headers().values("Authorization").get(0));
    }

    @Test
    void executeAsync_whenRefreshFails_completesExceptionally() throws IOException {
        GoogleCredentialsHttpClient client = new GoogleCredentialsHttpClient(mockDelegate, mockCredentials);
        doThrow(new IOException("Refresh failed")).when(mockCredentials).refreshIfExpired();

        CompletableFuture<HttpResponse> future = client.executeAsync(createRequest(), RequestOptions.none());

        ExecutionException exception = assertThrows(ExecutionException.class, future::get);
        assertInstanceOf(GenAiIOException.class, exception.getCause());
        assertEquals("Failed to refresh credentials.", exception.getCause().getMessage());
    }

    @Test
    void close_delegatesToWrappedClient() {
        GoogleCredentialsHttpClient client = new GoogleCredentialsHttpClient(mockDelegate, mockCredentials);

        client.close();

        verify(mockDelegate).close();
    }
}

