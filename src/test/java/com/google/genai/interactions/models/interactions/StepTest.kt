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

internal class StepTest {

    @Test
    fun ofUserInput() {
        val userInput =
            UserInputStep.builder()
                .addContent(
                    TextContent.builder()
                        .text("text")
                        .addAnnotation(
                            UrlCitation.builder()
                                .endIndex(0)
                                .startIndex(0)
                                .title("title")
                                .url("url")
                                .build()
                        )
                        .build()
                )
                .build()

        val step = Step.ofUserInput(userInput)

        assertThat(step.userInput()).contains(userInput)
        assertThat(step.modelOutput()).isEmpty
        assertThat(step.thought()).isEmpty
        assertThat(step.functionCall()).isEmpty
        assertThat(step.codeExecutionCall()).isEmpty
        assertThat(step.urlContextCall()).isEmpty
        assertThat(step.mcpServerToolCall()).isEmpty
        assertThat(step.googleSearchCall()).isEmpty
        assertThat(step.fileSearchCall()).isEmpty
        assertThat(step.googleMapsCall()).isEmpty
        assertThat(step.functionResult()).isEmpty
        assertThat(step.codeExecutionResult()).isEmpty
        assertThat(step.urlContextResult()).isEmpty
        assertThat(step.googleSearchResult()).isEmpty
        assertThat(step.mcpServerToolResult()).isEmpty
        assertThat(step.fileSearchResult()).isEmpty
        assertThat(step.googleMapsResult()).isEmpty
    }

    @Test
    fun ofUserInputRoundtrip() {
        val jsonMapper = jsonMapper()
        val step =
            Step.ofUserInput(
                UserInputStep.builder()
                    .addContent(
                        TextContent.builder()
                            .text("text")
                            .addAnnotation(
                                UrlCitation.builder()
                                    .endIndex(0)
                                    .startIndex(0)
                                    .title("title")
                                    .url("url")
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )

        val roundtrippedStep =
            jsonMapper.readValue(jsonMapper.writeValueAsString(step), jacksonTypeRef<Step>())

        assertThat(roundtrippedStep).isEqualTo(step)
    }

    @Test
    fun ofModelOutput() {
        val modelOutput =
            ModelOutputStep.builder()
                .addContent(
                    TextContent.builder()
                        .text("text")
                        .addAnnotation(
                            UrlCitation.builder()
                                .endIndex(0)
                                .startIndex(0)
                                .title("title")
                                .url("url")
                                .build()
                        )
                        .build()
                )
                .build()

        val step = Step.ofModelOutput(modelOutput)

        assertThat(step.userInput()).isEmpty
        assertThat(step.modelOutput()).contains(modelOutput)
        assertThat(step.thought()).isEmpty
        assertThat(step.functionCall()).isEmpty
        assertThat(step.codeExecutionCall()).isEmpty
        assertThat(step.urlContextCall()).isEmpty
        assertThat(step.mcpServerToolCall()).isEmpty
        assertThat(step.googleSearchCall()).isEmpty
        assertThat(step.fileSearchCall()).isEmpty
        assertThat(step.googleMapsCall()).isEmpty
        assertThat(step.functionResult()).isEmpty
        assertThat(step.codeExecutionResult()).isEmpty
        assertThat(step.urlContextResult()).isEmpty
        assertThat(step.googleSearchResult()).isEmpty
        assertThat(step.mcpServerToolResult()).isEmpty
        assertThat(step.fileSearchResult()).isEmpty
        assertThat(step.googleMapsResult()).isEmpty
    }

    @Test
    fun ofModelOutputRoundtrip() {
        val jsonMapper = jsonMapper()
        val step =
            Step.ofModelOutput(
                ModelOutputStep.builder()
                    .addContent(
                        TextContent.builder()
                            .text("text")
                            .addAnnotation(
                                UrlCitation.builder()
                                    .endIndex(0)
                                    .startIndex(0)
                                    .title("title")
                                    .url("url")
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )

        val roundtrippedStep =
            jsonMapper.readValue(jsonMapper.writeValueAsString(step), jacksonTypeRef<Step>())

        assertThat(roundtrippedStep).isEqualTo(step)
    }

    @Test
    fun ofThought() {
        val thought =
            ThoughtStep.builder()
                .signature("U3RhaW5sZXNzIHJvY2tz")
                .addSummary(
                    TextContent.builder()
                        .text("text")
                        .addAnnotation(
                            UrlCitation.builder()
                                .endIndex(0)
                                .startIndex(0)
                                .title("title")
                                .url("url")
                                .build()
                        )
                        .build()
                )
                .build()

        val step = Step.ofThought(thought)

        assertThat(step.userInput()).isEmpty
        assertThat(step.modelOutput()).isEmpty
        assertThat(step.thought()).contains(thought)
        assertThat(step.functionCall()).isEmpty
        assertThat(step.codeExecutionCall()).isEmpty
        assertThat(step.urlContextCall()).isEmpty
        assertThat(step.mcpServerToolCall()).isEmpty
        assertThat(step.googleSearchCall()).isEmpty
        assertThat(step.fileSearchCall()).isEmpty
        assertThat(step.googleMapsCall()).isEmpty
        assertThat(step.functionResult()).isEmpty
        assertThat(step.codeExecutionResult()).isEmpty
        assertThat(step.urlContextResult()).isEmpty
        assertThat(step.googleSearchResult()).isEmpty
        assertThat(step.mcpServerToolResult()).isEmpty
        assertThat(step.fileSearchResult()).isEmpty
        assertThat(step.googleMapsResult()).isEmpty
    }

    @Test
    fun ofThoughtRoundtrip() {
        val jsonMapper = jsonMapper()
        val step =
            Step.ofThought(
                ThoughtStep.builder()
                    .signature("U3RhaW5sZXNzIHJvY2tz")
                    .addSummary(
                        TextContent.builder()
                            .text("text")
                            .addAnnotation(
                                UrlCitation.builder()
                                    .endIndex(0)
                                    .startIndex(0)
                                    .title("title")
                                    .url("url")
                                    .build()
                            )
                            .build()
                    )
                    .build()
            )

        val roundtrippedStep =
            jsonMapper.readValue(jsonMapper.writeValueAsString(step), jacksonTypeRef<Step>())

        assertThat(roundtrippedStep).isEqualTo(step)
    }

    @Test
    fun ofFunctionCall() {
        val functionCall =
            FunctionCallStep.builder()
                .id("id")
                .arguments(
                    FunctionCallStep.Arguments.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .name("name")
                .signature("U3RhaW5sZXNzIHJvY2tz")
                .build()

        val step = Step.ofFunctionCall(functionCall)

        assertThat(step.userInput()).isEmpty
        assertThat(step.modelOutput()).isEmpty
        assertThat(step.thought()).isEmpty
        assertThat(step.functionCall()).contains(functionCall)
        assertThat(step.codeExecutionCall()).isEmpty
        assertThat(step.urlContextCall()).isEmpty
        assertThat(step.mcpServerToolCall()).isEmpty
        assertThat(step.googleSearchCall()).isEmpty
        assertThat(step.fileSearchCall()).isEmpty
        assertThat(step.googleMapsCall()).isEmpty
        assertThat(step.functionResult()).isEmpty
        assertThat(step.codeExecutionResult()).isEmpty
        assertThat(step.urlContextResult()).isEmpty
        assertThat(step.googleSearchResult()).isEmpty
        assertThat(step.mcpServerToolResult()).isEmpty
        assertThat(step.fileSearchResult()).isEmpty
        assertThat(step.googleMapsResult()).isEmpty
    }

    @Test
    fun ofFunctionCallRoundtrip() {
        val jsonMapper = jsonMapper()
        val step =
            Step.ofFunctionCall(
                FunctionCallStep.builder()
                    .id("id")
                    .arguments(
                        FunctionCallStep.Arguments.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .name("name")
                    .signature("U3RhaW5sZXNzIHJvY2tz")
                    .build()
            )

        val roundtrippedStep =
            jsonMapper.readValue(jsonMapper.writeValueAsString(step), jacksonTypeRef<Step>())

        assertThat(roundtrippedStep).isEqualTo(step)
    }

    @Test
    fun ofCodeExecutionCall() {
        val codeExecutionCall =
            CodeExecutionCallStep.builder()
                .id("id")
                .arguments(
                    CodeExecutionCallStep.Arguments.builder()
                        .code("code")
                        .language(CodeExecutionCallStep.Arguments.Language.PYTHON)
                        .build()
                )
                .signature("U3RhaW5sZXNzIHJvY2tz")
                .build()

        val step = Step.ofCodeExecutionCall(codeExecutionCall)

        assertThat(step.userInput()).isEmpty
        assertThat(step.modelOutput()).isEmpty
        assertThat(step.thought()).isEmpty
        assertThat(step.functionCall()).isEmpty
        assertThat(step.codeExecutionCall()).contains(codeExecutionCall)
        assertThat(step.urlContextCall()).isEmpty
        assertThat(step.mcpServerToolCall()).isEmpty
        assertThat(step.googleSearchCall()).isEmpty
        assertThat(step.fileSearchCall()).isEmpty
        assertThat(step.googleMapsCall()).isEmpty
        assertThat(step.functionResult()).isEmpty
        assertThat(step.codeExecutionResult()).isEmpty
        assertThat(step.urlContextResult()).isEmpty
        assertThat(step.googleSearchResult()).isEmpty
        assertThat(step.mcpServerToolResult()).isEmpty
        assertThat(step.fileSearchResult()).isEmpty
        assertThat(step.googleMapsResult()).isEmpty
    }

    @Test
    fun ofCodeExecutionCallRoundtrip() {
        val jsonMapper = jsonMapper()
        val step =
            Step.ofCodeExecutionCall(
                CodeExecutionCallStep.builder()
                    .id("id")
                    .arguments(
                        CodeExecutionCallStep.Arguments.builder()
                            .code("code")
                            .language(CodeExecutionCallStep.Arguments.Language.PYTHON)
                            .build()
                    )
                    .signature("U3RhaW5sZXNzIHJvY2tz")
                    .build()
            )

        val roundtrippedStep =
            jsonMapper.readValue(jsonMapper.writeValueAsString(step), jacksonTypeRef<Step>())

        assertThat(roundtrippedStep).isEqualTo(step)
    }

    @Test
    fun ofUrlContextCall() {
        val urlContextCall =
            UrlContextCallStep.builder()
                .id("id")
                .arguments(UrlContextCallStep.Arguments.builder().addUrl("string").build())
                .signature("U3RhaW5sZXNzIHJvY2tz")
                .build()

        val step = Step.ofUrlContextCall(urlContextCall)

        assertThat(step.userInput()).isEmpty
        assertThat(step.modelOutput()).isEmpty
        assertThat(step.thought()).isEmpty
        assertThat(step.functionCall()).isEmpty
        assertThat(step.codeExecutionCall()).isEmpty
        assertThat(step.urlContextCall()).contains(urlContextCall)
        assertThat(step.mcpServerToolCall()).isEmpty
        assertThat(step.googleSearchCall()).isEmpty
        assertThat(step.fileSearchCall()).isEmpty
        assertThat(step.googleMapsCall()).isEmpty
        assertThat(step.functionResult()).isEmpty
        assertThat(step.codeExecutionResult()).isEmpty
        assertThat(step.urlContextResult()).isEmpty
        assertThat(step.googleSearchResult()).isEmpty
        assertThat(step.mcpServerToolResult()).isEmpty
        assertThat(step.fileSearchResult()).isEmpty
        assertThat(step.googleMapsResult()).isEmpty
    }

    @Test
    fun ofUrlContextCallRoundtrip() {
        val jsonMapper = jsonMapper()
        val step =
            Step.ofUrlContextCall(
                UrlContextCallStep.builder()
                    .id("id")
                    .arguments(UrlContextCallStep.Arguments.builder().addUrl("string").build())
                    .signature("U3RhaW5sZXNzIHJvY2tz")
                    .build()
            )

        val roundtrippedStep =
            jsonMapper.readValue(jsonMapper.writeValueAsString(step), jacksonTypeRef<Step>())

        assertThat(roundtrippedStep).isEqualTo(step)
    }

    @Test
    fun ofMcpServerToolCall() {
        val mcpServerToolCall =
            McpServerToolCallStep.builder()
                .id("id")
                .arguments(
                    McpServerToolCallStep.Arguments.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .name("name")
                .serverName("server_name")
                .signature("U3RhaW5sZXNzIHJvY2tz")
                .build()

        val step = Step.ofMcpServerToolCall(mcpServerToolCall)

        assertThat(step.userInput()).isEmpty
        assertThat(step.modelOutput()).isEmpty
        assertThat(step.thought()).isEmpty
        assertThat(step.functionCall()).isEmpty
        assertThat(step.codeExecutionCall()).isEmpty
        assertThat(step.urlContextCall()).isEmpty
        assertThat(step.mcpServerToolCall()).contains(mcpServerToolCall)
        assertThat(step.googleSearchCall()).isEmpty
        assertThat(step.fileSearchCall()).isEmpty
        assertThat(step.googleMapsCall()).isEmpty
        assertThat(step.functionResult()).isEmpty
        assertThat(step.codeExecutionResult()).isEmpty
        assertThat(step.urlContextResult()).isEmpty
        assertThat(step.googleSearchResult()).isEmpty
        assertThat(step.mcpServerToolResult()).isEmpty
        assertThat(step.fileSearchResult()).isEmpty
        assertThat(step.googleMapsResult()).isEmpty
    }

    @Test
    fun ofMcpServerToolCallRoundtrip() {
        val jsonMapper = jsonMapper()
        val step =
            Step.ofMcpServerToolCall(
                McpServerToolCallStep.builder()
                    .id("id")
                    .arguments(
                        McpServerToolCallStep.Arguments.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .name("name")
                    .serverName("server_name")
                    .signature("U3RhaW5sZXNzIHJvY2tz")
                    .build()
            )

        val roundtrippedStep =
            jsonMapper.readValue(jsonMapper.writeValueAsString(step), jacksonTypeRef<Step>())

        assertThat(roundtrippedStep).isEqualTo(step)
    }

    @Test
    fun ofGoogleSearchCall() {
        val googleSearchCall =
            GoogleSearchCallStep.builder()
                .id("id")
                .arguments(GoogleSearchCallStep.Arguments.builder().addQuery("string").build())
                .searchType(GoogleSearchCallStep.SearchType.WEB_SEARCH)
                .signature("U3RhaW5sZXNzIHJvY2tz")
                .build()

        val step = Step.ofGoogleSearchCall(googleSearchCall)

        assertThat(step.userInput()).isEmpty
        assertThat(step.modelOutput()).isEmpty
        assertThat(step.thought()).isEmpty
        assertThat(step.functionCall()).isEmpty
        assertThat(step.codeExecutionCall()).isEmpty
        assertThat(step.urlContextCall()).isEmpty
        assertThat(step.mcpServerToolCall()).isEmpty
        assertThat(step.googleSearchCall()).contains(googleSearchCall)
        assertThat(step.fileSearchCall()).isEmpty
        assertThat(step.googleMapsCall()).isEmpty
        assertThat(step.functionResult()).isEmpty
        assertThat(step.codeExecutionResult()).isEmpty
        assertThat(step.urlContextResult()).isEmpty
        assertThat(step.googleSearchResult()).isEmpty
        assertThat(step.mcpServerToolResult()).isEmpty
        assertThat(step.fileSearchResult()).isEmpty
        assertThat(step.googleMapsResult()).isEmpty
    }

    @Test
    fun ofGoogleSearchCallRoundtrip() {
        val jsonMapper = jsonMapper()
        val step =
            Step.ofGoogleSearchCall(
                GoogleSearchCallStep.builder()
                    .id("id")
                    .arguments(GoogleSearchCallStep.Arguments.builder().addQuery("string").build())
                    .searchType(GoogleSearchCallStep.SearchType.WEB_SEARCH)
                    .signature("U3RhaW5sZXNzIHJvY2tz")
                    .build()
            )

        val roundtrippedStep =
            jsonMapper.readValue(jsonMapper.writeValueAsString(step), jacksonTypeRef<Step>())

        assertThat(roundtrippedStep).isEqualTo(step)
    }

    @Test
    fun ofFileSearchCall() {
        val fileSearchCall =
            FileSearchCallStep.builder().id("id").signature("U3RhaW5sZXNzIHJvY2tz").build()

        val step = Step.ofFileSearchCall(fileSearchCall)

        assertThat(step.userInput()).isEmpty
        assertThat(step.modelOutput()).isEmpty
        assertThat(step.thought()).isEmpty
        assertThat(step.functionCall()).isEmpty
        assertThat(step.codeExecutionCall()).isEmpty
        assertThat(step.urlContextCall()).isEmpty
        assertThat(step.mcpServerToolCall()).isEmpty
        assertThat(step.googleSearchCall()).isEmpty
        assertThat(step.fileSearchCall()).contains(fileSearchCall)
        assertThat(step.googleMapsCall()).isEmpty
        assertThat(step.functionResult()).isEmpty
        assertThat(step.codeExecutionResult()).isEmpty
        assertThat(step.urlContextResult()).isEmpty
        assertThat(step.googleSearchResult()).isEmpty
        assertThat(step.mcpServerToolResult()).isEmpty
        assertThat(step.fileSearchResult()).isEmpty
        assertThat(step.googleMapsResult()).isEmpty
    }

    @Test
    fun ofFileSearchCallRoundtrip() {
        val jsonMapper = jsonMapper()
        val step =
            Step.ofFileSearchCall(
                FileSearchCallStep.builder().id("id").signature("U3RhaW5sZXNzIHJvY2tz").build()
            )

        val roundtrippedStep =
            jsonMapper.readValue(jsonMapper.writeValueAsString(step), jacksonTypeRef<Step>())

        assertThat(roundtrippedStep).isEqualTo(step)
    }

    @Test
    fun ofGoogleMapsCall() {
        val googleMapsCall =
            GoogleMapsCallStep.builder()
                .id("id")
                .arguments(GoogleMapsCallStep.Arguments.builder().addQuery("string").build())
                .signature("U3RhaW5sZXNzIHJvY2tz")
                .build()

        val step = Step.ofGoogleMapsCall(googleMapsCall)

        assertThat(step.userInput()).isEmpty
        assertThat(step.modelOutput()).isEmpty
        assertThat(step.thought()).isEmpty
        assertThat(step.functionCall()).isEmpty
        assertThat(step.codeExecutionCall()).isEmpty
        assertThat(step.urlContextCall()).isEmpty
        assertThat(step.mcpServerToolCall()).isEmpty
        assertThat(step.googleSearchCall()).isEmpty
        assertThat(step.fileSearchCall()).isEmpty
        assertThat(step.googleMapsCall()).contains(googleMapsCall)
        assertThat(step.functionResult()).isEmpty
        assertThat(step.codeExecutionResult()).isEmpty
        assertThat(step.urlContextResult()).isEmpty
        assertThat(step.googleSearchResult()).isEmpty
        assertThat(step.mcpServerToolResult()).isEmpty
        assertThat(step.fileSearchResult()).isEmpty
        assertThat(step.googleMapsResult()).isEmpty
    }

    @Test
    fun ofGoogleMapsCallRoundtrip() {
        val jsonMapper = jsonMapper()
        val step =
            Step.ofGoogleMapsCall(
                GoogleMapsCallStep.builder()
                    .id("id")
                    .arguments(GoogleMapsCallStep.Arguments.builder().addQuery("string").build())
                    .signature("U3RhaW5sZXNzIHJvY2tz")
                    .build()
            )

        val roundtrippedStep =
            jsonMapper.readValue(jsonMapper.writeValueAsString(step), jacksonTypeRef<Step>())

        assertThat(roundtrippedStep).isEqualTo(step)
    }

    @Test
    fun ofFunctionResult() {
        val functionResult =
            FunctionResultStep.builder()
                .callId("call_id")
                .result(JsonValue.from(mapOf<String, Any>()))
                .isError(true)
                .name("name")
                .signature("U3RhaW5sZXNzIHJvY2tz")
                .build()

        val step = Step.ofFunctionResult(functionResult)

        assertThat(step.userInput()).isEmpty
        assertThat(step.modelOutput()).isEmpty
        assertThat(step.thought()).isEmpty
        assertThat(step.functionCall()).isEmpty
        assertThat(step.codeExecutionCall()).isEmpty
        assertThat(step.urlContextCall()).isEmpty
        assertThat(step.mcpServerToolCall()).isEmpty
        assertThat(step.googleSearchCall()).isEmpty
        assertThat(step.fileSearchCall()).isEmpty
        assertThat(step.googleMapsCall()).isEmpty
        assertThat(step.functionResult()).contains(functionResult)
        assertThat(step.codeExecutionResult()).isEmpty
        assertThat(step.urlContextResult()).isEmpty
        assertThat(step.googleSearchResult()).isEmpty
        assertThat(step.mcpServerToolResult()).isEmpty
        assertThat(step.fileSearchResult()).isEmpty
        assertThat(step.googleMapsResult()).isEmpty
    }

    @Test
    fun ofFunctionResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val step =
            Step.ofFunctionResult(
                FunctionResultStep.builder()
                    .callId("call_id")
                    .result(JsonValue.from(mapOf<String, Any>()))
                    .isError(true)
                    .name("name")
                    .signature("U3RhaW5sZXNzIHJvY2tz")
                    .build()
            )

        val roundtrippedStep =
            jsonMapper.readValue(jsonMapper.writeValueAsString(step), jacksonTypeRef<Step>())

        assertThat(roundtrippedStep).isEqualTo(step)
    }

    @Test
    fun ofCodeExecutionResult() {
        val codeExecutionResult =
            CodeExecutionResultStep.builder()
                .callId("call_id")
                .result("result")
                .isError(true)
                .signature("U3RhaW5sZXNzIHJvY2tz")
                .build()

        val step = Step.ofCodeExecutionResult(codeExecutionResult)

        assertThat(step.userInput()).isEmpty
        assertThat(step.modelOutput()).isEmpty
        assertThat(step.thought()).isEmpty
        assertThat(step.functionCall()).isEmpty
        assertThat(step.codeExecutionCall()).isEmpty
        assertThat(step.urlContextCall()).isEmpty
        assertThat(step.mcpServerToolCall()).isEmpty
        assertThat(step.googleSearchCall()).isEmpty
        assertThat(step.fileSearchCall()).isEmpty
        assertThat(step.googleMapsCall()).isEmpty
        assertThat(step.functionResult()).isEmpty
        assertThat(step.codeExecutionResult()).contains(codeExecutionResult)
        assertThat(step.urlContextResult()).isEmpty
        assertThat(step.googleSearchResult()).isEmpty
        assertThat(step.mcpServerToolResult()).isEmpty
        assertThat(step.fileSearchResult()).isEmpty
        assertThat(step.googleMapsResult()).isEmpty
    }

    @Test
    fun ofCodeExecutionResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val step =
            Step.ofCodeExecutionResult(
                CodeExecutionResultStep.builder()
                    .callId("call_id")
                    .result("result")
                    .isError(true)
                    .signature("U3RhaW5sZXNzIHJvY2tz")
                    .build()
            )

        val roundtrippedStep =
            jsonMapper.readValue(jsonMapper.writeValueAsString(step), jacksonTypeRef<Step>())

        assertThat(roundtrippedStep).isEqualTo(step)
    }

    @Test
    fun ofUrlContextResult() {
        val urlContextResult =
            UrlContextResultStep.builder()
                .callId("call_id")
                .addResult(
                    UrlContextResultStep.Result.builder()
                        .status(UrlContextResultStep.Result.Status.SUCCESS)
                        .url("url")
                        .build()
                )
                .isError(true)
                .signature("U3RhaW5sZXNzIHJvY2tz")
                .build()

        val step = Step.ofUrlContextResult(urlContextResult)

        assertThat(step.userInput()).isEmpty
        assertThat(step.modelOutput()).isEmpty
        assertThat(step.thought()).isEmpty
        assertThat(step.functionCall()).isEmpty
        assertThat(step.codeExecutionCall()).isEmpty
        assertThat(step.urlContextCall()).isEmpty
        assertThat(step.mcpServerToolCall()).isEmpty
        assertThat(step.googleSearchCall()).isEmpty
        assertThat(step.fileSearchCall()).isEmpty
        assertThat(step.googleMapsCall()).isEmpty
        assertThat(step.functionResult()).isEmpty
        assertThat(step.codeExecutionResult()).isEmpty
        assertThat(step.urlContextResult()).contains(urlContextResult)
        assertThat(step.googleSearchResult()).isEmpty
        assertThat(step.mcpServerToolResult()).isEmpty
        assertThat(step.fileSearchResult()).isEmpty
        assertThat(step.googleMapsResult()).isEmpty
    }

    @Test
    fun ofUrlContextResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val step =
            Step.ofUrlContextResult(
                UrlContextResultStep.builder()
                    .callId("call_id")
                    .addResult(
                        UrlContextResultStep.Result.builder()
                            .status(UrlContextResultStep.Result.Status.SUCCESS)
                            .url("url")
                            .build()
                    )
                    .isError(true)
                    .signature("U3RhaW5sZXNzIHJvY2tz")
                    .build()
            )

        val roundtrippedStep =
            jsonMapper.readValue(jsonMapper.writeValueAsString(step), jacksonTypeRef<Step>())

        assertThat(roundtrippedStep).isEqualTo(step)
    }

    @Test
    fun ofGoogleSearchResult() {
        val googleSearchResult =
            GoogleSearchResultStep.builder()
                .callId("call_id")
                .addResult(
                    GoogleSearchResultStep.Result.builder()
                        .searchSuggestions("search_suggestions")
                        .build()
                )
                .isError(true)
                .signature("U3RhaW5sZXNzIHJvY2tz")
                .build()

        val step = Step.ofGoogleSearchResult(googleSearchResult)

        assertThat(step.userInput()).isEmpty
        assertThat(step.modelOutput()).isEmpty
        assertThat(step.thought()).isEmpty
        assertThat(step.functionCall()).isEmpty
        assertThat(step.codeExecutionCall()).isEmpty
        assertThat(step.urlContextCall()).isEmpty
        assertThat(step.mcpServerToolCall()).isEmpty
        assertThat(step.googleSearchCall()).isEmpty
        assertThat(step.fileSearchCall()).isEmpty
        assertThat(step.googleMapsCall()).isEmpty
        assertThat(step.functionResult()).isEmpty
        assertThat(step.codeExecutionResult()).isEmpty
        assertThat(step.urlContextResult()).isEmpty
        assertThat(step.googleSearchResult()).contains(googleSearchResult)
        assertThat(step.mcpServerToolResult()).isEmpty
        assertThat(step.fileSearchResult()).isEmpty
        assertThat(step.googleMapsResult()).isEmpty
    }

    @Test
    fun ofGoogleSearchResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val step =
            Step.ofGoogleSearchResult(
                GoogleSearchResultStep.builder()
                    .callId("call_id")
                    .addResult(
                        GoogleSearchResultStep.Result.builder()
                            .searchSuggestions("search_suggestions")
                            .build()
                    )
                    .isError(true)
                    .signature("U3RhaW5sZXNzIHJvY2tz")
                    .build()
            )

        val roundtrippedStep =
            jsonMapper.readValue(jsonMapper.writeValueAsString(step), jacksonTypeRef<Step>())

        assertThat(roundtrippedStep).isEqualTo(step)
    }

    @Test
    fun ofMcpServerToolResult() {
        val mcpServerToolResult =
            McpServerToolResultStep.builder()
                .callId("call_id")
                .result(JsonValue.from(mapOf<String, Any>()))
                .name("name")
                .serverName("server_name")
                .signature("U3RhaW5sZXNzIHJvY2tz")
                .build()

        val step = Step.ofMcpServerToolResult(mcpServerToolResult)

        assertThat(step.userInput()).isEmpty
        assertThat(step.modelOutput()).isEmpty
        assertThat(step.thought()).isEmpty
        assertThat(step.functionCall()).isEmpty
        assertThat(step.codeExecutionCall()).isEmpty
        assertThat(step.urlContextCall()).isEmpty
        assertThat(step.mcpServerToolCall()).isEmpty
        assertThat(step.googleSearchCall()).isEmpty
        assertThat(step.fileSearchCall()).isEmpty
        assertThat(step.googleMapsCall()).isEmpty
        assertThat(step.functionResult()).isEmpty
        assertThat(step.codeExecutionResult()).isEmpty
        assertThat(step.urlContextResult()).isEmpty
        assertThat(step.googleSearchResult()).isEmpty
        assertThat(step.mcpServerToolResult()).contains(mcpServerToolResult)
        assertThat(step.fileSearchResult()).isEmpty
        assertThat(step.googleMapsResult()).isEmpty
    }

    @Test
    fun ofMcpServerToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val step =
            Step.ofMcpServerToolResult(
                McpServerToolResultStep.builder()
                    .callId("call_id")
                    .result(JsonValue.from(mapOf<String, Any>()))
                    .name("name")
                    .serverName("server_name")
                    .signature("U3RhaW5sZXNzIHJvY2tz")
                    .build()
            )

        val roundtrippedStep =
            jsonMapper.readValue(jsonMapper.writeValueAsString(step), jacksonTypeRef<Step>())

        assertThat(roundtrippedStep).isEqualTo(step)
    }

    @Test
    fun ofFileSearchResult() {
        val fileSearchResult =
            FileSearchResultStep.builder()
                .callId("call_id")
                .signature("U3RhaW5sZXNzIHJvY2tz")
                .build()

        val step = Step.ofFileSearchResult(fileSearchResult)

        assertThat(step.userInput()).isEmpty
        assertThat(step.modelOutput()).isEmpty
        assertThat(step.thought()).isEmpty
        assertThat(step.functionCall()).isEmpty
        assertThat(step.codeExecutionCall()).isEmpty
        assertThat(step.urlContextCall()).isEmpty
        assertThat(step.mcpServerToolCall()).isEmpty
        assertThat(step.googleSearchCall()).isEmpty
        assertThat(step.fileSearchCall()).isEmpty
        assertThat(step.googleMapsCall()).isEmpty
        assertThat(step.functionResult()).isEmpty
        assertThat(step.codeExecutionResult()).isEmpty
        assertThat(step.urlContextResult()).isEmpty
        assertThat(step.googleSearchResult()).isEmpty
        assertThat(step.mcpServerToolResult()).isEmpty
        assertThat(step.fileSearchResult()).contains(fileSearchResult)
        assertThat(step.googleMapsResult()).isEmpty
    }

    @Test
    fun ofFileSearchResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val step =
            Step.ofFileSearchResult(
                FileSearchResultStep.builder()
                    .callId("call_id")
                    .signature("U3RhaW5sZXNzIHJvY2tz")
                    .build()
            )

        val roundtrippedStep =
            jsonMapper.readValue(jsonMapper.writeValueAsString(step), jacksonTypeRef<Step>())

        assertThat(roundtrippedStep).isEqualTo(step)
    }

    @Test
    fun ofGoogleMapsResult() {
        val googleMapsResult =
            GoogleMapsResultStep.builder()
                .callId("call_id")
                .addResult(
                    GoogleMapsResultStep.Result.builder()
                        .addPlace(
                            GoogleMapsResultStep.Result.Place.builder()
                                .name("name")
                                .placeId("place_id")
                                .addReviewSnippet(
                                    GoogleMapsResultStep.Result.Place.ReviewSnippet.builder()
                                        .reviewId("review_id")
                                        .title("title")
                                        .url("url")
                                        .build()
                                )
                                .url("url")
                                .build()
                        )
                        .widgetContextToken("widget_context_token")
                        .build()
                )
                .signature("U3RhaW5sZXNzIHJvY2tz")
                .build()

        val step = Step.ofGoogleMapsResult(googleMapsResult)

        assertThat(step.userInput()).isEmpty
        assertThat(step.modelOutput()).isEmpty
        assertThat(step.thought()).isEmpty
        assertThat(step.functionCall()).isEmpty
        assertThat(step.codeExecutionCall()).isEmpty
        assertThat(step.urlContextCall()).isEmpty
        assertThat(step.mcpServerToolCall()).isEmpty
        assertThat(step.googleSearchCall()).isEmpty
        assertThat(step.fileSearchCall()).isEmpty
        assertThat(step.googleMapsCall()).isEmpty
        assertThat(step.functionResult()).isEmpty
        assertThat(step.codeExecutionResult()).isEmpty
        assertThat(step.urlContextResult()).isEmpty
        assertThat(step.googleSearchResult()).isEmpty
        assertThat(step.mcpServerToolResult()).isEmpty
        assertThat(step.fileSearchResult()).isEmpty
        assertThat(step.googleMapsResult()).contains(googleMapsResult)
    }

    @Test
    fun ofGoogleMapsResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val step =
            Step.ofGoogleMapsResult(
                GoogleMapsResultStep.builder()
                    .callId("call_id")
                    .addResult(
                        GoogleMapsResultStep.Result.builder()
                            .addPlace(
                                GoogleMapsResultStep.Result.Place.builder()
                                    .name("name")
                                    .placeId("place_id")
                                    .addReviewSnippet(
                                        GoogleMapsResultStep.Result.Place.ReviewSnippet.builder()
                                            .reviewId("review_id")
                                            .title("title")
                                            .url("url")
                                            .build()
                                    )
                                    .url("url")
                                    .build()
                            )
                            .widgetContextToken("widget_context_token")
                            .build()
                    )
                    .signature("U3RhaW5sZXNzIHJvY2tz")
                    .build()
            )

        val roundtrippedStep =
            jsonMapper.readValue(jsonMapper.writeValueAsString(step), jacksonTypeRef<Step>())

        assertThat(roundtrippedStep).isEqualTo(step)
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
        val step = jsonMapper().convertValue(testCase.value, jacksonTypeRef<Step>())

        val e = assertThrows<GeminiNextGenApiInvalidDataException> { step.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
