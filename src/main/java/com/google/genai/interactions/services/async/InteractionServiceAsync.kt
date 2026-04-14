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
import com.google.genai.interactions.core.RequestOptions
import com.google.genai.interactions.core.http.AsyncStreamResponse
import com.google.genai.interactions.core.http.HttpResponseFor
import com.google.genai.interactions.core.http.StreamResponse
import com.google.genai.interactions.models.interactions.CreateAgentInteractionParams
import com.google.genai.interactions.models.interactions.CreateModelInteractionParams
import com.google.genai.interactions.models.interactions.Interaction
import com.google.genai.interactions.models.interactions.InteractionCancelParams
import com.google.genai.interactions.models.interactions.InteractionCreateParams
import com.google.genai.interactions.models.interactions.InteractionDeleteParams
import com.google.genai.interactions.models.interactions.InteractionDeleteResponse
import com.google.genai.interactions.models.interactions.InteractionGetParams
import com.google.genai.interactions.models.interactions.InteractionSseEvent
import com.google.errorprone.annotations.MustBeClosed
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

/** <p>The interactions service is experimental. */
interface InteractionServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): InteractionServiceAsync

    /**
     * Creates a new interaction.
     *
     * <p>The interactions service is experimental.
     */
    fun create(params: InteractionCreateParams): CompletableFuture<Interaction> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: InteractionCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<Interaction>

    /** @see create */
    fun create(
        body: InteractionCreateParams.Body,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<Interaction> =
        create(InteractionCreateParams.builder().body(body).build(), requestOptions)

    /** @see create */
    fun create(body: InteractionCreateParams.Body): CompletableFuture<Interaction> =
        create(body, RequestOptions.none())

    /** @see create */
    fun create(
        createModelInteractionParams: CreateModelInteractionParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<Interaction> =
        create(
            InteractionCreateParams.Body.ofCreateModelInteractionParams(
                createModelInteractionParams
            ),
            requestOptions,
        )

    /** @see create */
    fun create(
        createModelInteractionParams: CreateModelInteractionParams
    ): CompletableFuture<Interaction> = create(createModelInteractionParams, RequestOptions.none())

    /** @see create */
    fun create(
        createAgentInteractionParams: CreateAgentInteractionParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<Interaction> =
        create(
            InteractionCreateParams.Body.ofCreateAgentInteractionParams(
                createAgentInteractionParams
            ),
            requestOptions,
        )

    /** @see create */
    fun create(
        createAgentInteractionParams: CreateAgentInteractionParams
    ): CompletableFuture<Interaction> = create(createAgentInteractionParams, RequestOptions.none())

    /** Creates a new interaction. */
    fun createStreaming(params: InteractionCreateParams): AsyncStreamResponse<InteractionSseEvent> =
        createStreaming(params, RequestOptions.none())

    /** @see createStreaming */
    fun createStreaming(
        params: InteractionCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AsyncStreamResponse<InteractionSseEvent>

    /** @see createStreaming */
    fun createStreaming(
        body: InteractionCreateParams.Body,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AsyncStreamResponse<InteractionSseEvent> =
        createStreaming(InteractionCreateParams.builder().body(body).build(), requestOptions)

    /** @see createStreaming */
    fun createStreaming(
        body: InteractionCreateParams.Body
    ): AsyncStreamResponse<InteractionSseEvent> = createStreaming(body, RequestOptions.none())

    /** @see createStreaming */
    fun createStreaming(
        createModelInteractionParams: CreateModelInteractionParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AsyncStreamResponse<InteractionSseEvent> =
        createStreaming(
            InteractionCreateParams.Body.ofCreateModelInteractionParams(
                createModelInteractionParams
            ),
            requestOptions,
        )

    /** @see createStreaming */
    fun createStreaming(
        createModelInteractionParams: CreateModelInteractionParams
    ): AsyncStreamResponse<InteractionSseEvent> =
        createStreaming(createModelInteractionParams, RequestOptions.none())

    /** @see createStreaming */
    fun createStreaming(
        createAgentInteractionParams: CreateAgentInteractionParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AsyncStreamResponse<InteractionSseEvent> =
        createStreaming(
            InteractionCreateParams.Body.ofCreateAgentInteractionParams(
                createAgentInteractionParams
            ),
            requestOptions,
        )

    /** @see createStreaming */
    fun createStreaming(
        createAgentInteractionParams: CreateAgentInteractionParams
    ): AsyncStreamResponse<InteractionSseEvent> =
        createStreaming(createAgentInteractionParams, RequestOptions.none())

    /** Deletes the interaction by id. */
    fun delete(id: String): CompletableFuture<InteractionDeleteResponse> =
        delete(id, InteractionDeleteParams.none())

    /** @see delete */
    fun delete(
        id: String,
        params: InteractionDeleteParams = InteractionDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<InteractionDeleteResponse> =
        delete(params.toBuilder().id(id).build(), requestOptions)

    /** @see delete */
    fun delete(
        id: String,
        params: InteractionDeleteParams = InteractionDeleteParams.none(),
    ): CompletableFuture<InteractionDeleteResponse> = delete(id, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: InteractionDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<InteractionDeleteResponse>

    /** @see delete */
    fun delete(params: InteractionDeleteParams): CompletableFuture<InteractionDeleteResponse> =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        id: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<InteractionDeleteResponse> =
        delete(id, InteractionDeleteParams.none(), requestOptions)

    /**
     * Cancels an interaction by id. This only applies to background interactions that are still
     * running.
     */
    fun cancel(id: String): CompletableFuture<Interaction> =
        cancel(id, InteractionCancelParams.none())

    /** @see cancel */
    fun cancel(
        id: String,
        params: InteractionCancelParams = InteractionCancelParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<Interaction> = cancel(params.toBuilder().id(id).build(), requestOptions)

    /** @see cancel */
    fun cancel(
        id: String,
        params: InteractionCancelParams = InteractionCancelParams.none(),
    ): CompletableFuture<Interaction> = cancel(id, params, RequestOptions.none())

    /** @see cancel */
    fun cancel(
        params: InteractionCancelParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<Interaction>

    /** @see cancel */
    fun cancel(params: InteractionCancelParams): CompletableFuture<Interaction> =
        cancel(params, RequestOptions.none())

    /** @see cancel */
    fun cancel(id: String, requestOptions: RequestOptions): CompletableFuture<Interaction> =
        cancel(id, InteractionCancelParams.none(), requestOptions)

    /** Retrieves the full details of a single interaction based on its `Interaction.id`. */
    fun get(id: String): CompletableFuture<Interaction> = get(id, InteractionGetParams.none())

    /** @see get */
    fun get(
        id: String,
        params: InteractionGetParams = InteractionGetParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<Interaction> = get(params.toBuilder().id(id).build(), requestOptions)

    /** @see get */
    fun get(
        id: String,
        params: InteractionGetParams = InteractionGetParams.none(),
    ): CompletableFuture<Interaction> = get(id, params, RequestOptions.none())

    /** @see get */
    fun get(
        params: InteractionGetParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<Interaction>

    /** @see get */
    fun get(params: InteractionGetParams): CompletableFuture<Interaction> =
        get(params, RequestOptions.none())

    /** @see get */
    fun get(id: String, requestOptions: RequestOptions): CompletableFuture<Interaction> =
        get(id, InteractionGetParams.none(), requestOptions)

    /** Retrieves the full details of a single interaction based on its `Interaction.id`. */
    fun getStreaming(id: String): AsyncStreamResponse<InteractionSseEvent> =
        getStreaming(id, InteractionGetParams.none())

    /** @see getStreaming */
    fun getStreaming(
        id: String,
        params: InteractionGetParams = InteractionGetParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AsyncStreamResponse<InteractionSseEvent> =
        getStreaming(params.toBuilder().id(id).build(), requestOptions)

    /** @see getStreaming */
    fun getStreaming(
        id: String,
        params: InteractionGetParams = InteractionGetParams.none(),
    ): AsyncStreamResponse<InteractionSseEvent> = getStreaming(id, params, RequestOptions.none())

    /** @see getStreaming */
    fun getStreaming(
        params: InteractionGetParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): AsyncStreamResponse<InteractionSseEvent>

    /** @see getStreaming */
    fun getStreaming(params: InteractionGetParams): AsyncStreamResponse<InteractionSseEvent> =
        getStreaming(params, RequestOptions.none())

    /** @see getStreaming */
    fun getStreaming(
        id: String,
        requestOptions: RequestOptions,
    ): AsyncStreamResponse<InteractionSseEvent> =
        getStreaming(id, InteractionGetParams.none(), requestOptions)

    /**
     * A view of [InteractionServiceAsync] that provides access to raw HTTP responses for each
     * method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): InteractionServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /{api_version}/interactions`, but is otherwise the
         * same as [InteractionServiceAsync.create].
         */
        fun create(
            params: InteractionCreateParams
        ): CompletableFuture<HttpResponseFor<Interaction>> = create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: InteractionCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<Interaction>>

        /** @see create */
        fun create(
            body: InteractionCreateParams.Body,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<Interaction>> =
            create(InteractionCreateParams.builder().body(body).build(), requestOptions)

        /** @see create */
        fun create(
            body: InteractionCreateParams.Body
        ): CompletableFuture<HttpResponseFor<Interaction>> = create(body, RequestOptions.none())

        /** @see create */
        fun create(
            createModelInteractionParams: CreateModelInteractionParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<Interaction>> =
            create(
                InteractionCreateParams.Body.ofCreateModelInteractionParams(
                    createModelInteractionParams
                ),
                requestOptions,
            )

        /** @see create */
        fun create(
            createModelInteractionParams: CreateModelInteractionParams
        ): CompletableFuture<HttpResponseFor<Interaction>> =
            create(createModelInteractionParams, RequestOptions.none())

        /** @see create */
        fun create(
            createAgentInteractionParams: CreateAgentInteractionParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<Interaction>> =
            create(
                InteractionCreateParams.Body.ofCreateAgentInteractionParams(
                    createAgentInteractionParams
                ),
                requestOptions,
            )

        /** @see create */
        fun create(
            createAgentInteractionParams: CreateAgentInteractionParams
        ): CompletableFuture<HttpResponseFor<Interaction>> =
            create(createAgentInteractionParams, RequestOptions.none())

        /**
         * Returns a raw HTTP response for `post /{api_version}/interactions`, but is otherwise the
         * same as [InteractionServiceAsync.createStreaming].
         */
        @MustBeClosed
        fun createStreaming(
            params: InteractionCreateParams
        ): CompletableFuture<HttpResponseFor<StreamResponse<InteractionSseEvent>>> =
            createStreaming(params, RequestOptions.none())

        /** @see createStreaming */
        @MustBeClosed
        fun createStreaming(
            params: InteractionCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<StreamResponse<InteractionSseEvent>>>

        /** @see createStreaming */
        @MustBeClosed
        fun createStreaming(
            body: InteractionCreateParams.Body,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<StreamResponse<InteractionSseEvent>>> =
            createStreaming(InteractionCreateParams.builder().body(body).build(), requestOptions)

        /** @see createStreaming */
        @MustBeClosed
        fun createStreaming(
            body: InteractionCreateParams.Body
        ): CompletableFuture<HttpResponseFor<StreamResponse<InteractionSseEvent>>> =
            createStreaming(body, RequestOptions.none())

        /** @see createStreaming */
        @MustBeClosed
        fun createStreaming(
            createModelInteractionParams: CreateModelInteractionParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<StreamResponse<InteractionSseEvent>>> =
            createStreaming(
                InteractionCreateParams.Body.ofCreateModelInteractionParams(
                    createModelInteractionParams
                ),
                requestOptions,
            )

        /** @see createStreaming */
        @MustBeClosed
        fun createStreaming(
            createModelInteractionParams: CreateModelInteractionParams
        ): CompletableFuture<HttpResponseFor<StreamResponse<InteractionSseEvent>>> =
            createStreaming(createModelInteractionParams, RequestOptions.none())

        /** @see createStreaming */
        @MustBeClosed
        fun createStreaming(
            createAgentInteractionParams: CreateAgentInteractionParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<StreamResponse<InteractionSseEvent>>> =
            createStreaming(
                InteractionCreateParams.Body.ofCreateAgentInteractionParams(
                    createAgentInteractionParams
                ),
                requestOptions,
            )

        /** @see createStreaming */
        @MustBeClosed
        fun createStreaming(
            createAgentInteractionParams: CreateAgentInteractionParams
        ): CompletableFuture<HttpResponseFor<StreamResponse<InteractionSseEvent>>> =
            createStreaming(createAgentInteractionParams, RequestOptions.none())

        /**
         * Returns a raw HTTP response for `delete /{api_version}/interactions/{id}`, but is
         * otherwise the same as [InteractionServiceAsync.delete].
         */
        fun delete(id: String): CompletableFuture<HttpResponseFor<InteractionDeleteResponse>> =
            delete(id, InteractionDeleteParams.none())

        /** @see delete */
        fun delete(
            id: String,
            params: InteractionDeleteParams = InteractionDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<InteractionDeleteResponse>> =
            delete(params.toBuilder().id(id).build(), requestOptions)

        /** @see delete */
        fun delete(
            id: String,
            params: InteractionDeleteParams = InteractionDeleteParams.none(),
        ): CompletableFuture<HttpResponseFor<InteractionDeleteResponse>> =
            delete(id, params, RequestOptions.none())

        /** @see delete */
        fun delete(
            params: InteractionDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<InteractionDeleteResponse>>

        /** @see delete */
        fun delete(
            params: InteractionDeleteParams
        ): CompletableFuture<HttpResponseFor<InteractionDeleteResponse>> =
            delete(params, RequestOptions.none())

        /** @see delete */
        fun delete(
            id: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<InteractionDeleteResponse>> =
            delete(id, InteractionDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /{api_version}/interactions/{id}/cancel`, but is
         * otherwise the same as [InteractionServiceAsync.cancel].
         */
        fun cancel(id: String): CompletableFuture<HttpResponseFor<Interaction>> =
            cancel(id, InteractionCancelParams.none())

        /** @see cancel */
        fun cancel(
            id: String,
            params: InteractionCancelParams = InteractionCancelParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<Interaction>> =
            cancel(params.toBuilder().id(id).build(), requestOptions)

        /** @see cancel */
        fun cancel(
            id: String,
            params: InteractionCancelParams = InteractionCancelParams.none(),
        ): CompletableFuture<HttpResponseFor<Interaction>> =
            cancel(id, params, RequestOptions.none())

        /** @see cancel */
        fun cancel(
            params: InteractionCancelParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<Interaction>>

        /** @see cancel */
        fun cancel(
            params: InteractionCancelParams
        ): CompletableFuture<HttpResponseFor<Interaction>> = cancel(params, RequestOptions.none())

        /** @see cancel */
        fun cancel(
            id: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Interaction>> =
            cancel(id, InteractionCancelParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /{api_version}/interactions/{id}`, but is otherwise
         * the same as [InteractionServiceAsync.get].
         */
        fun get(id: String): CompletableFuture<HttpResponseFor<Interaction>> =
            get(id, InteractionGetParams.none())

        /** @see get */
        fun get(
            id: String,
            params: InteractionGetParams = InteractionGetParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<Interaction>> =
            get(params.toBuilder().id(id).build(), requestOptions)

        /** @see get */
        fun get(
            id: String,
            params: InteractionGetParams = InteractionGetParams.none(),
        ): CompletableFuture<HttpResponseFor<Interaction>> = get(id, params, RequestOptions.none())

        /** @see get */
        fun get(
            params: InteractionGetParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<Interaction>>

        /** @see get */
        fun get(params: InteractionGetParams): CompletableFuture<HttpResponseFor<Interaction>> =
            get(params, RequestOptions.none())

        /** @see get */
        fun get(
            id: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Interaction>> =
            get(id, InteractionGetParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /{api_version}/interactions/{id}`, but is otherwise
         * the same as [InteractionServiceAsync.getStreaming].
         */
        @MustBeClosed
        fun getStreaming(
            id: String
        ): CompletableFuture<HttpResponseFor<StreamResponse<InteractionSseEvent>>> =
            getStreaming(id, InteractionGetParams.none())

        /** @see getStreaming */
        @MustBeClosed
        fun getStreaming(
            id: String,
            params: InteractionGetParams = InteractionGetParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<StreamResponse<InteractionSseEvent>>> =
            getStreaming(params.toBuilder().id(id).build(), requestOptions)

        /** @see getStreaming */
        @MustBeClosed
        fun getStreaming(
            id: String,
            params: InteractionGetParams = InteractionGetParams.none(),
        ): CompletableFuture<HttpResponseFor<StreamResponse<InteractionSseEvent>>> =
            getStreaming(id, params, RequestOptions.none())

        /** @see getStreaming */
        @MustBeClosed
        fun getStreaming(
            params: InteractionGetParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<StreamResponse<InteractionSseEvent>>>

        /** @see getStreaming */
        @MustBeClosed
        fun getStreaming(
            params: InteractionGetParams
        ): CompletableFuture<HttpResponseFor<StreamResponse<InteractionSseEvent>>> =
            getStreaming(params, RequestOptions.none())

        /** @see getStreaming */
        @MustBeClosed
        fun getStreaming(
            id: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<StreamResponse<InteractionSseEvent>>> =
            getStreaming(id, InteractionGetParams.none(), requestOptions)
    }
}
