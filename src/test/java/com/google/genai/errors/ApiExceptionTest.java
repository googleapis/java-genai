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

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;

class ApiExceptionTest {

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

  // Helper Methods
  private static Response createFakeResponse(int statusCode, String reasonPhrase, String body) {
    return new Response.Builder()
        .request(new Request.Builder().url("http://example.com").build())
        .protocol(Protocol.HTTP_1_1)
        .code(statusCode)
        .message(reasonPhrase)
        .body(body != null ? ResponseBody.create(MediaType.get("application/json"), body) : null)
        .build();
  }
}
