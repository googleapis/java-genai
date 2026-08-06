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

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class TestUtils {
  static final ScheduledExecutorService sharedScheduler =
      Executors.newSingleThreadScheduledExecutor(
          r -> {
            Thread t = new Thread(r, "test-retry-scheduler");
            t.setDaemon(true);
            return t;
          });

  static final String API_KEY = "api-key";
  static final String PROJECT = "project";
  static final String LOCATION = "location";

  private TestUtils() {}

  /** Creates a client given the vertexAI and replayId. Can be used in replay tests. */
  public static Client createClient(boolean vertexAI, String replayId) {
    String clientMode = System.getenv("GOOGLE_GENAI_CLIENT_MODE");
    DebugConfig debugConfig =
        new DebugConfig(
            clientMode == null ? "replay" : clientMode,
            "",
            System.getenv("GOOGLE_GENAI_REPLAYS_DIRECTORY"));

    Client.Builder clientBuilder =
        Client.builder()
            .vertexAI(vertexAI)
            .debugConfig(debugConfig)
            .asyncRetryScheduler(sharedScheduler);

    if (vertexAI) {
      clientBuilder.project(PROJECT).location(LOCATION);
    } else {
      clientBuilder.apiKey(API_KEY);
    }
    Client client = clientBuilder.build();

    if (client.clientMode().equals("replay")) {
      client.setReplayId(replayId);
    }
    return client;
  }
}
