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

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.genai.JsonSerializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/** Tool details of a tool that the model may use to generate a response. */
@AutoValue
@JsonDeserialize(builder = Tool.Builder.class)
public abstract class Tool extends JsonSerializable {
  /**
   * Optional. Retrieval tool type. System will always execute the provided retrieval tool(s) to get
   * external knowledge to answer the prompt. Retrieval results are presented to the model for
   * generation. This field is not supported in Gemini API.
   */
  @JsonProperty("retrieval")
  public abstract Optional<Retrieval> retrieval();

  /**
   * Optional. GoogleSearchRetrieval tool type. Specialized retrieval tool that is powered by Google
   * search.
   */
  @JsonProperty("googleSearchRetrieval")
  public abstract Optional<GoogleSearchRetrieval> googleSearchRetrieval();

  /**
   * The java.lang.reflect.Method instance. If provided, it will to be parsed into a list of
   * FunctionDeclaration instances, and be assigned to the functionDeclarations field.
   */
  @JsonIgnore
  public abstract Optional<List<Method>> functions();

  /**
   * Optional. Tool to support the model interacting directly with the computer. If enabled, it
   * automatically populates computer-use specific Function Declarations.
   */
  @JsonProperty("computerUse")
  public abstract Optional<ComputerUse> computerUse();

  /** Optional. CodeExecution tool type. Enables the model to execute code as part of generation. */
  @JsonProperty("codeExecution")
  public abstract Optional<ToolCodeExecution> codeExecution();

  /**
   * Optional. Tool to support searching public web data, powered by Vertex AI Search and Sec4
   * compliance. This field is not supported in Gemini API.
   */
  @JsonProperty("enterpriseWebSearch")
  public abstract Optional<EnterpriseWebSearch> enterpriseWebSearch();

  /**
   * Optional. Function tool type. One or more function declarations to be passed to the model along
   * with the current user query. Model may decide to call a subset of these functions by populating
   * FunctionCall in the response. User should provide a FunctionResponse for each function call in
   * the next turn. Based on the function responses, Model will generate the final response back to
   * the user. Maximum 512 function declarations can be provided.
   */
  @JsonProperty("functionDeclarations")
  public abstract Optional<List<FunctionDeclaration>> functionDeclarations();

  /** Optional. GoogleMaps tool type. Tool to support Google Maps in Model. */
  @JsonProperty("googleMaps")
  public abstract Optional<GoogleMaps> googleMaps();

  /**
   * Optional. GoogleSearch tool type. Tool to support Google Search in Model. Powered by Google.
   */
  @JsonProperty("googleSearch")
  public abstract Optional<GoogleSearch> googleSearch();

  /** Optional. Tool to support URL context retrieval. */
  @JsonProperty("urlContext")
  public abstract Optional<UrlContext> urlContext();

  /** Instantiates a builder for Tool. */
  @ExcludeFromGeneratedCoverageReport
  public static Builder builder() {
    return new AutoValue_Tool.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for Tool. */
  @AutoValue.Builder
  public abstract static class Builder {
    /** For internal usage. Please use `Tool.builder()` for instantiation. */
    @JsonCreator
    private static Builder create() {
      return new AutoValue_Tool.Builder();
    }

    /**
     * Setter for retrieval.
     *
     * <p>retrieval: Optional. Retrieval tool type. System will always execute the provided
     * retrieval tool(s) to get external knowledge to answer the prompt. Retrieval results are
     * presented to the model for generation. This field is not supported in Gemini API.
     */
    @JsonProperty("retrieval")
    public abstract Builder retrieval(Retrieval retrieval);

    /**
     * Setter for retrieval builder.
     *
     * <p>retrieval: Optional. Retrieval tool type. System will always execute the provided
     * retrieval tool(s) to get external knowledge to answer the prompt. Retrieval results are
     * presented to the model for generation. This field is not supported in Gemini API.
     */
    @CanIgnoreReturnValue
    public Builder retrieval(Retrieval.Builder retrievalBuilder) {
      return retrieval(retrievalBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder retrieval(Optional<Retrieval> retrieval);

    /** Clears the value of retrieval field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearRetrieval() {
      return retrieval(Optional.empty());
    }

    /**
     * Setter for googleSearchRetrieval.
     *
     * <p>googleSearchRetrieval: Optional. GoogleSearchRetrieval tool type. Specialized retrieval
     * tool that is powered by Google search.
     */
    @JsonProperty("googleSearchRetrieval")
    public abstract Builder googleSearchRetrieval(GoogleSearchRetrieval googleSearchRetrieval);

    /**
     * Setter for googleSearchRetrieval builder.
     *
     * <p>googleSearchRetrieval: Optional. GoogleSearchRetrieval tool type. Specialized retrieval
     * tool that is powered by Google search.
     */
    @CanIgnoreReturnValue
    public Builder googleSearchRetrieval(
        GoogleSearchRetrieval.Builder googleSearchRetrievalBuilder) {
      return googleSearchRetrieval(googleSearchRetrievalBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder googleSearchRetrieval(Optional<GoogleSearchRetrieval> googleSearchRetrieval);

    /** Clears the value of googleSearchRetrieval field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearGoogleSearchRetrieval() {
      return googleSearchRetrieval(Optional.empty());
    }

    /**
     * Setter for functions.
     *
     * <p>functions: The java.lang.reflect.Method instance. If provided, it will to be parsed into a
     * list of FunctionDeclaration instances, and be assigned to the functionDeclarations field.
     */
    @JsonIgnore
    public abstract Builder functions(List<Method> functions);

    /**
     * Setter for functions.
     *
     * <p>functions: The java.lang.reflect.Method instance. If provided, it will to be parsed into a
     * list of FunctionDeclaration instances, and be assigned to the functionDeclarations field.
     */
    @CanIgnoreReturnValue
    public Builder functions(Method... functions) {
      return functions(Arrays.asList(functions));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder functions(Optional<List<Method>> functions);

    /** Clears the value of functions field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearFunctions() {
      return functions(Optional.empty());
    }

    /**
     * Setter for computerUse.
     *
     * <p>computerUse: Optional. Tool to support the model interacting directly with the computer.
     * If enabled, it automatically populates computer-use specific Function Declarations.
     */
    @JsonProperty("computerUse")
    public abstract Builder computerUse(ComputerUse computerUse);

    /**
     * Setter for computerUse builder.
     *
     * <p>computerUse: Optional. Tool to support the model interacting directly with the computer.
     * If enabled, it automatically populates computer-use specific Function Declarations.
     */
    @CanIgnoreReturnValue
    public Builder computerUse(ComputerUse.Builder computerUseBuilder) {
      return computerUse(computerUseBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder computerUse(Optional<ComputerUse> computerUse);

    /** Clears the value of computerUse field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearComputerUse() {
      return computerUse(Optional.empty());
    }

    /**
     * Setter for codeExecution.
     *
     * <p>codeExecution: Optional. CodeExecution tool type. Enables the model to execute code as
     * part of generation.
     */
    @JsonProperty("codeExecution")
    public abstract Builder codeExecution(ToolCodeExecution codeExecution);

    /**
     * Setter for codeExecution builder.
     *
     * <p>codeExecution: Optional. CodeExecution tool type. Enables the model to execute code as
     * part of generation.
     */
    @CanIgnoreReturnValue
    public Builder codeExecution(ToolCodeExecution.Builder codeExecutionBuilder) {
      return codeExecution(codeExecutionBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder codeExecution(Optional<ToolCodeExecution> codeExecution);

    /** Clears the value of codeExecution field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearCodeExecution() {
      return codeExecution(Optional.empty());
    }

    /**
     * Setter for enterpriseWebSearch.
     *
     * <p>enterpriseWebSearch: Optional. Tool to support searching public web data, powered by
     * Vertex AI Search and Sec4 compliance. This field is not supported in Gemini API.
     */
    @JsonProperty("enterpriseWebSearch")
    public abstract Builder enterpriseWebSearch(EnterpriseWebSearch enterpriseWebSearch);

    /**
     * Setter for enterpriseWebSearch builder.
     *
     * <p>enterpriseWebSearch: Optional. Tool to support searching public web data, powered by
     * Vertex AI Search and Sec4 compliance. This field is not supported in Gemini API.
     */
    @CanIgnoreReturnValue
    public Builder enterpriseWebSearch(EnterpriseWebSearch.Builder enterpriseWebSearchBuilder) {
      return enterpriseWebSearch(enterpriseWebSearchBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder enterpriseWebSearch(Optional<EnterpriseWebSearch> enterpriseWebSearch);

    /** Clears the value of enterpriseWebSearch field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearEnterpriseWebSearch() {
      return enterpriseWebSearch(Optional.empty());
    }

    /**
     * Setter for functionDeclarations.
     *
     * <p>functionDeclarations: Optional. Function tool type. One or more function declarations to
     * be passed to the model along with the current user query. Model may decide to call a subset
     * of these functions by populating FunctionCall in the response. User should provide a
     * FunctionResponse for each function call in the next turn. Based on the function responses,
     * Model will generate the final response back to the user. Maximum 512 function declarations
     * can be provided.
     */
    @JsonProperty("functionDeclarations")
    public abstract Builder functionDeclarations(List<FunctionDeclaration> functionDeclarations);

    /**
     * Setter for functionDeclarations.
     *
     * <p>functionDeclarations: Optional. Function tool type. One or more function declarations to
     * be passed to the model along with the current user query. Model may decide to call a subset
     * of these functions by populating FunctionCall in the response. User should provide a
     * FunctionResponse for each function call in the next turn. Based on the function responses,
     * Model will generate the final response back to the user. Maximum 512 function declarations
     * can be provided.
     */
    @CanIgnoreReturnValue
    public Builder functionDeclarations(FunctionDeclaration... functionDeclarations) {
      return functionDeclarations(Arrays.asList(functionDeclarations));
    }

    /**
     * Setter for functionDeclarations builder.
     *
     * <p>functionDeclarations: Optional. Function tool type. One or more function declarations to
     * be passed to the model along with the current user query. Model may decide to call a subset
     * of these functions by populating FunctionCall in the response. User should provide a
     * FunctionResponse for each function call in the next turn. Based on the function responses,
     * Model will generate the final response back to the user. Maximum 512 function declarations
     * can be provided.
     */
    @CanIgnoreReturnValue
    public Builder functionDeclarations(
        FunctionDeclaration.Builder... functionDeclarationsBuilders) {
      return functionDeclarations(
          Arrays.asList(functionDeclarationsBuilders).stream()
              .map(FunctionDeclaration.Builder::build)
              .collect(toImmutableList()));
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder functionDeclarations(Optional<List<FunctionDeclaration>> functionDeclarations);

    /** Clears the value of functionDeclarations field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearFunctionDeclarations() {
      return functionDeclarations(Optional.empty());
    }

    /**
     * Setter for googleMaps.
     *
     * <p>googleMaps: Optional. GoogleMaps tool type. Tool to support Google Maps in Model.
     */
    @JsonProperty("googleMaps")
    public abstract Builder googleMaps(GoogleMaps googleMaps);

    /**
     * Setter for googleMaps builder.
     *
     * <p>googleMaps: Optional. GoogleMaps tool type. Tool to support Google Maps in Model.
     */
    @CanIgnoreReturnValue
    public Builder googleMaps(GoogleMaps.Builder googleMapsBuilder) {
      return googleMaps(googleMapsBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder googleMaps(Optional<GoogleMaps> googleMaps);

    /** Clears the value of googleMaps field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearGoogleMaps() {
      return googleMaps(Optional.empty());
    }

    /**
     * Setter for googleSearch.
     *
     * <p>googleSearch: Optional. GoogleSearch tool type. Tool to support Google Search in Model.
     * Powered by Google.
     */
    @JsonProperty("googleSearch")
    public abstract Builder googleSearch(GoogleSearch googleSearch);

    /**
     * Setter for googleSearch builder.
     *
     * <p>googleSearch: Optional. GoogleSearch tool type. Tool to support Google Search in Model.
     * Powered by Google.
     */
    @CanIgnoreReturnValue
    public Builder googleSearch(GoogleSearch.Builder googleSearchBuilder) {
      return googleSearch(googleSearchBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder googleSearch(Optional<GoogleSearch> googleSearch);

    /** Clears the value of googleSearch field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearGoogleSearch() {
      return googleSearch(Optional.empty());
    }

    /**
     * Setter for urlContext.
     *
     * <p>urlContext: Optional. Tool to support URL context retrieval.
     */
    @JsonProperty("urlContext")
    public abstract Builder urlContext(UrlContext urlContext);

    /**
     * Setter for urlContext builder.
     *
     * <p>urlContext: Optional. Tool to support URL context retrieval.
     */
    @CanIgnoreReturnValue
    public Builder urlContext(UrlContext.Builder urlContextBuilder) {
      return urlContext(urlContextBuilder.build());
    }

    @ExcludeFromGeneratedCoverageReport
    abstract Builder urlContext(Optional<UrlContext> urlContext);

    /** Clears the value of urlContext field. */
    @ExcludeFromGeneratedCoverageReport
    @CanIgnoreReturnValue
    public Builder clearUrlContext() {
      return urlContext(Optional.empty());
    }

    public abstract Tool build();
  }

  /** Deserializes a JSON string to a Tool object. */
  @ExcludeFromGeneratedCoverageReport
  public static Tool fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Tool.class);
  }
}
