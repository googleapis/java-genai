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
import com.google.genai.interactions.services.blocking.InteractionService
import com.google.genai.interactions.services.blocking.InteractionServiceImpl
import java.util.function.Consumer

class GeminiNextGenApiClientImpl(private val clientOptions: ClientOptions) :
    GeminiNextGenApiClient {

    private val clientOptionsWithUserAgent =
        if (clientOptions.headers.names().contains("User-Agent")) clientOptions
        else
            clientOptions
                .toBuilder()
                .putHeader("User-Agent", "${javaClass.simpleName}/Java ${getPackageVersion()}")
                .build()

    // Pass the original clientOptions so that this client sets its own User-Agent.
    private val async: GeminiNextGenApiClientAsync by lazy {
        GeminiNextGenApiClientAsyncImpl(clientOptions)
    }

    private val withRawResponse: GeminiNextGenApiClient.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    @ExperimentalGenAiApi
    private val interactions: InteractionService by lazy {
        InteractionServiceImpl(clientOptionsWithUserAgent)
    }

    override fun async(): GeminiNextGenApiClientAsync = async

    override fun withRawResponse(): GeminiNextGenApiClient.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): GeminiNextGenApiClient =
        GeminiNextGenApiClientImpl(clientOptions.toBuilder().apply(modifier::accept).build())

        @ExperimentalGenAiApi
    override fun interactions(): InteractionService = interactions

    override fun close() = clientOptions.close()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        GeminiNextGenApiClient.WithRawResponse {

        @ExperimentalGenAiApi
        private val interactions: InteractionService.WithRawResponse by lazy {
            InteractionServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): GeminiNextGenApiClient.WithRawResponse =
            GeminiNextGenApiClientImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        @ExperimentalGenAiApi
        override fun interactions(): InteractionService.WithRawResponse = interactions
    }
}
