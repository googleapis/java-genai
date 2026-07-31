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

import static com.google.common.truth.Truth.assertThat;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ClientAndroidTest {
  private static final String API_KEY = "api-key";
  private static final String PROJECT = "project";
  private static final String LOCATION = "location";

  @Test
  public void testInitClientFromBuilder_mldev() {
    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    try {
      Client client =
          Client.builder().apiKey(API_KEY).vertexAI(false).asyncRetryScheduler(scheduler).build();

      assertThat(client.apiKey()).isEqualTo(API_KEY);
      assertThat(client.project()).isNull();
      assertThat(client.location()).isNull();
      assertThat(client.vertexAI()).isFalse();
      assertThat(client.baseUrl().orElse(null))
          .isEqualTo("https://generativelanguage.googleapis.com");
    } finally {
      scheduler.shutdown();
    }
  }

  @Test
  public void testInitClientFromBuilder_vertex() {
    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    try {
      Client client =
          Client.builder()
              .apiKey(API_KEY)
              .project(PROJECT)
              .location(LOCATION)
              .vertexAI(true)
              .asyncRetryScheduler(scheduler)
              .build();

      assertThat(client.apiKey()).isEqualTo(API_KEY);
      assertThat(client.project()).isEqualTo(PROJECT);
      assertThat(client.location()).isEqualTo(LOCATION);
      assertThat(client.vertexAI()).isTrue();
      assertThat(client.baseUrl().orElse(null))
          .isEqualTo("https://location-aiplatform.googleapis.com");
    } finally {
      scheduler.shutdown();
    }
  }
}
