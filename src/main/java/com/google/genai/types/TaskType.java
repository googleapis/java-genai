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

// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Ascii;
import java.util.Objects;

/** The task type of the embedding. */
public class TaskType {

  /** Enum representing the known values for TaskType. */
  public enum Known {
    /**
     * Unset value, which will default to one of the other enum values. This enum value is not
     * supported in Vertex AI.
     */
    TASK_TYPE_UNSPECIFIED,

    /** Specifies the given text is a query in a search/retrieval setting. */
    RETRIEVAL_QUERY,

    /** Specifies the given text is a document from the corpus being searched. */
    RETRIEVAL_DOCUMENT,

    /** Specifies the given text will be used for STS. */
    SEMANTIC_SIMILARITY,

    /** Specifies that the given text will be classified. */
    CLASSIFICATION,

    /** Specifies that the embeddings will be used for clustering. */
    CLUSTERING,

    /** Specifies that the embeddings will be used for question answering. */
    QUESTION_ANSWERING,

    /** Specifies that the embeddings will be used for fact verification. */
    FACT_VERIFICATION,

    /** Specifies that the embeddings will be used for code retrieval. */
    CODE_RETRIEVAL_QUERY,

    /**
     * Unset value, which will default to one of the other enum values. This enum value is not
     * supported in Gemini API.
     */
    UNSPECIFIED
  }

  private Known taskTypeEnum;
  private final String value;

  @JsonCreator
  public TaskType(String value) {
    this.value = value;
    for (Known taskTypeEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(taskTypeEnum.toString(), value)) {
        this.taskTypeEnum = taskTypeEnum;
        break;
      }
    }
    if (this.taskTypeEnum == null) {
      this.taskTypeEnum = Known.TASK_TYPE_UNSPECIFIED;
    }
  }

  public TaskType(Known knownValue) {
    this.taskTypeEnum = knownValue;
    this.value = knownValue.toString();
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  @JsonValue
  public String toString() {
    return this.value;
  }

  @ExcludeFromGeneratedCoverageReport
  @SuppressWarnings("PatternMatchingInstanceof")
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return false;
    }

    if (!(o instanceof TaskType)) {
      return false;
    }

    TaskType other = (TaskType) o;

    if (this.taskTypeEnum != Known.TASK_TYPE_UNSPECIFIED
        && other.taskTypeEnum != Known.TASK_TYPE_UNSPECIFIED) {
      return this.taskTypeEnum == other.taskTypeEnum;
    } else if (this.taskTypeEnum == Known.TASK_TYPE_UNSPECIFIED
        && other.taskTypeEnum == Known.TASK_TYPE_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.taskTypeEnum != Known.TASK_TYPE_UNSPECIFIED) {
      return this.taskTypeEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.taskTypeEnum;
  }
}
