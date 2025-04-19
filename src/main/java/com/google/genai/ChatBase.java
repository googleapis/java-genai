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

import com.google.genai.types.Content;
import com.google.genai.types.Part;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Base class for chat sessions, used for history management. */
class ChatBase {
  protected final List<Content> comprehensiveHistory;
  protected final List<Content> curatedHistory;

  ChatBase(List<Content> comprehensiveHistory, List<Content> curatedHistory) {
    this.comprehensiveHistory = new ArrayList<>();
    this.curatedHistory = new ArrayList<>();
  }

  /**
   * Records the chat history.
   *
   * @param currentHistory The current history of messages.
   */
  protected void recordHistory(List<Content> currentHistory) {
    this.comprehensiveHistory.addAll(currentHistory);
    List<Content> validatedHistory = validateHistory(currentHistory);
    this.curatedHistory.addAll(validatedHistory);
  }

  /**
   * Validates the content of a {@link Content} object by checking that all parts are not null.
   *
   * @param content The {@link Content} object to validate.
   * @return True if the content is valid, false otherwise.
   */
  protected boolean validateContent(Content content) {
    if (content.parts().isPresent()) {
      for (Part part : content.parts().get()) {
        if (part.equals(Part.builder().build())) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Validates the content of a list of {@link Content} objects by checking that all parts are not
   * null.
   *
   * @param contents The list of {@link Content} objects to validate.
   * @return True if the content is valid, false otherwise.
   */
  protected boolean validateContents(List<Content> contents) {
    for (Content content : contents) {
      if (!validateContent(content)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Validates the history of messages by checking that the first message is from the user, the
   * roles of subsequent messages are valid, and the content of the messages is valid.
   *
   * @param history The history of messages to validate.
   * @return The validated history of messages.
   * @throws IllegalArgumentException If the history is invalid.
   */
  protected List<Content> validateHistory(List<Content> history) {
    List<Content> validatedHistory = new ArrayList<>();
    List<Content> currentInput = new ArrayList<>();
    List<Content> currentOutput = new ArrayList<>();
    List<String> validRoles = Arrays.asList("user", "model");
    for (int i = 0; i < history.size(); i++) {
      // The second condition handles the case where the history is empty and we are validating the
      // first message in a streaming call
      if ((i == 0
              && history.get(i).role().isPresent()
              && !history.get(i).role().get().equals("user"))
          || (curatedHistory.size() == 0
              && history.size() > 0
              && history.get(0).role().isPresent()
              && !history.get(0).role().get().equals("user"))) {
        throw new IllegalArgumentException(
            "The first message in the history must be from the user.");
      }
      if (i < history.size() && !validRoles.contains(history.get(i).role().get())) {
        throw new IllegalArgumentException(
            "The role of the message must be either 'user' or 'model'.");
      }
      if (history.get(i).role().isPresent() && history.get(i).role().get().equals("user")) {
        if (validateContent(history.get(i))) {
          currentInput.add(history.get(i));
        }
      } else {
        boolean isValid = true;
        while (i < history.size()
            && history.get(i).role().isPresent()
            && history.get(i).role().get().equals("model")) {
          currentOutput.add(history.get(i));
          if (isValid && !validateContent(history.get(i))) {
            isValid = false;
          }
          i++;
        }
        if (isValid) {
          validatedHistory.addAll(currentInput);
          validatedHistory.addAll(currentOutput);
          currentInput = new ArrayList<>();
          currentOutput = new ArrayList<>();
        }
      }
    }
    return validatedHistory;
  }

  /**
   * Returns the chat history.
   *
   * @param curated Whether to return the curated history or the comprehensive history.
   *     Comprehensive history includes all messages, including empty or invalid parts. Curated
   *     history excludes empty or invalid parts.
   */
  public List<Content> getHistory(boolean curated) {
    if (curated) {
      return this.curatedHistory;
    } else {
      return this.comprehensiveHistory;
    }
  }
}
