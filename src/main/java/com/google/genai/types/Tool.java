// Auto-generated code. Do not edit.

package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.genai.JsonSerializable;
import java.util.List;
import java.util.Optional;

/** Tool details of a tool that the model may use to generate a response. */
@AutoValue
@JsonDeserialize(builder = AutoValue_Tool.Builder.class)
public abstract class Tool extends JsonSerializable {
  /** List of function declarations that the tool supports. */
  public abstract Optional<List<FunctionDeclaration>> getFunctionDeclarations();

  /**
   * Optional. Retrieval tool type. System will always execute the provided retrieval tool(s) to get
   * external knowledge to answer the prompt. Retrieval results are presented to the model for
   * generation.
   */
  public abstract Optional<Retrieval> getRetrieval();

  /**
   * Optional. Google Search tool type. Specialized retrieval tool that is powered by Google Search.
   */
  public abstract Optional<GoogleSearch> getGoogleSearch();

  /**
   * Optional. GoogleSearchRetrieval tool type. Specialized retrieval tool that is powered by Google
   * search.
   */
  public abstract Optional<GoogleSearchRetrieval> getGoogleSearchRetrieval();

  /**
   * Optional. CodeExecution tool type. Enables the model to execute code as part of generation.
   * This field is only used by the Gemini Developer API services.
   */
  public abstract Optional<ToolCodeExecution> getCodeExecution();

  /** Instantiates a builder for Tool. */
  public static Builder builder() {
    return new AutoValue_Tool.Builder();
  }

  /** Creates a builder with the same values as this instance. */
  public abstract Builder toBuilder();

  /** Builder for Tool. */
  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("functionDeclarations")
    public abstract Builder setFunctionDeclarations(List<FunctionDeclaration> functionDeclarations);

    @JsonProperty("retrieval")
    public abstract Builder setRetrieval(Retrieval retrieval);

    @JsonProperty("googleSearch")
    public abstract Builder setGoogleSearch(GoogleSearch googleSearch);

    @JsonProperty("googleSearchRetrieval")
    public abstract Builder setGoogleSearchRetrieval(GoogleSearchRetrieval googleSearchRetrieval);

    @JsonProperty("codeExecution")
    public abstract Builder setCodeExecution(ToolCodeExecution codeExecution);

    public abstract Tool build();
  }

  /** Deserializes a JSON string to a Tool object. */
  public static Tool fromJson(String jsonString) {
    return JsonSerializable.fromJsonString(jsonString, Tool.class);
  }
}
