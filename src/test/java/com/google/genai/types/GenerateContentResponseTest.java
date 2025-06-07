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

package com.google.genai.types;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.google.common.collect.ImmutableList;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

public class GenerateContentResponseTest {

  private static final FunctionCall FUNCTION_CALL_1 =
      FunctionCall.builder()
          .name("funcCall1")
          .args(
              new HashMap<String, Object>() {
                {
                  put("key1", "val1");
                }
              })
          .build();
  private static final FunctionCall FUNCTION_CALL_2 =
      FunctionCall.builder()
          .name("funcCall2")
          .args(
              new HashMap<String, Object>() {
                {
                  put("key2", "val2");
                }
              })
          .build();

  private static final Part PART_1 = Part.builder().text("text1").build();
  private static final Part PART_2 = Part.builder().text("text2").build();
  private static final Part PART_FUNCTION_CALL_1 =
      Part.builder().functionCall(FUNCTION_CALL_1).thought(false).build();
  private static final Part PART_FUNCTION_CALL_2 =
      Part.builder().functionCall(FUNCTION_CALL_2).thought(false).build();
  private static final Part PART_WITH_THOUGHT = Part.builder().text("text3").thought(true).build();
  private static final Part PART_WITH_EXECUTABLE_CODE =
      Part.builder()
          .executableCode(
              ExecutableCode.builder().code("executableCode").language(Language.Known.PYTHON))
          .build();
  private static final Part PART_WITH_CODE_EXECUTION_RESULT =
      Part.builder()
          .codeExecutionResult(
              CodeExecutionResult.builder()
                  .output("codeExecutionResult")
                  .outcome(Outcome.Known.OUTCOME_OK))
          .build();

  private static final Content CONTENT_1 = Content.builder().parts(PART_1).build();
  private static final Content CONTENT_2 = Content.builder().parts(PART_2).build();
  private static final Content CONTENT_WITH_MULTIPLE_PARTS =
      Content.builder().parts(PART_1, PART_2).build();
  private static final Content CONTENT_WITH_FUNCTION_CALLS =
      Content.builder().parts(PART_FUNCTION_CALL_1, PART_FUNCTION_CALL_2).build();
  private static final Content CONTENT_WITH_MIXED_PARTS =
      Content.builder().parts(PART_1, PART_FUNCTION_CALL_1).build();
  private static final Content CONTENT_WITH_EMPTY_PARTS =
      Content.builder().parts(ImmutableList.of()).build();
  private static final Content CONTENT_WITH_THOUGHT =
      Content.builder().parts(PART_1, PART_WITH_THOUGHT).build();
  private static final Content CONTENT_WITH_EXECUTABLE_CODE =
      Content.builder().parts(PART_WITH_EXECUTABLE_CODE).build();
  private static final Content CONTENT_WITH_EXECUTABLE_CODE_AND_TEXT =
      Content.builder().parts(PART_WITH_EXECUTABLE_CODE, PART_1).build();
  private static final Content CONTENT_WITH_CODE_EXECUTION_RESULT =
      Content.builder().parts(PART_WITH_CODE_EXECUTION_RESULT).build();
  private static final Content CONTENT_WITH_CODE_EXECUTION_RESULT_AND_TEXT =
      Content.builder().parts(PART_WITH_CODE_EXECUTION_RESULT, PART_1).build();

  private static final Candidate CANDIDATE_1 =
      Candidate.builder().content(CONTENT_1).finishReason(FinishReason.Known.STOP).build();
  private static final Candidate CANDIDATE_2 =
      Candidate.builder().content(CONTENT_2).finishReason(FinishReason.Known.STOP).build();
  private static final Candidate CANDIDATE_WITH_EMPTY_PARTS =
      Candidate.builder()
          .content(CONTENT_WITH_EMPTY_PARTS)
          .finishReason(FinishReason.Known.STOP)
          .build();
  private static final Candidate CANDIDATE_WITH_THOUGHT =
      Candidate.builder()
          .content(CONTENT_WITH_THOUGHT)
          .finishReason(FinishReason.Known.STOP)
          .build();
  private static final Candidate CANDIDATE_WITH_MULTIPLE_PARTS =
      Candidate.builder()
          .content(CONTENT_WITH_MULTIPLE_PARTS)
          .finishReason(FinishReason.Known.STOP)
          .build();
  private static final Candidate CANDIDATE_WITH_FUNCTION_CALLS =
      Candidate.builder()
          .content(CONTENT_WITH_FUNCTION_CALLS)
          .finishReason(FinishReason.Known.STOP)
          .build();
  private static final Candidate CANDIDATE_WITH_MIXED_PARTS =
      Candidate.builder()
          .content(CONTENT_WITH_MIXED_PARTS)
          .finishReason(FinishReason.Known.STOP)
          .build();
  private static final Candidate CANDIDATE_WITH_UNEXPECTED_FINISH_REASON =
      Candidate.builder().content(CONTENT_1).finishReason(FinishReason.Known.SAFETY).build();

  @Test
  public void testParts_EmptyCandidates() {
    GenerateContentResponse response = GenerateContentResponse.builder().build();

    assertEquals(null, response.parts());
  }

  @Test
  public void testParts_UnexpectedFinishReason() {
    GenerateContentResponse response =
        GenerateContentResponse.builder()
            .candidates(CANDIDATE_WITH_UNEXPECTED_FINISH_REASON)
            .build();

    Exception e = assertThrows(IllegalArgumentException.class, () -> response.parts());
    assertEquals("The response finished unexpectedly with reason SAFETY.", e.getMessage());
  }

  @Test
  public void testParts_MultipleCandidates() {
    GenerateContentResponse response =
        GenerateContentResponse.builder().candidates(CANDIDATE_1, CANDIDATE_2).build();

    // Only the first candidate is used.
    assertEquals(ImmutableList.of(PART_1), response.parts());
  }

  @Test
  public void testParts_SinglePart() {
    GenerateContentResponse response =
        GenerateContentResponse.builder().candidates(CANDIDATE_1).build();
    assertEquals(ImmutableList.of(PART_1), response.parts());
  }

  @Test
  public void testParts_MultipleParts() {
    GenerateContentResponse response =
        GenerateContentResponse.builder().candidates(CANDIDATE_WITH_MULTIPLE_PARTS).build();

    assertEquals(ImmutableList.of(PART_1, PART_2), response.parts());
  }

  @Test
  public void testText_EmptyCandidates() {
    GenerateContentResponse response = GenerateContentResponse.builder().build();

    assertEquals(null, response.text());
  }

  @Test
  public void testText_UnexpectedFinishReason() {
    GenerateContentResponse response =
        GenerateContentResponse.builder()
            .candidates(CANDIDATE_WITH_UNEXPECTED_FINISH_REASON)
            .build();

    Exception e = assertThrows(IllegalArgumentException.class, () -> response.text());
    assertEquals("The response finished unexpectedly with reason SAFETY.", e.getMessage());
  }

  @Test
  public void testText_MultipleCandidates() {
    GenerateContentResponse response =
        GenerateContentResponse.builder().candidates(CANDIDATE_1, CANDIDATE_2).build();

    String result = response.text();

    assertEquals("text1", result);
  }

  @Test
  public void testText_EmptyParts() {
    GenerateContentResponse response =
        GenerateContentResponse.builder().candidates(CANDIDATE_WITH_EMPTY_PARTS).build();

    String result = response.text();

    assertEquals(null, result);
  }

  @Test
  public void testText_PartWithText() {
    GenerateContentResponse response =
        GenerateContentResponse.builder().candidates(CANDIDATE_1).build();

    String result = response.text();

    assertEquals("text1", result);
  }

  @Test
  public void testText_MultiplePartsWithText() {
    GenerateContentResponse response =
        GenerateContentResponse.builder().candidates(CANDIDATE_WITH_MULTIPLE_PARTS).build();

    String result = response.text();

    assertEquals("text1text2", result);
  }

  @Test
  public void testText_MultiplePartsWithThought() {
    GenerateContentResponse response =
        GenerateContentResponse.builder().candidates(CANDIDATE_WITH_THOUGHT).build();

    String result = response.text();

    assertEquals("text1", result);
  }

  @Test
  public void testText_MixedParts() {
    GenerateContentResponse response =
        GenerateContentResponse.builder().candidates(CANDIDATE_WITH_MIXED_PARTS).build();

    String result = response.text();

    assertEquals("text1", result);
  }

  @Test
  public void testFunctionCalls_EmptyCandidates() {
    GenerateContentResponse response = GenerateContentResponse.builder().build();

    ImmutableList<FunctionCall> result = response.functionCalls();

    assertEquals(null, result);
  }

  @Test
  public void testFunctionCalls_EmptyParts() {
    GenerateContentResponse response =
        GenerateContentResponse.builder().candidates(CANDIDATE_WITH_EMPTY_PARTS).build();

    ImmutableList<FunctionCall> result = response.functionCalls();

    assertEquals(null, result);
  }

  @Test
  public void testFunctionCalls_PartWithFunctionCall() {
    GenerateContentResponse response =
        GenerateContentResponse.builder().candidates(CANDIDATE_WITH_FUNCTION_CALLS).build();

    ImmutableList<FunctionCall> result = response.functionCalls();

    assertEquals(2, result.size());
    assertEquals(FUNCTION_CALL_1, result.get(0));
    assertEquals(FUNCTION_CALL_2, result.get(1));
  }

  @Test
  public void testFunctionCalls_MixedParts() {
    GenerateContentResponse response =
        GenerateContentResponse.builder().candidates(CANDIDATE_WITH_MIXED_PARTS).build();

    ImmutableList<FunctionCall> result = response.functionCalls();

    assertEquals(1, result.size());
    assertEquals(FUNCTION_CALL_1, result.get(0));
  }

  @Test
  public void testExecutableCode_EmptyCandidates() {
    GenerateContentResponse response = GenerateContentResponse.builder().build();

    String result = response.executableCode();

    assertEquals(null, result);
  }

  @Test
  public void testExecutableCode_EmptyContent() {
    GenerateContentResponse response =
        GenerateContentResponse.builder().candidates(Candidate.builder()).build();

    String result = response.executableCode();

    assertEquals(null, result);
  }

  @Test
  public void testExecutableCode_EmptyParts() {
    GenerateContentResponse response =
        GenerateContentResponse.builder().candidates(CANDIDATE_WITH_EMPTY_PARTS).build();

    String result = response.executableCode();

    assertEquals(null, result);
  }

  @Test
  public void testExecutableCode_MultipleCandidates() {
    GenerateContentResponse response =
        GenerateContentResponse.builder()
            .candidates(
                Candidate.builder()
                    .content(CONTENT_WITH_EXECUTABLE_CODE)
                    .finishReason(FinishReason.Known.STOP)
                    .build(),
                CANDIDATE_1)
            .build();

    String result = response.executableCode();

    assertEquals("executableCode", result);
  }

  @Test
  public void testExecutableCode_PartWithExecutableCode() {
    GenerateContentResponse response =
        GenerateContentResponse.builder()
            .candidates(
                Candidate.builder()
                    .content(CONTENT_WITH_EXECUTABLE_CODE)
                    .finishReason(FinishReason.Known.STOP))
            .build();

    String result = response.executableCode();

    assertEquals("executableCode", result);
  }


  @Test
  public void testExecutableCode_TextPart() {
    GenerateContentResponse response =
        GenerateContentResponse.builder().candidates(CANDIDATE_1).build();

    String result = response.executableCode();

    assertEquals(null, result);
  }

  @Test
  public void testExecutableCode_MultipleParts() {
    GenerateContentResponse response =
        GenerateContentResponse.builder()
            .candidates(
                Candidate.builder()
                    .content(CONTENT_WITH_EXECUTABLE_CODE_AND_TEXT)
                    .finishReason(FinishReason.Known.STOP))
            .build();

    String result = response.executableCode();

    assertEquals("executableCode", result);
  }

  @Test
  public void testCodeExecutionResult_EmptyCandidates() {
    GenerateContentResponse response = GenerateContentResponse.builder().build();

    String result = response.codeExecutionResult();

    assertEquals(null, result);
  }

  @Test
  public void testCodeExecutionResult_EmptyContent() {
    GenerateContentResponse response =
        GenerateContentResponse.builder().candidates(Candidate.builder()).build();

    String result = response.codeExecutionResult();

    assertEquals(null, result);
  }

  @Test
  public void testCodeExecutionResult_EmptyParts() {
    GenerateContentResponse response =
        GenerateContentResponse.builder().candidates(CANDIDATE_WITH_EMPTY_PARTS).build();

    String result = response.codeExecutionResult();

    assertEquals(null, result);
  }

  @Test
  public void testCodeExecutionResult_MultipleCandidates() {
    GenerateContentResponse response =
        GenerateContentResponse.builder()
            .candidates(
                Candidate.builder()
                    .content(CONTENT_WITH_CODE_EXECUTION_RESULT)
                    .finishReason(FinishReason.Known.STOP)
                    .build(),
                CANDIDATE_1)
            .build();

    String result = response.codeExecutionResult();

    assertEquals("codeExecutionResult", result);
  }

  @Test
  public void testCodeExecutionResult_PartWithCodeExecutionResult() {
    GenerateContentResponse response =
        GenerateContentResponse.builder()
            .candidates(
                Candidate.builder()
                    .content(CONTENT_WITH_CODE_EXECUTION_RESULT)
                    .finishReason(FinishReason.Known.STOP))
            .build();

    String result = response.codeExecutionResult();

    assertEquals("codeExecutionResult", result);
  }

  @Test
  public void testCodeExecutionResult_TextPart() {
    GenerateContentResponse response =
        GenerateContentResponse.builder().candidates(CANDIDATE_1).build();

    String result = response.codeExecutionResult();

    assertEquals(null, result);
  }

  @Test
  public void testCodeExecutionResult_MultipleParts() {
    GenerateContentResponse response =
        GenerateContentResponse.builder()
            .candidates(
                Candidate.builder()
                    .content(CONTENT_WITH_CODE_EXECUTION_RESULT_AND_TEXT)
                    .finishReason(FinishReason.Known.STOP))
            .build();

    String result = response.codeExecutionResult();

    assertEquals("codeExecutionResult", result);
  }
}
