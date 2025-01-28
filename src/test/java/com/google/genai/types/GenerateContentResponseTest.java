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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class GenerateContentResponseTest {

  private static final FunctionCall FUNCTION_CALL_1 =
      FunctionCall.builder().setName("funcCall1").setArgs(Map.of("key1", "val1")).build();
  private static final FunctionCall FUNCTION_CALL_2 =
      FunctionCall.builder().setName("funcCall2").setArgs(Map.of("key2", "val2")).build();

  private static final Part PART_1 =
      Part.builder().setText("text1").setFunctionCall(FUNCTION_CALL_1).setThought(false).build();
  private static final Part PART_2 =
      Part.builder().setText("text2").setFunctionCall(FUNCTION_CALL_2).setThought(false).build();
  private static final Part PART_WITH_THOUGHT =
      Part.builder().setText("text3").setThought(true).build();

  private static final Content CONTENT_1 =
      Content.builder().setParts(ImmutableList.of(PART_1)).build();
  private static final Content CONTENT_2 =
      Content.builder().setParts(ImmutableList.of(PART_2)).build();
  private static final Content CONTENT_WITH_MULTIPLE_PARTS =
      Content.builder().setParts(ImmutableList.of(PART_1, PART_2)).build();
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
  private static final Candidate CANDIDATE_WITH_UNEXPECTED_FINISH_REASON =
      Candidate.builder().setContent(CONTENT_1).setFinishReason("SAFETY").build();

  @Test
  public void testParts_EmptyCandidates() {
    GenerateContentResponse response =
        GenerateContentResponse.builder().setCandidates(ImmutableList.of()).build();

    Exception e = assertThrows(IllegalArgumentException.class, () -> response.parts());
    assertEquals("This response has no candidate.", e.getMessage());
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
    GenerateContentResponse response =
        GenerateContentResponse.builder().setCandidates(List.of()).build();

    Optional<String> result = response.text();

    assertTrue(result.isEmpty());
  }

  @Test
  public void testText_MultipleCandidates() {
    Part part1 = Part.builder().setText("text1").setThought(false).build();
    Content content1 = Content.builder().setParts(List.of(part1)).build();
    Candidate candidate1 = Candidate.builder().setContent(content1).build();

    Part part2 = Part.builder().setText("text2").setThought(false).build();
    Content content2 = Content.builder().setParts(List.of(part2)).build();
    Candidate candidate2 = Candidate.builder().setContent(content2).build();

    GenerateContentResponse response =
        GenerateContentResponse.builder().setCandidates(List.of(candidate1, candidate2)).build();

    Optional<String> result = response.text();

    assertTrue(result.isPresent());
    assertEquals("text1", result.get());
  }

  @Test
  public void testText_EmptyParts() {
    Content content = Content.builder().setParts(List.of()).build();
    Candidate candidate = Candidate.builder().setContent(content).build();
    GenerateContentResponse response =
        GenerateContentResponse.builder().setCandidates(List.of(candidate)).build();

    Optional<String> result = response.text();

    assertTrue(result.isEmpty());
  }

  @Test
  public void testText_PartWithText() {
    Part part = Part.builder().setText("text").setThought(false).build();
    Content content = Content.builder().setParts(List.of(part)).build();
    Candidate candidate = Candidate.builder().setContent(content).build();
    GenerateContentResponse response =
        GenerateContentResponse.builder().setCandidates(List.of(candidate)).build();

    Optional<String> result = response.text();

    assertTrue(result.isPresent());
    assertEquals("text", result.get());
  }

  @Test
  public void testText_MultiplePartsWithText() {
    Part part1 = Part.builder().setText("text1").setThought(false).build();
    Part part2 = Part.builder().setText("text2").setThought(false).build();
    Content content = Content.builder().setParts(List.of(part1, part2)).build();
    Candidate candidate = Candidate.builder().setContent(content).build();
    GenerateContentResponse response =
        GenerateContentResponse.builder().setCandidates(List.of(candidate)).build();

    Optional<String> result = response.text();

    assertTrue(result.isPresent());
    assertEquals("text1text2", result.get());
  }

  @Test
  public void testText_MultiplePartsWithThought() {
    Part part1 = Part.builder().setText("text1").setThought(false).build();
    Part part2 = Part.builder().setText("text2").setThought(true).build();
    Content content = Content.builder().setParts(List.of(part1, part2)).build();
    Candidate candidate = Candidate.builder().setContent(content).build();
    GenerateContentResponse response =
        GenerateContentResponse.builder().setCandidates(List.of(candidate)).build();

    Optional<String> result = response.text();

    assertTrue(result.isPresent());
    assertEquals("text1text2", result.get());
  }

  @Test
  public void testText_PartWithInlineData() {
    Part part1 = Part.builder().setText("text1").setThought(false).build();
    Part part2 = Part.builder().setInlineData(Blob.builder().build()).build();
    Content content = Content.builder().setParts(List.of(part1, part2)).build();
    Candidate candidate = Candidate.builder().setContent(content).build();
    GenerateContentResponse response =
        GenerateContentResponse.builder().setCandidates(List.of(candidate)).build();

    assertThrows(IllegalArgumentException.class, () -> response.text());
  }

  @Test
  public void testFunctionCalls_EmptyCandidates() {
    GenerateContentResponse response =
        GenerateContentResponse.builder().setCandidates(List.of()).build();

    Optional<List<FunctionCall>> result = response.functionCalls();

    assertTrue(result.isEmpty());
  }

  @Test
  public void testFunctionCalls_MultipleCandidates() {
    FunctionCall functionCall1 =
        FunctionCall.builder().setName("funcCall1").setArgs(Map.of("key1", "val1")).build();
    Part part1 = Part.builder().setFunctionCall(functionCall1).build();
    Content content1 = Content.builder().setParts(List.of(part1)).build();
    Candidate candidate1 = Candidate.builder().setContent(content1).build();

    FunctionCall functionCall2 =
        FunctionCall.builder().setName("funcCall2").setArgs(Map.of("key2", "val2")).build();
    Part part2 = Part.builder().setFunctionCall(functionCall2).build();
    Content content2 = Content.builder().setParts(List.of(part2)).build();
    Candidate candidate2 = Candidate.builder().setContent(content2).build();

    GenerateContentResponse response =
        GenerateContentResponse.builder().setCandidates(List.of(candidate1, candidate2)).build();

    Optional<List<FunctionCall>> result = response.functionCalls();

    assertTrue(result.isPresent());
    assertEquals(1, result.get().size());
    assertEquals(functionCall1, result.get().get(0));
  }

  @Test
  public void testFunctionCalls_EmptyParts() {
    Content content = Content.builder().setParts(List.of()).build();
    Candidate candidate = Candidate.builder().setContent(content).build();
    GenerateContentResponse response =
        GenerateContentResponse.builder().setCandidates(List.of(candidate)).build();

    Optional<List<FunctionCall>> result = response.functionCalls();

    assertTrue(result.isEmpty());
  }

  @Test
  public void testFunctionCalls_PartWithFunctionCall() {
    FunctionCall functionCall1 =
        FunctionCall.builder().setName("funcCall1").setArgs(Map.of("key1", "val1")).build();
    FunctionCall functionCall2 =
        FunctionCall.builder().setName("funcCall2").setArgs(Map.of("key2", "val2")).build();
    Part part1 = Part.builder().setFunctionCall(functionCall1).build();
    Part part2 = Part.builder().setFunctionCall(functionCall2).build();
    Content content = Content.builder().setParts(List.of(part1, part2)).build();
    Candidate candidate = Candidate.builder().setContent(content).build();
    GenerateContentResponse response =
        GenerateContentResponse.builder().setCandidates(List.of(candidate)).build();

    Optional<List<FunctionCall>> result = response.functionCalls();

    assertTrue(result.isPresent());
    assertEquals(2, result.get().size());
    assertEquals(functionCall1, result.get().get(0));
    assertEquals(functionCall2, result.get().get(1));
  }
}
