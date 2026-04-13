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

package com.google.genai.interactions.services.async

import com.google.genai.interactions.core.ClientOptions
import com.google.genai.interactions.core.JsonValue
import com.google.genai.interactions.core.RequestOptions
import com.google.genai.interactions.core.checkRequired
import com.google.genai.interactions.core.handlers.errorBodyHandler
import com.google.genai.interactions.core.handlers.errorHandler
import com.google.genai.interactions.core.handlers.jsonHandler
import com.google.genai.interactions.core.handlers.mapJson
import com.google.genai.interactions.core.handlers.sseHandler
import com.google.genai.interactions.core.http.AsyncStreamResponse
import com.google.genai.interactions.core.http.HttpMethod
import com.google.genai.interactions.core.http.HttpRequest
import com.google.genai.interactions.core.http.HttpResponse
import com.google.genai.interactions.core.http.HttpResponse.Handler
import com.google.genai.interactions.core.http.HttpResponseFor
import com.google.genai.interactions.core.http.StreamResponse
import com.google.genai.interactions.core.http.json
import com.google.genai.interactions.core.http.map
import com.google.genai.interactions.core.http.parseable
import com.google.genai.interactions.core.http.toAsync
import com.google.genai.interactions.core.prepareAsync
import com.google.genai.interactions.models.interactions.CreateAgentInteractionParams
import com.google.genai.interactions.models.interactions.CreateModelInteractionParams
import com.google.genai.interactions.models.interactions.Interaction
import com.google.genai.interactions.models.interactions.InteractionCancelParams
import com.google.genai.interactions.models.interactions.InteractionCreateParams
import com.google.genai.interactions.models.interactions.InteractionDeleteParams
import com.google.genai.interactions.models.interactions.InteractionDeleteResponse
import com.google.genai.interactions.models.interactions.InteractionGetParams
import com.google.genai.interactions.models.interactions.InteractionSseEvent
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class InteractionServiceAsyncImpl constructor(private val clientOptions: ClientOptions) :
    InteractionServiceAsync {

    private val withRawResponse: InteractionServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): InteractionServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): InteractionServiceAsync =
        InteractionServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun create(
        params: InteractionCreateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Interaction> =
        // post /{api_version}/interactions
        withRawResponse().create(params, requestOptions).thenApply { it.parse() }

    override fun createStreaming(
        params: InteractionCreateParams,
        requestOptions: RequestOptions,
    ): AsyncStreamResponse<InteractionSseEvent> =
        // post /{api_version}/interactions
        withRawResponse()
            .createStreaming(params, requestOptions)
            .thenApply { it.parse() }
            .toAsync(clientOptions.streamHandlerExecutor)

    override fun delete(
        params: InteractionDeleteParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<InteractionDeleteResponse> =
        // delete /{api_version}/interactions/{id}
        withRawResponse().delete(params, requestOptions).thenApply { it.parse() }

    override fun cancel(
        params: InteractionCancelParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Interaction> =
        // post /{api_version}/interactions/{id}/cancel
        withRawResponse().cancel(params, requestOptions).thenApply { it.parse() }

    override fun get(
        params: InteractionGetParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Interaction> =
        // get /{api_version}/interactions/{id}
        withRawResponse().get(params, requestOptions).thenApply { it.parse() }

    override fun getStreaming(
        params: InteractionGetParams,
        requestOptions: RequestOptions,
    ): AsyncStreamResponse<InteractionSseEvent> =
        // get /{api_version}/interactions/{id}
        withRawResponse()
            .getStreaming(params, requestOptions)
            .thenApply { it.parse() }
            .toAsync(clientOptions.streamHandlerExecutor)

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        InteractionServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): InteractionServiceAsync.WithRawResponse =
            InteractionServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val createHandler: Handler<Interaction> =
            jsonHandler<Interaction>(clientOptions.jsonMapper)

        override fun create(
            params: InteractionCreateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Interaction>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        params._pathParam(0).ifBlank { clientOptions.apiVersion },
                        "interactions",
                    )
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { createHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val createStreamingHandler: Handler<StreamResponse<InteractionSseEvent>> =
            sseHandler(clientOptions.jsonMapper).mapJson<InteractionSseEvent>()

        override fun createStreaming(
            params: InteractionCreateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<StreamResponse<InteractionSseEvent>>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        params._pathParam(0).ifBlank { clientOptions.apiVersion },
                        "interactions",
                    )
                    .putHeader("Accept", "text/event-stream")
                    .body(
                        json(
                            clientOptions.jsonMapper,
                            params
                                ._body()
                                .accept(
                                    object :
                                        InteractionCreateParams.Body.Visitor<
                                            InteractionCreateParams.Body
                                        > {
                                        override fun visitCreateModelInteractionParams(
                                            createModelInteractionParams:
                                                CreateModelInteractionParams
                                        ) =
                                            InteractionCreateParams.Body
                                                .ofCreateModelInteractionParams(
                                                    createModelInteractionParams
                                                        .toBuilder()
                                                        .putAdditionalProperty(
                                                            "stream",
                                                            JsonValue.from(true),
                                                        )
                                                        .build()
                                                )

                                        override fun visitCreateAgentInteractionParams(
                                            createAgentInteractionParams:
                                                CreateAgentInteractionParams
                                        ) =
                                            InteractionCreateParams.Body
                                                .ofCreateAgentInteractionParams(
                                                    createAgentInteractionParams
                                                        .toBuilder()
                                                        .putAdditionalProperty(
                                                            "stream",
                                                            JsonValue.from(true),
                                                        )
                                                        .build()
                                                )
                                    }
                                ),
                        )
                    )
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .let { createStreamingHandler.handle(it) }
                            .let { streamResponse ->
                                if (requestOptions.responseValidation!!) {
                                    streamResponse.map { it.validate() }
                                } else {
                                    streamResponse
                                }
                            }
                    }
                }
        }

        private val deleteHandler: Handler<InteractionDeleteResponse> =
            jsonHandler<InteractionDeleteResponse>(clientOptions.jsonMapper)

        override fun delete(
            params: InteractionDeleteParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<InteractionDeleteResponse>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        params._pathParam(0).ifBlank { clientOptions.apiVersion },
                        "interactions",
                        params._pathParam(1),
                    )
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { deleteHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val cancelHandler: Handler<Interaction> =
            jsonHandler<Interaction>(clientOptions.jsonMapper)

        override fun cancel(
            params: InteractionCancelParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Interaction>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        params._pathParam(0).ifBlank { clientOptions.apiVersion },
                        "interactions",
                        params._pathParam(1),
                        "cancel",
                    )
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { cancelHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val getHandler: Handler<Interaction> =
            jsonHandler<Interaction>(clientOptions.jsonMapper)

        override fun get(
            params: InteractionGetParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Interaction>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        params._pathParam(0).ifBlank { clientOptions.apiVersion },
                        "interactions",
                        params._pathParam(1),
                    )
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { getHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val getStreamingHandler: Handler<StreamResponse<InteractionSseEvent>> =
            sseHandler(clientOptions.jsonMapper).mapJson<InteractionSseEvent>()

        override fun getStreaming(
            params: InteractionGetParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<StreamResponse<InteractionSseEvent>>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("id", params.id().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        params._pathParam(0).ifBlank { clientOptions.apiVersion },
                        "interactions",
                        params._pathParam(1),
                    )
                    .putQueryParam("stream", "true")
                    .putHeader("Accept", "text/event-stream")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .let { getStreamingHandler.handle(it) }
                            .let { streamResponse ->
                                if (requestOptions.responseValidation!!) {
                                    streamResponse.map { it.validate() }
                                } else {
                                    streamResponse
                                }
                            }
                    }
                }
        }
    }
}
