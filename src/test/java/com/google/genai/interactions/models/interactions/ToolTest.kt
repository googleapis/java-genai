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
import com.google.genai.interactions.errors.GeminiNextGenApiInvalidDataException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class ToolTest {

    @Test
    fun ofFunction() {
        val function =
            Function.builder()
                .description("description")
                .name("name")
                .parameters(JsonValue.from(mapOf<String, Any>()))
                .build()

        val tool = Tool.ofFunction(function)

        assertThat(tool.function()).contains(function)
        assertThat(tool.codeExecution()).isEmpty
        assertThat(tool.urlContext()).isEmpty
        assertThat(tool.computerUse()).isEmpty
        assertThat(tool.mcpServer()).isEmpty
        assertThat(tool.googleSearch()).isEmpty
        assertThat(tool.fileSearch()).isEmpty
        assertThat(tool.googleMaps()).isEmpty
        assertThat(tool.retrieval()).isEmpty
    }

    @Test
    fun ofFunctionRoundtrip() {
        val jsonMapper = jsonMapper()
        val tool =
            Tool.ofFunction(
                Function.builder()
                    .description("description")
                    .name("name")
                    .parameters(JsonValue.from(mapOf<String, Any>()))
                    .build()
            )

        val roundtrippedTool =
            jsonMapper.readValue(jsonMapper.writeValueAsString(tool), jacksonTypeRef<Tool>())

        assertThat(roundtrippedTool).isEqualTo(tool)
    }

    @Test
    fun ofCodeExecution() {
        val tool = Tool.ofCodeExecution()

        assertThat(tool.function()).isEmpty
        assertThat(tool.codeExecution()).contains(JsonValue.from(mapOf("type" to "code_execution")))
        assertThat(tool.urlContext()).isEmpty
        assertThat(tool.computerUse()).isEmpty
        assertThat(tool.mcpServer()).isEmpty
        assertThat(tool.googleSearch()).isEmpty
        assertThat(tool.fileSearch()).isEmpty
        assertThat(tool.googleMaps()).isEmpty
        assertThat(tool.retrieval()).isEmpty
    }

    @Test
    fun ofCodeExecutionRoundtrip() {
        val jsonMapper = jsonMapper()
        val tool = Tool.ofCodeExecution()

        val roundtrippedTool =
            jsonMapper.readValue(jsonMapper.writeValueAsString(tool), jacksonTypeRef<Tool>())

        assertThat(roundtrippedTool).isEqualTo(tool)
    }

    @Test
    fun ofUrlContext() {
        val tool = Tool.ofUrlContext()

        assertThat(tool.function()).isEmpty
        assertThat(tool.codeExecution()).isEmpty
        assertThat(tool.urlContext()).contains(JsonValue.from(mapOf("type" to "url_context")))
        assertThat(tool.computerUse()).isEmpty
        assertThat(tool.mcpServer()).isEmpty
        assertThat(tool.googleSearch()).isEmpty
        assertThat(tool.fileSearch()).isEmpty
        assertThat(tool.googleMaps()).isEmpty
        assertThat(tool.retrieval()).isEmpty
    }

    @Test
    fun ofUrlContextRoundtrip() {
        val jsonMapper = jsonMapper()
        val tool = Tool.ofUrlContext()

        val roundtrippedTool =
            jsonMapper.readValue(jsonMapper.writeValueAsString(tool), jacksonTypeRef<Tool>())

        assertThat(roundtrippedTool).isEqualTo(tool)
    }

    @Test
    fun ofComputerUse() {
        val computerUse =
            Tool.ComputerUse.builder()
                .environment(Tool.ComputerUse.Environment.BROWSER)
                .addExcludedPredefinedFunction("string")
                .build()

        val tool = Tool.ofComputerUse(computerUse)

        assertThat(tool.function()).isEmpty
        assertThat(tool.codeExecution()).isEmpty
        assertThat(tool.urlContext()).isEmpty
        assertThat(tool.computerUse()).contains(computerUse)
        assertThat(tool.mcpServer()).isEmpty
        assertThat(tool.googleSearch()).isEmpty
        assertThat(tool.fileSearch()).isEmpty
        assertThat(tool.googleMaps()).isEmpty
        assertThat(tool.retrieval()).isEmpty
    }

    @Test
    fun ofComputerUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val tool =
            Tool.ofComputerUse(
                Tool.ComputerUse.builder()
                    .environment(Tool.ComputerUse.Environment.BROWSER)
                    .addExcludedPredefinedFunction("string")
                    .build()
            )

        val roundtrippedTool =
            jsonMapper.readValue(jsonMapper.writeValueAsString(tool), jacksonTypeRef<Tool>())

        assertThat(roundtrippedTool).isEqualTo(tool)
    }

    @Test
    fun ofMcpServer() {
        val mcpServer =
            Tool.McpServer.builder()
                .addAllowedTool(
                    AllowedTools.builder().mode(ToolChoiceType.AUTO).addTool("string").build()
                )
                .headers(
                    Tool.McpServer.Headers.builder()
                        .putAdditionalProperty("foo", JsonValue.from("string"))
                        .build()
                )
                .name("name")
                .url("url")
                .build()

        val tool = Tool.ofMcpServer(mcpServer)

        assertThat(tool.function()).isEmpty
        assertThat(tool.codeExecution()).isEmpty
        assertThat(tool.urlContext()).isEmpty
        assertThat(tool.computerUse()).isEmpty
        assertThat(tool.mcpServer()).contains(mcpServer)
        assertThat(tool.googleSearch()).isEmpty
        assertThat(tool.fileSearch()).isEmpty
        assertThat(tool.googleMaps()).isEmpty
        assertThat(tool.retrieval()).isEmpty
    }

    @Test
    fun ofMcpServerRoundtrip() {
        val jsonMapper = jsonMapper()
        val tool =
            Tool.ofMcpServer(
                Tool.McpServer.builder()
                    .addAllowedTool(
                        AllowedTools.builder().mode(ToolChoiceType.AUTO).addTool("string").build()
                    )
                    .headers(
                        Tool.McpServer.Headers.builder()
                            .putAdditionalProperty("foo", JsonValue.from("string"))
                            .build()
                    )
                    .name("name")
                    .url("url")
                    .build()
            )

        val roundtrippedTool =
            jsonMapper.readValue(jsonMapper.writeValueAsString(tool), jacksonTypeRef<Tool>())

        assertThat(roundtrippedTool).isEqualTo(tool)
    }

    @Test
    fun ofGoogleSearch() {
        val googleSearch =
            Tool.GoogleSearch.builder()
                .addSearchType(Tool.GoogleSearch.SearchType.WEB_SEARCH)
                .build()

        val tool = Tool.ofGoogleSearch(googleSearch)

        assertThat(tool.function()).isEmpty
        assertThat(tool.codeExecution()).isEmpty
        assertThat(tool.urlContext()).isEmpty
        assertThat(tool.computerUse()).isEmpty
        assertThat(tool.mcpServer()).isEmpty
        assertThat(tool.googleSearch()).contains(googleSearch)
        assertThat(tool.fileSearch()).isEmpty
        assertThat(tool.googleMaps()).isEmpty
        assertThat(tool.retrieval()).isEmpty
    }

    @Test
    fun ofGoogleSearchRoundtrip() {
        val jsonMapper = jsonMapper()
        val tool =
            Tool.ofGoogleSearch(
                Tool.GoogleSearch.builder()
                    .addSearchType(Tool.GoogleSearch.SearchType.WEB_SEARCH)
                    .build()
            )

        val roundtrippedTool =
            jsonMapper.readValue(jsonMapper.writeValueAsString(tool), jacksonTypeRef<Tool>())

        assertThat(roundtrippedTool).isEqualTo(tool)
    }

    @Test
    fun ofFileSearch() {
        val fileSearch =
            Tool.FileSearch.builder()
                .addFileSearchStoreName("string")
                .metadataFilter("metadata_filter")
                .topK(0)
                .build()

        val tool = Tool.ofFileSearch(fileSearch)

        assertThat(tool.function()).isEmpty
        assertThat(tool.codeExecution()).isEmpty
        assertThat(tool.urlContext()).isEmpty
        assertThat(tool.computerUse()).isEmpty
        assertThat(tool.mcpServer()).isEmpty
        assertThat(tool.googleSearch()).isEmpty
        assertThat(tool.fileSearch()).contains(fileSearch)
        assertThat(tool.googleMaps()).isEmpty
        assertThat(tool.retrieval()).isEmpty
    }

    @Test
    fun ofFileSearchRoundtrip() {
        val jsonMapper = jsonMapper()
        val tool =
            Tool.ofFileSearch(
                Tool.FileSearch.builder()
                    .addFileSearchStoreName("string")
                    .metadataFilter("metadata_filter")
                    .topK(0)
                    .build()
            )

        val roundtrippedTool =
            jsonMapper.readValue(jsonMapper.writeValueAsString(tool), jacksonTypeRef<Tool>())

        assertThat(roundtrippedTool).isEqualTo(tool)
    }

    @Test
    fun ofGoogleMaps() {
        val googleMaps =
            Tool.GoogleMaps.builder().enableWidget(true).latitude(0.0).longitude(0.0).build()

        val tool = Tool.ofGoogleMaps(googleMaps)

        assertThat(tool.function()).isEmpty
        assertThat(tool.codeExecution()).isEmpty
        assertThat(tool.urlContext()).isEmpty
        assertThat(tool.computerUse()).isEmpty
        assertThat(tool.mcpServer()).isEmpty
        assertThat(tool.googleSearch()).isEmpty
        assertThat(tool.fileSearch()).isEmpty
        assertThat(tool.googleMaps()).contains(googleMaps)
        assertThat(tool.retrieval()).isEmpty
    }

    @Test
    fun ofGoogleMapsRoundtrip() {
        val jsonMapper = jsonMapper()
        val tool =
            Tool.ofGoogleMaps(
                Tool.GoogleMaps.builder().enableWidget(true).latitude(0.0).longitude(0.0).build()
            )

        val roundtrippedTool =
            jsonMapper.readValue(jsonMapper.writeValueAsString(tool), jacksonTypeRef<Tool>())

        assertThat(roundtrippedTool).isEqualTo(tool)
    }

    @Test
    fun ofRetrieval() {
        val retrieval =
            Tool.Retrieval.builder()
                .addRetrievalType(Tool.Retrieval.RetrievalType.VERTEX_AI_SEARCH)
                .vertexAiSearchConfig(
                    Tool.Retrieval.VertexAiSearchConfig.builder()
                        .addDatastore("string")
                        .engine("engine")
                        .build()
                )
                .build()

        val tool = Tool.ofRetrieval(retrieval)

        assertThat(tool.function()).isEmpty
        assertThat(tool.codeExecution()).isEmpty
        assertThat(tool.urlContext()).isEmpty
        assertThat(tool.computerUse()).isEmpty
        assertThat(tool.mcpServer()).isEmpty
        assertThat(tool.googleSearch()).isEmpty
        assertThat(tool.fileSearch()).isEmpty
        assertThat(tool.googleMaps()).isEmpty
        assertThat(tool.retrieval()).contains(retrieval)
    }

    @Test
    fun ofRetrievalRoundtrip() {
        val jsonMapper = jsonMapper()
        val tool =
            Tool.ofRetrieval(
                Tool.Retrieval.builder()
                    .addRetrievalType(Tool.Retrieval.RetrievalType.VERTEX_AI_SEARCH)
                    .vertexAiSearchConfig(
                        Tool.Retrieval.VertexAiSearchConfig.builder()
                            .addDatastore("string")
                            .engine("engine")
                            .build()
                    )
                    .build()
            )

        val roundtrippedTool =
            jsonMapper.readValue(jsonMapper.writeValueAsString(tool), jacksonTypeRef<Tool>())

        assertThat(roundtrippedTool).isEqualTo(tool)
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        STRING(JsonValue.from("invalid")),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val tool = jsonMapper().convertValue(testCase.value, jacksonTypeRef<Tool>())

        val e = assertThrows<GeminiNextGenApiInvalidDataException> { tool.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
