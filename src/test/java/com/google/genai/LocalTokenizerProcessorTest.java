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

import com.google.common.collect.ImmutableList;
import com.google.genai.proto.SentencepieceModel.ModelProto;
import com.google.genai.proto.SentencepieceModel.NormalizerSpec;
import com.google.genai.proto.SentencepieceModel.TrainerSpec;
import com.google.genai.proto.SentencepieceModel.ModelProto.SentencePiece;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** Unit tests for {@link LocalTokenizerProcessor}. */
public class LocalTokenizerProcessorTest {

  private LocalTokenizerProcessor processor;

  @BeforeEach
  void setUp() {
    // Create a mock ModelProto for testing purposes.
    // The scores are set to prefer longer tokens.
    ModelProto testProto =
        ModelProto.newBuilder()
            .setTrainerSpec(
                TrainerSpec.newBuilder()
                    .setModelType(TrainerSpec.ModelType.BPE)
                    .setVocabSize(0)
                    .setUnkId(0)
                    .build())
            .setNormalizerSpec(
                NormalizerSpec.newBuilder()
                    .setAddDummyPrefix(false)
                    .setRemoveExtraWhitespaces(false)
                    .build())
            // 0: <unk>
            .addPieces(
                SentencePiece.newBuilder()
                    .setPiece("<unk>")
                    .setType(SentencePiece.Type.UNKNOWN)
                    .setScore(0.0f))
            // 1: <s>
            .addPieces(
                SentencePiece.newBuilder()
                    .setPiece("<s>")
                    .setType(SentencePiece.Type.CONTROL)
                    .setScore(0.0f))
            // 2: </s>
            .addPieces(
                SentencePiece.newBuilder()
                    .setPiece("</s>")
                    .setType(SentencePiece.Type.CONTROL)
                    .setScore(0.0f))
            // 3: " "
            .addPieces(SentencePiece.newBuilder().setPiece("▁").setScore(-3.0f))
            // 4: "h"
            .addPieces(SentencePiece.newBuilder().setPiece("h").setScore(-20.0f))
            // 5: "e"
            .addPieces(SentencePiece.newBuilder().setPiece("e").setScore(-21.0f))
            // 6: "l"
            .addPieces(SentencePiece.newBuilder().setPiece("l").setScore(-22.0f))
            // 7: "o"
            .addPieces(SentencePiece.newBuilder().setPiece("o").setScore(-23.0f))
            // 8: "w"
            .addPieces(SentencePiece.newBuilder().setPiece("w").setScore(-24.0f))
            // 9: "r"
            .addPieces(SentencePiece.newBuilder().setPiece("r").setScore(-25.0f))
            // 10: "d"
            .addPieces(SentencePiece.newBuilder().setPiece("d").setScore(-26.0f))
            // Merged pieces with better scores
            // 11: "he"
            .addPieces(SentencePiece.newBuilder().setPiece("he").setScore(-10.0f))
            // 12: "lo"
            .addPieces(SentencePiece.newBuilder().setPiece("lo").setScore(-10.0f))
            // 13: "llo"
            .addPieces(SentencePiece.newBuilder().setPiece("llo").setScore(-11.0f))
            // 14: "world"
            .addPieces(SentencePiece.newBuilder().setPiece("▁world").setScore(-5.0f))
            // 15: "hello"
            .addPieces(SentencePiece.newBuilder().setPiece("hello").setScore(-4.0f))
            .addPieces(SentencePiece.newBuilder().setPiece("▁w").setScore(-12.0f))
            .addPieces(SentencePiece.newBuilder().setPiece("▁wo").setScore(-12.0f))
            .addPieces(SentencePiece.newBuilder().setPiece("▁wor").setScore(-12.0f))
            .addPieces(SentencePiece.newBuilder().setPiece("ld").setScore(-12.0f))
            .build();

    processor = new LocalTokenizerProcessor(testProto);
  }

  @Test
  @DisplayName("Encode should correctly tokenize a simple string into whole words")
  void encode_simpleString_returnsCorrectTokens() throws Exception {
    String inputText = "hello world";
    List<Token> expectedTokens = ImmutableList.of(new Token("hello", 15), new Token("▁world", 14));

    List<Token> actualTokens = processor.encode(inputText);

    assertNotNull(actualTokens);
    assertEquals(expectedTokens, actualTokens);
  }

  @Test
  @DisplayName("Encode should correctly tokenize a string into subwords")
  void encode_stringWithSubwords_returnsCorrectTokens() throws Exception {
    // This test uses a different vocabulary where "hello" is not a single token.
    ModelProto subwordProto =
        ModelProto.newBuilder()
            .setTrainerSpec(
                TrainerSpec.newBuilder()
                    .setModelType(TrainerSpec.ModelType.BPE)
                    .setVocabSize(0)
                    .setUnkId(0)
                    .build())
            .setNormalizerSpec(
                NormalizerSpec.newBuilder()
                    .setAddDummyPrefix(false)
                    .setRemoveExtraWhitespaces(false)
                    .build())
            .addPieces(
                SentencePiece.newBuilder()
                    .setPiece("<unk>")
                    .setType(SentencePiece.Type.UNKNOWN)
                    .setScore(0.0f)) // 0
            .addPieces(
                SentencePiece.newBuilder()
                    .setPiece("<s>")
                    .setType(SentencePiece.Type.CONTROL)
                    .setScore(0.0f)) // 1
            .addPieces(
                SentencePiece.newBuilder()
                    .setPiece("</s>")
                    .setType(SentencePiece.Type.CONTROL)
                    .setScore(0.0f)) // 2
            .addPieces(SentencePiece.newBuilder().setPiece("h").setScore(-5.0f)) // 3
            .addPieces(SentencePiece.newBuilder().setPiece("e").setScore(-5.0f)) // 4
            .addPieces(SentencePiece.newBuilder().setPiece("l").setScore(-5.0f)) // 5
            .addPieces(SentencePiece.newBuilder().setPiece("o").setScore(-5.0f)) // 6
            .addPieces(SentencePiece.newBuilder().setPiece("he").setScore(-2.0f)) // 7
            .addPieces(SentencePiece.newBuilder().setPiece("lo").setScore(-2.0f)) // 8
            .addPieces(SentencePiece.newBuilder().setPiece("llo").setScore(-2.0f)) // 9
            .build();
    LocalTokenizerProcessor subwordProcessor = new LocalTokenizerProcessor(subwordProto);
    String inputText = "hello";
    List<Token> expectedTokens = ImmutableList.of(new Token("he", 7), new Token("llo", 9));

    List<Token> actualTokens = subwordProcessor.encode(inputText);

    assertNotNull(actualTokens);
    assertEquals(expectedTokens, actualTokens);
  }

  @Test
  @DisplayName("Encode should handle unknown characters by returning the unknown token")
  void encode_unknownCharacters_returnsUnknownToken() throws Exception {
    String inputText = "z";
    // Assuming byte fallback is not enabled in the test proto, "z" is unknown.
    // The piece is the unknown character itself, and the ID is for the <unk> token
    // (ID 0).
    List<Token> expectedTokens = ImmutableList.of(new Token("z", 0));

    List<Token> actualTokens = processor.encode(inputText);

    assertNotNull(actualTokens);
    assertEquals(expectedTokens, actualTokens);
  }

  @Test
  @DisplayName("Decode should correctly reconstruct a string from token IDs")
  void decodeIds_simpleList_returnsCorrectString() {
    List<Integer> ids = Arrays.asList(15, 14);
    String expectedText = "hello world";

    String actualText = processor.decodeIds(ids);

    assertEquals(expectedText, actualText);
  }

  @Test
  @DisplayName("Decode should correctly reconstruct a string from subword token IDs")
  void decodeIds_withSubwords_returnsCorrectString() {
    List<Integer> ids = Arrays.asList(11, 13);
    String expectedText = "hello";

    String actualText = processor.decodeIds(ids);

    assertEquals(expectedText, actualText);
  }

  @Test
  @DisplayName("Decode should ignore control characters")
  void decodeIds_withControlCharacters_ignoresThem() {
    // IDs 1 (<s>) and 2 (</s>) are control characters.
    List<Integer> ids = Arrays.asList(1, 15, 14, 2);
    String expectedText = "hello world";

    String actualText = processor.decodeIds(ids);

    assertEquals(expectedText, actualText);
  }

  @Test
  @DisplayName("Encode and decode should be consistent")
  void encodeAndDecode_areConsistent() throws Exception {
    String inputText = "hello world";

    List<Token> tokens = processor.encode(inputText);
    List<Integer> ids = tokens.stream().map(Token::id).collect(Collectors.toList());
    String decodedText = processor.decodeIds(ids);

    assertEquals(inputText, decodedText);
  }
}
