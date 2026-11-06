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

/**
 * Output only. The reason why the model stopped generating tokens. If empty, the model has not
 * stopped generating.
 */
public class FinishReason {

  /** Enum representing the known values for FinishReason. */
  public enum Known {
    /** The finish reason is unspecified. */
    FINISH_REASON_UNSPECIFIED,

    /** The model reached a natural stopping point or a configured stop sequence. */
    STOP,

    /**
     * The model generated the maximum number of tokens allowed by the `max_output_tokens`
     * parameter.
     */
    MAX_TOKENS,

    /**
     * The model stopped generating because the content potentially violates safety policies. NOTE:
     * When streaming, the `content` field is empty if content filters block the output.
     */
    SAFETY,

    /** The model stopped generating because the content may be a recitation from a source. */
    RECITATION,

    /**
     * The response candidate content was flagged for using an unsupported language. This enum value
     * is not supported in Vertex AI.
     */
    LANGUAGE,

    /** The model stopped generating for a reason not otherwise specified. */
    OTHER,

    /**
     * The model stopped generating because the content contains a term from a configured blocklist.
     */
    BLOCKLIST,

    /** The model stopped generating because the content may be prohibited. */
    PROHIBITED_CONTENT,

    /**
     * The model stopped generating because the content may contain sensitive personally
     * identifiable information (SPII).
     */
    SPII,

    /** The model generated a function call that is syntactically invalid and can't be parsed. */
    MALFORMED_FUNCTION_CALL,

    /** The generated image potentially violates safety policies. */
    IMAGE_SAFETY,

    /** The generated image may contain prohibited content. */
    IMAGE_PROHIBITED_CONTENT,

    /** The image generation stopped for a reason not otherwise specified. */
    IMAGE_OTHER,

    /** The model was expected to generate an image, but didn't. */
    NO_IMAGE,

    /** The generated image may be a recitation from a source. */
    IMAGE_RECITATION,

    /**
     * The model generated a function call that is semantically invalid. This can happen, for
     * example, if function calling is not enabled or the generated function is not in the function
     * declaration.
     */
    UNEXPECTED_TOOL_CALL,

    /**
     * The model made too many tool calls in a single request, so the server-side tool loop stopped
     * before the model returned a final answer.
     */
    TOO_MANY_TOOL_CALLS,

    /**
     * Request has at least one thought signature missing. This enum value is not supported in
     * Vertex AI.
     */
    MISSING_THOUGHT_SIGNATURE,

    /** Finishes due to malformed response. */
    MALFORMED_RESPONSE,

    /**
     * Request was filtered by an escalation rule. This enum value is not supported in Vertex AI.
     */
    ESCALATION,

    /**
     * Indicates that token generation stopped because the user account is limited or disabled due
     * to Prohibited Use Policy (PUP) violations. This enum value is not supported in Vertex AI.
     */
    PUP_LIMITED_DISABLED,

    /**
     * Token generation was stopped because the response reached the per-request token limit, but
     * generation is not yet complete. The model response can be continued via a subsequent request
     * using the returned `continuation_token`.
     */
    CONTINUATION,

    /**
     * The model response was blocked by Model Armor. This enum value is not supported in Gemini
     * API.
     */
    MODEL_ARMOR
  }

  private Known finishReasonEnum;
  private final String value;

  @JsonCreator
  public FinishReason(String value) {
    this.value = value;
    for (Known finishReasonEnum : Known.values()) {
      if (Ascii.equalsIgnoreCase(finishReasonEnum.toString(), value)) {
        this.finishReasonEnum = finishReasonEnum;
        break;
      }
    }
    if (this.finishReasonEnum == null) {
      this.finishReasonEnum = Known.FINISH_REASON_UNSPECIFIED;
    }
  }

  public FinishReason(Known knownValue) {
    this.finishReasonEnum = knownValue;
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

    if (!(o instanceof FinishReason)) {
      return false;
    }

    FinishReason other = (FinishReason) o;

    if (this.finishReasonEnum != Known.FINISH_REASON_UNSPECIFIED
        && other.finishReasonEnum != Known.FINISH_REASON_UNSPECIFIED) {
      return this.finishReasonEnum == other.finishReasonEnum;
    } else if (this.finishReasonEnum == Known.FINISH_REASON_UNSPECIFIED
        && other.finishReasonEnum == Known.FINISH_REASON_UNSPECIFIED) {
      return this.value.equals(other.value);
    }
    return false;
  }

  @ExcludeFromGeneratedCoverageReport
  @Override
  public int hashCode() {
    if (this.finishReasonEnum != Known.FINISH_REASON_UNSPECIFIED) {
      return this.finishReasonEnum.hashCode();
    } else {
      return Objects.hashCode(this.value);
    }
  }

  @ExcludeFromGeneratedCoverageReport
  public Known knownEnum() {
    return this.finishReasonEnum;
  }
}
