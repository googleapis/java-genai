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

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

public class AutoCloseableExecutionTest {

  public static class SampleApp {
    public static void main(String[] args) {
      System.out.println("Starting SampleApp...");
      try (Client client = Client.builder().apiKey("dummy-key").vertexAI(false).build()) {
        System.out.println("Client created and will close on exit.");
      }
      System.out.println("SampleApp main() finished.");
    }
  }

  @Test
  public void testGenAiImplementsAutoCloseable() throws Exception {
    Instant start = Instant.now();
    try (com.google.genai.gaos.GenAI genai = com.google.genai.gaos.GenAI.builder().build()) {
      assertTrue(genai instanceof AutoCloseable);
    }
    Duration elapsed = Duration.between(start, Instant.now());
    assertTrue(elapsed.toMillis() < 30000, "Close took too long: " + elapsed.toMillis() + "ms");
  }

  @Test
  public void testClientImplementsAutoCloseable() throws Exception {
    Instant start = Instant.now();
    try (Client client = Client.builder().apiKey("test-key").vertexAI(false).build()) {
      assertTrue(client instanceof AutoCloseable);
    }
    Duration elapsed = Duration.between(start, Instant.now());
    assertTrue(elapsed.toMillis() < 30000, "Close took too long: " + elapsed.toMillis() + "ms");
  }

  @Test
  public void testSpeakeasyHTTPClientClosesDispatcherImmediately() throws Exception {
    Instant start = Instant.now();
    try (com.google.genai.gaos.utils.SpeakeasyHTTPClient httpClient =
        new com.google.genai.gaos.utils.SpeakeasyHTTPClient()) {
      assertTrue(httpClient instanceof AutoCloseable);
    }
    Duration elapsed = Duration.between(start, Instant.now());
    assertTrue(elapsed.toMillis() < 30000, "Close took too long: " + elapsed.toMillis() + "ms");
  }

  @Test
  public void testForkedJvmExitsImmediatelyWithout60sTimeout() throws Exception {
    String javaHome = System.getProperty("java.home");
    String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
    String classpath = System.getProperty("java.class.path");

    ProcessBuilder pb =
        new ProcessBuilder(javaBin, "-cp", classpath, SampleApp.class.getName());
    pb.redirectErrorStream(true);

    Instant start = Instant.now();
    Process process = pb.start();
    boolean exited = process.waitFor(30, TimeUnit.SECONDS);
    Duration elapsed = Duration.between(start, Instant.now());

    assertTrue(exited, "Process hung and did not exit within 30 seconds!");
    assertEquals(0, process.exitValue(), "Process exited with non-zero exit code");
    assertTrue(
        elapsed.toMillis() < 30000,
        "Process exit took " + elapsed.toMillis() + "ms (should be < 30000ms, not 60000ms)");
    System.out.println("Forked JVM exited cleanly in " + elapsed.toMillis() + "ms.");
  }
}
