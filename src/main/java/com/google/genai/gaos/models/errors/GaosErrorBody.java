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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.google.genai.gaos.utils.Utils;
import jakarta.annotation.Nullable;
import java.io.IOException;

/**
 * Reads the status and message of a JSON error body, so gaos errors surface them through the
 * native ApiException. Accepts both the {@code {"error":{...}}} object and the single-element
 * {@code [{"error":{...}}]} array that streaming endpoints return; any other body keeps the
 * defaults.
 */
final class GaosErrorBody {

    /** Message Speakeasy passes when it has nothing more specific to report. */
    static final String DEFAULT_MESSAGE = "API error occurred";

    private GaosErrorBody() {}

    /**
     * Returns the OnePlatform {@code error.status}, else the Interactions API string
     * {@code error.code}, else an empty string.
     *
     * <p>The {@code error.code} fallback is gaos-specific: the native ApiException falls back to
     * the HTTP reason phrase instead, which is empty on HTTP/2. For the Interactions body
     * {@code {"error":{"message":"Model name cannot be empty.","code":"invalid_request"}}} gaos
     * reports {@code 400 invalid_request. Model name cannot be empty.}, while native reports
     * {@code 400 Bad Request. ...} on HTTP/1.1 and {@code 400 . ...} on HTTP/2.
     */
    static String status(@Nullable byte[] body) {
        JsonNode error = errorNode(body);
        String status = text(error.path("status"));
        return status.isEmpty() ? text(error.path("code")) : status;
    }

    /**
     * Replaces {@link #DEFAULT_MESSAGE} with {@code error.message} and {@code error.details} when
     * the body has either. Details are appended one per line after {@code "\nDetails: "}, as an
     * entry's textual {@code detail} field or else its raw JSON, so the message can span several
     * lines.
     *
     * <p>For {@code {"error":{...}}} bodies this matches the native
     * {@code ApiException.getErrorMessageFromBody}. Array bodies differ from the native
     * {@code ApiException.throwFromErrorNode}: a single-element array is read like an object
     * (message plus details, where native drops the details), and a multi-element array keeps
     * {@link #DEFAULT_MESSAGE} (native reads the first element).
     */
    static String message(String message, @Nullable byte[] body) {
        if (!DEFAULT_MESSAGE.equals(message)) {
            return message;
        }
        JsonNode error = errorNode(body);
        String bodyMessage = text(error.path("message"));
        String details = details(error.path("details"));
        if (details.isEmpty()) {
            return bodyMessage.isEmpty() ? message : bodyMessage;
        }
        return bodyMessage.isEmpty() ? "Details: " + details : bodyMessage + "\nDetails: " + details;
    }

    /** Returns the {@code error} object of the body, or a missing node when there is none. */
    private static JsonNode errorNode(@Nullable byte[] body) {
        if (body == null || body.length == 0) {
            return MissingNode.getInstance();
        }
        try {
            JsonNode root = Utils.mapper().readTree(body);
            if (root != null && root.isArray() && root.size() == 1) {
                root = root.get(0);
            }
            JsonNode error = root == null ? null : root.get("error");
            return error != null && error.isObject() ? error : MissingNode.getInstance();
        } catch (IOException e) {
            return MissingNode.getInstance();
        }
    }

    /** Joins {@code error.details} one per line: an entry's {@code detail} text, else its JSON. */
    private static String details(JsonNode details) {
        if (!details.isArray()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (JsonNode item : details) {
            JsonNode detail = item.path("detail");
            if (detail.isTextual()) {
                sb.append(detail.asText()).append('\n');
            } else if (!item.isNull()) {
                sb.append(item.toString()).append('\n');
            }
        }
        return sb.toString().trim();
    }

    private static String text(JsonNode node) {
        return node.isTextual() ? node.asText() : "";
    }
}
