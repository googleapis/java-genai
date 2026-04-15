/*
* Copyright 2025 Google LLC
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

// File generated from our OpenAPI spec by Stainless.

package com.google.genai.interactions.client

import com.google.genai.interactions.core.ClientOptions
import com.google.genai.interactions.core.ExperimentalGenAiApi
import com.google.genai.interactions.core.getPackageVersion
import com.google.genai.interactions.services.async.InteractionServiceAsync
import com.google.genai.interactions.services.async.InteractionServiceAsyncImpl
import java.util.function.Consumer

class GeminiNextGenApiClientAsyncImpl(private val clientOptions: ClientOptions) :
    GeminiNextGenApiClientAsync {

    private val clientOptionsWithUserAgent =
        if (clientOptions.headers.names().contains("User-Agent")) clientOptions
        else
            clientOptions
                .toBuilder()
                .putHeader("User-Agent", "${javaClass.simpleName}/Java ${getPackageVersion()}")
                .build()

    // Pass the original clientOptions so that this client sets its own User-Agent.
    private val sync: GeminiNextGenApiClient by lazy { GeminiNextGenApiClientImpl(clientOptions) }

    private val withRawResponse: GeminiNextGenApiClientAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    @ExperimentalGenAiApi
    private val interactions: InteractionServiceAsync by lazy {
        InteractionServiceAsyncImpl(clientOptionsWithUserAgent)
    }

    override fun sync(): GeminiNextGenApiClient = sync

    override fun withRawResponse(): GeminiNextGenApiClientAsync.WithRawResponse = withRawResponse

    override fun withOptions(
        modifier: Consumer<ClientOptions.Builder>
    ): GeminiNextGenApiClientAsync =
        GeminiNextGenApiClientAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    @ExperimentalGenAiApi
    override fun interactions(): InteractionServiceAsync = interactions

    override fun close() = clientOptions.close()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        GeminiNextGenApiClientAsync.WithRawResponse {

        @ExperimentalGenAiApi
        private val interactions: InteractionServiceAsync.WithRawResponse by lazy {
            InteractionServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): GeminiNextGenApiClientAsync.WithRawResponse =
            GeminiNextGenApiClientAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        @ExperimentalGenAiApi
        override fun interactions(): InteractionServiceAsync.WithRawResponse = interactions
    }
}
