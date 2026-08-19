/*
 * Copyright 2026 Google LLC
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

/*
 * Injected by scripts/sync_speakeasy_outputs.py DO NOT EDIT.
 */
package com.google.genai.gaos.models.errors;

import com.google.genai.gaos.utils.Headers;
import com.google.genai.gaos.utils.transport.HttpResponse;
import jakarta.annotation.Nullable;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * Carrier base bridging gaos 5xx errors onto the native ServerException hierarchy while
 * preserving the gaos body/rawResponse/headers surface.
 */
@SuppressWarnings("serial")
public abstract class GaosServerException extends com.google.genai.errors.ServerException {

    private byte[] body;
    private HttpResponse<?> rawResponse;

    public GaosServerException(String message, int code, @Nullable byte[] body, HttpResponse<?> rawResponse, @Nullable Throwable cause) {
        super(code, "", message, cause);
        this.body = body;
        this.rawResponse = rawResponse;
    }

    public Optional<byte[]> body() {
        return Optional.ofNullable(body);
    }

    public Optional<String> bodyAsString() {
        return body().map(x -> new String(x, StandardCharsets.UTF_8));
    }

    public HttpResponse<?> rawResponse() {
        return rawResponse;
    }

    public Headers headers() {
        return new Headers(rawResponse.headers().map());
    }
}
