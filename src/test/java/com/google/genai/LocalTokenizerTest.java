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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.genai.proto.SentencepieceModel.ModelProto;
import com.google.genai.proto.SentencepieceModel.ModelProto.SentencePiece;
import com.google.genai.types.Content;
import com.google.genai.types.CountTokensConfig;
import com.google.genai.types.CountTokensResult;
import com.google.genai.types.FunctionDeclaration;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerationConfig;
import com.google.genai.types.Part;
import com.google.genai.types.Schema;
import com.google.genai.types.Tool;
import com.google.genai.types.ComputeTokensResult;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class LocalTokenizerTest {

  private static final String TEST_MODEL_NAME = "gemini-pro";

  @Mock private LocalTokenizerProcessor mockedProcessor;
  @Mock private ModelProto mockedProto;

  private LocalTokenizer tokenizer;
  private AutoCloseable closeable;

  @BeforeEach
  void setUp() {
    closeable = MockitoAnnotations.openMocks(this);
    tokenizer = new LocalTokenizer(mockedProto, mockedProcessor);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void constructor_unsupportedModel_throwsException() {
    String unsupportedModel = "unsupported-model";
    try (MockedStatic<LocalTokenizerLoader> mockedLoader =
        Mockito.mockStatic(LocalTokenizerLoader.class)) {
      mockedLoader
          .when(() -> LocalTokenizerLoader.getTokenizerName(unsupportedModel))
          .thenThrow(
              new IllegalArgumentException("Model " + unsupportedModel + " is not supported."));

      IllegalArgumentException exception =
          assertThrows(IllegalArgumentException.class, () -> new LocalTokenizer(unsupportedModel));
      assertTrue(exception.getMessage().contains("is not supported"));
    }
  }

  @Test
  void countTokens_string() throws Exception {
    String text = "hello world!";
    when(mockedProcessor.encode(text))
        .thenReturn(ImmutableList.of(new Token("a", 1), new Token("b", 2)));

    CountTokensResult response = tokenizer.countTokens(text, null);

    assertEquals(2, response.totalTokens().get());
  }

  @Test
  void countTokens_content() throws Exception {
    String text = "hello world!";
    Content content = Content.fromParts(Part.fromText(text));
    when(mockedProcessor.encode(text))
        .thenReturn(ImmutableList.of(new Token("a", 1), new Token("b", 2)));

    CountTokensResult response = tokenizer.countTokens(content, null);
    assertEquals(2, response.totalTokens().get());
  }

  @Test
  void countTokens_withConfig() throws Exception {
    Content systemInstruction = Content.fromParts(Part.fromText("hello!"));
    Tool tool =
        Tool.builder()
            .functionDeclarations(
                FunctionDeclaration.builder()
                    .name("hello")
                    .description(" world")
                    .parameters(
                        Schema.builder()
                            .properties(
                                ImmutableMap.of(
                                    "hello", Schema.builder().description("!").build()))))
            .build();
    Schema responseSchema = Schema.builder().description("hello world").build();
    CountTokensConfig config =
        CountTokensConfig.builder()
            .systemInstruction(systemInstruction)
            .tools(tool)
            .generationConfig(GenerationConfig.builder().responseSchema(responseSchema).build())
            .build();
    List<Content> contents = ImmutableList.of(Content.fromParts(Part.fromText("hello")));

    when(mockedProcessor.encode("hello")).thenReturn(ImmutableList.of(new Token("hello", 1)));
    when(mockedProcessor.encode("hello!"))
        .thenReturn(ImmutableList.of(new Token("hello", 1), new Token("!", 2)));
    when(mockedProcessor.encode("hello world"))
        .thenReturn(ImmutableList.of(new Token("hello world", 3)));
    when(mockedProcessor.encode(" world")).thenReturn(ImmutableList.of(new Token(" world", 4)));
    when(mockedProcessor.encode("!")).thenReturn(ImmutableList.of(new Token("!", 5)));

    CountTokensResult response = tokenizer.countTokens(contents, config);

    assertEquals(8, response.totalTokens().get());
  }

  @Test
  void countTokens_withFunctionCalling() throws Exception {
    Content content =
        Content.fromParts(
            Part.fromFunctionCall("hello", ImmutableMap.of("key", " world")),
            Part.fromFunctionResponse("hello", ImmutableMap.of("key", "!")));

    when(mockedProcessor.encode("hello")).thenReturn(ImmutableList.of(new Token("hello", 1)));
    when(mockedProcessor.encode("key"))
        .thenReturn(ImmutableList.of(new Token("k", 2), new Token("e", 3), new Token("y", 4)));
    when(mockedProcessor.encode(" world")).thenReturn(ImmutableList.of(new Token(" world", 5)));
    when(mockedProcessor.encode("!")).thenReturn(ImmutableList.of(new Token("!", 6)));

    CountTokensResult response = tokenizer.countTokens(ImmutableList.of(content), null);

    assertEquals(10, response.totalTokens().get());
  }

  @Test
  void countTokens_unsupportedPart_throwsException() {
    Content content = Content.fromParts(Part.fromBytes(new byte[0], "image/png"));

    IllegalArgumentException e =
        assertThrows(IllegalArgumentException.class, () -> tokenizer.countTokens(content, null));
    assertEquals("LocalTokenizers do not support non-text content types.", e.getMessage());
  }

  @Test
  void computeTokens_string() throws Exception {
    String text = "hello world!";
    when(mockedProcessor.encode(text))
        .thenReturn(ImmutableList.of(new Token("hello world", 4), new Token("!", 3)));
    when(mockedProto.getPieces(anyInt()))
        .thenReturn(SentencePiece.newBuilder().setType(SentencePiece.Type.NORMAL).build());

    ComputeTokensResult response = tokenizer.computeTokens(text);

    assertEquals(1, response.tokensInfo().get().size());
    assertEquals(2, response.tokensInfo().get().get(0).tokenIds().get().size());
    assertEquals(4L, response.tokensInfo().get().get(0).tokenIds().get().get(0));
    assertEquals(3L, response.tokensInfo().get().get(0).tokenIds().get().get(1));
    assertEquals(
        "hello world", new String(response.tokensInfo().get().get(0).tokens().get().get(0)));
    assertEquals("!", new String(response.tokensInfo().get().get(0).tokens().get().get(1)));
  }

  @Test
  void computeTokens_withBytePiece() throws Exception {
    String text = "A";
    when(mockedProcessor.encode(text)).thenReturn(ImmutableList.of(new Token("<0x41>", 5)));
    when(mockedProto.getPieces(5))
        .thenReturn(SentencePiece.newBuilder().setType(SentencePiece.Type.BYTE).build());

    ComputeTokensResult response = tokenizer.computeTokens(text);

    assertEquals(1, response.tokensInfo().get().size());
    assertEquals(1, response.tokensInfo().get().get(0).tokenIds().get().size());
    assertEquals(5L, response.tokensInfo().get().get(0).tokenIds().get().get(0));
    byte[] expectedBytes = new byte[] {(byte) 0x41};
    assertTrue(
        Arrays.equals(expectedBytes, response.tokensInfo().get().get(0).tokens().get().get(0)));
  }
}
