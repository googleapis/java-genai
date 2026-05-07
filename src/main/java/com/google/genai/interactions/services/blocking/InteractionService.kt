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

package com.google.genai.interactions.services.blocking

import com.google.genai.interactions.core.ClientOptions
import com.google.genai.interactions.core.RequestOptions
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
import java.util.function.Consumer

interface InteractionService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): InteractionService

    /** Creates a new interaction. */
    fun create(params: InteractionCreateParams): Interaction = create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: InteractionCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Interaction

    /** @see create */
    fun create(
        body: InteractionCreateParams.Body,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Interaction = create(InteractionCreateParams.builder().body(body).build(), requestOptions)

    /** @see create */
    fun create(body: InteractionCreateParams.Body): Interaction =
        create(body, RequestOptions.none())

    /** @see create */
    fun create(
        createModelInteractionParams: CreateModelInteractionParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Interaction =
        create(
            InteractionCreateParams.Body.ofCreateModelInteractionParams(
                createModelInteractionParams
            ),
            requestOptions,
        )

    /** @see create */
    fun create(createModelInteractionParams: CreateModelInteractionParams): Interaction =
        create(createModelInteractionParams, RequestOptions.none())

    /** @see create */
    fun create(
        createAgentInteractionParams: CreateAgentInteractionParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Interaction =
        create(
            InteractionCreateParams.Body.ofCreateAgentInteractionParams(
                createAgentInteractionParams
            ),
            requestOptions,
        )

    /** @see create */
    fun create(createAgentInteractionParams: CreateAgentInteractionParams): Interaction =
        create(createAgentInteractionParams, RequestOptions.none())

    /** Creates a new interaction. */
    @MustBeClosed
    fun createStreaming(params: InteractionCreateParams): StreamResponse<InteractionSseEvent> =
        createStreaming(params, RequestOptions.none())

    /** @see createStreaming */
    @MustBeClosed
    fun createStreaming(
        params: InteractionCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StreamResponse<InteractionSseEvent>

    /** @see createStreaming */
    @MustBeClosed
    fun createStreaming(
        body: InteractionCreateParams.Body,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StreamResponse<InteractionSseEvent> =
        createStreaming(InteractionCreateParams.builder().body(body).build(), requestOptions)

    /** @see createStreaming */
    @MustBeClosed
    fun createStreaming(body: InteractionCreateParams.Body): StreamResponse<InteractionSseEvent> =
        createStreaming(body, RequestOptions.none())

    /** @see createStreaming */
    @MustBeClosed
    fun createStreaming(
        createModelInteractionParams: CreateModelInteractionParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StreamResponse<InteractionSseEvent> =
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
    ): StreamResponse<InteractionSseEvent> =
        createStreaming(createModelInteractionParams, RequestOptions.none())

    /** @see createStreaming */
    @MustBeClosed
    fun createStreaming(
        createAgentInteractionParams: CreateAgentInteractionParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StreamResponse<InteractionSseEvent> =
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
    ): StreamResponse<InteractionSseEvent> =
        createStreaming(createAgentInteractionParams, RequestOptions.none())

    /** Deletes the interaction by id. */
    fun delete(id: String): InteractionDeleteResponse = delete(id, InteractionDeleteParams.none())

    /** @see delete */
    fun delete(
        id: String,
        params: InteractionDeleteParams = InteractionDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): InteractionDeleteResponse = delete(params.toBuilder().id(id).build(), requestOptions)

    /** @see delete */
    fun delete(
        id: String,
        params: InteractionDeleteParams = InteractionDeleteParams.none(),
    ): InteractionDeleteResponse = delete(id, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: InteractionDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): InteractionDeleteResponse

    /** @see delete */
    fun delete(params: InteractionDeleteParams): InteractionDeleteResponse =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(id: String, requestOptions: RequestOptions): InteractionDeleteResponse =
        delete(id, InteractionDeleteParams.none(), requestOptions)

    /**
     * Cancels an interaction by id. This only applies to background interactions that are still
     * running.
     */
    fun cancel(id: String): Interaction = cancel(id, InteractionCancelParams.none())

    /** @see cancel */
    fun cancel(
        id: String,
        params: InteractionCancelParams = InteractionCancelParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Interaction = cancel(params.toBuilder().id(id).build(), requestOptions)

    /** @see cancel */
    fun cancel(
        id: String,
        params: InteractionCancelParams = InteractionCancelParams.none(),
    ): Interaction = cancel(id, params, RequestOptions.none())

    /** @see cancel */
    fun cancel(
        params: InteractionCancelParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Interaction

    /** @see cancel */
    fun cancel(params: InteractionCancelParams): Interaction = cancel(params, RequestOptions.none())

    /** @see cancel */
    fun cancel(id: String, requestOptions: RequestOptions): Interaction =
        cancel(id, InteractionCancelParams.none(), requestOptions)

    /** Retrieves the full details of a single interaction based on its `Interaction.id`. */
    fun get(id: String): Interaction = get(id, InteractionGetParams.none())

    /** @see get */
    fun get(
        id: String,
        params: InteractionGetParams = InteractionGetParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Interaction = get(params.toBuilder().id(id).build(), requestOptions)

    /** @see get */
    fun get(id: String, params: InteractionGetParams = InteractionGetParams.none()): Interaction =
        get(id, params, RequestOptions.none())

    /** @see get */
    fun get(
        params: InteractionGetParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): Interaction

    /** @see get */
    fun get(params: InteractionGetParams): Interaction = get(params, RequestOptions.none())

    /** @see get */
    fun get(id: String, requestOptions: RequestOptions): Interaction =
        get(id, InteractionGetParams.none(), requestOptions)

    /** Retrieves the full details of a single interaction based on its `Interaction.id`. */
    @MustBeClosed
    fun getStreaming(id: String): StreamResponse<InteractionSseEvent> =
        getStreaming(id, InteractionGetParams.none())

    /** @see getStreaming */
    @MustBeClosed
    fun getStreaming(
        id: String,
        params: InteractionGetParams = InteractionGetParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StreamResponse<InteractionSseEvent> =
        getStreaming(params.toBuilder().id(id).build(), requestOptions)

    /** @see getStreaming */
    @MustBeClosed
    fun getStreaming(
        id: String,
        params: InteractionGetParams = InteractionGetParams.none(),
    ): StreamResponse<InteractionSseEvent> = getStreaming(id, params, RequestOptions.none())

    /** @see getStreaming */
    @MustBeClosed
    fun getStreaming(
        params: InteractionGetParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): StreamResponse<InteractionSseEvent>

    /** @see getStreaming */
    @MustBeClosed
    fun getStreaming(params: InteractionGetParams): StreamResponse<InteractionSseEvent> =
        getStreaming(params, RequestOptions.none())

    /** @see getStreaming */
    @MustBeClosed
    fun getStreaming(
        id: String,
        requestOptions: RequestOptions,
    ): StreamResponse<InteractionSseEvent> =
        getStreaming(id, InteractionGetParams.none(), requestOptions)

    /**
     * A view of [InteractionService] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): InteractionService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /{api_version}/interactions`, but is otherwise the
         * same as [InteractionService.create].
         */
        @MustBeClosed
        fun create(params: InteractionCreateParams): HttpResponseFor<Interaction> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: InteractionCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Interaction>

        /** @see create */
        @MustBeClosed
        fun create(
            body: InteractionCreateParams.Body,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Interaction> =
            create(InteractionCreateParams.builder().body(body).build(), requestOptions)

        /** @see create */
        @MustBeClosed
        fun create(body: InteractionCreateParams.Body): HttpResponseFor<Interaction> =
            create(body, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            createModelInteractionParams: CreateModelInteractionParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Interaction> =
            create(
                InteractionCreateParams.Body.ofCreateModelInteractionParams(
                    createModelInteractionParams
                ),
                requestOptions,
            )

        /** @see create */
        @MustBeClosed
        fun create(
            createModelInteractionParams: CreateModelInteractionParams
        ): HttpResponseFor<Interaction> =
            create(createModelInteractionParams, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            createAgentInteractionParams: CreateAgentInteractionParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Interaction> =
            create(
                InteractionCreateParams.Body.ofCreateAgentInteractionParams(
                    createAgentInteractionParams
                ),
                requestOptions,
            )

        /** @see create */
        @MustBeClosed
        fun create(
            createAgentInteractionParams: CreateAgentInteractionParams
        ): HttpResponseFor<Interaction> =
            create(createAgentInteractionParams, RequestOptions.none())

        /**
         * Returns a raw HTTP response for `post /{api_version}/interactions`, but is otherwise the
         * same as [InteractionService.createStreaming].
         */
        @MustBeClosed
        fun createStreaming(
            params: InteractionCreateParams
        ): HttpResponseFor<StreamResponse<InteractionSseEvent>> =
            createStreaming(params, RequestOptions.none())

        /** @see createStreaming */
        @MustBeClosed
        fun createStreaming(
            params: InteractionCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StreamResponse<InteractionSseEvent>>

        /** @see createStreaming */
        @MustBeClosed
        fun createStreaming(
            body: InteractionCreateParams.Body,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StreamResponse<InteractionSseEvent>> =
            createStreaming(InteractionCreateParams.builder().body(body).build(), requestOptions)

        /** @see createStreaming */
        @MustBeClosed
        fun createStreaming(
            body: InteractionCreateParams.Body
        ): HttpResponseFor<StreamResponse<InteractionSseEvent>> =
            createStreaming(body, RequestOptions.none())

        /** @see createStreaming */
        @MustBeClosed
        fun createStreaming(
            createModelInteractionParams: CreateModelInteractionParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StreamResponse<InteractionSseEvent>> =
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
        ): HttpResponseFor<StreamResponse<InteractionSseEvent>> =
            createStreaming(createModelInteractionParams, RequestOptions.none())

        /** @see createStreaming */
        @MustBeClosed
        fun createStreaming(
            createAgentInteractionParams: CreateAgentInteractionParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StreamResponse<InteractionSseEvent>> =
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
        ): HttpResponseFor<StreamResponse<InteractionSseEvent>> =
            createStreaming(createAgentInteractionParams, RequestOptions.none())

        /**
         * Returns a raw HTTP response for `delete /{api_version}/interactions/{id}`, but is
         * otherwise the same as [InteractionService.delete].
         */
        @MustBeClosed
        fun delete(id: String): HttpResponseFor<InteractionDeleteResponse> =
            delete(id, InteractionDeleteParams.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            id: String,
            params: InteractionDeleteParams = InteractionDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<InteractionDeleteResponse> =
            delete(params.toBuilder().id(id).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(
            id: String,
            params: InteractionDeleteParams = InteractionDeleteParams.none(),
        ): HttpResponseFor<InteractionDeleteResponse> = delete(id, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: InteractionDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<InteractionDeleteResponse>

        /** @see delete */
        @MustBeClosed
        fun delete(params: InteractionDeleteParams): HttpResponseFor<InteractionDeleteResponse> =
            delete(params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<InteractionDeleteResponse> =
            delete(id, InteractionDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /{api_version}/interactions/{id}/cancel`, but is
         * otherwise the same as [InteractionService.cancel].
         */
        @MustBeClosed
        fun cancel(id: String): HttpResponseFor<Interaction> =
            cancel(id, InteractionCancelParams.none())

        /** @see cancel */
        @MustBeClosed
        fun cancel(
            id: String,
            params: InteractionCancelParams = InteractionCancelParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Interaction> = cancel(params.toBuilder().id(id).build(), requestOptions)

        /** @see cancel */
        @MustBeClosed
        fun cancel(
            id: String,
            params: InteractionCancelParams = InteractionCancelParams.none(),
        ): HttpResponseFor<Interaction> = cancel(id, params, RequestOptions.none())

        /** @see cancel */
        @MustBeClosed
        fun cancel(
            params: InteractionCancelParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Interaction>

        /** @see cancel */
        @MustBeClosed
        fun cancel(params: InteractionCancelParams): HttpResponseFor<Interaction> =
            cancel(params, RequestOptions.none())

        /** @see cancel */
        @MustBeClosed
        fun cancel(id: String, requestOptions: RequestOptions): HttpResponseFor<Interaction> =
            cancel(id, InteractionCancelParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /{api_version}/interactions/{id}`, but is otherwise
         * the same as [InteractionService.get].
         */
        @MustBeClosed
        fun get(id: String): HttpResponseFor<Interaction> = get(id, InteractionGetParams.none())

        /** @see get */
        @MustBeClosed
        fun get(
            id: String,
            params: InteractionGetParams = InteractionGetParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Interaction> = get(params.toBuilder().id(id).build(), requestOptions)

        /** @see get */
        @MustBeClosed
        fun get(
            id: String,
            params: InteractionGetParams = InteractionGetParams.none(),
        ): HttpResponseFor<Interaction> = get(id, params, RequestOptions.none())

        /** @see get */
        @MustBeClosed
        fun get(
            params: InteractionGetParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<Interaction>

        /** @see get */
        @MustBeClosed
        fun get(params: InteractionGetParams): HttpResponseFor<Interaction> =
            get(params, RequestOptions.none())

        /** @see get */
        @MustBeClosed
        fun get(id: String, requestOptions: RequestOptions): HttpResponseFor<Interaction> =
            get(id, InteractionGetParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /{api_version}/interactions/{id}`, but is otherwise
         * the same as [InteractionService.getStreaming].
         */
        @MustBeClosed
        fun getStreaming(id: String): HttpResponseFor<StreamResponse<InteractionSseEvent>> =
            getStreaming(id, InteractionGetParams.none())

        /** @see getStreaming */
        @MustBeClosed
        fun getStreaming(
            id: String,
            params: InteractionGetParams = InteractionGetParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StreamResponse<InteractionSseEvent>> =
            getStreaming(params.toBuilder().id(id).build(), requestOptions)

        /** @see getStreaming */
        @MustBeClosed
        fun getStreaming(
            id: String,
            params: InteractionGetParams = InteractionGetParams.none(),
        ): HttpResponseFor<StreamResponse<InteractionSseEvent>> =
            getStreaming(id, params, RequestOptions.none())

        /** @see getStreaming */
        @MustBeClosed
        fun getStreaming(
            params: InteractionGetParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<StreamResponse<InteractionSseEvent>>

        /** @see getStreaming */
        @MustBeClosed
        fun getStreaming(
            params: InteractionGetParams
        ): HttpResponseFor<StreamResponse<InteractionSseEvent>> =
            getStreaming(params, RequestOptions.none())

        /** @see getStreaming */
        @MustBeClosed
        fun getStreaming(
            id: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<StreamResponse<InteractionSseEvent>> =
            getStreaming(id, InteractionGetParams.none(), requestOptions)
    }
}
