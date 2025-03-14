package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_LiveServerMessage extends LiveServerMessage {

  private final Optional<LiveServerSetupComplete> setupComplete;

  private final Optional<LiveServerContent> serverContent;

  private final Optional<LiveServerToolCall> toolCall;

  private final Optional<LiveServerToolCallCancellation> toolCallCancellation;

  private AutoValue_LiveServerMessage(
      Optional<LiveServerSetupComplete> setupComplete,
      Optional<LiveServerContent> serverContent,
      Optional<LiveServerToolCall> toolCall,
      Optional<LiveServerToolCallCancellation> toolCallCancellation) {
    this.setupComplete = setupComplete;
    this.serverContent = serverContent;
    this.toolCall = toolCall;
    this.toolCallCancellation = toolCallCancellation;
  }

  @JsonProperty("setupComplete")
  @Override
  public Optional<LiveServerSetupComplete> setupComplete() {
    return setupComplete;
  }

  @JsonProperty("serverContent")
  @Override
  public Optional<LiveServerContent> serverContent() {
    return serverContent;
  }

  @JsonProperty("toolCall")
  @Override
  public Optional<LiveServerToolCall> toolCall() {
    return toolCall;
  }

  @JsonProperty("toolCallCancellation")
  @Override
  public Optional<LiveServerToolCallCancellation> toolCallCancellation() {
    return toolCallCancellation;
  }

  @Override
  public String toString() {
    return "LiveServerMessage{"
        + "setupComplete=" + setupComplete + ", "
        + "serverContent=" + serverContent + ", "
        + "toolCall=" + toolCall + ", "
        + "toolCallCancellation=" + toolCallCancellation
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof LiveServerMessage) {
      LiveServerMessage that = (LiveServerMessage) o;
      return this.setupComplete.equals(that.setupComplete())
          && this.serverContent.equals(that.serverContent())
          && this.toolCall.equals(that.toolCall())
          && this.toolCallCancellation.equals(that.toolCallCancellation());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= setupComplete.hashCode();
    h$ *= 1000003;
    h$ ^= serverContent.hashCode();
    h$ *= 1000003;
    h$ ^= toolCall.hashCode();
    h$ *= 1000003;
    h$ ^= toolCallCancellation.hashCode();
    return h$;
  }

  @Override
  public LiveServerMessage.Builder toBuilder() {
    return new AutoValue_LiveServerMessage.Builder(this);
  }

  static final class Builder extends LiveServerMessage.Builder {
    private Optional<LiveServerSetupComplete> setupComplete = Optional.empty();
    private Optional<LiveServerContent> serverContent = Optional.empty();
    private Optional<LiveServerToolCall> toolCall = Optional.empty();
    private Optional<LiveServerToolCallCancellation> toolCallCancellation = Optional.empty();
    Builder() {
    }
    Builder(LiveServerMessage source) {
      this.setupComplete = source.setupComplete();
      this.serverContent = source.serverContent();
      this.toolCall = source.toolCall();
      this.toolCallCancellation = source.toolCallCancellation();
    }
    @Override
    public LiveServerMessage.Builder setupComplete(LiveServerSetupComplete setupComplete) {
      this.setupComplete = Optional.of(setupComplete);
      return this;
    }
    @Override
    public LiveServerMessage.Builder serverContent(LiveServerContent serverContent) {
      this.serverContent = Optional.of(serverContent);
      return this;
    }
    @Override
    public LiveServerMessage.Builder toolCall(LiveServerToolCall toolCall) {
      this.toolCall = Optional.of(toolCall);
      return this;
    }
    @Override
    public LiveServerMessage.Builder toolCallCancellation(LiveServerToolCallCancellation toolCallCancellation) {
      this.toolCallCancellation = Optional.of(toolCallCancellation);
      return this;
    }
    @Override
    public LiveServerMessage build() {
      return new AutoValue_LiveServerMessage(
          this.setupComplete,
          this.serverContent,
          this.toolCall,
          this.toolCallCancellation);
    }
  }

}
