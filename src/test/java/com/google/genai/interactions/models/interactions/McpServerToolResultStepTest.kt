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

package com.google.genai.interactions.models.interactions

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.google.genai.interactions.core.JsonValue
import com.google.genai.interactions.core.jsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class McpServerToolResultStepTest {

    @Test
    fun create() {
        val mcpServerToolResultStep =
            McpServerToolResultStep.builder()
                .callId("call_id")
                .result(JsonValue.from(mapOf<String, Any>()))
                .name("name")
                .serverName("server_name")
                .signature("U3RhaW5sZXNzIHJvY2tz")
                .build()

        assertThat(mcpServerToolResultStep.callId()).isEqualTo("call_id")
        assertThat(mcpServerToolResultStep.result())
            .isEqualTo(
                McpServerToolResultStep.Result.ofJsonValue(JsonValue.from(mapOf<String, Any>()))
            )
        assertThat(mcpServerToolResultStep.name()).contains("name")
        assertThat(mcpServerToolResultStep.serverName()).contains("server_name")
        assertThat(mcpServerToolResultStep.signature()).contains("U3RhaW5sZXNzIHJvY2tz")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val mcpServerToolResultStep =
            McpServerToolResultStep.builder()
                .callId("call_id")
                .result(JsonValue.from(mapOf<String, Any>()))
                .name("name")
                .serverName("server_name")
                .signature("U3RhaW5sZXNzIHJvY2tz")
                .build()

        val roundtrippedMcpServerToolResultStep =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(mcpServerToolResultStep),
                jacksonTypeRef<McpServerToolResultStep>(),
            )

        assertThat(roundtrippedMcpServerToolResultStep).isEqualTo(mcpServerToolResultStep)
    }
}
