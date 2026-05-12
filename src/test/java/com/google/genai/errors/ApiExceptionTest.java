/*
 * Copyright 2025 Google LLC
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

package com.google.genai.errors;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;

class ApiExceptionTest {
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @Test
  void testThrowFromResponse_200Status_DoesNotThrow() {
    Response response = createFakeResponse(200, "OK", "");

    assertDoesNotThrow(() -> ApiException.throwFromResponse(response));
  }

  @Test
  void testThrowFromResponse_ClientError_ThrowsClientException() {
    String status = "Bad Request";
    String message = "Invalid request";
    String jsonBody = String.format("{ \"error\": { \"message\": \"%s\" } }", message);
    Response response = createFakeResponse(400, status, jsonBody);

    ClientException thrown =
        assertThrows(ClientException.class, () -> ApiException.throwFromResponse(response));

    assertInstanceOf(ClientException.class, thrown);
    assertEquals(400, thrown.code());
    assertEquals(status, thrown.status());
    assertEquals(message, thrown.message());
    assertEquals(String.format("%d %s. %s", 400, status, message), thrown.getMessage());
  }

  @Test
  void testThrowFromResponse_ServerError_ThrowsServerException() {
    String status = "Internal Server Error";
    String message = "Server fault";
    String jsonBody = String.format("{ \"error\": { \"message\": \"%s\" } }", message);

    Response response = createFakeResponse(500, status, jsonBody);

    // Act & Assert
    ServerException thrown =
        assertThrows(ServerException.class, () -> ApiException.throwFromResponse(response));

    assertInstanceOf(ServerException.class, thrown);
    assertEquals(500, thrown.code());
    assertEquals(status, thrown.status());
    assertEquals(message, thrown.message());
    assertEquals(String.format("%d %s. %s", 500, status, message), thrown.getMessage());
  }

  @Test
  void testThrowFromResponse_OtherError_ThrowsApiException() {
    String status = "Moved Permanently";

    Response response = createFakeResponse(301, status, "");

    ApiException thrown =
        assertThrows(ApiException.class, () -> ApiException.throwFromResponse(response));

    assertFalse(thrown instanceof ClientException);
    assertFalse(thrown instanceof ServerException);

    assertEquals(301, thrown.code());
    assertEquals(status, thrown.status());
    assertEquals("", thrown.message());
    assertEquals(String.format("%d %s. ", 301, status), thrown.getMessage());
  }

  @Test
  void testGetErrorMessage_ValidMessage() {
    Response response =
        createFakeResponse(400, "Bad", "{ \"error\": { \"message\": \"test message\" } }");
    String message = ApiException.getErrorMessageFromResponse(response);
    assertEquals("test message", message);
  }

  @Test
  void testGetErrorMessage_WithDetails() {
    String jsonBody =
        "{  \"error\": {    \"code\": 400,    \"message\": \"Request contains an invalid"
            + " argument.\",    \"status\": \"INVALID_ARGUMENT\",    \"details\": [      {       "
            + " \"@type\": \"type.googleapis.com/google.rpc.DebugInfo\",        \"detail\":"
            + " \"[ORIGINAL ERROR] generic::invalid_argument: Referencing Google Cloud Storage"
            + " files directly is not supported.\"      }    ]  }}";
    Response response = createFakeResponse(400, "Bad Request", jsonBody);
    String message = ApiException.getErrorMessageFromResponse(response);
    String expectedMessage =
        "Request contains an invalid argument.\n"
            + "Details: [ORIGINAL ERROR] generic::invalid_argument: Referencing Google Cloud"
            + " Storage files directly is not supported.";
    assertEquals(expectedMessage, message);
  }

  @Test
  void testGetErrorMessage_WithDetailsNotArray() {
    String jsonBody =
        "{"
            + "  \"error\": {"
            + "    \"code\": 400,"
            + "    \"message\": \"Invalid argument\","
            + "    \"status\": \"INVALID_ARGUMENT\","
            + "    \"details\": \"some string\""
            + "  }"
            + "}";
    Response response = createFakeResponse(400, "Bad Request", jsonBody);
    String message = ApiException.getErrorMessageFromResponse(response);
    assertEquals("Invalid argument", message);
  }

  @Test
  void testGetErrorMessage_WithDetailsNonObjectItem() {
    String jsonBody =
        "{"
            + "  \"error\": {"
            + "    \"code\": 400,"
            + "    \"message\": \"Invalid argument\","
            + "    \"status\": \"INVALID_ARGUMENT\","
            + "    \"details\": ["
            + "      \"string detail\","
            + "      123"
            + "    ]"
            + "  }"
            + "}";
    Response response = createFakeResponse(400, "Bad Request", jsonBody);
    String message = ApiException.getErrorMessageFromResponse(response);
    String expectedMessage = "Invalid argument\nDetails: \"string detail\"\n123";
    assertEquals(expectedMessage, message);
  }

  @Test
  void testGetErrorMessage_WithDetailsNonStringDetail() {
    String jsonBody =
        "{"
            + "  \"error\": {"
            + "    \"code\": 400,"
            + "    \"message\": \"Invalid argument\","
            + "    \"status\": \"INVALID_ARGUMENT\","
            + "    \"details\": ["
            + "      {"
            + "        \"detail\": { \"sub\": \"error\" }"
            + "      }"
            + "    ]"
            + "  }"
            + "}";
    Response response = createFakeResponse(400, "Bad Request", jsonBody);
    String message = ApiException.getErrorMessageFromResponse(response);
    String expectedMessage = "Invalid argument\nDetails: {\"detail\":{\"sub\":\"error\"}}";
    assertEquals(expectedMessage, message);
  }

  @Test
  void testGetErrorMessage_NoErrorNode() {
    Response response = createFakeResponse(400, "Bad", "{}");
    String message = ApiException.getErrorMessageFromResponse(response);
    assertEquals("", message);
  }

  @Test
  void testGetErrorMessage_NoMessageNode() {
    Response response = createFakeResponse(400, "Bad", "{ \"error\": {} }");
    String message = ApiException.getErrorMessageFromResponse(response);
    assertEquals("", message);
  }

  @Test
  void testGetErrorMessage_MessageNotText() {
    Response response = createFakeResponse(400, "Bad", "{ \"error\": {\"message\": 123} }");
    String message = ApiException.getErrorMessageFromResponse(response);
    assertEquals("", message);
  }

  @Test
  void testGetErrorMessage_InvalidJson() {
    Response response = createFakeResponse(400, "Bad", "not json");
    String message = ApiException.getErrorMessageFromResponse(response);
    // JsonProcessingException is caught and ignored, returns ""
    assertEquals("", message);
  }

  @Test
  void testGetErrorMessage_EmptyBody() {
    Response response = createFakeResponse(400, "Bad", "");
    String message = ApiException.getErrorMessageFromResponse(response);
    assertEquals("", message);
  }

  @Test
  void testGetErrorMessage_NullBody() {
    Response response = createFakeResponse(400, "Bad", null);
    String message = ApiException.getErrorMessageFromResponse(response);
    assertEquals("", message);
  }

  @Test
  void testThrowFromErrorNode_OkStatus_DoesNotThrow() {
    ArrayNode errorNode = createErrorNode("test message", "OK", 200);
    assertDoesNotThrow(() -> ApiException.throwFromErrorNode(errorNode, 200));
  }

  @Test
  void testThrowFromErrorNode_ClientError_ThrowsClientException() {
    String status = "BAD_REQUEST";
    String message = "Invalid request";
    ArrayNode errorNode = createErrorNode(message, status, 400);
    ClientException thrown =
        assertThrows(ClientException.class, () -> ApiException.throwFromErrorNode(errorNode, 400));
    assertEquals(400, thrown.code());
    assertEquals(status, thrown.status());
    assertEquals(message, thrown.message());
  }

  @Test
  void testThrowFromErrorNode_ServerError_ThrowsServerException() {
    String status = "INTERNAL";
    String message = "Server error";
    ArrayNode errorNode = createErrorNode(message, status, 500);
    ServerException thrown =
        assertThrows(ServerException.class, () -> ApiException.throwFromErrorNode(errorNode, 500));
    assertEquals(500, thrown.code());
    assertEquals(status, thrown.status());
    assertEquals(message, thrown.message());
  }

  @Test
  void testThrowFromErrorNode_NullNode_defaultMessageAndStatus() {
    ApiException thrown =
        assertThrows(ApiException.class, () -> ApiException.throwFromErrorNode(null, 300));
    assertEquals(300, thrown.code());
    assertEquals("UNKNOWN", thrown.status());
    assertEquals("", thrown.message());
  }

  @Test
  void testThrowFromErrorNode_EmptyNode_defaultMessageAndStatus() {
    ApiException thrown =
        assertThrows(
            ApiException.class,
            () -> ApiException.throwFromErrorNode(OBJECT_MAPPER.createArrayNode(), 300));
    assertEquals(300, thrown.code());
    assertEquals("UNKNOWN", thrown.status());
    assertEquals("", thrown.message());
  }

  @Test
  void testThrowFromErrorNode_NoMessageNode_defaultMessage() {
    ArrayNode errorNode = createErrorNode(null, "BAD_REQUEST", 400);
    ApiException thrown =
        assertThrows(ApiException.class, () -> ApiException.throwFromErrorNode(errorNode, 400));
    assertEquals(400, thrown.code());
    assertEquals("BAD_REQUEST", thrown.status());
    assertEquals("", thrown.message());
  }

  @Test
  void testThrowFromErrorNode_NoStatusNode_defaultStatus() {
    ArrayNode errorNode = createErrorNode("Invalid Request", null, 400);
    ApiException thrown =
        assertThrows(ApiException.class, () -> ApiException.throwFromErrorNode(errorNode, 400));
    assertEquals(400, thrown.code());
    assertEquals("UNKNOWN", thrown.status());
    assertEquals("Invalid Request", thrown.message());
  }

  // Helper Methods
  private static Response createFakeResponse(int statusCode, String reasonPhrase, String body) {
    return new Response.Builder()
        .request(new Request.Builder().url("http://example.com").build())
        .protocol(Protocol.HTTP_1_1)
        .code(statusCode)
        .message(reasonPhrase)
        .body(body != null ? ResponseBody.create(body, MediaType.get("application/json")) : null)
        .build();
  }

  private static ArrayNode createErrorNode(String message, String status, int code) {
    ObjectNode node = OBJECT_MAPPER.createObjectNode();
    node.put("message", message);
    node.put("status", status);
    node.put("code", code);
    ObjectNode errorNode = OBJECT_MAPPER.createObjectNode();
    errorNode.set("error", node);
    return OBJECT_MAPPER.createArrayNode().add(errorNode);
  }
}
