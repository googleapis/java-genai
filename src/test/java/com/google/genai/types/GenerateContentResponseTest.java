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
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.ImmutableList;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class GenerateContentResponseTest {

  private static final FunctionCall FUNCTION_CALL_1 =
      FunctionCall.builder().setName("funcCall1").setArgs(Map.of("key1", "val1")).build();
  private static final FunctionCall FUNCTION_CALL_2 =
      FunctionCall.builder().setName("funcCall2").setArgs(Map.of("key2", "val2")).build();

  private static final Part PART_1 = Part.builder().setText("text1").build();
  private static final Part PART_2 = Part.builder().setText("text2").build();
  private static final Part PART_FUNCTION_CALL_1 =
      Part.builder().setFunctionCall(FUNCTION_CALL_1).setThought(false).build();
  private static final Part PART_FUNCTION_CALL_2 =
      Part.builder().setFunctionCall(FUNCTION_CALL_2).setThought(false).build();
  private static final Part PART_WITH_THOUGHT =
      Part.builder().setText("text3").setThought(true).build();

  private static final Content CONTENT_1 =
      Content.builder().setParts(ImmutableList.of(PART_1)).build();
  private static final Content CONTENT_2 =
      Content.builder().setParts(ImmutableList.of(PART_2)).build();
  private static final Content CONTENT_WITH_MULTIPLE_PARTS =
      Content.builder().setParts(ImmutableList.of(PART_1, PART_2)).build();
  private static final Content CONTENT_WITH_FUNCTION_CALLS =
      Content.builder()
          .setParts(ImmutableList.of(PART_FUNCTION_CALL_1, PART_FUNCTION_CALL_2))
          .build();
  private static final Content CONTENT_WITH_MIXED_PARTS =
      Content.builder().setParts(ImmutableList.of(PART_1, PART_FUNCTION_CALL_1)).build();
  private static final Content CONTENT_WITH_EMPTY_PARTS =
      Content.builder().setParts(ImmutableList.of()).build();
  private static final Content CONTENT_WITH_THOUGHT =
      Content.builder().setParts(ImmutableList.of(PART_1, PART_WITH_THOUGHT)).build();

  private static final Candidate CANDIDATE_1 =
      Candidate.builder().setContent(CONTENT_1).setFinishReason("STOP").build();
  private static final Candidate CANDIDATE_2 =
      Candidate.builder().setContent(CONTENT_2).setFinishReason("STOP").build();
  private static final Candidate CANDIDATE_WITH_EMPTY_PARTS =
      Candidate.builder().setContent(CONTENT_WITH_EMPTY_PARTS).setFinishReason("STOP").build();
  private static final Candidate CANDIDATE_WITH_THOUGHT =
      Candidate.builder().setContent(CONTENT_WITH_THOUGHT).setFinishReason("STOP").build();
  private static final Candidate CANDIDATE_WITH_MULTIPLE_PARTS =
      Candidate.builder().setContent(CONTENT_WITH_MULTIPLE_PARTS).setFinishReason("STOP").build();
  private static final Candidate CANDIDATE_WITH_FUNCTION_CALLS =
      Candidate.builder().setContent(CONTENT_WITH_FUNCTION_CALLS).setFinishReason("STOP").build();
  private static final Candidate CANDIDATE_WITH_MIXED_PARTS =
      Candidate.builder().setContent(CONTENT_WITH_MIXED_PARTS).setFinishReason("STOP").build();
  private static final Candidate CANDIDATE_WITH_UNEXPECTED_FINISH_REASON =
      Candidate.builder().setContent(CONTENT_1).setFinishReason("SAFETY").build();

  @Test
  public void testParts_EmptyCandidates() {
    GenerateContentResponse response = GenerateContentResponse.builder().build();

    assertEquals(null, response.parts());
  }

  @Test
  public void testParts_UnexpectedFinishReason() {
    GenerateContentResponse response =
        GenerateContentResponse.builder()
            .setCandidates(ImmutableList.of(CANDIDATE_WITH_UNEXPECTED_FINISH_REASON))
            .build();

    Exception e = assertThrows(IllegalArgumentException.class, () -> response.parts());
    assertEquals("The response finished unexpectedly with reason SAFETY.", e.getMessage());
  }

  @Test
  public void testParts_MultipleCandidates() {
    GenerateContentResponse response =
        GenerateContentResponse.builder()
            .setCandidates(ImmutableList.of(CANDIDATE_1, CANDIDATE_2))
            .build();

    // Only the first candidate is used.
    assertEquals(ImmutableList.of(PART_1), response.parts());
  }

  @Test
  public void testParts_SinglePart() {
    GenerateContentResponse response =
        GenerateContentResponse.builder().setCandidates(ImmutableList.of(CANDIDATE_1)).build();
    assertEquals(ImmutableList.of(PART_1), response.parts());
  }

  @Test
  public void testParts_MultipleParts() {
    GenerateContentResponse response =
        GenerateContentResponse.builder()
            .setCandidates(ImmutableList.of(CANDIDATE_WITH_MULTIPLE_PARTS))
            .build();

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
            .setCandidates(ImmutableList.of(CANDIDATE_WITH_UNEXPECTED_FINISH_REASON))
            .build();

    Exception e = assertThrows(IllegalArgumentException.class, () -> response.text());
    assertEquals("The response finished unexpectedly with reason SAFETY.", e.getMessage());
  }

  @Test
  public void testText_MultipleCandidates() {
    GenerateContentResponse response =
        GenerateContentResponse.builder()
            .setCandidates(ImmutableList.of(CANDIDATE_1, CANDIDATE_2))
            .build();

    String result = response.text();

    assertEquals("text1", result);
  }

  @Test
  public void testText_EmptyParts() {
    GenerateContentResponse response =
        GenerateContentResponse.builder()
            .setCandidates(ImmutableList.of(CANDIDATE_WITH_EMPTY_PARTS))
            .build();

    String result = response.text();

    assertEquals(null, result);
  }

  @Test
  public void testText_PartWithText() {
    GenerateContentResponse response =
        GenerateContentResponse.builder().setCandidates(ImmutableList.of(CANDIDATE_1)).build();

    String result = response.text();

    assertEquals("text1", result);
  }

  @Test
  public void testText_MultiplePartsWithText() {
    GenerateContentResponse response =
        GenerateContentResponse.builder()
            .setCandidates(ImmutableList.of(CANDIDATE_WITH_MULTIPLE_PARTS))
            .build();

    String result = response.text();

    assertEquals("text1text2", result);
  }

  @Test
  public void testText_MultiplePartsWithThought() {
    GenerateContentResponse response =
        GenerateContentResponse.builder()
            .setCandidates(ImmutableList.of(CANDIDATE_WITH_THOUGHT))
            .build();

    String result = response.text();

    assertEquals("text1", result);
  }

  @Test
  public void testText_MixedParts() {
    GenerateContentResponse response =
        GenerateContentResponse.builder()
            .setCandidates(ImmutableList.of(CANDIDATE_WITH_MIXED_PARTS))
            .build();

    Exception e = assertThrows(IllegalArgumentException.class, () -> response.text());
    assertEquals(
        String.format("Only text parts are supported, but got %s", PART_FUNCTION_CALL_1),
        e.getMessage());
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
        GenerateContentResponse.builder()
            .setCandidates(ImmutableList.of(CANDIDATE_WITH_EMPTY_PARTS))
            .build();

    ImmutableList<FunctionCall> result = response.functionCalls();

    assertEquals(null, result);
  }

  @Test
  public void testFunctionCalls_PartWithFunctionCall() {
    GenerateContentResponse response =
        GenerateContentResponse.builder()
            .setCandidates(ImmutableList.of(CANDIDATE_WITH_FUNCTION_CALLS))
            .build();

    ImmutableList<FunctionCall> result = response.functionCalls();

    assertEquals(2, result.size());
    assertEquals(FUNCTION_CALL_1, result.get(0));
    assertEquals(FUNCTION_CALL_2, result.get(1));
  }

  @Test
  public void testFunctionCalls_MixedParts() {
    GenerateContentResponse response =
        GenerateContentResponse.builder()
            .setCandidates(ImmutableList.of(CANDIDATE_WITH_MIXED_PARTS))
            .build();

    Exception e = assertThrows(IllegalArgumentException.class, () -> response.functionCalls());
    assertEquals(
        String.format("Only function call parts are supported, but got %s", PART_1),
        e.getMessage());
  }
}
