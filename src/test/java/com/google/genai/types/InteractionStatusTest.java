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

package com.google.genai.types;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class InteractionStatusTest {

  @Test
  public void testInteractionStatus() {
    InteractionStatus idle = new InteractionStatus(InteractionStatus.Known.IDLE);
    assertEquals("IDLE", idle.toString());
    assertEquals(InteractionStatus.Known.IDLE, idle.knownEnum());

    InteractionStatus idleFromString = new InteractionStatus("IDLE");
    assertEquals(idle, idleFromString);
    assertEquals(idle.hashCode(), idleFromString.hashCode());

    InteractionStatus inProgress = new InteractionStatus(InteractionStatus.Known.IN_PROGRESS);
    assertNotEquals(idle, inProgress);

    InteractionStatus unknown = new InteractionStatus("UNKNOWN_STATUS");
    assertEquals(InteractionStatus.Known.INTERACTION_STATUS_UNSPECIFIED, unknown.knownEnum());
    assertEquals("UNKNOWN_STATUS", unknown.toString());

    InteractionStatus requiresAction =
        new InteractionStatus(InteractionStatus.Known.REQUIRES_ACTION);
    assertEquals("REQUIRES_ACTION", requiresAction.toString());

    LiveServerContent content =
        LiveServerContent.builder()
            .interactionStatus(InteractionStatus.Known.IDLE)
            .build();
    assertEquals(InteractionStatus.Known.IDLE, content.interactionStatus().get().knownEnum());
    String json = content.toJson();
    LiveServerContent deserialized = LiveServerContent.fromJson(json);
    assertEquals(InteractionStatus.Known.IDLE, deserialized.interactionStatus().get().knownEnum());
  }
}
