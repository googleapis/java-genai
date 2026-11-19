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

package com.google.genai.gaos.models.errors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.genai.errors.ApiException;
import com.google.genai.gaos.utils.Headers;
import com.google.genai.gaos.utils.transport.HttpRequest;
import com.google.genai.gaos.utils.transport.HttpResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class GaosErrorBodyTest {

  private static GaosApiException apiException(int code, String message, String body) {
    byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
    HttpRequest request =
        HttpRequest.builder()
            .method("POST")
            .uri(URI.create("https://generativelanguage.googleapis.com/v1beta/interactions"))
            .build();
    HttpResponse<InputStream> rawResponse =
        new HttpResponse<>(request, code, new Headers(), new ByteArrayInputStream(bytes));
    return new GaosApiException(message, code, bytes, rawResponse, null);
  }

  @Test
  public void interactionsBodyProvidesStatusAndMessage() {
    GaosApiException ex =
        apiException(
            400,
            GaosErrorBody.DEFAULT_MESSAGE,
            "{\"error\":{\"message\":\"Model name cannot be empty.\",\"code\":\"invalid_request\"}}");

    assertEquals(400, ex.code());
    assertEquals("invalid_request", ex.status());
    assertEquals("400 invalid_request. Model name cannot be empty.", ex.getMessage());
  }

  @Test
  public void onePlatformBodyPrefersStatusOverNumericCode() {
    GaosApiException ex =
        apiException(
            400,
            GaosErrorBody.DEFAULT_MESSAGE,
            "{\"error\":{\"code\":400,\"message\":\"Bad thing\",\"status\":\"INVALID_ARGUMENT\"}}");

    assertEquals("INVALID_ARGUMENT", ex.status());
    assertEquals("400 INVALID_ARGUMENT. Bad thing", ex.getMessage());
  }

  @Test
  public void arrayWrappedBodyProvidesStatusAndMessage() {
    byte[] body =
        "[{\"error\":{\"code\":400,\"message\":\"Stream request is invalid.\",\"status\":\"INVALID_ARGUMENT\"}}]"
            .getBytes(StandardCharsets.UTF_8);

    assertEquals("INVALID_ARGUMENT", GaosErrorBody.status(body));
    assertEquals(
        "Stream request is invalid.", GaosErrorBody.message(GaosErrorBody.DEFAULT_MESSAGE, body));
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = '|',
      value = {
        "<html>Bad Gateway</html>",
        "{}",
        "[]",
        "{\"error\":\"flat string\"}",
        "{\"error\":{\"message\":\"\",\"status\":\"\"}}",
        "[{\"error\":{\"message\":\"first\",\"status\":\"A\"}},{\"error\":{\"message\":\"second\",\"status\":\"B\"}}]",
      })
  public void unusableBodyKeepsDefaults(String body) {
    assertEquals("", GaosErrorBody.status(body.getBytes(StandardCharsets.UTF_8)));
    assertEquals(
        GaosErrorBody.DEFAULT_MESSAGE,
        GaosErrorBody.message(GaosErrorBody.DEFAULT_MESSAGE, body.getBytes(StandardCharsets.UTF_8)));
  }

  @Test
  public void missingBodyKeepsDefaults() {
    assertEquals("", GaosErrorBody.status(null));
    assertEquals(GaosErrorBody.DEFAULT_MESSAGE, GaosErrorBody.message(GaosErrorBody.DEFAULT_MESSAGE, null));
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        "{\"error\":{\"message\":\"Bad thing\"}}",
        "{\"error\":{\"message\":\"Bad thing\",\"details\":[]}}",
        "{\"error\":{\"code\":400,\"message\":\"Bad thing\",\"status\":\"INVALID_ARGUMENT\","
            + "\"details\":[{\"@type\":\"type.googleapis.com/google.rpc.ErrorInfo\","
            + "\"reason\":\"API_KEY_INVALID\"}]}}",
        "{\"error\":{\"message\":\"Bad thing\","
            + "\"details\":[{\"detail\":\"field a is required\"},{\"detail\":\"field b is required\"}]}}",
        "{\"error\":{\"message\":\"Bad thing\",\"details\":[null,\"text\",7]}}",
        "{\"error\":{\"details\":[{\"reason\":\"API_KEY_INVALID\"}]}}",
      })
  public void objectBodyMessageMatchesNativeApiException(String body) {
    ApiException nativeErr = nativeException(Protocol.HTTP_2, "", body);

    assertEquals(
        nativeErr.message(),
        GaosErrorBody.message(GaosErrorBody.DEFAULT_MESSAGE, body.getBytes(StandardCharsets.UTF_8)));
  }

  @Test
  public void statusFallbackToInteractionsCodeDiffersFromNativeApiException() {
    String body =
        "{\"error\":{\"message\":\"Model name cannot be empty.\",\"code\":\"invalid_request\"}}";

    ApiException http2 = nativeException(Protocol.HTTP_2, "", body);
    ApiException http11 = nativeException(Protocol.HTTP_1_1, "Bad Request", body);

    // Native falls back to the HTTP reason phrase; gaos falls back to the string error.code.
    assertEquals("", http2.status());
    assertEquals("Bad Request", http11.status());
    assertEquals("invalid_request", GaosErrorBody.status(body.getBytes(StandardCharsets.UTF_8)));
    // The message itself is the same.
    assertEquals(
        http2.message(),
        GaosErrorBody.message(GaosErrorBody.DEFAULT_MESSAGE, body.getBytes(StandardCharsets.UTF_8)));
  }

  @Test
  public void arrayBodiesDifferFromNativeThrowFromErrorNode() throws IOException {
    String single =
        "[{\"error\":{\"code\":400,\"message\":\"Bad thing\","
            + "\"details\":[{\"reason\":\"API_KEY_INVALID\"}]}}]";
    ApiException nativeSingle = nativeArrayException(single);
    // Native reads only error.message and defaults a missing status to UNKNOWN; gaos also appends
    // the details and leaves a missing status empty.
    assertEquals("Bad thing", nativeSingle.message());
    assertEquals("UNKNOWN", nativeSingle.status());
    assertEquals(
        "Bad thing\nDetails: {\"reason\":\"API_KEY_INVALID\"}",
        GaosErrorBody.message(
            GaosErrorBody.DEFAULT_MESSAGE, single.getBytes(StandardCharsets.UTF_8)));
    assertEquals("", GaosErrorBody.status(single.getBytes(StandardCharsets.UTF_8)));

    String multi =
        "[{\"error\":{\"message\":\"first\",\"status\":\"A\"}},"
            + "{\"error\":{\"message\":\"second\",\"status\":\"B\"}}]";
    ApiException nativeMulti = nativeArrayException(multi);
    // Native reads the first element; gaos keeps the defaults.
    assertEquals("first", nativeMulti.message());
    assertEquals("A", nativeMulti.status());
    assertEquals(
        GaosErrorBody.DEFAULT_MESSAGE,
        GaosErrorBody.message(GaosErrorBody.DEFAULT_MESSAGE, multi.getBytes(StandardCharsets.UTF_8)));
    assertEquals("", GaosErrorBody.status(multi.getBytes(StandardCharsets.UTF_8)));
  }

  private static ApiException nativeException(Protocol protocol, String reason, String body) {
    Response response =
        new Response.Builder()
            .request(new Request.Builder().url("https://generativelanguage.googleapis.com").build())
            .protocol(protocol)
            .code(400)
            .message(reason)
            .body(ResponseBody.create(body, MediaType.get("application/json")))
            .build();
    return assertThrows(ApiException.class, () -> ApiException.throwFromResponse(response));
  }

  private static ApiException nativeArrayException(String body) throws IOException {
    ArrayNode errorNode = (ArrayNode) new ObjectMapper().readTree(body);
    return assertThrows(ApiException.class, () -> ApiException.throwFromErrorNode(errorNode, 400));
  }

  @Test
  public void detailsWithoutMessageAreSurfaced() {
    GaosApiException ex =
        apiException(
            400,
            GaosErrorBody.DEFAULT_MESSAGE,
            "{\"error\":{\"status\":\"INVALID_ARGUMENT\","
                + "\"details\":[{\"detail\":\"field a is required\"}]}}");

    assertEquals("400 INVALID_ARGUMENT. Details: field a is required", ex.getMessage());
  }

  @Test
  public void specificMessageIsNotReplaced() {
    String message = "Upload chunk failed with status: 400";

    assertEquals(
        message,
        GaosErrorBody.message(
            message,
            "{\"error\":{\"message\":\"Chunk too large.\"}}".getBytes(StandardCharsets.UTF_8)));
  }

  @Test
  public void gaosApiExceptionFromParsesOnePlatformAndInteractionsBodies() {
    HttpRequest request =
        HttpRequest.builder()
            .method("POST")
            .uri(URI.create("https://generativelanguage.googleapis.com/v1beta/decisions"))
            .build();

    byte[] onePlatformBytes =
        ("{\"error\":{\"code\":400,\"message\":\"Decision input is invalid.\","
                + "\"status\":\"INVALID_ARGUMENT\","
                + "\"details\":[{\"@type\":\"type.googleapis.com/google.rpc.ErrorInfo\","
                + "\"reason\":\"API_KEY_INVALID\"}]}}")
            .getBytes(StandardCharsets.UTF_8);
    GaosApiException onePlatformErr =
        GaosApiException.from(
            GaosErrorBody.DEFAULT_MESSAGE,
            new HttpResponse<>(
                request, 400, new Headers(), new ByteArrayInputStream(onePlatformBytes)));
    assertEquals(400, onePlatformErr.code());
    assertEquals("INVALID_ARGUMENT", onePlatformErr.status());
    assertEquals(
        "400 INVALID_ARGUMENT. Decision input is invalid."
            + "\nDetails: {\"@type\":\"type.googleapis.com/google.rpc.ErrorInfo\",\"reason\":\"API_KEY_INVALID\"}",
        onePlatformErr.getMessage());

    byte[] interactionsBytes =
        "{\"error\":{\"code\":\"invalid_request\",\"message\":\"Model name cannot be empty.\"}}"
            .getBytes(StandardCharsets.UTF_8);
    GaosApiException interactionsErr =
        GaosApiException.from(
            GaosErrorBody.DEFAULT_MESSAGE,
            new HttpResponse<>(
                request, 400, new Headers(), new ByteArrayInputStream(interactionsBytes)));
    assertEquals(400, interactionsErr.code());
    assertEquals("invalid_request", interactionsErr.status());
    assertEquals(
        "400 invalid_request. Model name cannot be empty.", interactionsErr.getMessage());
  }

  @Test
  public void gaosClientAndServerCarriersParseOnePlatformAndInteractionsBodies() {
    HttpRequest request =
        HttpRequest.builder()
            .method("POST")
            .uri(URI.create("https://generativelanguage.googleapis.com/v1beta/interactions"))
            .build();

    byte[] onePlatformBytes =
        ("{\"error\":{\"code\":400,\"message\":\"Interaction input is invalid.\","
                + "\"status\":\"INVALID_ARGUMENT\","
                + "\"details\":[{\"@type\":\"type.googleapis.com/google.rpc.ErrorInfo\","
                + "\"reason\":\"API_KEY_INVALID\"}]}}")
            .getBytes(StandardCharsets.UTF_8);
    CreateInteractionClientError onePlatformClientErr =
        CreateInteractionClientError.from(
            new HttpResponse<>(
                request, 400, new Headers(), new ByteArrayInputStream(onePlatformBytes)));
    assertEquals(400, onePlatformClientErr.code());
    assertEquals("INVALID_ARGUMENT", onePlatformClientErr.status());
    assertEquals(
        "400 INVALID_ARGUMENT. Interaction input is invalid."
            + "\nDetails: {\"@type\":\"type.googleapis.com/google.rpc.ErrorInfo\",\"reason\":\"API_KEY_INVALID\"}",
        onePlatformClientErr.getMessage());

    byte[] interactionsBytes =
        "{\"error\":{\"code\":\"invalid_request\",\"message\":\"Model name cannot be empty.\"}}"
            .getBytes(StandardCharsets.UTF_8);
    CreateInteractionClientError interactionsClientErr =
        CreateInteractionClientError.from(
            new HttpResponse<>(
                request, 400, new Headers(), new ByteArrayInputStream(interactionsBytes)));
    assertEquals(400, interactionsClientErr.code());
    assertEquals("invalid_request", interactionsClientErr.status());
    assertEquals(
        "400 invalid_request. Model name cannot be empty.",
        interactionsClientErr.getMessage());
    assertTrue(interactionsClientErr.data().isPresent());
    assertFalse(interactionsClientErr.deserializationException().isPresent());

    byte[] serverBytes =
        "{\"error\":{\"code\":503,\"message\":\"Backend unavailable.\",\"status\":\"UNAVAILABLE\"}}"
            .getBytes(StandardCharsets.UTF_8);
    CreateInteractionServerError serverErr =
        CreateInteractionServerError.from(
            new HttpResponse<>(request, 503, new Headers(), new ByteArrayInputStream(serverBytes)));
    assertEquals(503, serverErr.code());
    assertEquals("UNAVAILABLE", serverErr.status());
    assertEquals("503 UNAVAILABLE. Backend unavailable.", serverErr.getMessage());
  }
}
