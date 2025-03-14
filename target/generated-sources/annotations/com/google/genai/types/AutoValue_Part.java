package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_Part extends Part {

  private final Optional<VideoMetadata> videoMetadata;

  private final Optional<Boolean> thought;

  private final Optional<CodeExecutionResult> codeExecutionResult;

  private final Optional<ExecutableCode> executableCode;

  private final Optional<FileData> fileData;

  private final Optional<FunctionCall> functionCall;

  private final Optional<FunctionResponse> functionResponse;

  private final Optional<Blob> inlineData;

  private final Optional<String> text;

  private AutoValue_Part(
      Optional<VideoMetadata> videoMetadata,
      Optional<Boolean> thought,
      Optional<CodeExecutionResult> codeExecutionResult,
      Optional<ExecutableCode> executableCode,
      Optional<FileData> fileData,
      Optional<FunctionCall> functionCall,
      Optional<FunctionResponse> functionResponse,
      Optional<Blob> inlineData,
      Optional<String> text) {
    this.videoMetadata = videoMetadata;
    this.thought = thought;
    this.codeExecutionResult = codeExecutionResult;
    this.executableCode = executableCode;
    this.fileData = fileData;
    this.functionCall = functionCall;
    this.functionResponse = functionResponse;
    this.inlineData = inlineData;
    this.text = text;
  }

  @JsonProperty("videoMetadata")
  @Override
  public Optional<VideoMetadata> videoMetadata() {
    return videoMetadata;
  }

  @JsonProperty("thought")
  @Override
  public Optional<Boolean> thought() {
    return thought;
  }

  @JsonProperty("codeExecutionResult")
  @Override
  public Optional<CodeExecutionResult> codeExecutionResult() {
    return codeExecutionResult;
  }

  @JsonProperty("executableCode")
  @Override
  public Optional<ExecutableCode> executableCode() {
    return executableCode;
  }

  @JsonProperty("fileData")
  @Override
  public Optional<FileData> fileData() {
    return fileData;
  }

  @JsonProperty("functionCall")
  @Override
  public Optional<FunctionCall> functionCall() {
    return functionCall;
  }

  @JsonProperty("functionResponse")
  @Override
  public Optional<FunctionResponse> functionResponse() {
    return functionResponse;
  }

  @JsonProperty("inlineData")
  @Override
  public Optional<Blob> inlineData() {
    return inlineData;
  }

  @JsonProperty("text")
  @Override
  public Optional<String> text() {
    return text;
  }

  @Override
  public String toString() {
    return "Part{"
        + "videoMetadata=" + videoMetadata + ", "
        + "thought=" + thought + ", "
        + "codeExecutionResult=" + codeExecutionResult + ", "
        + "executableCode=" + executableCode + ", "
        + "fileData=" + fileData + ", "
        + "functionCall=" + functionCall + ", "
        + "functionResponse=" + functionResponse + ", "
        + "inlineData=" + inlineData + ", "
        + "text=" + text
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof Part) {
      Part that = (Part) o;
      return this.videoMetadata.equals(that.videoMetadata())
          && this.thought.equals(that.thought())
          && this.codeExecutionResult.equals(that.codeExecutionResult())
          && this.executableCode.equals(that.executableCode())
          && this.fileData.equals(that.fileData())
          && this.functionCall.equals(that.functionCall())
          && this.functionResponse.equals(that.functionResponse())
          && this.inlineData.equals(that.inlineData())
          && this.text.equals(that.text());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= videoMetadata.hashCode();
    h$ *= 1000003;
    h$ ^= thought.hashCode();
    h$ *= 1000003;
    h$ ^= codeExecutionResult.hashCode();
    h$ *= 1000003;
    h$ ^= executableCode.hashCode();
    h$ *= 1000003;
    h$ ^= fileData.hashCode();
    h$ *= 1000003;
    h$ ^= functionCall.hashCode();
    h$ *= 1000003;
    h$ ^= functionResponse.hashCode();
    h$ *= 1000003;
    h$ ^= inlineData.hashCode();
    h$ *= 1000003;
    h$ ^= text.hashCode();
    return h$;
  }

  @Override
  public Part.Builder toBuilder() {
    return new AutoValue_Part.Builder(this);
  }

  static final class Builder extends Part.Builder {
    private Optional<VideoMetadata> videoMetadata = Optional.empty();
    private Optional<Boolean> thought = Optional.empty();
    private Optional<CodeExecutionResult> codeExecutionResult = Optional.empty();
    private Optional<ExecutableCode> executableCode = Optional.empty();
    private Optional<FileData> fileData = Optional.empty();
    private Optional<FunctionCall> functionCall = Optional.empty();
    private Optional<FunctionResponse> functionResponse = Optional.empty();
    private Optional<Blob> inlineData = Optional.empty();
    private Optional<String> text = Optional.empty();
    Builder() {
    }
    Builder(Part source) {
      this.videoMetadata = source.videoMetadata();
      this.thought = source.thought();
      this.codeExecutionResult = source.codeExecutionResult();
      this.executableCode = source.executableCode();
      this.fileData = source.fileData();
      this.functionCall = source.functionCall();
      this.functionResponse = source.functionResponse();
      this.inlineData = source.inlineData();
      this.text = source.text();
    }
    @Override
    public Part.Builder videoMetadata(VideoMetadata videoMetadata) {
      this.videoMetadata = Optional.of(videoMetadata);
      return this;
    }
    @Override
    public Part.Builder thought(boolean thought) {
      this.thought = Optional.of(thought);
      return this;
    }
    @Override
    public Part.Builder codeExecutionResult(CodeExecutionResult codeExecutionResult) {
      this.codeExecutionResult = Optional.of(codeExecutionResult);
      return this;
    }
    @Override
    public Part.Builder executableCode(ExecutableCode executableCode) {
      this.executableCode = Optional.of(executableCode);
      return this;
    }
    @Override
    public Part.Builder fileData(FileData fileData) {
      this.fileData = Optional.of(fileData);
      return this;
    }
    @Override
    public Part.Builder functionCall(FunctionCall functionCall) {
      this.functionCall = Optional.of(functionCall);
      return this;
    }
    @Override
    public Part.Builder functionResponse(FunctionResponse functionResponse) {
      this.functionResponse = Optional.of(functionResponse);
      return this;
    }
    @Override
    public Part.Builder inlineData(Blob inlineData) {
      this.inlineData = Optional.of(inlineData);
      return this;
    }
    @Override
    public Part.Builder text(String text) {
      this.text = Optional.of(text);
      return this;
    }
    @Override
    public Part build() {
      return new AutoValue_Part(
          this.videoMetadata,
          this.thought,
          this.codeExecutionResult,
          this.executableCode,
          this.fileData,
          this.functionCall,
          this.functionResponse,
          this.inlineData,
          this.text);
    }
  }

}
