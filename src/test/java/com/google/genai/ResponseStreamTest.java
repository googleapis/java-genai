/*
 * Copyright 2026 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.genai;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.genai.types.Candidate;
import com.google.genai.types.GenerateContentResponse;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;

public final class ResponseStreamTest {

  public static class DummyConverter {
    public JsonNode convert(JsonNode fromObject, ObjectNode parentObject) {
      return fromObject;
    }
  }

  @Test
  public void testMultiLineSseParsing() throws Exception {
    String sseData =
        "data: {\"candidates\": [{\"content\": {\"parts\": [{\"text\": \"line1\\n\"}]}},\n"
            + "data: {\"content\": {\"parts\": [{\"text\": \"line2\"}]}}]}\n"
            + "\n"; // End of event

    ResponseBody body =
        ResponseBody.create(
            sseData.getBytes(StandardCharsets.UTF_8), MediaType.parse("text/event-stream"));
    FakeApiResponse response = new FakeApiResponse(Headers.of(), body);

    DummyConverter converter = new DummyConverter();
    ResponseStream<GenerateContentResponse> responseStream =
        new ResponseStream<>(GenerateContentResponse.class, response, converter, "convert");

    Iterator<GenerateContentResponse> iterator = responseStream.iterator();

    assertTrue(iterator.hasNext());
    GenerateContentResponse response1 = iterator.next();

    assertTrue(response1.candidates().isPresent());
    assertEquals(2, response1.candidates().get().size());

    Candidate c1 = response1.candidates().get().get(0);
    assertEquals("line1\n", c1.content().get().text());

    Candidate c2 = response1.candidates().get().get(1);
    assertEquals("line2", c2.content().get().text());

    assertTrue(!iterator.hasNext());
  }

  @Test
  public void testIgnoreNonSseLines() throws Exception {
    String sseData =
        "data: {\"candidates\": [{\"content\": {\"parts\": [{\"text\": \"valid data\"}]}}]}\n"
            + ": some comment line\n"
            + "ignored field: some value\n"
            + "\n"; // End of event

    ResponseBody body =
        ResponseBody.create(
            sseData.getBytes(StandardCharsets.UTF_8), MediaType.parse("text/event-stream"));
    FakeApiResponse response = new FakeApiResponse(Headers.of(), body);

    DummyConverter converter = new DummyConverter();
    ResponseStream<GenerateContentResponse> responseStream =
        new ResponseStream<>(GenerateContentResponse.class, response, converter, "convert");

    Iterator<GenerateContentResponse> iterator = responseStream.iterator();

    assertTrue(iterator.hasNext());
    GenerateContentResponse response1 = iterator.next();

    assertEquals("valid data", response1.text());

    assertTrue(!iterator.hasNext());
  }

  @Test
  public void testStreamErrorHandling() throws Exception {
    String sseData =
        "data: {\"candidates\": [{\"content\": {\"parts\": [{\"text\": \"valid data\"}]}}]}\n"
            + "\n"
            + "data: {\"error\": {\"code\": 429, \"message\": \"Quota exceeded\", \"status\": \"RESOURCE_EXHAUSTED\"}}\n"
            + "\n";

    ResponseBody body =
        ResponseBody.create(
            sseData.getBytes(StandardCharsets.UTF_8), MediaType.parse("text/event-stream"));
    FakeApiResponse response = new FakeApiResponse(Headers.of(), body);

    DummyConverter converter = new DummyConverter();
    ResponseStream<GenerateContentResponse> responseStream =
        new ResponseStream<>(GenerateContentResponse.class, response, converter, "convert");

    Iterator<GenerateContentResponse> iterator = responseStream.iterator();

    assertTrue(iterator.hasNext());
    GenerateContentResponse response1 = iterator.next();
    assertEquals("valid data", response1.text());

    assertTrue(iterator.hasNext());

    try {
      iterator.next();
      org.junit.jupiter.api.Assertions.fail("Expected ApiException was not thrown");
    } catch (com.google.genai.errors.ApiException e) {
      assertEquals(429, e.code());
      assertEquals("RESOURCE_EXHAUSTED", e.status());
      assertTrue(e.getMessage().contains("Quota exceeded"));
    }
  }

  @Test
  public void testMultipleValidEvents() throws Exception {
    String sseData =
        "data: {\"candidates\": [{\"content\": {\"parts\": [{\"text\": \"chunk1\"}]}}]}\n"
            + "\n"
            + "data: {\"candidates\": [{\"content\": {\"parts\": [{\"text\": \"chunk2\"}]}}]}\n"
            + "\n";

    ResponseBody body =
        ResponseBody.create(
            sseData.getBytes(StandardCharsets.UTF_8), MediaType.parse("text/event-stream"));
    FakeApiResponse response = new FakeApiResponse(Headers.of(), body);

    DummyConverter converter = new DummyConverter();
    ResponseStream<GenerateContentResponse> responseStream =
        new ResponseStream<>(GenerateContentResponse.class, response, converter, "convert");

    Iterator<GenerateContentResponse> iterator = responseStream.iterator();

    assertTrue(iterator.hasNext());
    GenerateContentResponse response1 = iterator.next();
    assertEquals("chunk1", response1.text());

    assertTrue(iterator.hasNext());
    GenerateContentResponse response2 = iterator.next();
    assertEquals("chunk2", response2.text());

    assertTrue(!iterator.hasNext());
  }
}
