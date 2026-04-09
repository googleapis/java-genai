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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.genai.types.ContentReferenceImage;
import com.google.genai.types.ControlReferenceImage;
import com.google.genai.types.MaskReferenceImage;
import com.google.genai.types.RawReferenceImage;
import com.google.genai.types.ReferenceImage;
import com.google.genai.types.StyleReferenceImage;
import com.google.genai.types.SubjectReferenceImage;
import java.io.IOException;

public final class TestUtils {
  static final String API_KEY = "api-key";
  static final String PROJECT = "project";
  static final String LOCATION = "location";

  private static ObjectMapper testObjectMapper;

  public static ObjectMapper getTestObjectMapper() {
    if (testObjectMapper == null) {
      testObjectMapper = JsonSerializable.objectMapper.copy();
      SimpleModule customModule = new SimpleModule();
      customModule.addDeserializer(ReferenceImage.class, new ReferenceImageDeserializer());
      testObjectMapper.registerModule(customModule);
    }
    return testObjectMapper;
  }

  private static class ReferenceImageDeserializer extends StdDeserializer<ReferenceImage> {
    public ReferenceImageDeserializer() {
      this(null);
    }

    public ReferenceImageDeserializer(Class<?> vc) {
      super(vc);
    }

    @Override
    public ReferenceImage deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = jp.getCodec().readTree(jp);
      if (node.isObject()) {
        com.fasterxml.jackson.databind.node.ObjectNode objNode =
            (com.fasterxml.jackson.databind.node.ObjectNode) node;
        if (objNode.has("maskImageConfig")) {
          objNode.set("config", objNode.get("maskImageConfig"));
        }
        if (objNode.has("styleImageConfig")) {
          objNode.set("config", objNode.get("styleImageConfig"));
        }
        if (objNode.has("controlImageConfig")) {
          objNode.set("config", objNode.get("controlImageConfig"));
        }
        if (objNode.has("subjectImageConfig")) {
          objNode.set("config", objNode.get("subjectImageConfig"));
        }
        if (objNode.has("contentImageConfig")) {
          objNode.set("config", objNode.get("contentImageConfig"));
        }
      }

      if (node.has("referenceType")) {
        String type = node.get("referenceType").asText();
        if ("REFERENCE_TYPE_RAW".equals(type)) {
          return jp.getCodec().treeToValue(node, RawReferenceImage.class);
        } else if ("REFERENCE_TYPE_MASK".equals(type)) {
          return jp.getCodec().treeToValue(node, MaskReferenceImage.class);
        } else if ("REFERENCE_TYPE_CONTROL".equals(type)) {
          return jp.getCodec().treeToValue(node, ControlReferenceImage.class);
        } else if ("REFERENCE_TYPE_STYLE".equals(type)) {
          return jp.getCodec().treeToValue(node, StyleReferenceImage.class);
        } else if ("REFERENCE_TYPE_SUBJECT".equals(type)) {
          return jp.getCodec().treeToValue(node, SubjectReferenceImage.class);
        } else if ("REFERENCE_TYPE_CONTENT".equals(type)) {
          return jp.getCodec().treeToValue(node, ContentReferenceImage.class);
        }
      }
      throw new IOException("Unknown or missing referenceType for ReferenceImage");
    }
  }

  private TestUtils() {}

  /** Creates a client given the vertexAI and replayId. Can be used in replay tests. */
  public static Client createClient(boolean vertexAI, String replayId) {
    String clientMode = System.getenv("GOOGLE_GENAI_CLIENT_MODE");
    DebugConfig debugConfig =
        new DebugConfig(
            clientMode == null ? "replay" : clientMode,
            "",
            System.getenv("GOOGLE_GENAI_REPLAYS_DIRECTORY"));

    Client.Builder clientBuilder = Client.builder().vertexAI(vertexAI).debugConfig(debugConfig);

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
