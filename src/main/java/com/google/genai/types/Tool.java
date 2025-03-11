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

import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.util.List;
import java.util.Optional;

/** Tool details of a tool that the model may use to generate a response. */
@GenerateTypeAdapter
@AutoValue
public abstract class Tool extends JsonSerializable {
  /** List of function declarations that the tool supports. */
  @SerializedName("functionDeclarations")
  public abstract Optional<List<FunctionDeclaration>> functionDeclarations();

  /**
   * Optional. Retrieval tool type. System will always execute the provided retrieval tool(s) to get
   * external knowledge to answer the prompt. Retrieval results are presented to the model for
   * generation.
   */
  @SerializedName("retrieval")
  public abstract Optional<Retrieval> retrieval();

  /**
   * Optional. Google Search tool type. Specialized retrieval tool that is powered by Google Search.
   */
  @SerializedName("googleSearch")
  public abstract Optional<GoogleSearch> googleSearch();

  /**
   * Optional. GoogleSearchRetrieval tool type. Specialized retrieval tool that is powered by Google
   * search.
   */
  @SerializedName("googleSearchRetrieval")
  public abstract Optional<GoogleSearchRetrieval> googleSearchRetrieval();

  /**
   * Optional. CodeExecution tool type. Enables the model to execute code as part of generation.
   * This field is only used by the Gemini Developer API services.
   */
  @SerializedName("codeExecution")
  public abstract Optional<ToolCodeExecution> codeExecution();

  /** Instantiates a builder for Tool. */
  public static Builder builder() {
    return new AutoValue_Tool.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for Tool. */
  @AutoValue.Builder
  public abstract static class Builder {
    @SerializedName("functionDeclarations")
    public abstract Builder functionDeclarations(List<FunctionDeclaration> functionDeclarations);

    @SerializedName("functionDeclarations")
    abstract Builder functionDeclarations(Optional<List<FunctionDeclaration>> functionDeclarations);

    @SerializedName("retrieval")
    public abstract Builder retrieval(Retrieval retrieval);

    @SerializedName("retrieval")
    abstract Builder retrieval(Optional<Retrieval> retrieval);

    @SerializedName("googleSearch")
    public abstract Builder googleSearch(GoogleSearch googleSearch);

    @SerializedName("googleSearch")
    abstract Builder googleSearch(Optional<GoogleSearch> googleSearch);

    @SerializedName("googleSearchRetrieval")
    public abstract Builder googleSearchRetrieval(GoogleSearchRetrieval googleSearchRetrieval);

    @SerializedName("googleSearchRetrieval")
    abstract Builder googleSearchRetrieval(Optional<GoogleSearchRetrieval> googleSearchRetrieval);

    @SerializedName("codeExecution")
    public abstract Builder codeExecution(ToolCodeExecution codeExecution);

    @SerializedName("codeExecution")
    abstract Builder codeExecution(Optional<ToolCodeExecution> codeExecution);

    public abstract Tool build();
  }

  /** Deserializes a JSON string to a Tool object. */
  public static Tool fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Tool.class);
  }
}
