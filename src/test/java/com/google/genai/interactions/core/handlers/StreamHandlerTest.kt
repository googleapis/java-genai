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

package com.google.genai.interactions.core.handlers

import com.google.genai.interactions.core.http.Headers
import com.google.genai.interactions.core.http.HttpResponse
import com.google.genai.interactions.errors.GeminiNextGenApiIoException
import java.io.IOException
import java.io.InputStream
import kotlin.streams.asSequence
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows

internal class StreamHandlerTest {

    @Test
    fun streamHandler_splitsStreamOnNewlines() {
        val handler = streamHandler { _, lines -> yieldAll(lines) }
        val streamResponse = handler.handle(httpResponse("a\nbb\nccc\ndddd".byteInputStream()))

        val lines = streamResponse.stream().asSequence().toList()

        assertThat(lines).containsExactly("a", "bb", "ccc", "dddd")
    }

    @Test
    fun streamHandler_whenClosedEarly_stopsYielding() {
        val handler = streamHandler { _, lines -> yieldAll(lines) }
        val streamResponse = handler.handle(httpResponse("a\nbb\nccc\ndddd".byteInputStream()))

        val lines =
            streamResponse
                .stream()
                .asSequence()
                .onEach {
                    if (it == "bb") {
                        streamResponse.close()
                    }
                }
                .toList()

        assertThat(lines).containsExactly("a", "bb")
    }

    @Test
    fun streamHandler_whenReaderThrowsIOException_wrapsException() {
        val handler = streamHandler<String> { _, lines -> lines.forEach {} }
        val streamResponse = handler.handle(httpResponse("a\nb\nc\n".byteInputStream().throwing()))

        val e = assertThrows<GeminiNextGenApiIoException> { streamResponse.stream().forEach {} }
        assertThat(e).hasMessage("Stream failed")
        assertThat(e).hasCauseInstanceOf(IOException::class.java)
    }

    @Test
    fun streamHandler_whenBlockThrowsIOException_doesNotWrapException() {
        val ioException = IOException("BOOM!")
        val handler =
            streamHandler<String> { _, lines ->
                lines.forEachIndexed { index, _ ->
                    if (index == 2) {
                        throw ioException
                    }
                }
            }
        val streamResponse = handler.handle(httpResponse("a\nb\nc\n".byteInputStream()))

        val e = assertThrows<IOException> { streamResponse.stream().forEach {} }
        assertThat(e).isSameAs(ioException)
    }

    private fun httpResponse(body: InputStream): HttpResponse =
        object : HttpResponse {

            override fun statusCode(): Int = 0

            override fun headers(): Headers = Headers.builder().build()

            override fun body(): InputStream = body

            override fun close() {}
        }

    private fun InputStream.throwing(): InputStream =
        object : InputStream() {

            override fun read(): Int {
                val byte = this@throwing.read()
                if (byte == -1) {
                    throw IOException("BOOM!")
                }
                return byte
            }
        }
}
