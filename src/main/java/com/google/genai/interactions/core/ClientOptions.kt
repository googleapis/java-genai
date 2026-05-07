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

package com.google.genai.interactions.core

import com.fasterxml.jackson.databind.json.JsonMapper
import com.google.genai.interactions.core.http.AsyncStreamResponse
import com.google.genai.interactions.core.http.Headers
import com.google.genai.interactions.core.http.HttpClient
import com.google.genai.interactions.core.http.PhantomReachableClosingHttpClient
import com.google.genai.interactions.core.http.QueryParams
import com.google.genai.interactions.core.http.RetryingHttpClient
import java.time.Clock
import java.time.Duration
import java.util.Optional
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ThreadFactory
import java.util.concurrent.atomic.AtomicLong
import kotlin.jvm.optionals.getOrNull

/** A class representing the SDK client configuration. */
class ClientOptions
private constructor(
    private val originalHttpClient: HttpClient,
    /**
     * The HTTP client to use in the SDK.
     *
     * Use the one published in `gemini-next-gen-api-java-client-okhttp` or implement your own.
     *
     * This class takes ownership of the client and closes it when closed.
     */
    @get:JvmName("httpClient") val httpClient: HttpClient,
    /**
     * Whether to throw an exception if any of the Jackson versions detected at runtime are
     * incompatible with the SDK's minimum supported Jackson version (2.13.4).
     *
     * Defaults to true. Use extreme caution when disabling this option. There is no guarantee that
     * the SDK will work correctly when using an incompatible Jackson version.
     */
    @get:JvmName("checkJacksonVersionCompatibility") val checkJacksonVersionCompatibility: Boolean,
    /**
     * The Jackson JSON mapper to use for serializing and deserializing JSON.
     *
     * Defaults to [com.google.genai.interactions.core.jsonMapper]. The default is usually sufficient
     * and rarely needs to be overridden.
     */
    @get:JvmName("jsonMapper") val jsonMapper: JsonMapper,
    /**
     * The executor to use for running [AsyncStreamResponse.Handler] callbacks.
     *
     * Defaults to a dedicated cached thread pool.
     *
     * This class takes ownership of the executor and shuts it down, if possible, when closed.
     */
    @get:JvmName("streamHandlerExecutor") val streamHandlerExecutor: Executor,
    /**
     * The interface to use for delaying execution, like during retries.
     *
     * This is primarily useful for using fake delays in tests.
     *
     * Defaults to real execution delays.
     *
     * This class takes ownership of the sleeper and closes it when closed.
     */
    @get:JvmName("sleeper") val sleeper: Sleeper,
    /**
     * The clock to use for operations that require timing, like retries.
     *
     * This is primarily useful for using a fake clock in tests.
     *
     * Defaults to [Clock.systemUTC].
     */
    @get:JvmName("clock") val clock: Clock,
    private val baseUrl: String?,
    /** Headers to send with the request. */
    @get:JvmName("headers") val headers: Headers,
    /** Query params to send with the request. */
    @get:JvmName("queryParams") val queryParams: QueryParams,
    /**
     * Whether to call `validate` on every response before returning it.
     *
     * Defaults to false, which means the shape of the response will not be validated upfront.
     * Instead, validation will only occur for the parts of the response that are accessed.
     */
    @get:JvmName("responseValidation") val responseValidation: Boolean,
    /**
     * Sets the maximum time allowed for various parts of an HTTP call's lifecycle, excluding
     * retries.
     *
     * Defaults to [Timeout.default].
     */
    @get:JvmName("timeout") val timeout: Timeout,
    /**
     * The maximum number of times to retry failed requests, with a short exponential backoff
     * between requests.
     *
     * Only the following error types are retried:
     * - Connection errors (for example, due to a network connectivity problem)
     * - 408 Request Timeout
     * - 409 Conflict
     * - 429 Rate Limit
     * - 5xx Internal
     *
     * The API may also explicitly instruct the SDK to retry or not retry a request.
     *
     * Defaults to 2.
     */
    @get:JvmName("maxRetries") val maxRetries: Int,
    private val apiKey: String?,
    /** Defaults to `"v1beta"`. */
    @get:JvmName("apiVersion") val apiVersion: String,
    @JvmSynthetic internal val vertexInfo: VertexInfo?,
) {

    init {
        if (checkJacksonVersionCompatibility) {
            checkJacksonVersionCompatibility()
        }
    }

    /**
     * The base URL to use for every request.
     *
     * Defaults to the production environment: `https://generativelanguage.googleapis.com`.
     */
    fun baseUrl(): String = baseUrl ?: PRODUCTION_URL

    fun apiKey(): Optional<String> = Optional.ofNullable(apiKey)

    fun toBuilder() = Builder().from(this)

    companion object {

        const val PRODUCTION_URL = "https://generativelanguage.googleapis.com"

        /**
         * Returns a mutable builder for constructing an instance of [ClientOptions].
         *
         * The following fields are required:
         * ```java
         * .httpClient()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns options configured using system properties and environment variables.
         *
         * @see Builder.fromEnv
         */
        @JvmStatic fun fromEnv(): ClientOptions = builder().fromEnv().build()
    }

    /** A builder for [ClientOptions]. */
    class Builder internal constructor() {

        private var httpClient: HttpClient? = null
        private var checkJacksonVersionCompatibility: Boolean = true
        private var jsonMapper: JsonMapper = jsonMapper()
        private var streamHandlerExecutor: Executor? = null
        private var sleeper: Sleeper? = null
        private var clock: Clock = Clock.systemUTC()
        private var baseUrl: String? = null
        private var headers: Headers.Builder = Headers.builder()
        private var queryParams: QueryParams.Builder = QueryParams.builder()
        private var responseValidation: Boolean = false
        private var timeout: Timeout = Timeout.default()
        private var maxRetries: Int = 2
        private var apiKey: String? = null
        private var apiVersion: String = "v1beta"
        private var vertexInfo: VertexInfo? = null

        @JvmSynthetic
        internal fun from(clientOptions: ClientOptions) = apply {
            httpClient = clientOptions.originalHttpClient
            checkJacksonVersionCompatibility = clientOptions.checkJacksonVersionCompatibility
            jsonMapper = clientOptions.jsonMapper
            streamHandlerExecutor = clientOptions.streamHandlerExecutor
            sleeper = clientOptions.sleeper
            clock = clientOptions.clock
            baseUrl = clientOptions.baseUrl
            headers = clientOptions.headers.toBuilder()
            queryParams = clientOptions.queryParams.toBuilder()
            responseValidation = clientOptions.responseValidation
            timeout = clientOptions.timeout
            maxRetries = clientOptions.maxRetries
            apiKey = clientOptions.apiKey
            apiVersion = clientOptions.apiVersion
            vertexInfo = clientOptions.vertexInfo
        }

        /**
         * The HTTP client to use in the SDK.
         *
         * Use the one published in `gemini-next-gen-api-java-client-okhttp` or implement your own.
         *
         * This class takes ownership of the client and closes it when closed.
         */
        fun httpClient(httpClient: HttpClient) = apply {
            this.httpClient = PhantomReachableClosingHttpClient(httpClient)
        }

        /**
         * Whether to throw an exception if any of the Jackson versions detected at runtime are
         * incompatible with the SDK's minimum supported Jackson version (2.13.4).
         *
         * Defaults to true. Use extreme caution when disabling this option. There is no guarantee
         * that the SDK will work correctly when using an incompatible Jackson version.
         */
        fun checkJacksonVersionCompatibility(checkJacksonVersionCompatibility: Boolean) = apply {
            this.checkJacksonVersionCompatibility = checkJacksonVersionCompatibility
        }

        /**
         * The Jackson JSON mapper to use for serializing and deserializing JSON.
         *
         * Defaults to [com.google.genai.interactions.core.jsonMapper]. The default is usually
         * sufficient and rarely needs to be overridden.
         */
        fun jsonMapper(jsonMapper: JsonMapper) = apply { this.jsonMapper = jsonMapper }

        /**
         * The executor to use for running [AsyncStreamResponse.Handler] callbacks.
         *
         * Defaults to a dedicated cached thread pool.
         *
         * This class takes ownership of the executor and shuts it down, if possible, when closed.
         */
        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor =
                if (streamHandlerExecutor is ExecutorService)
                    PhantomReachableExecutorService(streamHandlerExecutor)
                else streamHandlerExecutor
        }

        /**
         * The interface to use for delaying execution, like during retries.
         *
         * This is primarily useful for using fake delays in tests.
         *
         * Defaults to real execution delays.
         *
         * This class takes ownership of the sleeper and closes it when closed.
         */
        fun sleeper(sleeper: Sleeper) = apply { this.sleeper = PhantomReachableSleeper(sleeper) }

        /**
         * The clock to use for operations that require timing, like retries.
         *
         * This is primarily useful for using a fake clock in tests.
         *
         * Defaults to [Clock.systemUTC].
         */
        fun clock(clock: Clock) = apply { this.clock = clock }

        /**
         * The base URL to use for every request.
         *
         * Defaults to the production environment: `https://generativelanguage.googleapis.com`.
         */
        fun baseUrl(baseUrl: String?) = apply { this.baseUrl = baseUrl }

        /** Alias for calling [Builder.baseUrl] with `baseUrl.orElse(null)`. */
        fun baseUrl(baseUrl: Optional<String>) = baseUrl(baseUrl.getOrNull())

        /**
         * Whether to call `validate` on every response before returning it.
         *
         * Defaults to false, which means the shape of the response will not be validated upfront.
         * Instead, validation will only occur for the parts of the response that are accessed.
         */
        fun responseValidation(responseValidation: Boolean) = apply {
            this.responseValidation = responseValidation
        }

        /**
         * Sets the maximum time allowed for various parts of an HTTP call's lifecycle, excluding
         * retries.
         *
         * Defaults to [Timeout.default].
         */
        fun timeout(timeout: Timeout) = apply { this.timeout = timeout }

        /**
         * Sets the maximum time allowed for a complete HTTP call, not including retries.
         *
         * See [Timeout.request] for more details.
         *
         * For fine-grained control, pass a [Timeout] object.
         */
        fun timeout(timeout: Duration) = timeout(Timeout.builder().request(timeout).build())

        /**
         * The maximum number of times to retry failed requests, with a short exponential backoff
         * between requests.
         *
         * Only the following error types are retried:
         * - Connection errors (for example, due to a network connectivity problem)
         * - 408 Request Timeout
         * - 409 Conflict
         * - 429 Rate Limit
         * - 5xx Internal
         *
         * The API may also explicitly instruct the SDK to retry or not retry a request.
         *
         * Defaults to 2.
         */
        fun maxRetries(maxRetries: Int) = apply { this.maxRetries = maxRetries }

        fun apiKey(apiKey: String?) = apply { this.apiKey = apiKey }

        /** Alias for calling [Builder.apiKey] with `apiKey.orElse(null)`. */
        fun apiKey(apiKey: Optional<String>) = apiKey(apiKey.getOrNull())

        /** Defaults to `"v1beta"`. */
        fun apiVersion(apiVersion: String) = apply { this.apiVersion = apiVersion }

        fun vertexInfo(vertexInfo: VertexInfo?) = apply { this.vertexInfo = vertexInfo }

        fun headers(headers: Headers) = apply {
            this.headers.clear()
            putAllHeaders(headers)
        }

        fun headers(headers: Map<String, Iterable<String>>) = apply {
            this.headers.clear()
            putAllHeaders(headers)
        }

        fun putHeader(name: String, value: String) = apply { headers.put(name, value) }

        fun putHeaders(name: String, values: Iterable<String>) = apply { headers.put(name, values) }

        fun putAllHeaders(headers: Headers) = apply { this.headers.putAll(headers) }

        fun putAllHeaders(headers: Map<String, Iterable<String>>) = apply {
            this.headers.putAll(headers)
        }

        fun replaceHeaders(name: String, value: String) = apply { headers.replace(name, value) }

        fun replaceHeaders(name: String, values: Iterable<String>) = apply {
            headers.replace(name, values)
        }

        fun replaceAllHeaders(headers: Headers) = apply { this.headers.replaceAll(headers) }

        fun replaceAllHeaders(headers: Map<String, Iterable<String>>) = apply {
            this.headers.replaceAll(headers)
        }

        fun removeHeaders(name: String) = apply { headers.remove(name) }

        fun removeAllHeaders(names: Set<String>) = apply { headers.removeAll(names) }

        fun queryParams(queryParams: QueryParams) = apply {
            this.queryParams.clear()
            putAllQueryParams(queryParams)
        }

        fun queryParams(queryParams: Map<String, Iterable<String>>) = apply {
            this.queryParams.clear()
            putAllQueryParams(queryParams)
        }

        fun putQueryParam(key: String, value: String) = apply { queryParams.put(key, value) }

        fun putQueryParams(key: String, values: Iterable<String>) = apply {
            queryParams.put(key, values)
        }

        fun putAllQueryParams(queryParams: QueryParams) = apply {
            this.queryParams.putAll(queryParams)
        }

        fun putAllQueryParams(queryParams: Map<String, Iterable<String>>) = apply {
            this.queryParams.putAll(queryParams)
        }

        fun replaceQueryParams(key: String, value: String) = apply {
            queryParams.replace(key, value)
        }

        fun replaceQueryParams(key: String, values: Iterable<String>) = apply {
            queryParams.replace(key, values)
        }

        fun replaceAllQueryParams(queryParams: QueryParams) = apply {
            this.queryParams.replaceAll(queryParams)
        }

        fun replaceAllQueryParams(queryParams: Map<String, Iterable<String>>) = apply {
            this.queryParams.replaceAll(queryParams)
        }

        fun removeQueryParams(key: String) = apply { queryParams.remove(key) }

        fun removeAllQueryParams(keys: Set<String>) = apply { queryParams.removeAll(keys) }

        fun timeout(): Timeout = timeout

        /**
         * Updates configuration using system properties and environment variables.
         *
         * See this table for the available options:
         *
         * |Setter   |System property                |Environment variable          |Required|Default value                                |
         * |---------|-------------------------------|------------------------------|--------|---------------------------------------------|
         * |`apiKey` |`gemininextgenapi.geminiApiKey`|`GEMINI_API_KEY`              |false   |-                                            |
         * |`baseUrl`|`gemininextgenapi.baseUrl`     |`GEMINI_NEXT_GEN_API_BASE_URL`|true    |`"https://generativelanguage.googleapis.com"`|
         *
         * System properties take precedence over environment variables.
         */
        fun fromEnv() = apply {
            (System.getProperty("gemininextgenapi.baseUrl")
                    ?: System.getenv("GEMINI_NEXT_GEN_API_BASE_URL"))
                ?.let { baseUrl(it) }
            (System.getProperty("gemininextgenapi.geminiApiKey") ?: System.getenv("GEMINI_API_KEY"))
                ?.let { apiKey(it) }
        }

        /**
         * Returns an immutable instance of [ClientOptions].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .httpClient()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): ClientOptions {
            val httpClient = checkRequired("httpClient", httpClient)
            val streamHandlerExecutor =
                streamHandlerExecutor
                    ?: PhantomReachableExecutorService(
                        Executors.newCachedThreadPool(
                            object : ThreadFactory {

                                private val threadFactory: ThreadFactory =
                                    Executors.defaultThreadFactory()
                                private val count = AtomicLong(0)

                                override fun newThread(runnable: Runnable): Thread =
                                    threadFactory.newThread(runnable).also {
                                        it.name =
                                            "gemini-next-gen-api-stream-handler-thread-${count.getAndIncrement()}"
                                    }
                            }
                        )
                    )
            val sleeper = sleeper ?: PhantomReachableSleeper(DefaultSleeper())

            val headers = Headers.builder()
            val queryParams = QueryParams.builder()
            headers.put("Api-Revision", "2026-05-26")
            // We replace after all the default headers to allow end-users to overwrite them.
            headers.replaceAll(this.headers.build())
            queryParams.replaceAll(this.queryParams.build())
            apiKey?.let {
                if (!it.isEmpty()) {
                    headers.replace("x-goog-api-key", it)
                }
            }

            return ClientOptions(
                httpClient,
                RetryingHttpClient.builder()
                    .httpClient(httpClient)
                    .sleeper(sleeper)
                    .clock(clock)
                    .maxRetries(maxRetries)
                    .build(),
                checkJacksonVersionCompatibility,
                jsonMapper,
                streamHandlerExecutor,
                sleeper,
                clock,
                baseUrl,
                headers.build(),
                queryParams.build(),
                responseValidation,
                timeout,
                maxRetries,
                apiKey,
                apiVersion,
                vertexInfo,
            )
        }
    }

    /**
     * Closes these client options, relinquishing any underlying resources.
     *
     * This is purposefully not inherited from [AutoCloseable] because the client options are
     * long-lived and usually should not be synchronously closed via try-with-resources.
     *
     * It's also usually not necessary to call this method at all. the default client automatically
     * releases threads and connections if they remain idle, but if you are writing an application
     * that needs to aggressively release unused resources, then you may call this method.
     */
    fun close() {
        httpClient.close()
        (streamHandlerExecutor as? ExecutorService)?.shutdown()
        sleeper.close()
    }
}
