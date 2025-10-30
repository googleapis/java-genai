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

package com.google.genai;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.genai.errors.GenAiIOException;
import com.google.genai.proto.SentencepieceModel.ModelProto;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class LocalTokenizerLoaderTest {

  @TempDir Path tempDir;

  private String originalTmpDir;
  private OkHttpClient mockHttpClient;
  private Call mockCall;
  private Map<String, ?> originalTokenizers;

  // Helper to modify final static fields
  private static void setStatic(Field field, Object newValue) throws Exception {
    field.setAccessible(true);
    field.set(null, newValue);
  }

  @BeforeEach
  void setUp() throws Exception {
    originalTmpDir = System.getProperty("java.io.tmpdir");
    System.setProperty("java.io.tmpdir", tempDir.toString());

    // Mock HttpClient
    mockHttpClient = mock(OkHttpClient.class);
    mockCall = mock(Call.class);
    when(mockHttpClient.newCall(any(Request.class))).thenReturn(mockCall);

    Field clientField = LocalTokenizerLoader.class.getDeclaredField("httpClient");
    setStatic(clientField, mockHttpClient);

    // Backup original tokenizers map
    Field tokenizersField = LocalTokenizerLoader.class.getDeclaredField("TOKENIZERS");
    tokenizersField.setAccessible(true);
    originalTokenizers = (Map<String, ?>) tokenizersField.get(null);

    // Clear caches
    Field modelProtoCacheField = LocalTokenizerLoader.class.getDeclaredField("modelProtoCache");
    modelProtoCacheField.setAccessible(true);
    ((Map<?, ?>) modelProtoCacheField.get(null)).clear();

    Field processorCacheField =
        LocalTokenizerLoader.class.getDeclaredField("localTokenizerProcessorCache");
    processorCacheField.setAccessible(true);
    ((Map<?, ?>) processorCacheField.get(null)).clear();
  }

  @AfterEach
  void tearDown() throws Exception {
    System.setProperty("java.io.tmpdir", originalTmpDir);
    // Restore original tokenizers map
    Field tokenizersField = LocalTokenizerLoader.class.getDeclaredField("TOKENIZERS");
    setStatic(tokenizersField, originalTokenizers);
  }

  @Test
  void getTokenizerName_supportedModels() {
    assertEquals("gemma2", LocalTokenizerLoader.getTokenizerName("gemini-1.5-pro"));
    assertEquals("gemma3", LocalTokenizerLoader.getTokenizerName("gemini-2.5-pro"));
  }

  @Test
  void getTokenizerName_unsupportedModel_throwsException() {
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> LocalTokenizerLoader.getTokenizerName("unsupported-model"));
    assertTrue(exception.getMessage().contains("is not supported"));
  }

  @Test
  void loadModelProto_unsupportedTokenizer_throwsException() {
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> LocalTokenizerLoader.loadModelProto("unsupported-tokenizer"));
    assertTrue(exception.getMessage().contains("is not supported"));
  }

  @Test
  void loadModelProto_loadsAndCaches() throws Exception {
    // 1. Prepare test data
    String tokenizerName = "test-tokenizer";
    String modelUrl = "http://fake.url/model";
    ModelProto testProto =
        ModelProto.newBuilder()
            .addPieces(
                ModelProto.SentencePiece.newBuilder()
                    .setPiece("<s>")
                    .setType(ModelProto.SentencePiece.Type.CONTROL))
            .build();
    byte[] protoBytes = testProto.toByteArray();
    String protoHash = sha256(protoBytes);

    // 2. Inject test tokenizer config
    Field tokenizersField = LocalTokenizerLoader.class.getDeclaredField("TOKENIZERS");
    tokenizersField.setAccessible(true);
    @SuppressWarnings("unchecked")
    Map<String, Object> tokenizers = (Map<String, Object>) tokenizersField.get(null);
    Map<String, Object> modifiableTokenizers = new HashMap<>(tokenizers);
    LocalTokenizerLoader.TokenizerConfig tokenizerConfig =
        new LocalTokenizerLoader.TokenizerConfig(modelUrl, protoHash);
    modifiableTokenizers.put(tokenizerName, tokenizerConfig);
    setStatic(tokenizersField, Collections.unmodifiableMap(modifiableTokenizers));

    // 3. Mock the network response
    Response mockResponse =
        new Response.Builder()
            .request(new Request.Builder().url(modelUrl).build())
            .protocol(Protocol.HTTP_1_1)
            .code(200)
            .message("OK")
            .body(ResponseBody.create(null, protoBytes))
            .build();
    when(mockCall.execute()).thenReturn(mockResponse);

    // 4. First call: should download and cache
    ModelProto loadedProto1 = LocalTokenizerLoader.loadModelProto(tokenizerName);
    assertEquals(testProto, loadedProto1);
    verify(mockHttpClient, times(1)).newCall(any(Request.class));

    // 5. Second call: should use in-memory cache
    ModelProto loadedProto2 = LocalTokenizerLoader.loadModelProto(tokenizerName);
    assertEquals(testProto, loadedProto2);
    verify(mockHttpClient, times(1)).newCall(any(Request.class));

    // 6. Third call after clearing memory cache: should use file cache
    Field modelProtoCacheField = LocalTokenizerLoader.class.getDeclaredField("modelProtoCache");
    modelProtoCacheField.setAccessible(true);
    ((Map<?, ?>) modelProtoCacheField.get(null)).clear();

    ModelProto loadedProto3 = LocalTokenizerLoader.loadModelProto(tokenizerName);
    assertEquals(testProto, loadedProto3);
    verify(mockHttpClient, times(1)).newCall(any(Request.class));

    // Verify file was created in temp dir
    Path modelDir = Paths.get(tempDir.toString(), "vertexai_tokenizer_model");
    String filename = sha1(modelUrl);
    Path modelPath = modelDir.resolve(filename);
    assertTrue(Files.exists(modelPath));
    assertArrayEquals(protoBytes, Files.readAllBytes(modelPath));
  }

  @Test
  void loadModelProto_downloadFails() throws Exception {
    String tokenizerName = "test-tokenizer-fail";
    String modelUrl = "http://fake.url/model-fail";

    // Inject test tokenizer config
    Field tokenizersField = LocalTokenizerLoader.class.getDeclaredField("TOKENIZERS");
    tokenizersField.setAccessible(true);
    @SuppressWarnings("unchecked")
    Map<String, Object> tokenizers = (Map<String, Object>) tokenizersField.get(null);
    Map<String, Object> modifiableTokenizers = new HashMap<>(tokenizers);
    LocalTokenizerLoader.TokenizerConfig tokenizerConfig =
        new LocalTokenizerLoader.TokenizerConfig(modelUrl, "dummy-hash");
    modifiableTokenizers.put(tokenizerName, tokenizerConfig);
    setStatic(tokenizersField, Collections.unmodifiableMap(modifiableTokenizers));

    // Mock failed network response
    Response mockResponse =
        new Response.Builder()
            .request(new Request.Builder().url(modelUrl).build())
            .protocol(Protocol.HTTP_1_1)
            .code(404)
            .message("Not Found")
            .body(ResponseBody.create(null, ""))
            .build();
    when(mockCall.execute()).thenReturn(mockResponse);

    // Assert that an exception is thrown
    GenAiIOException exception =
        assertThrows(
            GenAiIOException.class, () -> LocalTokenizerLoader.loadModelProto(tokenizerName));
    assertTrue(exception.getMessage().contains("Failed to download tokenizer model"));
  }

  @Test
  void loadModelProto_hashMismatch() throws Exception {
    String tokenizerName = "test-tokenizer-hash-mismatch";
    String modelUrl = "http://fake.url/model-hash-mismatch";
    ModelProto testProto = ModelProto.newBuilder().build();
    byte[] protoBytes = testProto.toByteArray();
    String wrongHash = "wrong-hash";

    // Inject test tokenizer config
    Field tokenizersField = LocalTokenizerLoader.class.getDeclaredField("TOKENIZERS");
    tokenizersField.setAccessible(true);
    @SuppressWarnings("unchecked")
    Map<String, Object> tokenizers = (Map<String, Object>) tokenizersField.get(null);
    Map<String, Object> modifiableTokenizers = new HashMap<>(tokenizers);
    LocalTokenizerLoader.TokenizerConfig tokenizerConfig =
        new LocalTokenizerLoader.TokenizerConfig(modelUrl, wrongHash);
    modifiableTokenizers.put(tokenizerName, tokenizerConfig);
    setStatic(tokenizersField, Collections.unmodifiableMap(modifiableTokenizers));

    // Mock network response
    Response mockResponse =
        new Response.Builder()
            .request(new Request.Builder().url(modelUrl).build())
            .protocol(Protocol.HTTP_1_1)
            .code(200)
            .message("OK")
            .body(ResponseBody.create(null, protoBytes))
            .build();
    when(mockCall.execute()).thenReturn(mockResponse);

    // Assert that an exception is thrown
    GenAiIOException exception =
        assertThrows(
            GenAiIOException.class, () -> LocalTokenizerLoader.loadModelProto(tokenizerName));
    assertTrue(exception.getMessage().contains("Downloaded model file is corrupted"));
  }

  // Helper for hashing
  private String sha256(byte[] data) throws Exception {
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    byte[] hash = digest.digest(data);
    return bytesToHex(hash);
  }

  private String sha1(String input) throws Exception {
    MessageDigest digest = MessageDigest.getInstance("SHA-1");
    byte[] hash = digest.digest(input.getBytes());
    return bytesToHex(hash);
  }

  private String bytesToHex(byte[] hash) {
    StringBuilder hexString = new StringBuilder(2 * hash.length);
    for (byte b : hash) {
      String hex = Integer.toHexString(0xff & b);
      if (hex.length() == 1) {
        hexString.append('0');
      }
      hexString.append(hex);
    }
    return hexString.toString();
  }
}
