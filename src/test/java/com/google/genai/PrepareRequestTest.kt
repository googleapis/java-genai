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

package com.google.genai

import com.google.genai.interactions.core.ClientOptions
import com.google.genai.interactions.core.Params
import com.google.genai.interactions.core.VertexInfo
import com.google.genai.interactions.core.http.Headers
import com.google.genai.interactions.core.http.HttpClient
import com.google.genai.interactions.core.http.HttpMethod
import com.google.genai.interactions.core.http.HttpRequest
import com.google.genai.interactions.core.http.QueryParams
import com.google.genai.interactions.core.prepare
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock

class PrepareRequestTest {

    private lateinit var mockHttpClient: HttpClient

    @BeforeEach
    fun setUp() {
        mockHttpClient = mock(HttpClient::class.java)
    }

    private fun createClientOptions(vertexInfo: VertexInfo? = null): ClientOptions =
        ClientOptions.builder()
            .httpClient(mockHttpClient)
            .vertexInfo(vertexInfo)
            .build()

    private fun createRequest(pathSegments: List<String>): HttpRequest =
        HttpRequest.builder()
            .method(HttpMethod.POST)
            .baseUrl("https://example.com")
            .pathSegments(pathSegments)
            .build()

    private fun createEmptyParams(): Params = object : Params {
        override fun _headers(): Headers = Headers.builder().build()
        override fun _queryParams(): QueryParams = QueryParams.builder().build()
    }

    @Test
    fun `prepare transforms path segments when vertexInfo is present`() {
        val vertexInfo = VertexInfo("my-project", "us-central1")
        val clientOptions = createClientOptions(vertexInfo)
        val request = createRequest(listOf("v1beta", "interactions"))

        val preparedRequest = request.prepare(clientOptions, createEmptyParams())

        assertEquals(
            listOf("v1beta", "projects", "my-project", "locations", "us-central1", "interactions"),
            preparedRequest.pathSegments
        )
    }

    @Test
    fun `prepare does not transform path segments when vertexInfo is null`() {
        val clientOptions = createClientOptions(vertexInfo = null)
        val request = createRequest(listOf("v1beta", "interactions"))

        val preparedRequest = request.prepare(clientOptions, createEmptyParams())

        assertEquals(
            listOf("v1beta", "interactions"),
            preparedRequest.pathSegments
        )
    }
}

