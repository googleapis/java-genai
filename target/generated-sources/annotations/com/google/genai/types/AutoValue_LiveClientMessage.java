package com.google.genai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_LiveClientMessage extends LiveClientMessage {

  private final Optional<LiveClientSetup> setup;

  private final Optional<LiveClientContent> clientContent;

  private final Optional<LiveClientRealtimeInput> realtimeInput;

  private final Optional<LiveClientToolResponse> toolResponse;

  private AutoValue_LiveClientMessage(
      Optional<LiveClientSetup> setup,
      Optional<LiveClientContent> clientContent,
      Optional<LiveClientRealtimeInput> realtimeInput,
      Optional<LiveClientToolResponse> toolResponse) {
    this.setup = setup;
    this.clientContent = clientContent;
    this.realtimeInput = realtimeInput;
    this.toolResponse = toolResponse;
  }

  @JsonProperty("setup")
  @Override
  public Optional<LiveClientSetup> setup() {
    return setup;
  }

  @JsonProperty("clientContent")
  @Override
  public Optional<LiveClientContent> clientContent() {
    return clientContent;
  }

  @JsonProperty("realtimeInput")
  @Override
  public Optional<LiveClientRealtimeInput> realtimeInput() {
    return realtimeInput;
  }

  @JsonProperty("toolResponse")
  @Override
  public Optional<LiveClientToolResponse> toolResponse() {
    return toolResponse;
  }

  @Override
  public String toString() {
    return "LiveClientMessage{"
        + "setup=" + setup + ", "
        + "clientContent=" + clientContent + ", "
        + "realtimeInput=" + realtimeInput + ", "
        + "toolResponse=" + toolResponse
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof LiveClientMessage) {
      LiveClientMessage that = (LiveClientMessage) o;
      return this.setup.equals(that.setup())
          && this.clientContent.equals(that.clientContent())
          && this.realtimeInput.equals(that.realtimeInput())
          && this.toolResponse.equals(that.toolResponse());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= setup.hashCode();
    h$ *= 1000003;
    h$ ^= clientContent.hashCode();
    h$ *= 1000003;
    h$ ^= realtimeInput.hashCode();
    h$ *= 1000003;
    h$ ^= toolResponse.hashCode();
    return h$;
  }

  @Override
  public LiveClientMessage.Builder toBuilder() {
    return new AutoValue_LiveClientMessage.Builder(this);
  }

  static final class Builder extends LiveClientMessage.Builder {
    private Optional<LiveClientSetup> setup = Optional.empty();
    private Optional<LiveClientContent> clientContent = Optional.empty();
    private Optional<LiveClientRealtimeInput> realtimeInput = Optional.empty();
    private Optional<LiveClientToolResponse> toolResponse = Optional.empty();
    Builder() {
    }
    Builder(LiveClientMessage source) {
      this.setup = source.setup();
      this.clientContent = source.clientContent();
      this.realtimeInput = source.realtimeInput();
      this.toolResponse = source.toolResponse();
    }
    @Override
    public LiveClientMessage.Builder setup(LiveClientSetup setup) {
      this.setup = Optional.of(setup);
      return this;
    }
    @Override
    public LiveClientMessage.Builder clientContent(LiveClientContent clientContent) {
      this.clientContent = Optional.of(clientContent);
      return this;
    }
    @Override
    public LiveClientMessage.Builder realtimeInput(LiveClientRealtimeInput realtimeInput) {
      this.realtimeInput = Optional.of(realtimeInput);
      return this;
    }
    @Override
    public LiveClientMessage.Builder toolResponse(LiveClientToolResponse toolResponse) {
      this.toolResponse = Optional.of(toolResponse);
      return this;
    }
    @Override
    public LiveClientMessage build() {
      return new AutoValue_LiveClientMessage(
          this.setup,
          this.clientContent,
          this.realtimeInput,
          this.toolResponse);
    }
  }

}
