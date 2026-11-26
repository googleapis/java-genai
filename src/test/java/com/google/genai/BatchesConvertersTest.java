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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.genai.types.BatchJob;
import org.junit.jupiter.api.Test;

public class BatchesConvertersTest {

  private static BatchJob batchJobFromMldev(String response) {
    // The converter does not use the API client.
    Batches batches = new Batches(null);
    return BatchJob.fromJson(
        batches.batchJobFromMldev(JsonSerializable.stringToJsonNode(response), null).toString());
  }

  /** The input file name in the Gemini API response metadata populates src.fileName. */
  @Test
  public void batchJobFromMldev_setsSrcFileName() {
    BatchJob job =
        batchJobFromMldev(
            "{\"name\": \"batches/123\", \"metadata\": {\"state\": \"BATCH_STATE_PENDING\","
                + " \"inputConfig\": {\"fileName\": \"files/abc\"}}}");

    assertTrue(job.src().isPresent());
    assertEquals("files/abc", job.src().get().fileName().orElse(null));
    assertFalse(job.src().get().inlinedRequests().isPresent());
  }

  /** Inline requests in the Gemini API response are not mapped to src. */
  @Test
  public void batchJobFromMldev_inlinedRequests_leavesSrcUnset() {
    BatchJob job =
        batchJobFromMldev(
            "{\"name\": \"batches/123\", \"metadata\": {\"state\": \"BATCH_STATE_PENDING\","
                + " \"inputConfig\": {\"requests\": {\"requests\": [{\"request\": {\"contents\":"
                + " [{\"parts\": [{\"text\": \"Hello\"}]}]}}]}}}}");

    assertEquals("batches/123", job.name().orElse(null));
    assertFalse(job.src().isPresent());
  }
}
