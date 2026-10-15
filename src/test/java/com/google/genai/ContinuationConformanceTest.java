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

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.genai.errors.ApiException;
import com.google.genai.errors.GenAiIOException;
import com.google.genai.types.Candidate;
import com.google.genai.types.ClientOptions;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.GenerateContentResponseUsageMetadata;
import com.google.genai.types.HttpOptions;
import com.google.genai.types.HttpRetryOptions;
import com.google.genai.types.Part;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import okhttp3.mockwebserver.SocketPolicy;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

/**
 * Runs the shared automatic continuation conformance cases.
 *
 * <p>The cases live in a language-neutral JSON file shared by all the SDKs
 * (google/cloud/aiplatform/sdk/genai/replays/conformance/automatic_continuation.json); its
 * "notation" section describes the format. Each case scripts the server's response to every HTTP
 * request. This runner serves those responses from an OkHttp {@link MockWebServer}, so the SDK's
 * own request serialization, SSE parsing and error creation are exercised, calls the SDK (sync and
 * async), and checks the requests it sent and what it returned.
 */
public final class ContinuationConformanceTest {

  private static final String SPEC_PATH =
      "google/cloud/aiplatform/sdk/genai/replays/conformance/automatic_continuation.json";

  private static final String MODEL = "gemini-2.5-pro";
  private static final String PROMPT = "Hello";

  private static final ObjectMapper MAPPER = new ObjectMapper();

  /**
   * Finds the spec: under {@code $GOOGLE_GENAI_REPLAYS_DIRECTORY} if set, else at its google3 path
   * relative to the working directory or one of its parents (Maven runs in the SDK directory).
   */
  private static Optional<Path> findSpec() {
    String replaysDirectory = System.getenv("GOOGLE_GENAI_REPLAYS_DIRECTORY");
    if (replaysDirectory != null && !replaysDirectory.isEmpty()) {
      Path path = Paths.get(replaysDirectory, "conformance", "automatic_continuation.json");
      if (Files.isRegularFile(path)) {
        return Optional.of(path);
      }
    }
    for (Path dir = Paths.get("").toAbsolutePath(); dir != null; dir = dir.getParent()) {
      Path path = dir.resolve(SPEC_PATH);
      if (Files.isRegularFile(path)) {
        return Optional.of(path);
      }
    }
    return Optional.empty();
  }

  @TestFactory
  Stream<DynamicNode> conformance() throws IOException {
    Optional<Path> spec = findSpec();
    Assumptions.assumeTrue(
        spec.isPresent(),
        "Conformance spec not found: set GOOGLE_GENAI_REPLAYS_DIRECTORY or run from a google3"
            + " checkout containing "
            + SPEC_PATH);
    List<JsonNode> cases = new ArrayList<>();
    MAPPER.readTree(spec.get().toFile()).get("cases").forEach(cases::add);
    return Stream.of(
        DynamicContainer.dynamicContainer("sync", tests(cases, /* async= */ false)),
        DynamicContainer.dynamicContainer("async", tests(cases, /* async= */ true)));
  }

  private static Stream<DynamicTest> tests(List<JsonNode> cases, boolean async) {
    return cases.stream()
        .map(c -> DynamicTest.dynamicTest(c.get("name").asText(), () -> runCase(c, async)));
  }

  // Wire format.

  private static String encodeToken(String name) {
    return Base64.getEncoder().encodeToString(name.getBytes(UTF_8));
  }

  private static @Nullable String decodeToken(@Nullable String value) {
    if (value == null) {
      return null;
    }
    String standard = value.replace('-', '+').replace('_', '/');
    return new String(Base64.getMimeDecoder().decode(standard), UTF_8);
  }

  /** Converts a case response to the API's JSON wire format. */
  private static ObjectNode wireResponse(JsonNode r) {
    ObjectNode payload = MAPPER.createObjectNode();
    if (r.has("text") || r.has("token") || r.has("finish")) {
      ObjectNode candidate = MAPPER.createObjectNode();
      candidate.put("index", 0);
      if (r.has("text")) {
        ObjectNode content = candidate.putObject("content");
        content.put("role", "model");
        content.putArray("parts").addObject().put("text", r.get("text").asText());
      }
      if (r.has("finish")) {
        candidate.put("finishReason", r.get("finish").asText());
      }
      if (r.has("token")) {
        candidate.put("continuationToken", encodeToken(r.get("token").asText()));
      }
      payload.putArray("candidates").add(candidate);
    }
    if (r.has("prompt_feedback")) {
      payload.putObject("promptFeedback").put("blockReason", r.get("prompt_feedback").asText());
    }
    if (r.has("usage")) {
      Map<String, String> names = new LinkedHashMap<>();
      names.put("prompt", "promptTokenCount");
      names.put("candidates", "candidatesTokenCount");
      names.put("total", "totalTokenCount");
      ObjectNode usage = payload.putObject("usageMetadata");
      Iterator<Map.Entry<String, JsonNode>> fields = r.get("usage").fields();
      while (fields.hasNext()) {
        Map.Entry<String, JsonNode> field = fields.next();
        usage.set(names.get(field.getKey()), field.getValue());
      }
    }
    return payload;
  }

  private static String errorBody(int code) {
    ObjectNode body = MAPPER.createObjectNode();
    ObjectNode error = body.putObject("error");
    error.put("code", code);
    error.put("message", "conformance");
    error.put("status", "ERROR");
    return body.toString();
  }

  /**
   * Answers each request with the next scripted hop and records the continuation token it was sent
   * with.
   */
  private static final class FakeServer extends Dispatcher {
    private final List<JsonNode> hops = new ArrayList<>();
    final List<@Nullable String> requestTokens = new ArrayList<>();
    final List<String> unexpected = new ArrayList<>();

    FakeServer(JsonNode hops) {
      hops.forEach(this.hops::add);
    }

    @Override
    public synchronized MockResponse dispatch(RecordedRequest request) {
      JsonNode body;
      try {
        body = MAPPER.readTree(request.getBody().readUtf8());
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
      JsonNode token = body.get("continuationToken");
      requestTokens.add(token == null || token.isNull() ? null : decodeToken(token.asText()));
      if (requestTokens.size() > hops.size()) {
        unexpected.add("Unexpected request #" + requestTokens.size() + " to " + request.getPath());
        return new MockResponse().setResponseCode(599).setBody(errorBody(599));
      }
      JsonNode hop = hops.get(requestTokens.size() - 1);
      boolean streaming = request.getPath().contains(":streamGenerateContent");
      if (hop.has("status")) {
        int status = hop.get("status").asInt();
        return new MockResponse()
            .setResponseCode(status)
            .setHeader("Content-Type", "application/json")
            .setBody(errorBody(status));
      }
      if (hop.path("transport_error").asBoolean(false)) {
        // The server reads the request and closes the connection without answering.
        return new MockResponse().setSocketPolicy(SocketPolicy.DISCONNECT_AFTER_REQUEST);
      }
      if (hop.has("response")) {
        if (streaming) {
          unexpected.add("Streaming request for unary hop #" + requestTokens.size());
        }
        return new MockResponse()
            .setHeader("Content-Type", "application/json")
            .setBody(wireResponse(hop.get("response")).toString());
      }
      if (!streaming) {
        unexpected.add("Unary request for streaming hop #" + requestTokens.size());
      }
      StringBuilder sse = new StringBuilder();
      for (JsonNode chunk : hop.get("chunks")) {
        String data =
            chunk.has("error")
                ? errorBody(chunk.get("error").asInt())
                : wireResponse(chunk).toString();
        sse.append("data: ").append(data).append("\n\n");
      }
      MockResponse response =
          new MockResponse().setHeader("Content-Type", "text/event-stream").setBody(sse.toString());
      if ("drop".equals(hop.path("then").asText("close"))) {
        // Promise more bytes than are sent, then close the connection: the client reads every
        // chunk and then fails with an unexpected end of stream.
        long length = sse.toString().getBytes(UTF_8).length;
        response
            .setHeader("Content-Length", length + 1000)
            .setSocketPolicy(SocketPolicy.DISCONNECT_AT_END);
      }
      return response;
    }
  }

  // Running a case.

  private static void runCase(JsonNode testCase, boolean async) throws Exception {
    FakeServer fake = new FakeServer(testCase.get("hops"));
    MockWebServer server = new MockWebServer();
    server.setDispatcher(fake);
    server.start(InetAddress.getByName("127.0.0.1"), 0);
    try {
      // A literal IP gives OkHttp a single route: after a dropped connection it would otherwise
      // try "localhost" on ::1, where the server doesn't listen.
      String baseUrl = "http://127.0.0.1:" + server.getPort();
      Client client =
          Client.builder()
              .apiKey("test-key")
              .vertexAI(false)
              .debugConfig(new DebugConfig("", "", ""))
              .httpOptions(
                  HttpOptions.builder()
                      .baseUrl(baseUrl)
                      // Retrying rejected requests is not part of the continuation behavior.
                      .retryOptions(HttpRetryOptions.builder().attempts(1).build())
                      .build())
              .clientOptions(
                  ClientOptions.builder()
                      // Don't let OkHttp silently resend a request whose connection failed.
                      .customHttpClient(
                          new OkHttpClient.Builder().retryOnConnectionFailure(false).build())
                      .build())
              .build();
      GenerateContentConfig config =
          testCase.has("config") ? toConfig(testCase.get("config")) : null;
      boolean stream = testCase.get("method").asText().equals("generate_content_stream");

      GenerateContentResponse response = null;
      List<GenerateContentResponse> chunks = new ArrayList<>();
      RuntimeException error = null;
      try {
        if (!stream) {
          response =
              async
                  ? unwrap(() -> client.async.models.generateContent(MODEL, PROMPT, config).get())
                  : client.models.generateContent(MODEL, PROMPT, config);
        } else {
          ResponseStream<GenerateContentResponse> responses =
              async
                  ? unwrap(
                      () -> client.async.models.generateContentStream(MODEL, PROMPT, config).get())
                  : client.models.generateContentStream(MODEL, PROMPT, config);
          try {
            for (GenerateContentResponse chunk : responses) {
              chunks.add(chunk);
            }
          } finally {
            responses.close();
          }
        }
      } catch (ApiException | GenAiIOException e) {
        error = e;
      } finally {
        client.close();
      }
      assertTrue(fake.unexpected.isEmpty(), fake.unexpected.toString());
      checkResults(testCase, fake, response, chunks, error);
    } finally {
      server.shutdown();
    }
  }

  private interface Call<T> {
    T call() throws Exception;
  }

  /** Waits for an async call, rethrowing an SDK error as itself. */
  private static <T> T unwrap(Call<T> call) throws Exception {
    try {
      return call.call();
    } catch (ExecutionException | CompletionException e) {
      if (e.getCause() instanceof RuntimeException) {
        throw (RuntimeException) e.getCause();
      }
      throw e;
    }
  }

  private static GenerateContentConfig toConfig(JsonNode config) {
    GenerateContentConfig.Builder builder = GenerateContentConfig.builder();
    Iterator<Map.Entry<String, JsonNode>> fields = config.fields();
    while (fields.hasNext()) {
      Map.Entry<String, JsonNode> field = fields.next();
      if (field.getKey().equals("automatic_continuation")) {
        builder.automaticContinuation(field.getValue().asBoolean());
      } else {
        throw new IllegalArgumentException("Unsupported config field: " + field.getKey());
      }
    }
    return builder.build();
  }

  // Checks.

  /** Extracts the checked properties from a response or chunk, as JSON. */
  private static ObjectNode actual(GenerateContentResponse response) {
    Optional<Candidate> candidate =
        response.candidates().isPresent() && !response.candidates().get().isEmpty()
            ? Optional.of(response.candidates().get().get(0))
            : Optional.empty();
    StringBuilder text = new StringBuilder();
    if (candidate.isPresent()
        && candidate.get().content().isPresent()
        && candidate.get().content().get().parts().isPresent()) {
      for (Part part : candidate.get().content().get().parts().get()) {
        part.text().ifPresent(text::append);
      }
    }
    ObjectNode result = MAPPER.createObjectNode();
    result.put("text", text.toString());
    Optional<byte[]> token = candidate.flatMap(Candidate::continuationToken);
    result.put("token", token.isPresent() ? new String(token.get(), UTF_8) : null);
    result.put(
        "finish", candidate.flatMap(Candidate::finishReason).map(Object::toString).orElse(null));
    result.put(
        "prompt_feedback",
        response.promptFeedback().flatMap(f -> f.blockReason()).map(Object::toString).orElse(null));
    Optional<GenerateContentResponseUsageMetadata> usage = response.usageMetadata();
    ObjectNode usageNode = result.putObject("usage");
    usageNode.put(
        "prompt",
        usage.flatMap(GenerateContentResponseUsageMetadata::promptTokenCount).orElse(null));
    usageNode.put(
        "candidates",
        usage.flatMap(GenerateContentResponseUsageMetadata::candidatesTokenCount).orElse(null));
    usageNode.put(
        "total", usage.flatMap(GenerateContentResponseUsageMetadata::totalTokenCount).orElse(null));
    return result;
  }

  private static void check(String label, GenerateContentResponse response, JsonNode want) {
    ObjectNode actual = actual(response);
    Iterator<Map.Entry<String, JsonNode>> fields = want.fields();
    while (fields.hasNext()) {
      Map.Entry<String, JsonNode> field = fields.next();
      JsonNode got = actual.get(field.getKey());
      assertNotNull(got, "Unknown check " + field.getKey());
      if (field.getKey().equals("usage")) {
        ObjectNode subset = MAPPER.createObjectNode();
        field
            .getValue()
            .fieldNames()
            .forEachRemaining(k -> subset.set(k, actual.get("usage").get(k)));
        got = subset;
      }
      assertEquals(
          field.getValue(),
          got,
          label + "." + field.getKey() + ": got " + got + ", want " + field.getValue());
    }
  }

  private static void checkError(@Nullable RuntimeException error, JsonNode want) {
    if (want == null || want.isNull()) {
      if (error != null) {
        throw error;
      }
      return;
    }
    assertNotNull(error, "Expected an error " + want + ", but the call succeeded.");
    String type = want.get("type").asText();
    if (type.equals("api")) {
      assertTrue(error instanceof ApiException, "Expected an ApiException, got " + error);
      assertEquals(want.get("code").asInt(), ((ApiException) error).code(), error.toString());
    } else if (type.equals("transport")) {
      assertTrue(error instanceof GenAiIOException, "Expected a GenAiIOException, got " + error);
    } else {
      fail("Unknown error type: " + want);
    }
  }

  private static void checkResults(
      JsonNode testCase,
      FakeServer fake,
      @Nullable GenerateContentResponse response,
      List<GenerateContentResponse> chunks,
      @Nullable RuntimeException error) {
    JsonNode expect = testCase.get("expect");
    checkError(error, expect.get("error"));
    List<@Nullable String> wantTokens = new ArrayList<>();
    expect.get("request_tokens").forEach(t -> wantTokens.add(t.isNull() ? null : t.asText()));
    assertEquals(wantTokens, fake.requestTokens, "request tokens");
    if (testCase.get("method").asText().equals("generate_content")) {
      if (expect.has("response")) {
        assertNotNull(response, "response");
        check("response", response, expect.get("response"));
      }
    } else {
      JsonNode wantChunks = expect.has("chunks") ? expect.get("chunks") : MAPPER.createArrayNode();
      List<ObjectNode> actualChunks = new ArrayList<>();
      for (GenerateContentResponse chunk : chunks) {
        actualChunks.add(actual(chunk));
      }
      assertEquals(
          wantChunks.size(),
          chunks.size(),
          "chunks: got " + chunks.size() + ": " + actualChunks + ", want " + wantChunks.size());
      for (int i = 0; i < chunks.size(); i++) {
        check("chunks[" + i + "]", chunks.get(i), ((ArrayNode) wantChunks).get(i));
      }
    }
  }
}
