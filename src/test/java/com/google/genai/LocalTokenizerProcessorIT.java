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

import static org.junit.jupiter.api.Assertions.*;

import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LocalTokenizerProcessorIT {

  private LocalTokenizerProcessor processor;

  @BeforeAll
  void setUp() throws Exception {
    processor = LocalTokenizerLoader.getSentencePiece("gemma2");
  }

  @ParameterizedTest(name = "{index}")
  @MethodSource("encodeIDsTestCases")
  void testEncodeIDs(String inputText, List<Integer> expectedTokens) throws Exception {
    List<Token> actualPieces = processor.encode(inputText);
    assertNotNull(actualPieces, "Encoded result should not be null.");
    assertEquals(
        expectedTokens,
        actualPieces.stream().map(Token::id).collect(Collectors.toList()),
        String.format("Encoding '%s' did not produce the expected token IDs", inputText));
  }

  static Stream<Arguments> encodeIDsTestCases() {
    return Stream.of(
        Arguments.of("Hello world", Arrays.asList(4521, 2134)),
        Arguments.of("12345", Arrays.asList(235274, 235284, 235304, 235310, 235308)),
        Arguments.of("  ", Arrays.asList(139)),
        Arguments.of("   ", Arrays.asList(140)),
        Arguments.of("        ", Arrays.asList(145)),
        Arguments.of("ҔӌԐڎ", Arrays.asList(427, 365, 428, 357, 429, 361, 435, 359)),
        Arguments.of(" <mask>  <pad>", Arrays.asList(235248, 4, 139, 235322, 8939, 235313)),
        Arguments.of("<table><th></th></table>", Arrays.asList(169, 175, 183, 177)),
        Arguments.of("one line\nand another line", Arrays.asList(785, 2017, 108, 639, 2550, 2017)),
        Arguments.of(
            "Language: English\r\n\r\nCredits: Produced by David Widger\r\n",
            Arrays.asList(
                14357, 235292, 4645, 235316, 108, 235316, 108, 34711, 235292, 99662, 731, 6046,
                37303, 1197, 235316, 108)),
        Arguments.of("Bienvenido a este proyecto", Arrays.asList(176831, 476, 4004, 25431)),
        Arguments.of(
            "अस्मिन् परियोजनायां स्वागतम्",
            Arrays.asList(
                236088, 22740, 212361, 18029, 14480, 19900, 146166, 6751, 235563, 56545, 44071,
                235550, 26989)),
        Arguments.of(
            "if allow == true { return x;} else {return x+y;}",
            Arrays.asList(
                648, 2765, 1159, 1382, 612, 2203, 1141, 22505, 1354, 612, 773, 1141, 235340, 235267,
                22505)));
  }

  @ParameterizedTest(name = "{index} - input: {0}")
  @MethodSource("encodeTextsTestCases")
  void testEncodeTexts(String inputText, List<Token> expectedTokens) throws Exception {
    List<Token> actualTokens = processor.encode(inputText);

    assertNotNull(actualTokens, "Encoded result should not be null.");
    assertEquals(
        expectedTokens,
        actualTokens,
        String.format("Encoding '%s' did not produce the expected tokens", inputText));
  }

  static Stream<Arguments> encodeTextsTestCases() {
    return Stream.of(
        Arguments.of(
            "hi <td> bye",
            ImmutableList.of(
                new Token("hi", 544),
                new Token("▁", 235248),
                new Token("<td>", 176),
                new Token("▁bye", 44788))),
        Arguments.of(
            "hiƻ <td>🤨there ⇲bob, สวัสดี",
            ImmutableList.of(
                new Token("hi", 544),
                new Token("<0xC6>", 415),
                new Token("<0xBB>", 404),
                new Token("▁", 235248),
                new Token("<td>", 176),
                new Token("🤨", 241847),
                new Token("there", 11048),
                new Token("▁", 235248),
                new Token("⇲", 248372),
                new Token("bob", 26242),
                new Token(",", 235269),
                new Token("▁ส", 12515),
                new Token("วัส", 151622),
                new Token("ดี", 28890))));
  }

  @ParameterizedTest(name = "IDs: {0} -> Expected: \"{1}\"")
  @MethodSource("decodeIdsTestCases")
  void testDecodeIds(List<Integer> ids, String expectedText) {
    String actualText = processor.decodeIds(ids);
    assertEquals(
        expectedText,
        actualText,
        String.format("Decoding IDs %s did not produce the expected text", ids));
  }

  static Stream<Arguments> decodeIdsTestCases() {
    return Stream.of(
        Arguments.of(Arrays.asList(17534, 2134), "hello world"),
        Arguments.of(
            Arrays.asList(427, 365, 428, 357, 29422, 1653, 427, 365, 428, 357), "Ҕӌnever againҔӌ"),
        Arguments.of(Arrays.asList(785, 2017, 108, 639, 2550, 2017), "one line\nand another line"),
        Arguments.of(Arrays.asList(1001, 1002, 1003, 1004), "buark}) res"),
        Arguments.of(
            Arrays.asList(111001, 111002, 111003, 111004), " Wichita EducaçãoVocabulary天堂"),
        Arguments.of(Arrays.asList(139), "  "),
        Arguments.of(Arrays.asList(140), "   "),
        Arguments.of(Arrays.asList(145), "        "),
        Arguments.of(Arrays.asList(441, 401, 387), "ส"),
        Arguments.of(Arrays.asList(411, 380), "£"),

        // control IDs
        Arguments.of(Arrays.asList(2, 411, 380), "£"),
        Arguments.of(Arrays.asList(1, 2, 411, 380), "£"),
        Arguments.of(Arrays.asList(2, 411, 380, 0, 1, 2, 0), "£"),

        // unknown (id=3)
        Arguments.of(Arrays.asList(3, 411, 380), " ⁇ £"), // " ⁇ " is U+0020 U+2047
        Arguments.of(Arrays.asList(3, 3, 1000, 3), " ⁇  ⁇ ew ⁇ "),

        // invalid bytes for UTF-8, produce "invalid unicode" (U+FFFD)
        Arguments.of(Arrays.asList(349, 349, 349), "���"),
        Arguments.of(Arrays.asList(800, 348, 500, 348), "sed�it�"));
  }
}
