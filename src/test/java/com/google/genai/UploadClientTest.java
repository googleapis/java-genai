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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.google.genai.fakes.FakeUploadApiClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import org.apache.http.HttpEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UploadClientTest {

  private static final FakeUploadApiClient API_CLIENT = new FakeUploadApiClient();
  private static final String UPLOAD_URL = "upload-url";
  private static final String FILE_PATH = "./test-file.txt";
  private static final int CHUNK_SIZE = 10;
  private static final int TEST_BYTES_SIZE = 95;
  private byte[] testBytes;

  @BeforeEach
  public void setUp() {
    API_CLIENT.files.clear();
    File file = new File(FILE_PATH);
    Random random = new Random();
    testBytes = new byte[TEST_BYTES_SIZE];
    random.nextBytes(testBytes);
    try {
      FileOutputStream fos = new FileOutputStream(file);
      fos.write(testBytes);
      fos.close();
    } catch (IOException e) {
      System.err.println("An error occurred while writing to the file: " + e.getMessage());
    }
  }

  @Test
  public void upload_file_success() throws IOException {
    UploadClient uploadClient = new UploadClient(API_CLIENT, CHUNK_SIZE);
    HttpEntity entity = uploadClient.upload(UPLOAD_URL, FILE_PATH);
    assertBytesEqual(testBytes, API_CLIENT.files.get(UPLOAD_URL).uploadedBytes);
    assertNotNull(entity);
  }

  @Test
  public void upload_bytes_success() throws IOException {
    UploadClient uploadClient = new UploadClient(API_CLIENT, CHUNK_SIZE);
    HttpEntity entity = uploadClient.upload(UPLOAD_URL, testBytes);
    assertBytesEqual(testBytes, API_CLIENT.files.get(UPLOAD_URL).uploadedBytes);
    assertNotNull(entity);
  }

  @Test
  public void upload_stream_success() throws IOException {
    UploadClient uploadClient = new UploadClient(API_CLIENT, CHUNK_SIZE);
    HttpEntity entity;
    try (InputStream inputStream = new FileInputStream(FILE_PATH)) {
      entity = uploadClient.upload(UPLOAD_URL, inputStream, testBytes.length);
    }
    assertBytesEqual(testBytes, API_CLIENT.files.get(UPLOAD_URL).uploadedBytes);
    assertNotNull(entity);
  }

  @Test
  public void upload_file_with_retriable_error_success() throws IOException {
    API_CLIENT.makeFileUploadFail(UPLOAD_URL, 1);
    UploadClient uploadClient = new UploadClient(API_CLIENT, CHUNK_SIZE);
    HttpEntity entity = uploadClient.upload(UPLOAD_URL, FILE_PATH);
    assertBytesEqual(testBytes, API_CLIENT.files.get(UPLOAD_URL).uploadedBytes);
    assertNotNull(entity);
  }

  @Test
  public void upload_file_retries_exhausted_throws() throws IOException {
    API_CLIENT.makeFileUploadFail(UPLOAD_URL, 4);
    UploadClient uploadClient = new UploadClient(API_CLIENT, CHUNK_SIZE);
    assertThrows(IllegalStateException.class, () -> uploadClient.upload(UPLOAD_URL, FILE_PATH));
  }

  private void assertBytesEqual(byte[] expected, ArrayList<Byte> actual) {
    assertEquals(expected.length, actual.size());
    for (int i = 0; i < expected.length; i++) {
      assertEquals(expected[i], actual.get(i));
    }
  }

  @AfterEach
  public void tearDown() {
    File fileToDelete = new File(FILE_PATH);
    if (fileToDelete.exists()) {
      fileToDelete.delete();
    }
  }
}
